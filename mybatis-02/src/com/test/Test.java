package com.test;
import java.io.*;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import com.beans.UserInfo;
import com.mapper.UserMapper;

public class Test {
	
	public static void main(String[] args) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		
		//这个方法,就是得到mybatis帮我创建的代理对象
		UserMapper userMapper =session.getMapper(UserMapper.class);
	
		UserInfo userInfo = userMapper.getUserById(1);
		System.out.println(userInfo);
		
		UserInfo user=new UserInfo();
		List<UserInfo> userList1= userMapper.selectList(user);
		userList1.forEach(System.out::println);
		
		System.out.println("--------------");
		
		user.setUserName("aaa");
		List<UserInfo> userList2= userMapper.selectList(user);
		userList2.forEach(System.out::println);
		
		System.out.println("--------------");
		
		user.setPassword("123");
		List<UserInfo> userList3= userMapper.selectList(user);
		userList3.forEach(System.out::println);
		
		System.out.println("--------------");
		
		user.setId(18);
		List<UserInfo> userList4= userMapper.selectList(user);
		userList4.forEach(System.out::println);
		
		int count=userMapper.getUserCount(user);
		System.out.println("用户总数:"+count);
		
//		//增
//		UserInfo user=new UserInfo();
//		user.setUserName("admin");
//		user.setPassword("123");
//		user.setNote("中等管理员");
//		userMapper.addUser(user);
//		System.out.println("添加成功");
//		
//		
//		//查询一个
//		UserInfo user= userMapper.getUserById(3);
//		System.out.println(user);
//		
//		//更新
//		user.setUserName("新用户XXX");
//		user.setPassword("admin111");
//		int result=userMapper.updateUser(user);
//		System.out.println(result==1?"更新成功":"更新失败");
//		
//		//查询所有
//		List<UserInfo> userList= userMapper.getAll();
//		for(UserInfo u:userList) {
//			System.out.println(u);
//		}
//		
//		//删除
//		result=userMapper.deleteUser(3);
//		System.out.println(result==1?"删除成功":"删除失败");
		
		//登录
		userMapper.login("admin", "123");
		System.out.println(user == null ? "登录失败" : "登陆成功" + user);
		
		session.commit();
		session.close();
	}
}