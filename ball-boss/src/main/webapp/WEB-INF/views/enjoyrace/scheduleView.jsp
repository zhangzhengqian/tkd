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
			<div class="row">
				<div class="col-md-1 pull-right">
					<a class="btn btn-default btn-sm" href="${ctx}/enjoyRace/list">
						<span class="glyphicon"></span> 返回
					</a> &nbsp;&nbsp;
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<c:if test="${schedules.schedule_fields!=null&&fn:length(schedules.schedule_fields)>0}">
						<legend>
							<small>整体场地编排</small>
						</legend>
						<div style='width:100%; position:relative;'>
							<c:forEach items="${schedules.schedule_fields}" var="schedule_" >
								<div class="gameSchedule schedule-list" ng-class="{true: 'gameScheduleClear', false: ''}[$index!=1&&$index%4==0]" ng-repeat="(field,schedule) in schedules.schedule_fields">
								<table>
									<tr>
										<th colspan="4">${schedule_.key }号场地</th>
									<tr>
									<c:forEach items="${schedule_.value}" var="gamePoint" >
										<tr>
											<td>
											<c:choose>
												<c:when test="${gamePoint.game_type==1}">
													男子单打
												</c:when>
												<c:when test="${gamePoint.game_type==2}">
													女子单打
												</c:when>
												<c:when test="${gamePoint.game_type==3}">
													男子双打
												</c:when>
												<c:when test="${gamePoint.game_type==4}">
													女子双打
												</c:when>
												<c:when test="${gamePoint.game_type==5}">
													混合双打
												</c:when>
												<c:when test="${gamePoint.game_type==6}">
													混合单打
												</c:when>
												<c:when test="${gamePoint.game_type==7}">
													无性别限制双打
												</c:when>
											</c:choose>
											${gamePoint.group_id}组${gamePoint.turn}轮</td><td>${gamePoint.p1} </td><td>VS</td><td> ${gamePoint.p2}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							</c:forEach>
							<a class="downloadBtn" style="position:absolute;" href="${ctx}/enjoyRace/exportScheduleFields/${gameId}">下载</a>
						</div>
					</c:if>
				</div>
			</div>
					<c:forEach items="${schedules.games}" var="game" >
			<div class="row">
				<div class="col-md-12">
						<c:choose>
							<c:when test="${game.gameType==1}">
								<c:set var="gameTypeStr" value="男子单打"/>
							</c:when>
							<c:when test="${game.gameType==2}">
								<c:set var="gameTypeStr" value="女子单打"/>
							</c:when>
							<c:when test="${game.gameType==3}">
								<c:set var="gameTypeStr" value="男子双打"/>
							</c:when>
							<c:when test="${game.gameType==4}">
								<c:set var="gameTypeStr" value="女子双打"/>
							</c:when>
							<c:when test="${game.gameType==5}">
								<c:set var="gameTypeStr" value="混合双打"/>
							</c:when>
							<c:when test="${game.gameType==6}">
								<c:set var="gameTypeStr" value="混合单打"/>
							</c:when>
							<c:when test="${game.gameType==7}">
								<c:set var="gameTypeStr" value="无性别限制双打"/>
							</c:when>
						</c:choose>
						<div id="gameSchedule${game.id}">
						</div>
						<script type="text/javascript">
							$.post("${ctx}/enjoyRace/getScheduleByType/"+'${game.id}',null,
								function(res) {
									if (res.result) {
				        				$("#gameSchedule"+'${game.id}').append("<legend style='margin:20px 0px;'><small>"+'${gameTypeStr}'+"</small></legend>");
										var data = res.data;
										if(data.hasOwnProperty("groups")){
					        				var schedule = {};
					        				schedule["gameFormat"] = 1;
					        				schedule["groupScheduleList"] = data["groups"];
					        				var group_temp = new EJS({url: '${ctx}/static/template/groupSchedule.ejs?ver=1.7'});
					        				var group_html = group_temp.render({schedule:schedule});
					        				var div = $("<div id='group_schedule' style='width:100%;position:relative;'></div>");
					        				div.append(group_html);
					        				$("#gameSchedule"+'${game.id}').append(div);
					        				var downloadBtn = $('<a class="downloadBtn"style="position: absolute;" href="${ctx }/enjoyRace/exportSchedule/${game.id}">下载</a>');
					        				div.append(downloadBtn);
					        			}
					        			if(data.hasOwnProperty("tree")){
					        				var canvasObj =  document.createElement("canvas");
					        				gameTreeForCanvas(data["tree"],canvasObj);
					        				var div = $("<div id='tree_schedule' style='width:100%; position:relative;'></div>");
					        				div.append(canvasObj);
					        				$("#gameSchedule"+'${game.id}').append(div);
					        				var strDataURI = canvasObj.toDataURL("image/jpeg");
					        				var downloadBtn = $('<a class="downloadBtn" style="position:absolute;" href="javascript:;">下载</a>');
					        				downloadBtn.on("click",function(){
					        					saveFile(strDataURI,"淘汰赛程.jpg");
					        				})
					        				$(div).append(downloadBtn);
					        			}
									}
							},"json");
						</script>
				</div>
			</div>
					</c:forEach>
		</div>
	</div>
	<br>

	<!-- /右侧主体内容 -->
	<script src="${ctx}/static/js/utils.js"></script>
	<script src="${ctx}/static/js/gameTree.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
		});
		function gameTreeForCanvas(tree,canvasObj) {
			var canvas = canvasObj||document.getElementById("gameTreeCanvas");
			var labelMap = tree.labelMap;
			var turn = tree.turn;
	        var per = 65;
	        canvas.width = turn * 2*per + 10 * (turn - 2);
	        canvas.height = (Math.pow(2, turn - 1) + 1) * per;
	        var lastLength = Math.pow(2, turn - 2) * per;
	        var context = canvas.getContext("2d");
	        
	        //填充颜色
	      	context.rect(0,0,canvas.width,canvas.height);
	        context.fillStyle = "rgb(255,255,255)";
	        context.fill();
	        
	        context.beginPath();

	        context.lineWidth = 1;
	        context.strokeStyle = '#ccc';
	        context.font = "12px 微软雅黑"//其中文字特殊样式可选，如果不填写，默认为normal，还可以使用italic表示斜体等等
	        context.fillStyle = '#000';//文字颜色
	        
	        context.moveTo(canvas.width, canvas.height / 2);
	        context.lineTo(canvas.width - 3 * per, canvas.height / 2);
	        context.stroke();

	        if(tree.name!=undefined&&tree.name!=''){
	        	fillText(labelMap[tree.winner]+"　"+tree.name, {x: canvas.width - 0.75*per, y: (canvas.height / 2) - 5}, context, 'center','middle','#000');
	        	fillText('第一名', {x: canvas.width - 0.75*per, y: (canvas.height / 2) +10}, context, 'center','middle','#000');
	        	//文字
		        var second = {};
		        if(tree.lscore>tree.rscore){
		            second = tree.childrens[1];
		        }else{
		            second = tree.childrens[0];
		        }
		        if(second.name){
		        	fillText(labelMap[second.winner]+"　"+second.name, {x: canvas.width - 2.2 * per, y: (canvas.height / 2) - 5}, context, 'center','middle','#000');
		        	fillText('第二名', {x: canvas.width - 2.2 * per, y: (canvas.height / 2) +10}, context, 'center','middle','#000');
		        }
	        }

	        if(tree.smallscore){
	        	fillText(tree.lscore+":"+tree.rscore+"("+tree.smallscore+")", {x: canvas.width - 3 * per-10, y: (canvas.height / 2) - 5}, context, 'center','hanging','#FF8402');
	        }else{
	        	fillText(tree.lscore+":"+tree.rscore, {x: canvas.width - 3 * per-10, y: (canvas.height / 2) - 5}, context, 'center','hanging','#FF8402');
	        }
	        context.moveTo(canvas.width - per*1.5, canvas.height / 2);
	        context.lineTo(canvas.width - per*1.5, canvas.height / 2 - lastLength / 2);
	        context.stroke();

	        context.moveTo(canvas.width - per*1.5, canvas.height / 2);
	        context.lineTo(canvas.width - per*1.5, canvas.height / 2 + lastLength / 2);
	        context.stroke();

	        context.moveTo(canvas.width - per*1.5, canvas.height / 2 - lastLength / 2);
	        context.lineTo(canvas.width - 3 * per, canvas.height / 2 - lastLength / 2);
	        context.stroke();
	        var left = tree.childrens[0];
	        if(left.name){
		        fillText(labelMap[left.winner]+"　"+left.name, {x: canvas.width - 2.5 * per+10, y: canvas.height / 2 - lastLength / 2-5}, context, 'center','alphabetic','#000');
	        }

	        context.moveTo(canvas.width - per*1.5, canvas.height / 2 + lastLength / 2);
	        context.lineTo(canvas.width - 3 * per, canvas.height / 2 + lastLength / 2);
	        context.stroke();
	        var right = tree.childrens[1];
	        if(right.name){
	        	fillText(labelMap[right.winner]+"　"+right.name, {x: canvas.width - 2.5 * per+10, y: canvas.height / 2 + lastLength / 2+5}, context, 'center','hanging','#000');
	        }

	        var point = {};
	        point.x = canvas.width - 3 * per - 10;
	        point.y = canvas.height / 2 - lastLength / 2;
	        strokeChildren(point, left, per, context,labelMap);


	        point.x = canvas.width - 3 * per - 10;
	        point.y = canvas.height / 2 + lastLength / 2;
	        strokeChildren(point, right, per, context,labelMap);
	        
	        context.restore();
		};
		function fillText(text, point, context, align,base,fillStyle) {
			if(text==undefined||text=='undefined:undefined'){
				return;
			}
			context.fillStyle = fillStyle;
	        context.textAlign = align;//文本水平对齐方式
	        context.textBaseline = base;//文本垂直方向，基线位置
	        context.fillText(text, point.x, point.y);//创建文字，控制文件的起始位置
	    }

	    function strokeChildren(point, child, per, context,labelMap) {
	    	context.lineWidth = 1;
	        context.strokeStyle = '#ccc';
	        //下一轮
	        var turn = child.turn;
	        var lastLength = Math.pow(2, turn - 2) * per;

	        context.moveTo(point.x, point.y);
	        context.lineTo(point.x, point.y - lastLength / 2);
	        context.stroke();


	        context.moveTo(point.x, point.y);
	        context.lineTo(point.x, point.y + lastLength / 2);
	        context.stroke();


	        var left = child.childrens[0];
	        var point_l = {};
	        context.moveTo(point.x, point.y - lastLength / 2);
	        if (turn == 2) {
	            context.lineTo(point.x - 3 * per, point.y - lastLength / 2);
	        } else {
	            context.lineTo(point.x - 2*per, point.y - lastLength / 2);
	            point_l.x = point.x - 2*per - 10;
	            point_l.y = point.y - lastLength / 2;
	        }
	        context.stroke();


	        var right = child.childrens[1];
	        var point_r = {};
	        context.moveTo(point.x, point.y + lastLength / 2);
	        if (turn == 2) {
	            context.lineTo(point.x - 3 * per, point.y + lastLength / 2);
	        } else {
	            context.lineTo(point.x - 2*per, point.y + lastLength / 2);
	            point_r.x = point.x - 2*per - 10;
	            point_r.y = point.y + lastLength / 2;
	        }
	        context.stroke();

	        if (turn > 2) {
	        	var point_l_ = {x:point_l.x+per,y:point_l.y-3};
	        	if(left.name){
		            fillText(labelMap[left.winner]+"　"+left.name, point_l_, context, 'center','alphabetic','#000');
	        	}
	            var point_r_ = {x:point_r.x+per,y:point_r.y+3};
	            if(right.name){
		            fillText(labelMap[right.winner]+"　"+right.name, point_r_, context, 'center','hanging','#000');
	            }

	            var point_ = point;
	            point_.x = point.x-per/2;
	            if(child.smallscore){
	            	fillText(child.lscore+":"+child.rscore+"("+child.smallscore+")", point_, context, 'center','hanging','#FF8402');
	            }else{
	            	fillText(child.lscore+":"+child.rscore, point_, context, 'center','hanging','#FF8402');
	            }

	            strokeChildren(point_l, left, per, context,labelMap);

	            strokeChildren(point_r, right, per, context,labelMap);
	        }else{
	            point_l = {x:point.x-3*per+30,y:point.y - lastLength / 2-3};
	            fillText(left.name==''?'轮空':left.label+"　"+left.name, point_l, context, 'left','alphabetic','#000');
	            point_r = {x:point.x-3*per+30,y:point.y +lastLength / 2-3};
	            fillText(right.name==''?'轮空':right.label+"　"+right.name, point_r, context, 'left','alphabetic','#000');

	            if(left.name!=''&&right.name!=''){
	                var point_ = point;
	                point_.x = point.x-2*per;
	                point_.y = point.y-3;
	                if(child.smallscore){
	                	fillText(child.lscore+":"+child.rscore+"("+child.smallscore+")", point_, context, 'center','hanging','#FF8402');
	                }else{
	                	fillText(child.lscore+":"+child.rscore, point_, context, 'center','hanging','#FF8402');
	                }
	            }

	        }
	    }
	    
	    function saveFile(data, filename){
	        var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
	        save_link.href = data;
	        save_link.download = filename;
	       
	        var event = document.createEvent('MouseEvents');
	        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
	        save_link.dispatchEvent(event);
	    }
	</script>
</body>
</html>