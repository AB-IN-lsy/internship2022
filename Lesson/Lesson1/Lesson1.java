========================================Lesson1  ===================================================
1 ����ģʽ
2 spring���
3 springʾ������
4 setter��ʽע��
5 bean ��������
6 �������͵�װ��
7 P���ƿռ�
====================================================================================================
==== 1 ����ģʽ
		���ڴ�������
	
			public class Test {
				public static void main(String[] args) {
					TV tv1=TVFactory.creatTV();  //��������Ĵ���,�����˹�����,���ĺô�,���ö���Ĵ������̺�Ӧ�ñ������
					tv1.play();
					
					TV tv2=TVFactory.creatTV();
					
					System.out.println(tv1==tv2);  //false  ͨ�����������Ķ���,ÿһ�������µĶ���ʵ�� 
				}
			}
			
			class TVFactory{
				public static TV creatTV() {
					TV tv=new TV("��","����", 220);
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
					System.out.println(v+" ����ѹ��Ӧ���,���ӿ�ʼ����");
					System.out.println("ɫ��"+color);
					System.out.println("Ƶ��"+channel);
				}
			}
			
			����ģʽ,���ڴ����͵����ģʽ
			��������ÿ�������ǲ�ͬ��
			����Ҫ������: �Ѷ��󴴽��Ĺ���,��Ӧ�ñ������
			
==== 2 spring����еļ�����Ҫ����
		Spring ��һ����Դ��,���Ʒ�ת(IOC),����������(AOP) ���������
		  
		 1) IOC (Inversion of controll)
		   
		    Ӧ�ñ�������������Ĵ���, ���ǰ����ǵĴ����Ŀ���Ȩ����ⲿ����,����,����Ĵ����Ŀ���Ȩ
		    ����Ӧ�ñ���ת�����ⲿ����,����Ȩ��ת�ƽп��Ʒ�ת
		    
		    //��ͳ�ĳ��� 
		    class UserServlet{
		    		//dao ����һ����������,������ֱ��new������,�������Ӧ�ñ�������������Ĵ���
		    		private IUserDao dao=new UserDaoImpl(); 
		    	
		    	  void service(){
		    	  	 dao.login();
		    	  }	
		    }
		    
		    //��ת��ʽ
		    class UserServlet{
		    	  //���dao���� ,��������"����", Ҳ����˵�ⲿ���򴴽�,Ȼ�󴫸� "����"
		    	  public  UserServlet(IUserDao dao){
		    	  		this.dao=dao;
		    	  }
		    		//dao ����һ����������,������Ӧ�ñ�����
		    		private IUserDao dao;
		    	
		    	  void service(){
		    	  	 dao.login();
		    	  }	
		    }
		    
		    ����������� UserServlet
		    
		     IUserDao dao =new UserDaoImpl();
		     UserServlet servlet=new UserServlet(dao);
		     
		  
		 2) ����ע�� (DI)
		      �ڳ���������,���ⲿ����,����������̬�Ĵ������Ĺ���,�ͽ�����ע��
		      class UserServlet{
		    		private IUserDao dao;
		    		
		    	  public void service(){
		    	  	 dao.login();
		    	  }	
		    	  
		    	  //�������,�����ⲿ��������,�������ڰ��������󴫹���
		    	  public void setDao(IUserDao dao){
		    	  		this.dao=dao;
		    	  }
		      }
		      
		 3) AOP ���������� 
		       
==== 3 springʾ������
	  1) ����
	       ����֮��,��Ҫ������ӵ�����·���� 
	  
	  2) �����ļ� 
	       applicationContext.xml 
	       
	       ����srcĿ¼�¾Ϳ���  //��·����
					       
				<?xml version="1.0" encoding="UTF-8"?>
				<beans xmlns="http://www.springframework.org/schema/beans"
					xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
					
					<bean name="userInfo_name" class="com.beans.UserInfo">
						<property name="id" value="99" />
						<property name="userName" value="admin" />
						<property name="password" value="123" />
						<property name="note" value="����һ����Դ��spring�Ķ���" />
					</bean>
				</beans>
				
	  3) ����
			package com.test;
			import org.springframework.context.support.ClassPathXmlApplicationContext;
			import com.beans.UserInfo;
			public class Test {
				public static void main(String[] args) {
					//��ʼ����������,����·���¼��������ļ� 
					ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
					
					//�������еõ�һ��bean 
					UserInfo user= ioc.getBean("userInfo_name",UserInfo.class);	
					System.out.println(user);
				}
			}
			
			spring�������ļ��ǿ����ж����,�ڼ��ص�ʱ��,Ҳ����ͬʱ���ض��
			new ClassPathXmlApplicationContext("�����ļ�1.xml", "�����ļ�2.xml", ...  );
			
==== 4 setter��ʽע��
    ע��,���۵���������ΰ��������󴫸�Ӧ�õĹ��� ,��Ҫ�����¼��ַ�ʽ
     1)  setter ��ʽ
     2)  ���췽��
     3)  ��̬����
     4)  ʵ������
     
     ��������ֻ���۵�һ�� 
     
     (1) �����ӿ�
				package com.dao;
				public interface IUserDao {
					void addUser();
					void updateUser();
					void deleteUser();
				}
				
		 (2) ����ʵ����
				package com.dao.impl;
				import com.dao.IUserDao;
				public class UserDaoImplMySql implements IUserDao{
				
					public void addUser() {
						System.out.println("addUser ����������  mysqlʵ��");
					}
				
					public void updateUser() {
						System.out.println(" updateUser ����������  mysqlʵ��");
					}
				
					public void deleteUser() {
						System.out.println("deleteUser ����������  mysqlʵ��");
					}
				}
				
		 (3) ģ��һ�����Ʋ� Servlet
				package com.controller;
				import com.dao.IUserDao;
				
				public class UserServlet {	
					//��������
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
				
		 (4) �����ļ�
				<bean name="userServlet_name" class="com.controller.UserServlet">
					<property name="dao" ref="daoImpMysql" />
				</bean>
				
				<bean name="daoImpMysql" class="com.dao.impl.UserDaoImplMySql" />
				
				���Կ���,�������� ref�ķ�ʽ������������bean֮��Ĺ���
				
		 (5) ����
					public static void main(String[] args) {
						//��ʼ����������,����·���¼��������ļ� 
						ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
					
						UserServlet servlet=ioc.getBean("userServlet_name",UserServlet.class );
						
						servlet.service();
					}
					
					�����������
					
		 (6) ���ǿ����ٱ�дһ�� IUserDao ��ʵ����
					package com.dao.impl;
					import com.dao.IUserDao;
					public class UserDaoImplOracle implements IUserDao {
						public void addUser() {
							System.out.println("addUser ���������� oracleʵ��");
						}
					
						public void updateUser() {
							System.out.println("updateUser ���������� oracleʵ��");
						}
					
						public void deleteUser() {
							System.out.println("deleteUser ���������� oracleʵ��");
						}
					}
					
		 (7) �����ļ�
					<bean name="userServlet_name" class="com.controller.UserServlet">
						<property name="dao" ref="daoImpOracle" />
					</bean>
					
					<bean name="daoImpMysql" class="com.dao.impl.UserDaoImplMySql" />	
					<bean name="daoImpOracle" class="com.dao.impl.UserDaoImplOracle" />
					
					���Կ���,���Ժ����׵��� ��������ͬ��ʵ����֮�����ѡ��
					
					
==== 5 bean ��������
		 �����������,���Ǵ�spring�����еõ���bean�ǵ�ʵ���Ļ��Ƕ�ʵ������ ?  
				public static void main(String[] args) {

					ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
				
					UserServlet servlet1=ioc.getBean("userServlet_name",UserServlet.class );
					UserServlet servlet2=ioc.getBean("userServlet_name",UserServlet.class );
					
					System.out.println(servlet1==servlet2);  //true  
				}
					
			 ���Կ���,Ĭ�������,�õ��� bean �ǵ�ʵ����
			 
			 spring�й���� bean ,�м���������(scope ��ȡֵ) 
			   1) singleton
			   2) prototype
			   3) request
			   4) session
			   
			   
			   1) singleton ��Ĭ��ֵ,��ʾ������ʵ���ǵ�ʵ�� 
					   <bean name="userInfo_name" class="com.beans.UserInfo" scope="singleton">
							  ...
						 </bean>
						 
						 �����ʵ����bean��ʲôʱ�򴴽����� ? ���ڳ�ʼ��spring������ʱ������������spring����һ��bean ��ʱ��?
						 ������������ʼ����ʱ�򴴽��� 
						 �����Ϊ�ǿ��Ըı��,���ԼӲ���   lazy-init="true" ��ʾ��Ҫ��������ʼ����ʱ�򴴽�,�����ڳ��������ʱ��Ŵ���		 
							<bean name="userInfo_name" class="com.beans.UserInfo"  lazy-init="true">
							</bean>
						 ���ϣ�����е�bean ȫ������ ,Ҳ������ȫ�ֵ� beans��ǩ�Ͻ�������,���� 
						 <beans default-lazy-init="true"  ...   >
						 
			   2)��prototype
			   �������ȡֵ����ʾÿ�ζ�������һ���µĶ���
			   
			   ����ʲôʱ���õ�ʵ����ʲôʱ���ö�ʵ��
			   ����û���̰߳�ȫ���⣬�Ϳ����õ�ʵ��������Ҫ�ö�ʵ����
			   
			   ���������磬ͨ����һЩ�����࣬dao������û���̰߳�ȫ���⣬���ǵ�ʵ��
			   			�����Ʋ���ܻ����̰߳�ȫ���⣬�Ϳ����ö�ʵ����
			   
							
==== 6 �������͵�װ��
		1) set����
		2) map����
	    	...	
		public class UserServlet {	
			//��������
			private IUserDao dao;
			
			//set����
			private Set<String> mySet;
			
			//map����
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
			
			//�������,������spring�������õ�,�����Ǵ�����������
			public void setDao(IUserDao dao) {
				this.dao = dao;
			}
		}

	<bean name="userServlet_name" class="com.controller.UserServlet">
		<property name="dao" ref="daoImpMysql" />
		
		<property name="mySet">
			<set>
				<value>�ӵ�</value>
				<value>ըҩ</value>
				<value>�̵�</value>
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
								 
==== 7 p���ƿռ�									
	 ���bean ���԰�����ķ�ʽд								
	<!--  
	<bean name="userInfo_name" class="com.beans.UserInfo" scope="prototype" >
		<property name="id" value="99" />
		<property name="userName" value="admin" />
		<property name="password" value="123" />
		<property name="note" value="����һ����Դ��spring�Ķ���" />
	</bean>
	-->
	
	<bean name="userInfo_name"  class="com.beans.UserInfo" 
	   p:userName="root"
	   p:password="admin123"
	   p:id="20"
	   p:note="����һ������p���ƿռ����" />							
																			
															       
    �� �������ƿռ�
			<?xml version="1.0" encoding="UTF-8"?>   //������xml�ĵ�����,ǰ�治�����κ��ַ�
			
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:p="http://www.springframework.org/schema/p"
				xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
				
																		      
			xmlns ==> xml name space 	��Ϊxml���ƿռ�	
			
			ʹ��Ĭ�����ƿռ��е�����,���Բ��ü�ǰ׺,���������ƿռ��е�����,һ�㶼��Ҫ��ǰ׺��					
															
									       
					       
	      
		      
		    