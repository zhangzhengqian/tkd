<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业服务</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>企业服务列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/company/services" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_name" name="search_LIKE_name"
									value="${param.search_LIKE_name}" placeholder="按企业用户名称查询">
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
								&nbsp;&nbsp;
								<button type="button" onclick="exportData()" class="btn btn-success btn-sm">
									<span class="glyphicon glyphicon-export"></span> 导出
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-5 form-inline">
				</div>
				 <div class="col-md-7">
					<div class="btn-group-sm pull-right mtb10">
					<shiro:hasPermission name="service:save">
						<a class="btn btn-primary btn-sm" href="${ctx}/company/service/statistic"><span
							class="glyphicon glyphicon-search"></span> 统计报表</a>
					</shiro:hasPermission>
						<a class="btn btn-primary btn-sm" href="${ctx}/company/service/create"><span
							class="glyphicon glyphicon-plus"></span> 新建订单</a>
					</div>
				</div>
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
								<th>企业名称</th>
								<th>创建时间</th>
								<th>套餐类型</th>
								<th>订单总额(元)</th>
								<th>成本总额(元)</th>
								<th>利润总额(元)</th>
								<th>销售审核</th>
								<th>客服确认</th>
								<th>财务确认</th>
								<th>创建人</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="info" varStatus="stat">
							  	<td class="text-center">${stat.count}</td>
								<td class="text-center">${info.name}</td>
								<td class="text-center"><fmt:formatDate value="${info.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="text-center">${info.serviceType_}</td>
								<td class="text-center">${lf:formatMoney(info.fee)}</td>
								<td class="text-center">${lf:formatMoney(info.costFee)}</td>
								<td class="text-center">${lf:formatMoney(info.fee-info.costFee)}</td>
								<c:forEach items="${lf:getStatusNames(info.payType,info.serviceType,info.status,info.flowState)}" var="flowStateName">
									<td class="text-center">${flowStateName}</td>
								</c:forEach>
								<td class="text-center">${info.cb}</td>
								<td class="text-center">
								<a href="${ctx }/company/service/view/${info.id}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 查看详情</a>
											<shiro:hasPermission name="service:audit">
											<c:if test="${info.flowState==1&&info.status==0}">
												<a href="${ctx }/company/service/auditService/${info.id}/1"
											class="btn btn-default btn-sm"><span
												class="glyphicon glyphicon-search"></span> 审核通过</a>
											</c:if> 
											<c:if test="${info.flowState==1&&info.status==0}">
												<a href="${ctx }/company/service/auditService/${info.id}/0"
											class="btn btn-default btn-sm"><span
												class="glyphicon glyphicon-search"></span> 审核拒绝</a>
											</c:if> 
											<c:if test="${(info.serviceType==0||info.serviceType==1||info.serviceType==3)&&info.status==0&&(info.flowState==2||info.flowState==3)}">
												<a href="${ctx }/company/service/auditService/${info.id}/2"
											class="btn btn-default btn-sm"><span
												class="glyphicon glyphicon-search"></span> 取消</a>
											</c:if>
											</shiro:hasPermission>											
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
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#company-services');
			$('#adminFooter').hide();
		});

		function exportData() {
			var $form = $('#search_form');
			$form.attr("action","${ctx }/company/exportServices");
			$form[0].submit();
		}
	</script>
</body>
</html>