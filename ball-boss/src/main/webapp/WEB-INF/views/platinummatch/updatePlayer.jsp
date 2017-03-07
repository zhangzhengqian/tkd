<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>乐享赛</title>
<link rel="stylesheet" href="${ctx }/static/css/tree.css">
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>修改人员信息</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
					<form id="playerForm" class="form-horizontal" action="" method="post">
			           <table class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th>姓名</th>
								<th>身份证号</th>
								<th>性别</th>
								<th>手机号</th>
							</tr>
						</thead>
						<tbody>
			                <tr>
			                <input type="hidden" id="eliId" name="eliId" value="${player.eliId}" >
			                	<input type="hidden" id="idA" name="idA" value="${player.idA}">
			                    <td><input type="text" style="width:100px" id="nameA" name="nameA" value="${player.nameA}"></td>
			                	<td><input type="text" style="width:300px" id="cardNoA" name="cardNoA" value="${player.cardNoA}"></td>
			                	<td>
			                	 <select class="form-control" name="sexA" id="sexA" >
									<option id="option1" <c:if test="${player.sexA == 1} " > selected="selected" </c:if> value="1" >--男--</option>
									<option id="option2" <c:if test="${player.sexA == 0}" > selected="selected" </c:if>  value="0" >--女--</option>
								</select>
			                	<input type="hidden" style="width:100px"   id="sexA" name="sexA" value="${player.sexA}"></td>
			                	<td><input type="text" style="width:100px" id="phoneA" name="phoneA" value="${player.phoneA}"></td>
			                </tr>
			                <c:if test="${doubleFlag == 1 }">
			                  <tr>
			                    <td><input type="text" style="width:100px" id="nameB" name="nameB" value="${player.nameB}"></td>
			                	<td><input type="text" style="width:300px" id="cardNoB" name="cardNoB" value="${player.cardNoB}"></td>
			                	<td>
			                	 <select class="form-control" name="sexB" id="sexB" >
									<option id="option1" <c:if test="${player.sexB == 1} " > selected="selected" </c:if> value="1" >--男--</option>
									<option id="option2" <c:if test="${player.sexB == 0 }" > selected="selected" </c:if>  value="0" >--女--</option>
								</select>
			                	<input type="hidden" style="width:100px"   id="sexB" name="sexB" value="${player.sexB}"></td>
			                	<td><input type="text" style="width:100px" id="phoneB" name="phoneB" value="${player.phoneB}"></td>
			                	<input type="hidden" id="idB" name="idB" value="${player.idB}">
			                 </tr>
			                </c:if>

			         </tbody></table>
					<div class="form-group">
						<hr>
						<div class="col-md-offset-3 col-md-2">
							<a class="btn btn-default btn-block" href="#"  id="cancel_btn"><span
								class="glyphicon glyphicon-remove"></span> 返回</a>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-block"
								id="save_btn">
								<span class="glyphicon glyphicon-ok"></span> 确认
							</button>
						</div>
					   </button>
					</div>
			        </form>
			</div>
		</div>
	
		
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#platinumMatch-man');
			$('#adminFooter').hide();
			$("#save_btn").click(function(){
				var obj = $(this);
				var idA = $("#idA").val();
				var idB = $("#idB").val();
				$.ajax({
					url: "${ctx}/platinumMatch/savePlayer",
					type: "post",
					async: false,
					data:$('#playerForm').serialize(),
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