<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
  	<div class="row">
  		<div class="col-md-5">
			<div class="btn-group-sm">
				  <ul class="breadcrumb" style="display:inline;">
			        <li><span class="glyphicon glyphicon-home"></span> 账单管理</li>
			        <li class="active">账单列表</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="billForm" class="form-horizontal" action="${ctx }/orders/bill" method="post">
		        <%-- <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for=""></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_id" name="search_LIKE_id" value="${param.search_LIKE_id }" placeholder="按订单号查询">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_EQ_userPhone" name="search_EQ_userPhone" value="${param.search_EQ_userPhone }" placeholder="按联系电话查询">
       	  			</div>
		         </div> --%>
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for=""></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_GTE_createTime" name="search_GTE_createTime" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_createTime\')||\'%y-%M-%d\'}'})" value="${param.search_GTE_createTime }" placeholder="开始时间">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LTE_createTime" name="search_LTE_createTime" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_createTime\')}'})" value="${param.search_LTE_createTime }" placeholder="结束时间">
       	  			</div>
		         </div>
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for=""></label>
		         	<div class="col-md-5">
			        	<select class="form-control" id="search_EQ_status" name="search_EQ_status">
							<option value="">--请选择流水类型--</option>
							<option value="TRADE_SUCCESS">--订单付款--</option>
							<option value="REFUND_SUCCESS">--订单退款--</option>
							<option value="RECHARGE_SUCCESS">--账户充值--</option>
							<option value="WITH_DRAW_SUCCESS">--账户提现--</option>
						</select>
			       	</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="button" onclick="search()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				&nbsp;&nbsp;
	    				<button type="button" onclick="exportData()" id="export_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-import"></span> 导出Excel</button>
	    				&nbsp;&nbsp;
	    				<!-- <button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button> -->
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
				<th class="text-center">序号</th>
				<th>订单号</th>
				<th>流水号</th>
				<th>用户名称</th>
				<th>应收金额（元）</th>
				<th>实收金额（元）</th>
				<th>日期</th>
				<th>类型</th>
				<th>支付类型</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="log" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<c:if test="${log.payType==4 }"><td><a href="${ctx }/company/service/view/${log.orderId}">${log.orderId}</a></td></c:if>
					<c:if test="${log.payType!=4 }"><td><a href="${ctx }/orders/view/${log.orderId}">${log.orderId}</a></td></c:if>
					<td>${log.tradeNo }</td>
					<td>${log.ssoUser.nickName }</td>
					<td>${lf:formatMoney(log.fee) }</td>
					<td>${lf:formatMoney(log.finalFee) }</td>
					<td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<c:choose>
                   		<c:when test="${log.status == 'REFUND_SUCCESS'}"><td class="warning"><c:if test="${log.payType==4 }">公司-</c:if>订单退款  </td></c:when>
                   		<c:when test="${log.status == 'TRADE_SUCCESS'}"><td class="info"><c:if test="${log.payType==4 }">公司-</c:if>订单付款</td></c:when>
                   		<c:when test="${log.status == 'RECHARGE_SUCCESS'}"><td class="info"><c:if test="${log.payType==4 }">公司-</c:if>账户充值  </td></c:when>
                   		<c:when test="${log.status == 'WITH_DRAW_SUCCESS'}"><td class="warning"><c:if test="${log.payType==4 }">公司-</c:if>账户提现</td></c:when>
                   	</c:choose>
                    <td>${log.payTypeStr }</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
		<div class="col-md-12">
			<div class="col-md-6"></div>
  			<div class="col-md-3 text-danger">应收金额总计：${lf:formatMoney(sum.fee) }元</div>
  			<div class="col-md-3 text-danger">实收金额总计：${lf:formatMoney(sum.finalFee) }元</div>
		</div>
		<tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">
  $(function() {
	  menu.active('#bill-man');
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  $('#adminFooter').hide();
  });
  
  function exportData() {
	  var ids = getSelected();
       var $form = $('#billForm');
	   $form.attr("action","${ctx }/orders/bill/export");
	   $form[0].submit();
  }
  function search(){
		var $form = $('#billForm');
		$form.attr("action","${ctx }/orders/bill");
		$form[0].submit();
	}
</script>

</body>
</html>
