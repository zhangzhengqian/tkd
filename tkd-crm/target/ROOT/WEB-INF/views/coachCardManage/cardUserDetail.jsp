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
<%@include file="cardNav.jsp"%>

<div class="panel-body">
    <div class="panel panel-default">
        <div class="panel-heading" id="divHead">卡片信息</div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-2 text-right divFont">持卡人: </div>
                <div class="col-md-6 divFont">${userVo.name }</div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">手机号: </div>
                <div class="col-md-6 divFont">${userVo.phone }</div>
            </div>
            <div class="row">
                <div class="col-md-2 text-right divFont">卡片类型: </div>
                <div class="col-md-6 divFont">
                    <c:if test="${userVo.cardType == 1 }">储值卡</c:if>
                    <c:if test="${userVo.cardType == 2 }">期限卡</c:if>
                </div>
            </div>
            <div class="row" id="balanceArea" style="display: none">
                <div class="col-md-2 text-right divFont">余额: </div>
                <div class="col-md-6 divFont">
                    <fmt:formatNumber type="number" value="${userVo.balance/100 }" maxFractionDigits="0"/>
                </div>
            </div>
            <div class="row" id="timeArea" style="display: none">
                <div class="col-md-2 text-right divFont">卡片期限: </div>
                <div class="col-md-6 divFont">${userVo.cardTime }</div>
            </div>
        </div>
    </div>

    <div class="orderResult">
        <table>
            <tr>
                <th>No.</th>
                <th>订单号</th>
                <c:choose>
                    <c:when test="${userVo.cardType == 1}">
                        <th>金额</th>
                        <th>余额</th>
                    </c:when>
                    <c:when test="${userVo.cardType == 2}">
                        <th>开始日期</th>
                        <th>结束日期</th>
                    </c:when>
                </c:choose>
                <th>时间</th>
                <th>描述</th>
            </tr>
            <c:forEach items="${data.content }" var="cardLog" varStatus="stat">
                <tr>
                    <td>${stat.count }</td>
                    <td>${cardLog.orderId }</td>
                    <c:choose>
                        <c:when test="${cardLog.cardType == 1}">
                            <td>
                                <fmt:formatNumber value="${cardLog.amount/100 }" maxFractionDigits="0"/>
                            </td>
                            <td>
                                <fmt:formatNumber value="${cardLog.balance/100 }" maxFractionDigits="0"/>
                            </td>
                        </c:when>
                        <c:when test="${userVo.cardType == 2}">
                            <td>
                                <fmt:formatDate value="${cardLog.startDate }" pattern="yyyy/MM/dd"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${cardLog.endDate }" pattern="yyyy/MM/dd"/>
                            </td>
                        </c:when>
                    </c:choose>
                    <td>
                        <fmt:formatDate value="${cardLog.ct }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${cardLog.description }</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- 分页 -->
    <tags:pagination page="${data}" />
    <tags:errors />


    <div class="panel-footer">
        <div class="row">
            <div class="col-sm-12 text-right">
                <div class="formSubDiv" style="margin-right: -30px">
                    <a href="${ctx }/cardManage/cardUser" class="saveBtnBot">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        // 样式
        $('#card-man').addClass("active");
        $('#CARD_USER').addClass("active");

        // 卡片类型不同、展示不同
        if (${userVo.cardType == 1}) {
            $('#balanceArea').show();
        } else {
            $('#timeArea').show();
        }
    });

</script>

</body>
</html>