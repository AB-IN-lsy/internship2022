package com.dao.impl;

import com.dao.IUserDao;
//ctl +shift +o 导包
public class UserDaoImplMySql implements IUserDao{

	public void addUser() {
		System.out.println("addUser 方法调用了  mysql实现");
	}

	public void updateUser() {
		System.out.println(" updateUser 方法调用了  mysql实现");
	}

	public void deleteUser() {
		System.out.println("deleteUser 方法调用了  mysql实现");
	}
}
