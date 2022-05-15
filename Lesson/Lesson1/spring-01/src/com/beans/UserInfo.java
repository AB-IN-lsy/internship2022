package com.beans;

public class UserInfo {
	UserInfo(){
		System.out.println("userinfo对象的构造方法执行");
	}
	
	private int id;
	private String userName;
	private String password;
	private String note;
	
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName + ", password=" + password + ", note=" + note + "]";
	}
	
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
}
