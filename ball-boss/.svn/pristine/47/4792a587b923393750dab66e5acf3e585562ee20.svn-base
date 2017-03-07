<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>活动管理</title>
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
				<li>赛事管理</li>
				<li class="active">查看赛事</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<fieldset>
				<legend>
					<small>赛事信息</small>
				</legend>
				<div class="row">
					<form id="inputForm" action="${ctx}/event/${action }" method="post"
						class="form-horizontal">
						<div class="form-group form-group-sm">
							<label for="phone" class="col-md-3 control-label"><span
								class="text-red"></span>赛事类型:</label> <label for="name"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>
								<c:if test="${'1' == event.realNameSys}">实名制赛 </c:if>
								<c:if test="${'1' != event.realNameSys}">非实名制赛 </c:if>
						</div>
						<div class="form-group form-group-sm">
							<label for="phone" class="col-md-3 control-label"><span
								class="text-red"></span>比赛方式:</label> <label for="name"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>
								<c:if test="${'0' == event.type}">个人赛</c:if> 
									<c:if test="${'1' == event.type}">团体赛</c:if> 
						</div>
						<div class="form-group form-group-sm">
							<label for="phone" class="col-md-3 control-label"><span
								class="text-red"></span>赛事可见区域:</label> <label for="name"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>
								 ${event.scopeProvince} ${event.scopeCity}</label>
						</div>
						<div class="form-group form-group-sm">
							 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>报名队数限制:</label>
						     <div class="col-md-6 has-feedback">
				                    <c:if test="${event.isRestrict == 0}">不限制</c:if> 
						     		 <c:if test="${event.isRestrict != 0}"> 限制 </c:if> 
						     </div>
					  </div>
					  <div class="form-group form-group-sm">
							<label for="totalNumberFront" class="col-md-3 control-label"><span
								class="text-red"></span>赛事人/队数上限:</label> <label for="totalNumber"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.totalNumber}<c:if
									test="${'0' == event.type}">个人</c:if> <c:if
									test="${'1' == event.type}">团队</c:if></label>
						</div>

						<div class="form-group form-group-sm">
							<label for="applicantNumberFront" class="col-md-3 control-label"><span
								class="text-red"></span>已报名人/队数:</label> <label for="applicantNumber"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.applicantNumber}<c:if
									test="${'0' == event.type}">个人</c:if> <c:if
									test="${'1' == event.type}">团队</c:if></label>
						</div>
						<div class="form-group form-group-sm">
							<label for="phone" class="col-md-3 control-label"><span
								class="text-red"></span>赛事名称:</label> <label for="name"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.name}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="sponsorFont" class="col-md-3 control-label"><span
								class="text-red"></span>海报:</label> <label for="sponsor"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>
							<c:if test='${!empty event.showLogo}'>
									<img style="width: 128px; height: 128px; display: block;"
										src="${event.showLogo}">
								</c:if></label>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red"> </span>类别:</label> <label for="atype"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span> <c:if test="${'1' == activity.type}">羽毛球</c:if>
								<c:if test="${event.sportType == 'BOWLING'}">保龄球 &nbsp;</c:if> <c:if
									test="${event.sportType == 'BILLIARDS'}">台球 &nbsp;</c:if> <c:if
									test="${event.sportType == 'TABLE_TENNIS'}">乒乓球 &nbsp;</c:if> <c:if
									test="${event.sportType == 'FOOTBALL'}">足球 &nbsp;</c:if> <c:if
									test="${event.sportType == 'BASKETBALL'}">篮球 &nbsp;</c:if> <c:if
									test="${event.sportType == 'TENNIS'}">网球 &nbsp;</c:if> <c:if
									test="${event.sportType == 'GOLF'}">高尔夫 &nbsp;</c:if> <c:if
									test="${event.sportType == 'BADMINTON'}">羽毛球 &nbsp;</c:if> </label>
						</div>
						<div class="form-group form-group-sm">
							<label for="address" class="col-md-3 control-label"><span
								class="text-red"> </span>赛制:</label> <label for="address"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.formatStr}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="sponsorFont" class="col-md-3 control-label"><span
								class="text-red"></span>主办方:</label> <label for="sponsor"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.sponsor}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="sponsorFont" class="col-md-3 control-label"><span
								class="text-red"></span>主办方海报:</label> <label for="sponsor"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>
							<c:if test='${!empty event.logo}'>
									<img style="width: 128px; height: 128px; display: block;"
										src="${event.logo}">
								</c:if></label>
						</div>
						<div class="form-group form-group-sm">
							<label for="sponsorFont" class="col-md-3 control-label"><span
								class="text-red"></span>主办方介绍:</label> <label for="sponsor"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.introduce}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="expiryDateType" class="col-md-3 control-label"><span
								class="text-red"> </span>报名截止:</label> <label for="expiryDate"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.expiryDate }</label>

						</div>
						<div class="form-group form-group-sm">
							<label for="startTimeType" class="col-md-3 control-label"><span
								class="text-red"> </span>赛事开始时间:</label> <label for="startTime"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.startTime }</label>

						</div>
						<div class="form-group form-group-sm">
							<label for="endTimeType" class="col-md-3 control-label"><span
								class="text-red"> </span>赛事结束时间:</label> <label for="endTime"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.endTime }</label>

						</div>
						<div class="form-group form-group-sm">
							<label for="areaCode" class="col-md-3 control-label"><span
								class="text-red"> </span>比赛地点:</label> <label for="areaCode"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span> <tags:zonemap code="${event.areaCode }" /></label>
						</div>
						<div class="form-group form-group-sm">
							<label for="statiumName" class="col-md-3 control-label"><span
								class="text-red"></span>比赛场馆:</label> <label for="statiumName"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.statiumName}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="lngLat" class="col-md-3 control-label"><span
								class="text-red"> </span>场馆坐标:</label> <label for="lngLat"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.lng },${event.lat}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="address" class="col-md-3 control-label"><span
								class="text-red"> </span>详细地址:</label> <label for="address"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.address}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="moneyFront" class="col-md-3 control-label"><span
								class="text-red"></span>报名费用:</label> <label for="money"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.money/100}元/<c:if
									test="${'0' == event.type}">个人</c:if> <c:if
									test="${'1' == event.type}">团队</c:if></label>
						</div>
						<div class="form-group form-group-sm">
							<label for="birthday" class="col-md-3 control-label"><span
								class="text-red"> </span>赛程:</label>
							<c:if test='${!empty event.schedule}'>
								<img style="width: 128px; height: 128px; display: block;"
									src="${event.schedule}">
							</c:if>
						</div>
						<div class="form-group form-group-sm">
							<label for="sponsorFont" class="col-md-3 control-label"><span
								class="text-red"></span>章程:</label> <label for="sponsor"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.rules}</label>
						</div>
						<div class="form-group form-group-sm">
							<label for="telType" class="col-md-3 control-label"><span
								class="text-red"> </span>联系电话:</label> <label for="tel"
								class="col-md-6 control-label label2" style="text-align: left;"><span
								class="text-red"></span>${event.tel }</label>
						</div>
					</form>
					<!-- end row -->
				</div>
				<div class="form-group">
					<hr>
					<div class="col-md-offset-3 col-md-2">
						<a class="btn btn-default btn-block" href="${ctx}/event/list"><span
							class="glyphicon glyphicon-remove"></span> 返回</a>
					</div>
					<shiro:hasPermission name="activity:update">
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-block"
								id="submit_btn">
								<span class="glyphicon glyphicon-ok"></span> 修改
							</button>
						</div>
					</shiro:hasPermission>
				</div>
			</fieldset>

		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#event-man');
			$("#submit_btn").click(function() {
				location.href = "${ctx }/event/update/${event.id }";
			});
			$('#adminFooter').hide();

		});
	</script>
</body>
</html>
