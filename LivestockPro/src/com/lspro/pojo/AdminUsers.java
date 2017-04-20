package com.lspro.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="管理员信息表")
public class AdminUsers implements Serializable {
	
	@Id @Column(name="登录账号",nullable=false)
	private String loginId;
	@Column(name="登录密码")
	private String password;
	@Column(name="账号等级") //1 2 3
	private Integer grade;
	@Column(name="地址")
	private String address;
	@Column(name="所属省份")
	private String province;
	@Column(name="所属地级市")
	private String city;
	@Column(name="所属县级市")
	private String country;
	@Column(name="详细地址")
	private String detail;
	@Column(name="是否为超级管理员")
	private boolean isSuperAdmin;
	@Column(name="注册时间")
	private String registDate;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}
	public void setIsSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	@Override
	public String toString() {
		return "AdminUsers [loginId=" + loginId + ", password=" + password
				+ ", grade=" + grade + ", address=" + address + ", province="
				+ province + ", city=" + city + ", country=" + country
				+ ", detail=" + detail + ", isSuperAdmin=" + isSuperAdmin
				+ ", registDate=" + registDate + "]";
	}
	
}
