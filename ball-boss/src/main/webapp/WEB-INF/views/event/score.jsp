<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>录入比分</title>

<style>
        .wrap{
            width: 70em;
            margin: 0 auto;
            margin-bottom: 1em;
         }
        .head b{
            border: 1px solid #424242;
            font-weight: normal;
            text-align: center;
            padding:3px 75px;
            margin-right: 2.5em;
        }
        .tit{
            line-height:40px;
        }
        .content{
       		width:70em;
        }
        .content table{
			border-collapse:collapse;
        }
        .content table td{
            width: 15em;
            text-align: center;
            line-height: 60px;
			border: 1px solid #000;
        }
		.vs{
			background:url(${ctx}/static/img/vs.jpg) no-repeat center center;
			background-size:50px 50px;
		}
		.time{
			line-height:50px;
		}
		.time b{
			font-weight:normal;
			padding: 0px 1em;
		 }
		.time input{
			border:none;
			outline:none;
			height:32px;
		}
		.time input[type=text]{
			width:90px;
			border:1px solid #b6b6b6;
			margin-right:18px;
			padding-left:4px;
		}
		.time input[type=button]
		{
			width:91px;
			background-color:#8b0601;
			border-radius:5px;
			color:#fff;
		}
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
		<!-- / 右侧标题 -->

		<div class="panel-body">
			<c:forEach items="${data }" var="scoreVo" varStatus="sta">
				<div class="wrap">
					<div class="head">
			            <b>第${scoreVo.turnNum }轮</b>
			            <c:if test="${scoreVo.gameType == 0}">
			            	<span>循环赛</span>
			            </c:if>
			            <c:if test="${scoreVo.gameType == 1}">
			            	<span>淘汰赛</span>
			            </c:if>
			        </div>
			        <c:forEach items="${scoreVo.scoreGroupList }" var="scoreGroupVo" varStatus="sta">
			        	<c:if test="${sta.index != 0 }">
			        		<hr>
			        	</c:if>
			        	<div class="tit">${scoreGroupVo.gameGroup }组</div>
			        	<c:forEach items="${scoreGroupVo.scoreList }" var="gameScoreVo">
			        	<div>
			        		<div class="content">
					            <table>
					                <tr>
					                    <td><img width="50" height="50" src="${gameScoreVo.logoA }"></td>
					                    <td>${gameScoreVo.nameA }</td>
					                    <td>${gameScoreVo.integrationA }分</td>
					                    <td class="vs"></td>
					                    <td>${gameScoreVo.integrationB }分</td>
					                    <td>${gameScoreVo.nameB }</td>
					                    <td><img width="50" height="50" src="${gameScoreVo.logoB }"></td>
					                </tr>
					            </table>
					        </div>
				            <div class="time">
				            	<form action="" method="post">
					                <span>开始时间：${gameScoreVo.gameTime }</span>
					                <b>A战队：</b>
					                <input type="text" class="form-control" name="inA" id="inA" placeholder="请输入积分" />
					                <b>B战队：</b>
					                <input type="text" class="form-control" name="inB" id="inB" placeholder="请输入积分" />
					                <b>比分：</b>
					                <c:if test="${empty gameScoreVo.score}">
				                		<input type="text" class="form-control" name="score" id="score" placeholder="请输入比分"  value="${gameScoreVo.score }"/>
					                </c:if>
					                <c:if test="${!empty gameScoreVo.score}">
				                		<input type="text" class="form-control" name="score" id="score" placeholder="请输入比分"  value="${gameScoreVo.score }"/>
					                </c:if>
					                <input type="hidden" name="gScheduleId" id="gScheduleId" value="${gameScoreVo.id }"/>
					                <input type="hidden" name="idA" id="idA" value="${gameScoreVo.idA }"/>
					                <input type="hidden" name="idB" id="idB" value="${gameScoreVo.idB }"/>
				                	<input type="button" name="button" class="btn btn-default btn-primary" value="确定"  />
				            	</form>
			              	</div>
			        	</div>
			        	</c:forEach>
			        </c:forEach>
				</div>
			</c:forEach>
		</div>
		<input type="hidden" name="gameId" id="gameId" value="${gameId }"/>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#event-man');
			$('#adminFooter').hide();
			
			$(".time input[type=button]").click(function(e){
				var obj = $(this);
				var $form = obj.parent();
				var inA = obj.parent().children("input:eq(0)").val();
				var inB = obj.parent().children("input:eq(1)").val();
				var score = obj.parent().children("input:eq(3)").val();
				if (inA == ''){
					myAlert("请输入战队A积分");
					return false;
				}
				if (inB == ''){
					myAlert("请输入战队B积分");
					return false;
				}
				if (score == ''){
					myAlert("请输入比分");
					return false;
				}
				
				$.ajax({
					url: "${ctx}/event/saveScore",
					type: "post",
					async: false,
					data:$form.serialize(),
					dataType: 'json',
					success:function(data){
						if (data.result){
							myAlert("保存成功");
							obj.parent().children("input:eq(0)").val('');
							obj.parent().children("input:eq(1)").val('');
							$form.parent().parent().children(".content").find("table tr:eq(0) td:eq(2)").html(inA + "分");
							$form.parent().parent().children(".content").find("table tr:eq(0) td:eq(4)").html(inB + "分");
						} else {
							myAlert("保存失败");
						}
					}
				})
			})
			
		});

	</script>

</body>
</html>