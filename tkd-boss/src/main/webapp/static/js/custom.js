var menu = {};

/*
 * 高亮显示指定ID的菜单项
 */
menu.active = function(id) {
	if ($(id).parent().parent().is(':hidden')) {
		$(id).parent().parent().siblings('a').children('span').removeClass('closeLeftmenuIcon').addClass('openLeftmenuIcon');
		$(id).parent().parent().show();
	}
	$(id).addClass('active');
}


//左侧菜单JS
$(function() {
	//$(".leftmenu-list > li > a > span").addClass('openLeftmenuIcon'); 
	$(".leftmenu-list > li > a > span").addClass('closeLeftmenuIcon'); 
	//$('.leftmenu-list .list-group').css('display', 'block');
	
	$(".leftmenu-list > li> a").each(function() {
		 toggleMenu(this);
		 checkCookie(this); 
	});
	 
	 /*
	 * 
	 * check if there is a cookie set for a sub menu if there is
	 * then show the menu
	 * 
	 */
	function checkCookie(id) {
		var cookieName = id.id;
		
		var c = readCookie(cookieName);
		
		if (c === 'show') {

			$(id).each(
					function() {

						$(this).children("span").removeClass('closeLeftmenuIcon').addClass('openLeftmenuIcon');
						$(this).find("+ ul").slideDown('fast');

					});

		}
	}

	function toggleMenu(id) {
		$(id).click(function() {
			/*
			 * toggle the +/- indicators
			 */
			togglePlusMinus(this);

			/*
			 * toggle the menu open or closed
			 */
			$(this).find("+ ul").slideToggle("fast");

		});
	}

	function togglePlusMinus(id) {

		$(id).each(function() {

			if ($(this).find("+ ul").is(':visible')) {
				$(this).children("span").removeClass('openLeftmenuIcon').addClass('closeLeftmenuIcon');
				eraseCookie(this.id);
			} else {
				$(this).children("span").removeClass('closeLeftmenuIcon').addClass('openLeftmenuIcon');
				createCookie(this.id, 'show');//createCookie(this.id, 'show', 365);cookie结束于一年后
			}

		});
	}
});



// cookie functions http://www.quirksmode.org/js/cookies.html

function createCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		var expires = "; expires=" + date.toGMTString();
	} else
		var expires = "";
	document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}

//表格行被选中后的css类名称
var hlClass='success';

/*列表 Checkbox 全选 */
function checkAll(name) { 
	var el = document.getElementsByTagName('input'); 
	var len = el.length; 
	for(var i=0; i<len; i++) { 
		if((el[i].type=="checkbox") && (el[i].name==name)) { 
			el[i].checked = true;
			$(el[i]).parents('tr').addClass(hlClass);
		} 
	}
}
function clearAll(name) { 
	var el = document.getElementsByTagName('input'); 
	var len = el.length; 
	for(var i=0; i<len; i++) { 
		if((el[i].type=="checkbox") && (el[i].name==name)) { 
			el[i].checked = false; 
			$(el[i]).parents('tr').removeClass(hlClass);
		} 
	} 
} 

/* 域名信息配置 */

$(document).ready(function(e) {
	/*
    $('.toggle-list > li > a').click(function(){
		$('.toggle-list > li > table').toggle();
		});
	*/
	
	$('#info_1').parent('li').children('table').slideDown();
	
	/*
	$('#info_1').click(function(e) {
	$('#info_1').parent('li').children('table').slideToggle();
	});
	*/
	
	/* info_2 */
	$('#info_2_radio1').click(function(e) {
	$('#info_2_radio1').parent('a').siblings('table').slideUp();
	});
	
	$('#info_2_radio2').click(function(e) {
	$('#info_2_radio2').parent('a').siblings('table').slideDown();
	});
	
	/* info_3 */
	$('#info_3_radio1').click(function(e) {
	$('#info_3_radio1').parent('a').siblings('table').slideUp();
	});
	
	$('#info_3_radio2').click(function(e) {
	$('#info_3_radio2').parent('a').siblings('table').slideDown();
	});
	
});


/* 优惠券 */
$(document).ready(function(e) {
    $('#quan_use').click(function(e) {
        $('#quan').show();
    });
    $('#quan_close').click(function(e) {
        $('#quan').hide();
    });
});

/* 域名查询结果 > 右侧【购物车中域名】”清空“”删除“域名 */
$(document).ready(function(e) {
	$('#clear-all-ym').click(function(e) {
		$('.list-group-ymcart > li').remove();
        
    });
    $('.list-group-ymcart > li > .close ').click(function(e) {
		 $(this).parent('li').remove('li');
		
	});
    //$('.list-group-ymcart > li > .sr-only').mousedown().parent('li').hide();
});

/* 添加域名  */
$(document).ready(function() {
	$('#addDomainBox').hide();	
	$('#moreDomain').hide();	
	$('#addDomain').click(function() {
	$('#addDomainBox').slideToggle();
    });
	$('#addDomainBox > .close').click(function() {
	$('#addDomainBox').slideUp();
    });
	$('.btn-default').click(function() {
	$('#addDomainBox').slideUp();
    });
	$('#moreDomain-btn').click(function() {
	$('#singleDomain').hide().siblings('#moreDomain').show();
    });
	$('#singleDomain-btn').click(function() {
	$('#moreDomain').hide().siblings('#singleDomain').show();
    });
	
});

//checkbox all select
$(document).ready(function() {
	/*内容*/
	
$('.js-all-select').on("click", function() {
	var rangeName = $(this).attr("data-range-name");
	if (rangeName) {
		$(rangeName+" input[type='checkbox']:not(:disabled)").prop("checked",this.checked);
	}else{
		$("input[type='checkbox']:not(:disabled)").prop("checked",this.checked);
	}
});
});

/**
 * 去掉空格的方法
 * 2014-10-20
 * lxc
 * @param str
 * @returns
 */
function trim(str)      
{      
    var t = str.replace(/(^\s*)|(\s*$)/g, "");  // 用正则表达式将前后空格        
    return t.replace(/(^　*)|(　*$)/g, "");        // 用空字符串替代。      
} 
/**
 * 判断是否为空字符串
 * 2014-10-22
 * lxc
 * @param str
 * @returns
 */
function  isNullStr(str){
	var s=false;
	str=trim(str);
	if(""!=str&&null!=str){
		s=true;
	}
	return s;
}

/**
 * 获取文件扩展名.
 */
function getExtName(file) {
	var idx = file.lastIndexOf('.');
	if (idx > 0) {
		return file.substring(idx + 1, file.length);
	} else {
		return '';
	}
}

/**
 * 校验上传图片的格式
 * @returns {Boolean}
 */
function checkImg(file){ 
var str=["jpg","JPG","jpeg","gif","GIF","bmp","BMP","png","PNG","PSD","psd"];
var img_Suffix = getExtName(file); 
if(str.indexOf(img_Suffix)!=-1){//表示上传的格式正确
   return true;
 }else{
	 return false;
 }

} 

/**
 * 校验上传图片的格式
 * @returns {Boolean}
 */
function checkImg1(file){ 

	     debugger;
	if (!isNullStr(file)) {
		return true;
	} else {
		var str=["jpg","JPG","jpeg","gif","GIF","bmp","BMP","png","PNG","PSD","psd"];
		var img_Suffix = getExtName(file);
		if (str.indexOf(img_Suffix) != -1) {// 表示上传的格式正确
			return true;
		} else {
			return false;
		}
	}

} 
/**
 * 获取复选框选中的值
 * 2014-10-22
 * lxc
 * @returns {Array}
 */
function getSelected(checkboxName) {
	var ids = [];
	var checked = $('input:checked');
	if (checked.length) {
	  checked.each(function() {
	    if ($(this).attr('name')==checkboxName) {
	      ids.push($(this).val());
	    }
	  });
	}
	return ids;
}
/**
 * 是否有选中的
 * @param checkboxName
 * @returns {Boolean}
 */
function isCheckBoxSelect(checkboxName){
	
	var ids= getSelected(checkboxName);
	if(ids.length==0){
		return false;//表示所有的都未选中
	}
	return true;//表示有被选中的复选框
}
/**
 * 校验IP地址
 * 2014-10-22
 * lxc
 *boolean
 */
function f_check_IP(ip){ 
   var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;//正则表达式     
   if(re.test(ip)){     
       if( RegExp.$1<256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)   
       return true;     
   }        
   return false;      
}  

function f_chckDomain(domain){
	debugger;
	domain=trim(domain);
	var re=/^(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/;     
	if((re.test(domain))&&(!f_check_IP(domain))){     
	  return true;     
	}        
    return false;
}

function f_chckUrl(value){
	debugger;
	value=trim(value);
	var re=/^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/;     
	if(re.test(value)){     
	  return true;     
	}        
    return false;
}
/**
 * 校验网站
 * @param value
 * @returns {Boolean}
 */
function f_checkCustUrl(value){
	if(f_chckDomain(value)||f_chckUrl(value)){
		 return true;     
	}
	return false;
}
/**
 * @author lixiaochao
 * @功能：小写字母转大写字母的方法
 * @日期：2013-12-03
 * @param field
 * @returns void
 */
function toLocaleUpperCase(field){
	debugger;
	var b=field.toUpperCase();
	field=b;
	return field;
}
/**
 * 判断某字符串中以指定字符串结尾
 * li.xiaochao
 * @param Str
 * @param endStr
 * @returns {Boolean}
 */
function endWithStr(Str,endStr){
	debugger;
	var d=Str.length-endStr.length;
	return (d>=0&&Str.lastIndexOf(endStr)==d);
}
/**
 * 判断是否含有中文
 */
 function isHasCN(field){ 
	 debugger;
	 field=trim(field);
	if(/.*[\u4e00-\u9fa5]+.*$/.test(field)) 
	{ 

	return true; //包含中文
	} 
	return false;//不包含中文
}
 /**
  * 只能为数字或者英文
  * @param value
  */
function checkEnAndNum(value){
	 debugger;
	 field=trim(value);
	if(/^[A-Za-z0-9]+$/.test(field)) 
	{ 

	return true; //包含正确
	} 
	return false;//错误
}

/**
 * 只能为数字或者中文
 * @param value
 */
function checkCnAndNum(value){
	 debugger;
	 field=trim(value);
	if(/^[\u0391-\uFFE5]+$/.test(field)||(/^[0-9]+$/.test(field))) 
	{ 

	return true; //包含正确
	} 
	return false;//错误
}
/**
 * 校验特定规则
 * @param value
 * @returns {Boolean}
 */
function checkEn(value){
	debugger;
	if(checkNotSpec(value)||checkEnAndNum(value)){
		return true;
	}
	return false;
	
}
/**
 *不能输入特殊字符
 * @param value
 */
function checkNotSpec(value){
	 debugger;
	 field=trim(value);
	 var txt=new RegExp("[\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\<,\\>,\\{,\\},\\',\\;,\\=,\"]");
	if(txt.test(field)) 
	{ 
		return false;//错误
	
	} 
	return true; //包含正确
}


/**
 * 校验传真号码
 * @param value
 */
function checkFax(value){
	 debugger;
	 field=trim(value);
	if(field.length<=12 && /^([0-9]{1,12})?$/.test(field)){
		return true;
	}
	return false;
}


/**
 * 校验邮编
 * @param value
 */
function checkZipCode(value){
	 debugger;
	 field=trim(value);
	if(/^[0-9]{6}$/.test(field)){
		return true;
	}
	return false;
}

//文档加载完毕后初始化
$(function() {
	
	//点击表格行，自动勾选checkbox. >>>
	var selector = 'input:checkbox[name=chk_list]';
	
	$(selector).click(function(event) {
		event.stopPropagation();
		
		if (this.checked) {
			$(this).parents('tr').addClass(hlClass);
		} else {
			$(this).parents('tr').removeClass(hlClass);
		}
	});
	
	$(selector).parents('tr').click(function() {
		var item = $(this).find(selector)[0];
		item.checked = !item.checked;
		if (item.checked) $(this).addClass(hlClass)
		else $(this).removeClass(hlClass);
		
	}).css('cursor', 'pointer').find('a').click(function(event) {//在 链接 上停止事件冒泡
		event.stopPropagation();
	});
	//点击表格行，自动勾选checkbox. <<<

	
	//更多查询条件.. >>>
	$('#btn-query-more').click(function() {
		var $span = $(this).find('span');
		if ($span.hasClass('glyphicon-chevron-up')) {
			$span.removeClass('glyphicon-chevron-up');
			$span.addClass('glyphicon-chevron-down');
			$('.query-more').hide();
		} else {
			$span.removeClass('glyphicon-chevron-down');
			$span.addClass('glyphicon-chevron-up');
			$('.query-more').show();
		}
	});
	//更多查询条件.. <<<
	
});

