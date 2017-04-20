package com.lspro.pojo;

/**
 * Description:
 * 饲料、饲料添加剂和兽药使用记录。<br/>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 谢福成
 * @version 1.0
 * 
 */

 

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="饲料或饲料添加剂或兽药用记录")
public class FoodDrugUseRecord implements Serializable {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="开始使用时间")
	private String startTime;    //开始使用时间
	@Column(name="投入产品名称")
	private String productName;	//投入产品
	@Column(name="生产厂家")
	private String manufacturer;	//生产厂商
	@Column(name="批号")
	private String batchNum;	//批号/加工日期
	@Column(name = "加工时间")
	private String processDate;
	@Column(name="用量")
	private String dosage;	//用量
	@Column(name="停止使用时间")
	private String stopTime;	//停止时间
	@Column(name="备注")
	private String note;	//备注(1、养殖场外购的饲料应在备注栏注明原料组成。
							//	  2、养殖场自加工的饲料在生产厂家栏填写自加工，并在备注栏写明使用的药物饲料添加剂的详细成分)

	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER )
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
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

	public String getProcessDate() {
		return processDate;
	}
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
 
	

}
