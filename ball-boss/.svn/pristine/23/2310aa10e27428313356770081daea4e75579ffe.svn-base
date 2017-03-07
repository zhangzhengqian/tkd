<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>测评管理</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 测评管理</li>
				<li class="active">测评列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div class="btn-group-sm text-right">
					<a class="btn btn-primary btn-sm" href="${ctx}/assessment/add"><span
							class="glyphicon glyphicon-plus"></span> 新增测评</a>
				</div>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>测评标题</th>
								<th>发布时间</th>
								<th>浏览数</th>
								<th>评论数</th>
								<th>分享数</th>
								<th>点赞数</th>
								<th>状态</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="info" varStatus="stat">
								<tr>
									<td class="text-center" align="center">${stat.count }</td>
									<td class="text-center" align="center">${info.title }</td>
									<td class="text-center" align="center"><fmt:formatDate value="${info.sendTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td class="text-center" align="center">${info.lookNum}</td>
									<td class="text-center" align="center">${info.commentNum}</td>
									<td class="text-center" align="center">${info.shareNum}</td>
									<td class="text-center" align="center">${info.likeNum}</td>
									<td class="text-center" align="center">
									<c:choose>
										<c:when test="${info.sendType == 1}">
											已发送
										</c:when>
										<c:when test="${info.sendType == 0}">
											已撤回
										</c:when>
									</c:choose>
									</td>
									<td class="text-center" align="center">
										<a href="${ctx }/assessment/view/${info.id}" class="btn btn-default btn-sm">修改</a>
										<c:if test="${info.sendType==1}"><a class="btn btn-default btn-sm revoke-btn" assessment-id="${info.id }" href="javascript:;">撤回</a></c:if>
										<c:if test="${info.sendType==0}"><a class="btn btn-default btn-sm send-btn" assessment-id="${info.id }" href="javascript:;"> 发送</a></c:if>
										<a class="btn btn-default btn-sm delete-btn" assessment-id="${info.id }" href="javascript:;">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
		<tags:pagination page="${data}" />
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#assessment-man');
			$('#adminFooter').hide();
			
			$(".revoke-btn").on("click",function(){
				var assessmentId = $(this).attr("assessment-id");
				bootbox.confirm('撤回测评', function(result) {
					if(result) {
						window.location.href="${ctx}/assessment/revoke/"+assessmentId;
					}
				});
				return false;
			})
			
			$(".send-btn").on("click",function(){
				var assessmentId = $(this).attr("assessment-id");
				bootbox.confirm('发送测评', function(result) {
					if(result) {
						window.location.href="${ctx}/assessment/send/"+assessmentId;
					}
				});
				return false;
			})
			
			$(".delete-btn").on("click",function(){
				var assessmentId = $(this).attr("assessment-id");
				bootbox.confirm('删除测评', function(result) {
					if(result) {
						window.location.href="${ctx}/assessment/delete/"+assessmentId;
					}
				});
				return false;
			})
		});
	</script>
</body>
</html>