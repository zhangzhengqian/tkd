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
  <form id="inputForm" action="${ctx}/activity/save" method="post" class="form-horizontal">
  <input id="id" name="id" value="${activity.id }" type="hidden"/>
  <input id="statiumId" name="statiumId" value="${activity.statiumId }" type="hidden"/>
  <div class="panel-body"><!-- 右侧主体内容 -->
		<fieldset> <legend><small>活动信息</small></legend>
<div class="row">
	<div class="col-sm-12">
		<div class="form-group form-group-sm">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span> 活动标题:</label>
	       <div class="col-md-6 has-feedback">
	         <input type="text" id="activityTopic" name="activityTopic" class="form-control" placeholder="请填写活动标题" value="${activity.activityTopic }"
	       </div>
	    </div>  
	 </div>
	   
     <div class="form-group form-group-sm">
         <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>道馆名称:</label>
					<div class="col-md-6 has-feedback">
						<span id="captainName"></span> 
						<input readonly type="text" class="form-control" id="dgName" name="dgName" value="${activity.dgName }" placeholder="请选择所属道馆" style="width: 200px" /> 
						<a id="sel_captain" class="btn btn-default btn-primary">选择</a>
						<button class="btn btn-default btn-sm" type="button" id="dgNameClean">清除</button>
					</div>
      </div>
      <div class="form-group form-group-sm">
      	<label for="name" class="col-md-3 control-label"><span class="text-red">*</span>主办方名称:</label>
      		<div class="col-md-6 has-feedback">
      		<input type="text" class="form-control" name="hostName" value="${activity.hostName }"placeholder="请输入主办方名称"style="width:200px">
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
                  			class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})"/>
 							<label for="startTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                    </div>
                  	至
                    <div class="input-group">
                  		<input type="text" id="endTime" name="endTime" placeholder="请输入活动结束时间" value="${activity.endTime}"
                  			<c:if test="${action eq 'update' }">disabled="disabled"</c:if>
                  			class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',alwaysUseStartDate:true});"/>
							<label for="endTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                    </div>
            </div>
        </div>
       <div class="form-group form-group-sm">
         <label for="expiryDate" class="col-md-3 control-label"><span class="text-red">* </span> 报名类型:</label>
         <div class="col-md-6 has-feedback form-inline">
         	<div class="btn-group" id="expiryTypeDiv" data-toggle="buttons">
					  <label class="btn btn-default<c:if test="${activity.expiryType == 0 }"> active abc</c:if>">
					  		<input type="radio" name="expiryType" value="0" 
					  			<c:if test="${activity.expiryType == 0}">checked="checked"</c:if> autocomplete="off">
					     	报名截止时间
					  </label>
					  <label class="btn btn-default<c:if test="${activity.expiryType == 1}"> active abc</c:if>">
					  		<input type="radio" name="expiryType" value="1"  autocomplete="off"
					  		<c:if test="${activity.expiryType == 1}">checked="checked"</c:if> autocomplete="off">
					      	报名截止日期
					  </label>
				</div>
				<input  type="text" id="expiryDate1" name="expiryData" class="form-control"
					style="<c:if test="${activity.expiryType != 0}">display: none;</c:if>" 
					value="${activity.expiryData }" />
				<input type="text" id="expiryDate2" name="expiryData" class="form-control"
					style="<c:if test="${activity.expiryType != 1}">display: none;</c:if>" 
					value="${activity.expiryData }"/>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="hostIntroduce" class="col-md-3 control-label"><span class="text-red">* </span> 主办方介绍:</label>
         <div class="col-md-6 has-feedback">
         	<script id="editor" name="hostIntroduce" type="text/plain" value="${activity.activityIntroduce}">${activity.hostIntroduce}</script>
         </div>
      </div>
      
       <div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传活动图片:</label>
	         <div class="col-md-6" name="activity_imgs" style="width: 135px;" id="activity_imgs">
	         	 <input id="photo1File" type="file" multiple="false" />
	         	<c:set var="count" value="1"/>
	         </div>
	         <span name="picSpan" style="color:red;line-height:30px;">（标准尺寸：640px * 300px） </span>
	      </div>
	      <div class="form-group form-group-sm">
	       <label for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="col-md-6" name="activity_img" id="activity_img" >
	         	<c:set var="count" value="1"/>
	         	<c:if test="${not empty activity.photo}">
	         		<c:forEach items="${fn:split(activity.photo,'__') }" var="itm" varStatus="s">
	         			<c:if test="${s.last}">
	         				<c:set var="count" value="${s.index + 2}"/>
	         			</c:if>
	         			<div style="float:left;margin-right:10px;">
							<input id="photos${s.index + 1}" name="photos" value="${itm }" type="hidden" />
	         				<img alt="" src="${itm}" id="photo_img${s.index + 1}" height="100" width="130" />
	         				<div aria-hidden="true" class="img_close">&times;</div>
	         			</div>
	         		</c:forEach>
		         	<c:forEach var="i" begin="${count}" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photos${i}" name="photos" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	<c:if test="${empty activity.photo}">
	         		<c:forEach var="i" begin="1" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photos${i}" name="photos" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	
	         	<c:if test="${param.action == 'create' }">
					<div style="float:left;margin-right:10px;">
						<input id="photo1" name="photos" type="hidden" />
						<img alt="" src="" id="photo_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo2" name="photos" type="hidden" />
						<img alt="" src="" id="photo_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo3" name="photos" type="hidden" />
						<img alt="" src="" id="photo_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo4" name="photos" type="hidden" />
						<img alt="" src="" id="photo_img4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo5" name="photos" type="hidden" />
						<img alt="" src="" id="photo_img5"/>
	           		</div>
	    		</c:if>
	         </div>
	      </div>
      
      <div class="form-group form-group-sm">
         <label for="amount" class="col-md-3 control-label"><span class="text-red">* </span> 活动人数:</label>
         <div class="col-md-6 has-feedback form-inline">
           <input type="number" min="1" class="form-control" style="padding-right:0px;" placeholder="请输入活动人数" id="amount" name="amount" value="${activity.amount}" />
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
             	value="${activity.price}" class="form-control" placeholder="例如50，不能有小数"/>
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         	<label for="briefdesc" class="col-md-3 control-label"><span class="text-red">*</span> 活动介绍:</label>
         	<div class="col-md-6 has-feedback">
             	<script id="myEditor" name="activityIntroduce" type="text/plain" value="${activity.activityIntroduce}">${activity.activityIntroduce }</script>
         	</div>
      </div>
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

$("#sel_captain").on("click",function(){
	$("#myDlgBody_lg").load("${ctx}/common/statium_query_dlg"); 
	$("#myDlg_lg").modal();
})
function captainAddCallBack(dgId, a2) {
		$("#statiumId").val(dgId);
		$("#dgName").val(a2);
	}
$(function() {
	
	multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photos','limit':5});  // 
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
});
$(function() {
	$("#dgNameClean").click(function(){
		$("#dgName").val("");
		$("#dgId").val("");
	})
	$("#activity_img").on("click",
			'.img_close',
			function() {
				$(this).parent().find("input").val("");
				$(this).parent().find("img").attr("src", "").attr("height",
						"0").attr("width", "0");
				$(this).remove();
			});
				
	menu.active('#activity-man');
	$("#expiryTypeDiv").on("click","label",function(){
		/* if($("#id").val()){
			alert("111");
			return;
		} */
		if($(this).find("input[name=expiryType]:eq(0)").val() == 0){
			$("#expiryDate2").val("");
			$("#expiryDate1").val("");
			$("#expiryDate2").rules("remove");
			$("#expiryDate2").attr("disabled","disabled");
			$("#expiryDate2").hide();
			$("#expiryDate1").bind({click:function(){
				WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',alwaysUseStartDate:true});
			}}).removeAttr("disabled").attr("placeholder","请输入报名截止时间").show();
			$('#expiryDate1').rules( "add",{required: true});
		}else{
			$("#expiryDate2").val("");
			$("#expiryDate1").val("");
			$("#expiryDate1").rules("remove");
			$("#expiryDate1").attr("disabled","disabled");
			$("#expiryDate1").hide();
			
			$("#expiryDate2").removeAttr("disabled").attr("placeholder","例如2016-08-15").show();
			$('#expiryDate2').rules( "add",{required: true,number:true});
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
			"activityTopic" : {
				required: true,
			},
			"dgName" :{
				required:true,
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
			"expiryData" :{
				required:true,
				maxlength:21
			},
 			"free" :{
				required: true
			},
			"price":{
				required:true,
				digits: true,
				range:[0,99999999],
			},
			"hostIntroduce" :{
				required: true
			},
			"activityIntroduce" :{
				required: true
			},
			"photo" :{
				required: true
			},
		},
		messages: {
			price: {
				digits:"必须为正整数",
				range: "请输入一个介于 {0} 和 {99999999} 之间的值"
			}
		}
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