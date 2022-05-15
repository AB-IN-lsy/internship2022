package com.dao;
import java.util.ArrayList;
import java.util.List;
import com.beans.UserInfo;

public class UserDao {
	private List<UserInfo> userList;
	
	{
		userList=new ArrayList<UserInfo>();
		userList.add(new UserInfo(1,"admin","123","一号用户"));
		userList.add(new UserInfo(2,"root","root","二号用户"));
		userList.add(new UserInfo(3,"sa","sa","三号用户"));
		userList.add(new UserInfo(4,"scott","scott","四号用户"));
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
