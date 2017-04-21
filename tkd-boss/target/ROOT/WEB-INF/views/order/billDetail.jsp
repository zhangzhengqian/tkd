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
			        <li><span class="glyphicon glyphicon-home"></span> 道馆结账</li>
			        <li><a href="${ctx }/bill/orderBill">账期汇总</a></li>
			        <li class="active">场馆账单列表</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row">
			<div class="col-md-7 form-inline">
				<input type="text" id="statiumArea" placeholder="地区" class="form-control">
				<div class="input-group">
					<input type="text" id="statiumName" placeholder="道馆名称" class="form-control">
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
				<a href="${ctx }/bill/statiumBillSearch/${billId}/0" class="btn btn-primary btn-sm "> 查询新账单</a>
				<a href="${ctx }/bill/statiumBillSearch/${billId}/1" class="btn btn-info btn-sm"> 查询待结算</a>
				<a href="${ctx }/bill/statiumBill/${billId}" class="btn btn-success btn-sm"> 查询全部</a>
				<%-- <a href="${ctx }/bill/statiumBillExport/${billId}/0" class="btn btn-success btn-sm"> 导出新账单</a>
				<a href="${ctx }/bill/statiumBillExport/${billId}/1" class="btn btn-success btn-sm"> 导出待结算</a>
				<a href="${ctx }/bill/statiumBillExport/${billId}/2" class="btn btn-success btn-sm"> 导出已结算</a> --%>
				<a href="#" onclick="exportExcel(0)"class="btn btn-success btn-sm"> 导出新账单</a>
				<a href="#" onclick="exportExcel(1)" class="btn btn-success btn-sm"> 导出待结算</a>
				<a href="#" onclick="exportExcel(2)" class="btn btn-success btn-sm"> 导出已结算</a>
				
			</div>
			<div class="pull-right">
				<a href="Javascript:;" onclick="verify()" class="btn btn-primary btn-sm " id="verify"> 批量确认</a>
				<a href="javaScript:;" onclick="balance()" class="btn btn-info btn-sm "> 批量结算</a>
				<a href="${ctx }/bill/verifyBillAll?billId=${billId}" class="btn btn-primary btn-sm "> 全部确认</a>
				<a href="${ctx }/bill/balanceBillAll?billId=${billId}" class="btn btn-info btn-sm"> 全部结算</a>
			</div>
		</div>
	</div>
	<div class="row">
		&nbsp;
	</div>
	<div class="row">
    	<div class="col-table col-md-12" >			
		<table class="table table-bordered table-condensed table-hover">
			<input type="hidden" name="billId"  id="billId" value="${billId}"/>
			<thead class="thead">
			<tr>
				<th class="text-center"><input type="checkbox" name="chk_all" id="chk_all"/></th>
				<th>道馆名称</th>
				<th>地区</th>
				<th>地址</th>
				<th>电话</th>
				<th>订单总数</th>
				<th>总额</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="bill" varStatus="stat">
			<!-- 开始时间+结束时间 -->
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
					<td>${bill.costPrice}</td>
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
					<td>
						<a href="Javascript:;" onclick="selBill('${bill.billId}')">查看</a>
					<c:choose>
						<c:when test="${bill.status == 0}">
							　<a href="${ctx }/bill/verifyBillSel?id=${bill.id}&billId=${billId}">确认</a>
						</c:when>
						<c:when test="${bill.status == 1}">
							　<a href="${ctx }/bill/balanceBillSel?id=${bill.id}&billId=${billId}">结算</a>
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
  var billId = $("#billId").val();
  $(function() {
	  menu.active('#orders-bill');
	  <!--条件查询-->
	  $("#coordinate").on("click",function(){
		  //billId 开始时间+结束时间
		  var statiumName = $("#statiumName").val();
		  var statiumArea = $("#statiumArea").val();
		  var condition = statiumName+"_"+statiumArea;
		  var url="${ctx }/bill/statiumBillSearchByStatiumName/"+billId+"/"+condition;
		  window.location.href=url;
	  })
	  
	  
	  <!--全选和全不选-->
	  $("#chk_all").on("click",function(){
		  if(this.checked==true){
			  var chk_list = $("input[name='chk_list']");
			  if(chk_list.length){
				  chk_list.each(function(){
					  this.checked = true;
				  })
			  }
		  }
		  if(this.checked==false){
			  var chk_list = $("input[name='chk_list']");
			  if(chk_list.length){
				  chk_list.each(function(){
					  this.checked = false;
				  })
			  }
		  }
		  
		  
	  })
	  
  });
  
  function exportExcel(id){
	  $.ajax({
		  cache:true,
		  url:'${ctx}/bill/isExportOk',
		  data:{
			"billId":billId,
			"type":id  
		  },
		  type:'get',
		  error:function(request){
			  common.showMessage("导出账单失败");
		  },
		  success:function(data){
			  var data = eval('('+data+')');
			  if(!data.result){
				  common.showMessage(data.reason)
			  }
			  else{
				  window.location.href="${ctx}/bill/statiumBillExport/"+billId+"/"+id;
			  }
		  }
	  });
  }
  
  <!--查看每日结账-->
  function selBill(billId){
	$("#myDlgBody_lg").load("${ctx}/bill/init_orderBill_dlg?billId="+billId);
	$("#myDlg_lg").modal();
  }
  
  <!--确认和批量确认-->
  function verify(){
	  	var ids = getSelected();
	  	var billId = $("#billId").val();
	  	for(var index=0;index<ids.length;index++){
		  	var status = $("#"+ids[index]).attr("bill_status");
		  	if(status!=0){
			  	myAlert("所选账单中有非新账单，不能批量确认","error");
			  	return;
		  	}
		  	ids.join(",");
		  	window.location.href="${ctx }/bill/verifyBillSel?id="+ids+"&billId="+billId;
	  }
	  
	}
  
  <!--结算和批量结算-->
  function balance(){
	  	var ids = getSelected();
	  	var billId = $("#billId").val();
	  	for(var index=0;index<ids.length;index++){
		  	var status = $("#"+ids[index]).attr("bill_status");
		  	if(status!=1){
			  	myAlert("所选账单中有非新确认，不能进行结算","error");
			  	return;
		  	}
	  }
	  	ids.join(",");
	  	window.location.href="${ctx }/bill/balanceBillSel?id="+ids+"&billId="+billId;
  }
  <!--打对勾的道馆-->
  function getSelected(){
	  var ids = [];
	  var checkbox = $("input[name='chk_list']");
	  if(checkbox.length){
		  checkbox.each(function(){
			  if(this.checked){
				  ids.push($(this).val());
			  }
		  })
	  }
	  return ids;
  }
  

</script>

</body>
</html>
