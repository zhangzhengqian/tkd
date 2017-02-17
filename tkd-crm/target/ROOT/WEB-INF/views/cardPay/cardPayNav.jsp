<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lc.zy.ball.crm.framework.system.statium.service.StatiumService" %>
<%@ page import="com.lc.zy.common.util.SpringUtils" %>
<%@ page import="com.lc.zy.ball.crm.common.SessionUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
    StatiumService statiumService = SpringUtils.getBean(StatiumService.class);
    List list = statiumService.getClass(SessionUtil.currentStatium());
    request.setAttribute("classData", list);
%>


<!-- 卡片管理 -->
<div class="orderNav">
    <ul id="cardPay">
        <li id="APP_PAY">
            <a href="${ctx }/cardPay/appPay">APP签到</a>
        </li>
        <c:forEach items="${classData}" var="class" varStatus="stat">
            <li id="${class.classInfoId }">
                <a href="${ctx }/cardPay/classPay/${class.classInfoId }">${class.className }</a>
                &nbsp;&nbsp;${class.classTime }
            </li>
        </c:forEach>
    </ul>
</div>