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
		
		//用lambda表达式迭代 jk8支持
		userList.forEach( u->System.out.println(u));
		
	}
	
	static void Update() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		UserInfo user=session.selectOne("xxx.getUserById","1");
		user.setUserName("凌雨枫");
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
		
		List<UserInfo> userList= session.selectList("xxx.getUsersByName","刘思远");
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
		user.setNote("普通用户");
		
		session.insert("xxx.addUser", user);
		System.out.println("生成的主键是:"+user.getId());   //可以这样得到生成的主键
		session.commit();
		session.close();
	}
	static void Login() throws IOException {
		InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
		SqlSession session=factory.openSession();
		
		//以对象的方式传入参数
		UserInfo userParam=new UserInfo();
		userParam.setUserName("刘思远");
		userParam.setPassword("20010831");
		
		UserInfo user=session.selectOne("xxx.login",userParam);
		
		if(user!=null) {
			System.out.println("登录成功,用户信息如下:");
			System.out.println(user);
		}
		else {
			System.out.println("登录失败");
		}
						
	}
}