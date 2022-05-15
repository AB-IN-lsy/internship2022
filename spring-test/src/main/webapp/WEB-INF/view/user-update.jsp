<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户更新</title>
</head>
<body>
	<h1>用户更新</h1>
	<hr />
	<form action="${pageContext.request.contextPath}/user/update" method="post">
				<input type="hidden" name="id" value="${user.userId }" >
		账号:	<input name="userName" value="${user.userName }" >  <br />
		密码:	<input name="userPass" value="${user.userPass }"> <br />
			<input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
	</form>
	${msg} 
</body>
</html>