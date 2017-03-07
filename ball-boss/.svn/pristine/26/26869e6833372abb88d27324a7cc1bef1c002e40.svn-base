<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>奖金账户管理</title>
</head>
<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 奖金账户管理</li>
        <li class="active" >奖金账户列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="accountForm" action="${ctx}/accounts/bonusList" method="post">
				  <div class="form-group form-group-sm">
					  <label class="control-label col-md-1 sr-only" for=""></label>
					  <div class="col-md-5">
						  <input type="text" class="form-control" value="${param.search_GTE_ct}" name="search_GTE_ct" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'search_LTE_ct\')}'})" id="search_GTE_ct" placeholder="请输入开始时间">
					  </div>
					  <div class="col-md-5">
						  <input type="text" class="form-control"  value="${param.search_LTE_ct}"  name="search_LTE_ct" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'search_GTE_ct\')}'})" id="search_LTE_ct" placeholder="请输入结束时间">
					  </div>
				  </div>
				  <div class="form-group form-group-sm">
					  <label class="control-label col-md-1 sr-only" for=""></label>
					  <div class="col-md-5">
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
							<th>奖金余额（元）</th>
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
								  <a href="${ctx}/accounts/bonusAccountView/${account.userId }">查看</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>		
				</table>
			 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
	  <div class="col-md-12">
		  <div class="col-md-6"></div>
		  <div class="col-md-2 text-danger">奖金发放金额总计：${lf:formatMoney(ffze) }元</div>
		  <div class="col-md-2 text-danger">奖金消费金额总计：${lf:formatMoney(xfze) }元</div>
		  <div class="col-md-2 text-danger">奖金退款金额总计：${lf:formatMoney(tkze) }元</div>

	  </div>
		<form id="actionForm" action="" method="post">
       		<input type="hidden" id="ids" name="ids">
    	</form>
  </div><!-- /右侧主体内容 -->
</div>




<script type="text/javascript">
$(function() {
	  menu.active('#bonusAccounts-list');
	  $('#adminFooter').hide();
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
});
$("#search_btn").click(function(){
	$("#ssoUserForm").attr("action","${ctx}/accounts/bonusList");
	$("#ssoUserForm").attr("method","post");
})

</script>
</body>
</html>