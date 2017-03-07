//var ctx = $('#ctx').val();

// 生成订单
$('#btn_getValue').click(function() {
	var s = '';
	$('input:checkbox[name=chk_list]:checked').each(function() {
		s += this.value+';';
	});
	if(s == ''){
		bootbox.alert('请至少选择一个域名!');
	}else{
		$('#products').val(s);
		var $form = $('#comittOrderForm');
		$form.attr('action', ctx+ '/agent/order/generateOrders');
		$form.submit();
	}
});

// 推荐域名和更多后缀生成订单
/*function adviceAndMore(value){
	$('#products').val(value);
	var $form = $('#comittOrderForm');
	$form.attr('action', ctx +'/agent/order/generateOrders');
	$form.submit();
}
*/

/* 域名查询结果 > 显示更多 */
$(document).ready(function(e) {
    $('#ym-more-btn').click(function(e) {
        $('#ym-more-btn').hide();
        $('#ym-list-more').removeClass('hide');
    });
});

/* 域名查询 > 多选模式 */
$(document).ready(function(e) {
	$('#selected-more-domain-btn, #selected-single-domain-btn').css('cursor','pointer');
	$('#selected-single-domain-btn').hide();
    $('#selected-more-domain').hide();
    $('#selected-more-domain-btn').click(function(e) {
    	$('#checkedMode').val('more');
    	$('#prodType_select').hide();
        $('#selected-more-domain').slideDown('fast');
		$('[data-toggle="dropdown"]').hide();
	    $('#selected-more-domain-btn').hide();
	    $('#selected-single-domain-btn').show();
    });
    $('#selected-single-domain-btn').click(function(e) {
    	$('#checkedMode').val('single');
    	$('#prodType_select').show();
        $('#selected-more-domain').slideUp('fast');
		$('[data-toggle="dropdown"]').show();
	    $('#selected-single-domain-btn').hide();
	    $('#selected-more-domain-btn').show();
    });
});

// 域名查询输入框绑定回车事件
$(function(){
    $('#search_domainName').bind('keypress',function(event){
        if(event.keyCode == "13")    
        {
        	domainSearch();
        }
    });
});

// 立即查询按钮点击事件
$('#btn_search').click(function(){
	domainSearch();
});

////域名规则验证
//function onkeyCheck(field){
//	var reg = new RegExp("[`~!@#$%^&*()=|{}':;',\\[\\].<>/?~！@#￥……&（）——|{}【】‘；：”“'。，、？+]"); 
//	var v=$.trim(field.value);
//	var rs="";
//	if(v != ''){
//		for (var i = 0; i < v.length; i++) { 
//			rs = rs+v.substr(i, 1).replace(reg, ''); 
//		}
//	} 
//	field.value=rs;
//}

//域名查询(旧)
function domainSearchAb(){
	var reg = new RegExp("[`~!@#$%^&*()=|{}':;',\\[\\].<>/?~！@#￥……&（）——|{}【】‘；：”“'。，、？+]"); 
	var regs = /^[\u4e00-\u9fa5]{1,100}$/;
	var search_name = $('#search_domainName').val();
	if(search_name == ""){
		bootbox.alert("域名不能为空!");
	}else if(search_name.length > 30){
		bootbox.alert("域名长度不能超过30个字符!");
	}else if(reg.test(search_name) || search_name.indexOf(' ')>=0){
		bootbox.alert("域名格式不正确,请您查看域名注册规则!");
	}else if(regs.test(search_name)){
		bootbox.alert("暂时不支持中文域名注册!");
	}else{
		var domainName = $('#search_domainName').val();
		var custId = $('#custId').val();
		var mode = $('#checkedMode' ).val();
		var select = $('#prodType_select').val();
		var checkb = [];
		var checked = $('input:checked');
		if (checked.length) {
			checked.each(function() {
				if ($(this).attr('name') != 'chk_all') {
					checkb.push($(this).val());
				}
			});
		}
		if(mode == 'more' && checkb.length == 0){
			bootbox.alert("请选择域名后缀!");
		}else{
			$.ajax({
				url: ctx + "/agent/domain/search", 
				type:"POST",
				data:{
					search_domainName:domainName,
					search_checkedMode:mode,
					search_prodType_select:select,
					search_prodType_checkbox:checkb,
					custId:custId
				},
				dataType:"json",
				beforeSend:function () {
					var img = "<br><br><div class=\"text-center\"><img src=\"" + ctx + "/static/img/loading.gif\"></img></div><br><br>";
					var newimg = "<br><br>" + img;
					$('#result_checked').html(img);
				},
				success:function(data){
					var dataMsg = "";
					var dataMore = "";
					var dataAdvice = "";
					$.each(data.vo_showList,function(i,item){
						dataMsg = dataMsg + "<div class=\"row ym-list\"><div class=\"form-inline\">";
						if(item.registrationStat == "1"){
							dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><input type=\"checkbox\" name=\"chk_list\"" +
							" value=\"" + item.prodId + ":" + item.domainInfo + "\"/></div><div class=\"form-group\"><span>&nbsp;" + item.domainInfo +
							"</span><small class=\"text-success\">(尚未注册)</small></div></div><div class=\"col-md-5 text-right\">" +
							"<div class=\"form-group text-gray\"><small>现价<span class=\"text-danger\">" +
							item.domainPrice + "</span>元/首年</small></div></div>";
							
							// 加入原价或优惠信息的dataMsg
							/*dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><input type=\"checkbox\" name=\"chk_list\"" +
							" value=\"" + item.prodId + ":" + item.domainInfo + "\"/></div><div class=\"form-group\"><span>&nbsp;" + item.domainInfo +
							"</span><small class=\"text-success\">(尚未注册)</small></div></div><div class=\"col-md-5 text-right\">" +
							"<div class=\"form-group text-gray\"><small><del>原价320元/首年</del></small><small>现价<span class=\"text-danger\">" +
							item.domainPrice + "</span>元/首年</small></div></div>";*/
							
						}else if(item.registrationStat == "0"){
							dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><span class=\"glyphicon glyphicon-remove-sign\">"+
							"</span></div><div class=\"form-group\"><span>&nbsp;"+ item.domainInfo +"</span><small>(已被注册)</small></div></div>"+
							"<div class=\"col-md-5 text-right\"><div class=\"form-group\"><a href=\"" + ctx + "/agent/domain/domainWhoisInfo?domainInfo="+
							item.domainInfo + "\" target=\"_blank\">查看域名信息</a></div></div>";
						}else if(item.registrationStat == "2"){
							dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><span class=\"glyphicon glyphicon-remove-sign\">"+
							"</span></div><div class=\"form-group\"><span>&nbsp;"+ item.domainInfo +"</span><small>(查询超时)</small></div>"+
							"</div><div class=\"col-md-5 text-right\"></div>";
						}
						dataMsg = dataMsg + "</div></div>";
					});
					
					dataMsg = dataMsg + "<hr /><div class=\"row\"><div class=\"form-inline\"><div class=\"col-md-7\"><div class=\"form-group\">"+
					"<label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"chk_all\" onclick=\"if(this.checked==true) "+
					"{ checkAll('chk_list'); } else { clearAll('chk_list'); }\" />全选</div></div></div></div>";
					
					$('#result_checked').html(dataMsg);
					
					$.each(data.vo_moreSuffixList,function(i,item){
						dataMore = dataMore + "<li class=\"list-group-item\">" + item.domainInfo + "<span class=\"text-danger pull-right\">"+
						item.domainPrice + "元/首年&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"adviceAndMore(\'"+item.prodId +
						":" + item.domainInfo + ";" +"\') \">购买</a></span></li>";
					});
					
					$('#result_more').html(dataMore);
					
					$.each(data.vo_adviceList,function(i,item){
						dataAdvice = dataAdvice + "<li class=\"list-group-item\">" + item.domainInfo + "<span class=\"text-danger pull-right\">"+
						item.domainPrice + "元/首年&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"adviceAndMore(\'"+item.prodId +
						":" + item.domainInfo + ";" +"\') \">购买</a></span></li>";
					});
					
					$('#result_advice').html(dataAdvice);
				},
				error:function(){
					bootbox.alert("查询超时,请您刷新页面后重新尝试!");
					$('#result_checked').html("");
					$('#result_more').html("");
					$('#result_advice').html("");
				}
			});
		}
	}
}

// 选中域名查询
function domainSearch(){
	var reg = new RegExp("[`~!@#$%^&*()=|{}':;',\\[\\].<>/?~！@#￥……&（）——|{}【】‘；：”“'。，、？+]"); 
	var regs = /^[\u4e00-\u9fa5]{1,100}$/;
	var search_name = $('#search_domainName').val();
	if(search_name == ""){
		bootbox.alert("域名不能为空!");
	}else if(search_name.length > 30){
		bootbox.alert("域名长度不能超过30个字符!");
	}else if(reg.test(search_name) || search_name.indexOf(' ')>=0){
		bootbox.alert("域名格式不正确,请您查看域名注册规则!");
	}else if(regs.test(search_name)){
		bootbox.alert("暂时不支持中文域名注册!");
	}else{
		var domainName = $('#search_domainName').val();
		var custId = $('#custId').val();
		var mode = $('#checkedMode' ).val();
		var select = $('#prodType_select').val();
		var checkb = [];
		var checked = $('input:checked');
		if (checked.length) {
			checked.each(function() {
				if ($(this).attr('name') != 'chk_all') {
					checkb.push($(this).val());
				}
			});
		}
		if(mode == 'more' && checkb.length == 0){
			bootbox.alert("请选择域名后缀!");
		}else{
			$.ajax({
				url: ctx + "/agent/domain/search", 
				type:"POST",
				data:{
					search_domainName:domainName,
					search_checkedMode:mode,
					search_prodType_select:select,
					search_prodType_checkbox:checkb,
					custId:custId
				},
				dataType:"json",
				beforeSend:function () {
					var img = "<br><br><div class=\"text-center\"><img src=\"" + ctx + "/static/img/loading.gif\"></img></div><br><br>";
					var newimg = "<br><br>" + img;
					$('#result_checked').html(img);
				},
				success:function(data){
					var dataMsg = "";
					$.each(data.vo_showList,function(i,item){
						dataMsg = dataMsg + "<div class=\"row ym-list\"><div class=\"form-inline\">";
						if(item.registrationStat == "1"){
							dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><input type=\"checkbox\" name=\"chk_list\"" +
							" value=\"" + item.prodId + ":" + item.domainInfo + "\"/></div><div class=\"form-group\"><span>&nbsp;" + item.domainInfo +
							"</span><small class=\"text-success\">(尚未注册)</small></div></div><div class=\"col-md-5 text-right\">" +
							"<div class=\"form-group text-gray\"><small>现价<span class=\"text-danger\">" +
							item.domainPrice + "</span>元/首年</small></div></div>";
						}else if(item.registrationStat == "0"){
							dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><span class=\"glyphicon glyphicon-remove-sign\">"+
							"</span></div><div class=\"form-group\"><span>&nbsp;"+ item.domainInfo +"</span><small>(已被注册)</small></div></div>"+
							"<div class=\"col-md-5 text-right\"><div class=\"form-group\"><a href=\"" + ctx + "/agent/domain/domainWhoisInfo?domainInfo="+
							item.domainInfo + "\" target=\"_blank\">查看域名信息</a></div></div>";
						}else if(item.registrationStat == "2"){
							dataMsg = dataMsg + "<div class=\"col-md-7\"><div class=\"form-group\"><span class=\"glyphicon glyphicon-remove-sign\">"+
							"</span></div><div class=\"form-group\"><span>&nbsp;"+ item.domainInfo +"</span><small>(查询超时)</small></div>"+
							"</div><div class=\"col-md-5 text-right\"></div>";
						}
						dataMsg = dataMsg + "</div></div>";
					});
					$('#result_checked').html(dataMsg);
					searchAdvice();
					searchMore();
				},
				error:function(){
					$('#result_checked').html("");
				}
			});
		}
	}
}

// 更多后缀域名查询
function searchMore(){
	var domainName = $('#search_domainName').val();
	var custId = $('#custId').val();
	var mode = $('#checkedMode' ).val();
	var select = $('#prodType_select').val();
	var checkb = [];
	var checked = $('input:checked');
	if (checked.length) {
		checked.each(function() {
			if ($(this).attr('name') != 'chk_all') {
				checkb.push($(this).val());
			}
		});
	}
	$.ajax({
		url: ctx + "/agent/domain/searchMore", 
		type:"POST",
		data:{
			search_domainName:domainName,
			search_checkedMode:mode,
			search_prodType_select:select,
			search_prodType_checkbox:checkb,
			custId:custId
		},
		dataType:"json",
		beforeSend:function () {
			var img = "<br><br><div class=\"text-center\"><img src=\"" + ctx + "/static/img/loading.gif\"></img></div><br><br>";
			var newimg = "<br><br>" + img;
			$('#result_more').html(newimg);
		},
		success:function(data){
			var dataMore = "";
			$.each(data.vo_moreSuffixList,function(i,item){
				dataMore = dataMore + "<li class=\"list-group-item\">" + "<input type=\"checkbox\" name=\"chk_list\"" +
				" value=\"" + item.prodId + ":" + item.domainInfo + "\"/>&nbsp;" + item.domainInfo + "<span class=\"text-danger pull-right\">"+
				item.domainPrice + "元/首年</span></li>";
			});
			$('#result_more').html(dataMore);
		},
		error:function(){
			$('#result_more').html("");
		}
	});
}

// 推荐域名查询
function searchAdvice(){
	var domainName = $('#search_domainName').val();
	var custId = $('#custId').val();
	var mode = $('#checkedMode' ).val();
	var select = $('#prodType_select').val();
	var checkb = [];
	var checked = $('input:checked');
	if (checked.length) {
		checked.each(function() {
			if ($(this).attr('name') != 'chk_all') {
				checkb.push($(this).val());
			}
		});
	}
	$.ajax({
		url: ctx + "/agent/domain/searchAdvice", 
		type:"POST",
		data:{
			search_domainName:domainName,
			search_checkedMode:mode,
			search_prodType_select:select,
			search_prodType_checkbox:checkb,
			custId:custId
		},
		dataType:"json",
		beforeSend:function () {
			var img = "<br><br><div class=\"text-center\"><img src=\"" + ctx + "/static/img/loading.gif\"></img></div><br><br>";
			var newimg = "<br><br>" + img;
			$('#result_advice').html(newimg);
		},
		success:function(data){
			var dataAdvice = "";
			$.each(data.vo_adviceList,function(i,item){
				dataAdvice = dataAdvice + "<li class=\"list-group-item\">" + "<input type=\"checkbox\" name=\"chk_list\"" +
							" value=\"" + item.prodId + ":" + item.domainInfo + "\"/>&nbsp;" + item.domainInfo + "<span class=\"text-danger pull-right\">"+
				item.domainPrice + "元/首年&nbsp;&nbsp;</span></li>";
			});
			$('#result_advice').html(dataAdvice);
		},
		error:function(){
			$('#result_advice').html("");
		}
	});
}

//显示/隐藏域名规则
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
