<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>投票管理</title>
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
				<li>投票主题管理</li>
				 <c:choose>
				  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
				  		<c:set var="disable" value="false"/>
				  		<li class="active"><c:if test='${empty event.id}'> 新建投票</c:if>
						<c:if test='${!empty event.id}'> 修改投票</c:if></li>
				  	</c:when>
				  	<c:otherwise>
						<c:set var="disable" value="true"/>
						<c:set var="readonly" value="readonly='readonly'"/>
						<li class="active"> 查看投票</li>
				  	</c:otherwise>
				  </c:choose> 
				
			</ul>
		</div>
		<!-- / 右侧标题 -->
		<form id="inputForm" action="${ctx}/vote/save" method="post" class="form-horizontal">
			<input id="id" name="id" value="${info.id}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>投票主题信息</small>
					</legend>
					<div class="row">
						 <div class="col-sm-12">
							<div class="form-group form-group-sm">
								<label for="title" class="col-md-3 control-label"><span
									class="text-red">* </span>标题:</label>
								<div class="col-md-6 has-feedback form-inline">
									<div class="input-group">
										<input ${readonly } type="text" class="form-control" placeholder="请输入投票标题"
															id="title" name="title" value="${info.title}"/>
									</div>
								</div>
							</div>
						</div>
						<div>
						<div class="form-group form-group-sm">
							<label for="skin" class="col-md-3 control-label"><span
								class="text-red">* </span>主题皮肤:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly }  type="text" class="form-control" placeholder="请输入皮肤路径"
									id="skin" name="skin"
									value="${info.skin}"
							</div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label for="startTime" class="col-md-3 control-label"><span
							class="text-red">* </span>开始时间:</label>
						<div class="col-md-6 has-feedback">
							<div class="input-group">
								<input ${readonly }   type="text" id="startTime" name="startTime"
									placeholder="请填开始时间" value="${info.startTime}"
									class="form-control" data-date-format="yyyy-mm-dd HH"
									onclick="WdatePicker({startDate:'%y-%M-%d %H',dateFmt:'yyyy-MM-dd HH',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" />
								<label for="startTime" class="input-group-addon"><i
									class="fa fa-calendar"></i></label>
							</div>
						</div>
					</div>
						<div class="form-group form-group-sm">
							<label for="endTime" class="col-md-3 control-label"><span
								class="text-red">* </span>结束时间:</label>
							<div class="col-md-6 has-feedback">
								<div class="input-group">
									<input ${readonly }   type="text" id="endTime" name="endTime"
										placeholder="请填结束时间" value="${info.endTime}"
										class="form-control"
										onclick="WdatePicker({startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH',minDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})" />
									<label for="endTime" class="input-group-addon"><i
										class="fa fa-calendar"></i></label>
								</div>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label   class="col-md-3 control-label"><span
									class="text-red">*</span>投票细则:
							</label>
							<div class="col-md-6 has-feedback">
								<textarea name="rules" class="form-control" style="height:100px;">${info.rules}</textarea>
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

			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					if (um.getContentLength(true) == 0) {
						common.showMessage("请填写投票细则！");
						return;
					}
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
		});
	</script>
</body>
</html>
