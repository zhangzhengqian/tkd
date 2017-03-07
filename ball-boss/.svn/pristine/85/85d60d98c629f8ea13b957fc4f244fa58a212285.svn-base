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
        <li class="active">报名详情</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="col-md-12">
	      <div class="btn-group-sm pull-right mtb10">
	      	   <a class="btn btn-default btn-block" href="${ctx}/event/list"><span class="glyphicon"></span> 返回</a>
	      </div>
    </div>
	<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>球友号</th>
				<th>昵称</th>
				<th>性别</th>
				<th>手机号码</th>
				<th>报名时间</th>
				<c:if test="${isVerify == 1 }">
				<th>审核状态</th></c:if>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${memberList}" var="user" varStatus="stat">
				<tr class="<c:if test="${user.status == 0}">warning</c:if><c:if test="${user.status == 1}">success</c:if><c:if test="${user.status == 2}">danger</c:if>">
					<td class="text-center">${stat.count }</td>
					<td class="text-center">${user.qiuyouNo}</td>
					<td class="text-center">${user.nickName}</td>
					<td class="text-center">${user.sex}</td>
					<td class="text-center">${user.phone}</td>
					<td class="text-center"><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/></td>
					<c:if test="${isVerify == 1 }">
					<td class="text-center">
						<c:if test="${user.status == 0}">未审核</c:if>
						<c:if test="${user.status == 1}">通过</c:if>
						<c:if test="${user.status == 2}">未通过</c:if>
					</td></c:if>
					<td class="text-center">
						<a href="${ctx}/event/memberView/${user.eventId}/${user.memberId}/${user.userId}/${user.status}"> 查看</a>
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
	  menu.active('#event-man');
	  $('#adminFooter').hide();
  });
</script>
</body>
</html>