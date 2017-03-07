<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>个人信息</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 个人信息</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

        <table id="contentTable" class="table table-bordered table-hover">
            <tbody>
                <tr>
                    <td class="text-center">登录账号</td>
                    <td class="text-center">${user.loginName }</td>
                </tr>
                <tr>
                    <td class="text-center">真实姓名</td>
                    <td class="text-center">${user.nickname }</td>
                </tr>
                <tr>
                    <td class="text-center">员工角色</td>
                    <td class="text-center">
                    	<c:forEach var="v" items="${lf:currentUserRoles()}">
                    		${v.roleName }
                    	</c:forEach>
                    
                    </td>
                </tr>
                <tr>
                    <td class="text-center">所属组织</td>
                    <td class="text-center"><tags:orgName orgCode="${user.orgCode}" /></td>
                </tr>
            </tbody>        
        </table>
        <div class="panel-footer">
	        <div class="text-right">
                <a href="${ctx }${path }/update" class="btn btn-default">修改个人信息</a>
                <a href="${ctx }${path }/updatePwd" class="btn btn-default">修改密码</a>
	        </div>
        </div>
	</div>
    <tags:errors />

	<script type="text/javascript">
		$(function() {
			menu.active('#userinfo-man');
			$('#adminFooter').hide();
			$('#footer').hide();
		});
	</script>

</body>
</html>
