<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<style>
<!--
.false{
	height: 34px;
}
-->
</style>
<div class="panel panel-default">
	<div class="panel-heading">
    	<ul class="breadcrumb">
        	<li><span class="glyphicon glyphicon-list-alt active"></span> 检索条件</li>
        	<li><button type="button" class="abtn btn btn-sm btn-primary adBtn" value="search"><span class="glyphicon glyphicon-search"></span>搜索</button></li>
    	</ul>
    	
	</div>
	<div class="panel-body">
		<form class="form-horizontal" id="myForm" method="post" action="${ctx }/admin/statium/statistics/signed/list" >
			<input type="hidden" name="action" id="action" value="${condition.action }" />
			<div class="form-group">
				<label class="col-sm-2 control-label"><span class="text-red"></span>开始日期:</label>
                <div class="col-sm-4">
                     <div class="input-group">
                         <input type="text" name="strStartDate" id="strStartDate" class="form-control" onclick='WdatePicker();' value="${condition.strStartDate }" readonly>
                         <label for="strStartDate" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                </div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><span class="text-red"></span>结束日期:</label>
                <div class="col-sm-4">
                     <div class="input-group">
                         <input type="text" name="strEndDate" id="strStartDate" class="form-control" onclick='WdatePicker();' value="${condition.strEndDate }" readonly />
                         <label for="strStartDate" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                </div>
			</div>
		  	<div class="form-group">
		    	<label for="inputEmail3" class="col-sm-2 control-label">签约人</label>
		    	<div class="col-sm-4">
					<select class="form-control" name="cb" >
					  <option value="">--全部--</option>
					  <c:forEach items="${cbs }" var="itm" >
						  <option value="${itm.userId }" <c:if test="${condition.cb == itm.userId }">selected="selected"</c:if> >${itm.nickname }</option>
					  </c:forEach>
					</select>
		  		</div>
		  	</div>
		  	<div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">地区</label>
		    	<div class="col-sm-8">
		    		<tags:zone name="areaCode" value="${condition.areaCode }" clazz="false" />
		  		</div>
		  	</div>
		  	<div class="form-group inline-block">
		    	<label for="inputEmail3" class="col-sm-2 control-label">运动类型</label>
		    	<div class="col-sm-8">
		    		<div class="btn-group" data-toggle="buttons">
						<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
							<c:set var="active" value="" />	
							<c:set var="checked" value="" />	
							<c:forEach items="${condition.sportTypes }" var="obj" >
								<c:if test="${obj eq item.itemCode}">
									<c:set var="active" value="active" />	
									<c:set var="checked" value="checked" />	
								</c:if>	
							</c:forEach>
							<label class="btn btn-default ${active }">
				  				<input name="sportTypes" type="checkbox" autocomplete="off" ${checked } value="${item.itemCode }"> ${item.itemName }
							</label>
		       			</c:forEach>
		       		</div>
		  		</div>
		  	</div>
		</form>		
        <tags:errors />
	</div>
	<div class="panel-footer">
		<div class="row text-right">
			<div class="col-sm-12">
				
			</div>
		</div>	
	</div>
</div>

<script type="text/javascript">

$(function() {
	menu.active('#statium-statistics-signed-man');

	$(".abtn").click(function(){
		var action = $(this).val();
		$("#action").val(action);			  
		$("#myForm").submit();
	});
	$("#adminFooter").hide();
});

</script>