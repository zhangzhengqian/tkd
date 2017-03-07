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
							<li class="active">订单列表</li>
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
				<div class="col-md-12">
					<form id="orderForm" class="form-horizontal"
						action="${ctx }/mall/orderList" method="post">
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input  type="text" class="form-control input-sm" id="statiumName" name="statiumName" value="${param.statiumName}"  placeholder="按商户名称查询">
								<input  type="hidden" class="form-control input-sm" id="statiumId"  name="search_EQ_statiumId"  value="${param.search_EQ_statiumId }">
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_status"
									name="search_EQ_status">
									<option value=""  <c:if test="${empty param.search_EQ_status}">selected="selected"</c:if>>--请选择订单状态--</option>
									<option value="1" <c:if test="${param.search_EQ_status == 1}">selected="selected"</c:if>>--待确认--</option>
									<option value="2" <c:if test="${param.search_EQ_status == 2}">selected="selected"</c:if>>--已发货--</option>
									<option value="3" <c:if test="${param.search_EQ_status == 3}">selected="selected"</c:if>>--已收货--</option>
									<option value="6" <c:if test="${param.search_EQ_status == 6}">selected="selected"</c:if>>--退款中--</option>
									<option value="5" <c:if test="${param.search_EQ_status == 5}">selected="selected"</c:if>>--已退款--</option>
								</select>
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
				<!-- 操作按钮组 -->
				<div class="col-md-12">
				</div>
			</div>
			<!-- /操作按钮组 -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>订单号</th>
								<th>客户名称</th>
								<th>金额(元)</th>
								<th>订单状态</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.content}" var="order" varStatus="stat">
								<tr <c:if test="${order.status=='1'}">class="warning"</c:if> id="${order.id}" >
									<td class="text-center">
										 ${order.id} </td>
									<td>
											${order.statiumName}:${order.shMan}
									</td>
									<td>
											${order.feeView}
									</td>
									<c:choose>
										<c:when test="${order.status == '0'}">
											<td class="warning">待付款</td>
										</c:when>
										<c:when test="${order.status == '1'}">
											<td class="warning">待确认</td>
										</c:when>
										<c:when test="${order.status == '2'}">
											<td class="success">已发货</td>
										</c:when>
										<c:when test="${order.status == '3'}">
											<td class="">已收货</td>
										</c:when>
										<c:when test="${order.status == '4'}">
											<td class="">已取消</td>
										</c:when>
										<c:when test="${order.status == '5'}">
											<td class="">已退款</td>
										</c:when>
										<c:when test="${order.status == '6'}">
											<td class="">退款中</td>
										</c:when>
									</c:choose>
									<td  style="cursor: pointer;"><a title="查看详情" href="${ctx }/mall/orderView/${order.id}">查看详情</a></td>
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
			menu.active('#mallOrders-man');
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

		//====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
			max: 30,
			autoFill: false,
			mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
			matchContains: true,
			scrollHeight: 220,
			width: $('#statiumName').outerWidth(),
			multiple: false,
			formatItem: function (row, i, max) {                    //显示格式
				return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
			},
			formatResult: function (row) {                      //返回结果
				return row.name;
			},
			handleValue:function(id){
				$('#statiumId').val(id);
			},
			parse:function(data){
				var parsed = [];
				var rows = data;
				for (var i=0; i < rows.length; i++) {
					var row = rows[i];
					if (row) {
						parsed[parsed.length] = {
							data: row,
							value: row["id"],
							result: this.formatResult(row)
						};
					}
				}
				return parsed;
			}
		});
		//====================================================
		// 自动匹配 场馆名称 <<<<
		//====================================================

	</script>

</body>
</html>
