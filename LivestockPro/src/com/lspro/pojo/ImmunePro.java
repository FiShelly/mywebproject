package com.lspro.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="免疫程序表")
public class ImmunePro implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="序号")
	private Integer sequeueNum;
	
	@Column(name="接种动物")
	private String name;

	@Column(name="日龄")
	private String dateAge  ;

	
	@Column(name="疫苗名称或种类")
	private String vaccine;
	
	
	@Column(name="免疫剂量")
	private String dose;
	

	@Column(name="免疫方式")
	private String way;
	
	
	@Column(name="备注")
	private String note  ;
	
	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER)
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSequeueNum() {
		return sequeueNum;
	}


	public void setSequeueNum(Integer sequeueNum) {
		this.sequeueNum = sequeueNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDateAge() {
		return dateAge;
	}


	public void setDateAge(String dateAge) {
		this.dateAge = dateAge;
	}


	public String getVaccine() {
		return vaccine;
	}


	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}


	public String getDose() {
		return dose;
	}


	public void setDose(String dose) {
		this.dose = dose;
	}


	public String getWay() {
		return way;
	}


	public void setWay(String way) {
		this.way = way;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public FarmMes getFarm() {
		return farm;
	}


	public void setFarm(FarmMes farm) {
		this.farm = farm;
	}
	
	 
	
	
}
