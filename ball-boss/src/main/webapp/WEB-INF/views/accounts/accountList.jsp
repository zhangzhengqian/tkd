<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户管理</title>
</head>
<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 账户管理</li>
        <li class="active" >账户列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="accountForm" action="${ctx}/accounts/list" method="post">
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-0 sr-only" for="custName"></label>
	  				<div class="col-md-3">
            			<input type="text" class="form-control input-sm" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="按手机号查询">
       	  			</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button id="resetButton" type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" id="search_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				<shiro:hasPermission name="ssouser:exportExcel">
	    				<!-- &nbsp;&nbsp;
	    				<button type="submit" id="export_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-import"></span> 导出Excel</button> -->
	    				</shiro:hasPermission>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
				
		<div class="row">
	    	<div class="col-table col-md-12" >		
				<table id="contentTable" class="table table-bordered table-condensed table-hover">
					<thead class="thead">
						<tr>
							<th class="text-center">编号</th>
							<th>球友号</th>
							<th>用户昵称</th>
							<th>手机号</th>
							<th>用户类型</th>
							<th>余额（元）</th>
							<th>账户状态</th>
							<th>操作</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${data.content}" var="account" varStatus="stat">
							<tr>
								<td style="text-align: center;">${stat.count }</td>
								<td style="text-align: center;">${account.qiuyouNo }</td>
								<td style="text-align: center;">${account.nickName }</td>
								<td style="text-align: center;">${account.phone }</td>
								<td style="text-align: center;">${account.userType }</td>
								<td style="text-align: center;">
									<fmt:formatNumber value="${account.total }" type="number" pattern="0.00"/>
								</td>
								<td style="text-align: center;">${account.status }</td>
								<td style="text-align: center;">
								  <a href="${ctx}/accounts/accountView/${account.userId }">查看</a>
								  <shiro:hasPermission name="ssouser:sendMsg">
								  	<a onclick="withDrawInit('${account.userId }')" href="javascript:;">提现</a>
								  </shiro:hasPermission>
								  <c:if test="${account.status=='正常'}" >
									  <%-- <shiro:hasPermission name="ssouser:freezeSsoUser"> --%>
									  	<a href="#" onclick="freeze('${account.userId }')"> 冻结</a> 
									  <%-- </shiro:hasPermission> --%>
								  </c:if>
								  <c:if test="${account.status=='冻结'}" >
									  <%-- <shiro:hasPermission name="ssouser:unfreezeSsoUser"> --%>
									  	<a href="#" onclick="unfreeze('${account.userId }')"> 解冻</a> 
									  <%-- </shiro:hasPermission> --%>
								  </c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>		
				</table>
			 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
		
		<form id="actionForm" action="" method="post">
       		<input type="hidden" id="ids" name="ids">
    	</form>
  </div><!-- /右侧主体内容 -->
</div>

<div class="modal fade" id="withDrawModel" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">提现(只支持支付宝提现)</h4>
				</div>
				<div class="modal-body" class="">
					<div  class="form-group form-inline">
						<input type="hidden" id="draw_userId">
						<input type="text" class="form-control" onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="支付宝账号" id="draw_attrInfo">
						<input type="text" class="form-control" onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="提现金额" id="draw_amount">
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="withDraw()">确定</a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function() {
	  menu.active('#accounts-list');
	  $('#adminFooter').hide();
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  $('#search_EQ_level option[value='+$('#v_search_EQ_level').val()+']').attr('selected','selected');
	  $('#search_EQ_registSource option[value='+$('#v_search_EQ_registSource').val()+']').attr('selected','selected');
	  $('#search_EQ_activeState option[value='+$('#v_search_EQ_activeState').val()+']').attr('selected','selected');
});

//冻结
function freeze(userId) {
	 var $form = $('#actionForm');
	 $form.attr('action', '${ctx}/accounts/freeze/'+userId);
	 bootbox.confirm('你是否确认冻结该账户？', function(result) {
	   if(result) {
	     $form[0].submit();
	   }
	 });
	 return false;
}

//解冻
function unfreeze(userId) {
	var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/accounts/unfreeze/'+userId);
	  bootbox.confirm('你是否确认解冻该账户？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
}

$("#export_btn").click(function(){
	$("#ssoUserForm").attr("action","${ctx}/ssouser/exportExcel");
	$("#ssoUserForm").attr("method","post");
})


$("#search_btn").click(function(){
	$("#ssoUserForm").attr("action","${ctx}/ssouser/user");
	$("#ssoUserForm").attr("method","post");
})

function withDrawInit(userId){
	$("#draw_userId").val(userId);
	$("#draw_attrInfo").val("");
	$("#draw_amount").val("");
	$("#withDrawModel").modal();
	$("body").css('padding-right','0px');
}

function withDraw(userId){
	var draw_attrInfo = $("#draw_attrInfo").val();
	if(draw_attrInfo==undefined||draw_attrInfo==''){
		bootbox.alert("支付宝账号不能为空！");
		$("body").css('padding-right','0px');
		return;
	}
	var draw_amount = $("#draw_amount").val();
	if(draw_amount==undefined||draw_amount==''){
		bootbox.alert("提现金额不能为空！");
		$("body").css('padding-right','0px');
		return;
	}
	$.post("${ctx}/accounts/withdrawCash", {
		userId : $("#draw_userId").val(),
		amount : draw_amount,
		accountInfo:draw_attrInfo
	}, function(res) {
		if(res["success"]==0){
			myAlert("提现成功");
			window.location.reload();
		}else{
			myAlert(res["reason"],"error");
		}
	},"json");
}
</script>

</body>
</html>