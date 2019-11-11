package com.cn.scitc.web.controller.admin.excel;

import com.cn.scitc.repository.SchoolUserInfoRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
public class countExcel {
    @Autowired
    private SchoolUserInfoRepository schoolUserInfoRepository;

    @RequestMapping(value = "/UserCountDownloads")
    public void UserOutDownloads(HttpServletResponse response)throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计留校离校人数情况");
        Long outSchool =  schoolUserInfoRepository.findByType("out");//离校
        Long leaveSchool = schoolUserInfoRepository.findByType("stay");//留校
        Long counts = schoolUserInfoRepository.findByType();//合计
        System.out.println("outSchool:" + outSchool);
        System.out.println("leaveSchool:" + leaveSchool);
        System.out.println("counts:" + counts);
        String fileName = "countSchool" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        HSSFRow row = sheet.createRow(0);

        String[] headers = {"留校人数", "离校人数", "合计"};
        //headers表示excel表中第一行的表头
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {

            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

                HSSFRow row1 = sheet.createRow(rowNum);
                 row1.createCell(0).setCellValue(outSchool);
                 row1.createCell(1).setCellValue(leaveSchool);
                 row1.createCell(2).setCellValue(counts);


            rowNum++;


        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());

    }



}
