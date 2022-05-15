<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
  <form action="${pageContext.request.contextPath}/user/login">    <!-- ${pageContext.request.contextPath} 这个写法,是得到基地址,防止路径出现问题 -->
  	账号:<input name="userName"  >
  	密码:<input name="userPass"  >
  	<input type="submit" value="登录">
  </form>
  
   ${msg} <!-- 取出传递回来的作用域数据 -->
</body>
</html>	