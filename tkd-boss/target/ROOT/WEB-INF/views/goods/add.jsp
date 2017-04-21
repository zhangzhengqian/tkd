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
        <li><span class="glyphicon glyphicon-home"></span> 商品管理</li>
        <li class="active">新增商品</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body">
<form id="actionForm" action="${ctx }/goods/save" method="post" >
		<input name="id" type="hidden" value="${good.id }">
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		      商品图片
			</div>
			<div class="col-md-8">
		      <div class="has-feedback">
					<!-- 保存图片 -->
					<input id="coverImag" type="file" multiple="false" />
					<input id="cover" name="photo" type="hidden" value="${good.photo}" />
					<!-- 显示图片 -->
					<img alt="" style="<c:if test='${!empty good.photo}'>width:320px;height:240px;</c:if>"
						src="${good.photo}" id="cover_img">
	         </div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     名称
			</div>
			<div class="col-md-6">
		      <input  type="text" placeholder="请填写商品名称" class="form-control input-sm" name="name" value="${good.name}" id="name">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     净含量
			</div>
			<div class="col-md-6">
		      <input  type="text" placeholder="请填写商品净含量，例如100ml、50g" class="form-control input-sm" name="bulk" value="${good.bulk}" id="bulk">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     单位
			</div>
			<div class="col-md-6">
		      <input  type="text" placeholder="请填写单位，例如箱、盒" class="form-control input-sm" name="unit" value="${good.unit}" id="unit">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     数量
			</div>
			<div class="col-md-6">
		      <input  type="number" onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="请填写商品数量" class="form-control input-sm" name="amount" value="${good.amount}" id="amount">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     价格
			</div>
			<div class="col-md-6">
		      <input  type="number" placeholder="请填写商品价格" class="form-control input-sm" name="fee" value="${good.feeView}" id="price">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-2 col-md-offset-1">
		     状态
			</div>
			<div class="col-md-6">
		      <select class="form-control" name="status">
				<option <c:if test="${'0' == good.status}">selected</c:if>
					 value="0">上架</option>
				<option <c:if test="${'1' == good.status}">selected</c:if>
					 value="1">下架</option>
			</select>
			</div>
		</div>
		<hr>
    </form>
		<div class="form-group form-group-sm">
 				<div class="col-md-12 text-center">
 					<button type="botton" onclick="sub()" class="btn btn-primary btn-sm"> 保存</button> 
 					<button type="botton" class="btn btn-primary btn-sm back"> 取消</button> 
 				</div>
		</div>
    
  </div><!-- /右侧主体内容 -->

</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
  $(function() {
	  menu.active('#goods');
	  $('#adminFooter').hide();
	  upload({
			'id' : 'coverImag',
			'icon_img' : 'cover_img',
			'icon' : 'cover'
		}); // LOGO
		
	$(".back").on("click",function(){
		history.go(-1);
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
						width : '320px',
						height : '240px;'
					});
					$('#' + icon).val(data.url);
				}
			}
		});
	}
  
  function sub(){
	  if(!$("#cover").val()){
		  return;
	  }
	  if(!$("#price").val()){
		  return;
	  }
	  if(!$("#unit").val()){
		  return;
	  }
	  if(!$("#name").val()){
		  return;
	  }
	  if(!$("#bulk").val()){
		  return;
	  }
	  if(!$("#amount").val()){
		  return;
	  }
	  $("#actionForm").submit();
  }
  
</script>

</body>
</html>
