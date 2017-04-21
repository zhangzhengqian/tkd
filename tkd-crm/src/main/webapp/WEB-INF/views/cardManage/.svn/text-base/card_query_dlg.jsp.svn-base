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
					<th>卡片名称</th>
					<th>卡种</th>
					<th>面值(应交金额)</th>
					<th>赠送金额</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${cards }" var="card" varStatus="stat">
					<tr>
						<td>${stat.count }</td>
						<td>${card.cardName }</td>
						<c:choose>
							<c:when test="${card.cardType == 1}">
								<td class="warning">储值卡</td>
							</c:when>
							<c:when test="${card.cardType == 2}">
								<td class="info">期限卡</td>
							</c:when>
						</c:choose>
						<td><fmt:formatNumber type="number" value="${card.cardAmount/100 }" maxFractionDigits="0"/></td>
						<td><fmt:formatNumber type="number" value="${card.cardGift/100 }" maxFractionDigits="0"/></td>
						<td>
							<a href="javascript:selCard('${card.id}','${card.cardName }', '${card.cardType }', '${card.cardAmount }', '${card.cardGift }') ">选择</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>

<script>
	function selCard(cardId, cardName, cardType, cardAmount, cardGift) {
		captainAddCallBack(cardId, cardName, cardType, cardAmount, cardGift);
		$("#myDlg_lg").modal("hide");
	}
</script>