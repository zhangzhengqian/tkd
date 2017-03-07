<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理</title>
<style type="text/css">
#title div {
	margin-left: auto;
	margin-right: auto;
}

.blank {
	clear: both;
	height: 10px;
	line-height: 10px;
	visibility: hidden;
}

#allmap {
	width: 100%;
	height: 500px;
	margin: 0;
	position: relative;
}

#golist {
	display: none;
}

@media ( max-device-width : 780px) {
	#golist {
		display: block !important;
	}
}

.img_close {
	position: relative;
	top: -110px;
	right: -115px;
	cursor: pointer;
	font-size: 25px;
	background-color: #FF6F00;
	height: 20px;
	width: 20px;
	border-radius: 50%;
	line-height: 20px;
}
</style>
<script type="text/javascript"
	src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${ctx}/static/ueditor/ueditor.all.js"></script>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li>赛事管理</li>
				<li class="active"><c:if test='${empty event.id}'> 新建赛事</c:if>
					<c:if test='${!empty event.id}'> 修改赛事</c:if></li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/event/save" method="post"
			class="form-horizontal">
			<input id="id" name="id" value="${event.id}" type="hidden" />
			<input id="isOffical" name="isOffical" value="0" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>赛事信息</small>
					</legend>
					<div class="row">
					  <div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>赛事类型:</label>
									<div class="col-md-6 has-feedback">
									<select class="form-control" id="realNameSys" name="realNameSys">
										<option value="1"
											<c:if test="${'1' == event.realNameSys}">selected</c:if>>实名制赛</option>
										<option value="0"
											<c:if test="${'1' != event.realNameSys}">selected</c:if>>非实名制赛</option>
									</select>
									</div>
				      </div>
					 <div class="form-group form-group-sm">
						<label for="sign" class="col-md-3 control-label"><span
							class="text-red">* </span>比赛方式:</label>
						<div class="col-md-6 has-feedback">
							<select class="form-control" id="type" name="type">
								<option value="0"
									<c:if test="${'0' == event.type}">selected</c:if>>个人赛</option>
								<option value="1"
									<c:if test="${'1' == event.type}">selected</c:if>>团体赛</option>
							</select>
						</div>
					</div>
					  <div class="form-group form-group-sm">
							 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>赛事可见区域:</label>
						     <div class="col-md-6 has-feedback">
				                    <label class="radio-inline"><input type="radio" name="scope" value="0"  <c:if test="${event.scope != 1}"> checked="checked" </c:if>>全国</label>
						     		<label class="radio-inline"><input type="radio" name="scope" value="1"  <c:if test="${event.scope == 1}"> checked="checked" </c:if> >指定城市</label>
						     </div>
					  </div>
					  <div class="form-group form-group-sm form-inline" id="cityArea" style="display: none">
					        <label for="name" class="col-md-3 control-label"><span class="text-red"></span>城市:</label>
						     <div class="col-md-9">
						     <div class="form-inline">
								<tags:zoneCity name="scopeAreaCode" value="${event.scopeAreaCode}" id="scopeAreaCode" />
						     </div>
							 </div>
					  </div>
					  <div class="form-group form-group-sm">
							 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>报名队数限制:</label>
						     <div class="col-md-6 has-feedback">
				                    <label class="radio-inline"><input type="radio" name="isRestrict" value="0"  <c:if test="${event.isRestrict == 0}"> checked="checked" </c:if>>不限制</label>
						     		<label class="radio-inline"><input type="radio" name="isRestrict" value="1"  <c:if test="${event.isRestrict != 0}"> checked="checked" </c:if> >限制</label>
						     </div>
					  </div>
					<div class="form-group form-group-sm" id="numberDiv">
						<label for="sign" class="col-md-3 control-label"><span
							class="text-red">* </span>参赛数量上限:</label>
						<div class="col-md-6 has-feedback form-inline">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="请输入数量"
									id="totalNumber" name="totalNumber"
									value="${event.totalNumber}" />
							</div>
							<div class="input-group" id="numberType">人</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="sign" class="col-md-3 control-label"><span
							class="text-red">* </span>已报名数量:</label>
						<div class="col-md-6 has-feedback form-inline">
							<div class="input-group">
								<input readonly="readonly" type="text" class="form-control"
									placeholder="已报名数量" id="applicantNumber"
									name="applicantNumber" value="${event.applicantNumber}" />
							</div>
							<div class="input-group" id="applicantNumberType">人</div>
						</div>
					</div>
						<div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>赛事名称:</label>
								<div class="col-md-6 has-feedback">
									<input type="text" class="form-control" placeholder="请输入赛事名称"
										id="name" name="name" value="${event.name}"
								</div>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="againPassword" class="col-md-3 control-label"><span
								class="text-red"> </span>*运动类别:</label>
							<div class="col-md-6 has-feedback">
								<select class="form-control" id="sportType" name="sportType">
									<option
										<c:if test="${'BASKETBALL' == event.sportType}">selected</c:if>
										id="option0" value="BASKETBALL">--篮球--</option>
									<option
										<c:if test="${'FOOTBALL' == event.sportType}">selected</c:if>
										id="option1" value="FOOTBALL">--足球--</option>
									<option
										<c:if test="${'BADMINTON' == event.sportType}">selected</c:if>
										id="option2" value="BADMINTON">--羽毛球--</option>
									<option
										<c:if test="${'BILLIARDS' == event.sportType}">selected</c:if>
										id="option3" value="BILLIARDS">--台球--</option>
									<option
										<c:if test="${'BOWLING' == event.sportType}">selected</c:if>
										id="option4" value="BOWLING">--保龄球--</option>
									<option
										<c:if test="${'GOLF' == event.sportType}">selected</c:if>
										id="option5" value="GOLF">--高尔夫--</option>
									<option
										<c:if test="${'TABLE_TENNIS' == event.sportType}">selected</c:if>
										id="option6" value="TABLE_TENNIS">--乒乓球--</option>
									<option
										<c:if test="${'TENNIS' == event.sportType}">selected</c:if>
										id="option7" value="TENNIS">--网球--</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="sign" class="col-md-3 control-label"><span
								class="text-red">* </span>赛制: </label>
							<div class="col-md-6 has-feedback form-inline">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="请输入赛制，如：男双" id="formatStr" name="formatStr"
										value="${event.formatStr}" />
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>主办方名称:</label>
								<div class="col-md-6 has-feedback">
									<input type="text" class="form-control"
										placeholder="请输入赛事主办方名称" id="sponsor" name="sponsor"
										value="${event.sponsor}"
								</div>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>主办方海报:</label>
							<div class="col-md-6 has-feedback">
								<input id="logoFile" type="file" multiple="false" />
								<!-- 保存图片 -->
								<input id="logo" name="logo" type="hidden" value="${event.logo}" />
								<!-- 显示图片 -->
								<img alt=""
									style="<c:if test='${!empty event.logo}'>width:128px;height:128px;</c:if>"
									src="${event.logo}" id="logo_img">
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="introduction" class="col-md-3 control-label"><span
							class="text-red">*</span>主办方介绍:</label>
						<div class="col-md-6 has-feedback">
							<script id="myEditor" name="introduce" type="text/plain">${event.introduce}</script>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="birthday" class="col-md-3 control-label"><span
							class="text-red">* </span>报名截止:</label>
						<div class="col-md-6 has-feedback">
							<div class="input-group">
								<input type="text" id="expiryDate" name="expiryDate"
									placeholder="请填报名截止日期" value="${event.expiryDate}"
									class="form-control"
									onclick="WdatePicker({startDate:'%y-%M-%d 23:59',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})" />
								<label for="expiryDate" class="input-group-addon"><i
									class="fa fa-calendar"></i></label>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="birthday" class="col-md-3 control-label"><span
							class="text-red">* </span>赛事开始时间:</label>
						<div class="col-md-6 has-feedback">
							<div class="input-group">
								<input type="text" id="startTime" name="startTime"
									placeholder="请填赛事开始时间" value="${event.startTime}"
									class="form-control" data-date-format="yyyy-mm-dd"
									onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" />
								<label for="startTime" class="input-group-addon"><i
									class="fa fa-calendar"></i></label>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="birthday" class="col-md-3 control-label"><span
							class="text-red">* </span>赛事结束时间:</label>
						<div class="col-md-6 has-feedback">
							<div class="input-group">
								<input type="text" id="endTime" name="endTime"
									placeholder="请填赛事结束时间" value="${event.endTime}"
									class="form-control"
									onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})" />
								<label for="endTime" class="input-group-addon"><i
									class="fa fa-calendar"></i></label>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="areaCode" class="col-md-3 control-label"><span
							class="text-red">* </span>比赛地点:</label>
						<div id="div_areaCode" class="col-md-6 has-feedback form-inline">
							<c:choose>
								<c:when test="${empty readonly }">
									<tags:zone id="areaCode" name="areaCode"
										value="${event.areaCode }" disabled="${disable }" />
								</c:when>
								<c:otherwise>
									<tags:zonemap code="${event.areaCode }" />
								</c:otherwise>
							</c:choose>

						</div>
					</div>
					<div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>比赛场馆:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" placeholder="请输入场馆名称"
									id="statiumName" name="statiumName"
									value="${event.statiumName}"
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="" class="col-md-3 control-label"><span
							class="text-red">*</span>场馆坐标:</label>
						<div class="col-md-6 has-feedback form-inline">

							<div class="input-group">
								<input ${readonly } readOnly type="text" class="form-control"
									id="lngLat" name="lngLat"
									value="<c:if test="${ not empty event.lng}">${event.lng },${event.lat}</c:if>"
									placeholder="经度,纬度" /> <span class="input-group-btn"> <c:if
										test="${empty readonly }">
										<button id="coordinate" class="btn btn-primary btn-sm"
											type="button">坐标/地址 识取工具</button>
									</c:if>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="address" class="col-md-3 control-label"><span
							class="text-red">* </span>详细地址:</label>
						<div class="col-md-6 has-feedback ">
							<input ${readonly } type="text" class="form-control" id="address"
								name="address" value="${event.address}" />
						</div>
					</div>

					<div class="form-group form-group-sm" style="display: none;"
						id="baiduMap">
						<label for="" class="col-md-3 control-label"></label>
						<div class="col-md-6 has-feedback form-inline">
							<div class="panel panel-default">
								<div class="panel-heading">
									<input type="text" class="form-control" id="keyword" />
									<button type="button" class="btn btn-primary btn-sm"
										id="search">
										<span class="glyphicon glyphicon-search"></span> 搜 索
									</button>
									<button type="button" class="close" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="panel-body">
									<div id="allmap"></div>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sign" class="col-md-3 control-label"><span
							class="text-red">* </span>报名费用:</label>
						<div class="col-md-6 has-feedback form-inline">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="avgMoney"
									name="avgMoney" value="${event.money/100}" />
							</div>
							<div class="input-group" id="moneyType">人</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red">*</span>赛程:</label>
						<div class="col-md-6 has-feedback">
							<input id="scheduleFile" type="file" multiple="false" />
							<!-- 保存图片 -->
							<input id="schedule" name="schedule" type="hidden"
								value="${event.schedule}" />
							<!-- 显示图片 -->
							<img alt=""
								style="<c:if test='${!empty event.schedule}'>width:128px;height:128px;</c:if>"
								src="${event.schedule}" id="schedule_img">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="introduction" class="col-md-3 control-label"><span
							class="text-red">*</span>章程:<span  style="color:red;">（图片标准尺寸 宽 640px） </span>
							</label>
						<div class="col-md-6 has-feedback">
							<script id="myEditorRules" name="rules" type="text/plain">${event.rules}</script>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red">* </span>联系电话:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="tel"
								name="tel" value="${event.tel }" placeholder="请输入联系电话" />
						</div>
					</div>
						 <div class="form-group form-group-sm">
							 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>需要审核参赛成员:</label>
						     <div class="col-md-6 has-feedback">
				                    <label class="radio-inline"><input type="radio" name="isVerify" value="1"  <c:if test="${event.isVerify == 1}"> checked="checked" </c:if>>是</label>
						     		<label class="radio-inline"><input type="radio" name="isVerify" value="0"  <c:if test="${event.isVerify != 1}"> checked="checked" </c:if> >否</label>
					</div></div>
				
					<div class="form-group form-group-sm">
				         <label for="push" class="col-md-3 control-label text-danger"style="font-size:18px"><span class="text-danger">! </span> 是否推送:</label>
				         <div class="col-md-6 has-feedback form-inline">
					           	 <div class="btn-group" id="isPushDiv" data-toggle="buttons">
					           	 	  <label class="btn btn-default<c:if test="${activity.isPush == 0}"> active</c:if>">
									  		<input type="radio" name="isPush" value="0"  autocomplete="off"
									  		<c:if test='${activity.isPush == 0}'>checked="checked"</c:if> >
									      	即时推送
									  </label>
									  <label class="btn btn-default<c:if test="${activity.isPush == 1}"> active</c:if>">
									  		<input type="radio" name="isPush" value="1" autocomplete="off"
									  			<c:if test='${activity.isPush == 1}'>checked="checked"</c:if> >
									     	定时推送
									  </label>
									  <label class="btn btn-default<c:if test="${activity.isPush == 2 || empty activity.isPush}"> active</c:if>">
									  		<input type="radio" name="isPush" value="2"  autocomplete="off"
									  		<c:if test='${activity.isPush == 2}'>checked="checked"</c:if> >
									      	不推送
									  </label>
								</div>
								<input type="text" id="pushTime" name="pushTime" class="form-control"
									style="<c:if test="${activity.isPush != 1}">display: none;</c:if>" 
									value="${activity.pushTime }" />
				         </div>
				      </div>
				  </div>
			</div>
			</fieldset>

		</form>
	<div class="form-group">
		<hr>
		<div class="col-md-offset-3 col-md-2">
			<a class="btn btn-default btn-block" href="${ctx}/event/list"><span
				class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-primary btn-block"
				id="submit_btn">
				<span class="glyphicon glyphicon-ok"></span> 保存
			</button>
		</div>
	</div>
	</div>

	<!-- end row -->

	</fieldset>



	</div>

	</div>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
	<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
	<script src="${ctx}/static/js/project_js.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#event-man');
			$('#adminFooter').hide();
			
			//实名制目前只支持个人赛
			if('${event.realNameSys}' == 1){
				   $("#type").val("0");
				   $("#type").attr("disabled",true); 
			}
			
			$("#realNameSys").change(function(){
				if($('#realNameSys option:selected').val() == 1){
					   $("#type").val("0");
					   $("#type").attr("disabled",true); 
				}else{
					 $("#type").attr("disabled",false); 
				}
			});

			//可见区域
			 if('${event.scope}' == 1){
				 $("#cityArea").css("display","block");
				 $("#scopeAreaCode").addClass("required",true);
			 }else{
				 $("#cityArea").css("display","none");
				 $("#scopeAreaCode").removeClass("required");
			 }
			 $("input:radio[name='scope']").each(function(){
				$(this).change(function(){
					 var val=$('input:radio[name="scope"]:checked').val();
					 if(val == 1){
						 $("#cityArea").css("display","block");
						 $("#scopeAreaCode").addClass("required",true);         
					 }else{
						 $("#cityArea").css("display","none");
						 $("#scopeAreaCode").removeClass("required");
					 }
				});
			 });
			 
			 //是否限制报名人数或队数
			 if('${event.isRestrict}' != 0 || '${event.isRestrict}' == ""){
				$("#numberDiv").css("display","block");
				$("#totalNumber").addClass("required",true);

			 }else{
				$("#numberDiv").css("display","none");
			    $("#totalNumber").removeClass("required");

			 }
			 
			 $("input:radio[name='isRestrict']").each(function(){
				 $(this).change(function(){
					 var val=$('input:radio[name="isRestrict"]:checked').val();
					 if(val == 0){
						 $("#numberDiv").css("display","none");
						 $("#totalNumber").removeClass("required");
					 }else{
						 $("#numberDiv").css("display","block");
						 $("#totalNumber").addClass("required",true);
					 }
				 });
			 });
			
			// 是否推送
			$("#isPushDiv").on("click","label",function(){
				if($(this).find("input[name=isPush]").val() == 1){
					$("#pushTime").bind({click:function(){
						WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',alwaysUseStartDate:true});
					}}).removeAttr("disabled").attr("placeholder","请输入推送日期").show();
					$('#pushTime').rules( "add",{required: true});
				} else {
					$("#pushTime").rules("remove");
					$("#pushTime").attr("disabled","disabled");
					$("#pushTime").hide();
				}
			});
			
			//获取坐标

			$(".close").click(function() {
				$("#baiduMap").slideUp('slow');
			});
			$("#coordinate").click(function() {
				$("#baiduMap").show();
				var lnglat = $("#lngLat").val();
				var lng = 116.403867;
				var lat = 39.914113;
				var name = "天安门";
				if (lnglat) {
					var lnglats = lnglat.split(",");
					lng = lnglats[0];
					lat = lnglats[1];
					var name = $("#name").val();
				}
				// 百度地图API功能
				map = new BMap.Map("allmap");
				map.centerAndZoom(new BMap.Point(lng, lat), 14);
				map.setCenter(new BMap.Point(lng, lat));
				var marker1 = new BMap.Marker(new BMap.Point(lng, lat)); //创建标注
				map.addOverlay(marker1); // 将标注添加到地图中
				var infoWindow1 = new BMap.InfoWindow(name);
				marker1.addEventListener("click", function(e) {
					this.openInfoWindow(infoWindow1, false);
				});

				$("#search").click(function() {
					search($("#keyword").val(), map);
				});

				// 获取经纬度和地址
				map.addEventListener("click", function(e) {
					var lnglat = e.point.lng + "," + e.point.lat;
					$("#lngLat").val(lnglat);
					var gc = new BMap.Geocoder();
					gc.getLocation(e.point, function(rs) {
						var addComp = rs.addressComponents;
						var address = '';
						address += addComp.province;
						address += addComp.city;
						address += addComp.district;
						address += addComp.street;
						address += addComp.streetNumber;
						$("#address").val(address);
						//alert("当前定位地址为：" + address);
					});
				});
			});

			function search(name, map) {
				var local = new BMap.LocalSearch(map, {
					renderOptions : {
						map : map
					}
				});
				local.search(name);
			}
	<%--加载ueditor--%>
		//主办方介绍
			var um = UE.getEditor('myEditor', {
				initialFrameWidth : '620',
				initialFrameHeight : '500',
				elementPathEnabled : false,
				wordCount : false,
				toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo',
						'|', 'bold', 'italic', 'underline', 'fontborder',
						'strikethrough', 'superscript', 'subscript',
						'removeformat', 'formatmatch', 'autotypeset',
						'blockquote', 'pasteplain', '|', 'insertorderedlist',
						'insertunorderedlist', 'selectall', 'cleardoc', '|',
						'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
						'customstyle', 'paragraph', 'fontfamily', 'fontsize',
						'|', 'directionalityltr', 'directionalityrtl',
						'indent', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'justifyjustify', '|', 'touppercase',
						'tolowercase', '|', 'simpleupload', 'insertimage', '|',
						'preview' ] ]
			});

			//赛事规则介绍
			var umRules = UE.getEditor('myEditorRules', {
				initialFrameWidth : '620',
				initialFrameHeight : '500',
				elementPathEnabled : false,
				wordCount : false,
				toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo',
						'|', 'bold', 'italic', 'underline', 'fontborder',
						'strikethrough', 'superscript', 'subscript',
						'removeformat', 'formatmatch', 'autotypeset',
						'blockquote', 'pasteplain', '|', 'insertorderedlist',
						'insertunorderedlist', 'selectall', 'cleardoc', '|',
						'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
						'customstyle', 'paragraph', 'fontfamily', 'fontsize',
						'|', 'directionalityltr', 'directionalityrtl',
						'indent', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'justifyjustify', '|', 'touppercase',
						'tolowercase', '|', 'simpleupload', 'insertimage', '|',
						'preview' ] ]
			});
	<%--加载ueditor END--%>
		   if ('${event.id }') {
				$("#logoFile").parent().parent().find("label span").html("");
			} else {
				$("#logoFile").addClass("required");
			}
			if ('${event.id }') {
				$("#showLogoFile").parent().parent().find("label span")
						.html("");
			} else {
				$("#showLogoFile").addClass("required");
			}

			if ('${event.id }') {
				$("#scheduleFile").parent().parent().find("label span")
						.html("");
			} else {
				$("#scheduleFile").addClass("required");
			}
			upload({
				'id' : 'logoFile',
				'icon_img' : 'logo_img',
				'icon' : 'logo'
			}); // LOGO
			upload({
				'id' : 'showLogoFile',
				'icon_img' : 'showLogo_img',
				'icon' : 'showLogo'
			}); // 海报
			upload({
				'id' : 'scheduleFile',
				'icon_img' : 'schedule_img',
				'icon' : 'schedule'
			}); //赛程

			$("#submit_btn").click(function() {
				$("#realNameSys").attr("disabled",false); 
				$("#inputForm").submit();
			});

			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					//表单验证成功时，锁住提交按钮
					if (um.getContentLength(true) == 0) {
						common.showMessage("请填写主办方介绍！");
						return;
					}
					if (umRules.getContentLength(true) == 0) {
						common.showMessage("请填写比赛规则介绍！");
						return;
					}
					app.disabled("#submit_btn");
					//提交表单
					form.submit();
				},
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					"statiumName" : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					"areaCode" : {
						required : true
					},
					"address" : {
						required : true
					},
					"sponsor" : {
						required : true
					},
					"startTime" : {
						required : true
					},
					"endTime" : {
						required : true
					},
					"expiryDate" : {
						required : true
					},
					"totalNumber" : {
						required : true,
						digits : true,
						min : 1
					},
					"tel" : {
						required : true
					},
					"avgMoney" : {
						required : true,
						decimalMax2 : 2
					},
					"type" : {
						required : true
					},
					"formatStr" : {
						required : true
					},"pushType" : {
						required: true
					},
					"isPush" : {
						required: true
					}
				},
				messages : {

				}
			});

		});
		/**
		 *	option.id            上传元素id
		 *	option.icon_img      显示图片id
		 *	option.icon          保存图片的url的id
		 *	option.width         显示图片的宽度
		 *	option.height        显示图片的高度
		 */
		function upload(option) {
			var id = option.id || "icon_upload";
			var height = option.height || 40;
			var width = option.width || 120;
			var icon_img = option.icon_img || "icon_img";
			var icon = option.icon || "icon";
			$("#" + id).uploadify({
				//文件提交到 controller 里的文件对象的 key 
				fileObjName : 'upfile',
				//按钮名称
				buttonText : '选择图片',
				height : 30,
				multi : false,
				swf : ctx + '/static/uploadify/uploadify.swf',
				//提交到指定的 controller,写死即可，已封装
				uploader : ctx + '/uploader',
				width : 100,
				fileTypeExts : '*.gif;*.jpg;*.jpeg;*.png',
				//上传成功后回调此函数
				onUploadSuccess : function(file, data, response) {
					//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
					data = JSON.parse(data);
					if (data.success == true) {
						$('#' + icon_img).attr('src', data.url).css({
							width : '100px',
							height : '100px;'
						});
						$('#' + icon).val(data.url);
					}
				}
			});
		}
		$("#type").change(function() {
			if ($("#type").val() == 0) {
				$("#numberType").html("人");
				$("#moneyType").html("人");
				$("#applicantNumberType").html("人");
				//	$("#personNoDiv").css("display", "none");
				//	$("#personno").val("");
			} else {
				$("#numberType").html("队");
				$("#moneyType").html("队");
				$("#applicantNumberType").html("队");
				//	$("#personNoDiv").css("display", "block");
			}
		});
	</script>
</body>
</html>
