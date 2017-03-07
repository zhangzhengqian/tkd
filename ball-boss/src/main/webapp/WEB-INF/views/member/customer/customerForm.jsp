<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">


	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	       	<li><span class="glyphicon glyphicon-home active"></span> 会员管理</li>
	        <c:if test="${param.action == 'create' }">
		        <li class="active" >新增</li>
	        </c:if>
	        <c:if test="${param.action == 'edit' }">
		        <li class="active" >修改</li>
	        </c:if>
	        <c:if test="${param.action == 'view' }">
		        <li class="active" >查看</li>
	        </c:if>
	        
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <c:choose>
  	<c:when test="${param.action == 'create' }">
  		<c:set var="disable" value="false"/>
		<c:set var="readonly" value=" "/>
  	</c:when>
  	<c:when test="${param.action == 'edit' }">
  		<c:set var="disable" value="false"/>
		<c:set var="readonly" value=" "/>
  	</c:when>
  	<c:when test="${action == 'repeat' }">
  		<c:set var="disable" value="false"/>
		<c:set var="readonly" value=" "/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly' "/>
  	</c:otherwise>
  </c:choose>
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/member/customer/customerForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/member/customer" />
		<input type="hidden" name="id" value="${customer.id }" />
		<fieldset>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">*</span>会员名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input ${readonly } type="text" class="form-control" id="realName" name="realName" value="${customer.realName}" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>联系电话:</label>
			    <div class="col-md-6 has-feedback">
		         <input ${readonly } type="text" class="form-control" id="phone" name="phone" value="${customer.phone}" />
			    </div>
			</div>
      
      <div class="form-group form-group-sm">
         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>性别:</label>
         <div class="col-md-6 has-feedback">
			<select class="form-control" name="gender" <c:if test="${ disable=='true' }">disabled="disabled"</c:if> >
  				<option value="男" <c:if test="${customer.gender eq '男' }">selected="selected"</c:if> >-男-</option>
  				<option value="女" <c:if test="${customer.gender eq '女' }">selected="selected"</c:if> >-女-</option>
			</select>	
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="email" class="col-md-3 control-label"><span class="text-red">* </span>出生年月:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" onClick="pickDate()" id="birthday" name="birthday" value="${customer.birthday }" />
         </div>
      </div>

      <div class="form-group form-group-sm">
         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
         <div id="div_areaCode" class="col-md-6 has-feedback">
           <tags:zone name="areaCode" id="areaCode" clazz="false" value="${customer.areaCode }" disabled="${disable }" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>身份证号:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="idCard" name="idCard" value="${customer.idCard }" />
         </div>
      </div>
      
      
      <div class="form-group form-group-sm">
         <label for="liablePersion" class="col-md-3 control-label"><span class="text-red">* </span>家庭住址:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="address" name="address" value="${customer.address}" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>邮箱:</label>
         <div class="col-md-6 has-feedback">
           <input ${readonly }type="text" class="form-control" id="email" name="email" value="${customer.email }" />
         </div>
      </div>
     <c:if test="${param.action=='view' }">
     <hr>
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>创建人:</label>
         <div class="col-md-6 has-feedback">
         	
           <input readonly="readonly" type="text" class="form-control" value="<tags:getUserById id="${customer.cb }" />" />
         </div>
      </div>
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>创建日期:</label>
         <div class="col-md-6 has-feedback">
           <input readonly="readonly" type="text" class="form-control" value="<fmt:formatDate value="${customer.ct }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
         </div>
      </div>
      
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>最后修改人:</label>
         <div class="col-md-6 has-feedback">
         	
           <input readonly="readonly" type="text" class="form-control" value="<tags:getUserById id="${customer.eb }" />" />
         </div>
      </div>
      <div class="form-group form-group-sm">
         <label for="liableTel" class="col-md-3 control-label"><span class="text-red">* </span>最后修改日期:</label>
         <div class="col-md-6 has-feedback">
           <input readonly="readonly" type="text" class="form-control" value="<fmt:formatDate value="${customer.et }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
         </div>
      </div>
      
     </c:if> 
     
     	<hr/> 
		<div class="form-group">
			<c:if test="${readonly==' ' }">
				<div class="col-md-offset-3 col-md-2">	
		    		<a href="${ctx }/member/customer" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
		    		<button type="submit" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
	    	<c:if test="${readonly!=' '}">
	    		<div class="col-md-offset-3 col-md-2">	
		    		<a href="javascript:window.history.go(-1);" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
					<a href="${ctx }/member/customer/customerForm?action=edit&id=${customer.id}" class="btn btn-primary btn-block" > 修改 </a>
				</div>
	    	</c:if>
		</div>

		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">
function pickDate(){
	var now=new Date();
	
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var date=now.getDate(); 
    WdatePicker({maxDate:year+"-"+month+"-"+date,dateFmt:'yyyy-MM-dd'});
} 
$(function() {
	
	menu.active('#customer-man');
	$("#div_areaCode select:eq(2)").each(function(){
		$(this).addClass("required");
	});
	
	$('#inputForm').validate({
		rules: {
			realName: {
				required: true
			},
			phone: {
				required: true
			},
			gender: {
				required: true
			},
			birthday: {
				required: true
			},
			areaCode: {
				required: true
			},
			idCard: {
				required: true
			},
			address: {
				required: true
			},
			email: {
				required: true
			}
		},
		messages: {
		}
	});
	 $('#footer').hide();
	 if("repeat"=="${action}"){
          alert("用户名或者手机号重复")	; 
	 }
});
</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
