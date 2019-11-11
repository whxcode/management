package com.cn.scitc.web.controller.admin.excel;

import com.cn.scitc.entity.UserMessageInfo;
import com.cn.scitc.repository.ExcelRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class OutExcel {
    @Autowired
    private ExcelRepository excelRepository;

    /**
     * 导出离校信息excel
     * @return
     */
    @RequestMapping(value = "UserOutDownloads")
    public void UserOutDownloads(HttpServletResponse response, String type)throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("离校信息表");
        Iterable<UserMessageInfo> userStayList = excelRepository.findByType("out");


        String fileName = "outSchool" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        HSSFRow row = sheet.createRow(0);

        String[] headers = {"编号", "楼栋", "寝室号", "系部", "班级", "姓名","学生电话","辅导员","辅导员电话","备注"};
        //headers表示excel表中第一行的表头
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {

            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }


        //在表中存放查询到的数据放入对应的列
        for (UserMessageInfo user : userStayList) {
            if (user != null) {
                HSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(user.getId());
                row1.createCell(1).setCellValue(user.getBuilding());
                row1.createCell(2).setCellValue(user.getBedroom_number());
                row1.createCell(3).setCellValue(user.getDepartment());
                row1.createCell(4).setCellValue(user.getClasses());
                row1.createCell(5).setCellValue(user.getStuName());
                row1.createCell(6).setCellValue(user.getStu_phone());
                row1.createCell(7).setCellValue(user.getCounselor());
                row1.createCell(8).setCellValue(user.getCounselor_phone());
                row1.createCell(9).setCellValue(user.getRemark());

            }
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());


    }

}
