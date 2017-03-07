<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <li class="active">赛事录入</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
 
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<span  class="text-danger text-red" id = "success_span"><h3 style="text-align:center" id="success_h3">${success}</h3></span>
	<form id="inputForm" action="${ctx}/admin/event/eventForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
			<input type="hidden" name="next_page" value="/admin/statium/eventForm" />			
			<input type="hidden" name="userid" value="<%=id%>" /> 
			<input type="hidden" id="introduction" name="introduction" value="${event.introduction}" />
			<input type="hidden" id="id" name="id" value="${event.id}"/>
		<fieldset>
		
		<%--赛事类型已写死在页面 --%>
		<div class="form-group form-group-sm">
		    <label for="type" class="col-md-3 control-label">赛事类型</label>
		    <div class="col-md-6 has-feedback form-inline">
		    	<input type="hidden" id="type_hidden" value="${event.type}"/>
				<select class="form-control" name="type" id="type">
					<option id="option0" value="0" >--篮球--</option>
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
		

	
			<div class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red"></span>赛事时间:</label>
                <div class="col-sm-4">
                     <div class="input-group col-md-6 has-feedback">
                         <input value="${event.eventDate}" type="text" name="eventDate" id="eventDate" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                         <label for="eventDate" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                     <span id="eventDate_span" class="text-danger text-red">${event_date_error}</span>
                </div>
			</div>	
	      
	    
			<div class="form-group form-group-sm">
				<label for="ateam" class="col-md-3 control-label"><span class="text-red"></span>约战方队名:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="ateam" name="ateam" value="${event.ateam}" /><span id="ateam_span"  class="text-danger text-red">${ateam_error}</span>
			    </div>
			</div>
			<div class="form-group form-group-sm">
				<label for="bteam" class="col-md-3 control-label"><span class="text-red"></span>应战方队名:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="bteam" name="bteam" value="${event.bteam}" /><span id="bteam_span" class="text-danger text-red">${bteam_error}</span>
			    </div>
			</div>
      
	      <div class="form-group form-group-sm">
	         <div class="col-md-6 has-feedback col-md-offset-3">
           		<img src="${event.alogo}" width="80" height="50">
	         </div>
	      </div>      
      
	      <div class="form-group form-group-sm">
	         <label for="photo" class="col-md-3 control-label"><span class="text-red"></span>约战方LOGO:</label>
	         <div class="col-md-6 has-feedback">
           		<input type="file" class="form-control" id="photo" name="photo" value="${event.alogo}" /><span id="photo_span"  class="text-danger text-red">${photo_error}</span>
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <div class="col-md-6 has-feedback col-md-offset-3">
           		<img src="${event.blogo}" width="80" height="50">
	         </div>
	      </div>     
	      
   	      <div class="form-group form-group-sm">
	         <label for="photo1" class="col-md-3 control-label"><span class="text-red"></span>应战方LOGO:</label>
	         <div class="col-md-6 has-feedback">
           		<input type="file" class="form-control" id="photo1" name="photo1" value="${event.blogo}"/><span id="photo1_span"  class="text-danger text-red">${photo1_error}</span>
	         </div>
	      </div>
	      
     	  <div class="form-group form-group-sm">
	         <label for="ascore" class="col-md-3 control-label"><span class="text-red"></span>约战方得分:</label>
	         <div class="col-md-6 has-feedback">
	         	<input type="text" class="form-control" id="ascore" name="ascore" value="${event.ascore}" /><span id="ascore_span"  class="text-danger text-red">${ascore_error}</span>
	         </div>
	      </div>
	      
         <div class="form-group form-group-sm">
	         <label for="bscore" class="col-md-3 control-label"><span class="text-red"></span>应战方得分:</label>
	         <div class="col-md-6 has-feedback">
	         	<input type="text" class="form-control" id="bscore" name="bscore" value="${event.bscore}" /><span id="bscore_span" class="text-danger text-red">${bscore_error}</span>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="referee" class="col-md-3 control-label"><span class="text-red"></span>公证方:</label>
	         <div class="col-md-6 has-feedback">
	         	<input type="text" class="form-control" id="referee" name="referee" value="${event.referee}" /><span id="referee_span"  class="text-danger text-red">${referee_error}</span>
	         </div>
	      </div>
	      
     	  <div class="form-group ">
	         <label for="introduction" class="col-md-3 control-label"><span class="text-red"></span>赛事介绍:</label>
	         <div class="col-md-6 has-feedback">
	             <script id="myEditor" name="myEditor" type="text/plain"></script>
				 <span id="introduction_span" class="text-danger text-red">${introduction_error}</span> 
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red"></span>赛事地址:</label>
	         <div class="col-md-6 has-feedback">
	         	<input type="text" class="form-control" id="address" name="address" value="${event.address}" /><span id="address_span" class="text-danger text-red">${address_error}</span>
	         </div>
	      </div>

	      
      	 <div class="form-group form-group-sm">
			<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/admin/event/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>         
	      	<div class="col-md-2">
		    	<button id = "submit_button" type="button" class="btn btn-primary btn-block" > 保存 </button>
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
<script type="text/javascript"src="${ctx}/static/ueditor/ueditor.config.js"></script>  
<script type="text/javascript"src="${ctx}/static/ueditor/ueditor.all.js"></script>  
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

<script type="text/javascript">
<%--加载ueditor--%>  
var um = UE.getEditor('myEditor');  
um.ready(function(){um.setContent($("#introduction").val());}); 
$(function(){
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
	menu.active('#event-form');
	$("#submit_button").click(function(){
		var content = um.getContentLength(true);
		if(content >10000){
			alert("赛事介绍：字数超出最大允许值！");
			//um.setFocus();
			return;
		}
		$("#introduction").val(um.getContent());
		$("#inputForm").submit();
	});
	$("#ateam").focus(function(){
		$("#ateam_span").remove();
		$("#success_span").remove();
	});
	$("#bteam").focus(function(){
		$("#bteam_span").remove();
		$("#success_span").remove();
	});
	$("#ascore").focus(function(){
		$("#ascore_span").remove();
		$("#success_span").remove();
	});
	$("#bscore").focus(function(){
		$("#bscore_span").remove();
		$("#success_span").remove();
	});
	$("#referee").focus(function(){
		$("#referee_span").remove();
		$("#success_span").remove();
	});
	$("#introduction").focus(function(){
		$("#introduction_span").remove();
		$("#success_span").remove();
	});
	$("#address").focus(function(){
		$("#address_span").remove();
		$("#success_span").remove();
	});
	$("#eventDate").focusout(function(){
		$("#eventDate_span").remove();
		$("#success_span").remove();
	});
	$("#photo").focusout(function(){
		$("#photo_span").remove();
		$("success_span").remove();
	});
	$("#photo1").focusout(function(){
		$("#photo1_span").remove();
		$("success_span").remove();
	});
	$("#adminFooter").hide();
});
</script>
