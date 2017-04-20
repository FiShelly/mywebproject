package com.lspro.pojo;

/**
 * Description:
 * 动物检疫合格证明表(产品B)。<br/>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 谢福成
 * @version 1.0
 * 
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="动物检疫合格证明表_产品B")
public class ProductB implements Serializable {
	@Id @Column(name="编号")
	private String id;
	@Column(name="货主名")
	private String shipperName;
	@Column(name="产品名称")
	private String productName;
	@Column(name="数量以及单位")
	private String number;
	@Column(name="产地")
	private String producter;
	@Column(name="生产地址及名字")
	private String addressName;
	@Column(name="目的地")
	private String destination;
	@Column(name="生产单位_省份")
	private String startProvince;
	@Column(name="生产单位_地级市")
	private String startCity;
	@Column(name="生产单位_县级市")
	private String startCountry;
	@Column(name="生产单位_详细")
	private String startDetail;
	@Column(name="目的地_省份")
	private String endProvince;
	@Column(name="目的地_地级市")
	private String endCity;
	@Column(name="目的地_县级市")
	private String endCountry;
	@Column(name="目的地_详细")
	private String endDetail;
	@Column(name="检疫标志号")
	private String quarantinemarks;
	@Column(name="备注")
	private String note;
	@Column(name="签发日期")
	private String date;
	@Column(name="合格证有效天数")
	private String days;
	@Column(name="经手兽医姓名")
	private String vetName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getStartProvince() {
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getStartCountry() {
		return startCountry;
	}
	public void setStartCountry(String startCountry) {
		this.startCountry = startCountry;
	}
	public String getStartDetail() {
		return startDetail;
	}
	public void setStartDetail(String startDetail) {
		this.startDetail = startDetail;
	}
	public String getEndProvince() {
		return endProvince;
	}
	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getEndCountry() {
		return endCountry;
	}
	public void setEndCountry(String endCountry) {
		this.endCountry = endCountry;
	}
	public String getEndDetail() {
		return endDetail;
	}
	public void setEndDetail(String endDetail) {
		this.endDetail = endDetail;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getQuarantinemarks() {
		return quarantinemarks;
	}
	public void setQuarantinemarks(String quarantinemarks) {
		this.quarantinemarks = quarantinemarks;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getProducter() {
		return producter;
	}
	public void setProducter(String producter) {
		this.producter = producter;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getVetName() {
		return vetName;
	}
	public void setVetName(String vetName) {
		this.vetName = vetName;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
