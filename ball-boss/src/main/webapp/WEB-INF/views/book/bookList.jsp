<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>约球管理</title>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<div class="row">
				<div class="col-md-5">
					<div class="btn-group-sm">
						<ul class="breadcrumb" style="display: inline;">
							<li><span class="glyphicon glyphicon-home"></span> 约球管理</li>
							<li class="active">约球列表</li>
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
						action="${ctx }/book/list" method="post">
						<input type="hidden" id="search_EQ_status" name="search_EQ_status" />
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_orderId" name="search_LIKE_orderId"
									value="${param.search_LIKE_orderId }" placeholder="按订单号查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_EQ_userPhone" name="search_EQ_userPhone"
									value="${param.search_EQ_userPhone }" placeholder="按联系电话查询">
							</div>
						</div>

						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_EQ_userNickname" name="search_EQ_userNickname"
									value="${param.search_EQ_userNickname }" placeholder="按用户昵称查询">
							</div>
							<div class="col-md-5">
								<input id="statiumName" type="text"
									class="form-control input-sm" name="search_EQ_statiumName"
									value="${param.search_EQ_statiumName }" placeholder="按球馆名称查询">
							</div>
						</div>

						<div class="form-group form-group-sm query-more">
							<div class="form-group form-group-sm">
								<label class="control-label col-md-1 sr-only" for=""></label>
								<div class="col-md-5">
									<select class="form-control" id="search_LIKE_sportType"
										name="search_LIKE_sportType">
										<option value="">--请选约球类型--</option>
										<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
											<option value="${item.itemCode }">--${item.itemName }--</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-5">
									<input id="time" type="text" class="form-control input-sm"
										name="search_EQ_time" value="${param.search_EQ_time }"
										placeholder="按发布时间查询">
								</div>
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
								<button type="button" class="btn btn-link btn-sm"
									id="btn-query-more">
									<span class="glyphicon glyphicon-chevron-down"></span> 更多条件
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
					<div class="btn-group-sm pull-right mtb10">

						<div class="btn-group" role="group" aria-label="...">

							<a class="btn btn-sm btn-default"
								href="javascript:statusClick('')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								全部</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_PLAYING')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								交易成功</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_CANCELED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								交易关闭</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_REFUNDING')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								退款中</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_REFUNDED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已退款</a> <a class="btn btn-sm btn-warning"
								href="javascript:statusClick('ORDER_NEW')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								待付款</a> <a class="btn btn-sm btn-success"
								href="javascript:statusClick('ORDER_PAIED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已付款</a> <a class="btn btn-sm btn-info"
								href="javascript:statusClick('ORDER_VERIFY')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已确认</a>

						</div>


					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->


			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>订单号</th>
								<th>场馆</th>
								<th>会员姓名</th>
								<th>联系电话</th>
								<th>实付(元)</th>
								<th>成本(元)</th>
								<th>补贴金额(元)</th>
								<th>下单时间</th>
								<th>最后处理</th>
								<th>订单状态</th>
								<th>支付方式</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.content}" var="order" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td><a href="${ctx }/book/view/${order.statiumId}">${order.id}</a></td>
									<td>${order.statiumName }</td>
									<td>${order.username }</td>
									<td>${order.userPhone }</td>
									<td>${lf:formatMoney(order.finalFee) }</td>
									<td>${lf:totalCostPrice(order.id) }</td>
									<td>${lf:totalSubsidies(order.id) }</td>
									<td><fmt:formatDate value="${order.ct}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${order.et}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>

									<c:choose>
										<c:when test="${order.status == 'ORDER_NEW'}">
											<td class="warning">待付款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_VERIFY'}">
											<td class="info">已确认</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_PLAYING'}">
											<td class="">交易成功</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_PAIED'}">
											<td class="success">已付款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_REFUNDING'}">
											<td class="">退款中</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_REFUNDED'}">
											<td class="">已退款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_CANCELED'}">
											<td class="">交易关闭</td>
										</c:when>
										<c:when test="${order.status == null}">
											<td class=""></td>
										</c:when>
									</c:choose>

									<td><c:choose>
											<c:when test="${order.payType == '1'}">支付宝付款</c:when>
											<c:when test="${order.payType == '2'}">微信付款</c:when>
											<c:when test="${order.payType == '3'}">公众平台付款</c:when>
											<c:when test="${order.payType == '4'}">球友圈付款</c:when>
											<c:when test="${order.payType == null}">&nbsp;</c:when>
										</c:choose></td>



									<td><a href="${ctx }/book/view/${order.statiumId}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 详情</a> <shiro:hasPermission
											name="orders:verify">
											<c:if test="${order.status == 'ORDER_PAIED' }">
												<a href="javascript:orderVerify('${order.id}')"
													class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 确认</a>
											</c:if>
										</shiro:hasPermission> <shiro:hasPermission name="orders:applyRefund">
											<c:if
												test="${order.status == 'ORDER_PAIED'||order.status == 'ORDER_VERIFY'}">
												<a href="javascript:applyRefund('${order.id}')"
													class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 申请退款</a>
											</c:if>
										</shiro:hasPermission> <shiro:hasPermission name="orders:confirmRefund">
											<c:if test="${order.status == 'ORDER_REFUNDING' }">
												<a onclick="confirmRefund('${order.id}')"
													href="javascript:void(0)" class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 确认退款</a>
											</c:if>
										</shiro:hasPermission></td>
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

	<div class="modal fade" id="backorderModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">退款原因</h4>
				</div>
				<div class="modal-body" class="">
					<form class="form-inline" method="POST" id="backorderForm">
						<div id="backreason" class="form-group">
							<label>原因：</label> <select id="reason1"
								class="form-control input-sm" name="reason1">
								<option value="">请选择</option>
								<option value="用户要求退款">用户要求退款</option>
								<option value="场地被占用">场地被占用</option>
							</select>
						</div>
						<div id="backtextDiv" class="form-group">
							<label>其他原因：</label> <input class="form-control input-sm"
								id="reason" type="text" name="reason" />
						</div>
						<input type="hidden" id="backOrderDetail" value="" name="orderId">
						<input type="hidden" value="ORDER_REFUNDING" name="status">
					</form>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="backForm()"
						data-dismiss="modal" aria-hidden="true">确定</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function confirmRefund(v) {
			var num = 0;
			bootbox.confirm('是否确认退款' + v + '订单?', function(result) {
				if (result && !num) {
					num++;
					$.ajax({
						cache : true,
						type : "POST",
						url : '${ctx}/orders/confirmRefund/' + v,
						data : {},
						async : false,
						error : function(request) {
							common.showMessage("确认退款失败！");
						},
						success : function(data) {
							data = eval("(" + data + ")");
							if (!data.result || data.result == 'false') {
								if (!data.reason) {
									common.showMessage("确认退款失败！");
								} else {
									common.showMessage(data.reason);
								}

							} else {
								common.showMessage("确认退款成功！");
								setTimeout(function() {
									var $form = $('#orderForm');
									$form[0].submit();
								}, 1000);
							}
						}
					});
				}
			});
			return false;
		}

		function orderVerify(v) {
			var num = 0;
			bootbox.confirm('是否确认' + v + '订单?', function(result) {
				if (result && !num) {
					num++;
					window.location.href = "${ctx}/orders/orderVerify/" + v;
				}
			});
		}

		function applyRefund(v) {
			$("#backOrderDetail").val(v);
			$("#backorderModel").modal();
		};

		function backForm() {
			if (!$("#reason1").val() && !$("#reason").val()) {
				bootbox.alert('请填写退款原因！');
				return false;
			}
			$("#backorderForm").attr("action", "${ctx}/orders/applyRefund");
			$("#backorderForm").submit();
		};

		$(function() {
			menu.active('#book-man');

			var v_search_EQ_ordersType = '${param.search_EQ_ordersType}';
			if (v_search_EQ_ordersType) {
				$(
						'#search_EQ_ordersType option[value='
								+ v_search_EQ_ordersType + ']').attr(
						'selected', 'selected');
			}

			$("button[type=reset]")
					.click(
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
			$('#adminFooter').hide();

		});

		function statusClick(v) {
			$("#search_EQ_status").attr("value", v);
			$("#orderForm").submit();
		}
	</script>

</body>
</html>
