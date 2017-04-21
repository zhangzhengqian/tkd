<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
  	<div class="row">
  		<div class="col-md-5">
			<div class="btn-group-sm">
				  <ul class="breadcrumb" style="display:inline;">
			        <li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
			        <li class="active">注册渠道每日数据</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
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
				<th>注册总数</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content }" var="statistic" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${statistic.channel }</td>
					<td>${statistic.ct }</td>
					<td>${statistic.counter}</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		 <tags:pagination page="${data}" />
		<tags:errors />
  </div><!-- /右侧主体内容 -->

</div>
<script>
$(function(){
	menu.active("#statistic-man");
})
</script>
</body>
</html>