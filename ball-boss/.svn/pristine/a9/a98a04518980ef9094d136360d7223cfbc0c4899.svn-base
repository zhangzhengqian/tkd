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
				<li><span class="glyphicon glyphicon-home"></span>企业用户</li>
				<li>企业详情</li>
				<li>企业员工消费列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
			</div>
			<!-- /操作按钮组 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">	
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>订单号</th>
								<th>姓名</th>
								<th>手机号码</th>
								<th>订单时间</th>
								<th>类别</th>
								<th>金额</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data}" var="info" varStatus="stat">
							<td class="text-center">${stat.count}</td>
								<td>
									 ${info.orderId}
								</td>
								<td>
									 ${info.userName}
								</td>
								<td>
									 ${info.phone}
								</td>
								<td><fmt:formatDate value="${info.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									${info.ordersType }
								</td>
								<td>
									 ${info.useAmout/100}元
								</td>
									<td><a href="${ctx }/orders/view/${info.orderId}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 详情</a> 
								  
								</td>

								</tr>
							</c:forEach>
						</tbody>
					</table></form>
					<div class="form-group form-group-sm">
						<div class="col-md-12 text-center">
							<a class="btn btn-default btn-sm" href="${ctx}/company/list">
								<span class="glyphicon"></span> 返回
							</a> &nbsp;&nbsp;
						</div>
					</div>
					
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
			menu.active('#company-man');
			$('#adminFooter').hide();
		});
	</script>
</body>
</html>