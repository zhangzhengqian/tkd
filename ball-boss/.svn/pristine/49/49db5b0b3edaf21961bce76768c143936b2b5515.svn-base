<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li><span class="glyphicon glyphicon-stats"></span> 全国统计(不包含直辖市) </li>
        	<li class="active">${provinceName}</li>
    	</ul>
	</div>
	<div class="panel-body">
	
		<div class="row">
			<c:forEach items="${city }" var="itm">
				<div class="col-md-3 text-center ">
					<%-- <a href="${ctx }/admin/statium/statistics/province/${provinceName}?cityName=${itm.name}">${itm.name } (<font color="red">${itm.total }</font>)</a> --%>
					<a href="javascript:void(0);" onClick="showCitName('${provinceName}','${itm.area}')">${itm.area } (<font color="red">${itm.total }</font>)</a>
				</div>
			</c:forEach>
	    </div>
		  
	</div>
	
<form id="CityNameForm" action="${ctx }/admin/statium/statistics/province" method = "post">
	<input class="hidden" name="name" id="name"/>
	<input class="hidden" name="cityName" id="cityName"/>
</form>

</div>

<c:forEach items="${city }" var="itm2">
<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li class="active">${itm2.area }-场馆统计</li>
    	</ul>
	</div>
	<div class="panel-body">
		<c:forEach  items="${statiumMap}" var="map" >
		    <c:if test="${map.key eq itm2.area }">
		     <c:forEach items="${statiumMap[map.key]}" varStatus="idx" var="itm">
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
		    
		    </c:if>
		   
		  </c:forEach>
	</div>

</div>
</c:forEach>
<p class="well text-right">
	<a class="btn btn-sm btn-default" href="${ctx }/admin/statium/statistics">返回</a>	
</p>


<script type="text/javascript">
$(function() {
	menu.active('#statium-statistics-man');
	$("#adminFooter").hide();
});

function showCitName(provinceName,name){
	var proName = $("#name").val(provinceName);
	var cityName = $("#cityName").val(name);
	$("#CityNameForm").submit();
}
</script>




