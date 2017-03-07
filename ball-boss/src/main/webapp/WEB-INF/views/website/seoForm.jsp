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
				<li>SEO管理</li>
				<li class="active"><c:if test='${empty info.id}'> 新建 </c:if>
					<c:if test='${!empty info.id}'> 修改 </c:if></li>
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm"  method="post" action="${ctx}/seo/save"
			class="form-horizontal">
			<input type = "hidden" name="id" value = "${info.id}"> 
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>公告信息</small>
					</legend>
					<div class="row">
						<div class="col-sm-10">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>类型:</label>
								<div class="col-md-6 has-feedback">
										<select name="type" id="type">
											<option value="0" <c:if test="${'0' == info.type}">selected</c:if>>首页</option>
											<option value="1" <c:if test="${'1' == info.type}">selected</c:if>>订场</option>
											<option value="2" <c:if test="${'2' == info.type}">selected</c:if>>活动</option>
											<option value="3" <c:if test="${'3' == info.type}">selected</c:if>>赛事</option>
											<option value="4" <c:if test="${'4' == info.type}">selected</c:if>>教陪练</option>
											<option value="5" <c:if test="${'5' == info.type}">selected</c:if>>教学</option>
										</select>
								</div>
							</div>
						</div>
						<div class="col-sm-10">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>位置:</label>
								<div class="col-md-6 has-feedback">
										<select name="pageType" id="pageType">
											<option value="" <c:if test="${null == info.pageType}">selected</c:if>>无</option>
											<option value="0" <c:if test="${'0' == info.pageType}">selected</c:if>>列表页</option>
											<option value="1" <c:if test="${'1' == info.pageType}">selected</c:if>>内容页</option>
										</select>
								</div>
							</div>
						</div>
						<div class="col-sm-10">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>seo类型:</label>
								<div class="col-md-6 has-feedback">
										<select name="pageType" id="pageType">
											<option value="0" <c:if test="${0 == info.pageType}">selected</c:if>>Description</option>
											<option value="1" <c:if test="${1 == info.pageType}">selected</c:if>>Keywords</option>
											<option value="2" <c:if test="${2 == info.pageType}">selected</c:if>>Title</option>
										</select>
								</div>
							</div>
						</div>
						<div class="col-sm-10">
							<div class="form-group form-group-sm">
								<label for="name" class="col-md-3 control-label"><span
									class="text-red">* </span>名称:</label>
								<div class="col-md-6 has-feedback">
										<input type="text" value= "${info.title}" name="title">
								</div>
							</div>
						</div>
						<div class="col-sm-10">
						<div class="form-group form-group-sm">
							<label for="introduction" class="col-md-3 control-label"><span
								class="text-red">*</span>内容:</label>
							<div class="col-md-6 has-feedback">
						   			<textarea rows="15" cols="150" id="description" name="description">${info.description}</textarea>
							</div>
						</div>
						</div>
					</div>
			</div>
			<div class="form-group">
			<hr>
			<div class="col-md-offset-3 col-md-2">
				<a class="btn btn-default btn-block" href="#" onclick="javascript:history.go(-1)" ><span
					class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-primary btn-block"
					id="submit_btn">
					<span class="glyphicon glyphicon-ok"></span> 保存
				</button>
			</div>
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
			menu.active('#seo-man');
			$('#adminFooter').hide();
			

		 $("#submit_btn").click(function() {
				$("#inputForm").submit();
		 });

		 $('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					app.disabled("#submit_btn");
					//提交表单
					form.submit();
				},
				rules : {
					"description" : {
						required : true,
					},
				},
				messages : {
				}
		});
	});
	</script>
</body>
</html>
