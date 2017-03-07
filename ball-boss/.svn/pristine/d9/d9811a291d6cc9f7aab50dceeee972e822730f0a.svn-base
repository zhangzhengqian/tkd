<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
							<li class="active">糯米订单</li>
						</ul>
					</div>
				</div>
				<div class="col-md-7"></div>
			</div>

		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->

			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="orderForm" class="form-horizontal"
						action="${ctx }/orders/nuomiList" method="post">
						<input type="hidden" id="search_EQ_status" name="search_EQ_status" />
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_id" name="search_LIKE_id"
									value="${param.search_LIKE_id }" placeholder="按订单号查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control"
									   id="search_EQ_userPhone" name="search_EQ_userPhone"
									   value="${param.search_EQ_userPhone }" placeholder="按联系电话查询">
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only"></label>
							<div class="col-md-5">
								<input id="statiumName" type="text"
									class="form-control" 
									name="statiumName"
									value="${param.statiumName }" placeholder="按球馆名称查询">
								<input id="statiumId" type="hidden"
									class="form-control" 
									name="search_EQ_statiumId"
									value="${param.search_EQ_statiumId }" placeholder="按球馆名称查询">
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_externalStatus"
										name="search_EQ_verifyFlag">
									<option value="">--是否确认--</option>
									<option value="1">--未确认--</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm form-inline">
							<div class="col-md-1">
							</div>
				         	<div class="col-md-7">
				         		<div class="form-inline" style=" ">
						        	<tags:zone name="search_LIKE_areaCode" value="${param.search_LIKE_areaCode}" id="LIKE_areaCode" />
						        	<input type="text" class="form-control"  name="search_EQ_handleMan" value="${param.search_EQ_handleMan }" placeholder="订单处理人">
				         		</div>
					       	</div>
						</div>
						<div class="form-group form-group-sm form-inline">
							<label class="control-label col-md-1 sr-only"></label>
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
						<div class="form-group form-group-sm form-inline">
							<label class="control-label col-md-1 sr-only"></label>
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
								<button type="submit" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="exportData()" class="btn btn-success btn-sm">
									<span class="glyphicon glyphicon-export"></span> 导出
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
					<div class="col-md-5 form-inline">
						<div class="btn-group" role="group" aria-label="...">
							<button value=""  type="button" class="searchExternalStatus btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;全部状态</button>
							<button value="1" type="button" class="searchExternalStatus btn btn-warning btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;待审核</button>
							<button value="3" type="button" class="searchExternalStatus btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核通过</button>
							<button value="4" type="button" class="searchExternalStatus btn btn-info btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核拒绝</button>
						</div>
					</div>

					<div class="col-md-7 text-right">

						<div class="btn-group" role="group" aria-label="...">

							<a class="btn btn-sm btn-default"
								href="javascript:statusClick('')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								全部</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_PLAYING')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								交易成功</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_CANCELED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								交易关闭</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_REFUNDING')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								退款中</a> <a class="btn btn-sm btn-default"
								href="javascript:statusClick('ORDER_REFUNDED')"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;
								已退款</a> <a class="btn btn-sm btn-warning"
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
				</div>
			<!-- /操作按钮组 -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>订单号</th>
								<th>场馆</th>
								<th>地区</th>
								<th>联系电话</th>
								<th>预约日期</th>
								<th>预约时间</th>
								<th>实付(元)</th>
								<th>成本(元)</th>
								<th>补贴金额(元)</th>
								<th>下单时间</th>
								<th>订单状态</th>
								<th>品类</th>
								<th>处理人</th>
								<th>处理状态</th>
								<th>处理结果</th>
								<th>类型</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.content}" var="order" varStatus="stat">
								<tr <c:if test="${order.verifyFlag=='1'}">class="warning"</c:if> id="${order.id}" >
									<td><a title="查看详情" href="${ctx }/orders/view/${order.id}">${order.id}</a></td>
									<td <c:if test="${order.ordersType == '0'}"> onmouseover="showPhone('${order.userPhone}',this)"</c:if>  style="cursor: pointer;" onclick="queryStatiumOrders('${order.statiumId}','${order.ordersType}','${order.name}')" >
											${order.name}								
									</td>
									<td>
											${order.areaStr}									
									</td>
									<td onmouseover="showPhone('${order.phone}',this)">${order.phone }</td>
									<td>${order.orderDate }</td>
									<td>${order.orderTime }</td>
									<td>${order.finalFee+order.qiuyouFee}</td>
									<td>${lf:totalCostPrice(order.id) }</td>
									<td>${lf:totalSubsidies(order.id) }</td>
									<td><fmt:formatDate value="${order.ct}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<c:choose>
										<c:when test="${order.status == 'ORDER_NEW'}">
											<td class="warning">待付款</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_VERIFY'}">
											<td class="info">已确认</td>
										</c:when>
										<c:when test="${order.status == 'ORDER_PLAYING'}">
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
										<c:when test="${order.status == 'ORDER_CANCELED'}">
											<td class="">交易关闭</td>
										</c:when>
									</c:choose>
									</td>
									<td>
											${order.sportType}									
									</td>
									<td>
											${order.handleName}									
									</td>
									<td>
											${order.handleStatusStr}									
									</td>
									<td>
											${order.reason}									
									</td>
									<td>
										<c:choose>
											<c:when test="${order.ordersType == '0'}">订场</c:when>
											<c:when test="${order.ordersType == '1'}">教/陪练</c:when>
											<c:when test="${order.ordersType == '2'}">活动</c:when>
											<c:when test="${order.ordersType == '4'}">赛事</c:when>
											<c:when test="${order.ordersType == '6'}">乐享赛事</c:when>
											<c:when test="${order.ordersType == '7'}">白金赛</c:when>
										</c:choose>
									</td>
									<td>
										<a href="${ctx }/orders/nuomiView/${order.id}"
											class="btn btn-default btn-sm"><span
												class="glyphicon glyphicon-search"></span> 详情</a>
										<c:if test="${order.externalStatus == 2}">
											<a href="#" onclick="nuomiOrderAudiReason('${order.id}',3)"
												class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-search"></span> 通过</a>
											<a href="#" onclick="nuomiOrderAudiReason('${order.id}',4)"
												   class="btn btn-default btn-sm"><span
														class="glyphicon glyphicon-search"></span> 不通过</a>
										</c:if>
										<c:if test="${order.externalStatus == 1}">
											<a href="#" onclick="applyRefund('${order.id}')"
											   class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-search"></span>申请退款</a>
										</c:if>
										<c:if test="${order.status == 'ORDER_PAIED'}">
											<a href="${ctx }/orders/nuomiVerify/${order.id}"
											   class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-search"></span> 确认</a>
									    </c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<tags:pagination page="${data}" />
			<tags:errors />
			<form id="actionForm" action="" method="post">
				<input type="hidden" id="ids" name="search_IN_id">
			</form>

		</div>
		<!-- /右侧主体内容 -->

	</div>

	<div class="modal fade" id="backorderModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">退款原因</h4>
				</div>
				<div class="modal-body" class="">
					<form class="form-inline" method="POST" id="backorderForm">
						<div id="backreason" class="form-group">
							<label>原因：</label> <select id="reason1"
								class="form-control input-sm" name="reason1">
								<option value="">请选择</option>
								<option value="用户要求退款">用户要求退款</option>
								<option value="场地被占用">场地被占用</option>
							</select>
						</div>
						<div id="backtextDiv" class="form-group">
							<label>其他原因：</label> <input class="form-control input-sm"
								id="reason" type="text" name="reason" />
						</div>
						<input type="hidden" id="backOrderDetail"  name="orderId">
						<input type="hidden" value="ORDER_REFUNDING" name="status">
					</form>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="nuomiOrderCancel()"
						data-dismiss="modal" aria-hidden="true">确定</a>
				</div>
			</div>
		</div>
	  </div>
 	</div>

	<div class="modal fade" id="audiOrderModel" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					<h4 class="modal-title">审核不通过原因</h4>
				</div>
				<div class="modal-body" class="">
					<form class="form-inline" method="POST" id="backorderForm">
						<div id="backtextDiv" class="form-group">
							<label>不通过原因：</label>
							<input class="form-control input-sm" id="audiReason" type="text" name="audiReason" />
						</div>
						<input type="hidden" id="audiId"   name="audiId">
						<input type="hidden" id="audiStatus"   name="audiStatus">
					</form>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"  aria-hidden="true">取消</a>
					<a href="javascript:;" class="btn btn-primary alert-to-ok" onClick="nuomiOrderAudi()" data-dismiss="modal" aria-hidden="true">确定</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
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

		$(function() {
			menu.active('#nuomiorders-man');
			/* 按状态查询 */
			$(".searchExternalStatus").click(function () {
				var v = $(this).val();
				$("#search_EQ_externalStatus").val(v);
				$("#search_form").submit();
			});
			$("button[type=reset]").click(
							function() {
								$(this).closest("form").find("input").attr(
										"value", "");
								$(this).closest("form").find(
										"select option:selected").attr(
										"selected", false);
								$(this).closest("form").find(
										"select option:first").attr("selected",
										true);
							});
			$('#adminFooter').hide();
			
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

		//打开退款愿意录入页
		function applyRefund(v) {
			$("#backOrderDetail").val(v);
			$("#backorderModel").modal();
		};

		//调用糯米取消订单接口
		function nuomiOrderCancel() {
			if (!$("#reason1").val() && !$("#reason").val()) {
				bootbox.alert('请填写退款原因！');
				return false;
			}
			var orderId = $("#backOrderDetail").val();
			bootbox.confirm('是否确认退款' + orderId + '订单?', function(result) {
				Util.getData(ctx + '/statium/nuomiOrderCancel', function (data) {
					if (data.result) {
						myAlert(data.reason);
						window.location.reload();
					} else {
						myAlert(data.reason, "error");
						return false;
					}
				}, null, {"orderId":orderId,"reason":$("#reason").val(),"reason1":$("#reason1").val()}, 'get');
			});
			return false;
		};

		//调用糯米取消订单接口
		function nuomiOrderAudiReason(orderId,status) {
			$("#audiId").val(orderId);
			$("#audiStatus").val(status);
			if(status == 4){
				$("#audiOrderModel").modal();
			}else{
				nuomiOrderAudi(orderId,status);
			}

		};
		function nuomiOrderAudi(orderId,status) {
			if (!$("#audiReason").val() && status == 4) {
				bootbox.alert('请填写不通过原因！');
				return false;
			}
			var orderIdV = $("#audiId").val();
			var statusV = $("#audiStatus").val();
			var reasonV = $("#audiReason").val();
			if(status == 3){
				orderIdV = orderId;
				statusV = status;
			}
			var tips = "是否确认不通过"+orderIdV+"订单?";
			if(status == 3){
				tips = "是否确认通过"+orderIdV+"订单?";
			}
			bootbox.confirm(tips, function(result) {
				Util.getData(ctx + '/statium/nuomiOrderAudi', function (data) {
					if (data.result) {
						myAlert(data.reason);
						window.location.reload()
					} else {
						myAlert(data.reason, "error");
						return false;
					}
				}, null, {"orderId":orderIdV,"reason":reasonV,"status":statusV}, 'get');
			});
			return false;
		};

		function exportData() {
			var $form = $('#orderForm');
			$form.attr("action","${ctx }/orders/nuomiexport");
			$form[0].submit();
		}

		function statusClick(v) {
			$("#search_EQ_status").attr("value", v);
			$("#orderForm").submit();
		}
		
		function queryStatiumOrders(statiumId,orderType,name){
			if(orderType=='0'){
				$("#statiumId").val(statiumId);
			}else if(orderType=='1'){
				$("#coachName").val(name);
			}else if(orderType=='2'){
				$("#activityName").val(name);
			}
			else if(orderType=='4'){
				$("#gameName").val(name);
			}
			$("#orderForm").submit();
		}
		
		function showPhone(phone,obj){
			var scrollHeight = $(document).scrollTop();
			var point = $(obj).offset();
			var width = $(obj).outerWidth();
			var height = $(obj).outerHeight();
			$.bootstrapGrowl('<div class="form-inline"><span>'+phone+'</span><a href="javascript:callPhone(\''+phone+'\')"  class="btn btn-primary btn-sm pull-right">呼叫</a></div>', {
				position:'absolute',
	            type: 'info',
	            align: (point.left+width)+','+(point.top-scrollHeight),
	            stackup_spacing: 10
	        });
		}
		
		function showPhone(phone,obj){
			var scrollHeight = $(document).scrollTop();
			var point = $(obj).offset();
			var width = $(obj).outerWidth();
			var height = $(obj).outerHeight();
			$.bootstrapGrowl('<div class="form-inline"><span>'+phone+'</span><a  href="javascript:callPhone(\''+phone+'\')"   class="btn btn-primary btn-sm pull-right">呼叫</a></div>', {
				position:'absolute',
	            type: 'info',
	            align: (point.left+width)+','+(point.top-scrollHeight),
	            stackup_spacing: 10
	        });
		}
		
	</script>

</body>
</html>
