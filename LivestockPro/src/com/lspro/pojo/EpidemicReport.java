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
@Table(name="疫情上报信息表")
public class EpidemicReport implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER )
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	@Column(name="发病动物种类")
	private String sickSpecies;
	@Column(name="饲养种类")
	private String feedSpecies;
	@Column(name="饲养规模")
	private String feedScale;
	@Column(name="饲养情况")
	private String feedSitua;
	@Column(name="临床症状")
	private String clinicalSysp;
	@Column(name="死亡情况")
	private String deaths;
	@Column(name="是否有人感染")
	private boolean isPeoInfect;
	@Column(name="封锁情况")
	private String blockadeSitua;
	@Column(name="免疫情况")
	private String immunitySitua;
	@Column(name="治疗情况")
	private String treatmentSitua;
	@Column(name="审核及处理情况")
	private int status;
	@Column(name="爆发日期")
	private String date;
	
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
	public String getSickSpecies() {
		return sickSpecies;
	}
	public void setSickSpecies(String sickSpecies) {
		this.sickSpecies = sickSpecies;
	}
	public String getFeedScale() {
		return feedScale;
	}
	public void setFeedScale(String feedScale) {
		this.feedScale = feedScale;
	}
	public String getFeedSitua() {
		return feedSitua;
	}
	public void setFeedSitua(String feedSitua) {
		this.feedSitua = feedSitua;
	}
	public String getClinicalSysp() {
		return clinicalSysp;
	}
	public void setClinicalSysp(String clinicalSysp) {
		this.clinicalSysp = clinicalSysp;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public boolean getIsPeoInfect() {
		return isPeoInfect;
	}
	public void setPeoInfect(boolean isPeoInfect) {
		this.isPeoInfect = isPeoInfect;
	}
	public String getBlockadeSitua() {
		return blockadeSitua;
	}
	public void setBlockadeSitua(String blockadeSitua) {
		this.blockadeSitua = blockadeSitua;
	}
	public String getImmunitySitua() {
		return immunitySitua;
	}
	public void setImmunitySitua(String immunitySitua) {
		this.immunitySitua = immunitySitua;
	}
	public String getTreatmentSitua() {
		return treatmentSitua;
	}
	public void setTreatmentSitua(String treatmentSitua) {
		this.treatmentSitua = treatmentSitua;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "EpidemicReport [id=" + id + ", farm=" + farm + ", sickSpecies="
				+ sickSpecies + ", feedScale=" + feedScale + ", feedSitua="
				+ feedSitua + ", clinicalSysp=" + clinicalSysp + ", deaths="
				+ deaths + ", isPeoInfect=" + isPeoInfect + ", blockadeSitua="
				+ blockadeSitua + ", immunitySitua=" + immunitySitua
				+ ", treatmentSitua=" + treatmentSitua + ", status=" + status
				+ ", date=" + date + "]";
	}
	public String getFeedSpecies() {
		return feedSpecies;
	}
	public void setFeedSpecies(String feedSpecies) {
		this.feedSpecies = feedSpecies;
	}
	
	
}
