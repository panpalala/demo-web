<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册账户页面</title>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
	<h1>用户注册</h1>
	<form action="regist" method="post">
		用户名：<input type="text" name="userName"><br/>
		密码：<input type="password" name="userPwd"><br>
		<button type="submit">注册</button>
	</form>
</body>
</html>