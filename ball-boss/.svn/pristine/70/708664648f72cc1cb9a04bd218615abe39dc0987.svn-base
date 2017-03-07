<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投票主题</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 资讯管理</li>
				<li class="active">资讯</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div class="btn-group-sm pull-right">
					<a class="btn btn-primary btn-sm" href="${ctx}/info/add"><span
							class="glyphicon glyphicon-plus"></span> 新增资讯</a>
				</div>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>资讯标题</th>
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
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${info.title }</td>
									<td class="text-center"><fmt:formatDate value="${info.sendTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td class="text-center">${info.lookNum}</td>
									<td class="text-center">${info.commentNum}</td>
									<td class="text-center">${info.shareNum}</td>
									<td class="text-center">${info.likeNum}</td>
									<td class="text-center">
									<c:choose>
										<c:when test="${info.sendType == 0}">
											草稿
										</c:when>
										<c:when test="${info.sendType == 1}">
											已发送
										</c:when>
										<c:when test="${info.sendType == 2}">
											已撤回
										</c:when>
										<c:when test="${info.sendType == 3}">
											等待发送
										</c:when>
									</c:choose>
									</td>
									<td class="text-center">
										<a href="${ctx }/info/view/${info.id}" class="btn btn-default btn-sm">查看</a>
										<c:if test="${info.sendType==1&&info.stick==0}"><a href="javascript:;" info-id="${info.id }" class="btn btn-default btn-sm static_btn">置顶</a></c:if>
										<c:if test="${info.sendType==1&&info.stick==1}"><span class="text-danger">置顶</span></c:if>
										<c:if test="${info.sendType==1&&info.stick==0}"><a class="btn btn-default btn-sm revoke-btn" info-id="${info.id }" href="javascript:;">撤回</a></c:if>
										<a class="btn btn-default btn-sm delete-btn" info-id="${info.id }" href="javascript:;">删除</a>
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
			menu.active('#info-man');
			$('#adminFooter').hide();
			$(".static_btn").on("click",function(){
				var infoId = $(this).attr("info-id");
				bootbox.confirm('置顶资讯', function(result) {
					if(result) {
						window.location.href="${ctx}/info/stick/"+infoId;
					}
				});
				return false;
			})
			
			$(".revoke-btn").on("click",function(){
				var infoId = $(this).attr("info-id");
				bootbox.confirm('撤销资讯', function(result) {
					if(result) {
						window.location.href="${ctx}/info/revoke/"+infoId;
					}
				});
				return false;
			})
			$(".delete-btn").on("click",function(){
				var infoId = $(this).attr("info-id");
				bootbox.confirm('删除资讯', function(result) {
					if(result) {
						window.location.href="${ctx}/info/delete/"+infoId;
					}
				});
				return false;
			})
		});
	</script>
</body>
</html>