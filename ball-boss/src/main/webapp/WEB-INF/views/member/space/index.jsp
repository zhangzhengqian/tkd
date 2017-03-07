<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场馆管理</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 场馆管理</li>
        <li class="active">场地管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row mtb10"><!-- 查询条件 -->
			<div class="col-md-10">
		      <form class="form-inline" action="${ctx }/member/space">
		        <div class="form-group">
		          <label class="sr-only" for="search_EQ_sportType">按类型查找：</label>
		          <select name="search_EQ_sportType" class="form-control input-sm">
		               <option value="" <c:if test="${empty param.search_EQ_sportType }">selected</c:if> > 所有类型
		               <c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
		                   <option value="${item.itemCode }" <c:if test="${param.search_EQ_sportType eq item.itemCode }">selected</c:if> > ${item.itemName }
		               </c:forEach>
		          </select>
		        </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                </div>
                <shiro:hasRole name="statium_manager">
                <div class="form-group">
                    <a class="btn btn-primary btn-sm" href="${ctx }/member/space/create" ><span class="glyphicon glyphicon-plus"></span> 新建</a>
                </div>
                </shiro:hasRole>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	  
		<table id="contentTable" class="table table-bordered table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center">编号</th>
	                <th class="text-center">名称</th>
	                <th class="text-center">类型</th>
	                <th class="text-center">备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="statium" varStatus="stat">
				<tr>
                    <td class="text-center">${statium.spaceCode }</td>
                    <td class="text-center">${statium.spaceName }</td>
                    <td class="text-center">${lf:dicItem(statium.sportType).itemName }</td>
                    <td class="text-center">${statium.comment }</td>
					<td>
						<shiro:hasRole name="statium_manager">
					  		<a href="${ctx}/member/space/update/${statium.id}" id="editLink-${statium.id}" class="btn btn-sm btn-default"> 修改</a>
					   		<span class="cutline"></span> 
					  		<a href="#" data="${statium.spaceCode }" id="removeLink-${statium.id}" onclick="deleteById('${statium.id}')" class="btn btn-sm btn-default"> 删除</a>
					  	</shiro:hasRole>
					</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		
        <tags:pagination page="${data}" />
        <tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#statium-space-man');
	  $('#footer').hide();
  });
	  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/member/space/delete/' + id);
	  //bootbox.setDefaults({size:'large'});
	  bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

</script>

</body>
</html>
