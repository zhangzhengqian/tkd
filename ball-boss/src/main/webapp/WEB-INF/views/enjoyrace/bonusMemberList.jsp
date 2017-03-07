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
								<th>队友身份证号</th>
								<th>队友性别</th>
								<th>队友手机号码</th>
								<th>奖金/人</th>
								<th>排名</th>
								<th>状态</th>
								<th>原因</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data.content}" var="user" varStatus="stat">
								<tr class="<c:if test="${user.state eq 0}">warning</c:if><c:if test="${user.state eq 1}">success</c:if><c:if test="${user.state == 2}">danger</c:if>">
									<td class="text-center">${stat.count }</td>
									<td class="text-center">${user.nameA }</td>
									<td class="text-center">${user.cardNoA}</td>
									<td class="text-center"><c:if test="${user.sexA eq 0}">女</c:if>
										<c:if test="${user.sexA eq 1}">男</c:if></td>
									<td class="text-center">${user.phoneA}</td>
									<c:if test="${user.doubleFlag == 1}">
										<td class="text-center">${user.nameB }</td>
										<td class="text-center">${user.cardNoB}</td>
										<td class="text-center"><c:if test="${user.sexB eq 0}">女</c:if>
											<c:if test="${user.sexB eq 1}">男</c:if></td>
										<td class="text-center">${user.phoneB}</td>
									</c:if>
										<c:if test="${user.doubleFlag != 1}">
										<td class="text-center">无</td>
										<td class="text-center">无</td>
										<td class="text-center">无</td>
										<td class="text-center">无</td>
									</c:if>
									<td class="text-center">${user.bonus}</td>
									<td class="text-center">${user.rank}</td>
									<td>
										<c:if test="${user.isIssueBonus eq 0}">未审核</c:if>
									    <c:if test="${user.isIssueBonus eq 2}">运营已审核</c:if>
									    <c:if test="${user.isIssueBonus eq 3}">财务已拒绝</c:if>
										<c:if test="${user.isIssueBonus eq 1}">已发放</c:if>
									</td>
									<td>
									    ${user.reason}
									</td>
									<td class="text-center">
										<shiro:hasPermission name="bonus:update">
										  <c:if test="${user.isIssueBonus eq 0 || user.isIssueBonus eq 3}">
										   <a href="#" onclick="changeBonus('${user.idA}','${user.idB}','${user.nameA}','${user.nameB}','${user.bonus}')" class="btn btn-default btn-sm">
										    <i class="glyphicon glyphicon glyphicon-remove"></i>修改金额</a>
										   	<a href="#" onclick="auditBonus('${user.idA}','${id}')" class="btn btn-default btn-sm">
												<i class="glyphicon glyphicon glyphicon-remove"></i>确认</a>
										   </c:if>
										</shiro:hasPermission>
										<shiro:hasPermission name="bonus:confirm">
										 <c:if test="${user.isIssueBonus eq 2}">
										      <a href="#" onclick="refuse('${user.idA}','${id}')" class="btn btn-default btn-sm">
												<i class="glyphicon glyphicon glyphicon-remove"></i>拒绝</a>
												<a href="#" onclick="confirmBonus('${user.idA}','${id}')" class="btn btn-default btn-sm">
												<i class="glyphicon glyphicon glyphicon-remove"></i>确认发放</a>
										</c:if> 
										</shiro:hasPermission>									
									</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="form-group form-group-sm">
						<div class="col-md-12 text-center">
							<a class="btn btn-default btn-sm" href="javascript:history.go(-1)">
								<span class="glyphicon"></span> 返回
							</a> &nbsp;&nbsp;
						</div>
					</div>

				</div>
				<!-- end col-table -->
			</div>
			<!-- end row -->
		</div>
		<!-- /右侧主体内容 -->
	</div>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#bonus-man');
			$('#adminFooter').hide();
		});
		
		//运营修改金额
		function changeBonus(AId,BId,nameA,nameB,bonus){
			$("#myDlgBody_lg").load("${ctx}/enjoyRace/udpateBonus_dlg?AId="+AId+"&BId="+BId+"&nameA="+nameA+"&nameB="+nameB+"&bonus="+bonus);
			$("#myDlgBody_lg").css("width","802px");
			$("#myDlg_lg").modal();
		}
		
		//财务审核拒绝
		function refuse(AId,BId){
			$("#myDlgBody_lg").load("${ctx}/enjoyRace/refuse_dlg?mAId="+AId+"&mBId="+BId);
			$("#myDlgBody_lg").css("width","802px");
			$("#myDlg_lg").modal();
		}
		
		//运营确认奖金额度
		function auditBonus(userId,gameId){
			  var $form = $('#actionForm');
		      Util.getData(ctx + '/enjoyRace/checkTime', function(data){
		      	 if(data.result){
		      		 Util.getData(ctx + '/enjoyRace/auditBonus', function(data2){
				      	 if(data2.result){
					      	 myAlert("奖金已确认并交由财务确认发放");
					      	 window.location.reload()
					     }else{
					    	 myAlert(data2.reason,"error");
						 }
				      }, null, {"memberAId":userId}, 'post');
		      	 }else{ 
		      		 myAlert(data.reason,"error");
				 }
		      }, null, {"gameId":gameId}, 'post');
		      return false;
		}
		
		//财务确认发放至用户账户
		function confirmBonus(userId,id){
			  var $form = $('#actionForm');
			      Util.getData(ctx + '/enjoyRace/confirmBonus', function(data){
			      	 if(data.result){
				      	 myAlert("奖金发送成功");
				      	 window.location.reload()
				     }else{
				    	 myAlert(data.reason,"error");
					 }
			      }, null, {"memberAId":userId,"gameId":id}, 'post');
			  return false;
			}
	</script>
</body>
</html>