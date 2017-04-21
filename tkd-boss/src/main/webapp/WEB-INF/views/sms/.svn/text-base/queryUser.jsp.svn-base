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
			<h3 class="panel-title">会员列表</h3>
		</div>
		<div class="panel-body">

			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<div class="form-group form-group-sm">
						<div class="col-md-6" style="display:none;">
							<input type="text" class="form-control input-sm" id="statium"
								placeholder="按所属道馆查询">
						</div>
						<div class="col-md-6">
				  	  		<input  type="text" class="form-control input-sm" id="name"   placeholder="按姓名/手机号查询"></div>
					</div>
				</div>
			</div>
			<br>
			<div class="form-group form-group-sm">
				<div class="col-md-12 text-center">
					<button type="reset" id="reset_btn" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-refresh"></span> 重 置
					</button>
					&nbsp;&nbsp;
					<button type="submit" id="sub_btn" class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-search"></span> 搜 索
					</button>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th style="width:50px;"><input type="checkbox" id="checkAll"></th>
								<th>学员号</th>
								<th>用户昵称</th>
								<th>用户性别</th>
								<th>手机号</th>
							</tr>
						</thead>

						<tbody id="content_table">
							<c:forEach items="${ssoUsers}" var="item" varStatus="stat">
								<tr>
									<td style="text-align: center;"><input type="checkbox" name="checklinkman" value="${item.username}/${item.phone }"/></td>									
									<td>${item.tkdNo }</td>
									<td>${item.nickName }</td>
									<td>${item.sex }</td>
									<td>${item.phone }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-offset-5 col-md-2">
							<button id="add_button" type="button"
								class="btn btn-primary btn-block">添加</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function() {
		$("#reset_btn").on("click", function(e) {
			$("#statiumm,#name").val("");
		});
		
		$("#checkAll").on("click",function(){
			var checked=this.checked;
			$("#content_table>tr>td>input[type=checkbox][name=checklinkman]").each(function(index,obj){
				obj.checked=checked;
			});
		});
		
		$("#add_button").on("click",function(){
			var ids=[];
			$("#content_table>tr>td>input[type=checkbox][name=checklinkman]:checked").each(function(index,obj){
				ids.push(obj.value);
			});
			if (!ids.length){
				myAlert("请选择联系人！");
			}else{
				$("#linkmans").val(ids.join(";"));
				$("#myDlg_lg").modal("hide");
			}
		});

		$("#sub_btn").on("click",function(e) {
							var statiumm = $("#statium").val();
							var name=$("#name").val();
							$.get('${ctx}/sms/ajax_ssouser_query_dlg?statium='+ statiumm+"&name="+name,
											function(data) {
												if (data != null) {
													var temp = new EJS({url : '${ctx}/static/template/ssousers.ejs?ver='+ Math.random()});
													var html = temp.render({
														items : data["data"]
													});
													$('#content_table').html(html);
												}
											}, "json");
						});
	});
	function selUser(dgId, a2) {
		captainAddCallBack(dgId, a2);
		$("#myDlg_lg").modal("hide");
	}
</script>