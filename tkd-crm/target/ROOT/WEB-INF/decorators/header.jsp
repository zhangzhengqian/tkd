<%@ page import="com.lc.zy.ball.crm.common.SessionUtil" %>
<%@ page import="com.lc.zy.ball.domain.oa.po.CrmUser" %><%--
  Created by IntelliJ IDEA.
  User: sl
  Date: 2016/11/21
  Time: 下午1:33
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%
    String statiumId = SessionUtil.currentStatium();
    request.setAttribute("statiumId", statiumId);
%>
<div class="header" ng-controller="loginUserCTL">
    <div class="headerCont">
        <h1><tags:getStatiumInfo statiumId="${statiumId}"/></h1>
        <ul class="headerNav">
            <li id="card-man">
                <a href="${ctx }/cardManage/cardUser">
                    <span class="carManageIco"></span>
                    <strong>卡片管理</strong>
                </a>
            </li>
            <li id="statium-man">
                <a href="${ctx }/statiumManage/student">
                    <span class="statiumManIco"></span>
                    <strong>人员管理</strong>
                </a>
            </li>
           <%-- <li id="">
                <a href="#bill">
                    <span class="billSearchIco"></span>
                    <strong>道馆管理</strong>
                </a>
            </li>--%>
            <li id="order-man">
                <a href="${ctx }/order/list">
                    <span class="orderIco"></span>
                    <strong>订单管理</strong>
                </a>
            </li>
            <li id="bill-man">
                <a href="${ctx }/billManage/appBill">
                    <span class="billIco"></span>
                    <strong>账单结算</strong>
                </a>
            </li>
            <li id="info-man">
                <a href="${ctx }/infoManage/statiumUser">
                    <span class="infoIco"></span>
                    <strong>信息管理</strong>
                </a>
            </li>
            <li id="pay-man">
                <a href="${ctx }/cardPay/appPay">
                    <span class="payIcon"></span>
                    <strong>刷卡管理</strong>
                </a>
            </li>
            <%--<li id="members-man">
                <a href="#members">
                    <span class="memberIco"></span>
                    <strong>会员管理</strong>
                </a>
            </li>--%>
        </ul>
        <div class="logout">
            <a href="/logout">注销</a>
        </div>
        <div class="help">
            <a target="_blank" href="http://wenku.baidu.com/view/a2f12d0f657d27284b73f242336c1eb91a3733f0">帮助手册</a>
        </div>
        <%--<div class="headerMessTip">
            <a class="mgIco" href="#messages"><span id="unreadNum">{{msgCount}}</span></a>
        </div>--%>

    </div>
</div>
