package com.lspro.pojo;

/**
 * Desperation:
 * 疫苗基本信息表<br>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 阿呆
 * @see ImmuneRecord
 * @version 1.0
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Vaccine implements Serializable {
	
	@Column(name="免疫数量")
	private Integer immuneNum;
	@Column(name="疫苗名称")
	private String vaccineName;
	@Column(name="疫苗产商")
	private String vaccineProducers;
	@Column(name="疫苗批号或有效期")
	private String vaccineValidTime;
	@Column(name="免疫方法")
	private String immuneMethod;
	@Column(name="免疫剂量")
	private Double immuneDosage;
	@Column(name="免疫人员")
	private String immunePeople;
	
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
	public Double getImmuneDosage() {
		return immuneDosage;
	}
	public void setImmuneDosage(Double immuneDosage) {
		this.immuneDosage = immuneDosage;
	}
	public String getImmunePeople() {
		return immunePeople;
	}
	public void setImmunePeople(String immunePeople) {
		this.immunePeople = immunePeople;
	}
	
	
}
