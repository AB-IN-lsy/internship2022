====================================== Lesson4 ===========================================
1 数据库连接说明
2 mybatis 做为dao层
3 映射文件的引入方式
4 传入多个参数
5 mybatis 配置输出sql语句
6 动态sql
7 sql片段
8 注解的方式
==========================================================================================
==== 1 数据库连接说明
		安装mysql服务端
		安装客户端 
		
		从命令行启动客户端: mysql -uroot -p  回车,然后输入密码
			
		mysql5 
		   导5的驱动 
		   className: com.mysql.jdbc.Driver
		   url:  jdbc:mysql://localhost:3306/oa?useUnicode=true&characterEncoding=UTF8

		   
		mysql8 
		   导8的驱动
		    className: com.mysql.cj.jdbc.Driver
		    url:  jdbc:mysql://localhost:3306/oa?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true

	  要注意,如果把配置直接写在xml文件中,要把 & 符号进行转义 ,要用 &amp; 去替换,比如上面的 就要改成
	    jdbc:mysql://localhost:3306/oa?useUnicode=true&amp;characterEncoding=UTF8&amp;serverTimezone=UTC&amp;useSSL=false&allowPublicKeyRetrieval=true
	    
==== 2 mybatis 做为dao层
    SqlSessionFactory 应该是单例的 (未来要交给	Spring 管理)
    SqlSession 是线程不安全的 ,用的时候要使用局部变量
    
		mybatis做为dao层开发,有两种方式
			1) 原始方式 
			     创建dao层的接口,和 dao层接口对应的实现类(需要我们手动实现)
			
			2) mapper代理的方式
			     只需要创建dao层接口, dao层对应的实现类,不用我们去实现,mybatis帮我们实现
			     
			     
		
		使用mapper代理的方式开发持久层 
		  规则
		   1) 映射文件中的namespace 属性,要写成接口的全路径名称
		   2) mapper接口中声明的方法名,要和映射文件中的sql的id相同
		   3) 接口中方法的返回值,要和sql中的 resultType 一致
		   4) 接口中的方法的参数类型,要和sql中的 parameterType 一致
		  
		    只要上面的条件满足了,mybatis就会自动帮我们创建这个接口的代理对象
		  
		  1) 创建dao层接口 UserMapper
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
		 2) config/mappings/ 创建一个收UserMapper.xml 的映射文件 内容如下
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="com.mapper.UserMapper">
					<select id="getUserById"   resultType="UserInfo">
						select * from userInfo where id= #{id}    
					</select>
				
					<!-- 添加用户 ,一定要设定数据库的主键是自增主键-->
					<insert id="addUser"  parameterType="userInfo" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
						insert into userInfo (userName,password,note) values (#{userName} , #{password},#{note}) 
					</insert>
					
					<!-- 删除用户 -->
					<delete id="deleteUser" parameterType="int">
						delete from userInfo where id=#{id}
					</delete>
					
					<!-- 修改用户 -->
					<update id="updateUser" parameterType="UserInfo" >
						update userInfo set userName=#{userName},  password=#{password},note=#{note} where id=#{id}
					</update>
					
					<!-- 查询列表 -->
					<select id="getAll" resultType="UserInfo"> 
						select * from userInfo
					</select>
				</mapper>
				
		 3) 不要忘了在主配置文件(mybatis-config)中引入
			    <mappers>
			    	<mapper resource="mappings/UserMapper.xml" />
			    </mappers>
			    
		 4) 测试
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
						
						//这个方法,就是得到mybatis帮我创建的代理对象
						UserMapper userMapper =session.getMapper(UserMapper.class);
							
						/*增
						UserInfo user=new UserInfo();
						user.setUserName("mybatis用户");
						user.setPassword("abc");
						user.setNote("中等管理员");
						userMapper.addUser(user);
						System.out.println("添加成功"); */
						
						
						//查询一个
						UserInfo user= userMapper.getUserById(23);
						System.out.println(user);
						
						//更新
						user.setUserName("新用户XXX");
						user.setPassword("admin111");
						int result=userMapper.updateUser(user);
						System.out.println(result==1?"更新成功":"更新失败");
						
						//查询所有
						List<UserInfo> userList= userMapper.getAll();
						for(UserInfo u:userList) {
							System.out.println(u);
						}
						
						//删除
						result=userMapper.deleteUser(23);
						System.out.println(result==1?"删除成功":"删除失败");
						
						session.commit();
						session.close();
					}
				}

				    
==== 3 映射文件的引入方式
		 在主配置文件中引入映射文件
		  1) 相对于类路径的方式 
		       <mappers>
			    	<mapper resource="mappings/UserMapper.xml" />
			    	<mapper resource="mappings/GoodMapper.xml" />
			    	<mapper resource="mappings/MemberMapper.xml" />
			    </mappers>
			    
			2) 全路径方式 略
			
			
			3) 使用类名,包名的方式 ,要用这种方式,则要求映射文件的名称和接口名要一致,且位于同一包下
		      <mappers>
			    	<mapper  class="com.mapper.UserMapper"  />
			    </mappers>
			    
			4) 使用 mapper的方式批量加载 
					<mappers>
			        <package name="com.mapper" />
			    </mappers>
			    
			   这种方式,同样要求映射文件和接口名相同,且放在接口相同的包下,它会自动加载这个包下的所有映射文件
			    
			    
==== 4 传入多个参数
		 输入参数类型  parameterType 的取值,只能是一个,可以是
		 
		  1)简单类型 
		  2)复合类型 
		       pojo
		       hashMap
		       包装过的pojo
		       
		  
		  我们也可以在声明接口的时候,用注解的方式传入多个参数
		  
		   接口中的方法声明:
		   UserInfo login(@Param("a") String userName, @Param("b") String password);
		  	
			 配置文件
			<select id="login" resultType="UserInfo">   
				select * from userInfo where userName=#{a}  and password=#{b}
			</select>
			
==== 5 mybatis 配置输出sql语句
     在 config 目录下,配置一个配置文件 
       log4j.properties
       内容如下:
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
				
				
		 还要导入日志相关的包
		    log4j-1.2.17.jar
				log4j-api-2.0-rc1.jar
				log4j-core-2.0-rc1.jar
				slf4j-api-1.7.5.jar
				slf4j-log4j12-1.7.5.jar
				
		这样在运行的时候,它会输出运行的sql语句,如下:
		
			2022-04-24 10:15:20,052 [main] DEBUG [com.mapper.UserMapper.login] - ==>  Preparing: select * from userInfo where userName=? and password=?
			2022-04-24 10:15:20,113 [main] DEBUG [com.mapper.UserMapper.login] - ==> Parameters: admin(String), 123(String)
			2022-04-24 10:15:20,140 [main] DEBUG [com.mapper.UserMapper.login] - <==      Total: 1
			
==== 6 动态sql
   mybatis 中,实现动态sql的元素有
     if
     choose(when  otherwise)
     trim
     where
     set
     foreach
     
     //附 多条件复合查询示例
				public static void main(String[] args) {
						seach(0,null,null);
						seach(10,null,null);
						seach(10,"admin",null);
						seach(10,"admin","中国");
						seach(0, null,"中国");	
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
					
					//执行
					System.out.println(sql);	
				}
				
		 //例 mybatis中做动态查询 ( if 的使用 )
					<!-- 多条件查询 -->
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
					
					在接口中进行声明
					//多条件复合查询
					List<UserInfo> selectList(UserInfo user);
					
					测试
					public static void main(String[] args) throws IOException {
							InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
							SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
							SqlSession session =factory.openSession();
							
							//这个方法,就是得到mybatis帮我创建的代理对象
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
							
							
			 //例  choose(when  otherwise) 的使用										
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
					
			 //例 foreach
			    select * from userInfo where id in (1,3,5,7,9)
			    
			   1) 接口中添加一个方法
			     	//根据id列表进行查询
					  List<UserInfo> getUserListByIds( List<Integer> idList); 
					  
				 2) 配置文件
						<select id="getUserListByIds" parameterType="list" resultType="UserInfo">
							      select * from userInfo where id in     
							      <foreach collection="list" item="userId" open="(" close=")" separator=",">
							      	 #{userId}
							      </foreach>
							</select>
					  
				 3) 测试类中调用
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
						  
						日志:
						2022-04-24 11:20:33,477 [main] DEBUG [com.mapper.UserMapper.getUserListByIds] - ==>  Preparing: select * from userInfo where id in ( ? , ? , ? , ? , ? )
						2022-04-24 11:20:33,512 [main] DEBUG [com.mapper.UserMapper.getUserListByIds] - ==> Parameters: 1(Integer), 9(Integer), 11(Integer), 12(Integer), 13(Integer)
						2022-04-24 11:20:33,554 [main] DEBUG [com.mapper.UserMapper.getUserListByIds] - <==      Total: 5
							
					
				  实在不行,我们可以用下面的方法处理复杂的查询
					<select id="getXXXX" parameterType="string"  resultType="UserInfo">
						${value}
					</select>
					
					接口
					List<UserInfo> getXXXX(String sql);
		
					测试			
					UserMapper userMapper =session.getMapper(UserMapper.class);
					List<UserInfo> userList=userMapper.getXXXX("select * from userInfo where id in(1,9,11) ");
					userList.forEach(System.out::println);
					
==== 7 sql片
			<mapper namespace="com.mapper.UserMapper">
			  <!--这里声明了一个sql片段 -->
				<sql id="cols">
					id,userName,password,note
				</sql>
				
				<select id="getUserById"   resultType="UserInfo">
					  select 
					  <include refid="cols" />
				    from userInfo where id= #{id}    
				</select>
				
				 <!--这里声明了一个sql片段 -->
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

				<!-- 多条件查询 ,用到了sql片段 -->
				<select id="selectList"   parameterType="userInfo" resultType="UserInfo" >
				    select * from userInfo  
				    <where>
				     	 <include refid="select_condation"  />
				    </where>
				</select>
				
				<!--查询数据条数,用到了sql片段 -->
				<select id="getUserCount" parameterType="userInfo" resultType="int">
					select count(*) from userInfo
					<where>
				     	 <include refid="select_condation"  />
				    </where>
				</select>
				...
				
			</mapper>
			
			接口中:
				//多条件查询用户总数
				int getUserCount(UserInfo user);
				
		  测试类:
		      ...
				UserMapper userMapper =session.getMapper(UserMapper.class);
				UserInfo user=new UserInfo();
				user.setUserName("aaa");
				user.setPassword("123");
				
				List<UserInfo> userList1= userMapper.selectList(user);
				userList1.forEach(System.out::println);
				
				int count=userMapper.getUserCount(user);
				System.out.println("用户总数:"+count); 
			    ...							
			    
==== 8 注解的方式						
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
			
			//以下这些,没有采用注解方式,依然在xml中进行配置
			
			//多条件复合查询
			List<UserInfo> selectList(UserInfo user);
			
			//根据id列表进行查询
			List<UserInfo> getUserListByIds( List<Integer> idList); 
			
			//传入sql进行查询
			List<UserInfo> getXXXX(String sql);
			
			//多条件查询用户总数
			int getUserCount(UserInfo user);
		}

   
		以 Mapper 代理的方式,完成下面的表对应的 持久层代码,并测试通过
		
		public class User {
		    private Integer userId;   //主键,自增id
		
		    private String userName;  //账号
		
		    private String userPass;  //密码
		
		    private String userNickname;  //昵称
		
		    private String userEmail;  //邮箱
		
		    private String userUrl;  //用户网址
		
		    private String userAvatar;   //头象图片地址
		
		    private String userLastLoginIp;  //最后登录的ip地址
		
		    private Date userRegisterTime;  //注册时间
		
		    private Date userLastLoginTime; //最后登录时间
		
		    private Integer userStatus;  //用户状态
		}
		
		
		 持久层提供以下方法:
		    login() 
		    
		    add()
		    
		    update()
		    
		    getUserList()
		    
		    delete ()
		    
		    count()  