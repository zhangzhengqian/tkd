<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span><a href="${ctx }/corps/"> 战队管理 </a></li>
	        <li class="active">战队信息</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<h3>战队信息</h3>
	<hr>
	<form id="inputForm" action="${ctx}/corps/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
			<input type="hidden" class="form-control" id="captain" name="captain" value=""/>
			<input type="hidden" class="form-control" id="memberList" name="memberList" value=""/>
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>战队名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input type="text" class="form-control" id="name" name="name" value="" placeholder="请输入战队名称" />
		       </div>
		    </div>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>战队队长:</label>
		       <div class="col-md-6 has-feedback">
		       	 <span id="captainName"></span>
		         <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
		       </div>
		    </div>
   		  <div class="form-group form-group-sm">
			 <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>活动区域:</label>
		     <div class="col-md-6 has-feedback form-inline">
		     	<tags:zone id="area" name="area" value="" />
		     </div>
		 </div>
		 <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>详细地址:</label>
	         <div class="col-md-6 has-feedback ">
	           <input type="text" class="form-control" id="activityArea" name="activityArea" value="" placeholder="请输入详细地址"/>
	         </div>
	     </div>
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>运动类型:</label>
	         <div class="col-md-3 has-feedback ">
	           <select class="form-control" id="sportType" name="sportType">		
					<option  value="" >--请选择运动类别--</option>
					<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
						<option  value="${item.itemCode }" >--${item.itemName }--</option>
					</c:forEach>
			   </select>
	         </div>
	     </div>
	     <div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red">* </span>战队LOGO:</label>
	         <div class="col-md-6 has-feedback">
				<!-- 上传控件 -->
				<input id="logoFile" type="file" multiple="false" />
				<!-- 保存图片 -->
				<input id="logo" name="logo" type="hidden" value="" />
				<!-- 显示图片 -->
				<img alt="" src="" id="logo_img">
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>联系电话:</label>
	         <div class="col-md-6 has-feedback ">
	           <input type="text" class="form-control" id="phone" name="phone" value="" />
	         </div>
	     </div>
	     
	     <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>战队介绍:</label>
	         <div class="col-md-6 has-feedback ">
	           <textarea  rows="5" style="height:8em;" id="introduction" class="form-control" placeholder="请输入战队介绍" name="introduction"></textarea>
	         </div>
	     </div>
	     <div class="form-group form-group-sm">
	       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>添加队员:</label>
	       <div class="col-md-6 has-feedback">
	         <a id="add_member" class="btn btn-default btn-primary">添加</a>
	       </div>
	    </div>
	    <div class="form-group form-group-sm">
	    	<label class="col-md-3 control-label"></label>
	    	<div class="col-md-6 has-feedback">
	    	<table id="member_table" class="table table-bordered">
	    		
	    	</table>
	    	</div>
	    </div>
		</fieldset>
	<div class="form-group form-group-sm">
	  	<div class="col-md-offset-3 col-md-2">
		   <a class="btn btn-default btn-block" href="${ctx}/corps"><span class="glyphicon glyphicon-remove"></span> 返回</a>
		</div>         
      	<div class="col-md-2">
	    	<button id="submit_button" type="submit" class="btn btn-primary btn-block" ><span class="glyphicon glyphicon-save"></span> 保存 </button>
		</div>
	</div>	
	</form>
  </div>
</div>
<div class="modal fade" id="addMember" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加队员</h4>
				</div>
				<div class="modal-body" id="memberInfo">
				
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">关闭</a>
				</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var members = [];
$(function() {
	menu.active('#corps-man');
	upload({'id':'logoFile','icon_img':'logo_img','icon':'logo'});  // 球馆LOGO
	$('#inputForm').validate({
		submitHandler: function(form) {
			var logo = $("#logo").val();
			if(logo==''){
				myAlert("请上传战队logo.","error");
				return;
			}
			var captain = $("#captain").val();
			if(captain==''){
				myAlert("请选择战队队长.","error");
				return;
			}
			var area = $("#area").val();
			if(area==''){
				myAlert("请选择活动区域.","error");
				return;
			}else{
				var provincePart = new RegExp("[0-9]{2}0000");
	            var cityPart = new RegExp("[0-9]{4}00");
	            if(provincePart.test(area)){
	            	myAlert("请选择市.","error");
	            	return;
	            }else if(cityPart.test(area)){
	            	myAlert("请选择区.","error");
	            	return;
	            }
			}
			var sportType = $("#sportType").val();
			if(sportType==''){
				myAlert("请选择运动类型.","error");
				return;
			}
			$("#memberList").val(JSON.stringify(members));
			form.submit();
		},
		rules: {
			name: {
				required: true,
				maxlength:30
			},
			phone: {
				required: true,
				integer:true,
				isMobile : true
			},
			introduction:{
				required: true,
				maxlength:30
			}
		},
	});
	$('#sel_captain').on('click',function(){
		$("#myDlgBody_lg").load("${ctx}/common/qiuyou_query_dlg");
		$("#myDlg_lg").modal();
	})
	$('#add_member').on('click',function(){
		var temp = new EJS({url: '${ctx}/static/template/add_member.ejs?ver='+Math.random()});
		var html = temp.render();
		$('#memberInfo').html(html);
		$("#addMember").modal();
	})
});

/**
*	option.id            上传元素id
*	option.icon_img      显示图片id
*	option.icon          保存图片的url的id
*	option.width         显示图片的宽度
*	option.height        显示图片的高度
*/
function upload(option){
	var id = option.id || "icon_upload";
	var height = option.height|| 40;
	var width = option.width || 120;
	var icon_img = option.icon_img || "icon_img";
	var icon = option.icon || "icon";
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
	    //按钮名称
		buttonText    : '选择图片',
		height        : 30,
		multi         :false,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploader',
		width         : 100,
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$('#'+icon_img).attr('src',data.url).css({width:'100px',height:'100px;'});
				$('#'+icon).val(data.url);
	       }
	    }  
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
function captainAddCallBack(userId,nickName){
	$("#captain").val(userId);
	$("#captainName").html(nickName);
}
function removeMember(obj){
	var index =  $(obj).parent().parent().index();
	members.splice(index,1);
	$(obj).parent().parent().remove();
}
</script>