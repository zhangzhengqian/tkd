<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%
if (SecurityUtils.getSubject().isAuthenticated()) {
    response.sendRedirect(request.getContextPath());
}
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="renderer" content="webkit" />
<title></title>

<script type="text/javascript">var ctx = '${pageContext.request.contextPath}';</script>



</head>

<body>
<%
if (SecurityUtils.getSubject().isAuthenticated()) {
    response.sendRedirect(request.getContextPath());
}
%>

<div class="container">

<div class="row">
	<div class="col-md-12" style="min-height: 100px;"></div>
</div>

<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">球友圈-CRM</h3>
			</div>
			<div class="panel-body">

					<c:if test="${!empty shiroLoginFailure }">
						<p class="help-block text-center text-red">
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
						</p>
					</c:if>

					<form id="loginForm" class="form-horizontal" role="form" action="${ctx}/login" method="post">
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">账号</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="username" value="root"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="password"  value="123123"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">&nbsp;</label>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">&nbsp;</label>
						<div class="col-sm-10">
							<button type="submit" class="btn btn-default">登&nbsp;&nbsp;录</button>
						</div>
					</div>
					
				</form>
							
			
			</div>
		</div>

	</div>

	<div class="col-md-4"></div>
</div>

</div>

</body>
</html>