<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<style type="text/css">
		#allmap {width: 100%;height: 500px;margin:0;position:relative;}
		#golist {display: none;}
		@media (max-device-width: 780px){#golist{display: block !important;}}
	.img_close{
		position: relative;
		top: -110px;
		right: -115px;
		cursor: pointer;
		font-size: 25px;
		background-color: #FF6F00;
		height: 20px;
		width: 20px;
		border-radius: 50%;
		line-height: 20px;	
	}
	</style>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span><a href="${ctx }/statium/"> 新闻列表 </a></li>
	        <li class="active">新闻信息</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <c:choose>
  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
  		<c:set var="disable" value="false"/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly'"/>
  	</c:otherwise>
  </c:choose>  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<h3>新闻信息</h3>
	<hr>
	<form id="inputForm" action="${ctx}/news/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${news.id}" />
		<fieldset>

	    <div class="form-group form-group-sm">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>标题:</label>
	       <div class="col-md-6 has-feedback">
	         <input ${readonly } type="text" class="form-control" id="title" name="title" value="${news.title }" placeholder="请输入标题" />
	       </div>
	    </div>
           
	    <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>URL:</label>
	         <div class="col-md-6 has-feedback ">
	           <input ${readonly } type="text" class="form-control" id="url" name="url" value="${news.url}" />
	         </div>
	    </div>
	    
		<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传图片:</label>
	         <div class="col-md-6" name="statium_img" style="width: 135px;">
	         	 <input id="photo1File" type="file" multiple="false" />
	         	<c:set var="count" value="1"/>
	         </div>
	         <span name="picSpan" style="color:red;line-height:30px;">（标准尺寸：640px * 300px） </span>
	      </div>
	      <div class="form-group form-group-sm">
	       <label for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="col-md-6" name="statium_img"  >
	         	<c:set var="count" value="1"/>
	         	<c:if test="${not empty news.images}">
	         		<c:forEach items="${fn:split(news.images,'__') }" var="itm" varStatus="s">
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
	         	<c:if test="${empty news.images}">
	         		<c:forEach var="i" begin="1" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	
	         	<c:if test="${param.action == 'create' }">
					<div style="float:left;margin-right:10px;">
						<input id="photo1" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo2" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo3" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo4" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo5" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img5"/>
	           		</div>
	    		</c:if>
	         </div>
	      </div>
		
		<div class="form-group">
			<c:if test="${empty readonly }">
				<div class="col-md-offset-3 col-md-3">	
		    		<a href="${ctx }/news/list" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-3">
		    		<button type="submit" id="submit_btn" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
		</div>
		</fieldset>
	</form>
  </div>
</div>

<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/project_js.js"></script>
<script type="text/javascript">
function trim(str) {
	return str.replace(/(\s)/g, "");
}
$(function() {
	menu.active('#news-man');
	$("#myDlgBody_lg").dragmove();
	$("div[name = statium_img]").on("click",'.img_close',function(){
		$(this).parent().find("input").val("");
		$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
		$(this).remove();
	});
	
});

$(function() {
	$('#inputForm').validate({
		submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
			getData(function(data){
				app.disabled("#submit_btn");
				form.submit(); 
			});
		},
		rules: {
			"title" : {
				required: true,
			},
			"url" :{
				required:true,
			},
		},
		messages: {
		}
	});
	
});

$(function() {
	multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':5});  // 
});

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
		buttonText    : '选择图片',
		height        : 30,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
		width         : 100,
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
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
    
</script>