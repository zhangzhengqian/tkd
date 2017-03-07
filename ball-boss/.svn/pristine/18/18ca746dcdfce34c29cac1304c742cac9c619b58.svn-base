<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网站管理</title>
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
	<c:choose>
		<c:when test="${param.action == 'edit' || param.action == 'create'}">
			<c:set var="disable" value="false" />
		</c:when>
		<c:otherwise>
			<c:set var="disable" value="true" />
			<c:set var="readonly" value="readonly='readonly'" />
		</c:otherwise>
	</c:choose>
	<div class="panel panel-default">

		<div class="panel-heading">
			<!-- 右侧标题 -->
			<ul class="breadcrumb">
				<li><span class="glyphicon glyphicon-home"></span> 网站管理</li>
				<li>公告管理</li>
				<li class="active"><c:if test='${empty info.id}'> 新建公告</c:if>
					<c:if test='${!empty info.id}'> 修改公告</c:if></li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/notice/save" method="post"
			class="form-horizontal">
			<input type="hidden" name="id"  value="${info.id}">
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>公告信息</small>
					</legend>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>标题:</label>
								<div class="col-md-6 has-feedback">
									<c:choose>
										<c:when
											test="${param.action == 'edit' || param.action == 'create'}">
											<input type="text" class="form-control"
												placeholder="请输入公告标题（16个汉子之内）" ${readonly } id="name"
												name="title" value="${info.title}">
										</c:when>
										<c:otherwise>
								${info.title}
						  	</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="introduction" class="col-md-3 control-label"><span
								class="text-red">*</span>公告内容:</label>
							<div class="col-md-6 has-feedback">
								<c:choose>
									<c:when
										test="${param.action == 'edit' || param.action == 'create'}">
										<script id="myEditor" name="content" type="text/plain">${info.content}</script>
									</c:when>
									<c:otherwise>
								  ${info.content}
						     	</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
			</div>
				<div class="form-group">
			<hr>
			<div class="col-md-offset-3 col-md-2">
				<a class="btn btn-default btn-block" href="#" onclick="javascript:history.go(-1)"><span
					class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<c:choose>
				<c:when test="${param.action == 'edit' || param.action == 'create'}">
					<div class="col-md-2">
						<button type="button" class="btn btn-primary btn-block"
							id="submit_btn">
							<span class="glyphicon glyphicon-ok"></span> 保存
						</button>
					</div>
				</c:when>
			</c:choose>
	</div>
			</fieldset>

		</form>
		</div>
	
	<!-- end row -->
	</fieldset>
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
	<script type="text/javascript">
		$(function() {
			menu.active('#notice-man');
			$('#adminFooter').hide();

			function search(name, map) {
				var local = new BMap.LocalSearch(map, {
					renderOptions : {
						map : map
					}
				});
				local.search(name);
			}
	<%--加载ueditor--%>
		//公告内容
			var um = UE.getEditor('myEditor', {
				initialFrameWidth : '620',
				initialFrameHeight : '500',
				elementPathEnabled : false,
				wordCount : false,
				toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo',
						'|', 'bold', 'italic', 'underline', 'fontborder',
						'strikethrough', 'superscript', 'subscript',
						'removeformat', 'formatmatch', 'autotypeset',
						'blockquote', 'pasteplain', '|', 'insertorderedlist',
						'insertunorderedlist', 'selectall', 'cleardoc', '|',
						'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
						'customstyle', 'paragraph', 'fontfamily', 'fontsize',
						'|', 'directionalityltr', 'directionalityrtl',
						'indent', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'justifyjustify', '|', 'touppercase',
						'tolowercase', '|', 'simpleupload', 'insertimage', '|',
						'preview' ] ]
			});
	<%--加载ueditor END--%>
		$("#submit_btn").click(function() {
				$("#inputForm").submit();
			});
		$("#update_btn").click(function() {
			$('#inputForm').attr("action","${ctx }/seo/detailForm?id=${info.id}&action=edit");
			$("#inputForm").submit();
		});
		

			$('#inputForm').validate({
				submitHandler : function(form) {
					//表单验证成功时，锁住提交按钮
					if (um.getContentLength(true) == 0) {
						common.showMessage("请填写公告内容！");
						return;
					}
					app.disabled("#submit_btn");
					//提交表单
					form.submit();
				},
				rules : {
					"title" : {
						required : true,
						minlength : 2,
						maxlength : 16
					},
				},
				messages : {

				}
			});

		});
	</script>
</body>
</html>
