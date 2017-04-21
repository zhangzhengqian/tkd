var ctx = $('#ctx').val();
var custId = $('#custId').val();
$(function() {
	menu.active('#domain-transfer');
	
	//textare 提示内容显示、隐藏效果
	$("#transferDomains").bind("input propertychange",function(){
	    var value = $("#transferDomains").val();
	    
	    if(null == value || '' == value){
	    	if($("#transferLabel").is(":hidden")){
	    		$("#transferLabel").show();
	    	}
	    }else{
	    	$("#transferLabel").hide();
	    }
	});
	
	//加载续费价格
	$('#domain_EN').load(ctx + '/price/showTransferPrice/PROD_CAT_DOMAIN?custId='+custId+'&plain');
	$('#domain_CN').load(ctx + '/price/showTransferPrice/PROD_CAT_DOMAIN_CN?custId='+custId+'&plain*');
	
	//页面滚动到输入框处
	$(".transferTop").click(function() {
		$('html, body').animate({scrollTop:0}, 'slow');
		//输入框获取焦点
		$('#transferDomains').focus();
	});
	
	$('#checkTransferDomainsSubmit').click(function() {
		  var domains = $('#transferDomains').val();
		  if($.trim(domains).length <= 0){
			  bootbox.alert('请输入域名和对应的转移密码！');
			  return false;
		  }
		  
		  //执行Reset按钮，将输入框清空
		  $('#transferReset').click();
		  //页面滚动到结果显示处
		  $('html, body').animate({scrollTop:500}, 'slow');
		  $.ajax({
				url:ctx + "/agent/domain/checkTransferDomains",
				type:"POST",
				dataType:"json", 
				data:{
					domains:domains,
					custId:custId
				}, 
				beforeSend: function(){
					$('#checkIngPanel').show();
				},
				success: function(data){
					$('#checkIngPanel').hide();
					var unableTransferMap = data.unableTransferMap;
					var transferList = data.transferList;
					var temp = "";
					for(var domain in unableTransferMap){
						temp = "<li class=\"transferNot\">"+domain+"：<span style=\"color: gray;\">"+unableTransferMap[domain]+"</span></li>";
						$('#transferNotDomains').append(temp);
						showTransferNotCounts();
					}
					
					var temp2 = "";
					if(transferList.length > 0){
						for(var i = 0; i < transferList.length; i ++){
							var elmentCount = $("input[id='"+transferList[i].domain+"'][name='transferContent']").size();
							if(elmentCount > 0){
								continue ;
							}
							temp2 = "<li><a href=\"javascript:void(0);\" class=\"transferRemove\" title=\"删除\"> " +
									"<span class=\"glyphicon glyphicon-remove\"></span></a> " +
									transferList[i].domain +
									"  <span style=\"color: gray;\">(" +
									transferList[i].amount +
									"元/首年)</span>" +
									"<input type=\"hidden\" name=\"transferContent\" id=\""+transferList[i].domain+"\" " +
									"value="+transferList[i].domain+","+transferList[i].transferPwd+","+transferList[i].prodId+","+transferList[i].amount+" >" +
									"</li>";
							$('#transferOKDomains').append(temp2);
							showTransferOKCounts();
							
							//可转入的域名移除
							$(".transferRemove").click(function() {
								$(this).parent().remove();
								showTransferOKCounts();
							});
							
						}
					}
				},
				error:function () {
					$('#checkIngPanel').hide();
					bootbox.alert('查询失败！');
				}
			}); 
		  
	});
	
});

//统计可转入域名个数
function showTransferOKCounts(){
	var count = $('.transferRemove').length;
	if(count > 0){
		$('#transferOKPanel').show();
	}else{
		$('#transferOKPanel').hide();
	}
	$('#transferOK').html(count);
}

//统计不可转入域名个数
function showTransferNotCounts(){
	var count = $('.transferNot').length;
	if(count > 0){
		$('#transferNotPanel').show();
	}else{
		$('#transferNotPanel').hide();
	}
	$('#transferNot').html(count);
}

function showtransferLabel(){
	$("#transferLabel").show();
}