======================================Lesson14 ============================================
1 完成 article-list.jsp的设计
2 article-list.jsp页面中的数据查询
3 article-list.jsp中的数据呈现
4 article-list.jsp 中的分类信息显示
5 分页显示
===========================================================================================
==== 1 完成 article-list.jsp的设计
		1)  从导航上发起请求 (framework.jsp)
		       ...
				   <dd><a href="article">全部文章</a></dd>
				   ...
	
	  2)  创建ArticleController
					package com.controller;
					import org.springframework.stereotype.Controller;
					import org.springframework.web.bind.annotation.RequestMapping;
					
					/**
					 * 文章信息,控制层
					 */
					@Controller   @RequestMapping("/article")
					public class ArticleController {
						
						@RequestMapping(value="")
						public String index() {
							
							//分页查询文章相关的数据,放到作用域中 欠着
							
							return "/Article/article-list" ;
						}
					}
					
		3) 在view 下建 Article 目录,里面建 article-list.jsp  先把静态内容引入
		
					<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
					<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
					
					<rapid:override name="frame-content">
					<blockquote class="layui-elem-quote">
						<span class="layui-breadcrumb" lay-separator="/"> <a
							href="/admin">首页</a> <a><cite>文章列表</cite></a>
						</span>
					</blockquote>
					
					<div class="layui-tab layui-tab-card">
						<form id="articleForm" method="post">
							<input type="hidden" name="currentUrl" id="currentUrl" value="">
							<table class="layui-table">
								<colgroup>
									<col width="300">
									<col width="150">
									<col width="100">
									<col width="150">
									<col width="100">
									<col width="50">
								</colgroup>
								<thead>
									<tr>
										<th>标题</th>
										<th>所属分类</th>
										<th>状态</th>
										<th>发布时间</th>
										<th>操作</th>
										<th>id</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><a href="/article/33" target="_blank"> MySQL常用命令语句</a></td>
										<td><a href="/category/10" target="_blank">计算机科学</a>
											&nbsp; <a href="/category/13" target="_blank">数据库</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 21:06:52</td>
										<td><a href="/admin/article/edit/33"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(33)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>33</td>
									</tr>
									<tr>
										<td><a href="/article/32" target="_blank">
												Docker_入门？只要这篇就够了！（纯干货适合0基础小白）</a></td>
										<td><a href="/category/15" target="_blank">其他技术</a> &nbsp;
											<a href="/category/100000006" target="_blank">开发利器</a> &nbsp;
										</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 21:05:05</td>
										<td><a href="/admin/article/edit/32"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(32)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>32</td>
									</tr>
									<tr>
										<td><a href="/article/31" target="_blank"> RocketMQ
												实战之快速入门</a></td>
										<td><a href="/category/15" target="_blank">其他技术</a> &nbsp;
											<a href="/category/16" target="_blank">消息服务</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 21:02:40</td>
										<td><a href="/admin/article/edit/31"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(31)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>31</td>
									</tr>
									<tr>
										<td><a href="/article/30" target="_blank"> SpringBoot
												+ mongodb 整合, 记录网站操作日志，常用查询操作</a></td>
										<td><a href="/category/10" target="_blank">计算机科学</a>
											&nbsp; <a href="/category/13" target="_blank">数据库</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 21:01:24</td>
										<td><a href="/admin/article/edit/30"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(30)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>30</td>
									</tr>
									<tr>
										<td><a href="/article/29" target="_blank">
												IDEA启动EDAS项目</a></td>
										<td><a href="/category/15" target="_blank">其他技术</a> &nbsp;
											<a href="/category/100000003" target="_blank">微服务</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 21:00:24</td>
										<td><a href="/admin/article/edit/29"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(29)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>29</td>
									</tr>
									<tr>
										<td><a href="/article/28" target="_blank"> SpringCloud
												中使用 Eureka 和 Feign 实现增删改查</a></td>
										<td><a href="/category/15" target="_blank">其他技术</a> &nbsp;
											<a href="/category/100000003" target="_blank">微服务</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 20:58:22</td>
										<td><a href="/admin/article/edit/28"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(28)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>28</td>
									</tr>
									<tr>
										<td><a href="/article/27" target="_blank"> SpringCloud
												中使用 Eureka 和 Feign 实现增删改查</a></td>
										<td><a href="/category/100000000" target="_blank">未分类</a>
											&nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 20:56:50</td>
										<td><a href="/admin/article/edit/27"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(27)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>27</td>
									</tr>
									<tr>
										<td><a href="/article/26" target="_blank"> SpringBoot
												整合 elasticsearch 实例</a></td>
										<td><a href="/category/15" target="_blank">其他技术</a> &nbsp;
											<a href="/category/100000004" target="_blank">搜索引擎</a> &nbsp;
										</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">已发布</span>
										</a></td>
										<td>2018-11-25 20:42:58</td>
										<td><a href="/admin/article/edit/26"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(26)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>26</td>
									</tr>
									<tr>
										<td><a href="/article/25" target="_blank"> sssssssss</a></td>
										<td><a href="/category/10" target="_blank">计算机科学</a>
											&nbsp; <a href="/category/12" target="_blank">操作系统</a> &nbsp;
										</td>
										<td><a href="/admin/article?status=0"> <span
												style="color: #FF5722;">草稿</span>
										</a></td>
										<td>2018-11-25 17:22:10</td>
										<td><a href="/admin/article/edit/25"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(25)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>25</td>
									</tr>
									<tr>
										<td><a href="/article/24" target="_blank"> sssss</a></td>
										<td><a href="/category/10" target="_blank">计算机科学</a>
											&nbsp; <a href="/category/13" target="_blank">数据库</a> &nbsp;</td>
										<td><a href="/admin/article?status=0"> <span
												style="color: #FF5722;">草稿</span>
										</a></td>
										<td>2018-11-25 17:19:09</td>
										<td><a href="/admin/article/edit/24"
											class="layui-btn layui-btn-mini">编辑</a> <a
											href="javascript:void(0)" onclick="deleteArticle(24)"
											class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
										<td>24</td>
									</tr>
								</tbody>
							</table>
						</form>
						<nav class="navigation pagination" role="navigation">
							<div class="nav-links">
								<a class="page-numbers current">1</a> <a class="page-numbers"
									href="/admin/article?pageIndex=2">2</a> <a class="page-numbers"
									href="/admin/article?pageIndex=3">3</a> <a class="page-numbers"
									href="/admin/article?pageIndex=2"> <i class="layui-icon">&#xe602;</i>
								</a>
							</div>
						</nav>
					</div>
					</rapid:override>
					
					<%@ include file="../framework.jsp"  %>
					
		4) 启动项目,点左侧导航上的 "全部文章" 链接,测试能否正确转到 view/Article/article-jsp ,以及其静态内容显示的是否正常
		
==== 2 article-list.jsp页面中的数据查询
		 1) 建 Category 实体类,代表信息的分类
			     public class Category{
			       	    //分类的自增ID
							    private Integer categoryId;
							
							    //分类的父级id
							    private Integer categoryPid;
							
								  //分类名称
							    private String categoryName;
							
							    //分类描述
							    private String categoryDescription;
							
							    //分类的顺序
							    private Integer categoryOrder;
							
							 	  //分类的图标
							    private String categoryIcon;
							
							    //文章数量(非数据库字段)
							    private Integer articleCount; 
					   }
					   
		 2) Article 实体类中,添加一个字段,用于建立文章和分类的关系
			    
			    //文章属于哪些分类(属于哪个大分类和哪个小分类)
			    private List<Category> categoryList;
			    
		 3) 在pom.xml中, 引入分页插件
	  		  <dependency>
	            <groupId>com.github.pagehelper</groupId>
	            <artifactId>pagehelper</artifactId>
	            <version>4.2.1</version>
	        </dependency>
	   
	   4) 在 mybatis-config中,进行分页相关的配置
			   <plugins>
			        <plugin interceptor="com.github.pagehelper.PageHelper">
			            <property name="dialect" value="mysql"/>
			            <property name="offsetAsPageNum" value="false"/>
			            <property name="rowBoundsWithCount" value="false"/>
			            <property name="pageSizeZero" value="true"/>
			            <property name="reasonable" value="true"/>
			            <property name="supportMethodsArguments" value="false"/>
			            <property name="returnPageInfo" value="none"/>
			        </plugin>
			    </plugins>
		
		5) ArticleController 控制层
				package com.controller;
				import javax.annotation.Resource;
				import org.springframework.stereotype.Controller;
				import org.springframework.ui.ModelMap;
				import org.springframework.web.bind.annotation.*;
				import com.entity.Article;
				import com.github.pagehelper.PageInfo;
				import com.service.ArticleService;
				
				/**
				 * 文章信息,控制层
				 */
				@Controller   @RequestMapping("/article")
				public class ArticleController {
					@Resource
					private ArticleService articleService;
					
					/**
					 * 分页查询文章信息
					 * @param pageIndex  用于分页,表示当前是第几页,默认是1
					 * @param pageSize 用于分页,表示每页有多少条数据,默认是10
					 * @param m
					 * @return  返回的是 PageInfo类型的数据,它里面含有分页信息,和具体的查出来的数据
					 */
					@RequestMapping(value="")
					public String index(
								@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
								@RequestParam(required = false, defaultValue = "10") Integer pageSize,
								ModelMap m ) {
						
						//分页查询文章相关的数据,放到作用域中 欠着
						PageInfo <Article> pageInfo =articleService.getPageArticleList(pageIndex, pageSize);
						m.put("pageInfo", pageInfo);
						return "/Article/article-list" ;
					}
				}
				
		6) ArticleService 接口和实现类

				public interface ArticleService {
					//这个是以前的 ,不用管
					List<Article> listRecentArticle(Integer n);
				
					//下面的是新增的
					PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize);
				}
			
		     package com.service.impl;
				import java.util.List;
				import javax.annotation.Resource;
				import org.springframework.stereotype.Service;
				import com.entity.Article;
				import com.github.pagehelper.*;
				import com.mapper.ArticleMapper;
				import com.service.ArticleService;
				
				@Service
				public class ArticleSericeImpl implements ArticleService {
					@Resource
					private ArticleMapper articleMapper;
				
					public List<Article> listRecentArticle(Integer n) {
						return articleMapper.listRecentArticle(n);
					}
				
					public PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize) {		
						PageHelper.startPage(pageIndex,pageSize);		
						List<Article> articleList =articleMapper.findAll(); 
						
						//对每个文章,都要把它对应的大分类,小分类信息查出来
						for(Article a:  articleList) {
							//每个文章要添加分类信息, 欠账
						}	
						return  new PageInfo<Article>(articleList);
					}
				}
				
	   7) ArticleMapper 接口 					    
					/**
					 * 查询所有的文章
					 * @return 文列表
					 */
					List<Article> findAll();
					
		 8) ArticleMapper.xml 
				<select id="findAll"  resultType="Article">
					 <include refid="cols" /> from article  order by article_order desc,article_id desc 
				</select>
				
==== 3 article-list.jsp中的数据呈现
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
		 <tbody>
					<c:forEach var="a" items="${pageInfo.list}">
						<tr>
							<td><a href="/article/${a.articleId }" target="_blank"> ${a.articleTitle }</a></td>
							<td><a href="/category/10" target="_blank">大分类</a>
								&nbsp; <a href="/category/13" target="_blank">小分类</a> &nbsp;</td>
							<td>
								<a href="/admin/article?status=1">
								    <c:if test="${a.articleStatus==1 }">
								    	 <span style="color: #5FB878;">已发布</span>
								    </c:if>
								    
								     <c:if test="${a.articleStatus==0 }">
								    	 <span style="color: red">草稿</span>
								    </c:if>
									 
								</a>
							</td>
							<td>  <fmt:formatDate value="${a.articleCreateTime    }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
							<td><a href="/admin/article/edit/${a.articleId }"
								class="layui-btn layui-btn-mini">编辑</a> <a
								href="javascript:void(0)" onclick="deleteArticle(${a.articleId })"
								class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
							<td>33</td>
						</tr>
					</c:forEach>
			</tbody>
			
			还欠两笔账:
			  1) 文章所属分类信息,还没有呈现出来
			  2) 分页还没有真实实现
			  
==== 4 article-list.jsp 中的分类信息显示
   1) ArticleServiceImpl 中:
   
			public PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize) {
				
				PageHelper.startPage(pageIndex,pageSize);
				
				List<Article> articleList =articleMapper.findAll(); 
				
				//对每个文章,都要把它对应的大分类,小分类信息查出来
				for(Article a:  articleList) {
					//每个文章要添加分类信息, 欠账
					List<Category> categoryList=articleMapper.listCategoryByArticleId(a.getArticleId());
					a.setCategoryList(categoryList   );
				}
				
				return  new PageInfo<Article>(articleList);
			}
			
	 2) articleMapper 接口中,添加 listCategoryByArticleId() 这个方法
				public interface ArticleMapper {
					...
				
					/**
					 * 根据文章id,查询文章所属的分类(大分类,小分类)
					 * @param articleId 文章id
					 * @return 分类列表
					 */
					List<Category> listCategoryByArticleId(Integer articleId);
				
				}

   3) articleMapper.xml中实现上面的方法
	   	<!-- 根据文章id,查询文章属于哪些分类 -->
			<select id="listCategoryByArticleId" resultType="Category">
				select * from category where category_id in(select category_id from article_category_ref where article_id=#{articleId} )
			</select>
			
	 4) 在article_list.jsp中,把分类信息也显示出来
	    ...
				<td>
					<c:forEach var="c" items="${a.categoryList}">
					 	 <a href="/category/${c.categoryId }" target="_blank">${c.categoryName }</a> &nbsp;
					</c:forEach>	
			  </td>
	    ...
	    
	    
==== 5 分页显示
		1) 控制层中的查询:
		
			@RequestMapping(value="")
			public String index(
						@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
						@RequestParam(required = false, defaultValue = "5") Integer pageSize,
						ModelMap m ) {
				
				//分页查询文章相关的数据,放到作用域中 欠着
				PageInfo <Article> pageInfo =articleService.getPageArticleList(pageIndex, pageSize);
				
				m.put("pageUrlPrefix","article?pageIndex"); //把前缀传给分页的页面
		
				m.put("pageInfo", pageInfo);
				return "/Article/article-list" ;
			}
			
	 2) 新建用于分页的页面 view/page.jsp
				<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
				
					<c:if test="${pageInfo.pages > 1}">
				    <nav class="navigation pagination" role="navigation">
				        <div class="nav-links">
				            <c:choose>
				                <c:when test="${pageInfo.pages <= 3 }">
				                    <c:set var="begin" value="1"/>
				                    <c:set var="end" value="${pageInfo.pages }"/>
				                </c:when>
				                <c:otherwise>
				                    <c:set var="begin" value="${pageInfo.pageNum-1 }"/>
				                    <c:set var="end" value="${pageInfo.pageNum + 2}"/>
				                    <c:if test="${begin < 2 }">
				                        <c:set var="begin" value="1"/>
				                        <c:set var="end" value="3"/>
				                    </c:if>
				                    <c:if test="${end > pageInfo.pages }">
				                        <c:set var="begin" value="${pageInfo.pages-2 }"/>
				                        <c:set var="end" value="${pageInfo.pages }"/>
				                    </c:if>
				                </c:otherwise>
				            </c:choose>
				                <%--上一页 --%>
				            <c:choose>
				                <c:when test="${pageInfo.pageNum eq 1 }">
				                    <%--当前页为第一页，隐藏上一页按钮--%>
				                </c:when>
				                <c:otherwise>
				                    <a class="page-numbers" href="${pageUrlPrefix}=${pageInfo.pageNum-1}">   
				                        <i class="layui-icon">&#xe603;</i>
				                    </a>
				                </c:otherwise>
				            </c:choose>
				                <%--显示第一页的页码--%>
				            <c:if test="${begin >= 2 }">
				                <a class="page-numbers" href="${pageUrlPrefix}=1">1</a> 
				            </c:if>
				                <%--显示点点点--%>
				            <c:if test="${begin  > 2 }">
				                <span class="page-numbers dots">…</span>
				            </c:if>
				                <%--打印 页码--%>
				            <c:forEach begin="${begin }" end="${end }" var="i">
				                <c:choose>
				                    <c:when test="${i eq pageInfo.pageNum }">
				                        <a class="page-numbers current">${i}</a>
				                    </c:when>
				                    <c:otherwise>
				                        <a class="page-numbers" href="${pageUrlPrefix}=${i}">${i}</a>   
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
				                <%-- 显示点点点 --%>
				            <c:if test="${end < pageInfo.pages-1 }">
				                <span class="page-numbers dots">…</span>
				            </c:if>
				                <%-- 显示最后一页的数字 --%>
				            <c:if test="${end < pageInfo.pages }">
				                <a href="${pageUrlPrefix}=${pageInfo.pages}">   ${pageInfo.pages}</a>
				            </c:if>
				                <%--下一页 --%>
				            <c:choose>
				                <c:when test="${pageInfo.pageNum eq pageInfo.pages }">
				                    <%--到了尾页隐藏，下一页按钮--%>
				                </c:when>
				                <c:otherwise>
				                    <a class="page-numbers"  href="${pageUrlPrefix}=${pageInfo.pageNum+1}"> <i class="layui-icon">&#xe602;</i>  </a>
				                </c:otherwise>
				            </c:choose>
				        </div>
				    </nav>
				</c:if>		
				
		3) 在 article-list.jsp中引用分页页面
		   	<%@ include file="../page.jsp" %>