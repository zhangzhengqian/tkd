<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>课程购买</title>
</head>
<body>

<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="cardUserForm" class="form-horizontal" action="${ctx }/userClassManage/saveCardUser" method="post" name="	">
    <div class="modulHead">
        <p>学员课程管理 》课程购买</p>
    </div>
    <div class="formTable">
    	<input type="hidden" name="classId" id="classId" value="" />
    	<!-- 私教卡cardId 隐藏域 -->
    	<input type="hidden" name="coachCardId"  id="coachCardId" value="" />
        <ul>
        	<li>
                <span class="title">手机号码：</span>
                <input type="text" id="telPhone" name="telPhone" maxlength=11 minLength=11 value="">
            </li>
            
            <!-- 学员信息 -->
            <div id="ssoUserForm" style="display:none">
            		<input type="hidden" name="userId" value="" id="userId"/>
					<li>
                		<span class="title">用户昵称：</span>
                		<input type="text" id="nickName" name="nickName" maxlength= value="" placeholder="请输入注册昵称">
            		</li>
            		
            		<li>
                		<span class="title">真实姓名：</span>
                		<input type="text" id="username" name="username" maxlength= value="" placeholder="请输入真实姓名">
            		</li>
            		
            		<li>
            			<span class="title">个人头像: </span>
            			<div class="col-md-6 has-feedback">
            			<!-- 上传控件 -->
						<!-- <input id="logoFile" type="file" multiple="false" /> -->
						<span id="logoFile">选择图片</span>
						<!-- 保存图片 -->
						<input id="logo" name="photo" type="hidden" value="" />
						<!-- 显示图片 -->
						<div id="title">
							<img id="logo_img" />
						</div>
						</div>

            		</li>
            		
            		<li>
                		<span class="title">性别：</span>
							<select class="form-control" name="sex" id="sex">
								<option id="option1" value="男">--男--</option>
								<option id="option2" value="女">--女--</option>
							</select>
            		</li>
            		
            		<li>
            			<span class="title">所在地区 :</span>
            			<div class="col-md-6 has-feedback form-inline">
							<tags:zone id="city" name="city" />
						</div>
            		</li>
            		
            		<li>
                		<span class="title">家庭住址:</span>
                		<input placeholder="请输入家庭住址" type="text" class="form-control"
								id="address" name="address" style="width: 480px" />
            		</li>
            		
            		<li>
                		<span class="title">身份证号:</span>
                		<input type="text" id="idCardId" name="cardId" value="" placeholder="请输入身份证号">
            		</li>
            		
            		<li>
                		<span class="title">电子邮箱:</span>
                		<input type="text" id="email" name="email" value="" placeholder="请输入电子邮箱">
            		</li>
            		
				</div>
            
            <li>
                <span class="title">私教卡：</span>
                <span id="captainName"></span> <input readonly type="text" class="form-control" id="cardName"
                                                      value="" placeholder="请选择卡片" style="width: 200px" />
                <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
            </li>
            
            <li>
                <span class="title">课程名称：</span>
                <input type="text" id="className" name="className" value="" readonly>
            </li>
            
            <li>
                <span class="title">优惠价格：</span>
                <input type="text" id="discountPrice" name="discountPrice" value="" >
            </li>
            
            <li>
                <span class="title">次数：</span>
                <input type="text" id="frequency" name="frequency" value="" >
            </li>
            <li>
                <span class="title">赠送次数：</span>
                <input type="text" id="giftFrequency" name="giftFrequency" value="" >
            </li>
            <li>
                <span class="title">有效期：</span>
                <input type="text" id="period" name="period" value="" >
            </li>
            <div id="dateDiv">
                <li>
                    <span class="title">开始日期：</span>
                    <input type="text" id="startTime" name="startTime" value="${cardVo.startTime }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})">
                </li>
                <li>
                    <span class="title">到期日期：</span>
                    <input type="text" id="endTime" name=endTime value="${cardVo.endTime }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})">
                </li>
                <label style="margin-top: 10px; margin-left:10px; color: red">*开始时间结束时间请遵循有效期</label>
            </div>


        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveCardUser()">保存</a>
        </div>

    </div>
</form>

<script type="text/javascript">
    $(function() {
    	// 样式
        $('#coacchCard-man').addClass("active");
        $('#CARD_USER').addClass("active");
		
        uploadWebUploader({'id':'logoFile','icon_img':'logo_img','icon':'logo'}); //个人头像
        /* upload({'id':'logoFile','icon_img':'icon_img','icon':'logo'});  // 个人头像
 */
    });
    
 // 选取卡片类型
    $('#sel_captain').on('click', function() {
        $("#myDlgBody_lg").load("${ctx}/userClassManage/card_query_dlg", {
        });
        $("#myDlg_lg").modal();
    })
    
    //手机号input框改变值触发查询用户事件
     $("#telPhone").change(function(){
    	var telPhone = $("#telPhone").val();
    	$.ajax({
    		cache:true,
    		url:"${ctx}/user/isRegister",
    		method:"POST",
    		dataType:"json",
    		data:{
    			"telPhone":telPhone,
    		},
    		error:function(request){
    			swal({
					title: "警告",
					text: "查询用户失败"
				});
    		},
    		success:function(data){
    			if(data.result=='success'){
    				console.log(data.data.city);
    				//返回成功 不能改变手机号
    				$("#telPhone").attr("readOnly","true");
    				
    				//用户id赋值
    				$("#userId").val(data.data.id);
    				$("#ssoUserForm").show();
    				
    				$("#username").val(data.data.username);
    				$("#nickName").val(data.data.nickName);
    				$("#sex").val(data.data.sex);
    				//地区码处理
    				$("#address").val(data.data.address);
    				$("#idCardId").val(data.data.cardId);
    				$("#email").val(data.data.email);
    				$("#logo").val(data.data.photo);
    				$('#logo_img').attr('src',data.data.photo).css({width:'200px',height:'200px;'});
    				
    			}else if(data.result='false'){
    				$("#ssoUserForm").show();
    			}
    		}
    	});
    }); 
    
    function captainAddCallBack(cardId,cardName,classTitle, discountPrice, frequency, giftFrequency, period,classId) {
    	$("#coachCardId").val(cardId);
    	$("#cardName").val(cardName);
        $("#className").val(classTitle);
        $("#discountPrice").val(discountPrice/100);
        $('#frequency').val(frequency);
        $('#giftFrequency').val(giftFrequency);
        $('#period').val(period);
        $('#classId').val(classId);
    }
    
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
					$('#'+opt.icon_img).attr('src',response.url).css({width:'200px',height:'200px;'});
					$('#'+opt.icon).val(response.url);
				}
		});
	}
	
    /**
    *	option.id            上传元素id
    *	option.icon_img      显示图片id
    *	option.icon          保存图片的url的id
    *	option.width         显示图片的宽度
    *	option.height        显示图片的高度
    */
    <%-- function upload(option){	
    	var id = option.id || "logoFile";
    	var height = option.height|| 200;
    	var width = option.width || 200;
    	var icon_img = option.icon_img || "icon_img";
    	var icon = option.icon || "logo";
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
    				$('#'+icon_img).attr('src',data.url).css({width:'200px',height:'200px;'});
    				$('#'+icon).val(data.url);
    	       }
    	    }  
    	});
    } --%>
    

    // 保存
    function saveCardUser() {
        // 判断会员姓名是否为空
        /* if ($('#userName').val() == undefined || $('#userName').val() == ''){
            swal({
                title: "警告",
                text: "学员姓名不能为空！"
            });
            return;
        }
        */
        
    	//次数校验
		 var reg = /^[0-9]\d*$/; 
	     var obj = document.getElementById("frequency").value.trim();
	     var dis = document.getElementById("giftFrequency").value.trim();
	     var maxPeople = $('input[name="maxPeople"]').val();
	    if(!reg.test(obj)){
	    	swal({
               title: "警告",
               text: "次数请输入阿拉伯数字！"
           });
           return; 
	    }
	    
	    if(!reg.test(dis)){
	    	swal({
               title: "警告",
               text: "赠送次数请输入阿拉伯数字！"
           });
           return; 
	    }
        
        // 判断手机号是否为空
        if ($('#telPhone').val() == undefined || $('#telPhone').val() == ''){
            swal({
                title: "警告",
                text: "手机号不能为空！"
            });
            return;
        } 
        
        if ($('#discountPrice').val() == undefined || $('#discountPrice').val() == ''){
            swal({
                title: "警告",
                text: "金额不能为空！"
            });
            return;
        }
        
        if ($('#frequency').val() == undefined || $('#frequency').val() == ''){
            swal({
                title: "警告",
                text: "次数不能为空！"
            });
            return;
        }
        
        if ($('#giftFrequency').val() == undefined || $('#giftFrequency').val() == ''){
            swal({
                title: "警告",
                text: "赠送次数不能为空！"
            });
            return;
        }
        
        if ($('#period').val() == undefined || $('#period').val() == ''){
            swal({
                title: "警告",
                text: "有效期不能为空！"
            });
            return;
        }
        
        if ($('#startTime').val() == undefined || $('#startTime').val() == ''){
            swal({
                title: "警告",
                text: "开始时间不能为空！"
            });
            return;
        }
        
        if ($('#endTime').val() == undefined || $('#endTime').val() == ''){
            swal({
                title: "警告",
                text: "结束时间不能为空！"
            });
            return;
        }
        
        // 提交
        $('#cardUserForm').submit();
    }

</script>

</body>
</html>