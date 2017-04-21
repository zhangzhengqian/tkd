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
					<th>No.</th>
					<th>课程名称</th>
					<th>课程价格</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${classList }" var="class" varStatus="stat">
					<tr>
						<td>${stat.count }</td>
						<td>${class.classTitle }</td>
						<td><fmt:formatNumber type="number" value="${class.discountPrice/100 }" maxFractionDigits="0"/></td>
						<td>
							<a href="javascript:selCard('${class.id}','${class.classTitle }', '${class.discountPrice }') ">选择</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>

<script>
	function selCard(classId, classTitle, discountPrice) {
		captainAddCallBack(classId, classTitle, discountPrice);
		$("#myDlg_lg").modal("hide");
	}
</script>