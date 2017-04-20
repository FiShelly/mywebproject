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
@Table(name="额内外课时系数表")
public class ClassIn implements Serializable{
	@Id 
	@Column(name="年份",length=5)
	private int years;
	@Column(name="额度",length=10)
	private int classInNum;
	@Column(name="额内课时系数",length=10)
	private double coefficientIn;
	@Column(name="额外课时系数",length=10)
	private double coefficientOut;
	@ManyToOne(targetEntity=YearMsg.class,fetch=FetchType.EAGER)
	@JoinColumn(name="年度",referencedColumnName="年度",nullable=false)
	private YearMsg yearMsg;
	
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public double getCoefficientIn() {
		return coefficientIn;
	}
	public void setCoefficientIn(double coefficientIn) {
		this.coefficientIn = coefficientIn;
	}
	public double getCoefficientOut() {
		return coefficientOut;
	}
	public void setCoefficientOut(double coefficientOut) {
		this.coefficientOut = coefficientOut;
	}
	public YearMsg getYearMsg() {
		return yearMsg;
	}
	public void setYearMsg(YearMsg yearMsg) {
		this.yearMsg = yearMsg;
	}
	@Override
	public String toString() {
		return "ClassIn [id=" + years + ", classInNum=" + getClassInNum()
				+ ", coefficientIn=" + coefficientIn + ", coefficientOut="
				+ coefficientOut + ", yearMsg=" + yearMsg + "]";
	}
	public int getClassInNum() {
		return classInNum;
	}
	public void setClassInNum(int classInNum) {
		this.classInNum = classInNum;
	}
	
}
