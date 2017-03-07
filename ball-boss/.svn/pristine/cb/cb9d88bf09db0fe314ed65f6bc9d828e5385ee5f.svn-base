<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>录入比分</title>
<link rel="stylesheet" href="${ctx }/static/css/tree.css">
<style>
.saveScore{cursor:pointer;}
</style>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 录入比分</li>
			</ul>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12 text-right">
				<c:if test="${(isScoreNotice!=1&&isOffical!=1&&((gameFormat == 1&&gameState == 2)||(gameFormat != 1&&gameState==1))) || (isScoreNotice==2&&isOffical==1)}" >
					<a href="#" id="notice" class="btn btn-default btn-sm">
						<i class="glyphicon glyphicon glyphicon-cog"></i> 发布成绩公告</a>
			    </c:if>
			    <c:if test="${gameState == 1&&gameFormat == 1 }" >
					<a href="#" id="createSche"
						class="btn btn-default btn-sm"><i
						class="glyphicon glyphicon glyphicon-cog"></i> 生成淘汰赛程</a>
			    </c:if>
				<a class="btn btn-default btn-sm" href="${ctx}/enjoyRace/list">
					<span class="glyphicon"></span> 返回
				</a> &nbsp;&nbsp;
				</div>
			</div>
		<c:if test="${gameFormat == 1 }" >
			<legend>
				<small>循环赛程</small>
			</legend>
			<div class="row" style="margin-top:30px;">
			<div class="col-md-1 text-left">
				<a href="${ctx }/enjoyRace/exportScore/${gameId}" class="btn btn-default btn-sm">
					<i class="glyphicon glyphicon glyphicon-cog"></i>导出循环赛比分</a>
			</div>
			<c:if test="${gameState != 2}" >
			<div class="col-md-1 pull-left">
				<input id="scoreFile" type="file" multiple="false" />
			</div>
			</c:if>
		</div>
		<div id="groupSchedule">
			
		</div>
		</c:if>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
		 <form id="actionForm" method="post">
			<c:forEach items="${fields }" var="score" varStatus="sta1">
				<c:if test="${sta1.index % 2 == 0 }">
					<div class="gameScheduleClear">
				</c:if>
					<div class="gameSchedule">
			            <table>
				            <th colspan=12>${score.key }号场地</th>
					        <c:forEach items="${score.value }" var="playList" varStatus="sta">
					                <tr>
					                	<td><input type="hidden" id="id" name="id" value="${playList.id }"></td>
					                	<td><input type="hidden" id="gameId" name="gameId" value="${playList.gameId }"></td>
					                	<td><input type="hidden" id="clasli1" name="clasli1" value="${playList.clasli1 }"></td>
					                	<td><input type="hidden" id="clasli2" name="clasli2" value="${playList.clasli2 }"></td>
					                    <td>第${playList.turn }轮</td>
					                    <%--<td><input type="text" id="gameTime" name="gameTime" style="width:200px" placeholder="比赛时间" class="form-control" value="${playList.gameTime }"--%>
					                 	<%--<c:if test="${gameState != 2}" >  onclick="WdatePicker({dateFmt:'MM-dd HH:mm',alwaysUseStartDate:true})" </c:if> /></td>--%>
					                    <td>${playList.p1 }</td>
					                    <td><input type="text"  id="score1" name="score1" style="width:50px" placeholder="分数" value="${playList.score1 }" /></td>
					                    <td class="vs"></td>
					                    <td>
					                    	<input type="text"  id="score2" name="score2" style="width:50px" placeholder="分数" value="${playList.score2 }" />
					                    	<c:if test="${playList.smallscore!=null&&playList.smallscore!=''}">
					                    		<small style="color:red;">(${playList.smallscore})</small>
					                    	</c:if>
					                    </td>
					                    <td>${playList.p2 }</td>
					                    <td>
					                    	<c:if test="${gameState != 2}" >
					                   			 <a class="saveScore">录入</a>
					                   		</c:if>
					                    </td>
					                	<td><input type="hidden" id="smallscore" name="smallscore" value="${playList.smallscore }"></td>
					                </tr>
					        </c:forEach>
			            </table>
					</div>
			</c:forEach>
			<input type="hidden" name="gameLevel" id= "gameLevel" value="${gameLevel }">
			<input type="hidden" name="gameType" id= "gameType" value="${gameType }">
			<input type="hidden" name="eventId" id="eventId" value="${gameId }"/>
			<input type="hidden" name="gNum" id="gNum" value="${gnum }"/>
			<input type="hidden" name="sNum" id="sNum" value="${snum }"/>
			<input type="hidden" name="type" id="type" value="2"/>
			<input type="hidden" name = "gameFormat" id="gameFormat" value="1"/>
			<input type="hidden" name="isScoreNotice" id="isScoreNotice" value="${isScoreNotice}"/>
			<input type="hidden" name="drawResult" id= "drawResult" value="">
		</form>
			<legend id="scheDivT" style="display: none;padding-top: 20px;" >
				<small>淘汰赛程</small>
			</legend>
			<canvas id="gameTreeCanvas"></canvas>
			<div class="wrapper">
				<div id="scheDiv" class="text-center col-md-12 gameTree">
			</div>
			</div>
		</div>
		
		<!-- /右侧主体内容 -->
	</div>
					<div class="modal inmodal fade" id="drawModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header" style="border:none;">
				抽签
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>
				<div class="drawPlayers">
					<dl>
						<dd>
							<span class="group_name">A组(抽第二名)</span>
							<span class="drawPlayer"><button class="btn">阿布</button></span>
							<span class="drawPlayer"><button class="btn">ss</button></span>
						</dd>
						<dd>
							<span class="group_name">B组(抽第二名)</span>
							<span class="drawPlayer"><button class="btn active">阿布</button></span>
							<span class="drawPlayer"><button class="btn">ss</button></span>
						</dd>
					</dl>
				</div>
				<div class="modal-footer" style="border:none;">
					<button  type="button" class="btn btn-white" id="enterScoreBtn" onclick="drawPlayer()">确定</button>
					<button  type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
		<script src="${ctx}/static/js/gameTree.js"></script>
	<script type="text/javascript">
		var gameFormat1 = '${ gameFormat2}';
		$(function() {
			upload({'id':'scoreFile'});
			var availableScoreFor4 = ["4:0","4:1","4:2","4:3","0:4","1:4","2:4","3:4"];
			var availableScoreFor6 = ["6:0","6:1","6:2","6:3","6:4","0:6","1:6","2:6","3:6","4:6","7:5","5:7","7:6","6:7"];
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
			
			var gameFormat = '${gameFormat}';
			
			if(gameFormat==='1'){
				var groupSchedules = eval('('+'${groupSchedules}'+')');
				var group_temp = new EJS({url: '${ctx}/static/template/groupSchedule.ejs?ver=1.6'});
				var schedule = {};
				schedule["gameFormat"] = 1;
				schedule["groupScheduleList"] = groupSchedules;
				var group_html = group_temp.render({schedule:schedule});
				$("#groupSchedule").html(group_html);
				$("#groupSchedule table td").css({"width":"120px","height":"40px"})
			}
			
			$(".gameSchedule a").click(function(e){
				var obj = $(this);
				var id = obj.parent().parent().children("td:eq(0)").children("input:eq(0)").val();
				var gameId = obj.parent().parent().children("td:eq(1)").children("input:eq(0)").val();
				var clasli1 = obj.parent().parent().children("td:eq(2)").children("input:eq(0)").val();
				var clasli2 = obj.parent().parent().children("td:eq(3)").children("input:eq(0)").val();
				/*var gameTime = obj.parent().parent().children("td:eq(5)").children("input:eq(0)").val();
				var score1 = obj.parent().parent().children("td:eq(7)").children("input:eq(0)").val();
				var score2 = obj.parent().parent().children("td:eq(9)").children("input:eq(0)").val();
				if (gameTime == ''){
					myAlert("请输入比赛时间");
					obj.parent().parent().children("td:eq(1)").children("input:eq(0)").focus();
					return false;
				}*/
				var score1 = obj.parent().parent().children("td:eq(6)").children("input:eq(0)").val();
				var score2 = obj.parent().parent().children("td:eq(8)").children("input:eq(0)").val();
				if (score1 == ''){
					myAlert("请输入比分");
					obj.parent().parent().children("td:eq(7)").children("input:eq(0)").focus();
					return false;
				}
				if (score2 == ''){
					myAlert("请输入比分");
					obj.parent().parent().children("td:eq(9)").children("input:eq(0)").focus();
					return false;
				}
				if(gameFormat1==='0'){
					if(availableScoreFor4.indexOf(score1+":"+score2)==-1){
						myAlert("比分不符合4局制！");
						if(score1 >= score2){
						/*	obj.parent().parent().children("td:eq(7)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(7)").children("input:eq(0)").focus();*/
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").focus();
						}else{
							/*obj.parent().parent().children("td:eq(9)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(9)").children("input:eq(0)").focus();*/
							obj.parent().parent().children("td:eq(8)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(8)").children("input:eq(0)").focus();
						}
			    		return false;
					}
				}else{
					if(availableScoreFor6.indexOf(score1+":"+score2)==-1){
						myAlert("比分不符合6局制！");
						if(score1 >= score2){
							/*obj.parent().parent().children("td:eq(7)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(7)").children("input:eq(0)").focus();*/
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").focus();
						}else{
							/*obj.parent().parent().children("td:eq(9)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(9)").children("input:eq(0)").focus();*/
							obj.parent().parent().children("td:eq(8)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(8)").children("input:eq(0)").focus();
						}
			    		return false;
					}
				}
				 var smallscore="";
				 if("4:3".indexOf(score1+":"+score2)!=-1||"3:4".indexOf(score1+":"+score2)!=-1
						||"7:6".indexOf(score1+":"+score2)!=-1||"6:7".indexOf(score1+":"+score2)!=-1){
					var smallscore = obj.parent().parent().children("td:eq(11)").children("input:eq(0)").val();
					smallscore = window.prompt("请输入小分",smallscore);
					if(smallscore){
						
					}else{
						myAlert("请输入小分","error");
						return;
					}
					
				}
/*
				var schedule = {"id":id,"gameId":gameId,"gameTime": gameTime,"clasli1":clasli1,"score1":score1,"score2":score2,"clasli2":clasli2};
*/
				var schedule = {};
				if(smallscore){
					schedule = {"id":id,"gameId":gameId,"clasli1":clasli1,"score1":score1,"score2":score2,"clasli2":clasli2,"smallscore":smallscore};
				}else{
					schedule = {"id":id,"gameId":gameId,"clasli1":clasli1,"score1":score1,"score2":score2,"clasli2":clasli2};
				}
				$.ajax({
					url: "${ctx}/enjoyRace/scoreSave",
					type: "post",
					async: false,
					data: schedule,
					dataType: 'json',
					success:function(data){
						if (data.result){
							myAlert("保存成功");
							if(smallscore){
								obj.parent().parent().children("td:eq(11)").children("input:eq(0)").val(smallscore);
								obj.parent().parent().find("small").html("("+smallscore+")")
							}
					/*		obj.parent().parent().children("td:eq(5)").children("input:eq(0)").val(gameTime);
							obj.parent().parent().children("td:eq(7)").children("input:eq(0)").val(score1);
							obj.parent().parent().children("td:eq(9)").children("input:eq(0)").val(score2);*/
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").val(score1);
							obj.parent().parent().children("td:eq(8)").children("input:eq(0)").val(score2);
							var temp = new EJS({url: '${ctx}/static/template/enjoyScore.ejs?ver='+Math.random()});
							var html = temp.render({items:data["data"]});
							$(".scoreView").html(html);
						} else {
							/*obj.parent().parent().children("td:eq(5)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(7)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(9)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(5)").children("input:eq(0)").focus();*/
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(8)").children("input:eq(0)").val("");
							obj.parent().parent().children("td:eq(6)").children("input:eq(0)").focus();
							myAlert("保存失败");
						}
					}
				})
			})
			
		    $.post("${ctx}/enjoyRace/scheduleList",{gameFormat:2,gameId:$("#eventId").val()},
				 function(data) {
				if (data.result) {
					var format = $('#gameFormat').val();
						var temp = new EJS(
								{
									url : '${ctx}/static/template/treeNode.ejs?ver='+ Math.random()
								});
						if (data.data != null) {
							$("#scheDiv").gameTree(temp,data.data);
							$("#scheDivT").css("display","block");
							gameTreeForCanvas(data.data);
						}
				}
			},"json");
			
			$("#createSche").click(beginOut);
			
		
			
		$("#notice").click(function(){
			Util.getData(ctx + '/enjoyRace/notice', function(data){
		      	 if(data.result){
		      		window.location.reload();
			     }else{
			    	 myAlert(data.data);
			    	 return false;
				 }
		      }, null, {"eventId":$("#eventId").val(),"gameType":$("#gameType").val(),"gameLevel":$("#gameLevel").val()}, 'post');
	  });
	});
		
		function beginOut(){
			var obj_ = $("#createSche");
			$.ajax({
				cache : true,
				type : "POST",
				url : '${ctx}/enjoyRace/createSche',
				data :$('#actionForm').serialize(),
				async : false,
				error : function(request) {
					common.showMessage("失败！");
					obj_.show();
				},
				success : function(data) {
					data = eval("(" + data + ")");
					if (data.result) {
						if(data["data"]["sameScores"]!=null){
							obj_.show();
		    				var sameScores = data["data"]["sameScores"];
		    				var drawPlaysHtml="";
		    				$.each(sameScores,function(key,item){
		    					var drawPlayHtml = '<dd>';
		    					$.each(item,function(key_,item_){
		        					var tip = "";
		        					if(key_=='2'){
		        						tip = "抽第二名";
		        					}else{
		        						tip = "抽一、二名";
		        					}
		        					drawPlayHtml = drawPlayHtml+ '<span class="group_name" draw_type="'+key_+'" group_id="'+key+'">'+key+'组('+tip+')</span>';
		        					$.each(item_,function(index,it){
		        						drawPlayHtml = drawPlayHtml+ '<span class="drawPlayer" member_id="'+it["memberId"]+'"><button draw_type="'+key_+'" class="btn">'+it["name"]+'</button></span>';
		        					});
		        				})
		    					drawPlayHtml = drawPlayHtml+ '</dd>';
		        				drawPlaysHtml = drawPlaysHtml+drawPlayHtml;
		    				});
		    				$(".drawPlayers dl").html(drawPlaysHtml);
		    				$(".drawPlayer .btn").on("click",function(){
		    					if($(this).attr("draw_type")=='12'){
		    						if($(this).hasClass("active")){
		        						$(this).find("i").remove();
		        						var i = $(this).closest("dd").find("i");
		        						if(i.size()==1&&i.text()=="2"){
		        							i.text("1");
		        						}
		        					}else{
		        						var i = $(this).closest("dd").find("i");
		        						if(i.size()==0){
		        							$(this).append('<i class="badge pull-right" style="margin-right:-10px;">1</i>');
		        						}else if(i.size()==1){
		        							$(this).append('<i class="badge pull-right" style="margin-right:-10px;">2</i>');
		        						}else{
		        							$(this).append('<i class="badge pull-right" style="margin-right:-10px;">1</i>');
		        							$(this).parent().siblings().find("i").remove();
		        							$(this).parent().siblings().find("button").removeClass("active");
		        						}
		        					}
		    					}
		    					$(this).toggleClass("active");
		    				})
		    				$("#drawModal").modal();
							$("body").css('padding-right','0px');
							drawPlayer = function(){
								var drawResult = {};
								var flag_ = true;
								$(".drawPlayers dl").children("dd").each(function(index,item){
									var draw_type = $(item).children(".group_name").attr("draw_type");
									var selecSize = $(item).find(".active").size();
									if(selecSize==0){
										myAlert("请抽签！");
										flag_ = false;
										return false;
									}
									if(draw_type=='2'){
										if(selecSize!=1){
											myAlert("抽第二名时只能选择一个！");
											flag_ = false;
											return false;
										}
									}else{
										if(selecSize!=2){
											myAlert("抽第一、二名时必须选择两个！");
											flag_ = false;
											return false;
										}
									}
									if(flag_){
										var groupId = $(item).children(".group_name").attr("group_id");
										var member_id = '';
										if(draw_type=='2'){
											member_id = $(item).find(".active").parent().attr("member_id");
										}else{
											var member_ids = new Array(2);
											$(item).find(".active").each(function(index,obj){
												if($(obj).find("i").text()=='1'){
													member_ids[0] = $(obj).parent().attr("member_id");
												}else{
													member_ids[1] = $(obj).parent().attr("member_id");
												}
											});
											member_id = member_ids.join();
										}
										drawResult[groupId] = {"draw_type":draw_type,"member_id":member_id};
									}
								});
								if(flag_){
									$("#drawModal").modal("hide");
									console.log(drawResult);
									$("#drawResult").val(JSON.stringify(drawResult));
									beginOut();
								}
							}
		    				return;
		    			}
						obj_.hide();
						var temp = new EJS(
								{
									url : '${ctx}/static/template/treeNode.ejs?ver='+ Math.random()
								});
						if (data.data != null) {
							$("#scheDiv").gameTree(temp,data.data);
							$("#scheDivT").css("display","block");
							gameTreeForCanvas(data.data);
							 $.post("${ctx}/enjoyRace/updateGameSate",{gameId:$("#eventId").val(),state:2},
									 function(data) {},"json");
						}
					}else{
						myAlert(data.data);
						obj_.show();
					}
				}
			});
	  }
		
		function entryScore(id,lId,rId){
			var po = $("#"+id);
			if(po.find("ul").children("li:eq(0)").find("a:eq(0)").text().indexOf("轮空")!=-1||po.find("ul").children("li:eq(1)").find("a:eq(0)").text().indexOf("轮空")!=-1){
				return;
			}
			$("#myDlgBody_lg").load("${ctx}/enjoyRace/entryScore_dlg?id="+id+"&lid="+lId+"&rid="+rId+"&gameFormat2="+gameFormat1);
			$("#myDlgBody_lg").css("width","802px");
			$("body").css('padding-right','0px');
			$("#myDlg_lg").modal();
		}

		function upload(option){
			var id = option.id || "icon_upload";
			$("#"+id).uploadify({
				//文件提交到 controller 里的文件对象的 key
				fileObjName   : 'upfile',
				//按钮名称
				buttonText    : '导入循环赛比分',
				height        : 30,
				multi         :false,
				swf           : ctx + '/static/uploadify/uploadify.swf',
				//提交到指定的 controller,写死即可，已封装
				uploader      : ctx + '/enjoyRace/uploader',
				width         : 100,
				formData	  : {"gameId":$("#eventId").val()},
				fileTypeExts:'*',
				//上传成功后回调此函数
				onUploadSuccess : function(file, data, response) {
					data = JSON.parse(data);
					console.log(data);
					if(data.success==true){
						window.location.reload();
					}else{
						myAlert(data.result.result);
					}
				}
			});
		}
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
		
	</script>

</body>
</html>