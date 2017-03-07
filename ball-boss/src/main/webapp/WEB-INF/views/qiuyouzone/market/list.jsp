<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>球友圈管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 球友圈</li>
        <li class="active">球友圈管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="labelForm" class="form-horizontal" action="${ctx }/qiuyouzone/market/list" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" ></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_qiuyouNo" name="search_qiuyouNo" value="${param.search_qiuyouNo }" placeholder="按球友号查询">
			       	</div>
		         	<div class="col-md-5">
						<input type="text" class="form-control input-sm" id="search_name" name="search_name" value="${param.search_name }" placeholder="按昵称查询">
			  		</div>
		         </div>
				 <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" ></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_content" name="search_content" value="${param.search_LIKE_content }" placeholder="按内容查询">
			       	</div>
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_sendType" name="search_EQ_sendType">		
							<option  value="" >--请选择--</option>
							<option <c:if test="${'0' == param.search_EQ_status}">selected</c:if>  value="0" >--未发送--</option>
							<option <c:if test="${'1' == param.search_EQ_status}">selected</c:if>  value="1" >--已发送--</option>
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
		  <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		    <shiro:hasPermission name="label:create">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-sm btn-primary" href="${ctx }/qiuyouzone/market/add" ><span class="glyphicon glyphicon-plus"></span> 新增</a>
		      </div>
		    </shiro:hasPermission>
		    </div>
	    </div><!-- /操作按钮组 -->
		<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>球友号</th>
				<th>昵称</th>
				<th>手机号</th>
				<th>发布时间</th>
				<th>内容</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content }" var="zone" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<c:set var="ssouser" value="${lf:getSsouserNameAndQiuyouNo(zone.userId)}"></c:set>
					<td style="text-align: center;">${ssouser['qiuyouNo']}</td>
					<td style="text-align: center;">${ssouser['name']}</td>
					<td style="text-align: center;">${ssouser['phone']}</td>
					<td style="text-align: center;"><fmt:formatDate value="${zone.ct}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="text-align: center;">${fn:substring(zone.content,0,30)}<c:if test="${fn:length(zone.content)>30}">...</c:if></td>
					<td style="text-align: center;">
						<c:choose>
							<c:when test="${zone.sendType==0}">
								未发送
							</c:when>
							<c:when test="${zone.sendType==1}">
								已发送
							</c:when>
						</c:choose>
					</td>
 					<td style="text-align: center;">
						<a href="${ctx}/qiuyouzone/market/update/${zone.id}"> 修改</a>
						<span class="cutline"></span>
						<a href="javascript:;" onclick="dele('${zone.id}')"> 删除</a>
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
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">
 
  $(function() {
	  menu.active('#qiuyouzone_market-list');
	  $('#adminFooter').hide();
  });
  
  function dele(id){
  	var url = "${ctx}/qiuyouzone/market/delete/"+id;
  	bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	    	window.location.href=url;
	    }
	});
  }
  
</script>

</body>
</html>
