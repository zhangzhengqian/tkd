<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>消息服务器</title>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
</head>

<body>
<div class="panel panel-default">

<div class="panel-heading"><!-- 右侧标题 -->
  <ul class="breadcrumb">
    <li><span class="glyphicon glyphicon-home"></span> 消息服务器</li>
    <li><a href="${ctx}/admin/emsg"> 消息服务器信息</a></li>
<%--     <li class="active">
      <c:if test="${'create' eq action }"> 新建角色</c:if>
      <c:if test="${'update' eq action }"> 修改角色</c:if>
    </li> --%>
  </ul>
</div><!-- / 右侧标题 -->

<div class="panel-body"><!-- 右侧主体内容 -->
	
<form id="form" role="form" method="post" class="form-horizontal" action="${ctx}/admin/emsg/server/save">
	<zy:token/>
	<input type="hidden" name="id" value="${emsgServer.id }"/>
	<fieldset>
		<legend><small>消息服务器信息</small></legend>
		<div class="form-group form-group-sm">
		   <label for="host" class="col-sm-2 control-label"><span class="text-red">* </span>主机:</label>
		   <div class="col-sm-6 has-feedback">
				<input type="text" class="form-control" id="host" name="host" value="${emsgServer.host }" placeholder="只能输入域名或IP" />
		   </div>
		</div>
		
		<div class="form-group form-group-sm">
	       	<label for="port" class="col-sm-2 control-label"><span class="text-red">* </span>端口:</label>
	       	<div class="col-sm-6 has-feedback">
	        	<input type="text" class="form-control" id="port" name="port" value="${emsgServer.port }" placeholder="只能输入数字" />
	       	</div>
    	</div>
		
		<div class="form-group form-group-sm">
	       	<label for="domain" class="col-sm-2 control-label"><span class="text-red">* </span>域名:</label>
	       	<div class="col-sm-6 has-feedback">
	        	<input type="text" class="form-control" id="domain" name="domain" value="${emsgServer.domain }" placeholder="只能输入域名" />
	       	</div>
    	</div>
    	
		<div class="form-group form-group-sm">
	       	<label for="license" class="col-sm-2 control-label"><span class="text-red">* </span>License:</label>
	       	<div class="col-sm-6 has-feedback">
	        	<input type="text" class="form-control" id="license" name="license" value="${emsgServer.license }" />
	       	</div>
    	</div> 	
    	
	</fieldset>
	
	<div class="form-group">
	   <div class="col-sm-offset-2 col-sm-4">
	     <span class="col-sm-offset-5"></span>
	     <button type="submit" class="btn btn-primary" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
	   </div>
	</div>
          
</form>

</div><!-- / 右侧主体内容 -->

</div>

<script type="text/javascript">
  $(document).ready(function() {
    menu.active('#emsg-man');
    
    //为表单注册validate函数
    $("#form").validate({
    	submitHandler: function(form) {
    		form.submit();
    	},
      rules: {
        host: {
          	required: true
        },
        port: {
        	required: true
        },
        domain: {
        	required:true
        },
        license: {
        	required:true
        }
      }
    });
  });
</script>

</body>
</html>
