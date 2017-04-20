package com.teacherwork.domain;

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

@SuppressWarnings("serial")
@Entity
@Table(name="工作量记录表")
public class Work implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="项目名称",length=10)
	private String itemName;
	@Column(name="完成数目",length=10)
	private double comNum;
	@Column(name="系数",length=10)
	private double coefficient;
	@Column(name="工作量",length=10)
	private double allWork;
	@Column(name="是否新开课程",length=5)
	private boolean isNewClass;
	@Column(name="是否双语教学",length=5)
	private boolean isTwoLauage;
	@Column(name="人数",length=10)
	private int stuNum;
	@Column(name="班级数",length=10)
	private int classNum;
	@Column(name="审核状态",length=1)
	private int state;  //0未审核       1审核但不通过        2审核通过        3重新审核
	@ManyToOne(targetEntity=YearTerm.class,fetch=FetchType.EAGER )
	@JoinColumn(name="学期",referencedColumnName="id",nullable=false)
	private YearTerm term;
	@ManyToOne(targetEntity=Type.class,fetch=FetchType.EAGER )
	@JoinColumn(name="类别",referencedColumnName="id",nullable=false)
	private Type type;
	@ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER )
	@JoinColumn(name="用户",referencedColumnName="登录账号",nullable=false)
	private User user;
	@Column(name="反馈内容")
	private String fbContent;
	@Column(name="补充说明")
	private String supplement;
	@ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER )
	@JoinColumn(name="审核人",referencedColumnName="登录账号")
	private User duser;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getComNum() {
		return comNum;
	}
	public void setComNum(double comNum) {
		this.comNum = comNum;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public double getAllWork() {
		return allWork;
	}
	public void setAllWork(double allWork) {
		this.allWork = allWork;
	}
	public boolean getIsNewClass() {
		return isNewClass;
	}
	public void setIsNewClass(boolean isNewClass) {
		this.isNewClass = isNewClass;
	}
	public boolean getIsTwoLauage() {
		return isTwoLauage;
	}
	public void setIsTwoLauage(boolean isTwoLauage) {
		this.isTwoLauage = isTwoLauage;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public YearTerm getTerm() {
		return term;
	}
	public void setTerm(YearTerm term) {
		this.term = term;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFbContent() {
		return fbContent;
	}
	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}
	public String getSupplement() {
		return supplement;
	}
	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}
	public User getDuser() {
		return duser;
	}
	public void setDuser(User duser) {
		this.duser = duser;
	}
	@Override
	public String toString() {
		return "Work [id=" + id + ", itemName=" + itemName + ", comNum="
				+ comNum + ", coefficient=" + coefficient + ", allWork="
				+ allWork + ", isNewClass=" + isNewClass + ", isTwoLauage="
				+ isTwoLauage + ", stuNum=" + stuNum + ", classNum=" + classNum
				+ ", state=" + state + ", term=" + term + ", type=" + type
				+ ", user=" + user + ", fbContent=" + fbContent
				+ ", supplement=" + supplement + ", duser=" + duser + "]";
	}
	
}
