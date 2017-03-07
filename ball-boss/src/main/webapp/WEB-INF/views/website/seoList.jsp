<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>SEO列表</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> SEO列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="" method="post">
			 
			        <div class="form-group form-group-sm">
			          <lable class="control-label col-md-1 sr-only"></lable>
			          	<div class=" col-md-5">
			        		<input  type="text" class="form-control input-sm" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title}"  placeholder="按名称查询">
			        		<input type="hidden" name ="search_EQ_type"  id="search_EQ_type" value="${param.search_EQ_type }">
			        	</div>         
			        </div>			 		
			        
				<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button id="search_btn" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				  </div>
				</div>
   			 </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
	       <div class="col-md-5 form-inline">
		    <div class="btn-group" role="group" aria-label="..." id="btnGroup">
				  <button id="btn0" value="0"  type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;首页</button>
				  <button id="btn1"  value="1" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;订场</button>
				  <button id="btn2"  value="2" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;活动</button>
				  <button id="btn3"  value="3" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;赛事</button>
				  <button id="btn4"  value="4" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;教陪练</button>
				  <button id="btn5"  value="5" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;教学</button>
			</div>
		</div>
	    <div class="col-md-7 text-right">
		    <shiro:hasPermission name="seo:createForm">
		    	<a class="btn btn-primary btn-sm" href="${ctx }/seo/createForm?action=create" ><span class="glyphicon glyphicon-plus"></span> 新增</a>
		    </shiro:hasPermission>
	    </div>
	  </div><!-- /操作按钮组 -->
	  </div><!-- /操作按钮组 -->
	<br>	
				
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">位置</th>
				<th>标题</th>
				<th>内容</th>
				<th>位置</th>
				<th>seo类型</th>
				<th>修改时间</th>
				<th>发布人</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}"  var="seo"  varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>
					   ${seo.title}</a>
					</td>
					<td>${seo.description }</td>
					<td>
						<c:choose>
			  				<c:when test="${seo.pageType == 0}">列表页</c:when>
			                <c:when test="${seo.pageType == 1}">内容页</c:when>
			  			</c:choose>
					</td>
					<td>
						<c:choose>
			  				<c:when test="${seo.seoType == 0}">Description</c:when>
			                <c:when test="${seo.seoType == 1}">Keywords</c:when>
			                <c:when test="${seo.seoType == 2}">Title</c:when>
			  			</c:choose>
					</td>
					<td><fmt:formatDate value="${seo.ct}" pattern="yyyy-MM-dd"/></td>
					<td>
						<tags:getUserById id="${seo.cb }" />
					</td>
					<td>
					   <shiro:hasPermission name="seo:detailForm">
					   		<a class="btn btn-default btn-sm" href="${ctx }/seo/detailForm?id=${seo.id}&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a></a>
					   </shiro:hasPermission>
					   <shiro:hasPermission name="seo:delete">
					   		<a class="btn btn-danger btn-sm" href="#" onclick="deleteById('${seo.id}')"> <i class="glyphicon glyphicon-trash"></i> 删除</a>
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
	  menu.active('#seo-man');
	  $('#adminFooter').hide();
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
	  });
	  
	  $('#btnGroup :button').each(function(i){
		  var _this = $(this);
		  _this.click(function(){
				 $('#btnGroup :button').each(function(i){
					 $(this).removeClass("btn-success");
				 });
				 _this.addClass("btn-success");
				$('#search_EQ_type').val(_this.val());
				$("#search_form").attr("action","${ctx}/seo/list/"+_this.val());  
				$('#search_form').submit();
			});
	  });
	  
	  var position = '${param.search_EQ_type}';
	  if(position > 0){$("#btn"+position).addClass("btn-success")}else{$("#btn0").addClass("btn-success")}
	  
	  $("#search_btn").click(function(){
			$("#search_form").attr("action","${ctx}/seo/list/"+$('#search_type').val());
	  })
});

function deleteById(id) {
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除此条数据吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/seo/delete', function(data){
	      	 if(data.result){
		      	 myAlert(" 删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert(" 删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
	  
</script>

</body>
</html>