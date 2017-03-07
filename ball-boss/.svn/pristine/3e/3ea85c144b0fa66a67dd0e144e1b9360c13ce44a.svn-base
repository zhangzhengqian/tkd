<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业积分</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>企业积分列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/company/integraLlist" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_name" name="search_LIKE_name"
									value="${param.search_LIKE_name}" placeholder="按企业用户名称查询">
							</div>

							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									   id="search_EQ_month" name="search_EQ_month"
									   value="${param.search_EQ_month}" placeholder="按月份查询"
									   onclick='WdatePicker({"dateFmt":"yyyy-MM"});' readonly>
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
						<input type="hidden" name="search_EQ_state" id="search_EQ_state" value="${param.search_EQ_state}" >
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->

			<!-- /操作按钮组 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">积分排名</th>
								<th>企业名称</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>月份 </th>
								<th>积分</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						    <c:set var="integralOrder" value="0" />
							<c:forEach items="${data.content}" var="info" varStatus="stat">
								<c:set var="integralOrder" value="${integralOrder+stat.count}" />
								<tr class="info" >
									<td class="text-center">${integralOrder}</td>
								<td>
									 ${info.name}
								</td>
								<td>
									 ${info.linkMan}
								</td>
								<td>
									 ${info.linkPhone}
								</td>
								<td>
									 ${info.month}
								</td>
								<td>${info.integral}</td>
								<td><a class="btn btn-default btn-sm" href="#" onclick="updateIntegral('${info.id}')"><i 	class="glyphicon glyphicon-edit"></i>积分修改</a>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</table></form>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
				<tags:pagination page="${data}" />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			if("${message}" != null && "${message}" != ""){
				myAlert("${message}");
			}
			menu.active('#companyIntegral-man');
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

		//修改积分
		function updateIntegral(id){
			$("#myDlgBody_lg").load("${ctx}/company/updateIntegral_dlg?id="+id+"&id="+id);
			$("#myDlgBody_lg").css("width","802px");
			$("#myDlg_lg").modal();
		}

	</script>
</body>
</html>