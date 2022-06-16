======================================Lesson18 ===========================================
1 把jsp迁移到WEB-INF 目录下
2 拦截器
3 AOP切面
4 退出注销
5 其他功能分析
6 标签相关的功能
==========================================================================================
==== 1 把jsp页面迁移到WEB-INF 目录下
    1) 把 webapp/view 这个目录 直接放到WEB-INF下 ,因为WEB-INF 是安全目录,不能从客户端直接访问
    
    2) 重新配置视图解析器 spring-mvc.xml中
			<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<property name="prefix" value="/WEB-INF/view/" />
				<property name="suffix" value=".jsp"  />
			</bean>
				
	  3) 现在已经无法通过 
	      http://localhost:8080/aolin-system/WEB-INF/view/login.jsp 访问服务端了
	      可以改成从服务端 "绕行" 
	      
			  在 UserController 中 添加如下方法
				@RequestMapping(value="/login", method=RequestMethod.GET)
				public String goToLogin() {
					return "login";
				}
				     
			 再访问下面的地址,就可以转到登录页了
       http://localhost:8080/aolin-system/user/login 
				      
==== 2 拦截器
     1) 建 com.aop包,在里面建拦截器 
				package com.aop;
				import javax.servlet.http.HttpServletRequest;
				import javax.servlet.http.HttpServletResponse;
				import org.springframework.web.method.HandlerMethod;
				import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
				
				/**
				 * 拦截器,用于控制用户,必须有session才能登录
				 * @author Administrator
				 *
				 */
				public class SessionInterceptor  extends HandlerInterceptorAdapter{
				
					/**
					 *  handler 这个参数,可以得到调用的方法的相关信息
					 */
					public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
							throws Exception {

						//没有session,则转到登录页
						if(request.getSession().getAttribute("session_user")==null) {
							request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
							return false;
						}
						else {
							return true;
						}
					}
				
				}
				
		2) 在配置文件中配置拦截器 spring-mvc.xml
				<mvc:interceptors>
					<mvc:interceptor>
						<mvc:mapping path="/**"/>   //拦截所有
						<mvc:exclude-mapping path="/user/login" />  //登录的功能不能拦
						<mvc:exclude-mapping path="/resources/**" />  //所有的静态资源可以不拦
						<bean class="com.aop.SessionInterceptor"  />  //指明拦截器是哪个类
					</mvc:interceptor>
				</mvc:interceptors>
					   
		3) 测试:
		   重新启动项目,输入 http://localhost:8080/aolin-system/user/add  发现无法进入了,程序转到了
		   login.jsp ,证明拦截成功
		   
		   
==== 3 AOP切面
		1) 导入相关 log4j 相关依赖
        <!-- 添加log4j日志 -->
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
        <!-- apache共公包 -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
        
    2) 配置 log4j的配置文件   log4j.properties 放在 src/main/resources/ 下就可以
					# Configure logging for testing: optionally with log file
					log4j.rootLogger=INFO, stdout, logfile
					
					log4j.appender.stdout=org.apache.log4j.ConsoleAppender
					log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
					log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss} %p [%c] %L - %m%n
					
					log4j.appender.logfile=org.apache.log4j.FileAppender
					log4j.appender.logfile.File=d://my.log
					log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
					log4j.appender.logfile.layout.ConversionPattern=%d{HH:mm:ss} %p [%c] %L - %m%n
		
	 3) 切面
					package com.aop;
					import org.apache.log4j.Logger;
					import org.aspectj.lang.ProceedingJoinPoint;
					import org.aspectj.lang.annotation.*;
					import org.springframework.stereotype.Component;
					
					@Aspect @Component
					public class LogAspect {
						Logger log =Logger.getLogger(LogAspect.class);
						
						@Pointcut("execution(* com.controller.*.*(..))")  //定义切入点
						private void anyMethod() {}
						
						@Around("anyMethod()")
						public Object aroundMethod(ProceedingJoinPoint point) {
							Object result=null;
							
							log.trace("前置通知触发了..在这里记录了 trace信息");
							
							String methodName=point.getSignature().getName();
							log.info("当前执行的方法是:"+methodName);
					
							try {
								result=  point.proceed();	
								log.info("方法执行完比,输出的是后置通知");
							}
							catch(Throwable e) {
								e.printStackTrace();
								log.error("方法执行出错,例外通知");
								
							}finally {
								log.info("最终通知触发了");
							}
							
							return result;
						}
					
					}
					
   4)	 在spring-mvc.xml中 
           引入 aop名称空间后,配置
            <aop:aspectj-autoproxy />
            
   5)  运行系统.测试
   
         可以看到,在控制台输出了日志,
         在d盘上的my.log文件中,也存放了日志
         
==== 4 退出注销
  1) framework.jsp上发起请求
   <a href="user/logout" onclick="return confrim('确定要退出吗?')">退出</a>
   
  2)@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//一定要清除用户的session
		session.invalidate();
		return "login";
	}
											
									      
==== 5 其他功能分析
   链接的实体类: 
		public class Link{
		    private Integer linkId;
		
		    //链接地址
		    private String linkUrl;
		
		    //链接名称
		    private String linkName;
		
		    //链接图片
		    private String linkImage;
		
		    //链接描述
		    private String linkDescription;
		
		    //链接所有者昵称
		    private String linkOwnerNickname;
		
		    //联系方式
		    private String linkOwnerContact;
		
		    //最后更新时间
		    private Date linkUpdateTime;
		
		    //创建时间
		    private Date linkCreateTime;
		
		    //链接顺序
		    private Integer linkOrder;
		
		    //链接状态
		    private Integer linkStatus;
		  }
						
	  公告对应的实体类 	
	  public class Notice{
	    private Integer noticeId;
	    private String noticeTitle;
		  private String noticeContent;
	    private Date noticeCreateTime;
	    private Date noticeUpdateTime;
	    private Integer noticeStatus;
	    private Integer noticeOrder;
	  }	
	  
		评论 对应的实体类
	 public class Comment{
			    private Integer commentId;
			
			    private Integer commentPid;
			
			    //评论人名称
			    private String commentPname;
			
			    //被评论的文章id
			    private Integer commentArticleId;
			
			    private String commentAuthorName;
			
			    //评论人邮箱
			    private String commentAuthorEmail;
			
			    //评论人网址
			    private String commentAuthorUrl;
			
			    //类似 http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG 指向一个头象地址
			    private String commentAuthorAvatar;
			
			    //评论内容
			    private String commentContent;
			
			    private String commentAgent;
			
			    //评论人ip
			    private String commentIp;
			
			    //评论时间
			    private Date commentCreateTime;
			 
			    //角色(管理员1，访客0)
			    private Integer commentRole;
			
			    //非数据库字段,表示评论的文章是哪个
			    private Article article;
			 }					
						 
			菜单对应的实体类
			public class Menu  {
			
			    private Integer menuId;
			    private String menuName;
			    private String menuUrl;
			    
			    //这个值是用来区分是顶部菜单(1)还是主要菜单的(2)
			    private Integer menuLevel;
			      
			    private String menuIcon;
			    
			    //菜单的顺序
			    private Integer menuOrder;
			  }
			    
			站点信息
			public class Options {
			    private Integer optionId;
			
			    //站点名称
			    private String optionSiteTitle;
			
			    //站点描述
			    private String optionSiteDescrption;
			
			    //首页描述
			    private String optionMetaDescrption;
			
			    //首页关键词
			    private String optionMetaKeyword;
			
			    //头象图片
			    private byte [] optionAboutsitePhoto;
			
			    //昵称
			    private String optionAboutsiteTitle;
			
			    //说明
			    private String optionAboutsiteContent;
			
			    //微信二维码图片
			    private  byte [] optionAboutsiteWechatphoto;
			
			    //QQ
			    private String optionAboutsiteQq;
			
			    //git地址
			    private String optionAboutsiteGithub;
			
			    //微博
			    private String optionAboutsiteWeibo;
			
			    private String optionTongji;
			
			    private Integer optionStatus;
			  }
						 
						 
==== 6 标签相关的功能   
		标签相关的功能
	   
	   查询列表功能:
		   1) 在 framework.jsp上发起请求
		         <dd><a href="tag">全部标签</a></dd>
		   
		   2) TagController 控制层:
					
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
					
      3) 页面
					<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
					<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
					<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
					
					<rapid:override name="frame-header-style">
					  <style>
					        /*覆盖 layui*/
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
					              <a href="/admin">首页</a>
					              <a><cite>标签列表</cite></a>
					        </span>
					    </blockquote>
					    <div class="layui-row">
					        <div class="layui-col-md4">
					            <form class="layui-form"  method="post" id="myForm" action="/admin/tag/insertSubmit">
					                <div class="layui-form-item">
					                    <div class="layui-input-block">
					                        <strong>添加标签</strong>
					                    </div>
					                    <div class="layui-input-block">
					                        名称 <span style="color: #FF5722; ">*</span>
					                        <input type="text" name="tagName" placeholder="请输入标签名称" autocomplete="off" class="layui-input" required>
					                    </div>
					                    <br>
					                    <div class="layui-input-block">
					                        标签描述
					                        <input type="text" name="tagDescription" placeholder="请输入标签描述" autocomplete="off" class="layui-input" >
					                    </div>
					                    <br>
					                    <div class="layui-input-block">
					                        <button class="layui-btn" lay-filter="formDemo" type="submit">添加</button>
					                    </div>
					                </div>
					            </form>
					            <blockquote class="layui-elem-quote layui-quote-nm">
					                温馨提示：
					                <ul>
					                    <li>1、标签名必选，建议不要太长</li>
					                    <li>2、标签名勿重复</li>
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
					                    <th>名称</th>
					                    <th>文章数</th>
					                    <th>操作</th>
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
						                            <a href="/admin/tag/edit/${t.tagId }" class="layui-btn layui-btn-mini">编辑</a>
						                            </td>
						                        <td >${t.tagId }</td>
						                 </tr>  
					                 </c:forEach>
					
					                </tbody>
					            </table>
					            <blockquote class="layui-elem-quote layui-quote-nm">
					                温馨提示：
					                <ul>
					                    <li>如果该标签包含文章，将不可删除</li>
					                </ul>
					            </blockquote>
					        </div>
					    </div>
					</rapid:override>
					
					<%@ include file="../framework.jsp"  %>
		
		 添加标签功能
		    1) 表单
 					<form class="layui-form"  method="post" id="myForm" action="tag/addOrUpdate">
            	<input type="hidden" value="tagId"  >
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>添加标签</strong>
                    </div>
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="tagName" placeholder="请输入标签名称" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        标签描述
                        <input type="text" name="tagDescription" placeholder="请输入标签描述" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">提交</button>
                    </div>
                </div>
            </form>
            
        2) 控制层:
	         @RequestMapping("/addOrUpdate")
						public String addOrUpdate(Tag tag) {
							//新增
							if(tag.getTagId()==null ||tag.getTagId()==0) {
								tagService.addTag(tag);
							}
							else{
								  //这里是留给修改的
							}
							
							return "forward:/tag";
							
						}
							   
			修改标签功能:
			  1) 从列表上发起请求
			     <a href="tag/edit/${t.tagId }" class="layui-btn layui-btn-mini">编辑</a>
			 
			  2) 控制层
								 		
						//根据id,查出tag信息,转到修改页面
						@RequestMapping("/edit/{tagId}")
						public String editTag(@PathVariable("tagId")Integer tagId, ModelMap m) {
							Tag tag =tagService.getTagById(tagId);
							m.put("tag", tag);
							
							return "forward:/tag";
						}
			  
			  3) 修改页面:
	    			<form class="layui-form"  method="post" id="myForm" action="tag/addOrUpdate">
	            	  <input type="hidden" name="tagId"  value="${tag.tagId }" >  //一定要有这个
	                <div class="layui-form-item">
	                    <div class="layui-input-block">
	                        <strong>添加标签</strong>
	                    </div>
	                    <div class="layui-input-block">
	                        名称 <span style="color: #FF5722; ">*</span>
	                        <input type="text" name="tagName" value="${tag.tagName }" placeholder="请输入标签名称" autocomplete="off" class="layui-input" required>
	                    </div>
	                    <br>
	                    <div class="layui-input-block">
	                        标签描述
	                        <input type="text" name="tagDescription" value="${tag.tagDescription }" placeholder="请输入标签描述" autocomplete="off" class="layui-input" >
	                    </div>
	                    <br>
	                    <div class="layui-input-block">
	                        <button class="layui-btn" lay-filter="formDemo" type="submit">提交</button>
	                    </div>
	                </div>
	            </form>
	            
	       4) 提交表单,控制层
						@RequestMapping("/addOrUpdate")
							public String addOrUpdate(Tag tag) {
								 //新增
								if(tag.getTagId()==null ||tag.getTagId()==0) {
									tagService.addTag(tag);
								}
								else {//修改
									tagService.updateTag(tag);
								}
								
								return "forward:/tag";
								
							}
							
				 5) 所有的显务层,Mapper,映射文件,略