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
						<li class="active"><c:if test='${empty info.id}'> 新建企业用户</c:if>
							<c:if test='${!empty info.id}'> 修改企业用户</c:if></li>
					</c:when>
					<c:otherwise>
						<c:set var="disable" value="true" />
						<c:set var="readonly" value="readonly='readonly'" />
						<li class="active">查看企业信息</li>
					</c:otherwise>
				</c:choose>

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
		<form id="inputForm" action="${ctx}/company/save" method="post"
			class="form-horizontal" enctype="multipart/form-data">
			<input id="id" name="id" value="${info.id}" type="hidden" />
			<div class="panel-body">
				<!-- 右侧主体内容 -->
				<fieldset>
					<legend>
						<small>企业基本信息</small>
					</legend>
					<div class="row">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label">企业LOGO:</label>
							<div class="col-md-6 has-feedback" style="width: 125px;">
							<c:choose>
							<c:when test="${empty readonly }">
								<input id="logoFile" type="file" multiple="false" />
							</c:when>
							</c:choose>
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
								class="text-red">* </span>企业名称:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly } type="text" class="form-control"
									placeholder="请输入企业名称" id="name" name="name"
									value="${info.name}">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>企业简称:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly } type="text" class="form-control"
									placeholder="请输入企业简称" id="brefName" name="brefName"
									value="${info.brefName}">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="areaCode" class="col-md-3 control-label"><span
								class="text-red">* </span>企业地址:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly } type="text" class="form-control"
									placeholder="请输入企业地址" id="addr" name="addr"
									value="${info.addr}">
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="email" class="col-md-3 control-label"><span
								class="text-red">* </span>企业邮箱:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" id="email" name="email"  ${readonly } 
									value="${info.email }" placeholder="请输入企业邮箱" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="tel" class="col-md-3 control-label"><span
								class="text-red">* </span>企业座机:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" id="tel" name="tel"  ${readonly } 
									value="${info.tel }" placeholder="请输入企业座机" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="chiefJudge" class="col-md-3 control-label"><span
								class="text-red">* </span>联系人:</label>
							<div class="col-md-6 has-feedback ">
								<input ${readonly } type="text" class="form-control"
									id="linkMan" placeholder="请输入联系人" name="linkMan"
									value="${info.linkMan}" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="" class="col-md-3 control-label"><span
								class="text-red">* </span>联系电话:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly } type="text" class="form-control"
									id="linkPhone" name="linkPhone" value="${info.linkPhone }"
									placeholder="请输入联系人电话" />
							</div>
						</div>
					<c:if test="${!empty readonly }">
						<div class="form-group form-group-sm">
							<label for="" class="col-md-3 control-label"><span
									class="text-red">* </span>企业积分:</label>
							<div class="col-md-6 has-feedback">
								<input ${readonly } type="text" class="form-control"
													id="integral" name="integral" value="${info.integral }"
								/>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="" class="col-md-3 control-label"><span
								class="text-red">* </span>员工（人）:</label>
							<label  
									class="col-md-6 control-label label2" style="text-align: left;">${usersCount}</label>
						</div>
					</c:if>
					
					</div>
			  <c:if test="${!empty readonly }">
				<legend>
						<small>企业账户信息</small>
				</legend>
					<div class="row">
						<div class="form-group form-group-sm">
							<label for="name" class="col-md-3 control-label"><span
								class="text-red">* </span>账户余额（元）:</label>
							<label for="name"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${(account.balance)/100}</label>
						<a class="btn btn-primary btn-sm" href="${ctx}/company/employeeOrderList?id=${info.id}">员工消费详情</a>
						</div>
						<div class="form-group form-group-sm">
								<label for="phone" class="col-md-3 control-label"><span
									class="text-red"></span>已消费金额（元）:</label> <label for="name"
									class="col-md-6 control-label label2" style="text-align: left;"><span
									class="text-red"></span>${lf:formatMoney(useAmout)*-1 }	
								</label>	
						</div>
						<div class="form-group form-group-sm">
								<label for="phone" class="col-md-3 control-label"><span
									class="text-red"></span>服务列表:</label> 
						</div>
								<div class="col-table col-md-12"> 
									<table id="contentTable"
										class="table table-bordered table-condensed table-hover">
										<thead class="thead">
											<tr>
												<th class="text-center">序号</th>
												<th>类别</th>
												<th>服务号</th>
												<th>金额（元）</th>
												<th>时间</th>
												<th>销售人</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${servicesList }" var="services" varStatus="status">
											<tr>
												<td class="text-center"> ${status.count}</td>
												<td>
													<c:choose>
										                <c:when test="${services.serviceType==0}">固定包场</c:when>
										                <c:when test="${services.serviceType==1}">活动赛事</c:when>
										                <c:when test="${services.serviceType==2}">账户充值</c:when>
										                <c:when test="${services.serviceType==3}">账户提现</c:when>
										        	</c:choose>
												</td>
												<td>${services.id } </td>
												<td>${lf:formatMoney(services.fee) } </td>
												<td><fmt:formatDate value="${services.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><tags:getUserById id="${services.cb }" /></td>
												<td>
													<c:forEach items="${lf:getStatusNames(services.payType,services.serviceType,services.status,services.flowState)}" varStatus="stat" var="flowStateName">
														<c:if test="${stat.index==0}">【销售经理审核：${flowStateName}】</c:if>
														<c:if test="${stat.index==1}">【客服审核：${flowStateName}】</c:if>
														<c:if test="${stat.index==2}">【财务审核：${flowStateName}】</c:if>
													</c:forEach>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table> 
								</div>
					</div>
					
					<div class="form-group form-group-sm">
								<label for="phone" class="col-md-3 control-label"><span
									class="text-red"></span>充值、提现列表:</label> 
						</div>
								<div class="col-table col-md-12"> 
									<table id="contentTable"
										class="table table-bordered table-condensed table-hover">
										<thead class="thead">
											<tr>
												<th class="text-center">序号</th>
												<th>类别</th>
												<th>服务号</th>
												<th>金额（元）</th>
												<th>时间</th>
												<th>销售人</th>
												<th>线上、线下</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${withDrawAndChargeList }" var="services" varStatus="status">
											<tr>
												<td class="text-center"> ${status.count}</td>
												<td>
													<c:choose>
										                <c:when test="${services.serviceType==0}">固定包场</c:when>
										                <c:when test="${services.serviceType==1}">活动赛事</c:when>
										                <c:when test="${services.serviceType==2}">账户充值</c:when>
										                <c:when test="${services.serviceType==3}">账户提现</c:when>
										        	</c:choose>
												</td>
												<td>${services.id } </td>
												<td>${lf:formatMoney(services.fee) } </td>
												<td><fmt:formatDate value="${services.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><tags:getUserById id="${services.cb }" /></td>
												<td>
													<c:choose>
										                <c:when test="${services.payType==0}">线上</c:when>
										                <c:when test="${services.payType==1}">线下</c:when>
										        	</c:choose>
									        	</td>
												<td>
													<c:forEach items="${lf:getStatusNames(services.payType,services.serviceType,services.status,services.flowState)}" varStatus="stat" var="flowStateName">
														<c:if test="${stat.index==0}">【销售经理审核：${flowStateName}】</c:if>
														<c:if test="${stat.index==1}">【客服审核：${flowStateName}】</c:if>
														<c:if test="${stat.index==2}">【财务审核：${flowStateName}】</c:if>
													</c:forEach>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table> 
								</div>
					</div>
							
						<div class="col-md-5 form-inline">
						 	<small style="font-size:20px;">企业服务日志</small>
						 </div>
						 <div class="col-md-7 text-right" style="line-height: 15px;">
									<a class="btn btn-primary btn-sm" href="#" onclick="servicesLogs('create','')"><span
										class="glyphicon glyphicon-plus"></span> 新增服务记录</a>
						</div>
					<legend>
					 </legend>
			   
					<div class="row">
						<div class="col-table col-md-12">
								<table id="contentTable"
									class="table table-bordered table-condensed table-hover">
									<thead class="thead">
										<tr>
											<th class="text-center">序号</th>
											<th>内容</th>
											<th>时间</th>
											<th>创建人</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									   <c:forEach var="logs" varStatus="status" items="${serviceLogs }">
										<tr>
											<td class="text-center">${status.count } </td>
											<td>${logs.content }</td>
											<td><fmt:formatDate value="${logs.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td><tags:getUserById id="${logs.cb }" /> </td>
											<td><a class="btn btn-default btn-sm"
												href="#" onclick="servicesLogs('edit','${logs.id}')"><i
												class="glyphicon glyphicon-edit"></i>编辑</a>
								 			</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
						</div>
					</div>
			 </c:if>
			</div>
			<div class="form-group">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/company/list"><span
						class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>
				<c:choose>
					<c:when test="${empty readonly }">
						<div class="col-md-3">
							<button type="submit" id="submit_btn"
								class="btn btn-primary btn-block">保存</button>
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
			$('#inputForm').validate({
				ignore : "", // 开启hidden验证， 1.9版本后默认关闭
				submitHandler : function(form) {
					app.disabled("#submit_btn");
					//提交表单
					form.submit();
				},
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					"brefName":{
						required:true,
						minlength:2,
						maxlength:6
					},
					"address" : {
						required : true
					},
					"email" : {
						required : true
					},
					"tel" : {
						required : true
					},
					"linkMan" : {
						required : true,
						minlength : 2,
						maxlength : 50
					},
					"linkPhone" : {
						required : true,
						integer : true,
						isMobile : true

					},
					"pwd" : {
						required : true,
					},
					"logoFile": {
						required: true
					},
				},
				messages : {

				}
			});
			
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
		
		
		function servicesLogs(type,id){
			$("#myDlgBody_lg").load("${ctx}/company/services_dlg?id="+id+"&companyId=${info.id}");
			$("#myDlg_lg").modal();
		}
		
		</script>
</body>
</html>
