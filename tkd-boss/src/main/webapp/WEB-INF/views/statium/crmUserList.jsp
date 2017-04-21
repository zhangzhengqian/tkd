<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>道馆管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 道馆管理</li>
        <li class="active">道馆用户管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->

		
		
		<div class="row">
			<div class="col-md-12 text-right">
				<shiro:hasPermission name="user:create">
	        		<a class="btn btn-sm btn-primary" href="${ctx }/statiumOa/addCrmUser?statiumId=${statiumId}" ><span class="glyphicon glyphicon-plus"></span> 创建新用户</a>
	      		</shiro:hasPermission>	
			</div>
		</div>		

		<!-- 列表 -->
		<div class="row"><div class="col-md-12 col-table" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
			<%-- 
			  <th class="text-center"><input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
			--%>
				<th class="text-center">序号</th>
				<th>登录账号</th>
				<th>真实姓名</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${data.content}" var="user" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${user.loginName}</td>
					<td>${user.realname}</td>
					<td>${user.cb } </td>
					<td>
					  <a href="${ctx}/statiumOa/update?userId=${user.userId}&statiumId=${statiumId}" id="editLink-${user.userId}"> 修改</a>
					   <span class="cutline"></span> 
					  <a href="#" data="${user.nickname }" id="removeLink-${user.userId}" onclick="deleteById('${user.userId}')"> 删除</a>
					   <span class="cutline"></span>
					  <a href="${ctx}/admin/user/resetPwd/${user.userId}" data="${user.nickname }" class="resetPwd" id="resetLink-${user.userId}"> 重置密码</a>
					</td>
				</tr>
			</c:forEach>
			
			</tbody>	
		</table>
		</div></div><!-- /row -->
		<tags:pagination page="${data}" /> 

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#statiumOa-man');
	  
	});
	
	
	
</script>

</body>
</html>
