<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>轮播图管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 轮播图管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form id="searchForm" class="form-horizontal" action="${ctx}/carousel/list" method="post">
		      			<div class="form-group form-group-sm">
		      				<label class="col-md-1 control-label"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="按名称查询">
							</div>
							<div class="col-md-5">
								<select class="form-control" name="search_LIKE_type" id="search_LIKE_type">
									<option value="" >--请选择类型--</option>
									<option value="activity" >--活动--</option>
									<option value="race"  >--比赛--</option>
									<option value="statium" >--场馆--</option>
									<option value="url"  >--URL--</option>
									<option value="video"  >--视频--</option>
									<option value="videoList"  >--视频集--</option>
								</select>
							</div>
						</div>
				<div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
						 <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
					</div>
				</div>
		      </form>
			</div>
			
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
		 <div class="col-md-12 text-right">
		      <div class="btn-group-sm pull-right mtb10">
		      <shiro:hasPermission name="carousel:sign">
		       <a class="btn btn-primary" href="${ctx}/carousel/sign" ><span class="glyphicon glyphicon-plus"></span> 轮播图上传</a>
		      </shiro:hasPermission>
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
				<th>类型</th>
				<th style="width:50px;">id</th>
				<th>图片</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="carousalVo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${carousalVo.name }</td>
					<td><c:if test="${carousalVo.type == 'activity'}" >
							活动
						</c:if>
						<c:if test="${carousalVo.type == 'race'}" >
							比赛
						</c:if>
						<c:if test="${carousalVo.type == 'statium'}" >
							场馆
						</c:if>
						<c:if test="${carousalVo.type == 'url'}" >
							url
						</c:if>
						<c:if test="${carousalVo.type == 'video' }">
							视频
						</c:if>
						<c:if test="${carousalVo.type == 'videoList' }">
							视频集
						</c:if>
					</td>
					<td style="width:5%; white-space:normal">${carousalVo.resourceId }</td>
					<td>
			         	<img alt="" src="${carousalVo.image }" height="100" onclick="getOriginal('${carousalVo.image }')">
	         		</td>
					<td>${carousalVo.sort }</td>
					<td style="width:350px;vertical-align:middle">
					<shiro:hasPermission name="carousel:updateCarousel">
					  <a class="btn btn-sm btn-default" href="${ctx}/carousel/updateCarousel/${carousalVo.id }" id="editLink-${carousalVo.id }"> <span class="glyphicon glyphicon-edit">修改</a>
					  </shiro:hasPermission>
					  <shiro:hasPermission name="carousel:deleteCarousel">
					  <a class="btn btn-sm btn-default" href="#" onclick="deleteById('${carousalVo.id }','${carousalVo.areaCode }')"> <span class="glyphicon glyphicon-edit">删除</a>
					 </shiro:hasPermission>
					 <%--  <c:if test="${stat.count != 1}"> --%>
					  	<a class="btn btn-sm btn-default" href="#" onclick="setTop('${carousalVo.sort }','${carousalVo.areaCode }')"> <span class="glyphicon glyphicon-edit">置顶</a> 
					  	<a class="btn btn-sm btn-default" href="#" onclick="setUp('${carousalVo.sort }','${carousalVo.areaCode }')"> <span class="glyphicon glyphicon-edit">上移</a>
					  <%-- </c:if> --%>
					  <%-- <c:if test="${!stat.last}"> --%>
					  	<a class="btn btn-sm btn-default" href="#" onclick="setDown('${carousalVo.sort }','${carousalVo.areaCode }')"> <span class="glyphicon glyphicon-edit">下移</a> 
					  <%-- </c:if> --%>
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
	  menu.active('#carousel-man');
	  $('#adminFooter').hide();
	  
	  var typeValue = '${param.search_LIKE_type}';
	  if(typeValue){
		$("select[name=search_LIKE_type] option[value="+typeValue+"]").attr("selected","selected");
	  }
	  
	  $('#btnGroup :button').each(function(i){
		  var _this = $(this);
		  _this.removeClass("btn-success");
		  _this.click(function(){
				 $('#btnGroup :button').each(function(i){
					 $(this).removeClass("btn-success");
				 });
				 _this.addClass("btn-success");
				$('#search_EQ_position').val(_this.val());
				$('#searchForm').submit();
			});
	  });
	  
	  var position = '${param.search_EQ_position}';
	  if(position > 0){$("#btn"+position).addClass("btn-success")}else{$("#btn0").addClass("btn-success")}
});
 
 // 删除
function deleteById(id,areaCode) {
	  var $form = $('#actionForm');
	  if(areaCode!=''){
		  $form.attr('action', '${ctx}/carousel/deleteCarousel/' + id+'/'+areaCode);
	  }else{
		  $form.attr('action', '${ctx}/carousel/deleteCarousel/' + id+'/'+0);
	  }
	  bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
}
 
// 置顶
function setTop(sort,areaCode){
	var $form = $('#actionForm');
	if(areaCode!=''){
		$form.attr('action', '${ctx}/carousel/setTop/' + sort+'/'+areaCode);
	}else{
		$form.attr('action', '${ctx}/carousel/setTop/' + sort+'/'+0);
	}
	bootbox.confirm('您确定置顶吗？', function(result) {
	   if(result) {
	     $form[0].submit();
	   }
	});
	return false; 
}

// 上移
function setUp(sort,areaCode){
	var $form = $('#actionForm');
	if(areaCode!=''){
		$form.attr('action', '${ctx}/carousel/setUp/' + sort+'/'+areaCode);
	}else{
		$form.attr('action', '${ctx}/carousel/setUp/' + sort+'/'+0);
	}
	bootbox.confirm('您确定上移吗？', function(result) {
	   if(result) {
	     $form[0].submit();
	   }
	});
	return false; 
}

// 下移
function setDown(sort,areaCode){
	var $form = $('#actionForm');
	if(areaCode!=''){
		$form.attr('action', '${ctx}/carousel/setDown/' + sort+'/'+areaCode);
	}else{
		$form.attr('action', '${ctx}/carousel/setDown/' + sort+'/'+0);
	}
	bootbox.confirm('您确定下移吗？', function(result) {
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