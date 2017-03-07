<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <li class="active">教练/陪练录入</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
 
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<span  class="text-danger text-red" id = "error_span"><h3 style="text-align:center" id="error_h3">${error}</h3></span>
	<form id="inputForm" action="${ctx}/admin/coach/coachForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
			<input type="hidden" name="nextPage" value="/admin/coachForm" />			
			<input type="hidden" id="userId" name="userId" value="<%=id%>" /> 
			<input type="hidden" id="introduce" name="introduce" value="${coachVo.introduce}" />
			<input type="hidden" id="id" name="ssoUser.id" value="${coachVo.ssoUser.id}"/>
			<input type="hidden" id="coachId" name="coachId" value="${coachVo.coachId}"/> 
			<input type="hidden" id="oldPhone" name="oldPhone" value="${coachVo.ssoUser.phone}"/> 
			
		<fieldset>
		
			<div class="form-group form-group-sm">
				<label for="ssoUser.name" class="col-md-3 control-label"><span class="text-red"></span>姓名:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="name" name="ssoUser.name" value="${coachVo.ssoUser.name}" /><span id="name_span" class="text-danger text-red">${name_error}</span>
			    </div>
			</div>

			<div class="form-group form-group-sm">
				<label for="ssoUser.nickName" class="col-md-3 control-label"><span class="text-red"></span>*昵称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="nickName" name="ssoUser.nickName" value="${coachVo.ssoUser.nickName}" /><span id="nickName_span" class="text-danger text-red">${nickName_error}</span>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="ssoUser.sign" class="col-md-3 control-label"><span class="text-red"></span>个人签名:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="sign" name="ssoUser.sign" value="${coachVo.ssoUser.sign}" />
			    </div>
			</div>			
		
			<div class="form-group form-group-sm">
			    <label for="ssoUser.sex" class="col-md-3 control-label">*性别</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	 <input type="hidden" id="sex_hidden" value="${coachVo.ssoUser.sex}"/> 
					<select class="form-control" name="ssoUser.sex" id="sex">
						<option id="option10" value="男" >--男性--</option>
						<option id="option11" value="女" >--女性--</option>
					</select>
			  	</div>
			 </div>		

			<div class="form-group form-group-sm">
				<label for="ssoUser.phone" class="col-md-3 control-label"><span class="text-red"></span>*手机号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="phone" name="ssoUser.phone" value="${coachVo.ssoUser.phone}" /><span id="phone_span" class="text-danger text-red">${phone_error}</span>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="statiumTelephone" class="col-md-3 control-label"><span class="text-red"></span>场馆电话:</label>
			    <div class="col-md-6 has-feedback">
			    	<input  type="text" class="form-control" id="statiumTelephone" name="statiumTelephone" value="${coachVo.statiumTelephone}" /><span id="statiumTelephone_span" class="text-danger text-red">${statiumTelephone_error}</span>
			    </div>
			</div>	
			
			<div class="form-group form-group-sm">
			    <label for="property" class="col-md-3 control-label">教练/陪练</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input type="hidden" id="property_hidden" value="${coachVo.ssoUser.property}"/>
					<select class="form-control" name="ssoUser.property" id="property">
						<option id="option8"  value="教练" >--教练--</option>
						<option id="option9"  value="陪练" >--陪练--</option>
					</select>
			  	</div>
			</div>				
			
			<div class="form-group form-group-sm" id="div_sportType">
			    <label for="sportType" class="col-md-3 control-label">*运动类型</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<input type="hidden" id="sportType_hidden" value="${coachVo.sportType}"/>
					<select class="form-control" name="sportType" id="sportType">
						<option id="option0"  value="0" >--篮球--</option>
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
			
	      	<div class="form-group form-group-sm" id="div_tags">
	        	<label for="tags" class="col-md-3 control-label"><span class="text-red">* </span>陪练类型:</label>
	         	<div class="col-md-7 has-feedback">
	           	<div class="btn-group" data-toggle="buttons" id="changSportType">
	           	<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item" varStatus="status" >
 					<c:set var="active" value="" />	
					<c:set var="checked" value="" />	
					<c:forEach items="${fn:split(coachVo.ssoUser.tags, ',') }" var="obj" >
						<c:if test="${obj == item.itemName}">
							<c:set var="active" value="active" />	
							<c:set var="checked" value="checked" />	
						</c:if>	
					</c:forEach> 
					<label  class="btn btn-default label_tags ${active }"  id="label${status.index}">
				  		<input class="input_tags"  name="ssoUser.tags" type="checkbox" ${checked}  autocomplete="off" id="changSportType${status.index}"  onchange="changeSportType('${status.index}')"  value="${item.itemCode }"> ${item.itemName }
					</label>
		       	</c:forEach>
				</div>
	         	</div>
	     	 </div>

			<div class="form-group form-group-sm">
			    <label for="ssoUser.birthday" class="col-md-3 control-label">*出生日期</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<%-- <input type="hidden" id="type_hidden" value="${event.type}"/> --%>
                  <div class="input-group">
                      <input type="text" value="${coachVo.ssoUser.birthday}" name="ssoUser.birthday" id="birthday"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" class="form-control" placeholder="年龄" readonly>
                      <label for="ssoUser.birthday" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                  </div>
                  <span id="birthday_span" class="text-danger text-red">${birthday_error}</span>
			  	</div>
			</div>

			<div class="form-group form-group-sm">
				<label for="trainingYears" class="col-md-3 control-label"><span class="text-red"></span>球龄:</label>
			    <div class="col-md-6 has-feedback">
			    	<div class="form-inline">
			    		<input placeholder="请填写0-100之间的整数"  type="text" class="form-control" id="trainingYears" name="trainingYears" value="${coachVo.trainingYears}" /><span>年</span>
			    	</div>
			    	<span id="trainingYears_span" class="text-danger text-red">${trainingYears_error}</span>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
			    <label for="teachingMethod" class="col-md-3 control-label">*授课方式</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<%-- <input type="hidden" id="type_hidden" value="${event.type}"/> --%>
					<select class="form-control" value="coachVo.teachingMethod" name="teachingMethod" id="teachingMethod">
						<option  value="0" <c:if test="${coachVo.teachingMethod == 0}">selected</c:if>>--一对一--</option>
						<option  value="1" <c:if test="${coachVo.teachingMethod == 1}">selected</c:if>>--一对多--</option>
					</select>
			  	</div>
			</div>
			
			<div class="form-group form-group-sm">
			    <label for="ssoUser.price" class="col-md-3 control-label">*价格</label>
			    <div class="col-md-6 has-feedback form-inline">
					<input placeholder="非负数保留两位有效数字"  type="text" class="form-control" id="price" name="ssoUser.price" value="${coachVo.ssoUser.price}" /><span>元</span>
					<select class="form-control">
						<option  value="0" >--时--</option>
					</select>
					&nbsp;&nbsp;<input type="checkbox" name="isContainStatium" id="isContainStatium" value="1" /><span>包含场地费用</span><input type="hidden" id="isContainStatium_hidden" value="${coachVo.isContainStatium}"/>
			  	</div>
			  	<span id="price_span" class="text-danger text-red">${price_error}</span>
			</div>
			
			<div class="form-group form-group-sm">
			    <label for="" class="col-md-3 control-label">教习时间</label>
			    <div class="col-md-6 has-feedback form-inline">
			    	<%-- <input type="hidden" id="type_hidden" value="${event.type}"/> --%>
                  <div class="input-group">
                      <input type="text" value="${coachVo.startTime}" name="startTime" id="startTime"  onClick="WdatePicker({dateFmt:'H:mm',maxDate:'#F{$dp.$D(\'endTime\')}'})"   class="form-control" placeholder="从什么时候开始" readonly>
                      <label for="startTime" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                  </div>
                 	 到
                  <div class="input-group">
                      <input type="text" value="${coachVo.endTime}" name="endTime" id="endTime"  onClick="WdatePicker({dateFmt:'H:mm',minDate:'#F{$dp.$D(\'startTime\')}'})"  class="form-control" placeholder="从什么时候结束" readonly>
                      <label for="endTime" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                  </div>
			  	</div>
			  	<span id="startTime_span" class="text-danger text-red">${startTime_error}</span>
			  	<span id="endTime_span" class="text-danger text-red">${endTime_error}</span>
			</div>
			
	      <div class="form-group form-group-sm">
	         <label for="areaCode" class="col-md-3 control-label"><span class="text-red"></span>*所在地区:</label>
	         <div class="col-md-6 has-feedback" id="div_areaCode">
				 <tags:zone id="areaCode" name="areaCode" value="${coachVo.areaCode}" clazz="false"  />
				 <input placeholder="场馆名称" id="statiumName" name="statiumName" value="${coachVo.statiumName}"  />
				 <span id="areaCode_span" class="text-danger text-red">${areaCode_error}</span> 
	         </div>
	      </div>								

	      <div class="form-group form-group-sm">
		  	 <label for="coachLevel" class="col-md-3 control-label"><span class="text-red"></span>教/陪练资质:</label>
		     <div class="col-md-6 has-feedback">
		    	 <input placeholder="最多输入10个汉字长度内"  type="text" class="form-control" id="coachLevel" name="coachLevel" value="${coachVo.coachLevel}" /><span id="coachLevel_span" class="text-danger text-red">${coachLevel_error}</span>
		     </div>
		  </div>	

	      <div class="form-group form-group-sm">
		  	 <label for="ssoUser.cardId" class="col-md-3 control-label"><span class="text-red"></span>身份证号:</label>
		     <div class="col-md-6 has-feedback">
		    	 <input type="text" class="form-control" id="cardId" name="ssoUser.cardId" value="${coachVo.ssoUser.cardId}" /><span id="cardId_span" class="text-danger text-red">${cardId_error}</span>
		     </div>
		  </div>	

   	      <div class="form-group form-group-sm">
	         <label for="photo1" class="col-md-3 control-label"><span class="text-red"></span>身份证正面照:</label>
	         <div class="col-md-6 has-feedback">
     	        <c:if test="${not empty coachVo.ssoUser.certList }">
	         		<c:forEach items="${fn:split(coachVo.ssoUser.certList,',') }" var="itm">
	         			<img alt="" src="${itm }" height="100">
	         		</c:forEach> 
	         	</c:if>
           		<input type="file" class="form-control" id="photo1" name="photo1" /><span id="photo1_span"  class="text-danger text-red">${photo1_error}</span>
	         </div>
	      </div>

   	      <div class="form-group form-group-sm">
	         <label for="photo2" class="col-md-3 control-label"><span class="text-red"></span>身份证反面照:</label>
	         <div class="col-md-6 has-feedback">
           		<input type="file" class="form-control" id="photo2" name="photo2" /><span id="photo2_span"  class="text-danger text-red">${photo2_error}</span>
	         </div>
	      </div>	

   	      <div class="form-group form-group-sm">
	         <label for="photo9" class="col-md-3 control-label"><span class="text-red"></span>*教练/陪练头像:</label>
	         <div class="col-md-6 has-feedback">
        	    <c:if test = "${not empty coachVo.ssoUser.photo && not empty coachVo.ssoUser.id}">
	         		<img alt="" src="${coachVo.ssoUser.photo}" height="100">	
	        	</c:if>
           		<input type="file" class="form-control uploadImgStyle" id="photo9" name="photo9" /><span id="photo9_span"  class="text-danger text-red">${photo9_error}</span>
	         </div>
	      </div>	

	      <div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">个人风采:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${not empty coachVo.ssoUser.elegantList }">
	         		<c:forEach items="${fn:split(coachVo.ssoUser.elegantList,',') }" var="itm">
	         			<img alt="" src="${itm }" height="100">
	         		</c:forEach> 
	         	</c:if>
	           		<input type="file" class="form-control" name="photo3" />
	           		<input type="file" class="form-control" name="photo4" />
	           		<input type="file" class="form-control" name="photo5" />
	           		<input type="file" class="form-control" name="photo6" />
	           		<input type="file" class="form-control" name="photo7" />
	           		<input type="file" class="form-control" name="photo8" />
	         </div>
	      </div>

     	  <div class="form-group ">
	         <label for="introduce" class="col-md-3 control-label"><span class="text-red"></span>教练/陪练介绍:</label>
	         <div class="col-md-6 has-feedback">
	             <script id="myEditor" name="myEditor" type="text/plain"></script>
	         </div>
	      </div>

      	 <div class="form-group form-group-sm">
		  	<div class="col-md-offset-3 col-md-2">
			   <a class="btn btn-default btn-block" href="${ctx}/admin/coach/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
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
um.ready(function(){um.setContent($("#introduce").val());}); 
$(function(){
 	$(window).load(function(){
 		var property_val = $("#property").val();
 		var property_value = $("#property_hidden").val();
		var type_value =$("#sportType_hidden").val();
		var sex_value = $("#sex_hidden").val();
		var isContainStatium_value = $("#isContainStatium_hidden").val();
		
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
		if(isContainStatium_value==1){
			$("#isContainStatium").attr("checked","checked");	
		}
		
		if(property_value == '教练'){
			$("#option8").attr("selected","selected");
			$("#div_tags").css("display","none");
			$("#div_sportType").css("display","block");
		}else{
			$("#option9").attr("selected","selected");
			$("#div_sportType").css("display","none");
			$("#div_tags").css("display","block");
		}
		
		if(sex_value == '男'){
			$("#option10").attr("selected","selected");	
		}
		if(sex_value == '女'){
			$("#option11").attr("selected","selected");	
		}		
	});
 	$("#property").change(function(){
 		var property_val = $("#property").val();
		if(property_val == '教练'){
			$("#div_tags").css("display","none");
			$("#div_sportType").css("display","block");
		}
		if(property_val == '陪练'){
			$("#div_sportType").css("display","none");
			$("#div_tags").css("display","block");
		}		 		
 	});
	menu.active('#coach-manager-form');
	
	$("#div_areaCode select:eq(2)").each(function(){
		$(this).addClass("required");
	});
	if(!$("#id").val()){
		$("#photo9").addClass("required");
	}
	
 	$('#inputForm').validate({
		rules: {
			"ssoUser.nickName":{
				required:true
			},
			"ssoUser.phone": {
				required: true
				,isMobile: true
			},
			"ssoUser.tags": {
				required: true
			},
			"ssoUser.birthday": {
				required: true
			},"ssoUser.price": {
				required: true
				,isMoney: true
			},"trainingYears": {
				range:[0,100],
				digits:true
			},"areaCode": {
				required: true   
			},"coachLevel": {
				maxlength:10
			},"ssoUser.cardId": {
				isEasyIdCard: true
			}
		},
		messages: {
			trainingYears:{required:"球龄不能为空 ",digits:"必须为整数",range:"请输入一个介于 {0} 和 {1} 之间的值"}
		}
	}); 
	
 	$("#submit_button").click(function(){
		$("#introduce").val(um.getContent());
		$("#inputForm").submit();
	});
 	$("#name").focus(function(){
		$("#name_span").remove();
		$("error_span").remove();
	});
	$("#statiumTelephone").focus(function(){
		$("#statiumTelephone_span").remove();
		$("error_span").remove();
	});
	$("#birthday").focus(function(){
		$("#birthday_error").remove();
		$("error_span").remove();
	});
	$("#trainingYears").focus(function(){
		$("#trainingYears_span").remove();
		$("error_span").remove();
	});
	$("#price").focus(function(){
		$("#price_span").remove();
		$("error_span").remove();
	});
	$("#startTime").focus(function(){
		$("#startTime_span").remove();
		$("error_span").remove();
	});
	$("#endTime").focus(function(){
		$("#endTime_span").remove();
		$("error_span").remove();
	});
	$("#cardId").focusout(function(){
		$("#cardId_span").remove();
		$("error_span").remove();
	});
	$("#photo1").focusout(function(){
		$("#photo1_span").remove();
		$("error_span").remove();
	});
	$("#photo2").focusout(function(){
		$("#photo2_span").remove();
		$("error_span").remove();
	});  
	$("#areaCode").focusout(function(){
		$("#areaCode_span").remove();
		$("error_span").remove();
	});	
	$("#adminFooter").hide();
});
function changeSportType(index){
	var num=$(".input_tags:checked").length;
	if(num>4){
		alert("陪练运动类型不能多于4个!");
		$("#label"+index).addClass("active");
		$("#changSportType"+index).attr('checked',false);
	}
} 
</script>