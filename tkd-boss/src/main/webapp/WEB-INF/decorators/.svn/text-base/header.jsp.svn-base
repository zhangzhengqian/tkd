<%@page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%--
<nav class="navbar navbar-default navbar-static-top" >
--%>
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">

	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<!-- LOGO -->
			<a class="navbar-brand" href="${ctx }/"><img src="${ctx }/static/img/logo.png"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		
			<ul class="nav navbar-nav">
				<li class="active"><a href="${ctx }/"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> &nbsp;  首页</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<%--
				<li>
					<a href="#" >
						<span class="glyphicon glyphicon-time"></span>
						<% request.setAttribute("now", new java.util.Date()); %>
						<fmt:formatDate value="${now }" pattern="yyyy-MM-dd"/>
					</a>
				</li>
				--%>
				
				<%--<li>
					<a href="#" id="message_btn" >
						<span class="glyphicon glyphicon-envelope"></span>
						消息
						<span class="badge" id="message_badge">0</span>
					</a>
				</li>--%>
				
				<li>
					<a comment="当前用户" href="${ctx }/admin/userinfo">
						<span class="glyphicon glyphicon-user"></span> <shiro:principal />
					</a>
				</li>
				
				<li>
					<a comment="退出" href="${ctx }/logout">
						<span class="glyphicon glyphicon-off"></span>退出
					</a>
				</li>
				
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
