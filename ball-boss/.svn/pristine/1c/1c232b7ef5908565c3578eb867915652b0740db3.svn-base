<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动管理</title>
	<style type="text/css">
		.label2{
		text-align: left; 
		font-weight: normal;
		}
	</style>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
		        <li class="active">活动管理</li>
			    <li class="active">报名详情</li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
	  
	  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>用户审核</small></legend>
		<form id="inputForm" action="${ctx}/ssouser/updateSsoUser" method="post" class="form-horizontal" enctype="multipart/form-data">
	        <zy:token/>
	        <input type="hidden" id="id" name="id" value="${ssoUser.id }"/>
			<fieldset>
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label">个人头像:</label>
					<div class="col-md-1 has-feedback">
						<img id="icon_img" src="${ssoUser.photo }" style="width:128px;height:128px;display:block;margin-left: auto;margin-right: auto;"/>
					</div>
				</div>
			
				<div class="form-group form-group-sm">
					<label class="col-md-3 control-label">球友号:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.qiuyouno }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">用户昵称:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.nickName }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">用户姓名:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.name }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">用户性别:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.sex }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">会员等级:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.level }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label class="col-md-3 control-label">出生日期:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">
						<fmt:formatDate value="${ssoUser.birthday }" pattern="yyyy-MM-dd"/>
					</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">所在地区:</label>
					<label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.city }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">家庭住址:</label>
				    <label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.address }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">身份证号:</label>
				    <label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.cardId }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">注册手机:</label>
				    <label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.phone }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">电子邮箱:</label>
				    <label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.email }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">爱好:</label>
				    <label class="col-md-6 control-label label2" style="text-align:left;">${ssoUser.tags }</label>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label">累计消费:</label>
				    <label class="col-md-6 control-label label2" style="text-align:left;">${orderAmount}</label>
				</div>
			</fieldset>
			
			<div class="form-group">
			 	<hr>
			 	<c:if test="${auditState == 1}">
			 		<div class="col-md-offset-2 col-md-2">
			 			<a class="btn btn-default btn-block" href="${ctx}/activity/updateState/${ssoUser.id}/${activityItemId }/2" style="background-color: #dff0d8;" ><span class="glyphicon glyphicon-ok"></span> 审核通过</a>
			 		</div>
			 		<div class="col-md-2">
			 			<a class="btn btn-primary btn-block" href="${ctx}/activity/updateState/${ssoUser.id}/${activityItemId }/3"><span class="glyphicon glyphicon-remove"></span> 审核不通过</a>	
			 		</div>
			 	</c:if>
			 	<c:if test="${auditState != 1 }">
			 		<div class="col-md-offset-2 col-md-2"></div>
			 	</c:if>
				<div class="col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/activity/userList/${activityItemId }"><span class="glyphicon"></span> 返回</a>			  
				</div>
			</div>
		</form>
	  </div>
	</div>
	<script type="text/javascript">
	  $(function() {
		  menu.active('#activity-man');
	  });
	</script>
</body>
</html>
