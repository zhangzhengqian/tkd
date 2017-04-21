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
					<th>课程名称</th>
					<th>面值(应交金额)</th>
					<th>次数</th>
					<th>赠送次数</th>
					<th>有效期</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${coachCard }" var="card" varStatus="stat">
					<tr>
						<td>${stat.count }</td>
						<td>${card.cardName }</td>
						<td>私教卡</td>
						<td>${card.classTitle }</td>
						<td><fmt:formatNumber type="number" value="${card.discountPrice/100 }" maxFractionDigits="0"/></td>
						<td>${card.frequency }</td>
						<td>${card.giftFrequency }</td>
						<td>${card.period }个月</td>
						<td>
							<a href="javascript:selCard('${card.id }','${card.cardName }','${card.classTitle }', '${card.discountPrice }', '${card.frequency }','${card.giftFrequency }','${card.period }','${card.classId }') ">选择</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>

<script>
	function selCard(cardId,cardName,classTitle, discountPrice, frequency, giftFrequency, period,classId) {
		captainAddCallBack(cardId,cardName,classTitle, discountPrice, frequency, giftFrequency, period,classId);
		$("#myDlg_lg").modal("hide");
	}
</script>