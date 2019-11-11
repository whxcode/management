package com.cn.scitc.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户基本信息表
 * @author jswzj
 *
 */
@Entity
@Table(name="sysuser")
public class SysUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	   @Column(name = "id")
		private Long id;           //用户编号
		private String username;   //用户名
		private String nickname;   //姓名
		private String password;   //密码
		private int status;        //用户状态
	    private String gender;     //性别
	    private String classes;    //班级
		private Date create_time;  //创建时间
		private Date last_login_time; //最后一次登录时间
		private Date last_update_time;    //最后一次修改时间



	public SysUser() {
	}

	public SysUser(String username, String nickname) {
		this.username = username;
		this.nickname = nickname;
	}

	@ManyToMany(targetEntity = SysRole.class, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_roles",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<SysRole> roles = new ArrayList<>();//多对多关系映射



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		return this.username;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//设置用户角色权限
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<SysRole> roles =  this.getRoles();
		for(SysRole role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;

	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}