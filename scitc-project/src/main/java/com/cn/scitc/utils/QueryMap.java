package com.cn.scitc.utils;
/**
 * 
 * @ClassName:  QueryMap   
 * @Description:自定义查询结构 
 * @author: hqc
 * @date:   2018年12月6日   
 *     
 *
 */
public class QueryMap {
	private String key;
	private Object value;
	private Object nexus;

	public QueryMap() {

	}
	/**
	 * 
	 * @param key 实体类对应的字段
	 * @param value	查询值，类型要与字段类型匹配
	 * @param nexus 查询关系，=、!=、>、<等
	 */
	public QueryMap(String key, Object value,Object nexus) {
		this.key = key;
		this.value = value;
		this.nexus = nexus;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getNexus() {
		return nexus;
	}

	public void setNexus(Object nexus) {
		this.nexus = nexus;
	}
	
}
