<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<%-- <!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>操作员管理</title>
  <script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
  <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

</head>

<body> --%>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 场馆管理</li>
        <li>操作员管理</li>
        <li class="active">
          <c:if test="${'create' eq action }"> 新建操作员</c:if>
          <c:if test="${'update' eq action }"> 修改操作员</c:if>
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/member/operator/${action }" method="post" class="form-horizontal">
    <zy:token/>
		<input type="hidden" name="userId" value="${user.userId}"/>
		
		<fieldset>
			<legend><small>操作员信息</small></legend>
			
	    <div class="form-group form-group-sm">
	       <label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>登录账号:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" class="form-control" id="loginName" name="loginName" value="${user.loginName}"
	           <c:if test="${!empty user.userId }">readonly="readonly"</c:if> />
	       </div>
	    </div>
	    
        <div class="form-group form-group-sm">
         <label for="nickname" class="col-md-3 control-label"><span class="text-red">* </span>姓名:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" />
         </div>
      </div>
      
        <div class="form-group form-group-sm">
         <label for="secMobile" class="col-md-3 control-label"><span class="text-red">* </span>手机:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="secMobile" name="secMobile" value="${user.secMobile}" />
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
      
		
		
		<br/>
		
		<!--  <div class="form-group"> -->
			<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/member/operator"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
			  <button type="submit" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
			</div>
		<!-- </div> -->
		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">
$(function() {
	menu.active('#operator-man');
	
 	$('#inputForm').validate({
		rules: {
			loginName: {
				required: true
				, letter: true
				, rangelength : [6, 16]
	            /* , remote: "${ctx}/admin/user/checkLoginName?oldName=" + encodeURIComponent("${user.loginName}" ) */
			},
            nickname: {
                required: true
                , rangelength : [2, 16]
			/* , remote: "${ctx}/admin/user/checkNickname?oldName=" + encodeURIComponent('${user.nickname}' )*/
            },
            secMobile: {
                required: true
                ,isMobile: true
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
			},
			nickname: {
				remote: '用户名已经存在，请重新输入！'
			}
		}
	}); 
	$('#footer').hide();
});
</script>
<!-- </body>
</html> -->
