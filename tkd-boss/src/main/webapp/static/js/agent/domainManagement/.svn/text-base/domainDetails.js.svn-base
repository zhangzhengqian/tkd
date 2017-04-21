//var ctx = $('#ctx').val();
var pwd = '';
var domainName = $('#domainNameHidden').val();
var checkedArray = [];

// 隐藏提示框
$('#closeReminder').click(function() {
	$('#reminder').hide();
});

/**
 * 查看域名密码
 * @param status 域名状态
 */
function checkPwd(){//DOMAIN_NORMAL DOMAIN_RENEW DOMAIN_DELETE
	$.ajax({
		url: ctx + "/agent/domain/checkPwd", 
		type:"POST",
		data:{
			domainName:domainName
		},
		dataType:"json",
		success:function(data){
			$.each(data,function(key,value){
				if(key == 'current_status'){
					if(value == 'fail'){
						common.showMessage("服务器繁忙,请您稍后再试","warn");

					}
				}
				if(key == 'current_domianName'){
					$('#domainNameForPwd').html(value);
				}
				if(key == 'current_password'){
					$('#currentDomainPwd').html(value);
					pwd = value;
				}
			});
		},
		error:function(){
			common.showMessage("获取域名密码失败,请您刷新页面后重新尝试","warn");
		}
	}); 
	$('#checkPwd').modal('show');
}
	

// 查看/隐藏密码规则
$(document).ready(function() {
	$('.mytogglebox').hide();
	$('.mytogglebtn_close').hide();
	$('.mytogglebtn_open').click(function(e) {
		$(this).parent().next('.mytogglebox').slideDown();
		$(this).hide();
		$(this).siblings('.mytogglebtn_close').show();
	});
	$('.mytogglebtn_close').click(function(e) {
		$('.mytogglebox').slideUp();
		$('.mytogglebtn_close').hide();
		$('.mytogglebtn_open').show();
	});
});

// 修改域名密码
$('#submitPwd').click(function(){
	var $form = $('#pwdForm');
	$form.attr('action', ctx + '/agent/domain/updatePwd');
	$form.submit();
});

jQuery.validator.addMethod("noChange", function(value, element){
	if(value == pwd){
		return false;
	}
    return true;
}, "不能与原始密码相同");

jQuery.validator.addMethod("pwdCheck", function(value, element) {         
	return checkpwd(value);
}, "格式不正确,请参照密码规则");   

$("#pwdForm").validate({
	rules: {
		newPwd:{
			required:true,
			rangelength:[9, 16],
			noChange:true,
			pwdCheck:true
		},
		rePwd:{
			required:true,
			rangelength:[9, 16],
			equalTo:newPwd
		}
	},
	messages:{
		newPwd:{
			required:"密码不能为空",
			rangelength:"长度不正确,请参照密码规则",
		},
		rePwd:{
			required:"密码不能为空",
			rangelength:"长度不正确,请参照密码规则",
			equalTo:"两次输入不一致"
		}
	}
});

function checkpwd(value){
	var numRemark = 0;
	var charRemark = 0;
	var otherRemark = 0;
	for (var i = 0; i < value.length; i++) {
	    var asciiNumber = value.substr(i, 1).charCodeAt();
	    if (asciiNumber >= 48 && asciiNumber <= 57) {
	    	numRemark += 1;
	    }
	    if ((asciiNumber >= 65 && asciiNumber <= 90)||(asciiNumber >= 97 && asciiNumber <= 122)) {
	    	charRemark += 1;
	    } 
	    if ((asciiNumber >= 33 && asciiNumber <= 47)||(asciiNumber >= 58 && asciiNumber <= 64)||(asciiNumber >= 91 && asciiNumber <= 96)||(asciiNumber >= 123 && asciiNumber <= 126)) {
	    	otherRemark += 1;
	    }
	}
	if(0==numRemark || 0==charRemark || 0==otherRemark)  {
	    return false;
	}else{
	    return true;
	}
};

// 弹出DNS服务器列表框
function createDNS(){
	debugger;
$('#dnsModal').modal('show');
}


// 修改DNS服务器
$('#saveDNS').click(function(){
	var submitArray = [];
	var nsList = '';
	var ns1 = $('#nsValue1').val();
	var ns2 = $('#nsValue2').val();
	var ns3 = $('#nsValue3').val();
	var ns4 = $('#nsValue4').val();
	var ns5 = $('#nsValue5').val();
	var ns6 = $('#nsValue6').val();

	if(ns1=="" || ns2==""){
		common.showMessage("请您至少选择2个DNS","warn");
	}else{
		nsList = nsList + ns1 + ',' + ns2 + ',';
		submitArray.push(ns1);
		submitArray.push(ns2);
		if(ns3!=""){
			nsList = nsList + ns3 + ',';
			submitArray.push(ns3);
		}
		if(ns4!=""){
			nsList = nsList + ns4 + ',';
			submitArray.push(ns4);
		}
		if(ns5!=""){
			nsList = nsList + ns5 + ',';
			submitArray.push(ns5);
		}
		if(ns6!=""){
			nsList = nsList + ns6;
			submitArray.push(ns6);
		}
		var flag = false;
		var nary=submitArray.sort();
		for(var i=0;i<nary.length;i++){
			 if (nary[i]==nary[i+1]){
				 flag = true;
			 }
		}
		if(flag){
			common.showMessage("请不要重复选择DNS","warn");
		}else{
			$('#NShidden').val(nsList);
			var $form = $('#nsForm');
			$form.attr('action', ctx + '/agent/domain/updateDNS');
			$form.submit();
		}
	}
});

// 上传资料
function uploadMaterial(custId,domainId,certType){
	$('#custId').val(custId);
	$('#domainId').val(domainId);
	var $form = $('#uploadMaterialForm');
	$('#certType').val(certType);
	$form.attr('action','${ctx}/agent/domain/material/materialUploadForm');
    
	$form.submit();
	
}

// 修改资料
function updateUploadMaterial(custId,domainId,matId,certType){
	$('#custId').val(custId);
	$('#matId').val(matId);
	$('#domainId').val(domainId);
	var $form = $('#uploadMaterialForm');
	$('#certType').val(certType);
	$form.attr('action','${ctx}/agent/domain/material/updateMaterialForm');
	$form.submit();
}

// 查看资料
function viewMaterial(custId,domainId,matId,certType){
	$('#custId').val(custId);
	$('#matId').val(matId);
	$('#domainId').val(domainId);
	var $form = $('#uploadMaterialForm');
	$('#certType').val(certType);
	$form.attr('action','${ctx}/agent/domain/material/viewMaterial');
	$form.submit();
}

// 重提
function recommit(custId,domainId,matId,certType){
	$('#custId').val(custId);
	$('#matId').val(matId);
	$('#domainId').val(domainId);
	$('#recommit').val('recommit');
	var $form = $('#uploadMaterialForm');
	$('#certType').val(certType);
	$form.attr('action','${ctx}/agent/domain/material/updateMaterialForm');
	$form.submit();
}


// 查看原因
function showReason(matId,certType){
	debugger;
	$.ajax({
		url:"${ctx}/agent/domain/material/showReason/",
		type:"GET",
		data :{
			certType : certType,
			matId : matId
		},
		dataType:"json", 
		success: function(data){
			debugger;
		   $("#reason").html(data);
			$('#resonModal').modal('show');
		}
	});
}