<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>课程列表</title>
</head>
<body>
<!-- 导航 -->
<%@include file="infoNav.jsp"%>
<form id="statiumInfoForm" action="${ctx}/infoManage/save" method="post" class="form-horizontal" enctype="multipart/form-data">
	<div class="formTable">
		<input type="hidden" id="status" name="status">
		<input type="hidden" id="id" name="id" value="${statium.id }">
		<ul>
			<li>
				<span class="title">球馆名称：</span>
				<input type="text" id="dgName" name="dgName" value="${statium.dgName }">
			</li>
			<li>
				<span class="title">球馆LOGO：</span>
				<div class="col-md-6 has-feedback">
					<!-- 上传控件 -->
					<input id="logoFile" type="file" multiple="false" />
					<!-- 保存图片 -->
					<input id="logo" name="logo" type="hidden" value="${statium.logo}" />
					<!-- 显示图片 -->
					<img alt="" src="${statium.logo}" id="logo_img" <c:if test="${not empty statium.logo }">height="100" </c:if>>
				</div>
			</li>
			<li>
				<span class="title">所在地区：</span>
					<tags:zone id="areacode" name="areacode" value="${statium.areacode }" disabled="${disable }" />
			</li>
			<li>
				<span class="title">场馆坐标：</span>
				<input type="text" id="lnglat" name="lnglat"
					   value="<c:if test="${ not empty statium.lng}">${statium.lng },${statium.lat}</c:if>">
				<button id="coordinate" class="btn coordBtn btn-primary btn-sm" type="button">坐标/地址 识取工具</button>
			</li>
			<li>
				<span class="title">详细地址：</span>
				<input type="text" id="address" name="address" value="${statium.address}" >
			</li>
			<li>
				<div class="form-group form-group-sm" style="display: none;" id="baiduMap">
					<label class="col-md-3 control-label"></label>
					<div class="col-md-6 has-feedback form-inline">
						<div class="panel panel-default">
							<div class="panel-heading">
								<input type="text" class="form-control" id="keyword" />
								<button type="button" class="btn btn-primary btn-sm" id="search">
									<span class="glyphicon glyphicon-search"></span> 搜 索
								</button>
								<button type="button" class="close" aria-label="Close" id="closeMap">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="panel-body">
								<div id="allmap"></div>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<span class="title">道馆联系人：</span>
				<input type="text" id="contact" name="contact" value="${statium.contact }" />
			</li>
			<li>
				<span class="title">馆长电话：</span>
				<input type="text" id="tel" name="tel" value="${statium.tel }" />
			</li>
			<li>
				<span class="title">前台电话：</span>
				<input type="text" id="serviceTel" name="serviceTel" value="${statium.serviceTel }" />
			</li>
			<li>
				<span class="title">开户人：</span>
				<input type="text" id="bankAccountName" name="bankAccountName" value="${statium.bankAccountName }" />
			</li>
			<li>
				<span class="title">开户账号：</span>
				<input type="text" id="bankAccountNo" name="bankAccountNo" value="${statium.bankAccountNo }" >
			</li>
			<li>
				<span class="title">开户银行：</span>
				<input type="text" id="bankAccountBranchName" name="bankAccountBranchName" value="${statium.bankAccountBranchName }" >
			</li>
			<li>
				<span class="title">球馆介绍：</span>
				<textarea class="form-control" rows="4" id="remark"s name="remark">${statium.remark }
				</textarea>
			</li>
			<li>
				<span class="title">道馆二维码：</span>
				<img src="${statium.qrCode }" id="img">
				<input value="${statium.qrCode }" type="hidden" id="qrCode1"/>
			</li>
			<li>
				<span class="title">场馆图片：</span>
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
						<c:if test="${not empty statium.photos}">
							<c:forEach items="${fn:split(statium.photos,'__') }" var="itm" varStatus="s">
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
		$('#info-man').addClass("active");
		$('#STATIUM_INFO').addClass("active");

		$("div[name = statium_img]").on("click",'.img_close',function(){
			$(this).parent().find("input").val("");
			$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
			$(this).remove();
		});
	});

	// 地图
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

	// 关闭地图
	$('#closeMap').click(function () {
		$("#baiduMap").hide();
	})

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
		$("#statiumInfoForm").submit();
	}

</script>

</body>
</html>