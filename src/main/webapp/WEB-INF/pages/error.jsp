<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
<script type="text/javascript">

	window.onload = function(){
		var btn = document.getElementsByTagName("button");//此方法的返回值为数组
		btn[0].onclick = function(){
			//回到上一个页面
			window.history.back();
		};
	};

</script>
</head>
<body>
	<h2>${exception.message }</h2><br/>
	<button>返回</button>
</body>
</html>