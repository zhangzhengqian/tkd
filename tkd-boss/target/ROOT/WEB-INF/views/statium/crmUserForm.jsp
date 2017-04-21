<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil"%>
<%
	String id = SessionUtil.currentUserId();
%>
<div class="panel panel-default">
	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span><a
				href="${ctx }/statium/"> 道馆列表 </a></li>
			<c:if test="${'add' eq action}"><li class="active">crm用户添加</li></c:if>
			<c:if test="${'update' eq action}"><li class="active">crm用户修改</li></c:if>
		</ul>
	</div>
	<!-- / 右侧标题 -->
	<shiro:hasPermission name="updatecrmuser:man">
		<c:set var="updatecrmuserable" value="true" />
	</shiro:hasPermission>
	<c:choose>
		<c:when test="${user.userId == null}">
			<c:set var="disable" value="false" />
		</c:when>
		<c:otherwise>
			<c:set var="disable" value="true" />
			<c:set var="readonly" value="disabled='disable'" />
		</c:otherwise>
	</c:choose>
	<div class="panel-body">
		<!-- 右侧主体内容 -->
		<fieldset>
			<legend>
				<small>${dgName }</small>
			</legend>
			<form id="inputForm" action="${ctx}/statiumOa/saveCrmUser"
				method="post" class="form-horizontal">
				<input type="hidden" name="statiumId" value="${user.statiumId}">
				<input type="hidden" name="userId" value="${user.userId}">
				<input type="hidden" name="page" value="${page}">
				<input type="hidden" name="pageSize" value="${pageSize}">
				<fieldset>
					<div class="form-group form-group-sm">
						<label for="loginName" class="col-md-3 control-label"><span
							class="text-red">* </span>登录名:</label>
						<div class="col-md-6 has-feedback">
						<c:choose>
						<c:when test="${updatecrmuserable||empty user.userId}">
							<input  type="text" class="form-control"
								id="loginName" name="loginName" value="${user.loginName }" />
						</c:when>
						<c:otherwise>
							${user.loginName }
						</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="password" class="col-md-3 control-label"><span
							class="text-red">* </span>密码:</label>
						<div class="col-md-6 has-feedback">
							<input type="password" class="form-control" id="password"
								name="password" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="againPassword" class="col-md-3 control-label"><span
							class="text-red">* </span>重复密码:</label>
						<div class="col-md-6 has-feedback">
							<input type="password" class="form-control" id="againPassword"
								name="againPassword" />
						</div>
					</div>
					<hr>
					<div class="form-group">

						<div class="col-md-offset-3 col-md-3">
							<a href="javascript:window.history.go(-1);" class="btn btn-default btn-block">
								返回 </a>
						</div>
						<shiro:hasPermission name="updatecrmuser:man"> 
						<div class="col-md-3">
							<button type="button" id="submit_btn"
								class="btn btn-primary btn-block">保存</button>
						</div>
						</shiro:hasPermission>
					</div>

				</fieldset>
			</form>
	</div>
	<div class="panel-footer">
		<div class="row text-right">
			<div class="col-sm-12"></div>
		</div>
	</div>
</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		// 菜单栏显示底色（选中）
		menu.active('#statiumOa-man');
		$('#adminFooter').hide();
		// 保存
		var userId = '${user.userId}';
		$("#submit_btn").click(function() {
			var loginName = $("#loginName").val();
			 $.ajax({
				cache:true,
				url:"${ctx}/statiumOa/checkLoginName",
				dataType:"text",
				type:"post",
				data:{
					"loginName":loginName,
					"userId":userId,
				},
				error:function(request){
					common.showMessage("保存用户失败");
				},
				success:function(data){
					//var data= eval("("+data+")");
					data = JSON.parse(data);
					if(!data.result||data.reason!=null){
						common.showMessage(data.reason);
					}
					else{
						$("#inputForm").submit();
					} 
				}
			});
		});
		if(userId==''){
			$('#inputForm').validate({
				rules : {
					loginName : {
						required : true,
						letter : true,
						rangelength : [ 3, 16 ],
					},
					password : {
						required : true,
						rangelength : [ 6, 20 ]
					},
					againPassword : {
						required : true,
						equalTo : '#password'
					}
				},
			});
		}else{
			$('#inputForm').validate({
				rules : {
					password : {
						required : false,
						rangelength : [ 6, 20 ]
					},
					againPassword : {
						required : false,
						equalTo : '#password'
					}
				},
			});
		}
	});
</script> 