<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>微信公众平台维护</title>
	<link rel="stylesheet" href="${ctx}/static/js/jquery/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
	<style type="text/css">
		.tree-container{
			min-height:450px;
			border: #efefef 1px solid;
		}
		#img-content{
			display: none;
			margin:20px 0px;
		}
		#file-content{
			display: none;
			margin:20px 0px;
		}
	</style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 女子白金赛赛程图片上传</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
	<div class="row">
    <div class="col-table col-md-3" >
		<input id="h5File" type="file" multiple="false" />
	</div>
  </div><!-- /右侧主体内容 -->
</div>
<script type="text/javascript">
var pathFlag = true;
var path = "/home/appusr/www/WXGZPT";
var name = "WXGZPT";
$(function() {
	menu.active('#html5-man');
	upload({'id':'h5File'});
});
function upload(option){
	var id = option.id || "icon_upload";
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
	    //按钮名称
		buttonText    : '选择文件',
		height        : 30,
		multi         :false,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/html5/uploadSchedule',
		width         : 100,
		formData	  : {"path":path,"pathFlag":pathFlag},
		fileTypeExts:'*',
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
			data = JSON.parse(data);
			if(data.success==true){
				window.location.reload();
	       }
	    }  
	});
}

</script>
</body>
</html>