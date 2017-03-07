//var ctx = $('#ctx').val();
var pwd = '';
var domainName = $('#domainNameHidden').val();
var checkedArray = [];

// 隐藏提示框
$('#closeReminder').click(function() {
	$('#reminder').hide();
});

// 查看域名密码
function checkPwd(){
	$.ajax({
		url: ctx + "/self/checkPwd", 
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
	if(checkDateAndNow()){
		$('#reminder').show();
	}else{
		$('#reminder').hide();
	}
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
	
	//为表单注册validate函数
    $("#contactForm")
		.validate(
				{
					rules : {
						linkman : {
							required : true,
							isCN : true
						},
						linkmanEn : {
							required : true,
							isEN : true
						},
						orgName : {
							required : true,
							isSpecChar : true
						},
						orgNameEn : {
							required : true,
							isSpecChar : true
						},
						street : {
							required : true,
							isSpecChar : true,
							maxlength:75 
						},
						streetEn : {
							required : true,
							isSpecChar : true,
							maxlength:75 
						},
						zipCode : {
							isZipCode : true,	
							
						},
						
						firstNum2 : {
							required : true,
							digits : true,
							rangelength : [ 3, 4 ]
						},
						lastNum2 : {
							required : true,
							digits : true,
							rangelength : [ 6, 8 ]
						},
						mobile : {
							required : true,
							isMobile:true
						},
					    fax : {
							isFax : true
						},
						email : {
							required : true,
							email : true
						},
						memo: {
							  isSpecChar: true
							},
						
						
					},
					
				});


    /**
     * 组装电话号码-onblur事件
     */
    $("#lastNum2").blur(function() {
    	debugger;
    	var  firstNum2=$("#contactForm input[id=firstNum2]").val();
    	var  lastNum2=$("#contactForm input[id=lastNum2]").val();
    	if(''!=firstNum2&&''!=lastNum2){
	    $('#phone').val(firstNum2+'-'+lastNum2);
        var phone1=$('#phone').val();
    	}
    	});
	
	
});


//域名结束日期和今天进行比较
function checkDateAndNow(){
	debugger;
	var flag=false;//默认不显示
	var endDate=$('#endDate').val();
	//var endDate="2015-01-20";
	 var nowDate1=new Date();
	 var year=parseInt(endDate.substring(0,4),10);
	 var month=parseInt(endDate.substring(5,7),10);
	 var day=parseInt(endDate.substring(8,10),10);
	 var year1=parseInt(nowDate1.getFullYear(),10);
	 var month1=parseInt(nowDate1.getMonth(),10)+1;
	 var day1=parseInt(nowDate1.getDate(),10);
	 var strDate=Date.parse(month+'/'+day+'/'+year);
	 var nowDate=Date.parse(month1+'/'+day1+'/'+year1);
	 if(((strDate-nowDate)/86400000<14)||(((strDate-nowDate)/86400000)==14)){//今天距离域名结束日期大于14天不显示提示框
		 flag=true;
	 }
	 
	 return flag;
	 
}
// 修改域名密码
$('#submitPwd').click(function(){
	var $form = $('#pwdForm');
	$form.attr('action', ctx + '/self/updatePwd');
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
	$('#dnsModal').modal('show');
}

//弹出修改联系人的列表框
function toUpdateContat(){
	$('#contactModal').modal('show');
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
			$form.attr('action', ctx + '/self/updateDNS');
			$form.submit();
		}
	}
});




$('.pinyin-source').blur(function() {
  	  var $this = $(this);
  	  var $form = $(this).parents('form');
  	  $.get(ctx+'/common/pinyin', {src:$(this).val()}, function(data) {
  		  $('#' + $this.attr('for')).val(data);
  	  });
    });
    //---------------------------------------/汉字转拼音
    
      //----------------------------------------监听省份改变事件
$('select.province').change(function() {
var $this = $(this);
var url = ctx+'/common/area/province/' + $this.val() + '/city';
$.get(url, function(data) {
debugger;
var cities = $.parseJSON(data);
if (cities.length) {
var $select = $($this.parents('form')[0].city).empty();
var options = "";
$.each(cities, function(i, o) {
options += '<option value="' + o.id + '">' + o.name + '</option>';
});
$select.append(options);
}
});
});


$(document).ready(function() {
	debugger;
var	toClickFlag=$("#toClickFlag").val();//得到是否手动触发点击到域名解析的页面
if('1'==toClickFlag){
	
	$("#reslving_button").click();
}
//为表单注册validate函数
					    $("#contactForm")
							.validate(
									{
										rules : {
											linkman : {
												required : true,
												isCN : true
											},
											linkmanEn : {
												required : true,
												isEN : true
											},
											orgName : {
												required : true,
												isSpecChar : true
											},
											orgNameEn : {
												required : true,
												isSpecChar : true
											},
											street : {
												required : true,
												isSpecChar : true,
												maxlength:75 
											},
											streetEn : {
												required : true,
												isSpecChar : true,
												maxlength:75 
											},
											zipCode : {
												isZipCode : true,	
												
											},
											
											firstNum2 : {
												required : true,
												digits : true,
												rangelength : [ 3, 4 ]
											},
											lastNum2 : {
												required : true,
												digits : true,
												rangelength : [ 6, 8 ]
											},
											mobile : {
												required : true,
												isMobile:true
											},
										    fax : {
												isFax : true
											},
											email : {
												required : true,
												email : true
											},
											memo: {
												  isSpecChar: true
												},
											
											
										},
										
									});
				
					
					    /**
					     * 组装电话号码-onblur事件
					     */
					    $("#lastNum2").blur(function() {
					    	debugger;
					    	var  firstNum2=$("#contactForm input[id=firstNum2]").val();
					    	var  lastNum2=$("#contactForm input[id=lastNum2]").val();
					    	if(''!=firstNum2&&''!=lastNum2){
						    $('#phone').val(firstNum2+'-'+lastNum2);
                            var phone1=$('#phone').val();
					    	}
					    	});
					    
					    /**
					     * 组装电话号码-onblur事件
					     */
					    $("#firstNum2").blur(function() {
					    	debugger;
					    	var  firstNum2=$("#contactForm input[id=firstNum2]").val();
					    	var  lastNum2=$("#contactForm input[id=lastNum2]").val();
					    	if(''!=firstNum2&&''!=lastNum2){
						    $('#phone').val(firstNum2+'-'+lastNum2);
                            var phone1=$('#phone').val();
					    	}
					    	});
					 
					    	});

//拼音处理
$('.pinyin-source').blur(function() {
	debugger;
	
  	  var $this = $(this);
  	  var $form = $(this).parents('form');
  	  
       var valueTmp=$('#' + $this.attr('for')).val();//得到英文值域
       if(isNullStr(valueTmp)){//当英文域不能空时不需要再转换成中文对应的英文----以用户输入为准
    	   return false;
       }
  	  $.get(ctx+'/common/pinyin', {src:$(this).val()}, function(data) {
  		  $('#' + $this.attr('for')).val(data);
  	  });
    });
    //---------------------------------------/汉字转拼音
					    
					 


