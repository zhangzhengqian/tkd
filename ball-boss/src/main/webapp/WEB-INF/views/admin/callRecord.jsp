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
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
        <li class="active">通话记录管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<!-- 查询 -->
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" action="${ctx }/cph/callRecord" method="post">
 
					<div class="form-group form-group-sm">
						<label class="control-label col-md-1 sr-only" for="custName"></label>
						<div class="col-md-5">
		         				<input type="text" class="form-control input-sm" id="search_LIKE_exten" name="search_LIKE_exten" value="${param.search_LIKE_exten }" placeholder="按坐席号查询">
		       			</div>
		       			<div class="col-md-5">
		         				<input type="text" class="form-control input-sm" id="search_LIKE_callno" name="search_LIKE_callno" value="${param.search_LIKE_callno }" placeholder="按主叫号码查询">
		       			</div>	
					</div>
					
					<div class="form-group form-group-sm">
						<label class="control-label col-md-1 sr-only" for="calledno"></label>
						<div class="col-md-5">
		         				<input type="text" class="form-control input-sm" id="search_LIKE_calledno" name="search_LIKE_calledno" value="${param.search_LIKE_calledno }" placeholder="按被叫号查询">
		       			</div>
					</div>
					
					<!-- 带有约束的日期条件，开始－结束 --> 
                       <div class="form-group form-group-sm query-more">
                       	<lable class="control-label col-md-1 sr-only"></lable>
                       	<div class="col-md-5">
                       		<input type="text" class="form-control Wdate " 
                       			id="search_GTE_ct" name="search_GTE_ct" 
                       			value="${param.search_GTE_createTime }" 
                       			placeholder="创建日期-开始" 
                       			onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_ct\')||\'%y-%M-%d\'}'})" />
                       	</div>
                       	<div class="col-md-5">
                       		<input type="text" class="form-control Wdate " 
                       			id="search_LTE_ct" name="search_LTE_ct" 
                       			value="${param.search_LTE_createTime }" 
                       			placeholder="创建日期-结束" 
                       			onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_ct\')}'})" />
                       	</div>
                       </div>
                       
					<div class="form-group form-group-sm">
						<div class="col-md-12 text-center">
							<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
							&nbsp;&nbsp;
							<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
							&nbsp;&nbsp;
							<button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- / 查询 -->

		
		
		<div class="row">
			<div class="col-md-12 text-right">
			</div>
		</div>		
		<br>

		<!-- 列表 -->
		<div class="row"><div class="col-md-12 col-table" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
			<%-- 
			  <th class="text-center"><input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
			--%>
				<th class="text-center">序号</th>
				<th>Actionid</th>
				<th>主叫号码</th>
				<th>被叫号码</th>
				<th>坐席号</th>
				<th>CallId</th>
				<td>通话录音</th>
				<th>创建时间</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="data" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${data.actionid}</td>
					<td>${data.callno}</td>
					<td>${data.calledno}</td>
					<td>${data.exten }</td>
					<td>${data.callid }</td>
					<td> <audio controls="controls">
      <source src="${data.fileserver }/${data.recordfile }" type="audio/mpeg">
    </audio>
				 </td>
					<td> <fmt:formatDate value="${data.ct}" pattern="YYYY-MM-dd HH:mm:ss"/> </td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div></div><!-- /row -->
		<tags:pagination page="${data}" />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#user-man');
	  $('#adminFooter').hide();
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
  });
</script>

</body>
</html>
