<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球馆会员卡充值记录</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">球馆会员卡充值记录</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
	    <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-primary" href="${ctx}/vip/exportVipCardRechargeLog/${vipCardId}"><span class="glyphicon"></span> 导出Excel</a>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->		 
  
	<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>会员卡号</th>
				<th>会员卡名称</th>
				<th>场馆名称</th>
				<th>会员卡余额</th>
				<th>充值余额</th>
				<th>充值时间</th>
				<th>充值人员</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="log" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${log.cardNumber }</td>
					<td style="text-align: center;">${log.cardName }</td>
					<td style="text-align: center;">${log.statiumName }</td>
					<td style="text-align: center;">${lf:formatMoney(log.balance)}</td>
					<td style="text-align: center;">${lf:formatMoney(log.rechargeAmount)}</td>
					<td style="text-align: center;"><fmt:formatDate value="${log.ct}" pattern="yyyy-MM-dd"/></td>
					<td style="text-align: center;">${log.rechargePerson}</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		<div class="form-group form-group-sm">
			<hr>
			<div class="col-md-offset-4 col-md-2">
				<a class="btn btn-default btn-block" href="${ctx}/vip/view/${vipCardId}"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
     	</div>	
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
	  menu.active('#vip-man');
	  
	  $("#export_btn").click(function(){
	   		$("#activityForm").attr("action","${ctx}/activity/export");
			$("#activityForm").submit();
			setTimeout("modify()",1000);
	  }); 
  });
</script>
</body>
</html>