$(document).ready(function() {
	debugger;
	 var custCode=$('#custCode').val();
	var custName=$('#custName').val();
	//var ctx=$('#ctx').val();
	  
//为表单注册validate函数
					    $("#form1")
							.validate(
									{
										rules : {
											custCode: {
												required : true,
												letter:true,
												rangelength : [ 2, 20 ],
												remote : ctx+"/admin/agent/checkCustCode?oldCode="
														+ encodeURIComponent(custCode)
											},
											custName: {
												required : true,
												isSpecChar : true,
												rangelength : [ 2, 20 ],
												remote : ctx+"/admin/agent/checkCustName?oldName="
													+ encodeURIComponent(custName)
											},
											custNameEn: {
												required : true,
												isSpecChar : true,
												isEN : true
											},
											mainBusiness: {
												  isSpecChar: true
											
											},
											url: {
												unNeed : true
											},
											street : {
												required : true,
												isSpecChar : true,
												maxlength:75 
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
												isSpecChar : true,
												isCN : true
											},
											linkmanEn : {
												required : true,
												isSpecChar : true,
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
												remote : "代理商编码已经存在"
											},
											custName : {
												remote : "代理商名称已存在"
											}
										}
									});
					    
					    
					    /**
					     * 客户名称-onblur事件
					     */
					     $('#custName').blur(function() {
					    	debugger;
					    	//把客户名称的值赋给联系人的单位名称
					    		if(checkNotSpec($('#custName').val())){
						    		  $('#orgName').val($('#custName').val());
						    	}
					    	
					    	});
					     
					     $('#custNameEn').blur(function() {
						    	debugger;
						    	//把客户名称的值赋给联系人的单位名称
						    		if(checkNotSpec($('#custNameEn').val())){
							    		  $('#orgNameEn').val($('#custNameEn').val());
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
					     *街道地址-onblur事件
					     */
					    $('#street').blur(function() {
					    	debugger;
					    	var  street=$('#street').val();
					    	if(checkNotSpec($('#street').val())){
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
						    
						      //----------------------------------------监听省份改变事件
	  $('select.province').change(function() {
		  var $this = $(this);
		  var url = ctx+'/common/area/province/' + $this.val() + '/city';
		  $.get(url, function(data) {
			  var cities = $.parseJSON(data);
			  if (cities.length) {
				  debugger;
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


