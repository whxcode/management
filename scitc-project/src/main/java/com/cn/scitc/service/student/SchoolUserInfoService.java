package com.cn.scitc.service.student;

import com.cn.scitc.entity.UserMessageInfo;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.web.dto.SchoolUserInfoDTO;
import com.cn.scitc.web.form.SchcoolUserInfoForm;
import com.cn.scitc.web.form.SchoolUserInfoLeaveForm;

import java.util.List;

public interface SchoolUserInfoService {
    /**
     * 填写留校表单信息
     * @param studentForm
     * @return
     */
    ServiceResult<SchoolUserInfoDTO> save(SchcoolUserInfoForm studentForm);

    /**
     * 填写离校表单信息
     * @param studentLeaveForm
     * @return
     */
    ServiceResult<SchoolUserInfoDTO> save(SchoolUserInfoLeaveForm studentLeaveForm);

    /**
     * 修改填写表单信息
     * @param studentForm
     * @return
     */
    ServiceResult update(SchcoolUserInfoForm studentForm);

    /**
     * 加载当前登录的id
     * @return
     */
    List<UserMessageInfo> findCompleteOne();


}
