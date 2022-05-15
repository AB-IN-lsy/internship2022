package com.controller;
import java.util.Map;
import java.util.Set;

import com.dao.IUserDao;

public class UserServlet {	
	//依赖对象
	private IUserDao dao;
	
	//set集合
	private Set<String> mySet;
	
	//map集合
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
	
	//这个方法,是留给spring容器调用的,帮我们传入依赖对象
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}
}
