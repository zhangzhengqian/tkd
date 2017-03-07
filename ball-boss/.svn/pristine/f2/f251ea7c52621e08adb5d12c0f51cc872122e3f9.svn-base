<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>活动管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li class="active">活动管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form id="activityForm" class="form-horizontal" action="${ctx }/activity/list" method="post">
		        <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="按活动名称查询">
			       	</div>
	  				<div class="col-md-5">
            			<input type="text" class="form-control input-sm" id="search_LIKE_address" name="search_LIKE_address" value="${param.search_LIKE_address }" placeholder="按活动地点查询">
       	  			</div>
		         </div>
		         
		         <div class="form-group form-group-sm query-more">
		         	<label class="control-label col-md-1 sr-only" for="type"></label>
		         	<div class="col-md-5">
						<select class="form-control" id="search_EQ_type" name="search_EQ_type">		
						<option  value="" >--请选择运动类别--</option>
						<option <c:if test="${'3' == param.search_EQ_type}">selected</c:if> id="option0" value="3" >--篮球--</option>
						<option <c:if test="${'6' == param.search_EQ_type}">selected</c:if> id="option1" value="6" >--足球--</option>
						<option <c:if test="${'1' == param.search_EQ_type}">selected</c:if> id="option2" value="1" >--羽毛球--</option>
						<option <c:if test="${'7' == param.search_EQ_type}">selected</c:if> id="option3" value="7" >--台球--</option>
						<option <c:if test="${'8' == param.search_EQ_type}">selected</c:if> id="option4" value="8" >--保龄球--</option>
						<option <c:if test="${'5' == param.search_EQ_type}">selected</c:if> id="option5" value="5" >--高尔夫--</option>
						<option <c:if test="${'4' == param.search_EQ_type}">selected</c:if> id="option6" value="4" >--乒乓球--</option>
						<option <c:if test="${'2' == param.search_EQ_type}">selected</c:if> id="option7" value="2" >--网球--</option>
					</select>
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
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
		    <div class="col-md-12">
		    <shiro:hasPermission name="activity:create">
		      <div class="btn-group-sm pull-right mtb10">
		        <a class="btn btn-sm btn-primary" href="${ctx }/activity/create" ><span class="glyphicon glyphicon-plus"></span> 新增活动</a>
		      </div>
		    </shiro:hasPermission>
		    </div>
	    </div><!-- /操作按钮组 -->
			<div class="row">
    	<div class="col-table col-md-12" >			
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>活动类型</th>
				<th>活动名称</th>
				<th>活动地点</th>
				<th>推送类型</th>
				<th>推送状态</th>
				<th>推送时间</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${data.content}" var="activity" varStatus="stat">
				<tr>
					<td style="text-align: center;">${stat.count }</td>
					<td style="text-align: center;">
						<c:if test="${'3' == activity.type}">篮球</c:if>
						<c:if test="${'6' == activity.type}">足球</c:if>
						<c:if test="${'1' == activity.type}">羽毛球</c:if>
						<c:if test="${'7' == activity.type}">台球</c:if>
						<c:if test="${'8' == activity.type}">保龄球</c:if>
						<c:if test="${'5' == activity.type}">高尔夫</c:if>
						<c:if test="${'4' == activity.type}">乒乓球</c:if>
						<c:if test="${'2' == activity.type}">网球</c:if>
					</td>
					<td style="text-align: center;">${activity.name}</td>
					<td style="text-align: center;">${activity.address}</td>
					<td style="text-align: center;">
						<c:if test="${'0' == activity.isPush}">即时推送</c:if>
						<c:if test="${'1' == activity.isPush}">定时推送</c:if>
						<c:if test="${'2' == activity.isPush || empty activity.isPush}">未推送</c:if>
						<c:if test="${'3' == activity.isPush}">推送管理即时推送</c:if>
						<c:if test="${'4' == activity.isPush}">推送管理定时推送</c:if>
					</td>
					<td style="text-align: center;">
						<c:if test="${'0' == activity.pushState || empty activity.pushState}">未推送</c:if>
						<c:if test="${'1' == activity.pushState}">已推送</c:if>
					</td>
					<td style="text-align: center;"><fmt:formatDate value="${activity.factPushTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;">${activity.createUserName }</td>
					<td style="text-align: center;">
						<shiro:hasPermission name="activity:unstick">
							<c:if test="${activity.stick == 0 and maxStick >= 0 }">
								<a href="${ctx}/activity/unstick/${activity.id}" id="editLink-${activity.id}"> 置底</a>
							</c:if>
						</shiro:hasPermission>
						<shiro:hasPermission name="activity:stick">
							<c:if test="${activity.stick != 0}">
								<a href="${ctx}/activity/stick/${activity.id}" id="editLink-${activity.id}"> 置顶</a>
							</c:if>
						</shiro:hasPermission>
					   	<span class="cutline"></span> 
					  	<a href="${ctx}/activity/view/${activity.id}" id="editLink-${activity.id}"> 查看</a>
					   	<span class="cutline"></span> 
					  	<shiro:hasPermission name="activity:delete">
					  		<a href="javascript:void(0)" data="${activity.name }" id="removeLink-${activity.id }" onclick="deleteById('${activity.id}')"> 删除</a>
					  	</shiro:hasPermission>
					  	<span class="cutline"></span>
					  	<c:if test="${activity.stick != 0}">
					  		<a href="${ctx}/activity/moveUp/${activity.id }" id="editLink-${activity.id}" > 上移</a>
					  	</c:if>
					  	<span class="cutline"></span>
					  	<c:if test="${maxStick != activity.stick and maxStick != -1 }">
					  		<a href="${ctx}/activity/moveDown/${activity.id }" id="editLink-${activity.id}" > 下移</a>
					  	</c:if>
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
	  menu.active('#activity-man');
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
	  
	function deleteById(id) {
	  bootbox.confirm('您确定要删除 [' + $('#removeLink-' + id).attr('data') + '] 吗？', function(result) {
	    if(result) {
	    	$.ajax({
                cache: true,
                type: "POST",
                url:'${ctx}/activity/delete/' + id,
                data:{},
                async: false,
                error: function(request) {
                	common.showMessage("删除活动失败！");
                },
                success: function(data) {
                	data = eval("("+data+")");
                	if(!data.result || data.result != 'success'){
                		common.showMessage("删除活动失败！");
                	}else{
                		common.showMessage("删除活动成功！");
                		setTimeout(function(){
		                	var $form = $('#activityForm');
		          	        $form[0].submit();
                		},1000);
                	}
                }
            });
	    }
	  });
	  return false;
	}
</script>
</body>
</html>