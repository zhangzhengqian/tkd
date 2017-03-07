<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>参赛人员</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
				<li class="active">乐享赛事</li>
				<li class="active">参赛成员</li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<div class="col-md-12">
				<div class="btn-group-sm pull-right">
			   	<shiro:hasPermission name="enjoyrace:update">
					<c:if test="${empty gameState || gameState == '0'}" >
						<div class="btn-group" role="group" aria-label="...">
							<a class="btn btn-default btn-sm"
								href="${ctx}/enjoyRace/reorder/${id}"> <i
								class="glyphicon glyphicon-edit"></i>种子排位
							</a>
						</div>
						<a href="#" id="createBtn"
							class="btn btn-default btn-sm"><i
							class="glyphicon glyphicon glyphicon-plus"></i> 生成赛程</a>
				   </c:if>
			 </shiro:hasPermission>
						<a href="${ctx}/enjoyRace/export/${id}" id="exportInfo"
							class="btn btn-default btn-sm"><i
							class="glyphicon glyphicon glyphicon-export"></i> 导出报名信息</a>
							<a href="javascript:;" id="collectIntegral"
				   class="btn btn-default btn-sm"><i
						class="glyphicon glyphicon glyphicon-export"></i> 汇总积分</a>
						<a class="btn btn-default btn-sm" href="${ctx}/enjoyRace/list">
								<span class="glyphicon"></span> 返回
							</a>
						</div>
			</div>
			<div class="row">
				<div class="col-table col-md-12">
					<table id="contentTable"
						class="table table-bordered table-condensed table-hover">
						<thead class="thead">
							<tr>
								<th class="text-center">序号</th>
								<th>姓名</th>
								<th>身份证号</th>
								<th>性别</th>
								<th>手机号码</th>
								<th>队友姓名</th>
								<th>队友性别</th>
								<th>队友身份证号</th>
								<th>队友手机号码</th>
								<th>CTA积分</th>
								<th>报名时间</th>
								<th>审核状态</th>
							    <th>原因</th>
								<th>是否种子</th>
							    <th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="user" varStatus="stat">
								<tr
									class="<c:if test="${user.state == 0}">warning</c:if><c:if test="${user.state == 1}">success</c:if><c:if test="${user.state == 2}">danger</c:if>">
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${user.nameA }</td>
									<td class="text-center">${user.cardNoA}</td>
									<td class="text-center"><c:if test="${user.sexA == 0}">女</c:if>
										<c:if test="${user.sexA == 1}">男</c:if></td>
									<td class="text-center">${user.phoneA}</td>
									<c:if test="${user.doubleFlag == 1}">
										<td class="text-center">${user.nameB }</td>
										<td class="text-center"><c:if test="${user.sexB == 0}">女</c:if>
											<c:if test="${user.sexB == 1}">男</c:if></td>
										<td class="text-center">${user.cardNoB}</td>
										<td class="text-center">${user.phoneB}</td>
									</c:if>
									<c:if test="${user.doubleFlag != 1}">
										<td class="text-center"> </td>
										<td class="text-center"> </td>
										<td class="text-center"> </td>
										<td class="text-center"> </td>
									</c:if>
									<td class="text-center">${user.ctaIntegral}</td>
									<td class="text-center">${user.ct }</td>
									<td class="text-center"><c:if test="${user.state == 0}">未审核</c:if>
										<c:if test="${user.state == 1}">通过</c:if> <c:if
											test="${user.state == 2}">未通过</c:if></td>
								    	<td class="text-center">${user.reason}</td>
									<td><c:if test="${user.isSeed == 1 }">是</c:if> <c:if
											test="${empty user.isSeed || user.isSeed == 0 }">否</c:if></td>
							   
									<td class="text-center">
									<shiro:hasPermission name="enjoyrace:update">
										<a href="#" onclick="refuseSigned('${id}','${user.idA}',2)">不通过</a>
									  <c:if test="${empty gameState || gameState == '0'}" >
										  <c:if test="${user.state == 1 && (user.isSeed == 0 || empty user.isSeed)}">
												<a href="#" onclick="mark('${id}','${user.idA }',1)">设为种子</a>
										  </c:if> 
										  <c:if test="${user.state == 1 && user.isSeed == 1}">
												<a href="#" onclick="mark('${id}','${user.idA }',0)">取消种子</a>
										  </c:if>
									  </c:if>
									 </shiro:hasPermission>
									  	<shiro:hasPermission name="enjoyRace:updatePlayer">
									  <a href="#" onclick="updatePlayer('${id}','${user.idA }')">修改人员信息</a>
									  </shiro:hasPermission>
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
		<form id="createForm" action="${ctx }/enjoyRace/schedule/${id}"  method="post">
			<input type="hidden" id="ids" name="ids">
		</form>
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyrace-man');
			$('#adminFooter').hide();
			$("#createBtn").click(function(){
				//检查报名人数是否足8人 或是否已经报名结束
			   Util.getData(ctx + '/enjoyRace/checkSignedCount', function(data){
			      	 if(data.result){
			      		 $("#createForm").submit();
				     }else{
				    	 myAlert(data.reason,"error");
					 }
			   }, null, {"gameId":'${id}'}, 'post');
			});
            $("#collectIntegral").on("click",function(){
               var gameId = '${id}';
                Util.getData(ctx+'/enjoyRace/collectIntegral/'+gameId,function(data){
                    if(data.result){
                        myAlert("操作成功",'success')
                    }else{
                        myAlert("操作失败",'error');
                    }
                })
            });
		});
		
		
		//设置种子
		function mark(id,userId,state){
			Util.getData(ctx + '/enjoyRace/checkSignedCount', function(data){
		      	 if(data.result){
		      		 var $form = $('#actionForm');
				      Util.getData(ctx + '/enjoyRace/mark/', function(data){
				      	 if(data.result){
					      	 myAlert("设置种子成功");
					      	 window.location.reload()
					     }else{
					    	 myAlert(data.reason,"error");
						 }
				      }, null, {"eventId":id,"userAId":userId,"isSeed":state}, 'post');
				  return false;
			     }else{
			    	 myAlert(data.reason,"error");
				 }
		   }, null, {"gameId":'${id}'}, 'post');
		}
		
		//修改报名人员信息及CTA积分
		function updatePlayer(gameId,id){
			$("#myDlgBody_lg").load("${ctx}/enjoyRace/updatePlayer_dlg?id="+id+"&gameId="+gameId);
			$("#myDlgBody_lg").css("width","802px");
			$("#myDlg_lg").modal();
		}

		function refuseSigned(id,idA,state){
			$("#myDlgBody_lg").load("${ctx}/enjoyRace/refuseSigned_dlg?id="+id+"&idA="+idA+"&state="+state);
			$("#myDlgBody_lg").css("width","802px");
			$("#myDlg_lg").modal();
		}

		//审核报名人员
		function auditMember(id,idA,state,reason){
      		 var $form = $('#actionForm');
		      Util.getData(ctx + '/enjoyRace/auditMember/', function(data){
		      	 if(data.result){
			      	 window.location.reload()
			     }else{
			    	 myAlert(data.reason,"error");
				 }
		      }, null, {"id":id,"idA":idA,"state":state,"reason":reason}, 'post');
		}
	</script>
</body>
</html>