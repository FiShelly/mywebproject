package com.teacherwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="职称系数表")
public class Title  implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="职称名称",length=20)
	private String titleName;
	@Column(name="职称系数",length=10)
	private double coefficient;
	@ManyToOne(targetEntity=YearMsg.class,fetch=FetchType.EAGER)
	@JoinColumn(name="年度",referencedColumnName="年度",nullable=false)
	private YearMsg yearMsg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public YearMsg getYearMsg() {
		return yearMsg;
	}
	public void setYearMsg(YearMsg yearMsg) {
		this.yearMsg = yearMsg;
	}
	
	@Override
	public String toString() {
		return "Title [id=" + id + ", titleName=" + titleName
				+ ", coefficient=" + coefficient + ", yearTerm=" + getYearMsg()
				+ "]";
	}
}
