<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户注册统计</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
        <li class="active" >PV统计</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
				
		<div class="row">
    	<div class="col-md-6"><!-- 字典类别 -->
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>渠道</th>
					<th>昨日pv数</th>
					<th>pv总数</th>
					<th>增长率</th>

				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="regUser" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
						<td><a href="${ctx}/statisticUalog/queryByChannel/${regUser.channel}"  >${regUser.channel }</a></td>
						<td>${regUser.counter }</td>
						<td>${regUser.total }</td>
						<td>${regUser.growthrate }</td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		  <div class="col-md-6">
		 <div class="panel-heading"> 
			<div class="row">
				<div class="col-sm-6 text-left">
						<span class="glyphicon glyphicon-stats" aria-hidden="true">
						</span> PV饼图  
	            		
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="row">
			<div class="col-sm-4 text-left" >
				<table  >
				<tbody>
				<c:forEach items="${channelColorPage}" var="regUser" varStatus="stat">
						<tr>
						<td ><span class="label " style="background-color: ${regUser.value };" >${regUser.key }</span></td>
					</tr>
					</c:forEach>
				
				</tbody>		
			</table>
			</div>
				<div class="col-sm-6" >
					<div>
						<canvas id="ualogPie" height="320" ></canvas>
					</div>
					
				</div>
				
				
			</div>
		</div>
		
		</div>
		<div class="col-md-6"><!-- 字典类别 -->
			
		 </div><!-- end col-table -
	</div><!-- end row -->
  </div>
  

<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	myChart.pie("#ualogPie","${ctx}/statisticUalog/uglogForChart",{});
	//myChart.line("#regUser10day","${ctx}/statistic/ordersChartDay",{days:10});
	menu.active('#statisticUalog-ualogList');
	
	
});

</script>

</body>
</html>