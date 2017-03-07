<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场馆管理</title>
</head>

<body>

<div class="panel panel-default">

	<div class="panel-heading"><!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span> 场馆管理</li>
			<li class="active">同步糯米</li>
		</ul>
	</div><!-- / 右侧标题 -->

	<div class="panel-body"><!-- 右侧主体内容 -->

		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			</div>
		</div><!-- /查询条件 -->
		<div class="row"><!-- 操作按钮组 -->
			<div class="col-md-12">

			</div>
		</div><!-- /操作按钮组 -->
		<div class="row">
			<div class="col-table col-md-12" >
				<table id="contentTable" class="table table-bordered table-condensed table-hover">
					<thead class="thead">
					<tr>
						<input type="hidden" id="statiumId" name = "statiumId" value="${statiumId}">
						<th class="text-center">tp_poi_id</th>
						<th class="text-center">tp_deal_id</th>
						<th class="text-center">类型</th>
						<th class="text-center">同步状态</th>
						<th class="text-center">同步进度</th>
						<th class="text-center">最后同步时间</th>
						<th class="text-center">最后操作人</th>
						<th class="text-center">操作</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="sport" varStatus="stat">
						<tr >
							<td>${sport.tppoiid}</td>
							<td>${sport.id}</td>
							<td  >
								<c:forEach items="${fn:split(statium.sportType,';;') }" var="s" >
									<c:if test="${sport.sportType == 'BOWLING'}">
										保龄球 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'BILLIARDS'}">
										台球 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'TABLE_TENNIS'}">
										乒乓球 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'FOOTBALL'}">
										足球 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'BASKETBALL'}">
										篮球 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'TENNIS'}">
										网球 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'GOLF'}">
										高尔夫 &nbsp;
									</c:if>
									<c:if test="${sport.sportType == 'BADMINTON'}">
										羽毛球 &nbsp;
									</c:if>
								</c:forEach>
							</td>
							<td >
								<c:if test="${sport.uploadType == 0}">
									已提交同步
								</c:if>
								<c:if test="${sport.uploadType == 2}">
									已在檽米隐藏
								</c:if>
								<c:if test="${sport.uploadType == 3}">
									更新已提交同步
								</c:if>
								<c:if test="${empty sport.id}">
									未同步到糯米
								</c:if>
							</td>
							<td >
								<c:if test="${sport.status == 0}">
									糯米处理中或同步失败
								</c:if>
								<c:if test="${sport.status == 1}">
									同步成功
								</c:if>
								<c:if test="${sport.status == 9}">
									糯米处理同步中
								</c:if>
							</td>
							<td ><fmt:formatDate value="${sport.et}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
							<td ><tags:getUserById id="${sport.eb }" /></td>
							<td >
								<c:if test="${sport.status != 1}" >
								   <a href="#" onclick="uploadData('${sport.sportType}',0)"  class="btn btn-default btn-sm" > 同步或更新到糯米</a>
								</c:if>
								<a href="#" onclick="uploadData('${sport.sportType}',1)"  class="btn btn-default btn-sm" >隐藏在糯米数据</a>
								<c:if test="${!empty sport.id && sport.status == 1}">
									<a href="#" onclick="uploadData('${sport.sportType}',0)"  class="btn btn-default btn-sm" > 同步或更新到糯米</a>
								</c:if>
							</td>

						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div><!-- end col-table -->
		</div><!-- end row -->
	</div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">

	$(function() {
		menu.active('#statium-man');
		$('#adminFooter').hide();
	});

	function uploadData(sportType,type) {
		var statiumId = $("#statiumId").val();
		if(statiumId == "" || statiumId == null){
			myAlert("场馆ID为空");
		}else if(sportType == "" || sportType == null){
			myAlert("运动类型为空");
		}else {
			Util.getData(ctx + '/statium/uploadToNuomi', function (data) {
				if (data.result) {
					myAlert(data.reason);
					window.location.reload()
				} else {
					myAlert(data.reason, "error");
					return false;
				}
			}, null, {"sportType":sportType,"statiumId":statiumId,"isHidden":type}, 'get');
		}
	}

</script>
</body>
</html>
