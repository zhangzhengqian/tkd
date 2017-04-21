<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
							<li class="active">订单列表</li>
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
						action="${ctx }/order/list" method="post" name="id">
						<input type="hidden" id="search_EQ_status" name="search_EQ_status" />
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
									<option value="2">--会员卡充值--</option>
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
						<a class="btn btn-sm btn-default"
								href="javascript:batchVerify('')">批量确认</a>
					</div>
				
					<div class="btn-group-sm pull-right mtb10">

						<div class="btn-group" role="group" aria-label="...">
							<a class="btn btn-sm btn-default"
								href="javascript:statusClick('')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								全部</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_BILLED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								交易成功</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_CANCELED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								交易关闭</a> <a class="btn btn-sm btn-warning"
								href="javascript:statusClick('ORDER_REFUNDING')"><span
								class="glyphicon glyphicon-search " aria-hidden="true"></span>&nbsp;
								退款中</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_REFUNDED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已退款</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_NEW')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								待付款</a> <a class="btn btn-sm btn-success"
								href="javascript:statusClick('ORDER_PAIED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已付款</a> <a class="btn btn-sm btn-info"
								href="javascript:statusClick('ORDER_VERIFY')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已确认</a>
						</div>
					</div>
					<div class="row">
						<div class="col-table col-md-12">
							<table id="contentTable" class="table table-bordered table-condensed table-hover">
							<thead class="thead">
								<tr>
									<th class="text-center"><input type="checkbox" name="chk_all" 
									onclick="if(this.checked==true){checkAll('chk_list');}else{clearAll('chk_list');}"/></th>
								<th>订单号</th>
								<th>场馆</th>
								<th>教练</th>
								<th>用户电话</th>
								<th>地区</th>
								<th>预约日期</th>
								<th>预约时间</th>
								<th>实付(元)</th>
								<th>下单时间</th>
								<th>订单状态</th>
								<th>订单来源</th>
								<th>支付方式</th>
								<th>处理人</th>
								<th>处理状态</th>
								<th>类型</th>
								<th style="width:50px">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${data.content}" var="order" varStatus="stat">
								<c:set var="order_class" value=""></c:set>
								<c:if test="${order.status=='ORDER_NEW' }">
								<c:set var="order_class" value=""></c:set>
								</c:if>
								<c:if test="${order.status=='ORDER_VERIFY' }">
								<c:set var="order_class" value="info"></c:set>
								</c:if>
								<c:if test="${order.status=='ORDER_PAIED' }">
								<c:set var="order_class" value="success"></c:set>
								</c:if>
								<c:if test="${order.status=='ORDER_REFUNDING' }">
								<c:set var="order_class" value="warning"></c:set>
								</c:if>
								<tr id="${order.id}" class="${order_class }">
									<td class="text-center">
									<input type="checkbox"
								name="export" id="export" value="${order.id}" />
								</td>
									<td><a title="查看详情" href="${ctx }/order/view/${order.id}">${order.id}</a></td>
									<td <c:if test="${order.ordersType == '0'}"></c:if>  style="cursor: pointer;" >
											${order.statiumName}								
									</td>
									<td>${order.coachName }</td>
									<td>${order.phone }</td>
									<td>
											${order.areaStr}									
									</td>
									<td>${order.orderDate }</td>
									<td>${order.orderTime }</td>
									<td>${order.finalFee }</td>
									<td><fmt:formatDate value="${order.ct}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<c:choose>
										<c:when test="${order.status == 'ORDER_NEW'}">
											<td class="warning">待付款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_VERIFY'}">
											<td class="info">已确认</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_BILLED' || order.status == 'ORDER_SIGN'}">
											<td class="">交易成功</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_PAIED'}">
											<td class="success">已付款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_REFUNDING'}">
											<td class="">退款中</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_REFUNDED'}">
											<td class="">已退款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_CANCELED' || order.status == ' ' || order.status == ''}">
											<td class="">交易关闭</td>
										</c:when>
									</c:choose>
									<td>APP</td>
									<c:if test="${order.payType==1 }">
										<td>支付宝</td>
									</c:if>
									<c:if test="${order.payType==2 }">
										<td>微信</td>
									</c:if>
									<c:if test="${order.payType==3}">
										<td>会员卡支付</td>
									</c:if>
									<c:if test="${order.payType==null}">
										<td></td>
									</c:if>
									<c:choose>
									<c:when test="${order.handleName==null }">
										<td class=""></td>
									</c:when>
									<c:when test="${order.handleName!='' }">
										<td>${order.handleName}</td>
									</c:when>
									</c:choose>
									<c:choose>
									<c:when test="${order.handleStatusStr==null }">
										<td class=""></td>
									</c:when>
									<c:when test="${order.handleStatusStr!='' }">
										<td>${order.handleStatusStr}</td>
									</c:when>
									</c:choose>
									<td>
										<c:choose>
											<c:when test="${order.ordersType == '0'}">课程</c:when>
											<c:when test="${order.ordersType == '1'}">活动</c:when>
											<c:when test="${order.ordersType == '2'}">会员卡充值</c:when>
										</c:choose>				
									</td>
									<td id="apply">
									<shiro:hasPermission name="order:view">
									<a href="${ctx }/order/view/${order.id}"class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 详情</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="order:applyRefeund">
									<c:if test="${(order.ordersType == '0' || order.ordersType == '1') && (order.status=='ORDER_PAIED'||order.status=='ORDER_VERIFY')}">
										<a href="javascript:backForm(${order.id })"class="btn btn-default btn-sm" ><span class="glyphicon glyphicon-check"></span>申请退款</a>
									</c:if>
									</shiro:hasPermission>
									</td>		 
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
			<div class="modal fade" id="backorderModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">退款原因</h4>
						</div>
						<div class="modal-body" class="">
							<form class="form-inline" method="POST" id="backorderForm">
								<div id="backtextDiv" class="form-group">
									<label>原因：</label> <input class="form-control input-sm"
										id="reason" type="text" name="reason" />
								</div>
								<input type="hidden" id="backOrderDetail" value=""
									name="orderId"> <input type="hidden"
									value="ORDER_REFUNDING" name="status">
							</form>
						</div>
						<div class="modal-footer">
							<a href="javascript:;" class="btn" data-dismiss="modal"
								aria-hidden="true">取消</a> <a href="javascript:;"
								class="btn btn-primary alert-to-ok" onClick="orderVerify(${order.id})"
								data-dismiss="modal" aria-hidden="true">确定</a>
						</div>
					</div>
				</div>
			</div>
			<!-- 导出execl -->
			<form id="actionForm" action="${ctx}/order/export" method="post">
       			<input type="hidden" id="ids" name="search_IN_id">
    		</form>
			<script type="text/javascript">
			
			function statusClick(v){
				$("#search_EQ_status").attr("value",v);
				$("#orderForm").submit();
			}
			//弹出申请退款框
			function applyRefund(v){
				$("#backOrderDetail").val(v);
				$("#backorderModel").modal();
			}
		 	function orderVerify(id){
		 		if(!$("#reason").val()){
					bootbox.alert('请填写退款原因！');
					return false;
				}
				var id = $("#backOrderDetail").val();
				var reason = $("#reason").val();
				var $form=$("#backorderForm");
				$form.attr("action","${ctx}/order/applyRefund");
				bootbox.confirm("您确定要申请退款吗",function(result){
					if(result){
						$form[0].submit();
					}
				});
			}
		 	//点击申请退款按钮 ajax请求后台判断是否可以退款
			function backForm(id){
				$.ajax({
					cache:true,
					type:"post",
					url:"${ctx}/order/isRefundedOk",
					data:{
						"id":id,
					},
					error : function(request) {
						common.showMessage("申请退款失败！");
					},
					success:function(data){
						var data = eval("("+data+")");
						if(!data.result||data.result=='false'){
							common.showMessage(data.reason);
						}
						else{
							applyRefund(id);
						}
					}
				});
				
				  
			}
			 $(function(){
				menu.active($("#order-man"));
				var v_search_EQ_ordersType='${param.search_EQ_ordersType}';
				if(v_search_EQ_ordersType){
					$("#search_EQ_ordersType option[value="+v_search_EQ_ordersType+"]").attr("selected","selected");
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
			</script>
</body>
</html>