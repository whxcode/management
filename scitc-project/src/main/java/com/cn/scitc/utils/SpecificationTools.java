package com.cn.scitc.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
/**
 * 
 * @ClassName:  SpecificationTools   
 * @Description:查询器生成 
 * @author: hqc
 * @date:   2018年12月5日   
 *   
 * @param <T>  
 *
 */
public class SpecificationTools<T> {  
	private static Logger logger = LoggerFactory.getLogger(SpecificationTools.class);
	protected Class<T> entityClass;//记录泛型

	private List<QueryMap> andsec = new ArrayList<>();//单逻辑查询数据
	
	
	private List<QueryMap> orsec = new ArrayList<>();//or关系查询数据
	/**
	 * 构造器
	 * @param tClass 泛型的class
	 */
	public SpecificationTools(Class<T> tClass) {
		this.entityClass = tClass;
	}

    /**
	     * 建立分页排序请求  
	* @param page 页码
	* @param size 每页记录数
	* @param direction 排序
	* @param column 根据某个字段排序
	* @param @return  参数说明 
	* @throws 
	* @return PageRequest    返回类型 
	*/
     public PageRequest buildPageRequest( int page, int size, Sort.Direction direction,String column) {  
    	 	Sort sort =new Sort(direction,column);  
    	 	page = CommUtil.null2Int(page) <= 0 ? 0 : page - 1;
           return PageRequest.of(page,size, sort);  
     } 
     /**
                * 建立分页排序请求  
     * @param page 页码
     * @param size 每页记录数，默认12
     * @param direction 排序，默认desc
     * @param column 根据某个字段排序，默认addtime
     * @param @return  参数说明 
     * @throws 
     * @return PageRequest    返回类型 
      */
     public PageRequest buildPageRequest(int page) {  
    	 Sort sort =new Sort(Sort.Direction.DESC,"addTime");  
    	 page = CommUtil.null2Int(page) <= 0 ? 0 : page - 1;
    	 return PageRequest.of(page,12, sort);  
     }
     /**
	      * 建立分页排序请求  
	* @param page 页码
	* @param size 每页记录数
	* @param direction 排序，默认desc
	* @param column 根据某个字段排序，默认addtime
	* @param @return  参数说明 
	* @throws 
	* @return PageRequest    返回类型 
	*/
     public PageRequest buildPageRequest(int page, int size) {  
 	 	Sort sort =new Sort(Sort.Direction.DESC,"addTime");
 	 	page = CommUtil.null2Int(page) <= 0 ? 0 : page - 1;
        return PageRequest.of(page,size, sort);  
     } 
     /**
	      * 建立分页排序请求  
	* @param page 页码
	* @param size 每页记录数，默认12
	* @param direction 排序，默认desc
	* @param column 根据某个字段排序，默认addtime
	* @param @return  参数说明 
	* @throws 
	* @return PageRequest    返回类型 
	*/
     public PageRequest buildPageRequest(int page,String column) {  
    	 Sort sort =new Sort(Sort.Direction.DESC,column);  
    	 page = CommUtil.null2Int(page) <= 0 ? 0 : page - 1;
    	 return PageRequest.of(page,12, sort);  
     }
     /**
	      * 建立分页排序请求  
	* @param page 页码
	* @param size 每页记录数，默认12
	* @param direction 排序desc ,asc 使用Sort.Direction.ASC
	* @param column 根据某个字段排序，默认addtime
	* @param @return  参数说明 
	* @throws 
	* @return PageRequest    返回类型 
	*/
     public PageRequest buildPageRequest(int page,Sort.Direction direction) { 

         page = CommUtil.null2Int(page) <= 0 ? 0 : page - 1;
         
    	 Sort sort =new Sort(direction,"addTime");  
    	 
    	 return PageRequest.of(page,12, sort);  
     }
     /**
      * 建立分页排序请求  
	* @param page 页码
	* @param size 每页记录数，默认12
	* @param direction 排序desc ,asc 使用Sort.Direction.ASC
	* @param column 根据某个字段排序
	* @param @return  参数说明 
	* @throws 
	* @return PageRequest    返回类型 
	*/
     public PageRequest buildPageRequest( int page, Sort.Direction direction, String column) {  
 	 	Sort sort =new Sort(direction,column);  
 	 	page = CommUtil.null2Int(page) <= 0 ? 0 : page - 1;
        return PageRequest.of(page,12, sort);  
     }
     /**
	      * 添加一个查询条件
	      * 类似于：
	  *		where 1=1 and map.key =:map.value
	  *		=等效于map.getNexus
      * @Title: setandsec    
      * @param: @param and            
      */
     public void setandsec(QueryMap and) {
    	 this.andsec.add(and);
     }
     /**
	      * 添加一个查询条件
	      * 类似于：
	  *		where 1=1 and ( map.key =:map.value or map1.key =:map1.value)
	  *		=等效于map.getNexus
	  * @Title: setandsec    
	  * @param: @param and            
	  */
	 public void setorsec(QueryMap or) {
		 this.orsec.add(or);
	 }
 	/**
	 *创建查询条件
	 *<p>目前只支持= ,!=,<,<=,>,>=,like </p>
	    * 类似：
	 * <p>  select                  </p>
	 * <p>		obj					</p>
	 * <p>  from					</p>
	 * <p>       entity obj 		</p>
	 * <p>  where					</p>	
	 * <p>     		obj.parameter1=?</p>
	 * <p>      and 				</p>	
	 * <p>  		obj.parameter2=?</p>
	 * <p>      and 				</p>
	 * <p>	  ( 	obj.parameter1=?</p>
	 * <p>      or 					</p>
	 * <p>			obj.parameter2=?</p>
	 * <p>	  )						</p>
	 * @return: Specification<T>      
	 */  
     public Specification<T> query() {
         Specification<T> specification = new Specification<T>() {
             /**   
			 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
			 */
			private static final long serialVersionUID = 160277887508809289L;

			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(andsec.isEmpty() && orsec.isEmpty()) {
					return null;
				} 
				List<Predicate> predicates = new ArrayList<Predicate>();
                 Iterator<QueryMap> iter = andsec.iterator();
                 while(iter.hasNext()){
                  QueryMap and = iter.next();
                  logger.info("the key is:"+and.getKey()+",and the value is:"+and.getValue()+",and the Nexus is:"+and.getNexus());                 
                  predicates.add(getPredicate( and, root,  cb) );                 
                 } 
                 Iterator<QueryMap> oriter = orsec.iterator();
                 List<Predicate> orPredicates = new ArrayList<Predicate>();
                 while(oriter.hasNext()){
                  QueryMap or = oriter.next();
                  logger.info("the key is:"+or.getKey()+",and the value is:"+or.getValue()+",and the Nexus is:"+or.getNexus());                 
                  Predicate orPredicate = cb.or(getPredicate( or, root,  cb) );
                  orPredicates.add(orPredicate);
                 } 
//                 if (Param.getName() != null && !"".equals(Param.getName())) {
//                     predicates.add(cb.equal(root.<String>get("name"), Param.getName()));
//                 }
//                 if (Param.getStartDate() != null && !"".equals(Param.getStartDate())) {
//                     predicates.add(cb.greaterThan(root.<String>get("birthday"), Param.getStartDate()));
//                 }
//                 if (Param.getEndDate() != null && !"".equals(Param.getEndDate())) {
//                     predicates.add(cb.lessThan(root.<String>get("birthday"), Param.getEndDate()));
//                 }
//                 //定义or的条件数组
//                 List<Predicate> orPredicates = new ArrayList<Predicate>();
//                 if (Param.getSchool() != null && !"".equals(Param.getSchool())) {
//                     orPredicates.add(cb.equal(root.<String>get("school"),  Param.getSchool()));
//                 }
//                 if (Param.getAddress() != null && !"".equals(Param.getAddress())) {
//                     orPredicates.add(cb.equal(root.<String>get("address"), Param.getAddress()));
//                 }
                 if(orsec.isEmpty()) {
                	 return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                 }
                 //生成or的查询表达式
                 Predicate orPredicate = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
                 //与and结合起来
                 predicates.add(orPredicate);
                 return cb.and(predicates.toArray(new Predicate[predicates.size()]));
             }
         };
         return specification;
     }
     /**
                   * 简单处理查询条件生成器
      * @Title: getPredicate    
      * @param: @param and
      * @param: @param root
      * @param: @param cb
      * @param: @return            
      */
     private Predicate getPredicate(QueryMap and,Root<T> root, CriteriaBuilder cb) {
    	 	String parameterTypes="";
			// 此处应该判断beanObj,property不为null
			PropertyDescriptor pd = null;
			try {
				pd = new PropertyDescriptor(and.getKey(), this.entityClass);
			} catch (IntrospectionException e) {
				logger.info("参数出错");
			}
			//通过获取该字段的set方法来获得该字段的类型
			Method setMethod = pd.getWriteMethod();
			if (setMethod != null) {
				 parameterTypes = setMethod.getParameterTypes()[0].getTypeName();
			}
			//因为获取的是包装类型 所以需要替换下
			parameterTypes = parameterTypes.replaceAll("java.lang.", "");
			Object[] objs = processingType(and, root, parameterTypes);
		 switch (and.getNexus().toString()) {
			case "=":
				return cb.equal((Path<?>)objs[0], objs[1]);
			case "!=":
				return cb.notEqual((Path<?>)objs[0], objs[1]);
			case ">":
				return cb.gt(root.<Number>get(and.getKey()), (Number)and.getValue());
			case "<":
				return cb.lt(root.<Number>get(and.getKey()), (Number)and.getValue());
			case "<=":
				return cb.le(root.<Number>get(and.getKey()), (Number)and.getValue());
			case ">=":
				return cb.ge(root.<Number>get(and.getKey()), (Number)and.getValue());
			case "like":
				return cb.like(root.<String>get(and.getKey()), (String)"%"+and.getValue()+"%");
		  }                 
    	 return null;
     }
     /**
      * 确认泛型类型,根据field类型返回对应的处理方法
     * @param @param and
     * @param @param root
     * @param @param field
     * @param @return  参数说明 
     * @throws 
     * @return Object[]    返回类型 
      */
     private Object[] processingType(QueryMap and,Root<T> root,String field) {
    	 		Object[] objs=new Object[2];
		 		switch (field) {
				case "int":
					objs[0]=root.<Integer>get(and.getKey());
					objs[1]=Integer.parseInt(and.getValue().toString());
					break;
				case "Integer":
					objs[0]=root.<Integer>get(and.getKey());
					objs[1]=Integer.parseInt(and.getValue().toString());
					break;
				case "boolean":
					objs[0]=root.<Boolean>get(and.getKey());
					objs[1]=Boolean.parseBoolean(and.getValue().toString());
					break;
				case "Boolean":
					objs[0]=root.<Boolean>get(and.getKey());
					objs[1]=Boolean.parseBoolean(and.getValue().toString());
					break;
				case "String":
					objs[0]=root.<String>get(and.getKey());
					objs[1]=and.getValue().toString();
					break;
				case "Long":
					objs[0]=root.<Long>get(and.getKey());
					objs[1]=Long.parseLong(and.getValue().toString());
					break;
				case "long":
					objs[0]=root.<Long>get(and.getKey());
					objs[1]=Long.parseLong(and.getValue().toString());
					break;
				case "byte":
					objs[0]=root.<Byte>get(and.getKey());
					objs[1]=new Byte(and.getValue().toString());
					break;
				case "Byte":
					objs[0]=root.<Byte>get(and.getKey());
					objs[1]=new Byte(and.getValue().toString());
					break;
			}
		 		return objs;
     }
}  