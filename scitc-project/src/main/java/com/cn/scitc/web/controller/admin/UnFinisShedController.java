package com.cn.scitc.web.controller.admin;

import com.cn.scitc.entity.SysUser;
import com.cn.scitc.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin")
public class UnFinisShedController {
    @Autowired
    private SysUserRepository sysUserRepository;
    private String studentClasses;
    private String studentName;
    private String studentUsername;

    @RequestMapping("show/unfinshed/all")

    public String getUnfinShedAll(@RequestParam(value = "page",defaultValue = "0",required = false
    )
            Integer page, @RequestParam(value = "size",defaultValue = "5",required = false) Integer size, Model model){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<SysUser> datas = sysUserRepository.findAll(pageable);



        int totalElements = (int) datas.getTotalElements(); //总条数
        int totalPages =  datas.getTotalPages(); // 总页数
        int number = datas.getNumber();
        boolean isfrist = datas.isFirst();
        boolean islast = datas.isLast();
        List<SysUser> content = datas.getContent();
        model.addAttribute("content",content);
        model.addAttribute("datas",datas);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("number",number);
        model.addAttribute("isfrist",isfrist);
        model.addAttribute("islast",islast);
        return "admin/unFinisShed";

   }

   @RequestMapping("show/unfinshed/qbc")
    public String getUnfinShedQbc(@RequestParam(value = "nickname",defaultValue = "",required = false) String nickname,
                                  @RequestParam(value = "username",defaultValue = "",required = false) String username,
                                  @RequestParam(value = "classes", defaultValue = "",required = false) String classes,
                                  @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                                  @RequestParam(value = "size",defaultValue = "5",required = false) Integer size,Model model){
       if(!"".equals(classes)){
           this.studentClasses = classes;
       }

       if (!"".equals(nickname)){
           this.studentName = nickname;
       }

       if (!"".equals(username)){
           this.studentUsername = username;
       }

       Sort sort = new Sort(Sort.Direction.ASC,"id");
       Pageable pageable = PageRequest.of(page,size,sort);
       Page<SysUser> datas = sysUserRepository.findByNicknameLike(nickname,username,studentClasses,pageable);

       System.out.println("studentName:" + studentName + ", " + "studentUsername:"+ studentUsername +  "," + "studentClasses:" + studentClasses);
       int totalElements = (int) datas.getTotalElements(); //总条数
       int totalPages =  datas.getTotalPages(); // 总页数
       int number = datas.getNumber();
       boolean isfrist = datas.isFirst();
       boolean islast = datas.isLast();
       List<SysUser> content = datas.getContent();
       model.addAttribute("content",content);
       model.addAttribute("datas",datas);
       model.addAttribute("page",page);
       model.addAttribute("size",size);
       model.addAttribute("totalPages",totalPages);
       model.addAttribute("totalElements",totalElements);
       model.addAttribute("number",number);
       model.addAttribute("isfrist",isfrist);
       model.addAttribute("islast",islast);
       model.addAttribute("nickname",nickname);
       model.addAttribute("username",username);
       model.addAttribute("classes",classes);
       return "admin/unFinisShed";

   }



}
