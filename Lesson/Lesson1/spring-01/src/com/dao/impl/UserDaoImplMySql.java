package com.dao.impl;

import com.dao.IUserDao;
//ctl +shift +o ����
public class UserDaoImplMySql implements IUserDao{

	public void addUser() {
		System.out.println("addUser ����������  mysqlʵ��");
	}

	public void updateUser() {
		System.out.println(" updateUser ����������  mysqlʵ��");
	}

	public void deleteUser() {
		System.out.println("deleteUser ����������  mysqlʵ��");
	}
}
