<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
			<div class="row">
				<div class="col-sm-6 text-left">
						 场馆抽奖奖品设置
						&nbsp;&nbsp;
						<c:forEach items="2016,2017,2018,2019,2020" var="itm">
							<a href="#"  class="year label  <c:if test="${not (year eq itm) }">label-default</c:if> <c:if test="${year eq itm }">label-primary</c:if>">${itm }</a>
						</c:forEach>
				</div>
			</div>
	</div>
	
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-6 text-left">
					<c:forEach items="1,2,3,4,5,6,7,8,9,10,11,12" var="itm">
						<a href="#"  class="month label  <c:if test="${not (month eq itm) }">label-default</c:if> <c:if test="${month eq itm }">label-primary</c:if>">${itm }</a>
					</c:forEach>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-sm-12 text-right">
					<a href="#" id="config" class="btn btn-primary">设置</a>
					<a href="#" onclick="delConfig()" class="btn btn-primary">删除</a>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-sm-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>奖品编码</th>
								<th>奖品名称</th>
								<th>数量</th>
								<th>价格</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${configs}" var="config" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${config.prizeType }</td>
									<td class="text-center">${config.prizeName }</td>
									<td class="text-center">${config.amount}</td>
									<td class="text-center">${config.money}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</div>
		</div>
	</div>
</div>




<script>
	$(function() {
		menu.active('#prize-set-man');
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
			window.location.href="${ctx}/prize/getConfig/"+year+"/"+month;
		})
		$("#config").on("click",function(){
			year = $(".label-primary")[0].innerHTML;
			month = $(".label-primary")[1].innerHTML;
			window.location.href="${ctx}/prize/config/"+year+"/"+month;
		})
	});
	
	function delConfig(){
		year = $(".label-primary")[0].innerHTML;
		month = $(".label-primary")[1].innerHTML;
		$.ajax({
			type : "post",
			url : "${ctx}/prize/delConfig",
			dataType : "text",
			data : {year:year,month:month},
			success : function(msg) {
				if(msg=='success'){
					window.location.href="${ctx}/prize/getConfig/"+year+"/"+month;
				}else{
					myAlert("删除奖品设置失败!");
				}
			}
		});
	}
</script>

