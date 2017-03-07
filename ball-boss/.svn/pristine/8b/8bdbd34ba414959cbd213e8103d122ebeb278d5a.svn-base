<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>评论管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 评论管理</li>
        <li class="active" >评论管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form class="form-horizontal" id="commentForm" action="${ctx}/comment/list" method="post">
		      
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="content"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content }" placeholder="按内容查询">
			       		<input type="hidden" class="form-control input-sm" id="search_type" value="${search_type }">
			       	</div>
		         </div>
		         
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button id="resetButton" type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" id="search_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
		<div class="row"><!-- 操作按钮组 -->
	    <div class="col-md-5 form-inline">
		    <div class="btn-group" role="group" aria-label="..." id="btnGroup">
				  <button value="0"  type="button" class="searchTop btn btn-default btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;场馆</button>
				  <button value="1" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;活动</button>
				  <button value="2" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;赛事</button>
				  <button value="3" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;教陪</button>
				  <button value="4" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;约球</button>
			</div>
		</div>
		 <div class="col-md-7 text-right">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-primary" href="#" onclick="deleteByIds('${search_type }')" ><span class="glyphicon glyphicon-plus"></span> 批量删除</a>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
	          <th class="text-center"><input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>           
					<th >编号</th>
					<th>昵称</th>
					<th>手机号</th>
					<th>评论内容</th>
					<th>评论时间</th>
					<th>操作</th>
				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="comment" varStatus="stat">
					<tr>
						<td><input type="checkbox" name="chk_list" value="${comment.id }"/></td>
						<td class="text-center">${stat.count }</td>
						<td>${comment.nickName }</td>
						<td>${comment.phone }</td>
						<td>${comment.content }</td>
						<td>
							<fmt:formatDate value="${comment.ct }" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>
						  	<a href="#" onclick="deleteById('${comment.id }','${search_type }')"> 删除</a>
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
	  menu.active('#comment-man');
	  $('#adminFooter').hide();
	  $('#btnGroup :button').each(function(i){
		  var _this = $(this);
		  if(_this.val() == '${search_type}'){
			  $(this).removeClass("btn-success");
			  $(this).addClass("btn-success");
		  }else{
			  $(this).removeClass("btn-success");
		  }
		  _this.click(function(){
				 $('#btnGroup :button').each(function(i){
					 $(this).removeClass("btn-success");
				      $(this).addClass("btn-default");
				 });
				 _this.removeClass("btn-default");
				 _this.addClass("btn-success");
				$('#search_type').val(_this.val());
				$("#commentForm").attr("action","${ctx}/comment/list/"+_this.val());  
				$('#commentForm').submit();
			});
	  });

	$("#search_btn").click(function(){
		$("#commentForm").attr("action","${ctx}/comment/list/"+$('#search_type').val());
	})
		  
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
	  });
});
 
// 删除
function deleteById(id,type){
	  var $form = $('#commentForm');
	  bootbox.confirm('您确定要删除该评论吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/comment/delete/'+type, function(data){
	      	 if(data.result){
		      	 myAlert("删除评论成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("评论删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
	
	function getSelected() {
	  var ids = [];
	  var checked = $('input:checked');
	  if (checked.length) {
	    checked.each(function() {
	      if ($(this).attr('name') != 'chk_all') {
	        ids.push($(this).val());
	      }
	    });
	  }
	  return ids;
	}

  function deleteByIds(type){
	var ids = getSelected();
	if (ids.length == 0) {
		bootbox.alert('请选择要删除的角色！');
		return false;
	}
	  var $form = $('#commentForm');
	  bootbox.confirm('您确定要删除该评论吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/comment/deleteAll/'+type, function(data){
	      	 if(data.result){
		      	 myAlert("删除评论成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("评论删除失败","error");
			 }
	      }, null, {"ids":ids.join(',')}, 'post');
	    }
	  });
 }
 
</script>

</body>
</html>