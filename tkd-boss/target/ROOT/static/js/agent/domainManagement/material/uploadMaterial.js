
jQuery.validator.addMethod("isCnDomain", function(value, element){
	if(checkCnDomain()){
		return true;
	}
    return false;
}, "");

jQuery.validator.addMethod("isGovCn", function(value, element){
	if(checkGovCn()){
		return true;
	}
    return false;
}, "");

jQuery.validator.addMethod("imgNull", function(value, element){
	debugger;
	var values="";
	if(!isNullStr(value)){//如果为空
		values=element.defaultValue;
	}else{//如果不能为空
		values=value;
	}
	
	if(isNullStr(values)){
		return true;
	}
    return false;
}, "必输字段");

$(document).ready(function() {
	 init_Form();//初始化表单
	
//为表单注册validate函数
					    $("#form1")
							.validate(
									{
										rules : {
											tmCertImgs: {
												isCnDomain:true,
												imgNull : true,
											    uploadImgStyle : true
											},
											tmAuthImgs: {
												isCnDomain:true,
												imgNull : true,
											    uploadImgStyle : true
											},
											tmOrgImgs: {
												isCnDomain:true,
												imgNull : true,
											    uploadImgStyle : true
											},
											govImgs: {
												isGovCn:true,
												imgNull : true,
											    uploadImgStyle : true
											},
										},
									
									});
					    
					     
					    
							  
});







/**
 * 初始化表单
 */
function init_Form(){
	debugger;
	
	//是否显示中文域名有关的证书上传---开始
	var prodCat=$('#prodCat').val();
	if('PROD_CAT_DOMAIN_CN'==prodCat){//表示大类别为中文域名
		$('#shangLog_div').show();
	}else{
		$('#shangLog_div').hide();
	}
	//是否显示中文域名有关的证书上传---结束
	
	//是否显示gov.cn域名有关的证书上传---开始
	var prodType=$('#prodType').val();
	var prodCat=$('#prodCat').val();
	if(('PROD_CAT_DOMAIN'==prodCat)&&('DOMAIN_GOV_CN'==prodType)){//表示为gov.cn域名
		$('#govcn_div').show();
	}else{
		$('#govcn_div').hide();
	}
	//是否显示gov.cn域名有关的证书上传---结束
	
	
}
/**
 * 校验中文域名
 * @returns {Boolean}
 */
function checkCnDomain(){
	var prodCat=$('#prodCat').val();
	if('PROD_CAT_DOMAIN_CN'==prodCat){//表示为中文域名
		return true;
	}
	return false;
}

/**
 * 校验为gov.cn
 * @returns {Boolean}
 */
function checkGovCn(){
	var prodType=$('#prodType').val();
	if('DOMAIN_GOV_CN'==prodType){//表示为gov.cn域名
		return true;
	}
	return false;
}

