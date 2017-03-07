
var ctx = $('#ctx').val();

var oldValue = "";

$(document).ready(function() {
	
	showPriceInfo();
	
});

//查询价格信息
function showPriceInfo(){
	//如果保存按钮显示，则将其隐藏
	$("#saveButton").hide();
	var prodCat = $('#prodCat').val();
	var actionType = $('#actionType').val();
	var custLevel = $('#custLevel').val();
	$('#priceInfo').html("");
	$.ajax({
		url:ctx + "/admin/price/selectPrice",
		type:"GET",
		dataType:"json", 
		data:{
			prodCat:prodCat,
			actionType:actionType,
			custLevel:custLevel
		}, 
		success: function(data){
			printData(data, "priceInfo");
			$(".dblclk").dblclick(function () {
				dblFunction(this);
			});
		},
		error:function () {
			$('#priceInfo').html("<tr><td colspan='20' >网络繁忙，请刷新重试！</td></tr>");
		}
	});
};

//将查到的价格信息数据写入到页面中
function printData(data, tbodyId){
	if(null == data || "" == data){
		$('#'+tbodyId+'').html("<tr><td colspan='20'>网络繁忙，请刷新重试！</td></tr>");
	}
	if(data.length == 0){
		$('#'+tbodyId+'').html("<tr><td colspan='20'>未查询到数据！</td></tr>");
	}
	for(var i = 0; i < data.length; i ++){
		var htmlData = "";
		htmlData = htmlData + "<tr>" + 
					"<td colspan=\"11\" class=\"bg-warning\"><b>" + 
					data[i].prodCatName + 
					"</b></td>" + 
					"</tr>";
				for(var j =0; j < data[i].list.length ; j++){
					htmlData = htmlData + "<tr>" + 
								"<td>" + data[i].list[j].prodName ;
								if(0 == data[i].list[j].price1){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+12+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+12+"' class='dblclk'>" + data[i].list[j].price1 ;
								}
								if(0 == data[i].list[j].price2){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+24+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+24+"' class='dblclk'>" + data[i].list[j].price2 ;
								}
								if(0 == data[i].list[j].price3){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+36+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+36+"' class='dblclk'>" + data[i].list[j].price3 ;
								}
								if(0 == data[i].list[j].price4){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+48+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+48+"' class='dblclk'>" + data[i].list[j].price4 ;
								}
								if(0 == data[i].list[j].price5){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+60+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+60+"' class='dblclk'>" + data[i].list[j].price5 ;
								}
								if(0 == data[i].list[j].price6){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+72+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+72+"' class='dblclk'>" + data[i].list[j].price6 ;
								}
								if(0 == data[i].list[j].price7){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+84+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+84+"' class='dblclk'>" + data[i].list[j].price7 ;
								}
								if(0 == data[i].list[j].price8){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+96+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+96+"' class='dblclk'>" + data[i].list[j].price8 ;
								}
								if(0 == data[i].list[j].price9){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+108+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+108+"' class='dblclk'>" + data[i].list[j].price9 ;
								}
								if(0 == data[i].list[j].price10){
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+120+"' class='dblclk'>" + "-" ;
								}else {
									htmlData = htmlData + "</td><td id='"+data[i].list[j].prodId+":"+120+"' class='dblclk'>" + data[i].list[j].price10 ;
								}
								htmlData = htmlData + 
								"</td>" + 
								"</tr>";
								
				}
			$('#'+tbodyId+'').append(htmlData);
	}
	
}

//双击事件触发后，在双击的位置插入一个input输入框
function dblFunction(pThis){
	if ($(pThis).children('input').length == 0) {
		oldValue = $.trim($(pThis).text());
		oldValue = oldValue === '-' ? '' : oldValue;
		var $input = $("<input type='text' value='"+oldValue+"' name='"+$(pThis).attr("id")+"' class='tbInput form-control input-sm' maxlength='5'>");
		$input.keyup(function(){filterVal(this)}).blur(onBlur);
		$(pThis).empty();
		$(pThis).append($input);
		$input.select();
	} else {
		$(pThis).children('input').select();
	}
	
	$("#saveButton").show();
};

//过滤输入的价格，仅保留数字
function filterVal(input) {
	var str = $(input).val();
	$(input).val(str.replace(/[^0-9]/g,''));
}

//input元素失去焦点时触发
function onBlur() {
	filterVal(this);
	var str = $(this).val();
	if ($.trim(str) === '') {
		var p = $(this).parent();
		p.empty();
		p.text('-');
	}
}

//保存修改的价格
function savePrice(){
	var args = "";
	$('table input').each(function(){
		args = args + $(this).attr("name") + ":" + $(this).val() + ";";
	});
	var actionType = $('#actionType').val();
	var custLevel = $('#custLevel').val();
	$.ajax({
		url:ctx + "/admin/price/changePrice",
		type:"GET",
		dataType:"json", 
		data:{
			args:args,
			actionType:actionType,
			custLevel:custLevel
		},
		success: function(data){
			showPriceInfo();
		},
		error:function () {
			alert("网络繁忙，请稍后重试！");
			showPriceInfo();
		}
	});
}






//输入框失去焦点，触发Ajax事件去修改价格
function blurFunction(pThis){
	var value = $(pThis).val();
	var id = $(pThis).parent().attr("id");
	var actionType = $('#actionType').val();
	var custLevel = $('#custLevel').val();
	if(isNaN(value)){
		$(pThis).parent().html(oldValue);
		return ;
	}
	$.ajax({
		url:ctx + "/admin/price/changePrice",
		type:"GET",
		dataType:"json", 
		data:{
			value:value,
			id:id,
			actionType:actionType,
			custLevel:custLevel
		},
		success: function(data){
			showPriceInfo();
		},
		error:function () {
			$(pThis).parent().html(oldValue);
//			alert("网络繁忙，请稍后重试！");
		}
	});
}
