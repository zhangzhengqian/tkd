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
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 球友圈</li>
        <li class="active">营销账户管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>球友号</th>
				<th>昵称</th>
				<th>手机号</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content }" var="user" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<c:set var="ssouser" value="${lf:getSsouserNameAndQiuyouNo(user.ssoUserId)}"></c:set>
					<td style="text-align: center;">${ssouser['qiuyouNo']}</td>
					<td style="text-align: center;">${ssouser['name']}</td>
					<td style="text-align: center;">${ssouser['phone']}</td>
 					<td style="text-align: center;">
						<a href="${ctx}/ssouser/view?action=view&id=${user.ssoUserId}" target="_blank"> 查看</a>
						<span class="cutline"></span>
						<a href="javascript:;" onclick="dele('${user.id}')"> 删除</a>
						<span class="cutline"></span>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
		<tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="search_IN_id">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">
 
  $(function() {
	  menu.active('#qiuyouzone_market-user');
	  $('#adminFooter').hide();
  });
  
  function dele(id){
  	var url = "${ctx}/qiuyouzone/market/user/delete/"+id;
  	bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	    	window.location.href=url;
	    }
	});
  }
  
</script>

</body>
</html>
