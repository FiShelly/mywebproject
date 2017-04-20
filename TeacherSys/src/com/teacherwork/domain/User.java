package com.teacherwork.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "用户信息表")
public class User implements Serializable {
	@Id
	@Column(name = "登录账号", length = 20)
	private String loginId;
	@Column(name = "登录密码", length = 50)
	private String pw;
	@Column(name = "用户名", length = 20)
	private String userName;
	@Column(name = "出生日期", length = 20)
	private String birthDate;
	@OneToOne(targetEntity=Title.class,fetch=FetchType.EAGER)
	@JoinColumn(name = "职称", referencedColumnName = "id", nullable = false)
	private Title title;
	@OneToOne(targetEntity=Post.class,fetch=FetchType.EAGER)
	@JoinColumn(name = "职务", referencedColumnName = "id", nullable=false)
	private Post post;
	@Column(name = "状态", length = 10)
	private boolean state;
	@Column(name = "角色", length = 5)
	private int role;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "User [loginId=" + loginId + ", pw=" + pw + ", userName="
				+ userName + ", birthDate=" + birthDate + ", title=" + title
				+ ", post=" + post + ", state=" + state + ", role=" + role
				+ "]";
	}
	
}
