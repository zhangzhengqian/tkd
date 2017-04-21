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
			<li class="active">用户管理</li>
			<c:if test="${param.action == 'view' }">
				<li class="active">用户详情</li>
			</c:if>
			<c:if test="${param.action == 'edit' }">
				<li class="active">用户修改</li>
			</c:if>
		</ul>
	</div>
	<!-- / 右侧标题 -->

	<c:choose>
		<c:when test="${param.action == 'edit' }">
			<c:set var="readonly" value=" " />
		</c:when>
		<c:otherwise>
			<c:set var="readonly" value="readonly='readonly' " />
		</c:otherwise>
	</c:choose>

	<div class="panel-body">
		<!-- 右侧主体内容 -->
		<c:if test="${param.action == 'view' }">
			<fieldset>
				<legend>
					<small>用户信息详情</small>
				</legend>
		</c:if>
		<c:if test="${param.action == 'edit' }">
			<fieldset>
				<legend>
					<small>用户信息修改</small>
				</legend>
		</c:if>
		<form id="inputForm" action="${ctx}/ssouser/updateSsoUser"
			method="post" class="form-horizontal" enctype="multipart/form-data">
			<zy:token />
			<input type="hidden" id="id" name="id" value="${ssoUser.id }" />
			<fieldset>
				<c:if test="${readonly!=' '}">
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red"></span>个人头像:</label>
						<div class="col-md-1 has-feedback">
							<img src="${ssoUser.photo }"
								style="width: 128px; height: 128px; display: block; margin-left: auto; margin-right: auto;" />
						</div>
					</div>
				</c:if>

				<c:if test="${readonly==' '}">
					<div class="form-group form-group-sm">
						<label for="name" class="col-md-3 control-label"><span
							class="text-red"></span>个人头像:</label>
						<div id="title" class="col-md-1 has-feedback">
							<img id="icon_img" src="${ssoUser.photo }"
								style="width: 128px; height: 128px; display: block; margin-left: auto; margin-right: auto;" />
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span></label>
						<div class="col-md-6 has-feedback">
							<input type="file" class="form-control" id="icon_upload"
								name="icon_upload" multiple="false" /> <input type="hidden"
								id="photo" name="photo" value="" />
						</div>
					</div>

				</c:if>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>学员号:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" id="tkdNo" name="tkdNo"
							style="width: 480px" value="${ssoUser.tkdNo }" readonly />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>用户昵称:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="nickName"
							name="nickName" style="width: 480px" value="${ssoUser.nickName }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>用户姓名:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="name"
							name="name" style="width: 480px" value="${ssoUser.name }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>用户性别:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="sex"
							name="sex" style="width: 480px" value="${ssoUser.sex }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>会员等级:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" id="level" name="level"
							style="width: 480px" value="${ssoUser.level }" readonly />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label class="col-md-3 control-label"><span
						class="text-red"></span>出生日期:</label>
					<div class="col-sm-4">
						<div class="input-group col-md-6 has-feedback">
							<input value="${ssoUser.birthday }" type="text" name="bDate"
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
							name="city" style="width: 480px" value="${ssoUser.city }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>家庭住址:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="address"
							name="address" style="width: 480px" value="${ssoUser.address }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>身份证号:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="cardId"
							name="cardId" style="width: 480px" value="${ssoUser.cardId }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>注册手机:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" id="phone" name="phone"
							style="width: 480px" value="${ssoUser.phone }" readonly />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>电子邮箱:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="email"
							name="email" style="width: 480px" value="${ssoUser.email }" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>爱好:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" id="tags" name="tags"
							style="width: 480px" value="${ssoUser.tags }" readonly />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>累计消费:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" style="width: 480px"
							value="${orderAmount }" readonly />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>账户状态：</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" style="width: 480px"
							value="${data.state }" readonly />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>账户余额:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" style="width: 480px"
							value="${accountAmount }" readonly />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>冻结金额:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" style="width: 480px"
							value="${data.freez }元" readonly />
					</div>
				</div>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-table col-md-8">
						<table id="contentTable"
							class="table table-bordered table-condensed table-hover">
							<thead class="thead">
								<tr>
									<th>流水号</th>
									<th>订单号</th>
									<th>交易金额</th>
									<th>交易时间</th>
									<th>类型</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${data.logs}" var="log" varStatus="stat">
									<tr>
										<td>${log.tradeno }</td>
										<td>${log.orderId }</td>
										<td>${log.amount/100 }</td>
										<td style="text-align: center;"><fmt:formatDate
												value="${log.ct }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${log.description }</td>
										<td>查看</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- end col-table -->
				</div>
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>学员卡余额:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" style="width: 480px"
							value="${qiuyouAmount }" readonly />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span
						class="text-red"></span>剩余积分:</label>
					<div class="col-md-6 has-feedback">
						<input type="text" class="form-control" style="width: 480px"
							value="${integralAccount }" readonly />
						<%-- 　<a href="${ctx}/ssouser/viewQiuyouAccount?id=${ssoUser.id }">查看球友卡</a> --%>
					</div>
				</div>

				<%-- <div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>备注:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="remark" name="remark" style="width:480px" value="${ssoUser.remark }"/>
			    </div>
			</div> --%>

				<div class="row">
					<div class="col-md-1"></div>

					<div class="col-md-8">
						<table class="table table-bordered table-condensed table-hover">
							<thead class="thead">
								<tr>
									<th style="border-bottom-width: 0px;">序号</th>
									<th style="border-bottom-width: 0px;">积分数</th>
									<th style="border-bottom-width: 0px;">积分来源</th>
									<th style="border-bottom-width: 0px;">时间</th>
									<!-- <th style="border-bottom-width: 0px;">有效期至</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${integrals}" var="item" varStatus="stat">
									<tr>
										<td class="text-center">${stat.count }</td>
										<td>${item.integral }</td>
										<td>${item.description }</td>
										<td>${item.ct }</td>
										<%-- <td>${item.integralType}</td> --%>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<c:if test="${readonly==' ' }">
						<div class="col-md-offset-3 col-md-2">
							<a class="btn btn-default btn-block" href="${ctx}/ssouser/user"><span
								class="glyphicon glyphicon-remove"></span> 返回</a>
						</div>
						<div class="col-md-2">
							<button id="submit_button" type="button"
								class="btn btn-primary btn-block">保存</button>
						</div>
					</c:if>
					<c:if test="${readonly!=' '}">
						<div class="col-md-offset-3 col-md-2">
							<a href="javascript:window.history.go(-1);"
								class="btn btn-default btn-block"> 返回 </a>
						</div>
						<div class="col-md-2">
							<shiro:hasPermission name="ssouser:update">
								<a href="${ctx }/ssouser/view?action=edit&id=${ssoUser.id }"
									class="btn btn-primary btn-block"> 修改 </a>
							</shiro:hasPermission>
						</div>
					</c:if>
				</div>
			</fieldset>
		</form>
	</div>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js"
		type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
		type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			// 菜单栏显示底色（选中）
			menu.active('#statiums-man');

			$("#icon_upload").uploadify({
				//文件提交到 controller 里的文件对象的 key 
				fileObjName : 'upfile',
				//按钮名称
				buttonText : '选择图片',
				height : 40,
				swf : '${ctx}/static/uploadify/uploadify.swf',
				//提交到指定的 controller,写死即可，已封装
				uploader : '${ctx}/uploader',
				width : 120,
				//上传成功后回调此函数
				onUploadSuccess : function(file, data, response) {
					//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
					data = JSON.parse(data);
					if (data.success == true) {
						$('#icon_img').attr('src', data.url);
						$('#photo').val(data.url);
					}
				}
			});

			// 控件校验
			$('#inputForm').validate({
				rules : {},
				messages : {}
			});

			// 保存
			$("#submit_button").click(function() {
				$("#inputForm").submit();
			});

			// 低栏隐藏
			$("#adminFooter").hide();
		});

		// 验证图片样式
		function checkImgType(obj) {
			var _file = document.getElementById("mImage");
			var i = _file.value.lastIndexOf('.');
			var len = _file.value.length;
			var extEndName = _file.value.substring(i + 1, len);
			var extName = "GIF,BMP,JPG,JPEG,SWF";//首先对格式进行验证
			if (extName.indexOf(extEndName.toUpperCase()) == -1) {
				alert("*您只能输入" + extName + "格式的文件");
				document.getElementById("mImage").value = '';
				return false;
			}
		}
	</script>