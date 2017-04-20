package com.teacherwork.domain;

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
@Table(name="年度信息表")
public class YearMsg {

	@Id
	@Column(name="年度",length=5)
	private int years;
	@Column(name="是否当前",length=5)
	private boolean isCurrent;
	@Column(name="创建日期",length=15)
	private String date;
	@OneToOne(targetEntity=YearTerm.class,fetch=FetchType.EAGER)
	@JoinColumn(name="第一学期",referencedColumnName="id",unique=true)
	@Cascade(CascadeType.ALL)
	private YearTerm lastTerm;
	@OneToOne(targetEntity=YearTerm.class,fetch=FetchType.EAGER)
	@JoinColumn(name="第二学期",referencedColumnName="id",unique=true)
	@Cascade(CascadeType.ALL)
	private YearTerm nextTerm;
	@Column(name="单位课酬",length=10)
	private double sal;
	
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public boolean getIsCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public YearTerm getLastTerm() {
		return lastTerm;
	}
	public void setLastTerm(YearTerm lastTerm) {
		this.lastTerm = lastTerm;
	}
	public YearTerm getNextTerm() {
		return nextTerm;
	}
	public void setNextTerm(YearTerm nextTerm) {
		this.nextTerm = nextTerm;
	}
	
	@Override
	public String toString() {
		return "YearMsg [years=" + years + ", isCurrent=" + isCurrent
				+ ", date=" + date + ", lastTerm=" + lastTerm + ", nextTerm="
				+ nextTerm + ", sal=" + sal + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	
}
