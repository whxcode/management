package com.cn.scitc.web.controller.admin;

import com.cn.scitc.entity.SysUser;
import com.cn.scitc.repository.SysUserRepository;
import com.cn.scitc.service.UserService;
import com.cn.scitc.utils.CommUtil;
import com.cn.scitc.utils.MD5Util;
import com.cn.scitc.utils.QueryMap;
import com.cn.scitc.utils.SpecificationTools;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class UserListController {

    private String studentName;

    private String studentUsername;

    private String studentClasses;

    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private UserService userService;

    @ApiOperation("用户列表页")
    @RequestMapping("/show/page/list")
    public String getlinkList(@RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                              @RequestParam(value = "size",defaultValue = "5",required = false) Integer size, Model model) {

        Sort sort = new Sort(Sort.Direction.ASC,"id");//排序
        Pageable pageable = PageRequest.of(page,size,sort);//pageable 请求分页参数
        Page<SysUser> datas = userRepository.findAll(pageable);
        int totalElements = (int) datas.getTotalElements(); //总条数
        int totalPages = datas.getTotalPages(); // 总页数
        int number = datas.getNumber();
        boolean isfrist = datas.isFirst();
        boolean islast = datas.isLast();


        List<SysUser> content = datas.getContent();

        model.addAttribute("content", content);
        model.addAttribute("datas", datas);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("number", number);
        model.addAttribute("isfrist", isfrist);
        model.addAttribute("islast", islast);
        return "admin/tables";

    }


    @ApiModelProperty("用户列表查询页")
    @RequestMapping("/show/qbc/list")
    public String findUsernameAndNickname(@RequestParam(value = "username", required = false, defaultValue = "") String username,
                                          @RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
                                          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                                          Model model) {

        if (!" ".equals(nickname)) {
            this.studentName = nickname;
        }

        if (!" ".equals(username)){
            this.studentUsername = username;
        }



        if (username == null) {
            username = "";
        }



        if (nickname == null) {
            nickname = "";
        }

//        SpecificationTools<SysUser> queryList = new SpecificationTools<SysUser>(SysUser.class);
//        if (CommUtil.null2String(sec).equals("")){
//            queryList.setandsec(new QueryMap("username",sec,"like"));
//            queryList.setandsec(new QueryMap("nickname",sec,"like"));
//            queryList.setandsec(new QueryMap("classes",sec,"like"));
//        }


        Sort sort = new Sort(Sort.Direction.ASC,"id");//排序
        Pageable pageable = PageRequest.of(page,size,sort);//pageable 请求分页参数
        Page<SysUser> datas = userRepository.findByOrUsernameLikeOrNicknameLike(username, nickname,pageable);
        //获取总条数
        int totalElements = (int) datas.getTotalElements();
        int totalPages = datas.getTotalPages();
        int number = datas.getNumber();
        boolean isfrist = datas.isFirst();
        boolean islast = datas.isLast();
        List<SysUser> result = datas.getContent();
        model.addAttribute("result", result);
        model.addAttribute("datas", datas);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("number", number);
        model.addAttribute("isfrist", isfrist);
        model.addAttribute("islast", islast);

        model.addAttribute("username", username);
        model.addAttribute("nickname", nickname);
        return "admin/tables";


    }


    @ApiOperation(value = "用户注册")
    @RequestMapping("/toAdd")
    public String addUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "username")
            String usernmae,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "nickname") String nickname,
                          @RequestParam(value = "classes")String classes,
                          @RequestParam(value = "gender") String gender,
                          @RequestParam(value = "names") String names
                         )
                         {
        SysUser result = userService.regiserByUsername(usernmae, password, nickname,classes,gender,names);


        try {
            request.getRequestDispatcher("/admin/show/page/list").forward(request, response);
            return null;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;

}


    @RequestMapping("/user/register")
    public String UserAdd(){

        return "admin/userAdd";
    }


    @RequestMapping("/toUserEdit")
    public String toUserEdit(Model model, Long id){
        SysUser user = userRepository.getOne(id);

        user.setPassword(MD5Util.encode(user.getPassword()));


         userService.save(user);
        model.addAttribute("user",user);
        return "admin/userEdit";

    }

    @RequestMapping("/edit")
    public String edit(SysUser user){
        userService.save(user);
        return "redirect:/admin/show/page/list";
    }





    /**
     *类型转换
     * @param binder
     * @param request
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
