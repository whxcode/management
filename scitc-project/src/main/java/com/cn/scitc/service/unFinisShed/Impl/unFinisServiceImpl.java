package com.cn.scitc.service.unFinisShed.Impl;

import com.cn.scitc.entity.SysUser;
import com.cn.scitc.repository.SysUserRepository;
import com.cn.scitc.service.ServiceMultiResult;
import com.cn.scitc.service.ServiceResult;
import com.cn.scitc.service.unFinisShed.unFinisService;
import com.cn.scitc.utils.LoginUtil;
import com.cn.scitc.web.dto.SysUserDTO;
import com.cn.scitc.web.form.RentSearch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class unFinisServiceImpl implements unFinisService {
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ServiceResult<SysUserDTO> findCompleteOne(Long id) {
        SysUser user =  sysUserRepository.getById(id);
        if (user == null){
            return ServiceResult.noutFound();
        }
        SysUserDTO result = modelMapper.map(user,SysUserDTO.class);

        return ServiceResult.of(result);
    }

    @Override
    public ServiceMultiResult<SysUserDTO> query(RentSearch rentSearch) {

        return null;
    }
}
