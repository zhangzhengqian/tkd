<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<div class="modal-header">
	<button type="button" class="close" style="margin: -7px 0px 0px 0px"
		data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>

<div class="modal-body">
	<div class="orderResult">
		<table>
			<tr>
				<th>序号</th>
				<th>教练名</th>
				<th>教练类型</th>
				<th>性别</th>
				<th>手机号</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${users }" var="item" varStatus="stat">
				<tr>
					<td>${stat.count }</td>
					<td>${item.name }</td>
					<td>
						<c:if test="${item.type == 0}">大课教练</c:if>
						<c:if test="${item.type == 1}">私人教练</c:if>
					</td>
					<td>${item.sex }</td>
					<td>${item.phone }</td>
					<td>
						<a href="javascript:selUser('${item.id}','${item.name }')">选择</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<script>
	function selUser(userId, name) {
		captainAddCallBack(userId, name);
		$("#myDlg_lg").modal("hide");
	}
</script>