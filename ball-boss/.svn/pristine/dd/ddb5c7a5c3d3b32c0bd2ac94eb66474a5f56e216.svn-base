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
        <li><span class="glyphicon glyphicon-home active"></span> 场馆管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row mtb10"><!-- 查询条件 -->
			<div class="col-md-10">
		      <form class="form-inline" action="${ctx }/admin/statium" method="post">
		        <div class="form-group">
		          	地区：<tags:zone name="search_LIKE_areaCode" value="${param.search_LIKE_areaCode }" clazz="false" />
		        </div>
		         <div class="form-group">
			         <input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="按场馆名称查询">
		         </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                </div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	  
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
				<tr>
	               <!--  <th width="50"></th> -->
	                <th class="text-center">名称</th>
	                <th class="text-center">地区</th>
	                <th class="text-center">球馆管理员</th>
	                <th class="text-center">签约／录入</th>
	                <th class="text-center">最后修改日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="statium" varStatus="stat">
				<tr>
                    <%-- <td>
                    	<img src="${statium.logo}" width="80" height="50" />  
                    </td> --%>
                    <td class="">${statium.name }</td>
                    <td class="">
                    	<tags:zonemap code="${statium.areaCode }" />
                    </td>
                    <td class="text-center">
                    	<tags:getUserById id="${statium.cb }" />
                    </td>
                    <td class="text-center">
                    	<tags:getUserById id="${statium.sb }" field="nickname" />
                    </td>
                    <td class="text-center">
                    	<fmt:formatDate value="${statium.et }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
					<td>
					  <a href="${ctx }/admin/statium/audit/${statium.cb}?action=view" class="btn btn-sm btn-default" > 查看</a>
					  <tags:statiumAuditButton cb="${statium.cb }"/>
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
	  $('#adminFooter').hide();
	  menu.active('#statium-man');
  });
	  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/admin/space/delete/' + id);
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
