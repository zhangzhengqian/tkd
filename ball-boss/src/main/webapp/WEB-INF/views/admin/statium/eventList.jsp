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
        <li><span class="glyphicon glyphicon-home"></span> 赛事管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form class="form-inline" action="${ctx}/admin/event/list" method="post">
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_ateam">约战方：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_ateam" name="search_LIKE_ateam" value="${param.search_LIKE_ateam}" placeholder="按约战方查询">
		        </div>
		        <div class="form-group">
		          <label class="sr-only" for="search_LIKE_bteam">应战方：</label>
		          <input type="text" class="form-control input-sm" id="search_LIKE_bteam" name="search_LIKE_bteam" value="${param.search_LIKE_bteam }" placeholder="按应战方查询">
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
					<label class="col-md-3 control-label" style="width:90px;line-height:30px;"><span class="text-red"></span>开始时间:</label>
	                <div class="col-sm-4">
	                     <div class="input-group col-md-6 has-feedback">
	                         <input type="text" name="search_GT_start" style="width:200px;" value="${param.search_GT_start }" id="search_GT_start" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM-dd HH:mm:ss"});' value="" readonly>
	                         <label for="search_GT_start" class="input-group-addon"><i class="fa fa-calendar"></i></label>
	                     </div>
	                </div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:90px;line-height:30px;"><span class="text-red"></span>结束时间:</label>
	                <div class="col-sm-6">
	                     <div class="input-group col-md-6 has-feedback">
	                         <input type="text" name="search_LT_end" id="search_LT_end" style="width:200px;" value="${param.search_LT_end }" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM-dd HH:mm:ss"});' value="" readonly>
	                         <label for="search_LT_end" class="input-group-addon"><i class="fa fa-calendar"></i></label>
	                     </div>
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
	        <a class="btn btn-primary" href="${ctx}/admin/event/sign/0" ><span class="glyphicon glyphicon-plus"></span> 新增赛事</a>
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
				<th>赛事类型</th>
				<th>约战方信息</th>
				<th>应战方信息</th>
				<th>比赛时间</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="event" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>
						<c:if test="${event.type == 0}" >
							篮球
						</c:if>
						<c:if test="${event.type == 1}" >
							足球
						</c:if>
						<c:if test="${event.type == 2}" >
							羽毛球
						</c:if>
						<c:if test="${event.type == 3}" >
							台球
						</c:if>
						<c:if test="${event.type == 4}" >
							保龄球
						</c:if>
						<c:if test="${event.type == 5}" >
							高尔夫
						</c:if>
						<c:if test="${event.type == 6}" >
							乒乓球
						</c:if>
						<c:if test="${event.type == 7}" >
							网球
						</c:if>
					</td>
					<td>${event.ateam}</td>
					<td>${event.bteam}</td>
					<td>${event.eventDate}</td>
					<td>${event.user.loginName}</td>
					<td>
					  <a href="${ctx}/admin/event/sign/${event.id}" id="editLink-${event.id}"> 修改</a>
					   <span class="cutline"></span> 
					  <a href="#" data="${event.id}" id="removeLink-${event.id}" onclick="deleteById('${event.id}')"> 删除</a>
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

</div>

<script type="text/javascript">
$(function() {
	  menu.active('#event-form');
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
	  $form.attr('action', '${ctx}/admin/event/delete/' + id);
	  //bootbox.setDefaults({size:'large'});
	  bootbox.confirm('您确定要删除该赛事吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

</script>

</body>
</html>