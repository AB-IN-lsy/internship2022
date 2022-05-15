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
		
		//�������,���ǵõ�mybatis���Ҵ����Ĵ������
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
		System.out.println("�û�����:"+count);
		
//		//��
//		UserInfo user=new UserInfo();
//		user.setUserName("admin");
//		user.setPassword("123");
//		user.setNote("�еȹ���Ա");
//		userMapper.addUser(user);
//		System.out.println("��ӳɹ�");
//		
//		
//		//��ѯһ��
//		UserInfo user= userMapper.getUserById(3);
//		System.out.println(user);
//		
//		//����
//		user.setUserName("���û�XXX");
//		user.setPassword("admin111");
//		int result=userMapper.updateUser(user);
//		System.out.println(result==1?"���³ɹ�":"����ʧ��");
//		
//		//��ѯ����
//		List<UserInfo> userList= userMapper.getAll();
//		for(UserInfo u:userList) {
//			System.out.println(u);
//		}
//		
//		//ɾ��
//		result=userMapper.deleteUser(3);
//		System.out.println(result==1?"ɾ���ɹ�":"ɾ��ʧ��");
		
		//��¼
		userMapper.login("admin", "123");
		System.out.println(user == null ? "��¼ʧ��" : "��½�ɹ�" + user);
		
		session.commit();
		session.close();
	}
}