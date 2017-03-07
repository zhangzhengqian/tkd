<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
    		<li><span class="glyphicon glyphicon-stats"></span> 直辖市统计 </li>
        	<li class="">${cityName}</li>
        	<li class="active">${areaName} <span class="badge">${total }</span></li>
    	</ul>
	</div>
	<div class="panel-body">
	
		<c:forEach items="${statium }" var="itm" varStatus="idx">
			<c:if test="${ idx.index==0 or idx.index mod 4 == 0 }">
				<div class="row ">
			</c:if>	
			    	<div class="col-md-3 text-center ">
			    		<div class="well">
					    	${itm.name} <span class="badge">${itm.total }</span>
			    		</div>
			    	</div>
			<c:if test="${ idx.index==3 or idx.index mod 4 == 3 }">
		    	</div>
			</c:if>	
	    </c:forEach>
		  
	</div>

</div>


<p class="well text-right">
	<a class="btn btn-sm btn-default" href="${ctx }/admin/statium/statistics">返回</a>	
</p>


<script type="text/javascript">
$(function() {
	menu.active('#statium-statistics-man');
	$('#adminFooter').hide();
});
</script>




