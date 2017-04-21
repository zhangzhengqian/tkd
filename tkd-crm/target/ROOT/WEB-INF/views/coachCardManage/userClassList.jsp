<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡列表</title>
</head>
<body >
<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="cardUserFrom" class="form-horizontal" action="${ctx }/userClassManage/userClassList" method="post" name="id">
    <div class="orderSearch myVipOrderSearch">
        <ul>
            <li class="timeLi subSearchLi1">
                <span>姓名</span>
                <input style="width: 200px" type="text" id="search_LIKE_username" name="search_LIKE_username" value="${param.search_LIKE_username }">
            </li>
            <li class="subSearch subSearchLi1">
                <a class="reset" type="reset" href="javascript:;">重置</a>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardUserSubmit()" >查询</a>
            </li>
            <li class="subSearch subSearchLi">
            </li>
            <li class="subSearch subSearchLi">
                <a class="submit" href="${ctx }/userClassManage/initClassBuy">购买课程 </a>
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
            <th>课程名称</th>
            <th>购买总次数</th>
            <th>消费次数</th>
            <th>剩余次数</th>
            <th>有效时间</th>
        </tr>

        <c:forEach items="${data.content }" var="userClass" varStatus="stat">
            <tr>
                <td>${stat.count }</td>
                <td>${userClass.username }</td>
                <td>${userClass.telPhone}</td>
                <td>${userClass.className}</td>
                <td>${userClass.totalFrequency}</td>
                <td>${userClass.consumeFrequency}</td>
                <td>${userClass.leftFrequency}</td>
                <td>${userClass.avaliableTime}</td>
                    <%-- <a class="btn btn-default btn-sm" href="${ctx }/cardManage/cardUserDetail/${cardUser.accountId }"><i class="glyphicon glyphicon-list"></i> 详情</a> --%>
                    <%-- <a class="btn btn-default btn-sm" href="${ctx }/cardManage/initTurnCard/${cardUser.accountId }"><i class="glyphicon glyphicon-edit"></i> 转卡</a> --%>
                    <%-- <c:choose>
                        <c:when test="${cardUser.cardType == 1}">
                            <a class="btn btn-default btn-sm" href="${ctx }/cardManage/initRechargeCard/${cardUser.accountId }"><i class="glyphicon glyphicon-edit"></i> 充值</a>
                        </c:when>
                        <c:when test="${cardUser.cardType == 2}">
                            <a class="btn btn-default btn-sm" href="${ctx }/cardManage/initRechargeCard/${cardUser.accountId }"><i class="glyphicon glyphicon-edit"></i> 延期</a>
                        </c:when>
                    </c:choose> --%>
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
        $('#coacchCard-man').addClass("active");
        $('#CARD_USER').addClass("active");
    });

    // 查询
    function cardUserSubmit() {
        $('#cardUserFrom').submit();
    }

</script>

</body>
</html>