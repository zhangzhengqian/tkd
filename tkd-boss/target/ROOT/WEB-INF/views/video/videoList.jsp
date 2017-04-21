<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>视频集列表</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 视频集列表</li>
    </ul>
  </div><!-- / 右侧标题 -->
	
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/video/list" method="post">
			  	
			        <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
				  	  <div class="col-md-5">
				  	  		<input  type="text" class="form-control input-sm" id="search_LIKE_videoName"  name="search_LIKE_videoName"  value="${param.search_LIKE_videoName }" placeholder="按视频名称查询">
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
	    <div class="col-md-12 text-right">
	    	<!-- <a class="btn btn-default btn-sm" href="javascript:window.history.go(-1);" ><span class="glyphicon glyphicon-arrow-left"></span> 返回道馆列表</a> -->
		    <shiro:hasPermission name="video:add">
		    <a class="btn btn-primary btn-sm" href="${ctx }/video/createGroup?action=create" ><span class="glyphicon glyphicon-plus"></span> 添加视频集</a>
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
				<th>视频集名称</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>视频个数</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="video" varStatus="stat">
				<c:set var="status_class" value="" />
				<tr class="${status_class }" >
					<td class="text-center">${stat.count}</td>
					<td>
						<a href="${ctx }/video/detailVideo?id=${video.id}"> ${video.videoName}</a>
					</td>
					<td>${video.cb}</td>
					<td><fmt:formatDate value="${video.ct }" pattern = "yyyy-MM-dd HH:mm:ss"/></td>
					<td>${video.videoNum }</td>
					<td>
						<a class="btn btn-default btn-sm" href="${ctx }/video/detailVideo?id=${video.id}&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a>
						<a class="btn btn-default btn-sm" href="javascirpt:void(0);" onclick="deleteById('${video.id}')"><i class="glyphicon glyphicon-edit"></i> 删除</a>
						<a class="btn btn-default btn-sm" href="${ctx }/videoItem/videoItemList/${video.id}"><i class="glyphicon glyphicon-cog"></i> 视频列表</a>
					   
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
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	menu.active('#video-man');

	$("button[type=reset]").click(function(){
	  $(this).closest("form").find("input").attr("value","");
	  $(this).closest("form").find("select option:selected").attr("selected",false);
	  $(this).closest("form").find("select option:first").attr("selected",true);
	});
		  
});

	function deleteById(id) {
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除视频集吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/video/deleteGroup', function(data){
	      	 if(data.result){
		      	 myAlert("视频集删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("视频集删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
	
</script>

</body>
</html>