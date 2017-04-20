package com.teacherwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="数据统计表")
public class DataCount implements Serializable {
	@Id
	@Column(name="用户",length=10)
	private String loginId;
	@Id
	@Column(name="年份",length=10)
	private int years;
	@Id
	@Column(name="审核状态",length=10)
	private boolean state;
	@Id
	@Column(name="学期",length=5)
	private int term;
	@Column(name="额内工作量",length=10)
	private double inWork;
	@Column(name="额外工作量",length=10)
	private double inOut;
	@Column(name="总工作量",length=10)
	private double allWork;
	@Column(name="总金额",length=10)
	private double allSal;
	@ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER )
	@JoinColumn(name="用户账号",referencedColumnName="登录账号",nullable=false)
	private User user;
	
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
	public double getInWork() {
		return inWork;
	}
	public void setInWork(double inWork) {
		this.inWork = inWork;
	}
	public double getInOut() {
		return inOut;
	}
	public void setInOut(double inOut) {
		this.inOut = inOut;
	}
	public double getAllWork() {
		return allWork;
	}
	public void setAllWork(double allWork) {
		this.allWork = allWork;
	}
	public double getAllSal() {
		return allSal;
	}
	public void setAllSal(double allSal) {
		this.allSal = allSal;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "DataCount [loginId=" + loginId + ", years=" + years
				+ ", state=" + state + ", term=" + term + ", inWork=" + inWork
				+ ", inOut=" + inOut + ", allWork=" + allWork + ", allSal="
				+ allSal + ", user=" + user + "]";
	}
	
}
