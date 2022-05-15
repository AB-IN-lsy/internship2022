package com.beans;

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
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName + ", password=" + password + ", note=" + note + "]";
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

	private String userName;
	private String password;
	private String note;
}