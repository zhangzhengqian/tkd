<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li>活动统计</li>
        <li class="active">修改</li>
    </ul>
  </div><!-- / 右侧标题 -->
 
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<fieldset> <legend><small>活动统计</small></legend>
	<form id="inputForm" action="${ctx}/activity/updateActivityStatistics" method="post" class="form-horizontal">
		<input id="id" name="id" value="${activity.id }" type="hidden"/>
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="costPrice" class="col-md-3 control-label"><span class="text-red"></span>运营成本:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入运营成本" type="text" class="form-control" id="costPrice" name="costPrice" value="${activity.costPrice}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="expenditure" class="col-md-3 control-label"><span class="text-red"></span>场地支出:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入场地支出" type="text" class="form-control" id="expenditure" name="expenditure" value="${activity.expenditure}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="profit" class="col-md-3 control-label"><span class="text-red"></span>盈利:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入盈利" type="text" class="form-control" id="profit" name="profit" value="${activity.profit}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="remark" class="col-md-3 control-label"><span class="text-red"></span>备注:</label>
			    <div class="col-md-6 has-feedback">
			    	<textarea rows="5" cols="105" id="remark" placeholder="请输入备注信息" name="remark">${activity.remark}</textarea>
			    </div>
			</div>
			
      	  <div class="form-group form-group-sm">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
				</div>
				<div class="col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/activity/statisticsList"><span class="glyphicon glyphicon-remove"></span> 返回</a>			  
				</div>
		  </div>			 		 					
		</fieldset>
	</form>
  </div>
  <div class="panel-footer">
	<div class="row text-right">
		<div class="col-sm-12">
		</div>
	</div>	
  </div>	
</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		menu.active('#activityStatistics-man');
	});
	$("#submit_btn").click(function(){
		$("#inputForm").submit();
	});
	
	$('#inputForm').validate({
		rules: {
			"costPrice" : {
				digits:true,
			},
			"expenditure" : {
				digits:true,
			},
			"profit" : {
				digits:true,
			},
		},
		messages: {
			
		}
	});
</script>