<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>报名详情-审核</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">活动管理</li>
        <li class="active">报名详情</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="row">
		<div class="col-md-12" style="margin-bottom: 10px;">
		    <div class="btn-group" role="group" aria-label="...">
		    	  <c:forEach items="${activityItemList }" var="item" varStatus="stat">
				  	  <button id="${item.id }" value="${item.date }" type="button" onclick="active_btn(this)"
				  	  		class="btn btn-default btn-sm <c:if test='${item.date == activityItem.date }'>active</c:if>">
				  	  <span class="glyphicon" aria-hidden="true"></span>
				  	  ${item.date }</button>
		    	  </c:forEach>
			</div>
	    </div>
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>订单号</th>
				<th>球友号</th>
				<th>昵称</th>
				<th>性别</th>
				<th>手机号码</th>
				<th>报名时间</th>
				<th>审核时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${userList }" var="user" varStatus="stat">
				<tr class="<c:if test="${user.state == 1}">warning</c:if><c:if test="${user.state == 2}">success</c:if><c:if test="${user.state == 3}">danger</c:if>">
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${user.orderId }</td>
					<td style="text-align: center;">${user.qiuyouno }</td>
					<td style="text-align: center;">${user.nickName }</td>
					<td style="text-align: center;">${user.sex }</td>
					<td style="text-align: center;">${user.phone }</td>
					<td style="text-align: center;"><fmt:formatDate value="${user.registrationTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;">
						<c:if test="${user.state == 0 }">未支付</c:if>
						<c:if test="${user.state == 1 }">未审核</c:if>
						<c:if test="${user.state == 2 }">已审核</c:if>
						<c:if test="${user.state == 3 }">未通过</c:if>
					</td>
					<td style="text-align: center;">
						<a href="${ctx}/activity/findUserById/${user.id }/${activityItem.id }"> 查看</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
  </div><!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
  $(function() {
	  menu.active('#activity-man');
  });
  
  function active_btn(obj){
	  var activityItemId = $(obj).attr("id");
	  $("button[class='btn btn-default btn-sm active']").each(function(){
		  //先清除上一个已选择button
		  $(this).removeClass("active");
	  })
	  $(obj).addClass("active");
	  
	  location.href = "${ctx }/activity/userList/"+activityItemId;
  }
</script>
</body>
</html>