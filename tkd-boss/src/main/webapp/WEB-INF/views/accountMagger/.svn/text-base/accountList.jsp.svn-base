<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
.remind{
	background-color: #FF6347;
}
tbody tr{
	text-align: center
}
</style>
<title>账号管理</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<div class="row">
				<div class="col-md-5">
					<div class="btn-group-sm">
						<ul class="breadcrumb" style="display: inline;">
							<li><span class="glyphicon glyphicon-home"></span> 账号管理</li>
							<li class="active">账号列表</li>
						</ul>
					</div>
				</div>
				<div class="col-md-7"></div>
			 </div>
		</div>
	<div class="panel-body">
			<!-- 右侧主体内容 -->

			<div class="row">
				<!-- 查询条件 -->
				<div class="col-md-12">
					<form id="orderForm" class="form-horizontal"
						action="${ctx }/account/list" method="post" name="id">
						<input type="hidden" id="search_EQ_status" name="search_EQ_status" />
							<div class="col-md-5 form-inline">
						        	<%-- <tags:zone name="search_LIKE_areaCode" value="${param.search_LIKE_areaCode}" id="LIKE_areaCode" /> --%>
				         			&nbsp;&nbsp;&nbsp;&nbsp;
				         			<button type="reset" class="btn btn-default btn-sm">
										<span class="glyphicon glyphicon-refresh"></span> 重 置
									</button>
										&nbsp;&nbsp;
									<button type="submit" class="btn btn-primary btn-sm" >
										<span class="glyphicon glyphicon-search"></span> 搜 索
									</button>
				         	</div>
							<br>
					</form>
				</div>
			</div>
			<!-- /查询条件 -->
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12 text-right">
					 <a class="btn btn-primary btn-sm" href="${ctx }/account/sign?action=create" ><span class="glyphicon glyphicon-plus"></span> 添加账户</a> 
				</div>
			</div>
			<br>
			
			<div class="row">
				<!-- 操作按钮组 -->
				<div class="col-md-12">
					<div class="row">
						<div class="col-table col-md-12">
							<table id="contentTable" class="table table-bordered table-condensed table-hover">
							<thead class="thead">
								<tr >
								<th>序号</th>
								<th>账号</th>
								<th>账号名称</th>
								<th>账号类型</th>
								<th>创建时间</th>
								<th>操作</th>
								<!-- <th style="width:50px">操作</th> -->
								</tr>
							</thead>
							<tbody align="center">
								<c:forEach items="${data.content}" var="account" varStatus="stat">
									<tr>
										<td class="text-center">${stat.count }</td>
										<td>${account.loginName}</td>
										<td>${account.nickname}</td>
										<td><tags:orgName orgCode="${account.orgCode}" /></td>
										<td> <fmt:formatDate value="${account.createTime}" pattern="YYYY-MM-dd HH:mm:ss"/> </td>
										<td style="text-align:center;">
										 <%--  <a href="${ctx}/admin/user/update/${account.userId}" id="editLink-${account.userId}"> 修改</a> --%>
										   <span class="cutline"></span> 
										  <a href="#" data="${account.nickname }" id="removeLink-${account.userId}" onclick="deleteById('${account.userId}')"> 删除</a>
										   <span class="cutline"></span>
										  <a href="#" onclick="initPassById('${account.userId}')" data="${account.nickname }" class="resetPwd" id="resetLink-${account.userId}"> 重置密码</a>
										</td>
									</tr>
							   </c:forEach>
							</tbody> 
							</table>
						</div>
				</div>
				<form id="orderVerify" type="hidden">
				</form>
				<tags:pagination page="${data}" />
				<tags:errors />
				<form id="actionForm" action="" method="post">
			       <input type="hidden" id="ids" name="ids">
			    </form>
			</div>
	</div>
			
			<!-- 导出execl -->
			<form id="actionForm" action="${ctx}/order/export" method="post">
       			<input type="hidden" id="ids" name="search_IN_id">
    		</form>
			<script type="text/javascript">
			
			function statusClick(v){
				$("#search_EQ_status").attr("value",v);
				$("#orderForm").submit();
			}
			 $(function(){
				menu.active($("#account-man"));
				var v_search_EQ_ordersType='${param.search_EQ_ordersType}';
				if(v_search_EQ_ordersType){
					$("#search_EQ_ordersType option[value="+v_search_EQ_ordersType+"]").attr("selected","selected");
				}
				 var v_search_EQ_verifyFlag='${param.search_EQ_verifyFlag}';
				if(v_search_EQ_verifyFlag){
					$("#search_EQ_verifyFlag option[value="+v_search_EQ_verifyFlag+"]").attr("selected","selected");
				} 
				$("button[type='reset']").click(function(){
					$(this).closest("form").find("input").attr("value", "");
					$(this).closest("form").find("select option:selected").attr("selected", false);
					$(this).closest("form").find("select option:first").attr("selected",true);
				});
			}); 
			 
			 function deleteById(id) {
				 console.log(id);
				  var $form = $('#actionForm');
				  $form.attr('action', '${ctx}/account/delAccount/' + id);
				  bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
				    if(result) {
				      $form[0].submit();
				    }
				  });
				  return false;
				}
			 
			 function initPassById(id){
				 console.log(id);
				  var $form = $('#actionForm');
				  $form.attr('action', '${ctx}/account/resetPwd/' + id);
				  bootbox.confirm('您确定要初始化 [' + $('#resetLink-' + id).attr('data') + '] 密码吗？初始化后密码为123456！', function(result) {
				    if(result) {
				      $form[0].submit();
				    }
				  });
				  return false;
				}
			</script>
</body>
</html>