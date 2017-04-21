<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<style type="text/css">
		#allmap {width: 100%;height: 500px;margin:0;position:relative;}
		#golist {display: none;}
		@media (max-device-width: 780px){#golist{display: block !important;}}
	.img_close{
		position: relative;
		top: -110px;
		right: -115px;
		cursor: pointer;
		font-size: 25px;
		background-color: #FF6F00;
		height: 20px;
		width: 20px;
		border-radius: 50%;
		line-height: 20px;	
	}
	</style>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span><a href="${ctx }/statiumOa/"> 道馆列表 </a></li>
	        <li class="active">道馆信息</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <c:choose>
  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
  		<c:set var="disable" value="false"/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly'"/>
  	</c:otherwise>
  </c:choose>  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<h3>道馆信息</h3>
	<hr>
	<form id="inputForm" action="${ctx}/statiumOa/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${statium.id}" />
		<input type="hidden" name="dgId" value="${statium.dgId}" />
		<input type="hidden" name="page" value="${page }" />
		<input type="hidden" name="pageSize" value="${pageSize }" />
		<fieldset>

 		    <div class="form-group form-group-sm">
		       <label for="dgName" class="col-md-3 control-label"><span class="text-red">* </span>道馆名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input ${readonly } type="text" class="form-control" id="dgName" name="dgName" value="${statium.dgName }" placeholder="请输入道馆名称" />
		       </div>
		    </div>
      
	      <div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red"></span>道馆LOGO:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${not empty statium.logo && not empty readonly}">
	         		<img alt="" src="${statium.logo }" <c:if test="${not empty statium.logo }">height="100" </c:if>>
	         	</c:if>
				<c:if test="${empty readonly }">
					<!-- 上传控件 -->
					<input id="logoFile" type="file" multiple="false" />
					<!-- 保存图片 -->
					<input id="logo" name="logo" type="hidden" value="${statium.logo}" />
					<!-- 显示图片 -->
					<img alt="" src="${statium.logo}" id="logo_img" <c:if test="${not empty statium.logo }">height="100" </c:if>>
	    		</c:if>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
	         <div id="div_areaCode" class="col-md-6 has-feedback form-inline" >
	         	<c:choose>
				  	<c:when test="${empty readonly }">
				  		<tags:zone id="areacode" name="areacode" value="${statium.areacode }"  disabled="${disable }" />
				  	</c:when>
				  	<c:otherwise>
						<tags:zonemap code="${statium.areacode }" />
				  	</c:otherwise>
				  </c:choose>  
				
	         </div>
	      </div>
	      
	     <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">*</span>道馆坐标:</label>
	         <div class="col-md-6 has-feedback form-inline">

				<div class="input-group">
					<input ${readonly } readOnly type="text" class="form-control" id="lnglat" name="lnglat" value="<c:if test="${ not empty statium.lng}">${statium.lng },${statium.lat}</c:if>" placeholder="经度,纬度" />
		         	<span class="input-group-btn">
		         		<c:if test="${empty readonly }">
		            		<button id="coordinate" class="btn btn-primary btn-sm" type="button">坐标/地址 识取工具</button>
		            	</c:if>	
		          	</span>
		        </div>
        	 </div>
	    </div>
	    
	    <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>详细地址:</label>
	         <div class="col-md-6 has-feedback ">
	           <input ${readonly } type="text" class="form-control" id="address" name="address" value="${statium.address}" />
	         </div>
	    </div>
	    
	    <div class="form-group form-group-sm" style="display: none;" id="baiduMap">
	         <label for="" class="col-md-3 control-label"></label>
	         <div class="col-md-6 has-feedback form-inline">
	         	<div class="panel panel-default">
	         		<div class="panel-heading">
							<input type="text" class="form-control" id="keyword"/>
							<button type="button" class="btn btn-primary btn-sm" id="search"><span class="glyphicon glyphicon-search"></span> 搜 索</button>	         			
	         				<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	         		</div>
	         		<div class="panel-body">
				       	<div id="allmap"></div>
	         		</div>
	         	</div>
        	 </div>
	    </div>
	    
	    <div class="form-group form-group-sm">
	         <label for="contact" class="col-md-3 control-label"><span class="text-red">* </span>道馆联系人:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="contact" name="contact" value="${statium.contact }" />
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>馆长电话:</label>
	         <div class="col-md-6 has-feedback ">
	           <input ${readonly } type="text" class="form-control" id="tel" name="tel" value="${statium.tel }" placeholder=""/>
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	      	 <label for="serviceTel" class="col-md-3 control-label"><span class="text-red"></span>前台电话:</label>
	      	 <div class="col-md-6 has-feedback">
	      	 	<input ${readonly } type="text" class="form-control" id="serviceTel" name="serviceTel" value="${statium.serviceTel }" placeholder=""/> 
	      	 </div>
	      </div>
	      <div class="form-group form-group-sm">
	      	 <label for="bankAccountName" class="col-md-3 control-label"><span class="text-red"></span>开户人:</label>
	      	 <div class="col-md-6 has-feedback">
	      	 	<input ${readonly } type="text" class="form-control" id="bankAccountName" name="bankAccountName" value="${statium.bankAccountName }" placeholder=""/> 
	      	 </div>
	      </div>
	      <div class="form-group form-group-sm">
	      	 <label for="bankAccountNo" class="col-md-3 control-label"><span class="text-red"></span>开户账号:</label>
	      	 <div class="col-md-6 has-feedback">
	      	 	<input ${readonly } type="text" class="form-control" id="bankAccountNo" name="bankAccountNo" value="${statium.bankAccountNo }" placeholder=""/> 
	      	 </div>
	      </div>
	      <div class="form-group form-group-sm">
	      	 <label for="masterName" class="col-md-3 control-label"><span class="text-red"></span>开户银行:</label>
	      	 <div class="col-md-6 has-feedback">
	      	 	<input ${readonly } type="text" class="form-control" id="bankAccountBranchName" name="bankAccountBranchName" value="${statium.bankAccountBranchName }" placeholder=""/> 
	      	 </div>
	      </div>

		<div class="form-group form-group-sm">
	         <label for="remark" class="col-md-3 control-label"><span class="text-red"></span>道馆介绍:</label>
	         <div class="col-md-6 has-feedback">
	         	<textarea  ${readonly } style="height: 100px;" class="form-control" rows="5" id="remark" placeholder="道馆介绍" name="remark">${statium.remark }</textarea>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm" id="qrCode">
	      	<label for="qrCode" class="col-md-3 control-label" ><span>道馆二维码：</span></label>
	      	<div class="col-md-6">
	      		<img src="${statium.qrCode }" id="img">
	      		<input value="${statium.qrCode }" type="hidden" id="qrCode1"/>
	      	</div>
	      </div>

		<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传道馆图片:</label>
	         <div class="col-md-6" name="statium_img" style="width: 135px;">
	         	 <input id="photo1File" type="file" multiple="false" />
	         	<c:set var="count" value="1"/>
	         </div>
	         <span name="picSpan" style="color:red;line-height:30px;">（标准尺寸：640px * 300px） </span>
	      </div>
	      <div class="form-group form-group-sm">
	       <label for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="col-md-6" name="statium_img"  >
	         	<c:set var="count" value="1"/>
	         	<c:if test="${not empty statium.photos}">
	         		<c:forEach items="${fn:split(statium.photos,'__') }" var="itm" varStatus="s">
	         			<c:if test="${s.last}">
	         				<c:set var="count" value="${s.index + 2}"/>
	         			</c:if>
	         			<div style="float:left;margin-right:10px;">
							<input id="photo${s.index + 1}" name="photo" value="${itm }" type="hidden" />
	         				<img alt="" src="${itm}" id="photo_img${s.index + 1}" height="100" width="130" />
	         				<!-- <div aria-hidden="true" class="img_close">&times;</div> -->
	         			</div>
	         		</c:forEach>
		         	<c:forEach var="i" begin="${count}" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	<c:if test="${empty statium.photos}">
	         		<c:forEach var="i" begin="1" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	
	         	<c:if test="${param.action == 'create' }">
					<div style="float:left;margin-right:10px;">
						<input id="photo1" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo2" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo3" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo4" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo5" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img5"/>
	           		</div>
	    		</c:if>
	         </div>
	      </div>

		<hr/>
		
		<div class="form-group">
			<div class="col-md-offset-3 col-md-3">	
	    		<a href="${ctx }/statiums/list?page=${page}&pageSize=${pageSize}" class="btn btn-default btn-block" > 返回 </a>
			</div>
			<c:if test="${empty readonly }">
				<div class="col-md-3">
		    		<button type="submit" id="submit_btn" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
		</div>
		</fieldset>
	</form>
	</div>
	</div>
	<%-- <div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon"></span> 道馆审核</li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
		<div class="panel-body">
		
			<form id="visitForm" action="${ctx}/statiumOa/statiumAudit" method="post" class="form-horizontal" enctype="multipart/form-data" >
				<input type="hidden" name="statiumId" value="${statium.dgId}" />
				<input type="hidden" name="page" value="${page }" />
				<input type="hidden" name="pageSize" value="${pageSize }" />
				<fieldset>
					<div class="form-group form-group-sm">
						 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>道馆描述:</label>
					     <div class="col-md-5 has-feedback">
				       		<input class="form-control"  style="height: 30px;" id="description" name="description" value="${statiumAuditLog.description }">
				       	 </div>
					 </div>
					 <div class="form-group form-group-sm">	
					 <label class="col-md-3 control-label"><span class="text-red"></span>审核操作:</label>
					<div class="col-md-5">
								<select class="form-control" id="action"
									name="action">
									<option value="">--请选择审核操作--</option>
									<option value="0" <c:if test="${statiumAuditLog.action=='0' }">selected</c:if>>--待审核--</option>
									<option value="1" <c:if test="${statiumAuditLog.action=='1' }">selected</c:if>>--审核通过--</option>
									<option value="2"<c:if test="${statiumAuditLog.action=='2' }">selected</c:if>>--审核拒绝--</option>
								</select>
					 </div>
					 </div>	
					<div class="form-group">
						<div class="col-md-offset-3 col-md-3">	
				    		<button type="reset" id="reset" class="btn btn-default btn-block" > 重置 </button>
						</div>
						<shiro:hasPermission name="bill:verifyStatus">
						<c:if test = "${empty readonly}">
						<div class="col-md-3">
				    		<button type="submit" id="verify" class="btn btn-primary btn-block" > 确认 </button>
						</div>
						</c:if>
						</shiro:hasPermission>
					</div>
					
				</fieldset>
			</form>
		</div>
</div>
<!-- 审核记录表 -->
<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon"></span> 审核记录 </li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
		<div class="panel-body">
   			<table class="table table-hover">
			    <thead>
					<tr>
						<th>#</th>
						<th>动作</th>
						<th>描述</th>
						<th>操作人</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${auditLog}" var="itm" varStatus="stat">
						<tr>
							<td>${stat.index+1 }</td>
							<c:if test="${itm.action=='0' }"><td>待审核</td></c:if>	
							<c:if test="${itm.action=='1' }"><td>审核通过</td></c:if>					
							<c:if test="${itm.action=='2' }"><td>审核拒绝</td></c:if>					
							<td>${itm.description }</td>						
							<td>${itm.cb }</td>						
							<td>
								<fmt:formatDate value="${itm.ct }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>						
						</tr>	
					</c:forEach>
				</tbody>
   			 </table>
						
		</div>
	</div> --%>

<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
<script type="text/javascript">
function trim(str) {
	return str.replace(/(\s)/g, "");
}
$("#reset").click(function(){
	$("#description").attr("value","");
	$("#action").find("option:selected").attr("selected",false);
	$("#action").find("option[value='']").attr("selected",true);
});
$(function() {
	
	menu.active('#statiumOa-man');
	$("#action").change(function(){
		$("#description").val("");
	})
	
	var qrCode = $("#qrCode1").val();
	if(qrCode==''){
		$("#qrCode").hide();
	}
	$("#myDlgBody_lg").dragmove();
	$("div[name = statium_img]").on("click",'.img_close',function(){
		$(this).parent().find("input").val("");
		$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
		$(this).remove();
	});
	
	$(".close").click(function(){
		$("#baiduMap").slideUp('slow');
	});
	$('#batch_query_btn').on('click',function(){
		$("#myDlgBody_lg").load("${ctx}/common/batch_query_dlg",{statiumId:'${statium.id}'});
		$("#myDlg_lg").modal();
	})
	$("#coordinate").click(function(){
		$("#baiduMap").show();
		var lnglat = $("#lnglat").val();
		var lng = 116.403867;
		var lat = 39.914113;
		var name = "天安门";
		if(lnglat){
			var lnglats = lnglat.split(",");
			lng = lnglats[0];
			lat = lnglats[1];
			var name = $("#name").val();
		}
		// 百度地图API功能
        map = new BMap.Map("allmap");
        map.centerAndZoom(new BMap.Point(lng,lat), 14);
        map.setCenter(new BMap.Point(lng,lat));
        var marker1 = new BMap.Marker(new BMap.Point(lng, lat));  //创建标注
        map.addOverlay(marker1);                 // 将标注添加到地图中
        var infoWindow1 = new BMap.InfoWindow(name);
        marker1.addEventListener("click", function (e) {
           this.openInfoWindow(infoWindow1, false);
        });
        
        $("#search").click(function(){
        	search($("#keyword").val(),map);
        });
        
        // 获取经纬度和地址
        map.addEventListener("click", function(e){
        	var lnglat = e.point.lng + "," + e.point.lat;
        	$("#lnglat").val(lnglat);
        	var gc = new BMap.Geocoder(); 
            gc.getLocation(e.point, function(rs) {
             	var addComp = rs.addressComponents; 
             	var address = '';
                address += addComp.province;
                address += addComp.city;
                address += addComp.district;
                address += addComp.street;
                address += addComp.streetNumber;
                $("#address").val(address);
                //alert("当前定位地址为：" + address);
             });
        });
	});	
	
	function search(name,map){
		 var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		}); 
		local.search(name);
	}
	
});

$(function(){
	 $(window).load(function(){
			
			if('${statium.id }'){
				$("#logoFile").parent().parent().find("label span").html("");
			}else{
				$("#logoFile").addClass("required");
			}
		});
});

$(function() {
	$("#div_areaCode select:eq(1)").each(function(){
		$(this).addClass("required");
	});
	
	$('#inputForm').validate({
		submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
			getData(function(data){
				app.disabled("#submit_btn");
				form.submit(); 
			});
		},
		rules: {
			dgName: {
				required: true,
				maxlength:30
			},
			address: {
				required: true
			},
			contact: {
				required: true
			},
			lnglat:{
				required: true,
				isCoordinate:true
			},
			serviceTel:{
				required:true,
				isPhoneCode : true
			},
			tel:{
				required:true,
				isPhoneCode : true,
				remote:"${ctx}/statiumOa/checkPhone?dgId=${statium.dgId}"
			}
		},
		messages: {
			tel:{
				remote:'馆长电话已存在!'
			}
		}
	});
	
});

$(function() {
	upload({'id':'logoFile','icon_img':'logo_img','icon':'logo'});  // 道馆LOGO
	
	multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':5});  // 
});

/**
*	option.id            上传元素id
*	option.icon_img      显示图片id
*	option.icon          保存图片的url的id
*	option.width         显示图片的宽度
*	option.height        显示图片的高度
*/
function upload(option){
	var id = option.id || "icon_upload";
	var height = option.height|| 40;
	var width = option.width || 120;
	var icon_img = option.icon_img || "icon_img";
	var icon = option.icon || "icon";
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
	    //按钮名称
		buttonText    : '选择图片',
		height        : 30,
		multi         :false,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
		width         : 100,
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$('#'+icon_img).attr('src',data.url).css({width:'100px',height:'100px;'});
				$('#'+icon).val(data.url);
	       }
	    }  
	});
}

function multipleUpload(option){
	var id = option.id || "icon_upload";
	var height = option.height|| 40;
	var width = option.width || 120;
	var icon_img = option.icon_img || "icon_img";
	var icon = option.icon || "icon";
	var limit = option.limit || 1;
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
		queueSizeLimit: limit,
		multi         :true,
	    //按钮名称	
		buttonText    : '选择图片',
		height        : 30,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
		width         : 100,
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
		overrideEvents : [ 'onDialogClose','onSelectError' ],
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$("input[name="+icon+"]").each(function(i){
					var photo = $(this).val();
					if(!photo){
						var index = i+1;
						$('#'+ icon_img + index).attr('src',data.url).attr({"height":"100","width":"130"});
						$('#'+ icon + index ).val(data.url);
						$('#'+ icon_img + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
						return false;
					}
				});
			}
	    },
	   onSelectError : uploadify_onSelectError,  
	});
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        myAlert(msgText);	
    };
</script>