<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>报名人员审核</title>
<link rel="stylesheet" href="${ctx }/static/css/tree.css">
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>拒绝原因</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
					<form id="auditForm" class="form-horizontal" action="" method="post">
			          	    <div class="form-group form-group-sm">
				       <label for="name" class="col-md-3 control-label">原因:</label>
								<input type="hidden" name="id" id="id" value="${id}">
								<input type="hidden" name= "idA" id="idA" value="${idA}">
								<input type="hidden" name="state" id="state" value="${state}">
				       <div class="col-md-6 has-feedback">
				       		<textarea class="form-control" rows="3" style="height: 50px;" id="reason" name="reason" ></textarea>
				       </div>
				    </div>
					<hr />
					<div class="form-group">
						<div class="col-md-offset-3 col-md-2">
							<a class="btn btn-default btn-block" href="#"  id="cancel_btn"><span
								class="glyphicon glyphicon-remove"></span> 返回</a>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-block"
								id="audit_btn">
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
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
			
			$("#audit_btn").click(function(){
				var reason = $("#reason").val();
				if(!reason){
					myAlert("请填写拒绝原因.");			
					return ;
				}else{
					window.parent.auditMember($("#id").val(),$("#idA").val(),$("#state").val(),reason);
					$("#myDlg_lg").modal('hide');
				}
			});
			
			$("#cancel_btn").click(function(){
				$("#myDlg_lg").modal('hide');
				//window.parent.auditMember($("#id").val(),$("#idA").val(),$("#state").val());
			});
		});
		
	</script>

</body>
</html>