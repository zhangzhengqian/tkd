<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>学习资料管理</title>
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
        <li class="active">
          <c:if test="${'create' eq action }"> 上传学习资料</c:if>
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  <form id="inputForm" action="${ctx}/studyFile/save" method="post" class="form-horizontal">
  <input id="id" name="id " value="${id }" type="hidden"/>
  <div class="panel-body"><!-- 右侧主体内容 -->
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
					<div >
						<input id="fileUrl1" name="fileUrl" type="hidden" class="icon" />
						<input id="fileName1" name="fileName" type="hidden" />
						<input id="fileSize1" name="fileSize" type="hidden" />
						<input id="fileSuffix1" name="fileSuffix" type="hidden" />
						<div id="fileId1" style="hight:100px;width:230px"></div>
					</div>
					<div>
						<input id="fileUrl2" name="fileUrl" type="hidden" class="icon" />
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
				<a class="btn btn-default btn-block" href="${ctx}/studyFile/list"><span class="glyphicon glyphicon-remove"></span> 取消上传</a>			  
			</div>
		</div>
  </div>
</div>
<script type="text/javascript">
$(function() {
	// 样式
	$('#study-man').addClass("active");
	$('#STUDY').addClass("active");

	// select状态
	// 教练类型
	var coachType = '${param.search_EQ_type }';
	if(coachType){
		$("#search_EQ_type option[value="+coachType+"]").attr("selected","selected");
	}
});

$(function() {
	
	uploadMultipleWebUploader({'id':'photo1File','icon_img':'fileId','icon':'fileUrl','file_name':'fileName','limit':5,'size':'10MB','file_size':'fileSize','file_suffix':'fileSuffix'});  //
	
	function uploadMultipleWebUploader(opt){
		var uploader= WebUploader.create({
		    // swf文件路径
		    swf: ctx+'/static/lib/plugins/webuploader/Uploader.swf',
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
	
	/* multipleUpload({'id':'photo1File','icon_img':'fileId','icon':'fileUrl','file_name':'fileName','limit':5,'size':'2MB','file_size':'fileSize','file_suffix':'fileSuffix'}); */  
	function multipleUpload(option){
		var id = option.id || "icon_upload";
		var height = option.height|| 40;
		var width = option.width || 120;
		var icon_img = option.icon_img || "icon_img";
		var icon = option.icon || "icon";
		var file_name = option.file_name || "file_name";
		var limit = option.limit || 1;
		var size = option.size || 2048;
		var file_size = option.file_size || "file_size";
		var file_suffix = option.file_suffix || "file_suffix";
		$("#"+id).uploadify({
	        //文件提交到 controller 里的文件对象的 key 
			fileObjName   : 'upfile',
			queueSizeLimit: limit,
			fileSizeLimit : size,
			multi         :true,
		    //按钮名称	
			buttonText    : '选择文件',
			height        : 30,
			swf           : ctx + '/static/uploadify/uploadify.swf',
		    //提交到指定的 controller,写死即可，已封装
		    uploader      : ctx + '/uploadFile;JSESSIONID=<%=session.getId()%>',
			width         : 100,
			fileTypeExts:'*.docx;*.xlsx;*.pdf;*.zip',
			fileName:encodeURIComponent(file_name),
			overrideEvents : [ 'onDialogClose','onSelectError' ],
			//上传成功后回调此函数
		    onUploadSuccess : function(file, data, response) {
		        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if(data.success==true){
					$("input[name="+icon+"]").each(function(i){
						var files = $(this).val();
						if(!files){
							var index = i+1;
							//$('#'+ icon_img + index).attr('src',data.fileName).attr({"height":"100","width":"130"});
							$('#'+ icon_img + index).html(file.name);
							$('#'+ icon + index ).val(data.url);
							$('#'+ file_name + index).val(file.name);
							$('#'+ file_size + index).val(data.fileSize);
							$('#'+ file_suffix + index).val(data.suffixName);
							$('#'+ icon_img + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
							return false;
						}
					});
				}
		    },
		   onSelectError : uploadify_onSelectError
		});
	}
	
	
	
	var uploadify_onSelectError = function(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        myAlert(msgText);	
    };
});
$(function() {
	$("#studyFiles").on("click",
			'.img_close',
			function() {
				$(this).parent().find("input").val("");
				/* $(this).parent().find("img").attr("src", "").attr("height",
						"0").attr("width", "0"); */
				$(this).parent().find("div").html("");
				$(this).remove();
			});
				
      
      $("#submit_btn").click(function(){
			$("#inputForm").submit();
	  });

	
});
	
 	function freeFun(val){
		if(val == 0){
			$("#price").hide();
			$("#price").rules("remove");
		}else{
			$("#price").show();
			$('#price').rules( "add",{required: true,decimalMax2: 2});
		}
	}
	
</script>
</body>
</html>