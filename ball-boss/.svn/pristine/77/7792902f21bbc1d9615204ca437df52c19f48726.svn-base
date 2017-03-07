<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业用户</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>企业用户列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/company/list" method="post">
						<div class="form-group form-group-sm">
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_name" name="search_LIKE_name"
									value="${param.search_LIKE_name}" placeholder="按企业用户名称查询">
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
						  <button value="0" type="button" class="searchStatus btn btn-warning btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;正常用户</button>
						  <button value="1" type="button" class="searchStatus btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;冻结用户</button>
					</div>
				</div>
				 <div class="col-md-7 text-right">
					<div class="btn-group-sm pull-right mtb10">
						<shiro:hasPermission name="company:create">
							<a class="btn btn-primary btn-sm" href="${ctx}/company/create?action=create"><span
								class="glyphicon glyphicon-plus"></span> 添加企业用户</a>
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
								<th>企业名称</th>
								<th>企业地址</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>账户可用余额 </th>
								<th>员工数</th>
								<th>是否冻结</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="info" varStatus="stat">
							  <c:set var="status_class" value="" />
								<c:if test="${event.state == 0}">
									<c:set var="status_class" value="warning" />
								</c:if>
								<c:if test="${event.state == 1}">
									<c:set var="status_class" value="success" />
								</c:if>
								<c:if test="${event.state == 2}">
									<c:set var="status_class" value="info" />
								</c:if>
								<tr class="${status_class }" >
									<input type="hidden" id="eventId_${stat.count}"
										value="${event.id }" />
									<td class="text-center">${stat.count}</td>
								<td>
									 ${info.name}
								</td>
								<td>
									 ${info.addr}
								</td>
								<td>
									 ${info.linkMan}
								</td>
								<td>
									 ${info.linkPhone}
								</td>
								<td>
									 ${info.balance/100}元
								</td>
								<td>	${info.employees}人&nbsp;&nbsp;
								<c:if test="${!empty info.employees}">
									<a href="${ctx}/company/employeeList?id=${info.id}&gourpId=${info.groupsId}&name=${info.name}"
										id="editLink-${event.id}" class="btn btn-default btn-sm">
								<span 	class="glyphicon glyphicon-search"></span>查看员工</a>
								</c:if>
								</td>
								<td>
									<c:if test="${info.state == 0}">正常</c:if> 
									<c:if test="${info.state == 1}">已冻结</c:if> 
								</td>
									<td><a href="${ctx}/company/view?id=${info.id}"
										id="editLink-${event.id}" class="btn btn-default btn-sm"><span
											class="glyphicon glyphicon-search"></span>企业详情</a>
									<shiro:hasPermission name="company:update">
											<a class="btn btn-default btn-sm"
												href="${ctx}/company/view?id=${info.id}&action=edit"><i
												class="glyphicon glyphicon-edit"></i>企业修改</a>
									</shiro:hasPermission> 
									<shiro:hasPermission name="company:freeze">
									<c:if test="${info.state == 1}">
										<a href="${ctx}/company/freeze?id=${info.id}&freeze=0"
											id="editLink-${info.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 解冻</a>
									</c:if>
									<c:if test="${info.state == 0 || empty info.state}">
										<a href="${ctx}/company/freeze?id=${info.id}&freeze=1"
											id="editLink-${info.id}" class="btn btn-default btn-sm"><i
											class="glyphicon glyphicon glyphicon-remove"></i> 冻结</a>
									</c:if>
								</shiro:hasPermission>
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
			if("${message}" != null && "${message}" != ""){
				myAlert("${message}");
			}
			menu.active('#company-man');
			$('#adminFooter').hide();
			
			  /* 按状态查询 */
			  $(".searchStatus").click(function(){
				  var v = $(this).val();
				  $("#search_EQ_state").val(v);
				  $("#search_form").submit();
			  });

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
	</script>
</body>
</html>