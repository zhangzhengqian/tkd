<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span><a
				href="${ctx }/statiumClass/"> 课程列表 </a></li>
			<li class="active">课程信息</li>
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

	<div class="panel-body">
		<!-- 右侧主体内容 -->

		<h3>课程信息</h3>
		<hr>
		<form id="inputForm" action="${ctx}/statiumClass/save" method="post"
			class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${statiumClass.id}" />
			<input type="hidden" name="type" id= "type" value="${statiumClass.type}" />
			<input type="hidden" name="fromPage" id="fromPage" value="${param.fromPage }" />
			<c:choose>
				<c:when test="${param.action=='edit' }">
					<input type="hidden" name="statiumId" value="${statiumClass.statiumId}" />
				</c:when>
				<c:when test="${param.action=='create' }">
					<input type="hidden" name="statiumId" value="${param.statiumId}" />
				</c:when>
			</c:choose>
			<fieldset>

				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span
						class="text-red">* </span>课程名称:</label>
					<div class="col-md-6 has-feedback">
						<input ${readonly } type="text" class="form-control" id="classTitle"
							name="classTitle" value="${statiumClass.classTitle }" placeholder="请输入课程名称" />
					</div>
				</div>
				<div class="form-group form-group-sm">
					<label for="introduce" class="col-md-3 control-label"><span
						class="text-red"></span>课程介绍:</label>
					<div class="col-md-6 has-feedback">
						<textarea ${readonly } style="height: 100px;" class="form-control"
							rows="5" id="classIntroduce" placeholder="课程介绍" name="classIntroduce">${statiumClass.classIntroduce }</textarea>
					</div>
				</div>

				<div class="form-group form-group-sm">
					<label for="address" class="col-md-3 control-label"><span
						class="text-red">* </span>原价:</label>
					<div class="col-md-6 has-feedback ">
						<input ${readonly } type="text" class="form-control" id="flPrice"
							name="flPrice" value="${statiumClass.flPrice}" />
					</div>
				</div>
 				<div class="form-group form-group-sm">
					<label for="address" class="col-md-3 control-label"><span
						class="text-red"> </span>签约价:</label>
					<div class="col-md-6 has-feedback ">
						<input ${readonly } type="text" class="form-control" id="flDiscountPrice"
							name="flDiscountPrice" value="${statiumClass.flDiscountPrice}" />
					</div>
				</div>
				
				<div class="form-group form-group-sm">
			         <label for="teach" class="col-md-3 control-label "> 执教类型:</label>
			         <div class="col-md-6 has-feedback form-inline">
				           	 <div class="btn-group" id="teachDiv" data-toggle="buttons">
				           	 	  <label class="btn btn-default<c:if test="${statiumClass.type == 0 || statiumClass.type == null}"> active</c:if>">
								  		<input type="radio" name="type" id="type" value="0"  autocomplete="off"
								  		<c:if test='${statiumClass.type == 0 || statiumClass.type == null}'>checked="checked"</c:if> >
								      	大课
								  </label>
								  <label class="btn btn-default<c:if test="${statiumClass.type == 1}"> active</c:if>">
								  		<input type="radio" name="type" id="type" value="1" autocomplete="off"
								  			<c:if test='${statiumClass.type == 1}'>checked="checked"</c:if> >
								     	私教
								  </label>
							</div>
			         </div>
			    </div>
			    	<!-- 隐藏最小人数输入框 -->
 			    <div id="classArea"  style="display:none">
					<%--<div class="form-group form-group-sm">
						<label for="address" class="col-md-3 control-label"><span
							class="text-red"> </span>最小人数:</label>
						<div class="col-md-6 has-feedback ">
							<input ${readonly } type="text" class="form-control" id="minPeople"
								name="minPeople" value="${statiumClass.minPeople}" />
						</div>
					</div>--%>
					<div class="form-group form-group-sm">
						<label for="address" class="col-md-3 control-label"><span
							class="text-red"> </span>最大人数:</label>
						<div class="col-md-6 has-feedback ">
							<input ${readonly } type="text" class="form-control" id="maxPeople"
								name="maxPeople" value="${statiumClass.maxPeople}" />
						</div>
					</div>
				</div>
				
				<div id="teachArea" class="form-group form-group-sm">
						<label for="address" class="col-md-3 control-label"></label>
						<div class="col-md-6 has-feedback ">
							<input readonly type="text" class="form-control" value="一对一" />
						</div>
				</div>
				<div class="form-group form-group-sm">
						<div class="col-md-offset-3 col-md-3">
							<a href="javascript:window.history.go(-1);" class="btn btn-default btn-block">
								返回 </a>
						</div>
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


<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/project_js.js"></script>
<script src="${ctx}/static/js/bankData.js"></script>
<script type="text/javascript">
	function trim(str) {
		return str.replace(/(\s)/g, "");
	}
	$(function() {
		if ($("#fromPage").val() == 'oa') {
			menu.active('#statiumOa-man');
		} else {
		  	menu.active('#statium-man');
		}
		
		if ($("#type").val() == 1) {
			$("#classArea").hide();
			$("#teachArea").toggle();
		}
		if ($("#type").val() == 0) {
			$("#classArea").toggle();
			$("#teachArea").hide();
		}
		
	});

	$(function() {
		menu.active("#statiumOa-man");
		$("#div_areaCode select:eq(2)").each(function() {
			$(this).addClass("required");
		});

		$('#inputForm').validate({
			submitHandler : function(form) {
				//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
				/* getData(function(data) {
					app.disabled("#submit_btn");
					$("#priceTemps").val(data);
					form.submit();
				}); */
				form.submit();
			},
			rules : {
				classTitle : {
					required : true,
					maxlength : 20
				},
				flPrice : {
					required : true,
					integer: true
				},
				remark : {
					maxlength : 1500
				},
				flDiscountPrice:{
					required : true,
					integer: true
				},
				maxPeople : {
					required : true,
					integer: true
				}
			},
			messages : {

			}
		});

	});

	$("#teachDiv").on("click","label",function(){
		if($(this).find("input[name=type]").val() == 1){
			$("#classArea").hide();
			$("#teachArea").toggle();
			$("#type").val('1');
			$("#minPeople").val(0);
			$("#maxPeople").val(0);
		} else {
			$("#classArea").toggle();
			$("#teachArea").hide();
			$("#type").val('0');
		}
	});
</script>