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
    	<span class="glyphicon glyphicon-stats"></span>&nbsp;
        <li class="active" ><a href="${ctx}/statisticRegisterUser/registerList" >注册用户统计</a></li>
        <li class="active" >注册来源日数据</li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel panel-default">
	<div class="panel-heading"> 
		<div class="row">
			<div class="col-sm-6 text-left">
					<span class="glyphicon glyphicon-stats" aria-hidden="true">
					</span> 统计图
			</div>
			<div class="col-sm-6 text-right" >
					<span class="label " style="background-color: #3CB371;" >注册数</span>
					<span class="label " style="background-color: #A52A2A;" >激活数</span>
					<span class="label " style="background-color: #4682B4;" >订单数</span>
					<span class="label " style="background-color: #27408B;" >日活数</span>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12" >
				<div>
					<canvas id="statisticDay" height="70" ></canvas>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="panel panel-default">
	<div class="panel-heading"> 
		<div class="row">
			<div class="col-sm-6 text-left">
					<span class="glyphicon glyphicon-stats" aria-hidden="true">
					</span> 统计图
			</div>
			<div class="col-sm-6 text-right" >
					<span class="label " style="background-color: #8B4726;" >订单总额</span>
					<span class="label " style="background-color: #707070;" >毛利润总额</span>
					<span class="label " style="background-color: #006400;" >补贴总额</span>
					<span class="label " style="background-color: #CDAD00;" >优惠券总额</span>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12" >
				<div>
					<canvas id="orderNumChartDay" height="70" ></canvas>
				</div>
			</div>
		</div>
	</div>
</div>

  <div class="panel-body"><!-- 右侧主体内容 -->
  	<div class="row">
    	<div class="col-md-12 text-right" >	
			<button onclick="exportDatas()" class="btn btn-default btn-primary">导出</button>    	
    	</div>
    </div>
		<hr>
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>注册来源</th>
					<th>注册时间</th>
					<th>注册数</th>
					<th>激活数</th>
					<th>转化率</th>
					<c:if test="${channel=='allChannel'}">
						<th>订单数</th>
						<th>订单总额</th>
						<th>毛利润总额</th>
						<th>补贴总额</th>
						<th>优惠券总额</th>
						<th>日活</th>
						<th>pv</th>
					</c:if>
				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="regUser" varStatus="stat">
					<tr>
						<td style="text-align: center;">${stat.count }</td>
						<td style="text-align: center;">${regUser.channel }</td>
						<td style="text-align: center;"><a href="${ctx}/statisticRegisterUser/queryByChannelDay/${regUser.channel}?search_GTE_ct=${regUser.ct }&search_LTE_ct=${regUser.ct }"  >${regUser.ct }</a></td>
						<td style="text-align: center;">${regUser.counter }</td>
						<td style="text-align: center;">${regUser.avcounter }</td>
						<td style="text-align: center;">
							<fmt:formatNumber type="percent" value="${regUser.convertrate }" minFractionDigits="2" maxFractionDigits="2" />
						</td>
						<c:if test="${channel=='allChannel'}">
							<c:forEach items="${lf:orderDay(regUser.ct)}" var="item" varStatus="stat">
							<td style="text-align: center;">${item['orderCount'] }</td>
							<td style="text-align: center;">${lf:formatMoney(item['fee']) }</td>
							<td style="text-align: center;">${lf:formatMoney(item['profit']) }</td>
							<td style="text-align: center;">${lf:formatMoney(item['subsidies']) }</td>
							<td style="text-align: center;">${lf:formatMoney(item['subamount']) }</td>
							</c:forEach>
							<td style="text-align: center;">${lf:uvCount(regUser.ct)}</td>
							<td style="text-align: center;">${lf:pvCount(regUser.ct)}</td>
						</c:if>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
  </div>
  <div class="modal fade" id="exportModal" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">数据导出</h4>
				</div>
				<div class="modal-body" class="">
					<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="exportForm" class="form-horizontal" action="${ctx }/statisticRegisterUser/exportByChannel/${channel}" method="get">
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for=""></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_GTE_ct" name="search_GTE_ct" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_ct\')||\'%y-%M-%d\'}'})" value="${param.search_GTE_ct }" placeholder="开始时间">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LTE_ct" name="search_LTE_ct" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_ct\')}'})" value="${param.search_LTE_ct }" placeholder="结束时间">
       	  			</div>
		         </div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="exportForm()"
					data-dismiss="modal" aria-hidden="true">确定</a>
			</div>
		</div>
		</div>
	</div>
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	menu.active('#statisticRegUser-regUserList');
	myChart.line("#statisticDay","${ctx}/statistic/statisticNumChartDay",{});
	myChart.line("#orderNumChartDay","${ctx}/statistic/orderNumChartDay",{});
	
	$("#leftBtn").click(function(){
		myChart.line("#statisticDay","${ctx}/statistic/statisticNumChartDay",{});
		myChart.line("#orderNumChartDay","${ctx}/statistic/orderNumChartDay",{});
	})
});

function exportForm(){
	$("#exportForm").submit();
}

function exportDatas(){
	$("#exportModal").modal();
}

</script>

</body>
</html>