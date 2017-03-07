<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理</title>
<style type="text/css">
</style>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
</head>

<body>

	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li>赛事管理</li>
				<li class="active">赛程管理</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/event/scheduleForm" method="post"
			class="form-horizontal">
			<input type="hidden" name="allCorps" id="allCorps" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>赛程列表</small>
					</legend>
					<div name="turnDiv">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>轮次:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" style="width: 130px; text-align: center;"
									placeholder="请输入轮次" name="turn" value="${schedule.turn }">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>比赛形式：</label>
							<div class="col-md-6 has-feedback">
								<label style="height: 34px;" class="radio-inline"><input
									type="radio" name="eventType" value="0"
									<c:if test="${schedule.gameType != '1'}"> checked="checked"  </c:if>>循环赛</label>
								<label style="height: 34px;" class="radio-inline"><input
									type="radio" name="eventType" value="1"
									<c:if test="${schedule.gameType == '1'}"> checked="checked"  </c:if>>淘汰赛</label>&nbsp;
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>组名称：</label>
							<div class="col-md-6 has-feedback">
								<input type="text" text-align: center;" placeholder="请输组名称"
									name="gameGroup" value="${schedule.gameGroup }">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>开赛时间：</label>
							<div class="col-md-6 has-feedback">
								<input type="text" style="text-align: center;"
									placeholder="请选择开赛时间" name="gameTime"
									value="${schedule.gameTime}" data-date-format="yyyy-mm-dd"
									onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})">
								</td>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>参战A队：</label>
							<div class="col-md-6 has-feedback">
								<input type="hidden" name="marinesIdA" value="${schedule.marinesIdA}"> 
								 <img alt=""  style="width: 48px; height: 48px;" id="logoId" name="logoIdA" src="${schedule.logoA}"> 
								 <label style="height: 34px;" class="radio-inline" id="nameId" name="nameIdA">${schedule.nameA}</label>
								<button type="button" class="btn btn-default" id="addCorps1" name="addCorps">选择战队</button>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>参战B队：</label>
							<div class="col-md-6 has-feedback">
								<input type="hidden" name="marinesIdB" value="${schedule.marinesIdB}"> 
								<img alt="" style="width: 48px; height: 48px;" src="${schedule.logoB}" id="logoIdB" name="logoIdB"> 
								<label style="height: 34px;" class="radio-inline" id="nameId" name="nameIdB">${schedule.nameB}</label>
								<button type="button" class="btn btn-default" id="addCorps2" name="addCorps">选择战队</button>
							</div>
						</div>
						<input type="hidden" name="id" value="${schedule.id}"> 
						<input type="hidden" name="gameType" id="gameType" value="${schedule.gameType}"> 
						<input type="hidden" name="gamesId" value="${evnetId}">
					</div>
					<br>

				</fieldset>
				<div class="form-group">
					<hr>
					<div class="col-md-offset-3 col-md-2">
						<a class="btn btn-default btn-block"
							href="${ctx}/event/scheduleList/${evnetId}"><span
							class="glyphicon glyphicon-remove"></span> 返回</a>
					</div>
					<div class="col-md-2">
						<button type="button" class="btn btn-primary btn-block"
							id="submit_btn">
							<span class="glyphicon glyphicon-ok"></span> 保存
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	</div>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
	<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
	<script src="${ctx}/static/js/project_js.js"></script>
	<script type="text/javascript">
		var thisId;
		
		if("${action}" == "update"){
			$("img[name=logoIdA]").attr("display","block");
			$("img[name=logoIdB]").attr("display","block");
		}else{
			$("img[name=logoIdA]").attr("display","none");
			$("img[name=logoIdB]").attr("display","none");
		}
		
		//关闭战队选择页
		function colseMyDlg() {
			$("#myDlg_lg").modal('hide');
		}

		//选择对战回调方法，设置对战信息
		function checkCorps(v) {
			var p = $('#' + thisId).parent();
			if (thisId == "addCorps1") {
				p.find("input[name=marinesIdA]").val(v.split("_")[0]);
				p.find("label[name=nameIdA]").html(v.split("_")[1]);
				p.find("img[name=logoIdA]").attr("display","block");
				p.find("img[name=logoIdA]").attr("src", v.split("_")[2]);

			} else {
				p.find("input[name=marinesIdB]").val(v.split("_")[0]);
				p.find("label[name=nameIdB]").html(v.split("_")[1]);
				p.find("img[name=logoIdB]").attr("display","block");
				p.find("img[name=logoIdB]").attr("src", v.split("_")[2]);

			}

		}
		$(function() {
			var eventId = "${evnetId}";
			//添加战队
			$("button[name=addCorps]").each(function() {
				$(this).click(function() {
					var obj = this;
					thisId = $(obj).attr('id');
					$("#myDlgBody_lg").load("${ctx}/event/corpsFrame_dlg?eventId="+eventId);
					$("#myDlg_lg").modal();
				});
			});

			menu.active('#event-man');
			$('#adminFooter').hide();

			$('#inputForm').validate(
					{
						ignore : "", // 开启hidden验证， 1.9版本后默认关闭
						submitHandler : function(form) {
							var typeValue = $(
									'input:radio[name=eventType]:checked')
									.val();
							$('#gameType').val(typeValue);
							app.disabled("#submit_btn");
							//提交表单
							form.submit();
						},
						rules : {
							turn : {
								required : true
							},
							groupName : {
								required : true
							},
							gamesType : {
								required : true
							},
							marinesIdA : {
								required : true
							},
							marinesIdB : {
								required : true
							},
							nameIdA : {
								required : true
							},
							nameIdB : {
								required : true
							},
							gameTime : {
								required : true
							}
						},
						messages : {

						}
					});

			$("#submit_btn").click(function() {
				$("#inputForm").submit();
			});
		});
	</script>
</body>
</html>
