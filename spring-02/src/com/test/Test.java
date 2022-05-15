package com.test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.beans.UserInfo;
import com.dao.impl.UserDaoImpl;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("beans.xml");
		UserDaoImpl dao=ioc.getBean("userDaoImpl",UserDaoImpl.class);
		
		UserInfo user=new UserInfo();
		user.setUserName("����");
		user.setPassword("bzh");
		user.setNote("�й���������");
		
		int result=dao.addUser(user);
		System.out.println(result==1?"��ӳɹ�":"���ʧ��");
		
		/*��ѯ�û�
		UserInfo user=dao.getUserById(5);
		System.out.println(user);
		
		user.setUserName("������");
		user.setPassword("123");
		user.setNote("��װ��Ȼ������,���й���Ȼ������");
		int result=dao.updateUser(user);
		System.out.println(result==1?"����":"����ʧ��"); */
		 
		//ɾ��
//		int result1=dao.deleteUserById(6); 
//		System.out.println(result1==1?"ɾ���ɹ�":"ɾ��ʧ��");
		
	}

}