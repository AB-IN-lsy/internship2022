<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>

<style>
	table{
		width: 70%;
		border: 1px solid brown;
		border-collapse: collapse;
	}
	td{
		border: 1px solid brown;
	}

</style>


</head>
<body>

	<h1>用户列表</h1>
	<table>
		<tr>
			<td>Id</td>
			<td>账号</td>
			<td>密码</td>
			<td>昵称</td>
			<td>邮箱</td>
			<td>URL</td>
			<td>网址</td>
			<td>IP</td>
		</tr>
		<c:forEach var="u" items="${userList}">
		<tr>
			<td>${u.userId}</td>
			<td>${u.userName}</td>
			<td>${u.userPass}</td>
			<td>${u.userNickname}</td>
			<td>${u.userEmail}</td>
			<td>${u.userUrl}</td>
			<td>${u.userAvatar}</td>
			<td>${u.userLastLoginIp}</td>
			<td> <a href="${pageContext.request.contextPath}/user/update?userId=${u.userId}">修改</a>  | 
				 <a href="${pageContext.request.contextPath}/user/delete?userId=${u.userId}" onclick="return confirm('确定要删除用户吗')">删除</a>
			</td>
		</tr>
		</c:forEach>	
	</table>
</body>
</html>