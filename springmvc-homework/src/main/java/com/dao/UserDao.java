package com.dao;
import java.util.ArrayList;
import java.util.List;
import com.beans.UserInfo;

public class UserDao {
	private List<UserInfo> userList;
	
	{
		userList=new ArrayList<UserInfo>();
		userList.add(new UserInfo(1,"admin1","123","一号用户"));
		userList.add(new UserInfo(2,"admin2","123","二号用户"));
		userList.add(new UserInfo(3,"admin3","123","三号用户"));
		userList.add(new UserInfo(4,"admin4","123","四号用户"));
	}

	public void addUser() {
	}
	
	public int deleteUser() {
		return 1;
	}
	
	public UserInfo getUserById() {
		return null;
	}
	
	public List<UserInfo> getAll(){
		return userList;
	}	
}