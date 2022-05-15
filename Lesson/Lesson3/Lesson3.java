=========================================Lesson4 ============================================
 1 mybatis���
 2 ���ų���
 3 ${} �� #{} ������
 4 ��,ɾ,��,��
 5 ����ע�������
 6 �������ɵ���������
 7 ���ڱ���������
 8 ��¼����
=============================================================================================
====  1 mybatis���
    mybatis,�� apache ��һ����Դ����Ŀ 
    ��ORM���
		
		ORM : ����ģ�͵���ϵģ�͵�ӳ��
		
		����ģ�� 
		   java�е���Ͷ���
		
		
		��ϵģ�� 
		   ���ݿ��еı������ 
		   
		 myatis����м�����Ҫ�Ľ�ɫ
		 
		 1)mybatis-config.xml //���ֲ�һ���ǽ��������
		   ��������Դ,��������л���,����ӳ���ļ�(�����ж��)
		 
		 2)SqlSessionFactory
		   �Ự����,���Ǹ��������ļ�������,��������SqlSession 
		   
		 3)SqlSession 
		   ��Ϊ�Ự,��һ���ӿ�,���������������ݿ�,������,ɾ,��,���,�����̲߳���ȫ��
		   ��ʹ�õ�ʱ��,Ӧ�����ɾֲ�����
		   Ҫע��,���� web �� �� HttpSession û��һ���ϵ
		   ��ʵ������JDBC�е� Connection����
		
==== 2 ���ų���
   1) ����
   		  mybatis-3.5.5.jar
   		  mysql�������� mysql-connector-java-8.0.12.jar
   		  
   2) �������ݿ������ļ�
      �ڹ�����,��srcƽ��,��һ���� config ��Դ�ļ���,�Ժ�����ö��ŵ����Ŀ¼��
      
        config/db.properties �ļ�
					db.driverName=com.mysql.cj.jdbc.Driver
					db.url=jdbc:mysql://localhost:3306/oa?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
					db.username=root
					db.password=root
		
	 3) �����������ļ�
	      config/mybatis-config.xml 
					      
				<?xml version="1.0" encoding="UTF-8"?>  
				<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
				"http://mybatis.org/dtd/mybatis-3-config.dtd">  
				
				<configuration>
					<!-- �������ݿ���ص������ļ� -->
					<properties resource="db.properties"  />
					
					<!-- ����Դ�Ȼ�����ص����� -->
				    <environments default="development">    
				       <environment id="development">  
				           <transactionManager type="JDBC"/>  
				           <dataSource type="POOLED">    
				               <property name="driver" value="${db.driverName}"/>  
				               <property name="url" value="${db.url}"/>  
				               <property name="username" value="${db.username}"/>  
				               <property name="password" value="${db.password}"/>  
				           </dataSource>  
				       </environment>  
				    </environments>  
				    
				    <!-- Ҫ����������ӳ���ļ� -->  			    
				</configuration>
				
	 4) ��дӳ���ļ�(���ж��) 
	 		  ��  config/ �½�һ��Ŀ¼,�� mappings ,ר��������mybatis��ӳ���ļ�
	 		  
	 		  �� config/mappings ��,��һ���ļ��� UserInfo.xml
	 		  
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="xxx">
					<select id="getUserById" parameterType="int"  resultType="com.beans.UserInfo">
						select * from userInfo where id= #{id} 
					</select>
				</mapper>
						 		  
				˵��:namespace����������ͬ��namespacke �е� sql�����и����
				parameterType :����sql���Ĳ��������� 
				resultType: ִ�����ѯ�Ժ�,���صĽ����ʲô����, ֻ�в�ѯ������������ resultType
				
	 5) ���������ļ��� mybatis-config.xml ,����ӳ���ļ�
		    <!-- Ҫ����������ӳ���ļ� -->
		    <mappers>
		    	<mapper resource="mappings/UserInfo.xml" />
		    </mappers>
		    
	6) ���� 
			package com.test;
			import java.io.*;
			import org.apache.ibatis.io.Resources;
			import org.apache.ibatis.session.*;
			import com.beans.UserInfo;
			
			public class Test {
			
				public static void main(String[] args) throws IOException {
					//�����������ļ�,�����һ��������
					InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
					
					//����SqlSessionFactory 
					SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
							
					//�õ�SqlSession 
					SqlSession session=factory.openSession();
					
					UserInfo user= session.selectOne("xxx.getUserById",1);
					
					System.out.println(user);
					
					session.close();
				}
			}
			
	  ˵��:
	   1) ������  parameterType="int" ����ʡ�Բ�д,ʵ���� parameterType�ںܶ�����¶��ǿ���ʡȥ��
	   2)  select * from userInfo where id= #{id}  , �����#{id} ��� id����д������ֵ,ǰ���ǲ��������Ǽ�����
	   3) ���ָ����   parameterType="int" , �����ڴ��ε�ʱ��,�Ͳ��ܴ��ַ���

====  3 ${} �� #{} ������
   ��: like ���: select * from userInfo where userName like '%admin%';
   
   #{} �����Ĵ��η�ʽ,mybatis���������jdbc�е� PreparedStatement �е� ?  ������ռλ��
       ��������Ǽ�����,��{} �е���������
       
   ${value}  �����ķ�ʽ, mybatis ��ֱ�Ӱ������ɱ�����sql��ƴ��
             ����������ķ�ʽ,��{}�е�ֵ,����д�� value  
             Ҫע��,�����ķ�ʽ���������,���ܻ����SQLע�������
             
   
   //�� ģ����ѯ,������   ${value} �ķ�ʽ����
		<select id="getUsersByName" parameterType="string"  resultType="com.beans.UserInfo">
			select * from userInfo where userName like '%${value}%'
		</select>
		static void testGetUserByName() throws IOException {
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
				SqlSession session=factory.openSession();
				
				List<UserInfo> userList= session.selectList("xxx.getUsersByName","admin");
				for(UserInfo u: userList) {
					System.out.println(u);
				}
				session.close();
		}
		
====  4 ��,ɾ,��,��
		<?xml version="1.0" encoding="UTF-8" ?>  
		<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
		
		<mapper namespace="xxx">
			<select id="getUserById"   resultType="com.beans.UserInfo">
				select * from userInfo where id= #{id}    
			</select>
			
			<select id="getUsersByName" parameterType="string"  resultType="com.beans.UserInfo">
				select * from userInfo where userName like '%${value}%'
			</select>
			
			<!-- ����û� ,һ��Ҫ�趨���ݿ����������������-->
			<insert id="addUser"  parameterType="com.beans.UserInfo">
				insert into userInfo (userName,password,note) values (#{userName} , #{password},#{note}) 
			</insert>
			
			<!-- ɾ���û� -->
			<delete id="deleteUserById" >
				delete from userInfo where id=#{id}
			</delete>
			
			<!-- �޸��û� -->
			<update id="updateUser" parameterType="com.beans.UserInfo" >
				update userInfo set userName=#{userName},  password=#{password},note=#{note} where id=#{id}
			</update>
			
			<!-- ��ѯ�б� -->
			<select id="getAll" resultType="com.beans.UserInfo"> 
				select * from userInfo
			</select>
		</mapper>
		
		����
		package com.test;
		import java.io.IOException;
		import java.io.InputStream;
		import java.util.List;
		import org.apache.ibatis.io.Resources;
		import org.apache.ibatis.session.*;
		import com.beans.UserInfo;
		
		public class Test {
		
			public static void main(String[] args) throws IOException {
				testGetAll();
				System.out.println("---");
			}
			
			static void get() throws IOException {
				//�����������ļ�,�����һ��������
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				
				//����SqlSessionFactory 
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
						
				//�õ�SqlSession 
				SqlSession session=factory.openSession();
				
				UserInfo user= session.selectOne("xxx.getUserById","1");
				
				System.out.println(user);
				
				session.close();
			}
			
			static void testGetAll()  throws IOException  {
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
				SqlSession session=factory.openSession();
				
				List<UserInfo > userList= session.selectList("xxx.getAll");
				
				//��lambda���ʽ���� jk8֧��
				userList.forEach( u->System.out.println(u));
				
			}
			
			static void testUpdate() throws IOException {
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
				SqlSession session=factory.openSession();
				
				UserInfo user=session.selectOne("xxx.getUserById","1");
				user.setUserName("�������µ�һ");
				user.setPassword("wstxdy");
				user.setNote("����һ������ţ���û�");
				
				session.update("xxx.updateUser",user);
				session.commit();
				session.close();
			}
			
			static void testDel() throws IOException {
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
				SqlSession session=factory.openSession();
				
				session.delete("xxx.deleteUserById",16);
				session.commit();
				
				session.close();
			}
			
			static void testGetUserByName() throws IOException {
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
				SqlSession session=factory.openSession();
				
				List<UserInfo> userList= session.selectList("xxx.getUsersByName","admin");
				for(UserInfo u: userList) {
					System.out.println(u);
				}
		
				session.close();
			}
			
			static void testAdd() throws IOException {
				//�����������ļ�,�����һ��������
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				
				//����SqlSessionFactory 
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
						
				//�õ�SqlSession 
				SqlSession session=factory.openSession();
				
				UserInfo user=new UserInfo();
				user.setUserName("aaa");
				user.setPassword("aaa");
				user.setNote("��ͨ�û�");
				
				session.insert("xxx.addUser", user);
				
				session.commit();
				session.close();
			}
		}

====  5 ����ע�������
  	<select id="getUsersByName" parameterType="string"  resultType="com.beans.UserInfo">
			select * from userInfo where userName like '%${value}%'
		</select>
		
		static void testGetUserByName() throws IOException {
					InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
					SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
					SqlSession session=factory.openSession();
					
					Scanner scan=new Scanner(System.in);
					System.out.println("������Ҫ��ѯ���˺�");
					String userName= scan.nextLine();
					
					List<UserInfo> userList= session.selectList("xxx.getUsersByName",userName);
					for(UserInfo u: userList) {
						System.out.println(u);
					}
			
					session.close();
					
					//����û������������ 1' or '1'='1'  or '1= �����Ĵ�,������SQLע�������
		}

		
		//��Ϊ�õ���  ${} ��ʽ,��ֱ��ƴ�����ƴ�ɵ��ַ�����:
		select * from userInfo where userName like '% 1' or '1'='1'  or '1=%'
		
		
		��η�ֹע������
			Msql��������д
			select * from userInfo where userName like concat('%',  #{userName} , '%')
			
			MSSqlserver ��������д 
			select * from userInfo where userName like '%' + #{userName} + '%'
			
			Oracle ��������д
			select * from userInfo where userName like '%' || #{userName} || '%'
			
			
====  6 �������ɵ���������

	<insert id="addUser"  parameterType="com.beans.UserInfo" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
		insert into userInfo (userName,password,note) values (#{userName} , #{password},#{note}) 
	</insert>
	
	useGeneratedKeys ����mybatis�����ɵ�������������
	keyProperty ��ʾ���ص��������� UserInfo �������
  keyColumn	����mybatis ���ݿ�������������
  
static void testAdd() throws IOException {
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
	
====  7 ���ڱ���������
    �� mybatis-config.xml ��
    <!-- ������������,ֻҪ��com.beans���µ���,������ʹ��Ĭ�ϱ���,��������ĸ��Сд���� -->
		<typeAliases>
			<package name="com.beans"/>
		</typeAliases>
		
		�����˱����Ժ�,��ӳ���ļ���,�Ϳ���ʹ����
		<select id="getUserById"   resultType="UserInfo">  //�������˱���
			select * from userInfo where id= #{id}    
		</select>
		
		<select id="getUsersByName" parameterType="string"  resultType="userInfo">  //�������˱���
			select * from userInfo where userName like concat('%',  #{userName} , '%')
		</select>
		....
		
		
====  8 ��¼����
			<select id="login" parameterType="userInfo"   resultType="UserInfo">
				select * from userInfo where userName=#{userName} and password=#{password}
			</select>
			
			static void testLogin() throws IOException {
					InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
					SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
					SqlSession session=factory.openSession();
					
					//�Զ���ķ�ʽ�������
					UserInfo userParam=new UserInfo();
					userParam.setUserName("admi");
					userParam.setPassword("123");
					
					UserInfo user=session.selectOne("xxx.login",userParam);
					
					if(user!=null) {
						System.out.println("��¼�ɹ�,�û���Ϣ����:");
						System.out.println(user);
					}
					else {
						System.out.println("��¼ʧ��");
					}
									
				}