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
		<li class="active">7日/30日注册用户统计</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->				
	<div class="row">
    	<div class="col-table col-md-12" >
	    	<form class="form-horizontal" id="orderVoForm" action="${ctx }/statisticRegisterUser/registerListEx" method="post">
				<div class="form-group form-group-sm">
<!--					<label class="control-label col-md-1 sr-only" for=""></label>
 					<div class="col-md-5">
						<input class="form-control input-sm" type="text" name="dateTime" id="dateTime" 
		          		onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'%y-%M-%d\')}'})"
		          		value="" data-date-format="yyyy-mm-dd" placeholder="选择日期" />
					</div> -->
					<div class="col-md-5">
						<select name="queryDays" id="queryDays" class="form-control" style="width: 30%;">
							<option value="7"  <c:if test="${queryDays =='7' }">selected</c:if>>最近7日</option>
						    <option value="30" <c:if test="${queryDays =='30' }">selected</c:if>>最近30日</option>
						</select>
			        </div>
				</div>
<!-- 		        <div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
						<button type="submit"  class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-search"></span> 搜 索
						</button>
					</div>
				</div> -->
			 </form>
    		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>注册来源</th>
					<th>注册总数</th>
					<th>激活总数</th>
					<th>转化率</th>
				</tr>
				</thead>
				
				<tbody>
					<tr>
						<td style="text-align: center;">汇总</td>
						<td style="text-align: center;"><a href="${ctx}/statisticRegisterUser/queryByChannel/allChannel" >全部</a></td>
						<td style="text-align: center;">${totalData.total }</td>
						<td style="text-align: center;">${totalData.avtotal }</td>
						<td style="text-align: center;"><fmt:formatNumber type="percent" value="${totalData.convertratetotal }" minFractionDigits="2" maxFractionDigits="2" /></td>
					</tr>
				<c:forEach items="${data.content }" var="regUser" varStatus="stat">
					<tr>
						<td style="text-align: center;">${stat.count }</td>
						<td style="text-align: center;"><a href="${ctx}/statisticRegisterUser/queryByChannel/${regUser.channel}"  >${regUser.channel }</a></td>
						<td style="text-align: center;">${regUser.total }</td>
						<td style="text-align: center;">${regUser.avtotal }</td>
						<td style="text-align: center;"><fmt:formatNumber type="percent" value="${regUser.convertratetotal }" minFractionDigits="2" maxFractionDigits="2" /></td>
					</tr>
				</c:forEach>
				</tbody>		
			</table>
		 </div><!-- end col-table -->
	</div><!-- end row -->
	<tags:pagination page="${data }" />
  </div>
  
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	//myChart.line("#regUser10day","${ctx}/statisticRegisterUser/regUserForChart",{days:10});
	//myChart.line("#regUser10day","${ctx}/statistic/ordersChartDay",{days:10});
	menu.active('#statisticRegUser-regUserExList');
	
	$("#queryDays").change(function(){
		var days = $("#queryDays").find("option:selected").val();
		window.location.href="${ctx }/statisticRegisterUser/registerListEx?queryDays="+days;
	})
});

</script>

</body>
</html>