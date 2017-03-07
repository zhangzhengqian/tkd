<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>反馈管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
        <li class="active" >反馈管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>球友号</th>
					<th>用户昵称</th>
					<th>用户性别</th>
					<th>注册手机</th>
					<th>用户类型</th>
					<th>反馈时间</th>
					<th>反馈内容</th>
				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="feedback" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
						<td> ${feedback.qiuyouno } </td>
						<td>${feedback.nickName }</td>
						<td>${feedback.sex }</td>
						<td>${feedback.phone }</td>
						<td>${feedback.type }</td>
						<td>
		         			<fmt:formatDate value="${feedback.ct }" pattern="yyyy-MM-dd HH:mm:ss"/>
		         		</td>
						<td>${feedback.content }</td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		
		<tags:pagination page="${data}" />
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">
$(function() {
	  menu.active('#feedback-man');
	  $('#adminFooter').hide();
});
</script>

</body>
</html>