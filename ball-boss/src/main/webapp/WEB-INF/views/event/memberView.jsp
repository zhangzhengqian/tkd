<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil"%>
<%
	String id = SessionUtil.currentUserId();
%>
<div class="panel panel-default">
	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span> 用户管理</li>
			<li class="active">用户详情</li>
			<c:if test="${param.action == 'view' }">
				<li class="active">用户详情</li>
			</c:if>
		</ul>
	</div>
	<!-- / 右侧标题 -->
	<c:set var="readonly" value=" " />

	<div class="panel-body">
		<!-- 右侧主体内容 -->
		<fieldset>
			<legend>
				<small>用户详情</small>
			</legend>
			<form id="inputForm" action="" method="post" class="form-horizontal"
				enctype="multipart/form-data">
				<zy:token />
				<input type="hidden" id="id" name="id" value="${user.id }" />
				<fieldset>
					<c:if test="${readonly!=' '}">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red"></span>个人头像:</label>
							<div class="col-md-1 has-feedback">
								<img src="${user.photo }"
									style="width: 128px; height: 128px; display: block; margin-left: auto; margin-right: auto;" />
							</div>
						</div>
					</c:if>

					<c:if test="${readonly==' '}">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red"></span>个人头像:</label>
							<div id="title" class="col-md-1 has-feedback">
								<img id="icon_img" src="${user.photo }"
									style="width: 128px; height: 128px; display: block; margin-left: auto; margin-right: auto;" />
							</div>
						</div>
					</c:if>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>球友号:</label>
						<div class="col-md-6 has-feedback">
							<input type="text" class="form-control" id="qiuyouNo"
								name="qiuyouNo" style="width: 480px" value="${user.qiuyouno }"
								readonly />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>用户昵称:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control"
								id="nickName" name="nickName" style="width: 480px"
								value="${user.nickName }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>用户姓名:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="name"
								name="name" style="width: 480px" value="${user.name }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>用户性别:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="sex"
								name="sex" style="width: 480px" value="${user.sex }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>会员等级:</label>
						<div class="col-md-6 has-feedback">
							<input type="text" class="form-control" id="level" name="level"
								style="width: 480px" value="${user.level }" readonly />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label class="col-md-3 control-label"><span
							class="text-red"></span>出生日期:</label>
						<div class="col-sm-4">
							<div class="input-group col-md-6 has-feedback">
								<input value="${user.birthday }" type="text" name="bDate"
									id="bDate" class="form-control"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
								<label for="bDate" class="input-group-addon"><i
									class="fa fa-calendar"></i></label>
							</div>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>所在地区:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="city"
								name="city" style="width: 480px" value="${user.city }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>家庭住址:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="address"
								name="address" style="width: 480px" value="${user.address }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>身份证号:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="cardId"
								name="cardId" style="width: 480px" value="${user.cardId }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>注册手机:</label>
						<div class="col-md-6 has-feedback">
							<input type="text" class="form-control" id="phone" name="phone"
								style="width: 480px" value="${user.phone }" readonly />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>电子邮箱:</label>
						<div class="col-md-6 has-feedback">
							<input ${readonly } type="text" class="form-control" id="email"
								name="email" style="width: 480px" value="${user.email }" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>爱好:</label>
						<div class="col-md-6 has-feedback">
							<input type="text" class="form-control" id="tags" name="tags"
								style="width: 480px" value="${user.tags }" readonly />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>累计消费:</label>
						<div class="col-md-6 has-feedback">
							<input type="text" class="form-control" style="width: 480px"
								value="" readonly />
						</div>
					</div>
				</fieldset>

				<div class="form-group">
					<hr>
					
					<div class="col-md-offset-3 col-md-2">
						<a class="btn btn-default btn-block"
							href="${ctx}/event/memberList/${eventId}"><span
							class="glyphicon"></span> 返回</a>
					</div>
					<c:if test="${status == 0}">
						<div class=" col-md-2">
							<a class="btn btn-default btn-block"
								href="${ctx}/event/updateMemberState/${memberId}/1"><span
								class="glyphicon glyphicon-ok"></span> 审核通过</a>
						</div>

						<div class="col-md-2">
							<a class="btn btn-primary btn-block"
								href="${ctx}/event/updateMemberState/${memberId}/2"><span
								class="glyphicon glyphicon-remove"></span> 审核不通过</a>
						</div>

					</c:if>

					
				</div>
			</form>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		menu.active('#event-man');
		$('#adminFooter').hide();
	});
</script>