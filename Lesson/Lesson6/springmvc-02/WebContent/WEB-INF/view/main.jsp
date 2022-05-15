<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>信息系统</h1>
	  登录成功,当前用户:  ${session_userName }
	<hr />
		
	<ul>
		<li> <a href="${pageContext.request.contextPath}/user/add" >用户添加</a>  </li>
		<li> <a href="${pageContext.request.contextPath}/user/getAll" >用户列表</a>  </li>
		<li> <a href="${pageContext.request.contextPath}/user/logout"  onclick="return confirm('确定要退出吗')">退出登录</a>  </li>
	</ul>
	
	
	

</body>
</html>