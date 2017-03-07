<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理</title>
</head>

<body>

<div class="panel panel-default">

 	<div class="panel-heading"><!-- 右侧标题 -->
	  <ul class="breadcrumb">
	      <li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
	      <li class="active">角色管理</li>
	  </ul>
	</div><!-- / 右侧标题 -->
	
	<div class="panel-body"><!-- 右侧主体内容 -->
	
	  <!-- 域名查询 -->
    <div class="row">
      <div class="col-md-9">
        <form class="form-inline" >
        	<div class="form-group">
			      <label class="sr-only" for="roleName">角色名称：</label>
			      <input type="text" class="form-control input-sm" id="search_LIKE_roleName" name="search_LIKE_roleName" value="${param.search_LIKE_roleName }" placeholder="按名称查询">
			    </div>
        	<div class="form-group">
			      <label class="sr-only" for="roleCode">角色编码：</label>
			      <input type="text" class="form-control input-sm" id="search_LIKE_roleCode" name="search_LIKE_roleCode" value="${param.search_LIKE_roleCode }" placeholder="按编码查询">
			    </div>
			    <div class="form-group">
			      <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
			    </div>
        </form>
      </div>
      <div class="col-md-3 text-right form-inline">
	        <a class="btn btn-sm btn-primary" href="${ctx }/admin/role/create"><span class="glyphicon glyphicon-plus"></span> 创建新角色</a>
	        <button type="button" class="btn btn-sm btn-danger" onclick="deleteBySelected()"><span class="glyphicon glyphicon-remove"></span> 删除角色</button>
      </div>
    </div>
    <!-- / 域名查询 -->
	     
	<hr>
    
	  <div class="tab-content">
	    <div class="tab-pane active">
	      <table class="table table-bordered table-condensed table-hover" id="table1">
	        <thead>
	          <tr class="thead">
	          <th class="text-center"><input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>           
	          <th>角色编码</th>
	          <th>角色名称</th>
	          <th>操作</th>
	          </tr>
	        </thead>
	        <tbody>
	          
					<c:forEach items="${data.content}" var="role" varStatus="stat">
					  <tr>
					    <td class="text-center"><input type="checkbox" name="chk_list" id="chk_list_${stat.index }" value="${role.roleId }" /></td>
					    <td>${role.roleCode}</td>
					    <td>${role.roleName}</td>
					    <td>
						    <a href="${ctx}/admin/role/update/${role.roleId}" id="editLink-${role.roleId}">修改</a>
						     <span class="cutline"></span> 
						    <a href="#" id="editLink-${role.roleId}" onclick="deleteById('${role.roleId}')">删除</a>
						     <span class="cutline"></span> 
						    <a href="${ctx}/admin/role/perms/${role.roleId}" id="editLink-${role.roleId}">分配权限</a>
					    </td>
					  </tr>
					</c:forEach>
    
	        </tbody>
	      </table>
	      
        <tags:pagination page="${data}" />
	  
	    </div>
	    
	  </div>
	  
	
	  
	  <form id="actionForm" action="" method="post">
	     <input type="hidden" id="ids" name="ids">
	  </form>
	  
	</div><!-- /右侧主体内容 -->

</div>

  
  
<script type="text/javascript">

$(function() {
  menu.active('#role-man');
});

function getSelected() {
	  var ids = [];
	  var checked = $('input:checked');
	  if (checked.length) {
	    checked.each(function() {
	      if ($(this).attr('name') != 'chk_all') {
	        ids.push($(this).val());
	      }
	    });
	  }
	  return ids;
}
	
function deleteById(rid) {
	var $form = $('#actionForm');
	$form.attr('action', '${ctx}/admin/role/delete/' + rid);
	//bootbox.setDefaults({size:'large'});
	bootbox.confirm('您确定要删除该角色吗？', function(result) {
	  if(result) {
	    $form[0].submit();
	  }
	});
}

function deleteBySelected() {
	var $form = $('#actionForm');
	$form.attr('action', '${ctx}/admin/role/delete/');
	
	var ids = getSelected();
	if (ids.length == 0) {
		bootbox.alert('请选择要删除的角色！');
		return false;
	}
	
	bootbox.confirm('您确定要删除选中的角色吗？', function(result) {
		if(result) {
			$('#ids').val(ids.join(';'));
			$form[0].submit();
		}
	}) ;
}

</script>

</body>
</html>
