jQuery.validator.addMethod("isQiye", function(value, element){
	if(isQiye()){
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
	debugger;
	var custType=$('#custType').val();
	if('CUST_PERSONAL'==custType){
		$('#org_div').hide();
	}else if('CUST_CORPORATE'==custType){
		$('#org_div').show();
	}
//为表单注册validate函数
					    $("#form1")
							.validate(
									{
										rules : {
											idCode: {
											    required : true,
											    isIdCard : true
											},
											idImgs: {
												imgNull : true,
											    uploadImgStyle : true
											},
											orgCode: {
												isQiye:true,
											    required : true,
											    isSpecChar: true
											},
											blInfo: {
												isQiye:true,
											    required : true,
											    isSpecChar: true
											    
											},
											orgImgs: {
												isQiye:true,
												imgNull : true,
											    uploadImgStyle : true
											},
											otherImgs: {
												tempUploadImgStyle : true
											},
									
									
											
										},
									
									});
							  
});

/**
 * 是否为企业
 */
function isQiye(){
	debugger;
	var flag=false;//默认不是企业
	var custType=$('#custType').val();
	
	if('CUST_CORPORATE'==custType){
		flag=true;
	}
	return flag;
}
/**
* 校验是否为身份证件
* @returns {Boolean}
*/
function isIdCard(id){
	debugger;
	var flag=false;//默认不是身份证
	var certType=$('#idType').val();
	if('SFZ'==certType){
		if(CheckValue(id)){
		flag=true;
		}else{
			flag=false;	
		}
	}else{
		flag=true;
	}
	return flag;
}




/**
 * 校验身份证件号码
 * lixiaochao
 * @returns {Boolean}
 */
function CheckValue(id) {
	 debugger;
	// var id=$('#idCode').val();
	var flag = true;
	var birthday;
	var yyyy;
	var mm;
	var dd;
	var idCard = trim(id);
	var id = idCard;
	var id_length = id.length;
if (id_length != 15 && id_length != 18) {
	//$('#'+id).focus();
		return false;
	}

	if (id_length == 15) {

		yyyy = "19" + id.substring(6, 8);
		mm = id.substring(8, 10);
		dd = id.substring(10, 12);

		if (mm > 12 || mm <= 0) {
			//$('#'+id).focus();
			return false;
		}

		if (dd > 31 || dd <= 0) {
			//$('#'+id).focus();
			return false;
		}

		birthday = yyyy + "-" + mm + "-" + dd;

		if ("13579".indexOf(id.substring(14, 15)) != -1) {
			sex = "1";
		} else {
			sex = "2";
		}
	} else if (id_length == 18) {
		if (id.indexOf("X") > 0 && id.indexOf("X") != 17 || id.indexOf("x") > 0
				&& id.indexOf("x") != 17) {
			//$('#'+id).focus();
			return false;
		}

		yyyy = id.substring(6, 10);
		if (yyyy > 2200 || yyyy < 1900) {
			//$('#'+id).focus();
			return false;
		}

		mm = id.substring(10, 12);
		if (mm > 12 || mm <= 0) {
			//$('#'+id).focus();
			return false;
		}

		dd = id.substring(12, 14);
		if (dd > 31 || dd <= 0) {
			//$('#'+id).focus();
			return false;
		}

		if (id.charAt(17) == "x" || id.charAt(17) == "X") {
			if ("x" != GetVerifyBit(id) && "X" != GetVerifyBit(id)) {
				//$('#'+id).focus();
				return false;
			}

		} else {
			if (id.charAt(17) != GetVerifyBit(id)) {
				//$('#'+id).focus();
				return false;
			}
		}

		birthday = id.substring(6, 10) + "-" + id.substring(10, 12) + "-"
				+ id.substring(12, 14);
		if ("13579".indexOf(id.substring(16, 17)) > -1) {
			sex = "1";
		} else {
			sex = "2";
		}
	}
	
	return true;
}
/**
 * 身份证校验调用
 * lixiaochao
 * @param id
 * @returns {String}
 */
function GetVerifyBit(id) {
	var result;
	var nNum = eval(id.charAt(0) * 7 + id.charAt(1) * 9 + id.charAt(2) * 10
			+ id.charAt(3) * 5 + id.charAt(4) * 8 + id.charAt(5) * 4
			+ id.charAt(6) * 2 + id.charAt(7) * 1 + id.charAt(8) * 6
			+ id.charAt(9) * 3 + id.charAt(10) * 7 + id.charAt(11) * 9
			+ id.charAt(12) * 10 + id.charAt(13) * 5 + id.charAt(14) * 8
			+ id.charAt(15) * 4 + id.charAt(16) * 2);
	nNum = nNum % 11;
	switch (nNum) {
	case 0:
		result = "1";
		break;
	case 1:
		result = "0";
		break;
	case 2:
		result = "X";
		break;
	case 3:
		result = "9";
		break;
	case 4:
		result = "8";
		break;
	case 5:
		result = "7";
		break;
	case 6:
		result = "6";
		break;
	case 7:
		result = "5";
		break;
	case 8:
		result = "4";
		break;
	case 9:
		result = "3";
		break;
	case 10:
		result = "2";
		break;
	}
	// document.write(result);
	return result;
}
