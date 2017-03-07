<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

 
<form id="form1" action="${ctx}/admin/func/save" method="post" class="form-horizontal">
    <zy:token/>
    <input type="hidden" name="funcId" value="${func.funcId}"/>
    <input type="hidden" name="parentId" value="${func.parentId}"/>

	<fieldset>
		<legend>
			<small>功能信息</small>
		</legend>

		<div class="form-group form-group-sm">
			<label for="funcName" class="col-md-3 control-label"><span
				class="text-red">* </span>功能名称:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="funcName"
					name="funcName" value="${func.funcName}" maxlength="15" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label for="action" class="col-md-3 control-label"><span
				class="text-red">* </span>活动:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="action" name="action"
					value="${func.action}" placeholder="填写URL地址模板，如：/foo/bar"
					maxlength="128" />
			</div>
		</div>


		<div class="form-group form-group-sm">
			<label for="permission" class="col-md-3 control-label"><span
				class="text-red">* </span>权限代码:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="permission"
					name="permission" value="${func.permission}"
					placeholder="权限代码格式如：module:action" maxlength="128" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label for="seqNum" class="col-md-3 control-label"><span
				class="text-red"> </span>排序号:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="seqNum" name="seqNum"
					value="${func.seqNum}" placeholder="输入 [0-99]的整数" maxlength="2" />
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-3 col-md-2">
				<button type="reset" class="btn btn-default btn-block" >
				    <span class="glyphicon glyphicon-repeat"></span> 重置
				 </button>
			</div>
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary btn-block" id="submit_btn">
					<span class="glyphicon glyphicon-ok"></span> 保存
				</button>
			</div>
			
			<c:if test="${!empty func.funcId }">
			<div class="col-md-2">
				<button type="button" class="btn btn-warning btn-block" id="del_btn">
					<span class="glyphicon glyphicon-floppy-remove"></span> 删除
				</button>
			</div>
			</c:if>
		</div>

	</fieldset>

</form>



<script type="text/javascript" >
var oldName = encodeURIComponent('${func.funcName}');
$('#form1').validate({
	rules: {
		funcName: {
			required: true
			, rangelength: [3, 20]
			, remote : '${ctx}/admin/func/checkName?oldName=' + oldName + '&pId=${func.parentId}'
		},
		action : {
			required: true
			, rangelength: [2, 100]
		},
		permission: {
			required: true
			, rangelength: [2, 50]
		},
		seqNum: {
			digits: true
		}
	},
	messages: {
		funcName: {
			remote: '功能名称已经存在，请重新输入！'
		}
	}
});

$('#del_btn').on('click', function() {
    func.delFunction('${func.funcId}', '${func.funcName}');
});

</script>