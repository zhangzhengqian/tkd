<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!-- <!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>操作员管理</title>
</head>
<body> -->

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home active"></span> 操作员管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
      <shiro:hasRole name="statium_manager">
      <div class="row">
	    <div class="col-md-12">
	      <div class="btn-group-sm pull-right mtb10">
          	<a class="btn btn-primary" href="${ctx }/member/operator/create" ><span class="glyphicon glyphicon-plus"></span>新建</a>
	      </div>
	    </div>
	  </div>
      </shiro:hasRole> 
        
		<table id="contentTable" class="table table-bordered table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center">登录账号</th>
	                <th class="text-center">姓名</th>
	                <th class="text-center">创建日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data}" var="operator" varStatus="stat">
				<tr>
					<td class="text-center">${operator.loginName}</td>
					<td class="text-center">${operator.nickname}</td>
					<td class="text-center"><fmt:formatDate value="${operator.createTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                    	<shiro:hasRole name="statium_manager">
                    	<a class="btn btn-sm btn-default" href="${ctx }/member/operator/update/${operator.userId}" ><span class="glyphicon glyphicon-edit"></span>修改</a>
                    	<a class="btn btn-sm btn-default" href="#" onclick="deleteById('${operator.userId}','${itm.realName }')" ><span class="glyphicon glyphicon-remove"></span>删除</a>
                    	</shiro:hasRole>
                    </td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		
        <tags:errors />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">

  $(function() {
	  menu.active('#operator-man');
	  $('#footer').hide();
  });
	  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/member/operator/delete/' + id);
	  bootbox.confirm('您确定要删除此操作员吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

</script>

<!-- </body>
</html> -->
