<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>奖金管理列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>已分配奖金赛事列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/enjoyRace/bonusList" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<select   class="form-control" id="search_EQ_gameLevel" name="search_EQ_gameLevel">
									<option <c:if test="${empty param.search_EQ_gameLevel}">selected</c:if>  value="">--选择赛事级别--</option>
									<option
											<c:if test="${'1' == param.search_EQ_gameLevel}">selected</c:if>
											id="option0" value="1">--乐享一级--</option>
									<option
											<c:if test="${'2' == param.search_EQ_gameLevel}">selected</c:if>
											id="option1" value="2">--乐享二级--</option>
									<option
											<c:if test="${'3' == param.search_EQ_gameLevel}">selected</c:if>
											id="option2" value="3">--乐享三级--</option>
									<option
											<c:if test="${'4' == param.search_EQ_gameLevel}">selected</c:if>
											id="option3" value="4">--乐享四级--</option>
								</select>
							</div>
							<div class="col-md-5">
								<select   class="form-control" id="search_EQ_gameType" name="search_EQ_gameType">
									<option <c:if test="${empty param.search_EQ_gameType}">selected</c:if>  value="">--选择比赛方式--</option>
									<option
											<c:if test="${'1' == param.search_EQ_gameType}">selected</c:if>
											id="option0" value="1">--男单--</option>
									<option
											<c:if test="${'2' == param.search_EQ_gameType}">selected</c:if>
											id="option1" value="2">--女单--</option>
									<option
											<c:if test="${'3' == param.search_EQ_gameType}">selected</c:if>
											id="option2" value="3">--男双--</option>
									<option
											<c:if test="${'4' == param.search_EQ_gameType}">selected</c:if>
											id="option3" value="4">--女双--</option>
									<option
											<c:if test="${'5' == param.search_EQ_gameType}">selected</c:if>
											id="option3" value="5">--混双--</option>
									<option
											<c:if test="${'6' == param.search_EQ_gameType}">selected</c:if>
											id="option3" value="6">--混单--</option>
									<option
											<c:if test="${'7' == param.search_EQ_gameType}">selected</c:if>
											id="option3" value="7">--无性别限制双打--</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
								<div class=" col-md-5 ">
									<input type="text" name="search_GTE_startTime"
										   placeholder="比赛开始时间" value="${param.search_GTE_startTime }"
										   id="search_GTE_startTime" class="form-control"
										   onclick='WdatePicker({"dateFmt":"yyyy-MM-dd HH:mm"});' >
								</div>
								<div class=" col-md-5">
									<input type="text" name="search_LTE_endTime"
										   placeholder="比赛结束时间" id="search_LTE_endTime"
										   value="${param.search_LTE_endTime }" class="form-control"
										   onclick='WdatePicker({"dateFmt":"yyyy-MM-dd HH:mm"});' >
								</div>
						</div>
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									   id="search_LIKE_city" name="search_LIKE_city"
									   value="${param.search_LIKE_city}" placeholder="按举办城市查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									   id="search_LIKE_statiumName" name="search_LIKE_statiumName"
									   value="${param.search_LIKE_statiumName}" placeholder="按场馆名称查询">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_isIssueBonus"
										name="search_EQ_isIssueBonus">
									<option value=""  <c:if test="${empty param.search_EQ_isIssueBonus}"> selected</c:if>>--奖金发放状态--</option>
									<option value="0"  <c:if test="${'0' == param.search_EQ_isIssueBonus}"> selected</c:if>>--未发放--</option>
									<option value="2"  <c:if test="${'2' == param.search_EQ_isIssueBonus}"> selected</c:if>>--发放中--</option>
									<option value="1"  <c:if test="${'1' == param.search_EQ_isIssueBonus}"> selected</c:if>>--已发放--</option>
								</select>
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									   id="search_LIKE_name" name="search_LIKE_name"
									   value="${param.search_LIKE_name}" placeholder="按赛事名称查询">
							</div>

						</div>
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_isOffical"
										name="search_EQ_isOffical">
									<option value=""  <c:if test="${empty param.search_EQ_isOffical}"> selected</c:if>>--请选择发布方--</option>
									<option value="0"  <c:if test="${'0' == param.search_EQ_isOffical}"> selected</c:if>>--oss发布--</option>
									<option value="1"  <c:if test="${'1' == param.search_EQ_isOffical}"> selected</c:if>>--场馆发布--</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="reset" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 重 置
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="submitSearch()"  class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="exportData()" class="btn btn-success btn-sm">
									<span class="glyphicon glyphicon-export"></span> 导出
								</button>
							</div>
						</div>
						<input type="hidden" name="search_EQ_sportType" id="search_EQ_sportType" value="${param.search_EQ_sportType}" >
						<input type="hidden" name="search_EQ_state" id="search_EQ_state" value="${param.search_EQ_state}" >
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">	
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>赛事名称</th>
								<th>赛事级别</th>
								<th>比赛方式</th>
								<th>站点(省-市)</th>
								<th>场馆</th>
								<th>奖金状态</th>
								<th>开始时间  ↓</th>
								<th>结束时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="event" varStatus="stat">
							  <c:set var="status_class" value="" />
								<c:if test="${event.state == 0 || empty event.state}">
									<c:set var="status_class" value="warning" />
								</c:if>
								<c:if test="${event.state == 1}">
									<c:set var="status_class" value="success" />
								</c:if>
								<c:if test="${event.state == 2}">
									<c:set var="status_class" value="info" />
								</c:if>
								<c:if test="${event.gamesLength ge 0}">
									<c:set var="gamess_length" value="${event.gamesLength + 1}" />
								</c:if>
								
								<tr class="${status_class }" >
									<input type="hidden" id="eventId_${stat.count}"
										value="${event.id }" />
									<td class="text-center">${stat.count}</td>
								<td >
									${event.name}杯Open-CTA乐享赛
								</td>
								<td><c:if test="${event.gameLevel == '1'}">乐享一级 &nbsp;</c:if> 
								<c:if test="${event.gameLevel == '2'}">乐享二级 &nbsp;</c:if>
								<c:if test="${event.gameLevel == '3'}">乐享三级 &nbsp;</c:if> 
								<c:if test="${event.gameLevel == '4'}">乐享四级 &nbsp;</c:if> </td>
								<td><c:if test="${'1' == event.gameType}">男单</c:if>
								<c:if test="${event.gameType == '2'}">女单 &nbsp;</c:if> 
								<c:if test="${event.gameType == '3'}">男双 &nbsp;</c:if> 
								<c:if test="${event.gameType == '4'}">女双 &nbsp;</c:if>
								<c:if test="${event.gameType == '5'}">混双 &nbsp;</c:if> 
								<c:if test="${event.gameType == '6'}">混单 &nbsp;</c:if>
								<c:if test="${event.gameType == '7'}">无性别限制双打 &nbsp;</c:if>
								</td>
								<td >
									${event.siteName}
								</td>
								<td >
									${event.statiumName}
								</td>
								<td >
									${event.stateValue}
								</td>
									<td >${event.startTime}</td>
									<td >${event.endTime}</td>
								<td>
									<a target="_blank"  href="${ctx }/enjoyRace/bonusManage?gameId=${event.id}&gameLevel=${event.gameLevel}" class="btn btn-default btn-sm"><i
									class="glyphicon glyphicon glyphicon-cog"></i> 奖金管理</a>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</table></form>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
				<tags:pagination page="${data}" />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#bonus-man');
			$('#adminFooter').hide();
			
			  /* 按状态查询 */
			  $(".searchStatus").click(function(){
				  var v = $(this).val();
				  $("#search_EQ_state").val(v);
				  $("#search_form").submit();
			  });
			
			var isOffical = '${param.search_LIKE_isOffical }';

			if (isOffical) {
				$("select[name=search_LIKE_isOffical] option[value=" + isOffical + "]").attr("selected", "selected");
			}

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

		});
		
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

		function exportData() {
			var $form = $('#search_form');
			$form.attr("action","${ctx }/enjoyRace/exportBonusList");
			$form[0].submit();
		}
		function submitSearch(){
			var $form = $('#search_form');
			$form.attr("action","${ctx}/enjoyRace/bonusList");
			$form[0].submit();
		}
		
	</script>
</body>
</html>