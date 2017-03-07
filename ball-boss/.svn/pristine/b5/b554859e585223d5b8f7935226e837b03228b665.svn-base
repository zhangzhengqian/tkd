<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>战队管理</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 战队管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/corps" method="post">
			  		<input  type="hidden"  id="search_EQ_sortBy"  name="search_EQ_sortBy"  value="${param.search_EQ_sortBy }">
			        <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
				  	  <div class="col-md-5">
				  	  		<input  type="text" class="form-control input-sm" id="search_EQ_name"  name="search_EQ_name"  value="${param.search_EQ_name }" placeholder="按战队名称查询">
			       	  </div>
			       	  <div class="col-md-5">
				  	  		<input  type="text" class="form-control input-sm" id="search_EQ_username"  name="search_EQ_username"  value="${param.search_EQ_username }" placeholder="按战队队长查询">
			       	  </div>		        
			       	</div>
			        <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
			       	  <div class="col-md-5">
			       	  	<select class="form-control" id="search_EQ_sportType" name="search_EQ_sportType">		
							<option  value="" >--请选择运动类别--</option>
							<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
								<option  value="${item.itemCode }" >--${item.itemName }--</option>
							</c:forEach>
					    </select>
			       	  </div>	
			        </div>
				<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				  </div>
				</div>
   			 </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row" style="margin-bottom:1.5em;"><!-- 操作按钮组 -->
	    <div class="col-md-12 text-right">
			<div class="btn-group" role="group" aria-label="...">
			  <button value="0" type="button" class="sortBy btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;人数最多</button>
			  <button value="1" type="button" class="sortBy btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;积分升序</button>
			  <button value="2" type="button" class="sortBy btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;积分降序</button>
		      <a class="btn btn-primary btn-sm" href="${ctx }/corps/add" ><span class="glyphicon glyphicon-plus"></span> 新增</a>
	    </div>
	  </div><!-- /操作按钮组 -->
	  </div>
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>战队名称</th>
				<th>战队队长</th>
				<th>活动区域</th>
				<th>战队人数</th>
				<th>运动类型</th>
				<th>城市排名</th>
				<th>联系电话</th>
				<th>创建时间</th>
				<th>参赛次数</th>
				<th>战队积分</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="corps" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>${corps.name}</td>
					<td>${corps.captainStr}</td>
					<td>${corps.area}&nbsp;${corps.activityArea}</td>
					<td>${corps.currentNumber}</td>
					<td>${corps.sportType}</td>
					<td>${corps.integralNum}</td>
					<td>${corps.phone}</td>
					<td><fmt:formatDate value="${corps.ct}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${corps.gameCount}</td>
					<td>${corps.integral}</td>
					<td>
						<a href="${ctx }/corps/view/${corps.id}" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i> 查看</a>
						<a href="${ctx }/corps/update/${corps.id}" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-check"></i> 修改</a>
						<a href="javascript:;" corps_id="${corps.id}" class="disband btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i> 删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		    </div><!-- end col-table -->
</div><!-- end row -->
		<tags:pagination page="${data}" />
  </div><!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
$(function() {
	menu.active('#corps-man');
	$(".sortBy").on("click",function(e){
		$("#search_EQ_sortBy").val($(this).val());
		$("#search_form").submit();
	});
	$(".disband").on("click",function(e){
		var corps_id = $(this).attr("corps_id");
		bootbox.confirm('是否删除该战队?', function(result) {
		    if(result) {
		    	window.location.href='${ctx }/corps/disband/'+corps_id;
		    }
		  });
	})
});
  
</script>
</body>
</html>