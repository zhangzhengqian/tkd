<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <li class="active">球馆信息</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  
	<% request.setAttribute("status",com.lc.zy.ball.boss.common.SessionUtil.currentUser().getStatus());	%>
    <c:if test="${'UNPASS' eq status }" >
  	<div class="panel-heading"><!-- 右侧标题 -->
  	
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1" align="center">
				<h3>资料没有通过审核，请按照客服提示进行修改，并提交审核</h3>
			</div>
		</div>
	</div>	
 	</c:if>  
 
  
  <c:choose>
  	<c:when test="${param.action == 'edit' }">
  		<c:set var="disable" value="false"/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly'"/>
  	</c:otherwise>
  </c:choose>
  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/admin/statiumForm"  method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/member/org/statiumForm" />
		<input type="hidden" name="id" value="${statium.id }" />
		<fieldset>

	      <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">*</span>场馆坐标:</label>
	         <div class="col-md-6 has-feedback form-inline">
		        
		        <input ${readonly } type="text" class="form-control" id="lnglat" name="lnglat" value="<c:if test="${ not empty statium.lng}">${statium.lng },${statium.lat}</c:if>" placeholder="经度,纬度" />
				<c:if test="${param.action=='edit' }">
					<a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="_blank">坐标识取工具</a>
				</c:if>	
         	</div>

	      </div>
	      
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>球馆名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input ${readonly } type="text" class="form-control" id="name" name="name" value="${statium.name }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="branchName" class="col-md-3 control-label"><span class="text-red"></span>分店名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="branchName" name="branchName" value="${statium.branchName }" />
			    </div>
			</div>
      
	      <div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red">* </span>球馆LOGO:</label>
	         <div class="col-md-6 has-feedback">
	            <img alt="" src="${statium.logo }" height="100">
				<c:if test="${empty readonly }">
	           		<input type="file" class="form-control uploadImgStyle" id="logoFile" name="logoFile" />
	    		</c:if>
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
			 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>是否免费:</label>
		     <div class="col-md-6 has-feedback">
		    	    <label class="radio-inline"><input type="radio" name="isRating" value="1"  <c:if test="${statium.isRating == 1}"> checked="checked" </c:if> >是</label>
                    <label class="radio-inline"><input type="radio" name="isRating" value="0"  <c:if test="${statium.isRating != 1}"> checked="checked" </c:if>>否</label>
		     </div>
		 </div>
		 
	      <div class="form-group form-group-sm">
			 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>是否签约:</label>
		     <div class="col-md-6 has-feedback">
		    	    <label class="radio-inline"><input type="radio" name="isSigned" value="1"  <c:if test="${statium.isSigned == 1}"> checked="checked" </c:if> >是</label>
                    <label class="radio-inline"><input type="radio" name="isSigned" value="0"  <c:if test="${statium.isSigned != 1}"> checked="checked" </c:if>>否</label>
		     </div>
		 </div>
	      
<%--    		 <div class="form-group form-group-sm">
			 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>场馆折扣:</label>
		     <div class="col-md-6 has-feedback">
		    	 <input ${readonly } type="text" class="form-control" id="qiuyouRating" name="qiuyouRating" value="${statium.qiuyouRating }" placeholder="折扣只能填一位小数,且值小于1" /><span id="qiuyouRating_span"  class="text-danger text-red"></span>
		     </div>
		 </div> --%>
		 
		 <div class="form-group form-group-sm">
			<label for="morningPrice" class="col-md-3 control-label"><span class="text-red">*</span>门市价格早(7:00~10:00):</label>
		    <div class="col-md-6 has-feedback">
		    	<input readonly  type="text" class="form-control" id="morningPrice" name="morningPrice" value="${statium.morningPrice}" placeholder="" /><span id="morningPriceSpan"  class="text-danger text-red"></span>
	 	 </div>
		 </div>
 		 <div class="form-group form-group-sm">
			<label for="afternoonPrice" class="col-md-3 control-label"><span class="text-red">*</span>门市价格中(10:00~16:00):</label>
		    <div class="col-md-6 has-feedback">
		    	<input readonly  type="text" class="form-control" id="afternoonPrice" name="afternoonPrice" value="${statium.afternoonPrice}" placeholder="" /><span id="afternoonPriceSpan"  class="text-danger text-red"></span>
		 	</div>
		 </div>	 
 		 <div class="form-group form-group-sm">
			<label for="eveningPrice" class="col-md-3 control-label"><span class="text-red">*</span>门市价格晚(16:00~22：00):</label>
		    <div class="col-md-6 has-feedback">
		    	<input readonly  type="text" class="form-control" id="eveningPrice" name="eveningPrice" value="${statium.eveningPrice}" placeholder="" /><span id="eveningPriceSpan"  class="text-danger text-red"></span>
		 	</div>
		 </div>	 
 		 <div class="form-group form-group-sm">
			<label for="holidayMorningPrice" class="col-md-3 control-label"><span class="text-red">*</span>节假日门市价格早(7:00~10:00):</label>
		    <div class="col-md-6 has-feedback">
		    	<input readonly  type="text" class="form-control" id="holidayMorningPrice" name="holidayMorningPrice" value="${statium.holidayMorningPrice}" placeholder="" /><span id="holidayMorningPriceSpan"  class="text-danger text-red"></span>
		 	</div>
		 </div>	 
 		 <div class="form-group form-group-sm">
			<label for="holidayAfternoonPrice" class="col-md-3 control-label"><span class="text-red">*</span>节假日门市价格中(10:00~16:00):</label>
		    <div class="col-md-6 has-feedback">
		    	<input readonly  type="text" class="form-control" id="holidayAfternoonPrice" name="holidayAfternoonPrice" value="${statium.holidayAfternoonPrice}" placeholder="" /><span id="holidayAfternoonPriceSpan"  class="text-danger text-red"></span>
		 	</div>
		 </div>	 
		 <div class="form-group form-group-sm">
			<label for="holidayEveningPrice" class="col-md-3 control-label"><span class="text-red">*</span>节假日门市价格晚(16:00~22：00):</label>
		    <div class="col-md-6 has-feedback">
		    	<input readonly  type="text" class="form-control" id="holidayEveningPrice" name="holidayEveningPrice" value="${statium.morningPrice}" placeholder="" /><span id="holidayEveningPriceSpan"  class="text-danger text-red"></span>
		 	</div>
		 </div>	 
	      
         <div class="form-group form-group-sm">
              <label for="startTime" class="col-md-3 control-label"><span class="text-red">* </span>开馆时间:</label>
              <div class="col-md-6 has-feedback">
                  <div class="input-group">
                      <input type="text" value="${statium.startTime}" name="startTime" id="startTime"  onClick="WdatePicker({dateFmt:'H:mm'})" class="form-control" placeholder="从什么时候开始" readonly>
                      <label for="startTime" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                  </div>
              </div>
          </div>
          
          <div class="form-group form-group-sm">
              <label for="endTime" class="col-md-3 control-label"><span class="text-red">* </span>闭馆时间:</label>
              <div class="col-md-6 has-feedback">
                  <div class="input-group">
                      <input type="text" value="${statium.endTime}" name="endTime" id="endTime"   onClick="WdatePicker({dateFmt:'H:mm'})" class="form-control" placeholder="到什么时间结束" readonly>
                      <label for="endTime" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                  </div>
              </div>
          </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>场馆电话:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="tel" name="tel" value="${statium.tel }" />
	         </div>
	      </div>
	
	      <div class="form-group form-group-sm">
	         <label for="email" class="col-md-3 control-label"><span class="text-red"></span>电子邮箱:</label>
	         <div class="col-md-6 has-feedback">
	         	<input ${readonly } type="text" class="form-control" id="email" name="email" value="${statium.email }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
	         <div class="col-md-6 has-feedback" >
				<tags:zone id="areaCode" name="areaCode" value="${statium.areaCode }" clazz="false" disabled="${disable }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>详细地址:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="address" name="address" value="${statium.address}" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传场馆图片:</label>
	         <div class="col-md-6">
	         	<c:if test="${not empty statium.photos }">
	         		<c:forEach items="${fn:split(statium.photos,'__') }" var="itm">
	         			<img alt="" src="${itm }" height="100">
	         		</c:forEach> 
	         	</c:if>
	         	<c:if test="${empty readonly }">
	         	<div class="has-feedback">
	           		<input type="file" class="form-control uploadImgStyle" name="photo1" />
	           	</div>
	           	<div class="has-feedback">
	           		<input type="file" class="form-control uploadImgStyle" name="photo2" />
	           	</div>
	           	<div class="has-feedback">
	           		<input type="file" class="form-control uploadImgStyle" name="photo3" />
	           	</div>
	           	<div class="has-feedback">
	           		<input type="file" class="form-control uploadImgStyle" name="photo4" />
	           	</div>
	           	<div class="has-feedback">
	           		<input type="file" class="form-control uploadImgStyle" name="photo5" />
	           	</div>
	    		</c:if>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>场馆设施:</label>
	         <div class="col-md-7 has-feedback">
	           	<div class="btn-group" data-toggle="buttons">
	           		<c:forEach items="储物柜,更衣室,热水淋浴,商店,室内,停车场,wifi,休息室,夜场灯光,银行卡,主场裁判,主场教练,租赁" var="facility" >
	           			<c:set var="active" value="" />
						<c:set var="checked" value="" />	
						<c:forEach items="${fn:split(statium.facilities,',') }" var="obj" >
							<c:if test="${obj == facility}">
								<c:set var="active" value="active abc" />	
								<c:set var="checked" value="checked" />
							</c:if>	
						</c:forEach>
			       		<label class="btn btn-default ${active }">
					  		<input name="facilities" type="checkbox" autocomplete="off" ${checked } value="${facility }"> ${facility }
						</label>
					</c:forEach>
				</div>
	         </div>
	      </div> 
	      
	 	  <div class="form-group form-group-sm">
	         <label for="introduce" class="col-md-3 control-label"><span class="text-red"></span>场馆介绍:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="introduce" name="introduce" value="${statium.introduce }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="masterName" class="col-md-3 control-label"><span class="text-red">* </span>负责人姓名:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="masterName" name="masterName" value="${statium.masterName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="masterTel" class="col-md-3 control-label"><span class="text-red">* </span>联系电话:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="masterTel" name="masterTel" value="${statium.masterTel }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>场地类别:</label>
	         <div class="col-md-7 has-feedback">
	           	<div class="btn-group" data-toggle="buttons">
	           	<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
					<c:set var="active" value="" />
					<c:set var="checked" value="" />	
					<c:forEach items="${fn:split(statium.sportType,';;') }" var="obj" >
						<c:if test="${obj eq item.itemCode}">
							<c:set var="active" value="active abc" />	
							<c:set var="checked" value="checked" />	
						</c:if>	
					</c:forEach>
					<label class="btn btn-default ${active }">
				  		<input name="sportTypes" type="checkbox" autocomplete="off" ${checked } value="${item.itemCode }"> ${item.itemName }
					</label>
		       	</c:forEach>
				</div>
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCard" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="masterIdCard" name="masterIdCard" value="${statium.masterIdCard }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCardImg1File" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证正面:</label>
	         <div class="col-md-6 has-feedback">
	           <img alt="" src="${statium.masterIdCardImg1 }" height="100">
				<c:if test="${empty readonly }">
	           		<input type="file" class="form-control uploadImgStyle" id="masterIdCardImg1File" name="masterIdCardImg1File" />
	    		</c:if>
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCardImg2File" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证背面:</label>
	         <div class="col-md-6 has-feedback">
	         	<img alt="" src="${statium.masterIdCardImg2 }" height="100">
				<c:if test="${empty readonly }">
	           		<input type="file" class="form-control uploadImgStyle" id="masterIdCardImg2File" name="masterIdCardImg2File" />
	    		</c:if>
	         </div>
	      </div>

		  <div class="form-group form-group-sm">
    		 <label for="accountType" class="col-md-3 control-label">*开户类型</label>
		     <div class="col-md-6 has-feedback form-inline">
				 <select class="form-control" value="${statium.accountType }" name="accountType" id="accountType" >
				 	<option id="person_option"  value="person" >--个人--</option>
					<option id="company_option"  value="company">--企业--</option>
				 </select>
		  	 </div>
	      </div>

	      <div class="form-group form-group-sm" id="accountCert_div">
	         <label for="accountCert" class="col-md-3 control-label"><span class="text-red"></span>*开户行所登记的身份证:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="accountCert" name="accountCert" value="${statium.accountCert}" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="bankAccountName" class="col-md-3 control-label"><span class="text-red"></span>银行开户名:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" maxlength="50" id="bankAccountName" name="bankAccountName" value="${statium.bankAccountName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankAccountNo" class="col-md-3 control-label"><span class="text-red"></span>银行账号:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="bankAccountNo" name="bankAccountNo" value="${statium.bankAccountNo }" />
	         </div>
	      </div>
	      
	      
	      <div class="form-group form-group-sm">
	         <label for="bankAccountBranchName" class="col-md-3 control-label"><span class="text-red"></span>开户支行名称:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" maxlength="50" id="bankAccountBranchName" name="bankAccountBranchName" value="${statium.bankAccountBranchName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankAccountBranchNo" class="col-md-3 control-label"><span class="text-red"></span>支行联行号:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="bankAccountBranchNo" name="bankAccountBranchNo" value="${statium.bankAccountBranchNo }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankLicenseImgFile" class="col-md-3 control-label"><span class="text-red"></span>开户银行许可证:</label>
	         <div class="col-md-6 has-feedback">
	           <img alt="" src="${statium.bankLicenseImg}" height="100">
				<c:if test="${empty readonly }">
	           		<input type="file" class="form-control uploadImgStyle" id="bankLicenseImgFile" name="bankLicenseImgFile" />
	    		</c:if>
	         </div>
	      </div>

		<hr/>
		<shiro:hasRole name="statium_manager">
		<div class="form-group">
			<c:if test="${empty readonly }">
				<div class="col-md-offset-3 col-md-2">	
		    		<a href="${ctx }/member/org/statiumForm" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
		    		<button type="submit" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
	    	<c:if test="${not empty readonly }">
				<div class="col-md-offset-3 col-md-2">
					<a href="${ctx }/member/org/statiumForm?action=edit" class="btn btn-primary btn-block" > 修改 </a>
				</div>
				<div class="col-md-2">
					<c:if test="${'UNPASS' eq status }" >
						<a href="${ctx }/register/pushForm" class="btn btn-default btn-block" > 资料修改完成 </a>	
					</c:if>
				</div>
	    	</c:if>
		</div>
		</shiro:hasRole>
		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">
$(function() {
	menu.active('#statium-man');
	$('#inputForm').validate({
		rules: {
			name: {
				required: true,
				maxlength:50
			},
			/*branchName: {
				required: true
			},*/
			tel: {
				required: true
			},
			/* email: {
				required: true
			}, */
			areaCode: {
				required: true
			},
			address: {
				required: true
			},
			masterName: {
				required: true
			},
			masterTel: {
				required: true
			},
			sportTypes : {
				required: true
			},
            startTime : {
                required : true
            },
            endTime : {
                required : true
            },
			/* masterIdCard : {
				required: true
			} */
			accountCert:{
				required : true,
				number:true,
			    minlength:18
            },lnglat:{
				required: true,
				isCoordinate:true
			},facilities : {
				required: true
			}
		},
		messages: {
			accountCert:{
				required : "必须填写",
				number:"请输入正确的身份证号",
			    minlength:"请输入正确的身份证号"
            }
		}
	});
	$('#footer').hide();
});

$(function(){
	
	$("input[name=sportTypes],input[name=facilities]").parent("label").click(function(){
		if($(this).hasClass("abc")){
			$(this).removeClass("abc");
		}else{
			$(this).addClass("abc");
		}
		if($(this).parent("div").find("label[class*=abc]").size()==0){
			//只处理  消除error
			//$(this).parent("div").parent("div").removeClass("has-error").addClass("has-error");
			//$(this).parent("div").parent("div").find("p").remove();
			//$(this).parent("div").parent("div").append("<p id=\"sportTypes-error\" class=\"help-block\">必填字段</p>")
		}else{
			$(this).parent("div").parent("div").removeClass("has-error");
			$(this).parent("div").parent("div").find("p").remove();
		}
	});
	 $(window).load(function(){
		var accountTypeValue ='${statium.accountType }';
		if(accountTypeValue == "person"){
			 $("#accountCert").rules("add",{required:true});  
			$("#accountCert_div").css("display","block");
			$("#person_option").attr("selected","selected");
		}
		if(accountTypeValue == "company"){
			$("#accountCert_div").css("display","none");
			$("#accountCert").val("");
			$("#company_option").attr("selected","selected");
		}	
		if('${statium.id }'){
			$("#logoFile").parent().parent().find("label span").html("");
		}else{
			$("#logoFile").addClass("required");
		}
	});
	$("#accountType").change(function(){
		var accountTypeValue =$("#accountType option:selected").val();
		if(accountTypeValue == "person"){
			 $("#accountCert").rules("add",{required:true});  
			$("#accountCert_div").css("display","block");
			$("#person_option").attr("selected","selected");
		}
		if(accountTypeValue == "company"){
			$("#accountCert_div").css("display","none");
			$("#accountCert").val("");
			$("#company_option").attr("selected","selected");
		}
	});
	
});

$(function(){
	  $("#qiuyouRating").blur(function(){
		  	var value = $("#qiuyouRating").val();
		  	if(value!=""){
		  		var patrn= new RegExp("^0.[1-9]{1,2}$");
		  		if(!patrn.exec(value)){
		  			$("#qiuyouRating_span").html("<p id='qiuyouRating_p'>折扣只能填一位小数,且值小于0</p>");
		  		}else{
		  			$("#qiuyouRating_p").remove();
		  		}
		  	}
		  	if(value == ""){
		  		$("#qiuyouRating_p").remove();
		  	}
		  });
});
</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
