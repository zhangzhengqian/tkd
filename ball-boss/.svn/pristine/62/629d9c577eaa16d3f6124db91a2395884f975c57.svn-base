<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动码管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 活动码管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form id="searchForm" class="form-horizontal" action="${ctx}/code/codeList" method="post">
		      			<div class="form-group form-group-sm">
		      				<label class="col-md-1 control-label"></label>
							<div class="col-md-5">
								<input type="text" class="form-control input-sm" id="search_LIKE_channel" name="search_LIKE_channel" value="${param.search_LIKE_channel}" placeholder="按渠道名称查询">
							</div>
						</div>
				<div class="form-group form-group-sm">
					<div class="col-md-12 text-center">
						 <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
					</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	    <div class="row"><!-- 操作按钮组 -->
   			<div class="col-md-12">
	      		<div class="btn-group-sm pull-right mtb10">
	      		<shiro:hasPermission name="coupon:sign">
	        		<a class="btn btn-primary"  href="${ctx }/code/codeForm"><span class="glyphicon glyphicon-plus"></span> 新增活动码</a>
	        	</shiro:hasPermission>
	     		</div>
	   		</div>
	  	</div><!-- /操作按钮组 --> 
				
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>活动码数量</th>
				<th>渠道</th>
				<th>有效期</th>
				<th>状态</th>
				<th>简介</th>
				<th>添加人</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="code" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${code.number }</td>
					<td>${code.channel }</td>
					<td><fmt:formatDate value="${code.startDate}" pattern="yyyy/MM/dd"/>--<fmt:formatDate value="${code.endDate}" pattern="yyyy/MM/dd"/></td>
					<c:set var="nowDate">  
					    <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>  
					<c:set var="endDate">  
					    <fmt:formatDate value="${code.endDate}" pattern="yyyy-MM-dd " type="date"/>  
					</c:set>  
					<td>
						<c:if test="${nowDate > endDate }">  
					    	  已过期
						</c:if>
						<c:if test="${nowDate <= endDate }">  
					    	  可用
						</c:if>
					</td>
					<td>${code.description }</td>
					<td>${code.cb }</td>
					<td style="width:350px;vertical-align:middle">
					  <a class="btn btn-sm btn-default" href="${ctx}/code/codeInfo/${code.id }"> <span class="glyphicon glyphicon-edit">查看详情</a>
					  <a class="btn btn-sm btn-default" href="#" onclick="deleteById('${code.id }')"> <span class="glyphicon glyphicon-edit">删除</a>
					</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div><!-- end col-table -->
		</div><!-- end row -->
		
		<tags:pagination page="${data}" />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
<script type="text/javascript">
$(function() {
	  menu.active('#code-man');
	  $('#adminFooter').hide();
	  
	  
	  $('#btnGroup :button').each(function(i){
		  var _this = $(this);
		  _this.removeClass("btn-success");
		  _this.click(function(){
				 $('#btnGroup :button').each(function(i){
					 $(this).removeClass("btn-success");
				 });
				 _this.addClass("btn-success");
				$('#searchForm').submit();
			});
	  });
	  
});
 
 // 删除
function deleteById(id) {
	  	var $form = $('#actionForm');
		$form.attr('action', '${ctx}/code/deleteCode/' + id);
	  	bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
}
 
</script>

</body>
</html>