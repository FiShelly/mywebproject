package com.lspro.pojo;

/**
 * Description:
 * 养殖场档案基本信息，如名称，品种，规模，地址等。<br/>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 谢福成
 * @see TechnicalPerson
 * @version 2.0(在版本1.0的基础修改了数据表相应字段,由英文变成中文)
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name ="畜禽养殖信息表")
public class FarmMes implements Serializable {
	@Id @Column(name="畜禽标识代码" )
	private String farmId;    //畜禽标识代码
	@Column(name="养殖场名称")
	private String farmName;  //名称
	@Column(name = "畜禽种类")
	private String species;  //养殖品种
	@Column(name="规模")
	private Integer farmSize;  //规模
	@Column(name="地址")
	private String location;
	@Column(name="所在省份")
	private String province;
	@Column(name="所在地级市")
	private String city;
	@Column(name="所在县级市")
	private String country;
	@Column(name="详细地址")
	private String detail;
	@Column(name="负责人姓名")
	private String leader;    //负责人姓名
	@Column(name="邮编")
	private String zipcode;   //邮编
	@Column(name="联系号码")
	private String phoneNum;	//联系号码
	//private 生产场所和配套生产设施（主要生产工艺
	@ElementCollection(targetClass=TechnicalPerson.class,fetch=FetchType.EAGER)
	@CollectionTable(name = "技术人员信息表", joinColumns=@JoinColumn(name = "畜禽标识代码" , nullable = false))
	@Column(name = "技术人员姓名")
	@OrderColumn(name = "索引")
	private List<TechnicalPerson> person = new ArrayList<TechnicalPerson>(); //技术人员信息
	@Column(name="动物防疫资格证编号")
	private String certificate ;  //动物防疫资格证编号
	@Column(name="生产设施")
	private String productFac;
	@Column(name="生产设备")
	private String productEquip;
	@Column(name="环保设施")
	private String envirEquip;  //  环保设施
	@Column(name="平面图信息")
	private String farmPhoto;  //平面图
	
	@Column(name="注册时间")
	private String registDate;
	
	/*
	 * 以下为属性的get和set方法。
	 */
	public String getFarmId() {
		return farmId;
	}
	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
	public String getFarmName() {
		return farmName;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public Integer getFarmSize() {
		return farmSize;
	}
	public void setFarmSize(Integer farmSize) {
		this.farmSize = farmSize;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public List<TechnicalPerson> getPerson() {
		return person;
	}
	public void setPerson(List<TechnicalPerson> person) {
		this.person = person;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getEnvirEquip() {
		return envirEquip;
	}
	public void setEnvirEquip(String envirEquip) {
		this.envirEquip = envirEquip;
	}

	public String getFarmPhoto() {
		return farmPhoto;
	}
	public void setFarmPhoto(String farmPhoto) {
		this.farmPhoto = farmPhoto;
	}
	
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getProductFac() {
		return productFac;
	}
	public void setProductFac(String productFac) {
		this.productFac = productFac;
	}
	public String getProductEquip() {
		return productEquip;
	}
	public void setProductEquip(String productEquip) {
		this.productEquip = productEquip;
	}

	
	/*
	 * 以下重写了hashCode和equals方法，以便用于对象相等判断。
	 */

	
 

}
