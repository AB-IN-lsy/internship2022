package com.controller;
import java.util.Map;
import java.util.Set;

import com.dao.IUserDao;

public class UserServlet {	
	//��������
	private IUserDao dao;
	
	//set����
	private Set<String> mySet;
	
	//map����
	private Map<String,String> myMap;

	public void service() {
		dao.addUser();
		dao.updateUser();
		dao.deleteUser();	
		
		mySet.forEach( e->System.out.println(e));
		
		myMap.forEach((k,v)->System.out.println(k+":"+v));
	}
	
	public void setMySet(Set<String> mySet) {
		this.mySet = mySet;
	}

	public void setMyMap(Map<String, String> myMap) {
		this.myMap = myMap;
	}
	
	//�������,������spring�������õ�,�����Ǵ�����������
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}
}
