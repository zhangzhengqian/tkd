<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>参赛人员</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li class="active">乐享赛事</li>
				<li class="active">账单明细</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div class="btn-group-sm pull-right">
						<a class="btn btn-default btn-sm" href="${ctx}/enjoyRace/balance/${gameId}">
								<span class="glyphicon"></span> 确认结账
							</a>
						<a class="btn btn-default btn-sm" href="${ctx}/enjoyRace/list">
								<span class="glyphicon"></span> 返回
							</a>
				</div>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>赛事名称</th>
								<th>赛事种类</th>
								<th>赛事类型</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>订单总数</th>
								<th>成本总额</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${balanceList}" var="balance" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${balance.name }</td>
									<td class="text-center">
										<c:if test="${'1' == balance.gameLevel}">乐享一级赛</c:if>
										<c:if test="${balance.gameLevel == '2'}">乐享二级赛</c:if> 
										<c:if test="${balance.gameLevel == '3'}">乐享三级赛</c:if> 
										<c:if test="${balance.gameLevel == '4'}">乐享四级赛</c:if>
									</td>
									<td class="text-center">
										<c:if test="${'1' == balance.gameType}">男单</c:if>
										<c:if test="${balance.gameType == '2'}">女单</c:if> 
										<c:if test="${balance.gameType == '3'}">男双</c:if> 
										<c:if test="${balance.gameType == '4'}">女双</c:if>
										<c:if test="${balance.gameType == '5'}">混双</c:if> 
										<c:if test="${balance.gameType == '6'}">混单</c:if>
										<c:if test="${balance.gameType == '7'}">无性别限制双打</c:if>
									</td>
									<td class="text-center">${balance.startTime }</td>
									<td class="text-center">${balance.endTime }</td>
									<td class="text-center">${balance.orderCnt }</td>
									<td class="text-center">${balance.cbNum }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
		});
	</script>
</body>
</html>