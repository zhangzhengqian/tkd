<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span><a
				href="${ctx }/statiumClass/"> 视频集 </a></li>
			<li class="active">视频集信息</li>
		</ul>
	</div>
	<!-- / 右侧标题 -->

	<c:choose>
		<c:when test="${action == 'edit' || action == 'create'}">
			<c:set var="disable" value="false" />
		</c:when>
		<c:otherwise>
			<c:set var="disable" value="true" />
			<c:set var="readonly" value="readonly='readonly'" />
		</c:otherwise>
	</c:choose>

	<div class="panel-body">
		<!-- 右侧主体内容 -->
		<h3>视频集信息</h3>
		<hr>
		<form id="inputForm" action="${ctx}/video/save" method="post"
			class="form-horizontal" enctype="multipart/form-data">
				<input type = "hidden" name = "id" value="${videoGroup.id }">
				<div class="form-group form-group-sm">
					<label class="col-md-3 control-label"><span
						class="text-red">* </span>视频集名称:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="videoName"
							name="videoName" value="${videoGroup.videoName }" placeholder="请输入视频集名称" />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label class="col-md-3 control-label"><span
							class="text-red">* </span>视频集数:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="videoNum"
											name="videoNum" value="${videoGroup.videoNum }" placeholder="请输入视频集数" />
					</div>
				</div>

				
				<div class="form-group form-group-sm">
						<div class="col-md-offset-3 col-md-3">
							<a href="javascript:window.history.go(-1);" class="btn btn-default btn-block">
								返回 </a>
						</div>
					<!-- readOnly为空输出保存 -->
					<c:if test="${empty readonly }">
						<div class="col-md-3">
							<button type="submit" id="submit_btn"
								class="btn btn-primary btn-block">保存</button>
						</div>
					</c:if>
				</div>
		</form>
	</div>
</div>

<script type="text/javascript">

	$(function() {
		menu.active('#video-man');

		$('#inputForm').validate({
			rules : {
				videoName : {
					required : true
				},
				sort: {
					required: true,
					digits: true,
					range:[0,999],
				}
			},
			messages : {
				sort: {
					digits:"必须为正整数",
					range: "请输入一个介于 {0} 和 {999} 之间的值"
				}
			}
		});

	});

</script>