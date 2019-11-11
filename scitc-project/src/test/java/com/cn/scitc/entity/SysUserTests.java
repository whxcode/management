//package com.cn.scitc.entity;
//
//import com.cn.scitc.ApplicationTests;
//import com.cn.scitc.repository.*;
//import com.cn.scitc.service.UserService;
//import com.cn.scitc.service.student.SchoolUserInfoService;
//import com.cn.scitc.utils.MD5Util;
//
//import com.cn.scitc.utils.PageableTools;
//import com.cn.scitc.utils.SortDto;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import javax.persistence.EntityManager;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class SysUserTests extends ApplicationTests {
//    @Autowired
//    private SysUserRepository repository;
//
//    @Autowired
//    private SysRoleRepository sysRoleRepository;
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private SysUserRepository userRepository;
//
//    @Autowired
//    private ExcelRepository excelRepository;
//    @Autowired
//    private WebApplicationContext wac;
//    @Autowired
//    private SchoolUserInfoRepository userInfoRepository;
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private SchoolUserInfoService schoolUserInfoService;
//
//    @Autowired
//   private EntityManager entityManager;
//
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//
//    @Test
//    public void AddUser() {
//
//
//        //用户表
//        SysUser user = new SysUser();
//        user.setId(user.getId());
//        user.setNickname("开发者");
//        user.setUsername("18898890");
//        user.setGender("男");
//        user.setClasses("软件17-2");
//        Date now = new Date();
//        user.setCreate_time(now);
//        user.setLast_login_time(now);
//        user.setLast_update_time(now);
//        user.setPassword(MD5Util.encode("qwe86314"));
//        //角色表
//        SysRole role = new SysRole();
//        role.setId(role.getId());
//        role.setName("ROLE_ADMIN");
//        role = sysRoleRepository.save(role);
//        //中介表
//        user.getRoles().add(role);
//        user= repository.save(user);
//
//
//
//    }
//
//    @Test
//    public void register() throws Exception{
//        String username = "17302232";
//        String password = "123456";
//        String nickname = "阿三哥";
//        Date date = new Date();
//        System.out.println(date.getTime());
//        String content = "{\"username\":\"17302293\",\"password\":123456,\"nickname\":\"德玛西亚之力\"}";
//        String reuslt = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(content))
//                .andReturn().getResponse().getContentAsString();
//
//        System.out.println(reuslt);
//
//
//
//    }
//
//
//    @Test
//    public  void showpage(){
//        Pageable pageable = new PageRequest(45,15);
//        Page<SysUser> datas =  repository.findAll(pageable);
//        System.out.println("总条数:" + datas.getTotalElements());
//        System.out.println("总页数:" +datas.getTotalPages());
//        for (SysUser data : datas) {
//            System.out.println(data.getId() + "======" + data.getNickname());
//
//        }
//
//
//    }
//
//    private void print(Page<SysUser> datas) {
//        System.out.println("总条数："+datas.getTotalElements());
//        System.out.println("总页数："+datas.getTotalPages());
//
//        for (SysUser data : datas) {
//            System.out.println(data.getId() + "======" + data.getNickname());
//        }
//    }
//
//    @Test
//    public  void showlist2(){
//        Page<SysUser> datas = repository.findAll(PageableTools.basicPage(0));
//        print(datas);
//    }
//
//    @Test
//    public  void showlist3(){
//        Page<SysUser> datas = repository.findAll(PageableTools.basicPage(10,11));
//        print(datas);
//    }
//
//    @Test
//    public  void showlist4(){
//        Page<SysUser> datas = repository.findAll(PageableTools.basicPage(0,10,new SortDto("id")));
//        print(datas);
//    }
//
//   @Test
//    public  void showlist5(){
//        Page<SysUser> datas = repository.findAll(PageableTools.basicPage(1, 5, new SortDto("id")));
//        print(datas);
//
//
//       Page<SysUser> datas2 = repository.findAll(PageableTools.basicPage(1, 5, new SortDto("ASC", "id")));
//       print(datas2);
//
//    }
//
//
//    @Test
//    public  void lambid(){
//
//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//
//    }
//
//
//
//
//@Test
//
//    public void fun3(){
//        String type= "stay";
//    System.out.println(excelRepository.findByType(type));
//
//}
//
//
//
//
//
////@Test
////    public void  fun6(){
////        SysUser user = new SysUser();
////    Long id =1L;
////
////    try {
////
////        Page<SysUser> users = finisShedReposity.getById(PageableTools.basicPage(0,5));
////
////        if (users == null ){
////            System.out.println("user null !!!!!!");
////        }
////        System.out.println("11111111111:" + users.getSize() );
////    }catch (Exception e){
////
////        System.out.println(e.toString());
////    }
////
////
////}
//
// @Test
//    public void findId(){
//     System.out.println(schoolUserInfoService.findCompleteOne());
// }
//
//}
