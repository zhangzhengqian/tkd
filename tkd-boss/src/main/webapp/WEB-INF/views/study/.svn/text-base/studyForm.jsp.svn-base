<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>运营管理</title>
	<style type="text/css">
		#allmap {width: 100%;height: 500px;margin:0;position:relative;}
		#golist {display: none;}
		@media (max-device-width: 780px){#golist{display: block !important;}}
		#title div{
			margin-left: auto;
			margin-right: auto;
		}
		.blank {
		    clear: both;
		    height: 10px;
		    line-height: 10px;
		    visibility: hidden;
		}
		.img_close {
			position: relative;
			top: -20px;
			right: -235px;
			cursor: pointer;
			font-size: 25px;
			background-color: #FF6F00;
			height: 20px;
			width: 20px;
			border-radius: 50%;
			line-height: 20px;
		}
	</style>
</head>
<body>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li>学习资料管理</li>
        <li class="active">
          <c:if test="${'create' eq action }"> 上传学习资料</c:if>
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  <form id="inputForm" action="${ctx}/study/save" method="post" class="form-horizontal">
  <input id="id" name="id " value="${id }" type="hidden"/>
  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>学习资料</small></legend>
	<div class="row">
       <div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传学习资料:</label>
	         <div class="col-md-6" name="statium_img" style="width: 135px;">
				<span id="photo1File" >选择文件</span>
				<c:set var="count" value="1"/>
			</div>
	         <span name="picSpan" style="color:red;line-height:30px;"><b>若文件过大 请等待文件名称的显示</b> </span> 
					<span name="picSpan" style="color:red;line-height:30px;"><b>（限制格式：doc,docx,xls,xlsx,pdf,zip 限制大小 10M）</b> </span>
	      </div>
	      <div class="form-group form-group-sm">
	       <label for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="col-md-6" name="studyFiles" id="studyFiles" >
	         	<c:if test="${param.action == 'create' }">
					<div >
						<input id="fileUrl1" name="fileUrl" type="hidden" />
						<input id="fileName1" name="fileName" type="hidden" />
						<input id="fileSize1" name="fileSize" type="hidden" />
						<input id="fileSuffix1" name="fileSuffix" type="hidden" />
						<div id="fileId1" style="hight:100px;width:230px"></div>
					</div>
					<div>
						<input id="fileUrl2" name="fileUrl" type="hidden" />
						<input id="fileName2" name="fileName" type="hidden" />
						<input id="fileSize2" name="fileSize" type="hidden" />
						<input id="fileSuffix2" name="fileSuffix" type="hidden" />
						<div id="fileId2" style="hight:100px;width:230px"></div>
					</div>
					<div >
						<input id="fileUrl3" name="fileUrl" type="hidden" />
						<input id="fileName3" name="fileName" type="hidden" />
						<input id="fileSize3" name="fileSize" type="hidden" />
						<input id="fileSuffix3" name="fileSuffix" type="hidden" />
						<div id="fileId3" style="hight:100px;width:230px"></div>
					</div>
					<div >
						<input id="fileUrl4" name="fileUrl" type="hidden" />
						<input id="fileName4" name="fileName" type="hidden" />
						<input id="fileSize4" name="fileSize" type="hidden" />
						<input id="fileSuffix4" name="fileSuffix" type="hidden" />
						<div id="fileId4" style="hight:100px;width:230px"></div>
					</div>
					<div >
						<input id="fileUrl5" name="fileUrl" type="hidden" />
						<input id="fileName5" name="fileName" type="hidden" />
						<input id="fileSize5" name="fileSize" type="hidden" />
						<input id="fileSuffix5" name="fileSuffix" type="hidden" />
						<div id="fileId5" style="hight:100px;width:230px"></div>
	           		</div>
	    		</c:if>
	         </div>
	      </div>
	</form>
</div>
</div><!-- end row -->	
		</fieldset>
		 <div class="form-group">
		 	<hr>
			<div class="col-md-offset-3 col-md-2">
				<button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 上传</button>
			</div>
			<div class="col-md-2">
				<a class="btn btn-default btn-block" href="${ctx}/study/list"><span class="glyphicon glyphicon-remove"></span> 取消上传</a>			  
			</div>
		</div>
  </div>
</div>
<!-- 加载webuloader上传 -->
<script type="text/javascript">
$(function() {
	  //menu.active('#study-man');
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  $('#adminFooter').hide();
	  
	  uploadMultipleWebUploader({'id':'photo1File','icon_img':'fileId','icon':'fileUrl','file_name':'fileName','limit':5,'size':'10MB','file_size':'fileSize','file_suffix':'fileSuffix'});  //

      
      $("#submit_btn").click(function(){
			$("#inputForm").submit();
	  });
	
});
	
	function uploadMultipleWebUploader(opt){
		var uploader= WebUploader.create({
		    // swf文件路径
		    swf: ctx+'/static/plugins/webuploader/Uploader.swf',
		    // 文件接收服务端。
		    server: ctx + '/uploadFile;JSESSIONID=<%=session.getId()%>',
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: {id:'#'+opt.id,innerHtml:'选择文件'},
		    fileNumLimit:opt.limit,
		    fileSingleSizeLimit:parseInt(opt.size)*1024*1024,
		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    //resize: false
		    // 只允许选择图片文件。
		    accept:{
			    title: 'fileType',
			    extensions: 'doc,docx,xls,xlsx,pdf,zip',
			    mimeTypes: 'application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/pdf'
			},
		    auto:true
		});
		
		uploader.on('filesQueued',function(files){
			console.info(files);
		});
		
		uploader.on('error', function(error){
			if (error=='F_EXCEED_SIZE'){
				alert ('上传文件不能大于'+opt.size+'！请重新选择！');
			}else if (error=='Q_TYPE_DENIED'){
				alert ('只能上传doc,docx,xls,xlsx,pdf,zip格式的文件');
			}/* else if (error=='Q_EXCEED_NUM_LIMIT'){
				alert ('最多只能选择'+opt.limit+'个文件');
			} */
		});
		uploader.on('uploadError',function(file,reason){
			alert ('文件上传失败，请文件上传');
		});
		
		uploader.on('uploadSuccess',function(file,response){	
			if(response.success==true){
				$("input[name="+opt.icon+"]").each(function(i){
					var files = $(this).val();
					if(!files){
						var index = i+1;
						//$('#'+ icon_img + index).attr('src',data.fileName).attr({"height":"100","width":"130"});
						$('#'+ opt.icon_img + index).html(file.name);
						$('#'+ opt.icon + index ).val(response.url);
						$('#'+ opt.file_name + index).val(file.name);
						$('#'+ opt.file_size + index).val(response.fileSize);
						$('#'+ opt.file_suffix + index).val(response.suffixName);
						$('#'+ opt.icon_img + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
						return false;
					}
				});
			}
		});
	}	
</script>
</body>
</html>