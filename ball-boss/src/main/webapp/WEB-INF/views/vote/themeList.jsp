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
				<li><span class="glyphicon glyphicon-home"></span> 投票管理</li>
				<li class="active">投票主题</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div class="btn-group-sm pull-right">
					<a class="btn btn-primary btn-sm" href="${ctx}/vote/create?action=create"><span
							class="glyphicon glyphicon-plus"></span> 添加主题</a>
				</div>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>投票主题</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>投票地址</th>
								<th>创建人</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="info" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${info.title }</td>
									<td class="text-center">${info.startTime}</td>
									<td class="text-center">${info.endTime}</td>
									<td class="text-center">${weixinDomain}votes/voting/${info.id}</td>
									<td class="text-center"><tags:getUserById id="${info.cb }" /></td>
									<td class="text-center">
										<a href="${ctx }/vote/memberList/${info.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i>投票候选人管理</a>
										<a href="${ctx }/vote/detail/${info.id}?action=edit" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 查看</a>
										<a class="btn btn-default btn-sm"
										   href="${ctx}/vote/detail/${info.id}?action=edit"><i
												class="glyphicon glyphicon-edit"></i>修改</a>
										<a class="btn btn-danger btn-sm" href="#"
										   onclick="deleteById('${info.id}')">
											<i class="glyphicon glyphicon-trash"></i> 删除
										</a>
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
		<form id="createForm" action="${ctx }/vote/memberList"  method="post">
			<input type="hidden" id="ids" name="ids">
		</form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#voteTheme-man');
			$('#adminFooter').hide();
		});

		function deleteById(id){
			var $form = $('#actionForm');
			bootbox.confirm('您确定要删除该主题吗？', function(result) {
				if(result) {
					Util.getData(ctx + '/vote/delTheme', function(data){
						if(data.result){
							myAlert("删除成功");
							window.location.reload()
						}else{
							myAlert("删除失败","error");
						}
					}, null, {"id":id}, 'post');
				}
			});
			return false;
		}
	</script>
</body>
</html>