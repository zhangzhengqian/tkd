<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商城管理</title>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<div class="row">
				<div class="col-md-5">
					<div class="btn-group-sm">
						<ul class="breadcrumb" style="display: inline;">
							<li><span class="glyphicon glyphicon-home"></span>商城管理</li>
							<li class="active">活动列表</li>
						</ul>
					</div>
				</div>
				<div class="col-md-7"></div>
			</div>

		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->

			<div class="row">
				<!-- 查询条件 -->

			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
				</div>
			</div>
			<!-- /操作按钮组 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-5 form-inline">
				</div>
				<div class="col-md-7 text-right">
					<div class="btn-group-sm pull-right mtb10">
						<shiro:hasPermission name="enjoyrace:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/mall/createActivity?action=create"><span
									class="glyphicon glyphicon-plus"></span> 添加活动</a>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->
			<br>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>编号</th>
								<th>商品</th>
								<th>时间</th>
								<th>内容</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.content}" var="activity" varStatus="stat">
								<tr>
								<td class="text-center">
									${stat.index +1}
								</td>
									<td>
										<c:forEach items="${activity.crmMallGoodsList}" var="goods" >
											<img alt=""
												 style="<c:if test='${!empty goods.photo}'>width:128px;height:128px;</c:if>"
												 src="${goods.photo}"">${goods.name}(${goods.bulk}*${goods.amount} 整${goods.unit})
											<br/>
										</c:forEach>
									</td>

									<td>
											${activity.startTime}至${activity.endTime}
									</td>
									<td>
										订购数量满${activity.buyAmount},赠${activity.goodsName}${activity.giveAmount}
									</td>
									<td>${activity.statusStr}</td>
									<td  style="cursor: pointer;">
										<a class="btn btn-default btn-sm"  href="${ctx }/mall/activityForm/${activity.id}"><i class="glyphicon glyphicon-edit"></i>修改</a>
										<a class="btn btn-danger btn-sm" href="${ctx }/mall/delActivity/${activity.id}"> <i class="glyphicon glyphicon-trash"></i> 删除 </a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<tags:pagination page="${data}" />
			<tags:errors />
			<form id="actionForm" action="" method="post">
				<input type="hidden" id="ids" name="search_IN_id">
			</form>

		</div>
		<!-- /右侧主体内容 -->
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#mallctivity-man');
			$('#adminFooter').hide();
			var v_search_EQ_status = '${param.search_EQ_status}';
			if (v_search_EQ_status) {
				$(
						'#search_EQ_status option[value='
								+ v_search_EQ_status + ']').attr(
						'selected', 'selected');
			}
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
	</script>

</body>
</html>
