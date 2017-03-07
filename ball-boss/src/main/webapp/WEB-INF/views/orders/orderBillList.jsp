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
		      <form id="billForm" class="form-horizontal" action="${ctx }/orders/bill/billBuild" method="post">
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for=""></label>
		         	<div class="col-md-3">
			        	<input type="text" class="form-control" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endDate\')}'})" id="startDate" placeholder="请输入开始时间">
			       	</div>
	  				<div class="col-md-3">
            			<input type="text" class="form-control" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startDate\')}'})" id="endDate" placeholder="请输入结束时间">
       	  			</div>
       	  			<div class="col-md-3">
            			<select name="ordersType" class="form-control">
			        	   <option value="0">-场馆-</option>
			          	   <option value="1">-乐享赛-</option>
		          		</select>
       	  			</div>
		         </div>
		        <shiro:hasPermission name="orderBill:build">
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" onclick="search()" id="submit_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span>生成</button>
	  				</div>
				</div>
				</shiro:hasPermission>
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
				<th>球馆数量</th>
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
								场馆订场
							</c:when>
							<c:when test="${bill.type == 1}">
								乐享赛
							</c:when>
						</c:choose>
					</td>
					<td><a href="${ctx }/orders/bill/statiumBill/${bill.id}">查看</a><c:if test="${bill.del}">　<a href="${ctx }/orders/bill/delBill?id=${bill.id}">删除</a></c:if></td>
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
	  $('#billForm').validate({
			submitHandler: function(form) {
				//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
				getData(function(data){
					app.disabled("#submit_btn");
					form.submit(); 
				});
			}
	  });
  }
</script>

</body>
</html>
