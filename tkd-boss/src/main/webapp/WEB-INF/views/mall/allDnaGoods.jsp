<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商城管理</title>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<div class="row">
				<div class="col-md-5">
					<div class="btn-group-sm">
						<ul class="breadcrumb" style="display: inline;">
							<li><span class="glyphicon glyphicon-home"></span>商城管理</li>
							<li class="active">商品列表</li>
						</ul>
					</div>
				</div>
				<div class="col-md-7"></div>
			</div>

		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->

			<div class="row">
				<!-- 查询条件 -->

			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
				</div>
			</div>
			<!-- /操作按钮组 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-5 form-inline">
				</div>
			</div>
			<!-- /操作按钮组 -->
			<br>
			<div class="row">
				<div class="col-table col-md-12">
					<form id="playerForm" class="form-horizontal" action="" method="post">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">
									<input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
								<th>商品</th>
								<th>名称</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data}" var="goods" varStatus="stat">
								<tr>
									<td class="text-center"  width="60px;"><input type="checkbox" value="${goods.id}_${goods.name}(${goods.bulk}*${goods.amount}) 整${goods.unit}" name="chk_list"/></td>
									<td class="text-center" width="130px;">
										<img alt=""
											 style="<c:if test='${!empty goods.photo}'>width:128px;height:120px;</c:if>"
											 src="${goods.photo}">
									</td>
									<td>
										${goods.name}(${goods.bulk}*${goods.amount} 整${goods.unit})
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
						</form>
					<div class="form-group">
						<hr>
						<div class="col-md-offset-3 col-md-2">
							<a class="btn btn-default btn-block"   id="cancel_btn"><span
									class="glyphicon glyphicon-remove"></span> 返回</a>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-block"
									id="save_btn">
								<span class="glyphicon glyphicon-ok"></span> 确认
							</button>
						</div>
						</button>
					</div>
				</div>

				<!-- end col-table -->
			</div>
			<!-- end row -->
			<tags:errors />
			<form id="actionForm" action="" method="post">
				<input type="hidden" id="ids" name="ids">
			</form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script type="text/javascript">
		$(function() {
			menu.active('#mallctivity-man');
			$('#adminFooter').hide();

			$("#cancel_btn").click(function(){
				$("#myDlg_lg").modal('hide');
			});

			$("#save_btn").click(function(){
				var idAndNames = [];
				var checked = $('input:checked');
				if (checked.length) {
					checked.each(function() {
						if ($(this).attr('name') != 'chk_all') {
							idAndNames.push($(this).val());
						}
					});
				}
				if(idAndNames.length > 0){
					window.parent.setGoodIds(${type},idAndNames);
				}
				$("#myDlg_lg").modal('hide');
			});
		});
	</script>
</body>
</html>
