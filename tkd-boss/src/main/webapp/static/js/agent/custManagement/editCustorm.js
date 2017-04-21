 
jQuery.validator.addMethod("isQiye", function(value, element){
	if(isQiye()){
		return true;
	}
    return false;
}, "");


$(document).ready(function() {
	debugger;
	  $("#operAgeDiv") .hide();
	  $("#firmSizeDiv") .hide();
	  $("#industryTypeDiv") .hide();
	  $("#mainBusinessDiv") .hide();
	  var custCode=$('#custCode').val();
		var custName=$('#custName').val();
		//var ctx=$('#ctx').val();
    	if('CUST_CORPORATE'==custType){//当客户类型为企业时
    		//把客户名称的值赋给联系人的单位名称
    		if(checkNotSpec($('#custName').val())){
	    		  $('#orgName').val($('#custName').val());
	    		}
    		var a= $('#orgName').val();
    		  $("#operAgeDiv") .show();
    		  $("#firmSizeDiv") .show();
    		  $("#industryTypeDiv") .show();
    		  $("#mainBusinessDiv") .show();
    		  
    	}else{
    		   //$('#orgName').val('');
    		  $("#operAgeDiv") .hide();
    		  $("#firmSizeDiv") .hide();
    		  $("#industryTypeDiv") .hide();
    		  $("#mainBusinessDiv") .hide();
    	}
	  
//为表单注册validate函数
					    $("#form1")
							.validate(
									{
										rules : {
											custCode: {
												required : true,
												letter:true,
												rangelength : [ 2, 20 ],
												remote : ctx+"/agent/customer/checkCustCode?oldCode="
														+ encodeURIComponent(custCode)
											},
											custName: {
												required : true,
												isSpecChar : true,
												rangelength : [ 2, 20 ],
												remote : ctx+"/agent/customer/checkCustName?oldName="
												+ encodeURIComponent(custName)
											},
											
											custNameEn: {
												required : true,
												isSpecChar : true,
												maxlength:64, 
												isEN : true
											},
											operAge: {
												isQiye : true,
												required : true,
												digits : true
											},
											mainBusiness: {
												  isQiye : true,
												  isSpecChar: true
											 },
										     url: {
													unNeed : true
												},
											street : {
												required : true,
												isSpecChar : true,
												maxlength:64 
											},
											firstNum1 : {
												required : true,
												digits : true,	
												rangelength : [ 3, 4 ]
											},
											lastNum1 : {
												required : true,
												digits : true,
												rangelength : [ 6, 8 ]
											},
											fax : {
												isFax : true
											},
											email : {
												required : true,
												email : true
											},
											
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
											street1 : {
												required : true,
												isSpecChar : true,
												maxlength:64 
											},
											streetEn : {
												required : true,
												isSpecChar : true,
												maxlength:64 
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
										    fax1 : {
												isFax : true
											},
											email1 : {
												required : true,
												email : true
											},
											memo: {
												  isSpecChar: true
												},
											
											
										},
										messages : {
											custCode : {
												remote : "客户编码已经存在"
											},
											custName : {
												remote : "客户名称已存在"
											}
										}
									});
					    
					    $('#custType').change(function() {
					    	debugger;
					    	var certStatus= $('#certStatus').val();
					    	var custType= $('#custType').val();
					    	if(("AUDITING"!=certStatus)&&("PASS"!=certStatus)){
					    	if('CUST_CORPORATE'==custType){//当客户类型为企业时
					    		//把客户名称的值赋给联系人的单位名称
					    		if(checkNotSpec($('#custName').val())){
						    		  $('#orgName').val($('#custName').val());
						    		}
					    		var a= $('#orgName').val();
					    		  $("#operAgeDiv") .show();
					    		  $("#firmSizeDiv") .show();
					    		  $("#industryTypeDiv") .show();
					    		  $("#mainBusinessDiv") .show();
					    		  
					    	}else{
					    		$('#orgName').val('');
					    		  $("#operAgeDiv") .hide();
					    		  $("#firmSizeDiv") .hide();
					    		  $("#industryTypeDiv") .hide();
					    		  $("#mainBusinessDiv") .hide();
					    	}
					    	}
							});
					    
					    /**
					     * 客户名称-onblur事件
					     */
					     $('#custName').blur(function() {
					    	debugger;
					    	var custType=$('#custType').val();
					    	var certStatus= $('#certStatus').val();
					    	if(("AUDITING"!=certStatus)&&("PASS"!=certStatus)){
					    	if('CUST_CORPORATE'==custType){//当客户类型为企业时
					    		//把客户名称的值赋给联系人的单位名称
					    		if(checkNotSpec($('#custName').val())){
						    		  $('#orgName').val($('#custName').val());
						    		}
					    		var a= $('#orgName').val();
					    	}else{
					    		$('#orgName').val('');
					    	}
					    	}
					    	});
					    /**
					     * 组装电话号码-onblur事件
					     */
					    $("#lastNum1").blur(function() {
					    	debugger;
					    	var  firstNum1=$("#form1 input[id=firstNum1]").val();
					    	var  lastNum1=$("#form1 input[id=lastNum1]").val();
					    	
					    	$("#form1 input[id=firstNum2]").val(firstNum1);
					    	$("#form1 input[id=lastNum2]").val(lastNum1);


					    	if(''!=firstNum1&&''!=lastNum1){
					    		var  firstNum2=$("#form1 input[id=firstNum2]").val();
						    	var  lastNum2=$("#form1 input[id=lastNum2]").val();
						    	$('#phone').val(firstNum1+'-'+lastNum1);
						    	$('#phone1').val(firstNum2+'-'+lastNum2);
                            var phone1=$('#phone').val();
                            var phone2=$('#phone1').val();
					    	}
					    	});
					    
					    /**
					     * 省份,市-onchange事件
					     */
					  /*   $('#city').change(function() {
					    	debugger;
					    	var  province1=$('#province').val();
					    	var  city1=$('#city').val();
					    	var  provincetext=$('#province').find("option:selected").text();
					    	var  citytext=$('#city').find("option:selected").text();
					    	$('#province1').val(province1);
					    	$('#city1').val(city1);
					    	$('#province1').text(provincetext);
					    	$('#city').text(citytext);

					    	}); */
					    
					    /**
					     *街道地址-onblur事件
					     */
					    $('#street').blur(function() {
					    	debugger;
					    	var  street=$('#street').val();
					    	if(checkNotSpec(street)){
					    	 $('#street1').val(street);
					    	}
					    });
					    /**
					     *传真-onblur事件
					     */
					    $('#fax').blur(function() {
					    	debugger;
					    	var  fax= $('#fax').val();
					    	 $('#fax1').val(fax);
					    });
					    /**
					     *电子邮件-onblur事件
					     */
					    $('#email').blur(function() {
					    	debugger;
					    	var  email= $('#email').val();
					    	 $('#email1').val(email);
					    });
					    
					    $('.pinyin-source').blur(function() {
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
	  //----------------------------------------/监听省份改变事件  
	  
	  					      //----------------------------------------监听省份改变事件
	  $('select.province1').change(function() {
		  debugger;
		  var $this = $(this);
		  var url = ctx+'/common/area/province/' + $this.val() + '/city';
		  $.get(url, function(data) {
			  debugger;
			  var cities = $.parseJSON(data);
			  if (cities.length) {
				  var $select = $($this.parents('form')[0].city1).empty();
				  var options = "";
				  $.each(cities, function(i, o) {
					  options += '<option value="' + o.id + '">' + o.name + '</option>';
				  });
				  $select.append(options);
			  }
		  });
	  });
	  //----------------------------------------/监听省份改变事件  
					   
					    
					  
});
/**
* 校验企业
* @returns {Boolean}
*/
function isQiye(){
	var flag=false;//默认不是企业
	var custType=$('#custType').val();
	if('CUST_CORPORATE'==custType){
		
		flag=true;
	}
	return flag;
}



