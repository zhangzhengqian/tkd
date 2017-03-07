<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>参赛队伍</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">参赛队伍</li>
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
    	 <form id="actionForm" class="form-horizontal"  method="post">		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>队伍名称</th>
				<th>队长</th>
				<th>队员数</th>
				<th>报名时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${eventCorps}" var="corps" varStatus="stat">
				<tr>
					<td class="text-center">${stat.index+1 }</td>
					<td class="text-center">${corps.name}</td>
					<td class="text-center">${lf:getSsouserNameById(corps.captain)} </td>
					<td class="text-center">${corps.currentNumber}</td>
					<td class="text-center"><fmt:formatDate value="${corps.ct}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td class="text-center">
					<a href="${ctx}/event/corpsView/${corps.id}"  class="btn btn-default btn-sm"> 查看详情</a>
						<a class="btn btn-danger btn-sm" href="#" onclick="deleteById('${corps.id}')">
						 <i class="glyphicon glyphicon-trash"></i> 删除 </a>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table></form>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  menu.active('#event-man');
	  $('#adminFooter').hide();
  });
  
	function deleteById(id){
		  var $form = $('#actionForm');
		  bootbox.confirm('您确定要删除该对战吗？', function(result) {
		    if(result) {
		      Util.getData(ctx + '/event/delCorps', function(data){
		      	 if(data.result){
			      	 myAlert("删除对战成功");
			      	 window.location.reload()
			     }else{
			    	 myAlert("对战删除失败","error");
				 }
		      }, null, {"id":id}, 'post');
		    }
		  });
		  return false;
		}
</script>
</body>
</html>