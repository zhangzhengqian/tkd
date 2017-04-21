<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 视频集</li>
	        <li class="active">教学视频添加</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
  <div class="panel-body"><!-- 右侧主体内容 -->
	<form id="inputForm" action="${ctx}/videoItem/save" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
			<input type="hidden" id="id" name="id" value="${video.id }"/>
			<input type="hidden" id="parentId" name="parentId" value="${parentId }"/>
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="name" name="name" style="width:229px" value="${video.name }" />
			    </div>
			</div>
			<%--<div class="form-group form-group-sm" id="show">--%>
				<%--<label for="name" class="col-md-3 control-label"><span class="text-red"></span>视频时长:</label>--%>
				<%--<div class="col-md-6 has-feedback">--%>
					<%--<input type="text" class="form-control" id="duration" name="duration" style="width:229px" value="${carousel.duration }" placeholder="例如 7:20"/>--%>
				<%--</div>--%>
			<%--</div>--%>
			
			<div class="form-group form-group-sm">
				<label for="img" class="col-md-3 control-label"><span class="text-red"></span>图片:</label>
				<div id="title" class="col-md-1 has-feedback">
					<img id="icon_img" src="${video.image }" style="width:320px;height:156px;display:block;margin-left: auto;margin-right: auto;"/>
				</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="ff" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback" style="width:140px;">
			    	<input type="file" class="form-control" id="icon_upload" name="icon_upload" multiple="false" />
			    	
			    	<input type="hidden" id="image" name="image" value="${video.image }"/>
			    </div>
	         	<span name="picSpan" style="color:red;line-height:40px;">（标准尺寸：320px * 156px） </span>
			</div>
			
			

			<div class="form-group form-group-sm">
				<label for="url" class="col-md-3 control-label"><span class="text-red"></span>URL:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="url" name="url" style="width:650px" value="${video.url }" />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>排序值:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="输入正整数" type="text" class="form-control" id="sort" name="sort" style="width:229px" value="${video.sort }" />
			    </div>
			</div>			

      	 <div class="form-group form-group-sm">
		  	<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/videoItem/videoItemList/${parentId}"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>         
	      	<div class="col-md-2">
		    	<button id = "submit_button" type="button" class="btn btn-primary btn-block" > 提交 </button>
			</div>
		  </div>			 		 					
		</fieldset>
	</form>
  </div>
  <div class="panel-footer">
	<div class="row text-right">
		<div class="col-sm-12">
		</div>
	</div>	
  </div>	
</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<img alt="" src="" id="originalPicture" onclick="closeLogin()"
		style="display:none; POSITION:absolute; left:50%; top:40%; width:600px; height:400px; margin-left:-300px; margin-top:-200px; border:10px solid #EDEDED; background-color:#FFFFFF; text-align:center"><br>

<script type="text/javascript">
$(function(){
	// 菜单栏显示底色（选中）
	menu.active('#video-man');

	// 控件校验
 	$('#inputForm').validate({
		rules: {
			// 名称
			"name":{
				required:true
			},
			// 排序
			"sort": {
				required: true,
				digits: true,
				range:[0,999],
			}
		},
		messages: {
			sort: {
				digits:"必须为正整数",
				range: "请输入一个介于 {0} 和 {999} 之间的值"
			}
		}
	}); 
	
	// 保存
 	$("#submit_button").click(function(){
		$("#inputForm").submit();
	}); 
	
 	// 低栏隐藏
	$("#adminFooter").hide();
 	
});


$("#icon_upload").uploadify({
    //文件提交到 controller 里的文件对象的 key 
	fileObjName   : 'upfile',
    //按钮名称
	buttonText    : '选择图片',
	height        : 40,
	swf           : '${ctx}/static/uploadify/uploadify.swf',
    //提交到指定的 controller,写死即可，已封装
    uploader      : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
	width         : 120,
	//上传成功后回调此函数
    onUploadSuccess : function(file, data, response) {
    //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
		data = JSON.parse(data);
		if(data.success==true){
			$('#icon_img').attr('src',data.url);
			$('#image').val(data.url);
		}
       }
});

</script>