<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span><a
				href="${ctx }/statiumClassInfo/"> 课时列表 </a></li>
			<li class="active">课时信息</li>
		</ul>
	</div>
	<!-- / 右侧标题 -->

	<c:choose>
		<c:when test="${param.action == 'create'}">
			<c:set var="disable" value="false" />
		</c:when>
		<c:when test="${param.action == 'edit' }">
			<c:set var="style" value="display:none"/>
			<c:set var="disable" value="false" />
		</c:when>
		<c:otherwise>
			<c:set var="disable" value="true" />
			<c:set var="readonly" value="readonly='readonly'" />
		</c:otherwise>
	</c:choose>

	<div class="panel-body">
		<!-- 右侧主体内容 -->

		<h3>课时信息</h3>
		<hr>
		<form id="inputForm" action="${ctx}/statiumClassInfo/save"
			method="post" class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" id="coachId" name="coachId" value="${statiumClassInfo.coachId }" /> 
			<input type="hidden" id="id" name="id" value="${statiumClassInfo.id}" /> 
			<input type="hidden" name="statiumId" value="${param.statiumId}" /> 
			<input type="hidden" name="dgid" value="${param.dgid}" />
			<c:choose>
				<c:when test="${param.action=='edit' }">
					<input type="hidden" name="classId"
						value="${statiumClassInfo.classId}" />
				</c:when>
				<c:when test="${param.action=='create' }">
					<input type="hidden" name="classId" value="${param.classId}" />
				</c:when>
			</c:choose>
			<fieldset>
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span
						class="text-red">* </span>上课日期:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control Wdate "
							id="cDate" name="cDate" value="${statiumClassInfo.cDate }"
							placeholder="请选择上课日期"
							onfocus="WdatePicker({readOnly:true,doubleCalendar:true})" />
					</div>
				</div>
				
				<div class="form-group form-group-sm" style="${style}">
        	 		<label for="isWeek" class="col-md-3 control-label"><span class="text-red">* </span> 是否按周添加:</label>
         				<div class="col-md-6 has-feedback form-inline">
         				<div class="btn-group" id="isWeek" data-toggle="buttons">
					  		<label class="btn btn-default<c:if test="${statiumClassInfo.isWeek == 1 }"> active abc</c:if>">
					  			<input type="radio" name="isWeek" value="1" autocomplete="off"
					  			<c:if test="${statiumClassInfo.isWeek == 1}">checked="checked"</c:if>>
					     	是
					     	</label>
					  		<label class="btn btn-default<c:if test="${statiumClassInfo.isWeek == 0}"> active abc</c:if>">
					  			<input type="radio" name="isWeek" value="0"  autocomplete="off"
					  			<c:if test="${statiumClassInfo.isWeek == 0}">checked="checked"</c:if> autocomplete="off">
					      	否
					  		</label><br><br>
					  		<span name="picSpan" style="color:red;line-height:30px;">温馨提示：如当前时段课程一段时期内每周都有，请选择“是"。那么从当前日期开始之后的一个月内，每周的这个时间这个课时将会自动被添加；如该时段课程若只添加这一次，请选择“否”。 </span>
						</div>
         				</div>
      			</div>
				
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span
						class="text-red">* </span>上课教练:</label>
					<div class="col-md-6 has-feedback">
						<span id="captainName"></span> <input readonly type="text" class="form-control" id="coachName" name="coachName"
							value="${statiumClassInfo.coachName }" placeholder="请选择上课教练" style="width: 200px" /> 
							<a id="sel_captain" class="btn btn-default btn-primary">选择</a>
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label"><span
						class="text-red">* </span>上课时间:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control"
							id="classStartTime" name="classStartTime"
							value="${statiumClassInfo.classStartTime }" placeholder="请选择上课时间"
							onfocus="WdatePicker({readOnly:true,dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\'classEndTime\')}'})" />
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="address" class="col-md-3 control-label"><span
						class="text-red">* </span>下课时间:</label>
					<div class="col-md-6 has-feedback ">
						<input ${readonly } type="text" class="form-control"
							id="classEndTime" name="classEndTime"
							value="${statiumClassInfo.classEndTime}" placeholder="请选择下课时间"
							onfocus="WdatePicker({readOnly:true,dateFmt:'HH:mm',minDate:'#F{$dp.$D(\'classStartTime\')}'})" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-offset-3 col-md-3">
						<a href="javascript:window.history.go(-1);"
							class="btn btn-default btn-block"> 返回 </a>
					</div>
					<c:if test="${empty readonly }">
						<div class="col-md-3">
							<button type="submit" id="submit_btn"
								class="btn btn-primary btn-block">保存</button>
						</div>
					</c:if>
				</div>
			</fieldset>
		</form>
	</div>
</div>


<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/project_js.js"></script>
<script src="${ctx}/static/js/bankData.js"></script>
<script type="text/javascript">
	$('#sel_captain').on('click', function() {
		$("#myDlgBody_lg").load("${ctx}/common/coach_query_dlg", {
			statiumId : '${param.statiumId}'
		});
		$("#myDlg_lg").modal();
	})
	function captainAddCallBack(userId, nickName) {
		$("#coachId").val(userId);
		$("#coachName").val(nickName);
	}

	function trim(str) {
		return str.replace(/(\s)/g, "");
	}
	$(function() {
		<!--列表高亮 -->
		menu.active('#statiumOa-man');

		$("#qiuyouRating")
				.blur(
						function() {
							var value = $("#qiuyouRating").val();
							if (value != "") {
								var patrn = new RegExp("^0.[1-9]{1,2}$");
								if (!patrn.exec(value)) {
									$("#qiuyouRating_span")
											.html(
													"<p id='qiuyouRating_p'>折扣只能填一位小数,且值小于0</p>");
								} else {
									$("#qiuyouRating_p").remove();
								}
							}
							if (value == "") {
								$("#qiuyouRating_p").remove();
							}
						});
	});

	$(function() {
		$(window).load(function() {
			if ('${isExist }') {
				alert('${isExist }');
			}

			if ('${statium.id }') {
				$("#logoFile").parent().parent().find("label span").html("");
			} else {
				$("#logoFile").addClass("required");
			}
		});
	});

	$(function() {
		$("#div_areaCode select:eq(2)").each(function() {
			$(this).addClass("required");
		});

		$('#inputForm').validate({
			submitHandler : function(form) {
				//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
				/* getData(function(data) {
					app.disabled("#submit_btn");
					$("#priceTemps").val(data);
				}); */
				form.submit();
			},
			rules : {
				cDate : {
					required : true,
				},
				coachName : {
					required : true
				},
				classStartTime : {
					required : true
				},
				classEndTime : {
					required : true
				},
				isWeek : {
					required : true
				}
				
			},
			messages : {

			}
		});

	});

	$(function() {
	});
</script>