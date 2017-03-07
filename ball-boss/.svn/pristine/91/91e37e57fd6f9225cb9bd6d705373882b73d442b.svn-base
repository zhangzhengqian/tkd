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
							<li class="active">乐享赛订单</li>
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
						action="${ctx }/orders/enjoylist" method="post">
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="startTime" name="startTime"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}'})"
									value="${sercher.startTime}" placeholder="开始时间">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="endTime" name="endTime"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startTime\')}'})"
									value="${sercher.endTime}" placeholder="结束时间">
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="ct" name="ct"
									onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd'})"
									value="${sercher.ct}" placeholder="报名时间">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="orderId" name="orderId"
									value="${sercher.orderId }" placeholder="订单号">
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<select class="form-control" id="gameType"
									name="gameType">
									<option value="">--报名类型--</option>
									<option value="1">--男子单打--</option>
									<option value="2">--女子单打--</option>
									<option value="3">--男子双打--</option>
									<option value="4">--女子双打--</option>
									<option value="5">--混合双打--</option>
									<option value="6">--混合单打--</option>
								</select>
							</div>
							<div class="col-md-5">
								<select class="form-control" id="gameLevel"
									name="gameLevel">
									<option value="">--报名种类--</option>
									<option value="1">--乐享一级赛--</option>
									<option value="2">--乐享二级赛--</option>
									<option value="3">--乐享三级赛--</option>
									<option value="4">--乐享四级赛--</option>
								</select>
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="name" name="name"
									value="${sercher.name}" placeholder="赛事名称">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="pName" name="pName"
									value="${sercher.pName }" placeholder="报名人姓名">
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="pCard" name="pCard"
									value="${sercher.pCard}" placeholder="报名人身份证号">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="phone" name="phone"
									value="${sercher.phone }" placeholder="报名人手机号">
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
								<input id="statiumName" type="text"
									class="form-control" 
									value="" placeholder="球馆名称">
								<input id="statiumId" type="hidden"
									class="form-control" 
									name="statiumId"
									value="${sercher.statiumId }">
							</div>
							<div class="col-md-5">
								<select class="form-control" id="city"
									name="city">
									<option <c:if test="${sercher.city==null||sercher.city=='' }">selected</c:if> value="">--请选择城市--</option>
									<c:forEach items="${siteList }" var="city">
										<option <c:if test="${sercher.city==city }">selected</c:if> value="${city }">--${city }--</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="reset" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 重 置
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="search()" class="btn btn-primary btn-sm">
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
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>订单号</th>
								<th>场馆</th>
								<th>地区</th>
								<th>详细地址</th>
								<th>赛事名称</th>
								<th>联系电话</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>报名人</th>
								<th>报名时间</th>
								<th>报名次数</th>
								<th>身份证号</th>
								<th>手机号</th>
								<th>实收金额(元)</th>
								<th>账户支付(元)</th>
								<th>奖金账户支付(元)</th>
								<th>球友卡金额(元)</th>
								<th>优惠券金额(元)</th>
								<th>场地费(元)</th>
								<th>报名费(元)</th>
								<th>收入(元)</th>
								<th>报名日期</th>
								<th>报名种类</th>
								<th>报名类别</th>
								<th>奖金</th>
								<th>订单状态</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${data.content}" var="order" varStatus="stat">
								<tr>
									<td>${order.id}</td>
									<td>
											${order.name}								
									</td>
									<td>
											${order.areaStr}								
									</td>
									<td>
											${order.address}									
									</td>
									<td>
											${order.enjoyName}									
									</td>
									<td onmouseover="showPhone('${order.phone}',this)">${order.phone }</td>
									<td>
											${order.startTime}									
									</td>
									<td>
											${order.endTime}									
									</td>
									<td>
											${order.pname}									
									</td>
									<td>${order.orderDate }</td>
									<td>
											${order.pnum}									
									</td>
									<td>
											${order.pcard}									
									</td>
									<td>
											${order.pphone}									
									</td>
									<td>${order.finalFee}</td>
									<td>${order.acountFee}</td>
									<td>${order.bounsAccountFee}</td>
									<td>${order.qiuyouFee}</td>
									<td>${order.subAmount}</td>
									<td>${order.cdFee}</td>
									<td>${order.bmFee}</td>
									<td>${order.srFee}</td>
									<td>${order.pct}</td>
									<td>
										${order.gameType}
									</td>
									
									<td>
										${order.gameLevel}
									</td>
									<td>${order.gameBonus}</td>
									<td>${order.status}</td>
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
		</div>
		<!-- /右侧主体内容 -->

	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyorders-man');
			var gameType = '${sercher.gameType}';
			if (gameType) {
				$(
						'#gameType option[value='
								+ gameType + ']').attr(
						'selected', 'selected');
			}
			
			var gameLevel = '${sercher.gameLevel}';
			if (gameLevel) {
				$(
						'#gameLevel option[value='
								+ gameLevel + ']').attr(
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
		
		function exportData() {
	       	var $form = $('#orderForm');
		   	$form.attr("action","${ctx }/orders/enjoyexport");
		   	$form[0].submit();
	    }
	    function search(){
			var $form = $('#orderForm');
			$form.attr("action","${ctx }/orders/enjoylist");
			$form[0].submit();
		}
		
	</script>

</body>
</html>
