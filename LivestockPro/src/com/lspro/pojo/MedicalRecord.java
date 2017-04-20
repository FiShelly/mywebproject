package com.lspro.pojo;

/**
 * Description:
 * 诊疗记录基本数据表。<br/>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 谢福成
 * @version 1.0
 * 
 */

 
import java.io.Serializable;
import java.util.Map;
 
import java.util.TreeMap;

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
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
 
import javax.persistence.Table;

@Entity
@Table(name="诊疗记录表")
public class MedicalRecord implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;  //标识id
	@Column(name="诊疗日期")
	private String medicalTime; 	//诊疗日期	
	@Column(name="畜禽标识编码")
	private String livestockId;	//畜禽标识编码
	@Column(name="圈舍号")
	private String roomNum;	//圈舍号
	@Column(name="日龄")
	private String dateAge;	//日龄
	@Column(name="发病数")
	private Integer sickNum;	//发病数
	@Column(name="病因")
	private String sickReason;	//病因
	@Column(name="诊疗人员")
	private String medicalPeo;	//诊疗人员
	@Column(name="用药名称")
	private String drugName;
	@Column(name="用药方法")
	private String method;
	@Column(name="诊疗结果")
	private String medicalResult;	//诊疗结果
	@Column(name="存栏数")
	private Integer remain;
	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER )
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMedicalTime() {
		return medicalTime;
	}
	public void setMedicalTime(String medicalTime) {
		this.medicalTime = medicalTime;
	}

	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getDateAge() {
		return dateAge;
	}
	public void setDateAge(String dateAge) {
		this.dateAge = dateAge;
	}
	public Integer getSickNum() {
		return sickNum;
	}
	public void setSickNum(Integer sickNum) {
		this.sickNum = sickNum;
	}
	public String getSickReason() {
		return sickReason;
	}
	
	public void setSickReason(String sickReason) {
		this.sickReason = sickReason;
	}
	public String getMedicalPeo() {
		return medicalPeo;
	}
	public void setMedicalPeo(String medicalPeo) {
		this.medicalPeo = medicalPeo;
	}

	public String getMedicalResult() {
		return medicalResult;
	}
	public void setMedicalResult(String medicalResult) {
		this.medicalResult = medicalResult;
	}
	public FarmMes getFarm() {
		return farm;
	}
	public void setFarm(FarmMes farm) {
		this.farm = farm;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getLivestockId() {
		return livestockId;
	}
	public void setLivestockId(String livestockId) {
		this.livestockId = livestockId;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	
}
