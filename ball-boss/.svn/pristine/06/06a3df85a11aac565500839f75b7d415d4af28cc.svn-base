<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>赛事管理</title>
<style type="text/css">
.label2 {
	text-align: left;
	font-weight: normal;
}
</style>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 赛事管理</li>
				<li>参赛队伍</li>
				<li class="active">详情</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<fieldset>
				<legend>
					<small>详细信息</small>
				</legend>
				<div class="row">
					<form id="inputForm" action="#" method="post"
						class="form-horizontal">
						<div class="col-sm-12">
						<div class="form-group form-group-sm">
								<label for="logo" class="col-md-3 control-label"><span
									class="text-red"> </span>头像:</label>
								<div class="col-md-8 has-feedback">
									<c:if test='${!empty info.photo}'>
										<img style="width: 128px; height: 128px; display: block;"
											src="${info.photo}">
									</c:if>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label for="address" class="col-md-3 control-label"><span
									class="text-red"> </span>真实姓名:</label> <label for="address"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.name}</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="address" class="col-md-3 control-label"><span
									class="text-red"> </span>身份证号:</label> <label for="address"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.cardId}</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="phone" class="col-md-3 control-label"><span
									class="text-red"></span>昵称:</label> <label for="name"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.nickName}</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="address" class="col-md-3 control-label"><span
									class="text-red"> </span>联系电话:</label> <label for="address"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.phone}</label>
							</div>
						</div>
					</form>
				</div>
				<!-- end row -->

			</fieldset>

			<div class="form-group">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/event/list"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
			</div>


		</div>

	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#event-man');
			$('#adminFooter').hide();

		});
	</script>
</body>
</html>
