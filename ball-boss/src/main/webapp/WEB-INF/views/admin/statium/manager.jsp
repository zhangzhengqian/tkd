<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home active"></span> 我管理的场馆</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->

	  	
	  <div class="row"><!-- 操作按钮组 -->
	   	 <div class="col-md-12">
    	    <form class="form-inline" action="${ctx}/admin/statium/managerForAdmin" method="post">
   		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_login">登录名：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_login" name="search_LIKE_login" value="${param.search_LIKE_login}" placeholder="按登录名查询">
		        </div>    
  		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_name">场馆名称：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="按场馆名称查询">
		        </div>  
		        
 		        <div class="form-group form-group-sm">
				    <label for="search_LIKE_sportType" style="width:90px;line-height:30px;" class="col-md-3 control-label">场地类别</label>
				    <div class="col-md-6 has-feedback form-inline">
				    	<input type="hidden" id="sportType" />
						<select class="form-control" name="search_LIKE_sportType" id="search_LIKE_sportType">
							<option id="option0" value="" selected>--任何类别--</option>
							<option id="option0" value="BASKETBALL" >--篮球--</option>
							<option id="option1" value="FOOTBALL" >--足球--</option>
							<option id="option2" value="BADMINTON" >--羽毛球--</option>
							<option id="option3" value="BILLIARDS" >--台球--</option>
							<option id="option4" value="BOWLING" >--保龄球--</option>
							<option id="option5" value="GOLF" >--高尔夫--</option>
							<option id="option6" value="TABLE_TENNIS" >--乒乓球--</option>
							<option id="option7" value="TENNIS" >--网球--</option>
						</select>
				  	</div>
				 </div>  
		 
		        <div class="form-group">
		          <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
		        </div>		        
	        </form> 
	      <div class="btn-group-sm pull-right mtb10">
	        <a class="btn btn-primary" href="${ctx }/admin/statium/manager/create" ><span class="glyphicon glyphicon-plus"></span> 添加球馆</a>
	      </div>
	    </div>
	  	</div><!-- /操作按钮组 -->	
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center">登录名</th>
	                <th class="text-center">姓名</th>
	                <th class="text-center">创建日期</th>
	                <th class="text-center">球馆名称</th>
	                <th class="text-center">地区</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="itm" varStatus="stat">
				<tr>
                    <td class="text-center"> ${itm.loginName }</td>
                    <td class="text-center"> ${itm.nickname } </td>
                    <td class="text-center">
                    	<fmt:formatDate value="${itm.statium.ct }" pattern="yyyy-MM-dd"/>
                    </td>
					<td>
					${itm.statium.name }
					</td>

                    <td class="">
                 		  <tags:zonemap code="${itm.statium.areaCode }" />    
                    </td>
					<td>
						<a href="${ctx }/admin/org/companyForm/${itm.userId }" class="btn btn-sm btn-default" > 公司资质 </a>
						<a href="${ctx }/admin/org/businessForm/${itm.userId }" class="btn btn-sm btn-default" > 营业资质 </a>
						<a href="${ctx }/admin/org/statiumForm/${itm.userId }" class="btn btn-sm btn-default" > 球馆信息 </a>
						<a data="${itm.userId}" onclick="deleteById('${itm.userId}')" href="#" id="removeLink-${itm.userId}" class="btn btn-sm btn-default" > 删除</a> 
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
	  menu.active('#statium-manager-man');
	  $('#adminFooter').hide();
  });

	function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx }/admin/org/deleteStatium/' + id);
	  //bootbox.setDefaults({size:'large'});
	  //bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
		bootbox.confirm('您确定要删除该记录吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

</script>
