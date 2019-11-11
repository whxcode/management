package com.cn.scitc.web.controller;

import com.cn.scitc.base.ApiResponse;
import com.cn.scitc.entity.UserMessageInfo;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.service.UserService;
import com.cn.scitc.service.student.SchoolUserInfoService;
import com.cn.scitc.utils.DuplicateSubmitToken;
import com.cn.scitc.utils.LoginUtil;
import com.cn.scitc.web.dto.SchoolUserInfoDTO;
import com.cn.scitc.web.form.SchcoolUserInfoForm;
import com.cn.scitc.web.form.SchoolUserInfoLeaveForm;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SchoolUserInfoService userInfoService;
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 留校信息提交
     * @param userInfoForm
     * @param bindingResult
     * @return
     */

    @ApiOperation("留校信息添加")
    @DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION)
    @PostMapping("/student/info")
    @ResponseBody
    public ApiResponse addUserStudentInfo(@Valid SchcoolUserInfoForm userInfoForm,
                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(),
                    bindingResult.getAllErrors().get(0).getDefaultMessage(),null);
        }


        ServiceResult<SchoolUserInfoDTO> result = userInfoService.save(userInfoForm);
        System.out.println("获取到json数据是:" + result);

        if (result.isSuccess()){
            return ApiResponse.ofMessage(200,"恭喜您提交成功,请勿重复提交");
        }

        return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
   }

    /**
     * 离校信息提交
     * @param userInfoLeaveForm
     * @param bindingResult
     * @return
     */
   @ApiOperation("离校信息添加")
   @DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION)
   @PostMapping("/student/leave")
   @ResponseBody
    public  ApiResponse addUserStudentLeave(@Valid SchoolUserInfoLeaveForm
              userInfoLeaveForm,BindingResult bindingResult){



        if (bindingResult.hasErrors()){
            return  new ApiResponse(HttpStatus.BAD_REQUEST.value()
            ,bindingResult.getAllErrors().get(0).getDefaultMessage(),null);
        }

        ServiceResult<SchoolUserInfoDTO> result = userInfoService.save(userInfoLeaveForm);
        if (result.isSuccess()){
            return ApiResponse.ofMessage(200,"恭喜您提交成功,请勿重复提交");
        }
        return  ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
   }



    @ApiOperation("用户信息修改")
    @RequestMapping("/api/updateUserInfo")
    @DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION)
    @ResponseBody
    public ApiResponse updateUserInfo(@RequestParam(value = "profile")
                                              String profile, @RequestParam(value = "value")
                                              String value){
       Long userId = LoginUtil.getLoginUserId();
       if (userId == -1){
           return ApiResponse.ofMessage(500,"没有权限修改");
       }
        if (value.isEmpty()){
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        logger.info("profile:" + profile);
        logger.info("value:" + value);
        ServiceResult result = userService.modifyUserProfile(profile, value);
        if (result.isSuccess()){
            return ApiResponse.ofSuccess("");
        }else {
            return ApiResponse.ofMessage(400, result.getMessage());
        }

    }


@ApiOperation("个人留校离校信息查询")
@GetMapping(value = "/show/personal")


public String showPersonal(Model model){

       List<UserMessageInfo> messageInfos = userInfoService.findCompleteOne();
       if (messageInfos ==null){
           return "404";
       }

       model.addAttribute("personal",messageInfos);


    return  "user/showPersonal";
}


}
