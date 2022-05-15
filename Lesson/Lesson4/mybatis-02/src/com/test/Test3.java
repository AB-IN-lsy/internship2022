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
		user.setUserName("ע���û�");
		user.setPassword("pwd111");
		userMapper.addUser(user);
		System.out.println("���ɵ�����������:"+user.getId()); */
		
		/*
		UserInfo user=userMapper.getUserById(1);
		user.setUserName("���û����û�");
		user.setPassword("������������");
		userMapper.updateUser(user);
		System.out.println("ok"); */
		
		int result=userMapper.deleteUser(20);
		System.out.println(result==1?"ɾ���ɹ�":"ɾ��ʧ��");
		
		
		session.commit();
		session.close();

	}

}
