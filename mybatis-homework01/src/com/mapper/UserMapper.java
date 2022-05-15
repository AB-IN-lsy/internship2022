package com.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.beans.User;

public interface UserMapper {
	
	int add(User user);
	
	@Update("update user set userName=#{userName} ,userPass= #{userPass} where userId =#{userId}")
	int update(User user);
	
	List<User> getUserList();
	
	@Delete("delete from user where userId =#{userId}")
	int delete(int userId);
	
	int count(User user);
	
	@Select("select * from user where userName=#{userName} and userPass=#{userPass}")
	User login(User user);
}