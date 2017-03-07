<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>投票管理</title>
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
<script type="text/javascript">
	$(document).ready(function() {
		$("#dataLoad").hide(); //页面加载完毕后即将DIV隐藏
	});
</script>
<body>
<div id="dataLoad" style="display:none"><!--页面载入显示-->
	<table width=100% height=100% border=0 align=center valign=middle>
		<tr height=50%><td align=center>&nbsp;</td></tr>
		<tr><td align=center><img src="${ctx}/static/img/loading-gif.gif"/></td></tr>
		<tr height=50%><td align=center>&nbsp;</td></tr>
	</table>
</div>
<div class="panel panel-default">

	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span>投票管理</li>
			<li>投票候选人管理</li>
			<c:choose>
				<c:when test="${param.action == 'edit' || param.action == 'create'}">
					<c:set var="disable" value="false"/>
					<li class="active"><c:if test='${empty event.id}'> 新建候选人</c:if>
						<c:if test='${!empty event.id}'> 修改候选人</c:if></li>
				</c:when>
				<c:otherwise>
					<c:set var="disable" value="true"/>
					<c:set var="readonly" value="readonly='readonly'"/>
					<li class="active"> 查看候选人</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
	<!-- / 右侧标题 -->
	<form id="inputForm" action="${ctx}/vote/saveMember" method="post" class="form-horizontal">
		<input id="id" name="id" value="${info.id}" type="hidden" />
		<div class="panel-body">
			<!-- 右侧主体内容 -->
			<fieldset>
				<legend>
					<small>投票候选人信息</small>
				</legend>
				<div class="row">
					<input name="themeId" type="hidden" id="themeId" value="${info.themeId }">
					<div class="col-sm-12">
						<div class="form-group form-group-sm">
							<label for="userName" class="col-md-3 control-label"><span
									class="text-red">* </span>候选人姓名:</label>
							<div class="col-md-6 has-feedback form-inline">
								<div class="input-group">
									<input ${readonly } type="text" class="form-control" placeholder="请输入候选人姓名"
														id="userName" name="userName" value="${info.userName}"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group form-group-sm">
							<label for="userName" class="col-md-3 control-label"><span
									class="text-red">* </span>候选人描述:</label>
							<div class="col-md-6 has-feedback form-inline">
								<div class="input-group">
									<input ${readonly } type="text" class="form-control" placeholder="请输入候选人描述"
														id="des" name="des" value="${info.des}"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="form-group form-group-sm">
							<label for="userName" class="col-md-3 control-label"><span
									class="text-red">* </span>候选人手机号:</label>
							<div class="col-md-6 has-feedback form-inline">
								<div class="input-group">
									<input ${readonly } type="text" class="form-control" placeholder="请输入候选人手机号"
														id="userPhone" name="userPhone" value="${info.userPhone}"/>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="userPhoto" class="col-md-3 control-label"><span
								class="text-red">*</span>候选人头像:</label>
						<div class="col-md-6 has-feedback">
<c:if test="${param.action == 'edit' || param.action == 'create'}" >	<input id="photoFile" type="file" multiple="false" /></c:if>
							<!-- 保存图片 -->
							<input id="userPhoto" name="userPhoto" type="hidden" value="${info.userPhoto}" />
							<!-- 显示图片 -->
							<img alt=""
								 style="<c:if test='${!empty info.userPhoto}'>width:128px;height:128px;</c:if>"
								 src="${info.userPhoto}" id="userPhoto_img">
						</div>
					</div>
				</div>
		</div>
		<div class="form-group">
			<hr>
			<c:if test="${empty readonly }">
				<div class="col-md-offset-1 col-md-3">
					<button type="submit" id="submit_btn" class="btn btn-primary btn-block" > 保存 </button>
				</div>
			</c:if>
			<div class="col-md-offset-2 col-md-3">
				<a class="btn btn-default btn-block" href="javascript:history.go(-1)"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
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
		menu.active('#voteTheme-man');
		$('#adminFooter').hide();

		upload({
			'id' : 'photoFile',
			'icon_img' : 'userPhoto_img',
			'icon' : 'userPhoto'
		}); // LOG
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
		$('#inputForm').validate({
			ignore : "", // 开启hidden验证， 1.9版本后默认关闭
			submitHandler : function(form) {
				app.disabled("#submit_btn");
				//提交表单
				form.submit();
			},
			rules : {
				"title" : {
					required : true
				},
				"skin" : {
					required : true
				},
				"startTime" : {
					required : true
				},
				"endTime" : {
					required : true
				},
			},
			messages : {

			}
		});

		//====================================================
		// 自动匹配 投票主题 >>>>
		//====================================================
		$('#themeName').autocomplete('${ctx}/common/search_voteTheme_by_name?flag=true',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
			max: 30,
			autoFill: false,
			mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
			matchContains: true,
			scrollHeight: 220,
			width: $('#themeName').outerWidth(),
			multiple: false,
			formatItem: function (row, i, max) {                    //显示格式
				return "【"+row.title+"】";
			},
			formatResult: function (row) {                      //返回结果
				return row.title;
			},
			handleValue:function(id){
				$('#themeId').val(id.split("_")[0]);
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
		// 自动匹配 投票主题 <<<<
		//====================================================
	});
</script>
</body>
</html>
