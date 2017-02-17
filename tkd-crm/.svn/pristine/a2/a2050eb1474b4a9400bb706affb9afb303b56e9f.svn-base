<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>app账单结算详情</title>
</head>
<body>
<!-- 导航 -->
<%@include file="billNav.jsp"%>
<input type = "hidden" id="type" value="${type }">
<div class="orderTipMeg">
    <a class="addLockBtn" href="javascript:history.go(-1)">返回</a>
    <p>总金额合计<span class="text-danger"><fmt:formatNumber type="number" value="${totalAmount }" maxFractionDigits="0"/></span>元</p>
</div>
<div class="orderResult">
    <table>
        <tr>
            <th>日期</th>
            <th>订单总数</th>
            <th>订单金额</th>
            <th>操作</th>
        </tr>
		
		<c:forEach items = "${data.content }" var = "orderBillItem">
            <tr>
                <td>${orderBillItem.startDate }</td>
                <td>
                    <fmt:formatNumber type="number" value="${orderBillItem.orderCount }" maxFractionDigits="0"/>
                </td>
                <td>
                    <fmt:formatNumber type="number" value="${orderBillItem.totalFee }" maxFractionDigits="0"/>
                </td>
                <td><a href="${ctx }/billManage/appList/${type}/${orderBillItem.startDate}" class="text-navy">查看订单</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- 分页 -->
<tags:pagination page="${data}" />
<tags:errors />

<script type="text/javascript">
    $(function() {
        // 样式 根据类型判断
        var type = $("#type").val();
        $('#bill-man').addClass("active");
        if(type == "app"){
        	$('#APP_BILL').addClass("active");
        }
        if(type == "card"){
        	$("#CARD_BILL").addClass("active");
        }
        
    });


</script>

</body>
</html>