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
				href="${ctx }/statium/"> 场馆列表 </a></li>
			<li class="active">球馆信息</li>
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

		<h3>场馆信息</h3>
		<hr>
		<form id="inputForm" action="${ctx}/statiumFree/save" method="post"
			class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" name="next_page"
				value="/admin/org/statiumForm/${userId }" /> <input type="hidden"
				name="id" value="${statium.id}" />
			<fieldset>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>球馆名称:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="name"
							name="name" value="${statium.name }" placeholder="请输入球馆名称" />
					</div>
				</div>
				<input type="hidden" name="status" value="1"> 
				<input type="hidden" name="isRating" value="1">
				<input type="hidden" name="isSigned" value="0">
				<div class="form-group form-group-sm">
					<label for="areaCode" class="col-md-3 control-label"><span
						class="text-red">* </span>所在地区:</label>
					<div id="div_areaCode" class="col-md-6 has-feedback form-inline">
						<c:choose>
							<c:when test="${empty readonly }">
								<tags:zone id="areaCode" name="areaCode"
									value="${statium.areaCode }"
									disabled="${disable }" />
							</c:when>
							<c:otherwise>
								<tags:zonemap code="${statium.areaCode }" />
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

				<div class="form-group form-group-sm">
					<label for="sportType" class="col-md-3 control-label"><span
						class="text-red">* </span>场馆设施:</label>
					<div class="col-md-9 has-feedback">
						<div class="btn-group" data-toggle="buttons">
							<c:set var="facilitys"
								value="储物柜,更衣室,热水淋浴,商店,室内,停车场,wifi,休息室,夜场灯光,银行卡,驻场裁判,驻场教练,租赁" />
							<c:set var="facilitysLen"
								value="${fn:length(fn:split(facilitys,','))}" />
							<c:choose>
								<c:when test="${facilitysLen % 2 == 0}">
									<c:set var="facilitysBr" value="${facilitysLen / 2}" />
								</c:when>
								<c:otherwise>
									<c:set var="facilitysBr" value="${(facilitysLen + 1) / 2}" />
								</c:otherwise>
							</c:choose>
							<c:forEach items="${facilitys}" var="facility" varStatus="status">
								<c:set var="active" value="" />
								<c:set var="checked" value="" />
								<c:forEach items="${fn:split(statium.facilities,',') }"
									var="obj">
									<c:if test="${obj == facility}">
										<c:set var="active" value="active abc" />
										<c:set var="checked" value="checked" />
									</c:if>
								</c:forEach>
								<label class="btn btn-default ${active }"> <input
									name="facilities" type="checkbox" autocomplete="off"
									${checked } value="${facility }"> ${facility }
								</label>
								<c:if test="${status.index == facilitysBr}">
									</br>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
					<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传场馆图片:</label>
	         <div class="col-md-6" id="statium_img">
	         	<c:if test="${param.action == 'edit' }">
	         		<input id="photo1File" type="file" multiple="false" />
	         	</c:if>
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
	         	</c:if>
	         	<c:forEach var="i" begin="${count}" end="5" step="1"> 
	         		<div style="float:left;margin-right:10px;">
        				<input id="photo${i}" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img${i}" />
	         		</div>
				</c:forEach>
	         	<c:if test="${param.action == 'create' }">
	         	<div class="has-feedback">
					<input id="photo1File" type="file" multiple="false" />
					<div style="float:left;margin-right:10px;">
						<input id="photo1" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo2" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo3" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo4" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo5" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img5"/>
	           		</div>
	           	</div>				
	    		</c:if>
	         </div>
	      </div>
				<div class="form-group form-group-sm">
					<label for="sportType" class="col-md-3 control-label"><span
						class="text-red">* </span>运动类别:</label>
					<div class="col-md-6 has-feedback">
						<div class="btn-group" id="sportTypeLable">
							<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
								<c:set var="active" value="" />
								<c:set var="checked" value="" />
								<c:forEach items="${fn:split(statium.sportType,';;') }"
									var="obj">
									<c:if test="${obj eq item.itemCode}">
										<c:set var="active" value="active abc" />
										<c:set var="checked" value="checked" />
									</c:if>
								</c:forEach>
								<label class="btn btn-default ${active } ball"
									ballTypeCode="${item.itemCode }" ballType="${item.itemName }">
									${item.itemName } </label>
							</c:forEach>
						</div>
					</div>
				</div>
				<input name="sportType" value="${statium.sportType}" id="sportType"
					type="hidden">
				<hr />
				<div class="form-group">
					<c:if test="${empty readonly }">
						<div class="col-md-offset-3 col-md-3">
							<a href="${ctx }/statiumFree" class="btn btn-default btn-block">
								返回 </a>
						</div>
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

<c:set var="_audit" value="false" />
<shiro:hasRole name="customer">
	<c:set var="_audit" value="true" />
</shiro:hasRole>
<shiro:hasRole name="customer_manager">
	<c:set var="_audit" value="true" />
</shiro:hasRole>
<shiro:hasRole name="admin">
	<c:set var="_audit" value="true" />
</shiro:hasRole>

<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
<script type="text/javascript">
	$(function() {
		menu.active('#statium-man-free');
		
		//删除图片
		$("#statium_img").on("click",'.img_close',function(){
			$(this).parent().find("input").val("");
			$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
			$(this).remove();
		});

		$(".close").click(function() {
			$("#baiduMap").slideUp('slow');
		});
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

		$('#sportTypeLable').on(
				"click",
				".ball",
				function() {
					var ball_type = $(this).attr('ballType'); //球类别
					var ball_type_code = $(this).attr('ballTypeCode'); //类样式区分
					if ($(this).hasClass("active")) {
						$(".ball[balltypecode=" + ball_type_code + "]")
								.removeClass("active abc");
						var sportType = $("#sportType").val().replace(
								ball_type_code + ";;", "");
						$("#sportType").val(sportType);
					} else {
						$("#sportType").val(
								$("#sportType").val() + ball_type_code + ";;");
						$(this).addClass("active abc");
						$(this).attr("class");
					}
				});

		function search(name, map) {
			var local = new BMap.LocalSearch(map, {
				renderOptions : {
					map : map
				}
			});
			local.search(name);
		}

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
		
		multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':5});  // 

	});
	
	
	
	$(function() {
		$(window).load(function() {
			if ('${isExist }') {
				alert('${isExist }');
			}
			
		});
	});

	function multipleUpload(option){
		var id = option.id || "icon_upload";
		var height = option.height|| 40;
		var width = option.width || 120;
		var icon_img = option.icon_img || "icon_img";
		var icon = option.icon || "icon";
		var limit = option.limit || 1;
		$("#photo1File").uploadify({
	        //文件提交到 controller 里的文件对象的 key 
			fileObjName   : 'upfile',
			queueSizeLimit: limit,
			multi         :true,
		    //按钮名称
			buttonText    : '选择图片',
			height        : 30,
			swf           : ctx + '/static/uploadify/uploadify.swf',
		    //提交到指定的 controller,写死即可，已封装
		    uploader      : ctx + '/uploader',
			width         : 100,
			fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
			overrideEvents : [ 'onDialogClose','onSelectError' ],
			//上传成功后回调此函数
		    onUploadSuccess : function(file, data, response) {
		        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if(data.success==true){
					$("input[name=photo]").each(function(i){
						var photo = $(this).val();
						if(!photo){
							var index = i+1;
							$('#'+ icon_img + index).attr('src',data.url).attr({"height":"100","width":"130"});
							$('#'+ icon + index ).val(data.url);
							$('#'+ icon + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
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
		$("#div_areaCode select:eq(2)").each(function() {
			$(this).addClass("required");
		});

		$('#inputForm').validate({
			rules : {
				name : {
					required : true,
					maxlength : 30
				},
				address : {
					required : true
				},
				facilities : {
					required : true
				},
				lnglat : {
					required : true,
					isCoordinate : true
				}
			},
			messages : {

			}
		});

	});
</script>