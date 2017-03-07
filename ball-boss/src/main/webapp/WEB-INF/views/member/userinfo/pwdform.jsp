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
        <li><span class="glyphicon glyphicon-home"></span> 修改密码</li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" method="post" class="form-horizontal">
        <zy:token/>
		<fieldset>
		    <div class="form-group form-group-sm">
				<label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>旧密码:</label>
				<div class="col-md-6 has-feedback">
				    <input type="password" class="form-control" id="old" name="old" />
				</div>
		    </div>
	    
			<div class="form-group form-group-sm">
                <label for="nickname" class="col-md-3 control-label"><span class="text-red">* </span>新密码:</label>
                <div class="col-md-6 has-feedback">
				    <input type="password" class="form-control" id="password" name="password" />
				</div>
			</div>
      
        <div class="form-group form-group-sm">
         <label for="secMobile" class="col-md-3 control-label"><span class="text-red">* </span>重复新密码:</label>
         <div class="col-md-6 has-feedback">
           <input type="password" class="form-control" id="again" name="again" />
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
			old: {
				required: true
			},
            password: {
                required: true
            },
			again: {
				required: true
				, equalTo : '#password'
			}
		},
		messages: {
			again: {
				equalTo: '两次密码输入不一致！'
			}
		}
	});
	$('#footer').hide();
});
</script>
</body>
</html>
