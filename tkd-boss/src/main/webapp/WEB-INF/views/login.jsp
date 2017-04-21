<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<link href="static/css/login.css" rel="stylesheet">
<%
	if (SecurityUtils.getSubject().isAuthenticated()) {
		response.sendRedirect(request.getContextPath());
	}
%>
<script src="${ctx}/static/js/jquery-validate/jquery.js"
	type="text/javascript"></script>
<div class="loginBody">
	<div class="loginCont">
		<form id="loginForm" class="form-horizontal" role="form"
			action="${ctx}/login" method="post">
			<dl>
				<dt>乾正隆－运营平台</dt>
				<dd class="ddName">
					<em></em><input name="username" id="username" type="text"
						placeholder="请输入登录账户" onblur="checkService()" />
				</dd>
				<dd class="ddPassword">
					<em></em><input name="password" type="password"
						placeholder="请输入登录密码" value="" />
				</dd>
				<dd class="ddTip">
					<span data-tip="错误信息提示"> <c:if
							test="${!empty shiroLoginFailure }">
							<c:choose>
								<c:when
									test="${fn:containsIgnoreCase(shiroLoginFailure, 'DisabledAccountException') }">
									<c:out value="该账号已被禁用,请联系管理员或使用其他账号登录." />
								</c:when>
								<c:when
									test="${fn:containsIgnoreCase(shiroLoginFailure, 'IncorrectCaptchaException') }">
									<c:out value="验证码输入不正确." />
								</c:when>
								<c:otherwise>
									<c:out value="登录失败，请检查用户名和密码后重新输入！" />
								</c:otherwise>
							</c:choose>
						</c:if>
					</span>
				</dd>
				<dd class="ddLoginBtn">
					<button type="submit" class="btn btn-default">登&nbsp;&nbsp;录</button>
				</dd>
				<dd class="ddDes">北京乾正隆文化交流有限公司   　&copy2015</dd>
				<dd class="ddDesTel"><tags:version /></dd>
			</dl>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		//为表单注册validate函数
		$("#loginForm").validate({
			rules : {
				username : {
					required : true,
					rangelength : [ 2, 60 ]
				}
			},
			messages : {
				username : {
					required : "请输入用户名"
				},
				password : {
					required : "请输入密码"
				}
			}
		});

		$('#username').focus();
		$("#loginBtn").click(function() {
			if ($("#agentNameDD").css('display') == 'block') {
				//
			}
			$("#loginForm").submit();
		});
	});

	function refreshCaptcha() {
		document.getElementById("img_captcha").src = "${ctx}/images/kaptcha.jpg?t="
				+ Math.random();
	}
</script>
