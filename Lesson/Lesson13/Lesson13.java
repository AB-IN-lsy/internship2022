======================================Lesson13=============================================
1 模板页的规划
2 文章列表数据
3 把文章列表数据,呈现在页面中 
4 评论信息
===========================================================================================
==== 1 模板页的规划
		1) 引入 rapid 支持jsp模板的继承
	       <dependency>
	          <groupId>com.googlecode.rapid-framework</groupId>
	          <artifactId>rapid-core</artifactId>
	          <version>4.0.5</version>
	       </dependency>
	       
	  2) 设计通用模板页
					<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
					<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
					<%
					String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					%>
					
					<!DOCTYPE html>
					<html lang="zh-CN">
					<head>
						<base href="<%=basePath%>"  >
					    <meta charset="utf-8">
					    <link rel="shortcut icon" href="resources/img/logo.png">
					    <title>信息系统台模板 </title>       
					           
					    <link rel="stylesheet" href="resources/plugin/layui/css/layui.css">
					    <link rel="stylesheet" href="resources/css/back.css">
					    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
					    
					    <!-- 留给别的页面去覆盖.让它们可以引入自已的css或 js -->
					    <rapid:block name="frame-header-style"></rapid:block>
					    <rapid:block name="frame-header-script"></rapid:block>
					
					</head>
					<body>
					<div class="layui-layout layui-layout-admin">
					    <div class="layui-header">
					        <div class="layui-logo"><a href="/admin" style="color:#009688;">
					          博客后台
					        </a>
					        </div>
					        <!-- 头部区域（可配合layui已有的水平导航） -->
					        <ul class="layui-nav layui-layout-left">
					            <li class="layui-nav-item"><a href="/" target="_blank">前台</a></li>
					            <li class="layui-nav-item">
					                <a href="javascript:;">新建</a>
					                <dl class="layui-nav-child">
					                    <dd><a href="/admin/article/insert">文章</a></dd>
					                    <dd><a href="/admin/page/insert">页面</a></dd>
					                    <dd><a href="/admin/category/insert">分类</a></dd>
					                    <dd><a href="/admin/notice/insert">公告</a></dd>
					                    <dd><a href="/admin/link/insert">链接</a></dd>
					                </dl>
					            </li>
					        </ul>
					        <ul class="layui-nav layui-layout-right">
					            <li class="layui-nav-item">
					                <a href="javascript:;">
					                    <img src="resources/uploads/2017/10/20171006225356181.jpg" class="layui-nav-img">
					                    admin</a>
					                <dl class="layui-nav-child">
					                    <dd><a href="/admin/user/profile">基本资料</a></dd>
					                </dl>
					            </li>
					            <li class="layui-nav-item">
					                <a href="/admin/logout">退出</a>
					            </li>
					        </ul>
					    </div>
					
					    <div class="layui-side layui-bg-black">
					        <div class="layui-side-scroll">
					            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
					            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
					                <li class="layui-nav-item layui-nav-itemed">
					                    <a class="" href="javascript:;">文章</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/article">全部文章</a></dd>
					                        <dd><a href="/admin/article/insert">写文章</a></dd>
					                        <dd><a href="/admin/category">全部分类</a></dd>
					                        <dd><a href="/admin/tag">全部标签</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="javascript:;">页面</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/page">全部页面</a></dd>
					                        <dd><a href="/admin/page/insert">添加页面</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a class="" href="javascript:;">
					                        链接
					                    </a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/link">全部链接</a></dd>
					                        <dd><a href="/admin/link/insert">添加链接</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="javascript:;">公告</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/notice">全部公告</a></dd>
					                        <dd><a href="/admin/notice/insert">添加公告</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="/admin/comment">
					                        评论
					                    </a>
					                </li>
					                <li class="layui-nav-item">
					                    <a class="" href="javascript:;">
					                        用户
					                    </a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/user">全部用户</a></dd>
					                        <dd><a href="/admin/user/insert">添加用户</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="javascript:;">设置</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/menu">菜单</a></dd>
					                        <dd><a href="/admin/options">主要选项</a></dd>
					                    </dl>
					                </li>
					            </ul>
					        </div>
					    </div>
					
					    <div class="layui-body">
								<div style="padding:15px">
									<rapid:block name="frame-content">
										这是主体区内容,每个页面这里都各不相同
									</rapid:block>
								</div>
					    </div>
					
					    <div class="layui-footer">
					        <!-- 底部固定区域 -->
					         ? 这里展示一些提示性信息 <a href="#" target="_blank">其他导航</a> / <a href="#" target="_blank">其他导航</a>
					    </div>
					</div>
					
					<script src="resources/js/jquery.min.js"></script>
					<script src="resources/plugin/layui/layui.all.js"></script>
					<script src="resources/js/back.js"></script>
					
					<!-- 留给别的页面覆盖,让它们可以引入自已的js -->
					<rapid:block name="frame-footer-script"></rapid:block>
					
					</body>
					</html>
   
    3) 把原来的index.jsp,改成从模板页继承,并覆盖相应的块
				<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
				
				<rapid:override name="frame-header-style">
					<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
				</rapid:override>
				
				<rapid:override name="frame-content">
					  这是index.jsp中要重写的内容,文章列表,评论列表,草稿功能...
				</rapid:override>
				
				<%@ include file="framework.jsp"  %>

    4) 把index.jsp中应该重写的静态内容整理过来
				<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
				
				<rapid:override name="frame-header-style">
					<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
				</rapid:override>
				
				<rapid:override name="frame-content">
						<!-- 下面这个div是重写的内容 -->
					<div class="layui-container">
						<div class="layui-row">
							<div class="layui-col-md6">
								<div id="dashboard_activity" class="postbox ">
									<div class="inside">
										<div id="activity-widget">
											<div id="published-posts" class="activity-block">
												<h3>最近发布</h3>
												<br>
												<ul>
													<li><span>21:06 11月25日</span> <a href="/article/33"
														target="_blank">MySQL常用命令语句</a></li>
													<li><span>21:05 11月25日</span> <a href="/article/32"
														target="_blank">Docker_入门？只要这篇就够了！（纯干货适合0基础小白）</a></li>
													<li><span>21:02 11月25日</span> <a href="/article/31"
														target="_blank">RocketMQ 实战之快速入门</a></li>
													<li><span>21:01 11月25日</span> <a href="/article/30"
														target="_blank">SpringBoot + mongodb 整合,
															记录网站操作日志，常用查询操作</a></li>
													<li><span>21:00 11月25日</span> <a href="/article/29"
														target="_blank">IDEA启动EDAS项目</a></li>
												</ul>
											</div>
											<br>
											<div id="latest-comments" class="activity-block">
												<h3>近期评论</h3>
												<ul id="the-comment-list" data-wp-lists="list:comment">
													<li class="comment   thread-even comment-item approved">
				
														<img alt=""
														src="http://cn.gravatar.com/avatar/88d5cb704d88bdad67d000eee4782000?s=128&d=identicon&r=PG"
														class="avatar avatar-50 photo" height="50" width="50">
														<div class="dashboard-comment-wrap has-row-actions">
															<p class="comment-meta">
																由<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">1111</a>
																</cite>发表在 《<a href="/article/6">Hibernate 简单的CURD操作</a>》
															</p>
				
															<blockquote>
																<p>1</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/29"> 回复 </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/29">编辑</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(29)">删除</a>
																</span>
															</p>
														</div>
													</li>
													<li class="comment   thread-even comment-item approved">
				
														<img alt=""
														src="http://cn.gravatar.com/avatar/487f87505f619bf9ea08f26bb34f8118?s=128&d=identicon&r=PG"
														class="avatar avatar-50 photo" height="50" width="50">
														<div class="dashboard-comment-wrap has-row-actions">
															<p class="comment-meta">
																由<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">你好</a>
																</cite>发表在 《<a href="/article/6">Hibernate 简单的CURD操作</a>》
															</p>
				
															<blockquote>
																<p>ssss</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/22"> 回复 </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/22">编辑</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(22)">删除</a>
																</span>
															</p>
														</div>
													</li>
													<li class="comment   thread-even comment-item approved">
				
														<img alt=""
														src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
														class="avatar avatar-50 photo" height="50" width="50">
														<div class="dashboard-comment-wrap has-row-actions">
															<p class="comment-meta">
																由<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">saysky3</a>
																</cite>发表在 《<a href="/article/2">springmvc 表单中文乱码解决方案</a>》
															</p>
				
															<blockquote>
																<p>33333</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/20"> 回复 </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/20">编辑</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(20)">删除</a>
																</span>
															</p>
														</div>
													</li>
													<li class="comment   thread-even comment-item approved">
				
														<img alt=""
														src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
														class="avatar avatar-50 photo" height="50" width="50">
														<div class="dashboard-comment-wrap has-row-actions">
															<p class="comment-meta">
																由<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">saysky2</a>
																</cite>发表在 《<a href="/article/2">springmvc 表单中文乱码解决方案</a>》
															</p>
				
															<blockquote>
																<p>sssssss</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/19"> 回复 </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/19">编辑</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(19)">删除</a>
																</span>
															</p>
														</div>
													</li>
													<li class="comment   thread-even comment-item approved">
				
														<img alt=""
														src="http://cn.gravatar.com/avatar/3ae8728fec3cd5cbfe99c4b966695f03?s=128&d=identicon&r=PG"
														class="avatar avatar-50 photo" height="50" width="50">
														<div class="dashboard-comment-wrap has-row-actions">
															<p class="comment-meta">
																由<cite class="comment-author"> <a target="_blank"
																	href="http://liuyanzhao.com" rel="external nofollow"
																	class="url">saysky</a>
																</cite>发表在 《<a href="/article/2">springmvc 表单中文乱码解决方案</a>》
															</p>
				
															<blockquote>
																<p>ssssss</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/18"> 回复 </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/18">编辑</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(18)">删除</a>
																</span>
															</p>
														</div>
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="layui-col-md6">
								<div id="dashboard_quick_press" class="postbox ">
									<div class="inside">
										<form name="post" method="post" id="insertDraftForm"
											class="initial-form hide-if-no-js"
											action="/admin/article/insertDraftSubmit">
				
											<div class="layui-form-item">
												<div class="layui-input-block">
													<input type="text" name="articleTitle" id="articleTitle"
														required lay-verify="required" placeholder="请输入标题"
														autocomplete="off" class="layui-input">
												</div>
											</div>
											<div class="layui-form-item layui-form-text">
												<div class="layui-input-block">
													<textarea name="articleContent" placeholder="请输入内容"
														id="articleContent" class="layui-textarea" required></textarea>
												</div>
											</div>
											<input type="hidden" name="articleStatus" value="0">
											<div class="layui-form-item">
												<div class="layui-input-block">
													<button class="layui-btn layui-btn-small" lay-submit
														lay-filter="formDemo" onclick="insertDraft()">保存草稿</button>
													<button type="reset"
														class="layui-btn layui-btn-small layui-btn-primary">重置</button>
												</div>
											</div>
				
										</form>
										<div class="drafts">
											<p class="view-all">
												<a href="/admin/article" aria-label="查看所有草稿">查看所有</a>
											</p>
											<h2 class="hide-if-no-js">草稿</h2>
											<ul>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</rapid:override>
				
				<%@ include file="framework.jsp"  %>
				
==== 2 文章列表数据
		1) 文章(信息) 对应的实体类
     public class Article {
		   
		    private Integer articleId;
		
		    //创建文章的用户
		    private Integer articleUserId;
		
		    //文章标题
		    private String articleTitle;
		
		    //浏览次数
		    private Integer articleViewCount;
		
		    //回复次数
		    private Integer articleCommentCount;
		
		    //喜欢次数
		    private Integer articleLikeCount;
		
		    //创建时间
		    private Date articleCreateTime;
		
		    //更新时间
		    private Date articleUpdateTime;
		
		    //是否回复
		    private Integer articleIsComment;
		
		    //状态
		    private Integer articleStatus;
		
		    //排序
		    private Integer articleOrder;
		
		    //文章内容
		    private String articleContent;
		
		    //言章概要
		    private String articleSummary;
		
		    //用户
		    private User user;
		
		    //文章挂有哪些标签
		    //private List<Tag> tagList;
		
		    //文章属于哪些分类
		    //private List<Category> categoryList;
		    
		    ...生成get set 方法
		
		  }
		 
		 2) ArticleMapper 接口   //注意,放在 com.mapper包下
					package com.mapper;
					import java.util.List;
					import com.entity.Article;
					
					public interface ArticleMapper {
						/**
						 * 查询最近发表的前n条文章
						 * @param n 查询出多少条
						 * @return 文章列表
						 */
						List<Article> listRecentArticle(Integer n);
					
					}

    3)  ArticleMapper.xml  //放在 src/main/resources/mybatis/mappings 下
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="com.mapper.ArticleMapper">
					<sql id="cols">
					    article_id, article_user_id, article_title, article_content,article_summary, article_view_count, article_comment_count, 
				   		article_like_count, article_create_time,article_update_time, article_is_comment, article_status, article_order			   
					</sql>
					
					<!-- 查询最新发布的前n条文章, 注意.草稿不要查出来  article_status=0 代表它是草稿-->
					<select id="listRecentArticle"   resultType="Article">
					    select  <include refid="cols" /> from article  where article_status!=0 order by article_id desc limit #{n} 
					</select>
				</mapper>
				
		4) 业务层接口和实现类
		     ArticleService
						package com.service;
						import java.util.List;
						import com.entity.Article;
						
						public interface ArticleService {
							/**
							 * 查询最近发表的前n条文章
							 * @param n 查询出多少条
							 * @return 文章列表
							 */
							List<Article> listRecentArticle(Integer n);
						}

		     
		     ArticleServiceImpl
						package com.service.impl;
						import java.util.List;
						import javax.annotation.Resource;
						import org.springframework.stereotype.Service;
						import com.entity.Article;
						import com.mapper.ArticleMapper;
						import com.service.ArticleService;
						
						@Service
						public class ArticleSericeImpl implements ArticleService {
							@Resource
							private ArticleMapper articleMapper;
						
							public List<Article> listRecentArticle(Integer n) {
								return articleMapper.listRecentArticle(n);
							}
						}

    5) 控制层:
				package com.controller;
				import java.util.List;
				import javax.annotation.Resource;
				import javax.servlet.http.HttpServletRequest;
				import org.springframework.stereotype.Controller;
				import org.springframework.web.bind.annotation.RequestMapping;
				import com.entity.*;
				import com.service.*;
				@Controller @RequestMapping("/user")
				public class UserController {
					@Resource
					UserService  userService;
					
					@Resource
					ArticleService articleService;
					
					@RequestMapping("/login")
					public String login(HttpServletRequest request) {
						String userNameOrEmail=request.getParameter("userName");
						String userPass=request.getParameter("userPass");
						String rememberMe=request.getParameter("rememberMe");
						
						User user=userService.loginByNameOrEmail(userNameOrEmail);
						if(user==null) {
							request.setAttribute("msg", "用户名错误");
							return "login";
						}
						else if(!user.getUserPass().equals(userPass)) {
							request.setAttribute("msg", "密码错误");
							return "login";
						}
						else {
							request.getSession().setAttribute("session_user", user);

							//把文章列表数据查出来,带到index页
							List<Article> artileList=articleService.listRecentArticle(5);
							request.setAttribute("artileList", artileList);
							
							//把评论列表数据查出来,带到inexx页 欠着
							
							return  "index";    
						}
					}
				}
				
==== 3 把文章列表数据,呈现在页面中 
				 在index.jsp中 
				 
				<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   //用于日期格式化
				
				
				把静态的文章列表内容,换成下面的:
				<ul>
					<c:forEach var="a" items="${artileList}">
						<li>
						   <span>
						       <fmt:formatDate value="${a.articleCreateTime }" pattern="yyyy/MM/dd HH:mm" />
						   </span> 
						   <a href="/article/${a.articleId }"	target="_blank">${a.articleTitle }</a>
						 </li>
					 </c:forEach>
				</ul>
				
==== 4 评论信息
		1) 实体类
					public class Comment {			
					  //自增id
				    private Integer commentId;
						
						//评论的父级ID( 或评论人的ID? )
				    private Integer commentPid;
				
						//评论人的名称
				    private String commentPname;
				
				    //被评论的文章的ID
				    private Integer commentArticleId;
				
						//评论者名称
				    private String commentAuthorName;
				
						//评论者邮箱
				    private String commentAuthorEmail;
				
				    //评论者的网址
				    private String commentAuthorUrl;
				
						//评论者的头象地址 
				    private String commentAuthorAvatar;
				
					  //评论的内容
				    private String commentContent;
				
				    private String commentAgent;
				
						//评论人的IP
				    private String commentIp;
				
						//论评创建的时间
				    private Date commentCreateTime;
				
						//论评人的角色(管理员 1, 访客 0)
				    private Integer commentRole;
				
						//被评论的文章是哪个 非数据库字段
				    private Article article;	
					}
		
	  2) 控制层
					@Controller @RequestMapping("/user")
					public class UserController {
						@Resource
						UserService  userService;
						
						@Resource
						ArticleService articleService;
						
						@Resource
						CommentService CommentService;
						
						
						@RequestMapping("/login")
						public String login(HttpServletRequest request) {
							  ...
								
								//把文章列表数据查出来,带到index页
								List<Article> artileList=articleService.listRecentArticle(5);
								request.setAttribute("artileList", artileList);
								
								//把评论列表数据查出来,带到inex页
								List<Comment> commentList=CommentService.listRecentComment(n);
								request.setAttribute("commentList", commentList);
								
								return  "index";    //src/main/webapp/view/index.jsp
							}
						}
					}
    
    3) 业务层接口 和 实现类
				package com.service;
				import java.util.List;
				import com.entity.Comment;
				
				public interface CommentService {
					List<Comment> listRecentComment(int i);
				}

				package com.service.impl;
				import java.util.List;
				import javax.annotation.Resource;
				import org.springframework.stereotype.Service;
				import com.entity.Comment;
				import com.mapper.CommentMapper;
				import com.service.CommentService;
				
				@Service
				public class CommentServiceImpl implements CommentService{
					@Resource
					private CommentMapper commentMapper;
				
					public List<Comment> listRecentComment(int n) {
						return commentMapper.listRecentComment(n);
					}
				
				}

    4) CommentMapper接口 
				package com.mapper;
				import java.util.List;
				import com.entity.Comment;
				
				public interface CommentMapper {
					List<Comment> listRecentComment(int n);
				}
		
		5) CommentMapper.xml
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="com.mapper.CommentMapper">
					<sql id="cols">
					     comment_id, comment_pid, comment_pname, comment_article_id, comment_author_name,
					     comment_author_email, comment_author_url, comment_author_avatar, comment_content, comment_agent,
					     comment_ip,comment_create_time, comment_role		   
					</sql>
					
					<!-- 查询最新发布的前n条评论 -->
					<select id="listRecentComment"   resultType="Comment">
					    select  <include refid="cols" /> from comment order by comment_id desc limit #{n} 
					</select>
				</mapper>
				
		6) index.jsp 上显示出数据
					<c:forEach var ="c" items="${commentList}">
						<li class="comment   thread-even comment-item approved">

							<img  src="${c.commentAuthorAvatar }"
							class="avatar avatar-50 photo" height="50" width="50">
							<div class="dashboard-comment-wrap has-row-actions">
								<p class="comment-meta">
									由<cite class="comment-author"> <a target="_blank"	href="" rel="external nofollow" class="url">${commentAuthorName }</a>
									</cite>发表在 《<a href="/article/${c.commentArticleId }">被评论的文章的标题,将来处理</a>》
								</p>

								<blockquote>
									<p>${c.commentContent }</p>
								</blockquote>
								<p class="row-actions">
									| <span class=""> <a data-comment-id="1268"
										href="/admin/comment/reply/${c.commentId }"> 回复 </a>
									</span> <span class=""> | <a
										href="/admin/comment/edit/${c.commentId }">编辑</a>
									</span> <span class=""> | <a href="javascript:void(0)"
										onclick="deleteComment(${c.commentId })">删除</a>
									</span>
								</p>
							</div>
						</li>
				   </c:forEach>
				   
		7) 把每条评论对应的文章的标题显示出来
		
			   在CommentServiceImpl类中,把文章信息查出来
				
				@Service
				public class CommentServiceImpl implements CommentService{
					@Resource
					private CommentMapper commentMapper;
					
					@Resource
					private ArticleMapper artileMapper;
				
					/**
					 * 查询最近的n条评论信息,每条评论信息中,都含有被评论的文章的信息
					 */
					public List<Comment> listRecentComment(int n) {
						
						List<Comment> commentList= commentMapper.listRecentComment(n);
						
						for(int i=0;i<commentList.size();i++) {
							
							//得到被评论的文章的id
							int commetArticleId=commentList.get(i).getCommentArticleId();
							
							//根据文章id得到文章信息
							Article article=artileMapper.getArticleById(commetArticleId);
							
							//把文章信息传给评论
							commentList.get(i).setArticle(article);
							
						}
						
						return commentList;
					}
			}
			
			ArticleMapper 接口中也要声明这个 getArticleById() 方法 
		
				public interface ArticleMapper {
					/**
					 * 查询最近发表的前n条文章
					 * @param n 查询出多少条
					 * @return 文章列表
					 */
					List<Article> listRecentArticle(Integer n);
				
					Article getArticleById(int commetArticleId);
				
				}
				
		 ArticleMapper.xml中也要进行实现 
				
				<!-- 根据文章id,查询文章信息 -->
				<select id="getArticleById"    resultType="Article">
					select  <include refid="cols" /> from article  where article_id=#{id}
				</select>
							
							
		  index.jsp 上显示出评论对应的文章标题:
		  
		   ...
		   	</cite>发表在 《<a href="/article/${c.commentArticleId }">${c.article.articleTitle}</a>》
		   ...
			





