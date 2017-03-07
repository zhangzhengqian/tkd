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
			        <li><span class="glyphicon glyphicon-home"></span> 场馆结账</li>
			        <li><a href="${ctx }/orders/bill/billList">账期汇总</a></li>
			        <li class="active">场馆账单列表</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
<%-- 		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="billForm" class="form-horizontal" action="${ctx }/orders/bill/billBuild" method="post">
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for=""></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endDate\')}'})" id="startDate" placeholder="请输入开始时间">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startDate\')}'})" id="endDate" placeholder="请输入结束时间">
       	  			</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="button" onclick="search()" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span>生成</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 --> --%>
		
		<div class="row">
			<div class="col-md-7 form-inline">
				<input type="text" id="statiumArea" placeholder="地区" class="form-control">
				<input type="text" id="statiumSport" placeholder="品类" class="form-control">
				<div class="input-group">
					<input type="text" id="statiumName" placeholder="场馆名称" class="form-control">
					<span class="input-group-btn">
		            		<button id="coordinate" class="btn btn-primary btn-sm" type="button">查询</button>
		          	</span>
	          	</div>
			</div>
		</div>
		<br>
		<div class="row">
		<div class="col-md-12">
			<div class="pull-left">
				<shiro:hasPermission name="orderBill:searchnew">
				<a href="${ctx }/orders/bill/statiumBillSearch/${billId}/0" class="btn btn-primary btn-sm "> 查询新账单</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="orderBill:searchbalance">
				<a href="${ctx }/orders/bill/statiumBillSearch/${billId}/1" class="btn btn-info btn-sm"> 查询待结算</a>
				</shiro:hasPermission>
				<a href="${ctx }/orders/bill/statiumBill/${billId}" class="btn btn-success btn-sm"> 查询全部</a>
				<a href="${ctx }/orders/bill/statiumBillExport/${billId}/0" class="btn btn-success btn-sm"> 导出新账单</a>
				<a href="${ctx }/orders/bill/statiumBillExport/${billId}/1" class="btn btn-success btn-sm"> 导出待结算</a>
				<a href="${ctx }/orders/bill/statiumBillExport/${billId}/2" class="btn btn-success btn-sm"> 导出已结算</a>
			</div>
			<div class="pull-right">
				<shiro:hasPermission name="orderBill:batchvery">
				<a href="JavaScript:;" onclick="verify()" class="btn btn-primary btn-sm "> 批量确认</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="orderBill:batchbalance">
				<a href="JavaScript:;" onclick="balance()" class="btn btn-info btn-sm "> 批量结算</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="orderBill:veryall">
				<a href="${ctx }/orders/bill/verifyBillAll?billId=${billId}" class="btn btn-primary btn-sm "> 全部确认</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="orderBill:balanceall">
				<a href="${ctx }/orders/bill/balanceBillAll?billId=${billId}" class="btn btn-info btn-sm"> 全部结算</a>
				</shiro:hasPermission>
			</div>
		</div>
	</div>
	<div class="row">
		&nbsp;
	</div>
	<div class="row">
    	<div class="col-table col-md-12" >			
		<table class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center"><input type="checkbox" name="chk_all"
							onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
				<th>场馆名称</th>
				<th>地区</th>
				<th>地址</th>
				<th>电话</th>
				<th>订单总数</th>
				<th>成本总额</th>
				<th>补贴总额</th>
				<th>总额</th>
				<th>状态</th>
				<th>品类</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="bill" varStatus="stat">
				<tr 
					<c:choose>
						<c:when test="${bill.status == 0}">
							class="warning"
						</c:when>
						<c:when test="${bill.status == 1}">
							class="info"
						</c:when>
						<c:when test="${bill.status == 2}">
							
						</c:when>
					</c:choose>
				 id="${bill.id}" bill_status="${bill.status}">
					<td class="text-center"><input type="checkbox"
								name="chk_list" id="chk_list_${stat.index }" value="${bill.id}" /></td>
					<td>${bill.statiumName}</a></td>
					<td>${bill.area}</a></td>
					<td>${bill.address }</td>
					<td>${bill.phone}</td>
					<td>${bill.orderNum}</td>
					<td>${bill.fee}</td>
					<td>${bill.subsidy}</td>
					<td>${bill.subsidy+bill.fee}</td>
					<c:choose>
						<c:when test="${bill.status == 0}">
							<td>新账单</td>
						</c:when>
						<c:when test="${bill.status == 1}">
							<td>待结算</td>
						</c:when>
						<c:when test="${bill.status == 2}">
							<td>已结算</td>
						</c:when>
					</c:choose>
					<td>${bill.sportType}</td>
					<td>
						<a href="javascript:;" onclick="selBill('${bill.billId}','${bill.statiumId}')">查看</a>
					<c:choose>
						<c:when test="${bill.status == 0}">
							<shiro:hasPermission name="orderBill:batchvery">
							　<a href="${ctx }/orders/bill/verifyBillSel?id=${bill.id}&billId=${bill.billId}">确认</a>
							</shiro:hasPermission>
						</c:when>
						<c:when test="${bill.status == 1}">
							<shiro:hasPermission name="orderBill:batchbalance">
							　<a href="${ctx }/orders/bill/balanceBillSel?id=${bill.id}&billId=${bill.billId}">结算</a>
							</shiro:hasPermission>
						</c:when>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
		<tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">
  $(function() {
	  menu.active('#orders-bill');
	  $("#coordinate").on("click",function(){
		  var url="${ctx }/orders/bill/statiumBillSearchByStatiumName/${billId}/"+$("#statiumName").val()+"_"+$("#statiumArea").val()+"_"+$("#statiumSport").val();
		  window.location.href=url;
	  })
  });
  function search(){
	  $("#billForm").submit();
  }
  function selBill(billId,statiumId){
	$("#myDlgBody_lg").load("${ctx}/common/search_order_dlg",{billId:billId+statiumId});
	$("#myDlg_lg").modal();
  }
  function getSelected() {
	var ids = [];
	var checked = $('input:checked');
	if (checked.length) {
		checked.each(function() {
			if ($(this).attr('name') != 'chk_all') {
				ids.push($(this).val());
			}
		});
	}
	return ids;
  }
  function verify(){
	  var ids = getSelected();
	  for(var index=0;index<ids.length;index++){
		  var status = $("#"+ids[index]).attr("bill_status");
		  if(status!='0'){
			  myAlert("所选账单中有非新账单，不能批量确认","error");
			  return;
		  }
	  }
	  ids = ids.join(',');
	  window.location.href="${ctx }/orders/bill/verifyBillSel?id="+ids+"&billId=${billId}";
  }
  
  function balance(){
	  var ids = getSelected();
	  for(var index=0;index<ids.length;index++){
		  var status = $("#"+ids[index]).attr("bill_status");
		  if(status!='1'){
			  myAlert("所选账单中有非待结算，不能批量结算","error");
			  return;
		  }
	  }
	  ids = ids.join(',');
	  window.location.href="${ctx }/orders/bill/balanceBillSel?id="+ids+"&billId=${billId}";
  }

</script>

</body>
</html>
