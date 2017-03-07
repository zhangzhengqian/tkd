<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>标签管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">标签管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="labelForm" class="form-horizontal" action="${ctx }/label/list" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" ></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="按标签名称查询">
			       	</div>
			       	
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_type" name="search_EQ_type">		
						<option  value="" >--请选择标签类别--</option>
						<option <c:if test="${'0' == param.search_EQ_type}">selected</c:if> id="option0" value="0" >--用户标签--</option>
						<option <c:if test="${'1' == param.search_EQ_type}">selected</c:if> id="option1" value="1" >--场馆标签--</option>
						<option <c:if test="${'2' == param.search_EQ_type}">selected</c:if> id="option2" value="2" >--教培标签--</option>
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
		        <a class="btn btn-sm btn-primary" href="${ctx }/label/create/" ><span class="glyphicon glyphicon-plus"></span> 新增标签</a>
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
				<th>标签名称</th>
				<th>标签类型</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content }" var="label" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${label.name }</td>
					<td style="text-align: center;">
						<c:if test="${label.type=='0' }" >用户标签</c:if>
						<c:if test="${label.type=='1' }" >场馆标签</c:if>
						<c:if test="${label.type=='2' }" >教培标签</c:if>
					</td>
					<td style="text-align: center;">
						<c:if test="${label.status==0 }">停用</c:if>
						<c:if test="${label.status==1 }">启用</c:if>
					</td>
					<td style="text-align: center;"><fmt:formatDate value="${label.ct }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
 					<td style="text-align: center;">
						<shiro:hasPermission name="label:create">
							<a href="${ctx}/label/create/${label.id}" id="editLink-${label.id}"> 修改</a>
						</shiro:hasPermission>
						<span class="cutline"></span>
						<shiro:hasPermission name="label:status">
							<a href="${ctx}/label/status/${label.id}" id="statusLink-${label.id}">
								<c:if test="${label.status==1 }"> 冻结</c:if>
								<c:if test="${label.status==0 }"> 启用</c:if>
							</a>
						</shiro:hasPermission>
						<span class="cutline"></span>
							<shiro:hasPermission name="label:delete">
						  		<a href="javascript:void(0)" data="${label.name }" id="removeLink-${label.id}" onclick="deleteById('${label.id}')"> 删除</a>
						  	</shiro:hasPermission>
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
	  menu.active('#label-man');
	  
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  
	  $('#adminFooter').hide();
  });
	  
	function deleteById(id) {
	  bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
	    if(result) {
	    	$.ajax({
                cache: true,
                type: "POST",
                url:'${ctx}/label/delete/' + id,
                data:{},
                async: false,
                error: function(request) {
                	common.showMessage("删除标签失败！");
                },
                success: function(data) {
                	data = eval("("+data+")");
                	if(data.result=="success"){
                		common.showMessage("删除标签成功！");
                		location.reload();
                	}else{
                		common.showMessage("删除标签失败！");
                	}
                }
            });
	    }
	  });
	}
</script>

</body>
</html>
