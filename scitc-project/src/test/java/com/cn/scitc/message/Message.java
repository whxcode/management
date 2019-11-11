//package com.cn.scitc.message;
//
//import com.cn.scitc.ApplicationTests;
//import com.cn.scitc.repository.MessageReposiotry;
//import com.cn.scitc.repository.SysUserRepository;
//import com.cn.scitc.service.UserService;
//import com.cn.scitc.utils.PageableTools;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class Message extends ApplicationTests {
//    @Autowired
//    private MessageReposiotry reposiotry;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private SysUserRepository userRepository;
//
//    @Test
//    public void fun1(){
//
//        System.out.println(reposiotry.findByIdAndTitle(PageableTools.basicPage(0,5)));
//
//    }
//
//    @Test
//    public void  fun2(){
//        System.out.println(userService.findCompleteOne(1L));
//    }
//
//
//
//
//
//}
