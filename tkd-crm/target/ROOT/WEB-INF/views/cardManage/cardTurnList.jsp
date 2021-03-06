<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="cardTurnFrom" class="form-horizontal" action="${ctx }/cardManage/cardTurnList" method="post" name="id">
    <div class="modulHead">
        <p>会员卡管理 》转卡记录</p>
    </div>
    <div class="orderSearch myVipOrderSearch">
        <ul>
            <li class="timeLi subSearchLi1">
                <span>手机号</span>
                <input style="width: 200px" type="text" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }">
            </li>
            <li class="timeLi subSearchLi1">
                <span>转卡日期</span>
                <input type="text" id="search_EQ_ct" name="search_EQ_ct" value="${param.search_EQ_ct }"
                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
            </li>
            <li class="subSearch subSearchLi1">
                <a class="reset" type="reset" href="javascript:;">重置</a>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardUserSubmit()" >查询</a>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="${ctx }/cardManage/cardUser" class="saveBtnBot">返回</a>
            </li>

        </ul>
    </div>
</form>

<div class="orderResult">
    <table>
        <tr>
            <th>No.</th>
            <th>姓名</th>
            <th>手机号</th>
            <th>原姓名</th>
            <th>原手机号</th>
            <th>卡片类型</th>
            <th>金额/期限</th>
            <th>成本</th>
            <th>原因</th>
            <th>转卡日期</th>
        </tr>

        <c:forEach items="${data.content }" var="cardTurn" varStatus="stat">
            <tr>
                <td>${stat.count }</td>
                <td>${cardTurn.newName }</td>
                <td>${cardTurn.newPhone}</td>
                <td>${cardTurn.oldName }</td>
                <td>${cardTurn.oldPhone}</td>
                <c:choose>
                    <c:when test="${cardTurn.cardType == 1}">
                        <td>储值卡</td>
                    </c:when>
                    <c:when test="${cardTurn.cardType == 2}">
                        <td>期限卡</td>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${cardTurn.cardType == 1}">
                        <td><fmt:formatNumber type="number" value="${cardTurn.cardBalance/100 }" maxFractionDigits="0"/></td>
                    </c:when>
                    <c:when test="${cardTurn.cardType == 2}">
                        <td>
                            <fmt:formatDate value="${cardTurn.startDate }" pattern="yyyy/MM/dd"/>
                            ~
                            <fmt:formatDate value="${cardTurn.endDate }" pattern="yyyy/MM/dd"/>
                        </td>
                    </c:when>
                </c:choose>
                <td>
                    <fmt:formatNumber value="${cardTurn.cardCost/100 }" maxFractionDigits="0"/>
                </td>
                <td>${cardTurn.reason }</td>
                <td>
                    <fmt:formatDate value="${cardTurn.ct }" pattern="yyyy/MM/dd"></fmt:formatDate>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- 分页 -->
<tags:pagination page="${data}" />
<tags:errors />

<script src="${ctx}/static/lib/reset.js"></script>
<script type="text/javascript">
    $(function() {
        // 样式
        $('#card-man').addClass("active");
        $('#CARD_USER').addClass("active");
    });

    // 查询
    function cardUserSubmit() {
        $('#cardTurnFrom').submit();
    }

</script>

</body>
</html>