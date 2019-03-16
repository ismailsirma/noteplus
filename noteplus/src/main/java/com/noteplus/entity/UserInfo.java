package com.noteplus.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="USERINFO")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="ROLE")	
	private String role;
	@Column(name="FULL_NAME")	
	private String fullName;
	@Column(name="STATUS")	
	private boolean status;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
