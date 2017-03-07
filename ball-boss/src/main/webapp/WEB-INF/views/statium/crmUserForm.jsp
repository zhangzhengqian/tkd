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
				href="${ctx }/statium/"> 场馆列表 </a></li>
			<li class="active">crm用户添加</li>
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
				<small>用户信息</small>
			</legend>
			<form id="inputForm" action="${ctx}/statium/saveCrmUser"
				method="post" class="form-horizontal">
				<input type="hidden" name="statiumId" value="${user.statiumId}">
				<input type="hidden" name="userId" value="${user.userId}">
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
							<a href="/statium" class="btn btn-default btn-block">
								返回 </a>
						</div>
						<div class="col-md-3">
							<button type="submit" id="submit_btn"
								class="btn btn-primary btn-block">保存</button>
						</div>
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
		menu.active('#statium-man');
		$('#adminFooter').hide();

		// 保存
		$("#submit_btn").click(function() {
			$("#inputForm").submit();
		});
		var userId = '${user.userId}';
		if(userId==''){
			$('#inputForm').validate({
				rules : {
					loginName : {
						required : true,
						letter : true,
						rangelength : [ 6, 16 ],
						remote : {
							url : "${ctx}/statium/checkLoginName?oldName="
						}
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
				messages : {
					loginName : {
						remote : '登录名已经存在，请重新输入！'
					}
				}
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
				messages : {
					loginName : {
						remote : '登录名已经存在，请重新输入！'
					}
				}
			});
		}
	});
</script>