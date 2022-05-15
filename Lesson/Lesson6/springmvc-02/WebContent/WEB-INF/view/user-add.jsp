<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>用户添加</h1>
	<hr />
	<form action="${pageContext.request.contextPath}/user/add"  method="post" enctype="multipart/form-data">
	账号:	<input name="userName" >  <br />
	密码:	<input name="password" > <br />
	备注:	<textarea  name="note"></textarea> <br />
       照片:   <input type=file name="uploadphoto" >
		<input type="submit" value="提交"  onclick="return confirm('确定要提交吗')">
	
	</form>
	${msg} 
	
</body>
</html>