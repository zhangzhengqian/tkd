<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投票人列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 投票管理</li>
				<li class="active">投票人列表</li>
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
								<th>投票人姓名</th>
								<th>投票人照片</th>
								<th>投票人电话</th>
								<th>注册时间</th>
								<th>投票时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="user" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count } <input type="checkbox"
								name="chk_list" id="chk_list_${stat.index }" value="${user.id}" /></td>
									<td class="text-center">${user.userName}</td>
									<td class="text-center">
										<img alt=""  style="<c:if test='${!empty user.userPhoto}'>width:128px;height:128px;</c:if>"
																 src="${user.userPhoto}"></td>
									<td class="text-center">${user.phone}</td>
									<td class="text-center">${user.registTime}</td>
									<td class="text-center">${user.createTime}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
		<tags:pagination page="${data}" />
			<div class="col-md-6">
				<div class="btn-group-sm pull-right">
					投票人数总计：${totalParticipants}
				</div>
			</div>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
</body>
</html>