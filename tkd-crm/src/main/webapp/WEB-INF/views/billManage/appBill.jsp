<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="billNav.jsp"%>

<div class="orderResult">
    <table>
        <tr>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>订单总数</th>
            <th>总金额</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
		
		<c:forEach items="${data.content }" var = "bill">
        <tr>
            <td>${bill.startDate}</td>
            <td>${bill.endDate }</td>
            <td>
                <fmt:formatNumber type="number" value="${bill.totalNum }" maxFractionDigits="0"/>
            </td>
            <td>
                <fmt:formatNumber type="number" value="${bill.totalAmount }" maxFractionDigits="0"/>
            </td>
            <td><fmt:formatDate value="${bill.billCt }" pattern = "yyyy-MM-dd HH:mm:ss"/></td>
            <td><a href="${ctx }/billManage/appBillItem/${bill.billId}/${bill.statiumId}" href="javascript:;" class="text-navy">查看</a></td>
        </tr>
		</c:forEach>
		
    </table>
</div>
<!-- 分页 -->
<tags:pagination page="${data}" />
<tags:errors />

<script type="text/javascript">
    $(function() {
        // 样式
        $('#bill-man').addClass("active");
        $('#APP_BILL').addClass("active");
    });


</script>

</body>
</html>