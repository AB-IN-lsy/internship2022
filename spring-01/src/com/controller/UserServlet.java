package com.controller;
import com.dao.IUserDao;

public class UserServlet {
	private IUserDao dao;
	
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}
	
	public void service() {
		dao.addUser();
		dao.updateUser();
		dao.deleteUser();
	}
	
}
