package com.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.beans.UserInfo;
import com.mapper.UserMapper;

public class Test {
	
	public static void main(String[] args) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		
		//这个方法,就是得到mybatis帮我创建的代理对象
		
		UserMapper userMapper =session.getMapper(UserMapper.class);
		UserInfo user=new UserInfo();
		user.setUserName("aaa");
		user.setPassword("123");
		
		List<UserInfo> userList1= userMapper.selectList(user);
		userList1.forEach(System.out::println);
		
		int count=userMapper.getUserCount(user);
		System.out.println("用户总数:"+count); 
		
		/*
		List<UserInfo> userList=userMapper.getXXXX("select * from userInfo where id in(1,9,11) ");
		userList.forEach(System.out::println); */
		
		/*
		List<Integer> idList=new ArrayList<>();
		idList.add(1);
		idList.add(9);
		idList.add(11);
		idList.add(12);
		idList.add(13);
		
		List<UserInfo> userList=userMapper.getUserListByIds(idList);
		userList.forEach(System.out::println);
		*/
				
		/*
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
		userList4.forEach(System.out::println);  */

		
			
		/*增
		UserInfo user=new UserInfo();
		user.setUserName("mybatis用户");
		user.setPassword("abc");
		user.setNote("中等管理员");
		userMapper.addUser(user);
		System.out.println("添加成功"); 

		//查询一个
		UserInfo user= userMapper.getUserById(21);
		System.out.println(user);
		
		//更新
		user.setUserName("新用户XXX");
		user.setPassword("admin111");
		int result=userMapper.updateUser(user);
		System.out.println(result==1?"更新成功":"更新失败");
		
		//查询所有
		List<UserInfo> userList= userMapper.getAll();
		for(UserInfo u:userList) {
			System.out.println(u);
		}
		
		//删除
		result=userMapper.deleteUser(21);
		System.out.println(result==1?"删除成功":"删除失败");  
		
		
		UserInfo user=userMapper.login("admin", "123");
		System.out.println(user==null?"登录失败":"登录成功,信息如下:"+user);
		*/
		
		session.commit();
		session.close();
	}


}
