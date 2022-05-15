<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>用户修改</h1>
	<hr />
	<form action="${pageContext.request.contextPath }/user/update" method="post">
			<input type="hidden" name="id" value="${user.id }" >
 	账号:	<input name="userName" value="${user.userName }" >  <br />
	密码:	<input name="password" value="${user.password }"> <br />
	备注:	<textarea  name="note">${user.note }</textarea> <br />
	
    <input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
	
	</form>
	
	${msg }
	
	
	
</body>
</html>