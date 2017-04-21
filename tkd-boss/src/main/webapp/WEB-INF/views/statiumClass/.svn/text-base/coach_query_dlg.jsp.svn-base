<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<div class="modal-header">
	<button type="button" class="close" style="margin: -7px 0px 0px 0px"
		data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">教练列表</h3>
		</div>
		<div class="panel-body">
			<input type="hidden" name="statiumId" id="statiumId" value="${statiumId}" />
			<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			        <div class="form-group form-group-sm">
				  	  <div class="col-md-6">
				  	  		<input  type="text" class="form-control input-sm" id="name"   placeholder="按教练名查询">
			       	  </div>
			       	  <div class="col-md-6">
				  	  		<input  type="text" class="form-control input-sm" id="phone"   placeholder="按手机号查询">
			       	  </div>		        
			       	</div>
			</div>
		</div>
		<br>
			<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <button type="reset" id="reset_btn" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" id="sub_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				  </div>
				</div>
			<hr>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>序号</th>
								<th>教练名</th>
								<th>教练类型</th>
								<th>性别</th>
								<th>手机号</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="content_table">
							<c:forEach items="${users}" var="item" varStatus="stat">
								<tr>
									<td>${stat.count }</td>
									<td>${item.name}</td>
									<td>
										<c:if test="${item.type == 0}">大课教练</c:if>
									    <c:if test="${item.type == 1}">私人教练</c:if>
									</td>
									<td>${item.sex}</td>
									<td>${item.phone}</td>
									<td><a
										href="javascript:selUser('${item.id}','${item.name }')">添加</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$("button[type=reset]").click(function() {
			$("#name").attr("value", "");
			$("#phone").attr("value", "");
		});

		$("#sub_btn").on("click", function(e) {
					var username = $("#name").val();
					var phone = $("#phone").val();
					var statiumId = $("#statiumId").val();
					$.get('${ctx}/common/ajax_coach_query_dlg?username='
							+ username + '&phone=' + phone + '&statiumId=' + statiumId, function(data) {
						if (data != null) {
							var temp = new EJS({
								url : '${ctx}/static/template/coachs.ejs?ver='
										+ Math.random()
							});
							var html = temp.render({
								items : data["data"]
							});
							$('#content_table').html(html);
						}
					}, "json");
				});
	});
	function selUser(userId, name) {
		captainAddCallBack(userId, name);
		$("#myDlg_lg").modal("hide");
	}
</script>