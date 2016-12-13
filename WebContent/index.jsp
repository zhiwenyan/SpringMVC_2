<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="testJson">JSON数据 </a>

	<form action="testHttpMessageConverter" method="post"
		enctype="multipart/form-data">
		File:<input type="file" name="file"> Desc:<input type="text"
			name="desc"> <input type="submit" value="submit">
	</form>
	<a href="testResponseEntity">下载 </a>
	<br>
	<a href="getUsers">getUsers </a>
	<form action="testUpFileLoad" method="post"
		enctype="multipart/form-data">
		File:<input type="file" name="file"> Desc:<input type="text"
			name="desc"> <input type="submit" value="上传">
	</form>
	<a href="testInterceptor">testInterceptor </a>
	<br>
	<a href="testHandlerExceptionResolver?i=0">testHandlerExceptionResolver
	</a>
	<br>
	<a href="testResponseStatusExceptionResolver?i=13">testHandlerExceptionResolver
	</a>
	<br>
	<a href="testDefaultHandlerExceptionResolver">testDefaultHandlerExceptionResolver
	</a>
	<a href="testSimpleMappingExceptionResolver?i=11">testSimpleMappingExceptionResolver
	</a>
	<a href="helloworld">Hello World</a>
	<form action="login" method="post">
		username:<input type="text" name="username"><br>
		password:<input type="password" name="password"><br> <input
			type="submit" value="submit">
	</form>


	<form action="testConversionService" method="post">
		User:<input type="text" name="user"><input
			type="submit" value="submit">
	</form>

</body>
</html>