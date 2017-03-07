<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信统计</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
        <li class="active" >短信统计</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  				<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="smsVoForm" action="${ctx }/statisticSms/smsList" method="post">
		      <div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
									<input class="form-control input-sm" type="text" name="search_GTE_ct" id="search_GTE_ct" 
		          		onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'search_LTE_ct\')}'})"
		          		value="${param.search_GTE_ct}" data-date-format="yyyy-mm-dd" placeholder="开始日期" />
							</div>
							<div class="col-md-5">
								<input class="form-control input-sm" type="text" name="search_LTE_ct" id="search_LTE_ct"
		          		onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'search_GTE_ct\')}'})"
		           		value="${param.search_LTE_ct}" data-date-format="yyyy-mm-dd" placeholder="结束日期" />
							</div>
						</div>
		      
		        <div class="form-group form-group-sm">
							<div class="col-md-12 text-center">
								<button type="submit"  class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
							</div>
						</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>短信渠道</th>
					<th>短信总数</th>

				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="sms" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
						<td>${sms.channel }</td>
						<td>${sms.counter }</td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		
  </div>

</div>
<script type="text/javascript">
$(function() {
	menu.active('#statisticSms-smsList');
});

</script>

</body>
</html>