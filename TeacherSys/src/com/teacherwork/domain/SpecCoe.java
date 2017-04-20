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
@Table(name="特殊系数表")
public class SpecCoe implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="特殊项目名称",length=20)
	private String specItemName;
	@Column(name="特殊系数",length=10)
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
	public String getSpecItemName() {
		return specItemName;
	}
	public void setSpecItemName(String specItemName) {
		this.specItemName = specItemName;
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
		return "SpecCoe [id=" + id + ", specItemName=" + specItemName
				+ ", coefficient=" + coefficient + ", yearMsg=" + yearMsg + "]";
	}
	
	
}
