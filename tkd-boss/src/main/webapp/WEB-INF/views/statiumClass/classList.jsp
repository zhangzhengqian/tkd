<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 课程列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12 hide">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/statium" method="post">
			  		<input type="hidden" name="search_EQ_statiumId" value="${param.search_EQ_statiumId}" id="search_EQ_statiumId"  />
			  		<input type="hidden" name="fromPage" id="fromPage" value="${param.action }" />
			  	
			        <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
				  	  <div class="col-md-5">
				  	  		<input  type="text" class="form-control input-sm" id="search_LIKE_a2"  name="search_LIKE_a2"  value="${param.search_LIKE_a2 }" placeholder="按道馆地址查询">
			       	  </div>
			       	  <div class="col-md-5">
							<input  type="text" class="form-control input-sm" id="search_LIKE_cnname"  name="search_LIKE_cnname"  value="${param.search_LIKE_cnname }" placeholder="按道馆负责人名称查询">
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
	    <div class="col-md-12 text-right">
	    	<!-- <a class="btn btn-default btn-sm" href="javascript:window.history.go(-1);" ><span class="glyphicon glyphicon-arrow-left"></span> 返回道馆列表</a> -->
		    <a class="btn btn-primary btn-sm" href="${ctx }/statiumClass/createForm?action=create&statiumId=${param.dgid}&fromPage=${param.action}" ><span class="glyphicon glyphicon-plus"></span> 添加课程</a>
		</div>
	  </div><!-- /操作按钮组 -->
	<br>	
				
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>课程名称</th>
				<th>原价</th>
				<th>签约价</th>
				<th>课程类型</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="class" varStatus="stat">
				<c:set var="status_class" value="" />
				<tr class="${status_class }" >
					<td class="text-center">${stat.count}</td>
					<td>
						<a href="${ctx }/statiumClass/detailForm?id=${class.id}&fromPage=${param.action}"> ${class.classTitle}</a>
					</td>
					<td>${class.flPrice}</td>
					<td>${class.discountPrice/100 }</td>
					<!-- 隐藏折扣输入框 -->
					<%-- <td>${class.flDiscount}折</td>
					<td>${class.flDiscountPrice}</td> --%>
					<td>
						<c:if test="${'0' == class.type}">${class.minPeople}－${class.maxPeople }人</c:if>
						<c:if test="${'1' == class.type}">一对一</c:if>
					</td>
					<td>
						<a class="btn btn-default btn-sm" href="${ctx }/statiumClass/detailForm?id=${class.id}&statiumId=${param.dgid }&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a>
						<a class="btn btn-default btn-sm" href="javascirpt:void(0);" onclick="deleteById('${class.id}')"><i class="glyphicon glyphicon-edit"></i> 删除</a>
						<a class="btn btn-default btn-sm" href="${ctx }/statiumClassInfo/list?classId=${class.id}&statiumId=${param.dgid}&action=${param.action}"><i class="glyphicon glyphicon-cog"></i> 课时安排</a>
					   
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
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	menu.active('#statiumOa-man');
	/*if ($("#fromPage").val() == 'oa') {
	  menu.active('#statiumOa-man');
	} else {
	  menu.active('#statium-man');
	}*/
	  
	  $('#adminFooter').hide();
	  
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
	  bootbox.confirm('您确定要删除课程吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/statiumClass/delete', function(data){
	      	 if(data.result){
		      	 myAlert("课程删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("课程删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
	
</script>

</body>
</html>