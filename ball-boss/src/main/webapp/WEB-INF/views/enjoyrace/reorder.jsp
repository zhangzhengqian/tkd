<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<style type="text/css"> 
.placeHolder{background-color:white!important;border:dashed 1px gray!important;} 
</style> 
<head>
<title>参赛人员</title>
<script src="${ctx}/static/js/jquery/jquery-1.8.3.js"></script>
<script src="${ctx}/static/js/jquery/jquery.dragsort-0.5.2.min.js"></script>
</head>

<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li class="active">乐享赛事</li>
				<li class="active">参赛成员</li>
				<li class="active">种子排位</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
			选中行拖动鼠标进行排序
			</div>
			<div class="row">
			
			<form id="actionForm" action="" method="post">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead"  id="dataThead">
							<tr  style="cursor: pointer;">
								<th class="text-center">序号</th>
								<th>姓名</th>
								<th>身份证号</th>
								<th>性别</th>
								<th>手机号码</th>
								<c:if test="${doubleFlag == 1}">
									<th>双打队友</th>
									<th>姓名</th>
									<th>性别</th>
									<th>身份证号</th>
									<th>手机号码</th>
								</c:if>
								<th>排位</th>
							</tr>
						</thead>
						<tbody id="data">
							<c:forEach items="${list}" var="user" varStatus="stat">
								<tr>
									
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${user.nameA }</td>
									<td class="text-center">${user.cardNoA}</td>
									<td class="text-center"><c:if test="${user.sexA == 0}">女</c:if>
										<c:if test="${user.sexA == 1}">男</c:if></td>
									<td class="text-center">${user.phoneA}</td>
									<c:if test="${user.doubleFlag == 1}">
										<td class="text-center">${user.nameB }</td>
										<td class="text-center">${user.cardNoB}</td>
										<td class="text-center"><c:if test="${user.sexB == 0}">女</c:if>
											<c:if test="${user.sexB == 1}">男</c:if></td>
										<td class="text-center">${user.phoneB}</td>
									</c:if>
									<td class="text-center">${user.seedNum }</td>
									<td name="id" style="display: none;">${user.idA }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- end col-table -->
			</div>
				</form>
			<!-- end row -->
			<div class="form-group">
		<hr>
		<div class="col-md-offset-3 col-md-2">
			<a class="btn btn-default btn-block" href="${ctx}/enjoyRace/memberList/${eventId}"><span
				class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>
		<div class="col-md-3">
		    <button type="button" onclick="save()" class="btn btn-primary btn-block" > 保存排位 </button>
		</div>
	</div>
		</div>
		<!-- /右侧主体内容 -->
		
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
            $("#data").dragsort({ itemSelector: "tr", dragSelector: "tr", dragBetween: true, placeHolderTemplate: "<tr></tr>" });  
		});
        
        function save(){
            var ids = $("[name='id']").map(function() { return $(this).html(); }).get();  
            var $form = $('#actionForm');
			  bootbox.confirm('您确定要保存排位吗？', function(result) {
			    if(result) {
			      Util.getData(ctx + '/enjoyRace/saveReOrder', function(data){
			      	 if(data.result){
				      	 myAlert("排位成功");
				      	 window.location.reload()
				     }else{
				    	 myAlert("排位失败","error");
					 }
			      }, null, {"ids":ids.join("|")}, 'post');
			    }
			  });
			  return false;
			}

	</script>
</body>
</html>