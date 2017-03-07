<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>运营管理/推送管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
		<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
		<li>赛事管理</li>
		<li class="active">赛事通知</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  		<input type="hidden" id="msgId" name="msgId" value="${gameId }">
		<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
					<div class="btn-group-sm pull-right mtb10">
							<a class="btn btn-primary btn-sm" href="${ctx }/enjoyRace/msgForm?gameId=${gameId }"><span
								class="glyphicon glyphicon-plus"></span> 新增通知</a>
					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->
	<br>
	
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>发布时间</th>
				<th>发布人</th>
				<th>发布内容</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="pushVo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td style="text-align: center;">${pushVo.cb }</td>
					<td style="text-align: center;"><fmt:formatDate value="${pushVo.factPushTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;">${pushVo.pushTitle }</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div><!-- end col-table -->
		</div><!-- end row -->
		
		<tags:pagination page="${data}" />	
				
	
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	menu.active('#enjoyrace-man');
	
	$('#adminFooter').hide();
});
	
</script>

</body>
</html>