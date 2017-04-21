<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.lc.zy.ball.boss.common.security.ShiroDbRealm.ShiroUser"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">

<div class="panel-heading" style="overflow:hidden;">
	
	<span style="float:left; margin-top:2px;" class="glyphicon glyphicon-th-large"></span>
	<strong style="float:left; margin-left:5px;">功能菜单</strong>
	<a class="leftBtn" id="leftBtn" href="javascript:;">收起</a>
	
</div>

<div class="panel-body leftmenu"><!-- 左侧菜单 -->
  <ul class="list-group leftmenu-list">
  
    
    <li>
      <a id="" ><span></span> 用户管理</a>
      <ul class="list-group">
	      <shiro:hasPermission name="ssouser:man">
	        <li><a href="${ctx}/ssouser/user" class="list-group-item" id="ssouser-man">用户管理</a></li>
	      </shiro:hasPermission>
          <shiro:hasPermission name="userRegister:man">
              <li><a href="${ctx}/admin/statisticRegisterUser" class="list-group-item" id="statistic-man">注册用户统计</a></li>
          </shiro:hasPermission>
      </ul>
    </li>
   <li>
      <a id="" ><span></span> 道馆管理</a>
      <ul class="list-group">
      <shiro:hasPermission name="statium:list">
      	<li><a href="${ctx}/statiumOa" class="list-group-item" id="statiumOa-man">道馆管理</a></li>
      </shiro:hasPermission>	
      	<!-- 隐藏pc道馆管理 -->
      	<%-- <li><a href="${ctx}/statium" class="list-group-item" id="statium-man">pc道馆管理</a></li> --%>
      </ul>
   </li>
   <li>
       <a id=""><span></span>订单管理</a>
       <ul class="list-group">
       <shiro:hasPermission name="order:list">
      	<li><a href="${ctx }/order/list" class="list-group-item" id="order-man">订单管理</a></li>
      	</shiro:hasPermission>
        <shiro:hasPermission name="order:orderList">
            <li><a href="${ctx }/order/orderList" class="list-group-item" id="orderList-man">订单查询</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="order:orderBill">
           <li><a href="${ctx }/bill/orderBill" class="list-group-item" id="orders-bill">道馆结账</a></li>
        </shiro:hasPermission>
      </ul>
   </li>
   <li>
      <a id="" ><span></span> 教练管理</a>
      <ul class="list-group">
      <shiro:hasPermission name="coach:list">
      	<li><a href="${ctx}/coach" class="list-group-item" id="coach-man">教练管理</a></li>
      </shiro:hasPermission>	
      </ul>
   </li>
   
    <li>
      <a ><span></span> 商城管理</a>
      <ul class="list-group">
      <shiro:hasPermission name="mallOrders:man">
      <li><a href="${ctx}/goods" class="list-group-item" id="goods">商品管理</a></li>
      </shiro:hasPermission>
       <shiro:hasPermission name="mallOrders:man">
              <li><a href="${ctx}/mall/orderList" class="list-group-item" id="mallOrders-man">订单管理</a></li>
       </shiro:hasPermission>
          <shiro:hasPermission name="mallctivity:man">
              <li><a href="${ctx}/mall/activityList" class="list-group-item" id="mallctivity-man">活动管理</a></li>
          </shiro:hasPermission>
       </ul>
  	</li>
   
    <li>
      <a id="" ><span></span> 运营管理</a>
      <ul class="list-group">
          <shiro:hasPermission name="carousel:list">
              <li><a href="${ctx}/carousel/list" class="list-group-item" id="carousel-man">轮播图管理</a></li>
          </shiro:hasPermission>
          <shiro:hasPermission name="news:list">
              <li><a href="${ctx}/news/list" class="list-group-item" id="news-man">新闻资讯管理</a></li>
          </shiro:hasPermission>
	      <shiro:hasPermission name="activity:list">
              <!-- 活动管理 -->
	          <li><a href="${ctx}/activity/list" class="list-group-item" id="activity-man">活动管理</a></li>
          </shiro:hasPermission>
          <shiro:hasPermission name="video:list">
          	  <li><a href="${ctx}/video/list" class="list-group-item" id="video-man">视频管理</a></li>
          </shiro:hasPermission>
          <shiro:hasPermission name="contest:list">
          	  <li><a href="${ctx}/activity/contestList" class="list-group-item" id="contest-man">赛事活动管理</a></li>
          </shiro:hasPermission>
          <shiro:hasPermission name="studyData:list">
          	  <li><a href="${ctx}/study/list" class="list-group-item" id="study-man">学习资料管理</a></li>
          </shiro:hasPermission>
      </ul>
    </li>
    
    <li>
    	<a id="" ><span></span> 会员管理</a>
      	<ul class="list-group">
      		<li><a href="${ctx }/account/list" class="list-group-item" id="account-man">账户管理</a></li>
      		<li><a href="${ctx}/statiums/list" class="list-group-item" id="statiums-man">道馆查看</a></li>
      	</ul>      	
    </li>
    
     <li>
    	<a id="" ><span></span> 短信群发</a>
      	<ul class="list-group">
      		<li><a href="${ctx }/sms/list" class="list-group-item" id="sms-man">短信群发</a></li>
      	</ul>      	
    </li>
    
    
    <li>
      <a id="" ><span></span> 系统管理</a>
      <ul class="list-group">
	      <shiro:hasPermission name="user:list">
	        <li><a href="${ctx}/admin/user" class="list-group-item" id="user-man">员工管理</a></li>
	      </shiro:hasPermission>
	      <shiro:hasPermission name="role:list">
	        <li><a href="${ctx}/admin/role" class="list-group-item" id="role-man">角色管理</a></li>
	      </shiro:hasPermission>
	      
	      <shiro:hasPermission name="func:list">
	        <li><a href="${ctx}/admin/func" class="list-group-item" id="func-man">功能管理</a></li>
	      </shiro:hasPermission>
	      <shiro:hasPermission name="org">
	        <li><a href="${ctx}/admin/org" class="list-group-item" id="org-man">组织管理</a></li>
	      </shiro:hasPermission>
	      <shiro:hasPermission name="dic">
	        <li><a href="${ctx}/admin/dic" class="list-group-item" id="dic-man">字典管理</a></li>
	      </shiro:hasPermission>
	      <li><a href="${ctx}/admin/userinfo" class="list-group-item" id="userinfo-man">个人信息</a></li>
	      <shiro:hasPermission name="holiday">
	      	<li><a href="${ctx}/admin/holiday" class="list-group-item" id="holiday-man">节假日设置</a></li>
	      </shiro:hasPermission>
      </ul>
    </li>
  </ul>
</div><!-- / 左侧菜单 -->

</div>



 