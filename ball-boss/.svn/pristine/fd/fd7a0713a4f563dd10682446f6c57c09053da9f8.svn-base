<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
		<ul class="breadcrumb">
	        <li ><span class="glyphicon glyphicon-home"></span>账户信息</li>
	        <li class="active">公司资质</li>
	    </ul> 
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->

	
  
	<form id="inputForm" action="${ctx}/admin/companyForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		
		<fieldset>
			<legend><small>公司信息${param.action }</small></legend>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>公司名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input type="text" class="form-control" id="name" name="name" value="${vo.name }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>公司LOGO:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="file" class="form-control" id="logoFile" name="logoFile" value="" />
			    </div>
			</div>
      
      <div class="form-group form-group-sm">
         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>公司电话:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="tel" name="tel" value="${vo.tel }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="email" class="col-md-3 control-label"><span class="text-red">* </span>公司邮箱:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="email" name="email" value="${vo.email }" />
         </div>
      </div>

      <div class="form-group form-group-sm">
         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
         <div class="col-md-6 has-feedback">
           <tags:zone name="areaCode" id="areaCode" clazz="false" value="${vo.areaCode }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>公司地址:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="address" name="address" value="${vo.address }" />
         </div>
      </div>
      
      <legend><small>法人信息</small></legend>
      
      <div class="form-group form-group-sm">
         <label for="liablePersion" class="col-md-3 control-label"><span class="text-red">* </span>法人名称:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="liablePersion" name="liablePersion" value="${vo.liablePersion}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>法人电话:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="liableTel" name="liableTel" value="${vo.liableTel }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableIdCard" class="col-md-3 control-label"><span class="text-red">* </span>法人身份证号:</label>
         <div class="col-md-6 has-feedback">
           <input type="text" class="form-control" id="liableIdCard" name="liableIdCard" value="${vo.liableIdCard }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableIdCardImg1" class="col-md-3 control-label"><span class="text-red">* </span>法人身份证正面:</label>
         <div class="col-md-6 has-feedback">
           <input type="file" class="form-control" id="liableIdCardImg1File" name="liableIdCardImg1File" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableIdCardImg2" class="col-md-3 control-label"><span class="text-red">* </span>法人身份背面:</label>
         <div class="col-md-6 has-feedback">
           <input type="file" class="form-control" id="liableIdCardImg2File" name="liableIdCardImg2File" />
         </div>
      </div>
      
		<div class="form-group">
			<div class="col-md-offset-3 col-md-2">
			</div>
			<div class="col-md-2">
			  <button type="submit" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
			</div>
		</div>

		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">
$(function() {
	menu.active('#company-man');
	
	$('#inputForm').validate({
		rules: {
			name: {
				required: true
			  //, remote: "${ctx}/admin/user/checkNickname?oldName=" + encodeURIComponent('')
			},
			logoFile: {
				required: true
			},
			tel: {
				required: true
			},
			email: {
				required: true
			},
			areaCode: {
				required: true
			},
			address: {
				required: true
			},
			liablePersion: {
				required: true
			},
			liableTel: {
				required: true
			},
			liableIdCard: {
				required: true
			},
			liableIdCardImg1File: {
				required: true
			},
			liableIdCardImg2File: {
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
