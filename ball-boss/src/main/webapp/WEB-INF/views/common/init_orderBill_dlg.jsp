<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<style>
	#contentTable tbody tr:nth-last-child(1){
		color: red;
		font-weight: bold;
	}

</style>

<div class="modal-header">
	<button type="button" class="close" style="margin:-7px 0px 0px 0px" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">账单明细</h3>
	  </div>
  		<div class="panel-body">
  		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th>日期</th>
				<th>订单总数</th>
				<th>成本总额</th>
				<th>补贴总额</th>
			</tr>
			</thead>
			
			<tbody>
  			<c:forEach items="${items}" var="item" varStatus="stat">
  				<tr>
  					 <td>${item.startDate }</td>
					 <td>${item.orderCount}</td> 				
  					 <td>${item.totalFee}</td>
  					 <td>${item.subsidyAmount}</td>
  				</tr>
  		    </c:forEach>
  		    	<tr>
  					 <td>总计</td>
					 <td>${totalOrder}</td> 				
  					 <td>${totalFee}</td>
  					 <td>${totalSubsidy}</td>
  				</tr>
  		    </tbody>
       </div>
   </div>

</div>