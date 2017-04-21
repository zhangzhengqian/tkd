<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>视频管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 视频管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form id="searchForm" class="form-horizontal" action="${ctx}/videoItem/videoItemList/${parentId}" method="post">
		      			<div class="form-group form-group-sm">
		      				<label class="col-md-1 control-label"></label>
							<div class="col-md-5">
								 <input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="按名称查询"> 
							</div>
						</div>
				<div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
						 <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
						 &nbsp;&nbsp;
						 <button type="button" class="btn btn-default btn-sm" id="clean"><span class="glyphicon glyphicon-refresh"></span> 清空</button>
					</div>
				</div>
		      </form>
			</div>
			
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
		 <div class="col-md-12 text-right">
		      <div class="btn-group-sm pull-right mtb10">
		       <a class="btn btn-primary" href="${ctx}/videoItem/videoItemSign/${parentId}" ><span class="glyphicon glyphicon-plus"></span> 视频上传</a>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
	   
				
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>名称</th>
				<th style="width:50px;">URL</th>
				<th>图片</th>
				<th>排序值</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="videoItem" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${videoItem.name }</td>
					<td style="width:5%; white-space:normal">${videoItem.url }</td>
					<td>
			         	<img alt="" src="${videoItem.image }" height="100" onclick="getOriginal('${videoItem.image }')">
	         		</td>
					<td>${videoItem.sort }</td>
					<td style="width:350px;vertical-align:middle">
						<shiro:hasPermission name="carousel:updateCarousel">
							<a class="btn btn-sm btn-default" href="${ctx}/videoItem/updateVideoItem/${videoItem.id }" id="editLink-${videoItem.id }"> <span class="glyphicon glyphicon-edit"/>修改</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="carousel:deleteCarousel">
							<a class="btn btn-sm btn-default" href="#" onclick="deleteById('${videoItem.id }')"> <span class="glyphicon glyphicon-edit"/>删除</a>
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
	<img alt="" src="" id="originalPicture" onclick="closeLogin()"
		style="display:none; POSITION:absolute; left:50%; top:40%; width:600px; height:400px; margin-left:-300px; margin-top:-200px; border:10px solid #EDEDED; background-color:#FFFFFF; text-align:center"><br>
<script type="text/javascript">
$(function() {
	  menu.active('#video-man');
	  $('#adminFooter').hide();
	  
	  $("#clean").click(function(){
		  $("#search_LIKE_name").val("");
		  
	  })
	  
});
 
 // 删除
function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/videoItem/deleteVideoItem/' + id);
	  bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
}
 
// 预览图片
function getOriginal(url){
	if(url.substring(url.length-1,url.length)=="/"){
		document.getElementById("originalPicture").src=url.substring(0,url.length-1);
	}else{
		document.getElementById("originalPicture").src=url.substring(0,url.length);
	}
		document.getElementById("originalPicture").style.display="";
	}
 
// 关闭预览
function closeLogin(){
	document.getElementById("originalPicture").style.display="none";
}
</script>

</body>
</html>