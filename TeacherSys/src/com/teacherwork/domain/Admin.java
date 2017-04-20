package com.teacherwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "管理员信息表")
public class Admin implements Serializable {

	@Id
	@Column(name = "登录账号", length = 20)
	private String loginId;
	@Column(name = "登录密码", length = 50)
	private String pw;
	@Column(name="角色" , length = 5)
	private int role;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "Admin [loginId=" + loginId + ", pw=" + pw + "]";
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
