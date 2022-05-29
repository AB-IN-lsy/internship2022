======================================Lesson13=============================================
1 ģ��ҳ�Ĺ滮
2 �����б�����
3 �������б�����,������ҳ���� 
4 ������Ϣ
===========================================================================================
==== 1 ģ��ҳ�Ĺ滮
		1) ���� rapid ֧��jspģ��ļ̳�
	       <dependency>
	          <groupId>com.googlecode.rapid-framework</groupId>
	          <artifactId>rapid-core</artifactId>
	          <version>4.0.5</version>
	       </dependency>
	       
	  2) ���ͨ��ģ��ҳ
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
					    <title>��Ϣϵͳ̨ģ�� </title>       
					           
					    <link rel="stylesheet" href="resources/plugin/layui/css/layui.css">
					    <link rel="stylesheet" href="resources/css/back.css">
					    <link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.min.css">
					    
					    <!-- �������ҳ��ȥ����.�����ǿ����������ѵ�css�� js -->
					    <rapid:block name="frame-header-style"></rapid:block>
					    <rapid:block name="frame-header-script"></rapid:block>
					
					</head>
					<body>
					<div class="layui-layout layui-layout-admin">
					    <div class="layui-header">
					        <div class="layui-logo"><a href="/admin" style="color:#009688;">
					          ���ͺ�̨
					        </a>
					        </div>
					        <!-- ͷ�����򣨿����layui���е�ˮƽ������ -->
					        <ul class="layui-nav layui-layout-left">
					            <li class="layui-nav-item"><a href="/" target="_blank">ǰ̨</a></li>
					            <li class="layui-nav-item">
					                <a href="javascript:;">�½�</a>
					                <dl class="layui-nav-child">
					                    <dd><a href="/admin/article/insert">����</a></dd>
					                    <dd><a href="/admin/page/insert">ҳ��</a></dd>
					                    <dd><a href="/admin/category/insert">����</a></dd>
					                    <dd><a href="/admin/notice/insert">����</a></dd>
					                    <dd><a href="/admin/link/insert">����</a></dd>
					                </dl>
					            </li>
					        </ul>
					        <ul class="layui-nav layui-layout-right">
					            <li class="layui-nav-item">
					                <a href="javascript:;">
					                    <img src="resources/uploads/2017/10/20171006225356181.jpg" class="layui-nav-img">
					                    admin</a>
					                <dl class="layui-nav-child">
					                    <dd><a href="/admin/user/profile">��������</a></dd>
					                </dl>
					            </li>
					            <li class="layui-nav-item">
					                <a href="/admin/logout">�˳�</a>
					            </li>
					        </ul>
					    </div>
					
					    <div class="layui-side layui-bg-black">
					        <div class="layui-side-scroll">
					            <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
					            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
					                <li class="layui-nav-item layui-nav-itemed">
					                    <a class="" href="javascript:;">����</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/article">ȫ������</a></dd>
					                        <dd><a href="/admin/article/insert">д����</a></dd>
					                        <dd><a href="/admin/category">ȫ������</a></dd>
					                        <dd><a href="/admin/tag">ȫ����ǩ</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="javascript:;">ҳ��</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/page">ȫ��ҳ��</a></dd>
					                        <dd><a href="/admin/page/insert">���ҳ��</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a class="" href="javascript:;">
					                        ����
					                    </a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/link">ȫ������</a></dd>
					                        <dd><a href="/admin/link/insert">�������</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="javascript:;">����</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/notice">ȫ������</a></dd>
					                        <dd><a href="/admin/notice/insert">��ӹ���</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="/admin/comment">
					                        ����
					                    </a>
					                </li>
					                <li class="layui-nav-item">
					                    <a class="" href="javascript:;">
					                        �û�
					                    </a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/user">ȫ���û�</a></dd>
					                        <dd><a href="/admin/user/insert">����û�</a></dd>
					                    </dl>
					                </li>
					                <li class="layui-nav-item">
					                    <a href="javascript:;">����</a>
					                    <dl class="layui-nav-child">
					                        <dd><a href="/admin/menu">�˵�</a></dd>
					                        <dd><a href="/admin/options">��Ҫѡ��</a></dd>
					                    </dl>
					                </li>
					            </ul>
					        </div>
					    </div>
					
					    <div class="layui-body">
								<div style="padding:15px">
									<rapid:block name="frame-content">
										��������������,ÿ��ҳ�����ﶼ������ͬ
									</rapid:block>
								</div>
					    </div>
					
					    <div class="layui-footer">
					        <!-- �ײ��̶����� -->
					         ? ����չʾһЩ��ʾ����Ϣ <a href="#" target="_blank">��������</a> / <a href="#" target="_blank">��������</a>
					    </div>
					</div>
					
					<script src="resources/js/jquery.min.js"></script>
					<script src="resources/plugin/layui/layui.all.js"></script>
					<script src="resources/js/back.js"></script>
					
					<!-- �������ҳ�渲��,�����ǿ����������ѵ�js -->
					<rapid:block name="frame-footer-script"></rapid:block>
					
					</body>
					</html>
   
    3) ��ԭ����index.jsp,�ĳɴ�ģ��ҳ�̳�,��������Ӧ�Ŀ�
				<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
				
				<rapid:override name="frame-header-style">
					<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
				</rapid:override>
				
				<rapid:override name="frame-content">
					  ����index.jsp��Ҫ��д������,�����б�,�����б�,�ݸ幦��...
				</rapid:override>
				
				<%@ include file="framework.jsp"  %>

    4) ��index.jsp��Ӧ����д�ľ�̬�����������
				<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<%@ taglib uri="http://www.rapid-framework.org.cn/rapid"  prefix="rapid" %>
				
				<rapid:override name="frame-header-style">
					<link rel="stylesheet" type="text/css" href="resources/css/index-page.css" ></link>
				</rapid:override>
				
				<rapid:override name="frame-content">
						<!-- �������div����д������ -->
					<div class="layui-container">
						<div class="layui-row">
							<div class="layui-col-md6">
								<div id="dashboard_activity" class="postbox ">
									<div class="inside">
										<div id="activity-widget">
											<div id="published-posts" class="activity-block">
												<h3>�������</h3>
												<br>
												<ul>
													<li><span>21:06 11��25��</span> <a href="/article/33"
														target="_blank">MySQL�����������</a></li>
													<li><span>21:05 11��25��</span> <a href="/article/32"
														target="_blank">Docker_���ţ�ֻҪ��ƪ�͹��ˣ������ɻ��ʺ�0����С�ף�</a></li>
													<li><span>21:02 11��25��</span> <a href="/article/31"
														target="_blank">RocketMQ ʵս֮��������</a></li>
													<li><span>21:01 11��25��</span> <a href="/article/30"
														target="_blank">SpringBoot + mongodb ����,
															��¼��վ������־�����ò�ѯ����</a></li>
													<li><span>21:00 11��25��</span> <a href="/article/29"
														target="_blank">IDEA����EDAS��Ŀ</a></li>
												</ul>
											</div>
											<br>
											<div id="latest-comments" class="activity-block">
												<h3>��������</h3>
												<ul id="the-comment-list" data-wp-lists="list:comment">
													<li class="comment   thread-even comment-item approved">
				
														<img alt=""
														src="http://cn.gravatar.com/avatar/88d5cb704d88bdad67d000eee4782000?s=128&d=identicon&r=PG"
														class="avatar avatar-50 photo" height="50" width="50">
														<div class="dashboard-comment-wrap has-row-actions">
															<p class="comment-meta">
																��<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">1111</a>
																</cite>������ ��<a href="/article/6">Hibernate �򵥵�CURD����</a>��
															</p>
				
															<blockquote>
																<p>1</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/29"> �ظ� </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/29">�༭</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(29)">ɾ��</a>
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
																��<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">���</a>
																</cite>������ ��<a href="/article/6">Hibernate �򵥵�CURD����</a>��
															</p>
				
															<blockquote>
																<p>ssss</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/22"> �ظ� </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/22">�༭</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(22)">ɾ��</a>
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
																��<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">saysky3</a>
																</cite>������ ��<a href="/article/2">springmvc ����������������</a>��
															</p>
				
															<blockquote>
																<p>33333</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/20"> �ظ� </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/20">�༭</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(20)">ɾ��</a>
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
																��<cite class="comment-author"> <a target="_blank"
																	href="" rel="external nofollow" class="url">saysky2</a>
																</cite>������ ��<a href="/article/2">springmvc ����������������</a>��
															</p>
				
															<blockquote>
																<p>sssssss</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/19"> �ظ� </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/19">�༭</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(19)">ɾ��</a>
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
																��<cite class="comment-author"> <a target="_blank"
																	href="http://liuyanzhao.com" rel="external nofollow"
																	class="url">saysky</a>
																</cite>������ ��<a href="/article/2">springmvc ����������������</a>��
															</p>
				
															<blockquote>
																<p>ssssss</p>
															</blockquote>
															<p class="row-actions">
																| <span class=""> <a data-comment-id="1268"
																	href="/admin/comment/reply/18"> �ظ� </a>
																</span> <span class=""> | <a
																	href="/admin/comment/edit/18">�༭</a>
																</span> <span class=""> | <a href="javascript:void(0)"
																	onclick="deleteComment(18)">ɾ��</a>
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
														required lay-verify="required" placeholder="���������"
														autocomplete="off" class="layui-input">
												</div>
											</div>
											<div class="layui-form-item layui-form-text">
												<div class="layui-input-block">
													<textarea name="articleContent" placeholder="����������"
														id="articleContent" class="layui-textarea" required></textarea>
												</div>
											</div>
											<input type="hidden" name="articleStatus" value="0">
											<div class="layui-form-item">
												<div class="layui-input-block">
													<button class="layui-btn layui-btn-small" lay-submit
														lay-filter="formDemo" onclick="insertDraft()">����ݸ�</button>
													<button type="reset"
														class="layui-btn layui-btn-small layui-btn-primary">����</button>
												</div>
											</div>
				
										</form>
										<div class="drafts">
											<p class="view-all">
												<a href="/admin/article" aria-label="�鿴���вݸ�">�鿴����</a>
											</p>
											<h2 class="hide-if-no-js">�ݸ�</h2>
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
				
==== 2 �����б�����
		1) ����(��Ϣ) ��Ӧ��ʵ����
     public class Article {
		   
		    private Integer articleId;
		
		    //�������µ��û�
		    private Integer articleUserId;
		
		    //���±���
		    private String articleTitle;
		
		    //�������
		    private Integer articleViewCount;
		
		    //�ظ�����
		    private Integer articleCommentCount;
		
		    //ϲ������
		    private Integer articleLikeCount;
		
		    //����ʱ��
		    private Date articleCreateTime;
		
		    //����ʱ��
		    private Date articleUpdateTime;
		
		    //�Ƿ�ظ�
		    private Integer articleIsComment;
		
		    //״̬
		    private Integer articleStatus;
		
		    //����
		    private Integer articleOrder;
		
		    //��������
		    private String articleContent;
		
		    //���¸�Ҫ
		    private String articleSummary;
		
		    //�û�
		    private User user;
		
		    //���¹�����Щ��ǩ
		    //private List<Tag> tagList;
		
		    //����������Щ����
		    //private List<Category> categoryList;
		    
		    ...����get set ����
		
		  }
		 
		 2) ArticleMapper �ӿ�   //ע��,���� com.mapper����
					package com.mapper;
					import java.util.List;
					import com.entity.Article;
					
					public interface ArticleMapper {
						/**
						 * ��ѯ��������ǰn������
						 * @param n ��ѯ��������
						 * @return �����б�
						 */
						List<Article> listRecentArticle(Integer n);
					
					}

    3)  ArticleMapper.xml  //���� src/main/resources/mybatis/mappings ��
				<?xml version="1.0" encoding="UTF-8" ?>  
				<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
				
				<mapper namespace="com.mapper.ArticleMapper">
					<sql id="cols">
					    article_id, article_user_id, article_title, article_content,article_summary, article_view_count, article_comment_count, 
				   		article_like_count, article_create_time,article_update_time, article_is_comment, article_status, article_order			   
					</sql>
					
					<!-- ��ѯ���·�����ǰn������, ע��.�ݸ岻Ҫ�����  article_status=0 �������ǲݸ�-->
					<select id="listRecentArticle"   resultType="Article">
					    select  <include refid="cols" /> from article  where article_status!=0 order by article_id desc limit #{n} 
					</select>
				</mapper>
				
		4) ҵ���ӿں�ʵ����
		     ArticleService
						package com.service;
						import java.util.List;
						import com.entity.Article;
						
						public interface ArticleService {
							/**
							 * ��ѯ��������ǰn������
							 * @param n ��ѯ��������
							 * @return �����б�
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

    5) ���Ʋ�:
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
							request.setAttribute("msg", "�û�������");
							return "login";
						}
						else if(!user.getUserPass().equals(userPass)) {
							request.setAttribute("msg", "�������");
							return "login";
						}
						else {
							request.getSession().setAttribute("session_user", user);

							//�������б����ݲ����,����indexҳ
							List<Article> artileList=articleService.listRecentArticle(5);
							request.setAttribute("artileList", artileList);
							
							//�������б����ݲ����,����inexxҳ Ƿ��
							
							return  "index";    
						}
					}
				}
				
==== 3 �������б�����,������ҳ���� 
				 ��index.jsp�� 
				 
				<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   //�������ڸ�ʽ��
				
				
				�Ѿ�̬�������б�����,���������:
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
				
==== 4 ������Ϣ
		1) ʵ����
					public class Comment {			
					  //����id
				    private Integer commentId;
						
						//���۵ĸ���ID( �������˵�ID? )
				    private Integer commentPid;
				
						//�����˵�����
				    private String commentPname;
				
				    //�����۵����µ�ID
				    private Integer commentArticleId;
				
						//����������
				    private String commentAuthorName;
				
						//����������
				    private String commentAuthorEmail;
				
				    //�����ߵ���ַ
				    private String commentAuthorUrl;
				
						//�����ߵ�ͷ���ַ 
				    private String commentAuthorAvatar;
				
					  //���۵�����
				    private String commentContent;
				
				    private String commentAgent;
				
						//�����˵�IP
				    private String commentIp;
				
						//����������ʱ��
				    private Date commentCreateTime;
				
						//�����˵Ľ�ɫ(����Ա 1, �ÿ� 0)
				    private Integer commentRole;
				
						//�����۵��������ĸ� �����ݿ��ֶ�
				    private Article article;	
					}
		
	  2) ���Ʋ�
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
								
								//�������б����ݲ����,����indexҳ
								List<Article> artileList=articleService.listRecentArticle(5);
								request.setAttribute("artileList", artileList);
								
								//�������б����ݲ����,����inexҳ
								List<Comment> commentList=CommentService.listRecentComment(n);
								request.setAttribute("commentList", commentList);
								
								return  "index";    //src/main/webapp/view/index.jsp
							}
						}
					}
    
    3) ҵ���ӿ� �� ʵ����
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

    4) CommentMapper�ӿ� 
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
					
					<!-- ��ѯ���·�����ǰn������ -->
					<select id="listRecentComment"   resultType="Comment">
					    select  <include refid="cols" /> from comment order by comment_id desc limit #{n} 
					</select>
				</mapper>
				
		6) index.jsp ����ʾ������
					<c:forEach var ="c" items="${commentList}">
						<li class="comment   thread-even comment-item approved">

							<img  src="${c.commentAuthorAvatar }"
							class="avatar avatar-50 photo" height="50" width="50">
							<div class="dashboard-comment-wrap has-row-actions">
								<p class="comment-meta">
									��<cite class="comment-author"> <a target="_blank"	href="" rel="external nofollow" class="url">${commentAuthorName }</a>
									</cite>������ ��<a href="/article/${c.commentArticleId }">�����۵����µı���,��������</a>��
								</p>

								<blockquote>
									<p>${c.commentContent }</p>
								</blockquote>
								<p class="row-actions">
									| <span class=""> <a data-comment-id="1268"
										href="/admin/comment/reply/${c.commentId }"> �ظ� </a>
									</span> <span class=""> | <a
										href="/admin/comment/edit/${c.commentId }">�༭</a>
									</span> <span class=""> | <a href="javascript:void(0)"
										onclick="deleteComment(${c.commentId })">ɾ��</a>
									</span>
								</p>
							</div>
						</li>
				   </c:forEach>
				   
		7) ��ÿ�����۶�Ӧ�����µı�����ʾ����
		
			   ��CommentServiceImpl����,��������Ϣ�����
				
				@Service
				public class CommentServiceImpl implements CommentService{
					@Resource
					private CommentMapper commentMapper;
					
					@Resource
					private ArticleMapper artileMapper;
				
					/**
					 * ��ѯ�����n��������Ϣ,ÿ��������Ϣ��,�����б����۵����µ���Ϣ
					 */
					public List<Comment> listRecentComment(int n) {
						
						List<Comment> commentList= commentMapper.listRecentComment(n);
						
						for(int i=0;i<commentList.size();i++) {
							
							//�õ������۵����µ�id
							int commetArticleId=commentList.get(i).getCommentArticleId();
							
							//��������id�õ�������Ϣ
							Article article=artileMapper.getArticleById(commetArticleId);
							
							//��������Ϣ��������
							commentList.get(i).setArticle(article);
							
						}
						
						return commentList;
					}
			}
			
			ArticleMapper �ӿ���ҲҪ������� getArticleById() ���� 
		
				public interface ArticleMapper {
					/**
					 * ��ѯ��������ǰn������
					 * @param n ��ѯ��������
					 * @return �����б�
					 */
					List<Article> listRecentArticle(Integer n);
				
					Article getArticleById(int commetArticleId);
				
				}
				
		 ArticleMapper.xml��ҲҪ����ʵ�� 
				
				<!-- ��������id,��ѯ������Ϣ -->
				<select id="getArticleById"    resultType="Article">
					select  <include refid="cols" /> from article  where article_id=#{id}
				</select>
							
							
		  index.jsp ����ʾ�����۶�Ӧ�����±���:
		  
		   ...
		   	</cite>������ ��<a href="/article/${c.commentArticleId }">${c.article.articleTitle}</a>��
		   ...
			





