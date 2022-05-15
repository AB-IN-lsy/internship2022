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
		
		//�������,���ǵõ�mybatis���Ҵ����Ĵ������
		
		UserMapper userMapper =session.getMapper(UserMapper.class);
		UserInfo user=new UserInfo();
		user.setUserName("aaa");
		user.setPassword("123");
		
		List<UserInfo> userList1= userMapper.selectList(user);
		userList1.forEach(System.out::println);
		
		int count=userMapper.getUserCount(user);
		System.out.println("�û�����:"+count); 
		
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

		
			
		/*��
		UserInfo user=new UserInfo();
		user.setUserName("mybatis�û�");
		user.setPassword("abc");
		user.setNote("�еȹ���Ա");
		userMapper.addUser(user);
		System.out.println("��ӳɹ�"); 

		//��ѯһ��
		UserInfo user= userMapper.getUserById(21);
		System.out.println(user);
		
		//����
		user.setUserName("���û�XXX");
		user.setPassword("admin111");
		int result=userMapper.updateUser(user);
		System.out.println(result==1?"���³ɹ�":"����ʧ��");
		
		//��ѯ����
		List<UserInfo> userList= userMapper.getAll();
		for(UserInfo u:userList) {
			System.out.println(u);
		}
		
		//ɾ��
		result=userMapper.deleteUser(21);
		System.out.println(result==1?"ɾ���ɹ�":"ɾ��ʧ��");  
		
		
		UserInfo user=userMapper.login("admin", "123");
		System.out.println(user==null?"��¼ʧ��":"��¼�ɹ�,��Ϣ����:"+user);
		*/
		
		session.commit();
		session.close();
	}


}
