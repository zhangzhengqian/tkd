<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<html>
<head>
<title>组织管理</title>

<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

<link rel="stylesheet" href="${ctx}/static/js/jquery/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
<style type="text/css">
<!--
.ztree * {
	font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei UI",
		simsun, sans-serif;
	font-size: 14px;
}

.ztree li {
	margin: 3px 0;
}

.ztree li a.curSelectedNode {
	height: 18px;
}

/*冻结根结节*/
.ztree li span.button.switch.level0 {
	visibility: hidden;
	width: 1px;
}

.ztree li ul.level0 {
	padding: 0;
	background: none;
}

/*根节点图标样式*/
.ztree li span.button.root_ico_open,.ztree li span.button.root_ico_close
	{
	width: 0px;
	height: 0px;
}


/*编辑按钮图标样式*/
.ztree li a span.button.edit
, .ztree li a span.button.remove
, .ztree li a span.button.add {
	margin-left: 10px;
	margin-right: -5px;
}
.ztree li span.button.add {
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}

.tree-container {
	border: #efefef 1px solid;
	overflow: auto;
}
-->
</style>
	
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
        <li class="active">组织管理</li>
        <span id="loading" class="pull-right"><img src="${ctx }/static/img/loading.gif" /></span>
    </ul>
  </div><!-- / 右侧标题 -->
  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
    <div class="row">
			<div class="col-md-4"><!-- 功能树 -->
			  <div class="tree-container">
				<ul id="funcTree" class="ztree"></ul>
			  </div>
			</div><!-- /功能树-->
			
			<div class="col-md-8" id="func-form" ></div>
		
		</div>
	</div>

</div>

<form id="actionForm" action="#" method="post"></form>

<script type="text/javascript" src="${ctx}/static/js/admin/org.js" ></script>

</body>
</html>
