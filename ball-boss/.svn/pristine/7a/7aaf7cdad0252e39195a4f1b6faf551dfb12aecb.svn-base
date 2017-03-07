<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>资讯管理</title>
	<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>  
	<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 资讯管理</li>
        <li class="active">新增资讯</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body">
<form id="actionForm" action="${ctx }/gift/save" method="post">
		<input name="id" type="hidden" value="${gift.id }">
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     手机号
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="phone" value="${gift.phone}" id="listContent">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     姓名
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="name" value="${gift.name}" id="listContent">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     快递
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="express" value="${gift.express}" id="listContent">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     快递单号
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="expressNumber" value="${gift.expressNumber}" id="listContent">
			</div>
		</div>
		<hr>
		<div class="form-group form-group-sm">
 				<div class="col-md-12 text-center">
 					<button type="botton" class="btn btn-primary btn-sm back"> 返回</button> 
 					<button type="submit" class="btn btn-primary btn-sm"> 修改</button> 
 				</div>
		</div>
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  menu.active('#info-man');
	  $('#adminFooter').hide();
	  $(".back").on("click",function(){
		  history.go(-1);
	  })
  });
  
</script>

</body>
</html>
