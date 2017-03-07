<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业用户</title>
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
				<li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
				<li>企业用户</li>
				<c:choose>
					<c:when
						test="${param.action == 'edit' || param.action == 'create'}">
						<c:set var="disable" value="false" />
						<li class="active"><c:if test='${empty info.id}'> 新建服务记录</c:if>
							<c:if test='${!empty info.id}'> 修改服务信息</c:if></li>
					</c:when>
				</c:choose>

			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/company/saveServices" method="post"
			class="form-horizontal" enctype="multipart/form-data">
			<input id="id" name="id" value="${info.id}" type="hidden" />
			<input id="companyId" name="companyId" value="${companyId}" type="hidden">
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>服务内容</small>
					</legend>
					<div class="row">
						<div class="form-group form-group-sm">
						<label for="introduction" class="col-md-12 control-label">
						<div class="col-md-12 has-feedback">
							<script id="myEditorContent" name="content" type="text/plain">${info.content}</script>
						</div>
				    	</div>
					</div>
			</div>
			<div class="form-group">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<a class="btn btn-default btn-block" href="#" id="close_btn"><span
						class="glyphicon glyphicon-remove"></span> 关闭</a>
				</div>
				<c:choose>
					<c:when test="${empty readonly }">
						<div class="col-md-3">
							<button type="submit" id="submit_btn" class="btn btn-primary btn-block" > 保存 </button>
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
	<script type="text/javascript"   
		src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
	<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
	<script src="${ctx}/static/js/project_js.js"></script>
	<script src="${ctx}/static/js/utils.js"></script>
	<script type="text/javascript">
	$(function() {
			menu.active('#company-man');
			$('#adminFooter').hide();
			$("#close_btn").click(function(){
				$("#myDlg_lg").modal('hide');
			});
			
			<%--加载ueditor--%>
			//赛事规则介绍
			var umRules = UE.getEditor('myEditorContent', {
				initialFrameWidth : '850',
				initialFrameHeight : '300',
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
		$('#inputForm').validate({
			ignore : "", // 开启hidden验证， 1.9版本后默认关闭
			submitHandler : function(form) {
				if (umRules.getContentLength(true) == 0) {
					common.showMessage("请填写内容！");
					return;
				}
				app.disabled("#submit_btn");
				//提交表单
				form.submit();
			},
			rules : {
			},
			messages : {

			}
		});
});
</script>
</body>
</html>
