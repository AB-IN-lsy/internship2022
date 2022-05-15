package com.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserInfo {
	
	public UserInfo(int id, String userName, String password, String note) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.note = note;
	}
	
	public UserInfo() {	
	}
	
	private int id;
	private String userName;
	private String password;
	private String note;
	private String photo;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
