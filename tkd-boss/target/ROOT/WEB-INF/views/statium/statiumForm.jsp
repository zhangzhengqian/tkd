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
				href="${ctx }/statium/"> 道馆列表 </a></li>
			<li class="active">道馆信息</li>
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

		<h3>道馆信息</h3>
		<hr>
		<form id="inputForm" action="${ctx}/statium/save" method="post"
			class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${statium.id}" /> 
			<input type="hidden" name="dgId" value="${statium.dgId}" />
			<fieldset>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span
						class="text-red">* </span>道馆名称:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="dgName"
							name="dgName" value="${statium.dgName }" placeholder="请输入道馆名称" />
					</div>
				</div>
				<input type="hidden" name="isRating" value="0">

				<div class="form-group form-group-sm">
					<label for="logoFile" class="col-md-3 control-label"><span
						class="text-red">* </span>道馆LOGO:</label>
					<div class="col-md-6 has-feedback">
						<c:if test="${not empty statium.logo && not empty readonly}">
							<img alt="" src="${statium.logo }"
								<c:if test="${not empty statium.logo }">height="100" </c:if>>
						</c:if>
						<c:if test="${empty readonly }">
							<!-- 上传控件 -->
							<input id="logoFile" type="file" multiple="false" />
							<!-- 保存图片 -->
							<input id="logo" name="logo" type="hidden"
								value="${statium.logo}" />
							<!-- 显示图片 -->
							<img alt="" src="${statium.logo}" id="logo_img"
								<c:if test="${not empty statium.logo }">height="100" </c:if>>
						</c:if>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="areaCode" class="col-md-3 control-label"><span
						class="text-red">* </span>所在地区:</label>
					<div id="div_areaCode" class="col-md-6 has-feedback form-inline">
						<c:choose>
							<c:when test="${empty readonly }">
								<tags:zone id="areacode" name="areacode"
									value="${statium.areacode }" disabled="${disable }" />
							</c:when>
							<c:otherwise>
								<tags:zonemap code="${statium.areacode }" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="" class="col-md-3 control-label"><span
						class="text-red">*</span>场馆坐标:</label>
					<div class="col-md-6 has-feedback form-inline">
						<div class="input-group">
							<input ${readonly } readOnly type="text" class="form-control"
								id="lnglat" name="lnglat"
								value="<c:if test="${ not empty statium.lng}">${statium.lng },${statium.lat}</c:if>"
								placeholder="经度,纬度" /> <span class="input-group-btn"> <c:if
									test="${empty readonly }">
									<button id="coordinate" class="btn btn-primary btn-sm"
										type="button">坐标/地址 识取工具</button>
								</c:if>
							</span>
						</div>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="address" class="col-md-3 control-label"><span
						class="text-red">* </span>详细地址:</label>
					<div class="col-md-6 has-feedback ">
						<input ${readonly } type="text" class="form-control" id="address"
							name="address" value="${statium.address}" />
					</div>
				</div>

				<div class="form-group form-group-sm" style="display: none;"
					id="baiduMap">
					<label for="" class="col-md-3 control-label"></label>
					<div class="col-md-6 has-feedback form-inline">
						<div class="panel panel-default">
							<div class="panel-heading">
								<input type="text" class="form-control" id="keyword" />
								<button type="button" class="btn btn-primary btn-sm" id="search">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								<button type="button" class="close" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="panel-body">
								<div id="allmap"></div>
							</div>
						</div>
					</div>
				</div>

				<%-- <div class="form-group form-group-sm">
	         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>订场电话:</label>
	         <div class="col-md-6 has-feedback ">
	           <input ${readonly } type="text" class="form-control" id="tel" name="tel" value="${statium.tel }" placeholder="请输入场馆电话,格式: 区号-电话号"/>
	         </div>
	      	</div>
 			--%>
				<div class="form-group form-group-sm">
					<label for="masterName" class="col-md-3 control-label"><span
						class="text-red">* </span>负责人姓名:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="contact"
							name="contact" value="${statium.contact }" />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="masterTel" class="col-md-3 control-label"><span
						class="text-red">* </span>联系电话:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="tel"
							name="tel" value="${statium.tel }" />
					</div>
				</div>



				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label"><span
						class="text-red"></span>道馆介绍:</label>
					<div class="col-md-6 has-feedback">
						<textarea ${readonly } style="height: 100px;" class="form-control"
							rows="5" id="remark" placeholder="道馆介绍" name="remark">${statium.remark }</textarea>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="photosFile" class="col-md-3 control-label">上传道馆图片:</label>
					<div class="col-md-6" id="statium_img">
						<c:if test="${param.action == 'edit' }">
							<input id="photo1File" type="file" multiple="false" />
						</c:if>
						<c:set var="count" value="1" />
						<c:if test="${not empty statium.photos}">
							<c:forEach items="${fn:split(statium.photos,'__') }" var="itm"
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
						</c:if>
						<c:forEach var="i" begin="${count}" end="5" step="1">
							<div style="float: left; margin-right: 10px;">
								<input id="photo${i}" name="photo" type="hidden" /> <img alt=""
									src="" id="photo_img${i}" />
							</div>
						</c:forEach>

						<c:if test="${param.action == 'create' }">
							<div class="has-feedback">
								<input id="photo1File" type="file" multiple="false" />
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
							</div>
						</c:if>
					</div>
				</div>

				<hr />

				<div class="form-group">
					<div class="col-md-offset-3 col-md-3">
						<a href="javascript:window.history.go(-1);"
							class="btn btn-default btn-block"> 返回 </a>
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
	function trim(str) {
		return str.replace(/(\s)/g, "");
	}
	$(function() {
		menu.active('#statium-man');
		$("#myDlgBody_lg").dragmove();
		$("#statium_img").on(
				"click",
				'.img_close',
				function() {
					$(this).parent().find("input").val("");
					$(this).parent().find("img").attr("src", "").attr("height",
							"0").attr("width", "0");
					$(this).remove();
				});

		$("#audit_btn").click(function() {
			var audit = $("#audit").val();
			var reason = $("#reason").val();
			if (audit == 2) {
				if (!reason) {
					myAlert("请填写拒绝原因.");
					return;
				}
			}
			$("#auditForm").submit();
		});

		$(".close").click(function() {
			alert("222");
			$("#baiduMap").slideUp('slow');
		});
		$('#batch_query_btn').on('click', function() {
			$("#myDlgBody_lg").load("${ctx}/common/batch_query_dlg", {
				statiumId : '${statium.id}'
			});
			$("#myDlg_lg").modal();
		})
		$("#coordinate").click(function() {
			$("#baiduMap").show();
			var lnglat = $("#lnglat").val();
			var lng = 116.403867;
			var lat = 39.914113;
			var name = "天安门";
			if (lnglat) {
				var lnglats = lnglat.split(",");
				lng = lnglats[0];
				lat = lnglats[1];
				var name = $("#name").val();
			}
			// 百度地图API功能
			map = new BMap.Map("allmap");
			map.centerAndZoom(new BMap.Point(lng, lat), 14);
			map.setCenter(new BMap.Point(lng, lat));
			var marker1 = new BMap.Marker(new BMap.Point(lng, lat)); //创建标注
			map.addOverlay(marker1); // 将标注添加到地图中
			var infoWindow1 = new BMap.InfoWindow(name);
			marker1.addEventListener("click", function(e) {
				this.openInfoWindow(infoWindow1, false);
			});

			$("#search").click(function() {
				search($("#keyword").val(), map);
			});

			// 获取经纬度和地址
			map.addEventListener("click", function(e) {
				var lnglat = e.point.lng + "," + e.point.lat;
				$("#lnglat").val(lnglat);
				var gc = new BMap.Geocoder();
				gc.getLocation(e.point, function(rs) {
					var addComp = rs.addressComponents;
					var address = '';
					address += addComp.province;
					address += addComp.city;
					address += addComp.district;
					address += addComp.street;
					address += addComp.streetNumber;
					$("#address").val(address);
					//alert("当前定位地址为：" + address);
				});
			});
		});

		function search(name, map) {
			var local = new BMap.LocalSearch(map, {
				renderOptions : {
					map : map
				}
			});
			local.search(name);
		}

		<c:if test="${not empty spacePrices }">
		var data = JSON.parse('${spacePrices}');
		BindAndEditWrap(data, 0);
		<c:if test="${not empty readonly}">
		$("#contentByTemplate").find("input,select,button").attr("disabled",
				true);
		$(".btn_close").hide();
		</c:if>
		</c:if>
		$("input[name=facilities]").parent("label").click(function() {
			if ($(this).hasClass("abc")) {
				$(this).removeClass("abc");
			} else {
				$(this).addClass("abc");
			}
			if ($(this).parent("div").find("label[class*=abc]").size() == 0) {
				//只处理  消除error
				//$(this).parent("div").parent("div").removeClass("has-error").addClass("has-error");
				//$(this).parent("div").parent("div").find("p").remove();
				//$(this).parent("div").parent("div").append("<p id=\"sportTypes-error\" class=\"help-block\">必填字段</p>")
			} else {
				$(this).parent("div").parent("div").removeClass("has-error");
				$(this).parent("div").parent("div").find("p").remove();
			}
		});
		$("#qiuyouRating")
				.blur(
						function() {
							var value = $("#qiuyouRating").val();
							if (value != "") {
								var patrn = new RegExp("^0.[1-9]{1,2}$");
								if (!patrn.exec(value)) {
									$("#qiuyouRating_span")
											.html(
													"<p id='qiuyouRating_p'>折扣只能填一位小数,且值小于0</p>");
								} else {
									$("#qiuyouRating_p").remove();
								}
							}
							if (value == "") {
								$("#qiuyouRating_p").remove();
							}
						});
	});

	$(function() {
		$(window).load(function() {
			if ('${isExist }') {
				alert('${isExist }');
			}

			if ('${statium.id }') {
				$("#logoFile").parent().parent().find("label span").html("");
			} else {
				$("#logoFile").addClass("required");
			}
		});
	});

	$(function() {
		$("#div_areaCode select:eq(2)").each(function() {
			$(this).addClass("required");
		});

		$('#inputForm').validate({
			submitHandler : function(form) {
				//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
				getData(function(data) {
					app.disabled("#submit_btn");
					form.submit();
				});
			},
			rules : {
				dgName : {
					required : true,
					maxlength : 20
				//,remote: "${ctx}/statium/checkName?id=${statium.id}"
				},
				address : {
					required : true
				},
				contact : {
					required : true
				},
				starttime : {
					required : true,
					time23 : true
				},
				endtime : {
					required : true,
					time23 : true
				},
				lnglat : {
					required : true,
					isCoordinate : true
				},
				remark : {
					maxlength : 1500
				},
			/* masterIdCard : {
				required: true
			} */
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
			buttonText : '选择图片',
			height : 30,
			multi : false,
			swf : '${ctx}/static/uploadify/uploadify.swf',
			//提交到指定的 controller,写死即可，已封装
			uploader : '${ctx}/uploadImage',
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
							uploader : '${ctx}/uploadImage',
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
</script>