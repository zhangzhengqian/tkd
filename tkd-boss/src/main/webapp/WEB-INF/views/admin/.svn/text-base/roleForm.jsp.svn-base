<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色管理</title>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
</head>

<body>
<div class="panel panel-default">

<div class="panel-heading"><!-- 右侧标题 -->
  <ul class="breadcrumb">
    <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
    <li><a href="${ctx}/admin/role"> 角色管理</a></li>
    <li class="active">
      <c:if test="${'create' eq action }"> 新建角色</c:if>
      <c:if test="${'update' eq action }"> 修改角色</c:if>
    </li>
  </ul>
</div><!-- / 右侧标题 -->

<div class="panel-body"><!-- 右侧主体内容 -->
	
<form id="form1" role="form" method="post" class="form-horizontal" action="${ctx}/admin/role/${action }">
	<zy:token/>
	<input type="hidden" name="roleId" value="${role.roleId}"/>
	<fieldset>
		<legend><small>角色信息</small></legend>
		<div class="form-group form-group-sm">
		   <label for="roleCode" class="col-sm-2 control-label"><span class="text-red">* </span>角色编码:</label>
		   <div class="col-sm-6 has-feedback">
			   <input type="text" class="form-control" id="roleCode" name="roleCode" value="${role.roleCode}"
			     placeholder="只能输入英文字母和数字" required="required"
			     <c:if test="${!empty role.roleId }">readonly="readonly"</c:if> />
		   </div>
		</div>
		
		<div class="form-group form-group-sm">
       <label for="roleName" class="col-sm-2 control-label"><span class="text-red">* </span>角色名称:</label>
       <div class="col-sm-6 has-feedback">
         <input type="text" class="form-control" id="roleName" name="roleName" value="${role.roleName}" placeholder="" />
       </div>
    </div>
    <%-- 
		<div class="form-group form-group-sm">
       <label for="isAdmin" class="col-sm-2 control-label"><span class="text-red">* </span>管理员:</label>
       <div class="col-sm-6 has-feedback">
        <label class="radio-inline"><input type="radio" name="isAdmin" value="0" /> 否</label>
        <label class="radio-inline"><input type="radio" name="isAdmin" value="1" /> 是</label>
       </div>
    </div>
    --%>
	</fieldset>
	
	<div class="form-group">
	   <div class="col-sm-offset-2 col-sm-4">
	     <a class="btn btn-default" href="${ctx}/admin/role"><span class="glyphicon glyphicon-remove"></span> 返回</a>
	     <span class="col-sm-offset-1"></span>
	     <button type="submit" class="btn btn-primary" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
	   </div>
	</div>
          
</form>


</div><!-- / 右侧主体内容 -->

</div>


<script type="text/javascript">
  $(document).ready(function() {
    menu.active('#role-man');
    
    //为表单注册validate函数
    $("#form1").validate({
    	submitHandler: function(form) {
    		form.submit();
    	},
      rules: {
        roleCode: {
          required: true,
          rangelength: [2, 20],
          remote: "${ctx}/admin/role/checkRoleCode?oldCode=" + encodeURIComponent('${role.roleCode}')
        },
        roleName: {
          required: true,
          rangelength: [2, 20],
          remote: "${ctx}/admin/role/checkRoleName?oldName=" + encodeURIComponent('${role.roleName}')
        },
        isAdmin: {
        	required:true
        }
      },
      messages: {
        roleCode: {
          remote: "角色编码已经存在"
        },
        roleName: {
          remote: "角色名已存在"
        }
      }
    });
  });
</script>

</body>
</html>
