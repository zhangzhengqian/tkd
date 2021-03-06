<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>禁词管理</title>
	<style type="text/css">
		#title div{margin-left: auto;margin-right: auto;}
		.blank {clear: both;height: 10px;line-height: 10px;visibility: hidden;}
		.img_close{position: relative;top: -110px;right: -115px;cursor: pointer;font-size: 25px;background-color: #FF6F00;height: 20px;width: 20px;border-radius: 50%;line-height: 20px;}
	</style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 禁词管理</li>
        <li>禁词管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
  <h3>导入禁词</h3>
  <hr>
  <div style="margin-left: 10%;">
  <form id="inputForm" action="${ctx}/badWords/uploadWords" method="post" class="form-horizontal"  enctype="multipart/form-data">
	  <fieldset>
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>txt文件（UTF-8格式）:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="file" class="form-control" name="myfile" id="myfile" value=""  />
	       </div>
		</fieldset>
	</form>
	</div>
	<div class="form-group">
	 	<hr>
		<div class="col-md-offset-3 col-md-2">
		   <a class="btn btn-default btn-block" href="javascript:history.go(-1)"><span class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>
		<div class="col-md-2">
		  <button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 导入</button>
		</div>
	</div>
	
</div>
</div><!-- end row -->	
</div>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	menu.active('#badwords-man');
	$('#adminFooter').hide();
     $("#submit_btn").click(function(){
     	$("#inputForm").submit();
	 });
});
	
</script>
</body>
</html>