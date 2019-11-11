package com.cn.scitc.web.controller.admin;

import com.cn.scitc.echarts.echarts;
import com.cn.scitc.repository.SchoolUserInfoRepository;
import com.cn.scitc.service.echarts.echartsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class echartsController {

    @Autowired
    private echartsService echartsService;
    @Autowired
    private SchoolUserInfoRepository userInfoRepository;

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/getEcharts")
    public String getResult() {

        return "admin/echarts_count";

    }
    @GetMapping("/getData")
    @ResponseBody
    public List<echarts> getData(Model model) {

        List<echarts> results = new ArrayList<echarts>();
        echarts outEchart = new echarts();
        echarts stayEchart = new echarts();
        echarts countEchart = new echarts();
        outEchart.setValue(echartsService.findByType("out"));
        outEchart.setName("留校人数");
        results.add(outEchart);
        stayEchart.setValue(echartsService.findByType("stay"));
        countEchart.setName("已提交信息人数");
        countEchart.setValue(userInfoRepository.findByType());
        stayEchart.setName("离校人数");
        results.add(countEchart);
        results.add(stayEchart);
        model.addAttribute("data", gson.toJson(results));
        System.out.println(gson.toJson(results));

        return results;
    }



}
