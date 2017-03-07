<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
	        <li class="active">用户管理</li>
	        <li class="active">用户添加</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  	
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<fieldset> <legend><small>用户信息</small></legend>
	<form id="inputForm" action="${ctx}/ssouser/ssoUserForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>个人头像:</label>
				<div id="title" class="col-md-1 has-feedback">
					<img id="icon_img" src="http://www.chfax.com/public/avatar/noavatar_middle.gif" style="width:128px;height:128px;display:block;margin-left: auto;margin-right: auto;"/>
				</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback">
			    	<input type="file" class="form-control" id="icon_upload" name="icon_upload" multiple="false" />
			    	<input type="hidden" id="photo" name="photo" value=""/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>用户昵称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入注册昵称" type="text" class="form-control" id="nickName" name="nickName" style="width:480px" />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red">*</span>真实姓名:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入真实姓名" type="text" class="form-control" id="name" name="name" style="width:480px" />
			    </div>
			</div>
		
			<div class="form-group form-group-sm">
				<label for="position" class="col-md-3 control-label"><span class="text-red"></span>性别:</label>
			    <div class="col-md-6 has-feedback form-inline">
					<select class="form-control" name="sex" id="sex" >
						<option id="option1" value="男" >--男--</option>
						<option id="option2" value="女" >--女--</option>
					</select>
			  	</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="position" class="col-md-3 control-label"><span class="text-red"></span>所在地区：</label>
				<div class="col-md-6 has-feedback form-inline">
					<tags:zone id="city" name="city"   />
			    </div>
		    </div>
			
			<div class="form-group form-group-sm">
				<label for="resourceId" class="col-md-3 control-label"><span class="text-red"></span>家庭住址:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  placeholder="请输入家庭住址" type="text" class="form-control" id="address" name="address" style="width:480px" />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>身份证号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入身份证号" type="text" class="form-control" id="cardId" name="cardId" style="width:480px" />
			    </div>
			</div>	
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red">*</span>手机号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入手机号" type="text" class="form-control" id="phone" name="phone" style="width:480px" />
			    </div>
			</div>		
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>电子邮箱:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入电子邮箱地址" type="text" class="form-control" id="email" name="email" style="width:480px" />
			    </div>
			</div>
			
			<!-- <div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>备注:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入备注" type="text" class="form-control" id="remark " name="remark " style="width:229px" />
			    </div>
			</div>	 -->			

	      	 <div class="form-group form-group-sm">
			  	<div class="col-md-offset-3 col-md-2">
				   <a class="btn btn-default btn-block" href="${ctx}/ssouser/user"><span class="glyphicon glyphicon-remove"></span> 返回</a>
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

<script type="text/javascript">
$(function(){
	// 菜单栏显示底色（选中）
	menu.active('#ssouser-man');
	
	$("#icon_upload").uploadify({
	    //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
	    //按钮名称
		buttonText    : '选择图片',
		height        : 40,
		swf           : '${ctx}/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : '${ctx}/uploader',
		width         : 120,
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	    //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$('#icon_img').attr('src',data.url);
				$('#photo').val(data.url);
			}
	       }
	});
	
	// 控件校验
 	$('#inputForm').validate({
		rules: {
			// 名称
			"name":{
				required:true,
				maxlength: 8,
				minlength: 2
			},
			// 手机号
			"phone": {
				required: true,
				isMobile: true
			}
		},
		messages: {
			name: {
				maxlength:"不多于30个汉字",
				minlength:"不小于2个汉字"
			},
			phone: {
				remote: '电话格式不正确，请重新输入！'
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

// 验证图片样式
function checkImgType(obj){  
	var _file=document.getElementById("mImage");
    var i=_file.value.lastIndexOf('.');
    var len=_file.value.length;
    var extEndName=_file.value.substring(i+1,len);
    var extName="GIF,BMP,JPG,JPEG,SWF";//首先对格式进行验证
    if(extName.indexOf(extEndName.toUpperCase())==-1){
        alert("*您只能输入"+extName+"格式的文件");
        document.getElementById("mImage").value='';
        return false;
    }
} 

</script>