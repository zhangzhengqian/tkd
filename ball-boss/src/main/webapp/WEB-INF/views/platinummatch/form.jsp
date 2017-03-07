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
				<li>白金赛管理</li>
				 <c:choose>
				  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
				  		<c:set var="disable" value="false"/>
				  		<li class="active"><c:if test='${empty event.id}'> 新建白金赛</c:if>
						<c:if test='${!empty event.id}'> 修改赛事</c:if></li>
				  	</c:when>
				  	<c:when test="${param.action == 'auti'}">
				  		<c:set var="disable" value="true"/>
						<c:set var="readonly" value="readonly='readonly'"/>
						<li class="active"> 审核白金赛</li>
				  	</c:when>
				  	<c:otherwise>
						<c:set var="disable" value="true"/>
						<c:set var="readonly" value="readonly='readonly'"/>
						<li class="active"> 查看白金赛</li>
				  	</c:otherwise>
				  </c:choose> 
				
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/platinumMatch/save" method="post" class="form-horizontal">
			<input id="id" name="id" value="${event.id}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>赛事信息</small>
					</legend>
					<div class="row">
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
								</div>
							</div>
						</div>
						<div>
					</div>
					<div class="form-group form-group-sm">
						<label for="address" class="col-md-3 control-label"><span
							class="text-red">* </span>详细地址:</label>
						<div class="col-md-6 has-feedback ">
							<input ${readonly } type="text" class="form-control" id="address"
								name="address" value="${event.address}" />
						</div>
					</div>
					    <c:choose>
						  	<c:when test="${param.action == 'create'}">
						  		<div class="form-group form-group-sm" style="display: none">
								<label for="" class="col-md-3 control-label"><span
									class="text-red"> </span>*比赛方式:</label>
								 <div class="col-md-6 has-feedback" >
									<input type="checkbox" id="gameType1" name="gameTypes" checked="checked" value="1"/> 男子单打
									<input type="checkbox" id="gameType2" name="gameTypes" checked="checked"  value="2"/>女子单打
									<input type="checkbox" id="gameType3" name="gameTypes"  checked="checked"  value="3"/> 青年组男子双打
									<input type="checkbox" id="gameType3" name="gameTypes"  checked="checked"  value="4"/> 常青组男子双打
									<input type="checkbox" id="gameType4" name="gameTypes"  checked="checked"  value="5"/> 青年组女子双打
									<input type="checkbox" id="gameType5" name="gameTypes"  checked="checked"  value="6"/> 常青组女子双打
									<input type="checkbox" id="gameType5" name="gameTypes"  checked="checked"  value="7"/> 青年组混合双打
									<input type="checkbox" id="gameType5" name="gameTypes"  checked="checked"  value="8"/> 常青组混合双打
								</div>
								</div>
								<div id="szdiv1" >
								  <div class="form-group form-group-sm">
										<label for="" class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（男子单打）:</label>
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
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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
								</div>
							   <div id="szdiv2" >
								  <div class="form-group form-group-sm">
										<label for="price" class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（女子单打）:</label>
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
											 <label   class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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

								</div>
							    	<div id="szdiv3" >
								  <div class="form-group form-group-sm">
										<label  class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（青年组男子双打）:</label>
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
											 <label for="gameFormat" class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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

								</div>
							    	<div id="szdiv4"  >
								  <div class="form-group form-group-sm">
										<label for="price" class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（常青组男子双打）:</label>
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
											 <label  class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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

								</div>
							    	<div id="szdiv5"  >
								  <div class="form-group form-group-sm">
										<label   class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（青年组女子双打）:</label>
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
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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
								</div>
								
								<div id="szdiv6"  >
								  <div class="form-group form-group-sm">
										<label   class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（长青组女子双打）:</label>
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
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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
								</div>
								
								<div id="szdiv7"  >
								  <div class="form-group form-group-sm">
										<label   class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（青年组混合双打）:</label>
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
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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
								</div>
								
								<div id="szdiv8"  >
								  <div class="form-group form-group-sm">
										<label   class="col-md-3 control-label"><span
											class="text-red">* </span>报名费用（常青组混合双打）:</label>
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
											 <label for="level" class="col-md-3 control-label"><span class="text-red"></span>水平级别:</label>
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
								</div>
								
						  	</c:when>
						  	<c:otherwise>
								<c:forEach items="${gameMap}" var="gameInfo">
									<c:if test="${gameInfo.value.type == 1}">
										<c:set var="gameType" value="男子单打" />
										<c:set var="dw" value="人" />
									</c:if>
									<c:if test="${gameInfo.value.type == 2}">
										<c:set var="gameType" value="女子单打" />
										<c:set var="dw" value="人" />
									</c:if>
									<c:if test="${gameInfo.value.type == 3}">
										<c:set var="gameType" value="青年组男子双打" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.type == 4}">
										<c:set var="gameType" value="常青组男子双打" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.type == 5}">
										<c:set var="gameType" value="青年组女子双打" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.type == 6}">
										<c:set var="gameType" value="常青组女子双打" />
										<c:set var="dw" value="组" />
									</c:if>
									<c:if test="${gameInfo.value.type == 7}">
										<c:set var="gameType" value="青年组混合双打" />
										<c:set var="dw" value="人" />
									</c:if>
									<c:if test="${gameInfo.value.type == 8}">
										<c:set var="gameType" value="常青组混合双打" />
										<c:set var="dw" value="人" />
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
								</c:forEach>
							
						  	</c:otherwise>
						  </c:choose> 
						<div class="form-group form-group-sm">
							<label for="startTime" class="col-md-3 control-label"><span
								class="text-red">* </span>赛事开始时间:</label>
							<div class="col-md-6 has-feedback">
								<div class="input-group">
									<input ${readonly }   type="text" id="startTime" name="startTime"
										placeholder="请填赛事开始时间" value="${event.startTime}"
										class="form-control" data-date-format="yyyy-mm-dd HH:mm"
										onclick="WdatePicker({startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+7}',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" />
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
										onclick="WdatePicker({startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'startTime\',{d:-1});}',alwaysUseStartDate:true})" />
									<label for="expiryDate" class="input-group-addon"><i
										class="fa fa-calendar"></i></label>
								</div>
							</div>
						</div>
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
					<a class="btn btn-default btn-block" href="${ctx}/platinumMatch/list"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
		</div>
		</fieldset>
		</form>
	</div>
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
			menu.active('#platinumMatch-man');
			$('#adminFooter').hide();
			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					var isSubmmit = true;
					var tips = 1;
					$("input:checkbox[name='gameTypes']").each(function(){
						var limitPrice = 160;
						if($(this).val() == 1){
							limitPrice = 80;
						}
						if ( $("#szdiv"+$(this).val()).css("display") != "none") {
							var showPrices = $("#szdiv"+$(this).val()).find("input[name='showPrices']");
							if(showPrices.val() == "" || showPrices.val() < limitPrice){
								showPrices.focus();
								isSubmmit =  false;
								if($(this).val() != 1){
									tips = 2;
								}
							}
						}
					});
					if(!isSubmmit && tips == 2){
						myAlert("双人赛报名费必须大于160元");
						return false;
					}else if(!isSubmmit){
						myAlert("单人赛报名费必须大于80元");
						return false;
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
					}
				},
				messages : {

				}
			});

		});
	</script>
</body>
</html>
