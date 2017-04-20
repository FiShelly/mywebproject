package com.lspro.pojo;
/**
 * Description:
 * 物资储备基本信息.
 * @author 谢福成
 * @version 1.0
 *
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="物资储备点表")
public class Supplies implements Serializable {
	
	@Id @Column(name="储备点编号")
	private String reserveId;
	@Column(name="储备点名称")
	private String name;
	@Column(name="储备点")
	private String address;	//储备点
	@Column(name="储备点_省份")
	private String province;
	@Column(name="储备点_地级市")
	private String city;
	@Column(name="储备点_县级市")
	private String country;
	@Column(name="储备点_详细地址")
	private String detail;
	@Column(name="管理单位")
	private String managementstation;	//管理单位
	@Column(name="负责人")
	private String head;	//负责人
	@Column(name="联系方式")
	private String phoneNum;
	@Column(name="职位")
	private String position;
	@Column(name="注册日期")
	private String registDate;
	@OneToMany(targetEntity=SuppliesItem.class,mappedBy="supplies",fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<SuppliesItem> supItems = new ArrayList<SuppliesItem>();
	
	public Supplies(){
		
	}
	
	public Supplies(List<SuppliesItem> supItems) {
		super();
		this.supItems = supItems;
	}

	public Supplies(String reserveId, String address, String managementstation,
			String head, String phoneNum, String position, String registDate,String name) {
		super();
		this.reserveId = reserveId;
		this.address = address;
		this.managementstation = managementstation;
		this.head = head;
		this.phoneNum = phoneNum;
		this.position = position;
		this.registDate = registDate;
		this.name = name;
	}
	public String getReserveId() {
		return reserveId;
	}
	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
	public String getManagementstation() {
		return managementstation;
	}
	public void setManagementstation(String managementstation) {
		this.managementstation = managementstation;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<SuppliesItem> getSupItems() {
		return supItems;
	}
	public void setSupItems(List<SuppliesItem> supItems) {
		this.supItems = supItems;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	@Override
	public String toString() {
		return "Supplies [reserveId=" + reserveId + ", address=" + address
				+ ", province=" + province + ", city=" + city + ", country="
				+ country + ", detail=" + detail + ", managementstation="
				+ managementstation + ", head=" + head + ", phoneNum="
				+ phoneNum + ", position=" + position + ", registDate="
				+ registDate + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
