package com.lspro.pojo;

/**
 * Description:
 * 动物检疫合格证明表(动物B)。<br/>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 谢福成
 * @version 1.0
 * 
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="动物检疫合格证明表_动物B")
public class AnimalB implements Serializable {
	@Id @Column(name="编号")
	private String id;
	@Column(name="货主名")
	private String shipperName;
	@Column(name="联系电话")
	private String phoneNum;
	@Column(name="动物种类")
	private String animalSpecies;
	@Column(name="数量以及单位")
	private String number;
	@Column(name="用途")
	private String use;
	@Column(name="启运地点")
	private String startAddress;
	@Column(name="到达地点")
	private String destination;
	@Column(name="启运地点_省份")
	private String startProvince;
	@Column(name="启运地点_地级市")
	private String startCity;
	@Column(name="启运地点_县级市")
	private String startCountry;
	@Column(name="启运地点_详细")
	private String startDetail;
	@Column(name="到达地点_省份")
	private String endProvince;
	@Column(name="到达地点_地级市")
	private String endCity;
	@Column(name="到达地点_县级市")
	private String endCountry;
	@Column(name="到达地点_详细")
	private String endDetail;
	@ElementCollection(targetClass=String.class,fetch=FetchType.EAGER)
	@CollectionTable(name="牲畜耳标表_B" ,joinColumns=@JoinColumn(name="编号",nullable=false))
	@Column(name="牲畜耳标号")
	@OrderColumn(name="索引")
	private List<String> animalId;
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
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAnimalSpecies() {
		return animalSpecies;
	}
	public void setAnimalSpecies(String animalSpecies) {
		this.animalSpecies = animalSpecies;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
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
	public List<String> getAnimalId() {
		return animalId;
	}
	public void setAnimalId(List<String> animalId) {
		this.animalId = animalId;
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
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
