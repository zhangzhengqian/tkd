<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<c:choose>
	<c:when test="${param.action=='view'}">
		<c:set var="readonly" value="readonly='readonly'"/>
		<c:set var="disabled" value="disabled='true'"/>
		
	</c:when>
</c:choose>

<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <c:choose>
	        	<c:when test="${param.action=='edit'}">
	        		<li class="active">竞赛活动修改</li>
	        	</c:when>
	        	<c:when test="${param.action=='add'}">
	        		<li class="active">竞赛活动添加</li>
	        	</c:when>
	        </c:choose>
	        
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
  <div class="panel-body"><!-- 右侧主体内容 -->
	<form id="inputForm" action="${ctx}/activity/saveContestForm" method="post" class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="${contest.id }"/>
			<div class="form-group form-group-sm">
				<label for="title" class="col-md-3 control-label"><span class="text-red"></span>标题:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" ${readonly } class="form-control" id="title" name="title" style="width:300px" value="${contest.title }" />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red"></span>赛事活动LOGO:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${empty readonly }">
					<!-- 上传控件 -->
					<input id="logoFile" type="file" multiple="false" />
					<!-- 保存图片 -->
					<input id="logo" name="logo" type="hidden" value="${contest.logo}" />
				</c:if>		
					<!-- 显示图片 -->
					<img alt="" src="${contest.logo}" id="logo_img" <c:if test="${not empty contest.logo }">height="100" </c:if>>
				
	         </div>
	      </div>
		
				<div  class="form-group form-group-sm" >
					<label for="url" class="col-md-3 control-label"><span class="text-red"></span>易企秀链接:</label>
			    	<div class="col-md-6 has-feedback">
			    		<input type="text" ${readonly } class="col-md-9 form-control" id="url" name="url" style="width:458px" value="${contest.url }"/>
			   	 	</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="color" class="col-md-3 control-label"><span class="text-red"></span>背景色值:</label>
			    	<div class="col-md-6 has-feedback">
			    		<input type="text" ${readonly } class="form-control" id="color" name="color" style="width:229px" value="${contest.color}"/>
			   	 	</div>
				</div>
			
				<%-- <div class="form-group form-group-sm">
         			<label for="briefdesc" class="col-md-3 control-label"><span class="text-red">*</span> 活动介绍:</label>
         				<div class="col-md-6 has-feedback">
             				<script id="myEditor" name="content" type="text/plain"  ${readonly } value="${contest.content}">${contest.content }</script>
         				</div>
      			</div> --%>
      			
      			<div class="form-group form-group-sm">
	         		<label for="content" class="col-md-3 control-label"><span class="text-red"></span>活动介绍:</label>
	         			<div class="col-md-6 has-feedback">
	         				<textarea  ${readonly } style="height: 100px;" class="form-control" rows="5" placeholder="活动介绍" name="content" id="content">${contest.content }</textarea>
	         			</div>
	      		</div>
      			
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>排序值:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="输入正整数" type="text" ${readonly } class="form-control" id="sort" name="sort" style="width:229px" value="${contest.sort }" />
			    </div>
			</div>			

      	 <div class="form-group form-group-sm">
		  	<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/activity/contestList"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<shiro:hasPermission name="contest:update">         
	      	<div class="col-md-2">
		    	<button id = "submit_button" type="submit" class="btn btn-primary btn-block"  ${disabled }> 提交 </button>
			</div>
			</shiro:hasPermission>
		  </div>			 		 					
	</form>
  </div>
</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>  
<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>

<script type="text/javascript">
$(function(){
	$('#inputForm').validate({
		 submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
			getData(function(data){
				app.disabled("#submit_btn");
				form.submit(); 
			});
		},
			rules: {
				// 标题
				title:{
					required:true
				},
				color:{
					required:true,
				},
				// 排序
				sort: {
					required: true
				}
			},
	});
});



$(function(){
	// 菜单栏显示底色（选中）
	menu.active('#contest-man');
	
	upload({'id':'logoFile','icon_img':'logo_img','icon':'logo'});  // 道馆LOGO
	
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
 		    uploader      : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
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
});
	
</script>