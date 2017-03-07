<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>


<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li><span class="glyphicon glyphicon-stats"></span> 订单类型统计(${totalOrder}) </li>
    	</ul>
	</div>
	<div class="panel-body">
		<c:forEach items="${totalOrder_orderType}" var="itm" varStatus="idx">
			<c:if test="${ idx.index==0 or idx.index mod 4 == 0 }">
				<div class="row ">
			</c:if>	
			    	<div class="col-md-3 text-center ">
			    		<div class="well">
					    	<c:choose>
                    		<c:when test="${itm.ordersType == '0'}">场馆</c:when>
                    		<c:when test="${itm.ordersType == '1'}">教/陪练</c:when>
                    		<c:when test="${itm.ordersType == '2'}">活动</c:when>
                    		 <c:otherwise> 其他 </c:otherwise>
                    	</c:choose>
					    	<span class="badge">${itm.total}</span>
			    		</div>
			    	</div>
			<c:if test="${ idx.index==3 or idx.index mod 4 == 3 }">
		    	</div>
			</c:if>	
	    </c:forEach>
	    

</div>
</div>


<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li><span class="glyphicon glyphicon-stats"></span> 场地类型统计(${totalOrder}) </li>
    	</ul>
	</div>
	<div class="panel-body">
		<c:forEach items="${totalOrder_sportType}" var="itm" varStatus="idx">
			<c:if test="${ idx.index==0 or idx.index mod 4 == 0 }">
				<div class="row ">
			</c:if>	
			    	<div class="col-md-3 text-center ">
			    		<div class="well">
					    	<c:choose>
                    		<c:when test="${itm.ordersType == 'BASKETBALL'}">篮球</c:when>
                    		<c:when test="${itm.ordersType == 'FOOTBALL'}">足球</c:when>
                    		<c:when test="${itm.ordersType == 'BADMINTON'}">羽毛球</c:when>
                    		<c:when test="${itm.ordersType == 'BILLIARDS'}">台球</c:when>
                    		<c:when test="${itm.ordersType == 'BOWLING'}">保龄球</c:when>
                    		<c:when test="${itm.ordersType == 'GOLF'}">高尔夫</c:when>
                    		<c:when test="${itm.ordersType == 'TABLE_TENNIS'}">乒乓球</c:when>
                    		<c:when test="${itm.ordersType == 'TENNIS'}">网球</c:when>
                    	</c:choose> 
					    	<span class="badge">${itm.total}</span>
			    		</div>
			    	</div>
			<c:if test="${ idx.index==3 or idx.index mod 4 == 3 }">
		    	</div>
			</c:if>	
	    </c:forEach>
	    

</div>
</div>
</div>

<script type="text/javascript">
$(function() {
	menu.active('#order-statistics');
	  $('#footer').hide();
});


</script>
