<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 合作管理</li>
	        <li class="active">活动码添加</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
  <div class="panel-body"><!-- 右侧主体内容 -->
	<form id="inputForm" action="${ctx}/code/codeSave" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
        <input type="hidden" class="form-control" id="statiums" name="statiums" value=""/>
		<fieldset>
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>生成数量:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="输入正整数" type="text" class="form-control" id="number" name="number" style="width:229px" value="${carousel.sort }" />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>使用场馆:</label>
		       <div class="col-md-6 has-feedback">
		       	 <input type="text" class="form-control" id="statiumName" name="statiumName" readonly/>
		         <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
		       </div>
		    </div>
		    
		   <div class="form-group form-group-sm">
	            <label for="date1" class="col-md-3 control-label"><span class="text-red">* </span>有效期限:</label>
	            <div class="col-md-6 has-feedback form-inline">
	           		  <div class="input-group">
	                  		<input type="text" id="sDate" name="sDate" placeholder="请输入开始时间"
	                  			class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'eDate\')}',alwaysUseStartDate:true})"/>
	 							<label for="sDate" class="input-group-addon"><i class="fa fa-calendar"></i></label>
	                    </div>
	                  	至
	                    <div class="input-group">
	                  		<input type="text" id="eDate" name="eDate" placeholder="请输入结束时间"
	                  			class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sDate\')}',alwaysUseStartDate:true})"/>
								<label for="eDate" class="input-group-addon"><i class="fa fa-calendar"></i></label>
	                    </div>
	            </div>
        	</div>
		
			<div class="form-group form-group-sm">
		         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>简介:</label>
		         <div class="col-md-6 has-feedback ">
		           <textarea  rows="5" style="height:8em;" id="description" class="form-control" placeholder="请输入活动码简介" name="description"></textarea>
		         </div>
	     	</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red">* </span>渠道:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="输入相应渠道" type="text" class="form-control" id="channel" name="channel" style="width:229px" value="${carousel.sort }" />
			    </div>
			</div>	 	

      	 <div class="form-group form-group-sm">
		  	<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/code/codeList"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>         
	      	<div class="col-md-2">
		    	<button id = "submit_button" type="button" class="btn btn-primary btn-block" > 提交 </button>
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
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	// 菜单栏显示底色（选中）
	menu.active('#code-man');
	
	// 控件校验
 	$('#inputForm').validate({
		rules: {
			// 生成数量
			"number":{
				required:true,
				digits: true,
				range:[0,99999999],
			},
			// 使用场馆
			"statiumName": {
				required: true
			},
			// 开始日期
			"startDate": {
				required: true
			},
			// 结束日期
			"endDate": {
				required: true
			},
			// 渠道
			"channel":{
				 required: true
			}
		},
		messages: {
			number: {
				digits:"必须为正整数",
				range: "请输入一个介于 {0} 和 {99999999} 之间的值"
			}
		}
	}); 
	
	// 保存
 	$("#submit_button").click(function(){
		$("#inputForm").submit();
	}); 
	
 	// 低栏隐藏
	$("#adminFooter").hide();
 	
 	// 添加场馆
	$('#sel_captain').on('click',function(){
		$("#myDlgBody_lg").load("${ctx}/code/statium_dlg");
		$("#myDlg_lg").modal();
	})
 	
});
	function captainAddCallBack(sid,name){
		var sId = $("#statiums").val();
		var newId = sId + sid + ",";
		$("#statiums").val(newId);
		var sName = $("#statiumName").val();
		var newName = sName + name + "、";
		$("#statiumName").val(newName);
	}


</script>