<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
			<div class="row">
				<div class="col-sm-6 text-left">
						 场馆抽奖奖品设置
						&nbsp;&nbsp;
						${year}年${month}月
				</div>
			</div>
	</div>
	
	<div class="panel-body prize-body">
		<form id="prize-body">
			
		</form>
		<div class="saveBtn form-group form-group-sm">
 				<div class="col-md-6 text-center">
 					<button type="botton" onclick="sub()" class="btn btn-primary btn-sm"> 保存</button> 
 				</div>
		</div>
	</div>
</div>
<div class="template row">
	<div  class="col-sm-1 form-inline">
		
	</div>
	<div  class="col-sm-11 form-inline">
		<input  type="text" placeholder="奖品编码" class="form-control input-sm" name="prizeType" value="">
		<input  type="text" placeholder="奖品名称" class="form-control input-sm" name="prizeName" value="">
		<input  type="text" placeholder="奖品数量" class="form-control input-sm" name="amount" value="">
		<input  type="text" placeholder="奖品金额" class="form-control input-sm" name="money" value="">
	</div>
	<hr>	
</div>



<script>
	$(function() {
		menu.active('#prize-set-man');
		$(".template").hide();
		$(".saveBtn").hide();
		var template = $(".template");
		for(var i=0;i<8;i++){
			var row = template.clone();
			row.show();
			row.removeClass("template");
			$("#prize-body").append(row);
			if($("#prize-body").children().size()==1){
				$(".saveBtn").show();
			}
		}
	});
	function sub(){
		var params = $("#prize-body").serialize();
		var pm = params.split("&");
		var paramArray = [];
		var param = {};
		for(var i=0;i<pm.length;i++){
			var p1=pm[i].split("=")[0];
			var p2=pm[i].split("=")[1];
			if(p1=='prizeType'&&i!=0){
				paramArray.push(param);
				param = {};
			}
			if(i==pm.length-1){
				paramArray.push(param);
			}
			param[p1]=p2;
		}
		$.ajax({
			type : "post",
			url : "${ctx}/prize/setConfig",
			dataType : "text",
			method:"post",
			data : {paramArray:JSON.stringify(paramArray),year:'${year}',month:'${month}'},
			success : function(msg) {
				if(msg=='success'){
					window.location.href="${ctx}/prize/getConfig/"+${year}+"/"+${month};;
				}else{
					myAlert("奖品设置失败!");
				}
			}
		});
	}
</script>

