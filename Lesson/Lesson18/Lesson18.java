======================================Lesson18 ===========================================
1 ��jspǨ�Ƶ�WEB-INF Ŀ¼��
2 ������
3 AOP����
4 �˳�ע��
5 �������ܷ���
6 ��ǩ��صĹ���
==========================================================================================
==== 1 ��jspҳ��Ǩ�Ƶ�WEB-INF Ŀ¼��
    1) �� webapp/view ���Ŀ¼ ֱ�ӷŵ�WEB-INF�� ,��ΪWEB-INF �ǰ�ȫĿ¼,���ܴӿͻ���ֱ�ӷ���
    
    2) ����������ͼ������ spring-mvc.xml��
			<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<property name="prefix" value="/WEB-INF/view/" />
				<property name="suffix" value=".jsp"  />
			</bean>
				
	  3) �����Ѿ��޷�ͨ�� 
	      http://localhost:8080/aolin-system/WEB-INF/view/login.jsp ���ʷ������
	      ���Ըĳɴӷ���� "����" 
	      
			  �� UserController �� ������·���
				@RequestMapping(value="/login", method=RequestMethod.GET)
				public String goToLogin() {
					return "login";
				}
				     
			 �ٷ�������ĵ�ַ,�Ϳ���ת����¼ҳ��
       http://localhost:8080/aolin-system/user/login 
				      
==== 2 ������
     1) �� com.aop��,�����潨������ 
				package com.aop;
				import javax.servlet.http.HttpServletRequest;
				import javax.servlet.http.HttpServletResponse;
				import org.springframework.web.method.HandlerMethod;
				import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
				
				/**
				 * ������,���ڿ����û�,������session���ܵ�¼
				 * @author Administrator
				 *
				 */
				public class SessionInterceptor  extends HandlerInterceptorAdapter{
				
					/**
					 *  handler �������,���Եõ����õķ����������Ϣ
					 */
					public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
							throws Exception {

						//û��session,��ת����¼ҳ
						if(request.getSession().getAttribute("session_user")==null) {
							request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
							return false;
						}
						else {
							return true;
						}
					}
				
				}
				
		2) �������ļ������������� spring-mvc.xml
				<mvc:interceptors>
					<mvc:interceptor>
						<mvc:mapping path="/**"/>   //��������
						<mvc:exclude-mapping path="/user/login" />  //��¼�Ĺ��ܲ�����
						<mvc:exclude-mapping path="/resources/**" />  //���еľ�̬��Դ���Բ���
						<bean class="com.aop.SessionInterceptor"  />  //ָ�����������ĸ���
					</mvc:interceptor>
				</mvc:interceptors>
					   
		3) ����:
		   ����������Ŀ,���� http://localhost:8080/aolin-system/user/add  �����޷�������,����ת����
		   login.jsp ,֤�����سɹ�
		   
		   
==== 3 AOP����
		1) ������� log4j �������
        <!-- ���log4j��־ -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.21</version>
        </dependency>
        <!-- apache������ -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
        
    2) ���� log4j�������ļ�   log4j.properties ���� src/main/resources/ �¾Ϳ���
					# Configure logging for testing: optionally with log file
					log4j.rootLogger=INFO, stdout, logfile
					
					log4j.appender.stdout=org.apache.log4j.ConsoleAppender
					log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
					log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} %p [%c] %L - %m%n
					
					log4j.appender.logfile=org.apache.log4j.FileAppender
					log4j.appender.logfile.File=d://my.log
					log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
					log4j.appender.logfile.layout.ConversionPattern=%d{HH:mm:ss} %p [%c] %L - %m%n
		
	 3) ����
					package com.aop;
					import org.apache.log4j.Logger;
					import org.aspectj.lang.ProceedingJoinPoint;
					import org.aspectj.lang.annotation.*;
					import org.springframework.stereotype.Component;
					
					@Aspect @Component
					public class LogAspect {
						Logger log =Logger.getLogger(LogAspect.class);
						
						@Pointcut("execution(* com.controller.*.*(..))")  //���������
						private void anyMethod() {}
						
						@Around("anyMethod()")
						public Object aroundMethod(ProceedingJoinPoint point) {
							Object result=null;
							
							log.trace("ǰ��֪ͨ������..�������¼�� trace��Ϣ");
							
							String methodName=point.getSignature().getName();
							log.info("��ǰִ�еķ�����:"+methodName);
					
							try {
								result=  point.proceed();	
								log.info("����ִ�����,������Ǻ���֪ͨ");
							}
							catch(Throwable e) {
								e.printStackTrace();
								log.error("����ִ�г���,����֪ͨ");
								
							}finally {
								log.info("����֪ͨ������");
							}
							
							return result;
						}
					
					}
					
   4)	 ��spring-mvc.xml�� 
           ���� aop���ƿռ��,����
            <aop:aspectj-autoproxy />
            
   5)  ����ϵͳ.����
   
         ���Կ���,�ڿ���̨�������־,
         ��d���ϵ�my.log�ļ���,Ҳ�������־
         
==== 4 �˳�ע��
  1) framework.jsp�Ϸ�������
   <a href="user/logout" onclick="return confrim('ȷ��Ҫ�˳���?')">�˳�</a>
   
  2)@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//һ��Ҫ����û���session
		session.invalidate();
		return "login";
	}
											
									      
==== 5 �������ܷ���
   ���ӵ�ʵ����: 
		public class Link{
		    private Integer linkId;
		
		    //���ӵ�ַ
		    private String linkUrl;
		
		    //��������
		    private String linkName;
		
		    //����ͼƬ
		    private String linkImage;
		
		    //��������
		    private String linkDescription;
		
		    //�����������ǳ�
		    private String linkOwnerNickname;
		
		    //��ϵ��ʽ
		    private String linkOwnerContact;
		
		    //������ʱ��
		    private Date linkUpdateTime;
		
		    //����ʱ��
		    private Date linkCreateTime;
		
		    //����˳��
		    private Integer linkOrder;
		
		    //����״̬
		    private Integer linkStatus;
		  }
						
	  �����Ӧ��ʵ���� 	
	  public class Notice{
	    private Integer noticeId;
	    private String noticeTitle;
		  private String noticeContent;
	    private Date noticeCreateTime;
	    private Date noticeUpdateTime;
	    private Integer noticeStatus;
	    private Integer noticeOrder;
	  }	
	  
		���� ��Ӧ��ʵ����
	 public class Comment{
			    private Integer commentId;
			
			    private Integer commentPid;
			
			    //����������
			    private String commentPname;
			
			    //�����۵�����id
			    private Integer commentArticleId;
			
			    private String commentAuthorName;
			
			    //����������
			    private String commentAuthorEmail;
			
			    //��������ַ
			    private String commentAuthorUrl;
			
			    //���� http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG ָ��һ��ͷ���ַ
			    private String commentAuthorAvatar;
			
			    //��������
			    private String commentContent;
			
			    private String commentAgent;
			
			    //������ip
			    private String commentIp;
			
			    //����ʱ��
			    private Date commentCreateTime;
			 
			    //��ɫ(����Ա1���ÿ�0)
			    private Integer commentRole;
			
			    //�����ݿ��ֶ�,��ʾ���۵��������ĸ�
			    private Article article;
			 }					
						 
			�˵���Ӧ��ʵ����
			public class Menu  {
			
			    private Integer menuId;
			    private String menuName;
			    private String menuUrl;
			    
			    //���ֵ�����������Ƕ����˵�(1)������Ҫ�˵���(2)
			    private Integer menuLevel;
			      
			    private String menuIcon;
			    
			    //�˵���˳��
			    private Integer menuOrder;
			  }
			    
			վ����Ϣ
			public class Options {
			    private Integer optionId;
			
			    //վ������
			    private String optionSiteTitle;
			
			    //վ������
			    private String optionSiteDescrption;
			
			    //��ҳ����
			    private String optionMetaDescrption;
			
			    //��ҳ�ؼ���
			    private String optionMetaKeyword;
			
			    //ͷ��ͼƬ
			    private byte [] optionAboutsitePhoto;
			
			    //�ǳ�
			    private String optionAboutsiteTitle;
			
			    //˵��
			    private String optionAboutsiteContent;
			
			    //΢�Ŷ�ά��ͼƬ
			    private  byte [] optionAboutsiteWechatphoto;
			
			    //QQ
			    private String optionAboutsiteQq;
			
			    //git��ַ
			    private String optionAboutsiteGithub;
			
			    //΢��
			    private String optionAboutsiteWeibo;
			
			    private String optionTongji;
			
			    private Integer optionStatus;
			  }
						 
						 
==== 6 ��ǩ��صĹ���   
		��ǩ��صĹ���
	   
	   ��ѯ�б���:
		   1) �� framework.jsp�Ϸ�������
		         <dd><a href="tag">ȫ����ǩ</a></dd>
		   
		   2) TagController ���Ʋ�:
					
					@Controller @RequestMapping("/tag")
					public class TagController {
						@Resource
						TagService tagService;
					
						@RequestMapping("")
						public String listTag(ModelMap m) {
							List<Tag> tagList=tagService.listTag();
							m.put("tagList", tagList);
							return "Tag/tag";	
						}
						
					}
					
      3) ҳ��
					<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
					<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
					
					<rapid:override name="frame-header-style">
					  <style>
					        /*���� layui*/
					        .layui-input-block {
					            margin:0px 10px;
					        }
					        .layui-table {
					            margin-top: 0;
					        }
					        .layui-col-md4 {
					            padding:10px;
					        }
					        .layui-col-md8 {
					            padding:10px;
					        }
					        .layui-btn {
					            margin: 2px 0!important;
					        }
					    </style>
					    
					</rapid:override>
					<rapid:override name="frame-content">
						<blockquote class="layui-elem-quote">
					        <span class="layui-breadcrumb" lay-separator="/">
					              <a href="/admin">��ҳ</a>
					              <a><cite>��ǩ�б�</cite></a>
					        </span>
					    </blockquote>
					    <div class="layui-row">
					        <div class="layui-col-md4">
					            <form class="layui-form"  method="post" id="myForm" action="/admin/tag/insertSubmit">
					                <div class="layui-form-item">
					                    <div class="layui-input-block">
					                        <strong>��ӱ�ǩ</strong>
					                    </div>
					                    <div class="layui-input-block">
					                        ���� <span style="color: #FF5722; ">*</span>
					                        <input type="text" name="tagName" placeholder="�������ǩ����" autocomplete="off" class="layui-input" required>
					                    </div>
					                    <br>
					                    <div class="layui-input-block">
					                        ��ǩ����
					                        <input type="text" name="tagDescription" placeholder="�������ǩ����" autocomplete="off" class="layui-input" >
					                    </div>
					                    <br>
					                    <div class="layui-input-block">
					                        <button class="layui-btn" lay-filter="formDemo" type="submit">���</button>
					                    </div>
					                </div>
					            </form>
					            <blockquote class="layui-elem-quote layui-quote-nm">
					                ��ܰ��ʾ��
					                <ul>
					                    <li>1����ǩ����ѡ�����鲻Ҫ̫��</li>
					                    <li>2����ǩ�����ظ�</li>
					                </ul>
					            </blockquote>
					        </div>
					        <div class="layui-col-md8" >
					
					            <table class="layui-table" >
					                <colgroup>
					                    <col width="300">
					                    <col width="50">
					                    <col width="100">
					                    <col width="50">
					                </colgroup>
					                <thead>
					                <tr>
					                    <th>����</th>
					                    <th>������</th>
					                    <th>����</th>
					                    <th>ID</th>
					                </tr>
					                </thead>
					                <tbody>
					                  <c:forEach var="t" items="${tagList}">
						                  <tr>
						                        <td>
						                            <a href="/tag/${t.tagId }" target="_blank">${t.tagName }</a>
						                        </td>
						                        <td >
						                            <a href="/tag/${t.tagId }" target="_blank"  lay-data="{sort:true}">${t.articleCount }</a>
						                        </td>
						                        <td>
						                            <a href="/admin/tag/edit/${t.tagId }" class="layui-btn layui-btn-mini">�༭</a>
						                            </td>
						                        <td >${t.tagId }</td>
						                 </tr>  
					                 </c:forEach>
					
					                </tbody>
					            </table>
					            <blockquote class="layui-elem-quote layui-quote-nm">
					                ��ܰ��ʾ��
					                <ul>
					                    <li>����ñ�ǩ�������£�������ɾ��</li>
					                </ul>
					            </blockquote>
					        </div>
					    </div>
					</rapid:override>
					
					<%@ include file="../framework.jsp"  %>
		
		 ��ӱ�ǩ����
		    1) ��
 					<form class="layui-form"  method="post" id="myForm" action="tag/addOrUpdate">
            	<input type="hidden" value="tagId"  >
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>��ӱ�ǩ</strong>
                    </div>
                    <div class="layui-input-block">
                        ���� <span style="color: #FF5722; ">*</span>
                        <input type="text" name="tagName" placeholder="�������ǩ����" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        ��ǩ����
                        <input type="text" name="tagDescription" placeholder="�������ǩ����" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">�ύ</button>
                    </div>
                </div>
            </form>
            
        2) ���Ʋ�:
	         @RequestMapping("/addOrUpdate")
						public String addOrUpdate(Tag tag) {
							//����
							if(tag.getTagId()==null ||tag.getTagId()==0) {
								tagService.addTag(tag);
							}
							else{
								  //�����������޸ĵ�
							}
							
							return "forward:/tag";
							
						}
							   
			�޸ı�ǩ����:
			  1) ���б��Ϸ�������
			     <a href="tag/edit/${t.tagId }" class="layui-btn layui-btn-mini">�༭</a>
			 
			  2) ���Ʋ�
								 		
						//����id,���tag��Ϣ,ת���޸�ҳ��
						@RequestMapping("/edit/{tagId}")
						public String editTag(@PathVariable("tagId")Integer tagId, ModelMap m) {
							Tag tag =tagService.getTagById(tagId);
							m.put("tag", tag);
							
							return "forward:/tag";
						}
			  
			  3) �޸�ҳ��:
	    			<form class="layui-form"  method="post" id="myForm" action="tag/addOrUpdate">
	            	  <input type="hidden" name="tagId"  value="${tag.tagId }" >  //һ��Ҫ�����
	                <div class="layui-form-item">
	                    <div class="layui-input-block">
	                        <strong>��ӱ�ǩ</strong>
	                    </div>
	                    <div class="layui-input-block">
	                        ���� <span style="color: #FF5722; ">*</span>
	                        <input type="text" name="tagName" value="${tag.tagName }" placeholder="�������ǩ����" autocomplete="off" class="layui-input" required>
	                    </div>
	                    <br>
	                    <div class="layui-input-block">
	                        ��ǩ����
	                        <input type="text" name="tagDescription" value="${tag.tagDescription }" placeholder="�������ǩ����" autocomplete="off" class="layui-input" >
	                    </div>
	                    <br>
	                    <div class="layui-input-block">
	                        <button class="layui-btn" lay-filter="formDemo" type="submit">�ύ</button>
	                    </div>
	                </div>
	            </form>
	            
	       4) �ύ��,���Ʋ�
						@RequestMapping("/addOrUpdate")
							public String addOrUpdate(Tag tag) {
								 //����
								if(tag.getTagId()==null ||tag.getTagId()==0) {
									tagService.addTag(tag);
								}
								else {//�޸�
									tagService.updateTag(tag);
								}
								
								return "forward:/tag";
								
							}
							
				 5) ���е������,Mapper,ӳ���ļ�,��