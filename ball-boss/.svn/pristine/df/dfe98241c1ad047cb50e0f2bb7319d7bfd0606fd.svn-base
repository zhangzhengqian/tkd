<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动码管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 活动码管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>场馆名称</th>
				<th>激活数量</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${useInfo}" var="statium" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${statium.statiumName }</td>
					<td>${statium.useNum }</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div><!-- end col-table -->
		</div><!-- end row -->
		
		<form id="actionForm" action="" method="post">
       		<input type="hidden" id="codeId" name="codeId" value="${codeId }">
		    <div class="row"><!-- 操作按钮组 -->
	   			<div class="col-md-12">
				    <div class="btn-group-sm pull-right mtb10">
					   <a class="btn btn-default btn-block" href="${ctx}/code/codeList"><span class="glyphicon glyphicon-remove"></span> 返回</a>
					</div>
					&nbsp;&nbsp;
		      		<div class="btn-group-sm pull-right mtb10">
		      			<button type="submit" id="export_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-import"></span> 导出Excel</button>
		     		</div>
		   		</div>
		  	</div><!-- /操作按钮组 --> 
    	</form>
				
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable1" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>活动码</th>
				<th>状态</th>
				<th>用户</th>
				<th>使用日期</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="codeInfo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${codeInfo.code }</td>
					<td>
						<c:if test="${codeInfo.statue == '0' }">  
					    	  未使用
						</c:if>
						<c:if test="${codeInfo.statue == '1' }">  
					    	  已使用
						</c:if>
						<c:if test="${codeInfo.statue == '2' }">  
					    	  已过期
						</c:if>
					</td>
					<td>${codeInfo.useName }</td>
					<td><fmt:formatDate value="${codeInfo.useDate}" pattern="yyyy/MM/dd"/></td>
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
	  menu.active('#code-man');
	  $('#adminFooter').hide();
	  
});

// 导出Excel
$("#export_btn").click(function(){
	$("#actionForm").attr("action","${ctx}/code/exportExcel/"+ $("#codeId").val());
	$("#actionForm").attr("method","post");
})
 
</script>

</body>
</html>