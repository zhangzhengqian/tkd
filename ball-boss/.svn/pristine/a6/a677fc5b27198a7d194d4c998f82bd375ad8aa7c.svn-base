<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>乐享赛管理</title>
<style type="text/css">
#title div {
	margin-left: auto;
	margin-right: auto;
}

.blank {
	clear: both;
	height: 10px;
	line-height: 10px;
	visibility: hidden;
}

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
<script type="text/javascript"
	src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/jquery.ztree.helper.js"></script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${ctx}/static/ueditor/ueditor.all.js"></script>
</head>

<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span>乐享赛管理</li>
				<li>站点管理</li>
				 <c:choose>
				  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
				  		<c:set var="disable" value="false"/>
				  		<li class="active"><c:if test='${empty info.id}'> 新增站点</c:if>
						<c:if test='${!empty info.id}'> 修改站点</c:if></li>
				  	</c:when>
				  	<c:otherwise>
						<c:set var="disable" value="true"/>
						<c:set var="readonly" value="readonly='readonly'"/>
						<li class="active"> 查看站点</li>
				  	</c:otherwise>
				  </c:choose> 
				
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/enjoyRace/saveSite" method="post" class="form-horizontal">
			<input id="id" name="id" value="${info.id}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>站点信息</small>
					</legend>
					<div class="row">
						<div class="form-group form-group-sm">
							<label for="areaCode" class="col-md-3 control-label"><span
								class="text-red">* </span>城市:</label>
							<div id="div_areaCode" class="col-md-6 has-feedback form-inline">
								<c:choose>
									<c:when test="${empty readonly }">
										<tags:zoneCity id="code" name="code"
											value="${info.code }" disabled="${disable }" />
									</c:when>
									<c:otherwise>
										<tags:zoneCity name="code" value="${info.code }" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<input type="hidden" name="statiumId" id="statiumId" value="${info.statiumId }"/>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>场馆名称:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly }  type="text" class="form-control" placeholder="请输入场馆名称"
									id="statiumName" name="statiumName"
									value="${info.statiumName}"
							</div>
						</div>
				 	 </div>
				 	 <div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">*</span>场馆海报:</label>
							<div class="col-md-6 has-feedback">
								<input id="logoFile" type="file" multiple="false" />
								<!-- 保存图片 -->
								<input id="logo" name="logo" type="hidden" value="${info.logo}" />
								<!-- 显示图片 -->
								<img alt=""
									style="<c:if test='${!empty info.logo}'>width:128px;height:128px;</c:if>"
									src="${info.logo}" id="logo_img">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>赛事名称:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly }  type="text" class="form-control" placeholder="请输入赛事名称如：2016’球友圈杯中国业余网球公开赛"
													 id="gameTitle" name="gameTitle"
													 value="${info.gameTitle}"
							</div>
						</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>简称:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly }  type="text" class="form-control" placeholder="请输入简称如：（CTA-Open）乐享赛水立方管"
													 id="shortTitle" name="shortTitle"
															 value="${info.shortTitle}"
									</div>
								</div>
						</div>
				 	 <div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>可发布赛事次数:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly }  type="text" class="form-control" placeholder="请输入次数"
									id="num" name="num"
									value="${info.num}"/>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>授权开始时间:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" 
									id="start" name="start" 
									onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'end\')||\'%y-%M-%d\'}'})"
									value="${info.start}"/>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>授权结束时间:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" 
									id="end" name="end" 
									onfocus="WdatePicker({firstDayOfWeek:1,minDate:'#F{$dp.$D(\'start\')||\'%y-%M-%d\'}'})"
									value="${info.end}"/>
							</div>
						</div>
					</div>
					<div class="form-group">
		<hr>
		<div class="col-md-offset-3 col-md-2">
			<a class="btn btn-default btn-block" href="${ctx}/enjoyRace/siteList"><span
				class="glyphicon glyphicon-remove"></span> 取消</a>
		</div>
		<c:choose>
			<c:when test="${empty readonly }">
				<div class="col-md-3">
		    		<button type="submit" id="submit_btn" class="btn btn-primary btn-block" >确定 </button>
					</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		</div>
		</fieldset>
		</form>
	</div>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
	<script src="${ctx}/static/js/project_js.js"></script>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
		$(function() {
			menu.active('#enjoyraceSite-man');
			$('#adminFooter').hide();
			 if ('${info.id }') {
					$("#logoFile").parent().parent().find("label span").html("");
				} else {
					$("#logoFile").addClass("required");
				}
			upload({
				'id' : 'logoFile',
				'icon_img' : 'logo_img',
				'icon' : 'logo'
			}); // LOGO
			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					app.disabled("#submit_btn");
					//提交表单
					form.submit();
				},
				rules : {
					"statiumName" : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					"code" : {
						required : true
					}
				},
				messages : {

				}
			});
			
			//场馆名
			$("#statiumName").blur(function(){
				var statiumName = $("#statiumName").val();
				var data = {"statiumName":statiumName};
				if($('#statiumId').val() == null || $('#statiumId').val() == "" || statiumName == null && statiumName == ""){
					$("#statiumName").val("");
					$("#statiumId").val("");
				}else{
					//根据场馆名字查询场馆是存在
					$.ajax({
						url: "${ctx}/enjoyRace/checkStatium",
						type: "post",
						async: false,
						data: data,
						dataType: 'json',
						success:function(data){
							if (!data.result){
								myAlert("场馆不存在，请输入后从下拉项选择！");
								$("#statiumName").val("");
								$('#statiumId').val("")
							}
						}
					});
				}
			});

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
							width : '100px',
							height : '100px;'
						});
						$('#' + icon).val(data.url);
					}
				}
			});
		}
		
		 //====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
            max: 30,
            autoFill: false,
            mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
            matchContains: true, 
            scrollHeight: 220,
            width: $('#statiumName').outerWidth(),
            multiple: false,
            formatItem: function (row, i, max) {                    //显示格式
                return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
            },
            formatResult: function (row) {                      //返回结果
                return row.name;
            },
            handleValue:function(id){
            	$('#statiumId').val(id);
            },
            parse:function(data){
            	var parsed = [];
        		var rows = data;
        		for (var i=0; i < rows.length; i++) {
        			var row = rows[i];
        			if (row) {
        				parsed[parsed.length] = {
        					data: row,
        					value: row["id"],
        					result: this.formatResult(row)
        				};
        			}
        		}
        		return parsed;
            }
		});
		//====================================================
		// 自动匹配 场馆名称 <<<<
		//====================================================
	</script>
</body>
</html>
