<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
   table{
   	width:70%;
   	border:1px solid brown;
   	border-collapse:collapse;
   }
   
   td{
    	border:1px solid brown;
   }
</style>
</head>
<body>
	<h1>用户列表</h1>
	<hr />
		
    <form action="${pageContext.request.contextPath }/user/deleteUsers" method="post">
		<table>
			<tr>
				<td></td>
				<td>图片</td>
				<td>id</td>
				<td>账号</td>
				<td>密码</td>
				<td>备注</td>
				<td>操作</td>
			</tr>
			<c:forEach var="u" items="${userList }">
			<tr>
			
				<td><img src="${pageContext.request.contextPath }/upload-files/${u.photo }"  > </td>
				<td><input type="checkbox" name="ids" value="${u.id}"></td>
				<td>${u.id}</td>
				<td>${u.userName}</td>
				<td>${u.password}</td>
				<td>${u.note}</td>
			
				<td> 
					<a href="${pageContext.request.contextPath }/user/update?id=${u.id}">修改</a>  |
                    <a href="${pageContext.request.contextPath }/user/delete?id=${u.id}" onclick="return confirm('确定要删除用户吗')">删除</a>| |
                    
                    <a href="${pageContext.request.contextPath }/user/del/${u.id}" onclick="return confirm('确定要删除用户吗')">Rest风格删除</a>
                 </td>
			</tr>
			</c:forEach>		
		</table>
		<input type="submit" value="删除所选" onclick="return confirm('确定要删除所选用户吗')">
	</form>
	

</body>
</html>