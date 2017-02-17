<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="statiumNav.jsp"%>
<form id="coachForm" action="${ctx}/statiumManage/save" method="post" class="form-horizontal" enctype="multipart/form-data">
	<div class="formTable">
		<input type="hidden" id="status" name="status">
		<input type="hidden" name="id" id="id" value="${coach.id}" />
		<ul>
			<li>
				<span class="title">姓名：</span>
				<input type="text" id="name" name="name" value="${coach.name }">
			</li>
			<li>
				<span class="title">昵称：</span>
				<input type="text" id="nick" name="nick" value="${coach.nick }">
			</li>
			<li>
				<span class="title">头像：</span>
				<div class="col-md-6 has-feedback">
					<!-- 上传控件 -->
					<input id="logoFile" type="file" multiple="false" />
					<!-- 保存图片 -->
					<input id="logo" name="logo" type="hidden" value="${coach.logo }" />
					<!-- 显示图片 -->
					<img alt="" src="${coach.logo }" id="logo_img" <c:if test="${not empty coach.logo }">height="100" </c:if>>
				</div>
			</li>
			<li>
				<span class="title">所在地区：</span>
					<tags:zone id="areacode" name="areacode" value="${coach.areacode }" disabled="${disable }" />
			</li>
			<li style="line-height: 30px;">
				<span class="title">性别：</span>
				<label class="memberFormlabel">
					<input type="radio" value="男" name="sex" checked="checked" <c:if test="${coach.sex =='男'}">checked="checked"</c:if> class="sexMan memberFormSex"/>男
				</label>
				<label class="memberFormlabel">
					<input type="radio" value="女" name="sex" <c:if test="${coach.sex == '女'}">checked="checked"</c:if> class="sexWomen memberFormSex"/>女
				</label>
			</li>
			<li style="line-height: 30px;">
				<span class="title">教龄类型：</span>
				<label class="memberFormlabel">
					<input type="radio" name="type" value="0" checked="checked" <c:if test="${coach.type == 0}">checked="checked"</c:if> class="sexMan memberFormSex"/>大课教练
				</label>
				<label class="memberFormlabel">
					<input type="radio" name="type" value="1" <c:if test="${coach.type == 1}">checked="checked"</c:if> class="sexWomen memberFormSex"/>私人教练
				</label>
			</li>
			<li>
				<span class="title">职位：</span>
				<input type="text" id="position" name="position" value="${coach.position }">
			</li>
			<li>
				<span class="title">级段位：</span>
				<input type="hidden" id="danlevel_hidden" value="${coach.danlevel }"/>
				<select class="form-control" name="danlevel" id="danlevel" >
					<option <c:if test="${'白黄带(九级)' == coach.danlevel}">selected</c:if> id="option1" value="白黄带(九级)" >--白黄带(九级)--</option>
					<option <c:if test="${'黄带(八级)' == coach.danlevel}">selected</c:if> id="option2" value="黄带(八级)" >--黄带(八级)--</option>
					<option <c:if test="${'黄绿带(七级)' == coach.danlevel}">selected</c:if> id="option7" value="黄绿带(七级)" >--黄绿带(七级)--</option>
					<option <c:if test="${'绿带(六级)' == coach.danlevel}">selected</c:if> id="option8" value="绿带(六级)" >--绿带(六级)--</option>
					<option <c:if test="${'绿蓝带(五级)' == coach.danlevel}">selected</c:if> id="option9" value="绿蓝带(五级)" >--绿蓝带(五级)--</option>
					<option <c:if test="${'蓝红带(三级)' == coach.danlevel}">selected</c:if> id="option10" value="蓝红带(三级)" >--蓝带(四级)--</option>
					<option <c:if test="${'红带(二级)' == coach.danlevel}">selected</c:if> id="option3" value="红带(二级)" >--红带(二级)--</option>
					<option <c:if test="${'红黑带(一级)' == coach.danlevel}">selected</c:if> id="option4" value="红黑带(一级)" >--红黑带(一级)--</option>
					<option <c:if test="${'黑带一段' == coach.danlevel}">selected</c:if> id="option5" value="黑带一段" >--黑带一段--</option>
					<option <c:if test="${'黑带二段' == coach.danlevel}">selected</c:if> id="option6" value="黑带二段" >--黑带二段--</option>
					<option <c:if test="${'黑带三段' == coach.danlevel}">selected</c:if> id="option11" value="黑带三段" >--黑带三段--</option>
					<option <c:if test="${'黑带四段' == coach.danlevel}">selected</c:if> id="option12" value="黑带四段" >--黑带四段--</option>
					<option <c:if test="${'黑带五段' == coach.danlevel}">selected</c:if> id="option13" value="黑带五段" >--黑带五段--</option>
					<option <c:if test="${'黑带六段' == coach.danlevel}">selected</c:if> id="option14" value="黑带六段" >--黑带六段--</option>
					<option <c:if test="${'黑带七段' == coach.danlevel}">selected</c:if> id="option15" value="黑带七段" >--黑带七段--</option>
					<option <c:if test="${'黑带八段' == coach.danlevel}">selected</c:if> id="option16" value="黑带八段" >--黑带八段--</option>
					<option <c:if test="${'黑带九段' == coach.danlevel}">selected</c:if> id="option17" value="黑带九段" >--黑带九段--</option>
				</select>
			</li>
			<li>
				<span class="title">教龄：</span>
				<input type="text" id="teachyear" name="teachyear" value="${coach.teachyear }">
			</li>
			<li>
				<span class="title">电话：</span>
				<input type="text" id="phone" name="phone" value="${coach.phone }" maxlength="11">
			</li>
			<li style="line-height: 30px;">
				<span class="title">是否身份认证：</span>
				<label class="memberFormlabel">
					<input type="radio" name="iscard" value="1" checked="checked"<c:if test="${coach.iscard == 1}">checked="checked"</c:if> class="sexMan memberFormSex"/>是
				</label>
				<label class="memberFormlabel">
					<input type="radio" name="iscard" value="0" <c:if test="${coach.iscard == 0}">checked="checked"</c:if> class="sexWomen memberFormSex"/>否
				</label>
			</li>
			<li style="line-height: 30px;">
				<span class="title">是否级/段位认证：</span>
				<label class="memberFormlabel">
					<input type="radio" name="isdan" value="1" checked="checked" <c:if test="${coach.isdan == 1}">checked="checked"</c:if> class="sexMan memberFormSex"/>是
				</label>
				<label class="memberFormlabel">
					<input type="radio" name="isdan" value="0" <c:if test="${coach.isdan == 0}">checked="checked"</c:if> class="sexWomen memberFormSex"/>否
				</label>
			</li>
			<li>
				<span class="title">个人介绍：</span>
				<textarea class="form-control" rows="4" id="helfexper"s name="helfexper">${coach.helfexper }
				</textarea>
			</li>
			<li>
				<span class="title">执教成果：</span>
				<textarea class="form-control" rows="4" id="tearchexper"s name="tearchexper">${coach.tearchexper }
				</textarea>
			</li>
			<li>
				<span class="title">专长：</span>
				<input type="text" id="specialty" name="specialty" value="${coach.specialty }">
			</li>
			<li>
				<span class="title">学员数量：</span>
				<input type="text" id="students" name="students" value="${coach.students }">
			</li>
			<li>
				<span class="title">上传教练照片：</span>
				<div class="form-group form-group-sm">
					<div class="col-md-6" name="statium_img" style="width: 135px;">
						<input id="photo1File" type="file" multiple="false" />
						<c:set var="count" value="1"/>
					</div>
					<span name="picSpan" style="color:red;line-height:30px;">（标准尺寸：640px * 300px） </span>
				</div>
				<div class="form-group form-group-sm">
					<div class="col-md-8" name="statium_img"  >
						<label class="col-md-2 control-label"> </label>
						<c:set var="count" value="1"/>
						<c:if test="${not empty coach.photos}">
							<c:forEach items="${fn:split(coach.photos,'__') }" var="itm" varStatus="s">
								<c:if test="${s.last}">
									<c:set var="count" value="${s.index + 2}"/>
								</c:if>
								<div style="float:left;margin-right:10px;">
									<input id="photo${s.index + 1}" name="photo" value="${itm }" type="hidden" />
									<img alt="" src="${itm}" id="photo_img${s.index + 1}" height="100" width="130" />
									<div aria-hidden="true" class="img_close">&times;</div>
								</div>
							</c:forEach>
							<c:forEach var="i" begin="${count}" end="5" step="1">
								<div style="float:left;margin-right:10px;">
									<input id="photo${i}" name="photo" type="hidden" />
									<img alt="" src="" id="photo_img${i}" />
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty coach.photos}">
							<c:forEach var="i" begin="1" end="5" step="1">
								<div style="float: left; margin-right: 10px;">
									<input id="photo${i}" name="photo" type="hidden" /> <img
										alt="" src="" id="photo_img${i}" />
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${coach.photos == null}">
							<!-- 	<div class="has-feedback">
							<input id="photo1File" type="file" multiple="false" /> -->
							<div style="float: left; margin-right: 10px;">
								<input id="photo1" name="photo" type="hidden" /> <img alt=""
																					  src="" id="photo_img1" />
							</div>
							<div style="float: left; margin-right: 10px;">
								<input id="photo2" name="photo" type="hidden" /> <img alt=""
																					  src="" id="photo_img2" />
							</div>
							<div style="float: left; margin-right: 10px;">
								<input id="photo3" name="photo" type="hidden" /> <img alt=""
																					  src="" id="photo_img3" />
							</div>
							<div style="float: left; margin-right: 10px;">
								<input id="photo4" name="photo" type="hidden" /> <img alt=""
																					  src="" id="photo_img4" />
							</div>
							<div style="float: left; margin-right: 10px;">
								<input id="photo5" name="photo" type="hidden" /> <img alt=""
																					  src="" id="photo_img5" />
							</div>
							<!-- </div> -->
						</c:if>
					</div>
				</div>
			</li>
		</ul>
		<div class="formSubCenterDiv">
			<a href="javascript:history.go(-1)" class="saveBtnCenter">返回</a>
			<a class="saveBtnCenter" href="javascript:saveForm()">保存</a>
		</div>
	</div>
</form>

<script type="text/javascript"
		src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
<script type="text/javascript">
	$(function() {
		// 样式
		$('#statium-man').addClass("active");
		$('#COACH').addClass("active");

		$("div[name = statium_img]").on("click",'.img_close',function(){
			$(this).parent().find("input").val("");
			$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
			$(this).remove();
		});
	});

	$(function() {
		upload({'id':'logoFile','icon_img':'logo_img','icon':'logo'});  // 球馆LOGO

		multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':5});  //
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
			uploader      : ctx + '/uploadImage',
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
			uploader      : ctx + '/uploadImage',
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

	// 表单提交
	function saveForm() {
		// 渠道
		$("#status").attr("value",0);
		$("#coachForm").submit();
	}

</script>

</body>
</html>