<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>

<form id ="myForm" action="${pageContext.request.contextPath}/login" method="post">
	<div><input type="hidden" name="userId" value="1"></div>
	<div><span>用户名：</span><input type="text" name="userName"></div>
	<div><span>密码：</span><input  type="password" name="userPassword"></div>	
	<button type="submit" >登&nbsp&nbsp录</button>
</form>
  
</body>
</html>