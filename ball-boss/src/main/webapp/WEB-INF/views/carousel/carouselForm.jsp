<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <li class="active">轮播图添加</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
  <div class="panel-body"><!-- 右侧主体内容 -->
	<form id="inputForm" action="${ctx}/carousel/carousalForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
			<input type="hidden" id="id" name="id" value="${carousel.id }"/>
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="name" name="name" style="width:229px" value="${carousel.name }" />
			    </div>
			</div>
		
			<div class="form-group form-group-sm">
				<label for="position" class="col-md-3 control-label"><span class="text-red"></span>位置:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input type="hidden" id="position_hidden" value="${carousel.position }"/> 
					<select class="form-control" name="position" id="position" >
						<option id="option1" value="0" >--APP首页--</option>
						<option id="option2" value="1" >--APP场馆--</option>
						<option id="option12" value="7" >--APP球友圈首页--</option>
						<option id="option7" value="2" >--微信公众号--</option>
					    <option id="option8" value="3" >--WEB首页--</option>
						<option id="option9" value="4" >--WEB活动--</option>
						<option id="option10" value="5" >--WEB赛事--</option>
						<option id="option11" value="6" >--WEB教学--</option>
						<option id="option13" value="8" >--启动页广告--</option>
					</select>
			  	</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="type" class="col-md-3 control-label"><span class="text-red"></span>类型:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input type="hidden" id="type_hidden" value="${carousel.type }"/>
					<select class="form-control" name="type" id="type" >
						<option id="option3" value="activity" >--活动--</option>
						<option id="option4" value="race"  >--比赛--</option>
						<option id="option5" value="statium" >--场馆--</option>
						<option id="option6" value="url"  >--URL--</option>
					</select>
			  	</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="type" class="col-md-3 control-label"><span class="text-red"></span>渠道:</label>
			    <div class="col-md-6 has-feedback form-inline">
					<select class="form-control" name="channel" id="channel" >
						<option  value=''>--通用--</option>
						<option  value=1  >--android--</option>
						<option  value=2  >--ios--</option>
						<option  value=3  >--微信--</option>
						<option  value=4  >--网站--</option>
					</select>
			  	</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="type" class="col-md-3 control-label"><span class="text-red"></span>地区:</label>
			    <div  class="col-md-6 has-feedback form-inline" >
				  	<tags:zoneCity name="areaCode" value="${carousel.areaCode }" />
	         	</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="img" class="col-md-3 control-label"><span class="text-red"></span>图片:</label>
				<div id="title" class="col-md-1 has-feedback">
					<img id="icon_img" src="${carousel.image }" style="width:640px;height:156px;display:block;margin-left: auto;margin-right: auto;"/>
				</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="ff" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback" style="width:140px;">
			    	<input type="file" class="form-control" id="icon_upload" name="icon_upload" multiple="false" />
			    	
			    	<input type="hidden" id="image" name="image" value="${carousel.image }"/>
			    </div>
	         	<span name="picSpan" style="color:red;line-height:40px;">（标准尺寸：640px * 300px） </span>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="img" class="col-md-3 control-label"><span class="text-red"></span>新版图片:</label>
				<div id="title" class="col-md-1 has-feedback">
					<img id="icon_img_" src="${carousel.imageNew }" style="width:640px;height:200px;display:block;margin-left: auto;margin-right: auto;"/>
				</div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="ff" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback" style="width:140px;">
			    	<input type="file" class="form-control" id="icon_upload_" name="icon_upload_" multiple="false" />
			    	<input type="hidden" id="imageNew" name=imageNew value="${carousel.imageNew }"/>
			    </div>
	         	<span name="picSpan" style="color:red;line-height:40px;">（标准尺寸：640px * 300px） </span>
			</div>
			
			<%-- <c:if test="${!empty carousel.id }">
			    <div class="form-group form-group-sm" id="mImageArea">
		         	<div class="col-md-6 has-feedback col-md-offset-3">
		           		<img src="${carousel.image }" width="80" height="50" onclick="getOriginal('${carousel.image }')" >
		         	</div>
			    </div>   
		    </c:if>  --%>
			
			<!-- <div class="form-group form-group-sm" id="imageOrcontent" >

	      	</div>	 -->

			<div class="form-group form-group-sm">
				<label for="resourceId" class="col-md-3 control-label"><span class="text-red"></span>id:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="resourceId" name="resourceId" style="width:650px" value="${carousel.resourceId }" />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>排序值:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="输入正整数" type="text" class="form-control" id="sort" name="sort" style="width:229px" value="${carousel.sort }" />
			    </div>
			</div>			

      	 <div class="form-group form-group-sm">
		  	<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/carousel/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
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
	menu.active('#carousel-man');
	// 下拉列表初始化（修改）
	// 位置
	var position=$("#position_hidden").val();
	if(position==0){
		$("#option1").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：640px * 200px）");
        });
	} else if(position==1){
		$("#option2").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：640px * 300px）");
		 });
	}else if(position==2){
		$("#option7").attr("selected","selected");
	} else if(position==3){
		$("#option8").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：1200px * 340px）"); 
        });
	}else if(position==4){
		$("#option9").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：900px * 340px）"); 
        });
	} else if(position==5){
		$("#option10").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：1200px * 360px）");
        });
	}else if(position==6){
		$("#option11").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：1200px * 360px）");
        });
	}else if(position==8){
		$("#option13").attr("selected","selected");
		$("span[name='picSpan']").each(function(){
            $(this).html("（标准尺寸：720px * 1280px）");
        });
	}
	
	
	// 类型
	var type=$("#type_hidden").val();
	if(type=='activity'){
		$("#option3").attr("selected","selected");
	} else if(type=='race'){
		$("#option4").attr("selected","selected");
	} else if(type=='statium'){
		$("#option5").attr("selected","selected");
	} else if(type=='url'){
		$("#option6").attr("selected","selected");
	}
	
	$("#position").change(function () {
		var posVal = $('#position option:selected').val();
		if(posVal==0){
			$("span[name='picSpan']").each(function(){
                $(this).html("（标准尺寸：640px * 200px）");
            });
		} else if(posVal==1){
			$("span[name='picSpan']").each(function(){
                $(this).html("（标准尺寸：640px * 300px）");
			 });
		} else if(posVal==3){
			$("span[name='picSpan']").each(function(){
                $(this).html("（标准尺寸：1200px * 340px）"); 
            });
		}else if(posVal==4){
			$("span[name='picSpan']").each(function(){
                $(this).html("（标准尺寸：900px * 340px）"); 
            });
		} else if(posVal==5){
			$("span[name='picSpan']").each(function(){
                $(this).html("（标准尺寸：1200px * 360px）");
            });
		}else if(posVal==6){
			$("span[name='picSpan']").each(function(){
                $(this).html("（标准尺寸：1200px * 360px）");
            });
		}
	}) 
	
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
 		// 校验
 		//checkValue();
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
    uploader      : '${ctx}/uploaderCarousel',
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
$("#icon_upload_").uploadify({
    //文件提交到 controller 里的文件对象的 key 
	fileObjName   : 'upfile',
    //按钮名称
	buttonText    : '选择图片',
	height        : 40,
	swf           : '${ctx}/static/uploadify/uploadify.swf',
    //提交到指定的 controller,写死即可，已封装
    uploader      : '${ctx}/uploaderCarousel',
	width         : 120,
	//上传成功后回调此函数
    onUploadSuccess : function(file, data, response) {
    //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
		data = JSON.parse(data);
		if(data.success==true){
			$('#icon_img_').attr('src',data.url);
			$('#imageNew').val(data.url);
		}
       }
});
 
</script>