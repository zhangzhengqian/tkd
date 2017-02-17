<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="infoNav.jsp"%>
<form id="statiumUserForm" action="${ctx}/infoManage/saveUser" method="post" class="form-horizontal">
	<div class="formTable">
		<ul>
			<li>
				<span class="title">登录名：</span>
				<input type="text" id="loginName" name="loginName" value="${user.loginName }" disabled>
			</li>
			<li>
				<span class="title">原密码：</span>
				<input type="password"  id="password" name="password" >
			</li>
			<li>
				<span class="title">新密码：</span>
				<input type="password" id="newPassword" name="newPassword">
			</li>
		</ul>
		<div class="formSubDiv">
			<a class="saveBtnBot" href="javascript:saveUser()">保存</a>
		</div>

	</div>
</form>

<script type="text/javascript">
	$(function() {
		// 样式
		$('#info-man').addClass("active");
		$('#STATIUM_USER').addClass("active");
	});
	// 保存
	function saveUser() {
		// 判断原密码是否为空
		if ($('#password').val() == undefined || $('#password').val() == ''){
			swal({
				title: "警告",
				text: "原密码不能为空！"
			});
			return;
		}
		// 判断新密码是否为空
		if ($('#newPassword').val() == undefined || $('#newPassword').val() == ''){
			swal({
				title: "警告",
				text: "新密码不能为空！"
			});
			return;
		}
		// 判断原密码和新密码不能相同
		if($('#password').val() == $('#newPassword').val()){
			swal({
				title: "警告",
				text: "原密码和新密码不能相同！"
			});
			return;
		}
		swal({
			title: "",
			text: "您确定修改密码？",
			type: "warning",
			showCancelButton: "true",
			showConfirmButton: "true",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			animation: "slide-from-top"
		}, function () {
			$('#loading').show();
			$.ajax({
				url : "${ctx }/infoManage/saveUser",
				method : "POST",
				data : $('#statiumUserForm').serialize(),
				dataType: 'json',
				success: function(data){
					$('#loading').hide();
					if(data.result=='success'){
						swal({
							title: "提示",
							text: "密码修改成功",
							showConfirmButton: "true",
							confirmButtonText: "确定",
							animation: "slide-from-top"
						}, function () {
							$('#password').val() == '';
							$('#newPassword').val() == '';
							location.href = "${ctx }/logout";
						})
					} else {
						swal({
							title: "警告",
							text: data.data
						})
					}
				}
			});
		})
	}

</script>

</body>
</html>