<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>app订单列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="billNav.jsp"%>
<div class="orderTipMeg">
    <div id="modulHeadBill">
        <c:choose>
            <c:when test="${type == 'app'}">
                <p style="float:left;color:#777;font-size:12px;line-height:42px;margin-right:20px;padding-left: 20px;margin-top: -15px">app订单 》日订单详情</p>
            </c:when>
            <c:when test="${type == 'card'}">
                <p style="float:left;color:#777;font-size:12px;line-height:42px;margin-right:20px;padding-left: 20px;margin-top: -15px">会员卡订单 》日订单详情</p>
            </c:when>
        </c:choose>
    </div>
    <a class="addLockBtn" href="javascript:history.go(-1)">返回</a>
    </p>
</div>
<input type="hidden" value="${type }" id = "type">
<div class="orderResult">
    <table>
        <tr>
            <th>订单号</th>
            <th>下单日期</th>
            <th>用户手机号</th>
            <c:if test = "${type == 'app'}">
            	<th>预约日期</th>
            	<th>预约时间</th>
            </c:if>
            <c:if test = "${type == 'card' }">
            	<th>卡片名称</th>
            	<th>面值</th>
            	<th>赠送金额</th>
            </c:if>
            <th>订单金额</th>
         </tr>   
            <c:forEach items = "${data.content }" var = "order">
           <tr>
            	<td>${order.id }</td>
                <td><fmt:formatDate value="${order.et}"
                                    pattern="yyyy-MM-dd HH:mm:ss" /></td>
                <td>${order.phone }</td>
                 <c:if test = "${type =='app'}">
                 	 <td>
                         <fmt:formatDate value="${order.signDate }" pattern="yyyy-MM-dd"/>
                     </td>
                	 <td>${order.signTime }</td>
                 </c:if>
               	 <c:if test = "${type == 'card' }">
               	 	<td>${order.cardName }</td>
               	 	<td>
                        <fmt:formatNumber type="number" value="${order.cardAmount/100 }" maxFractionDigits="0"/>
                    </td>
               	 	<td>
                        <fmt:formatNumber type="number" value="${order.giftFee/100 }" maxFractionDigits="0"/>
               	 	</td>
               	 </c:if>
                <td>
                    <fmt:formatNumber type="number" value="${order.finalFee }" maxFractionDigits="0"/>
                </td>
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