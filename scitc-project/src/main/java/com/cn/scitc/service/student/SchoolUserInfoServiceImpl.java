package com.cn.scitc.service.student;

import com.cn.scitc.entity.UserMessageInfo;
import com.cn.scitc.repository.SchoolUserInfoRepository;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.utils.LoginUtil;
import com.cn.scitc.web.dto.SchoolUserInfoDTO;
import com.cn.scitc.web.form.SchcoolUserInfoForm;
import com.cn.scitc.web.form.SchoolUserInfoLeaveForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SchoolUserInfoServiceImpl implements SchoolUserInfoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SchoolUserInfoRepository UserInfoRepository;


    @Override
    public ServiceResult<SchoolUserInfoDTO> save(SchcoolUserInfoForm studentForm) {
        UserMessageInfo student = new UserMessageInfo();
        modelMapper.map(studentForm,student);

        Date now = new Date();
        student.setIds(LoginUtil.getLoginUserId());
        student.setCreate_time(now);
        student.setDeten_last_time(now);
        student.setDeten_begin_time(now);
        UserInfoRepository.save(student);

        //隐射结果
        SchoolUserInfoDTO studentDTO = modelMapper.map(student,SchoolUserInfoDTO.class);

        return new ServiceResult<SchoolUserInfoDTO>(true,null,studentDTO);
    }

    @Override
    public ServiceResult<SchoolUserInfoDTO> save(SchoolUserInfoLeaveForm studentLeaveForm) {
        UserMessageInfo student = new UserMessageInfo();

        modelMapper.map(studentLeaveForm,student);
        student.setIds(LoginUtil.getLoginUserId());
        UserInfoRepository.save(student);
        //隐射结果
        SchoolUserInfoDTO studentDTO = modelMapper.map(student,SchoolUserInfoDTO.class);
        return new ServiceResult<SchoolUserInfoDTO>(true,null,studentDTO);
    }

    @Override
    public ServiceResult update(SchcoolUserInfoForm studentForm) {
        return null;
    }

    /**
     * 个人留校离校记录
     * @return
     */
    @Override
    public List<UserMessageInfo> findCompleteOne() {
       List<UserMessageInfo>  userMessageInfo  = UserInfoRepository.getByIds(LoginUtil.getLoginUserId());

        return  userMessageInfo;
    }


}
