package com.lspro.pojo;

/**
 * Desperation:
 * 技术人员基本信息,包括工作id,专业方向等<br>
 * 设置了所有属性相应的geter和setter方法。<br/>
 * 重写了equals和hashCode方法。<br/>
 * 使用了hibernate4.1框架，为属性进行了hibernate注解。<br/>
 * @author 阿呆
 * @see FarmMes
 * @version 1.0
 */


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TechnicalPerson implements Serializable{

	@Column(name = "专业方向")
	private String professSkill;    //专业方向
	@Column(name = "技术人员数量")
	private String personNum;			//工作id
	public String getProfessSkill() {
		return professSkill;
	}
	public void setProfessSkill(String professSkill) {
		this.professSkill = professSkill;
	}
	public String getPersonNum() {
		return personNum;
	}
	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	
}
