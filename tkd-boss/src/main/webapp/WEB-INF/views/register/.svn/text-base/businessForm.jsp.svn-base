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
		            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
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
  
	<form id="inputForm" action="${ctx}/admin/businessForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/register/push_audit" />	
		<input type="hidden" name="_pass" value="true" />
		<fieldset>
		    <div class="form-group form-group-sm">
		       <label for="orgCode" class="col-md-3 control-label"><span class="text-red">* </span>组织机构代码:</label>
		       <div class="col-md-6 has-feedback">
		         <input type="text" class="form-control" id="orgCode" name="orgCode" value="${vo.orgCode }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="bizLicenseCode" class="col-md-3 control-label"><span class="text-red">* </span>营业执照编号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="bizLicenseCode" name="bizLicenseCode" value="${vo.bizLicenseCode }" />
			    </div>
			</div>
      
	      <div class="form-group form-group-sm">
	         <label for="bizLicenseAddress" class="col-md-3 control-label"><span class="text-red">* </span>营业执照所在:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="text" class="form-control" id="bizLicenseAddress" name="bizLicenseAddress" value="${vo.bizLicenseAddress }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">* </span>营业执照有效期:</label>
	         <div class="col-md-6 has-feedback">

				<div class="form-inline">  
	             	<input type="text" class="form-control Wdate input-sm" id="bizLicenseBeginTimeStr" name="bizLicenseBeginTimeStr" value="${vo.bizLicenseBeginTime }" placeholder="开始日期" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'bizLicenseEndTimeStr\')||\'%y-%M-%d\'}'})">
	                
	                <input type="text" class="form-control Wdate input-sm" id="bizLicenseEndTimeStr" name="bizLicenseEndTimeStr" value="${vo.bizLicenseEndTime }" placeholder="结束日期" onfocus="WdatePicker({firstDayOfWeek:1,minDate:'#F{$dp.$D(\'bizLicenseBeginTimeStr\')}'})">
	            </div> 

	         </div>
	      </div>
	
	      <div class="form-group form-group-sm">
	         <label for="bizScope" class="col-md-3 control-label"><span class="text-red">* </span>法定经营范围:</label>
	         <div class="col-md-6 has-feedback">
	         	<textarea style="height: 70px;" class="form-control" rows="3" id="bizScope" name="bizScope">${vo.bizScope }</textarea>
	         </div>
	      </div>
	      <hr/>
	      <div class="form-group form-group-sm">
	         <label for="orgImgFile" class="col-md-3 control-label"><span class="text-red">* </span>组织机构代码:</label>
	         <div class="col-md-6 has-feedback" >
	           <input type="file" class="form-control uploadImgStyle" id="orgImgFile" name="orgImgFile" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="bizLicenseImgFile" class="col-md-3 control-label"><span class="text-red">* </span>营业执照副本:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control uploadImgStyle" id="bizLicenseImgFile" name="bizLicenseImgFile" value="" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="taxpayerProveImgFile" class="col-md-3 control-label"><span class="text-red">* </span>一般纳税人证明:</label>
	         <div class="col-md-6 has-feedback">
	           <input type="file" class="form-control uploadImgStyle" id="taxpayerProveImgFile" name="taxpayerProveImgFile" />
	         </div>
	      </div>
	        <hr>    
			<div class="form-group">
				<div class="col-md-offset-3 col-md-2">
				<c:if test="${'admin' == param._from}">
					<a class="btn btn-default btn-block" href="${ctx }/admin/statium/manager">返回</a>
				</c:if>
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
			},
			orgImgFile: {
				required: true
			},
			bizLicenseImgFile: {
				required: true
			},
			taxpayerProveImgFile: {
				required: true
			}
		},
		messages: {
		}
	});
	<c:if test="${'admin' == param._from}">
		menu.active('#statium-manager-man');
	</c:if>		
});
</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
