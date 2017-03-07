<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 我签约的场馆</li>
	        <li <c:if test="${param.action !='edit' }">class="active"</c:if>> 创建用户</li>
	        <c:if test="${param.action == 'edit' }">
		        <li class="active" >修改</li>
	        </c:if>
	        
	    </ul>
  	</div>
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/admin/save_user" method="post" class="form-horizontal">
		<input type="hidden" name="token" value="${token }" />
		<fieldset>

			
	    <div class="form-group form-group-sm">
	       <label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>登录账号:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" class="form-control" id="loginName" name="loginName" value="" />
	       </div>
	    </div>
	    
	  <div class="form-group form-group-sm">
         <label for="nickname" class="col-md-3 control-label"><span class="text-red">* </span>用户名:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="nickname" name="nickname" value="" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="secMobile" class="col-md-3 control-label"><span class="text-red">* </span>手机号:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="secMobile" name="secMobile" value="" />
         </div>
      </div>
      
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
     <hr> 
		<div class="form-group">
			<div class="col-md-offset-3 col-md-2">
				<c:if test="${'admin' == _from}">
					<a class="btn btn-default btn-block" href="${ctx }/admin/statium/manager">返回</a>
				</c:if>
			</div>
			<div class="col-md-2">
			  <button type="submit" class="btn btn-primary btn-block"> 下一步 >> </button>
			</div>
		</div>

		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">


<c:if test="${'admin' == _from}">
	$(function() {
		  menu.active('#statium-manager-man');
	});
</c:if>
	  

$(function() {
	$("#nav_register").attr("class","active");
	$('#inputForm').validate({
		rules: {
			loginName: {
				required: true
				, letter: true
				, rangelength : [6, 16]
	      		, remote: "${ctx}/admin/user/checkLoginName?oldName=" + encodeURIComponent('${user.loginName}')
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
			},secMobile: {
                required: true
			}
		},
		messages: {
			loginName: {
				remote: '登录名已经存在，请重新输入！'
			}
		//,
/* 			nickname: {
				remote: '用户名已经存在，请重新输入！'
			} */
		}
	});
});

</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

