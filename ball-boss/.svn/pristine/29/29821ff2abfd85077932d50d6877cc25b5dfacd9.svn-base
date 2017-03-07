<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>员工管理</title>


<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

<link rel="stylesheet" href="${ctx}/static/js/jquery/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
<style type="text/css">
<!--
.ztree * {
	font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei UI",
		simsun, sans-serif;
	font-size: 14px;
}

.ztree li {
	margin: 3px 0;
}

.ztree li a.curSelectedNode {
	height: 18px;
}

/*冻结根结节*/
.ztree li span.button.switch.level0 {
	visibility: hidden;
	width: 1px;
}

.ztree li ul.level0 {
	padding: 0;
	background: none;
}

/*根节点图标样式*/
.ztree li span.button.root_ico_open,.ztree li span.button.root_ico_close
	{
	width: 0px;
	height: 0px;
}


/*编辑按钮图标样式*/
.ztree li a span.button.edit
, .ztree li a span.button.remove
, .ztree li a span.button.add {
	margin-left: 10px;
	margin-right: -5px;
}
.ztree li span.button.add {
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}

.tree-container {
	border: #efefef 1px solid;
	overflow: auto;
}
-->
</style>

</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
        <li>渠道用户管理</li>
        <li class="active">
          <c:if test="${'createChannel' eq action }"> 新建员工</c:if>
          <c:if test="${'updateChannel' eq action }"> 修改员工</c:if>
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  

		<fieldset> <legend><small>渠道用户信息</small></legend>
			
			
		
<div class="row">
	<div class="col-sm-9">
		<form id="inputForm" action="${ctx}/admin/user/${action }" method="post" class="form-horizontal">
		<input type="hidden" name="userId" value="${user.userId}"/>
		<input type="hidden" name="orgCode" id="orgCode" value="${user.orgCode}"/>
		
	    <div class="form-group form-group-sm">
	       <label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>登录账号:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" class="form-control" id="loginName" name="loginName" value="${user.loginName}"
	           <c:if test="${!empty user.userId }">readonly="readonly"</c:if> />
	       </div>
	    </div>
	    
	    <div class="form-group form-group-sm">
         <label for="nickname" class="col-md-3 control-label"><span class="text-red">* </span>真实姓名:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" />
         </div>
      </div>
      
      <c:if test="${empty user.userId }" ><%-- 新建时显示, 修改时不显示 --%>
      
      <div class="form-group form-group-sm">
         <label for="password" class="col-md-3 control-label"><span class="text-red">* </span>密码:</label>
         <div class="col-md-6 has-feedback">
           <input type="password" class="form-control" id="password" name="password" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="againPassword" class="col-md-3 control-label"><span class="text-red">* </span>重复密码:</label>
         <div class="col-md-6 has-feedback">
           <input type="password" class="form-control" id="againPassword" name="againPassword" />
         </div>
      </div>
      </c:if><%-- /新建时显示 --%>
		<div class="form-group form-group-sm">
		    <label for="type" class="col-md-3 control-label">*员工角色</label>
		    <div class="col-md-6 has-feedback">
				<c:forEach items="${roles}" var="role">
					<p>
						<c:set var="role_checked" value="" />
						<c:forEach items="${user_roles }" var="user_role">
							<c:if test="${user_role.roleCode eq role.roleCode }">
								<c:set var="role_checked" value="checked='checked'" />
							</c:if>	
						</c:forEach>
						<input type="checkbox" name="roleIds" id="roleIds" ${role_checked } value="${role.roleId }" />
						&nbsp;&nbsp;${role.roleName }
					</p>
				</c:forEach>
		  	</div>
	    </div>
		</form>
	</div><!-- end col-sm-9 -->
</div><!-- end row -->	
		 
		</fieldset>
		
		 <div class="form-group">
		 	<hr>
			<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/admin/user/channelList"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
			  <button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
			</div>
		</div>


  </div>
	
</div>

<script type="text/javascript">

$(function() {
	menu.active('#user-man');
	
	$("#submit_btn").click(function(){
		$("#inputForm").submit();
	});
	
	$('#inputForm').validate({
		submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮
			app.disabled("#submit_btn");
			//提交表单
			form.submit(); 
		},
		rules: {
			roleIds : {
				required: true
			},
			loginName: {
				required: true
				, letter: true
				, rangelength : [4, 16]
	        ,remote: "${ctx}/admin/user/checkLoginName?oldName=" + encodeURIComponent('${user.loginName}')
			},
			nickname: {
				required: true
				, rangelength : [2, 16]
			},
			password: {
				required: true
				, rangelength : [6, 20]
			},
			againPassword: {
				required: true
				, equalTo : '#password'
			}
		},
		messages: {
			loginName: {
				remote: '登录名已经存在，请重新输入！'
			}
		}
    });
	$('#adminFooter').hide();

});
	
</script>
</body>
</html>
