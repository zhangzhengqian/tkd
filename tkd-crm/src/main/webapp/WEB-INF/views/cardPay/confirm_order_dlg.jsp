<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<style type="text/css">
	.divFont {
		font-size: 14px;
	}
	#divHead {
		background-color: #d9e2ee;
		height: 40px;
		font-size: 16px;
	}
</style>
<div class="modal-header">
	<button type="button" class="close" style="margin: -7px 0px 0px 0px"
		data-dismiss="modal">
		<span aria-hidden="true">X</span><span class="sr-only">Close</span>
	</button>
</div>

<div class="panel-body">
	<div class="panel panel-default">
		<div class="formTable">
			<div class="panel-heading" id="divHead" style="background-color: #d9e2ee;font-size: 16px; margin-top: -20px;">订单信息</div>
			<input type="hidden" id="id" name="id" value="${order.id }">
			<ul>
				<li>
					<span class="title">订单号：</span>
					<label class="title" style="color: red;">${order.id }</label>
				</li>
				<li>
					<span class="title">姓名：</span>
					<label class="title">${order.name }</label>
				</li>
				<li>
					<span class="title">手机号：</span>
					<label class="title" style="color: red;">${order.phone }</label>
				</li>
				<li>
					<span class="title">订单类型：</span>
					<label class="title">
						<c:choose>
							<c:when test="${order.ordersType == 0}">
								课程
							</c:when>
							<c:when test="${order.ordersType == 1}">
								活动
							</c:when>
						</c:choose>
					</label>
				</li>
				<c:if test="${order.ordersType == 0}">
					<li>
						<span class="title">课程名称：</span>
						<label class="title">${order.orderName }</label>
					</li>
					<li>
						<span class="title">教练：</span>
						<label class="title">${order.coachName }</label>
					</li>
				</c:if>
				<c:if test="${order.ordersType == 1}">
					<li>
						<span class="title">活动名称：</span>
						<label class="title">${order.orderName }</label>
					</li>
				</c:if>
				<li>
					<span class="title">价格：</span>
					<label class="title">
						<fmt:formatNumber value="${order.finalFee/100 }" maxFractionDigits="0"/>
					</label>
				</li>
				<c:if test="${order.ordersType == 0}">
					<li>
						<span class="title">预约日期：</span>
						<label class="title" style="color: red;">
							<fmt:formatDate value="${order.signDate }" pattern="yyyy/MM/dd"/>
						</label>
					</li>
					<li>
						<span class="title">预约日期：</span>
						<label class="title" style="color: red;">
							${order.startTime} ~ ${order.endTime}
						</label>
					</li>
				</c:if>
				<c:if test="${order.ordersType == 1}">
					<li>
						<span class="title">开始日期：</span>
						<label class="title" style="color: red;">
							<fmt:formatDate value="${order.asTime }" pattern="yyyy/MM/dd HH:mm"/>
						</label>
					</li>
					<li>
						<span class="title">结束日期：</span>
						<label class="title" style="color: red;">
							<fmt:formatDate value="${order.aeTime }" pattern="yyyy/MM/dd HH:mm"/>
						</label>
					</li>
				</c:if>
			</ul>
			<div class="formSubDiv">
				<a href="${ctx }/cardPay/appPay" class="saveBtnBot">返回</a>
				<a class="saveBtnBot" href="javascript:confirmOrder(${order.id })">确认</a>
			</div>

		</div>
	</div>

</div>

<script>
	// 签到
	function confirmOrder(orderId) {
		captainAddCallBack(orderId);
		$("#myDlg_lg").modal("hide");
	}
</script>