<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
    	<li><span class="glyphicon glyphicon-list-alt"></span> 检索条件</li>
        <li><span class="active"></span> 明细</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->

		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center"></th>
	                <th class="text-center">球馆名</th>
	                <th class="text-center">运动类别</th>
	                <th class="text-center">场馆电话</th>
	                <th class="text-center">所在地区</th>
	                <th class="text-center">详细地址</th>
	                <th class="text-center">导入会员数</th>
	                <th class="text-center">联系人</th>
	                <th class="text-center">录入日期</th>
	                <th class="text-center">签约/录入</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data.content}" var="itm" varStatus="stat">
					<tr>
						<td>${stat.index+1 }</td>
						<td>${itm.name }</td>
						<td title="${itm.sportType }">
							<tags:out len="4" value="${itm.sportType }" />
						</td>
						<td>${itm.tel}</td>
						<td>
							<tags:zonemap code="${itm.areaCode }" />
						</td>
						<td title="${itm.address}">
							<tags:out len="4" value="${itm.address}" />	
						</td>
						<td>${itm.totalCustomer}</td>
						<td>${itm.masterName}</td>
						<td>
							<fmt:formatDate value="${itm.ct}" pattern="yyyy-MM-dd"/>
						</td>
						<td> ${itm.sb} </td>
					</tr>
				</c:forEach>
			</tbody>		
		</table>
		
      	<tags:pagination page="${data}" />
        <tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div>
  <div class="panel-footer">
		<div class="row text-right">
			<div class="col-sm-12">
				<form action="${ctx }/admin/statium/statistics/signed/export" method="post" target="_blank" >
					<a href="${ctx }/admin/statium/statistics/signed"  class="abtn btn btn-sm btn-default" value="search"><span class="glyphicon glyphicon-chevron-left"></span>返回</a>	
					<button class="abtn btn btn-sm btn-primary" value="export"><span class="glyphicon glyphicon-export"></span>导出</button>	
				</form>
			</div>
		</div>	
  </div>
</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#statium-statistics-signed-man');
		$("#adminFooter").hide();
  });

</script>
