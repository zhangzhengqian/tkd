<%@ page contentType="text/html;charset=UTF-8"  isErrorPage="true" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="printable" />

	<title>404 - 页面不存在</title>
  <style>
    #error-detail {
      border: 1px solid #efefef;
      background-color: #efefef;
      padding:10px;
      height: 300px;
      display: none;
      overflow:auto;
    }
    body {
      padding: 15px;
    }
  </style>  	
</head>

<body>
	<h2>404 - 页面不存在.</h2>
	<p><a href="javascript:history.go(-1)">返回上一页</a></p>
</body>
</html>