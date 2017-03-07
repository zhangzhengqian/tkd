<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">

  <div class="panel-heading">

	<div class="row">
	    <div class="col-xs-10 col-xs-offset-1">
	        <div class="progress progress-chart">
	            <span class="progress-chart-1st"></span>
	            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
	                <span><!-- 节点 --></span>
	            </div>
	            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
	                <span><!-- 节点 --></span>
	            </div>
	            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
	                <span><!-- 节点 --></span>
	            </div>
	            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
	                <span><!-- 节点 --></span>
	            </div>
	            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
	                <span><!-- 节点 --></span>
	            </div>
	        </div><!--// progress:end -->
	        <div class="progress-chart-text">
	            <span class="">注册账号</span>
	            <span class="">公司资质</span>
	            <span class="">营业资质</span>
	            <span class="">球馆信息</span>
	            <span>提交审核</span>
	            <span class="progress-chart-text-last">完成</span>                            
	        </div>
	    </div>
  	</div>


  </div>
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/register/save_user" method="post" class="form-horizontal">
		
		<fieldset>
			<legend><small>注册账号</small></legend>
			
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
           <input type="number" class="form-control" id="secMobile" name="secMobile" value="" />
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
      
		<div class="form-group">
			<div class="col-md-offset-3 col-md-2">
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
$(function() {
	$("#nav_register").attr("class","active");
	$('#inputForm').validate({
		rules: {
			loginName: {
				required: true
				, letter: true
				, rangelength : [6, 16]
	      		//, remote: "${ctx}/admin/user/checkLoginName?oldName=" + encodeURIComponent('')
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
			},
			nickname: {
				remote: '用户名已经存在，请重新输入！'
			}
		}
	});
});

</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

