package com.dao;
import java.util.ArrayList;
import java.util.List;
import com.beans.UserInfo;

public class UserDao {
	private List<UserInfo> userList;
	
	{
		userList=new ArrayList<UserInfo>();
		userList.add(new UserInfo(1,"admin1","123","һ���û�"));
		userList.add(new UserInfo(2,"admin2","123","�����û�"));
		userList.add(new UserInfo(3,"admin3","123","�����û�"));
		userList.add(new UserInfo(4,"admin4","123","�ĺ��û�"));
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