<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>奖金审核</title>
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
				       <div class="col-md-6 has-feedback">
				       		<textarea class="form-control" rows="3" style="height: 50px;" id="reason" name="reason" ></textarea>
				       </div>
				    </div>
					<hr />
                    <input type="hidden" id="mBid" name="mBid" value="${mBid}" >
                	<input type="hidden" id="mAid" name="mAid" value="${mAId}">
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
				}
				var data = {"mAid":$("#mAid").val(),"mBid":$("#mBid").val(),"reason":reason};
				$.ajax({
					url: "${ctx}/enjoyRace/saveRefuse",
					type: "post",
					async: false,
					data:data,
					dataType: 'json',
					success:function(data){
						if (data.result){
							myAlert("审核成功");
							$("#myDlg_lg").modal('hide');
							 window.location.reload()
						} else {
							myAlert("审核失败");
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