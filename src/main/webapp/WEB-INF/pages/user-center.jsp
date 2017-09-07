<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息中心</title>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
</head>
<body>
	<center>
		<form action="update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="userId" value="${sessionScope.user.userId }"/>
			<table>
				<tr>
					<th><img height="100px" width="100px" src="http://192.168.252.128/${sessionScope.user.pictureGroupName }/${sessionScope.user.pictureRemoteName }"></th>
					<th><input type="file" name="imgFile" value="更换头像"/></th>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="userName" value="${sessionScope.user.userName }"/></td>
				</tr> 
				<tr>
					<td>昵称：</td>
					<td><input type="text" name="nickName" value="${sessionScope.user.nickName }"/></td>
				</tr> 
				<tr>
					<td>家乡：</td>
					<td><input type="text" name="hometown" value="${sessionScope.user.hometown }"/></td>
				</tr>
				<tr>
					<td>职业：</td>
					<td><input type="text" name="job" value="${sessionScope.user.job }"/></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><input type="text" name="gender" value="${sessionScope.user.gender }"/></td>
				</tr>
				<tr>
					<td>签名：</td>
					<td><input type="text" name="userDescribe" value="${sessionScope.user.userDescribe }"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><button>更新</button></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>