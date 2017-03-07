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
				<li>标签管理</li>
				<li class="active"><c:if test="${'create' eq action }"> 新建标签</c:if>
					<c:if test="${'edit' eq action }"> 修改标签</c:if></li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<form id="inputForm" action="${ctx}/label/${action }" method="post"
			class="form-horizontal">
			<input id="id" name="id" value="${label.id}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>标签信息</small>
					</legend>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label" style="margin-left: 10%;"><span
									class="text-red">* </span>标签名称:</label>
								<div class="col-md-6 has-feedback" style="width: 15%;">
									<input type="text" class="form-control" placeholder="请填写标签名称"
										id="name" name="name" value="${label.name}">
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="type" class="col-md-3 control-label" style="margin-left: 10%;"><span
									class="text-red">* </span>标签类型:</label>
								<div class="col-md-6 has-feedback" style="width: 15%;">
									<select class="form-control" id="type" name="type">
										<option value="">--请选择类型--</option>
										<option <c:if test="${'0' == label.type}">selected</c:if>
											id="option0" value="0">--用户标签--</option>
										<option <c:if test="${'1' == label.type}">selected</c:if>
											id="option1" value="1">--场馆标签--</option>
										<option <c:if test="${'2' == label.type}">selected</c:if>
											id="option1" value="2">--教培标签--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			
			<div class="form-group">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/label/list"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-block"
						id="submit_btn">
						<span class="glyphicon glyphicon-ok"></span> 保存
					</button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#label-man');
			
			$("#submit_btn").click(function() {
				$("#inputForm").submit();
			});

			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					//表单验证成功时，锁住提交按钮
					app.disabled("#submit_btn");
					//提交表单
					form.submit();
				},
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						maxlength : 10
					},
					"type" : { 
						required : true
					}
				},
				messages : {
				
				}
			});

			$('#adminFooter').hide();

		});
	</script>
</body>
</html>
