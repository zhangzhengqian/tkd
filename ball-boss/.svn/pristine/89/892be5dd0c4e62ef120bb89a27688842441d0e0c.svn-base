<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>赛事列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 赛事列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/enjoyRace/list" method="post">
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
						<div class="col-md-5">
							<input type="text" class="form-control input-sm"
								   id="search_LIKE_city" name="search_LIKE_city"
								   value="${param.search_LIKE_city}" placeholder="按举办城市">
						</div>
						<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									   id="search_LIKE_statiumName" name="search_LIKE_statiumName"
									   value="${param.search_LIKE_statiumName}" placeholder="按场馆名称查询">
						</div>
					</div>
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									   id="search_LIKE_name" name="search_LIKE_name"
									   value="${param.search_LIKE_name}" placeholder="按赛事名称查询">
							</div>
							<div class="col-md-5">
								<select   class="form-control" id="search_EQ_isScoreNotice" name="search_EQ_isScoreNotice">
									<option <c:if test="${empty param.search_EQ_isScoreNotice}">selected</c:if>  value="">--是否发布成绩公告--</option>
									<option
											<c:if test="${'0' == param.search_EQ_isScoreNotice}">selected</c:if>
											id="option0" value="0">--未发布公告--</option>
									<option
											<c:if test="${'1' == param.search_EQ_isScoreNotice}">selected</c:if>
											id="option1" value="1">--已发布公告--</option>
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
							<div class=" col-md-5 ">
								<input type="text" name="search_LIKE_expiryDate"
									   placeholder="报名截止时间" value="${param.search_LIKE_expiryDate }"
									   id="search_LIKE_expiryDate" class="form-control"
									   onclick='WdatePicker({"dateFmt":"yyyy-MM-dd"});' >
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_isShow"
										name="search_EQ_isShow">
									<option value=""  <c:if test="${empty param.search_EQ_isShow}"> selected</c:if>>--请选择在APP显示状态--</option>
									<option value="1"  <c:if test="${'1' == param.search_EQ_isShow}"> selected</c:if>>--显示--</option>
									<option value="0"  <c:if test="${'0' == param.search_EQ_isShow}"> selected</c:if>>--不显示--</option>
								</select>
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

						<!-- 带有约束的日期条件，开始－结束 -->
						<!-- 注意：每个 form-group 占一行，显示两个列，其中带有 query-more 样式的行默认是隐藏的  -->


						<div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="reset" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 重 置
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="submitSearch()" class="btn btn-primary btn-sm">
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
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-5 form-inline">
				    <div class="btn-group" role="group" aria-label="...">
						  <button value=""  type="button" class="searchStatus btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;全部状态</button>
						  <button value="0" type="button" class="searchStatus btn btn-warning btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;待审核</button>
						  <button value="1" type="button" class="searchStatus btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核通过</button>
						  <button value="2" type="button" class="searchStatus btn btn-info btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核拒绝</button>
					</div>
				</div>
				 <div class="col-md-7 text-right">
					<div class="btn-group-sm pull-right mtb10">
						<shiro:hasPermission name="enjoyrace:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/enjoyRace/create?action=create"><span
								class="glyphicon glyphicon-plus"></span> 添加赛事</a>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">	
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>赛事名称</th>
								<th>城市站数</th>
								<th>赛事级别</th>
								<th>比赛方式</th>
								<th>成绩公告状态</th>
								<th>开始时间  ↓</th>
								<th>结束时间</th>
								<th>报名截止时间</th>
								<th>站点</th>
								<th>场馆</th>
								<th>报名人数</th>
								<th>审核状态</th>
								<th>状态</th>
								<th>创建人</th>
								<th>发布方</th>
								<th>推送类型</th>
								<th>推送状态</th>
								<th>推送时间</th>
								<th>是否显示</th>
								<th>赛事管理</th>
								<th>基本操作</th>
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
									<td class="text-center" rowspan="${gamess_length}">${stat.count}</td>
								<td rowspan="${gamess_length}">
									<a a href="${ctx}/enjoyRace/view/${event.id}?action=auti"> ${event.name}</a>
								</td>
								<td rowspan="${gamess_length}">${event.province}-${event.city}
			                       <c:if test="${event.cityHoldTimes gt 0}">
									   第${event.cityHoldTimes}站
								   </c:if>
								</td>
								<td rowspan="${gamess_length}"><c:if test="${event.gameLevel == '1'}">乐享一级 &nbsp;</c:if>
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
								<td>
									<c:if test="${event.isScoreNotice == 0}" >
										未发布公告
									</c:if>
									<c:if test="${event.isScoreNotice == 1}">
										已发布公告
									</c:if>
									<c:if test="${event.isScoreNotice == 2}">
										待发布公告
									</c:if>
								</td>
									<td rowspan="${gamess_length}">${event.startTime}</td>
									<td rowspan="${gamess_length}">${event.endTime}</td>
									<td rowspan="${gamess_length}">${event.expiryDate} </td>
									<td rowspan="${gamess_length}">${event.siteName } </td>
									<td rowspan="${gamess_length}">${event.statiumName } </td>
									<td>${event.applicantNumber } </td>
									<td rowspan="${gamess_length}">
									<c:if test="${event.state == 0 }">
										待审核
									</c:if>
									<c:if test="${event.state == 1 }">
										通过
									</c:if>
									<c:if test="${event.state == 2 }">
										不通过
									</c:if>
									 </td>
									<td rowspan="${gamess_length}">${event.stateValue}</td>
									
									<td rowspan="${gamess_length}"><tags:getUserById id="${event.cb }" /></td>
									<td rowspan="${gamess_length}">
										<c:if test="${event.isOffical == 0}">
											OSS发布
										</c:if> <c:if test="${event.isOffical == 1}">
											CRM发布
										</c:if> 
									</td>
									<td rowspan="${gamess_length}">
										<c:if test="${'0' == event.isPush}">即时推送</c:if>
										<c:if test="${'1' == event.isPush}">定时推送</c:if>
										<c:if test="${'2' == event.isPush || empty event.isPush}">未推送</c:if>
										<c:if test="${'3' == event.isPush}">推送管理即时推送</c:if>
										<c:if test="${'4' == event.isPush}">推送管理定时推送</c:if>
									</td>
									<td rowspan="${gamess_length}">
										<c:if test="${'0' == event.pushState || empty event.pushState}">未推送</c:if>
										<c:if test="${'1' == event.pushState}">已推送</c:if>
									</td>
									<td rowspan="${gamess_length}"><fmt:formatDate value="${event.factPushTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td rowspan="${gamess_length}">
											<c:if test="${event.isShow == 1}">  显示 
											</c:if>
											<c:if test="${event.isShow == 0 || empty event.isShow}">
												  隐藏
											</c:if>
									
									</td>
									<td>
										<shiro:hasPermission name="enjoyrace:view">
											<a target="_blank" href="${ctx }/enjoyRace/memberList/${event.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-cog"></i> 参赛成员</a>
										</shiro:hasPermission> 
										<c:if test="${event.gameState!=0 }">
										<shiro:hasPermission name="enjoyrace:update">
											<a target="_blank" href="${ctx }/enjoyRace/score?gameId=${event.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i>比分录入</a>
										</shiro:hasPermission> 
										</c:if>
										<c:if test="${event.pauseFlag == 1}">
										 <a a href="${ctx}/enjoyRace/pause?id=${event.id}&state=0" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 开启报名</a>
										</c:if>
										<c:if test="${event.pauseFlag == 0 || empty event.pauseFlag}">
											<a href="${ctx}/enjoyRace/pause?id=${event.id}&state=1" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-remove"></i>暂停报名</a>
										</c:if>
									</td>
									<td rowspan="${gamess_length}">
										<c:if test="${event.gameState!=0 }">
											<shiro:hasPermission name="enjoyrace:update">
													<a target="_blank" href="${ctx }/enjoyRace/scheduleView/${event.id}?action=edit" class="btn btn-default btn-sm"><i
														class="glyphicon glyphicon glyphicon-cog"></i> 查看赛程</a>
											</shiro:hasPermission>
										</c:if>
										<c:if test="${event.stateValue=='已结束'&&event.balanceFlag==0 }">
											<a target="_blank" href="${ctx }/enjoyRace/balanceList/${event.id}" class="btn btn-danger btn-sm"><i
														class="glyphicon glyphicon glyphicon-usd"></i> 结账</a>
										</c:if>
										<a target="_blank" href="${ctx}/enjoyRace/view/${event.id}"
										id="editLink-${event.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>赛事详情</a>
										<shiro:hasPermission name="enjoyrace:update">
												<a target="_blank" class="btn btn-default btn-sm"
													href="${ctx}/enjoyRace/update/${event.id}?action=edit"><i
													class="glyphicon glyphicon-edit"></i>赛事修改</a>
											</shiro:hasPermission> 
										<c:if test="${sevent.pauseFlag == 1}">
										<shiro:hasPermission name="enjoyrace:update">
											<a target="_blank"  href="${ctx }/enjoyRace/eventMsg?gameId=${event.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 赛事通知</a>
										</shiro:hasPermission>
										</c:if>
										<shiro:hasPermission name="enjoyrace:unstick">
											<c:if test="${!empty event.stick && event.stick != 0}">
												<a href="${ctx}/enjoyRace/unstick/${event.id}"
													id="editLink-${event.id}" class="btn btn-default btn-sm"><i
													class="glyphicon glyphicon glyphicon-remove"></i> 取消置顶</a>
											</c:if>
										</shiro:hasPermission>
									 <shiro:hasPermission name="enjoyrace:stick">
											<c:if test="${event.stick == 0 || empty event.stick}">
												<a href="${ctx}/enjoyRace/stick/${event.id}"
													id="editLink-${event.id}" class="btn btn-default btn-sm"><i
													class="glyphicon glyphicon glyphicon-arrow-up"></i> 置顶</a>
											</c:if>
											
									</shiro:hasPermission> 
									<shiro:hasPermission name="enjoyrace:show">
									<c:if test="${event.isShow == 1}">
										<a href="${ctx}/enjoyRace/show?eventId=${event.id}&isShow=0"
											id="editLink-${event.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 隐藏</a>
									</c:if>
									<c:if test="${event.isShow == 0 || empty event.isShow}">
										<a href="${ctx}/enjoyRace/show?eventId=${event.id}&isShow=1"
											id="editLink-${event.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 显示</a>
									</c:if>
									</shiro:hasPermission>
									<shiro:hasPermission name="enjoyrace:delete">
										 <a class="btn btn-danger btn-sm" href="#"
											onclick="deleteById('${event.id}')"> 
											<i class="glyphicon glyphicon-trash"></i> 删除
										</a>
									</shiro:hasPermission>
									</td>
								</tr>
								<c:forEach items="${event.games }" var="info">
									<tr class="${status_class }" >
										<td><c:if test="${'1' == info.gameType}">男单</c:if>
								<c:if test="${info.gameType == '2'}">女单 &nbsp;</c:if> 
								<c:if test="${info.gameType == '3'}">男双 &nbsp;</c:if> 
								<c:if test="${info.gameType == '4'}">女双 &nbsp;</c:if>
								<c:if test="${info.gameType == '5'}">混双 &nbsp;</c:if> 
								<c:if test="${info.gameType == '6'}">混单 &nbsp;</c:if>
								<c:if test="${info.gameType == '7'}">无性别限制双打 &nbsp;</c:if>
								</td>
								<td>
									<c:if test="${event.isScoreNotice == 0}" >
										未发布公告
									</c:if>
									<c:if test="${event.isScoreNotice == 1}">
										已发布公告
									</c:if>
									<c:if test="${event.isScoreNotice == 2}">
										待发布公告
									</c:if>
								</td>
								<td>${info.applicantNumber } </td>
								<td>
									<shiro:hasPermission name="enjoyrace:update">
										<a target="_blank"  href="${ctx }/enjoyRace/memberList/${info.id}" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-cog"></i> 参赛成员</a>
									</shiro:hasPermission> 
									<c:if test="${info.gameState == 1 || info.gameState == 2 }">
									<shiro:hasPermission name="enjoyrace:update">
										<a target="_blank"  href="${ctx }/enjoyRace/score?gameId=${info.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-cog"></i>比分录入</a>
									</shiro:hasPermission> 
									</c:if>
									<c:if test="${info.pauseFlag == 1}">
									 <a  href="${ctx}/enjoyRace/pause?id=${info.id}&state=0" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-remove"></i> 开启报名</a>
									</c:if>
									<c:if test="${info.pauseFlag == 0 || empty info.pauseFlag}">
										<a href="${ctx}/enjoyRace/pause?id=${info.id}&state=1" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i>暂停报名</a>
									</c:if>
								</td>
								</tr>
								</c:forEach>
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
			menu.active('#enjoyrace-man');
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
		
		function deleteById(id){
			  var $form = $('#actionForm');
			  bootbox.confirm('您确定要删除该赛事吗？', function(result) {
			    if(result) {
			      Util.getData(ctx + '/enjoyRace/delGames', function(data){
			      	 if(data.result){
				      	 myAlert("删除赛事成功");
				      	 window.location.reload()
				     }else{
				    	 myAlert("赛事删除失败","error");
					 }
			      }, null, {"id":id}, 'post');
			    }
			  });
			  return false;
			}

		function exportData() {
			var $form = $('#search_form');
			$form.attr("action","${ctx }/enjoyRace/exportEnjoyGame");
			$form[0].submit();
		}

		function submitSearch(){
			var $form = $('#search_form');
			$form.attr("action","${ctx}/enjoyRace/list");
			$form[0].submit();
		}
	</script>
</body>
</html>