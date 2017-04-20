package com.lspro.pojo;

/**
 * Description:
 * 消毒记录数据表基本信息。<br/>
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
@Table(name = "消毒记录表")
public class DisinfectionRecord implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;  //标识id
	
	@Column(name="消毒时间")
	private String disinfectionTime;	//消毒时间
	@Column(name="消毒场所")
	private String place;		//消毒场所
	@Column(name="消毒药名称")
	private String disinfectionName;	//消毒药名称
	@Column(name="消毒药剂量")
	private String disinfectionDose;		//消毒药剂量
	@Column(name="消毒方法")
	private String method;  //消毒方法
	@Column(name="负责人")
	private String sign;	//负责人签字
	
	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER)
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDisinfectionTime() {
		return disinfectionTime;
	}
	public void setDisinfectionTime(String disinfectionTime) {
		this.disinfectionTime = disinfectionTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDisinfectionName() {
		return disinfectionName;
	}
	public void setDisinfectionName(String disinfectionName) {
		this.disinfectionName = disinfectionName;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public FarmMes getFarm() {
		return farm;
	}
	public void setFarm(FarmMes farm) {
		this.farm = farm;
	}
	public String getDisinfectionDose() {
		return disinfectionDose;
	}
	public void setDisinfectionDose(String disinfectionDose) {
		this.disinfectionDose = disinfectionDose;
	}
}
