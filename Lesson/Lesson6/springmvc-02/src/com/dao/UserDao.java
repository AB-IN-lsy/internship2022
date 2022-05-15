package com.dao;
import java.util.ArrayList;
import java.util.List;
import com.beans.UserInfo;

public class UserDao {
	private static List<UserInfo> userList;
	
	static {
		userList=new ArrayList<UserInfo>();
		userList.add(new UserInfo(1,"admin","123","һ���û�"));
		userList.add(new UserInfo(2,"root","root","�����û�"));
		userList.add(new UserInfo(3,"sa","sa","�����û�"));
		userList.add(new UserInfo(4,"scott","scott","�ĺ��û�"));
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

	//����id�б�ɾ������û�
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
