<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">


	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	       	<li><span class="glyphicon glyphicon-home active"></span> 会员管理</li>
		    <li class="active" >导入</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/member/customer/import" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-9 has-feedback" id="console">
			    </div>
			</div>
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>下载模版:</label>
			    <div class="col-md-4 has-feedback">
			    	<a href="${ctx }/static/customer_import.xls" target="_blank" class="btn btn-info btn-block" > 模版下载 </a>
			    	<a href="${ctx }/static/areaCode.xls" target="_blank" class="btn btn-warning btn-block" > 行政区域对照表下载 </a>
			    </div>
			</div>
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>上传:</label>
			    <div class="col-md-6 has-feedback">
		         <input type="file" class="form-control" id="file" name="file" value="" />
			    </div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-2">	
		    		<a href="${ctx }/member/customer" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
		    		<button type="submit" class="btn btn-primary btn-block" > 保存 </button>
				</div>
			</div>

		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">

$(function() {
	menu.active('#customer-man');
	$('#footer').hide();
	
	var success = "${param.success}";
	var entity = "${param.entity}";
	var reason = "${param.reason}";

	var error = "${param.error}";
	var counter= "${param.counter}";
	var counter_error = "${param.counter_error}";
	var counter_success= "${param.counter_success}";
	
	if(success=='true'){
		$('#console').append("<p>总纪录数:"+counter+" ; 成功导入:"+counter_success+" ; 失败:"+counter_error+"</p>");		
		if(counter_error!='0'){
			$('#console').append("<p>明细如下:</p>");		
			$('#console').append(error);	
		}
	}else if(success=='false'){
		$('#console').append(reason);	
	}
	
	$('#inputForm').validate({
		rules: {
			file : {
				required: true
			}
		},
		messages: {
		}
	});
	
});
</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
