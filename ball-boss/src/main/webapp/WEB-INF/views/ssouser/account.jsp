<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户管理</title>
	<style type="text/css">
		.panel-body .row{
			margin-top:10px;
		}
	</style>
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
	    <div class="row">
	    	<div class="col-md-2 text-right">球友号：</div>
	  		<div class="col-md-6">${data.qiuyouNo }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">用户名：</div>
	  		<div class="col-md-6">${data.name }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">手机号：</div>
	  		<div class="col-md-6">${data.phone }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">账户状态：</div>
	  		<div class="col-md-6">${data.state }</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">账户余额：</div>
	  		<div class="col-md-6">${data.total }元</div>
	    </div>
	    <div class="row">
	    	<div class="col-md-2 text-right">冻结金额：</div>
	  		<div class="col-md-6">${data.freez }元</div>
	    </div>
		
		<hr>
		 <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		      <div class="btn-group-sm pull-left mtb10 form-inline">
		      	<input type="text" class="form-control Wdate " id="starts" name="starts" value="${start}" placeholder="开始" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'starts\')||\'%y-%M-%d\'}'})">
            	<input type="text" class="form-control Wdate " id="ends" name="ends" value="${end}" placeholder="结束" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'ends\')}'})">
		      	<button type="button" style="margin-left:20px;" class="searchDate btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;筛选</button>
			  </div>
				<div class="btn-group-sm pull-right mtb10 form-inline">　　
		      	<button value="" type="button" class="searchState btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;全部</button>
		      	<button value="0" type="button" class="<c:if test='${type=="0"}'>active</c:if> searchState btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;充值记录</button>
		      	<button value="1" type="button" class="<c:if test='${type=="1"}'>active</c:if> searchState btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;支出记录</button>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
		<div class="row">
	    	<div class="col-table col-md-12" >		
				<table id="contentTable" class="table table-bordered table-condensed table-hover">
					<thead class="thead">
						<tr>
							<th>流水号</th>
							<th>订单号</th>
							<th>交易金额</th>
							<th>交易时间</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${data.logs}" var="log" varStatus="stat">
							<tr>
								<td>${log.tradeno }</td>
								<td>${log.orderId }</td>
								<td>${log.amount/100 }</td>
								<td style="text-align: center;">
									<fmt:formatDate value="${log.ct }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>${log.description }</td>
								<td>查看</td>
							</tr>
						</c:forEach>
					</tbody>		
				</table>
			 </div><!-- end col-table -->
		 </div><!-- end row -->
		<form id="actionForm" action="${ctx}/ssouser/viewAccount" method="post">
       		<input type="hidden" id="type" name="type" value="${type }">
       		<input type="hidden" id="start" name="start" value="${start }">
       		<input type="hidden" id="end" name="end" value="${end }">
       		<input type="hidden" id="id" name="id" value="${id }">
    	</form>
  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">
$(function() {
	  menu.active('#ssouser-man');
	  $('#adminFooter').hide();
	  $(".searchState").on("click",function(e){
		  var type= $(this).val();
		  if($(this).hasClass("active")){
			  $("#type").val("");
			  $(this).removeClass("active")
		  }else{
			  $("#type").val(type);
			  $(this).addClass("active")
		  }
		  $("#actionForm").submit();
	  });
	  $(".searchDate").on("click",function(e){
		  var type= $(this).val();
		  $("#start").val($("#starts").val());
		  $("#end").val($("#ends").val());
		  $("#actionForm").submit();
	  });
});

</script>

</body>
</html>