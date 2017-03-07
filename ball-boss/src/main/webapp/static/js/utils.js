jQuery.extend({
    commLoading : {
        show : function(){
            if($('#pop-loading-panel').size() < 1) {
                $(window.document.body).append($('<div id="pop-loading-panel" class="pop-loading"><div class="pop-loading-bg">&nbsp;</div><img class="pop-loading-img" src="'+ctx+'/static/img/loadding.gif" /><div class="pop-loading-txt">正在拼命的加载中...</div></div></div>'));
            } else {
                $('#pop-loading-panel').show();
            }
        },
        hide : function(){
            $('#pop-loading-panel').hide();
        }
    }
});

//数据请求
var Util = {
	getData : function(url, cb, errorcb, data, type,async) {
		if(async===undefined){
			async = true;
		}
		$.commLoading.show();
		$.ajax({
			url  : url,
			type : type,
			data : data,
			timeout:100000,
			async:async,
			dataType: "json",
			success : function(redata, s) {
				{
					cb.call(this, redata);
				}
				$.commLoading.hide();
			},
			error : function() {
				$.commLoading.hide();
				 myAlert("请求失败！","error");
				if(errorcb != null)
				errorcb.call(this);
			}
		});
	}
};