<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="statiumNav.jsp"%>

<form  id="studentFrom" class="form-horizontal" action="${ctx }/statiumManage/student" method="post" name="id">
	<div class="orderSearch myVipOrderSearch">
		<ul>
			<li class="timeLi subSearchLi1">
				<span>手机号</span>
				<input style="width: 200px" type="text" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }">
			</li>
			<li class="subSearch subSearchLi1">
				<a class="reset" type="reset" href="javascript:;">重置</a>
			</li>
			<li class="subSearch subSearchLi1">
				<a class="submit" href="javascript:studentSubmit()" >查询</a>
			</li>

		</ul>
	</div>
</form>

<div class="orderResult">
	<table>
		<tr>
			<th>No.</th>
			<th>学员号</th>
			<th>姓名</th>
			<th>手机</th>
			<th>账户余额</th>
			<th>期限卡</th>
			<th>操作</th>
		</tr>

		<c:forEach items="${data.content }" var="user" varStatus="stat">
			<tr>
				<td>${stat.count }</td>
				<td>${user.tkdNo }</td>
				<c:choose>
					<c:when test="${user.username == null}">
						<td>${user.nickName }</td>
					</c:when>
					<c:otherwise>
						<td>${user.username }</td>
					</c:otherwise>
				</c:choose>
				<td>${user.phone }</td>
				<td>
					<fmt:formatNumber type="number" value="${user.balance/100 }" maxFractionDigits="0"/>
				</td>
				<c:choose>
					<c:when test="${user.dateTime == null }">
						<td>无</td>
					</c:when>
					<c:otherwise>
						<c:set value="${fn:split(user.dateTime, ';') }" var="times" />
						<td>
							<c:forEach items="${times }" var="date">
								${date }
								<br>
							</c:forEach>
						</td>
					</c:otherwise>
				</c:choose>
				<td>
					<a class="btn btn-default btn-sm" href="${ctx }/statiumManage/studentDetail/${user.id }"><i class="glyphicon glyphicon-list"></i> 详情</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- 分页 -->
<tags:pagination page="${data}" />
<tags:errors />

<script type="text/javascript" src="${ctx }/static/lib/reset.js"></script>
<script type="text/javascript">
	$(function() {
		// 样式
		$('#statium-man').addClass("active");
		$('#STUDENT').addClass("active");
	});

	// 查询
	function studentSubmit() {
		$('#studentFrom').submit();
	}

</script>

</body>
</html>