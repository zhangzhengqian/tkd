<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">活动管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="activityForm" class="form-horizontal" action="${ctx}/activity/statisticsList" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="按活动名称查询">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LIKE_address" name="search_LIKE_address" value="${param.search_LIKE_address }" placeholder="按活动地点查询">
       	  			</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         	<label class="control-label col-md-1 sr-only" for="type"></label>
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_type" name="search_EQ_type">		
						<option  value="" >--请选择运动类别--</option>
						<option <c:if test="${'3' == param.search_EQ_type}">selected</c:if> id="option0" value="3" >--篮球--</option>
						<option <c:if test="${'6' == param.search_EQ_type}">selected</c:if> id="option1" value="6" >--足球--</option>
						<option <c:if test="${'1' == param.search_EQ_type}">selected</c:if> id="option2" value="1" >--羽毛球--</option>
						<option <c:if test="${'7' == param.search_EQ_type}">selected</c:if> id="option3" value="7" >--台球--</option>
						<option <c:if test="${'8' == param.search_EQ_type}">selected</c:if> id="option4" value="8" >--保龄球--</option>
						<option <c:if test="${'5' == param.search_EQ_type}">selected</c:if> id="option5" value="5" >--高尔夫--</option>
						<option <c:if test="${'4' == param.search_EQ_type}">selected</c:if> id="option6" value="4" >--乒乓球--</option>
						<option <c:if test="${'2' == param.search_EQ_type}">selected</c:if> id="option7" value="2" >--网球--</option>
					</select>
			  		</div>
		         </div>
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				&nbsp;&nbsp;
						<button type="button" id="export_btn" class="btn btn-default btn-sm">
							<span class="glyphicon"></span> 导出Excel
						</button>
	    				&nbsp;&nbsp;
	    				<button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button>
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
	    </div><!-- /操作按钮组 -->
			<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>活动名称</th>
				<th>运营成本（总）</th>
				<th>报名费</th>
				<th>报名人数</th>
				<th>场地支出</th>
				<th>盈利</th>
				<th>发布人</th>
				<th>发布时间</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="activity" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${activity.name}</td>
					<td style="text-align: center;">${lf:formatMoney(activity.costPrice)}</td>
					<td style="text-align: center;">${lf:formatMoney(activity.money)}</td>
					<td style="text-align: center;">${activity.registrationNumber}</td>
					<td style="text-align: center;">${lf:formatMoney(activity.expenditure)}</td>
					<td style="text-align: center;">${lf:formatMoney(activity.profit)}</td>
					<td style="text-align: center;">${activity.createUserName }</td>
					<td style="text-align: center;"><fmt:formatDate value="${activity.createTime}" pattern="yyyy-MM-dd"/></td>
					<td style="text-align: center;">${activity.remark}</td>
					<td style="text-align: center;"><span class="cutline"></span> 
					  	<a href="${ctx}/activity/updateForm/${activity.id}" id="editLink-${activity.id}"> 修改</a>
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
</div>
<script type="text/javascript">
  $(function() {
	  menu.active('#activityStatistics-man');
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  $("#export_btn").click(function(){
	   		$("#activityForm").attr("action","${ctx}/activity/export");
			$("#activityForm").submit();
			setTimeout("modify()",1000);
	  }); 
  });
  function modify(){
	  $("#activityForm").attr("action","${ctx}/activity/statisticsList");
  }
</script>
</body>
</html>