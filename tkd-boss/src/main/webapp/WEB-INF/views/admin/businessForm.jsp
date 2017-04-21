<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
	<ul class="breadcrumb">
        <li ><span class="glyphicon glyphicon-home"></span>账户信息</li>
        <li class="active">营业资质</li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/admin/companyForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		
		<fieldset>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>组织机构代码:</label>
		       <div class="col-md-6 has-feedback">
		         <input type="text" class="form-control" id="orgCode" name="orgCode" value="${vo.orgCode }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>营业执照编号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="file" class="form-control" id="logoFile" name="logoFile" value="" />
			    </div>
			</div>
      
	      <div class="form-group form-group-sm">
	         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>营业执照所在:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="email" name="email" value="${vo.email }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="email" class="col-md-3 control-label"><span class="text-red">* </span>营业执照有效期:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="email" name="email" value="${vo.email }" />
	         </div>
	      </div>
	
	      <div class="form-group form-group-sm">
	         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>法定经营范围:</label>
	         <div class="col-md-6 has-feedback">
	         	<textarea style="height: 70px;" class="form-control" rows="3"></textarea>
	         </div>
	      </div>
	      <hr/>
	      <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>组织机构代码:</label>
	         <div class="col-md-6 has-feedback" >
	           <input type="file" class="form-control" id="address" name="address" value="${vo.address }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="liablePersion" class="col-md-3 control-label"><span class="text-red">* </span>营业执照副本:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control" id="liablePersion" name="liablePersion" value="${vo.liablePersion}" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>一般纳税人证明:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control" id="liableTel" name="liableTel" value="${vo.liableTel }" />
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
	menu.active('#business-man');
	
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
