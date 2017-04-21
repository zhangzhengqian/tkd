<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
    <title>会员卡列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="statiumNav.jsp"%>

<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-heading" id="divHead">学员信息</div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-2 text-right divFont">姓名: </div>
                <div class="col-md-6 divFont">${studentVo.username }</div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">性别: </div>
                <div class="col-md-6 divFont">
                    <c:choose>
                        <c:when test="${studentVo.sex != null}">
                            ${studentVo.sex }
                        </c:when>
                        <c:otherwise>男</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">手机号: </div>
                <div class="col-md-6 divFont">${studentVo.phone }</div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">储值卡余额: </div>
                <div class="col-md-6 divFont">
                    <fmt:formatNumber type="number" value="${studentVo.balance/100 }" maxFractionDigits="0"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">期限卡期限: </div>
                <div class="col-md-6 divFont">
                    <c:choose>
                        <c:when test="${studentVo.dateTime != null}">
                            ${studentVo.dateTime }
                        </c:when>
                        <c:otherwise>无</c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">订单数: </div>
                <div class="col-md-6 divFont">${studentVo.orderNum }</div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">消费总金额: </div>
                <div class="col-md-6 divFont">
                    <fmt:formatNumber type="number" value="${studentVo.amount/100 }" maxFractionDigits="0"/>
                </div>
            </div>
        </div>
    </div>

    <div class="orderResult">
        <table>
            <tr>
                <th>No.</th>
                <th>订单号</th>
                <th>类型</th>
                <th>金额</th>
                <th>渠道</th>
                <th>下单时间</th>
                <th>完成时间</th>
            </tr>
            <c:forEach items="${data.content }" var="order" varStatus="stat">
                <tr>
                    <td>${stat.count }</td>
                    <td>${order.id }</td>
                    <c:choose>
                        <c:when test="${order.ordersType == 0}">
                            <td>课程</td>
                        </c:when>
                        <c:when test="${order.ordersType == 1}">
                            <td>活动</td>
                        </c:when>
                    </c:choose>
                    <td>
                        <fmt:formatNumber value="${order.finalFee/100 }" maxFractionDigits="0"/>
                    </td>
                    <c:choose>
                        <c:when test="${order.orderType == 'BOOK_APP'}">
                            <td>APP</td>
                        </c:when>
                        <c:when test="${order.orderType == 'BOOK_LIVE'}">
                            <td>线下</td>
                        </c:when>
                    </c:choose>
                    <td>
                        <fmt:formatDate value="${order.ct }" pattern="yyyy/MM/dd HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${order.et }" pattern="yyyy/MM/dd HH:mm:ss"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- 分页 -->
    <tags:pagination page="${data}" />
    <tags:errors />


    <%--<div class="panel-footer">
        <div class="row">
            <div class="col-sm-12 text-right">
                <div class="formSubDiv" style="margin-right: -30px">
                    <a href="javascript:history.go(-1);" class="saveBtnBot">返回</a>
                </div>
            </div>
        </div>
    </div>--%>
</div>

<script type="text/javascript">
    $(function() {
        // 样式
        $('#statium-man').addClass("active");
        $('#STUDENT').addClass("active");

    });

</script>

</body>
</html>