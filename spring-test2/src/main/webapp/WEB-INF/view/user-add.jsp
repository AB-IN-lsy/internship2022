<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"></link>
<meta charset="UTF-8">
<title>用户添加</title>
</head>
<body>
	<h1>用户添加</h1>
	<hr />
	<form action="${pageContext.request.contextPath}/user/add" method="post" enctype="multipart/form-data">
		账号:	<input name="userName" >  <br />
		密码:	<input name="userPass" > <br />
		昵称:	<input name="userNickname" >  <br />
		邮箱:	<input name="userEmail" >  <br />
		URL:	<input name="userUrl" >  <br />
		网址:	<input name="userAvatar" >  <br />
		IP:		<input name="userLastLoginIp" >  <br />
		照片: 	<input type=file name="uploadPhoto" > <br />
			<input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
	</form>
	${msg} 
</body>
</html>