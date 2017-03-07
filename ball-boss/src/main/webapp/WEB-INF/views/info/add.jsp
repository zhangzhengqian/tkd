<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>资讯管理</title>
	<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.config.js"></script>  
	<script type="text/javascript" src="${ctx}/static/ueditor/ueditor.all.js"></script>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 资讯管理</li>
        <li class="active">新增资讯</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body">
<form id="actionForm" action="${ctx }/info/save" method="post">
		<input name="id" type="hidden" value="${info.id }">
		<input name="oper" id="oper" type="hidden"/>
		<input name="sendTime_" id="sendTime" type="hidden"/>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     标题
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="title" value="${info.title}" id="title">
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
					<input id="cover" name="cover" type="hidden" value="${info.cover}" />
					<!-- 显示图片 -->
					<img alt="" style="<c:if test='${!empty info.cover}'>width:640px;height:264px;</c:if>"
						src="${info.cover}" id="cover_img">
	         </div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     列表内容
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" name="listContent" value="${info.listContent}" id="listContent">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      内容
			</div>
			<div class="col-md-6">
		      <script id="editor" name="content" type="text/plain">${info.content }</script>
			</div>
		</div>
		<hr>
		<c:if test="${info.sendType==3}">
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     定时发送
			</div>
			<div class="col-md-6">
		      <input  type="text" class="form-control input-sm" disabled value="${sendTime}" id="listContent">
			</div>
		</div>
		<hr>
		</c:if>
		<div class="form-group form-group-sm">
 				<div class="col-md-12 text-center">
 				<c:if test="${!empty info.id }">
 				<button type="button" class="btn btn-primary btn-sm xg-send"> 修改</button>
   				　　
 				</c:if>
 				<c:if test="${info.sendType==0||info.sendType==2||empty info.id}">
   				<button type="button" class="btn btn-primary btn-sm lj-send"> 发送</button>
   				
   				　　
   				<button type="button" class="btn btn-primary btn-sm ds-send"> 定时发送</button>
   				　　
   				<c:if test="${empty info.id}">
   				<button type="button" class="btn btn-primary btn-sm cg-send"> 存草稿</button>
   				　　
   				</c:if>
   				<button type="button" class="btn btn-primary btn-sm back-btn"> 取消</button>
   				</c:if>
 				</div>
		</div>
    </form>
    
    <div class="modal fade" id="dsModel" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">定时时间</h4>
				</div>
				<div class="modal-body" class="">
					<div  class="form-group">
						<input type="text" id="dsTime" placeholder="请输入定时时间"
                  			class="form-control" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',firstDayOfWeek:1,minDate:'%y-%M-%d'})"/>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="send()">确认</a>
			</div>
		</div>
	</div>
	</div>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  menu.active('#info-man');
	  $('#adminFooter').hide();
	  upload({
			'id' : 'coverImag',
			'icon_img' : 'cover_img',
			'icon' : 'cover'
		}); // LOGO
	
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
		})
		$(".cg-send").on("click",function(){
			$("#oper").val(0);
			$("#actionForm").submit();
		})
		$(".xg-send").on("click",function(){
			$("#oper").val(10);
			$("#actionForm").submit();
		})
		$(".ds-send").on("click",function(){
			$("#oper").val(3);
			$("#dsModel").modal();
		})
  });
  
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
