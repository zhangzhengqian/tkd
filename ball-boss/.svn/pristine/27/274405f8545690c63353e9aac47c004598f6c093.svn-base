<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业用户</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>统计报表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-5 form-inline">
				</div>
				 <div class="col-md-7">
					<div class="btn-group-sm pull-right mtb10">
						<a class="btn btn-primary btn-sm" href="javascript:history.go(-1)"><span
							class="glyphicon glyphicon-search"></span> 返回</a>
					</div>
				</div>
			</div>
			<br>
		<div class="panel-body">
			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">	
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>订单号</th>
								<th>企业名称</th>
								<th>时间</th>
								<th>类型</th>
								<th>总收入(元)</th>
								<th>总支出(元)</th>
								<th>利润</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data}" var="info" varStatus="stat">
								<tr>
							  	<td class="text-center">${stat.count}</td>
								<td class="text-center">${info.id}</td>
								<td class="text-center">${info.name}</td>
								<td class="text-center"><fmt:formatDate value="${info.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="text-center">
									<c:choose>
						                <c:when test="${info.serviceType==0}">固定包场</c:when>
						                <c:when test="${info.serviceType==1}">活动赛事</c:when>
						                <c:when test="${info.serviceType==2}">账户充值</c:when>
						                <c:when test="${info.serviceType==3}">账户提现</c:when>
						        	</c:choose>
								</td>
								<td class="text-center">${lf:formatMoney(info.fee)}</td>
								<td class="text-center">${lf:formatMoney(info.costFee)}</td>
								<td class="text-center">${lf:formatMoney(info.fee)-lf:formatMoney(info.costFee)}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table></form>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#company-services');
			$('#adminFooter').hide();
		});
	</script>
</body>
</html>