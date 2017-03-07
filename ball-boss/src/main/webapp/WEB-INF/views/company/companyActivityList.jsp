<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业积分</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>企业活动列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/company/companyActivityList" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<select class="form-control" id="search_EQ_type"
										name="search_EQ_type">
									<option value="1"
											<c:if test="${ type == 1}">selected</c:if>>企业活动</option>
									<option value="2"
											<c:if test="${ type == 2}">selected</c:if>>企业赛事</option>
								</select>
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

			<!-- /操作按钮组 -->
			<br>

 			<c:if test="${type == 1}">
			<div class="row">
				<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">	
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>企业名称</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>类型</th>
								<th>场馆名称</th>
								<th>开始时间--结束时间</th>
								<th>费用（元）</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${data.content }" var="info" varStatus="stat">
						     <tr class="info" >
									<input type="hidden" id="eventId_${stat.count}"
										value="${event.id }" />
									<td class="text-center">${stat.count}</td>
								<td>
									 ${info.companyName}
								</td>
								<td>
									 ${info.linkMan}
								</td>
								<td>
									 ${info.linkPhone}
								</td>
								<td>
									<c:if test="${info.sportType == 'BOWLING'}">
										保龄球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'BILLIARDS'}">
										台球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'TABLE_TENNIS'}">
										乒乓球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'FOOTBALL'}">
										足球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'BASKETBALL'}">
										篮球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'TENNIS'}">
										网球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'GOLF'}">
										高尔夫 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'BADMINTON'}">
										羽毛球 &nbsp;
									</c:if>
								</td>
								<td>${info.statiumName}</td>
								 <td>${info.begin}  -- ${info.end} </td>
								 <td>
									 <c:choose>
										 <c:when test="${info.price == null || info.price == '' }">0</c:when>
										 <c:when test="${info.price != null || info.price != '' }">${info.price/100 }</c:when>
									 </c:choose>
								 </td>
								 <td>
									 <c:if test="${info.status == 1}">
									 <a href="${ctx}/company/createServicesAuto/${info.id}"
										id="editLink-${info.id}" class="btn btn-default btn-sm"><span
											 class="glyphicon glyphicon-search"></span>创建服务订单</a>
									 </c:if>
								 </td>
								</tr>
							</c:forEach>
						</tbody>
					</table></form>
				</div>
				<!-- end col-table -->
			</div>
			<tags:pagination page="${data}" />
			</c:if>
			<c:if test="${type ==2}">
			<div class="row">
					<div class="col-table col-md-12"><form id="actionForm" class="form-horizontal"  method="post">
						<table id="contentTable"
							   class="table table-bordered table-condensed table-hover">
							<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>企业名称</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>类型</th>
								<th>对手企业名称</th>
								<th>对手企业联系人</th>
								<th>对手企业联系电话</th>
								<th>比赛时间</th>
								<th>获胜方</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${data.content }" var="info" varStatus="stat">
							<tr class="info" >
								<input type="hidden" id="eventId_${stat.count}"
									   value="${event.id }" />
								<td class="text-center">${stat.count}</td>
								<td>
									${info.companyName}
								</td>
								<td>
									${info.linkMan}
								</td>
								<td>
									${info.linkPhone}
								</td>
								<td>
									<c:if test="${info.sportType == 'BOWLING'}">
										保龄球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'BILLIARDS'}">
										台球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'TABLE_TENNIS'}">
										乒乓球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'FOOTBALL'}">
										足球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'BASKETBALL'}">
										篮球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'TENNIS'}">
										网球 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'GOLF'}">
										高尔夫 &nbsp;
									</c:if>
									<c:if test="${info.sportType == 'BADMINTON'}">
										羽毛球 &nbsp;
									</c:if>
								</td>
								<td>${info.opponentsName}</td>
								<td>${info.opponentsLinkUser} </td>
								<td>${info.opponentsLinkPhone} </td>
								<td>${info.gameTime} </td>
								<td>${info.winner} </td>
								<td><c:if test="${info.status == 1}">
									   待审核
								</c:if>
									<c:if test="${info.status == 2}">
										通过
									</c:if>
									<c:if test="${info.status == 3}">
										不通过
									</c:if>
								</td>
								<td> <c:if test="${info.status == 1}">
									<a href="#" onclick="audiCompanyGame('${info.id}',2)"
									   id="editLink-${info.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>确认通过</a>
									<a href="#" onclick="audiCompanyGame('${info.id}',3)"
									   id="editLink-${info.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>不通过</a>
								</c:if></td>
							</tr>
							</c:forEach>
							</tbody>
						</table></form>
					</div>
					<!-- end col-table -->
				</div>
			<!-- end row -->
			<tags:pagination page="${data}" />
			</c:if>
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
	$(function() {
		if("${message}" != null && "${message}" != ""){
			myAlert("${message}");
		}
		menu.active('#companyActivity-man');
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

	//审核
	function audiCompanyGame(id,state ){
		var $form = $('#actionForm');
		Util.getData(ctx + '/company/audiCompanyGame/', function(data){
			if(data.result){
				window.location.reload()
			}else{
				myAlert(data.reason,"error");
			}
		}, null, {"id":id,"state":state}, 'post');
	}

</script>
</body>
</html>