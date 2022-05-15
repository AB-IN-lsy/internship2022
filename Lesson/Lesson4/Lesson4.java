====================================== Lesson4 ===========================================
1 ���ݿ�����˵��
2 mybatis ��Ϊdao��
3 ӳ���ļ������뷽ʽ
4 ����������
5 mybatis �������sql���
6 ��̬sql
7 sqlƬ��
8 ע��ķ�ʽ
==========================================================================================
==== 1 ���ݿ�����˵��
		��װmysql�����
		��װ�ͻ��� 
		
		�������������ͻ���: mysql -uroot -p  �س�,Ȼ����������
			
		mysql5 
		   ��5������ 
		   className: com.mysql.jdbc.Driver
		   url:  jdbc:mysql://localhost:3306/oa?useUnicode=true&characterEncoding=UTF8

		   
		mysql8 
		   ��8������
		    className: com.mysql.cj.jdbc.Driver
		    url:  jdbc:mysql://localhost:3306/oa?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true

	  Ҫע��,���������ֱ��д��xml�ļ���,Ҫ�� & ���Ž���ת�� ,Ҫ�� &amp; ȥ�滻,��������� ��Ҫ�ĳ�
	    jdbc:mysql://localhost:3306/oa?useUnicode=true&amp;characterEncoding=UTF8&amp;serverTimezone=UTC&amp;useSSL=false&allowPublicKeyRetrieval=true
	    
==== 2 mybatis ��Ϊdao��
    SqlSessionFactory Ӧ���ǵ����� (δ��Ҫ����	Spring ����)
    SqlSession ���̲߳���ȫ�� ,�õ�ʱ��Ҫʹ�þֲ�����
    
		mybatis��Ϊdao�㿪��,�����ַ�ʽ
			1) ԭʼ��ʽ 
			     ����dao��Ľӿ�,�� dao��ӿڶ�Ӧ��ʵ����(��Ҫ�����ֶ�ʵ��)
			
			2) mapper����ķ�ʽ
			     ֻ��Ҫ����dao��ӿ�, dao���Ӧ��ʵ����,��������ȥʵ��,mybatis������ʵ��
			     
			     
		
		ʹ��mapper����ķ�ʽ�����־ò� 
		  ����
		   1) ӳ���ļ��е�namespace ����,Ҫд�ɽӿڵ�ȫ·������
		   2) mapper�ӿ��������ķ�����,Ҫ��ӳ���ļ��е�sql��id��ͬ
		   3) �ӿ��з����ķ���ֵ,Ҫ��sql�е� resultType һ��
		   4) �ӿ��еķ����Ĳ�������,Ҫ��sql�е� parameterType һ��
		  
		    ֻҪ���������������,mybatis�ͻ��Զ������Ǵ�������ӿڵĴ������
		  
		  1) ����dao��ӿ� UserMapper
						package com.mapper;
						import java.util.List;
						import com.beans.UserInfo;
						
						public interface UserMapper {
							UserInfo getUserById(int id);
							
							List<UserInfo> getAll();
							
							int addUser(UserInfo user);
							
							int updateUser(UserInfo user);
							
							int deleteUser(int id);
						}
		 2) config/mappings/ ����һ����UserMapper.xml ��ӳ���ļ� ��������
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="com.mapper.UserMapper">
					<select id="getUserById"   resultType="UserInfo">
						select * from userInfo where id= #{id}    
					</select>
				
					<!-- ����û� ,һ��Ҫ�趨���ݿ����������������-->
					<insert id="addUser"  parameterType="userInfo" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
						insert into userInfo (userName,password,note) values (#{userName} , #{password},#{note}) 
					</insert>
					
					<!-- ɾ���û� -->
					<delete id="deleteUser" parameterType="int">
						delete from userInfo where id=#{id}
					</delete>
					
					<!-- �޸��û� -->
					<update id="updateUser" parameterType="UserInfo" >
						update userInfo set userName=#{userName},  password=#{password},note=#{note} where id=#{id}
					</update>
					
					<!-- ��ѯ�б� -->
					<select id="getAll" resultType="UserInfo"> 
						select * from userInfo
					</select>
				</mapper>
				
		 3) ��Ҫ�������������ļ�(mybatis-config)������
			    <mappers>
			    	<mapper resource="mappings/UserMapper.xml" />
			    </mappers>
			    
		 4) ����
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
							
						/*��
						UserInfo user=new UserInfo();
						user.setUserName("mybatis�û�");
						user.setPassword("abc");
						user.setNote("�еȹ���Ա");
						userMapper.addUser(user);
						System.out.println("��ӳɹ�"); */
						
						
						//��ѯһ��
						UserInfo user= userMapper.getUserById(23);
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
						result=userMapper.deleteUser(23);
						System.out.println(result==1?"ɾ���ɹ�":"ɾ��ʧ��");
						
						session.commit();
						session.close();
					}
				}

				    
==== 3 ӳ���ļ������뷽ʽ
		 ���������ļ�������ӳ���ļ�
		  1) �������·���ķ�ʽ 
		       <mappers>
			    	<mapper resource="mappings/UserMapper.xml" />
			    	<mapper resource="mappings/GoodMapper.xml" />
			    	<mapper resource="mappings/MemberMapper.xml" />
			    </mappers>
			    
			2) ȫ·����ʽ ��
			
			
			3) ʹ������,�����ķ�ʽ ,Ҫ�����ַ�ʽ,��Ҫ��ӳ���ļ������ƺͽӿ���Ҫһ��,��λ��ͬһ����
		      <mappers>
			    	<mapper  class="com.mapper.UserMapper"  />
			    </mappers>
			    
			4) ʹ�� mapper�ķ�ʽ�������� 
					<mappers>
			        <package name="com.mapper" />
			    </mappers>
			    
			   ���ַ�ʽ,ͬ��Ҫ��ӳ���ļ��ͽӿ�����ͬ,�ҷ��ڽӿ���ͬ�İ���,�����Զ�����������µ�����ӳ���ļ�
			    
			    
==== 4 ����������
		 �����������  parameterType ��ȡֵ,ֻ����һ��,������
		 
		  1)������ 
		  2)�������� 
		       pojo
		       hashMap
		       ��װ����pojo
		       
		  
		  ����Ҳ�����������ӿڵ�ʱ��,��ע��ķ�ʽ����������
		  
		   �ӿ��еķ�������:
		   UserInfo login(@Param("a") String userName, @Param("b") String password);
		  	
			 �����ļ�
			<select id="login" resultType="UserInfo">   
				select * from userInfo where userName=#{a}  and password=#{b}
			</select>
			
==== 5 mybatis �������sql���
     �� config Ŀ¼��,����һ�������ļ� 
       log4j.properties
       ��������:
				log4j.rootLogger=DEBUG, Console
				#Console
				log4j.appender.Console=org.apache.log4j.ConsoleAppender
				log4j.appender.Console.layout=org.apache.log4j.PatternLayout
				log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n
				
				log4j.logger.java.sql.ResultSet=INFO
				log4j.logger.org.apache=INFO
				log4j.logger.java.sql.Connection=DEBUG
				log4j.logger.java.sql.Statement=DEBUG
				log4j.logger.java.sql.PreparedStatement=DEBUG 
				
				
		 ��Ҫ������־��صİ�
		    log4j-1.2.17.jar
				log4j-api-2.0-rc1.jar
				log4j-core-2.0-rc1.jar
				slf4j-api-1.7.5.jar
				slf4j-log4j12-1.7.5.jar
				
		���������е�ʱ��,����������е�sql���,����:
		
			2022-04-24 10:15:20,052 [main] DEBUG [com.mapper.UserMapper.login] - ==>  Preparing: select * from userInfo where userName=? and password=?
			2022-04-24 10:15:20,113 [main] DEBUG [com.mapper.UserMapper.login] - ==> Parameters: admin(String), 123(String)
			2022-04-24 10:15:20,140 [main] DEBUG [com.mapper.UserMapper.login] - <==      Total: 1
			
==== 6 ��̬sql
   mybatis ��,ʵ�ֶ�̬sql��Ԫ����
     if
     choose(when  otherwise)
     trim
     where
     set
     foreach
     
     //�� ���������ϲ�ѯʾ��
				public static void main(String[] args) {
						seach(0,null,null);
						seach(10,null,null);
						seach(10,"admin",null);
						seach(10,"admin","�й�");
						seach(0, null,"�й�");	
					}
							
				static void seach(int age,String name,String country) {
					String sql="select * from userInfo where 1=1  "; 
					
					if(age!=0) {
						sql+=" and age="+age;
					}
					
					if(name!=null) {
						sql+= " and name= '"+name+"'";
					}
					
					if(country!=null) {
						sql+= " and country= '"+country+"'";
					}
					
					//ִ��
					System.out.println(sql);	
				}
				
		 //�� mybatis������̬��ѯ ( if ��ʹ�� )
					<!-- ��������ѯ -->
					<select id="selectList"   parameterType="userInfo" resultType="UserInfo" >
					    select * from userInfo where 1=1
					    
					    <if test="id !=0">
					    	and id=#{id}
					    </if>  
					    <if test="userName!=null and userName!='' ">   
					    	and userName=#{userName}
					    </if>
					    
				 		<if test="password!=null and password!='' ">   
					    	and password=#{password}
					    </if>  
					</select>
					
					�ڽӿ��н�������
					//���������ϲ�ѯ
					List<UserInfo> selectList(UserInfo user);
					
					����
					public static void main(String[] args) throws IOException {
							InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
							SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
							SqlSession session =factory.openSession();
							
							//�������,���ǵõ�mybatis���Ҵ����Ĵ������
							UserMapper userMapper =session.getMapper(UserMapper.class);
							
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
							
							....
							
					}
							
							
			 //��  choose(when  otherwise) ��ʹ��										
					<select id="dynamicSearch"  parameterType="userInfo" resultType="UserInfo">
					 	 select * from userInfo where 1=1
					 	 <choose>
					 	 	<when test="userName!=null">
					 	 		and userName=#{userName}
					 	 	</when>
					 	 	<when test="password!=null">
					 	 		and password=#{password}
					 	 	</when>
					 	 	<otherwise>
					 	 		and note =#{note}
					 	 	</otherwise>
					 	 </choose>
					</select>
					
			 //�� foreach
			    select * from userInfo where id in (1,3,5,7,9)
			    
			   1) �ӿ������һ������
			     	//����id�б���в�ѯ
					  List<UserInfo> getUserListByIds( List<Integer> idList); 
					  
				 2) �����ļ�
						<select id="getUserListByIds" parameterType="list" resultType="UserInfo">
							      select * from userInfo where id in     
							      <foreach collection="list" item="userId" open="(" close=")" separator=",">
							      	 #{userId}
							      </foreach>
							</select>
					  
				 3) �������е���
				      ...		
						List<Integer> idList=new ArrayList<>();
						idList.add(1);
						idList.add(9);
						idList.add(11);
						idList.add(12);
						idList.add(13);
						
						List<UserInfo> userList=userMapper.getUserListByIds(idList);
						userList.forEach(System.out::println);
						  ...
						  
						��־:
						2022-04-24 11:20:33,477 [main] DEBUG [com.mapper.UserMapper.getUserListByIds] - ==>  Preparing: select * from userInfo where id in ( ? , ? , ? , ? , ? )
						2022-04-24 11:20:33,512 [main] DEBUG [com.mapper.UserMapper.getUserListByIds] - ==> Parameters: 1(Integer), 9(Integer), 11(Integer), 12(Integer), 13(Integer)
						2022-04-24 11:20:33,554 [main] DEBUG [com.mapper.UserMapper.getUserListByIds] - <==      Total: 5
							
					
				  ʵ�ڲ���,���ǿ���������ķ��������ӵĲ�ѯ
					<select id="getXXXX" parameterType="string"  resultType="UserInfo">
						${value}
					</select>
					
					�ӿ�
					List<UserInfo> getXXXX(String sql);
		
					����			
					UserMapper userMapper =session.getMapper(UserMapper.class);
					List<UserInfo> userList=userMapper.getXXXX("select * from userInfo where id in(1,9,11) ");
					userList.forEach(System.out::println);
					
==== 7 sqlƬ
			<mapper namespace="com.mapper.UserMapper">
			  <!--����������һ��sqlƬ�� -->
				<sql id="cols">
					id,userName,password,note
				</sql>
				
				<select id="getUserById"   resultType="UserInfo">
					  select 
					  <include refid="cols" />
				    from userInfo where id= #{id}    
				</select>
				
				 <!--����������һ��sqlƬ�� -->
				<sql id="select_condation">
				    <if test="id !=0">
				    	and id=#{id}
				    </if>  
				    <if test="userName!=null and userName!='' ">   
				    	and userName=#{userName}
				    </if>
				    
			 		<if test="password!=null and password!='' ">   
				    	and password=#{password}
				    </if>  
				</sql>

				<!-- ��������ѯ ,�õ���sqlƬ�� -->
				<select id="selectList"   parameterType="userInfo" resultType="UserInfo" >
				    select * from userInfo  
				    <where>
				     	 <include refid="select_condation"  />
				    </where>
				</select>
				
				<!--��ѯ��������,�õ���sqlƬ�� -->
				<select id="getUserCount" parameterType="userInfo" resultType="int">
					select count(*) from userInfo
					<where>
				     	 <include refid="select_condation"  />
				    </where>
				</select>
				...
				
			</mapper>
			
			�ӿ���:
				//��������ѯ�û�����
				int getUserCount(UserInfo user);
				
		  ������:
		      ...
				UserMapper userMapper =session.getMapper(UserMapper.class);
				UserInfo user=new UserInfo();
				user.setUserName("aaa");
				user.setPassword("123");
				
				List<UserInfo> userList1= userMapper.selectList(user);
				userList1.forEach(System.out::println);
				
				int count=userMapper.getUserCount(user);
				System.out.println("�û�����:"+count); 
			    ...							
			    
==== 8 ע��ķ�ʽ						
		public interface UserMapper {
			
			@Select("select * from userInfo where id=#{id}")
			UserInfo getUserById(int id);
			
			@Select("select * from userInfo")
			List<UserInfo> getAll();
			
			@Insert("insert into userInfo (userName,password,note) values (#{userName} , #{password}, #{note} )")
			@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
			int addUser(UserInfo user);
			
			@Update("update userInfo set userName=#{userName} ,password= #{password}, note =#{note} where id =#{id}")
			int updateUser(UserInfo user);
			
			@Delete("delete from userInfo where id =#{id}")
			int deleteUser(int id);
			
			@Select("select * from userInfo where userName=#{userName} and password=#{password}")
			UserInfo login(@Param("userName") String userName, @Param("password") String password);
			
			//������Щ,û�в���ע�ⷽʽ,��Ȼ��xml�н�������
			
			//���������ϲ�ѯ
			List<UserInfo> selectList(UserInfo user);
			
			//����id�б���в�ѯ
			List<UserInfo> getUserListByIds( List<Integer> idList); 
			
			//����sql���в�ѯ
			List<UserInfo> getXXXX(String sql);
			
			//��������ѯ�û�����
			int getUserCount(UserInfo user);
		}

   
		�� Mapper ����ķ�ʽ,�������ı��Ӧ�� �־ò����,������ͨ��
		
		public class User {
		    private Integer userId;   //����,����id
		
		    private String userName;  //�˺�
		
		    private String userPass;  //����
		
		    private String userNickname;  //�ǳ�
		
		    private String userEmail;  //����
		
		    private String userUrl;  //�û���ַ
		
		    private String userAvatar;   //ͷ��ͼƬ��ַ
		
		    private String userLastLoginIp;  //����¼��ip��ַ
		
		    private Date userRegisterTime;  //ע��ʱ��
		
		    private Date userLastLoginTime; //����¼ʱ��
		
		    private Integer userStatus;  //�û�״̬
		}
		
		
		 �־ò��ṩ���·���:
		    login() 
		    
		    add()
		    
		    update()
		    
		    getUserList()
		    
		    delete ()
		    
		    count()  