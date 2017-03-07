<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>赛事列表</title>
<link rel="stylesheet" href="${ctx }/static/css/tree.css">
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 赛程管理</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<c:forEach items="${schedules}" var="schedule">
				<div class="matchSchedule scheduleForm">
				<form>
					<p>
						<span>
							<c:choose>
								<c:when test="${schedule.gameType==1}">
									男子单打
								</c:when>
								<c:when test="${schedule.gameType==2}">
									女子单打
								</c:when>
								<c:when test="${schedule.gameType==3}">
									男子双打
								</c:when>
								<c:when test="${schedule.gameType==4}">
									女子双打
								</c:when>
								<c:when test="${schedule.gameType==5}">
									混合双打
								</c:when>
								<c:when test="${schedule.gameType==6}">
									混合单打
								</c:when>
							</c:choose>
						</span>
						<input type="hidden" name="gameId" value="${schedule.gameId }">
						<input type="hidden" name="gameType" value="${schedule.gameType }">
						<span>
							<select name="gameFormat">
								<option  value="2">单淘汰</option>
								<option  value="1">循环+淘汰</option>
							</select>
						</span>
						<span>
							<select name="gameFormat1">
								<option value="0" <c:if test="${schedule.gameFormat1==0}">selected</c:if>>抢四</option>
								<option value="1" <c:if test="${schedule.gameFormat1==1}">selected</c:if>>抢六</option>
							</select>
						</span>
					</p>
				</div>
			</form>
			</c:forEach>
			<div class="matchSchedule scheduleBtn">
				<p>
					<span><input type="number" id="fnum" placeholder="场地数"></span>
				</p>
				<a class="btn btn-default" onclick="setSchedule()" href="javascript:;">设置赛程</a>
				<a class="btn btn-default" onclick="saveSchedule()" href="javascript:;">确定</a>
				<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
			</div>
			<div class="row" id="gameSchedules"></div>
		</div>
	</div>
	
	<div id="gameTree" style="display:none;"></div>

	<!-- /右侧主体内容 -->
	<script src="${ctx}/static/js/utils.js"></script>
	<script src="${ctx}/static/js/gameTree.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
			
		});
		var schedules = {};
		var saveFlag = false;
		function setSchedule(){
			if($("#fnum").val()==undefined || $("#fnum").val()==''){
				myAlert("请填写场地数！","error")
				return;
			}
			var scheduleForms = new Array();
			var scheduleForm = {};
			$(".scheduleForm").each(function(index,obj){
				scheduleForm = {};
				var values = $(obj).find("form").serialize();
				values = values.split("&");
				for(var i=0;i<values.length;i++){
					var val = values[i];
					scheduleForm[val.split("=")[0]] = val.split("=")[1];
				}
				scheduleForms.push(scheduleForm);
			});
			scheduleForms[0].gameField = $("#fnum").val();
			$.ajax({
				type: "POST",
				url : '${ctx}/enjoyRace/setGameSchedule',
				data :JSON.stringify(scheduleForms),
				contentType: "application/json;charset=utf-8",
				error : function(request) {
					common.showMessage("失败！");
				},
				success : function(res) {
					saveFlag = true;
					res = eval("("+res+")");
					schedules = res.data;
					schedules["games"] = scheduleForms;
					var group_temp = new EJS({url: '${ctx}/static/template/gameSchedule.ejs?ver=1.6'});
    				var group_html = group_temp.render({schedules:res.data});
    				$("#gameSchedules").html(group_html);
				}
			}); 
		}
		function saveSchedule(){
			if(!saveFlag){
				return;
			}
			$.ajax({
				type: "POST",
				url : '${ctx}/enjoyRace/saveSchedule/1',
				data :JSON.stringify(schedules),
				contentType: "application/json;charset=utf-8",
				error : function(request) {
					common.showMessage("失败！");
				},
				success : function(res) {
					res = eval('('+res+')');
					if(res.result){
						window.location.href="${ctx}/enjoyRace/list";
					}else{
						common.showMessage("保存赛程失败！");
					}
				}
			}); 
		}
	</script>
</body>
</html>