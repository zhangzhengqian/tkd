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
			        <li class="active">账期汇总</li>
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
		      <form id="billForm" class="form-horizontal" action="${ctx }/bill/billBuild" method="post">
		         <div class="form-group form-group-sm"> 
		         	<div class="col-md-3">
			        	<input type="text" class="form-control" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endDate\')}'})" id="startDate" placeholder="请输入开始时间">
			       	</div>
	  				<div class="col-md-3">
            			<input type="text" class="form-control" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startDate\')}'})" id="endDate" placeholder="请输入结束时间">
       	  			</div>
       	  			<div class="col-md-3">
            			<select name="ordersType" class="form-control" id="ordersType">
			        	   <option value="0">--课程和活动--</option>
			        	   <option value="1">--充值卡--</option>
		          		</select>
       	  			</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<shiro:hasPermission name="order:buildBill">
	    				<button type="button" onclick="search()" id="submit_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span>生成</button>
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
				<th class="text-center">序号</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>道馆数量</th>
				<th>订单数量</th>
				<th>金额</th>
				<th>账单类型</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="bill" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${bill.startTime}</a></td>
					<td>${bill.endTime}</a></td>
					<td>${bill.statiumNum }</td>
					<td>${bill.orderNum}</td>
					<td>${bill.totalAmount}</td>
					 <td>
						<c:choose>
							<c:when test="${bill.type == 0}">
								课程和活动
							</c:when>
							<c:when test = "${bill.type == 1 }">
								充值卡
							</c:when>
						</c:choose>
					</td> 
					<td><a href="${ctx }/bill/statiumBill/${bill.id}">查看</a>&nbsp&nbsp
					<shiro:hasPermission name="bill:delBill">
					<c:if test="${bill.avaliable==true }"><a href="${ctx }/bill/delBill?id=${bill.id}">删除</a></c:if>
					</shiro:hasPermission>
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
  });
  function search(){
	  var startDate = $("#startDate").val();
	  var endDate = $("#endDate").val();
	   var ordersType = $("#ordersType").val(); 
	  if(startDate!=""&&endDate!=""){
		  $.ajax({
			  cache:true,
			  type:"post",
			  url:"${ctx}/bill/isBuildOk",
			  data:{
				  "startDate":startDate,
				  "ordersType":ordersType,
			  },
		  	  error:function(request){
		  		  common.showMessage("生成账单失败");
		  	  },
			  success:function(data){
				  var data = eval('('+data+')');
				  if(data.result==false){
					  common.showMessage(data.reason);
				  }
				     if(data.result==true){
						var form = $("#billForm");
						form.submit();
					  }
			  }
	  });
	  }
	  else{
		  return;
	  }
	 
}
</script>

</body>
</html>
