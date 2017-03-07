<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>录入比分</title>
<link rel="stylesheet" href="${ctx }/static/css/tree.css">
</head>
<body>
	<div class="panel panel-default" style="width: 800px;">
		<div class="panel-heading"  style="width: 800px;">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 录入比分</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body"  style="width: 800px;">
					<div class="gameSchedule">
					<form id="actionForm" class="form-horizontal" action="" method="post">
			            <table>
				                <tr>
				                	<td><input type="hidden" id="id" name="id" value="${socreEli.eliId }"></td>
				                    <td>第${socreEli.turnNo }轮</td>
				                  <%--  <td><input type="text" id="gameTime" name="gameTime" style="width:185px" placeholder="比赛时间" class="form-control" value=""
				                    onclick="WdatePicker({dateFmt:'MM-dd HH:mm',alwaysUseStartDate:true})"/></td>--%>
				                    <td>${socreEli.nameA }</td>
				                    <td><input type="text" id="lscore" name="lscore" value="${socreEli.scoreA }" style="width:50px" placeholder="分数"  /></td>
				                    <td class="vs" style="background-image: "></td>
				                    <td>
				                    	<input type="text" id="rscore" name="rscore" value="${socreEli.scoreB }" style="width:50px" placeholder="分数" />
				                    </td>
				                    <td>${socreEli.nameB }</td>
				                	<td><input type="hidden" id="idA" name="idA" value="${socreEli.idA }"></td>
				                	<td><input type="hidden" id="idB" name="idB" value="${socreEli.idB }"></td>
				                	<td><input type="hidden" id="winner" name="winner" value=""></td>
				                	<td></td>
				                	<td></td>
				                	<td></td>
				                	<td>
				                		<button type="botton" class="btn btn-primary btn-sm" id="cancel_btn">
										<span class="glyphicon glyphicon-search"></span> 取消
										</button>
									</td>
				                	<td>	
					                	<button type="botton" class="btn btn-primary btn-sm" id="save_btn">
											<span class="glyphicon glyphicon-search"></span> 确定
										</button>
									</td>
				                </tr>
			            </table>
			        </form>
					</div>
		</div>
		
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			var availableScoreFor4 = ["4:0","4:1","4:2","4:3","0:4","1:4","2:4","3:4"];
			var availableScoreFor6 = ["6:0","6:1","6:2","6:3","6:4","0:6","1:6","2:6","3:6","4:6","7:5","5:7","7:6","6:7"];
			var gameFormat1 = '${gameFormat2}';
			$('#adminFooter').hide();
			$("#save_btn").click(function(e){
				var obj = $(this);
				var id = obj.parent().parent().children("td:eq(0)").children("input:eq(0)").val();
				/*var gameTime = obj.parent().parent().children("td:eq(2)").children("input:eq(0)").val();
				var score1 = obj.parent().parent().children("td:eq(4)").children("input:eq(0)").val();
				var score2 = obj.parent().parent().children("td:eq(6)").children("input:eq(0)").val();
				if (gameTime == ''){
					myAlert("请输入比赛时间");
					return false;
				}*/
				var score1 = obj.parent().parent().children("td:eq(3)").children("input:eq(0)").val();
				var score2 = obj.parent().parent().children("td:eq(5)").children("input:eq(0)").val();

				if (score1 == ''){
					myAlert("请输入比分");
					return false;
				}
				if (score2 == ''){
					myAlert("请输入比分");
					return false;
				}
				if(gameFormat1==='0'){
					if(availableScoreFor4.indexOf(score1+":"+score2)==-1){
						myAlert("比分不符合4局制！");
			    		return false;
					}
				}else{
					if(availableScoreFor6.indexOf(score1+":"+score2)==-1){
						myAlert("比分不符合6局制！");
			    		return false;
					}
				}
				if(score1 > score2){
					$("#winner").val($("#idA").val());
				}else if(score1 < score2){
					$("#winner").val($("#idB").val());
				}
				if(gameFormat1==='0'){
					if(availableScoreFor4.indexOf(score1+":"+score2)==-1){
						swal({
			                title: "警告",
			                text: "比分不符合4局制！"
			            });
			    		return;
					}
				}else{
					if(availableScoreFor6.indexOf(score1+":"+score2)==-1){
						swal({
			                title: "警告",
			                text: "比分不符合6局制！"
			            });
			    		return;
					}
				}
				
				 var smallscore="";
				 if("4:3".indexOf(score1+":"+score2)!=-1||"3:4".indexOf(score1+":"+score2)!=-1
						||"7:6".indexOf(score1+":"+score2)!=-1||"6:7".indexOf(score1+":"+score2)!=-1){
					var smallscore = $("#smallscore").val();
					smallscore = window.prompt("请输入小分",smallscore);
					if(smallscore){
						
					}else{
						myAlert("请输入小分","error");
						return;
					}
					
				}
				 var eli = {};
				if(smallscore){
					eli = {"id":id,"lscore": score1,"rscore":score2,"winner":$("#winner").val(),"smallscore":smallscore};
				}else{
					eli = {"id":id,"lscore": score1,"rscore":score2,"winner":$("#winner").val(),};
				}
			/*	var eli = {"id":id,"gameTime":gameTime,"lscore": score1,"rscore":score2,"winner":$("#winner").val(),};*/
				$.ajax({
					url: "${ctx}/enjoyRace/saveScoreEli",
					type: "post",
					async: false,
					data: eli,
					dataType: 'json',
					success:function(data){
						if (data.result){
							myAlert("保存成功");
							$("#myDlg_lg").modal('hide');
							 window.location.reload()
						} else {
							myAlert("保存失败");
						}
					}
				});
			});
			
			$("#cancel_btn").click(function(){
				$("#myDlg_lg").modal('hide');
			});
		});
		
	</script>

</body>
</html>