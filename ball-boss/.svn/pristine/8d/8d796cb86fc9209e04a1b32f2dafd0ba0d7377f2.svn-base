<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>测评管理</title>
	<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>  
	<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
</head>

<body style="position: relative;">
<!-- 提示框 -->

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 测评管理</li>
        <li class="active">新增测评</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body">
<form id="actionForm" action="${ctx }/assessment/save" method="post">
		<input name="id" type="hidden" value="${assessment.id }">
		<input name="oper" id="oper" type="hidden"/>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     标题
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="title" value="${assessment.title}" id="title">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      封面图片(640*264)
			</div>
			<div class="col-md-8">
		      <div class="has-feedback" id="photo_content">
					<!-- 保存图片 -->
					<input id="coverImag" type="file" multiple="false" />
					<input id="cover" name="cover" type="hidden" value="${assessment.cover}" />
					<!-- 显示图片 -->
					<img alt="" style="<c:if test='${!empty assessment.cover}'>width:640px;height:264px;</c:if>"
						src="${assessment.cover}" id="cover_img">
	         </div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      分享图片
			</div>
			<div class="col-md-8">
		      <div class="has-feedback" id="photo_content1">
					<!-- 保存图片 -->
					<input id="contentUrl1" type="file" multiple="false" />
					<input id="contentUrl" name="contentUrl" type="hidden" value="${assessment.contentUrl}" />
					<!-- 显示图片 -->
					<img alt="" style="<c:if test='${!empty assessment.contentUrl}'></c:if>"
						src="${assessment.contentUrl}" id="contentUrl_img">
	         </div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     分享内容
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="listContent" value="${assessment.listContent}" id="listContent">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     视频地址
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="videoFile" value="${assessment.videoFile}" id="videoFile">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     视频封面
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="videoImage" value="${assessment.videoImage}" id="videoImage">
			</div>
		</div>
		<br>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      内容
			</div>
			<div class="col-md-6">
		      <script id="editor" name="content" type="text/plain">${assessment.content }</script>
			</div>
		</div>
		<hr>
		
		<div class="form-group form-group-sm">
 				<div class="col-md-12 text-center mesBox">
 				
 				<c:if test="${empty assessment.id }">
 				<button type="button" class="btn btn-primary btn-sm xg-send"> 发送</button>
 				</c:if>
 				<c:if test="${!empty assessment.id }">
 				<button type="button" class="btn btn-primary btn-sm xg-send"> 修改</button>
 				</c:if>
   				<button type="button" class="btn btn-primary btn-sm back-btn"> 取消</button>
 				</div>
		</div>
    </form>
    
    
	</div>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$('.mesBox').css("position",'relative');
$('.mesBox').prepend('<div class="tipMes" style="width:140px;padding:5px 0;background:rgba(17,17,17,.7);border-radius:10px;text-align:center;color:#eee;font-size:16px;position:absolute;bottom:-8%;left:30%;opacity:0;z-index:999;"></div>');
	// 提示框函数
	function showMessage(obj){
	    $(obj).animate({opacity:1},300,function(){
	        setTimeout(function(){
	            $(obj).animate({opacity:0},1000);
	        },700)
	    });
	}
  $(function() {
	  menu.active('#assessment-man');
	  $('#adminFooter').hide();
	  upload({
			'id' : 'coverImag',
			'icon_img' : 'cover_img',
			'icon' : 'cover'
		}); // LOGO
		
	 upload({
			'id' : 'contentUrl1',
			'icon_img' : 'contentUrl_img',
			'icon' : 'contentUrl'
		});
		
		$(".back-btn").click(function(){
			history.go(-1);
		})
		
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
		$(".lj-send").on("click",function(){
			$("#oper").val(1);
			$("#actionForm").submit();
			alert($("#oper").val());
		})
		
		$(".xg-send").on("click",function(){
			if("${assessment.sendType }"==0){
				$("#oper").val(2);
			}else{
				$("#oper").val(1);
			}
			if(checkAll()){
				$("#actionForm").submit();
			}
		})
		
  });
  
  function checkAll(){
	  var title=$("#title").val();
	  var cover=$("#cover").val();
	  var content=$("#editor").val();
	  if(title==""){
		  $(".tipMes").html('标题不能为空');
		  showMessage('.tipMes');
		  return false;
	  }
	  if(cover==""){
		  $(".tipMes").html('封面图片不能为空');
		  showMessage('.tipMes');
		  return false;
	  }
	  /* if(content==""){
		  $(".tipMes").html('内容不能为空');
		  showMessage('.tipMes');
		  return false;
	  } */
	  return true;
  }
  
  /**
	 *	option.id            上传元素id
	 *	option.icon_img      显示图片id
	 *	option.icon          保存图片的url的id
	 *	option.width         显示图片的宽度
	 *	option.height        显示图片的高度
	 */
	function upload(option) {
		var id = option.id || "icon_upload";
		var height = option.height || 40;
		var width = option.width || 120;
		var icon_img = option.icon_img || "icon_img";
		var icon = option.icon || "icon";
		$("#" + id).uploadify({
			//文件提交到 controller 里的文件对象的 key 
			fileObjName : 'upfile',
			//按钮名称
			buttonText : '选择图片',
			height : 30,
			multi : false,
			swf : ctx + '/static/uploadify/uploadify.swf',
			//提交到指定的 controller,写死即可，已封装
			uploader : ctx + '/uploader',
			width : 100,
			fileTypeExts : '*.gif;*.jpg;*.jpeg;*.png',
			//上传成功后回调此函数
			onUploadSuccess : function(file, data, response) {
				//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if (data.success == true) {
					$('#' + icon_img).attr('src', data.url).css({
						width : '640px',
						height : '264px;'
					});
					$('#' + icon).val(data.url);
				}
			}
		});
	}
  
  function send(){
	  var dsTime = $("#dsTime").val();
	  if(!dsTime){
		  return;
	  }
	  $("#sendTime").val(dsTime);
	  $("#actionForm").submit();
  }
</script>

</body>
</html>
