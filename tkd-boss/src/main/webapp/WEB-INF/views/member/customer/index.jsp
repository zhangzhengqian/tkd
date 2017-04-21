<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>会员管理</title>
</head>
<body>

<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home active"></span> 会员管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row mtb10"><!-- 查询条件 -->
			<div class="col-md-10">
		      <form class="form-inline" action="${ctx }/member/customer">
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_loginName">姓名：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_realName" name="search_LIKE_realName" value="${param.search_LIKE_realName}" placeholder="姓名">
		        </div>
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_nickname">手机：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="手机">
		        </div>
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_custName">身份证：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_idCard" name="search_LIKE_idCard" value="${param.search_LIKE_idCard }" placeholder="身份证">
		        </div>
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_custName">会员卡号：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_customerNo" name="search_LIKE_customerNo" value="${param.search_LIKE_customerNo }" placeholder="会员卡号">
		        </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                </div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	 	<div class="row"><!-- 操作按钮组 -->
	    	<div class="col-md-11">
	      		<div class="btn-group-sm pull-right mtb10">
	        		<a class="btn btn-default" href="${ctx }/member/customer/import" ><span class="glyphicon glyphicon-import" aria-hidden="true"></span>导入</a>
	      		</div>
	    	</div>
	    	<div class="col-md-1">
	      		<div class="btn-group-sm pull-right mtb10">
	        		<a class="btn btn-primary" href="${ctx }/member/customer/customerForm?action=create" ><span class="glyphicon glyphicon-plus"></span>创建会员</a>
	      		</div>
	    	</div>
	  	</div><!-- /操作按钮组 --> 
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
				<tr>
	                <th width="50">卡号</th>
	                <th class="text-center">姓名</th>
	                <th class="text-center">手机</th>
	                <th class="text-center">性别</th>
	                <th class="text-center">地区</th>
	                <th class="text-center">创建人</th>
	                <th class="text-center">创建日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="itm" varStatus="stat">
				<tr>
					<td>
						<a href="${ctx }/member/customer/customerForm?action=view&id=${itm.id}" >
							${itm.customerNo }
						</a>	
					</td>
					<td>${itm.realName}</td>
					<td>${itm.phone}</td>
					<td>${itm.gender}</td>
					<td>
						<tags:zonemap code="${itm.areaCode }" />
					</td>
					<td>
						<tags:getUserById id="${itm.cb }" />
					</td>
                    <td class="text-center">
                    	<fmt:formatDate value="${itm.ct }" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                    	<a class="btn btn-sm btn-default" href="${ctx }/member/customer/orders?id=${itm.id}" ><span class="glyphicon glyphicon-search"></span>消费记录</a>
                    	<a class="btn btn-sm btn-default" href="${ctx }/member/customer/customerForm?action=edit&id=${itm.id}" ><span class="glyphicon glyphicon-edit"></span>修改</a>
                    	<shiro:hasRole name="statium_manager">
	                    	<a class="btn btn-sm btn-default" href="#" onclick="deleteById('${itm.id}','${itm.realName }')" ><span class="glyphicon glyphicon-remove"></span>删除</a>
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
	  menu.active('#customer-man');
	  $('#footer').hide();
  });
	  
	function deleteById(id,realName) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/member/customer/delete/' + id);
	  bootbox.confirm('您确定要删除 [' + realName + '] 吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

</script>

</body>
</html>
