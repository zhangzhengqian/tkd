<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<div class="timeline mtb10">
	<div class="cells">场地</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>02:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>04:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>06:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>08:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>10:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>12:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>14:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>16:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>18:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>20:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>22:00</strong>
	</div>
	<div class="cells"></div>
	<div class="cells">
		<strong>24:00</strong>
	</div>
</div>
<c:forEach items="${data.content}" var="space" varStatus="stat">
	<div class="ground">
		<div title="${space.spaceCode }" class="cells"
			style="table-layout: fixed; word-break: break-all; overflow: hidden; line-height: 20px;">${space.spaceCode }</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<div class="cells">
			<span></span> <span></span>
		</div>
		<c:forEach items="${lf:orderItems(startDate, space.id) }" var="orderItem">
			<a href="#" 
			<c:if test="${orderItem.status == 'ORDER_PAIED'}">class="segment_paied"</c:if>
			<c:if test="${orderItem.status == 'ORDER_NEW'}">class="segment_new"</c:if>
			<c:if test="${orderItem.status == 'ORDER_PLAYING'}">class="segment_playing"</c:if>
			<c:if test="${orderItem.status == 'ORDER_VERIFY'}">class="segment_verify"</c:if>
			style="left: ${lf:minuteOfDay(orderItem.startTime)/15 }%; width: ${((orderItem.endTime - orderItem.startTime))/60/15}%"
			id="segment3" data-toggle="tooltip" data-placement="right"
			<jsp:useBean id="startTime" class="java.util.Date"/>
			<jsp:setProperty name="startTime" property="time" value="${orderItem.startTime * 1000}"/>
			<jsp:useBean id="endTime" class="java.util.Date"/>
			<jsp:setProperty name="endTime" property="time" value="${orderItem.endTime * 1000}"/>
			title="<fmt:formatDate value="${startTime}" pattern="HH:mm"/>~<fmt:formatDate value="${endTime}" pattern="HH:mm"/>"></a>
		</c:forEach>
	</div>
</c:forEach>