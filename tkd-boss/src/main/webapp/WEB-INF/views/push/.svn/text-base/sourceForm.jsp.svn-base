<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script type="text/javascript"src="${ctx}/static/ueditor/ueditor.config.js"></script>  
<script type="text/javascript"src="${ctx}/static/ueditor/ueditor.all.js"></script>  
<form id="form1" action="${ctx}/push/saveForm" method="post" class="form-horizontal">
    <zy:token/>
    <input type="hidden" name="id" value="${push.id}"/>
    <input type="hidden" name="groupId" value="${push.groupId}"/>

	<fieldset>
		<!-- <legend>
			<small>素材信息</small>
		</legend> -->

		<div class="form-group form-group-sm">
			<label for="orgName" class="col-md-3 control-label"><span class="text-red">* </span>标题:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="title" name="title" value="${push.title}" maxlength="15"  />
			</div>
		</div>

		<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red">*</span>封面:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="file" class="form-control" id="icon_upload_photo" name="icon_upload_photo" multiple="false" />
			    	<input type="hidden" id="image" name="image" value="${push.image}"/>
			    	<img alt="" style="<c:if test='${!empty push.image}'>width:300px;height:128px;</c:if>" src="${push.image}" id="icon_img_push" >
			    </div>
		</div>
		
		<div class="form-group form-group-sm">
	         	<label for="content" class="col-md-3 control-label"><span class="text-red">*</span>正文:</label>
	         	<div class="col-md-6 has-feedback">
	             	<script id="myEditor" name="content"  type="text/plain">${push.content}</script>
	         	</div>
	    </div>

		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span></label>
			<div class="col-md-6 has-feedback">
					<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
					&nbsp;&nbsp;
					<a class="label label-default" id="account" onclick="accountSet();">原文链接</a>
			</div>
	    </div>
		
		<div id="accountGroup" style="display:none">
			<div class="form-group form-group-sm">
				<label for="url" class="col-md-3 control-label"><span class="text-red"> </span></label>
				<div class="col-md-6 has-feedback">
					<input type="text" class="form-control" id="url" name="url" value="${push.url}" placeholder="" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-3 col-md-2">
				 <a class="btn btn-default btn-block" href="${ctx }/push"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary btn-block" id="submit_btn">
					<span class="glyphicon glyphicon-ok"></span> 保存
				</button>
			</div>
			
			<c:if test="${!empty push.id }">
			<div class="col-md-2">
				<button type="button" class="btn btn-warning btn-block" id="del_btn">
					<span class="glyphicon glyphicon-floppy-remove"></span> 删除
				</button>
			</div>
			</c:if>
		</div>

	</fieldset>

</form>



<script type="text/javascript" >
$(function() {
$('#form1').validate({
	rules: {
	},
	messages: {
	}
});

var um = UE.getEditor('myEditor',{
	initialFrameWidth:'620',
	initialFrameHeight:'500',
	elementPathEnabled:false,
	wordCount:false,
	toolbars: [[
	             'fullscreen', 'source', '|', 'undo', 'redo', '|',
	             'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',  'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	             'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	             'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	             'directionalityltr', 'directionalityrtl', 'indent', '|',
	             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase',
	             '|','simpleupload','insertimage','|','preview'
	         ]]
});  

var ids = ["icon_upload_photo"];
$.each(ids,function(n,value) {  
			$("#"+value).uploadify({
				fileObjName   : 'upfile',
				buttonText    : '选择图片',
				height        : 40,
				multi         :false,
				fileSizeLimit : 2*1024,
	            fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
				swf: '${ctx}/static/uploadify/uploadify.swf',
			    uploader: '${ctx}/uploader;jsessionid='+'${pageContext.session.id}',
				width: 120,
			    onUploadSuccess : function(file, data, response) {
					data = JSON.parse(data);
					if(data.success==true){
					    $("#"+value).parent().find("input[type=hidden]").val(data.url);
						$("#"+value).parent().find("img").attr('src',data.url);
						$("#"+value).parent().find("img").css({width:"100px",height:"100px"});
						$('#photo').val(data.url);
					}
			       },onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {
			    	   this.queueData.errorMsg = '上传失败';
			       },onSelectError : function(file,errorCode,errorMsg) {
			    	   switch(errorCode){
			    	   case -110:
			    		   this.queueData.errorMsg = '请上传不超过2M的图片';
			    	   }
			       }
			});
});  


});

// 删除
$("#del_btn").click(function(){
	var pushId = $("#id").val();
	var msg = "您确定要删除？";
	  bootbox.confirm(msg, function(result) {
		    if(result) {
				var $form = $('#form1');
				$form.attr('action', '${ctx }/push/delete/'+pushId);
				$form[0].submit();
		    }
		  }) ;
})

function accountSet(){
	$('#account').toggleClass('label-primary');
	$('#accountGroup').toggle();
}
</script>