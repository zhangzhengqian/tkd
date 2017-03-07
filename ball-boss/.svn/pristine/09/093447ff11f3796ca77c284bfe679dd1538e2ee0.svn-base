<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <li class="active">活动录入</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
<%String id = SessionUtil.currentUserId(); %>   
  
 

  <div class="panel-body"><!-- 右侧主体内容 -->
  	<span  class="text-danger text-red" id = "success_span"><h3 style="text-align:center" id="success_h3">${success}</h3></span>
	<form id="inputForm" action="${ctx}/admin/activity/activityForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
			<input type="hidden" id="next_page" name="next_page" value="admin/statium/activityForm" />			
			<input type="hidden" id="userid" name="userid" value="<%=id%>" /> 
		    <input type="hidden" id="id" name="id" value="${activity.id}" />
		<fieldset>
		
		<div class="form-group form-group-sm">
			<label for="name" class="col-md-3 control-label"><span class="text-red">* </span>活动名称:</label>
		    <div class="col-md-6 has-feedback">
		    	<input  type="text" class="form-control" id="name" name="name" value="${activity.name}" /><span id="name_span"  class="text-danger text-red">${name_error}</span>
		    </div>
		</div>
		
			<%--赛事类型已写死在页面 --%>
			<div class="form-group form-group-sm">
			    <label for="type" class="col-md-3 control-label"><span class="text-red"> </span>活动类型</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input type="hidden" id="type_hidden" value="${activity.type}"/>
					<select class="form-control" name="type" id="type">
						<option id="option0" value="0">--篮球--</option>
						<option id="option1" value="1" >--足球--</option>
						<option id="option2" value="2" >--羽毛球--</option>
						<option id="option3" value="3" >--台球--</option>
						<option id="option4" value="4" >--保龄球--</option>
						<option id="option5" value="5" >--高尔夫--</option>
						<option id="option6" value="6" >--乒乓球--</option>
						<option id="option7" value="7" >--网球--</option>
					</select>
			  	</div>
			 </div>
		
			<c:if test="${!empty activity.photo }">
			    <div class="form-group form-group-sm">
		         	<div class="col-md-6 has-feedback col-md-offset-3">
		           		<img src="${activity.photo}" width="80" height="50">
		         	</div>
			    </div>   
		    </c:if>   
		    <div class="form-group form-group-sm">
		    	<label for="photo1" class="col-md-3 control-label"><span class="text-red">
		    	<c:if test="${empty activity.photo }">*  </c:if>   </span>活动图片:</label>
		        <div class="col-md-6 has-feedback">
	           		<input type="file" class="form-control" id="photo1" name="photo1" /><span id="photo1_span"  class="text-danger text-red">${photo1_error}</span>
		        </div>
		    </div>
	
			<div class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red">* </span>活动开始时间:</label>
                <div class="col-sm-6 has-feedback">
                     <div class="input-group col-md-4">
                         <input type="text" name="startTime" id="startTime" class="form-control" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" value="${activity.startTime}" readonly>
                         <label for="startTime" class="input-group-addon"><i class="fa fa-calendar"></i></label> 
                     </div>
                     <span id="startTime_span" class="text-danger text-red">${start_time_error}</span>
                </div>
			</div>	
			
			<div class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red">* </span>活动结束时间:</label>
                <div class="col-sm-6 has-feedback">
                     <div class="input-group col-md-4">
                         <input type="text" name="endTime" id="endTime" class="form-control" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTime\')}',alwaysUseStartDate:true})" value="${activity.endTime}" readonly>
                         <label for="endTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                     <span id="endTime_span" class="text-danger text-red">${end_time_error}</span>
                </div>
			</div>	

			<div class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red">* </span>报名截止日期:</label>
                <div class="col-sm-6 has-feedback">
                     <div class="input-group col-md-4 has-feedback">
                         <input type="text" name="expiryDate" id="expiryDate" class="form-control" onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}',alwaysUseStartDate:true})" value="${activity.expiryDate}" readonly>
                         <label for="expiryDate" class="input-group-addon"><i class="fa fa-calendar"></i></label> 
                     </div>
                     <span id="expiryDate_span" class="text-danger text-red">${expiry_date_error}</span>
                </div>
			</div>	
	      
	    
			<div class="form-group form-group-sm">
				<label for="totalNumber" class="col-md-3 control-label"><span class="text-red">*</span>参赛人数:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="totalNumber" name="totalNumber" value="${activity.totalNumber}" /><span id="totalNumber_span"  class="text-danger text-red">${total_number_error}</span>
			    </div>
			</div>
			<!-- 
			<div class="form-group form-group-sm">
				<label for="applicantNumber" class="col-md-3 control-label"><span class="text-red"></span>已报名人数:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="applicantNumber" name="applicantNumber" value="${activity.applicantNumber}" /><span id="applicantNumber_span" class="text-danger text-red">${applicant_number_error}</span>
			    </div>
			</div> -->
			
			<div class="form-group form-group-sm">
				<label for="address" class="col-md-3 control-label"><span class="text-red">* </span>活动地点:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="address" name="address" value="${activity.address}" /><span id="address_span" class="text-danger text-red">${address_error}</span>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="undertake" class="col-md-3 control-label"><span class="text-red">* </span>主办方:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="undertake" name="undertake" value="${activity.undertake}" /><span id="undertake_span" class="text-danger text-red">${undertake_error}</span>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="money" class="col-md-3 control-label"><span class="text-red">* </span>报名金额:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="例如50.00，最多保留2位小数" type="text" class="form-control" id="money" name="money" value="${activity.money}" /><span id="money_span" class="text-danger text-red">${money_error}</span>
			    </div>
			</div>
      
      		<div class="form-group form-group-sm">
				<label for="undertake" class="col-md-3 control-label"><span class="text-red">* </span>活动简要描述:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="briefdesc" name="briefdesc" value="${activity.briefdesc}" /><span id="briefdesc_span" class="text-danger text-red">${briefdesc_error}</span>
			    </div>
			</div>
			
       	  	<div class="form-group ">
	         	<label for="introduction" class="col-md-3 control-label"><span class="text-red">*</span>活动介绍:</label>
	         	<div class="col-md-6 has-feedback">
	             	<script id="myEditor" name="introduction"  type="text/plain">${activity.introduction}</script>
				 	<span id="introduction_span" class="text-danger text-red">${introduction_error}</span> 
	         	</div>
	      	</div>
	      
      	 <div class="form-group form-group-sm">
			<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/admin/activity/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>     
	      	<div class="col-md-2">
		    	<button id = "submit_button" type="button" class="btn btn-primary btn-block" > 保存 </button>
			</div>
		</div>					
		</fieldset>
	</form>
  </div>	
</div>
<script type="text/javascript"src="${ctx}/static/ueditor/ueditor.config.js"></script>  
<script type="text/javascript"src="${ctx}/static/ueditor/ueditor.all.js"></script>  
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

<script type="text/javascript">
<%--加载ueditor--%>  
var um = UE.getEditor('myEditor',{
	initialFrameWidth:'620',
	initialFrameHeight:'500',
	elementPathEnabled:false,
	wordCount:false,
	toolbars: [[
	             'fullscreen', 'source', '|', 'undo', 'redo', '|',
	             'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',  'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	             'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	             'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	             'directionalityltr', 'directionalityrtl', 'indent', '|',
	             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase',
	             '|','simpleupload','insertimage','|','preview'
	         ]]
});  
$(function(){
		$('#inputForm').validate({
			rules: {
				totalNumber: {
					digits: true
	            },applicantNumber:{
	            	digits: true
				},money:{
					required: true,
					isMoney: true
				},briefdesc:{
					required: true,
					maxlength:50
				},name:{
					required: true,
					maxlength:50
				},startTime:{
					required: true
				},endTime:{
					required: true
				},expiryDate:{
					required: true
				},totalNumber:{
					required: true,
					range:[0,99999],
					digits: true
				},photo1:{
					uploadImgStyle: true
				},address:{
					required: true,
					maxlength:50
				},undertake:{
					required: true,
					maxlength:50
				},introduction:{
					required: true
				}
			},
			messages: {
			},
			errorPlacement : function(error, element) {
				if(element.attr("name") == 'startTime' ||element.attr("name") == 'endTime'||element.attr("name") == 'expiryDate'){
					error.appendTo(element.parent().parent());
				}else{
					error.appendTo(element.parent());
				}
			}
		});
		if(!'${activity.photo}'){
			$("#photo1").addClass("required");
		}
		
	
	$(window).load(function(){
		var type_value =$("#type_hidden").val();
		if(type_value==0){
			$("#option0").attr("selected","selected");
		}
		if(type_value==1){
			$("#option1").attr("selected","selected");
		}
		if(type_value==2){
			$("#option2").attr("selected","selected");
		}
		if(type_value==3){
			$("#option3").attr("selected","selected");
		}
		if(type_value==4){
			$("#option4").attr("selected","selected");
		}
		if(type_value==5){
			$("#option5").attr("selected","selected");
		}
		if(type_value==6){
			$("#option6").attr("selected","selected");
		}
		if(type_value==7){
			$("#option7").attr("selected","selected");
		}
	});
	menu.active('#activity-form');
	$("#submit_button").click(function(){
		if($('#inputForm').valid()){
			if(um.getContentLength(true) == 0){
				alert("请填写活动介绍！");
				return;
			}
			$("#introduction").val(um.getContent());
			$("#inputForm").submit();
		}
	});
	$("#money").focus(function(){
		$("#money_span").remove();
		$("#success_span").remove();
	});
	$("#undertake").focus(function(){
		$("#undertake_span").remove();
		$("#success_span").remove();
	});
	$("#address").focus(function(){
		$("#address_span").remove();
		$("#success_span").remove();
	});
	$("#applicantNumber").focus(function(){
		$("#applicantNumber_span").remove();
		$("#success_span").remove();
	});
	$("#totalNumber").focus(function(){
		$("#totalNumber_span").hide();
		$("#success_span").remove();
	});
	$("#introduction").focus(function(){
		$("#introduction_span").remove();
		$("#success_span").remove();
	});
	$("#type").focus(function(){
		$("#type_span").remove();
		$("#success_span").remove();
	});
	$("#endTime").focusout(function(){
		$("#endTime_span").remove();
		$("#success_span").remove();
	});
	$("#expiryDate").focusout(function(){
		$("#expiryDate_span").remove();
		$("#success_span").remove();
	});
	$("#startTime").focusout(function(){
		$("#startTime_span").remove();
		$("#success_span").remove();
	});
	$("#photo1").focusout(function(){
		$("#photo1_span").remove();
		$("#success_span").remove();
	});
	$("#name").focusout(function(){
		$("#name_span").remove();
		$("#success_span").remove();
	});
	$("#adminFooter").hide();
});
</script>
