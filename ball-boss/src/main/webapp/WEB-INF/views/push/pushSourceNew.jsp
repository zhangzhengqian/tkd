<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>素材管理</title>
	<link href="${ctx }/static/css/pushSourceNewStyle.css" rel="stylesheet">
	<style type="text/css">
		.label2{
		text-align: left; 
		font-weight: normal;
		}
	</style>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 素材管理</li>
        <li>素材管理</li>
        <li class="active">
          查看素材
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>素材信息</small></legend>
<div class="row">

	<div class="col-sm-2">
		<div class="isbody">
			<input type="hidden" id="mainId" name="mainId" value="${push.id }">
		    <div class="isimg"><img src="${push.image }"></div>
		    <div class="imginfo">${push.title }</div>
		    <div class="newlist" id="newlist">
		    	<c:if test="${!empty pushC }">
			    	<c:forEach items="${pushC }" var="cPush">
				    	<div class="newinfo" value="${cPush.id }">
				            <div class="newinfo1">${cPush.title }</div>
				            <div class="newinfo2"><img src="${cPush.image }"></div>
				        </div>
			    	</c:forEach>
		    	</c:if>
		        
		    </div>
		    <div class="htmlpush" id="htmlpush"><img src="${ctx}/static/img/pushAdd.png"></div>
		</div>
	</div>
	
	
	<div class="col-sm-10" id="func-form">

	</div>
	<input type="hidden" id="pid" name="pid" value="${push.id }">
	<input type="hidden" id="gid" name="gid" value="${push.groupId }">
	<input type="hidden" id="currentId" name="currentId" value="${currentId }">
</div><!-- end row -->	
		 
		</fieldset>
		
  </div>
	
</div>
<script src="${ctx}/static/js/zepto.min.js"></script>
<script type="text/javascript">
$(function() {
	menu.active('#push-man');
	$('#adminFooter').hide();
	
	$('#func-form').empty();
	var url = ctx + '/push/sourceForm?plain';
	var pid = $('#currentId').val();
	$('#func-form').load(url, {id:pid});
	
	 /* $('#newlist').delegate('.newinfo', 'mouseenter', function () {
         //alert(1);
         $(this).find('.backccc').show();
     })
     $('#newlist').delegate('.newinfo .isshanchu', 'click', function () {
         alert(2);
         $(this).parent().parent().remove();
     })
     $('#newlist').delegate('.newinfo', 'mouseout', function () {
         $(this).find('.backccc').hide();
         //$(this).
         //alert(2);
     }) */

     $('#htmlpush').on('click', function () {
         var ishtml = '<div class="newinfo">';
         ishtml += '<div class="newinfo1">标题</div>';
         ishtml += '<div class="newinfo2"><img src="${ctx}/static/img/suoluetu.png"></div>';
         $('#newlist').append(ishtml);
         
	     $('#func-form').empty();
	 	 var url = ctx + '/push/sourceForm?plain';
	 	 var gId = $('#gid').val();
	 	 $('#func-form').load(url, {groupId:gId});
     })
     
    $('#newlist').delegate('.newinfo', 'click', function () {
    	 var id = $(this).attr('value');
    	 $('#func-form').empty();
	 	 var url = ctx + '/push/sourceForm?plain';
	 	 $('#func-form').load(url, {id:id});
     }) 
     
     $('.isimg').click(function(){
    	 var id = $("#mainId").val();
    	 $('#func-form').empty();
	 	 var url = ctx + '/push/sourceForm?plain';
	 	 $('#func-form').load(url, {id:id});
     })
     
});
	
</script>
</body>
</html>
