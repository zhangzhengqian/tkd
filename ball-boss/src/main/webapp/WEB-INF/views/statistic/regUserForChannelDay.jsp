<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户注册统计</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <span class="glyphicon glyphicon-stats"></span>&nbsp;
        <li class="active" ><a href="${ctx}/statisticRegisterUser/registerList" >注册用户统计</a></li>
        <li class="active" ><a href="${ctx}/statisticRegisterUser/queryByChannel/${channel}" >注册来源日数据</a></li>
        <li class="active" >注册来源时数据</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
				
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>注册来源</th>
					<th>注册时间</th>
					<th>注册数</th>
					<th>激活数</th>
				</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${data.content }" var="regUser" varStatus="stat">
					<tr>
						<td style="text-align: center;">${stat.count }</td>
						<td style="text-align: center;">${regUser.channel }</td>
						<td style="text-align: center;">${regUser.ct }</td>
						<td style="text-align: center;">${regUser.counter }</td>
						<td style="text-align: center;">
							<c:if test="${!empty regUser.avcounter }">${regUser.avcounter }</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
  </div>
  
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	menu.active('#statisticRegUser-regUserList');
});

</script>

</body>
</html>