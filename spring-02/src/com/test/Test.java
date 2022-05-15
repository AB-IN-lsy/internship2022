package com.test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.beans.UserInfo;
import com.dao.impl.UserDaoImpl;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
		UserDaoImpl dao=ioc.getBean("userDaoImpl",UserDaoImpl.class);
		
		UserInfo user=new UserInfo();
		user.setUserName("拜振华");
		user.setPassword("bzh");
		user.setNote("中国人民万岁");
		
		int result=dao.addUser(user);
		System.out.println(result==1?"添加成功":"添加失败");
		
		/*查询用户
		UserInfo user=dao.getUserById(5);
		System.out.println(user);
		
		user.setUserName("川建国");
		user.setPassword("123");
		user.setNote("洋装虽然穿在身,但中国依然在我心");
		int result=dao.updateUser(user);
		System.out.println(result==1?"更新":"更新失败"); */
		 
		//删除
//		int result1=dao.deleteUserById(6); 
//		System.out.println(result1==1?"删除成功":"删除失败");
		
	}

}