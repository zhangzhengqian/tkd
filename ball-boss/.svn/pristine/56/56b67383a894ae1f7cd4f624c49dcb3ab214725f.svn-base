<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<div class="panel panel-default">
  <c:choose>
  	<c:when test="${param.action == 'edit' }">
  		<c:set var="disable" value="false"/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly'"/>
  	</c:otherwise>
  </c:choose>
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 我签约的场馆</li>
	       	<li <c:if test="${param.action !='edit' }">class="active"</c:if>>营业资质</li>
	        <c:if test="${param.action == 'edit' }">
		        <li class="active" >修改</li>
	        </c:if>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<form id="inputForm" action="${ctx}/admin/businessForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/admin/org/businessForm/${userId }" />
		<input type="hidden" name="id" value="${business.id }" />	
		<input type="hidden" name="cb" value="${userId}" />
		
		<fieldset>
		    <div class="form-group form-group-sm">
		       <label for="orgCode" class="col-md-3 control-label"><span class="text-red">* </span>组织机构代码:</label>
		       <div class="col-md-6 has-feedback">
		         <input ${readonly} type="text" class="form-control" id="orgCode" name="orgCode" value="${business.orgCode }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="bizLicenseCode" class="col-md-3 control-label"><span class="text-red">* </span>营业执照编号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly} type="text" class="form-control" id="bizLicenseCode" name="bizLicenseCode" value="${business.bizLicenseCode }" />
			    </div>
			</div>
      
	      <div class="form-group form-group-sm">
	         <label for="bizLicenseAddress" class="col-md-3 control-label"><span class="text-red">* </span>营业执照所在:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly} type="text" class="form-control" id="bizLicenseAddress" name="bizLicenseAddress" value="${business.bizLicenseAddress }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">* </span>营业执照有效期:</label>
	         <div class="col-md-6 has-feedback">

				<div class="form-inline">  
					<c:if test="${param.action == 'edit' }">
	             	<input ${readonly} type="text" class="form-control Wdate input-sm" id="bizLicenseBeginTimeStr" name="bizLicenseBeginTimeStr" value="<fmt:formatDate value="${business.bizLicenseBeginTime }" pattern="yyyy-MM-dd"/>" placeholder="开始日期" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'bizLicenseEndTimeStr\')||\'%y-%M-%d\'}'})">
	                <input ${readonly} type="text" class="form-control Wdate input-sm" id="bizLicenseEndTimeStr" name="bizLicenseEndTimeStr" value="<fmt:formatDate value="${business.bizLicenseEndTime }" pattern="yyyy-MM-dd"/>" placeholder="结束日期" onfocus="WdatePicker({firstDayOfWeek:1,minDate:'#F{$dp.$D(\'bizLicenseBeginTimeStr\')}'})">
					</c:if>
					<c:if test="${param.action != 'edit' }">
						<c:if test="${empty business.bizLicenseBeginTime}">
							长期有效
						</c:if>
						<c:if test="${not empty business.bizLicenseBeginTime}">
				           <input ${readonly} type="text" size="25" class="form-control" id="bizLicenseAddress" name="bizLicenseAddress" value="<fmt:formatDate value="${business.bizLicenseBeginTime }" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${business.bizLicenseEndTime }" pattern="yyyy-MM-dd"/>" />	
						</c:if>
					</c:if>
	            </div> 

	         </div>
	      </div>
	
	      <div class="form-group form-group-sm">
	         <label for="bizScope" class="col-md-3 control-label"><span class="text-red">* </span>法定经营范围:</label>
	         <div class="col-md-6 has-feedback">
	         	<textarea ${readonly } style="height: 70px;" class="form-control" rows="3" id="bizScope" name="bizScope">${business.bizScope }</textarea>
	         </div>
	      </div>
	      <hr/>
	      <div class="form-group form-group-sm">
	         <label for="orgImgFile" class="col-md-3 control-label"><span class="text-red">* </span>组织机构代码:</label>
	         <div class="col-md-6 has-feedback" >
	         	<img alt="" src="${business.orgImg }" height="100">
	    		<c:if test="${empty readonly }">
	            	<input type="file" class="form-control" id="orgImgFile" name="orgImgFile" />
	    		</c:if>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="bizLicenseImgFile" class="col-md-3 control-label"><span class="text-red">* </span>营业执照副本:</label>
	         <div class="col-md-6 has-feedback">
	         	<img alt="" src="${business.bizLicenseImg }" height="100">
	    		<c:if test="${empty readonly }">
	            	<input type="file" class="form-control" id="bizLicenseImgFile" name="bizLicenseImgFile" />
	    		</c:if>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="taxpayerProveImgFile" class="col-md-3 control-label"><span class="text-red">* </span>一般纳税人证明:</label>
	         <div class="col-md-6 has-feedback">
	           <img alt="" src="${business.taxpayerProveImg}" height="100">
	    		<c:if test="${empty readonly }">
	            	<input type="file" class="form-control" id="taxpayerProveImgFile" name="taxpayerProveImgFile" />
	    		</c:if>
	         </div>
	      </div>
	      <hr/> 
			<div class="form-group">
			<c:if test="${empty readonly }">
				<div class="col-md-offset-3 col-md-2">	
		    		<a href="${ctx }/admin/org/businessForm/${userId}" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
		    		<button type="submit" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
	    	<c:if test="${not empty readonly }">
				<div class="col-md-offset-3 col-md-2">
					<a href="${ctx }/admin/statium/manager" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-2">
					<a href="${ctx }/admin/org/businessForm/${userId }?action=edit" class="btn btn-primary btn-block" > 修改 </a>
				</div>
	    	</c:if>
			</div>

		</fieldset>
	</form>

  </div>
	
</div>

<script type="text/javascript">
$(function() {
	  menu.active('#statium-manager-man');
});

$(function() {
	menu.active('#business-man');
	
	$('#inputForm').validate({
		rules: {
			orgCode: {
				required: true
			},
			bizLicenseCode: {
				required: true
			},
			bizLicenseAddress: {
				required: true
			},
			bizLicenseBeginTimeStr: {
				required: true
			},
			bizLicenseEndTimeStr: {
				required: true
			},
			bizScope: {
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
