package com.lspro.pojo;

/**
 * Description:
 * 免疫记录数据表。<br/>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 谢福成
 * @see Vaccine
 * @version 1.0
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;
 
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name="免疫记录表")
public class ImmuneRecord implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="时间")
	private String immuneTime;
	@Column(name="圈舍号")
	private String roomNum;
	@Column(name="存栏数量")
 	private Integer remainNum;
 
	@Column(name="免疫数量")
	private Integer immuneNum;
	@Column(name="疫苗名称")
	private String vaccineName;
	@Column(name="疫苗产商")
	private String vaccineProducers;
	@Column(name="疫苗有效期")
	private String vaccineValidTime;
	@Column(name="疫苗批号")
	private String batchNum;
	@Column(name="免疫方法")
	private String immuneMethod;
	@Column(name="免疫剂量")
	private String immuneDosage;
	@Column(name="免疫人员")
	private String immunePeople;
	
	@Column(name="备注")
	private String note;
	
	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER )
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	
	public String getImmuneTime() {
		return immuneTime;
	}
	public void setImmuneTime(String immuneTime) {
		this.immuneTime = immuneTime;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public Integer getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public FarmMes getFarm() {
		return farm;
	}
	public void setFarm(FarmMes farm) {
		this.farm = farm;
	}
	public Integer getImmuneNum() {
		return immuneNum;
	}
	public void setImmuneNum(Integer immuneNum) {
		this.immuneNum = immuneNum;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getVaccineProducers() {
		return vaccineProducers;
	}
	public void setVaccineProducers(String vaccineProducers) {
		this.vaccineProducers = vaccineProducers;
	}
	public String getVaccineValidTime() {
		return vaccineValidTime;
	}
	public void setVaccineValidTime(String vaccineValidTime) {
		this.vaccineValidTime = vaccineValidTime;
	}
	public String getImmuneMethod() {
		return immuneMethod;
	}
	public void setImmuneMethod(String immuneMethod) {
		this.immuneMethod = immuneMethod;
	}
	public String getImmuneDosage() {
		return immuneDosage;
	}
	public void setImmuneDosage(String immuneDosage) {
		this.immuneDosage = immuneDosage;
	}
	public String getImmunePeople() {
		return immunePeople;
	}
	public void setImmunePeople(String immunePeople) {
		this.immunePeople = immunePeople;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
 
 

}
