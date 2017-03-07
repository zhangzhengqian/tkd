<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>教练/陪练管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 教练/陪练管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
  	
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  
		      <form class="form-inline" action="${ctx}/admin/coach/list" method="post">
			      <div class="form-group form-group-sm">
			      	<label class="col-md-3 control-label" style="width:100px;line-height:30px;"></span>运动类型:</label>
					    <div class="col-md-6 has-feedback form-inline">
<!-- 							<select class="form-control" name="search_LIKE_sportType" id="search_LIKE_sportType"> -->
							<select class="form-control" name="search_LIKE_tags" id="search_LIKE_tags">		
								<option  value="" >--请选择--</option>
								<option  value="篮球" >--篮球--</option>
								<option  value="足球" >--足球--</option>
								<option  value="羽毛球" >--羽毛球--</option>
								<option  value="台球" >--台球--</option>
								<option  value="保龄球" >--保龄球--</option>
								<option  value="高尔夫" >--高尔夫--</option>
								<option  value="乒乓球" >--乒乓球--</option>
								<option  value="网球" >--网球--</option>
							</select>
					  	</div>
				 </div>	
		         <div class="form-group">
		        	 <label class="col-md-3 control-label" style="width:80px;line-height:30px;"></span>姓名:</label>
			         <input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name}" placeholder="按姓名查询">
		         </div>
		         <div class="form-group">
		        	 <label class="col-md-3 control-label" style="width:100px;line-height:30px;"></span>手机号:</label>
			         <input type="text" class="form-control input-sm" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="按手机号查询">
		         </div>
		         <div class="form-group form-group-sm">
		            <label class="col-md-3 control-label" style="width:80px;line-height:30px;"></span>性别:</label>
				    <div class="col-md-6 has-feedback form-inline">
						<select class="form-control" name="search_LIKE_sex" id="search_LIKE_sex">
							<option id="option0" value="" >--请选择--</option>
							<option id="option0" value="男" <c:if test="${param.search_LIKE_sex == '男'}">selected</c:if> >--男性--</option>
							<option id="option1" value="女" <c:if test="${param.search_LIKE_sex == '女'}">selected</c:if> >--女性--</option>
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
		        <a class="btn btn-primary" href="${ctx}/admin/coach/sign" ><span class="glyphicon glyphicon-plus"></span> 录入教练</a>
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
				<th>证件照</th>
				<th>类型</th>
				<th>姓名</th>
				<th>性别</th>
				<th>手机号</th>
				<th>上传信息</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="coachVo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count}</td>
					<td>
						<c:if test="${not empty coachVo.ssoUser.certList }">
			         		<c:forEach items="${fn:split(coachVo.ssoUser.certList,',') }" var="itm">
			         			<img alt="" src="${itm }" height="100" onclick="getOriginal('${itm }')">
			         		</c:forEach> 
		         		</c:if>
	         		</td>
					<td>
						${coachVo.ssoUser.tags}
<%-- 						<c:if test="${coachVo.sportType == 0}" >
							篮球
						</c:if>
						<c:if test="${coachVo.sportType == 1}" >
							足球
						</c:if>
						<c:if test="${coachVo.sportType == 2}" >
							羽毛球
						</c:if>
						<c:if test="${coachVo.sportType == 3}" >
							台球
						</c:if>
						<c:if test="${coachVo.sportType == 4}" >
							保龄球
						</c:if>
						<c:if test="${coachVo.sportType == 5}" >
							高尔夫
						</c:if>
						<c:if test="${coachVo.sportType == 6}" >
							乒乓球
						</c:if>
						<c:if test="${coachVo.sportType == 7}" >
							网球
						</c:if> --%>
					</td>
					<td>${coachVo.ssoUser.name}</td>
					<td>${coachVo.ssoUser.sex}</td>
					<td>${coachVo.ssoUser.phone}</td>
					<td>${coachVo.loginName}</td>
					<td>${fn:substring(coachVo.ssoUser.updateTime,0,fn:length(coachVo.ssoUser.updateTime)-2)}</td>
					<td>
					  <a href="${ctx}/admin/coach/updateCoach/${coachVo.ssoUser.id}" id="editLink-${coachVo.ssoUser.id}"> 修改</a>
					  <span class="cutline"></span> 
					  <a href="#" data="${coachVo.ssoUser.id}" id="removeLink-${coachVo.ssoUser.id}" onclick="deleteById('${coachVo.ssoUser.id}')"> 删除</a>
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
	<img alt="" src="" id="originalPicture" onclick="closeLogin()"
		style="display:none; POSITION:absolute; left:50%; top:40%; width:600px; height:400px; margin-left:-300px; margin-top:-200px; border:10px solid #EDEDED; background-color:#FFFFFF; text-align:center"><br>
<script type="text/javascript">
$(function() {
	  menu.active('#coach-manager-form');
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
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/admin/coach/deleteCoach/' + id);
	  //bootbox.setDefaults({size:'large'});
	  bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
	}

	function getOriginal(url){
		if(url.substring(url.length-1,url.length)=="/"){
			document.getElementById("originalPicture").src=url.substring(0,url.length-1)+"bigPicture";
		}else{
			document.getElementById("originalPicture").src=url.substring(0,url.length)+"bigPicture";
		}
		document.getElementById("originalPicture").style.display="";
	}
	function closeLogin(){
		document.getElementById("originalPicture").style.display="none";
	}
</script>

</body>
</html>