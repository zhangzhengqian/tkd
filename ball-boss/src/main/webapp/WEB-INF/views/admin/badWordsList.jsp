<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>系统管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>系统管理</li>
        <li class="active" >禁词管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form class="form-horizontal" id="badwordsForm" action="${ctx}/badWords/list" method="post">
		      
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_badWord" name="search_LIKE_badWord" value="${param.search_LIKE_badWord }" placeholder="按用禁词内容查询">
			       	</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button id="resetButton" type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" id="search_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				<shiro:hasPermission name="badwords:uploadWord">
	    				&nbsp;&nbsp;
	    			    	<a class="btn btn-primary" href="${ctx}/badWords/uploadWords" ><span class="glyphicon glyphicon-import"></span>  导入禁词</a>
	    				</shiro:hasPermission>
	  				</div>
				</div>
		      </form>
			</div>
			
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		      <div class="btn-group-sm pull-right mtb10">
		      	<shiro:hasPermission name="badWords:create">
			        <a class="btn btn-primary" href="${ctx}/badWords/add" ><span class="glyphicon glyphicon-plus"></span> 禁词添加</a>
		      	</shiro:hasPermission>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
				
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center" width="20px;">编号</th>
					<th  width="200px;">添加时间</th>
					<th>禁词</th>
					<th width="200px;">创建人</th>
				<shiro:hasPermission name="badWords:delete">
					<th  width="200px;">操作</th>
					</shiro:hasPermission>
				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="badwords" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
					    <td>  
							<fmt:formatDate value="${badwords.ct }" pattern="yyyy-MM-dd"/>
						</td>
						<td>${badwords.badWord }</td>
						<td><tags:getUserById id="${badwords.cb }" /></td>
						<shiro:hasPermission name="badWords:delete">
						<td>
						  	<a href="#" onclick="deleteById('${badwords.id }')"> 删除</a>
						</td>
						 </shiro:hasPermission>  
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
	  menu.active('#badwords-man');
	  $('#adminFooter').hide();
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
	  });
});

function deleteById(id){
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除该禁词吗？', function(result) {
	    if(result) {
	      Util.getData('${ctx}/badWords/del/' + id, function(data){
	      	 if(data.result){
		      	 myAlert("删除禁词成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("禁词删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}

$("#search_btn").click(function(){
	$("#badwordsForm").attr("action","${ctx}/badWords/list");
	$("#badwordsForm").attr("method","post");
})
 
</script>

</body>
</html>