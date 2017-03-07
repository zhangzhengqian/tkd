<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改奖金</title>
<link rel="stylesheet" href="${ctx }/static/css/tree.css">
</head>
<body>
	<div class="panel panel-default" style="width: 800px;">
		<div class="panel-heading"  style="width: 800px;">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 修改奖金</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
					<div class="gameSchedule">
					<form id="actionForm" class="form-horizontal" action="" method="post">
			            <table>
			                <tr>
			                	<td><input type="hidden" id="id" name="id" value="${AId}"></td>
			                    <td>${nameA }</td>
			                    <td></td>
			                	<td></td>
			                    <td>${nameB }</td>
			                    <td>奖金</td>
			                    <td><input type="text" style="width:50px" id="bonus" name="bonus" value="${bonus}"></td>
			                	<td>/人</td>
			                	<td><input type="hidden" id="idA" name="idA" value="${AId}"></td>
			                	<td><input type="hidden" id="idB" name="idB" value="${BId}"></td>
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
			$('#adminFooter').hide();
			$("#save_btn").click(function(){
				var obj = $(this);
				var bonus = $("#bonus").val();
				var idA = $("#idA").val();
				var idB = $("#idB").val();

				if (bonus == ''){
					myAlert("请输入奖金");
					return false;
				}
				var member = {"idA":idA,"idB":idB,"bonus": bonus};

				$.ajax({
					url: "${ctx}/enjoyRace/saveBonus",
					type: "post",
					async: false,
					data: member,
					dataType: 'json',
					success:function(data){
						if (data.result){
							myAlert("修改成功");
							$("#myDlg_lg").modal('hide');
							 window.location.reload()
						} else {
							myAlert("修改失败");
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