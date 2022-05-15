========================================Lesson1  ===================================================
1 工厂模式
2 spring框架
3 spring示例程序
4 setter方式注入
5 bean 的作用域
6 集合类型的装配
7 P名称空间
====================================================================================================
==== 1 工厂模式
		用于创建对象
	
			public class Test {
				public static void main(String[] args) {
					TV tv1=TVFactory.creatTV();  //依赖对象的创建,交给了工厂类,最大的好处,是让对象的创建过程和应用本身解耦
					tv1.play();
					
					TV tv2=TVFactory.creatTV();
					
					System.out.println(tv1==tv2);  //false  通过工厂创建的对象,每一个都是新的对象实例 
				}
			}
			
			class TVFactory{
				public static TV creatTV() {
					TV tv=new TV("灰","国内", 220);
					return tv;
				}
			}
			
			class TV{
				public TV(String color , String channel, int v){
					this.color=color;
					this.channel=channel;
					this.v=v;	
				}
				private String color;
				private String channel;
				private int v;
				
				public void play() {
					System.out.println(v+" 伏电压供应完成,电视开始播放");
					System.out.println("色彩"+color);
					System.out.println("频道"+channel);
				}
			}
			
			工厂模式,属于创建型的设计模式
			它创建的每个对象都是不同的
			最主要的意义: 把对象创建的过程,和应用本身解耦
			
==== 2 spring框架中的几个重要概念
		Spring 是一个开源的,控制反转(IOC),和面象切面(AOP) 的容器框架
		  
		 1) IOC (Inversion of controll)
		   
		    应用本身负责依赖对象的创建, 而是把它们的创建的控制权义给外部容器,这样,对象的创建的控制权
		    就由应用本身转到了外部容器,控制权的转移叫控制反转
		    
		    //传统的程序 
		    class UserServlet{
		    		//dao 就是一个依赖对象,在这里直接new出来了,这就是由应用本身负责依赖对象的创建
		    		private IUserDao dao=new UserDaoImpl(); 
		    	
		    	  void service(){
		    	  	 dao.login();
		    	  }	
		    }
		    
		    //反转形式
		    class UserServlet{
		    	  //这个dao对象 ,将来是由"别人", 也就是说外部程序创建,然后传给 "我们"
		    	  public  UserServlet(IUserDao dao){
		    	  		this.dao=dao;
		    	  }
		    		//dao 就是一个依赖对象,但不由应用本身创建
		    		private IUserDao dao;
		    	
		    	  void service(){
		    	  	 dao.login();
		    	  }	
		    }
		    
		    其他程序调用 UserServlet
		    
		     IUserDao dao =new UserDaoImpl();
		     UserServlet servlet=new UserServlet(dao);
		     
		  
		 2) 依赖注入 (DI)
		      在程序运行期,由外部容器,把依赖对象动态的传过来的过程,就叫依赖注入
		      class UserServlet{
		    		private IUserDao dao;
		    		
		    	  public void service(){
		    	  	 dao.login();
		    	  }	
		    	  
		    	  //这个方法,留给外部容器调用,在运行期把依赖对象传过来
		    	  public void setDao(IUserDao dao){
		    	  		this.dao=dao;
		    	  }
		      }
		      
		 3) AOP 面象切面编程 
		       
==== 3 spring示例程序
	  1) 导包
	       导入之后,不要忘了添加到构建路径中 
	  
	  2) 配置文件 
	       applicationContext.xml 
	       
	       放在src目录下就可以  //类路径下
					       
				<?xml version="1.0" encoding="UTF-8"?>
				<beans xmlns="http://www.springframework.org/schema/beans"
					xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
					
					<bean name="userInfo_name" class="com.beans.UserInfo">
						<property name="id" value="99" />
						<property name="userName" value="admin" />
						<property name="password" value="123" />
						<property name="note" value="这是一个来源于spring的对象" />
					</bean>
				</beans>
				
	  3) 测试
			package com.test;
			import org.springframework.context.support.ClassPathXmlApplicationContext;
			import com.beans.UserInfo;
			public class Test {
				public static void main(String[] args) {
					//初始化容器对象,从类路径下加载配置文件 
					ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
					
					//从容器中得到一个bean 
					UserInfo user= ioc.getBean("userInfo_name",UserInfo.class);	
					System.out.println(user);
				}
			}
			
			spring的配置文件是可以有多个的,在加载的时候,也可以同时加载多个
			new ClassPathXmlApplicationContext("配置文件1.xml", "配置文件2.xml", ...  );
			
==== 4 setter方式注入
    注入,讨论的是容器如何把依赖对象传给应用的过程 ,主要有以下几种方式
     1)  setter 方式
     2)  构造方法
     3)  静态工厂
     4)  实例工厂
     
     这里我们只讨论第一种 
     
     (1) 创建接口
				package com.dao;
				public interface IUserDao {
					void addUser();
					void updateUser();
					void deleteUser();
				}
				
		 (2) 创建实现类
				package com.dao.impl;
				import com.dao.IUserDao;
				public class UserDaoImplMySql implements IUserDao{
				
					public void addUser() {
						System.out.println("addUser 方法调用了  mysql实现");
					}
				
					public void updateUser() {
						System.out.println(" updateUser 方法调用了  mysql实现");
					}
				
					public void deleteUser() {
						System.out.println("deleteUser 方法调用了  mysql实现");
					}
				}
				
		 (3) 模拟一个控制层 Servlet
				package com.controller;
				import com.dao.IUserDao;
				
				public class UserServlet {	
					//依赖对象
					private IUserDao dao;
					
					public void setDao(IUserDao dao) {
						this.dao = dao;
					}
				
					public void service() {
						dao.addUser();
						dao.updateUser();
						dao.deleteUser();	
					}
				}
				
		 (4) 配置文件
				<bean name="userServlet_name" class="com.controller.UserServlet">
					<property name="dao" ref="daoImpMysql" />
				</bean>
				
				<bean name="daoImpMysql" class="com.dao.impl.UserDaoImplMySql" />
				
				可以看到,上面是用 ref的方式建立起来两个bean之间的关联
				
		 (5) 测试
					public static void main(String[] args) {
						//初始化容器对象,从类路径下加载配置文件 
						ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
					
						UserServlet servlet=ioc.getBean("userServlet_name",UserServlet.class );
						
						servlet.service();
					}
					
					程序可以运行
					
		 (6) 我们可以再编写一个 IUserDao 的实现类
					package com.dao.impl;
					import com.dao.IUserDao;
					public class UserDaoImplOracle implements IUserDao {
						public void addUser() {
							System.out.println("addUser 方法调用了 oracle实现");
						}
					
						public void updateUser() {
							System.out.println("updateUser 方法调用了 oracle实现");
						}
					
						public void deleteUser() {
							System.out.println("deleteUser 方法调用了 oracle实现");
						}
					}
					
		 (7) 配置文件
					<bean name="userServlet_name" class="com.controller.UserServlet">
						<property name="dao" ref="daoImpOracle" />
					</bean>
					
					<bean name="daoImpMysql" class="com.dao.impl.UserDaoImplMySql" />	
					<bean name="daoImpOracle" class="com.dao.impl.UserDaoImplOracle" />
					
					可以看到,可以很轻易的在 在两个不同的实现类之间进行选择
					
					
==== 5 bean 的作用域
		 上面的例子中,我们从spring容器中得到的bean是单实例的还是多实例的呢 ?  
				public static void main(String[] args) {

					ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
				
					UserServlet servlet1=ioc.getBean("userServlet_name",UserServlet.class );
					UserServlet servlet2=ioc.getBean("userServlet_name",UserServlet.class );
					
					System.out.println(servlet1==servlet2);  //true  
				}
					
			 可以看到,默认情况下,得到的 bean 是单实例的
			 
			 spring中管理的 bean ,有几种作用域(scope 的取值) 
			   1) singleton
			   2) prototype
			   3) request
			   4) session
			   
			   
			   1) singleton 是默认值,表示创建的实例是单实例 
					   <bean name="userInfo_name" class="com.beans.UserInfo" scope="singleton">
							  ...
						 </bean>
						 
						 这个单实例的bean是什么时候创建的呢 ? 是在初始化spring容器的时候还是在我们向spring申请一个bean 的时候?
						 它是在容器初始化的时候创建的 
						 这个行为是可以改变的,可以加参数   lazy-init="true" 表示不要在容器初始化的时候创建,而是在程序申请的时候才创建		 
							<bean name="userInfo_name" class="com.beans.UserInfo"  lazy-init="true">
							</bean>
						 如果希望所有的bean 全是这样 ,也可以在全局的 beans标签上进行配置,如下 
						 <beans default-lazy-init="true"  ...   >
						 
			   2)　prototype
			   　　这个取值，表示每次都将产生一个新的对象
			   
			   　　什么时候用单实例，什么时候用多实例
			   　　没有线程安全问题，就可以用单实例，否则要用多实例　
			   
			   　　　比如，通常　一些工具类，dao，往往没有线程安全问题，就是单实例
			   			　控制层可能会有线程安全问题，就可以用多实例　
			   
							
==== 6 集合类型的装配
		1) set集合
		2) map集合
	    	...	
		public class UserServlet {	
			//依赖对象
			private IUserDao dao;
			
			//set集合
			private Set<String> mySet;
			
			//map集合
			private Map<String,String> myMap;
		
			public void service() {
				dao.addUser();
				dao.updateUser();
				dao.deleteUser();	
				
				mySet.forEach( e->System.out.println(e));
				
				myMap.forEach((k,v)->System.out.println(k+":"+v));
			}
			
			public void setMySet(Set<String> mySet) {
				this.mySet = mySet;
			}
		
			public void setMyMap(Map<String, String> myMap) {
				this.myMap = myMap;
			}
			
			//这个方法,是留给spring容器调用的,帮我们传入依赖对象
			public void setDao(IUserDao dao) {
				this.dao = dao;
			}
		}

	<bean name="userServlet_name" class="com.controller.UserServlet">
		<property name="dao" ref="daoImpMysql" />
		
		<property name="mySet">
			<set>
				<value>子弹</value>
				<value>炸药</value>
				<value>刺刀</value>
		    </set>
		</property>
		
		<property name="myMap">
			<map>
				<entry key="GUN_01"  value="AK47" />
				<entry key="GUN_02"  value="M16" />
				<entry key="GUN_03"  value="MP5" />
			</map>
		</property>
	</bean>
								 
==== 7 p名称空间									
	 这个bean 可以按下面的方式写								
	<!--  
	<bean name="userInfo_name" class="com.beans.UserInfo" scope="prototype" >
		<property name="id" value="99" />
		<property name="userName" value="admin" />
		<property name="password" value="123" />
		<property name="note" value="这是一个来源于spring的对象" />
	</bean>
	-->
	
	<bean name="userInfo_name"  class="com.beans.UserInfo" 
	   p:userName="root"
	   p:password="admin123"
	   p:id="20"
	   p:note="这是一个用了p名称空间的类" />							
																			
															       
    附 关于名称空间
			<?xml version="1.0" encoding="UTF-8"?>   //这行是xml文档声明,前面不能有任何字符
			
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:p="http://www.springframework.org/schema/p"
				xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
				
																		      
			xmlns ==> xml name space 	称为xml名称空间	
			
			使用默认名称空间中的内容,可以不用加前缀,用其他名称空间中的内容,一般都是要加前缀的					
															
									       
					       
	      
		      
		    