<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>刷卡管理</title>
</head>
<body>
<!-- 导航 -->
<%@include file="cardPayNav.jsp"%>

<form  id="cardForm" class="form-horizontal" action="" method="post" name="id">
    <div class="orderSearch myVipOrderSearch">
        <input type="hidden" id="classInfoId" name="classInfoId" value="${classInfoId }">
        <ul>
            <li class="timeLi subSearchLi1">
                <span>教练：</span>
            </li>
            <li class="timeLi subSearchLi1">
                <span>${classInfo.coachName }</span>
            </li>
            <li class="timeLi subSearchLi1">
                <span>价格：</span>
            </li>
            <li class="timeLi subSearchLi1">
                <span>
                    <fmt:formatNumber value="${classInfo.classAmount/100 }" maxFractionDigits="0"/>
                </span>
            </li>
            <li class="timeLi subSearchLi1">
                <span style="width: 100px;">课程时间：</span>
            </li>
            <li class="timeLi subSearchLi1">
                <span style="width: 100px;">${classInfo.classStartTime } ~ ${classInfo.classEndTime } </span>
            </li>
            <li class="subSearch subSearchLi">
            </li>

            <li class="subSearch subSearchLi">
                <a class="submit" href="javascript:cardPay();">刷卡 </a>
            </li>

        </ul>
    </div>
</form>

<div class="orderResult">
    <table>
        <tr>
            <th>No.</th>
            <th>订单号</th>
            <th>姓名</th>
            <th>手机号</th>
            <th>渠道</th>
            <th>支付方式</th>
            <th>下单日期</th>
        </tr>
        <c:forEach items="${data.content }" var="member" varStatus="stat">
            <tr>
                <td>${stat.count }</td>
                <td>${member.orderId }</td>
                <td>${member.name }</td>
                <td>${member.phone }</td>
                <td>
                    <c:choose>
                        <c:when test="${member.orderType == 'BOOK_APP'}">
                            app
                        </c:when>
                        <c:when test="${member.orderType == 'BOOK_LIVE'}">
                            线下
                        </c:when>
                    </c:choose>
                </td>
                <td><c:choose>
                    <c:when test="${member.payType == '1'}">
                        支付宝
                    </c:when>
                    <c:when test="${member.payType == '2'}">
                        微信
                    </c:when>
                    <c:when test="${member.payType == '3'}">
                        储值卡
                    </c:when>
                    <c:when test="${member.payType == '4'}">
                        期限卡
                    </c:when>
                </c:choose></td>
                <td>
                    <fmt:formatDate value="${member.orderDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
        // 样式
        $('#pay-man').addClass("active");
        $('#${classInfoId }').addClass("active");

    });

    // 签到信息确认
    function cardPay() {
        $("#myDlgBody_lg").load("${ctx}/cardPay/card_pay_dlg/" + $('#classInfoId ').val(), {
        });
        $("#myDlg_lg").modal();
    }

</script>

</body>
</html>