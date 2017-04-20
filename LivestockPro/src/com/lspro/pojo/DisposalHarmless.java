package com.lspro.pojo;

/**
 * Description:
 * 病死畜禽无害化处理记录基本数据表。<br/>
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
@Table(name="病死畜禽无害化处理记录表")
public class DisposalHarmless implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="处理日期")
	private String disposalTime;	//处理日期
	@Column(name="数量")
	private Integer number;		//数量
	@Column(name="处理或死亡原因")
	private String disposalOrResult;	//处理或死亡原因
	@Column(name="处理方法")
	private String disposalMethod;	//处理方法
	@Column(name="处理单位或负责人")
	private String disposalStation;	//处理单位
	@Column(name="备注")		
	private String note;	//备注
	
	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER)
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDisposalTime() {
		return disposalTime;
	}
	public void setDisposalTime(String disposalTime) {
		this.disposalTime = disposalTime;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDisposalOrResult() {
		return disposalOrResult;
	}
	public void setDisposalOrResult(String disposalOrResult) {
		this.disposalOrResult = disposalOrResult;
	}
	
	public String getDisposalMethod() {
		return disposalMethod;
	}
	public void setDisposalMethod(String disposalMethod) {
		this.disposalMethod = disposalMethod;
	}
	public String getDisposalStation() {
		return disposalStation;
	}
	public void setDisposalStation(String disposalStation) {
		this.disposalStation = disposalStation;
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
