package com.cn.scitc.web.controller.admin;

import com.cn.scitc.entity.UserMessageInfo;
import com.cn.scitc.repository.SchoolUserInfoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class ShowController {
    @Autowired
    private SchoolUserInfoRepository userInfoRepository;

    private String studentName;
    private String studentClasses;

    @ApiOperation("留校-离校信息列表")
    @GetMapping("/show/messageAll")
    public String getLinkList(@RequestParam(value = "type",defaultValue = "stay",required = false) String type,
                              @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                              @RequestParam(value = "size",defaultValue = "5",required = false) Integer size,
                              Model model){

        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable  = PageRequest.of(page,size,sort);
        Page<UserMessageInfo> datas = userInfoRepository.findByType(type,pageable);

        int totalElements = (int )datas.getTotalElements(); //得到总条数
        int totalPages =  datas.getTotalPages();//得到总页数
        int number = datas.getNumber();
        boolean isfrist = datas.isFirst();//是否是第一页
        boolean islast = datas.isLast();//是否是最后一页
        List<UserMessageInfo> content = datas.getContent();
        model.addAttribute("content",content);
        model.addAttribute("datas",datas);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("number",number);
        model.addAttribute("isfrist",isfrist);
        model.addAttribute("islast",islast);


        return "admin/userSchool";
    }

    @ApiOperation("留校-离校信息条件查询")
    @RequestMapping("/show/qbc/message")
    public String getSingle(@RequestParam(value = "type",required = false,defaultValue = "stay") String type,
                            @RequestParam(value = "classes",required = false,defaultValue = "") String classes,
                            @RequestParam(value = "stuName",required = false,defaultValue = "") String stuName ,
                            @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                            @RequestParam(value = "size",defaultValue = "5",required = false) Integer size,Model model){

        if(!"".equals(stuName)){
            this.studentName = stuName;
        }

        if (!"".equals(studentClasses)){
            this.studentClasses = classes;
        }

        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<UserMessageInfo> datas = userInfoRepository.findByTypeLikeAndClassesLikeOrStuNameLike(type,classes,stuName,pageable);



        if (classes == null){
            classes = "";
        }
        int totalElements = (int) datas.getTotalElements();//获取总记录数
        int totalPages = datas.getTotalPages();//获取总页数
        int number = datas.getNumber();
        boolean isfrist = datas.isFirst();
        boolean islast = datas.isLast();
        List<UserMessageInfo> result = datas.getContent();
        model.addAttribute("type",type);
        model.addAttribute("result",result);
        model.addAttribute("datas",datas);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalElements",totalElements);
        model.addAttribute("number",number);
        model.addAttribute("isfrist",isfrist);
        model.addAttribute("islast",islast);
        model.addAttribute("classes",classes);
        model.addAttribute("stuName",stuName);
        return  "admin/userSchool";
    }





}
