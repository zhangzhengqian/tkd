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
					<small>战队信息</small>
				</legend>
				<div class="row">
					<form id="inputForm" action="#" method="post"
						class="form-horizontal">
						<div class="col-sm-12">
						<div class="form-group form-group-sm">
								<label for="logo" class="col-md-3 control-label"><span
									class="text-red"> </span>战队logo:</label>
								<div class="col-md-8 has-feedback">
									<c:if test='${!empty info.logo}'>
										<img style="width: 128px; height: 128px; display: block;"
											src="${info.logo}">
									</c:if>
								</div>
							</div>
							<div class="form-group form-group-sm">
								<label for="phone" class="col-md-3 control-label"><span
									class="text-red"></span>战队名称:</label> <label for="name"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.name}</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="statiumName" class="col-md-3 control-label"><span
									class="text-red"></span>战队队长:</label> <label for="statiumName"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${lf:getSsouserNameById(info.captain)} </label>
							</div>
									<div class="form-group form-group-sm">
								<label for="lngLat" class="col-md-3 control-label"><span
									class="text-red"> </span>活动区域:</label> <label for="lngLat"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.area }</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red"> </span>运动类型:</label> <label for="atype"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span> <c:if test="${'1' == activity.type}">羽毛球</c:if>
												<c:if test="${info.sportType == 'BOWLING'}">保龄球 &nbsp;</c:if>
											<c:if test="${info.sportType == 'BILLIARDS'}">台球 &nbsp;</c:if>
											<c:if test="${info.sportType == 'TABLE_TENNIS'}">乒乓球 &nbsp;</c:if>
											<c:if test="${info.sportType == 'FOOTBALL'}">足球 &nbsp;</c:if>
											<c:if test="${info.sportType == 'BASKETBALL'}">篮球 &nbsp;</c:if>
											<c:if test="${info.sportType == 'TENNIS'}">网球 &nbsp;</c:if>
											<c:if test="${info.sportType == 'GOLF'}">高尔夫 &nbsp;</c:if>
											<c:if test="${info.sportType == 'BADMINTON'}">羽毛球 &nbsp;</c:if> </label>
							</div>
							
							<div class="form-group form-group-sm">
								<label for="areaCode" class="col-md-3 control-label"><span
									class="text-red"> </span>比赛地点:</label> <label for="areaCode"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span> <tags:zonemap
										code="${event.areaCode }" /></label>
							</div>
					
							<div class="form-group form-group-sm">
								<label for="address" class="col-md-3 control-label"><span
									class="text-red"> </span>联系电话:</label> <label for="address"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.phone}</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="sponsorFont" class="col-md-3 control-label"><span
									class="text-red"></span>主办方介绍:</label> <label for="sponsor"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.introduction}</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="telType" class="col-md-3 control-label"><span
									class="text-red"> </span>战队比分:</label> <label for="tel"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.integral }</label>
							</div>
							<div class="form-group form-group-sm">
								<label for="telType" class="col-md-3 control-label"><span
									class="text-red"> </span>战队积分:</label> <label for="tel"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span></label>
							</div>
							<div class="form-group form-group-sm">
								<label for="startTimeType" class="col-md-3 control-label"><span
									class="text-red"> </span>战队成员数:</label> <label for="startTime"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${info.currentNumber }</label>

							</div>
							<div class="form-group form-group-sm">
								<label for="endTimeType" class="col-md-3 control-label"><span
									class="text-red"> </span>参赛记录:</label> <label for="endTime"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span></label>
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
