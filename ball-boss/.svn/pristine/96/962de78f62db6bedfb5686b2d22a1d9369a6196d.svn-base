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
		<li class="active">昨日注册、激活用户统计</li>
    </ul>
  </div><!-- / 右侧标题 -->
<div style="position: fixed;top:40%;right:22px;z-index: 1000">
	<a href="#" class="btn btn-default"><i class="glyphicon glyphicon glyphicon-arrow-up"></i></a>
</div>
  <div class="panel-body"><!-- 右侧主体内容 -->	
  <div class="row">
    	<div class="col-md-6 has-feedback form-inline">
	         <div class="input-group ">
				<input type="text" id="city" class="form-control" placeholder="输入城市查询">
				<span class="input-group-btn">
					<button class="btn btn-primary btn-sm" onclick="search()">搜 索</button>
				</span>
			</div>
	       </div>
   </div>	
   <hr>		
	<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>城市</th>
					<th>注册总数</th>
					<th>激活总数</th>
				</tr><tr><td>合计</td>
				<td>全部 </td>
				    <td id="regTotalNum"></td>
				    <td id="actTotalNum"></td></tr>
				</thead>
				<tbody>
				
				<c:forEach items="${data}" var="map" varStatus="stat">
					<tr id="${map.city }">
					  <td class="text-center">${stat.count }</td>
							<td><a href="${ctx}/statisticRegisterUser/queryByCity/${map.province}/${map.city}">
							${map.province }
							<c:if test="${map.province != map.city }">
							  ${map.city }
							</c:if>
							
							</a></td>
							<td name="regNum">${map.regNum }</td>
							<td name="actNum">${map.actNum }</td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
	</div><!-- end row -->
  </div>
</div>
<script type="text/javascript">
var url = (window.location.href).split("#")[0];
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
function search(){
	var city = $("#city").val();
	if(city.indexOf("市")!=-1){
		city = city.split("市")[0];
	}
	$("#"+city).addClass("success");
	$("tr[id!="+city+"]").removeClass("success");
	window.location.href = url+"#"+city;
}

</script>

</body>
</html>