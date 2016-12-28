<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>验证码位置获取服务地址</title>
</head>
<body>
	验证码位置获取服务地址：<br>
	<p>${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/Casper</p>
</body>
</html>