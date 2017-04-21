<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>系统管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
  	<div class="row">
  		<div class="col-md-5">
			<div class="btn-group-sm">
				  <ul class="breadcrumb" style="display:inline;">
			        <li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
			        <li class="active">注册用户统计</li>
			    </ul>
			</div>
		</div>
		<div class="col-md-7">
		</div>
	</div>
  
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">编号</th>
				<th>注册来源</th>
				<th>注册总数</th>
			</tr>
			</thead>
			
			<tbody>
			<tr>
				<td class="text-center">汇总</td>
				<td><a href="${ctx }/admin/statisticRegisterUser/allChannelRegisterUser">全部</a></td>
				<td>${totalAmount}</td>
			</tr>
			<c:forEach items="${data.content}" var="statistic" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td><a href="${ctx }/admin/statisticRegisterUser/detailStatisticRegisterUser?channel=${statistic.channel}">${statistic.channel}</a></td>
					<td>${statistic.counter}</td>
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
	  menu.active('#statistic-man');
  });
</script>

</body>
</html>
