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
        <li><span class="glyphicon glyphicon-home"></span> 球友管理</li>
        <li class="active">标签管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="labelForm" class="form-horizontal" action="${ctx }/qiuyouzoneLabel/list" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" ></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="按标签名称查询">
			       	</div>
			       	
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_type" name="search_EQ_type">		
						<option  value="" >--请选择标签类别--</option>
						<option <c:if test="${'0' == param.search_EQ_type}">selected</c:if> id="option0" value="0" >--后台添加--</option>
						<option <c:if test="${'1' == param.search_EQ_type}">selected</c:if> id="option1" value="1" >--用户添加--</option>
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
		    <shiro:hasPermission name="qylabel:create">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-sm btn-primary" href="#" onclick="create()" ><span class="glyphicon glyphicon-plus"></span> 新增标签</a>
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
				<th>使用次数</th>
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
						<c:if test="${label.type=='0' }" >后台添加</c:if>
						<c:if test="${label.type=='1' }" >用户添加</c:if>
					</td>
					<td>${label.useNumber}</td>
					<td style="text-align: center;">
						<c:if test="${label.status==0 }">停用</c:if>
						<c:if test="${label.status==1 }">启用</c:if>
					</td>
					<td style="text-align: center;"><fmt:formatDate value="${label.ct }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
 					<td style="text-align: center;">
						<shiro:hasPermission name="qylabel:update">
							<a class="btn btn-default btn-sm"
							 href="${ctx}/qiuyouzoneLabel/detail/${label.id}" id="editLink-${label.id}"> 
							 <i class="glyphicon glyphicon-edit"></i>修改</a>
							 <span class="cutline"></span>
						</shiro:hasPermission>
						<shiro:hasPermission name="qylabel:status">
								<c:if test="${label.status==1 }"> 
									<a class="btn btn-default btn-sm" href="${ctx}/qiuyouzoneLabel/status/${label.id}/0" id="statusLink-${label.id}"><i class="glyphicon glyphicon-edit"></i>冻结</a>
								</c:if>
								<c:if test="${label.status==0 }"> 
									<a class="btn btn-default btn-sm" href="${ctx}/qiuyouzoneLabel/status/${label.id}/1" id="statusLink-${label.id}"><i class="glyphicon glyphicon-edit"></i>启用</a>
								</c:if>
							</a> <span class="cutline"></span>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="qylabel:stick">
								<c:if test="${label.topTime == null}">
									<a class="btn btn-default btn-sm" href="${ctx}/qiuyouzoneLabel/stick/${label.id}"
										id="editLink-${label.id}" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-remove"></i>置顶</a>
								</c:if>
							</shiro:hasPermission>
						<shiro:hasPermission name="qylabel:unstick">
								<c:if test="${label.topTime != null}">
									<a class="btn btn-default btn-sm" href="${ctx}/qiuyouzoneLabel/unstick/${label.id}"
										id="editLink-${label.id}" class="btn btn-default btn-sm"><i
										class="glyphicon glyphicon glyphicon-remove"></i> 取消置顶</a>
								</c:if>
							</shiro:hasPermission>
						<span class="cutline"></span>
							<shiro:hasPermission name="qylabel:delete">
						  		<a class="btn btn-default btn-sm" href="javascript:void(0)" data="${label.name }" id="removeLink-${label.id}" onclick="deleteById('${label.id}')">
						  		<i class="glyphicon glyphicon-trash"></i> 删除</a>
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
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
 
  $(function() {
	  menu.active('#qiuyoulabel-list');
	  
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  
	  $('#adminFooter').hide();
  });
	  
  
  function create(){
		$("#myDlgBody_lg").load("${ctx}/qiuyouzoneLabel/createForm_dlg");
		$("#myDlg_lg").modal();
  }
  
  function deleteById(id){
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除该标签吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/qiuyouzoneLabel/delete', function(data){
	      	 if(data.result){
		      	 myAlert("删除标签成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("标签删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
</script>

</body>
</html>
