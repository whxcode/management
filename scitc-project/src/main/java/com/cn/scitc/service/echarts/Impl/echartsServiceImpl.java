package com.cn.scitc.service.echarts.Impl;

import com.cn.scitc.repository.SchoolUserInfoRepository;
import com.cn.scitc.service.echarts.echartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class echartsServiceImpl implements echartsService {
    @Autowired
    private SchoolUserInfoRepository echartsRepository;
    @Override
        public Long findByType(String type) {

        return echartsRepository.findByType(type);
    }

}
