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
								<input id="companyName" type="text" class="form-control" value="" placeholder="公司名称">
								<input id="companyId" type="hidden" class="form-control" name="companyId" value="">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>服务类型:</label>
							<div class="col-md-4 form-inline">
								<select id="serviceType" name="serviceType" class="form-control">		
										<option  value="0">--固定包场--</option>
										<option  value="1">--活动赛事--</option>
										<option  value="2">--账户充值--</option>
										<option  value="3">--账户提现--</option>
							   </select>
							   <a class="btn btn-primary btn-sm" style="display:none" id="add_project" href="javascript:;"><span class="glyphicon glyphicon-plus"></span> 新建项目</a>
							</div>
						</div>
					</div>
					<div style="display:none" class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>时间:</label>
							<div class="col-md-4 form-inline">
								<input type="text" class="form-control input-sm"
									id="begin_" name="begin"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',firstDayOfWeek:1,minDate:'%y-%M-%d'})"
									value="" placeholder="开始时间">
									
									<input type="text" class="form-control input-sm"
									id="end_" name="end"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',firstDayOfWeek:1,minDate:'#F{$dp.$D(\'begin_\')}'})"
									value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>场馆:</label>
							<div class="col-md-3 has-feedback">
								<input id="statiumName_" type="text" class="form-control" name="statiumName_" value="" placeholder="场馆">
								<input id="statiumId_" type="hidden" class="form-control" name="statiumId_" value="">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>运动类型:</label>
							<div class="col-md-3 has-feedback">
								<select id="sportType_" name="sportType_" class="form-control">		
										<option  value="BADMINTON">--羽毛球--</option>
										<option  value="TENNIS">--网球--</option>
										<option  value="BASKETBALL">--篮球--</option>
										<option  value="TABLE_TENNIS">--乒乓球--</option>
										<option  value="BILLIARDS">--台球--</option>
										<option  value="GOLF">--高尔夫--</option>
										<option  value="BOWLING">--保龄球--</option>
										<option  value="FOOTBALL">--足球--</option>
							   </select>
							</div>
						</div>
					</div>
					<div style="display:none"  class="row activityModel">
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
					<div style="display:none"  class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>活动费用:</label>
							<div class="col-md-3 has-feedback">
								<input id="price_" type="text" class="form-control" name="price_" value="" placeholder="活动费用">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row activityModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>活动成本:</label>
							<div class="col-md-3 has-feedback">
								<input id="costPrice_" type="text" class="form-control" name="costPrice_" value="" placeholder="活动成本">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>场馆:</label>
							<div class="col-md-3 has-feedback">
								<input id="statiumName" type="text" class="form-control" name="statiumName" value="" placeholder="包场场馆">
								<input id="statiumId" type="hidden" class="form-control" name="statiumId" value="">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>运动类型:</label>
							<div class="col-md-3 has-feedback">
								<select id="sportType" name="sportType" class="form-control">		
										<option  value="BADMINTON">--羽毛球--</option>
										<option  value="TENNIS">--网球--</option>
										<option  value="BASKETBALL">--篮球--</option>
										<option  value="TABLE_TENNIS">--乒乓球--</option>
										<option  value="BILLIARDS">--台球--</option>
										<option  value="GOLF">--高尔夫--</option>
										<option  value="BOWLING">--保龄球--</option>
										<option  value="FOOTBALL">--足球--</option>
							   </select>
							</div>
						</div>
					</div>
					<div style="display:none"  class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>支付方式:</label>
							<div class="col-md-3 has-feedback">
								<select id="payType" name="payType" class="form-control">		
										<option  value="0">--线上--</option>
										<option  value=1>--线下--</option>
							   </select>
							</div>
						</div>
					</div>
					<div style="display:none"  class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>场地时租费:</label>
							<div class="col-md-3 has-feedback">
								<input id="unitPrice" type="text" class="form-control" name="unitPrice" value="" placeholder="场地时租费">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>周期:</label>
							<div class="col-md-1">
								<input type="text" class="form-control input-sm"
									id="begin" name="begin"
									onfocus="WdatePicker({firstDayOfWeek:1,minDate:'%y-%M-%d'})"
									value="" placeholder="开始日期">
							</div>
							<div class="col-md-1">
								<input type="text" class="form-control input-sm"
									id="end" name="end"
									onfocus="WdatePicker({firstDayOfWeek:1,minDate:'#F{$dp.$D(\'begin\')}'})"
									value="" placeholder="结束日期">
							</div>
							<div class="col-md-1">
								<input type="text" class="form-control input-sm"
									id="amount" name="amount"
									value="" placeholder="场地数量">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="1" class="form-control"> 周一
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="2" class="form-control"> 周二
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="3" class="form-control"> 周三
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="4" class="form-control"> 周四
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="5" class="form-control"> 周五
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="6" class="form-control"> 周六
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none" class="row bookSpaceModel">
						<div class="statium_cycle form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"></label>
							<div class="col-md-5 form-inline">
								<input type="checkbox" name="check_" value="7" class="form-control"> 周日
								<input type="number" class="form-control input-sm" value="" placeholder="开始时间">
								<input type="number" class="form-control input-sm" value="" placeholder="结束时间">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>包场费用:</label>
							<div class="col-md-3 has-feedback">
								<input id="price" type="text" class="form-control" name="price" value="" placeholder="包场费用">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row bookSpaceModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>包场成本:</label>
							<div class="col-md-3 has-feedback">
								<input id="costPrice" type="text" class="form-control" name="costPrice" value="" placeholder="包场成本">
							</div>
						</div>
					</div>
					<div style="display:none"  class="row withDrawModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>提现金额:</label>
							<div class="col-md-3 has-feedback">
								<input id="fee_draw" type="number" class="form-control" name="fee" value="" placeholder="提现金额">
							</div>
						</div>
					</div>
					
					<div style="display:none" class="row chargeModel">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>充值金额:</label>
							<div class="col-md-3 has-feedback">
								<input id="fee_charge" type="number" class="form-control" name="fee" value="" placeholder="充值金额">
							</div>
						</div>
					</div>
					<div id="other_project">
					</div>
					<div class="book_act">
				<hr>
				<div class="row">
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red">*</span>总费用:</label>
						<div class="col-md-3 has-feedback">
							<input id="fee" type="number" class="form-control" name="fee" value="" placeholder="总费用">
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
			
			$("[class$=Model]").hide();
			$(".bookSpaceModel").show();
			$("#add_project").show();
			$(".book_act").show();
			
			
			//====================================================
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
			
			$("#serviceType").on("change",function(){
				var serviceType = $(this).val();
				if(serviceType==0){//固定包场
					$("[class$=Model]").hide();
					$(".bookSpaceModel").show();
					$("#add_project").show();
					$("#other_project").show();
					$(".book_act").show();
				}else if(serviceType==1){//活动赛事
					$("[class$=Model]").hide();
					$(".activityModel").show();
					$("#add_project").show();
					$(".book_act").show();
					$("#other_project").show();
					$("#other_project").html("");
				}else if(serviceType==2){//账户充值
					$("[class$=Model]").hide();
					$(".chargeModel").show();
					$("#other_project").hide();
					$("#add_project").hide();
					$(".book_act").hide();
				}else if(serviceType==3){//账户提现
					$("[class$=Model]").hide();
					$(".withDrawModel").show();
					$("#other_project").hide();
					$("#add_project").hide();
					$(".book_act").hide();
				}
			})
			$("#add_project").on("click",function(){
				$(".statium_cycle").each(function(){
					var checked = $(this).find("input[type=checkbox]").prop("checked");
					if(checked){
						var v = $(this).find("input[type=checkbox]").val();
						var b = $(this).find("input[type=number]:eq(0)").val();
						var e = $(this).find("input[type=number]:eq(1)").val();
						if(b==''||e==''||e<=b){
							bootbox.alert('选择星期的开始时间或结束时间录入错误！');
							return false;
						}
					}
				})
				var temp = new EJS({url: '${ctx}/static/template/service_project.ejs?ver=1.0'});
				var html = temp.render({});
				$('#other_project').append(html);
			})
			$("#submit_btn").on("click",function(){
				var companyId = $("#companyId").val();
				if(companyId==''){
					bootbox.alert('请选择要服务的公司！');
					return false;
				}
				var serviceType = $("#serviceType").val();
				if(serviceType==0){
					var statiumId = $("#statiumId").val();
					if(statiumId==''){
						bootbox.alert('请选择场馆！');
						return false;
					}
					var unitPrice = $("#unitPrice").val();
					if(unitPrice==''){
						bootbox.alert('请填写时租费用！');
						return false;
					}
					var begin = $("#begin").val();
					if(begin==''){
						bootbox.alert('请填开始日期！');
						return false;
					}
					var end = $("#end").val();
					if(end==''){
						bootbox.alert('请填写结束日期！');
						return false;
					}
					var amount = $("#amount").val();
					if(amount==''){
						bootbox.alert('请填写场地数量！');
						return false;
					}
					var price_ = $("#price").val();
					if(price_==''){
						bootbox.alert('请填写包场费用！');
						return false;
					}
					var costPrice_ = $("#costPrice").val();
					if(costPrice_==''){
						bootbox.alert('请填写包场成本！');
						return false;
					}
					var costFee = $("#costFee").val();
					if(costFee==''){
						bootbox.alert('请填写总成本！');
						return false;
					}
					var checkFlag = [];
					var checkWrongFLag = true;
					var activityDate = {};
					$(".statium_cycle").each(function(){
						var checked = $(this).find("input[type=checkbox]").prop("checked");
						if(checked){
							checkFlag.push("1");
							var v = $(this).find("input[type=checkbox]").val();
							var b = $(this).find("input[type=number]").eq(0).val();
							var e = $(this).find("input[type=number]").eq(1).val();
							if(b==''||e==''||e<=b){
								bootbox.alert('选择星期的开始时间或结束时间录入错误！');
								checkWrongFLag = false;
								return false;
							}
							activityDate[v]=[b,e];
						}else{
							checkFlag.push("0");
						}
					})
					console.log(activityDate);
					if(!checkWrongFLag){
						return false;
					}
					if(checkFlag.toString()==["0","0","0","0","0","0","0"].toString()){
						bootbox.alert('请选择星期！');
						return false;
					}
					var addWrongFLag = true;
					var addProject = [];
					$("#other_project").find(".other_project").each(function(){
						var title = $(this).find("input[name=title]").val();
						var price = $(this).find("input[name=price]").val();
						var costPrice = $(this).find("input[name=costPrice]").val();
						var content = $(this).find("input[name=content]").val();
						if(title==''||price==''||costPrice==''||content==''){
							bootbox.alert('附加项目填写错误！');
							addWrongFLag = false;
							return false;
						}
						addProject.push({title:title,price:price*100,costPrice:costPrice*100,content:content});
					});
					
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
					var payType = $("#payType").val();
					var sportType = $("#sportType").val();
					var submit_obj = {"costPrice":costPrice_*100,"price":price_*100,"unitPrice":unitPrice,"statiumId":statiumId,"payType":payType,"sportType":sportType,"serviceType":serviceType,"fee":fee*100,"costFee":costFee*100,"companyId":companyId,"begin":begin,"end":end,"amount":amount,"activityDate":activityDate,"addProjects":addProject};
				}else if(serviceType==1){
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
					$("#other_project").find(".other_project").each(function(){
						var title = $(this).find("input[name=title]").val();
						var price = $(this).find("input[name=price]").val();
						var costPrice = $(this).find("input[name=costPrice]").val();
						var content = $(this).find("input[name=content]").val();
						if(title==''||price==''||costPrice==''||content==''){
							bootbox.alert('附加项目填写错误！');
							addWrongFLag = false;
							return false;
						}
						addProject.push({title:title,price:price*100,costPrice:costPrice*100,content:content});
					});
					
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
				}else if(serviceType==2){
					var fee = $("#fee_charge").val();
					if(fee==''){
						bootbox.alert('请填写充值金额！');
						return false;
					}
					var submit_obj = {"serviceType":serviceType,"fee":fee*100,"companyId":companyId};
				}else if(serviceType==3){
					var fee = $("#fee_draw").val();
					if(fee==''){
						bootbox.alert('请填写提现金额！');
						return false;
					}
					var submit_obj = {"serviceType":serviceType,"fee":fee*100,"companyId":companyId};
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
