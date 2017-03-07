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
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
		      				<label class="col-md-1 control-label"></label>
							<div class="col-md-5 form-inline">
								<tags:zoneCity name="search_EQ_areaCode" value="${param.search_EQ_areaCode }" />
							</div>
						</div>
						<input type ="hidden" name="search_EQ_position"  id="search_EQ_position" value="${param.search_EQ_position}"/>  
				<div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
						 <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
					</div>
				</div>
		      </form>
			</div>
			
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
	    <div class="col-md-7 form-inline">
		    <div class="btn-group" role="group" aria-label="..." id="btnGroup">
				  <button id="btn0" value="0"  type="button" class="searchTop btn btn-default btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;APP首页</button>
				  <button id="btn1"  value="1" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;APP场馆</button>
				  <button id="btn7"  value="7" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;APP球友圈首页</button>
				  <button id="btn2"  value="2" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;微信公众号</button>
				  <button id="btn3"  value="3" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;WEB首页</button>
				  <button id="btn4" value="4" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;WEB活动</button>
				  <button id="btn5"  value="5" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;WEB赛事</button>
				  <button id="btn6"  value="6" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;WEB教学</button>
				  <button id="btn8"  value="8" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;启动页广告</button>
			</div>
		</div>
		 <div class="col-md-5 text-right">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-primary" href="${ctx}/carousel/sign" ><span class="glyphicon glyphicon-plus"></span> 轮播图上传</a>
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
				<th>位置</th>
				<th>类型</th>
				<th style="width:50px;">id</th>
				<th>图片</th>
				<th>城市</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="carousalVo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${carousalVo.name }</td>
					<td><c:if test="${carousalVo.position == 0}" >
							首页
						</c:if>
						<c:if test="${carousalVo.position == 1}" >
							场馆
						</c:if>
						<c:if test="${carousalVo.position == 2}" >
							微信公众平台
						</c:if>
						<c:if test="${carousalVo.position == 7}" >
							球友圈首页
						</c:if>
						<c:if test="${carousalVo.position == 8}" >
							启动页
						</c:if>
					</td>
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
					</td>
					<td style="width:5%; white-space:normal">${carousalVo.resourceId }</td>
					<td>
			         	<img alt="" src="${carousalVo.image }" height="100" onclick="getOriginal('${carousalVo.image }')">
	         		</td>
	         		<td>${carousalVo.city  }</td>
					<td>${carousalVo.sort }</td>
					<td style="width:350px;vertical-align:middle">
					  <a class="btn btn-sm btn-default" href="${ctx}/carousel/updateCarousel/${carousalVo.id }" id="editLink-${carousalVo.id }"> <span class="glyphicon glyphicon-edit">修改</a>
					  <a class="btn btn-sm btn-default" href="#" onclick="deleteById('${carousalVo.id }','${carousalVo.areaCode }')"> <span class="glyphicon glyphicon-edit">删除</a>
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