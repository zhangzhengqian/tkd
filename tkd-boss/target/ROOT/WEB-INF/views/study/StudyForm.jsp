<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<style type="text/css">
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<!-- 导航 -->
<%-- <%@include file="infoNav.jsp"%> --%>
<form id="statiumInfoForm" action="${ctx}/infoManage/save" method="post" class="form-horizontal" enctype="multipart/form-data">
	<div class="formTable">
		<input type="hidden" id="status" name="status">
		<input type="hidden" id="userId" name="userId" value="${userId }">
		<input type="hidden" id="userName" name="userName" value="${userName }">
		<ul>
			<li>
				<span class="title">学习资料：</span>
				<div class="col-md-6" name="fileName" style="width: 135px;">
	         	 	<input id="photo1File" type="file" multiple="false" />
	         		<c:set var="count" value="1"/>
	         	</div>
	         <span name="picSpan" style="color:red;line-height:30px;">（文件格式限制：。。。） </span>
	         </li>
	      <li>
		  <div class="form-group form-group-sm">
	       <label style="width:12%" for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="" name="statium_img"  >
	         	<%-- <c:if test="${not empty statium.photos}">
	         		<c:forEach items="${fn:split(statium.photos,'__') }" var="itm" varStatus="s">
	         			<c:if test="${s.last}">
	         				<c:set var="count" value="${s.index + 2}"/>
	         			</c:if>
	         			<div style="float:left;margin-right:10px;">
							<input id="photo${s.index + 1}" name="photo" value="${itm }" type="hidden" />
	         				<img alt="" src="${itm}" id="photo_img${s.index + 1}" height="100" width="130" />
	         				<div aria-hidden="true" class="img_close">&times;</div>
	         			</div>
	         		</c:forEach>
		         	<c:forEach var="i" begin="${count}" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	<c:if test="${empty statium.photos}">
	         		<c:forEach var="i" begin="1" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if> --%>
	         	
	         	<c:if test="${param.action == 'create' }">
					<div style="float:left;margin-right:10px;">
						<input id="fileUrl1" name="fileUrl" type="hidden" />
						<input id="fileName1" name="fileName" type="" />
						<img alt="" src="" id="file1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="fileUrl2" name="fileUrl" type="hidden" />
						<input id="fileName2" name="fileName" type="" />
						<img alt="" src="" id="file2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="fileUrl3" name="fileUrl" type="hidden" />
						<input id="fileName3" name="fileName" type="" />
						<img alt="" src="" id="file3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="fileUrl4" name="fileUrl" type="hidden" />
						<input id="fileName4" name="fileName" type="" />
						<img alt="" src="" id="file4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="fileUrl5" name="fileUrl" type="hidden" />
						<input id="fileName5" name="fileName" type="" />
						<img alt="" src="" id="file5"/>
	           		</div>
	    		</c:if>
	         </div>
	      </div>
		</li>
		</ul>
		<div class="formSubCenterDiv">
			<a href="javascript:history.go(-1)" class="saveBtnCenter">返回</a>
			<a class="saveBtnCenter" href="javascript:saveForm()">保存</a>
		</div>
	</div>
</form>

<script type="text/javascript"
		src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
<script type="text/javascript">
	$(function() {
		// 样式
		$('#info-man').addClass("active");
		$('#STATIUM_INFO').addClass("active");
		$("div[name = statium_img]").on("click",'.img_close',function(){
			$(this).parent().find("input").val("");
			$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
			$(this).remove();
		});
	});


	$(function() {
		//upload({'id':'logoFile','icon_img':'logo_img','icon':'logo'});  // 球馆LOGO

		multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':5});  //
	});

	/**
	 *	option.id            上传元素id
	 *	option.icon_img      显示图片id
	 *	option.icon          保存图片的url的id
	 *	option.width         显示图片的宽度
	 *	option.height        显示图片的高度
	 */

	function multipleUpload(option){
		var id = option.id || "icon_upload";
		var height = option.height|| 40;
		var width = option.width || 120;
		var icon_img = option.icon_img || "icon_img";
		var icon = option.icon || "icon";
		var limit = option.limit || 1;
		$("#"+id).uploadify({
			//文件提交到 controller 里的文件对象的 key
			fileObjName   : 'upfile',
			queueSizeLimit: limit,
			multi         :true,
			//按钮名称
			buttonText    : '选择文件',
			height        : 30,
			swf           : ctx + '/static/uploadify/uploadify.swf',
			//提交到指定的 controller,写死即可，已封装
			uploader      : ctx + '/uploadFile',
			width         : 100,
			//*.gif;*.jpg;*.jpeg;*.png;
			fileTypeExts:'*.txt;*.pdf;*.docx;*.xlsx;*.zip',
			overrideEvents : [ 'onDialogClose','onSelectError' ],
			//上传成功后回调此函数
			onUploadSuccess : function(file, data, response) {
				//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if(data.success==true){
					$("input[name="+icon+"]").each(function(i){
						var photo = $(this).val();
						if(!photo){
							var index = i+1;
							$('#'+ icon_img + index).attr('src',data.url).attr({"height":"100","width":"130"});
							$('#'+ icon + index ).val(data.url);
							$('#'+ icon_img + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
							return false;
						}
					});
				}
			},
			onSelectError : uploadify_onSelectError,
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

	// 表单提交
	function saveForm() {
		// 渠道
		$("#status").attr("value",0);
		$("#statiumInfoForm").submit();
	}

</script>

</body>
</html>