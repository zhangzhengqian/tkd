<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>约球管理</title>
<style type="text/css">
</style>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 约球管理</li>
				<li class="active">约球详情</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容  style="background-color: #CDFFFF;"  -->
			<div class="panel panel-default">
				<div class="panel-heading">订单详情</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 text-right">订单号：</div>
						<div class="col-md-6">${order.id}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">订单状态：</div>
						<div class="col-md-6">
							<c:choose>
								<c:when test="${order.status == 'ORDER_NEW'}">待付款</c:when>
								<c:when test="${order.status == 'ORDER_CANCELED'}">交易关闭</c:when>
								<c:when test="${order.status == 'ORDER_PLAYING'}">交易成功</c:when>
								<c:when test="${order.status == 'ORDER_PAIED'}">已付款</c:when>
								<c:when test="${order.status == 'ORDER_REFUNDING'}">退款中</c:when>
								<c:when test="${order.status == 'ORDER_REFUNDED'}">已退款</c:when>
								<c:when test="${order.status == 'ORDER_VERIFY'}">已确认</c:when>
							</c:choose>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">订单类型：</div>
						<div class="col-md-6">
							<c:choose>
								<c:when test="${order.ordersType == '0'}">订场订单</c:when>
								<c:when test="${order.ordersType == '1'}">教陪练订单</c:when>
								<c:when test="${order.ordersType == '2'}">活动订单</c:when>
								<c:when test="${order.ordersType == '3'}">约球订单</c:when>
							</c:choose>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">订单金额：</div>
						<div class="col-md-6">${lf:formatMoney(order.fee) }元</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">优惠券抵扣：</div>
						<div class="col-md-6">
							<c:if test="${empty order.couponAmount}">
							</c:if>
							<c:if test="${!empty order.couponAmount}">
  			${lf:formatMoney(order.fee)-lf:formatMoney(order.finalFee)}元
  			</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">实付金额：</div>
						<div class="col-md-6">${lf:formatMoney(order.finalFee) }元</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">成本金额：</div>
						<div class="col-md-6">${lf:totalCostPrice(order.id) }元</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">补贴金额：</div>
						<div class="col-md-6">${lf:totalSubsidies(order.id) }元</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">订单创建时间：</div>
						<div class="col-md-6">
							<fmt:formatDate value="${order.ct}" pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">付款人：</div>
						<div class="col-md-6">${order.userName}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">付款人联系电话：</div>
						<div class="col-md-6">${order.userPhone}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">支付方式：</div>
						<div class="col-md-6">
							<c:choose>
								<c:when test="${order.payType == '1'}">支付宝付款</c:when>
								<c:when test="${order.payType == '2'}">微信付款</c:when>
								<c:when test="${order.payType == '3'}">公众平台付款</c:when>
								<c:when test="${order.payType == '4'}">球友圈付款</c:when>
							</c:choose>
						</div>
					</div>
					<c:if test="${order.payType == '1' or order.payType == '2'}">
						<div class="row">
							<div class="col-md-2 text-right">
								<c:if test="${order.payType == '1'}">
    			支付宝交易号：
    		</c:if>
								<c:if test="${order.payType == '2'}">
    			微信交易号：
    		</c:if>
							</div>
							<div class="col-md-6">${order.number}</div>
						</div>
					</c:if>
					<br />
					<table class="table table-bordered table-condensed table-hover">
						<c:if test="${order.ordersType == '3'}">
							<thead>
								<tr>
									<th style="border-bottom-width: 0px;">编号</th>
									<th style="border-bottom-width: 0px;">球馆名称</th>
									<th style="border-bottom-width: 0px;">预订场号</th>
									<th style="border-bottom-width: 0px;">预约时间</th>
									<th style="border-bottom-width: 0px;">球馆联系电话</th>
									<th style="border-bottom-width: 0px;">所在城市</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${order.orderItemVoList}" var="item"
									varStatus="stat">
									<tr>
										<td class="text-center">${stat.count }</td>
										<td>${item.statiumDetail.name }</td>
										<td>${item.spaceName }</td>
										<td>${item.orderTimeStr }</td>
										<td>${item.statiumDetail.tel }</td>
										<td>${order.areaStr }</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
					</table>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">用户信息</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 text-right">用户ID：</div>
						<div class="col-md-6">${data.qiuyouno}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">用户名：</div>
						<div class="col-md-6">${data.userName }</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">联系电话:：</div>
						<div class="col-md-6">${data.userPhone }</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">约球信息</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 text-right">标题：</div>
						<div class="col-md-6">${data.title}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">约球类型：</div>
						<div class="col-md-6">
							<c:if test="${book.sportType == 'BOWLING'}">保龄球 &nbsp;</c:if>
							<c:if test="${data.sportType == 'BILLIARDS'}">台球 &nbsp;</c:if>
							<c:if test="${data.sportType == 'TABLE_TENNIS'}">乒乓球 &nbsp;</c:if>
							<c:if test="${data.sportType == 'FOOTBALL'}">足球 &nbsp;</c:if>
							<c:if test="${data.sportType == 'BASKETBALL'}">篮球 &nbsp;</c:if>
							<c:if test="${data.sportType == 'TENNIS'}">网球 &nbsp;</c:if>
							<c:if test="${data.sportType == 'GOLF'}">高尔夫 &nbsp;</c:if>
							<c:if test="${data.sportType == 'BADMINTON'}">羽毛球 &nbsp;</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">场馆名称：</div>
						<div class="col-md-6">${data.statiumName}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">详细地址：</div>
						<div class="col-md-6">${data.address}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">约球人数：</div>
						<div class="col-md-6">${data.number}</div>
					</div>
					<div class="row">
						<div class="col-md-2 text-right">人均费用：</div>
						<div class="col-md-6">${data.avgPrice/100}元</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<form id="orderForm" class="form-horizontal"
						action="${ctx }/book/view/${order.id}" method="post">
				<div class="panel-heading">参与人员</div>
				
				<div class="panel-body">
					<table class="table table-bordered table-condensed table-hover">
							<thead>
								<tr>
									<th style="border-bottom-width: 0px;">用户名</th>
									<th style="border-bottom-width: 0px;">ID</th>
									<th style="border-bottom-width: 0px;">订单号</th>
									<th style="border-bottom-width: 0px;">订单金额</th>
									<th style="border-bottom-width: 0px;">实付金额</th>
									<th style="border-bottom-width: 0px;">状态</th>
									<th style="border-bottom-width: 0px;">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${memberList}" var="item" varStatus="stat">
									<tr>
										<td>${item.userName }</td>
										<td>${item.customerId }</td>
										<td>${item.id }</td>
										<td>${item.fee/100 }</td>
										<td>${item.finalFee/100 }</td>
										<c:choose>
											<c:when test="${item.status == 'ORDER_NEW'}">
												<td class="warning">待付款</td>
											</c:when>
											<c:when test="${item.status == 'ORDER_VERIFY'}">
												<td class="info">已确认</td>
											</c:when>
											<c:when test="${item.status == 'ORDER_PLAYING'}">
												<td class="">交易成功</td>
											</c:when>
											<c:when test="${item.status == 'ORDER_PAIED'}">
												<td class="success">已付款</td>
											</c:when>
											<c:when test="${item.status == 'ORDER_REFUNDING'}">
												<td class="">退款中</td>
											</c:when>
											<c:when test="${item.status == 'ORDER_REFUNDED'}">
												<td class="">已退款</td>
											</c:when>
											<c:when test="${item.status == 'ORDER_CANCELED'}">
												<td class="">交易关闭</td>
											</c:when>
											<c:when test="${item.status == null}">
												<td class=""></td>
											</c:when>
										</c:choose>
										<td><shiro:hasPermission
											name="orders:verify">
											<c:if test="${item.status == 'ORDER_PAIED' }">
												<a href="javascript:orderVerify0('${item.id}')"
													class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 确认</a>
											</c:if>
										</shiro:hasPermission> <shiro:hasPermission name="orders:applyRefund">
											<c:if
												test="${item.status == 'ORDER_PAIED'||item.status == 'ORDER_VERIFY'}">
												<a href="javascript:applyRefund0('${item.id}')"
													class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 申请退款</a>
											</c:if>
										</shiro:hasPermission> <shiro:hasPermission name="orders:confirmRefund">
											<c:if test="${item.status == 'ORDER_REFUNDING' }">
												<a onclick="confirmRefund0('${item.id}')"
													href="javascript:void(0)" class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 确认退款</a>
											</c:if>
										</shiro:hasPermission></td>
									</tr>
								</c:forEach>
							</tbody>
					</table>
				</div></form>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">流水日志</h3>
				</div>
				<div class="panel-body">
					<table class="table table-bordered table-condensed table-hover">
						<thead>
							<tr>
								<th style="border-bottom-width: 0px;">编号</th>
								<th style="border-bottom-width: 0px;">订单业务行为</th>
								<th style="border-bottom-width: 0px;">操作者</th>
								<th style="border-bottom-width: 0px;">时间</th>
								<th style="border-bottom-width: 0px;">描述</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderLogVoList}" var="log" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td><c:choose>
											<c:when test="${log.action == 'ORDER_ACTION_CREATE'}">下单</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_PLAY'}">开场</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_CANCEL'}">取消</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_TIMEOUT'}">超时</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_BILL'}">结账</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_DELETE'}">删除</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_PAY'}">支付成功</c:when>
											<c:when test="${log.action == 'ORDER_ACTION_REFUND'}">确认退款</c:when>
											<c:when test="${log.action == 'APPLY_ACTION_REFUND'}">申请退款</c:when>
											<c:when test="${log.action == 'ORDER_VERIFY'}">已确认</c:when>
										</c:choose></td>
									<td><c:if test="${!empty log.operatorType }">
											<c:if test="${log.operatorType == 1}">
								运营人员：${log.operatorName}
							</c:if>
											<c:if test="${log.operatorType == 2}">
								客户：${log.operatorName}
							</c:if>
										</c:if> <c:if test="${empty log.operatorType }">
							未记录
						</c:if></td>
									<td><fmt:formatDate value="${log.ct}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${log.comment}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="panel-footer">
					<div class="row">
						<div class="col-sm-12 text-right">
							<a class="btn btn-sm btn-default" href="${ctx }/book/list"><span
								class="glyphicon glyphicon-remove"></span> 返回</a>
							<shiro:hasPermission name="orders:verify">
								<c:if test="${order.status == 'ORDER_PAIED' }">
									<a href="javascript:orderVerify('${order.id}','${order.id}')"
										class="btn btn-sm btn-primary"><span
										class="glyphicon glyphicon-check"></span> 确认</a>
								</c:if>
							</shiro:hasPermission>
							<shiro:hasPermission name="orders:applyRefund">
								<c:if
									test="${order.status == 'ORDER_PAIED' and order.finalFee !=0}">
									<a href="javascript:applyRefund('${order.id}','${order.id}')"
										class="btn btn-sm btn-primary"><span
										class="glyphicon glyphicon-check"></span> 申请退款</a>
								</c:if>
							</shiro:hasPermission>
							<shiro:hasPermission name="orders:confirmRefund">
								<c:if test="${order.status == 'ORDER_REFUNDING' }">
									<a onclick="confirmRefund('${order.id}','${order.id}')"
										href="javascript:void(0)" class="btn btn-sm btn-primary"><span
										class="glyphicon glyphicon-check"></span> 确认退款</a>
								</c:if>
							</shiro:hasPermission>
						</div>
					</div>
				</div>

			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">相关订单</h3>
				</div>
				<div class="panel-body">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th style="border-bottom-width: 0px;">订单号</th>
								<th style="border-bottom-width: 0px;">球馆名称</th>
								<th style="border-bottom-width: 0px;">预订场号</th>
								<th style="border-bottom-width: 0px;">预约时间</th>
								<th style="border-bottom-width: 0px;">球馆联系电话</th>
								<th style="border-bottom-width: 0px;">所在城市</th>
								<th style="border-bottom-width: 0px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${order.relatedOrders}" var="relatedOrder"
								varStatus="stat1">
								<c:forEach items="${relatedOrder.orderItemVoList}"
									var="orderItem" varStatus="stat2">
									<tr>
										<c:if test="${stat2.count==1}">
											<td
												<c:if test="${fn:length(relatedOrder.orderItemVoList)>1}">style="line-height:8.57142858"  rowspan="${fn:length(relatedOrder.orderItemVoList)}" </c:if>
												class="text-center"><a
												href="${ctx }/orders/view/${relatedOrder.id}">${relatedOrder.id }</a></td>
										</c:if>
										<td>${orderItem.statiumDetail.name }</td>
										<td>${orderItem.spaceName }</td>
										<td>${orderItem.orderTimeStr }</td>
										<td>${orderItem.statiumDetail.tel }</td>
										<td>${order.areaStr }</td>
										<c:if test="${stat2.count==1}">
											<td
												<c:if test="${fn:length(relatedOrder.orderItemVoList)>1}">style="line-height:8.57142858"  rowspan="${fn:length(relatedOrder.orderItemVoList)}" </c:if>
												class="text-center"><shiro:hasPermission
													name="orders:verify">
													<c:if test="${relatedOrder.status == 'ORDER_PAIED' }">
														<a
															href="javascript:orderVerify('${relatedOrder.id}','${order.id}')"
															class="btn btn-sm btn-primary"><span
															class="glyphicon glyphicon-check"></span> 确认</a>
													</c:if>
												</shiro:hasPermission> <shiro:hasPermission name="orders:applyRefund">
													<c:if
														test="${relatedOrder.status == 'ORDER_PAIED' and relatedOrder.finalFee !=0}">
														<a
															href="javascript:applyRefund('${relatedOrder.id}','${order.id}')"
															class="btn btn-sm btn-primary"><span
															class="glyphicon glyphicon-check"></span> 申请退款</a>
													</c:if>
												</shiro:hasPermission> <shiro:hasPermission name="orders:confirmRefund">
													<c:if test="${relatedOrder.status == 'ORDER_REFUNDING' }">
														<a
															onclick="confirmRefund('${relatedOrder.id}','${order.id}')"
															href="javascript:void(0)" class="btn btn-sm btn-primary"><span
															class="glyphicon glyphicon-check"></span> 确认退款</a>
													</c:if>
												</shiro:hasPermission></td>
										</c:if>
									</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>


		</div>
		<div class="modal fade" id="backorderModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
							<input type="hidden" id="viewOrderDetail" value="" name="viewId">
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
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#book-man');
			$('#adminFooter').hide();
		});
		
		function confirmRefund0(v) {
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

		function orderVerify0(v) {
			var num = 0;
			bootbox.confirm('是否确认' + v + '订单?', function(result) {
				if (result && !num) {
					num++;
					window.location.href = "${ctx}/orders/orderVerify/" + v;
				}
			});
		}

		function applyRefund0(v) {
			$("#backOrderDetail").val(v);
			$("#backorderModel").modal();
		};




		function confirmRefund(v, view_id) {
			bootbox.confirm('是否确认退款' + v + '订单?', function(result) {
				if (result) {
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
								window.location.href = "${ctx}/orders/view/"
										+ view_id;
							}
						}
					});
				}
			});
			return false;
		}

		function orderVerify(v, view_id) {
			bootbox.confirm('是否确认' + v + '订单?', function(result) {
				if (result) {
					window.location.href = "${ctx}/orders/orderVerify/" + v
							+ "/" + view_id;
				}
			});
		}

		function applyRefund(v, view_id) {
			$("#backOrderDetail").val(v);
			$("#viewOrderDetail").val(view_id);
			$("#backorderModel").modal();
		};

		function backForm() {
			if (!$("#reason1").val() && !$("#reason").val()) {
				bootbox.alert('请填写退款原因！');
				//myAlert("请填写退款原因！","error");
				return false;
			}
			$("#backorderForm").attr("action", "${ctx}/orders/applyRefund");
			$("#backorderForm").submit();
		};
	</script>
</body>
</html>
