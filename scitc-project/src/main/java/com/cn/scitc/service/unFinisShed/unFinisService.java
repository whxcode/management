package com.cn.scitc.service.unFinisShed;

import com.cn.scitc.service.ServiceMultiResult;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.web.dto.MessageDTO;
import com.cn.scitc.web.dto.SysUserDTO;
import com.cn.scitc.web.form.RentSearch;

/**
 * 未提交信息
 */
public interface unFinisService {

    ServiceResult<SysUserDTO> findCompleteOne(Long id);

    ServiceMultiResult<SysUserDTO> query(RentSearch rentSearch);
}
