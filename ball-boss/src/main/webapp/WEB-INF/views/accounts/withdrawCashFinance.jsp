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
        <li class="active" >提现处理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="accountForm" action="" method="post">
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
							<th>提现金额（元）</th>
							<th>交易类型</th>
							<th>用户账号</th>
							<th>提交时间</th>
							<th>提现状态</th>
							<th>操作</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${data.content}" var="log" varStatus="stat">
							<tr>
								<td style="text-align: center;">${stat.count }</td>
								<td style="text-align: center;">${log.qiuyouNo }</td>
								<td style="text-align: center;">${log.nickName }</td>
								<td style="text-align: center;">${log.phone }</td>
								<td style="text-align: center;">
									<fmt:formatNumber value="${log.amount }" type="number" pattern="0.00"/>
								</td>
								<td style="text-align: center;">
								  <c:if test="${log.tradetype == 1}" >支付宝</c:if>
								  <c:if test="${log.tradetype == 2}" >微信</c:if>
								</td>
								<td style="text-align: center;">${log.attrInfo }</td>
								<td style="text-align: center;">${log.time }</td>
								<td style="text-align: center;">${log.status }</td>
								<td style="text-align: center;">
								        <a href="${ctx}/accounts/accountView/${log.userId }" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i> 查看</a>
								  <c:if test="${log.status=='处理中'}" >
									  <%-- <shiro:hasPermission name="ssouser:unfreezeSsoUser"> --%>
									    <a href="#" onclick="process('${log.logId }')" class="btn btn-default btn-sm"><i class="glyphicon"></i> 确认转账</a>
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

<script type="text/javascript">
$(function() {
	  menu.active('#withdrawCash-finance');
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

//审核
function process(logId) {
	 var $form = $('#actionForm');
	 $form.attr('action', '${ctx}/accounts/process/'+logId);
	 bootbox.confirm('你是否确认已完成该提现转账？', function(result) {
		   if(result) {
		     $form[0].submit();
		   }
		 });
	 return false;
}

$("#export_btn").click(function(){
//	$("#ssoUserForm").attr("action","${ctx}/ssouser/exportExcel");
//	$("#ssoUserForm").attr("method","post");
})


$("#search_btn").click(function(){
//	$("#ssoUserForm").attr("action","${ctx}/ssouser/user");
//	$("#ssoUserForm").attr("method","post");
})
</script>

</body>
</html>