package com.lspro.pojo;

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
@Entity
@Table(name="物资调度信息表")
public class SuppliesDispatch implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="调度日期")
	private String date;
	@Column(name="调度数量")
	private String number;
	@Column(name="调度省份")
	private String province;
	@Column(name="调度地级市")
	private String city;
	@Column(name="调度县级市")
	private String country;
	@Column(name="详细地址")
	private String detail;
	@Column(name="调度地址")
	private String targerAddress;
	@Column(name="是否到达")
	private boolean isArrive;
	@ManyToOne(targetEntity=SuppliesItem.class,fetch=FetchType.EAGER )
	@JoinColumn(name="物资编号",referencedColumnName="物资编号",nullable=false)
	private SuppliesItem item;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTargerAddress() {
		return targerAddress;
	}
	public void setTargerAddress(String targerAddress) {
		this.targerAddress = targerAddress;
	}
	public SuppliesItem getItem() {
		return item;
	}
	public void setItem(SuppliesItem item) {
		this.item = item;
	}
	public boolean getIsArrive() {
		return isArrive;
	}
	public void setArrive(boolean isArrive) {
		this.isArrive = isArrive;
	}
	
}
