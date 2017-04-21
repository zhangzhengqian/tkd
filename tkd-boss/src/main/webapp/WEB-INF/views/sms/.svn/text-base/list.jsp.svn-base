<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>短信群发</title>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 短信群发</li>
				<li class="active">短信群发</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<legend>
				<small>短信群发</small>
			</legend>
			<form id="inputForm" action="${ctx}/sms/send" method="post"
				class="form-horizontal" enctype="multipart/form-data">
				<zy:token />
				<fieldset>
					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red">*</span>发送内容:</label>
						<div class="col-md-6 has-feedback">
							<textarea class="form-control" name="content"
								style="width: 480px; height: 150px; padding-right: 5px;"></textarea>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red">*</span>接收人<a class="btn btn_link" id="sel_linkman"
							href="#"><span class="glyphicon glyphicon-plus"></span></a>:</label>
						<div class="col-md-6 has-feedback">
							<textarea class="form-control" readonly="readonly" id="linkmans"
								name="linkmans"
								style="width: 480px; height: 250px; padding-right: 5px;"></textarea>

						</div>
					</div>


					<div class="form-group form-group-sm">
						<div class="col-md-offset-4 col-md-2">
							<button id="submit_button" type="submit"
								class="btn btn-primary btn-block">发送</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<!-- /右侧主体内容 -->

	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#sms-man');

			$("#sel_linkman").bind("click", function() {
				$("#myDlgBody_lg").load("${ctx}/sms/ssouser_query_dlg");
				$("#myDlg_lg").modal();
			});

			$('#inputForm').validate({
				submitHandler : function(form) {
					//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
					app.disabled("#submit_btn");
					form.submit();
				},
				rules : {
					linkmans : {
						required : true
					},
					content : {
						required : true
					}

				},
				messages : {

				}
			});
		});
	</script>

</body>
</html>