<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>员工管理</title>


	<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.ztree.helper.js"></script>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

	<link rel="stylesheet" href="${ctx}/static/js/jquery/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

	<style type="text/css">
		<!--
		.ztree * {
			font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei UI",
			simsun, sans-serif;
			font-size: 14px;
		}

		.ztree li {
			margin: 3px 0;
		}

		.ztree li a.curSelectedNode {
			height: 18px;
		}

		/*冻结根结节*/
		.ztree li span.button.switch.level0 {
			visibility: hidden;
			width: 1px;
		}

		.ztree li ul.level0 {
			padding: 0;
			background: none;
		}

		/*根节点图标样式*/
		.ztree li span.button.root_ico_open,.ztree li span.button.root_ico_close
		{
			width: 0px;
			height: 0px;
		}


		/*编辑按钮图标样式*/
		.ztree li a span.button.edit
		, .ztree li a span.button.remove
		, .ztree li a span.button.add {
			margin-left: 10px;
			margin-right: -5px;
		}
		.ztree li span.button.add {
			background-position: -144px 0;
			vertical-align: top;
			*vertical-align: middle
		}

		.tree-container {
			border: #efefef 1px solid;
			overflow: auto;
		}
		-->
	</style>
</head>

<body>

<div class="panel panel-default">

	<div class="panel-heading"><!-- 右侧标题 -->
		<ul class="breadcrumb">
			<li><span class="glyphicon glyphicon-home"></span> 系统管理</li>
			<li>员工管理</li>
			<li class="active">
				<c:if test="${'create' eq action }"> 新建员工</c:if>
				<c:if test="${'update' eq action }"> 修改员工</c:if>
			</li>
		</ul>
	</div><!-- / 右侧标题 -->

	<div class="panel-body"><!-- 右侧主体内容 -->


		<fieldset> <legend><small>员工信息</small></legend>



			<div class="row">
				<!--<div class="col-sm-3">
					<div class="panel panel-default">
						<div class="panel-body" style="min-height: 350px;">
							<ul id="orgTreeUl" class="ztree"></ul>
						</div>
					</div>
				</div>--><!-- end col-sm-3 -->
				<div class="col-sm-9">
					<form id="inputForm" action="${ctx}/account/save" method="post" class="form-horizontal">
						<input type="hidden" name="userId" value=""/>
						<input type="hidden" name="orgCode" value="county"/>
                        <!-- modify by sl 2016-11-10 针对组织数删除 -->
						<!-- <input type="hidden" name="orgCode" id="orgCode" value="${user.orgCode}"/> -->
                        
                        <div class="form-group form-group-sm form-inline">
                        <label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>账号类型:</label>
                        	<div class="col-md-6 has-feedback">
                        		<!-- onchange="typeChange()" -->
								<select class="form-control" id="type" name="roleIds">
									<c:if test="${roleId==1001 }">
										<option value="1002">——省级账号——</option>
									</c:if>
									<c:if test="${roleId==1002 }">
										<option value="1003">——市级账号——</option>
									</c:if>
									<c:if test="${roleId==1003 }">
										<option value="1004">——县级账号——</option>
									</c:if>
								</select>
							</div>
				        </div>
				        
                        
                        <div class="form-group form-group-sm form-inline">
                        <label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>所属省份:</label>
                        	<div class="col-md-6 has-feedback">
								<tags:zone name="areacode" id="areacode" value="${user.areacode }"/>
								<!-- <select  class="form-control" id="province" name="areaId">
									<option value=''>——省——</option>
								</select>
								<select  class="form-control" id="city" name="areaId">
									<option value=''>——市——</option>
								</select>
								<select  class="form-control" id="area" name="areaId">
									<option value=''>——区——</option>
								</select> -->
						    </div>
				        </div>
				        
						<div class="form-group form-group-sm">
							<label for="loginName" class="col-md-3 control-label"><span class="text-red">* </span>登录账号:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" id="loginName" name="loginName"/>
							</div>
						</div>

						<div class="form-group form-group-sm">
							<label for="nickname" class="col-md-3 control-label"><span class="text-red">* </span>真实姓名:</label>
							<div class="col-md-6 has-feedback">
								<input type="text" class="form-control" id="nickname" name="nickname" />
							</div>
						</div>

							<div class="form-group form-group-sm">
								<label for="password" class="col-md-3 control-label"><span class="text-red">* </span>密码:</label>
								<div class="col-md-6 has-feedback">
									<input type="password" class="form-control" id="password" name="password" />
								</div>
							</div>

							<div class="form-group form-group-sm">
								<label for="againPassword" class="col-md-3 control-label"><span class="text-red">* </span>重复密码:</label>
								<div class="col-md-6 has-feedback">
									<input type="password" class="form-control" id="againPassword" name="againPassword" />
								</div>
							</div>
						<%-- /新建时显示 --%>
						<%-- <div class="form-group form-group-sm">
							<label for="type" class="col-md-3 control-label">*员工角色</label>
							<div class="col-md-6 has-feedback">
								<c:forEach items="${roles}" var="role">
									<p>
										<c:set var="role_checked" value="" />
										<c:forEach items="${user_roles }" var="user_role">
											<c:if test="${user_role.roleCode eq role.roleCode }">
												<c:set var="role_checked" value="checked='checked'" />
											</c:if>
										</c:forEach>
										<input type="checkbox" name="roleIds" id="roleIds" ${role_checked } value="${role.roleId }" />
										&nbsp;&nbsp;${role.roleName }
									</p>
								</c:forEach>
							</div>
						</div> --%>
					</form>
				</div><!-- end col-sm-9 -->
			</div><!-- end row -->

		</fieldset>

		<div class="form-group">
			<hr>
			<div class="col-md-offset-3 col-md-2">
				<a class="btn btn-default btn-block" href="${ctx}/account/list"><span class="glyphicon glyphicon-remove"></span> 返回</a>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-primary btn-block" id="submit_btn"><span class="glyphicon glyphicon-ok"></span> 保存</button>
			</div>
		</div>


	</div>

</div>

<script type="text/javascript">	
 	if ('${roleId}'=='1001'){
 	 	$("#zone_province").attr("disabled",false); 
 	 	$("#zone_city,#zone_area").attr("disabled",true); 
 	}else if ('${roleId}'=='1002'){
 	 	$("#zone_city").attr("disabled",false); 
 	 	$("#zone_province,#zone_area").attr("disabled",true); 
 	}else if ('${roleId}'=='1003'){
 	 	$("#zone_area").attr("disabled",false); 
 	 	$("#zone_city,#zone_province").attr("disabled",true); 
 	}
	var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
			onClick: zTreeOnClick
		}
	};

	var zNodes = {};
	if('${orgTree}'){
		zNodes = JSON.parse('${orgTree}');
	}

	function zTreeOnClick(event, treeId, treeNode) {
		//alert(treeId+", "+treeNode.id + ", " + treeNode.name);
    }
    $(function() {
		menu.active('#account-man');
		$.fn.zTree.init($("#orgTreeUl"), setting, zNodes);

		$("#submit_btn").click(function(){
            $("#inputForm").submit();
           
		});

		$('#inputForm').validate({
			submitHandler: function(form) {
				//表单验证成功时，锁住提交按钮
				app.disabled("#submit_btn");
				//提交表单
				form.submit();
			},
			rules: {
				roleIds : {
					required: true
				},
				loginName: {
					required: true
					, letter: true
					, rangelength : [4, 16]
					,remote: "${ctx}/admin/user/checkLoginName?oldName=" + encodeURIComponent('${user.loginName}')
				},
				nickname: {
					required: true
					, rangelength : [2, 16]
				},
				password: {
					required: true
					, rangelength : [6, 20]
				},
				againPassword: {
					required: true
					, equalTo : '#password'
				},
				areacode:{required:true}
			},
			messages: {
				loginName: {
					remote: '登录名已经存在，请重新输入！'
				}
			}
		});
		$('#adminFooter').hide();

	});
    
	/*function typeChange(){
		getArea();
		var type=$("#type option:selected").val();
		if(type=='1002'){
 	       $("#zone_province").attr("disabled",false); 
 	       $("#zone_city").attr("disabled",true); 
 	       $("#zone_area").attr("disabled",true); 
 	   }else if(type=='1003'){
 	       $("#zone_province").attr("disabled",false); 
 	       $("#zone_city").attr("disabled",false); 
 	       $("#zone_area").attr("disabled",true); 
 	   }else if(type=='1004'){
 	       $("#zone_province").attr("disabled",false);
 	       $("#zone_city").attr("disabled",false);
 	       $("#zone_area").attr("disabled",false); 
 	   }
	}
    function getArea(){
    	var html = "<option value='0'>——省——</option>";
    	$.ajax({
    		url:"${ctx}/account/area",
    		data:{parentId:0},
    		type:"GET",
    		dataType:"JSON",
    		success:function(response){
    			$.each(response.result,function(index,value){
    				html += '<option  value="'+value.id+'">'+value.name+'</option>';
    			});
    			$("#province").html(html);
    		},
    		error:function(response){
    			alert("网络错误！");
    		}
    	});
    }*/
    
    /**
     * 获取二级分类
     */
    /* $("#province").on('change',function(){
    	var parentId = $("#province").val();
    	var html = "<option>——市——</option>";
    	$.ajax({
    		url:"${ctx}/account/area",
    		data:{parentId:parentId},
    		type:"GET",
    		dataType:"JSON",
    		success:function(e){
    			$.each(e.result,function(i,value){
    				html += '<option  value="'+value.id+'" >'+value.name+'</option>';
    			});
    			$("#city").html(html);
    		},
    		error:function(){
    			alert("网络错误！");
    		}
    	})
    }) */
    /**
     * 获取三级分类
     */
    /* $("#city").on('change',function(){
    	var parentId = $("#city").val();
    	var html = "<option>——区——</option>";
    	$.ajax({
    		url:"${ctx}/account/area",
    		data:{parentId:parentId},
    		type:"GET",
    		dataType:"JSON",
    		success:function(e){
    			$.each(e.result,function(i,value){
    				html += '<option  value="'+value.id+'">'+value.name+'</option>';*/
    				/*else{
    					html += '<option  value="'+value.parent_id+'">'+value.name+'</option>';
    				}*/
    				/*});
    			$("#area").html(html);
    		},
    		error:function(e){
    			alert("网络错误！");
    		}
    	});
    }); */
    
   /*  $("#distpicker").distpicker(); */
    
    
    /* function provinceChange(){
        var provinceName=$("#provinceCode option:selected").val();
        var provinceAbbreviation=provinceabbreviation[provinceName];
        var provinceCode=$("#provinceCode option:selected").attr("data-code");
        if(provinceCode){
            $("#pabb").val(provinceAbbreviation+provinceCode);
        }else{
            $("#pabb").val("");
        }
    } */
    
    
    /* function typeChange(){
    	   $('#distpicker').distpicker('reset', true);
    	    <#-- $("#provinceCode").find("option").remove(); -->
    	   var type=$("#type option:selected").val();
    	    if(type=='P'){
    	       $("#provinceCode").attr("disabled",false); 
    	       $("#cityCode").attr("disabled",true); 
    	       $("#areaCode").attr("disabled",true); 
    	   }else if(type=='C'){
    	       $("#provinceCode").attr("disabled",false); 
    	       $("#cityCode").attr("disabled",false); 
    	       $("#areaCode").attr("disabled",true); 
    	   }else if(type=='A'){
    	       $("#provinceCode").attr("disabled",false);
    	       $("#cityCode").attr("disabled",false);
    	       $("#areaCode").attr("disabled",false); 
    	   }
    	   $("#pabb").val("");
    	} */
</script>
</body>
</html>
