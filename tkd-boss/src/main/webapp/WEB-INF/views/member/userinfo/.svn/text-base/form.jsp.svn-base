<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改个人信息</title>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
			<ul class="breadcrumb">
			    <li><span class="glyphicon glyphicon-home"></span> 修改个人信息</li>
	        </ul>
		</div><!-- / 右侧标题 -->
	<div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" method="post" class="form-horizontal">
        <input type="hidden" name="userId" value="${user.userId }" />
        <zy:token/>
        <fieldset>
			<legend><small>个人信息</small></legend>
		    <div class="form-group form-group-sm">
				<label for="loginName" class="col-md-3 control-label"><span class="text-red"> </span>登录账号:</label>
				<div class="col-md-6 has-feedback">
				    <input type="text" class="form-control" id="loginName" name="loginName" value="${user.loginName}" readonly />
				</div>
		    </div>
	    
			<div class="form-group form-group-sm">
                <label for="nickname" class="col-md-3 control-label"><span class="text-red">* </span>真实姓名:</label>
                <div class="col-md-6 has-feedback">
				    <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" />
				</div>
			</div>
      
		</fieldset>
		<br/>
		 <div class="form-group">
			<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="javascript:history.go(-1);"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
			  <button type="submit" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
			</div>
		</div>
	</form>
  </div>
</div>

<script type="text/javascript">
$(function() {
	menu.active('#userinfo-man');
	
	$('#inputForm').validate({
		rules: {
			loginName: {
				//修改不修改loginName
				//required: true
				//, letter: true
				//, rangelength : [6, 16]
			},
            nickname: {
                required: true
                , rangelength : [2, 16]
            },
            secMobile: {
                required: true
                ,isMobile: true
			}
		},
		messages: {
		}
	});
	$('#adminFooter').hide();
	$('#footer').hide();
});
</script>
</body>
</html>
