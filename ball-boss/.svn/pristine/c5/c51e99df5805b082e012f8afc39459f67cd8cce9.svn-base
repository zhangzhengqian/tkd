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
						action="${ctx}/event/list" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_address" name="search_LIKE_name"
									value="${param.search_LIKE_name}" placeholder="按赛事名称查询">
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_LIKE_sportType"
									name="search_LIKE_sportType">
									<option value="">--请选项目类型--</option>
									<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
										<option value="${item.itemCode }">--${item.itemName }--</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_sponsor" name="search_LIKE_sponsor"
									value="${param.search_LIKE_sponsor}" placeholder="按主办方查询">
							</div>
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_type"
									name="search_EQ_type">
									<option value="">--请选择赛事类别--</option>
									<option value="0"
										<c:if test="${'0' == param.search_EQ_type}">selected</c:if>>个人赛</option>
									<option value="1"
										<c:if test="${'1' == param.search_EQ_type}">selected</c:if>>团体赛</option>
								</select>
							</div>
						</div>

						<!-- 带有约束的日期条件，开始－结束 -->
						<!-- 注意：每个 form-group 占一行，显示两个列，其中带有 query-more 样式的行默认是隐藏的  -->
						<div class="form-group form-group-sm query-more">
							<lable class="control-label col-md-1 sr-only"></lable>
							<div class=" col-md-5 ">
								<input type="text" name="search_GTE_startTime"
									placeholder="赛事开始时间" value="${param.search_LIKE_ct }"
									id="search_LIKE_ct" class="form-control"
									onclick='WdatePicker({"dateFmt":"yyyy-MM-dd"});' readonly>
							</div>
							<div class=" col-md-5">
								<input type="text" name="search_LIKE_endTime"
									placeholder="赛事结束时间" id="search_LIKE_ct"
									value="${param.search_LIKE_ct }" class="form-control"
									onclick='WdatePicker({"dateFmt":"yyyy-MM-dd"});' readonly>
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
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
					<div class="btn-group-sm pull-right mtb10">
						<shiro:hasPermission name="event:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/event/create"><span
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
								<th>赛事类型</th>
								<th>运动类型</th>
								<th>赛事名称</th>
								<th>所在地区</th>
								<th>赛事总人/队数</th>
								<th>已报名人/队数</th>
								<th>比赛方式</th>
								<th>主办方</th>
								<th>赛事开始时间</th>
								<th>赛事结束时间</th>
								<th>报名截止时间</th>
								<th>创建人</th>
								<th>状态</th>
								<th>推送类型</th>
								<th>推送状态</th>
								<th>推送时间</th>
								<th>是否显示</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data}" var="event" varStatus="stat">
								<tr>
									<input type="hidden" id="eventId_${stat.count}"
										value="${event.id }" />
									<td class="text-center">${stat.count}</td>
									<td><c:if test="${event.realNameSys == 1}">实名制</c:if>
									<c:if test="${event.realNameSys != 1}">非实名制</c:if></td>
									<td><c:if test="${event.sportType == 'BOWLING'}">保龄球 &nbsp;</c:if>
										<c:if test="${event.sportType == 'BILLIARDS'}">台球 &nbsp;</c:if>
										<c:if test="${event.sportType == 'TABLE_TENNIS'}">乒乓球 &nbsp;</c:if>
										<c:if test="${event.sportType == 'FOOTBALL'}">足球 &nbsp;</c:if>
										<c:if test="${event.sportType == 'BASKETBALL'}">篮球 &nbsp;</c:if>
										<c:if test="${event.sportType == 'TENNIS'}">网球 &nbsp;</c:if> <c:if
											test="${event.sportType == 'GOLF'}">高尔夫 &nbsp;</c:if> <c:if
											test="${event.sportType == 'BADMINTON'}">羽毛球 &nbsp;</c:if></td>
									<td>${event.name}</td>
									<td>${event.address }</td>
									<td>${event.totalNumber}</td>
									<td>${event.applicantNumber}</td>
									<td><c:if test="${event.type == 0}">
											个人报名
										</c:if> <c:if test="${event.type == 1}">
											团体报名
										</c:if></td>
									<td>${event.sponsor }</td>
									<td>${event.startTime}</td>
									<td>${event.endTime}</td>
									<td>${event.expiryDate}</td>
									<td><tags:getUserById id="${event.cb }" /></td>
									<td><c:if test="${event.state == 0}">
											报名中
										</c:if> <c:if test="${event.state == 1}">
											报名截止
										</c:if> <c:if test="${event.state == 2}">
											进行中
										</c:if> <c:if test="${event.state == 2}">
											进行中
										</c:if></td>
									<td>
										<c:if test="${'0' == event.isPush}">即时推送</c:if>
										<c:if test="${'1' == event.isPush}">定时推送</c:if>
										<c:if test="${'2' == event.isPush || empty event.isPush}">未推送</c:if>
										<c:if test="${'3' == event.isPush}">推送管理即时推送</c:if>
										<c:if test="${'4' == event.isPush}">推送管理定时推送</c:if>
									</td>
									<td>
										<c:if test="${'0' == event.pushState || empty event.pushState}">未推送</c:if>
										<c:if test="${'1' == event.pushState}">已推送</c:if>
									</td>
									<td><fmt:formatDate value="${event.factPushTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
											<c:if test="${event.isShow == 1}">  显示 
											</c:if>
											<c:if test="${event.isShow == 0 || empty event.isShow}">
												  隐藏
											</c:if>
									
									</td>
									<td><a href="${ctx}/event/view/${event.id}"
										id="editLink-${event.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>赛事详情</a>
									<shiro:hasPermission name="event:update">
											<a class="btn btn-default btn-sm"
												href="${ctx }/event/update/${event.id}"><i
												class="glyphicon glyphicon-edit"></i>赛事修改</a>
										</shiro:hasPermission> 
										<shiro:hasPermission name="event:update">
											<c:if test="${event.type == 0}">
												<a href="${ctx }/event/memberList/${event.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 参赛成员</a>
											</c:if>
											<c:if test="${event.type == 1}">
											<a href="${ctx }/event/eventCorps/${event.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 参赛队伍</a>
											</c:if>
										</shiro:hasPermission> 
										<shiro:hasPermission name="event:update">
											<a href="${ctx }/event/scheduleList/${event.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 赛程管理</a>
										</shiro:hasPermission> 
										<shiro:hasPermission name="event:update">
											<a href="${ctx }/event/score?gameId=${event.id}" class="btn btn-default btn-sm"><i
												class="glyphicon glyphicon glyphicon-cog"></i> 比分录入</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="event:unstick">
											<c:if test="${event.stick != 0}"> 
												<a href="${ctx}/event/unstick/${event.id}"
													id="editLink-${event.id}" class="btn btn-default btn-sm"><i
													class="glyphicon glyphicon glyphicon-remove"></i> 取消置顶</a>
											</c:if>
										</shiro:hasPermission>
									 <shiro:hasPermission name="event:stick">
											<c:if test="${event.stick == 0}">
												<a href="${ctx}/event/stick/${event.id}"
													id="editLink-${event.id}" class="btn btn-default btn-sm"><i
													class="glyphicon glyphicon glyphicon-arrow-up"></i> 置顶</a>
											</c:if>
											
									</shiro:hasPermission> 
									 <c:if test="${stat.count != 1}">
											<a class="btn btn-default btn-sm" href="#"
												onclick="moveUp('${event.id}','${stat.count-1}')"><i
												class="glyphicon glyphicon-edit"></i> 上移</a>
									</c:if> 
										
									<c:if test="${stat.count != size}"><a class="btn btn-default btn-sm" href="#"
										onclick="moveDown('${event.id}','${stat.count+1}')"><i
											class="glyphicon glyphicon-edit"></i> 下移</a></c:if> 
									<shiro:hasPermission name="event:show">
									<c:if test="${event.isShow == 1}">
										<a href="${ctx}/event/show/${event.id}/0"
											id="editLink-${event.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 隐藏</a>
									</c:if>
									<c:if test="${event.isShow == 0 || empty event.isShow}">
										<a href="${ctx}/event/show/${event.id}/1"
											id="editLink-${event.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 显示</a>
									</c:if>
								</shiro:hasPermission>
									 <a
										class="btn btn-danger btn-sm" href="#"
										onclick="deleteById('${event.id}')"> 
										<i class="glyphicon glyphicon-trash"></i> 删除
									</a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table></form>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
			<form id="actionForm" action="" method="post">
				<input type="hidden" id="ids" name="ids">
			</form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#event-man');
			$('#adminFooter').hide();

			var v_search_LIKE_sportType = '${param.search_LIKE_sportType}';
			if (v_search_LIKE_sportType) {
				$(
						'#search_LIKE_sportType option[value='
								+ v_search_LIKE_sportType + ']').attr(
						'selected', 'selected');
			}

			var typeValue = '${param.search_EQ_type }';

			if (typeValue) {
				$("select[name=search_EQ_type] option[value=" + typeValue + "]")
						.attr("selected", "selected");
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

		function moveUp(id, pre) {
			var id_pre = "eventId_" + pre;
			var eventId_pre = $('#' + id_pre).val();
			$.ajax({
				cache : true,
				type : "POST",
				url : '${ctx}/event/up/' + id + "/" + eventId_pre,
				data : {},
				async : false,
				error : function(request) {
					common.showMessage("上移成功！");
				},
				success : function(data) {
					data = eval("(" + data + ")");
					if (!data.result || data.result != 'success') {
						common.showMessage("上移失败！");
					} else {
						common.showMessage("上移成功！");
						setTimeout(function() {
							var $form = $('#search_form');
							$form[0].submit();
						}, 1000);
					}
				}
			});
		}

		function moveDown(id, next) {
			var id_next = "eventId_" + next;
			var eventId_next = $('#' + id_next).val();
			$.ajax({
				cache : true,
				type : "POST",
				url : '${ctx}/event/down/' + id + "/" + eventId_next,
				data : {},
				async : false,
				error : function(request) {
					common.showMessage("上移成功！");
				},
				success : function(data) {
					data = eval("(" + data + ")");
					if (!data.result || data.result != 'success') {
						common.showMessage("上移失败！");
					} else {
						common.showMessage("上移成功！");
						setTimeout(function() {
							var $form = $('#search_form');
							$form[0].submit();
						}, 1000);
					}
				}
			});
		}
		
		function deleteById(id){
			  var $form = $('#actionForm');
			  bootbox.confirm('您确定要删除该赛事吗？', function(result) {
			    if(result) {
			      Util.getData(ctx + '/event/delGames', function(data){
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
	</script>

</body>
</html>