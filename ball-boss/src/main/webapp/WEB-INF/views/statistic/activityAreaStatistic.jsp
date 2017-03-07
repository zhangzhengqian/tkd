<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户激活地区统计</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
      <ul class="breadcrumb">
		<li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
		<li class="active">用户激活地区统计</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->				
	<div class="row">
    	<div class="col-table col-md-12" >
	    	<form class="form-horizontal" id="orderVoForm" action="${ctx }/statisticRegisterUser/activityAreaStatistic">
				<div class="form-group form-group-sm">
					<div class="col-md-5">
						<input type="text" name="search_EQ_date" style="width: 30%;"
							class="form-control input-sm" value="${param.search_EQ_date }"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-{%d-1}'})"
							data-date-format="yyyy-mm-dd" placeholder="选择日期" />
						<button type="submit"  class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
			        </div>
				</div>
			 </form>
    		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>地区</th>
					<th>激活总数</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${data.content }" var="record" varStatus="stat">
						<tr>
							<td style="text-align: center;">${stat.count }</td>
							<td style="text-align: center;">${record.area }</td>
							<td style="text-align: center;">${record.total }</td>
						</tr>
					</c:forEach>
				</tbody>		
			</table>
		 </div><!-- end col-table -->
	</div><!-- end row -->
	<%-- <tags:pagination page="${data }" /> --%>
  </div>
  
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	menu.active('#statisticRegUser-activityUserArea');
	
/* 	$("#queryDays").change(function(){
		var days = $("#queryDays").find("option:selected").val();
		window.location.href="${ctx }/statisticRegisterUser/registerListEx?queryDays="+days;
	}) */
});

</script>

</body>
</html>