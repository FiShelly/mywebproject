package com.teacherwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="审核预览表")
public class PreReview implements Serializable {
	@Id
	@Column(name="用户账号",length=10)
	private String loginId;
	@Id
	@Column(name="年份",length=10)
	private int years;
	@OneToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	@JoinColumn(name="用户",referencedColumnName="登录账号",unique=true)
	private User user;
	@Column(name="提交数目",length=5)
	private int subCount;
	@Column(name="最后提交日期",length=20)
	private String subDate;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getSubCount() {
		return subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	public String getSubDate() {
		return subDate;
	}
	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	@Override
	public String toString() {
		return "PreReview [loginId=" + loginId + ", years=" + years + ", user="
				+ user + ", subCount=" + subCount + ", subDate=" + subDate
				+ "]";
	}
	
}
