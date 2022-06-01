======================================Lesson15============================================
1 ����������ת�����·���ҳ
2 ���·���ҳ�����
==========================================================================================
==== 1 ����������ת�����·���ҳ
  1) �� framework.jsp �Ϸ�������
       <dd><a href="article/add">д����</a></dd>
       
  2) ���Ʋ�
			/**
			 * ������Ϣ,���Ʋ�
			 */
			@Controller   @RequestMapping("/article")
			public class ArticleController {
				@Resource
				private ArticleService articleService; 
				
				@Resource
				private CategoryService cateGoryService;   //��ʱ��û����
				
				@Resource
				private TagService tagService; //��ʱ��û����
				
				/**
				 * ת�����·���ҳ�� ,��Ϊ��ҳ����Ҫѡ����������,������ǩ,����Ҫ���ŷ����б�ͱ�ǩ�б��ȥ
				 */
				@RequestMapping(value ="/add",method=RequestMethod.GET)
				public String goToAdd(ModelMap m) {
					//������Ϣ
					List<Category> categoryList= cateGoryService.listCategory();
					
					//��ǩ��Ϣ
					List<Tag> tagList= tagService.listTag();
					
					//�ŵ�������
					 m.put("categoryList", categoryList);
					 m.put("tagList", tagList);
						
					//ת��д���µ�ҳ��
					return "/Article/article-add";		
				}
				 ....
			}
				
	 3) CategoryService  CategoryServiceImpl ,  TagService TagServiceImpl
	 		  CategoryService  ��
	 		  ===CateGoryServiceImpl ===
				package com.service.impl;
				import java.util.List;
				import javax.annotation.Resource;
				import org.springframework.stereotype.Service;
				import com.entity.Category;
				import com.service.CategoryService;
				
				@Service
				public class CategoryServiceImpl implements CategoryService {
				
					@Resource
					private CategoryMapper categoryMapper;
					
					//��ѯ���еķ�����Ϣ
					public List<Category> listCategory() {
						return categoryMapper.listCategory();
					}
					
					//���ݸ���id,��ѯ�ӷ�����Ϣ
					public List<Category> listCategoryByParentId(Integer parentId) {
						return categoryMapper.listCategoryByParentId(parentId);
					}
				}

       ===CategoryMapper===
				package com.mapper;
				import java.util.List;
				import com.entity.Category;
				
				public interface CategoryMapper {
					List<Category> listCategory();
					List<Category> listCategoryByParentId(Integer parentId);
				}
			 
			 ===CategoryMapper.xml===
			<?xml version="1.0" encoding="UTF-8" ?>  
			<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
			"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
			
			<mapper namespace="com.mapper.CategoryMapper">
				<sql id="cols">
				   category_id, category_pid, category_name, category_description, category_order, category_icon   
				</sql>
				
				<!-- ��ѯȫ�������б� -->
				<select id="listCategory"   resultType="Category">
				    select  <include refid="cols" /> from category 
				</select>
				
				<!-- ��ѯĳ������������ӷ��� -->
				<select id="listCategoryByParentId"   resultType="Category">
				    select  <include refid="cols" /> from category where category_pid=#{parentId}
				</select>
			</mapper>

   4) TagService , TagServiceImpl, TagMapper, TagMapper.xml
   
        (1) Tagʵ����
						package com.entity;
						public class Tag {
							private Integer tagId;
							private String tagName;
							private String tagDescription;
							...
						}
						
				(2) TagService ��
			
			  (3) TagServiceImpl
						package com.service.impl;
						import java.util.List;
						import javax.annotation.Resource;
						import org.springframework.stereotype.Service;
						import com.entity.Tag;
						import com.service.TagService;
						
						@Service
						public class TagServiceImpl implements TagService {
							@Resource
							private TagMapper tagMapper;
							
							public List<Tag> listTag() {
								return tagMapper.listTag();
							}
						
						}
						
				(4) TagMapper
						package com.mapper;
						import java.util.List;
						import com.entity.Tag;
						
						public interface TagMapper {
							List<Tag> listTag();
						}
						
				(5) TagMapper.xml
					
					<?xml version="1.0" encoding="UTF-8" ?>  
					<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
					"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
					
					<mapper namespace="com.mapper.TagMapper">
						<select id="listTag"  resultType="Tag">
							select * from tag
						</select>
					</mapper>
					
					
==== 2 ���·���ҳ�����
		<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<rapid:override name="frame-content">
			<blockquote class="layui-elem-quote">
				<span class="layui-breadcrumb" lay-separator="/"> <a
					href="/admin">��ҳ</a> <a href="/admin/article">�����б�</a> <a><cite>�������</cite></a>
				</span>
			</blockquote>
		
			<form class="layui-form" method="post" id="myForm"
				action="/admin/article/insertSubmit">
		
				<div class="layui-form-item">
					<label class="layui-form-label">���� <span
						style="color: #FF5722;">*</span></label>
					<div class="layui-input-block">
						<input type="text" name="articleTitle" lay-verify="title"
							id="title" autocomplete="off" placeholder="���������"
							class="layui-input">
					</div>
				</div>
				
							
		
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">���� <span
						style="color: #FF5722;">*</span></label>
					<div class="layui-input-block">
						<textarea class="layui-textarea layui-hide" name="articleContent" lay-verify="content" id="content"></textarea>			
					</div>
		
				</div>
				
		
		
				<div class="layui-form-item">
					<label class="layui-form-label">���� <span
						style="color: #FF5722;">*</span>
					</label>
					<div class="layui-input-inline">
						<select name="articleParentCategoryId" 	id="articleParentCategoryId" lay-filter="articleParentCategoryId" required>
							<option value="" >һ������</option>
							<c:forEach var="c" items="${categoryList }">
								<c:if test="${c.categoryPid==0 }">
									<option value="${c.categoryId }">${c.categoryName }</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="layui-input-inline">
						<select name="articleChildCategoryId" id="articleChildCategoryId">
							<option value="" selected>��������</option>
						</select>
					</div>
				</div>
		
				<div class="layui-form-item" pane="">
					<label class="layui-form-label">��ǩ</label>
					<div class="layui-input-block">
						<c:forEach var="t" items="${tagList }">
							<input type="checkbox" name="articleTagIds" lay-skin="primary" title="${t.tagName }" value="${t.tagName }"> 
						</c:forEach>
							
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">״̬</label>
					<div class="layui-input-block">
						<input type="radio" name="articleStatus" value="1" title="����"
							checked> <input type="radio" name="articleStatus"
							value="0" title="�ݸ�">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">�����ύ</button>
						<button type="reset" class="layui-btn layui-btn-primary"
							onclick="getCateIds()">����</button>
					</div>
				</div>
				<blockquote class="layui-elem-quote layui-quote-nm">
					��ܰ��ʾ��<br> 1���������ݵ����ݱ��ֶ�����ΪMEDIUMTEXT,ÿƪ���������벻Ҫ����500���� <br>
					2��д����֮ǰ����ȷ����ǩ�ͷ�����ڣ�����������½�������ˢ��ҳ�棬���Ͳ����Զ����� <br> 3���������ǰ�����Ե��
					<a href="http://liuyanzhao.com/code-highlight.html"
						target="_blank">�������</a>,������ת��HTML��ʽ
				</blockquote>
			</form>
		</rapid:override>
		
		 <rapid:override name="frame-footer-script">
			<script>
				layui.use(
				[ 'form', 'layedit', 'laydate' ],
				
				function() {
					var form = layui.form, 
					    layer = layui.layer, 
					    layedit = layui.layedit, 
					    laydate = layui.laydate;
		
					//�ϴ�ͼƬ,������� ����һ���༭��ǰ��
					layedit.set({
						uploadImage : {
							url : '/admin/upload/img' //�ӿ�url
							,
							type : 'post' //Ĭ��post
						}
					});
		
					//����һ���༭��
					var editIndex = layedit.build('content', {
						height : 350,
					});
		
					//�Զ�����֤����
					form.verify({
						title : function(value) {
							if (value.length < 5) {
								return '�������ٵ�5���ַ���';
							}
						},
						pass : [ /(.+){6,12}$/, '�������6��12λ' ],
						content : function(value) {
							layedit.sync(editIndex);
						}
					});
		
					layedit.build('content', {
						tool : [ 'strong' //�Ӵ�
						, 'italic' //б��
						, 'underline' //�»���
						, 'del' //ɾ����
						, '|' //�ָ���
						, 'left' //�����
						, 'center' //���ж���
						, 'right' //�Ҷ���
						, 'link' //������
						, 'unlink' //�������
						, 'face' //����
						, 'image' //����ͼƬ
						, 'code' ]
					});
					
				
		
					layui.use('code', function() { //����codeģ��
						layui.code();
					});
		
					//��������
					form
						.on(
							"select(articleParentCategoryId)",
							function() {
								var optionstring = "";
								var articleParentCategoryId = $(
										"#articleParentCategoryId")
										.val();
		
								if (articleParentCategoryId == 0) {
									optionstring += "<option name='childCategory' value='1'>Java</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='2'>Java����</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='3'>Core Java</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='4'>���̲߳������</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='5'>Sockets��IO</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='6'>���ģʽ�ͷ���</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='7'>JVM</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='8'>JavaWeb</option>";
								}
		
								if (articleParentCategoryId == 1) {
									optionstring += "<option name='childCategory' value='9'>Java���</option>";
								}
		
								if (articleParentCategoryId == 0) {
									optionstring += "<option name='childCategory' value='10'>�������ѧ</option>";
								}
		
								if (articleParentCategoryId == 10) {
									optionstring += "<option name='childCategory' value='11'>���ݽṹ���㷨</option>";
								}
		
								if (articleParentCategoryId == 10) {
									optionstring += "<option name='childCategory' value='12'>����ϵͳ</option>";
								}
		
								if (articleParentCategoryId == 10) {
									optionstring += "<option name='childCategory' value='13'>���ݿ�</option>";
								}
		
								if (articleParentCategoryId == 10) {
									optionstring += "<option name='childCategory' value='14'>���������</option>";
								}
		
								if (articleParentCategoryId == 0) {
									optionstring += "<option name='childCategory' value='15'>��������</option>";
								}
		
								if (articleParentCategoryId == 15) {
									optionstring += "<option name='childCategory' value='16'>��Ϣ����</option>";
								}
		
								if (articleParentCategoryId == 15) {
									optionstring += "<option name='childCategory' value='17'>�������</option>";
								}
		
								if (articleParentCategoryId == 100000000) {
									optionstring += "<option name='childCategory' value='19'>Hello</option>";
								}
		
								if (articleParentCategoryId == 15) {
									optionstring += "<option name='childCategory' value='100000003'>΢����</option>";
								}
		
								if (articleParentCategoryId == 15) {
									optionstring += "<option name='childCategory' value='100000004'>��������</option>";
								}
		
								if (articleParentCategoryId == 15) {
									optionstring += "<option name='childCategory' value='100000005'>Ȩ�޿��</option>";
								}
		
								if (articleParentCategoryId == 15) {
									optionstring += "<option name='childCategory' value='100000006'>��������</option>";
								}
		
								$("#articleChildCategoryId")
										.html(
												"<option value=''selected>��������</option>"
														+ optionstring);
								form.render('select'); //�������Ҫ
							})
		
						});
			</script>
		</rapid:override>
		
		
		<%@ include file="../framework.jsp" %>