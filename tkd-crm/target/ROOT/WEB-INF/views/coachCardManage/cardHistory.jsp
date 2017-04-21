<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡购买记录列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="cardHistoryForm" class="form-horizontal" action="${ctx }/cardManage/cardHistoryList/${cardId }" method="post" name="id">
    <div class="modulHead">
        <p>会员卡管理 》会员卡购买记录</p>
    </div>
    <div class="orderSearch myVipOrderSearch">
        <input type="hidden" id="cardId" name="cardId" value="${cardId }">
        <ul>
            <li class="timeLi subSearchLi1">
                <span>渠道：</span>
                <select id="search_EQ_buyType" name="search_EQ_buyType">
                    <option value="">请选择</option>
                    <option value="0">APP</option>
                    <option value="1">线下</option>
                </select>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardSubmit()" >查询</a>
            </li>
        </ul>
    </div>
</form>

<div class="orderResult">
    <table>
        <tr>
            <th>No.</th>
            <th>用户</th>
            <th>方式</th>
            <th>渠道</th>
            <th>购买日期</th>
        </tr>
        <c:forEach items="${data.content }" var="cardLog" varStatus="stat">
            <tr>
                <td>${stat.count }</td>
                <td>${cardLog.userName }</td>
                <c:choose>
                    <c:when test="${cardLog.type == 0 }">
                        <td>购卡</td>
                    </c:when>
                    <c:when test="${cardLog.type == 3 }">
                        <td>转卡</td>
                    </c:when>
                    <c:when test="${cardLog.type == 4 }">
                        <td>充值</td>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${cardLog.buyType == 0 }">
                        <td>APP</td>
                    </c:when>
                    <c:when test="${cardLog.buyType == 1 }">
                        <td>线下</td>
                    </c:when>
                </c:choose>
                <td>
                    <fmt:formatDate value="${cardLog.ct }" pattern="yyyy/MM/dd HH:mm:ss"/>
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
        $('#card-man').addClass("active");
        $('#CARD_TYPE').addClass("active");

        // select状态
        // 卡片类型
        var buyType = '${param.search_EQ_buyType }';
        if(buyType){
            $("#search_EQ_buyType option[value="+buyType+"]").attr("selected","selected");
        }
    });

    // 查询
    function cardSubmit() {
        $('#cardHistoryForm').submit();
    }

</script>

</body>
</html>