package com.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import com.beans.UserInfo;

public class Test {

	public static void main(String[] args) throws IOException {
		GetUserById();
		System.out.println("---");
		Login();
		System.out.println("---");
		GetAll();
		System.out.println("---");
		Update();
		System.out.println("---");
		Del();
		System.out.println("---");
		GetUserByName();
		System.out.println("---");
		Add();
		
	}
	
	static void GetUserById() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		UserInfo user= session.selectOne("xxx.getUserById","1");
		
		System.out.println(user);
		
		session.close();
	}
	
	static void GetAll()  throws IOException  {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		List<UserInfo > userList= session.selectList("xxx.getAll");
		
		//��lambda���ʽ���� jk8֧��
		userList.forEach( u->System.out.println(u));
		
	}
	
	static void Update() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		UserInfo user=session.selectOne("xxx.getUserById","1");
		user.setUserName("�����");
		user.setPassword("yyds");
		user.setNote("yyds");
		
		session.update("xxx.updateUser",user);
		session.commit();
		session.close();
	}
	
	static void Del() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		session.delete("xxx.deleteUserById",2);
		session.commit();
		
		session.close();
	}
	
	static void GetUserByName() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		List<UserInfo> userList= session.selectList("xxx.getUsersByName","��˼Զ");
		for(UserInfo u: userList) {
			System.out.println(u);
		}

		session.close();
	}
	
	static void Add() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		UserInfo user=new UserInfo();
		user.setUserName("aaa");
		user.setPassword("aaa");
		user.setNote("��ͨ�û�");
		
		session.insert("xxx.addUser", user);
		System.out.println("���ɵ�������:"+user.getId());   //���������õ����ɵ�����
		session.commit();
		session.close();
	}
	static void Login() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		//�Զ���ķ�ʽ�������
		UserInfo userParam=new UserInfo();
		userParam.setUserName("��˼Զ");
		userParam.setPassword("20010831");
		
		UserInfo user=session.selectOne("xxx.login",userParam);
		
		if(user!=null) {
			System.out.println("��¼�ɹ�,�û���Ϣ����:");
			System.out.println(user);
		}
		else {
			System.out.println("��¼ʧ��");
		}
						
	}
}