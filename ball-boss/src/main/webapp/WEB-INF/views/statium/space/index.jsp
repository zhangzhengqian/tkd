<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>场馆管理</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span><a
					href="${ctx }/statium/"> 场馆列表 </a></li>
				<li class="active">场地管理</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row mtb10">
				<!-- 查询条件 -->
				<div class="col-md-10">
					<form class="form-inline" action="${ctx }/statium/space">
						<div class="form-group">
							<input type="hidden" name="statiumId" value="${statiumId}" /> <label
								class="sr-only" for="search_EQ_sportType">按类型查找：</label> <select
								class="form-control input-sm" id="search_EQ_sportType"
								name="search_EQ_sportType">
								<option value="">请选择类型</option>
								<c:forEach items="${fn:split(sportTypes,';;') }" var="item">
									<option value="${item}"
										<c:if test="${param.search_EQ_sportType eq item }">selected</c:if>>
										<c:if test="${item eq 'BOWLING' }">保龄球</c:if>
										<c:if test="${item eq 'BILLIARDS' }">台球</c:if>
										<c:if test="${item eq 'TABLE_TENNIS' }">乒乓球</c:if>
										<c:if test="${item eq 'FOOTBALL' }">足球</c:if>
										<c:if test="${item eq 'BASKETBALL' }">篮球</c:if>
										<c:if test="${item eq 'TENNIS' }">网球</c:if>
										<c:if test="${item eq 'GOLF' }">高尔夫</c:if>
										<c:if test="${item eq 'BADMINTON' }">羽毛球</c:if>
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-search"></span> 搜索
							</button>
						</div>
						<div class="form-group">
							<a class="btn btn-primary btn-sm"
								href="${ctx }/statium/space/createForm?statiumId=${statiumId}"><span
								class="glyphicon glyphicon-plus"></span> 新建</a>
						</div>
					</form>
				</div>
				<div class="col-md-12 text-right form-inline">
					<button type="button" class="btn btn-sm btn-danger"
						onclick="deleteBySelected('${statiumId }')">
						<span class="glyphicon glyphicon-remove"></span>批量删除
					</button>
					<button type="button" class="btn btn-sm btn-info"
						onclick="updateBySelected('${statiumId }')">
						<span class="glyphicon glyphicon-edit"></span>批量修改
					</button>
					<button type="button" class="btn btn-sm btn-warning"
						onclick="searchBySelected('${statiumId }')">
						<span class="glyphicon glyphicon-search"></span>批量查看
					</button>
				</div>
			</div>
			<!-- /查询条件 -->

			<table id="contentTable" class="table table-bordered table-hover">
				<thead class="thead">
					<tr>
						<th class="text-center"><input type="checkbox" name="chk_all"
							onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
						<th class="text-center col-md-2">编号</th>
						<th class="text-center col-md-2">名称</th>
						<th class="text-center col-md-2">类型</th>
						<th class="text-center col-md-2">备注</th>
						<th class="text-center col-md-2">状态</th>
						<th class="text-center col-md-3">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${data.content}" var="space" varStatus="stat">
						<tr>
							<td class="text-center"><input type="checkbox"
								name="chk_list" id="chk_list_${stat.index }" value="${space.id}" /></td>
							<td class="text-center"><a
								href="${ctx}/statium/space/detailForm?id=${space.id}">
									${space.spaceCode }</a></td>
							<td class="text-center">${space.spaceName }</td>
							<td class="text-center">${lf:dicItem(space.sportType).itemName }</td>
							<td class="text-center">${space.comment }</td>
							<td class="text-center"><c:if test="${space.status == 0}">停用</c:if>
								<c:if test="${space.status == 1}">启用</c:if></td>
							<td><shiro:hasPermission name="space:updateForm">
									<a class="btn-info btn-sm"
										href="${ctx}/statium/space/updateForm?id=${space.id}"><i
										class="glyphicon glyphicon-edit"></i> 修改</a>
									</a>
								</shiro:hasPermission> <shiro:hasPermission name="space:delete">
									<a class="btn-danger btn-primary btn-sm" href="#"
										onclick="deleteById('${space.id}','${statiumId }')"> <i
										class="glyphicon glyphicon-trash"></i> 删除
									</a>
									</a>
								</shiro:hasPermission> <c:if test="${space.status == 0}">
									<a href="#" class="btn-info btn-sm"><i
										class="glyphicon glyphicon-ok-circle"></i> 启用</a>
									</a>
								</c:if> <c:if test="${space.status == 1}">
									<a href="#" class="btn-warning btn-sm"><i
										class="glyphicon glyphicon-ban-circle"></i> 停用</a>
									</a>
								</c:if></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>

			<tags:pagination page="${data}" />
			<tags:errors />
			<form id="actionForm" action="" method="post">
				<input type="hidden" id="ids" name="ids">
			</form>
		</div>
		<!-- /右侧主体内容 -->
	</div>

	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#statium-man');
		});

		function deleteById(id, statiumId) {
			var $form = $('#actionForm');
			bootbox.confirm('您确定要删除该场地吗？', function(result) {
				if (result) {
					Util.getData(ctx + '/statium/space/delete', function(data) {
						if (data.result) {
							window.location.reload()
						} else {
							myAlert(data.reason, "error");
							return false;
						}
					}, null, {
						"id" : id,
						"statiumId" : statiumId
					}, 'post');
				}
			});
			return false;
		}

		//批量删除场地,未完成订单的不删除
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

		function deleteBySelected(statiumId) {
			var $form = $('#actionForm');
			var ids = getSelected();
			if (ids.length == 0) {
				bootbox.alert('请选择要删除的场地！');
				return false;
			}
			bootbox.confirm('您确定要删除选中的场地吗？', function(result) {
				if (result) {
					Util.getData(ctx + '/statium/space/deleteAll', function(
							data) {
						if (data.result) {
							window.location.reload()
						} else {
							myAlert("场地删除失败", "error");
							return false;
						}
					}, null, {
						"ids" : ids.join(';'),
						"statiumId" : statiumId
					}, 'post');
				}
			});
			return false;
		}
		function updateBySelected(statiumId) {
			var $form = $('#actionForm');
			var ids = getSelected();
			if (ids.length == 0) {
				bootbox.alert('请选择要修改的场地！');
				return false;
			}
			window.location.href = "${ctx}/statium/space/batchUpdate/"+ids.join(',');
			return false;
		}
		function searchBySelected(statiumId) {
			var $form = $('#actionForm');
			var ids = getSelected();
			if (ids.length == 0) {
				bootbox.alert('请选择要查看的场地！');
				return false;
			}
			window.location.href = "${ctx}/statium/space/batchSearch/"+ids.join(',');
			return false;
		}
	</script>
</body>
</html>