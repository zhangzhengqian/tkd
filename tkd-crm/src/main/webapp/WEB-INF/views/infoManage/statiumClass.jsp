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

<form  id="classForm" class="form-horizontal" action="" method="post" name="id">
	<div class="orderTipMeg">
		<a class="addLockBtn" href="${ctx }/infoManage/classForm">添加课程</a><p></p>
	</div>
</form>

<div class="orderResult">
	<table>
		<tr>
			<th>课程名称</th>
			<th>原价</th>
			<th>签约价</th>
			<th>课程类型</th>
			<th>人数</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${data.content }" var="class" varStatus="stat">
			<tr>
				<td>
					<a href="${ctx }/infoManage/classForm?id=${class.id }"> ${class.classTitle }</a>
				</td>
				<td><fmt:formatNumber type="number" value="${class.price/100 }" maxFractionDigits="0"/></td>
				<td><fmt:formatNumber type="number" value="${class.discountPrice/100 }" maxFractionDigits="0"/></td>
				<c:choose>
					<c:when test="${class.type == 0}">
						 <td>大课</td>
					</c:when>
					<c:when test="${class.type == 1}">
						<td>私教</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${class.type == 0}">
						<td>${class.minPeople}～${class.maxPeople }</td>
					</c:when>
					<c:when test="${class.type == 1}">
						<td>一对一</td>
					</c:when>
				</c:choose>
				<td>
					<a class="btn btn-default btn-sm" href="${ctx }/infoManage/classForm?id=${class.id }"><i class="glyphicon glyphicon-edit"></i> 修改</a>
					<a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="deleteClass('${class.id}')"><i class="glyphicon glyphicon-edit"></i> 删除</a>
					<a class="btn btn-default btn-sm" href="${ctx }/infoManage/classInfo?classId=${class.id }"><i class="glyphicon glyphicon-cog"></i> 课时管理</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- 分页 -->
<tags:pagination page="${data }" />
<tags:errors />

<script type="text/javascript">
	$(function() {
		// 样式
		$('#info-man').addClass("active");
		$('#STATIUM_CLASS').addClass("active");
	});

	// 删除课程
	function deleteClass(v) {
		swal({
			title: "",
			text: "您确定删除课程？",
			type: "warning",
			showCancelButton: "true",
			showConfirmButton: "true",
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			animation: "slide-from-top"
		}, function () {
			$('#loading').show();
			$.ajax({
				url : "${ctx }/infoManage/deleteClass",
				method : "POST",
				data : {"classId" : v},
				dataType: 'json',
				success: function(data){
					$('#loading').hide();
					if(data.result=='success'){
						swal({
							title: "提示",
							text: "课程删除成功",
							showConfirmButton: "true",
							confirmButtonText: "确定",
							animation: "slide-from-top"
						}, function () {
							location.href = "${ctx }/infoManage/statiumClass";
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