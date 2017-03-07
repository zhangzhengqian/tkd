<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业订单</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>企业订单</li>
				<li>待审核订单列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/company/orderList" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_name" name="search_EQ_id"
									value="${param.search_EQ_id}" placeholder="按订单号查询">
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
			</div>
			<!-- /查询条件 -->
			<div class="row">
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
								<th>企业名称</th>
								<th>创建时间</th>
								<th>套餐类型</th>
								<th>订单总额（元） </th>
								<th>成本总额（元）</th>
								<th>利润总额（元）</th>
								<th>类型</th>
								<th>创建人</th>
								<th>销售主管审核</th>
								<th>客服审核</th>
								<th>财务确认</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="info" varStatus="stat">
								<tr>
								<td class="text-center">${stat.count}</td>
								<td>  ${info.id } </td>
								<td>
									 ${info.name}
								</td>
								 <td><fmt:formatDate value="${info.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									 ${info.serviceType_}
								</td>
								<td>
									 ${info.fee/100}元
								</td>
								<td>
									 ${info.costFee/100}元
								</td>
								<td>
									 ${info.fee/100-info.costFee/100}元
								</td>
								<td>
									${info.type}
								</td>
								<td><tags:getUserById id="${info.cb }" /></td>
								<td>
									${lf:getStatusNames(info.payType,info.serviceType,info.status,info.flowState)[0]}
								</td>
								<td>
									${lf:getStatusNames(info.payType,info.serviceType,info.status,info.flowState)[1]}
								</td>
								<td>
									${lf:getStatusNames(info.payType,info.serviceType,info.status,info.flowState)[2]}
								</td>
								<td><a href="${ctx }/company/service/view/${info.id}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 查看详情</a>
											<shiro:hasPermission name="service:financeAudit">
									<c:if test="${info.status == 0 &&  info.flowState==3}">
											<c:if test="${info.serviceType == 3 }">
												<a href="${ctx}/company/financeAudit?id=${info.id}&status=0"
											id="editLink-${info.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i>确认提现</a>
											 </c:if>
											 <c:if test="${info.serviceType == 0|| info.serviceType == 1||info.serviceType == 2}">
											 	<a href="${ctx}/company/financeAudit?id=${info.id}&status=1"
											id="editLink-${info.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i>确认收款</a>
											 </c:if>
									</c:if>
									</shiro:hasPermission>
									<shiro:hasPermission name="service:customAudit">
									<c:if test="${info.status == 0 && info.flowState==2}">
											<a href="${ctx}/company/customerAudit?id=${info.id}&status=1"
										id="editLink-${info.id}" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-ok"></i>通过</a>
										 	<a href="${ctx}/company/customerAudit?id=${info.id}&status=0"
										id="editLink-${info.id}" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-remove"></i>不通过</a>
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
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#companyOder-man');
			$('#adminFooter').hide();
			
			  /* 按状态查询 */
			  $(".searchStatus").click(function(){
				  var v = $(this).val();
				  $("#search_EQ_state").val(v);
				  $("#search_form").submit();
			  });

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