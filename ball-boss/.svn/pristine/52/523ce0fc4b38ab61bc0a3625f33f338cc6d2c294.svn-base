<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>赛事管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 活动管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form class="form-inline" action="${ctx}/admin/activity/list" method="post">
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_name">活动名称：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="按活动名称查询">
		        </div>
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_address">活动地点：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_address" name="search_LIKE_address" value="${param.search_LIKE_address }" placeholder="按活动地点查询">
		        </div>
		        <div class="form-group form-group-sm">
				    <!-- <label for="search_LIKE_sportType" style="width:90px;line-height:30px;" class="col-md-3 control-label">场地类别</label> -->
				    <div class="col-md-6 has-feedback form-inline">
				    	<input type="hidden" id="sportType" />
						<select class="form-control" name="search_EQ_type" id="search_EQ_type">
							<option id="option0" value="" selected>--场地类别--</option>
							<option id="option0" value="0" >--篮球--</option>
							<option id="option1" value="1" >--足球--</option>
							<option id="option2" value="2" >--羽毛球--</option>
							<option id="option3" value="3" >--台球--</option>
							<option id="option4" value="4" >--保龄球--</option>
							<option id="option5" value="5" >--高尔夫--</option>
							<option id="option6" value="6" >--乒乓球--</option>
							<option id="option7" value="7" >--网球--</option>
						</select>
				  	</div>
				 </div> 
		        <div class="form-group">
		          <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button>
		        </div>
		      </form>
			</div>
			
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
	    <div class="col-md-12">
	      <div class="btn-group-sm pull-right mtb10">
	        <a class="btn btn-primary" href="${ctx}/admin/activity/sign/0" ><span class="glyphicon glyphicon-plus"></span> 新增活动</a>
	      </div>
	    </div>
	  </div><!-- /操作按钮组 -->
				
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
			<%-- 
			  <th class="text-center"><input type="checkbox" name="chk_all" onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
			--%>
				<th class="text-center">序号</th>
				<th>活动类型</th>
				<th>活动名称</th>
				<th>活动地点</th>
				<!-- 
				<th>已报名人数</th>
				 -->
				<th>创建人</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="activity" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>
						<c:if test="${activity.type == 0}" >
							篮球
						</c:if>
						<c:if test="${activity.type == 1}" >
							足球
						</c:if>
						<c:if test="${activity.type == 2}" >
							羽毛球
						</c:if>
						<c:if test="${activity.type == 3}" >
							台球
						</c:if>
						<c:if test="${activity.type == 4}" >
							保龄球
						</c:if>
						<c:if test="${activity.type == 5}" >
							高尔夫
						</c:if>
						<c:if test="${activity.type == 6}" >
							乒乓球
						</c:if>
						<c:if test="${activity.type == 7}" >
							网球
						</c:if>
					</td>
					<td><c:out value="${activity.name}"/></td>
					<td><c:out value="${activity.address}"/></td>
					<!-- 
					<td><c:out value="${activity.applicantNumber}"/></td>
					-->
					<td><c:out value="${activity.userid}"/></td>
					<td>
					  <a href="#" id="stickLink-${activity.id}" onclick="stickById('${activity.id}')"> 置顶</a>
					  <span class="cutline"></span> 
					  <a href="${ctx}/admin/activity/sign/${activity.id}" id="editLink-${activity.id}"> 修改</a>
					   <span class="cutline"></span> 
					  <a href="#" data="${activity.id}" id="removeLink-${activity.id}" onclick="deleteById('${activity.id}')"> 删除</a>
					   <span class="cutline"></span> 
					</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		
		<tags:pagination page="${data}" />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
    
  </div><!-- /右侧主体内容 -->
<tags:errors />
</div>

<script type="text/javascript">
$(function() {
	  menu.active('#activity-form');
		$('#adminFooter').hide();
		
		var typeValue = '${param.search_EQ_type }';
		
		if(typeValue){
			$("select[name=search_EQ_type] option[value="+typeValue+"]").attr("selected","selected");
		}
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
	  $form.attr('action', '${ctx}/admin/activity/delete/' + id);
	  //bootbox.setDefaults({size:'large'});
	  bootbox.confirm('您确定要删除该条记录吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}
	
	function stickById(id) {
		  var $form = $('#actionForm');
		  $form.attr('action', '${ctx}/admin/activity/stick/' + id);
		  //bootbox.setDefaults({size:'large'});
		  bootbox.confirm('您确定要置顶该活动吗？', function(result) {
		    if(result) {
		      $form[0].submit();
		    }
		  });
		  return false;
		}

</script>

</body>
</html>