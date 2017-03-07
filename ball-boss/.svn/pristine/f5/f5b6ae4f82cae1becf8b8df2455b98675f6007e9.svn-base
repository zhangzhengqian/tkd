<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投票候选人</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 投票管理</li>
				<li class="active">投票候选人</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div class="btn-group-sm pull-right">
					<a class="btn btn-primary btn-sm" id='changeUserState' href="javascript:;" themeId="${themeId }"><span
							class="glyphicon glyphicon-plus"></span> 显示</a>
					<a class="btn btn-primary btn-sm" id='addVotes' href="javascript:;"><span
							class="glyphicon glyphicon-plus"></span> 加票</a>
					<a class="btn btn-primary btn-sm" href="${ctx}/vote/createMember/${themeId}?action=create"><span
							class="glyphicon glyphicon-plus"></span> 添加候选人</a>
				</div>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center"><input type="checkbox" name="chk_all"
							onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /> 序号</th>
								<th>投票主题</th>
								<th>候选人姓名</th>
								<th>候选人手机号</th>
								<th>候选人票数</th>
								<th>创建人</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="user" varStatus="stat">
								<tr class="listTr" themeId="${user.themeId }" candidateId="${user.id }">
									<td class="text-center">${stat.count } <input type="checkbox"
								name="chk_list" id="chk_list_${stat.index }" value="${user.id}" /></td>
									<td class="text-center">
										<img alt=""  style="<c:if test='${!empty user.userPhoto}'>width:128px;height:128px;</c:if>"
																 src="${user.userPhoto}"></td>
									<td class="text-center">${user.userName}</td>
									<td class="text-center">${user.userPhone}</td>
									<td class="text-center">${user.votesNumber}</td>
									<td class="text-center"><tags:getUserById id="${user.cb }" /></td>
									<td class="text-center">
										<%-- <a href="${ctx }/vote/participantList/${themeId}/${user.id}?action=view"  class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 投票列表</a> --%>
										<a href="${ctx }/vote/memberDetail/${user.id}?action=view" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 查看</a>
										<a href="javascript:;" themeId="${user.themeId }" candidateId="${user.id }" class="btn btn-default btn-sm addBtn"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 加票</a>
										<a class="btn btn-default btn-sm"
										   href="${ctx}/vote/memberDetail/${user.id}?action=edit"><i
												class="glyphicon glyphicon-edit"></i>修改</a>
										<a class="btn btn-danger btn-sm" href="#"
										   onclick="deleteById('${user.id}')">
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
		<form id="addVoteForm" action="${ctx }/vote/addVotes/${themeId}"  method="post">
			<input type="hidden" id="ids" name="ids">
			<input type="hidden" id="votesNumber" name="votesNumber">
		</form>
			<div class="col-md-6">
				<div class="btn-group-sm pull-right">
					投票人数总计：${totalParticipants}
				</div>
			</div>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".listTr").on("click",function(){
				var themeId = $(this).attr("themeId");
				var candidateId = $(this).attr("candidateId");
				window.location.href=ctx + '/vote/participantList/'+themeId+'/'+candidateId + '?action=view' ;
			})
		});
		
		$(function(){
			$("#changeUserState").on("click",function(){
				var ids = getSelected();
				if(ids.length==0){
					return;
				}
				ids = ids.join(',');
				var themeId = $(this).attr("themeId");
				window.location.href=ctx + '/vote/updateUserState/'+themeId+'/'+ids;
			})
		});
		
		$(function() {
			menu.active('#voteTheme-man');
			$('#adminFooter').hide();
			$(".addBtn").on("click",function(){
				var votesNumber = window.prompt("请输入票数");
				if(votesNumber){
					var themeId = $(this).attr("themeId");
					var candidateId = $(this).attr("candidateId");
					window.location.href=ctx + '/vote/addVotes/'+themeId+'/'+candidateId+'/'+votesNumber;
				}else{
					myAlert("请输入票数","error");
					return;
				}
			})
			$("#addVotes").on("click",function(){
				var ids = getSelected();
				if(ids.length==0){
					return;
				}
				ids = ids.join(',');
				var votesNumber = window.prompt("请输入票数");
				if(votesNumber){
					$("#votesNumber").val(votesNumber);
					$("#ids").val(ids);
					$("#addVoteForm").submit();
				}else{
					myAlert("请输入票数","error");
					return;
				}
			})
		});

		function deleteById(id){
			var $form = $('#actionForm');
			bootbox.confirm('您确定要删除该主题吗？', function(result) {
				if(result) {
					Util.getData(ctx + '/vote/delMember', function(data){
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
		function getSelected() {
			var ids = [];
			var checked = $('input:checked');
			if (checked.length) {
				checked.each(function() {
					if ($(this).attr('name') != 'chk_all') {
						ids.push($(this).val());
					}
				});
			}
			return ids;
		}
	</script>
</body>
</html>