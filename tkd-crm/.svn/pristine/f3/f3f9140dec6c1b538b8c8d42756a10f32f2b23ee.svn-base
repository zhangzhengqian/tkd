<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>球友圈CRM登录</title>
<link href="static/css/pages/login.css?ver=2345" rel="stylesheet">
<link href="static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
</head>
<%
if (SecurityUtils.getSubject().isAuthenticated()) {
    response.sendRedirect(request.getContextPath());
}
%>
<div class="loginBody">
	<div class="loginCont">
		<form id="loginForm" class="form-horizontal" role="form" action="${ctx}/login" method="post">
		<dl>
			<dt>登录</dt>
			<dd class="ddName"><em></em><input name="username" type="text" placeholder="请输入登录账户" value=""/></dd>
			<dd class="ddPassword"><em></em><input name="password" type="password" placeholder="请输入登录密码" value=""/></dd>
			<dd class="ddTip">
				<span data-tip="错误信息提示">
					<c:if test="${!empty shiroLoginFailure }">
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
			<dd class="ddLoginBtn"><a id="loginBtn" href="javascript:;">登录</a></dd>
			<%--<dd class="ddDes">北京乾正隆文化交流有限公司   　&copy;2016</dd>
			<dd class="ddDesTel">服务热线：010-57198965 </dd>
			<dd class="ddDesTel"><tags:version /></dd>--%>
		</dl>
		</form>
	</div>
</div>
<script src="${ctx}/static/lib/jquery-2.1.1.js"></script>
<script src="${ctx}/static/lib/bootstrap.min.js"></script>
<script src="${ctx}/static/lib/plugins/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="static/lib/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">

$(function() {
  //为表单注册validate函数
  /* $("#loginForm").validate({
	  rules : {
		  username: {
			  required: true,
			  rangelength: [2, 60]
		  }
	  },
    messages: {
      username: {
        required: "请输入用户名"
      },
      password: {
    	  required: "请输入密码"
      }
    }
  }); */
  	var mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
  	var webkit = /webkit/.test(navigator.userAgent.toLowerCase());
	$('#username').focus();
	$("#loginBtn").click(function(){
	if(mozilla || webkit){
		$("#loginForm").submit();
		}else{
		swal({
            title: "只支持360急速浏览器和Firefox浏览器，请更换或下载浏览器",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"火狐浏览器下载",
            cancelButtonColor: "#DD6B55",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "360急速浏览器下载",
            closeOnConfirm: false,
            closeOnCancel: false
        }, function (isConfirm) {
        	if (isConfirm) {
        		window.open("http://chrome.360.cn/");
        	}else{
        		window.open("http://rj.baidu.com/soft/detail/11843.html?ald");
        	}
        });
	}
	});
});  
</script>
</html>