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
@Table(name="职务系数表")
public class Post implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="职务名称",length=20)
	private String postName;
	@Column(name="职务系数",length=10)
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
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
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
		return "Post [id=" + id + ", postName=" + postName + ", coefficient="
				+ coefficient + ", yearMsg=" + yearMsg + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result
				+ ((postName == null) ? 0 : postName.hashCode());
		result = prime * result + ((yearMsg == null) ? 0 : yearMsg.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (Double.doubleToLongBits(coefficient) != Double
				.doubleToLongBits(other.coefficient))
			return false;
		if (id != other.id)
			return false;
		if (postName == null) {
			if (other.postName != null)
				return false;
		} else if (!postName.equals(other.postName))
			return false;
		if (yearMsg == null) {
			if (other.yearMsg != null)
				return false;
		} else if (!yearMsg.equals(other.yearMsg))
			return false;
		return true;
	}
	
	
}
