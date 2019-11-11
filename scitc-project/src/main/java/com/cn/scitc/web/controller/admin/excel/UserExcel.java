package com.cn.scitc.web.controller.admin.excel;

import com.cn.scitc.entity.SysUser;
import com.cn.scitc.repository.SysUserRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
public class UserExcel {
    @Autowired
    private SysUserRepository uerRepository;

    /**
     * 导出用户信息excel
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "UserExcelDownloads")
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();


        HSSFSheet sheet = workbook.createSheet("用户信息表");

        HSSFCell cell = null;

        Iterable<SysUser> userList = uerRepository.findAll();



        String fileName = "user"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;


        HSSFRow row = sheet.createRow(0);




        String[] headers = { "编号","用户名","姓名","密码","性别","班级","状态","创建时间","最后一次登录时间","最后一次修改时间"};
        //headers表示excel表中第一行的表头




        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){

            //HSSFCell cell = row.createCell(i);
            cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            sheet.autoSizeColumn(i);

        }



        //在表中存放查询到的数据放入对应的列
        for (SysUser user : userList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (user != null){
                HSSFRow row1 = sheet.createRow(rowNum);

                row1.createCell(0).setCellValue(user.getId());
                row1.createCell(1).setCellValue(user.getUsername());
                row1.createCell(2).setCellValue(user.getNickname());
                row1.createCell(3).setCellValue(user.getPassword());
                row1.createCell(4).setCellValue(user.getStatus());
                row1.createCell(5).setCellValue(user.getGender());
                row1.createCell(6).setCellValue(user.getClasses());
                row1.createCell(7).setCellValue(sdf.format(user.getCreate_time()));
                row1.createCell(8).setCellValue(sdf.format(user.getLast_login_time()));
                row1.createCell(9).setCellValue(sdf.format(user.getLast_update_time()));
            }
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }



}
