package com.beans;

import java.util.Date;


public class User {
	    private Integer userId;   //????,????id
	
	    private String userName;  //?˺?
	
	    private String userPass;  //????
	
	    private String userNickname;  //?ǳ?
	
	    private String userEmail;  //????	
	
	    private String userUrl;  //?û???ַ
	
	    private String userAvatar;   //ͷ??ͼƬ??ַ
	
	    private String userLastLoginIp;  //??????¼??ip??ַ
	    
	    private Date userRegisterTime;  //ע??ʱ??
	
	    private Date userLastLoginTime; //??????¼ʱ??
	
	    private Integer userStatus;  //?û?״̬
	    
	    private String userPhoto;   //ͼƬ
	    
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
