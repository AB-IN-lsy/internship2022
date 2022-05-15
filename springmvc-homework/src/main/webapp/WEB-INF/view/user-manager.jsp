<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>id</td>
			<td>账号</td>
			<td>密码</td>
			<td>备注</td>
		</tr>
		<c:forEach var="u" items="${userList}">
		<tr>
			<td>${u.id}</td>
			<td>${u.userName}</td>
			<td>${u.password}</td>
			<td>${u.note}</td>
		</tr>
		</c:forEach>	
	</table>
</body>
</html>