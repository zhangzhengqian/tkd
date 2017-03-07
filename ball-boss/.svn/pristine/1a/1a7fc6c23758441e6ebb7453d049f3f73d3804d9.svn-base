<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<link href="static/css/login.css" rel="stylesheet">
<%
if (SecurityUtils.getSubject().isAuthenticated()) {
    response.sendRedirect(request.getContextPath());
}
%>
<div class="loginBody">
	<div class="loginCont">
		<form id="loginForm" class="form-horizontal" role="form" action="${ctx}/login" method="post">
		<dl>
			<dt>球友圈-运营平台</dt>
			<dd class="ddName"><em></em><input name="username" id="username" type="text" placeholder="请输入登录账户"  /></dd>
			<dd class="ddPassword"><em></em><input name="password" type="password" placeholder="请输入登录密码" value=""/></dd>
 			<dd class="ddName"   id="agentNameDD"><em></em><input name="agent" id="agent"  type="text" placeholder="请输入坐席号" value=""/></dd>
			<dd class="ddPassword"   id="agentPasswordDD" ><em></em><input name="agentPwd" id="agentPwd"  type="password" placeholder="请输入坐席密码" value=""/></dd>
			<dt style="font-size: 14px;"><em></em>是否客服：<input name="isService"  onclick="changeRadio(0)"  type="radio"  value="0" checked="checked"/>否
			&nbsp;&nbsp;<input name="isService"  onclick="changeRadio(1)"   type="radio"  value="1"/>是</dt> 
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
			<dd class="ddLoginBtn"><button type="submit" class="btn btn-default">登&nbsp;&nbsp;录</button></dd>
			<dd class="ddDes">北京球友圈网络科技有限责任公司   　&copy2015</dd>
			<dd class="ddDesTel"><tags:version /></dd>
		</dl>
		</form>
	</div>
</div>
<%-- <div class="container">

<div class="row">
	<div class="col-md-12" style="min-height: 100px;"></div>
</div>

<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">球友圈-运营平台</h3>
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
			<div class="panel-footer text-right"> <tags:version /> </div>
		</div>

	</div>

	<div class="col-md-4"></div>
</div>


</div>

<footer class="bs-docs-footer" role="contentinfo">
  <div class="container">
    <p class="pull-right">
    </p> 
  </div>
</footer> --%>
<script type="text/javascript">

$(function() {
	$("#agentNameDD").css('display','none');
	$("#agentPasswordDD").css('display','none');
  //为表单注册validate函数
  $("#loginForm").validate({
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
  });

  $('#username').focus();
  $("#loginBtn").click(function(){
	   if($("#agentNameDD").css('display') == 'block'){
		   //
	   }
		$("#loginForm").submit();
	});
});

//检验是否是客服 ,是客服的必须先登录坐席号
 function changeRadio(v){
	if (v == 1) {
		$("#agentNameDD").css('display','block');
		$("#agentPasswordDD").css('display','block');
		//添加
		$("#agent").rules("add",{required:true});
		$("#agentPwd").rules("add",{required:true});
	}else{
		$("#agentNameDD").css('display','none');
		$("#agentPasswordDD").css('display','none');
		$("#agent").rules("remove","required");
		$("#agentPwd").rules("remove","required");
	}
}

function refreshCaptcha(){
    document.getElementById("img_captcha").src="${ctx}/images/kaptcha.jpg?t=" + Math.random();
}
</script>
