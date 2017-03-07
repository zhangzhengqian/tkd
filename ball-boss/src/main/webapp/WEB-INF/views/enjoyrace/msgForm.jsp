<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理</title>
<style type="text/css">
#title div {
	margin-left: auto;
	margin-right: auto;
}

.blank {
	clear: both;
	height: 10px;
	line-height: 10px;
	visibility: hidden;
}
</style>
<script type="text/javascript"
	src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
</head>

<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li>赛事管理</li>
				<li class="active">通知发布</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<form id="inputForm" action="${ctx}/enjoyRace/sendMsg" method="post"
			class="form-horizontal">
			<input id="msgId" name="msgId" value="${gameId}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>通知发布</small>
					</legend>
					<div class="row">
						<div class="col-sm-12">
						<div class="form-group form-group-sm">
					         <label for="address" class="col-md-3 control-label"></label>
					         <div class="col-md-6 has-feedback ">
					         	<textarea  rows="5" style="height:8em;" id="msg" class="form-control" placeholder="请输入通知内容" name="msg"></textarea>
					         </div>
	     				</div>
						</div>
					</div>
				</fieldset>
			</div>
			
			<div class="form-group">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/enjoyRace/eventMsg"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-block"
						id="submit_btn">
						<span class="glyphicon glyphicon-ok"></span> 确认发布
					</button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			
			$("#submit_btn").click(function() {
				$("#inputForm").submit();
			});

			$('#adminFooter').hide();
			
			$('#inputForm').validate({
				rules : {
					"msg" : {
						required : true,
						maxlength : 128
					},
					messages : {
	
					}
				}
			});

		});
	</script>
</body>
</html>
