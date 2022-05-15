package com.beans;

import java.util.Date;


public class User {
	    private Integer userId;   //主键,自增id
	
	    private String userName;  //账号
	
	    private String userPass;  //密码
	
	    private String userNickname;  //昵称
	
	    private String userEmail;  //邮箱	
	
	    private String userUrl;  //用户网址
	
	    private String userAvatar;   //头象图片地址
	
	    private String userLastLoginIp;  //最后登录的ip地址
	    
	    private Date userRegisterTime;  //注册时间
	
	    private Date userLastLoginTime; //最后登录时间
	
	    private Integer userStatus;  //用户状态
	    
	    private String userPhoto;   //图片
	    
		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPass() {
			return userPass;
		}

		public void setUserPass(String userPass) {
			this.userPass = userPass;
		}

		public String getUserNickname() {
			return userNickname;
		}

		public void setUserNickname(String userNickname) {
			this.userNickname = userNickname;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getUserUrl() {
			return userUrl;
		}

		public void setUserUrl(String userUrl) {
			this.userUrl = userUrl;
		}

		public String getUserAvatar() {
			return userAvatar;
		}

		public void setUserAvatar(String userAvatar) {
			this.userAvatar = userAvatar;
		}

		public String getUserLastLoginIp() {
			return userLastLoginIp;
		}

		public void setUserLastLoginIp(String userLastLoginIp) {
			this.userLastLoginIp = userLastLoginIp;
		}

		public Date getUserRegisterTime() {
			return userRegisterTime;
		}

		public void setUserRegisterTime(Date userRegisterTime) {
			this.userRegisterTime = userRegisterTime;
		}

		public Date getUserLastLoginTime() {
			return userLastLoginTime;
		}

		public void setUserLastLoginTime(Date userLastLoginTime) {
			this.userLastLoginTime = userLastLoginTime;
		}

		public Integer getUserStatus() {
			return userStatus;
		}

		public void setUserStatus(Integer userStatus) {
			this.userStatus = userStatus;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userNickname="
					+ userNickname + ", userEmail=" + userEmail + ", userUrl=" + userUrl + ", userAvatar=" + userAvatar
					+ ", userLastLoginIp=" + userLastLoginIp + ", userRegisterTime=" + userRegisterTime
					+ ", userLastLoginTime=" + userLastLoginTime + ", userStatus=" + userStatus + "]";
		}

		public String getUserPhoto() {
			return userPhoto;
		}

		public void setUserPhoto(String userPhoto) {
			this.userPhoto = userPhoto;
		}
}
