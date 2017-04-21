<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<%@include file="statiumNav.jsp"%>
<div class="panel panel-default">
	<!-- / 右侧标题 -->
	<c:choose>
	<c:when test="${action =='view'}">
		<c:set var="readOnly" value="readonly='readonly'"/>
	</c:when>
	<c:otherwise>
		<c:set var="readOnly" value=""/>
	</c:otherwise>
	</c:choose>
	<div class="panel-body">
		<!-- 右侧主体内容 -->
		<fieldset>
			<form id="inputForm" action="${ctx}/user/ssoUserForm"
				method="post" class="form-horizontal" enctype="multipart/form-data">
				<fieldset>
	      <div class="form-group form-group-sm">
	      			<label for="photosFile" class="col-md-3 control-label">用户头像 ：</label>
					<!-- 上传控件 -->
					<!-- <input id="logoFile" type="file" multiple="false" /> -->
					<span id="logoFile">选择图片</span>
					<!-- 保存图片 -->
					<input id="logo" name="photo" type="hidden" value="${user.photo }" />
					<!-- 显示图片 -->
					<img alt="" src="${user.photo }" id="logo_img" <c:if test="${not empty user.photo }">height="100" </c:if>>
	      </div>	
					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>用户昵称:</label>
						<div class="col-md-6 has-feedback">
							<input placeholder="请输入注册昵称" type="text" ${readOnly } class="form-control"
								id="nickName" name="nickName" style="width: 480px" value="${user.nickName }"/>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red">*</span>真实姓名:</label>
						<div class="col-md-6 has-feedback">
							<input placeholder="请输入真实姓名" type="text" class="form-control"
								id="name" name="name" style="width: 480px" value="${user.name }" ${readOnly }/>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="position" class="col-md-3 control-label"><span
							class="text-red"></span>性别:</label>
						<div class="col-md-6 has-feedback form-inline">
							<select class="form-control" name="sex" id="sex">
								<option id="option1" value="男">--男--</option>
								<option id="option2" value="女">--女--</option>
							</select>
						</div>
					</div>

					<%-- <div class="form-group form-group-sm">
						<label for="position" class="col-md-3 control-label"><span
							class="text-red"></span>*所在地区：</label>
							
							<c:choose>
				  				<c:when test="${empty readonly }">
				  					<tags:zone id="city" name="city" value="${user.city }"/>
				  				</c:when>
				  			<c:otherwise>
									<tags:zone value="${user.city }" disabled="disabled"/>
				  			</c:otherwise>
				  </c:choose>  
					
					</div> --%>
					<div class="form-group form-group-sm">
					<label for="position" class="col-md-3 control-label"><span class="text-red">*  </span>所在地区：</label>
					<c:choose>
				  				<c:when test="${empty readonly }">
				  					<tags:zone id="city" name="city" value="${user.city }"/>
				  				</c:when>
				  			<c:otherwise>
									<tags:zone value="${user.city }" disabled="disabled"/>
				  	</c:otherwise>
				  	</c:choose>
					</div>	
					<div class="form-group form-group-sm">
						<label for="resourceId" class="col-md-3 control-label"><span
							class="text-red"></span>家庭住址:</label>
						<div class="col-md-6 has-feedback">
							<input placeholder="请输入家庭住址" type="text" class="form-control"
								id="address" name="address" style="width: 480px" value="${user.address }" ${readOnly }/>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>身份证号:</label>
						<div class="col-md-6 has-feedback">
							<input placeholder="请输入身份证号" type="text" class="form-control"
								id="cardId" name="cardId" style="width: 480px" value="${user.cardId }" ${readOnly }/>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red">*</span>手机号:</label>
						<div class="col-md-6 has-feedback">
							<input placeholder="请输入手机号" type="text" class="form-control"
								id="phone" name="phone" style="width: 480px" value="${user.phone }"/>
						</div>
					</div>

					<div class="form-group form-group-sm">
						<label for="sort" class="col-md-3 control-label"><span
							class="text-red"></span>电子邮箱:</label>
						<div class="col-md-6 has-feedback">
							<input placeholder="请输入电子邮箱地址" type="text" class="form-control"
								id="email" name="email" style="width: 480px" value="${user.email}" ${readOnly }/>
						</div>
					</div>

					<!-- <div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>备注:</label>
			    <div class="col-md-6 has-feedback">
			    	<input placeholder="请输入备注" type="text" class="form-control" id="remark " name="remark " style="width:229px" />
			    </div>
			</div>	 -->

					<div class="form-group form-group-sm">
						<div class="col-md-offset-3 col-md-2">
							<a class="btn btn-default btn-block" href="${ctx}/statiumManage/student"><span
								class="glyphicon glyphicon-remove"></span> 返回</a>
						</div>
						<c:if test="${empty readOnly}">
						<div class="col-md-2">
							<button id="submit_button" type="button"
								class="btn btn-primary btn-block">提交</button>
						</div>
						</c:if>
					</div>
				</fieldset>
			</form>
	</div>
	<div class="panel-footer">
		<div class="row text-right">
			<div class="col-sm-12"></div>
		</div>
	</div>
</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js"
	type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		// 菜单栏显示底色（选中）
		// 样式
		$('#statium-man').addClass("active");
		$('#STUDENT').addClass("active");
		uploadWebUploader({'id':'logoFile','icon_img':'logo_img','icon':'logo'});
		
		function uploadWebUploader(opt){
			var uploader= WebUploader.create({
			    // swf文件路径
			    swf: ctx+'/static/lib/plugins/webuploader/Uploader.swf',
			    // 文件接收服务端。
			    server: ctx + '/uploadImage;JSESSIONID=<%=session.getId()%>',
			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    pick: {id:'#'+opt.id,innerHtml:'选择图片',multiple:false},
			    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    //resize: false
			    // 只允许选择图片文件。
			    accept: {
			        title: 'Images',
			        extensions: 'jpg,jpeg,png',
			        mimeTypes: 'image/jpg,image/jpeg,image/png'
			    },
			    auto:true
			});

			uploader.on('error', function(error){
				if (error=='F_EXCEED_SIZE'){
					alert ('上传文件不能大于'+(parseInt(size)/1024)+'M！请重新选择！');
				}else if (error=='Q_TYPE_DENIED'){
					alert ('只能上传JPG|JPEG|PNG格式的图片');
				}
			});
			uploader.on('uploadError',function(file,reason){
				alert ('文件上传失败，请更换图片上传');
			});
			
			uploader.on('uploadSuccess',function(file,response){	
			   	  if (response.success){
						$('#'+opt.icon_img).attr('src',response.url).css({width:'100px',height:'100px;'});
						$('#'+opt.icon).val(response.url);
					}
			});
		}
		
		$("#icon_upload").uploadify({
			//文件提交到 controller 里的文件对象的 key 
			fileObjName : 'upfile',
			//按钮名称
			buttonText : '选择图片',
			height : 40,
			swf : '${ctx}/static/uploadify/uploadify.swf',
			//提交到指定的 controller,写死即可，已封装
			uploader : '${ctx}/uploader',
			width : 120,
			//上传成功后回调此函数
			onUploadSuccess : function(file, data, response) {
				//分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
				data = JSON.parse(data);
				if (data.success == true) {
					$('#icon_img').attr('src', data.url);
					$('#icon').val(data.url);
				}
			}
		});

		//手机号校验
		$("#phone").change(function(){
			//手机号校验  
		    var reg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
		    var phone = document.getElementById('phone').value;
		    
		})
		
		// 保存
		$("#submit_button").click(function() {
			
			//手机号校验  
		    var reg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
		    var phone = document.getElementById('phone').value;
		    
		    if(!(reg.test(phone))){ 
		    	swal({
	                title: "警告",
	                text: "请填写正确格式的手机号！"
	            });
	            return;
		    }
			
		    if ($('#name').val() == undefined || $('#name').val() == ''){
	            swal({
	                title: "警告",
	                text: "姓名不能为空！"
	            });
	            return; 
	        }
	        
	        if ($('#city').val() == undefined || $('#city').val() == ''){
	            swal({
	                title: "警告",
	                text: "地区不能为空！"
	            });
	            return; 
	        }
		    
		    if ($('#phone').val() == undefined || $('#phone').val() == ''){
	            swal({
	                title: "警告",
	                text: "电话不能为空！"
	            });
	            return; 
	        }
		    
		    /* if ($('#phone').readOnly=true){
	            swal({
	                title: "警告",
	                text: "该手机号已经注册！！"
	            });
	            return; 
	        } */
		    
		    $.ajax({
	    		cache:true,
	    		url:"${ctx}/user/isRegister",
	    		method:"POST",
	    		dataType:"json",
	    		data:{
	    			"telPhone":phone,
	    		},
	    		error:function(request){
	    			swal({
						title: "警告",
						text: "查询用户失败"
					});
	    		},
	    		success:function(data){
	    			console.log(data);
	    			if(data.data==null){
	    				$("#inputForm").submit();
	    			}
	    			if(data.result=='success'){
	    				console.log(data.data.city);
	    				swal({
	    	                title: "警告",
	    	                text: "该手机号已经注册！"
	    	            });
	    	            return;
	    			}
	    			
	    		}
	    	});
			
		});

		// 低栏隐藏
		$("#adminFooter").hide();

	});

	// 验证图片样式
	function checkImgType(obj) {
		var _file = document.getElementById("mImage");
		var i = _file.value.lastIndexOf('.');
		var len = _file.value.length;
		var extEndName = _file.value.substring(i + 1, len);
		var extName = "GIF,BMP,JPG,JPEG,SWF";//首先对格式进行验证
		if (extName.indexOf(extEndName.toUpperCase()) == -1) {
			alert("*您只能输入" + extName + "格式的文件");
			document.getElementById("mImage").value = '';
			return false;
		}
	}
</script>