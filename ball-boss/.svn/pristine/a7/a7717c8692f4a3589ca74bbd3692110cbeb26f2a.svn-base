<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>球友圈管理</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 球友圈管理</li>
				<li>群组管理</li>
				<li class="active">群组详情</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<h3>群组信息</h3>
			<hr>
			<form id="inputForm" action="${ctx}/corps/save" method="post"
				class="form-horizontal" enctype="multipart/form-data">
				<fieldset>
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">群号：</label>
						<div class="col-md-6" style="margin-top: 7px;">${group.groupId}</div>
					</div>


					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">群名称：</label>
						<div class="col-md-6" style="margin-top: 7px;">${group.name}</div>
					</div>
					
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">群位置：</label>
						<div class="col-md-6" style="margin-top: 7px;">${group.address}</div>
					</div>
					
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">群组昵称：</label>
						<div class="col-md-6" style="margin-top: 7px;">${group.nickname}</div>
					</div>
					
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">创建时间：</label>
						<div class="col-md-6" style="margin-top: 7px;"><fmt:formatDate value="${group.ct }" pattern="yyyy-MM-dd HH:mm" /></div>
					</div>
					
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">群介绍：</label>
						<div class="col-md-6" style="margin-top: 7px;">${group.introduce}</div>
					</div>
					
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label">群成员：</label>
						<div class="col-md-6" style="margin-top: 7px;">${group.memberTotal}
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-default btn-sm" href="${ctx}/group/userList/${group.id}"> 
							<i class="glyphicon glyphicon-search"></i>查看成员</a>
						</div>
					</div>
				</fieldset>
				<div class="form-group form-group-sm">
				  	<div class="col-md-offset-3 col-md-2">
					   <a class="btn btn-default btn-block" href="${ctx}/group"><span class="glyphicon glyphicon-remove"></span> 返回</a>
					</div> 
					<shiro:hasPermission name="group:disband">
					<div class="col-md-2">
					   <a class="btn btn-default btn-block" href="javaScript:void(0)" onclick="disband('${group.id}');"><span class="glyphicon glyphicon-trash"></span> 解散</a>
					</div>       
					</shiro:hasPermission>
				</div>	
			</form>
		</div>
		<script src="${ctx}/static/js/utils.js"></script>
		<script type="text/javascript">
			$(function() {
				  menu.active('#qiuyouzone-group');
			});
			// 解散群
			  function disband(id){
				  bootbox.confirm('您确定要解散该群组？', function(result) {
				    if(result) {
				      Util.getData(ctx + '${ctx }/group/disband', function(data){
				      	 if(data.result){
					      	 myAlert("群解散成功");
					      	 window.location.href = "${ctx}/group";
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