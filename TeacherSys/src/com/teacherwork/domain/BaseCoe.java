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
@Table(name="基础系数表")
public class BaseCoe implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="班级数",length=10)
	private int classNum;
	@Column(name="人数范围左",length=10)
	private int personIn;
	@Column(name="人数范围右",length=10)
	private int personOut;
	@Column(name="基础系数",length=10)
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
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getPersonIn() {
		return personIn;
	}
	public void setPersonIn(int personIn) {
		this.personIn = personIn;
	}
	public int getPersonOut() {
		return personOut;
	}
	public void setPersonOut(int personOut) {
		this.personOut = personOut;
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
		return "BaseCoe [id=" + id + ", classNum=" + classNum + ", personIn="
				+ personIn + ", personOut=" + personOut + ", coefficient="
				+ coefficient + ", yearMsg=" + yearMsg + "]";
	}
	
	
}
