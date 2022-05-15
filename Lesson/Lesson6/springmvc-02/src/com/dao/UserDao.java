package com.dao;
import java.util.ArrayList;
import java.util.List;
import com.beans.UserInfo;

public class UserDao {
	private static List<UserInfo> userList;
	
	static {
		userList=new ArrayList<UserInfo>();
		userList.add(new UserInfo(1,"admin","123","一号用户"));
		userList.add(new UserInfo(2,"root","root","二号用户"));
		userList.add(new UserInfo(3,"sa","sa","三号用户"));
		userList.add(new UserInfo(4,"scott","scott","四号用户"));
	}
	
	public void updateUser(UserInfo user) {
		for(int i=0;i<userList.size();i++) {
			if(userList.get(i).getId()==user.getId())
			{
				userList.set(i, user);
			}
		}
	}

	public void addUser(UserInfo user) {
		userList.add(user);
	}
	
	public void deleteUser(int id) {
		for(int i=0;i<userList.size();i++) {
			if(userList.get(i).getId()==id) {
				userList.remove(i);
				return;
			}
		}
	}
	
	public UserInfo getUserById(int id) {
		for(UserInfo u:userList) {
			if(u.getId()==id) {
				return u;
			}
		}
		
		return null;
	}
	
	public List<UserInfo> getAll(){
		return userList;
	}

	//根据id列表删除多个用户
	public void deleteUsers(Integer[] ids) {
		List<UserInfo> tmpList=new ArrayList<>();
		for(int id:ids) {
			for(UserInfo u: userList) {
				if(u.getId()==id) {
					tmpList.add(u);
				}
			}	
		}
		
		for(UserInfo u:tmpList) {
			userList.remove(u);
		}
	}	
}
