package com.cn.scitc.web.form;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 离校表单校验
 */
public class SchoolUserInfoLeaveForm {
    @ApiModelProperty("编号")
    private Long id;                      //编号
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
    @NotNull(message = "班级不能为空")
    @Size(min = 1,max = 10,message = "班级的长度只能是,如软件17-2")
    @ApiModelProperty("班级")
    private String classes;             //班级
    @NotNull(message = "姓名不能为空")
    @Size(min = 2,max = 10,message = "姓名的长度只能是2-10位,如张三")
    @ApiModelProperty("姓名")
    private String stuName;            //姓名
    @NotNull(message = "必须填写学生电话")
    @Size(min = 11,max = 11,message = "学生电话的长度只能是11位,如18888888888")
    @ApiModelProperty("学生电话")
    private String stu_phone;          //学生电话
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

    private Long ids;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
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

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "SchoolUserInfoLeaveForm{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", bedroom_number='" + bedroom_number + '\'' +
                ", department='" + department + '\'' +
                ", classes='" + classes + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stu_phone='" + stu_phone + '\'' +
                ", counselor='" + counselor + '\'' +
                ", counselor_phone='" + counselor_phone + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", ids=" + ids +
                '}';
    }
}
