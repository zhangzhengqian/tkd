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
		            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
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

	
  
	<form id="inputForm" action="${ctx}/admin/companyForm" onsubmit="return check()"  method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
		<input type="hidden" name="next_page" value="/register/push_audit" />
		<input type="hidden" name="_pass" value="true" />
		<fieldset>
			<legend><small>公司资质</small></legend>
		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>公司名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input type="text" class="form-control" id="name" name="name" value="${vo.name }" />
		       </div>
		    </div>
	    
			<div class="form-group form-group-sm">
				<label for="logo" class="col-md-3 control-label"><span class="text-red">* </span>公司LOGO:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="file" class="form-control" id="logoFile" name="logoFile" value="" onChange="javascript:CheckFileType();"/>
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
				,isPhoneOrIsMobile: true
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
				,isPhoneOrIsMobile: true
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
			tel: {
				remote: '电话格式不正确，请重新输入！'
			},
			liableTel: {
				remote: '电话格式不正确，请重新输入！'
			},
		}
	});
	<c:if test="${'admin' == param._from}">
		menu.active('#statium-manager-man');
	</c:if>	
});

// 图片文件校验
function CheckFileType(){
	var filepath = document.getElementById("logoFile").value;
    if(filepath==""){
  		alert("请选择上传的文件！");
     	return;
  	}
    var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
 	extname = extname.toLowerCase();//处理了大小写
  	var str=["jpg","JPG","jpeg","gif","GIF","bmp","BMP","png","PNG","PSD","psd"];
  	if(str.indexOf(extname) == -1){//校验格式
  		alert("请上传正确的图片格式！");
  		document.getElementById('logoFile').value = "";
   		return;
  	 }
}

function check(){
	 if($('#areaCode').val() == ""){
		    alert("请选择所在地区");
	        return false;
	    }else{
	        return true;
	    }
}

</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
