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
				<li><span class="glyphicon glyphicon-home"></span> 道馆列表</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
			
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="search_form" class="form-horizontal"
						action="${ctx}/statiumOa" method="post">
						<input type="hidden" name="search_EQ_status" value="${param.search_EQ_status }" id="search_EQ_status"  />
						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for="option"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_address" name="search_LIKE_address"
									value="${param.search_LIKE_address }" placeholder="按道馆地址查询">
							</div>
							<div class=" col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_dgName" name="search_LIKE_dgName"
									value="${param.search_LIKE_dgName}" placeholder="按场馆名称查询">
							</div>
						</div>
						
						<div class="form-group form-group-sm">
						<label class="control-label col-md-1 sr-only" for=""></label>
							<div class=" col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_tel" name="search_LIKE_tel"
									value="${param.search_LIKE_tel}" placeholder="按馆长联系电话查询">
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
			<!-- /操作按钮组 -->
			<div class="row">
			<div class="col-md-5 form-inline">
			 <div class="btn-group" role="group" aria-label="...">
				  <button value=""  type="button" class="searchStatus btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;全部状态</button>
				  <button value="0" type="button" class="searchStatus btn btn-warning btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;待审核</button>
				  <button value="1" type="button" class="searchStatus btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核通过</button>
				  <button value="2" type="button" class="searchStatus btn btn-info btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核拒绝</button>
			</div>
			</div>
			<!-- 操作按钮组 -->
				<shiro:hasPermission name="statium:create">
				<div class="col-md-7 form-inline">
				<div class="col-md-12 text-right">
					<a class="btn btn-primary btn-sm" href="${ctx }/statiumOa/createForm?action=create" ><span class="glyphicon glyphicon-plus"></span> 添加道馆</a>
				</div>
				</div>
				</shiro:hasPermission>
			</div>
			<br>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>道馆名称</th>
								<th>地址</th>
								<th>联系人姓名</th>
								<th>联系电话</th>
								<th>道馆录入人</th>
								<th>录入时间</th>
								<th>审核状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="statium" varStatus="stat">
								<c:set var="status_class" value="" />
								<c:if test="${statium.status == 0}">
								<c:set var="status_class" value="warning" />
								</c:if>
								<c:if test="${statium.status == 1}">
								<c:set var="status_class" value="success" />
								</c:if>
								<c:if test="${statium.status == 2}">
								<c:set var="status_class" value="info" />
								 </c:if>
								<tr class="${status_class }">
									<td class="text-center">${stat.count}</td>
									<td><a href="${ctx }/statiumOa/detailForm?id=${statium.id}&page=${page}&pageSize=${pageSize}">
											${statium.dgName}</a>
									</td>
									<td>${statium.address }</td>
									<td>${statium.contact}</td>
									<td>${statium.tel}</td>
									<td>${statium.cb}</td>
									<td><fmt:formatDate value="${statium.ct }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<c:if test="${statium.status=='0' }">
									<td class="warning">待审核</td>
									</c:if>
									<c:if test="${statium.status=='1' }">
									<td class="success">已审核</td>
									</c:if>
									<c:if test="${statium.status=='2' }">
									<td class="success">审核拒绝</td>
									</c:if>
									<c:if test="${empty statium.status}">
									<td></td>
									</c:if>
									<td>
									<shiro:hasPermission name="statium:edit">
									<a class="btn btn-default btn-sm" href="${ctx }/statiumOa/detailForm?id=${statium.id}&action=edit&page=${page}&pageSize=${pageSize}">
									<i class="glyphicon glyphicon-edit"></i> 修改</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="statium:delete">
									<a class="btn btn-sm btn-default" href="#" onclick="deleteById('${statium.id }')"><i class="glyphicon glyphicon-trash"></i> 删除</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="statiumClass:list">
									<a class="btn btn-default btn-sm" href="${ctx }/statiumClass/list?dgid=${statium.dgId}&action=oa"><i
									class="glyphicon glyphicon-cog"></i> 课程设置</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="crm:user">
									<c:choose>
										<c:when test="${statium.recommend==1 }">
											<a class="btn btn-sm btn-default" href="${ctx }/statiumOa/crmManagerForm?statiumId=${statium.dgId}&page=${page}&pageSize=${pageSize}""><i class="glyphicon glyphicon-ok"></i> 道馆账号</a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-sm btn-default" href="${ctx }/statiumOa/crmManagerForm?statiumId=${statium.dgId}&page=${page}&pageSize=${pageSize}""><i class="glyphicon glyphicon-remove"></i> 道馆账号</a>
										</c:otherwise>
									</c:choose>
									</shiro:hasPermission>
									<%-- <c:choose>
									<c:when test="${statium.recommend==1 }">
									<shiro:hasPermission name="statium:recommend">
									<a class="btn btn-default btn-sm" href="${ctx }/statiumOa/recommend?dgid=${statium.dgId}&recommend=0"><i class="glyphicon glyphicon-cog">取消推荐</i></a>
									</shiro:hasPermission>
									</c:when>
									<c:otherwise>
									<shiro:hasPermission name="statium:recommend">
									<a class="btn btn-default btn-sm" href="${ctx }/statiumOa/recommend?dgid=${statium.dgId}&recommend=1"><i class="glyphicon glyphicon-cog">推荐</i></a>
									</shiro:hasPermission>
									</c:otherwise>
									</c:choose> --%>
									<c:if test="${statium.status=='1' }">
									<a class="btn btn-default btn-sm" href="${ctx }/statiumOa/buildQrCode?dgId=${statium.dgId}"><i class="glyphicon glyphicon-cog"></i> 生成二维码</a>
									</c:if>
									</td>
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
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#statiumOa-man');
			$(".searchStatus").click(function(){
				var status = $(this).val();
				$("#search_EQ_status").val(status);
				$("#search_form").submit();
			})

			$('#adminFooter').hide();

			var typeValue = '${param.search_EQ_type }';

			if (typeValue) {
				$("select[name=search_EQ_type] option[value=" + typeValue + "]")
						.attr("selected", "selected");
			}

			//====================================================
			// 自动匹配 场馆名称 >>>>
			//====================================================
			$('#statiumName').autocomplete(
					'${ctx}/common/search_statium_by_name?flag=true',
					{
						dataType : 'json',
						minChars : 2, //最少输入字条
						max : 30,
						autoFill : false,
						mustMatch : false, //是否全匹配, 如数据中没有此数据,将无法输入
						matchContains : true,
						scrollHeight : 220,
						width : $('#statiumName').outerWidth(),
						multiple : false,
						formatItem : function(row, i, max) { //显示格式
							return "【" + row.name + "】【" + row.area + "】【"
									+ row.address + "】";
						},
						formatResult : function(row) { //返回结果
							return row.name;
						},
						handleValue : function(id) {
							$('#statiumId').val(id);
						},
						parse : function(data) {
							var parsed = [];
							var rows = data;
							for (var i = 0; i < rows.length; i++) {
								var row = rows[i];
								if (row) {
									parsed[parsed.length] = {
										data : row,
										value : row["id"],
										result : this.formatResult(row)
									};
								}
							}
							return parsed;
						}
					});
			//====================================================
			// 自动匹配 场馆名称 <<<<
			//====================================================

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

		 // 删除
		function deleteById(id) {
			  var $form = $('#actionForm');
			  $form.attr('action', '${ctx}/statiumOa/deleteStatiumOa/' + id);
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