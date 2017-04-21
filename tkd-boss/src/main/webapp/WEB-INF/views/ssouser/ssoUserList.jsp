<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
				<li class="active">用户管理</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->

			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">

					<form class="form-horizontal" id="ssoUserForm"
						action="${ctx}/ssouser/user" method="post">

						<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_nickName" name="search_LIKE_nickName"
									value="${param.search_LIKE_nickName }" placeholder="按用户姓名查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_phone" name="search_LIKE_phone"
									value="${param.search_LIKE_phone }" placeholder="按手机号查询">
							</div>
						</div>

						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_cardId" name="search_LIKE_cardId"
									value="${param.search_LIKE_cardId }" placeholder="按用身份证号查询">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm"
									id="search_LIKE_tkdNo" name="search_LIKE_tkdNo"
									value="${param.search_LIKE_tkdNo }" placeholder="按学员号查询">
							</div>
						</div>

						<div class="form-group form-group-sm query-more">
							<lable class="control-label col-md-1 sr-only"></lable>
							<div class="col-md-5">
								<input type="text" class="form-control Wdate "
									id="search_GTE_registTime" name="search_GTE_registTime"
									value="${param.search_GTE_registTime }" placeholder="注册日期-开始"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_registTime\')||\'%y-%M-%d\'}'})">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control Wdate "
									id="search_LTE_registTime" name="search_LTE_registTime"
									value="${param.search_LTE_registTime }" placeholder="注册日期-结束"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_registTime\')}'})">
							</div>
						</div>

						<div class="form-group form-group-sm query-more">
							<lable class="control-label col-md-1 sr-only"></lable>
							<div class="col-md-5">
								<input type="text" class="form-control Wdate "
									id="search_GTE_createTime" name="search_GTE_createTime"
									value="${param.search_GTE_createTime }" placeholder="激活日期-开始"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_createTime\')||\'%y-%M-%d\'}'})">
							</div>
							<div class="col-md-5">
								<input type="text" class="form-control Wdate "
									id="search_LTE_createTime" name="search_LTE_createTime"
									value="${param.search_LTE_createTime }" placeholder="激活日期-结束"
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_createTime\')}'})">
							</div>
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

							<div class="col-md-5 form-inline">
								<tags:zone name="search_LIKE_city"
									value="${param.search_LIKE_city }" />
							</div>
						</div>

						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<select class="form-control" name="search_EQ_level"
									id="search_EQ_level">
									<option id="option8" value="">--请选择会员等级--</option>
									<option id="option9" value="0">--普通会员--</option>
									<option id="option10" value="1">--金牌会员--</option>
									<option id="option11" value="2">--白金会员--</option>
									<option id="option12" value="3">--钻石会员--</option>
								</select>
							</div>
							<div class="col-md-5">
								<select class="form-control" name="search_EQ_state"
									id="search_EQ_state">
									<option id="option13" value="">--请选择用户状态--</option>
									<option id="option14" value="1"
										<c:if test="${param.search_EQ_state ==1}">selected='selected'</c:if>>--正常--</option>
									<option id="option15" value="3"
										<c:if test="${param.search_EQ_state ==3}">selected='selected'</c:if>>--冻结--</option>
								</select>
							</div>
						</div>

						<div class="form-group form-group-sm query-more">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<select class="form-control" name="search_EQ_registSource"
									id="search_EQ_registSource">
									<option id="option16" value="">--请选择注册来源--</option>
									<option id="option17" value="0">--APP--</option>
									<option id="option18" value="1">--微信--</option>
									<option id="option19" value="2">--QQ--</option>
									<option id="option20" value="3">--新浪微博--</option>
									<option id="option21" value="4">--网站--</option>
									<option id="option22" value="5">--平台--</option>
									<option id="option23" value="6">--球馆--</option>
								</select>
							</div>
							<div class="col-md-5">
								<select class="form-control" name="search_EQ_activeState"
									id="search_EQ_activeState">
									<option id="option24" value="">--请选择激活状态--</option>
									<option id="option25" value="1">--已激活--</option>
									<option id="option26" value="0">--未激活--</option>
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
								<shiro:hasPermission name="ssouser:exportExcel">
	    				&nbsp;&nbsp;
	    					<button type="submit" id="export_btn"
										class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-import"></span> 导出Excel
									</button>
								</shiro:hasPermission>
								&nbsp;&nbsp;
								<button type="button" class="btn btn-link btn-sm"
									id="btn-query-more">
									<span class="glyphicon glyphicon-chevron-down"></span> 更多条件
								</button>
							</div>
						</div>
						<input type="hidden" id="v_search_EQ_activeState"
							value="${param.search_EQ_activeState}"> <input
							type="hidden" id="v_search_EQ_registSource"
							value="${param.search_EQ_registSource}"> <input
							type="hidden" id="v_search_EQ_level"
							value="${param.search_EQ_level}">
					</form>
				</div>

			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
					<div class="btn-group-sm pull-right mtb10">
						<!-- <button value="" type="button" class="searchProperty btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;全部</button>
		      	<button value="教练" type="button" class="searchProperty btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;教练</button>
		      	<button value="陪练" type="button" class="searchProperty btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;陪练</button> -->
						<button value="1" type="button"
							class="searchState btn btn-default btn-sm">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;正常
						</button>
						<button value="3" type="button"
							class="searchState btn btn-default btn-sm">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;冻结
						</button>
						<shiro:hasPermission name="ssouser:create">
							<a class="btn btn-primary" href="${ctx}/ssouser/sign"><span
								class="glyphicon glyphicon-plus"></span> 用户添加</a>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
			<!-- /操作按钮组 -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">编号</th>
								<th>学员号</th>
								<th>登录号</th>
								<th>用户昵称</th>
								<th>用户性别</th>
								<th>注册手机</th>
								<th>用户类型</th>
								<th>用户状态</th>
								<th>注册来源</th>
								<th>注册时间</th>
								<th>激活状态</th>
								<th>激活时间</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach items="${data.content}" var="ssoUser" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td><a
										href="${ctx}/ssouser/view?action=view&id=${ssoUser.id }">${ssoUser.tkdNo }</a>
									</td>
									<td>${ssoUser.username }</td>
									<td>${ssoUser.nickName }</td>
									<td>${ssoUser.sex }</td>
									<td>${ssoUser.phone }</td>
									<td>${ssoUser.property }</td>
									<td><c:if test="${ssoUser.state == '1'}">
								正常
							</c:if> <c:if test="${ssoUser.state == '3'}">
								冻结
							</c:if></td>
									<td><c:if test="${ssoUser.registSource == '0'}">
								App
							</c:if> <c:if test="${ssoUser.registSource == '1'}">
								微信
							</c:if> <c:if test="${ssoUser.registSource == '2'}">
								QQ
							</c:if> <c:if test="${ssoUser.registSource == '3'}">
								新浪微博
							</c:if> <c:if test="${ssoUser.registSource == '4'}">
								网站
							</c:if> <c:if test="${ssoUser.registSource == '5'}">
								平台
							</c:if> <c:if test="${ssoUser.registSource == '6'}">
								球馆
							</c:if></td>
									<td><fmt:formatDate value="${ssoUser.registTime }"
											pattern="yyyy-MM-dd" /></td>
									<td><c:if test="${ssoUser.activeState == '0'}">
								未激活
							</c:if> <c:if test="${ssoUser.activeState == '1'}">
								已激活
							</c:if></td>
									<td><fmt:formatDate value="${ssoUser.createTime }"
											pattern="yyyy-MM-dd" /></td>
									<td><a
										href="${ctx}/ssouser/view?action=view&id=${ssoUser.id }">查看</a>
										<!--   <shiro:hasPermission name="ssouser:delete">
						  	<a href="#" onclick="deleteById('${ssoUser.id }')"> 删除</a>
						  </shiro:hasPermission> --> <c:if test="${ssoUser.state == '1'}">
											<shiro:hasPermission name="ssouser:freezeSsoUser">
												<a href="#" onclick="freezeSsoUser('${ssoUser.id }')">
													冻结</a>
											</shiro:hasPermission>
											<shiro:hasPermission name="ssouser:sendMsg">
												<a href="#" onclick="sendSms('${ssoUser.phone}')"> 发送短信</a>
											</shiro:hasPermission>
										</c:if> <c:if test="${ssoUser.state == '3'}">
											<shiro:hasPermission name="ssouser:unfreezeSsoUser">
												<a href="#" onclick="unfreezeSsoUser('${ssoUser.id }')">
													解冻</a>
											</shiro:hasPermission>
										</c:if>
										<a href="#" onclick="unbundling('${ssoUser.id}')">解绑
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
	<div class="modal fade" id="msgModel" order_id="" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">短信内容</h4>
				</div>
				<div class="modal-body" class="">
					<div class="form-group">
						<textarea rows="5" cols="10" id="msg" class="form-control"
							placeholder="请填写短信内容"></textarea>
						<input type="hidden" id="msg_phone" />
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="sendMsg()">发送</a>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#ssouser-man');
			$('#adminFooter').hide();

			//重置查询条件
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

			var state = '${param.search_EQ_state}';
			if (state != '') {
				$(".searchState[value=" + state + "]").addClass("active");
			}
			var props = '${param.search_EQ_property}';
			if (props != '') {
				$(".searchProperty[value=" + props + "]").addClass("active");
			}
			$(
					'#search_EQ_level option[value="'
							+ $('#v_search_EQ_level').val() + '"]').attr(
					'selected', 'selected');
			$(
					'#search_EQ_registSource option[value="'
							+ $('#v_search_EQ_registSource').val() + '"]')
					.attr('selected', 'selected');
			$(
					'#search_EQ_activeState option[value="'
							+ $('#v_search_EQ_activeState').val() + '"]').attr(
					'selected', 'selected');

			$(".searchProperty").on("click", function(e) {
				if (!$(this).hasClass("active")) {
					var property = $(this).val();
					$("#search_EQ_property").val(property);
					$("#ssoUserForm").submit();
				} else {
					$("#search_EQ_property").val("");
					$("#ssoUserForm").submit();
				}
			});

			$(".searchState").on("click", function(e) {
				if (!$(this).hasClass("active")) {
					var state = $(this).val();
					$("#search_EQ_state").val(state);
					$("#ssoUserForm").submit();
				} else {
					$("#search_EQ_state").val("");
					$("#ssoUserForm").submit();
				}
			})

		});

		// 删除
		function deleteById(id) {
			var $form = $('#actionForm');
			$form.attr('action', '${ctx}/ssouser/delete/' + id);
			bootbox.confirm('您是否确认删除此条用户记录？', function(result) {
				if (result) {
					$form[0].submit();
				}
			});
			return false;
		}

		// 冻结
		function freezeSsoUser(id) {
			var $form = $('#actionForm');
			$form.attr('action', '${ctx}/ssouser/freezeSsoUser/' + id);
			bootbox.confirm('你是否确认冻结该账号？', function(result) {
				if (result) {
					$form[0].submit();
				}
			});
			return false;
		}

		// 解冻
		function unfreezeSsoUser(id) {
			var $form = $('#actionForm');
			$form.attr('action', '${ctx}/ssouser/unfreezeSsoUser/' + id);
			bootbox.confirm('你是否确认解冻该账号？', function(result) {
				if (result) {
					$form[0].submit();
				}
			});
			return false;
		}
		//解绑
		function unbundling(id){
			var $form=$("#actionForm");
			$form.attr('action','${ctx}/ssouser/unbundling/'+id);
			bootbox.confirm('你是否确认解绑该账号？', function(result) {
				if (result) {
					$form[0].submit();
				}
			});
			return false;
		}
		$("#export_btn").click(function() {
			$("#ssoUserForm").attr("action", "${ctx}/ssouser/exportExcel");
			$("#ssoUserForm").attr("method", "post");
		})

		function sendSms(phone) {
			if (phone) {
				$("#msg_phone").val(phone);
				$("#msgModel").modal();
			} else {
				bootbox.alert('手机号不能为空');
			}
		}

		function sendMsg() {
			var msg = $("#msg").val();
			var phone = $("#msg_phone").val();
			if (msg == '') {
				bootbox.alert('请编辑短信内容！');
				return;
			}
			Util.getData(ctx + '/ssouser/sendMsg', function(data) {
				if (data.result) {
					myAlert("短信发送成功");
					$("#msgModel").modal("hide");
				} else {
					myAlert("短信发送失败", "error");
				}
			}, null, {
				"phone" : phone,
				"msg" : msg
			}, 'post');
		}

		$("#search_btn").click(function() {
			$("#ssoUserForm").attr("action", "${ctx}/ssouser/user");
			$("#ssoUserForm").attr("method", "post");
		})
	</script>

</body>
</html>