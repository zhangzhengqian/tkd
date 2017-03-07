<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<style>
	#billTable tbody tr:nth-last-child(1){
		color: red;
		font-weight: bold;
	}

</style>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" style="margin:-7px 0px 0px 0px">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">

	<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">场馆结账</h3>
  </div>
  <div class="panel-body">
	<div class="row">
		<input id="startDate_" type="hidden">
		<input id="endDate_" type="hidden">
        <div class="col-sm-3">
           <input type="text" class="form-control" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endDate\')}'})" id="startDate" placeholder="请输入开始时间">
        </div>
        <div class="col-sm-3">
           <input type="text" class="form-control" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startDate\')}'})" id="endDate" placeholder="请输入结束时间">
        </div>
        <div class="col-sm-6">
           <button class="btn btn-primary" id="searchBtn" type="button">查询</button>　
           <button class="btn btn-primary" disabled id="balanceBtn" type="button">结算</button>
        </div>
    </div>
	<br>
    <div class="row">
	    <div id="orderTable" style="overflow: auto;" class="col-sm-12">
	    
	    </div>
    </div>
</div>
  </div>
  <textarea id="templates" style="display:none">
	    <div><span id="totalFee" totalFee="{($T.totalFee==null?0:$T.totalFee)}" class="text-danger">总金额：{($T.totalFee==null?0:$T.totalFee)}</span></div>
	        <table id="billTable" class="table table-bordered table-striped table-hover" >
	            <thead>
						<tr>
							<th>日期</th>
							<th>订单总数</th>
							<th>应付总额</th>
							<th>实付总额</th>
							<th>未结总额</th>
							<th>补贴总额</th>
						</tr>
					</thead>
					<tbody>
						{#foreach $T.table as record} 
						<tr>
							<td>{$T.record.perDate}</td>
							<td>{$T.record.orderCount}</td>
							<td>{$T.record.totalFee}</td>
							<td>{$T.record.totalFinalFee}</td>
							<td>{$T.record.costFee}</td>
							<td>{$T.record.subsidy}</td>
						</tr>
						{#/for}  
					</tbody>
	        </table>
	     </textarea>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal" id="search_org_selected">关闭</button>
</div>
<script type="text/javascript">
$(function(){
	var resultView = null;
	$("#searchBtn").on("click",function(e){
		if($("#startDate").val()==undefined||$("#startDate").val()==''){
			myAlert("开始时间不能为空！","error");
			return;
		}
		if($("#endDate").val()==undefined||$("#endDate").val()==''){
			myAlert("结束时间不能为空","error");
			return;
		}
		var subsidy = $("#subsidy").prop('checked');
		if(subsidy){
			subsidy=1;
		}else{
			subsidy=0;
		}
		$.get('${ctx}/common/getOrders?subsidy='+subsidy+'&statiumId='+statiumId+'&startDate='+$("#startDate").val()+"&endDate="+$("#endDate").val(),function(data){
			if(data!=null){
				if("error" in data){
					myAlert(data.message,"error");
				}else{
					var view = data[data.length-1];
					var subsidy = view["subsidy"];
					var costFee = view["costFee"];
		        	var tableDatas = {"totalFee":costFee+subsidy,table:data};
		        	$("#orderTable").html();
		        	
	        		$("#orderTable").setTemplateElement("templates");
	                // 给模板加载数据
	                $("#orderTable").processTemplate(tableDatas);
	                resultView = JSON.stringify(data);
	                $("#balanceBtn").attr("disabled",false);
				}
				
        	}
		},"json")
		$("#startDate_").val($("#startDate").val());
		$("#endDate_").val($("#endDate").val())
	});
	$("#balanceBtn").click(function(){
		if($("#startDate").val()==undefined||$("#startDate").val()==''){
			myAlert("开始时间不能为空！","error");
			return;
		}
		if($("#endDate").val()==undefined||$("#endDate").val()==''){
			myAlert("结束时间不能为空","error");
			return;
		}
		if($("#totalFee").attr("totalFee")=='0'){
			myAlert("金额为0，不需结账！","error");
			return;
		}
		callBack(resultView,$("#startDate_").val(),$("#endDate_").val());
		$("#myDlg_lg").modal("hide");
	});
});
</script>