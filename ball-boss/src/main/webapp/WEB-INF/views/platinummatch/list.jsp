<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>白金赛列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>白金赛列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/platinumMatch/list" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_name" name="search_LIKE_name"
									value="${param.search_LIKE_name}" placeholder="按赛事名称查询">
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
				<div class="col-md-12 form-inline">
					<div class="btn-group-sm pull-right mtb10">
						<shiro:hasPermission name="platinumMatch:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/platinumMatch/create?action=create">
								<span class="glyphicon glyphicon-plus"></span> 添加白金赛
							</a>
							<a class="btn btn-primary btn-sm" href="${ctx}/html5/importSchedule">
								<span class="glyphicon glyphicon-plus"></span> 上传赛程
							</a>
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
								<th>比赛方式</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>报名截止时间</th>
								<th>创建人</th>
								<th>参赛人员</th>
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
									<td class="text-center" rowspan="${gamess_length}">${stat.count}</td>
								<td rowspan="${gamess_length}">
									<a a href="${ctx}/platinumMatch/view/${event.id}?action=auti"> ${event.name}</a>
								</td>
								<td>  
										<c:if test="${1 == event.type}">男子单打</c:if>
										<c:if test="${event.type == 2}">女子单打 &nbsp;</c:if>
										<c:if test="${event.type == 3}">青年组男子双打 &nbsp;</c:if>
										<c:if test="${event.type == 4}">常青组男子双打 &nbsp;</c:if>
										<c:if test="${event.type == 5}">青年组女子双打 &nbsp;</c:if>
										<c:if test="${event.type == 6}">常青组女子双打 &nbsp;</c:if>
										<c:if test="${event.type ==7}">青年组混合双打 &nbsp;</c:if>
										<c:if test="${event.type == 8}">常青组混合双打 &nbsp;</c:if>
								</td>
									<td rowspan="${gamess_length}">${event.startTime}</td>
									<td rowspan="${gamess_length}">${event.endTime}</td>
									<td rowspan="${gamess_length}">${event.expiryDate} </td>
									<td rowspan="${gamess_length}"><tags:getUserById id="${event.cb }" /></td>
									<td>
											<a href="${ctx }/platinumMatch/memberList/${event.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-cog"></i> 参赛成员</a>
									</td>
									<td rowspan="${gamess_length}">
										<a href="${ctx}/platinumMatch/view/${event.id}"
										id="editLink-${event.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>赛事详情</a>
										<c:if test="${event.status == -1}">
										<shiro:hasPermission name="platinumMatch:update">
												<a class="btn btn-default btn-sm"
													href="${ctx}/platinumMatch/update/${event.id}?action=edit"><i
													class="glyphicon glyphicon-edit"></i>赛事修改</a>
											</shiro:hasPermission> 
										</c:if>
									<shiro:hasPermission name="platinumMatch:delete">
										 <a class="btn btn-danger btn-sm" href="#"
											onclick="deleteById('${event.id}')"> 
											<i class="glyphicon glyphicon-trash"></i> 删除
										</a>
									</shiro:hasPermission>
									</td>
								</tr>
								<c:forEach items="${event.games }" var="info">
									<tr class="${status_class }" >
										<td>
											<c:if test="${1 == info.type}">男子单打</c:if>
										<c:if test="${info.type == 2}">女子单打 &nbsp;</c:if>
										<c:if test="${info.type == 3}">青年组男子双打 &nbsp;</c:if>
										<c:if test="${info.type == 4}">常青组男子双打 &nbsp;</c:if>
										<c:if test="${info.type == 5}">青年组女子双打 &nbsp;</c:if>
										<c:if test="${info.type == 6}">常青组女子双打 &nbsp;</c:if>
										<c:if test="${info.type ==7}">青年组混合双打 &nbsp;</c:if>
										<c:if test="${info.type == 8}">常青组混合双打 &nbsp;</c:if>
										<td>
									<shiro:hasPermission name="platinumMatch:update">
										<a href="${ctx }/platinumMatch/memberList/${info.id}" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-cog"></i> 参赛成员</a>
									</shiro:hasPermission> 
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
			menu.active('#platinumMatch-man');
			$('#adminFooter').hide();

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
			      Util.getData(ctx + '/platinumMatch/delGames', function(data){
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