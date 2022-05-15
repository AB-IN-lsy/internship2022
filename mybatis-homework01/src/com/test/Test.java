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
		user.setUserLastLoginIp("������");
		user.setUserStatus(1);
		int result = userMapper.add(user);
		System.out.println(result == 1 ? "��ӳɹ�������Ϊ" + user.getUserId() :"���ʧ��" );
	}
	
	private static void update(UserMapper userMapper) {
		// update
		User user = new User();
		user.setUserId(2);
		user.setUserName("icey_dying");
		user.setUserPass("321");
		int result = userMapper.update(user);
		System.out.println(result == 1 ? "���³ɹ�" :"����ʧ��" );
	}
	
	private static void getUserList(UserMapper userMapper) {
		// getUserList
		List<User> list = userMapper.getUserList();
		list.forEach(System.out::println);
	}
	
	private static void delete(UserMapper userMapper) {
		// delete
		int result = userMapper.delete(3);
		System.out.println(result == 1 ? "ɾ���ɹ�" :"ɾ��ʧ��" );
	}
	
	private static void count(UserMapper userMapper) {
		// count
		User user = new User();
		user.setUserName("admin");
		int count = userMapper.count(user);
		System.out.println("�û�����:" + count); 
	}
	
	private static void login(UserMapper userMapper) {
		// login
		User userLogin = new User();
		userLogin.setUserName("admin");
		userLogin.setUserPass("123");
		
		User user = userMapper.login(userLogin);
		if(user!=null) {
			System.out.println("��¼�ɹ�,�û���Ϣ����:");
			System.out.println(user);
		}
		else {
			System.out.println("��¼ʧ��");
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
		SqlSession session =factory.openSession();
		
		//�������,���ǵõ�mybatis���Ҵ����Ĵ������
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