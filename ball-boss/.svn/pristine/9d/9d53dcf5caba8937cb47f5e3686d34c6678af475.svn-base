<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 运营管理</li>
        <li>添加会员卡</li>
        <li class="active"> 新增</li>
    </ul>
  </div><!-- / 右侧标题 -->
 
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<fieldset> <legend><small>添加球馆会员卡</small></legend>
	<form id="inputForm" action="${ctx}/vip/save" method="post" class="form-horizontal">
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="statiumName" class="col-md-3 control-label"><span class="text-red"></span>场馆名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入场馆名称" type="text" class="form-control" id="statiumName" name="statiumName"/>
			    	<input type="hidden" class="form-control input-sm" id="statiumId" name="statiumId">
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>会员卡名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入会员卡名称" type="text" class="form-control" id="name" name="name"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="cardNumber" class="col-md-3 control-label"><span class="text-red"></span>会员卡号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入会员卡号" type="text" class="form-control" id="cardNumber" name="cardNumber"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="amount" class="col-md-3 control-label"><span class="text-red"></span>面额:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input placeholder="请输入面额" type="text" class="form-control" id="amount" name="amount"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="balance" class="col-md-3 control-label"><span class="text-red"></span>余额:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input placeholder="请输入余额" type="text" class="form-control" id="balance" name="balance"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="transactor" class="col-md-3 control-label"><span class="text-red"></span>办理人:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input placeholder="请输入办理人" type="text" class="form-control" id="transactor" name="transactor"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="transactorTel" class="col-md-3 control-label"><span class="text-red"></span>办理人电话:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input placeholder="请输入办理人电话" type="text" class="form-control" id="transactorTel" name="transactorTel"/>
			    </div>
			</div>
			
      	  <div class="form-group form-group-sm">
				<hr>
				<div class="col-md-offset-3 col-md-2">
					<button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 确认</button>
				</div>
				<div class="col-md-2">
					<a class="btn btn-default btn-block" href="${ctx}/vip"><span class="glyphicon glyphicon-remove"></span> 返回</a>			  
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
		menu.active('#vip-man');
		//====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
            max: 30,
            autoFill: false,
            mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
            matchContains: true, 
            scrollHeight: 220,
            width: $('#statiumName').outerWidth(),
            multiple: false,
            formatItem: function (row, i, max) {                    //显示格式
                return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
            },
            formatResult: function (row) {                      //返回结果
                return row.name;
            },
            handleValue:function(id){
            	$('#statiumId').val(id);
            },
            parse:function(data){
            	var parsed = [];
        		var rows = data;
        		for (var i=0; i < rows.length; i++) {
        			var row = rows[i];
        			if (row) {
        				parsed[parsed.length] = {
        					data: row,
        					value: row["id"],
        					result: this.formatResult(row)
        				};
        			}
        		}
        		return parsed;
            }
		});
		//====================================================
		// 自动匹配 场馆名称 <<<<
		//====================================================
	});
	
	$('#statiumName').change(function(){
		if($.trim($(this).val()) == ""){
			$("#statiumId").val("");
		}
	});
	
	$("#submit_btn").click(function(){
		$("#inputForm").submit();
	});
	
	$('#inputForm').validate({
		submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮
			if($("#statiumId").val() == ""){
				myAlert("场馆不存在，请重新选择场馆","error");
				return;
			}
			app.disabled("#submit_btn");
			//提交表单
			form.submit(); 
		},
		rules: {
			"statiumName" : {
				required: true,
			},
			"name" : {
				required: true,
			},
			"cardNumber" : {
				required: true,
			},
			"amount" : {
				required: true,
				digits:true
			},
			"balance" : {
				required: true,
				digits:true
			},
			"transactor" : {
				required: true,
			},
			"transactorTel" : {
				required: true,
				isMobile : true
			},
		},
		messages: {
			
		}
	});
</script>