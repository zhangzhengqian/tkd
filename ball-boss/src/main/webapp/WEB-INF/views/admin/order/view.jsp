<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单详情</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 订单管理</li>
				<li class="active">订单详情</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

        <table id="contentTable" class="table table-bordered table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center">开场日期</th>
	                <th class="text-center">预约时间</th>
	                <th class="text-center">场地名称</th>
                    <th class="text-center">场地金额</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${orderSummaryList}" var="item">
				<tr>
                    <td class="text-center">
                   	 ${item.startDate}
                    </td>
                    <td class="text-center">${item.startTime }~${item.endTime }</td>
                    <td class="text-center">${item.spaceName }</td>
                    <td class="text-center">${item.price}</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
        <div class="panel-footer">
	        <div class="text-right">
                <a href="<%=request.getHeader("Referer") %>" class="btn btn-default btn-sm">返回</a>
	        <c:choose>
	            <c:when test="${order.status eq 'ORDER_NEW' }">
		            <button id="btnCancel" class="btn btn-primary btn-sm">撤销</button>
		            <button id="btnPlay" class="btn btn-primary btn-sm">开场</button>
	            </c:when>
	            <c:when test="${order.status eq 'ORDER_PLAYING' }">
	                <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#billDialog">结算</button>
	            </c:when>
	        </c:choose>
	        </div>
        </div>
	</div>

	<div id="billDialog" class="modal fade">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">结算</h4>
				</div>
				<div class="modal-body">
					<form id="billForm" action="${ctx }/member/order/bill" method="post">
					<input type="hidden" name="id" value="${order.id }">
						<div class="form-group">
							<label for="deposit" class="control-label">押金:</label> <input
								type="text" class="form-control" id="deposit" value="${lf:formatMoney(order.deposit) }" readonly>
						</div>
                        <div class="form-group">
                            <label for="deposit" class="control-label">价格:</label>
                            <input class="form-control" id="price" value="${lf:formatMoney(orderItem.price) }" readonly>
                        </div>
						<c:set var="hours" value="${(orderItem.endTime - orderItem.startTime)/3600 }" />
                        <div class="form-group">
                            <label for="timespan" class="control-label">时长:</label> <input
                                type="text" class="form-control" id="timespan" value="${lf:formatTimeSpan((orderItem.endTime - orderItem.startTime)) }" readonly>
                        </div>
                        <div class="form-group">
                            <label for="fee" class="control-label">应收:</label>
                            <input class="form-control" id="fee" value="${lf:formatMoney((orderItem.endTime - orderItem.startTime)*orderItem.price/3600) }" readonly>
                        </div>
						<div class="form-group">
							<label for="finalFee" class="control-label">实收:</label>
							<input class="form-control" value="${lf:formatMoney((orderItem.endTime - orderItem.startTime)*orderItem.price/3600) }" name="finalFee" id="finalFee">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btnBill" class="btn btn-primary">结算</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    <tags:errors />

	<form id="actionForm" action="" method="post">
       <input type="hidden" name="id" value="${order.id }">
    </form>
	<script type="text/javascript">
		$(function() {
			menu.active('#order-manager-item');

			$("#btnCancel").click(function() {
			    bootbox.confirm('您确定要删除此订单吗？', function(result) {
			        if(result) {
			        	$("#actionForm").attr("action", "${ctx}/member/order/cancel");
			        	$("#actionForm").submit();
			        }
			    });
			});
            $("#btnPlay").click(function() {
                bootbox.confirm('您确定要开场吗？', function(result) {
                    if(result) {
                        $("#actionForm").attr("action", "${ctx}/member/order/play");
                        $("#actionForm").submit();
                    }
                });
            });
            $("#btnBill").click(function() {
            	$("#billForm").submit();
            });
            $("#adminFooter").hide();
		});
	</script>

</body>
</html>
