<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<style type="text/css">
.remind{
	background-color: #FF6347;
}
</style>
<title>订单管理</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<div class="row">
				<div class="col-md-5">
					<div class="btn-group-sm">
						<ul class="breadcrumb" style="display: inline;">
							<li><span class="glyphicon glyphicon-home"></span> 订单管理</li>
							<li class="active"> 订单查询</li>
						</ul>
					</div>
				</div>
				<div class="col-md-7"></div>
			 </div>
		</div>
	<div class="panel-body">
			<!-- 右侧主体内容 -->

			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="orderForm" class="form-horizontal"
						action="${ctx }/order/orderList" method="post" name="id">
						<input class="form-group form-group-sm" type="hidden" name="search_EQ_handleMan" id="search_EQ_handleMan">
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_id" name="search_LIKE_id"
									value="${param.search_LIKE_id }" placeholder="按订单号查询">
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_ordersType"
									name="search_EQ_ordersType">
									<option value="">--请选择类型--</option>
									<option value="0">--课程--</option>
									<option value="1">--活动--</option>
								</select>
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input id="statiumId" type="text"
									class="form-control" 
									name="search_LIKE_statiumId"
									value="${param.search_LIKE_statiumId }" placeholder="按道馆名称查询">
							</div>
							<div class="col-md-5 form-inline">
						        	<tags:zone name="search_LIKE_areaCode" value="${param.search_LIKE_areaCode}" id="LIKE_areaCode" />
				         	</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" id="activityName"
									class="form-control" 
									name="search_LIKE_activityName"
									value="${param.search_LIKE_activityName }" placeholder="按活动名称查询,只可单独查询">
							</div>
							<div class="col-md-5">
								<input  type="text" id="coachName"
									class="form-control" 
									name="search_LIKE_coachName"
									value="${param.search_LIKE_coachName }" placeholder="按教练名称查询,只可单独查询">
							</div>
						</div>
						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_GTE_ct" name="search_GTE_ct"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_ct\')||\'%y-%M-%d\'}'})"
									value="${param.search_GTE_ct }" placeholder="下单时间-开始">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LTE_ct" name="search_LTE_ct"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_ct\')}'})"
									value="${param.search_LTE_ct }" placeholder="下单时间-结束">
							</div>
						</div>
						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_GTE_et" name="search_GTE_et"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_et\')||\'%y-%M-%d\'}'})"
									value="${param.search_GTE_et }" placeholder="最后修改时间-开始">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LTE_et" name="search_LTE_et"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_et\')}'})"
									value="${param.search_LTE_et }" placeholder="最后修改时间-结束">
							</div>
						</div>
							<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_status"
									name="search_EQ_status">
									<option value="">--全部--</option>
									<option value='ORDER_BILLED'>--交易成功--</option>
									<option value="ORDER_REFUNDED">--已退款--</option>
									<option value="ORDER_PAIED">--已付款--</option>
								</select>
							</div>
							</div>
						
							

						<div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="reset" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 重 置
								</button>
								&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary btn-sm" >
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								&nbsp;&nbsp;
								<shiro:hasPermission name="order:export">
								<button type="button" class="btn btn-primary btn-sm" onclick="exportOrder()"><span class="glyphicon glyphicon-import"> 导出Excel </span>
								</button>
								</shiro:hasPermission>
								<button type="button" class="btn btn-link btn-sm"
									id="btn-query-more">
									<span class="glyphicon glyphicon-chevron-down"></span> 更多条件
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /查询条件 -->

			
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
					<div class="pull-left">
					</div>
				
					<div class="row">
						<div class="col-table col-md-12">
							<table id="contentTable" class="table table-bordered table-hover ">
			<thead class="thead">
				<tr>
					<th class="text-center"><input type="checkbox" name="chk_all" id="ckAll"/></th>
	                <th class="text-center">下单日期</th>
	                <th class="text-center">修改日期</th>
	                <th class="text-center">订单号</th>
	                <th class="text-center">场地地区</th>
	                <th class="text-center">场地名称</th>
	                <th class="text-center">场地地址</th>
	                <th class="text-center">合作次数</th>
	                <th class="text-center">订场时段数量</th>
	                <th class="text-center">用户名</th>
	                <th class="text-center">用户手机号</th>
	                <th class="text-center">预约日期</th>
	                <th class="text-center">预约时间</th>
                    <th class="text-center">消费金额</th>
                    <th class="text-center">支付方式</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">订单类型</th>
                    <th class="text-center">原因/备注</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="order">
				<tr>
					<td class="text-center"><input type="checkbox" name="export" id="export" value="${order.id}" /></td>
					<td class="text-center"><fmt:formatDate value="${order.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="text-center"><fmt:formatDate value="${order.et}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="text-center">${order.id}</td>
					<td class="text-center">${order.areaStr}</td>
					<td class="text-center">${order.statiumName}</td>
					<td class="text-center">${order.address}</td>
                   	<td class="text-center">${order.cooperateNum}</td>
                   	<td class="text-center">${order.periodNum }</td>
					<td class="text-center">${order.userName}</td>
                   	<td class="text-center">${order.phone}</td>
                   	<td class="text-center">${order.orderDate}</td>
                   	<td class="text-center">${order.orderTime}</td>
                   	<td class="text-center">${order.finalFee}</td>
                   	<c:if test="${order.payType==1 }">
						<td>支付宝</td>
					</c:if>
					<c:if test="${order.payType==2 }">
						<td>微信</td>
					</c:if>
					<c:if test="${order.payType=='3' }">
						<td>钱包支付</td>
					</c:if>
					<c:if test="${order.payType==null}">
						<td></td>
					</c:if>
                   	<td class="text-center">
                   		<c:choose>
                    		<c:when test="${order.status == 'ORDER_BILLED'}">交易成功</c:when>
                    		<c:when test="${order.status == 'ORDER_PAIED'}">已支付</c:when>
                    		<c:when test="${order.status == 'ORDER_REFUNDED'}">已退款</c:when>
                    		<c:when test="${order.status == 'ORDER_REFUNDING' }">退款中</c:when>
                    		<c:when test="${order.status == 'ORDER_CANCELED' }">已取消</c:when>
                    	</c:choose>
                   	</td>
                    <td>
						<c:choose>
							<c:when test="${order.ordersType == '0'}">课程</c:when>
							<c:when test="${order.ordersType == '1'}">活动</c:when>
							<c:when test="${order.ordersType == '2'}">会员卡充值</c:when>
						</c:choose>				
					</td>
                   	<td class="text-center">${order.reason}</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
						</div>
				</div>
				<form id="orderVerify" type="hidden">
				</form>
				<tags:pagination page="${data}" />
				<tags:errors />
			</div>
	</div>
			<!-- 导出execl -->
			<form id="actionForm" action="${ctx}/order/export" method="post">
       			<input type="hidden" id="ids" name="search_IN_id">
    		</form>
			<script type="text/javascript">
			function getSelected(){
				var ids =[];
				var checked = $('input:checked');
				if(checked.length){
					checked.each(function(){
						if($(this).attr("name")=='export'){
							ids.push($(this).val());
						}
					})
				}
				return ids;
			}
			
			function exportOrder(){
				var ids = getSelected();
				if(ids.length==0){
					var $form = $("#orderForm");
					$form.attr("action","${ctx}/order/exportQueryData");
					$form.attr("method","post");
					$form[0].submit();
					<!--提交后清除action缓存要不缓存使action一直走导出excel方法-->
					$form.attr("action","");
				}
				else{
					var $form = $("#actionForm");
					var ids = ids.join(";");
					$form.attr("action","${ctx}/order/export?ids="+ids);
					$form[0].submit();
				}
			}
			 $(function(){
				 menu.active($("#orderList-man"));
				var v_search_EQ_ordersType='${param.search_EQ_ordersType}';
				if(v_search_EQ_ordersType){
					$("#search_EQ_ordersType option[value="+v_search_EQ_ordersType+"]").attr("selected","selected");
				}
				var v_search_EQ_status='${param.search_EQ_status}';
				if(v_search_EQ_status){
					$("#search_EQ_status option[value="+v_search_EQ_status+"]").attr("selected","selected");
				}
				 var v_search_EQ_verifyFlag='${param.search_EQ_verifyFlag}';
				if(v_search_EQ_verifyFlag){
					$("#search_EQ_verifyFlag option[value="+v_search_EQ_verifyFlag+"]").attr("selected","selected");
				} 
				$("button[type='reset']").click(function(){
					$(this).closest("form").find("input").attr("value", "");
					$(this).closest("form").find("select option:selected").attr("selected", false);
					$(this).closest("form").find("select option:first").attr("selected",true);
				});
				
			});
				  $("#ckAll").click(function(){
					  var ckExport = $("input[name='export']");
					  if(this.checked){
						  for(var i=0;i<ckExport.length;i++){
							  ckExport[i].checked=true;
						  }
					  }
					  else{
						  for(var i=0;i<ckExport.length;i++){
							  ckExport[i].checked=false;
						  }
					  }
				  })
			</script>
</body>
</html>