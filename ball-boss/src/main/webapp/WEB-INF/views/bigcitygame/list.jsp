<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>战队管理</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 战队管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>团体名称</th>
				<th>领队姓名</th>
				<th>教练员</th>
				<th>球队赞助单位</th>
				<th>联系人姓名</th>
				<th>联系电话</th>
				<th>报名时间</th>
				<th>审核状态</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="game" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>${game.name}</td>
					<td>${game.leader}</td>
					<td>${game.coach}</td>
					<td>${game.supportOrg}</td>
					<td>${game.linkMan}</td>
					<td>${game.linkPhone}</td>
					<td><fmt:formatDate value="${game.ct}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><c:choose>
			                <c:when test="${game.auditState == 0}">待审核</c:when>
			                <c:when test="${game.auditState == 1}">审核通过</c:when>
			                <c:when test="${game.auditState == 2}">审核不通过</c:when>
			        	</c:choose>
			        </td>
					<td>
						<a href="${ctx }/bigcitygame/view/${game.id}" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i> 详情</a>
						<a href="javascript:;" onclick="del('${game.id}')" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i> 删除</a>
					</td>
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
	menu.active('#bigcitygame-man');
});

function del(id){
	bootbox.confirm('删除后不可恢复，确定删除已报名团体？', function(result) {
	    if(result) {
	    	window.location.href="${ctx }/bigcitygame/delete/"+id;
	    }
	  });
}
  
</script>
</body>
</html>