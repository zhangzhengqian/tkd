<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 抽奖列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
			<div class="col-sm-6 text-left">
				<c:forEach items="2016,2017,2018,2019,2020" var="itm">
					<a href="#" class="year label  <c:if test="${not (year eq itm) }">label-default</c:if> <c:if test="${year eq itm }">label-primary</c:if>">${itm }</a>
				</c:forEach>
			</div>
			<br><br>
			</div>
			<div class="row">
			<div class="col-sm-6 text-left">
					<c:forEach items="1,2,3,4,5,6,7,8,9,10,11,12" var="itm">
						<a href="#"  class="month label  <c:if test="${not (month eq itm) }">label-default</c:if> <c:if test="${month eq itm }">label-primary</c:if>">${itm }</a>
					</c:forEach>
			</div>
			</div>
			<br>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>奖品名称</th>
								<th>总量</th>
								<th>剩余</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${prizeLaves}" var="lave" varStatus="stat">
								<tr>
									<td align="center">${lave.prizeName }</td>
									<td align="center">${lave.amount }</td>
									<td align="center">${lave.lave }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>场馆名称</th>
								<!-- <th>地址</th> -->
								<c:forEach items="${prizeName}" var="name" varStatus="stat">
									<th>${name }</th>
								</c:forEach>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="prize" varStatus="stat">
								<tr>
									<td align="center">${prize.statiumName }</td>
									<%-- <td>${prize.statiumAddress }</td> --%>
									<c:forEach items="${prize.prizeNum }" var="number" varStatus="stat">
										<td align="center">${number }</td>
									</c:forEach>
									<td align="center"><a class="btn btn-default btn-sw" id="show" statiumId="${prize.statiumId }"> <i class="glyphicon glyphicon glyphicon-cog"></i>查看</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<tags:pagination page="${data}" />
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script>
	$(function() {
		menu.active('#prize-list-man');
		var year = $(".label-primary")[0].innerHTML;
		var month = $(".label-primary")[1].innerHTML;
		$(".year,.month").on("click",function(){
			if($(this).hasClass("label-primary")){
				
			}else{
				$(this).addClass("label-primary");
				$(this).siblings().removeClass("label-primary").addClass("label-default");
			}
		})
		$(".month").on("click",function(){
			year = $(".label-primary")[0].innerHTML;
			month = $(".label-primary")[1].innerHTML;
			window.location.href="${ctx}/prize/getUserPrizeList/"+year+"/"+month;
		})
		$(".btn-sw").on("click",function(){
			year = $(".label-primary")[0].innerHTML;
			month = $(".label-primary")[1].innerHTML;
			var statiumId=$(this).attr("statiumId");
			window.location.href="${ctx}/prize/getStatiumPrizeList/"+statiumId+"/"+year+"/"+month;
		})
	});
	</script>