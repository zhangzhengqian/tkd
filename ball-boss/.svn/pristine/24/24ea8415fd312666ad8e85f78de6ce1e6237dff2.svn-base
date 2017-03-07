<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->

		<div class="row">
		    <div class="col-xs-10 col-xs-offset-1">
		        <div class="progress progress-chart">
		            <span class="progress-chart-1st"></span>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		        </div><!--// progress:end -->
		        <div class="progress-chart-text">
		            <span class="finish">注册账号</span>
		            <span class="finish">公司资质</span>
		            <span class="finish">营业资质</span>
		            <span class="finish">球馆信息</span>
		            <span>提交审核</span>
		            <span class="progress-chart-text-last">完成</span>                            
		        </div>
		    </div>
	  	</div>
	
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/admin/statiumForm"  method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/register/push_audit" />
		<input type="hidden" name="_pass" value="true" />
		<fieldset>

	      <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">* </span>场馆坐标:</label>
	         <div class="col-md-6 has-feedback form-inline">
		         <input type="text" class="form-control" id="lnglat" name="lnglat" value="<c:if test="not empty vo.lng">${vo.lng },${vo.lat}</c:if>" placeholder="经度,纬度" />
				<a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="_blank">坐标识取工具</a>
         	</div>

	      </div>
	      
 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>球馆名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input type="text" class="form-control" id="name" name="name" value="${vo.name }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="branchName" class="col-md-3 control-label"><span class="text-red"></span>分店名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="branchName" name="branchName" value="${vo.branchName }" />
			    </div>
			</div>
      
	      <div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red">* </span>球馆LOGO:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control uploadImgStyle" id="logoFile" name="logoFile" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>场馆电话:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="tel" name="tel" value="${vo.tel }" />
	         </div>
	      </div>
	
	      <div class="form-group form-group-sm">
	         <label for="email" class="col-md-3 control-label"><span class="text-red"></span>电子邮箱:</label>
	         <div class="col-md-6 has-feedback">
	         	<input type="text" class="form-control" id="email" name="email" value="${vo.email }" />
	         </div>
	      </div>

	      <div id="div_areaCode" class="form-group form-group-sm">
	         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
	         <div class="col-md-6 has-feedback" >
				<tags:zone id="areaCode" name="areaCode" value="${vo.areaCode }" clazz="false" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>详细地址:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="address" name="address" value="${vo.address}" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传场馆图片:</label>
	         <div class="col-md-6">
	         	<div class="has-feedback">
	           <input type="file" class="form-control uploadImgStyle" name="photosFile" />
	           </div>
	            <div class="has-feedback">
	           <input type="file" class="form-control uploadImgStyle" name="photosFile" />
	           </div>
	           <div class="has-feedback">
	           <input type="file" class="form-control uploadImgStyle" name="photosFile" />
	           </div>
	           <div class="has-feedback">
	           <input type="file" class="form-control uploadImgStyle" name="photosFile" />
	           </div>
	           <div class="has-feedback">
	           <input type="file" class="form-control uploadImgStyle" name="photosFile" />
	           </div>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="introduce" class="col-md-3 control-label"><span class="text-red">* </span>场馆介绍:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="introduce" name="introduce" value="${vo.introduce}" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="masterName" class="col-md-3 control-label"><span class="text-red">* </span>负责人姓名:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="masterName" name="masterName" value="${vo.masterName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="masterTel" class="col-md-3 control-label"><span class="text-red">* </span>联系电话:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="masterTel" name="masterTel" value="${vo.masterTel }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="sportTypes" class="col-md-3 control-label"><span class="text-red">* </span>场地类别:</label>
	         <div class="col-md-6 has-feedback">
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
				  		<input id="sportTypes" name="sportTypes" type="checkbox" autocomplete="off" ${checked } value="${item.itemCode }"> ${item.itemName }
					</label>
		       	</c:forEach>
				</div>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="facilities" class="col-md-3 control-label"><span class="text-red">* </span>场馆设施:</label>
	         <div class="col-md-7 has-feedback">
	           	<div class="btn-group" data-toggle="buttons">
	           		<c:forEach items="储物柜,更衣室,热水淋浴,商店,室内,停车场,wifi,休息室,夜场灯光,银行卡,主场裁判,主场教练,租赁" var="facility" >
	           			<c:set var="active" value="" />
						<c:set var="checked" value="" />	
						<c:forEach items="${fn:split(vo.facilities,',') }" var="obj" >
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
	         <label for="masterIdCard" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="masterIdCard" name="masterIdCard" value="${vo.masterIdCard }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCardImg1File" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证正面:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control uploadImgStyle" id="masterIdCardImg1File" name="masterIdCardImg1File" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCardImg2File" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证背面:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control uploadImgStyle" id="masterIdCardImg2File" name="masterIdCardImg2File" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
    		 <label for="accountType" class="col-md-3 control-label">*开户类型</label>
		     <div class="col-md-6 has-feedback form-inline">
				 <select class="form-control"  name="accountType" id="accountType">
				 	 <option id="person_option"  value="person" <c:if test = "$(vo.accountType) eq 'person' ">selected</c:if>>--个人--</option>
					 <option id="company_option"  value="company" <c:if test = "$(vo.accountType) eq 'company' ">selected</c:if>>--企业--</option>
				 </select>
		  	 </div>
	      </div>
	      <div class="form-group form-group-sm" id="accountCert_div">
	         <label for="accountCert" class="col-md-3 control-label"><span class="text-red">*</span>开户行所登记的身份证:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="accountCert" name="accountCert" value="${vo.accountCert }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="bankAccountName" class="col-md-3 control-label"><span class="text-red"></span>银行开户名:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="bankAccountName" name="bankAccountName" value="${vo.bankAccountName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankAccountNo" class="col-md-3 control-label"><span class="text-red"></span>银行账号:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="bankAccountNo" name="bankAccountNo" value="${vo.bankAccountNo }" />
	         </div>
	      </div>
	      
	      
	      <div class="form-group form-group-sm">
	         <label for="bankAccountBranchName" class="col-md-3 control-label"><span class="text-red"></span>开户支行名称:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="bankAccountBranchName" name="bankAccountBranchName" value="${vo.bankAccountBranchName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankAccountBranchNo" class="col-md-3 control-label"><span class="text-red"></span>支行联行号:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="bankAccountBranchNo" name="bankAccountBranchNo" value="${vo.bankAccountBranchNo }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankLicenseImgFile" class="col-md-3 control-label"><span class="text-red"></span>开户银行许可证:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control uploadImgStyle" id="bankLicenseImgFile" name="bankLicenseImgFile" />
	         </div>
	      </div>
		
			<div class="form-group">
				<div class="col-md-offset-3 col-md-2">
				</div>
				<div class="col-md-2">
				  <button type="submit" class="btn btn-primary btn-block" id="submit_btn"> 下一步 >> </button>
				</div>
			</div>

		</fieldset>
	</form>

  </div>
	
</div>
<script type="text/javascript">

<c:if test="${'admin' == _from}">
$(function() {
	  menu.active('#statium-manager-man');
});
</c:if>

$(function() {
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
	
	menu.active('#business-man');
	$("#div_areaCode select:eq(2)").each(function(){
		$(this).addClass("required");
	});
	 $(window).load(function(){
			var accountTypeValue =$("#accountType option:selected").attr("value");
			if(accountTypeValue == "person"){
				$("#accountCert_div").css("display","block");
				$("#accountCert").addClass("required");
			}
			if(accountTypeValue == "company"){
				$("#accountCert_div").css("display","none");
				$("#accountCert").val("");
			}		
		});
	$("#accountType").change(function(){
		var accountTypeValue =$("#accountType option:selected").val();
		if(accountTypeValue == "person"){
			$("#accountCert_div").css("display","block");
		}
		if(accountTypeValue == "company"){
			$("#accountCert_div").css("display","none");
			$("#accountCert").val("");
		}
	});
	
	$('#inputForm').validate({
		rules: {
			lnglat:{
				required: true,
				isCoordinate:true
			},
			name: {
				required: true,
				maxlength:50
			},
			logoFile:{
				required: true
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
			sportTypes: {
				required: true
			},facilities : {
                required : true
            }
			/* masterIdCard : {
				required: true
			} */
		},
		messages: {
		}
	});

});

</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
