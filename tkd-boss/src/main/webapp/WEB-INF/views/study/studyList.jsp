<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>学习资料</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">学习资料</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="activityForm" class="form-horizontal" action="${ctx }/study/list" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LIKE_fileName" name="search_LIKE_fileName" value="${param.search_LIKE_fileName }" placeholder="按文件名称查询">
       	  			</div>
		         </div>
		         
		        <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				&nbsp;&nbsp;
	  				</div>
				</div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
	   <div class="row"><!-- 操作按钮组 -->
		    	<div class="col-md-12 text-right">
		    	<div class="btn-group-sm pull-right mtb10">
		    	<shiro:hasPermission name="activity:createForm">
					 <a class="btn btn-primary btn-sm" href="${ctx }/study/createForm?action=create" >
					 <span class="glyphicon glyphicon-plus"></span> 添加学习资料</a> 
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
				<th>文件名称</th>
				<th>上传人</th>
				<th>上传时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="study" varStatus="stat">
				<tr>
					<input type="hidden" id="id" value="${study.id }"/>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">${study.fileName}</td>
					<td style="text-align: center;">${study.userName}</td>
					<td style="text-align: center;"><fmt:formatDate value="${study.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;">
					   	<span class="cutline"></span> 
					   	<shiro:hasPermission name="activity:deleteActivity">
					  	<a href="#" data="${study.id }"  onclick="deleteById('${study.id}')"> 删除</a>
					   	</shiro:hasPermission>
					   	<shiro:hasPermission name="activity:deleteActivity">
					  	<a href="${study.fileUrl }"  > 下载</a>
					   	</shiro:hasPermission>
					  	<span class="cutline"></span>
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
	  menu.active('#study-man');
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  $('#adminFooter').hide();
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
	//删除
	
	function deleteById(id){
		var $form =$("#actionForm");
		$form.attr("action","${ctx}/study/deleteStudy/" +id);
		bootbox.confirm("您确定要删除吗",function(result){
			if(result){
				$form[0].submit();
			}
		});
	}
	
	
</script>
</body>
</html>