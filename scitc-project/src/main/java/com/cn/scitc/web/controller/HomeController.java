
package com.cn.scitc.web.controller;

import com.cn.scitc.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.scitc.message.Msg;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jswzj
 *
 */

@Controller
public class HomeController {

	
	/**
	 * 登录成功进入主页
	 * @param model
	 * @return
	 */
    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("标题", "内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "user/index";
    }



    /**
     * 登录页
     */

    @GetMapping("/login")
    public String login() {
        return "login";

    }



    /**
     * 留校表单
     * @return
     */
    @GetMapping("/student/info")
    public String staySchool(){
        return "user/staySchoolForm";
    }

    /**
     * 离校页
     * @return
     */
    @GetMapping("/student/leave")
    public String userLeave(){

        return "user/leaveSchoolForm";
    }

    /**
     * 个人信息页面
     * @return
     */
    @GetMapping("/personal/info")
    public String personalInfo(){

        return "user/selfInfo";

    }

    /**
     * 添加公告信息
     * @return
     */
    @RequestMapping("/admin/addMessage")
    public String adminAddMessage(){
        return  "admin/infoMessage";
    }

    /**
     * 表单重复提交引导页
     * @return
     */
    @GetMapping("/message")
    public String message(){
        return "message/message";
    }

    /**
     * 404页面
     * @return
     */
    @GetMapping("/404")
    public String errorNotFound() {
    	return "404";
    }
    
    @GetMapping("/403")
    public String  accessError() {
    	return "403";
    }
    
    
    /**
     * 500页面
     * @return
     */
    @GetMapping("/500")
    public String internalError() {
    	return "500";
    }
    
    /**
     * 退出页面
     * @return
     */
    @GetMapping("/logout/page")
    public String logoutPage() {
        return "logout";
    }
    

}
