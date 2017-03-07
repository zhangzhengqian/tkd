<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>CTA-Open授权</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> CTA-Open授权管理</li>
				<li class="active">授权列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>ID</th>
								<th>授权号</th>
								<th>场馆</th>
								<th>地区</th>
								<th>开始时间</th>
								<th>结束时间</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data}" var="acc" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${acc.id }</td>
									<td class="text-center">${acc.number }</td>
									<td class="text-center">${acc.statiumNames}</td>
									<td class="text-center">${acc.area}</td>
									<td class="text-center">${acc.start}</td>
									<td class="text-center">${acc.end}</td>
									<td class="text-center">
										<a href="${ctx }/statium/accredit/${acc.id}" class="btn btn-default btn-sm">修改授权信息</a>
										<a href="${ctx }/statium/accredit/delete/${acc.id}" class="btn btn-default btn-sm">取消授权</a>
									</td>
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
			menu.active('#statium-accredit');
			$('#adminFooter').hide();
		});
	</script>
</body>
</html>