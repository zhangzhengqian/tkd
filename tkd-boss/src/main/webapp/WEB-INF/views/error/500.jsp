<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" session="false" %>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%response.setStatus(200);%>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
	
	StringWriter exStack = new StringWriter();
	ex.printStackTrace(new PrintWriter(exStack));
	pageContext.setAttribute("exStack", exStack);
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="decorator" content="printable" />

	<title>500 - 系统内部错误</title>
    <script src="${ctx}/static/js/jquery.min.js" type="text/javascript"></script>
	<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" />
	
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
			padding: 5px;
		}
	</style>
</head>

<body>

<h2>500 - 系统发生内部错误. <small><a href="javascript:history.go(-1);">返回上一页</a></small></h2>
	
<p class="alert alert-error">错误消息：<%=ex.getMessage() %></p>

<button type="button" id="show-detail">异常堆栈信息</button>

<pre id="error-detail">
<c:out value="${exStack }" />
</pre>

<script type="text/javascript">
$(function(){
	$('#show-detail').on('click', function(){
		$('#error-detail').toggle('normal');
	});
	
    var $content = $('#error-detail');
    var h = $(window).height();
    if ($content.length > 0) {
      var th = h - $('#show-detail').offset().top - 40;
      $content.css('height', th);
    }	
});
</script>
</body>
</html>
