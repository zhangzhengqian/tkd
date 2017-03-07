<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>公告列表</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 公告列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/notice/list" method="post">
			 
			        <div class="form-group form-group-sm">
			          <lable class="control-label col-md-1 sr-only"></lable>
			          	<div class=" col-md-5">
			        		<input  type="text" class="form-control input-sm" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title}"  placeholder="按公告名称查询">
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
	  <div class="row"><!-- 操作按钮组 -->
	    <div class="col-md-12 form-inline text-right">
		    <shiro:hasPermission name="notice:createForm">
		    	<a class="btn btn-primary btn-sm" href="${ctx }/notice/createForm?action=create" ><span class="glyphicon glyphicon-plus"></span> 新增公告</a>
		    </shiro:hasPermission>
	    </div>
	  </div><!-- /操作按钮组 -->
	<br>	
				
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>标题</th>
				<th>发布时间</th>
				<th>发布人</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="notice" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>
						 ${notice.title}</a>
					</td>
					<td><fmt:formatDate value="${notice.ct}" pattern="yyyy-MM-dd"/></td>
					<td>
						<tags:getUserById id="${notice.cb }" />
					</td>
					<td>
					<a href="${ctx}/notice/detailForm?id=${notice.id}" id="editLink-${notice.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>查看</a>
					   <shiro:hasPermission name="notice:detailForm">
					   		<a class="btn btn-default btn-sm" href="${ctx }/notice/detailForm?id=${notice.id}&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a></a>
					   </shiro:hasPermission>
					   <c:if test="${notice.stick == 0}">
							<a href="javaScript:" onclick="noticeTop('${notice.id}');" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-arrow-up"></i> 置顶</a>					   
					   </c:if>
					   <c:if test="${notice.stick == 1}">
							<a href="javaScript:" onclick="noticeUnTop('${notice.id}');" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-remove"></i> 取消置顶</a>					   
					   </c:if>
					   <shiro:hasPermission name="notice:delete">
					   		<a class="btn btn-danger btn-sm" href="#" onclick="deleteById('${notice.id}')"> <i class="glyphicon glyphicon-trash"></i> 删除</a>
					   </shiro:hasPermission>
					</td>
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
	  menu.active('#notice-man');
	  $('#adminFooter').hide();
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
	  });
	  
});
  
	  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除该公告吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/notice/delete', function(data){
	      	 if(data.result){
		      	 myAlert("公告删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("公告删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
	
  
  // 置顶
  function noticeTop(id ){
	  if(!id){
		  myAlert("公告id不能为空","error");
		  return false;
	  }
	  Util.getData(ctx + '/notice/stick', function(data){
      	 if(data.result){
	      	 myAlert("置顶成功");
	      	 window.location.reload()
	     }else{
	    	 myAlert(data.reason,"error");
	    	 return false;
		 }
      }, null, {"id":id }, 'get');
  } 
  
  // 取消置顶
  function noticeUnTop(id ){
	  if(!id){
		  myAlert("公告id不能为空","error");
		  return false;
	  }
	  Util.getData(ctx + '/notice/unstick', function(data){
      	 if(data.result){
	      	 myAlert("置顶成功");
	      	 window.location.reload()
	     }else{
	    	 myAlert(data.reason,"error");
	    	 return false;
		 }
      }, null, {"id":id }, 'get');
  }
</script>

</body>
</html>