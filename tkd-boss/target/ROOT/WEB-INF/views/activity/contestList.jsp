<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>赛事活动管理</title>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 赛事活动管理</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
	    <div class="row"><!-- 操作按钮组 -->
		 <div class="col-md-12 text-right">
		      <div class="btn-group-sm pull-right mtb10">
		       <a class="btn btn-primary" href="${ctx}/activity/contestForm?action=add" ><span class="glyphicon glyphicon-plus"></span> 赛事活动上传</a>
		      </div>
		    </div>
	    </div><!-- /操作按钮组 -->
				
		<div class="row">
    	<div class="col-table col-md-12" >		
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th class="text-center">序号</th>
				<th>标题</th>
				<th>logo</th>
				<th style="width:50px;">url</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
			</thead>
			
			<tbody>
			
			<c:forEach items="${data.content}" var="contestVo" varStatus="stat">
				<tr>
					<td class="text-center">${stat.count }</td>
					<td><a href="${ctx}/activity/detailContest?id=${contestVo.id}&&action=view">${contestVo.title }</td>
					<td>
			         	<img alt="" src="${contestVo.logo }" height="100" onclick="getOriginal('${contestVo.logo }')">
	         		</td>
					<td style="width:5%; white-space:normal">${contestVo.url}</td>
					<td>${contestVo.sort }</td>
					<td style="width:350px;vertical-align:middle">
						<span class="cutline"></span>
					  	<a href="${ctx}/activity/detailContest?id=${contestVo.id}&&action=edit">  修改 &nbsp</a>
					   	<span class="cutline"></span>
					   	<shiro:hasPermission name="contest:delete">
					  		<a href="#" data="${contestVo.id }"  onclick="deleteById('${contestVo.id}')"> 删除</a>
					  	</shiro:hasPermission> 
					</td>
				</tr>
			</c:forEach>
			
			</tbody>		
		</table>
		</div><!-- end col-table -->
		</div><!-- end row -->
		
		<tags:pagination page="${data}" />

    <form id="actionForm" action="" method="post">
       <input type="hidden" id="id" name="id">
    </form>
    
  </div><!-- /右侧主体内容 -->

</div>
	<img alt="" src="" id="originalPicture" onclick="closeLogin()"
		style="display:none; POSITION:absolute; left:50%; top:40%; width:600px; height:400px; margin-left:-300px; margin-top:-200px; border:10px solid #EDEDED; background-color:#FFFFFF; text-align:center"><br>
<script type="text/javascript">
$(function() {
	  menu.active('#contest-man');
	  $('#adminFooter').hide();
	  
	  var typeValue = '${param.search_LIKE_type}';
	  if(typeValue){
		$("select[name=search_LIKE_type] option[value="+typeValue+"]").attr("selected","selected");
	  }
	  
	  $('#btnGroup :button').each(function(i){
		  var _this = $(this);
		  _this.removeClass("btn-success");
		  _this.click(function(){
				 $('#btnGroup :button').each(function(i){
					 $(this).removeClass("btn-success");
				 });
				 _this.addClass("btn-success");
				$('#search_EQ_position').val(_this.val());
				$('#searchForm').submit();
			});
	  });
	  
	  var position = '${param.search_EQ_position}';
	  if(position > 0){$("#btn"+position).addClass("btn-success")}else{$("#btn0").addClass("btn-success")}
});
 
 // 删除
function deleteById(id) {
	  var $form = $('#actionForm');
	  $form.attr('action', '${ctx}/activity/deleteContest/' + id);
	  bootbox.confirm('您确定要删除吗？', function(result) {
	    if(result) {
	      $form[0].submit();
	    }
	  });
	  return false;
}


// 预览图片
function getOriginal(url){
	if(url.substring(url.length-1,url.length)=="/"){
		document.getElementById("originalPicture").src=url.substring(0,url.length-1);
	}else{
		document.getElementById("originalPicture").src=url.substring(0,url.length);
	}
		document.getElementById("originalPicture").style.display="";
	}
 
// 关闭预览
function closeLogin(){
	document.getElementById("originalPicture").style.display="none";
}
</script>

</body>
</html>