package com.cn.scitc.web.controller.admin;

import com.cn.scitc.base.ApiResponse;
import com.cn.scitc.repository.MessageReposiotry;
import com.cn.scitc.service.ServiceMultiResult;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.service.UserService;
import com.cn.scitc.web.dto.MessageDTO;
import com.cn.scitc.web.form.MessageForm;
import com.cn.scitc.web.form.RentSearch;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MessageController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageReposiotry messageReposiotry;

    @ApiOperation("管理员添加公告信息")
    @PostMapping("/add/message")
    @ResponseBody
    public ApiResponse addMessage(@Valid MessageForm messageForm, BindingResult bindingResult){


        if (bindingResult.hasErrors()){
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                    bindingResult.getAllErrors().get(0).getDefaultMessage(),null);
        }

        ServiceResult<MessageDTO>  result = userService.save(messageForm);

        if (result.isSuccess()){
            return ApiResponse.ofMessage(200,"公告信息添加成功,请勿重复提交");
        }

        return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
    }

    @ApiOperation("展示公告列表")
    @RequestMapping("/show/messageAll")
    public String showMessageAll(@ModelAttribute RentSearch rentSearch, Model model){

        ServiceMultiResult<MessageDTO> serviceMultiResult = userService.query(rentSearch);
        model.addAttribute("total",serviceMultiResult.getTatal());

            model.addAttribute("notices",serviceMultiResult.getResult());


        model.addAttribute("searchBody", rentSearch);
        return "user/main";


    }


    @GetMapping(value = "/show/message/{id}")
    public String show(@PathVariable(value = "id") Long id,Model model){
        if (id <= 0){
            return "404";
        }
        ServiceResult<MessageDTO> serviceResult  = userService.findCompleteOne(id);
        if (!serviceResult.isSuccess()){
            return "404";
        }

        MessageDTO messageDTO = serviceResult.getResutl();
        model.addAttribute("message",messageDTO);

        return "user/messageShow";
    }
}
