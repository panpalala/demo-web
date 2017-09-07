<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<title>首页</title>
</head>
<body>
	<center>
		<table width="200px" height="100px" border="1px" cellspacing="0px">
			<tr><th align="center">用户操作</th></tr>
			<tr><td align="center"><a href="demo/user/toLogin">去登录</a></td></tr>
			<tr><td align="center"><a href="demo/user/toRegist">去注册</a></td></tr>
			<tr></tr>
			<tr><td align="center"><a href="demo/photo/search">图片搜索</a></td></tr>
		</table>
	</center>
</body>
</html>