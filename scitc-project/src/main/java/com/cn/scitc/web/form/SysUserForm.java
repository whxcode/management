package com.cn.scitc.web.form;

import com.cn.scitc.entity.SysRole;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注册表单验证
 */
public class SysUserForm {
    private Long id;             //编号
    @NotNull(message = "用户名不能为空")
    @Size(min = 8,max = 8,message = "用户名的长度只能是8位")
    private String username;    //用户名
    @NotNull(message = "姓名不能为空")
    @Size(min = 3,max = 8,message = "姓名的长度只是是3~8位")
    private String nickname;        //姓名
    @NotNull(message = "密码不能为空")
    @Size(min = 6,max = 11,message = "密码的长度只是是3~8位")
    private String password;  //密码

    private String gender;     //性别
    private String classes;    //班级
    private Long status;      //用户状态
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time; //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date last_login_time;//最后一次登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date last_update_time;//最后一次修改时间


    private List<SysRole> roles = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Date getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "SysUserForm{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", classes='" + classes + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", last_login_time=" + last_login_time +
                ", last_update_time=" + last_update_time +
                ", roles=" + roles +
                '}';
    }
}
