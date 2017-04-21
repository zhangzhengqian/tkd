<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<style type="text/css">
#allmap {
	width: 100%;
	height: 500px;
	margin: 0;
	position: relative;
}

#golist {
	display: none;
}

@media ( max-device-width : 780px) {
	#golist {
		display: block !important;
	}
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
<div class="panel panel-default">
	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span><a
				href="${ctx }/coach/"> 教练列表 </a></li>

			<li class="active">教练信息</li>
		</ul>
	</div>
	<!-- / 右侧标题 -->

	<c:choose>
		<c:when test="${param.action == 'edit' || param.action == 'create'}">
			<c:set var="disable" value="false" />
		</c:when>
		<c:otherwise>
			<c:set var="disable" value="true" />
			<c:set var="readonly" value="readonly='readonly'" />
		</c:otherwise>
	</c:choose>

	<div class="panel-body">
		<!-- 右侧主体内容 -->

		<h3>教练信息</h3>
		<hr>
		<form id="inputForm" action="${ctx}/coach/save" method="post"
			class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" name="dgId" id="dgId" value="${coach.dgId}" />
			<input type="hidden" name="id" id="id" value="${coach.id}" /> 
			<fieldset>
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>所属道馆:</label>
					<div class="col-md-6 has-feedback">
						<span id="captainName"></span> 
						<input readonly type="text" class="form-control" id="statium" name="dgName" value="${coach.dgName }" placeholder="请选择所属道馆" style="width: 200px" /> 
						<a id="sel_captain" class="btn btn-default btn-primary">选择</a>
						<button class="btn btn-default btn-sm" type="button" id="dgNameClean">清除</button>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"> <span class="text-red">* </span>姓名:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="name" name="name" maxlength=20 value="${coach.name }" placeholder="请输入姓名" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"> <span class="text-red"></span>昵称:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="nick" name="nick" maxlength=50 value="${coach.nick }" placeholder="请输入昵称" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="logoFile" class="col-md-3 control-label"> <span class="text-red">* </span>头像:</label>
					<div class="col-md-6 has-feedback">
						<c:if test="${not empty coach.logo && not empty readonly}">
							<img alt="" src="${coach.logo }"
								<c:if test="${not empty coach.logo }">height="100" </c:if>>
						</c:if>
						<c:if test="${empty readonly }">
							<!-- 上传控件 -->
							<input id="logoFile" type="file" multiple="false" />
							<!-- 保存图片 -->
							<input id="logo" name="logo" type="hidden" value="${coach.logo}" />
							<!-- 显示图片 -->
							<img alt="" src="${coach.logo}" id="logo_img"
								<c:if test="${not empty coach.logo }">height="100" </c:if>>
						</c:if>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="area" class="col-md-3 control-label"> <span
						class="text-red">* </span>所在地区:
					</label>
					<div id="div_area" class="col-md-6 has-feedback form-inline">
						<c:choose>
							<c:when test="${empty readonly }">
								<tags:zone id="areacode" name="areacode" value="${coach.areacode}" disabled="${disable }" />
							</c:when>
							<c:otherwise>
								<tags:zonemap code="${coach.areacode}" />
							</c:otherwise>
						</c:choose>

					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"> <span class="text-red">* </span>性别:</label>
					<div class="col-md-6 has-feedback">
						<label class="radio-inline">
							<input type="radio" name="sex" value="男" checked="checked" <c:if test="${coach.sex =='男'}">checked="checked"</c:if> />男
						</label>
						<label class="radio-inline">
							<input type="radio" name="sex" value="女" <c:if test="${coach.sex == '女'}">checked="checked"</c:if> />女
						</label>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>教练类型:</label>
					<div class="col-md-6 has-feedback">
						<label class="radio-inline"><input type="radio" name="type" value="0" checked="checked" <c:if test="${coach.type == 0}">checked="checked"</c:if> />大课教练</label>
						<label class="radio-inline"><input type="radio" name="type" value="1" <c:if test="${coach.type == 1}">checked="checked"</c:if> />私人教练</label>
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="masterTel" class="col-md-3 control-label"><span class="text-red">* </span>职位:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="position" name="position" value="${coach.position }" placeholder="请输入职位" />
					</div>
				</div>
				
				<div class="form-group form-group-sm">
				<label for="position" class="col-md-3 control-label"><span class="text-red">*</span>级段位:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input type="hidden" id="danlevel_hidden" value="${coach.danlevel }"/> 
					<select class="form-control" name="danlevel" id="danlevel" >
						<option <c:if test="${'白黄带(九级)' == coach.danlevel}">selected</c:if> id="option1" value="白黄带(九级)" >--白黄带(九级)--</option>
						<option <c:if test="${'黄带(八级)' == coach.danlevel}">selected</c:if> id="option2" value="黄带(八级)" >--黄带(八级)--</option>
						<option <c:if test="${'黄绿带(七级)' == coach.danlevel}">selected</c:if> id="option7" value="黄绿带(七级)" >--黄绿带(七级)--</option>
					    <option <c:if test="${'绿带(六级)' == coach.danlevel}">selected</c:if> id="option8" value="绿带(六级)" >--绿带(六级)--</option>
						<option <c:if test="${'绿蓝带(五级)' == coach.danlevel}">selected</c:if> id="option9" value="绿蓝带(五级)" >--绿蓝带(五级)--</option>
						<option <c:if test="${'蓝红带(三级)' == coach.danlevel}">selected</c:if> id="option10" value="蓝红带(三级)" >--蓝带(四级)--</option>
						<option <c:if test="${'红带(二级)' == coach.danlevel}">selected</c:if> id="option11" value="红带(二级)" >--红带(二级)--</option>
						<option <c:if test="${'红黑带(一级)' == coach.danlevel}">selected</c:if> id="option11" value="红黑带(一级)" >--红黑带(一级)--</option>
						<option <c:if test="${'黑带一段' == coach.danlevel}">selected</c:if> id="option11" value="黑带一段" >--黑带一段--</option>
						<option <c:if test="${'黑带二段' == coach.danlevel}">selected</c:if> id="option11" value="黑带二段" >--黑带二段--</option>
						<option <c:if test="${'黑带三段' == coach.danlevel}">selected</c:if> id="option11" value="黑带三段" >--黑带三段--</option>
						<option <c:if test="${'黑带四段' == coach.danlevel}">selected</c:if> id="option11" value="黑带四段" >--黑带四段--</option>
						<option <c:if test="${'黑带五段' == coach.danlevel}">selected</c:if> id="option11" value="黑带五段" >--黑带五段--</option>
						<option <c:if test="${'黑带六段' == coach.danlevel}">selected</c:if> id="option11" value="黑带六段" >--黑带六段--</option>
						<option <c:if test="${'黑带七段' == coach.danlevel}">selected</c:if> id="option11" value="黑带七段" >--黑带七段--</option>
						<option <c:if test="${'黑带八段' == coach.danlevel}">selected</c:if> id="option11" value="黑带八段" >--黑带八段--</option>
						<option <c:if test="${'黑带九段' == coach.danlevel}">selected</c:if> id="option11" value="黑带九段" >--黑带九段--</option>
					</select>
			  	</div>
			 </div>

				<div class="form-group form-group-sm">
					<label for="masterName" class="col-md-3 control-label"><span class="text-red">* </span>教龄:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="teachyear" maxlength=6 name="teachyear" value="${coach.teachyear}" placeholder="请输入教练的教练" />
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="masterName" class="col-md-3 control-label"><span class="text-red">* </span>电话:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="phone" name="phone" value="${coach.phone }" placeholder="请输入教练电话" />
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>是否身份认证:</label>
					<div class="col-md-6 has-feedback">
						<label class="radio-inline"><input type="radio" name="iscard" value="1" checked="checked"<c:if test="${coach.iscard == 1}">checked="checked"</c:if> />是</label> 
						<label class="radio-inline"><input type="radio" name="iscard" value="0" <c:if test="${coach.iscard == 0}">checked="checked"</c:if> />否</label>
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>是否级/段位认证:</label>
					<div class="col-md-6 has-feedback">
						<label class="radio-inline"><input type="radio" name="isdan" value="1" checked="checked" <c:if test="${coach.isdan == 1}">checked="checked"</c:if> />是</label> 
						<label class="radio-inline"><input type="radio" name="isdan" value="0" <c:if test="${coach.isdan == 0}">checked="checked"</c:if> />否</label>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label">
					<span class="text-red"></span>个人介绍:</label>
					<div class="col-md-6 has-feedback">
						<textarea ${readonly } style="height: 100px;" class="form-control"
							rows="5" id="helfexper" placeholder="请输入个人简介" name="helfexper">${coach.helfexper}</textarea>
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label"><span class="text-red"></span>执教成果:</label>
					<div class="col-md-6 has-feedback">
						<textarea ${readonly } style="height: 100px;" class="form-control"
							rows="5" id="tearchexper" placeholder="请输入执教成果"
							name="tearchexper">${coach.tearchexper}</textarea>
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label"><span class="text-red"></span>专长:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control"
							id="specialty" name="specialty" value="${coach.specialty }" placeholder="请输入专长" />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label"><span class="text-red"></span>学员数量:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="students"
							name="students" value="${coach.students}" placeholder="请输入学员数量" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="photosFile" class="col-md-3 control-label">上传教练照片:</label>
					<div class="col-md-6" name="coach_img" style="width: 135px;">
						<%-- <c:if test="${param.action == 'edit' }"> --%>
						<input id="photo1File" type="file" multiple="false" />
						<%-- </c:if> --%>
						<c:set var="count" value="1" />
					</div>
					<span name="picSpan" style="color: red; line-height: 30px;">（标准尺寸：640px
						* 300px） </span>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="photosFile" class="col-md-3 control-label"> </label>
					<div class="col-md-6" name="coach_img">
						<c:set var="count" value="1" />
						<c:if test="${not empty coach.photos}">
							<c:forEach items="${fn:split(coach.photos,'__') }" var="itm"
								varStatus="s">
								<c:if test="${s.last}">
									<c:set var="count" value="${s.index + 2}" />
								</c:if>
								<div style="float: left; margin-right: 10px;">
									<input id="photo${s.index + 1}" name="photo" value="${itm }"
										type="hidden" /> <img alt="" src="${itm}"
										id="photo_img${s.index + 1}" height="100" width="130" />
									<div aria-hidden="true" class="img_close">&times;</div>
								</div>
							</c:forEach>
							<c:forEach var="i" begin="${count}" end="5" step="1">
								<div style="float: left; margin-right: 10px;">
									<input id="photo${i}" name="photo" type="hidden" /> <img
										alt="" src="" id="photo_img${i}" />
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
						<c:if test="${param.action == 'create' }">
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
				<hr />
				<div class="form-group">
					<div class="col-md-offset-3 col-md-3">
						<a href="${ctx }/coach" class="btn btn-default btn-block"> 返回
						</a>
					</div>
					<c:if test="${empty readonly }">
						<div class="col-md-3">
							<button type="submit" id="submit_btn"
								class="btn btn-primary btn-block">保存</button>
						</div>
					</c:if>
				</div>
			</fieldset>
		</form>
	</div>
</div>


<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/project_js.js"></script>
<script src="${ctx}/static/js/bankData.js"></script>
<script type="text/javascript">
	$('#sel_captain').on('click', function() {
		$("#myDlgBody_lg").load("${ctx}/common/statium_query_dlg");
		$("#myDlg_lg").modal();
	})

	 function captainAddCallBack(dgId, a2) {
		$("#dgId").val(dgId);
		$("#statium").val(a2);
	} 

	function trim(str) {
		return str.replace(/(\s)/g, "");
	}
	$(function() {
		menu.active('#coach-man');
		$("div[name = coach_img]").on(
				"click",
				'.img_close',
				function() {
					$(this).parent().find("input").val("");
					$(this).parent().find("img").attr("src", "").attr("height",
							"0").attr("width", "0");
					$(this).remove();
				});
		$("#dgNameClean").click(function() {
			$("#statium").val("");
			$("#dgId").val("");
		});
	});

	$(function() {
		$(window).load(function() {
			if ('${isExist }') {
				alert('${isExist }');
			}
		});
	});
	if (!$("#id").val()) {
		$("#logoFile").addClass("required");
	}
	$(function() {
		$('#inputForm').validate({
			submitHandler : function(form) {
				//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
				/* getData(function(data) {
					app.disabled("#submit_btn");
					$("#priceTemps").val(data);
					
				}); */
				form.submit();
			},
			rules : {
				dgName : {
					required : true,
					maxlength : 20
				},
				name : {
					required : true
				},
				phone : {
					required : true,
					integer : true,
					isMobile : true
				},
				position : {
					required : true
				},
				danlevel : {
					required : true
				},
				teachyear : {
					integer: true,
					required : true
				},
				logo : {
					required : true
				},
				students :{
					required:true,
					integer: true
				}
			},
			messages : {

			}
		});

	});
	$(function() {
		upload({
			'id' : 'logoFile',
			'icon_img' : 'logo_img',
			'icon' : 'logo'
		}); // 球馆LOGO

		multipleUpload({
			'id' : 'photo1File',
			'icon_img' : 'photo_img',
			'icon' : 'photo',
			'limit' : 5
		}); // 

		//upload({'id':'masterIdCardImg1','icon_img':'masterIdCardImg1File_img','icon':'masterIdCardImg1File'});  // 身份证正面
		//upload({'id':'masterIdCardImg2','icon_img':'masterIdCardImg2File_img','icon':'masterIdCardImg2File'});  // 身份证反面
		//upload({'id':'bankLicenseImg','icon_img':'bankLicenseImgFile_img','icon':'bankLicenseImgFile'});  // 开户银行许可证
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
			buttonText : '选择头像',
			height : 30,
			multi : false,
			swf : '${ctx}/static/uploadify/uploadify.swf',
			//提交到指定的 controller,写死即可，已封装
			uploader : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
			width : 100,
			fileTypeExts : '*.gif;*.jpg;*.jpeg;*.png',
			//上传成功后回调此函数
			onUploadSuccess : function(file, data, response) {
				//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if (data.success == true) {
					$('#' + icon_img).attr('src', data.url).css({
						width : '100px',
						height : '100px;'
					});
					$('#' + icon).val(data.url);
				}
			}
		});
	}

	function multipleUpload(option) {
		var id = option.id || "icon_upload";
		var height = option.height || 40;
		var width = option.width || 120;
		var icon_img = option.icon_img || "icon_img";
		var icon = option.icon || "icon";
		var limit = option.limit || 1;
		$("#photo1File")
				.uploadify(
						{
							//文件提交到 controller 里的文件对象的 key 
							fileObjName : 'upfile',
							queueSizeLimit : limit,
							multi : true,
							//按钮名称
							buttonText : '选择图片',
							height : 30,
							swf : '${ctx}/static/uploadify/uploadify.swf',
							//提交到指定的 controller,写死即可，已封装
							uploader : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
							width : 100,
							fileTypeExts : '*.gif;*.jpg;*.jpeg;*.png',
							overrideEvents : [ 'onDialogClose', 'onSelectError' ],
							//上传成功后回调此函数
							onUploadSuccess : function(file, data, response) {
								//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
								data = JSON.parse(data);
								if (data.success == true) {
									$("input[name=photo]")
											.each(
													function(i) {
														var photo = $(this)
																.val();
														if (!photo) {
															var index = i + 1;
															$(
																	'#'
																			+ icon_img
																			+ index)
																	.attr(
																			'src',
																			data.url)
																	.attr(
																			{
																				"height" : "100",
																				"width" : "130"
																			});
															$(
																	'#'
																			+ icon
																			+ index)
																	.val(
																			data.url);
															$(
																	'#'
																			+ icon
																			+ index)
																	.parent()
																	.append(
																			"<div aria-hidden='true' class='img_close'>&times;</div>");
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
	$(function() {
		$("#div_area select:eq(2)").each(function() {
			$(this).addClass("required");
		});
	})
</script>