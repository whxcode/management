package com.cn.scitc.web.dto;

import java.io.Serializable;
import java.util.Date;


public class SchoolUserInfoDTO implements Serializable {

    private static final long serialVersionUID = -8559464069416473339L;
    private Long id;                   //编号
    private String campus;             //校区
    private String building;           //楼栋
    private String bedroom_number;      //寝室号
    private String department;         //系部
    private String classes;            //班级
    private String stuName;            //姓名
    private String gender;             //性别
    private String deten_reason;        //留校理由
    private Date deten_begin_time;       //留校开始时间
    private Date deten_last_time;        //留校结束时间
    private Date create_time;           //创建时间
    private String stu_phone;           //学生电话
    private String parent_phone;        //家长电话
    private String counselor;          //辅导员
    private String counselor_phone;     //辅导员电话
    private String remark;             //备注

    private String type;              //留校离校标记 如果默认值是stay表示留校,out 代表离校

    private Long ids;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "SchoolUserInfoDTO{" +
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
                ", ids=" + ids +
                '}';
    }
}