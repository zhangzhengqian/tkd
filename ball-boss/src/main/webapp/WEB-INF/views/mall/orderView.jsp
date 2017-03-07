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
							<li class="active">订单详情</li>
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
								<th>商品</th>
								<th>名称</th>
								<th>单价(元)</th>
								<th>数量</th>
								<th>金额(元)</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.items}" var="order" varStatus="stat">
								<tr>
									<td width="150">
										<img alt=""
											 style="<c:if test='${!empty order.photo}'>width:128px;height:128px;</c:if>"
											 src="${order.photo}" id="logo_photo">
									</td>
									<td>
										${order.name}
									</td>
									<td>
										￥${order.feeView}
									</td>
									<td>
											${order.amount}
									</td>
									<td>
										￥${(order.price/100)*order.amount}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<div class="col-md-12">
				<div class="col-md-10"></div>
				<div class="col-md-2">订单金额：${data.feeView}元</div>
			</div>
			<hr/>
			<div class="col-md-12">
				<div class="col-md-2">收货信息：</div>
				<div class="col-md-10">${data.shAddress}</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2"> </div>
				<div class="col-md-10">${data.shMan}</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2"> </div>
				<div class="col-md-10">${data.shPhone}</div>
			</div>
			<hr/>
			<div class="col-md-12">
				<div class="col-md-2">订单信息：</div>
				<div class="col-md-10">${data.id}</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2"> </div>
				<div class="col-md-10">
					<c:choose>
						<c:when test="${data.status == '0'}">
							待付款
						</c:when>
						<c:when test="${data.status == '1'}">
							待确认
						</c:when>
						<c:when test="${data.status == '2'}">
							已发货
						</c:when>
						<c:when test="${data.status == '3'}">
							已收货
						</c:when>
						<c:when test="${data.status == '4'}">
							已取消
						</c:when>
						<c:when test="${data.status == '5'}">
							已退款
						</c:when>
						<c:when test="${data.status == '6'}">
							退款中
						</c:when>
					</c:choose>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2"> </div>
				<div class="col-md-10"><fmt:formatDate value="${data.ct}"
											pattern="yyyy-MM-dd HH:mm:ss" /></div>
			</div>
			<c:if test="${data.status==2}">
			<div class="col-md-12">
				<div class="col-md-2">物流信息：</div>
				<div class="col-md-10">${data.sendMan}</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2"> </div>
				<div class="col-md-10">${data.sendPhone}</div>
			</div>
			</c:if>
			<c:if test="${data.status==2}">
			<div class="col-md-12">
				<div class="col-md-2">延长收货：</div>
				<div class="col-md-10">${data.delayNum}次</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2">收货截止时间：</div>
				<div class="col-md-10" id="shTime">${data.shTime}</div>
			</div>
			</c:if>
			<div class="col-sm-12 text-right">
              <shiro:hasPermission name="orders:applyRefund">
				 <c:if test="${data.status == '2'&&data.delayable}">
					<a href="javascript:delay('${data.id}')" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 延期收货</a>
				 </c:if>
			  </shiro:hasPermission>
              <shiro:hasPermission name="orders:applyRefund">
				 <c:if test="${data.status == '1'||data.status == '2'||data.status == '3'}">
				<a href="javascript:applyRefund('${data.id}','${data.id}')" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 申请退款</a>
				 </c:if>
			  </shiro:hasPermission>
             <shiro:hasPermission name="orders:confirmRefund">
				 <c:if test="${data.status == '6'}">
				<a onclick="confirmRefund('${data.id}','${data.id}')" href="javascript:void(0)" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-check"></span> 退款至支付宝</a>
				 </c:if>
	         </shiro:hasPermission>
			</div>
		</div>
		</div>
		<!-- /右侧主体内容 -->

		<div class="panel panel-default" >
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
					<c:forEach items="${logs}" var="log" varStatus="stat">
						<tr>
							<td class="text-center">${stat.count }</td>
							<td>
									${log.comment}
							</td>
							<td>
								   <tags:getUserById id="${log.userId }" />
							</td>
							<td><fmt:formatDate value="${log.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							
							<td>
								<c:if test="${log.action=='0000001' }">
									${data.reason}
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	<div class="modal fade" id="backorderModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
					<h4 class="modal-title">退款原因</h4>
				</div>
				<div class="modal-body" class="">
					<form class="form-inline" method="POST" id="backorderForm">
						<div id="backreason" class="form-group">
							<label>原因：</label>
							<select id="reason1" class="form-control input-sm" name="reason1">
								<option value="">请选择</option>
								<option value="用户要求退款">用户要求退款</option>
								<option value="库存不足">库存不足</option>
							</select>
						</div>
						<div id="backtextDiv" class="form-group">
							<label >其他原因：</label>
							<input class="form-control input-sm" id="reason" type="text" name="reason"/>
						</div>
						<input type="hidden" id="backOrderDetail"   name="orderId">
						<input type="hidden" id="viewOrderDetail"   name="viewId">
						<input type="hidden" value="6" name="status">
					</form>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal" aria-hidden="true">取消</a>
					<a href="javascript:;" class="btn btn-primary alert-to-ok" onClick="backForm()" data-dismiss="modal" aria-hidden="true">确定</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#mallOrders-man');
			$('#adminFooter').hide();

		});


		function applyRefund(v,view_id){
			$("#backOrderDetail").val(v);
			$("#viewOrderDetail").val(view_id);
			$("#backorderModel").modal();
		};


		function backForm(){
			if(!$("#reason1").val() && !$("#reason").val()){
				bootbox.alert('请填写退款原因！');
				//myAlert("请填写退款原因！","error");
				return false;
			}
			$("#backorderForm").attr("action","${ctx}/mall/applyRefund");
			$("#backorderForm").submit();
		};

		function confirmRefund(v,view_id){
			bootbox.confirm('是否确认退款'+v+'订单?', function(result) {
				if(result) {
					$.ajax({
						cache: true,
						type: "POST",
						url:'${ctx}/mall/confirmRefund/'+v,
						data:{},
						async: false,
						error: function(request) {
							common.showMessage("确认退款失败！");
						},
						success: function(data) {
							data = eval("("+data+")");
							if(!data.result || data.result == 'false'){
								if(!data.reason){
									common.showMessage("确认退款失败！");
								}else{
									common.showMessage(data.reason);
								}

							}else{
								common.showMessage("确认退款成功！");
								window.location.href="${ctx}/mall/orderView//"+view_id;
							}
						}
					});
				}
			});
			return false;
		}
		function delay(id){
			$.ajax({
				cache: true,
				type: "POST",
				url:'${ctx}/mall/delay/'+id,
				data:{},
				async: false,
				error: function(request) {
					common.showMessage("延期收货失败！");
				},
				success: function(data) {
					data = eval("("+data+")");
					if(!data.result || data.result == 'false'){
						if(!data.reason){
							common.showMessage("延期收货失败！");
						}else{
							common.showMessage(data.reason);
						}

					}else{
						common.showMessage("延期收货成功！");
						setTimeout(function(){return function(){
							window.location.href="${ctx}/mall/orderView/"+id;
						}}(),2000)
					}
				}
			});
		}
	</script>

</body>
</html>
