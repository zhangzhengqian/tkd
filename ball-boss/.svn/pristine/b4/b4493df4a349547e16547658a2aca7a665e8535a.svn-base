<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投票主题</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 资讯管理</li>
				<li class="active">资讯</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>手机号</th>
								<th>姓名</th>
								<th>快递</th>
								<th>快递单号</th>
								<th>奖品</th>
								<th>是否通知</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="gift" varStatus="stat">
								<tr>
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${gift.phone }</td>
									<td class="text-center">${gift.name }</td>
									<td class="text-center">${gift.express}</td>
									<td class="text-center">${gift.expressNumber}</td>
									<td class="text-center">${gift.award}</td>
									<td class="text-center">
									<c:choose>
										<c:when test="${gift.isNotice == 0}">
											未通知
										</c:when>
										<c:when test="${gift.isNotice == 1}">
											已通知
										</c:when>
									</c:choose></td>
									<td class="text-center">
										<a href="${ctx }/gift/view/${gift.id}" class="btn btn-default btn-sm">修改</a>
										<a onclick="sendSms('${gift.id}',this)" class="btn btn-default btn-sm">短信通知</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
		<tags:pagination page="${data}" />
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<div class="modal fade" id="msgModel" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">短信内容</h4>
				</div>
				<div class="modal-body" class="">
					<div  class="form-group">
						<textarea rows="5" cols="10" id="msg" class="form-control" placeholder="请填写短信内容"></textarea>
						<input type="hidden" id="gift_id"/>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="sendMsg()">发送</a>
			</div>
		</div>
	</div>
</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#gift-man');
			$('#adminFooter').hide();
		});
		function sendSms(id,obj){
			$("#gift_id").val(id);
			$("#msgModel").modal();
			var tr = $(obj).parent().parent();
			$("#msg").val(tr.find("td:eq(2)").text()+" "+tr.find("td:eq(1)").text()+" "+tr.find("td:eq(3)").text()+" "+tr.find("td:eq(4)").text()+" "+tr.find("td:eq(5)").text());
		}
		function sendMsg(){
			  var msg = $("#msg").val();
			  var id = $("#gift_id").val();
			  if(msg==''){
				  bootbox.alert('请编辑短信内容！');
				  return;
			  }
			  Util.getData(ctx + '/gift/sendMsg/'+id, function(data){
		      	 if(data.result){
			      	 myAlert("短信发送成功");
			      	 $("#msgModel").modal("hide");
			     }else{
			    	 myAlert("短信发送失败","error");
				 }
		      }, null, {"msg":msg}, 'post');
		}
	</script>
</body>
</html>