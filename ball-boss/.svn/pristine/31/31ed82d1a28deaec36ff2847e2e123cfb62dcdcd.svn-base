<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>历史参赛人员信息</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li class="active">乐享赛事</li>
				<li class="active">历史参赛成员</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<form id="search_form" class="form-horizontal"
					  action="${ctx}/enjoyRace/playersList" method="post">
					<div class="form-group form-group-sm">
						<div class="col-md-5">
							<input type="text" class="form-control input-sm"
								   id="search_LIKE_name" name="search_LIKE_name"
								   value="${param.search_LIKE_name}" placeholder="按姓名查询">
						</div>
						<div class="col-md-5">
							<input type="text" class="form-control input-sm"
								   id="search_EQ_phone" name="search_EQ_phone"
								   value="${param.search_EQ_phone}" placeholder="按手机号查询">
						</div>
					</div>

					<div class="form-group form-group-sm">
						<div class="col-md-12 text-center">
							<button type="reset" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-refresh"></span> 重 置
							</button>
							&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-search"></span> 搜 索
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>姓名</th>
								<th>身份证号</th>
								<th>性别</th>
								<th>手机号码</th>
								<th>授权状态</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="user" varStatus="stat">
								<tr
									class="<c:if test="${user.state == 0}">warning</c:if><c:if test="${user.state == 1}">success</c:if><c:if test="${user.state == 2}">danger</c:if>">
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${user.nameA }</td>
									<td class="text-center">${user.cardNoA}</td>
									<td class="text-center"><c:if test="${user.sexA == 0}">女</c:if><c:if test="${user.sexA == 1}">男</c:if></td>
									<td class="text-center">${user.phoneA}</td>
									<td>
										<c:if test="${user.state != 1 }">
												未授权
										</c:if>
										<c:if test="${user.state == 1 }">
												已授权
										</c:if>

									</td>
									<td class="text-center">
									 	 <a class="btn btn-default btn-sm"  href="#" onclick="updatePlayerCardNo('${user.idA }')"><i class="glyphicon glyphicon-edit"></i>	修改人员信息</a>
									<c:if test="${user.state != 1 }">
										 <a  class="btn btn-default btn-sm" href="#" onclick="authority('${user.eliId }',1)" ><i class="glyphicon glyphicon-edit"></i>	授权app用户修改身份证</a>
									</c:if>
									<c:if test="${user.state == 1}">
										 <a  class="btn btn-default btn-sm"  href="#" onclick="authority('${user.eliId }',0)"><i class="glyphicon glyphicon-edit"></i>	取消app用户修改身份证权限</a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="form-group form-group-sm">
						<div class="col-md-12 text-center">
							<a class="btn btn-default btn-sm" href="${ctx}/enjoyRace/playersList">
								<span class="glyphicon"></span> 返回
							</a> &nbsp;&nbsp;
						</div>
					</div>

				</div>
				<!-- end col-table -->
			</div>
		</div>
		<tags:pagination page="${data}" />
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#playersList-man');
			$('#adminFooter').hide();

			$("button[type=reset]").click(
					function() {
						$(this).closest("form").find("input").attr(
								"value", "");
						$(this).closest("form").find(
								"select option:selected").attr(
								"selected", false);
						$(this).closest("form").find(
								"select option:first").attr("selected",
								true);
					});
		});

		//修改报名人员身份证信息
		function updatePlayerCardNo(id){
			$("#myDlgBody_lg").load("${ctx}/enjoyRace/updatePlayerCardNo_dlg?id="+id);
			$("#myDlgBody_lg").css("width","802px");
			$("#myDlg_lg").modal();
		}


		function authority(userId,state){
			var $form = $('#actionForm');
			Util.getData(ctx + '/enjoyRace/authority/', function(data){
				if(data.result){
					myAlert("授权成功");
					window.location.reload()
				}else{
					myAlert(data.reason,"error");
				}
			}, null, {"userId":userId,"state":state}, 'post');
		}

	</script>
</body>
</html>