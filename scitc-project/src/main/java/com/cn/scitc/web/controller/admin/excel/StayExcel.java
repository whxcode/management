package com.cn.scitc.web.controller.admin.excel;

import com.cn.scitc.entity.UserMessageInfo;
import com.cn.scitc.repository.ExcelRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
public class StayExcel {

    @Autowired
    private ExcelRepository excelRepository;

    /**
     * 导出留校信息excel
     * @return
     */
    @RequestMapping(value = "UserStayDownloads")
    public void UserStayDownloads(HttpServletResponse response, String type)throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("留校信息表");
        Iterable<UserMessageInfo> userStayList = excelRepository.findByType("stay");


        String fileName = "user" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;
        HSSFRow row = sheet.createRow(0);

        String[] headers = {"编号", "校区", "楼栋", "寝室号", "系部", "班级", "姓名", "性别"
                ,"留校理由","留校开始时间","留校结束时间","学生电话","家长电话","辅导员","辅导员电话","备注"};
        //headers表示excel表中第一行的表头

        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {

            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }


        //在表中存放查询到的数据放入对应的列
        for (UserMessageInfo user : userStayList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (user != null) {
                HSSFRow row1 = sheet.createRow(rowNum);

                row1.createCell(0).setCellValue(user.getId());
                row1.createCell(1).setCellValue(user.getCampus());
                row1.createCell(2).setCellValue(user.getBuilding());
                row1.createCell(3).setCellValue(user.getBedroom_number());
                row1.createCell(4).setCellValue(user.getDepartment());
                row1.createCell(5).setCellValue(user.getClasses());
                row1.createCell(6).setCellValue(user.getStuName());
                row1.createCell(7).setCellValue(user.getGender());
                row1.createCell(8).setCellValue(user.getDeten_reason());
                row1.createCell(9).setCellValue(sdf.format(user.getDeten_begin_time()));
                row1.createCell(10).setCellValue(sdf.format(user.getDeten_last_time()));
                row1.createCell(11).setCellValue(user.getStu_phone());
                row1.createCell(12).setCellValue(user.getParent_phone());
                row1.createCell(13).setCellValue(user.getCounselor());
                row1.createCell(14).setCellValue(user.getCounselor_phone());
                row1.createCell(15).setCellValue(user.getRemark());

            }
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());


    }

}
