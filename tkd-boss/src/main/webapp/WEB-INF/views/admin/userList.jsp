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
        <li class="active">员工管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<!-- 查询 -->
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" action="${ctx }/admin/user" method="post">
 
					<div class="form-group form-group-sm">
						<label class="control-label col-md-1 sr-only" for="custName"></label>
						<div class="col-md-5">
		         				<input type="text" class="form-control input-sm" id="search_LIKE_loginName" name="search_LIKE_loginName" value="${param.search_LIKE_loginName }" placeholder="按登录账号查询">
		       			</div>
		       			<div class="col-md-5">
		         				<input type="text" class="form-control input-sm" id="search_LIKE_nickname" name="search_LIKE_nickname" value="${param.search_LIKE_nickname }" placeholder="按用户名查询">
		       			</div>	
					</div>
					
					<div class="form-group form-group-sm">
							<label class="control-label col-md-1 sr-only" for="custName"></label>
							<div class="col-md-5">
								<div class="input-group">
					          		<input  type="text" class="form-control input-sm" id="orgName"  value="<tags:orgName orgCode="${param.search_EQ_orgCode }" />" placeholder="按组织查询">
					          		<span class="input-group-btn">
					            		<button class="btn btn-default btn-sm" type="button"  id="orgNameClean">清除</button>
					          		</span>
					        	</div>
								<input  type="hidden" class="form-control input-sm" id="search_EQ_orgCode" name="search_EQ_orgCode" value="${param.search_EQ_orgCode }" />
		       				</div>
		       				<div class="col-md-5">
                       			
                       			 <select class="form-control"  id="search_EQ_roleId" name="search_EQ_roleId" >
			                     	<option value="">-按角色查询-</option>
								 	<c:forEach items="${roleList}" var="role"  >
								 		<option value="${role.roleId}" <c:if test="${ param.search_EQ_roleId == role.roleId }">selected</c:if>>${role.roleName}(${role.roleCode})</option>
								 	</c:forEach>
			                     </select>
                       			
                       		</div>
						</div>
					
					<!-- 带有约束的日期条件，开始－结束 --> 
                       <div class="form-group form-group-sm query-more">
                       	<lable class="control-label col-md-1 sr-only"></lable>
                       	<div class="col-md-5">
                       		<input type="text" class="form-control Wdate " 
                       			id="search_GTE_createTime" name="search_GTE_createTime" 
                       			value="${param.search_GTE_createTime }" 
                       			placeholder="创建日期-开始" 
                       			onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_createTime\')||\'%y-%M-%d\'}'})" />
                       	</div>
                       	<div class="col-md-5">
                       		<input type="text" class="form-control Wdate " 
                       			id="search_LTE_createTime" name="search_LTE_createTime" 
                       			value="${param.search_LTE_createTime }" 
                       			placeholder="创建日期-结束" 
                       			onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'search_GTE_createTime\')}'})" />
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
				<shiro:hasPermission name="user:create">
	        		<a class="btn btn-sm btn-primary" href="${ctx }/admin/user/create" ><span class="glyphicon glyphicon-plus"></span> 创建新用户</a>
	      		</shiro:hasPermission>	
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
				<th>登录账号</th>
				<th>真实姓名</th>
				<th>组织</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="user" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td>${user.loginName}</td>
					<td>${user.nickname}</td>
					<td><tags:orgName orgCode="${user.orgCode}" /></td>
					<td> <fmt:formatDate value="${user.createTime}" pattern="YYYY-MM-dd HH:mm:ss"/> </td>
					<td>
					  <a href="${ctx}/admin/user/update/${user.userId}" id="editLink-${user.userId}"> 修改</a>
					   <span class="cutline"></span> 
					  <a href="#" data="${user.nickname }" id="removeLink-${user.userId}" onclick="deleteById('${user.userId}')"> 删除</a>
					   <span class="cutline"></span>
					  <a href="${ctx}/admin/user/resetPwd/${user.userId}" data="${user.nickname }" class="resetPwd" id="resetLink-${user.userId}"> 重置密码</a>
					</td>
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
	  
		/* 按照组织查询  */
		$("#orgName").click(function() {
			$("#myDlgBody").load("${ctx}/common/search_org_dlg");
			$("#myDlg").modal();
		});
		$("#orgNameClean").click(function(){
			$("#orgName").val('');
			$("#search_EQ_orgCode").val('');
	 	});
	  
	  $('a.resetPwd').click(function() {
		  var $this = $(this);
		  bootbox.confirm('您确定要重置 [' + $this.attr('data') + '] 的密码？', function(result) {
	        if(result) {
	            window.location.href=$this[0].href;
	        }
	      });
		  return false;
	  });
	  $('#adminFooter').hide();
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
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
	  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/admin/user/delete/' + id);
	  //bootbox.setDefaults({size:'large'});
	  bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

	function deleteBySelected() {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/admin/user/delete');
	  
	  var ids = getSelected();
	  if (ids.length == 0) {
	    bootbox.alert('请选择要删除的用户！');
	    return false;
	  }
	  
	  bootbox.confirm('您确定要删除选中的用户吗？', function(result) {
	    if(result) {
	      $('#ids').val(ids.join(';'));
	      $form[0].submit();
	    }
	  }) ;
	}
	
	
	
</script>

</body>
</html>
