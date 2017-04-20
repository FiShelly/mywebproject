package com.teacherwork.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="信息表")
public class Message implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="发送方Id",length=20)
	private String fromId;
	@Column(name="发送方姓名",length=20)
	private String fromName;
	@Column(name="接收方姓名",length=20)
	private String toName;
	@Column(name="接收方Id",length=20)
	private String toId;
	@Column(name="内容")
	private String content;
	@Column(name="日期",length=20)
	private String date;
	@Column(name="是否已读",length=2)
	private boolean isRead;
	@Column(name="通过状态",length=1)
	private int passState; // 0提交，未审核  1提交未通过  2 提交通过
	@Column(name="年份",length=4)
	private int years;
	@Column(name="发送方角色",length=2)
	private boolean fromRole; //true 为dean  false为teacher
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean getIsRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public int getPassState() {
		return passState;
	}
	public void setPassState(int passState) {
		this.passState = passState;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", fromId=" + fromId + ", fromName="
				+ fromName + ", toName=" + toName + ", toId=" + toId
				+ ", content=" + content + ", data=" + date + ", isRead="
				+ isRead + ", passState=" + passState + ", years=" + years
				+ "]";
	}
	public boolean isFromRole() {
		return fromRole;
	}
	public void setFromRole(boolean fromRole) {
		this.fromRole = fromRole;
	}
}
