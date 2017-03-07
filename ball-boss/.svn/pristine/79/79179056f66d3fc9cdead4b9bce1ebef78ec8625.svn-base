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
        <li class="active" >提现列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="accountForm" action="${ctx}/accounts/withdrawCashList" method="post">
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
							<th>提现账户信息</th>
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
								  &nbsp;&nbsp;
								  <c:if test="${log.status=='审核中' or log.status=='未通过'}" >
									  <%-- <shiro:hasPermission name="ssouser:freezeSsoUser"> --%>
									  <a href="javaScript:audit(0, '${log.logId }')" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-ok-circle"></i> 通过</a>
									  <%-- </shiro:hasPermission> --%>
								  </c:if>
								  <c:if test="${log.status=='审核中'}" >
								  		&nbsp;&nbsp;
								  	  <a href="javaScript:audit(1, '${log.logId }')" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-ban-circle"></i> 拒绝</a>  	
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

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
         	<form id="auditForm" action="" method="post">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">
               	拒绝原因
            </h4>
         </div>
         <div class="modal-body">
         <div class="row">
         		<label for="qiuyouRating" class="col-md-3 control-label text-right"><span class="text-red"></span>原因:</label>
			     <div class="col-md-6 text-right">
		       		<textarea class="form-control" rows="3" style="height: 50px;" id="reason" name="reason" ></textarea>
		       	 </div>
		    </div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭 </button>
            <button type="button" id="submit_btn" class="btn btn-primary">提交</button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript" src="${ctx}/static/js/bootstrap-validation/validate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap-validation/messages_zh.js"></script>
<script type="text/javascript">
$(function() {
	  menu.active('#withdrawCash-list');
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
function audit(action, logId) {
	 var $form = $('#actionForm');
	 $form.attr('action', '${ctx}/accounts/audit/'+action+'/'+logId);
	 if(action==0){
		 bootbox.confirm('你是否确认通过该提现申请？', function(result) {
		   if(result) {
		     $form[0].submit();
		   }
		 });
	 }else{
		 $('#myModal').modal("show");
		 $("#submit_btn").click(function(){
			 if(!$("#reason").val()){
			 	myAlert("请填写拒绝原因.");			
				return ;
			 }
			 var $form = $('#auditForm');
			 $form.attr('action', '${ctx}/accounts/audit/'+action+'/'+logId);
			 $form[0].submit();
		 });
	 }
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