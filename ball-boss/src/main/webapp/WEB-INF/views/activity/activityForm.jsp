sa<%@ page contentType="text/html;charset=UTF-8" %>
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
	</style>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap-validation/validate.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap-validation/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>  
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
</head>
<body>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li>活动管理</li>
        <li class="active">
          <c:if test="${'create' eq action }"> 新建活动</c:if>
          <c:if test="${'update' eq action }"> 修改活动</c:if>
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  <form id="inputForm" action="${ctx}/activity/${action }" method="post" class="form-horizontal">
  <input id="id" name="id" value="${activity.id }" type="hidden"/>
  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>活动信息</small></legend>
<div class="row">
	<div class="col-sm-12">
		<div class="form-group form-group-sm">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span> 活动标题:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" id="name" name="name" class="form-control" placeholder="请填写活动标题" value="${activity.name }"
	       </div>
	    </div>  
	 </div>
	   
     <div class="form-group form-group-sm">
         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span> 活动地点:</label>
         <div id="div_areaCode" class="col-md-6 has-feedback form-inline">
         	<c:choose>
			  	<c:when test="${empty readonly }">
			  		<tags:zone id="areaCode" name="areaCode" value="${activity.areaCode }" disabled="${disable }" />
			  	</c:when>
			  	<c:otherwise>
					<tags:zonemap code="${activity.areaCode }" />
			  	</c:otherwise>
			</c:choose>
         </div>
      </div>
      
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">*</span>详细地址:</label>
	         <div class="col-md-6 has-feedback ">
	         		<input id="address" type="text" class="form-control" style="width:460px;" name="address" value="${activity.address}" placeholder="请填活动地点"/>
	         </div>
	    </div>
	           		
      <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">*</span>坐标:</label>
	         <div class="col-md-6 has-feedback form-inline">
				<div class="input-group">
					<input ${readonly } readOnly type="text" class="form-control" id="lnglat" name="lnglat"
						value='<c:if test="${action ne 'create' }">${activity.lng },${activity.lat }</c:if>' placeholder="经度,纬度" />
		         	<span class="input-group-btn">
		            <button id="coordinate" class="btn btn-primary btn-sm" type="button">坐标/地址 识取工具</button>
		          	</span>
		        </div>
        	 </div>
	    </div>
	    
	    <div class="form-group form-group-sm" style="display: none;" id="baiduMap">
	         <label for="" class="col-md-3 control-label"></label>
	         <div class="col-md-6 has-feedback form-inline">
	         	<div class="panel panel-default">
	         		<div class="panel-heading">
							<input type="text" class="form-control" id="keyword"/>
							<button type="button" class="btn btn-primary btn-sm" id="search"><span class="glyphicon glyphicon-search"></span> 搜 索</button>	         			
	         				<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	         		</div>
	         		<div class="panel-body">
				       	<div id="allmap"></div>
	         		</div>
	         	</div>
        	 </div>
	    </div>
      
      <div class="form-group form-group-sm">
            <label for="startTime" class="col-md-3 control-label"><span class="text-red">* </span> 活动时间:</label>
            <div class="col-md-6 has-feedback form-inline">
           		  <div class="input-group">
                  		<input type="text" id="startTime" name="startTime" placeholder="请输入活动开始时间" value="${activity.startTime}"
                  			<c:if test="${action eq 'update' }">disabled="disabled"</c:if>
                  			class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})"/>
 							<label for="startTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                    </div>
                  	至
                    <div class="input-group">
                  		<input type="text" id="endTime" name="endTime" placeholder="请输入活动结束时间" value="${activity.endTime}"
                  			<c:if test="${action eq 'update' }">disabled="disabled"</c:if>
                  			class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})"/>
							<label for="endTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                    </div>
            </div>
        </div>
      
       <div class="form-group form-group-sm">
         <label for="expiryType" class="col-md-3 control-label"><span class="text-red">* </span> 报名类型:</label>
         <div class="col-md-6 has-feedback form-inline">
	           	 <div class="btn-group" id="expiryTypeDiv" data-toggle="buttons">
					  <label class="btn btn-default<c:if test="${activity.expiryType == 0}"> active</c:if>">
					  		<input type="radio" name="expiryType" value="0" autocomplete="off"
					  			<c:if test='${activity.expiryType == 0}'>checked="checked"</c:if> >
					     	报名截止日期
					  </label>
					  <label class="btn btn-default<c:if test="${activity.expiryType == 1}"> active</c:if>">
					  		<input type="radio" name="expiryType" value="1"  autocomplete="off"
					  		<c:if test='${activity.expiryType == 1}'>checked="checked"</c:if> >
					      	报名截止时间
					  </label>
				</div>
				<input type="text" id="expiryDate1" name="expiryDate" class="form-control"
					style="<c:if test="${activity.expiryType != 0}">display: none;</c:if>" 
					disabled="<c:if test="${action == 'update'}">disabled</c:if>"
					value="${activity.expiryDate }" />
				<input type="text" id="expiryDate2" name="expiryDate" class="form-control"
					style="<c:if test="${activity.expiryType != 1}">display: none;</c:if>" 
					disabled="<c:if test="${action == 'update'}">disabled</c:if>"
					value="${activity.expiryDate }"/>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="isRepeat" class="col-md-3 control-label"><span class="text-red">* </span> 是否重复报名:</label>
         <div class="col-md-6 has-feedback form-inline">
			  <div class="btn-group" data-toggle="buttons">
				  <label class="btn btn-default<c:if test="${activity.isRepeat == 0}"> active</c:if>">
				      <input type="radio" name="isRepeat" value="0" <c:if test="${activity.isRepeat == 0}">checked="checked"</c:if> autocomplete="off">否
				  </label>
				  <label class="btn btn-default<c:if test="${activity.isRepeat == 1}"> active</c:if>">
				      <input type="radio" name="isRepeat" value="1" <c:if test="${activity.isRepeat == 1}">checked="checked"</c:if> autocomplete="off">是
				  </label>
			 </div>
         </div>
      </div>
      
       <div class="form-group form-group-sm">
		  <label for="icon_upload_logo" class="col-md-3 control-label"><span class="text-red">*</span> 主办方LOGO:</label>
	      <div class="col-md-6 has-feedback">
		    	<input type="file" class="form-control" id="icon_upload_logo" name="icon_upload_logo" multiple="false" />
		    	<input type="hidden" id="logo" name="logo" value="${activity.logo}"/>
		    	<img alt="" style="<c:if test='${!empty activity.logo}'>width:128px;height:128px;</c:if>" src="${activity.logo}" id="icon_img_logo" >
	     </div>
	  </div>
      
      <div class="form-group form-group-sm">
         <label for="undertake" class="col-md-3 control-label"><span class="text-red">* </span> 主办方:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" placeholder="请输入活动主办方名称"  id="undertake" name="undertake" value="${activity.undertake}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm form-inline">
         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span> 联系电话:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" placeholder="请输入联系电话"  id="tel" name="tel" value="${activity.tel}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="introduction" class="col-md-3 control-label"><span class="text-red">* </span> 主办方介绍:</label>
         <div class="col-md-6 has-feedback">
         	<script id="editor" name="introduction" type="text/plain">${activity.introduction }</script>
         </div>
      </div>
      
       <div class="form-group form-group-sm">
		  <label for="photo" class="col-md-3 control-label"><span class="text-red">*</span> 活动图片:</label>
	      <div class="col-md-6 has-feedback" style="width: 145px;">
		    	<input type="file" class="form-control" id="icon_upload_photo" name="icon_upload_photo" multiple="false" />
		    	<input type="hidden" id="photo" name="photo" value="${activity.photo}"/>
		    	<img alt="" style="<c:if test='${!empty activity.photo}'>width:128px;height:128px;</c:if>" src="${activity.photo}" id="icon_img_activity" >
	     </div>
	     <span name="picSpan" style="color:red;line-height:40px;">（标准尺寸：640px * 264px） </span>
	  </div>
      
      <div class="form-group form-group-sm">
         <label for="totalNumber" class="col-md-3 control-label"><span class="text-red">* </span> 活动人数:</label>
         <div class="col-md-6 has-feedback form-inline">
           <input type="number" min="1" class="form-control" style="padding-right:0px;" placeholder="请输入活动人数" id="totalNumber" name="totalNumber" value="${activity.totalNumber}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="againPassword" class="col-md-3 control-label"><span class="text-red">* </span>运动类型:</label>
         <div class="col-md-6 has-feedback form-inline">
		   <select class="form-control" id="type" name="type">
		        <option value="">请选择</option>	
		        <option <c:if test="${'3' == activity.type}">selected</c:if> id="option0" value="3">篮球</option>
				<option <c:if test="${'6' == activity.type}">selected</c:if> id="option1" value="6">足球</option>
				<option <c:if test="${'1' == activity.type}">selected</c:if> id="option2" value="1">羽毛球</option>
				<option <c:if test="${'7' == activity.type}">selected</c:if> id="option3" value="7">台球</option>
				<option <c:if test="${'8' == activity.type}">selected</c:if> id="option4" value="8">保龄球</option>
				<option <c:if test="${'5' == activity.type}">selected</c:if> id="option5" value="5">高尔夫</option>
				<option <c:if test="${'4' == activity.type}">selected</c:if> id="option6" value="4">乒乓球</option>
				<option <c:if test="${'2' == activity.type}">selected</c:if> id="option7" value="2">网球</option>
			</select>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="free" class="col-md-3 control-label"><span class="text-red">* </span> 报名金额:</label>
         <div class="col-md-6 has-feedback form-inline">
			  <div class="btn-group" data-toggle="buttons">
				  <label class="btn btn-default<c:if test="${activity.free == 0}"> active abc</c:if>" onclick="freeFun(0)">
				      <input type="radio" name="free" value="0" <c:if test="${activity.free == 0}">checked="checked"</c:if> autocomplete="off">免费活动
				  </label>
				  <label class="btn btn-default<c:if test="${activity.free == 1}"> active abc</c:if>" onclick="freeFun(1)">
				      <input type="radio" name="free" value="1" <c:if test="${activity.free == 1}">checked="checked"</c:if> autocomplete="off">收费活动
				  </label>
			 </div>
             <input id="price" name="price" type="text" style="<c:if test="${activity.free == null || activity.free == 0 }">display:none;</c:if>width:220px;"
             	value="${activity.price }" class="form-control" placeholder="例如50.00，最多保留2位小数"/>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="contentShare" class="col-md-3 control-label"><span class="text-red"> </span>内容分享:</label>
         <div class="col-md-6 has-feedback">
		 	  <input type="text" class="form-control"  placeholder="请输入分享内容" id="contentShare" name="contentShare" value="${activity.contentShare}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
		  <label for="icon_upload_imageShare" class="col-md-3 control-label"><span class="text-red"></span> 图片分享:</label>
	      <div class="col-md-6 has-feedback" style="width: 145px;">
		    	<input type="file" class="form-control" id="icon_upload_imageShare" name="icon_upload_imageShare" multiple="false" />
		    	<input type="hidden" id="imageShare" name="imageShare" value="${activity.imageShare}"/>
		    	<img alt="" style="<c:if test='${!empty activity.imageShare}'>width:128px;height:128px;</c:if>" src="${activity.imageShare}" id="icon_img_imageShare" >
	     </div>
	  </div>
      
      <div class="form-group form-group-sm">
         	<label for="briefdesc" class="col-md-3 control-label"><span class="text-red">*</span> 活动介绍:</label>
         	<div class="col-md-6 has-feedback">
             	<script id="myEditor" name="briefdesc" type="text/plain">${activity.briefdesc }</script>
         	</div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="push" class="col-md-3 control-label text-danger"style="font-size:18px"><span class="text-danger">! </span> 是否推送:</label>
         <div class="col-md-6 has-feedback form-inline">
	           	 <div class="btn-group" id="isPushDiv" data-toggle="buttons">
	           	 	  <label class="btn btn-default<c:if test="${activity.isPush == 0}"> active</c:if>">
					  		<input type="radio" name="isPush" value="0"  autocomplete="off"
					  		<c:if test='${activity.isPush == 0}'>checked="checked"</c:if> >
					      	即时推送
					  </label>
					  <label class="btn btn-default<c:if test="${activity.isPush == 1}"> active</c:if>">
					  		<input type="radio" name="isPush" value="1" autocomplete="off"
					  			<c:if test='${activity.isPush == 1}'>checked="checked"</c:if> >
					     	定时推送
					  </label>
					  <label class="btn btn-default<c:if test="${activity.isPush == 2 || empty activity.isPush}"> active</c:if>">
					  		<input type="radio" name="isPush" value="2"  autocomplete="off"
					  		<c:if test='${activity.isPush == 2}'>checked="checked"</c:if> >
					      	不推送
					  </label>
				</div>
				<input type="text" id="pushTime" name="pushTime" class="form-control"
					style="<c:if test="${activity.isPush != 1}">display: none;</c:if>" 
					value="${activity.pushTime }" />
         </div>
      </div>
      <c:if test='${!empty activity.qrUrl}'>
      <div class="form-group form-group-sm">
         	<label class="col-md-3 control-label"><span class="text-red"></span> 活动二维码:</label>
         	<div class="col-md-6 has-feedback">
             	<img alt="" style="width:300px;height:300px;" src="${activity.qrUrl}bigPicture">
         	</div>
      </div>
      </c:if>
      
	</form>
</div>
</div><!-- end row -->	
		</fieldset>
		 <div class="form-group">
		 	<hr>
			<div class="col-md-offset-3 col-md-2">
				<button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 发布</button>
			</div>
			<div class="col-md-2">
				<a class="btn btn-default btn-block" href="${ctx}/activity/list"><span class="glyphicon glyphicon-remove"></span> 取消发布</a>			  
			</div>
		</div>
  </div>
</div>
<script type="text/javascript">
$(function() {
	menu.active('#activity-man');
	$("#expiryTypeDiv").on("click","label",function(){
		if($("#id").val()){
			return;
		}
		if($(this).find("input[name=expiryType]:eq(0)").val() == 0){
			$("#expiryDate2").rules("remove");
			$("#expiryDate2").attr("disabled","disabled");
			$("#expiryDate2").hide();
			
			$("#expiryDate1").bind({click:function(){
				WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',alwaysUseStartDate:true});
			}}).removeAttr("disabled").attr("placeholder","请输入报名截止日期").show();
			$('#expiryDate1').rules( "add",{required: true});
		}else{
			$("#expiryDate1").rules("remove");
			$("#expiryDate1").attr("disabled","disabled");
			$("#expiryDate1").hide();
			
			$("#expiryDate2").removeAttr("disabled").attr("placeholder","请输入报名截止时间").show();
			$('#expiryDate2').rules( "add",{required: true,number:true});
		}
	});
	
	// 是否推送
	$("#isPushDiv").on("click","label",function(){
		if($(this).find("input[name=isPush]").val() == 1){
			$("#pushTime").bind({click:function(){
				WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',alwaysUseStartDate:true});
			}}).removeAttr("disabled").attr("placeholder","请输入推送日期").show();
			$('#pushTime').rules( "add",{required: true});
		} else {
			$("#pushTime").rules("remove");
			$("#pushTime").attr("disabled","disabled");
			$("#pushTime").hide();
		}
	});
	
	
	$("#div_areaCode select:eq(2)").each(function(){
		$(this).addClass("required");
	});
	<%--加载ueditor--%>  
	var um = UE.getEditor('myEditor',{
		initialFrameWidth:'755',
		initialFrameHeight:'300',
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
	
	var um = UE.getEditor('editor',{
		initialFrameWidth:'755',
		initialFrameHeight:'300',
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

	var ids = ["icon_upload_photo","icon_upload_logo","icon_upload_imageShare"];
	  $.each(ids,function(n,value) {  
			$("#"+value).uploadify({
				fileObjName   : 'upfile',
				buttonText    : '选择图片',
				height        : 30,
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
      
      $("#submit_btn").click(function(){
			$("#inputForm").submit();
	  });

	  $('#inputForm').validate({
	    ignore: "", // 开启hidden验证， 1.9版本后默认关闭
		submitHandler: function(form) {
			if($("input[name=expiryType]:checked").val() == 1){
				var hour = parseInt($("#expiryDate2").val());
				if(hour < 0){
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					var stime = Date.parse(startTime);
					stime = new Date(stime);
					endTime = Date.parse(endTime)
					endTime = new Date(endTime);
					var currEndDate = new Date(stime.getFullYear(),stime.getMonth(),stime.getDate(),endTime.getHours(),endTime.getMinutes(),endTime.getSeconds());
					var expiryTime = stime.setHours(stime.getHours() - hour);
					if(currEndDate < expiryTime){
						common.showMessage("截止时间不能大于结束时间！");
						return;
					}
				}
			}
			//表单验证成功时，锁住提交按钮
			if(um.getContentLength(true) == 0){
				common.showMessage("请填写活动介绍！");
				return;
			}
			app.disabled("#submit_btn");
			//提交表单
			form.submit(); 
		},
		rules: {
			"name" : {
				required: true,
				minlength: 2,
				maxlength: 50
			},
			"type" : {
				required: true
			},
			"startTime" : {
				required: true
			},
			"endTime" : {
				required: true
			},
			"expiryType" : {
				required: true
			},
			"isRepeat" : {
				required: true
			},
			"totalNumber" : {
				required: true,
				digits: true,
				min:1
			},
			"address" : {
				required: true,
				maxlength: 100
			},
			"lnglat" :{
				required: true
			},
			"undertake" : {
				required: true,
				maxlength: 50
			},
			"tel" : {
				required: true,
				maxlength: 15
			},
			"briefdesc" : {
			    required: true
			},
			"free" :{
				required: true
			},
			"introduction" :{
				required: true
			},
			"photo" :{
				required: true
			},
			"pushType" : {
				required: true
			},
			"isPush" : {
				required: true
			}
		},
		messages: {
			
		}
    });
	
	$('#adminFooter').hide();
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
	
	$("#coordinate").click(function(){
		$("#baiduMap").show();
		var lnglat = $("#lnglat").val();
		var lng = 116.403867;
		var lat = 39.914113;
		var name = "天安门";
		if(lnglat){
			var lnglats = lnglat.split(",");
			lng = lnglats[0];
			lat = lnglats[1];
			var name = $("#name").val();
		}
		// 百度地图API功能
        map = new BMap.Map("allmap");
        map.centerAndZoom(new BMap.Point(lng,lat), 14);
        map.setCenter(new BMap.Point(lng,lat));
        var marker1 = new BMap.Marker(new BMap.Point(lng, lat));  //创建标注
        map.addOverlay(marker1);                 // 将标注添加到地图中
        var infoWindow1 = new BMap.InfoWindow(name);
        marker1.addEventListener("click", function (e) {
           this.openInfoWindow(infoWindow1, false);
        });
        
        $("#search").click(function(){
        	search($("#keyword").val(),map);
        });
        
        // 获取经纬度和地址
        map.addEventListener("click", function(e){
        	var lnglat = e.point.lng + "," + e.point.lat;
        	$("#lnglat").val(lnglat);
        	var gc = new BMap.Geocoder(); 
            gc.getLocation(e.point, function(rs) {
             	var addComp = rs.addressComponents; 
             	var address = '';
                address += addComp.province;
                address += addComp.city;
                address += addComp.district;
                address += addComp.street;
                address += addComp.streetNumber;
                $("#address").val(address);
                //alert("当前定位地址为：" + address);
             });
        });
	});
	
	function search(name,map){
		 var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		}); 
		local.search(name);
	}
	
	$(".close").click(function(){
		$("#baiduMap").slideUp('slow');
	});
</script>
</body>
</html>