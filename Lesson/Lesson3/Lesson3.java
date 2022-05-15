=========================================Lesson4 ============================================
 1 mybatis简介
 2 入门程序
 3 ${} 和 #{} 的区别
 4 增,删,改,查
 5 关于注入的问题
 6 返回生成的自增主键
 7 关于别名的配置
 8 登录功能
=============================================================================================
====  1 mybatis简介
    mybatis,是 apache 的一个开源的项目 
    半ORM框架
		
		ORM : 对象模型到关系模型的映射
		
		对象模型 
		   java中的类和对象
		
		
		关系模型 
		   数据库中的表和数据 
		   
		 myatis框架中几个重要的角色
		 
		 1)mybatis-config.xml //名字不一定非叫这个名字
		   配置数据源,事务等运行环境,引入映射文件(可以有多个)
		 
		 2)SqlSessionFactory
		   会话工厂,它是根据配置文件创建的,用来创建SqlSession 
		   
		 3)SqlSession 
		   称为会话,是一个接口,可以用来操作数据库,进行增,删,改,查等,它是线程不安全的
		   在使用的时候,应该做成局部变量
		   要注意,它和 web 中 的 HttpSession 没有一点关系
		   其实它更象JDBC中的 Connection对象
		
==== 2 入门程序
   1) 导包
   		  mybatis-3.5.5.jar
   		  mysql的驱动包 mysql-connector-java-8.0.12.jar
   		  
   2) 配置数据库配置文件
      在工程中,和src平级,建一个叫 config 的源文件夹,以后的配置都放到这个目录中
      
        config/db.properties 文件
					db.driverName=com.mysql.cj.jdbc.Driver
					db.url=jdbc:mysql://localhost:3306/oa?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
					db.username=root
					db.password=root
		
	 3) 配置主配置文件
	      config/mybatis-config.xml 
					      
				<?xml version="1.0" encoding="UTF-8"?>  
				<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
				"http://mybatis.org/dtd/mybatis-3-config.dtd">  
				
				<configuration>
					<!-- 读入数据库相关的配置文件 -->
					<properties resource="db.properties"  />
					
					<!-- 数据源等环境相关的配置 -->
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
				    
				    <!-- 要引入其他的映射文件 -->  			    
				</configuration>
				
	 4) 编写映射文件(会有多个) 
	 		  在  config/ 下建一个目录,叫 mappings ,专门用来放mybatis的映射文件
	 		  
	 		  在 config/mappings 下,建一个文件叫 UserInfo.xml
	 		  
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="xxx">
					<select id="getUserById" parameterType="int"  resultType="com.beans.UserInfo">
						select * from userInfo where id= #{id} 
					</select>
				</mapper>
						 		  
				说明:namespace　是用来不同的namespacke 中的 sql语句进行隔离的
				parameterType :传给sql语句的参数的类型 
				resultType: 执行完查询以后,返回的结果是什么类型, 只有查询类操作才有这个 resultType
				
	 5) 在主配置文件中 mybatis-config.xml ,引入映射文件
		    <!-- 要引入其他的映射文件 -->
		    <mappers>
		    	<mapper resource="mappings/UserInfo.xml" />
		    </mappers>
		    
	6) 测试 
			package com.test;
			import java.io.*;
			import org.apache.ibatis.io.Resources;
			import org.apache.ibatis.session.*;
			import com.beans.UserInfo;
			
			public class Test {
			
				public static void main(String[] args) throws IOException {
					//加载主配置文件,构造成一个输入流
					InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
					
					//创建SqlSessionFactory 
					SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
							
					//得到SqlSession 
					SqlSession session=factory.openSession();
					
					UserInfo user= session.selectOne("xxx.getUserById",1);
					
					System.out.println(user);
					
					session.close();
				}
			}
			
	  说明:
	   1) 上例中  parameterType="int" 可以省略不写,实际上 parameterType在很多情况下都是可以省去的
	   2)  select * from userInfo where id= #{id}  , 后面的#{id} 里的 id可以写成任意值,前提是参数必须是简单类型
	   3) 如果指定了   parameterType="int" , 我们在传参的时候,就不能传字符串

====  3 ${} 和 #{} 的区别
   附: like 语句: select * from userInfo where userName like '%admin%';
   
   #{} 这样的传参方式,mybatis会把它当成jdbc中的 PreparedStatement 中的 ?  这样的占位符
       如果参数是简单类型,则{} 中的名称任意
       
   ${value}  这样的方式, mybatis 会直接把它当成变量往sql中拼接
             如果用这样的方式,则{}中的值,必须写成 value  
             要注意,这样的方式如果处理不当,可能会存在SQL注入的问题
             
   
   //例 模乎查询,本例用   ${value} 的方式传参
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
		
====  4 增,删,改,查
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
			
			<!-- 添加用户 ,一定要设定数据库的主键是自增主键-->
			<insert id="addUser"  parameterType="com.beans.UserInfo">
				insert into userInfo (userName,password,note) values (#{userName} , #{password},#{note}) 
			</insert>
			
			<!-- 删除用户 -->
			<delete id="deleteUserById" >
				delete from userInfo where id=#{id}
			</delete>
			
			<!-- 修改用户 -->
			<update id="updateUser" parameterType="com.beans.UserInfo" >
				update userInfo set userName=#{userName},  password=#{password},note=#{note} where id=#{id}
			</update>
			
			<!-- 查询列表 -->
			<select id="getAll" resultType="com.beans.UserInfo"> 
				select * from userInfo
			</select>
		</mapper>
		
		测试
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
				//加载主配置文件,构造成一个输入流
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				
				//创建SqlSessionFactory 
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
						
				//得到SqlSession 
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
				
				//用lambda表达式迭代 jk8支持
				userList.forEach( u->System.out.println(u));
				
			}
			
			static void testUpdate() throws IOException {
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
				SqlSession session=factory.openSession();
				
				UserInfo user=session.selectOne("xxx.getUserById","1");
				user.setUserName("我是天下第一");
				user.setPassword("wstxdy");
				user.setNote("这是一个爱吹牛的用户");
				
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
				//加载主配置文件,构造成一个输入流
				InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
				
				//创建SqlSessionFactory 
				SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
						
				//得到SqlSession 
				SqlSession session=factory.openSession();
				
				UserInfo user=new UserInfo();
				user.setUserName("aaa");
				user.setPassword("aaa");
				user.setNote("普通用户");
				
				session.insert("xxx.addUser", user);
				
				session.commit();
				session.close();
			}
		}

====  5 关于注入的问题
  	<select id="getUsersByName" parameterType="string"  resultType="com.beans.UserInfo">
			select * from userInfo where userName like '%${value}%'
		</select>
		
		static void testGetUserByName() throws IOException {
					InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
					SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
					SqlSession session=factory.openSession();
					
					Scanner scan=new Scanner(System.in);
					System.out.println("请输入要查询的账号");
					String userName= scan.nextLine();
					
					List<UserInfo> userList= session.selectList("xxx.getUsersByName",userName);
					for(UserInfo u: userList) {
						System.out.println(u);
					}
			
					session.close();
					
					//如果用户输入的是类似 1' or '1'='1'  or '1= 这样的串,将产生SQL注入的问题
		}

		
		//因为用的是  ${} 方式,会直接拼接最后拼成的字符串是:
		select * from userInfo where userName like '% 1' or '1'='1'  or '1=%'
		
		
		如何防止注入问题
			Msql可以这样写
			select * from userInfo where userName like concat('%',  #{userName} , '%')
			
			MSSqlserver 可以这样写 
			select * from userInfo where userName like '%' + #{userName} + '%'
			
			Oracle 可以这样写
			select * from userInfo where userName like '%' || #{userName} || '%'
			
			
====  6 返回生成的自增主键

	<insert id="addUser"  parameterType="com.beans.UserInfo" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
		insert into userInfo (userName,password,note) values (#{userName} , #{password},#{note}) 
	</insert>
	
	useGeneratedKeys 告诉mybatis把生成的自增主键返回
	keyProperty 表示返回的主键放在 UserInfo 这个列中
  keyColumn	告诉mybatis 数据库中哪列是主键
  
static void testAdd() throws IOException {
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
	
====  7 关于别名的配置
    在 mybatis-config.xml 中
    <!-- 批量别名定义,只要是com.beans包下的类,都可以使用默认别名,类名首字母大小写均可 -->
		<typeAliases>
			<package name="com.beans"/>
		</typeAliases>
		
		配置了别名以后,在映射文件中,就可以使用了
		<select id="getUserById"   resultType="UserInfo">  //这里用了别名
			select * from userInfo where id= #{id}    
		</select>
		
		<select id="getUsersByName" parameterType="string"  resultType="userInfo">  //这里用了别名
			select * from userInfo where userName like concat('%',  #{userName} , '%')
		</select>
		....
		
		
====  8 登录功能
			<select id="login" parameterType="userInfo"   resultType="UserInfo">
				select * from userInfo where userName=#{userName} and password=#{password}
			</select>
			
			static void testLogin() throws IOException {
					InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
					SqlSessionFactory factory=new  SqlSessionFactoryBuilder().build(in);
					SqlSession session=factory.openSession();
					
					//以对象的方式传入参数
					UserInfo userParam=new UserInfo();
					userParam.setUserName("admi");
					userParam.setPassword("123");
					
					UserInfo user=session.selectOne("xxx.login",userParam);
					
					if(user!=null) {
						System.out.println("登录成功,用户信息如下:");
						System.out.println(user);
					}
					else {
						System.out.println("登录失败");
					}
									
				}