package com.cn.scitc.web.form;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 留校表单验证
 */
public class SchcoolUserInfoForm {
    private Long id;                   //编号
    @NotNull(message = "校区不能为空")
    @Size(min = 4,max =4,message = "校区的长度只能是四位,如学峰校区")
    @ApiModelProperty("校区")
    private String campus;             //校区
    @NotNull(message = "楼栋不能为空")
    @Size(min = 2,max = 2,message = "栋楼的长度只能是2位,如4栋")
    @ApiModelProperty("楼栋")
    private String building;           //楼栋
    @NotNull(message = "寝室号不能为空")
    @Size(min = 4,max = 4,message = "寝室号的长度只能是4位,如2108")
    @ApiModelProperty("寝室号")
    private String bedroom_number;      //寝室号
    @NotNull(message = "系部不能为空")
    @Size(min = 5,max = 5,message = "系部的长度只能是五位,如信息工程系")
    @ApiModelProperty("系部")
    private String department;         //系部
    @NotNull(message = "班级不能为空" )
    @Size(min =1 ,max =10 ,message = "班级的长度只能是,如软件17-2")
    @ApiModelProperty("班级")
    private String classes;            //班级
    @NotNull(message = "姓名不能为空")
    @Size(min =2 ,max = 10,message = "姓名的长度只能是2-10位,如张三")
    @ApiModelProperty("姓名")
    private String stuName;            //姓名
    @NotNull(message = "性别不能为空")
    @Size(min = 1,max = 1)
    @ApiModelProperty("性别")
    private String gender;             //性别
    @Size(min = 0,max = 255,message = "留校理由最少是10个字符")
    @ApiModelProperty("留校理由")
    private String deten_reason;        //留校理由
    @NotNull(message = "必选选择留校开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("留校开始时间")
    private Date deten_begin_time;       //留校开始时间
    @NotNull(message = "必须选择留校结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("留校结束时间")
    private Date deten_last_time;        //留校结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间,自动创建")
    private Date create_time;           //创建时间
    @NotNull(message = "必须填写学生电话")
    @Size(min = 11,max = 11,message = "学生电话的长度只能是11位,如18888888888")
    @ApiModelProperty("学生电话")
    private String stu_phone;           //学生电话
    @NotNull(message = "必须填写家长电话")
    @Size(min = 11,max = 11,message = "家长电话的长度只能是11位,如18888888888")
    @ApiModelProperty("家长电话")
    private String parent_phone;        //家长电话
    @NotNull(message = "必须填写辅导员姓名")
    @Size(min = 2,max = 10,message = "辅导员姓名长度只能是2到10位")
    @ApiModelProperty("辅导员")
    private String counselor;          //辅导员
    @NotNull(message = "必须填写辅导员电话")
    @Size(min = 11,max = 11,message = "辅导员电话的长度只能是11位,如18888888888")
    @ApiModelProperty("辅导员电话")
    private String counselor_phone;     //辅导员电话
    @ApiModelProperty("备注")
    private String remark;             //备注
    @ApiModelProperty("留校标记{stay,out}")

    private String type;              //留校离校标记 如果默认值是stay表示留校,out 代表离校


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBedroom_number() {
        return bedroom_number;
    }

    public void setBedroom_number(String bedroom_number) {
        this.bedroom_number = bedroom_number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeten_reason() {
        return deten_reason;
    }

    public void setDeten_reason(String deten_reason) {
        this.deten_reason = deten_reason;
    }

    public Date getDeten_begin_time() {
        return deten_begin_time;
    }

    public void setDeten_begin_time(Date deten_begin_time) {
        this.deten_begin_time = deten_begin_time;
    }

    public Date getDeten_last_time() {
        return deten_last_time;
    }

    public void setDeten_last_time(Date deten_last_time) {
        this.deten_last_time = deten_last_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getCounselor_phone() {
        return counselor_phone;
    }

    public void setCounselor_phone(String counselor_phone) {
        this.counselor_phone = counselor_phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "SchcoolUserInfoForm{" +
                "id=" + id +
                ", campus='" + campus + '\'' +
                ", building='" + building + '\'' +
                ", bedroom_number='" + bedroom_number + '\'' +
                ", department='" + department + '\'' +
                ", classes='" + classes + '\'' +
                ", stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                ", deten_reason='" + deten_reason + '\'' +
                ", deten_begin_time=" + deten_begin_time +
                ", deten_last_time=" + deten_last_time +
                ", create_time=" + create_time +
                ", stu_phone='" + stu_phone + '\'' +
                ", parent_phone='" + parent_phone + '\'' +
                ", counselor='" + counselor + '\'' +
                ", counselor_phone='" + counselor_phone + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
