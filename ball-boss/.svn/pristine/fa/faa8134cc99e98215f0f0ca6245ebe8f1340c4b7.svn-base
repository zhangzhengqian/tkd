<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球友圈管理</title>
	<style>
		.row{
			margin-top:10px;
		}
	</style>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 球友圈</li>
        <li class="active">球友圈管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body">
		<div class="row">
			<div class="col-md-12">
		      用户信息
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      球友号
			</div>
			<div class="col-md-8">
		      ${user.qiuyouno}
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      昵称
			</div>
			<div class="col-md-8">
		      ${user.nickName}
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      手机号
			</div>
			<div class="col-md-8">
		      ${user.phone}
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      用户状态
			</div>
			
		      <c:if test="${user.state == '1'}" >
		      		<div class="col-md-2">
					正常
					</div>
					<div class="col-md-2">
						<a onclick="freezeSsoUser('${user.id }')" href="javascript:;">冻结</a>
					</div>
			  </c:if>
			  <c:if test="${user.state == '3'}" >
			  	<div class="col-md-2">
				冻结
				</div>
				<div class="col-md-2">
					<a onclick="unfreezeSsoUser('${user.id }')" href="javascript:;">解冻</a>
				</div>
			  </c:if>
		</div>
		<div class="row">
			<div class="col-md-12">
		      发布信息
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      发布时间
			</div>
			<div class="col-md-8">
		      <fmt:formatDate value="${zone.ct}" pattern="yyyy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      发布内容
			</div>
			<div class="col-md-8">
		      <c:forEach items="${fn:split(zone.image,';')}" var="imageSrc">
		      	<img alt="球友圈" src="${imageSrc}">
		      </c:forEach>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      
			</div>
			<div class="col-md-8">
				${zone.content}
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
		      评论列表(${fn:length(comments)})
			</div>
		</div>
		<hr>
		<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>球友号</th>
				<th>昵称</th>
				<th>评论时间</th>
				<th>评论内容</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${comments}" var="comment" varStatus="stat">
					<tr>
						<td>${stat.count }</td>
						<c:set var="ssouser" value="${lf:getSsouserNameAndQiuyouNo(comment.userId)}"></c:set>
						<td>${ssouser['qiuyouNo']}</td>
						<td>${ssouser['name']}</td>
						<td><fmt:formatDate value="${comment.ct}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${comment.content }</td>
						<td><a onclick="removeComment(this,'${comment.id}')" href="javascript:;">删除</a></td>
					</tr>
				</c:forEach>			
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		 
		 <div class="row">
			<div class="col-md-12">
		      点赞列表(${fn:length(likes)})
			</div>
		</div>
		<hr>
		<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>球友号</th>
				<th>昵称</th>
				<th>评论时间</th>
			</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${likes}" var="like" varStatus="stat">
					<tr>
						<td>${stat.count }</td>
						<c:set var="ssouser" value="${lf:getSsouserNameAndQiuyouNo(like.userId)}"></c:set>
						<td>${ssouser['qiuyouNo']}</td>
						<td>${ssouser['name']}</td>
						<td><fmt:formatDate value="${like.ct}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>			
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
 
  $(function() {
	  menu.active('#qiuyouzone-man');
	  $('#adminFooter').hide();
  });
  
  function removeComment(obj,id){
	  bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/qiuyouzone/comment/delete/'+id, function(data){
	      	 if(data.result){
		      	 myAlert("删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("删除失败","error");
			 }
	      }, null, {}, 'post');
	    }
	  });
  }
  
  function freezeSsoUser(id){
	  bootbox.confirm('您确定要冻结此用户吗？', function(result) {
		  if(result) {
		      Util.getData(ctx + '/ssouser/frozen/'+id, function(data){
		      	 if(data.result){
			      	 myAlert("操作成功");
			      	 window.location.reload()
			     }else{
			    	 myAlert("操作失败","error");
				 }
		      }, null, {}, 'post');
		    }
	  });
  }
  
  function unfreezeSsoUser(id){
	  bootbox.confirm('您确定要解冻此用户吗？', function(result) {
		  if(result) {
		      Util.getData(ctx + '/ssouser/unfrozen/'+id, function(data){
		      	 if(data.result){
			      	 myAlert("操作成功");
			      	 window.location.reload()
			     }else{
			    	 myAlert("操作失败","error");
				 }
		      }, null, {}, 'post');
		    }
	  });
  }
  
</script>

</body>
</html>
