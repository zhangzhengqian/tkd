<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户注册统计</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统统计</li>
        <li class="active" ><a href="${ctx}/statisticUalog/ualogList" >PV统计</a></li>
        <li class="active" >PV日数据</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form class="form-horizontal" id="ssoUserForm" action="${ctx}/statisticUalog/queryByChannel/${channel}" method="get">
		      
		         <div class="form-group form-group-sm ">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control Wdate " id="search_nowMonth" name="search_nowMonth" value="${param.search_nowMonth }" placeholder="请选择月份" onfocus="WdatePicker({dateFmt: 'yyyy-MM',maxDate:'%y-%M', isShowToday: false, isShowClear: false})">
			       	</div>
			       	<div class="col-md-5">
	    				<button  type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" id="search_btn" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	  				</div>
		         </div>
	  				
		       
		      </form>
			</div>
			
		</div>	
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>渠道</th>
					<th>日期</th>
					<th>PV数</th>

				</tr>
				</thead>
				
				<tbody>
				
				<c:forEach items="${data.content}" var="ualog" varStatus="stat">
					<tr>
						<td class="text-center">${stat.count }</td>
						<td>${ualog.channel }</td>
						<td>${ualog.ct }</td>
						<td>${ualog.counter }</td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
  </div>
  
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	menu.active('#statisticUalog-ualogList');
});

</script>

</body>
</html>