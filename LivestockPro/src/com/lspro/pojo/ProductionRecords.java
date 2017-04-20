package com.lspro.pojo;

/**
 * Description:
 * 生产记录的基本信息.
 * @author 谢福成
 * @version 1.0
 *
 */
 
 
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="生产记录表")
public class ProductionRecords implements Serializable{
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="圈舍号")
	private String roomNum;  //圈舍号(畜禽饲养的圈，舍，栏的编号或名称)
	@Column(name="时间")
	private String recordDate;  //时间(出生,调入,调出和死淘的时间)
	@Column(name="出生数目")
	private Integer birthNum;	//出生数目
	@Column(name="调入数目")
	private Integer putNum;		//调入数目,调入的需要在备注栏注明动物检疫合格证明编号
	@Column(name="调出数目")
	private Integer inNum;		//调出数目,调出的需要在备注栏注明详细的去向
	@Column(name="死淘数目")
	private Integer deadNum;	//死淘数目,死亡的需要在备注栏注明死亡和淘汰的原因。
	@Column(name="存栏数")
	private Integer remainNum;	//上次存栏和变动的数量之和
	@Column(name="备注")
	private String note;	//备注

	@ManyToOne(targetEntity=FarmMes.class,fetch=FetchType.EAGER)
	@JoinColumn(name="畜禽标识代码",referencedColumnName="畜禽标识代码",nullable=false)
	private FarmMes farm;
	
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getBirthNum() {
		return birthNum;
	}
	public void setBirthNum(Integer birthNum) {
		this.birthNum = birthNum;
	}
	public Integer getPutNum() {
		return putNum;
	}
	public void setPutNum(Integer putNum) {
		this.putNum = putNum;
	}
	public Integer getInNum() {
		return inNum;
	}
	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}
	public Integer getDeadNum() {
		return deadNum;
	}
	public void setDeadNum(Integer deadNum) {
		this.deadNum = deadNum;
	}
	public Integer getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
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
	
}
