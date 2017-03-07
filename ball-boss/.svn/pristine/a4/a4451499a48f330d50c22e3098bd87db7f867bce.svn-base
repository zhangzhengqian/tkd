<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场馆结账</title>
	<style type="text/css">
		<!--
		.tree-container {
		 	border: #efefef 1px solid;
		 	overflow: auto;
		}
	-->
	</style>
</head>

<body>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
  	<div class="row">
  		<div class="col-md-5">
			<div class="btn-group-sm">
				  <ul class="breadcrumb" style="display:inline;">
			        <li><span class="glyphicon glyphicon-home"></span> 订单管理</li>
			        <li class="active">场馆结账</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	<div class="row">
  		<div class="col-md-6 tree-container" style="min-height:802.556px;">
  			<br>
	  		<div class="row"><!-- 查询条件 -->
				<div class="col-md-12">
			      <form id="billForm" class="form-horizontal" action="${ctx }/orders/billingint" method="post">
		         	<div class="form-group form-group-sm form-inline">
		         		<div class="col-md-3">
				    		地区：
				    	</div>
			         	<div class="col-md-9">
			         		<div class="form-inline">
					        	<tags:zone name="areaCode" value="${param.areaCode}" id="areaCode" />
			         		</div>
				       	</div>
				    </div>
				    <div class="form-group form-group-sm form-inline">
				    	<div class="col-md-3">
				    		场馆名称：
				    	</div>
		  				<div class="col-md-9">
	            			<input type="text" class="form-control input-sm" id="statiumName" name="statiumName" value="${param.statiumName }" placeholder="场馆名称">
	       	  			</div>
		         	</div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	  				</div>
				</div>
		      </form>
			    </div>
	  		</div>
	  		<hr>
	  		<div class="row">
	  			<div class="col-md-12">
		  			<table class="table table-bordered table-condensed table-hover">
		  				<thead>
		  					<tr>
		  						<th>序号</th>
		  						<th>球馆名称</th>
		  						<th>所在地区</th>
		  						<th>最后结账日期</th>
		  					</tr>
		  				</thead>
		  				<tbody>
		  					<c:forEach items="${data.content}" var="statium" varStatus="stat">
		  					<tr style="cursor: pointer;" class="selStatium" statium="${statium.id}">
		  						<td>${stat.count}</td>
		  						<td>${statium.name}</td>
		  						<td><tags:zonemap code="${statium.areaCode}"/></td>
		  						<td>${statium.billDate}</td>
		  					</tr>
		  					</c:forEach>
		  				</tbody>
		  			</table>
	  			<tags:pagination  page="${data}" />
	  			</div>
	  		</div>
  		</div>
  		<div class="col-md-6 tree-container"  style="min-height:802.556px;">
  			<div class="row">
	  			<div id="historyTable" class="col-md-12">
	  			
	  			
	  			</div>
  			</div>
  	
  		</div>
  	</div>
		<form id="balanceForm" method="post" action="${ctx}/orders/bill/do">
			<input type="hidden" id="statiumId_" name="statiumId">
			<input type="hidden" id="resultView" name="resultView">
			<input type="hidden" id="startDate_" name="startDate">
			<input type="hidden" id="endDate_" name="endDate">
		</form>
   </div><!-- /右侧主体内容 -->
</div>
<textarea id="template" style="display:none">
	<!-- 
	<button type="button" onclick="balanceOrder()" class="btn btn-primary btn-sm pull-right"><span class="glyphicon glyphicon-search"></span>结账</button>
     <table class="table table-bordered table-striped table-hover" >
         <thead>
		<tr>
			<th>结算日期</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>未结金额</th>
			<th>补贴金额</th>
			<th>总金额</th>
		</tr>
	</thead>
	<tbody>
		{#if $T.table.length==0}
			<tr><td colspan="6" style="text-align: center;">没有历史结账</td></tr>
		{#/if} 
		{#foreach $T.table as record}
		<tr onclick="selBill(this)" region="{$T.record.id}" style="cursor: pointer;">
			<td>{$T.record.ct}</td>
			<td>{formatDate($T.record.beginDate)}</td>
			<td>{formatDate($T.record.endDate)}</td>
			<td>{$T.record.fee}</td>
			<td>{$T.record.subsidy}</td>
			<td>{$T.record.fee+$T.record.subsidy}</td>
		</tr>
		{#/for}  
	</tbody>
     </table> -->
</textarea>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
var statiumId='';
	$(function(){
		menu.active('#orders-bill');
		$('#adminFooter').hide();
		
		//====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		var complateStatiumName = new Bloodhound({
			datumTokenizer : Bloodhound.tokenizers.obj.whitespace('value'),
			queryTokenizer : Bloodhound.tokenizers.whitespace,
			remote : {
				url : '${ctx}/common/search_statium_by_name?q=%QUERY',
				wildcard : '%QUERY'
			}
		});
		$('#statiumName').typeahead(
			{
				    hint: true,
				    highlight: true,
				    minLength: 2
		 	}, {	
				    limit:20,
					name : 'best-pictures',
					display : 'name',
					source : complateStatiumName
			}
		);
		$(".selStatium").click(function(){
			statiumId = $(this).attr("statium");
			$(this).parent().children().removeClass("success");
			$(this).addClass("success");
			$.post(ctx + '/orders/bill/history',{statiumId:statiumId},function(data){
				if(data.result){
					var tableDatas = {table:data["data"]};
					$("#historyTable").setTemplateElement("template");
	                // 给模板加载数据
	                $("#historyTable").processTemplate(tableDatas);
			     }else{
			    	 myAlert(data.reason,"error");
			    	 app.enabled("#submit_btn");
			    	 return false;
				 }
			},'json');
		});
	});
	function selBill(obj){
		$(obj).parent().children().removeClass("success");
		$(obj).addClass("success");
		var region = $(obj).attr("region");
		$("#myDlgBody_lg").load("${ctx}/common/search_order_dlg",{billId:region});
		$("#myDlg_lg").modal();
	}
	function balanceOrder(obj){
		$("#myDlgBody_lg").load("${ctx}/common/init_balanceOrder_dlg");
		$("#myDlg_lg").modal();
	}
	function formatDate(date){
		date = parseInt(date)*100000;
		var d = new Date();
		d.setTime(date);
		return d.format("yyyy-MM-dd");
	}
	Date.prototype.format = function(format) {
	       var date = {
	              "M+": this.getMonth() + 1,
	              "d+": this.getDate(),
	              "h+": this.getHours(),
	              "m+": this.getMinutes(),
	              "s+": this.getSeconds(),
	              "q+": Math.floor((this.getMonth() + 3) / 3),
	              "S+": this.getMilliseconds()
	       };
	       if (/(y+)/i.test(format)) {
	              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	       }
	       for (var k in date) {
	              if (new RegExp("(" + k + ")").test(format)) {
	                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
	                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	              }
	       }
	       return format;
	}
	function callBack(resultView,startDate,endDate){
		$("#statiumId_").val(statiumId);
		$("#resultView").val(resultView);
		$("#startDate_").val(startDate);
		$("#endDate_").val(endDate);
		$("#balanceForm").submit();
	}
</script>
</body>
</html>