<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>道馆列表</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 教练列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">

					<form class="form-horizontal" id="coachForm" action="${ctx}/coach"
						method="post">

						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_name" name="search_LIKE_name"
									value="${param.search_LIKE_name }" placeholder="按教练名查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_dgName" name="search_LIKE_dgName"
									value="${param.search_LIKE_dgName }" placeholder="按所属道馆查询">
							</div>
						</div>
						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_coachNo" name="search_LIKE_coachNo"
									value="${param.search_LIKE_coachNo }" placeholder="按教练号查询">
							</div>
							<div class="col-md-5">
								<select class="form-control" name="search_EQ_activeState"
									id="search_EQ_activeState">
									<option id="option24" value="">--请选择激活状态--</option>
									<option id="option25" value="1">--已激活--</option>
									<option id="option26" value="0">--未激活--</option>
								</select>
							</div>
							<%-- <div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_cardId" name="search_LIKE_cardId"
									value="${param.search_LIKE_cardId }" placeholder="按用身份证号查询">
							</div> --%>

						</div>
						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<select class="form-control" name="search_EQ_sex"
									id="search_EQ_sex">
									<option id="option5" value="">--请选择性别--</option>
									<option id="option6" value="男"
										<c:if test="${param.search_EQ_sex eq '男'}">selected='selected'</c:if>>--男--</option>
									<option id="option7" value="女"
										<c:if test="${param.search_EQ_sex eq '女'}">selected='selected'</c:if>>--女--</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button id="resetButton" type="reset"
									class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span> 重 置
								</button>
								&nbsp;&nbsp;
								<button type="submit" id="search_btn"
									class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								<button type="button" class="btn btn-link btn-sm"
									id="btn-query-more">
									<span class="glyphicon glyphicon-chevron-down"></span> 更多条件
								</button>
							</div>
						</div>
						<input type="hidden" id="v_search_EQ_activeState"
							value="${param.search_EQ_activeState}">
					</form>
				</div>

			</div>
			<!-- /查询条件 -->

			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12 text-right">
				<shiro:hasPermission name="coach:create">
					<a class="btn btn-primary btn-sm"
						href="${ctx }/coach/createForm?action=create"><span
						class="glyphicon glyphicon-plus"></span> 添加教练</a>
				</shiro:hasPermission>	
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
								<th>教练</th>
								<th>教练号</th>
								<th>性别</th>
								<th>所属道馆</th>
								<th>类型</th>
								<th>级段位</th>
								<th>是否身份认证</th>
								<th>是否级/段位认证</th>
								<th>教龄</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="coach" varStatus="stat">
								<tr class="${status_class }">
									<td class="text-center">${stat.count}</td>
									<td><a href="${ctx }/coach/detailForm?id=${coach.id}">
											${coach.name}</a></td>
									<td>${coach.coachNo}</td>
									<td>${coach.sex }</td>
									<td>${coach.dgName }</td>
									<td><c:choose>
											<c:when test="${coach.type=='0' }">
												大课教练	
											</c:when>
											<c:when test="${coach.type=='1' }">
												私人教练	
											</c:when>
										</c:choose></td>
									<td>${coach.danlevel }</td>
									<td><c:choose>
											<c:when test="${coach.iscard=='0' }">
												未认证	
											</c:when>
											<c:when test="${coach.iscard=='1' }">
												已认证	
											</c:when>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${coach.isdan=='0' }">
												未认证	
											</c:when>
											<c:when test="${coach.isdan=='1' }">
												已认证	
											</c:when>
										</c:choose></td>
									<td>${coach.teachyear}</td>
									<td>
										<shiro:hasPermission name="coach:edit">
										<a class="btn btn-default btn-sm" href="${ctx }/coach/detailForm?id=${coach.id }&action=edit"><iclass="glyphicon glyphicon-edit"></i> 修改</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="coach:deleteCoach">
										<a class="btn btn-sm btn-default" href="#" onclick="deleteById('${coach.id }')"> <span class="glyphicon glyphicon-edit">删除</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="coachClassInfo:list">
										<a class="btn btn-default btn-sm" href="${ctx }/coachClassInfo/list?id=${coach.id}"></i> 课时查看</a>
										</shiro:hasPermission>
									</td>
									<%-- 	<a class="btn btn-default btn-sm" href="${ctx }/statiumClass/list?dgid=${statium.id}"><i class="glyphicon glyphicon-cog"></i> 课程设置</a></a> --%>


								</tr>
							</c:forEach>
						</tbody>
					</table>
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

		<script src="${ctx}/static/js/utils.js"></script>
		<script type="text/javascript">
			$(function() {
				menu.active('#coach-man');

				$('#adminFooter').hide();

				var typeValue = '${param.search_EQ_type }';

				if (typeValue) {
					$(
							"select[name=search_EQ_type] option[value="
									+ typeValue + "]").attr("selected",
							"selected");
				}

				$("button[type=reset]").click(
						function() {
							$(this).closest("form").find("input").attr("value",
									"");
							$(this).closest("form").find(
									"select option:selected").attr("selected",
									false);
							$(this).closest("form").find("select option:first")
									.attr("selected", true);
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
			
			function deleteById(id) {
				  var $form = $('#actionForm');
				  $form.attr('action', '${ctx}/coach/deleteCoach/' + id);
				  bootbox.confirm('您确定要删除吗？', function(result) {
				    if(result) {
				      $form[0].submit();
				    }
				  });
				  return false;
			}
</script>
</body>
</html>