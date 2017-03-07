<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球友圈管理</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <!-- 右侧标题 -->
	<ul class="breadcrumb">
		<li><span class="glyphicon glyphicon-home"></span> 球友圈管理</li>
		<li>群组管理</li>
		<li class="active">成员信息</li>
	</ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<%-- <div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="groupUserForm" class="form-horizontal" id="ssoUserForm" action="${ctx}/ssouser/user" method="post">
		         <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_nickName" name="search_LIKE_nickName" value="${param.search_LIKE_nickName }" placeholder="按用户姓名查询">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="按手机号查询">
       	  			</div>
		         </div>
		         
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button id="resetButton" type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" id="search_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 --> --%>
		
		<div class="row">
		<h3>成员信息</h3>
		<hr>
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>球友号</th>
					<th>类型</th>
					<th>昵称</th>
					<th>性别</th>
					<th>手机</th>
					<th>入群时间</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${data.content}" var="user" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
						<td><a href="${ctx}/ssouser/view?action=view&id=${user.id }" >${user.qiuyouno }</a></td>
						<td><c:if test="${user.type == 1}" >
								群主
							</c:if>
							<c:if test="${user.type == 0}" >
								成员
							</c:if></td>
						<td>${user.nickName }</td>
						<td>${user.sex }</td>
						<td>${user.phone }</td>
		         		<td>
		         			<fmt:formatDate value="${user.ct }"  pattern="yyyy-MM-dd HH:mm"/>
		         		</td>
						<td>
						  <a href="${ctx}/ssouser/view?action=view&id=${user.id }">查看</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	  menu.active('#qiuyouzone-group');
	  $("button[type=reset]").click(function(){
		  $("#groupUserForm").find("input").attr("value","");
		  $("#groupUserForm").find("select option:selected").attr("selected",false);
		  $("#groupUserForm").find("select option:first").attr("selected",true);
	  });
});
</script>
</body>
</html>