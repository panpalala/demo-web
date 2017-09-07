<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
</head>
<body>
	<h1>用户登录</h1>
	<form action="login" method="post">
		<!-- 注册是提交的值被springmvc封装到了DUser对象中，以map的形式传到controller中，该map的key为类名，value为对象 -->
		用户名：<input type="text" name="userName" value="${DUser.userName }" ><br/>
		密码：<input type="password" name="userPwd" value="123456"><br>
		<button type="submit">登录</button>
	</form>
</body>
</html>