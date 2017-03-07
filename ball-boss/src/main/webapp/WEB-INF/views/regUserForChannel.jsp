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
    	<span class="glyphicon glyphicon-stats"></span>&nbsp;
        <li class="active" >注册数据</li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  	
				
		<div class="row">
    	<div class="col-table col-md-12" >		
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
				<tr>
					<th class="text-center">编号</th>
					<th>注册时间</th>
					<th>注册数</th>
					<th>激活数</th>
					<th>转化率</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${data.content}" var="regUser" varStatus="stat">
					<tr>
						<td style="text-align: center;">${stat.count }</td>
							<fmt:parseDate value="${regUser.ct}" var="regUserCt"  pattern="yyyy-MM-dd" /> 
							<fmt:parseDate value="2015-12-14" var="preCt"  pattern="yyyy-MM-dd" /> 
						<td style="text-align: center;"><a href="${ctx}/statisticRegisterUser/queryByChannelDay/${regUser.channel}?search_GTE_ct=${regUser.ct }&search_LTE_ct=${regUser.ct }"  >${regUser.ct }</a></td>
							<c:if test="${regUserCt > preCt }">
								 <td style="text-align: center;"><fmt:formatNumber  value="${regUser.counter*0.9-(regUser.counter*0.9)%1}" pattern="#"/></td>
								<td style="text-align: center;"><fmt:formatNumber  value="${regUser.avcounter*0.9-(regUser.avcounter*0.9)%1}" pattern="#"/></td>
								<td style="text-align: center;">
								<c:if test="${regUser.counter*0.9-(regUser.counter*0.9)%1 >= 1}">
									<fmt:formatNumber type="percent" value="${regUser.convertrate }" minFractionDigits="2" maxFractionDigits="2" />
								</c:if></td>
							</c:if>
							<c:if test="${regUserCt <= preCt }">
								 <td style="text-align: center;"> ${regUser.counter} </td>
								<td style="text-align: center;">${regUser.avcounter}</td>
								<td style="text-align: center;">
									<fmt:formatNumber type="percent" value="${regUser.convertrate }" minFractionDigits="2" maxFractionDigits="2" />
								</td>
							</c:if>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
		<tags:pagination page="${data}" />
  </div>
  
<!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
$("#leftbar").css('display','none'); 
});

function getNumber(){
	
}
</script>

</body>
</html>