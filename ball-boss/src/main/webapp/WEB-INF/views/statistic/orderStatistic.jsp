<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>系统管理</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
		<li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
		<li class="active">订单查询</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="orderVoForm" action="${ctx }/statistic/order" method="post">
		      <div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
									<input class="form-control input-sm" type="text" name="search_GTE_createTime" id="search_GTE_createTime" 
		          		onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'search_LTE_createTime\')}'})"
		          		value="${param.search_GTE_createTime}" data-date-format="yyyy-mm-dd" placeholder="开始日期" />
							</div>
							<div class="col-md-5">
								<input class="form-control input-sm" type="text" name="search_LTE_createTime" id="search_LTE_createTime"
		          		onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'search_GTE_createTime\')}'})"
		           		value="${param.search_LTE_createTime}" data-date-format="yyyy-mm-dd" placeholder="结束日期" />
							</div>
						</div>
		        <div class="form-group form-group-sm">
		        <label class="control-label col-md-1 sr-only" for=""></label>
		        <div class="col-md-5">
		          <input class="form-control input-sm" type="text" name="statiumName" id="statiumName"
		           		value="${param.statiumName}" placeholder="场馆名称" />
		           		<input id="statiumId" type="hidden"
									class="form-control" 
									name="search_EQ_statiumId"
									value="${param.search_EQ_statiumId }" placeholder="按球馆名称查询">
		           </div>
		           <div class="col-md-5">
		           <select name="search_EQ_status" class="form-control">
			          	   <option value="ALL" <c:if test="${param.search_EQ_status == ''}">selected</c:if>>-全部-</option>
		                   <option value="ORDER_PLAYING" <c:if test="${param.search_EQ_status == 'ORDER_PLAYING'}">selected</c:if>>交易成功</option>
		                   <option value="ORDER_PAIED" <c:if test="${param.search_EQ_status == 'ORDER_PAIED'}">selected</c:if>>已支付</option>
		                   <option value="ORDER_REFUNDED" <c:if test="${param.search_EQ_status == 'ORDER_REFUNDED'}">selected</c:if>>已退款</option>
			          </select>
		           </div>
		        </div>
		        <div class="form-group form-group-sm">
		        <label class="control-label col-md-1 sr-only" for=""></label>
		        <div class="col-md-3">
		          <input class="form-control input-sm" type="text" name="search_EQ_activityName" value="${param.search_EQ_activityName }" placeholder="活动名称" />
		         </div>
		           <div class="col-md-3">
		          <input class="form-control input-sm" type="text" name="search_EQ_gameName" value="${param.search_EQ_gameName }" placeholder="赛事名称" />
		         </div>
		         <div class="col-md-3">
		          <input class="form-control input-sm" type="text" name="search_EQ_coachName" value="${param.search_EQ_coachName }" placeholder="教培名称" />
		         </div>
		        </div>
		        <div class="form-group form-group-sm form-inline">
	         		<div class="col-md-1">
			    	</div>
		         	<div class="col-md-6">
		         		<div class="form-inline">
				        	<tags:zone name="search_LIKE_areaCode" value="${param.search_LIKE_areaCode}" id="LIKE_areaCode" />
		         		</div>
			       	</div>
				</div>
		        <div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="submit"  class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								&nbsp;&nbsp;
								<button type="button" id="export_btn" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 导出Excel
								</button>
							</div>
						</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row">
				<div class="col-table col-md-12">
	  <table id="contentTable" class="table table-bordered table-hover ">
			<thead class="thead">
				<tr>
	                <th class="text-center">下单日期</th>
	                <th class="text-center">修改日期</th>
	                <th class="text-center">订单号</th>
	                <th class="text-center">场地地区</th>
	                <th class="text-center">场地名称</th>
	                <th class="text-center">场地地址</th>
	                <th class="text-center">合作次数</th>
	                <th class="text-center">用户名</th>
	                <th class="text-center">用户手机号</th>
	                <th class="text-center">预约日期</th>
	                <th class="text-center">预约时间</th>
                    <th class="text-center">品类</th>
                    <th class="text-center">订场时段数量</th>
                    <th class="text-center">消费金额</th>
                    <th class="text-center">球友卡金额</th>
                    <th class="text-center">优惠券金额</th>
					<th class="text-center">账户支付金额</th>
					<th class="text-center">奖金账户支付金额</th>
                    <th class="text-center">成本金额</th>
                    <th class="text-center">补贴金额</th>
                    <th class="text-center">支付方式</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">订单类型</th>
                    <th class="text-center">原因/备注</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="order">
				<tr>
					<td class="text-center"><fmt:formatDate value="${order.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="text-center"><fmt:formatDate value="${order.et}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="text-center">${order.id}</td>
					<td class="text-center">${order.areaStr}</td>
					<td class="text-center">${order.name}</td>
					<td class="text-center">${order.address}</td>
                   	<td class="text-center">${order.cooperateNum}</td>
					<td class="text-center">${order.username}</td>
                   	<td class="text-center">${order.phone}</td>
                   	<td class="text-center">${order.orderDate}</td>
                   	<td class="text-center">${order.orderTime}</td>
                   	<td class="text-center">${order.sportType}</td>
                   	<td class="text-center">${order.periodNum}</td>
                   	<td class="text-center">${order.finalFee}</td>
                   	<td class="text-center">${order.qiuyouFee}</td>
                   	<td class="text-center">${order.subAmount}</td>

					<td class="text-center">${order.acountFee}</td>
					<td class="text-center">${order.bounsAccountFee}</td>

                   	<td class="text-center">${order.costPrice}</td>
                   	<td class="text-center">${order.subsidies}</td>
                   	<td class="text-center">${order.payTypeStr}</td>
                   	<td class="text-center">
                   		<c:choose>
                    		<c:when test="${order.status == 'ORDER_PLAYING'}">交易成功</c:when>
                    		<c:when test="${order.status == 'ORDER_PAIED'}">已支付</c:when>
                    		<c:when test="${order.status == 'ORDER_VERIFY'}">已确认</c:when>
                    	</c:choose>
                   	</td>
                   	<td class="text-center">${order.ordersTypeStr}</td>
                   	<td class="text-center">${order.reason}</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div></div>
        <tags:pagination page="${data}" />
        <tags:errors />
	  
  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#statistic-order');
	  $("#footer").hide();
	  $("#export_btn").click(function(){
   		$("#orderVoForm").attr("action","${ctx}/statistic/orderExport");
		$("#orderVoForm").submit();
		setTimeout("modify()",1000);
  	}); 
	  
	//====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
          max: 30,
          autoFill: false,
          mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
          matchContains: true, 
          scrollHeight: 220,
          width: $('#statiumName').outerWidth(),
          multiple: false,
          formatItem: function (row, i, max) {                    //显示格式
              return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
          },
          formatResult: function (row) {                      //返回结果
              return row.name;
          },
          handleValue:function(id){
          	$('#statiumId').val(id);
          },
          parse:function(data){
          	var parsed = [];
      		var rows = data;
      		for (var i=0; i < rows.length; i++) {
      			var row = rows[i];
      			if (row) {
      				parsed[parsed.length] = {
      					data: row,
      					value: row["id"],
      					result: this.formatResult(row)
      				};
      			}
      		}
      		return parsed;
          }
		});
		//====================================================
		// 自动匹配 场馆名称 <<<<
		//====================================================
  });
  function modify(){
	  $("#orderVoForm").attr("action","${ctx}/statistic/order");
  }

</script>

</body>
</html>
