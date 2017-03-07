<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>群组管理</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 群组管理</li>
        <li class="active">群组管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="groupForm" class="form-horizontal" action="${ctx }/group" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" ></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_groupId" name="search_LIKE_groupId" value="${param.search_LIKE_groupId }" placeholder="按群组号查询">
			       	</div>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="按群组名称查询">
			       	</div>
			  		</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	    <div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>群号</th>
				<th>群名称</th>
				<th>群主</th>
				<th>创建时间</th>
				<th>群成员</th>
				<th>群位置</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content }" var="group" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;"><a href="${ctx}/group/detail/${group.id}">${group.groupId }</a></td>
					<td style="text-align: center;">${group.name }</td>
					<td style="text-align: center;">${group.nickname }</td>
					<td style="text-align: center;"><fmt:formatDate value="${group.ct }" pattern="yyyy-MM-dd HH:mm" /></td>
					<td style="text-align: center;">${group.memberTotal }</td>
					<td style="text-align: center;">${group.address }</td>
 					<td style="text-align: center;">
 						<shiro:hasPermission name="group:disband">
						<a class="btn btn-default btn-sm" href="javaScript:void(0)" onclick="disband('${group.id}');"> 
						<i class="glyphicon glyphicon-edit"></i>解散</a>
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
		<tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  menu.active('#qiuyouzone-group');
	  $("button[type=reset]").click(function(){
		  $("#groupForm").find("input").attr("value","");
		  $("#groupForm").find("select option:selected").attr("selected",false);
		  $("#groupForm").find("select option:first").attr("selected",true);
	  });
  });
  
  // 解散群
  function disband(id){
	  bootbox.confirm('您确定要解散该群组？', function(result) {
	    if(result) {
	      Util.getData(ctx + '${ctx }/group/disband', function(data){
	      	 if(data.result){
		      	 myAlert("群解散成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("群解散失败","error");
			 }
	      }, null, {"groupId":id}, 'post');
	    }
	  });
	  return false;
  } 
</script>
</body>
</html>