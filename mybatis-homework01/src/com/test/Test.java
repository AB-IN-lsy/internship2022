package com.test;

import java.io.*;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import com.beans.User;
import com.mapper.UserMapper;

public class Test {
	
	private static void add(UserMapper userMapper) {
		// add
		User user = new User();
		user.setUserName("admin");
		user.setUserPass("123");
		user.setUserNickname("lsy");
		user.setUserEmail("s.y.liu19@nefu.edu.cn");
		user.setUserUrl("www.ab-in.cn");
		user.setUserAvatar("https://oss.ab-in.cn/images/AB-IN.jpg");
		user.setUserLastLoginIp("黑龙江");
		user.setUserStatus(1);
		int result = userMapper.add(user);
		System.out.println(result == 1 ? "添加成功，主键为" + user.getUserId() :"添加失败" );
	}
	
	private static void update(UserMapper userMapper) {
		// update
		User user = new User();
		user.setUserId(2);
		user.setUserName("icey_dying");
		user.setUserPass("321");
		int result = userMapper.update(user);
		System.out.println(result == 1 ? "更新成功" :"更新失败" );
	}
	
	private static void getUserList(UserMapper userMapper) {
		// getUserList
		List<User> list = userMapper.getUserList();
		list.forEach(System.out::println);
	}
	
	private static void delete(UserMapper userMapper) {
		// delete
		int result = userMapper.delete(3);
		System.out.println(result == 1 ? "删除成功" :"删除失败" );
	}
	
	private static void count(UserMapper userMapper) {
		// count
		User user = new User();
		user.setUserName("admin");
		int count = userMapper.count(user);
		System.out.println("用户总数:" + count); 
	}
	
	private static void login(UserMapper userMapper) {
		// login
		User userLogin = new User();
		userLogin.setUserName("admin");
		userLogin.setUserPass("123");
		
		User user = userMapper.login(userLogin);
		if(user!=null) {
			System.out.println("登录成功,用户信息如下:");
			System.out.println(user);
		}
		else {
			System.out.println("登录失败");
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		
		//这个方法,就是得到mybatis帮我创建的代理对象
		UserMapper userMapper =session.getMapper(UserMapper.class);
	
		// add
		// add(userMapper);
		
		// update
		// update(userMapper);
		
		// getUserList
		getUserList(userMapper);
		
		// delete
		// delete(userMapper);
		
		// count
		// count(userMapper);
		
		// login
		// login(userMapper);
		
		session.commit();
		session.close();
	}
}