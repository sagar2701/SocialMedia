package com.backend.colloboration.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;


import org.springframework.stereotype.Component;

@Entity
@Component

public class C_User {
	 @Id
	
		//@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private String email;
	 
	 @Column(nullable=false)
		private String password;
	private String firstname;
	private String lastname;
	
   private long mobile;
    private String dob;
	private String userid;
	
	private String role;
	@Column(name="online_status")
	private boolean Status;
	@Column(name="status")

	public boolean onlinestatus;
	
	public boolean isOnlinestatus() {
		return onlinestatus;
	}

	
	public void setOnlinestatus(boolean onlinestatus) {
		this.onlinestatus = onlinestatus;
	}

	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean Status) {
		this.Status = Status;
	}

	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid=userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getDob() {
		return dob;
	}

	
	public void setDob(String dob) {
		this.dob = dob;
	}
	

	public String getRole() {
		return role;
	}

	
	public void setRole(String role) {
		this.role = role;
	}

	
	
	public C_User()
	{
		this.userid = "USER" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
	
			}
