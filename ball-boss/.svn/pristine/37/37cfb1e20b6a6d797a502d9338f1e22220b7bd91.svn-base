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
							<li class="active">订单列表</li>
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
						action="${ctx }/orders/list" method="post">
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
									<option value="">--请选择订单类型--</option>
									<option value="0">--订场订单--</option>
									<option value="1">--教陪练订单--</option>
									<option value="2">--活动订单--</option>
									<option value="4">--赛事订单--</option>
									<option value="6">--乐享赛事订单--</option>
									<option value="7">--白金赛订单--</option>
								</select>
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
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
								<select class="form-control" id="search_EQ_verifyFlag"
									name="search_EQ_verifyFlag">
									<option value="">--是否确认--</option>
									<option value="1">--未确认--</option>
								</select>
							</div>
						</div>

						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-2">
								<input type="text" class="form-control"
									id="search_EQ_userPhone" name="search_EQ_userPhone"
									value="${param.search_EQ_userPhone }" placeholder="按联系电话查询">
							</div>
							<div class="col-md-2">
								<input  type="text" id="coachName"
									class="form-control" 
									name="search_EQ_coachName"
									value="${param.search_EQ_coachName }" placeholder="按教练名称查询">
							</div>
							<div class="col-md-2">
								<input type="text" id="activityName"
									class="form-control" 
									name="search_EQ_activityName"
									value="${param.search_EQ_activityName }" placeholder="按活动名称查询">
							</div>
							<div class="col-md-2">
								<input type="text" id="gameName"
									class="form-control" 
									name="search_EQ_gameName"
									value="${param.search_EQ_gameName }" placeholder="按赛事名称查询">
							</div>
						</div>
						<div class="form-group form-group-sm form-inline">
							<div class="col-md-1">
							</div>
				         	<div class="col-md-5">
				         		<div class="form-inline">
						        	<tags:zone name="search_LIKE_areaCode" value="${param.search_LIKE_areaCode}" id="LIKE_areaCode" />
						        	<input type="text" class="form-control"  name="search_EQ_handleMan" value="${param.search_EQ_handleMan }" placeholder="订单处理人">
				         		</div>
					       	</div>
							<div class="col-md-2">
								<select class="form-control" id="search_EQ_channel"
										name="search_EQ_channel">
									<option value="">--订单来源--</option>
									<option value="0" <c:if test="${param.search_EQ_channel == 0}">selected</c:if> >--app--</option>
									<option value="4"  <c:if test="${param.search_EQ_channel == 4}">selected</c:if> >--糯米--</option>
									<option value="1"  <c:if test="${param.search_EQ_channel == 1}">selected</c:if> >--web--</option>
									<option value="2" <c:if test="${param.search_EQ_channel == 2}">selected</c:if> >--微信--</option>
									<option value="3" <c:if test="${param.search_EQ_channel == 3}">selected</c:if> >--线下--</option>
								</select>
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
								<button type="submit" class="btn btn-primary btn-sm">
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
			</div>
			<!-- /操作按钮组 -->


			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center"><input type="checkbox" name="chk_all"
							onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
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
								<th>订单来源</th>
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
									<td class="text-center">
									<c:if test="${order.status == 'ORDER_PAIED' && (order.ordersType == '0'||order.ordersType == '1')}">
									<input type="checkbox"
								name="chk_list" id="chk_list_${stat.index }" value="${order.id}" />
									</c:if>
								</td>
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
										<c:if test="${order.channel == 0}">
											app
										</c:if>
										<c:if test="${order.channel == 1}">
											web
										</c:if>
										<c:if test="${order.channel == 2}">
											微信
										</c:if>
										<c:if test="${order.channel == 3}">
											线下
										</c:if>
										<c:if test="${order.channel == 4}">
											糯米
										</c:if>
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
											<%--
											订单类型：场馆:0  教/陪练:1 活动:2
											--%>
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
									<a href="${ctx }/orders/view/${order.id}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 详情</a> 
									<c:if test="${(order.status == 'ORDER_PAIED'||order.verifyFlag!=null) && (order.ordersType == '0'||order.ordersType == '1') && (order.channel != 4)}">
									<a href="${ctx }/orders/orderVerify/${order.id}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 确认</a>
									</c:if>
									<c:if test="${(order.status == 'ORDER_PAIED'||order.status == 'ORDER_PLAYING'||order.status == 'ORDER_VERIFY') && (order.ordersType == '0')  && (order.channel != 4)}">
									<a href="${ctx }/orders/update/${order.id}"
										class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span> 修改</a>
									</c:if>
										<shiro:hasPermission name="orders:applyRefund">
											<c:if
												test="${(order.status == 'ORDER_PAIED'||order.status == 'ORDER_VERIFY'||order.status == 'ORDER_PLAYING')  && (order.channel != 4)}">
												<a href="javascript:applyRefund('${order.id}')"
													class="btn btn-default btn-sm"><span
													class="glyphicon glyphicon-check"></span> 申请退款</a>
											</c:if>
										</shiro:hasPermission> </td>
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
						<input type="hidden" id="backOrderDetail" value="" name="orderId">
						<input type="hidden" value="ORDER_REFUNDING" name="status">
					</form>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="backForm()"
						data-dismiss="modal" aria-hidden="true">确定</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function confirmRefund(v) {
			var num = 0;
			bootbox.confirm('是否确认退款' + v + '订单?', function(result) {
				if (result && !num) {
					num++;
					$.ajax({
						cache : true,
						type : "POST",
						url : '${ctx}/orders/confirmRefund/' + v,
						data : {},
						async : false,
						error : function(request) {
							common.showMessage("确认退款失败！");
						},
						success : function(data) {
							data = eval("(" + data + ")");
							if (!data.result || data.result == 'false') {
								if (!data.reason) {
									common.showMessage("确认退款失败！");
								} else {
									common.showMessage(data.reason);
								}

							} else {
								common.showMessage("确认退款成功！");
								setTimeout(function() {
									var $form = $('#orderForm');
									$form[0].submit();
								}, 1000);
							}
						}
					});
				}
			});
			return false;
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
		
		function batchVerify(){
			var ids = getSelected();
			if(ids.length==0){
				return;
			}
			bootbox.confirm('是否确认订单?', function(result) {
				if (result){
					ids = ids.join(',');
					window.location.href = "${ctx}/orders/orderVerify/" + ids;
				}
			});
		}

		function orderVerify(v) {
			var num = 0;
			bootbox.confirm('是否确认' + v + '订单?', function(result) {
				if (result && !num) {
					num++;
					window.location.href = "${ctx}/orders/orderVerify/" + v;
				}
			});
		}

		function applyRefund(v) {
			$("#backOrderDetail").val(v);
			$("#backorderModel").modal();
		};

		function backForm() {
			if (!$("#reason1").val() && !$("#reason").val()) {
				bootbox.alert('请填写退款原因！');
				return false;
			}
			$("#backorderForm").attr("action", "${ctx}/orders/applyRefund");
			$("#backorderForm").submit();
		};

		$(function() {
			menu.active('#orders-man');
			/*
			var v_search_EQ_status = '${param.search_EQ_status}';
			if(v_search_EQ_status){
			  $('input[value='+v_search_EQ_status+']').parent().addClass("active");
			}
			
			var v_search_IN_status = '${param.search_IN_status}';
			if(v_search_IN_status){
			  var sp = v_search_IN_status.split(",");
			  $.each(sp,function(n,value) {  
				  $('input[value='+value+']').parent().addClass("active");
			      }); 
			}
			 */
			var v_search_EQ_ordersType = '${param.search_EQ_ordersType}';
			if (v_search_EQ_ordersType) {
				$(
						'#search_EQ_ordersType option[value='
								+ v_search_EQ_ordersType + ']').attr(
						'selected', 'selected');
			}

			$("button[type=reset]")
					.click(
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

			/*
			 $("button[name=btn_status]").click(function(){
			  $(this).toggleClass("active");
			});
			
				 $("button[type=submit]").click(function(){
			  var s = $("input[name=statusStr]:checked");
			  if(s.length != 0){
				  if(s.length == 1){
					  $("input[id=status]").attr("name","search_EQ_status");
				  }else{
					  $("input[id=status]").attr("name","search_IN_status");
				  }
				  var vs = new Array();
				  $(s).each(function(){
					 vs.push($(this).val()); 
				  });
				  $("input[id=status]").val(vs.toString());
			  }
			});
			 */
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

		function statusClick(v) {
			$("#search_EQ_status").attr("value", v);
			$("#orderForm").submit();
		}
		
		function queryStatiumOrders(statiumId,orderType,name){
			console.log(statiumId);
			console.log(orderType);
			console.log(name);
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
