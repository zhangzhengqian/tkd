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
 <script type="text/javascript">
  $(document).ready(function() {
   $("#dataLoad").hide(); //页面加载完毕后即将DIV隐藏
  });
 </script>
<body>
 <div id="dataLoad" style="display:none"><!--页面载入显示-->
   <table width=100% height=100% border=0 align=center valign=middle>
    <tr height=50%><td align=center>&nbsp;</td></tr>
    <tr><td align=center><img src="${ctx}/static/img/loading-gif.gif"/></td></tr>
    <tr height=50%><td align=center>&nbsp;</td></tr>
   </table>
  </div>
	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li>赛事管理</li>
				 <c:choose>
				  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
				  		<c:set var="disable" value="false"/>
				  		<li class="active"><c:if test='${empty event.id}'> 新建赛事</c:if>
						<c:if test='${!empty event.id}'> 修改赛事</c:if></li>
				  	</c:when>
				  	<c:when test="${param.action == 'auti'}">
				  		<c:set var="disable" value="true"/>
						<c:set var="readonly" value="readonly='readonly'"/>
						<li class="active"> 审核赛事</li>
				  	</c:when>
				  	<c:otherwise>
						<c:set var="disable" value="true"/>
						<c:set var="readonly" value="readonly='readonly'"/>
						<li class="active"> 查看赛事</li>
				  	</c:otherwise>
				  </c:choose> 
				
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/enjoyRace/save" method="post" class="form-horizontal">
			<input id="id" name="id" value="${event.id}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>赛事信息</small>
					</legend>
					<div class="row">
						<input name="statiumId" type="hidden" id="statiumId" value="${event.statiumId }">
						<input name="siteId" type="hidden" id="siteId" value="${event.siteId }">
						<div class="form-group form-group-sm">
							<label for="areaCode" class="col-md-3 control-label"><span
								class="text-red">* </span>地区:</label>
							<div id="div_areaCode" class="col-md-6 has-feedback form-inline">
								<c:choose>
									<c:when test="${empty readonly }">
										<tags:zoneCity id="areaCode" name="areaCode"
											value="${event.areaCode }" disabled="${disable }" />
									</c:when>
									<c:otherwise>
										<tags:zoneCity name="areaCode" value="${event.areaCode }" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						 <div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>赛事名称:</label>
								<div class="col-md-6 has-feedback form-inline">

										<div class="input-group">
											<input ${readonly } type="text" class="form-control" placeholder="请输入赛事名称"
																id="name" name="name" value="${event.name}"/>
										</div>
									<div class="input-group">
										<input ${readonly } type="text" class="form-control" placeholder="请输入简称"
															id="shortTitle" name="shortTitle" value="${event.shortTitle}"/>
									</div>
									<div class="input-group">
										<c:if test="${param.action != 'create'}" >
											<input type="hidden" class="form-control" placeholder=""
												   id="holdTimes" name="holdTimes" value="${event.holdTimes}"/>
											<input readonly="readonly" type="text" class="form-control" placeholder=""
												 value="${event.holdTimes}站"/>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>比赛场馆:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly }  type="text" class="form-control" placeholder="请输入场馆名称"
									id="statiumName" name="statiumName"
									value="${event.statiumName}"
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="lngLat" class="col-md-3 control-label"><span
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
					<input type= "hidden"  id="sportType" name="sportType"  value="2">
					<div class="form-group form-group-sm">
							<label for="againPassword" class="col-md-3 control-label"><span
								class="text-red"> </span>*比赛级别:</label>
							<div class="col-md-6 has-feedback">
								<select ${readonly }   class="form-control" id="gameLevel" name="gameLevel">
									<option 
										<c:if test="${'1' == event.gameLevel}">selected</c:if>
										id="option0" value="1">--乐享一级--</option>
									<option
										<c:if test="${'2' == event.gameLevel}">selected</c:if>
										id="option1" value="2">--乐享二级--</option>
									<option
										<c:if test="${'3' == event.gameLevel}">selected</c:if>
										id="option2" value="3">--乐享三级--</option>
									<option
										<c:if test="${'4' == event.gameLevel}">selected</c:if>
										id="option3" value="4">--乐享四级--</option>
								</select>
							</div>
					   </div>
					    <c:choose>
						  	<c:when test="${param.action == 'create'}">
						  		<div class="form-group form-group-sm">
								<label for="againPassword" class="col-md-3 control-label"><span
									class="text-red"> </span>*比赛方式:</label>
								 <div class="col-md-6 has-feedback">
									<input type="checkbox" id="gameType1" name="gameTypes"  ${readonly }  value="1"/> 男子单打	
									<input type="checkbox" id="gameType2" name="gameTypes"  ${readonly }  value="2"/> 女子单打	
									<input type="checkbox" id="gameType3" name="gameTypes"  ${readonly }  value="3"/> 男子双打	
									<input type="checkbox" id="gameType4" name="gameTypes"  ${readonly }  value="4"/> 女子双打	
									<input type="checkbox" id="gameType5" name="gameTypes"  ${readonly }  value="5"/> 混合双打	
									<input type="checkbox" id="gameType6" name="gameTypes"  ${readonly }  value="6"/> 混合单打	
									<input type="checkbox" id="gameType7" name="gameTypes"  ${readonly }  value="7"/> 无性别限制双打	
								</div>
								</div>
								<div id="szdiv1" style="display: none;">
								  <div class="form-group form-group-sm">
										<label for="price" class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（男单）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice1"
													name="showPrices" value="" autocomplete="off" />
											</div>
											<div class="input-group" id="moneyType">元/人</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别（男单）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2。0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label for="gameFormat" class="col-md-3 control-label"><span class="text-red"></span>局制（男单）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>
										     </div>
									   </div>
								</div>
							   <div id="szdiv2"  style="display: none;">
								  <div class="form-group form-group-sm">
										<label for="price" class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（女单）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice2"
													name="showPrices" value=""  autocomplete="off"/>
											</div>
											<div class="input-group" id="moneyType">元/人</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label   class="col-md-3 control-label"><span class="text-red"></span>水平级别（女单）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2.0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label for="gameFormat" class="col-md-3 control-label"><span class="text-red"></span>局制（女单）:</label>
										     <div class="col-md-6 has-feedback">
								                <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>   </div>
									   </div>
								</div>
							    	<div id="szdiv3" style="display: none;">
								  <div class="form-group form-group-sm">
										<label  class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（男双）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice3"
													name="showPrices" value=""  autocomplete="off"/>
											</div>
											<div class="input-group" id="moneyType">元/组</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label for="gameFormat" class="col-md-3 control-label"><span class="text-red"></span>水平级别（男双）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2.0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label for="gameFormat" class="col-md-3 control-label"><span class="text-red"></span>局制（男双）:</label>
										     <div class="col-md-6 has-feedback">
								                   <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>     </div>
									   </div>
								</div>
							    	<div id="szdiv4" style="display: none;">
								  <div class="form-group form-group-sm">
										<label for="price" class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（女双）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice4"
													name="showPrices" value=""  autocomplete="off"/>
											</div>
											<div class="input-group" id="moneyType">元/组</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label  class="col-md-3 control-label"><span class="text-red"></span>水平级别（女双）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2.0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label  class="col-md-3 control-label"><span class="text-red"></span>局制（女双）:</label>
										     <div class="col-md-6 has-feedback">
								                   <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>   </div>
									   </div>
								</div>
							    	<div id="szdiv5" style="display: none;">
								  <div class="form-group form-group-sm">
										<label   class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（混双）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice5"
													name="showPrices" value=""  autocomplete="off"/>
											</div>
											<div class="input-group" id="moneyType">元/组</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别（混双）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2.0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label    class="col-md-3 control-label"><span class="text-red"></span>局制（混双）:</label>
										     <div class="col-md-6 has-feedback">
								                    <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>    </div>
									   </div>
								</div>
							    	<div id="szdiv6" style="display: none;">
								  <div class="form-group form-group-sm">
										<label  class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（混单）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice6"
													name="showPrices" value=""  autocomplete="off"/>
											</div>
											<div class="input-group" id="moneyType">元/人</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别（混单）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2.0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label  class="col-md-3 control-label"><span class="text-red"></span>局制（混单）:</label>
										     <div class="col-md-6 has-feedback">
								                   <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>    </div>
									   </div>
								</div>
								
								<div id="szdiv7" style="display: none;">
								  <div class="form-group form-group-sm">
										<label  class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（无性别限制双打）:</label>
										<div class="col-md-6 has-feedback form-inline">
											<div class="input-group">
												<input ${readonly }   type="text" class="form-control"
													placeholder="输入价格/ 元，例如50.00，最多保留2位小数" id="showPrice6"
													name="showPrices" value=""  autocomplete="off"/>
											</div>
											<div class="input-group" id="moneyType">元/组</div>
										</div>
										</div>
										<div class="form-group form-group-sm">
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别（无性别限制双打）:</label>
										     <div class="col-md-6 has-feedback">
										     <select ${readonly }   class="form-control" id="level" name="levels">
													<option 
														<c:if test="${'1.0' == event.level}">selected</c:if>
														id="option0" value="1.0"> 1.0 </option>
													<option 
														<c:if test="${'1.5' == event.level}">selected</c:if>
														id="option0" value="1.5"> 1.5 </option>
													<option
														<c:if test="${'2.0' == event.level}">selected</c:if>
														id="option1" value="2.0"> 2.0 </option>
													<option 
														<c:if test="${'2.5' == event.level}">selected</c:if>
														id="option0" value="2.5"> 2.5 </option>
													<option
														<c:if test="${'3.0' == event.level}">selected</c:if>
														id="option2" value="3.0"> 3.0 </option>
													<option
														<c:if test="${'3.5' == event.level}">selected</c:if>
														id="option2" value="3.5"> 3.5 </option>
													<option
														<c:if test="${'4.0' == event.level}">selected</c:if>
														id="option3" value="4.0"> 4.0 </option>
													<option
														<c:if test="${'4.5' == event.level}">selected</c:if>
														id="option3" value="4.5"> 4.5 </option>
													<option
														<c:if test="${'5.0' == event.level}">selected</c:if>
														id="option3" value="5.0"> 5.0 </option>
													<option
														<c:if test="${'6.0' == event.level}">selected</c:if>
														id="option3" value="6.0"> 6.0 </option>
													<option
														<c:if test="${'6.5' == event.level}">selected</c:if>
														id="option3" value="6.5"> 6.5 </option>
													<option
														<c:if test="${'7.0' == event.level}">selected</c:if>
														id="option3" value="7.0"> 7.0 </option>
													<option
														<c:if test="${'7.5' == event.level}">selected</c:if>
														id="option3" value="7.5"> 7.5 </option>
													<option
														<c:if test="${'8.0' == event.level}">selected</c:if>
														id="option3" value="8.0"> 8.0 </option>
													<option
														<c:if test="${'8.5' == event.level}">selected</c:if>
														id="option3" value="8.5"> 8.5 </option>
														<option
														<c:if test="${'9.0' == event.level}">selected</c:if>
														id="option3" value="9.0"> 9.0 </option>
												</select>
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label  class="col-md-3 control-label"><span class="text-red"></span>局制（无性别限制双打）:</label>
										     <div class="col-md-6 has-feedback">
								                   <select ${readonly }   class="form-control" id="gameFormat2s" name="gameFormat2s">
													<option 
														 <c:if test="${event.gameFormat2 == 0 || empty event.gameFormat2}">selected</c:if>
														id="option0" value="0"> 四局制 </option>
													<option 
														<c:if test="${event.gameFormat2 == 1}">  selected</c:if>
														id="option0" value="1"> 六局制</option>
											</select>    </div>
									   </div>
								</div>
						  	
						  	</c:when>
						  	<c:otherwise>
								<div class="form-group form-group-sm">
								<label  class="col-md-3 control-label"><span
									class="text-red"> </span>*比赛方式:</label>
								 <div class="col-md-6 has-feedback">
								        <c:if test="${fn:contains(mapKeys,'1')}"> 男单 ;</c:if>
										<c:if test="${fn:contains(mapKeys,'2')}">女单 ;</c:if> 
										<c:if test="${fn:contains(mapKeys,'3')}">男双 ;</c:if> 
										<c:if test="${fn:contains(mapKeys,'4')}">女双 ;</c:if>
										<c:if test="${fn:contains(mapKeys,'5')}">混双 ;</c:if> 
										<c:if test="${fn:contains(mapKeys,'6')}">混单 ;</c:if>
										<c:if test="${fn:contains(mapKeys,'7')}">无性别限制双打 ;</c:if>
								</div>
								</div>
								<c:forEach items="${gameMap}" var="gameInfo"> 
									<c:if test="${gameInfo.value.gameType == 1}">
										<c:set var="gameType" value="男单" />
										<c:set var="dw" value="人" />
									</c:if>
									<c:if test="${gameInfo.value.gameType == 2}">
										<c:set var="gameType" value="女单" />
										<c:set var="dw" value="人" />
										<c:set var="dw" value="人" />
									</c:if>
									<c:if test="${gameInfo.value.gameType == 3}">
										<c:set var="gameType" value="男双" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.gameType == 4}">
										<c:set var="gameType" value="女双" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.gameType == 5}">
										<c:set var="gameType" value="混双" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.gameType == 6}">
										<c:set var="gameType" value="混单" />
										<c:set var="dw" value="人" />
									</c:if>
									<c:if test="${gameInfo.value.gameType == 7}">
										<c:set var="gameType" value="无性别限制双打" />
										<c:set var="dw" value="组" />
									</c:if>
									 <div class="form-group form-group-sm">
										<label class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（${gameType }）:</label>
										<div class="col-md-6 has-feedback">
										       ${gameInfo.value.price/100}元/${dw }
									 	</div>
									  </div>
										<div class="form-group form-group-sm">
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别（${gameType }）:</label>
										     <div class="col-md-6 has-feedback">
											       ${gameInfo.value.level}
										     </div>
									    </div>
									    <div class="form-group form-group-sm">
											 <label  class="col-md-3 control-label"><span class="text-red"></span>局制（${gameType }）:</label>
										     <div class="col-md-6 has-feedback">
											     <c:if test="${gameInfo.value.gameFormat2 == 0 || empty gameInfo.value.gameFormat2}">
											     		四局制
											     </c:if>
											      <c:if test="${gameInfo.value.gameFormat2 == 1}"> 六局制</c:if>
										     </div>
									   </div>
								
								</c:forEach>
							
						  	</c:otherwise>
						  </c:choose> 
						<div class="form-group form-group-sm">
						<label for="chiefJudge" class="col-md-3 control-label"><span
							class="text-red">* </span>联系人:</label>
						<div class="col-md-6 has-feedback ">
							<input ${readonly } type="text" class="form-control" id="chiefJudge"  placeholder="请输联系人"
								name="chiefJudge" value="${event.chiefJudge}" />
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
							<label for="startTime" class="col-md-3 control-label"><span
								class="text-red">* </span>赛事开始时间:</label>
							<div class="col-md-6 has-feedback">
								<div class="input-group">
									<input ${readonly }   type="text" id="startTime" name="startTime"
										placeholder="请填赛事开始时间" value="${event.startTime}"
										class="form-control" data-date-format="yyyy-mm-dd HH:mm"
										onclick="WdatePicker({startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" />
									<label for="startTime" class="input-group-addon"><i
										class="fa fa-calendar"></i></label>
								</div>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="endTime" class="col-md-3 control-label"><span
								class="text-red">* </span>赛事结束时间:</label>
							<div class="col-md-6 has-feedback">
								<div class="input-group">
									<input ${readonly }   type="text" id="endTime" name="endTime"
										placeholder="请填赛事结束时间" value="${event.endTime}"
										class="form-control"
										onclick="WdatePicker({startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})" />
									<label for="endTime" class="input-group-addon"><i
										class="fa fa-calendar"></i></label>
								</div>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="expiryDate" class="col-md-3 control-label"><span
								class="text-red">* </span>报名截止:</label>
							<div class="col-md-6 has-feedback">
								<div class="input-group">
									<input  ${readonly }  type="text" id="expiryDate" name="expiryDate"
										placeholder="请填报名截止日期" value="${event.expiryDate}"
										class="form-control"                                                               
										onclick="WdatePicker({startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'startTime\');}',alwaysUseStartDate:true})" />
									<label for="expiryDate" class="input-group-addon"><i
										class="fa fa-calendar"></i></label>
								</div>
							</div>
						</div>
					<c:choose>
						<c:when test="${empty readonly }">
						<div class="form-group form-group-sm">
				         <label  class="col-md-3 control-label text-danger"style="font-size:18px"><span class="text-danger">! </span> 是否推送:</label>
				         <div class="col-md-6 has-feedback form-inline">
					           	 <div class="btn-group" id="isPushDiv" data-toggle="buttons">
					           	 	  <label class="btn btn-default<c:if test="${event.isPush == 0}"> active</c:if>">
									  		<input type="radio" name="isPush" value="0"  autocomplete="off"
									  		<c:if test='${event.isPush == 0}'>checked="checked"</c:if> >
									      	即时推送
									  </label>
									  <label class="btn btn-default<c:if test="${event.isPush == 1}"> active</c:if>">
									  		<input type="radio" name="isPush" value="1" autocomplete="off"
									  			<c:if test='${event.isPush == 1}'>checked="checked"</c:if> >
									     	定时推送
									  </label>
									  <label class="btn btn-default<c:if test="${event.isPush == 2 || empty event.isPush}"> active</c:if>">
									  		<input type="radio" name="isPush" value="2"  autocomplete="off"
									  		<c:if test='${event.isPush == 2}'>checked="checked"</c:if> >
									      	不推送
									  </label>
								</div>
								<input type="text" id="pushTime" name="pushTime" class="form-control"
									style="<c:if test="${event.isPush != 1}">display: none;</c:if>" 
									value="${event.pushTime }" />
				         </div>
				      </div>
				      </c:when>
				      </c:choose>
				  </div>
			</div>
					<div class="form-group">
		<hr>
		   <c:if test="${empty readonly }">
				 <div class="col-md-offset-1 col-md-3">
		    		<button type="submit" id="submit_btn" class="btn btn-primary btn-block" > 保存 </button>
					</div>
		  </c:if>
		  		<div class="col-md-offset-2 col-md-3">
					<a class="btn btn-default btn-block" href="${ctx}/enjoyRace/list"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
		</div>
		</fieldset>
		</form>
		<c:if test="${!empty event.id && (event.state eq '0' || empty event.state)}">
				 <legend>
				 	  <small>赛事审核</small>
				 </legend>
				<div class="panel-body">
					<form id="auditForm" action="" method="post" class="form-horizontal"  >
						<input type="hidden" name="id" value="${event.id}" />
						<div class="form-group">
								<div class="col-md-offset-1 col-md-3">	
						    		<button type="button"  onclick="audit('${event.id}',2)" class="btn btn-default btn-block" > 不通过 </button>
								</div>
								<div class="col-md-3">
						    		<button type="button"   onclick="audit('${event.id}',1)"   class="btn btn-primary btn-block" > 通过 </button>
								</div>
							</div>
					</form>
				</div>
		</c:if>
	</div>
		<%--
审核表单 >>>>
--%>


<%--
审核表单 <<<<
--%>
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
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
			
			 $("input:checkbox[name='gameTypes']").each(function(){
				 var this_ = $(this);
				 this_.change(function(){
					 if ( $("#szdiv"+this_.val()).css("display") == "none") {
						 $("#szdiv"+this_.val()).css("display","block");
					//	 $("#szdiv"+this_.val()).find("input[name='showPrices']").rules("add",{ required: true,digits:true,min:1});
			         } else {
			        	 $("#szdiv"+this_.val()).css("display","none");
			        	// $("#szdiv"+this_.val()).find("input[name='showPrices']").rules("remove");
			         }
				 });
			 });
			
			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					var gameLevelValue=$("#gameLevel").val();
					var len = $("input:checkbox[name='gameTypes']:checked").length;
					
					if(!chcekHoldTimes()){
						myAlert("不能发布新赛事，该场馆发布赛事次数已达到上限！");
						return false;
					}
			     if($("#id").val() == null || $("#id").val() == ""){
			    	 var limitPrice =1; 
			    	 var msg = "一";
			 		if(gameLevelValue == 1){
			 			limitPrice = 40;
						if(len < 4){
							myAlert("一级赛，至少选择4种比赛方式");
							return false;  
						}
					}else if(gameLevelValue == 2){
						limitPrice = 30;
						msg = "二";
						if(len != 3){
							myAlert("二级赛，只能选择3种比赛方式");
							return false;  
						}
					}else if(gameLevelValue == 3){
						limitPrice = 20;
						msg = "三";
						if(len != 2){
							myAlert("三级赛，只能选择2种比赛方式");
							return false;  
						}
					}else if(gameLevelValue == 4){
						limitPrice = 10;
						msg = "四";
						if(len != 1){
							myAlert("四级赛，只能选择1种比赛方式");
							return false;  
						}
					}
					
					var isSubmmit = true;
					 $("input:checkbox[name='gameTypes']").each(function(){
						 if ( $("#szdiv"+$(this).val()).css("display") != "none") {
							 var showPrices = $("#szdiv"+$(this).val()).find("input[name='showPrices']");
							 if(showPrices.val() == "" || showPrices.val() < limitPrice){
									showPrices.focus();
									isSubmmit =  false;
								}
				         }
					 });
					if(!isSubmmit){
						myAlert(msg+"级赛报名费必须大于"+limitPrice+"元");
						return false;
					}
			    	 
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
					"startTime" : {
						required : true
					},
					"endTime" : {
						required : true
					},
					"expiryDate" : {
						required : true
					},
					"tel" : {
						required: true,
						integer:true,
						isMobile : true
					},
					/* "showPrices" : {
						required : true,
						decimalMax2 : 2,
						//digits:true ,
						//min:10
					}, */
					"formatStr" : {
						required : true
					},
					"isPush" : {
						required: true
					}
				},
				messages : {

				}
			});
			
			//场馆名
			$("#statiumName").blur(function(){
				var statiumName = $("#statiumName").val();
				var data = {"statiumName":statiumName};
				if($('#statiumId').val() == null || $('#statiumId').val() == "" || statiumName == null && statiumName == ""){
					$("#statiumName").val("");
					$("#statiumId").val("");
					$("#siteId").val("");
					$('#address').val("");
				}else{
					//根据场馆名字查询场馆是存在
					$.ajax({
						url: "${ctx}/enjoyRace/checkStatium",
						type: "post",
						async: false,
						data: data,
						dataType: 'json',
						success:function(data){
							if (!data.result){
								myAlert("场馆不存在，请输入后从下拉项选择！");
								$("#statiumName").val("");
								$('#statiumId').val("");
								$("#siteId").val("");
								$('#address').val("");
							}
						}
					});
				}
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
		});
		
		
		function chcekHoldTimes(){
			var flag = true;
			 Util.getData(ctx + '/enjoyRace/chcekHoldTimes', function(data){
		      	 if(data.result){
			     }else{
			    	 flag = false;
				 }
		      }, null, {"statiumId":$('#statiumId').val(),"siteId":$('#siteId').val()}, 'post',false);
			 return flag;
		}
		
		 //====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name_from_site?flag=true',{
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
            	$('#statiumId').val(id.split("_")[0]);
            	$('#siteId').val(id.split("_")[1]);
            	$('#address').val(id.split("_")[2]);
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
			
		  function audit(id,state){
			var msg = '确定审核通过吗？';
			if(state == 2){
				msg = '确定拒绝通过吗？';
			}
			  bootbox.confirm(msg, function(result) {
			    if(result) {
			      Util.getData(ctx + '/enjoyRace/audit', function(data){
			      	 if(data.result){
				      	 myAlert("成功");
				      	 window.location.reload()
				     }else{
				    	 myAlert("失败","error");
					 }
			      }, null, {"id":id,"state":state}, 'post');
			    }
			  });
			  return false;
		}
		
	</script>
</body>
</html>
