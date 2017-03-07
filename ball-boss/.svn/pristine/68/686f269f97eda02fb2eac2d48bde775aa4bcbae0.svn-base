<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">


	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 站点设置</li>
	        <li <c:if test="${param.action !='edit' }">class="active"</c:if>>公司资质</li>
	        <c:if test="${param.action == 'edit' }">
		        <li class="active" >修改</li>
	        </c:if>
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
  
	<form id="inputForm" action="${ctx}/admin/companyForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/member/org/companyForm" />
		<input type="hidden" name="id" value="${company.id }" />
		<fieldset>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>公司名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input ${readonly } type="text" class="form-control" id="name" name="name" value="${company.name }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>公司LOGO:</label>
			    <div class="col-md-6 has-feedback">
			    	<img alt="" src="${company.logo }" height="60">
			    	<c:if test="${empty readonly }">
				    	<input type="file" class="form-control" id="logoFile" name="logoFile" value="" />
			    	</c:if>
			    </div>
			</div>
      
      <div class="form-group form-group-sm">
         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>公司电话:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="tel" name="tel" value="${company.tel }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="email" class="col-md-3 control-label"><span class="text-red">* </span>公司邮箱:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="email" name="email" value="${company.email }" />
         </div>
      </div>

      <div class="form-group form-group-sm">
         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
         <div class="col-md-6 has-feedback">
           <tags:zone name="areaCode" id="areaCode" clazz="false" value="${company.areaCode }" disabled="${disable }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>公司地址:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="address" name="address" value="${company.address }" />
         </div>
      </div>
      
      
      <div class="form-group form-group-sm">
         <label for="liablePersion" class="col-md-3 control-label"><span class="text-red">* </span>法人名称:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="liablePersion" name="liablePersion" value="${company.liablePersion}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>法人电话:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="liableTel" name="liableTel" value="${company.liableTel }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableIdCard" class="col-md-3 control-label"><span class="text-red">* </span>法人身份证号:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="liableIdCard" name="liableIdCard" value="${company.liableIdCard }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableIdCardImg1" class="col-md-3 control-label"><span class="text-red">* </span>法人身份证正面:</label>
         <div class="col-md-6 has-feedback">
         
         	<img alt="" src="${company.liableIdCardImg1 }" height="100">
	    	<c:if test="${empty readonly }">
	           <input type="file" class="form-control" id="liableIdCardImg1File" name="liableIdCardImg1File" />
	    	</c:if>
         
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableIdCardImg2" class="col-md-3 control-label"><span class="text-red">* </span>法人身份背面:</label>
         <div class="col-md-6 has-feedback">
            <img alt="" src="${company.liableIdCardImg2 }" height="100">
	    	<c:if test="${empty readonly }">
	           <input type="file" class="form-control" id="liableIdCardImg2File" name="liableIdCardImg2File" />
	    	</c:if>
         </div>
      </div>
      
      <hr/>
		<div class="form-group">
			<c:if test="${empty readonly }">
				<div class="col-md-offset-3 col-md-2">	
		    		<a href="${ctx }/member/org/companyForm" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
		    		<button type="submit" class="btn btn-primary btn-block"> 保存 </button>
				</div>
	    	</c:if>
	    	<c:if test="${not empty readonly }">
				<div class="col-md-offset-3 col-md-2">
					<a href="${ctx }/member/org/companyForm?action=edit" class="btn btn-primary btn-block" > 修改 </a>
				</div>
				<div class="col-md-2">
					<c:if test="${'UNPASS' eq status }" >
						<a href="${ctx }/register/pushForm" class="btn btn-default btn-block" > 资料修改完成 </a>	
					</c:if>
				</div>
	    	</c:if>
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
				required: true,
				maxlength:50
			  //, remote: "${ctx}/admin/user/checkNickname?oldName=" + encodeURIComponent('')
			},
			logoFile:{
				uploadImgStyle: true
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
				uploadImgStyle: true
			},
			liableIdCardImg2File: {
				uploadImgStyle: true
			}
		},
		messages: {
		}
	});
	$('#footer').hide();
});
</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
