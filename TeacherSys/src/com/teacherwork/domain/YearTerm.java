package com.teacherwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="年度学期表")
public class YearTerm implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="是否为第一学期",length=5)
	private boolean isLastTerm;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getIsLastTerm() {
		return isLastTerm;
	}
	public void setLastTerm(boolean isLastTerm) {
		this.isLastTerm = isLastTerm;
	}
	
	@Override
	public String toString() {
		return "YearTerm [id=" + id + ", isLastTerm=" + isLastTerm
				+ ", yearMsg="  + "]";
	}
	
	
}
