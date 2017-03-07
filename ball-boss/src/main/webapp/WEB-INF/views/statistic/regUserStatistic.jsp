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
		<li class="active">注册用户统计</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->				
	<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>注册来源</th>
					<th>昨日注册数</th>
					<th>注册总数</th>
					<!-- <th>增长率</th> -->
					<th>昨日激活数</th>
					<th>激活总数</th>
					<th>昨日转化率</th>
					<th>总转化率</th>
					<th>昨日PV</th>
					<th>PV总数</th>
				</tr>
				</thead>
				
				<tbody>
					<tr>
						<td class="text-center">汇总</td>
						<td><a href="${ctx}/statisticRegisterUser/queryByChannel/allChannel"  >全部</a></td>
						<td>${totalData.counter }</td>
						<td>${totalData.total }</td>
						<%-- <td><fmt:formatNumber type="percent" value="${totalData.growthrate }" minFractionDigits="2" maxFractionDigits="2" /></td> --%>
						<td>${totalData.avcounter }</td>
						<td>${totalData.avtotal }</td>
						<td><fmt:formatNumber type="percent" value="${totalData.convertrate }" minFractionDigits="2" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber type="percent" value="${totalData.convertratetotal }" minFractionDigits="2" maxFractionDigits="2" /></td>
						<td>${totalData.pvcounter }</td>
						<td>${totalData.pvtotal }</td>
					</tr>
				<c:forEach items="${data.content}" var="regUser" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
						<td><a href="${ctx}/statisticRegisterUser/queryByChannel/${regUser.channel}"  >${regUser.channel }</a></td>
						<td>${regUser.counter }</td>
						<td>${regUser.total }</td>
						<%-- <td><fmt:formatNumber type="percent" value="${regUser.growthrate }" minFractionDigits="2" maxFractionDigits="2" /></td> --%>
						<td>${regUser.avcounter }</td>
						<td>${regUser.avtotal }</td>
						<td><fmt:formatNumber type="percent" value="${regUser.convertrate }" minFractionDigits="2" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber type="percent" value="${regUser.convertratetotal }" minFractionDigits="2" maxFractionDigits="2" /></td>
						<td><a href="${ctx}/statisticUalog/queryByChannel/${regUser.channel}"  >${regUser.pvcounter }</a></td>
						<td>${regUser.pvtotal }</td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
	</div><!-- end row -->
	<tags:pagination page="${data }" />
  </div>
  
  <div class="panel-body" style="display: none;">
	  <div class="panel panel-default">
		<div class="panel-heading"> 
			<div class="row">
				<div class="col-sm-6 text-left">
						<span class="glyphicon glyphicon-stats" aria-hidden="true">
						</span> 最近 10 日注册数
				</div>
				<div class="col-sm-6 text-right" >
					<c:forEach items="${channelColorPage}" var="regUser" varStatus="stat">
						<span class="label " style="background-color: ${regUser.value };" >${regUser.key }</span>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-12" >
					<div>
						<canvas id="regUser10day" height="70" ></canvas>
					</div>
				</div>
			</div>
		</div>
	  </div>
	</div>
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	myChart.line("#regUser10day","${ctx}/statisticRegisterUser/regUserForChart",{days:10});
	//myChart.line("#regUser10day","${ctx}/statistic/ordersChartDay",{days:10});
	menu.active('#statisticRegUser-regUserList');
	$("#leftBtn").click(function(){
		myChart.line("#regUser10day","${ctx}/statisticRegisterUser/regUserForChart",{days:10});
	})
});

</script>

</body>
</html>