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
        <li class="active" >账户详情</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
<%-- 		<div class="row"><!-- 查询条件 -->
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
	    				&nbsp;&nbsp;
	    					<button type="submit" id="export_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-import"></span> 导出Excel</button>
	    				</shiro:hasPermission>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 --> --%>
		
	    <div class="row">
	    	<div class="col-md-2 text-right">球友号：</div>
	  		<div class="col-md-6">${data.qiuyouNo }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">用户名：</div>
	  		<div class="col-md-6">${data.nickName }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">手机号：</div>
	  		<div class="col-md-6">${data.phone }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">账户状态：</div>
	  		<div class="col-md-6">${data.status }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">账户余额：</div>
	  		<div class="col-md-6">${data.total }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">冻结金额：</div>
	  		<div class="col-md-6">${data.frozen }</div>
	    </div>
		
		<br>
		<div class="row">
	    	<div class="col-table col-md-12" >		
				<table id="contentTable" class="table table-bordered table-condensed table-hover">
					<thead class="thead">
						<tr>
							<th class="text-center">编号</th>
							<th>流水号</th>
							<th>订单号</th>
							<th>交易金额</th>
							<th>交易时间</th>
							<th>描述</th>
							<!-- <th>操作</th> -->
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${logs.content }" var="log" varStatus="stat">
							<tr>
								<td style="text-align: center;">${stat.count }</td>
								<td style="text-align: center;">${log.tradeno }</td>
								<td style="text-align: center;">${log.orderId }</td>
								<td style="text-align: center;">${log.amount/100 }</td>
								<td style="text-align: center;">
									<fmt:formatDate value="${log.ct }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td style="text-align: center;">${log.description }</td>
								<!-- <td style="text-align: center;">查看</td> -->
							</tr>
						</c:forEach>
					</tbody>		
				</table>
			 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${logs }" />
		
		<form id="actionForm" action="" method="post">
       		<input type="hidden" id="ids" name="ids">
    	</form>
  </div><!-- /右侧主体内容 -->
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
/* function freeze(userId) {
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
}) */
</script>

</body>
</html>