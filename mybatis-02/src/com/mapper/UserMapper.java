package com.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.beans.UserInfo;

public interface UserMapper {
	UserInfo getUserById(int id);
	
	List<UserInfo> getAll();
	
	int addUser(UserInfo user);
	
	int updateUser(UserInfo user);
	
	@Options(useGeneratedKeys = true)
	int deleteUser(int id);
	
	UserInfo login(@Param("userName") String userName, @Param("password") String password);

	//���������ϲ�ѯ
	List<UserInfo> selectList(UserInfo user);
	//��������ѯ�û�����
	int getUserCount(UserInfo user);
}