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
				<li><span class="glyphicon glyphicon-home"></span> 赛程列表</li>
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
									id="search_LIKE_gameGroup" name="search_LIKE_gameGroup"
									value="${param.search_LIKE_gameGroup}" placeholder="按赛组称查询">
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
									placeholder="赛程开始时间" value="${param.search_LIKE_ct }"
									id="search_LIKE_ct" class="form-control"
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
						<shiro:hasPermission name="schedule:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/event/creatSchedule/${evnetId}"><span
								class="glyphicon glyphicon-plus"></span> 添加赛程</a>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->
			<br>

			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>轮次</th>
								<th>比赛方式</th>
								<th>组名称</th>
								<th>对阵战队A</th>
								<th>对阵战队B</th>
								<th>赛事开始时间</th>
								<th>比分</th>
								<th>积分</th>
								<th>创建人</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach  items="${list}" var="data" varStatus="stat">
							<tr>
									<input type="hidden" id="eventId_${stat.count}" value="${data.id }" />
									<td class="text-center">${stat.count}</td>
									<td>${data.turn}</td>
									<td><c:if test="${data.gameType == '0'}">
											个人报名
										</c:if> <c:if test="${data.gameType == '1'}">
											团体报名
										</c:if></td>
									<td>${data.gameGroup}</td>
									<td>${data.marinesIdA}</td>
									<td>${data.marinesIdB}</td>
									<td>${data.gameTime }</td>
									<td>${data.scoreA} : ${data.scoreB} </td>
									<td>${data.integrationA} : ${data.integrationB} </td>
									<td><tags:getUserById id="${data.cb}" /></td>
									<td>${data.state}</td>
									<td>	
									<shiro:hasPermission name="schedule:update">
										<a class="btn btn-default btn-sm"
												href="${ctx }/event/updateSchedule/${data.id}"><i
												class="glyphicon glyphicon-edit"></i>修改</a>
									</shiro:hasPermission>		
									<shiro:hasPermission name="schedule:delete">
									 <a class="btn btn-danger btn-sm" href="#"
										onclick="deleteById('${data.id}')"> <i
											class="glyphicon glyphicon-trash"></i> 删除
									</a></shiro:hasPermission></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
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

			var typeValue = '${param.search_EQ_type }';

			if (typeValue) {
				$("select[name=search_EQ_type] option[value=" + typeValue + "]").attr("selected", "selected");
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
		
	function deleteById(id) {
		  var $form = $('#actionForm');
		  bootbox.confirm('您确定要删除该赛程吗？', function(result) {
		    if(result) {
		      Util.getData(ctx + '/event/delSchedule', function(data){
		      	 if(data.result){
			      	 myAlert("删除赛程成功");
			      	 window.location.reload()
			     }else{
			    	 myAlert("赛程删除失败","error");
				 }
		      }, null, {"id":id}, 'post');
		    }
		  });
		  return false;
		}
		
	</script>
</body>
</html>