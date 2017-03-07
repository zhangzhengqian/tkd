<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>


<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li><span class="glyphicon glyphicon-stats"></span> 场馆统计 </li>
    	</ul>
	</div>
	<div class="panel-body">
		<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item" varStatus="idx">
			<c:if test="${ idx.index==0 or idx.index mod 4 == 0 }">
				<div class="row ">
			</c:if>	
			    	<div class="col-md-3 text-center ">
			    		<div class="well">
					    	${item.itemName } <span class="badge">${lf:totalStatium(item.itemCode) }</span>
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
        	<li><span class="glyphicon glyphicon-stats"></span> 直辖市统计 </li>
    	</ul>
	</div>
	<div class="panel-body">
		<div role="tabpanel">

  			<!-- Nav tabs -->
  			<ul class="nav nav-tabs" role="tablist">
  				<c:forEach items="beijing,shanghai,tianjin,chongqing" var="itm" varStatus="idx">
    				<li role="presentation" class="<c:if test="${idx.index==0 }">active</c:if>">
    					<a href="#${itm }" aria-controls="${itm }" role="tab" data-toggle="tab">
    						${municipality1[itm].name } <span class="badge">${municipality1[itm].total}</span>
    					</a>
    				</li>
    			</c:forEach>
  			</ul>

  			<!-- Tab panes -->
  			<div class="tab-content">
  				<c:forEach items="beijing,shanghai,tianjin,chongqing" var="itm0" varStatus="idx">
	    			<div role="tabpanel" class="tab-pane <c:if test="${idx.index==0 }">active</c:if>" id="${itm0 }">
	    				<br>
						<div class="row ">
							<c:forEach items="${municipality2[itm0]}" var="itm" >
								<div class="col-md-2 text-center ">
									<p class="well"><a href="${ctx }/admin/statium/statistics/municipality/${itm.name}?total=${itm.total}&pinyin=${itm0}" >${itm.name} (<font color="red">${itm.total }</font>)</a></p>
							    </div>
	    					</c:forEach>
						</div>
	    			</div>
  				</c:forEach>
  			</div>
		</div>
	</div>
</div>


<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li><span class="glyphicon glyphicon-stats"></span> 全国统计(不包含直辖市) </li>
    	</ul>
	</div>
	<div class="panel-body">
			<div class="row">
				<c:forEach items="${province }" var="itm">
					<div class="col-md-3 text-center ">
						<%-- <a href="${ctx }/admin/statium/statistics/province/${itm.name}">${itm.name } (<font color="red">${itm.total }</font>)</a> --%>
						<a href="javascript:void(0);" onclick="showProvince('${itm.name}')">${itm.name } (<font color="red">${itm.total }</font>)</a>
					</div>
				</c:forEach>
		    </div>
		  
	</div>
</div>

<form id="provinceForm" action="${ctx }/admin/statium/statistics/province" method = "post">
	<input class="hidden" name="name" id="name"/>
	<input class="hidden" name="cityName" id="cityName"/>
</form>


<p class="well"><span class="glyphicon glyphicon-stats"></span>&nbsp;全国共计签约球馆： <font color="red">${totalStatium }</font> </p>


<script type="text/javascript">
$(function() {
	menu.active('#statium-statistics-man');
	$('#adminFooter').hide();
});

function showProvince(name){
	$("#name").val(name);
	$("#provinceForm").submit();
}
</script>




