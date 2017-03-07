<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业服务</title>
</head>
<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li>企业服务</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/company/service/save" method="post"
			class="form-horizontal">
			<input id="id" name="id" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>服务信息</small>
					</legend>
					<div class="row">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>企业名称:</label>
							<div class="col-md-3 has-feedback">
								<input id="companyName" type="text" class="form-control" value="${info.companyName}" placeholder="公司名称">
								<input id="companyId" type="hidden" class="form-control" name="companyId" value="${info.companyId}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>服务类型:</label>
							<div class="col-md-4 form-inline">
								<select readonly="readonly" id="serviceType" name="serviceType" class="form-control">
										<option  value="0">--固定包场--</option>
										<option  value="1" selected>--活动赛事--</option>
										<option  value="2">--账户充值--</option>
										<option  value="3">--账户提现--</option>
							   </select>
							</div>
						</div>
					</div>
					<div class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>时间:</label>
							<div class="col-md-4 form-inline">
								<input type="text" class="form-control input-sm"
									id="begin_" name="begin"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',firstDayOfWeek:1,minDate:'%y-%M-%d'})"
									value="${info.begin}" placeholder="开始时间">
									
									<input type="text" class="form-control input-sm"
									id="end_" name="end"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',firstDayOfWeek:1,minDate:'#F{$dp.$D(\'begin_\')}'})"
									value="${info.end}" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div  class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>场馆:</label>
							<div class="col-md-3 has-feedback">
								<input id="statiumName_" type="text" class="form-control" name="statiumName_" value="" placeholder="场馆">
								<input id="statiumId_" type="hidden" class="form-control" name="statiumId_" value="">
							</div>
						</div>
					</div>
					<div class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>运动类型:</label>
							<div class="col-md-3 has-feedback">
								<select id="sportType_" name="sportType_" class="form-control">
									<option value="BASKETBALL" <c:if test="${info.sportType == 'BASKETBALL'}"> selected </c:if>>--篮球--</option>
									<option value="FOOTBALL" <c:if test="${info.sportType == 'FOOTBALL'}"> selected </c:if>>--足球--</option>
									<option  value="BADMINTON" <c:if test="${info.sportType == 'BADMINTON'}"> selected </c:if>>--羽毛球--</option>
									<option value="BILLIARDS" <c:if test="${info.sportType == 'BILLIARDS'}"> selected </c:if>>--台球--</option>
									<option value="BOWLING" <c:if test="${info.sportType == 'BOWLING'}"> selected </c:if>>--保龄球--</option>
									<option value="GOLF" <c:if test="${info.sportType == 'GOLF'}"> selected </c:if>>--高尔夫--</option>
									<option  value="TABLE_TENNIS" <c:if test="${info.sportType == 'TABLE_TENNIS'}"> selected </c:if>>--乒乓球--</option>
									<option value="TENNIS" <c:if test="${info.sportType == 'TENNIS'}"> selected </c:if>>--网球--</option>
							   </select>
							</div>
						</div>
					</div>
					<div    class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>支付方式:</label>
							<div class="col-md-3 has-feedback">
								<select id="payType_" name="payType_" class="form-control">		
										<option  value="0">--线上--</option>
										<option  value=1>--线下--</option>
							   </select>
							</div>
						</div>
					</div>
					<div  class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>活动费用:</label>
							<div class="col-md-3 has-feedback">
								<input id="price_" type="text" class="form-control" name="price_" value="${info.price}" placeholder="活动费用">
							</div>
						</div>
					</div>
					<div class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>活动成本:</label>
							<div class="col-md-3 has-feedback">
								<input id="costPrice_" type="text" class="form-control" name="costPrice_" value="" placeholder="活动成本">
							</div>
						</div>
					</div>
				<hr>
				<div class="row">
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red">*</span>总费用:</label>
						<div class="col-md-3 has-feedback">
							<input id="fee" type="number" class="form-control" name="fee" value="${info.price}" placeholder="总费用">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red">*</span>总成本:</label>
						<div class="col-md-3 has-feedback">
							<input id="costFee" type="number" class="form-control" name="costFee" value="" placeholder="总成本">
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/company/services"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
				<div class="col-md-3">
					<button type="submit" id="submit_btn"
						class="btn btn-primary btn-block">保存</button>
				</div>
			</div>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#company-services');
			$('#adminFooter').hide();
			// 自动匹配 场馆名称 >>>>
			//====================================================
			$('#companyName').autocomplete('${ctx}/common/search_company_by_name',{
				dataType:'json',
				minChars: 2,                   //最少输入字条
	            max: 30,
	            autoFill: false,
	            mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
	            matchContains: true, 
	            scrollHeight: 220,
	            width: $('#companyName').outerWidth()+100,
	            multiple: false,
	            formatItem: function (row, i, max) {                    //显示格式
	                return row.name;
	            },
	            formatResult: function (row) {                      //返回结果
	                return row.name;
	            },
	            handleValue:function(id){
	            	$('#companyId').val(id);
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
			// 自动匹配 场馆名称 >>>>
			//====================================================
			$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
				dataType:'json',
				minChars: 2,                   //最少输入字条
	            max: 30,
	            autoFill: false,
	            mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
	            matchContains: true, 
	            scrollHeight: 220,
	            width: $('#statiumName').outerWidth()+200,
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
			// 自动匹配 场馆名称 >>>>
			//====================================================
			$('#statiumName_').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
				dataType:'json',
				minChars: 2,                   //最少输入字条
	            max: 30,
	            autoFill: false,
	            mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
	            matchContains: true, 
	            scrollHeight: 220,
	            width: $('#statiumName').outerWidth()+200,
	            multiple: false,
	            formatItem: function (row, i, max) {                    //显示格式
	                return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
	            },
	            formatResult: function (row) {                      //返回结果
	                return row.name;
	            },
	            handleValue:function(id){
	            	$('#statiumId_').val(id);
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

			$("#submit_btn").on("click",function(){
				var companyId = $("#companyId").val();
				if(companyId==''){
					bootbox.alert('请选择要服务的公司！');
					return false;
				}
				var serviceType = $("#serviceType").val();
				if(serviceType==1){
					var begin = $("#begin_").val();
					if(begin==''){
						bootbox.alert('请填开始时间！');
						return false;
					}
					var end = $("#end_").val();
					if(end==''){
						bootbox.alert('请填写结束时间！');
						return false;
					}
					var statiumId = $("#statiumId_").val();
					if(statiumId==''){
						bootbox.alert('请选择场馆！');
						return false;
					}
					var price_ = $("#price_").val();
					if(price_==''){
						bootbox.alert('请填写活动费用！');
						return false;
					}
					var costPrice_ = $("#costPrice_").val();
					if(costPrice_==''){
						bootbox.alert('请填写活动成本！');
						return false;
					}
					var addWrongFLag = true;
					var addProject = [];


					if(!addWrongFLag){
						return false;
					}
					var fee = $("#fee").val();
					if(fee==''){
						bootbox.alert('请填写总费用！');
						return false;
					}
					var costFee = $("#costFee").val();
					if(costFee==''){
						bootbox.alert('请填写总成本！');
						return false;
					}
					var payType = $("#payType_").val();
					var sportType = $("#sportType_").val();
					var submit_obj = {"costPrice":costPrice_*100,"price":price_*100,"statiumId":statiumId,"payType":payType,"sportType":sportType,"serviceType":serviceType,"fee":fee*100,"costFee":costFee*100,"companyId":companyId,"begin":begin,"end":end,"addProjects":addProject};
				}
				console.log(submit_obj);
				$.ajax({
					cache : true,
					type : "POST",
					url : '${ctx}/company/service/save',
					data : JSON.stringify(submit_obj),
					contentType: "application/json;charset=utf-8",
					async : false,
					error : function(request) {
						
					},
					success : function(data) {
						data = eval('('+data+')');
						if(data["result"]=="success"){
							window.location.href="${ctx}/company/services";
						}else{
							bootbox.alert(data["reason"]);
						}
					}
				});
				return false;
			})
		});
		function clean_project(obj){
			$(obj).parent().parent().parent().parent().remove();
		}
	</script>
</body>
</html>
