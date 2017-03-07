<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>赛程管理</title>
</head>
<body>
  <iframe  src="${ctx}/event/corpsList_dlg?eventId=${eventId}" width="1000px;" height="900px;"></iframe>
</body>
</html>