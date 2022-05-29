======================================Lesson14 ============================================
1 ��� article-list.jsp�����
2 article-list.jspҳ���е����ݲ�ѯ
3 article-list.jsp�е����ݳ���
4 article-list.jsp �еķ�����Ϣ��ʾ
5 ��ҳ��ʾ
===========================================================================================
==== 1 ��� article-list.jsp�����
		1)  �ӵ����Ϸ������� (framework.jsp)
		       ...
				   <dd><a href="article">ȫ������</a></dd>
				   ...
	
	  2)  ����ArticleController
					package com.controller;
					import org.springframework.stereotype.Controller;
					import org.springframework.web.bind.annotation.RequestMapping;
					
					/**
					 * ������Ϣ,���Ʋ�
					 */
					@Controller   @RequestMapping("/article")
					public class ArticleController {
						
						@RequestMapping(value="")
						public String index() {
							
							//��ҳ��ѯ������ص�����,�ŵ��������� Ƿ��
							
							return "/Article/article-list" ;
						}
					}
					
		3) ��view �½� Article Ŀ¼,���潨 article-list.jsp  �ȰѾ�̬��������
		
					<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
					<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
					
					<rapid:override name="frame-content">
					<blockquote class="layui-elem-quote">
						<span class="layui-breadcrumb" lay-separator="/"> <a
							href="/admin">��ҳ</a> <a><cite>�����б�</cite></a>
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
										<th>����</th>
										<th>��������</th>
										<th>״̬</th>
										<th>����ʱ��</th>
										<th>����</th>
										<th>id</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><a href="/article/33" target="_blank"> MySQL�����������</a></td>
										<td><a href="/category/10" target="_blank">�������ѧ</a>
											&nbsp; <a href="/category/13" target="_blank">���ݿ�</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 21:06:52</td>
										<td><a href="/admin/article/edit/33"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(33)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>33</td>
									</tr>
									<tr>
										<td><a href="/article/32" target="_blank">
												Docker_���ţ�ֻҪ��ƪ�͹��ˣ������ɻ��ʺ�0����С�ף�</a></td>
										<td><a href="/category/15" target="_blank">��������</a> &nbsp;
											<a href="/category/100000006" target="_blank">��������</a> &nbsp;
										</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 21:05:05</td>
										<td><a href="/admin/article/edit/32"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(32)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>32</td>
									</tr>
									<tr>
										<td><a href="/article/31" target="_blank"> RocketMQ
												ʵս֮��������</a></td>
										<td><a href="/category/15" target="_blank">��������</a> &nbsp;
											<a href="/category/16" target="_blank">��Ϣ����</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 21:02:40</td>
										<td><a href="/admin/article/edit/31"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(31)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>31</td>
									</tr>
									<tr>
										<td><a href="/article/30" target="_blank"> SpringBoot
												+ mongodb ����, ��¼��վ������־�����ò�ѯ����</a></td>
										<td><a href="/category/10" target="_blank">�������ѧ</a>
											&nbsp; <a href="/category/13" target="_blank">���ݿ�</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 21:01:24</td>
										<td><a href="/admin/article/edit/30"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(30)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>30</td>
									</tr>
									<tr>
										<td><a href="/article/29" target="_blank">
												IDEA����EDAS��Ŀ</a></td>
										<td><a href="/category/15" target="_blank">��������</a> &nbsp;
											<a href="/category/100000003" target="_blank">΢����</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 21:00:24</td>
										<td><a href="/admin/article/edit/29"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(29)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>29</td>
									</tr>
									<tr>
										<td><a href="/article/28" target="_blank"> SpringCloud
												��ʹ�� Eureka �� Feign ʵ����ɾ�Ĳ�</a></td>
										<td><a href="/category/15" target="_blank">��������</a> &nbsp;
											<a href="/category/100000003" target="_blank">΢����</a> &nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 20:58:22</td>
										<td><a href="/admin/article/edit/28"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(28)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>28</td>
									</tr>
									<tr>
										<td><a href="/article/27" target="_blank"> SpringCloud
												��ʹ�� Eureka �� Feign ʵ����ɾ�Ĳ�</a></td>
										<td><a href="/category/100000000" target="_blank">δ����</a>
											&nbsp;</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 20:56:50</td>
										<td><a href="/admin/article/edit/27"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(27)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>27</td>
									</tr>
									<tr>
										<td><a href="/article/26" target="_blank"> SpringBoot
												���� elasticsearch ʵ��</a></td>
										<td><a href="/category/15" target="_blank">��������</a> &nbsp;
											<a href="/category/100000004" target="_blank">��������</a> &nbsp;
										</td>
										<td><a href="/admin/article?status=1"> <span
												style="color: #5FB878;">�ѷ���</span>
										</a></td>
										<td>2018-11-25 20:42:58</td>
										<td><a href="/admin/article/edit/26"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(26)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>26</td>
									</tr>
									<tr>
										<td><a href="/article/25" target="_blank"> sssssssss</a></td>
										<td><a href="/category/10" target="_blank">�������ѧ</a>
											&nbsp; <a href="/category/12" target="_blank">����ϵͳ</a> &nbsp;
										</td>
										<td><a href="/admin/article?status=0"> <span
												style="color: #FF5722;">�ݸ�</span>
										</a></td>
										<td>2018-11-25 17:22:10</td>
										<td><a href="/admin/article/edit/25"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(25)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
										<td>25</td>
									</tr>
									<tr>
										<td><a href="/article/24" target="_blank"> sssss</a></td>
										<td><a href="/category/10" target="_blank">�������ѧ</a>
											&nbsp; <a href="/category/13" target="_blank">���ݿ�</a> &nbsp;</td>
										<td><a href="/admin/article?status=0"> <span
												style="color: #FF5722;">�ݸ�</span>
										</a></td>
										<td>2018-11-25 17:19:09</td>
										<td><a href="/admin/article/edit/24"
											class="layui-btn layui-btn-mini">�༭</a> <a
											href="javascript:void(0)" onclick="deleteArticle(24)"
											class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
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
					
		4) ������Ŀ,����ർ���ϵ� "ȫ������" ����,�����ܷ���ȷת�� view/Article/article-jsp ,�Լ��侲̬������ʾ���Ƿ�����
		
==== 2 article-list.jspҳ���е����ݲ�ѯ
		 1) �� Category ʵ����,������Ϣ�ķ���
			     public class Category{
			       	    //���������ID
							    private Integer categoryId;
							
							    //����ĸ���id
							    private Integer categoryPid;
							
								  //��������
							    private String categoryName;
							
							    //��������
							    private String categoryDescription;
							
							    //�����˳��
							    private Integer categoryOrder;
							
							 	  //�����ͼ��
							    private String categoryIcon;
							
							    //��������(�����ݿ��ֶ�)
							    private Integer articleCount; 
					   }
					   
		 2) Article ʵ������,���һ���ֶ�,���ڽ������ºͷ���Ĺ�ϵ
			    
			    //����������Щ����(�����ĸ��������ĸ�С����)
			    private List<Category> categoryList;
			    
		 3) ��pom.xml��, �����ҳ���
	  		  <dependency>
	            <groupId>com.github.pagehelper</groupId>
	            <artifactId>pagehelper</artifactId>
	            <version>4.2.1</version>
	        </dependency>
	   
	   4) �� mybatis-config��,���з�ҳ��ص�����
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
		
		5) ArticleController ���Ʋ�
				package com.controller;
				import javax.annotation.Resource;
				import org.springframework.stereotype.Controller;
				import org.springframework.ui.ModelMap;
				import org.springframework.web.bind.annotation.*;
				import com.entity.Article;
				import com.github.pagehelper.PageInfo;
				import com.service.ArticleService;
				
				/**
				 * ������Ϣ,���Ʋ�
				 */
				@Controller   @RequestMapping("/article")
				public class ArticleController {
					@Resource
					private ArticleService articleService;
					
					/**
					 * ��ҳ��ѯ������Ϣ
					 * @param pageIndex  ���ڷ�ҳ,��ʾ��ǰ�ǵڼ�ҳ,Ĭ����1
					 * @param pageSize ���ڷ�ҳ,��ʾÿҳ�ж���������,Ĭ����10
					 * @param m
					 * @return  ���ص��� PageInfo���͵�����,�����溬�з�ҳ��Ϣ,�;���Ĳ����������
					 */
					@RequestMapping(value="")
					public String index(
								@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
								@RequestParam(required = false, defaultValue = "10") Integer pageSize,
								ModelMap m ) {
						
						//��ҳ��ѯ������ص�����,�ŵ��������� Ƿ��
						PageInfo <Article> pageInfo =articleService.getPageArticleList(pageIndex, pageSize);
						m.put("pageInfo", pageInfo);
						return "/Article/article-list" ;
					}
				}
				
		6) ArticleService �ӿں�ʵ����

				public interface ArticleService {
					//�������ǰ�� ,���ù�
					List<Article> listRecentArticle(Integer n);
				
					//�������������
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
						
						//��ÿ������,��Ҫ������Ӧ�Ĵ����,С������Ϣ�����
						for(Article a:  articleList) {
							//ÿ������Ҫ��ӷ�����Ϣ, Ƿ��
						}	
						return  new PageInfo<Article>(articleList);
					}
				}
				
	   7) ArticleMapper �ӿ� 					    
					/**
					 * ��ѯ���е�����
					 * @return ���б�
					 */
					List<Article> findAll();
					
		 8) ArticleMapper.xml 
				<select id="findAll"  resultType="Article">
					 <include refid="cols" /> from article  order by article_order desc,article_id desc 
				</select>
				
==== 3 article-list.jsp�е����ݳ���
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
		
		 <tbody>
					<c:forEach var="a" items="${pageInfo.list}">
						<tr>
							<td><a href="/article/${a.articleId }" target="_blank"> ${a.articleTitle }</a></td>
							<td><a href="/category/10" target="_blank">�����</a>
								&nbsp; <a href="/category/13" target="_blank">С����</a> &nbsp;</td>
							<td>
								<a href="/admin/article?status=1">
								    <c:if test="${a.articleStatus==1 }">
								    	 <span style="color: #5FB878;">�ѷ���</span>
								    </c:if>
								    
								     <c:if test="${a.articleStatus==0 }">
								    	 <span style="color: red">�ݸ�</span>
								    </c:if>
									 
								</a>
							</td>
							<td>  <fmt:formatDate value="${a.articleCreateTime    }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
							<td><a href="/admin/article/edit/${a.articleId }"
								class="layui-btn layui-btn-mini">�༭</a> <a
								href="javascript:void(0)" onclick="deleteArticle(${a.articleId })"
								class="layui-btn layui-btn-danger layui-btn-mini">ɾ��</a></td>
							<td>33</td>
						</tr>
					</c:forEach>
			</tbody>
			
			��Ƿ������:
			  1) ��������������Ϣ,��û�г��ֳ���
			  2) ��ҳ��û����ʵʵ��
			  
==== 4 article-list.jsp �еķ�����Ϣ��ʾ
   1) ArticleServiceImpl ��:
   
			public PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize) {
				
				PageHelper.startPage(pageIndex,pageSize);
				
				List<Article> articleList =articleMapper.findAll(); 
				
				//��ÿ������,��Ҫ������Ӧ�Ĵ����,С������Ϣ�����
				for(Article a:  articleList) {
					//ÿ������Ҫ��ӷ�����Ϣ, Ƿ��
					List<Category> categoryList=articleMapper.listCategoryByArticleId(a.getArticleId());
					a.setCategoryList(categoryList   );
				}
				
				return  new PageInfo<Article>(articleList);
			}
			
	 2) articleMapper �ӿ���,��� listCategoryByArticleId() �������
				public interface ArticleMapper {
					...
				
					/**
					 * ��������id,��ѯ���������ķ���(�����,С����)
					 * @param articleId ����id
					 * @return �����б�
					 */
					List<Category> listCategoryByArticleId(Integer articleId);
				
				}

   3) articleMapper.xml��ʵ������ķ���
	   	<!-- ��������id,��ѯ����������Щ���� -->
			<select id="listCategoryByArticleId" resultType="Category">
				select * from category where category_id in(select category_id from article_category_ref where article_id=#{articleId} )
			</select>
			
	 4) ��article_list.jsp��,�ѷ�����ϢҲ��ʾ����
	    ...
				<td>
					<c:forEach var="c" items="${a.categoryList}">
					 	 <a href="/category/${c.categoryId }" target="_blank">${c.categoryName }</a> &nbsp;
					</c:forEach>	
			  </td>
	    ...
	    
	    
==== 5 ��ҳ��ʾ
		1) ���Ʋ��еĲ�ѯ:
		
			@RequestMapping(value="")
			public String index(
						@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
						@RequestParam(required = false, defaultValue = "5") Integer pageSize,
						ModelMap m ) {
				
				//��ҳ��ѯ������ص�����,�ŵ��������� Ƿ��
				PageInfo <Article> pageInfo =articleService.getPageArticleList(pageIndex, pageSize);
				
				m.put("pageUrlPrefix","article?pageIndex"); //��ǰ׺������ҳ��ҳ��
		
				m.put("pageInfo", pageInfo);
				return "/Article/article-list" ;
			}
			
	 2) �½����ڷ�ҳ��ҳ�� view/page.jsp
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
				                <%--��һҳ --%>
				            <c:choose>
				                <c:when test="${pageInfo.pageNum eq 1 }">
				                    <%--��ǰҳΪ��һҳ��������һҳ��ť--%>
				                </c:when>
				                <c:otherwise>
				                    <a class="page-numbers" href="${pageUrlPrefix}=${pageInfo.pageNum-1}">   
				                        <i class="layui-icon">&#xe603;</i>
				                    </a>
				                </c:otherwise>
				            </c:choose>
				                <%--��ʾ��һҳ��ҳ��--%>
				            <c:if test="${begin >= 2 }">
				                <a class="page-numbers" href="${pageUrlPrefix}=1">1</a> 
				            </c:if>
				                <%--��ʾ����--%>
				            <c:if test="${begin  > 2 }">
				                <span class="page-numbers dots">��</span>
				            </c:if>
				                <%--��ӡ ҳ��--%>
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
				                <%-- ��ʾ���� --%>
				            <c:if test="${end < pageInfo.pages-1 }">
				                <span class="page-numbers dots">��</span>
				            </c:if>
				                <%-- ��ʾ���һҳ������ --%>
				            <c:if test="${end < pageInfo.pages }">
				                <a href="${pageUrlPrefix}=${pageInfo.pages}">   ${pageInfo.pages}</a>
				            </c:if>
				                <%--��һҳ --%>
				            <c:choose>
				                <c:when test="${pageInfo.pageNum eq pageInfo.pages }">
				                    <%--����βҳ���أ���һҳ��ť--%>
				                </c:when>
				                <c:otherwise>
				                    <a class="page-numbers"  href="${pageUrlPrefix}=${pageInfo.pageNum+1}"> <i class="layui-icon">&#xe602;</i>  </a>
				                </c:otherwise>
				            </c:choose>
				        </div>
				    </nav>
				</c:if>		
				
		3) �� article-list.jsp�����÷�ҳҳ��
		   	<%@ include file="../page.jsp" %>