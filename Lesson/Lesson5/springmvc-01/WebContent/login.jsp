<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
  <h1>登录</h1>
  
  <form action="${pageContext.request.contextPath}/user/login" method="post">
  	账号:<input name="userName"  >
  	密码:<input name="password"  >
  	<input type="submit" value="登录">
  </form>
  
    ${msg}

</body>
</html>