package com.lspro.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="普通用户信息表")
public class Users implements Serializable {
	@Id @Column(name="登录账号")
	private String loginId;
	@Column(name="登录密码")
	private String password;
	@OneToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER)
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",unique=true)
	@Cascade(CascadeType.ALL)
	private FarmMes farm;
	
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

	public FarmMes getFarm() {
		return farm;
	}
	public void setFarm(FarmMes farm) {
		this.farm = farm;
	}
	
 

	
}
