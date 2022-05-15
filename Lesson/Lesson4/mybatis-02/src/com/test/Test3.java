package com.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.beans.UserInfo;
import com.mapper.UserMapper;

public class Test3 {

	public static void main(String[] args) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();	

		UserMapper userMapper =session.getMapper(UserMapper.class);
		
		/*
		UserInfo user=userMapper.getUserById(1);
		System.out.println(user);  */
		
		/*
		UserInfo user=userMapper.login("admin","123");
		System.out.println(user); */
		
		/*
		List<UserInfo> userList=userMapper.getAll();
		userList.forEach(System.out::println); */
		
		/*
		UserInfo user=new UserInfo();
		user.setUserName("注解用户");
		user.setPassword("pwd111");
		userMapper.addUser(user);
		System.out.println("生成的自增主键是:"+user.getId()); */
		
		/*
		UserInfo user=userMapper.getUserById(1);
		user.setUserName("新用户新用户");
		user.setPassword("新密码新密码");
		userMapper.updateUser(user);
		System.out.println("ok"); */
		
		int result=userMapper.deleteUser(20);
		System.out.println(result==1?"删除成功":"删除失败");
		
		
		session.commit();
		session.close();

	}

}
