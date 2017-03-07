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
		<li class="active">注册、激活用户统计</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
  	 <div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" id="searchForm" action="${ctx }/statisticRegisterUser/queryByCity/${province}/${city}" method="post">
		      <div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for=""></label>
							<div class="col-md-5">
									<input class="form-control input-sm" type="text" name="search_EQ_ct" id="search_EQ_ct" 
		          		onClick="WdatePicker({dateFmt:'yyyy-MM'})" 
		          		value="${date}" data-date-format="yyyy-mm" placeholder="年月" />
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
					<th>日期</th>
					<th>城市</th>
					<th>注册总数</th>
					<th>激活总数</th>
				</tr>
				<tr><td colspan="2">总合计</td>
				<td >${province} 
					<c:if test="${province != city }">
						${city }
					</c:if></td>
				    <td>${regTotal}</td>
				    <td>${acttotal}</td></tr>
				<tr><td>合计</td>
				<td >${date}</td>
				<td >${province} 
					<c:if test="${province != city }">
						${city }
					</c:if></td>
				    <td  id="regTotalNum"></td>
				    <td  id="actTotalNum"></td></tr>
				</thead>
				<tbody>
				
				<c:forEach items="${data}" var="map" varStatus="stat">
					<tr>
					  <td class="text-center">${stat.count }</td>
							<td>${map.date }</td>
							<td>${province} 
								<c:if test="${province != city }">
									${city }
								</c:if>
							</td>
							<td name="regNum">${map.regNum}</td>
							<td name="actNum">${map.actNum}</td>
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
	menu.active('#statisticRegUser-queryByCityAll');
	var regTotal = 0;
	var actTotal = 0;
	$("td[name='regNum']").each(function(i){ 
	    regTotal += parseInt($(this).text());
	});
	$("#regTotalNum").text(regTotal);
	
	$("td[name='actNum']").each(function(i){ 
	    actTotal += parseInt($(this).text());
	});
	$("#actTotalNum").text(actTotal);
});
</script>

</body>
</html>