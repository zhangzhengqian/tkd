<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<% 
Date now = new Date();
request.setAttribute("now", now);
%>
<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row" style="margin-bottom: 8px;">
						<div class="col-sm-6 text-left">
							<ul class="breadcrumb">
	       						<li><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;系统统计</li>
	       						<li class="active">订单统计</li>
							</ul>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 text-left">
 							<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span> 
							 &nbsp;
							<b><fmt:formatDate value="${now }" pattern="yyyy-MM-dd" /></b>
							 &nbsp;
							截止目前：<b style="color: red;"  id="orderCounterToday" ></b> 
							&nbsp; 
							单
						</div>
						<div class="col-sm-6 text-right" >
								<span class="label " style="background-color: #3CB371;" >网球</span>
								<span class="label " style="background-color: #A52A2A;" >羽毛球</span>
								<span class="label " style="background-color: #27408B;" >乒乓球</span>
								<span class="label " style="background-color: #4682B4;" >台球</span>
								<span class="label " style="background-color: #CDAD00;" >篮球</span>
								<span class="label " style="background-color: #006400;" >足球</span>
								<span class="label " style="background-color: #8B4726;" >保龄球</span>
								<span class="label " style="background-color: #707070;" >高尔夫</span>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div>
						<canvas id="ordersHour" height="70" ></canvas>
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
					</span> 最近 10 日订单
			</div>
			<div class="col-sm-6 text-right" >
					<span class="label " style="background-color: #3CB371;" >网球</span>
					<span class="label " style="background-color: #A52A2A;" >羽毛球</span>
					<span class="label " style="background-color: #27408B;" >乒乓球</span>
					<span class="label " style="background-color: #4682B4;" >台球</span>
					<span class="label " style="background-color: #CDAD00;" >篮球</span>
					<span class="label " style="background-color: #006400;" >足球</span>
					<span class="label " style="background-color: #8B4726;" >保龄球</span>
					<span class="label " style="background-color: #707070;" >高尔夫</span>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12" >
				<div>
					<canvas id="orders10day" height="70" ></canvas>
				</div>
			</div>
		</div>
	</div>
</div> 
	<div class="row">
			<div class="col-sm-12" >
				<div class=" col-md-2 ">
			             	<input type="text" id="start" placeholder="选择月份" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM"});'readonly>
			          	</div>  
			        	<div class=" col-md-2">
				              <button type="botton" id="btn_1" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
			        	</div> 
			</div>
		</div>  
		<br>
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
<div class="row">
			<div class="col-sm-12" >
				<div class=" col-md-2 ">
			             	<input type="text" id="orderStart" placeholder="选择月份"   class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM"});'readonly>
			          	</div>  
			        		<div class=" col-md-2">
				              <button type="botton" id="btn_2" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
			        	</div> 
			</div>
		</div>  
		<br>
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

<div class="row">
			<div class="col-sm-12" >
				<div class=" col-md-2 ">
			             	<input type="text" id="orderStart2" placeholder="选择月份"   class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM"});'readonly>
			          	</div>  
			        		<div class=" col-md-2">
				              <button type="botton" id="btn_3" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
			        	</div> 
			</div>
		</div>  
		<br>
<div class="panel panel-default">
	<div class="panel-heading"> 
		<div class="row">
			<div class="col-sm-6 text-left">
					<span class="glyphicon glyphicon-stats" aria-hidden="true">
					</span> 统计图
			</div>
			<div class="col-sm-6 text-right" >
					<span class="label " style="background-color: #707070;" >毛利润总额</span>
					<span class="label " style="background-color: #006400;" >补贴总额</span>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-12" >
				<div>
					<canvas id="orderNumChartDay2" height="70" ></canvas>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(function() {
		myChart.bar("#orders10day","${ctx}/statistic/ordersChartDay",{days:10});
		myChart.line("#ordersHour","${ctx}/statistic/ordersChartHour",{});
		myChart.line("#statisticDay","${ctx}/statistic/statisticNumChartDay",{start:null});
		myChart.line("#orderNumChartDay","${ctx}/statistic/orderNumChartDay",{start:null});
		myChart.line("#orderNumChartDay2","${ctx}/statistic/orderNumChartDay2",{start:null});
		menu.active('#orderStatisticCharts');
		
		$.ajax({
			type : "post",
			url : "${ctx}/statistic/orderCounterToday",
			dataType : "text",
			data : {},
			success : function(data) {
				$('#orderCounterToday').html(data);
			}
		});
		
		$("#leftBtn").click(function(){
			var start = $("#start").val();
			var orderStart = $("#orderStart").val();
			var orderStart2 = $("#orderStart2").val();

			myChart.bar("#orders10day","${ctx}/statistic/ordersChartDay",{days:10});
			myChart.line("#ordersHour","${ctx}/statistic/ordersChartHour",{});
			myChart.line("#statisticDay","${ctx}/statistic/statisticNumChartDay",{start:strat});
			myChart.line("#orderNumChartDay","${ctx}/statistic/orderNumChartDay",{start:orderStart});
			myChart.line("#orderNumChartDay2","${ctx}/statistic/orderNumChartDay2",{start:orderStart2});

		})
		
		$("#btn_1").click(function(){
			var start = $("#start").val();
			myChart.line("#statisticDay","${ctx}/statistic/statisticNumChartDay",{start:start});
		});
		
		$("#btn_2").click(function(){
			var orderStart = $("#orderStart").val();
			myChart.line("#orderNumChartDay","${ctx}/statistic/orderNumChartDay",{start:orderStart});
		});
		
		$("#btn_3").click(function(){
			var orderStart = $("#orderStart2").val();
			myChart.line("#orderNumChartDay2","${ctx}/statistic/orderNumChartDay2",{start:orderStart});
		});
	});
</script>

