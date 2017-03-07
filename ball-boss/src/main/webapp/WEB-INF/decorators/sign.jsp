<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="renderer" content="webkit" />
<title></title>

<script type="text/javascript">var ctx = '${pageContext.request.contextPath}';</script>
<script src="${ctx}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/custom.js" type="text/javascript"></script><!-- 自定义：包含全选/取消全选脚本 -->
<script src="${ctx}/static/js/bootstrap.js" type="text/javascript"></script>
<script src="${ctx}/static/js/carousel.js" type="text/javascript"></script>
<script src="${ctx}/static/js/common.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/echarts.js"></script>


<link href="${ctx }/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="${ctx }/static/css/plugins/bootstrap.css" rel="stylesheet" />
<!-- Bootstrap core CSS -->
<link href="${ctx}/static/css/common.css" rel="stylesheet" /><!-- 自定义样式 -->
<link href="${ctx }/static/css/style.css" rel="stylesheet" />


<decorator:head />
</head>

<body>
	<decorator:body />
</body>
</html>