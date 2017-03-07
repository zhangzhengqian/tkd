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
		      <form id="labelForm" class="form-horizontal" action="${ctx }/qiuyouzone/list" method="post">
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
			        	<input type="text" class="form-control input-sm" id="search_content" name="search_content" value="${param.search_content }" placeholder="按内容查询">
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
		<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>昵称</th>
				<th>球友号</th>
				<th>手机号</th>
				<th>发布时间</th>
				<th>内容</th>
				<th>评论数</th>
				<th>点赞数</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content }" var="zone" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${zone["nickName"]}</td>
					<td style="text-align: center;">${zone["qiuyouNo"]}</td>
					<td style="text-align: center;">${zone["phone"]}</td>
					<td style="text-align: center;"><fmt:formatDate value="${zone['ct']}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="text-align: center;">${fn:substring(zone["content"],0,30)}<c:if test="${fn:length(zone['content'])>30}">...</c:if></td>
					<td style="text-align: center;">${zone["cont1"]}</td>
					<td style="text-align: center;">${zone["cont2"]}</td>
 					<td style="text-align: center;">
						<a href="${ctx}/qiuyouzone/view/${zone['id']}"> 查看</a>
						<span class="cutline"></span>
						<c:choose>
							<c:when test="${zone['status']=='0'}">
								<a href="javascript:;" onclick="frozen('${zone['id']}','冻结')">
								冻结
								</a>
							</c:when>
							<c:when test="${zone['status']=='1'}">
								<a href="javascript:;" onclick="frozen('${zone['id']}','解冻')">
								解冻
								</a>
							</c:when>
						</c:choose>
						<span class="cutline"></span>
						<a href="javascript:;" onclick="dele('${zone['id']}')"> 删除</a>
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
    
  </div><!-- /右侧主体内容 -->

</div>

<script type="text/javascript">
 
  $(function() {
	  menu.active('#qiuyouzone-man');
	  $('#adminFooter').hide();
  });
  
  function dele(id){
  	var url = "${ctx}/qiuyouzone/delete/"+id;
  	bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	    	window.location.href=url;
	    }
	});
  }
  
  function frozen(id,option){
	  var url = "${ctx}/qiuyouzone/frozen/"+id;
	  bootbox.confirm('您确定要'+option+'吗？', function(result) {
	    if(result) {
	    	window.location.href=url;
	    }
	  });
  }
  
</script>

</body>
</html>
