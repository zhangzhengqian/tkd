$(document).on('touchmove',function(e){e.preventDefault()})
var infoScroll=new iScroll('gameDetails',{
    onBeforeScrollStart:function(e){
        var target=e.target;
        while(target.nodeType!=1){  // 当对象不是元素节点时，找他的父节点，直到父节点是元素节点为止
            target=target.parentNode;
        }
        if(target.nodeName.toLowerCase()!='input' && target.nodeName.toLowerCase()!='select' && target.nodeName.toLowerCase()!='textarea'){
            e.preventDefault();
        }
    }
});
$('#returnBtn').on('tap',function(){
	window.location.href="activity.html";
})
$('#shareBtn').on('tap',function(){
	$('#shareBox').css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'10rem'
	})
})
$('#cancel').on('tap',function(){
	$(this).parent().css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'0rem'
	})
})
$('#closeBtn').on('tap',function(){
	$('#header').show();
	$(this).parents('#zanBox').css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'0'
	})	
	timer1(); 
})
$('#zan').on('tap',function(){
	alert(111)
	slideUp();
	$('#zanBox').css({
		'visibility':'visible'       
	})
	var handler = function(){   
		$('#header').hide();		
	}    
	var timer = setTimeout( handler , 500);
	/*common.access_sever('http://localhost:8080/oa/rest/v1.0/praise/modPraise',postDate,function(rs){
        	console.log(rs)
        });*/
	$.ajax({
            url:'/rest/v1.0/event/list',
            type:'get',
            async:false,
            dataType:'json',
            success:function(rs){
            	alert(rs)
            }
        });
	
})
function slideUp(target){	
	$('#footer').hide();	
	$('#zanBox').css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'100%'
	})	
}


$('#comments').on('tap',function(){
	//window.location.href='comments.html';
	$('#footer').hide();

	$('#commentsBox').css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'100%'
	})
	$('#zanBox').css({
		'visibility':'hidden'       
	})
	timer2();
})
function timer2(){
	var timer=setTimeout(function(){
		$('#header').hide();
	},500)
}
$('#releaseBtn').on('tap',function(){
	//window.location.href='gamedetail.html';
	$('#header').show();
	$('#commentsBox').css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'0'
	})
	timer1();

})
$('#returnBtn2').on('tap',function(){
	//slideUp();
	$('#header').show();
	$('#commentsBox').css({
		'-webkit-transition':'height 0.5s ease-in',
        'height':'0'
	})
	timer1();
	//window.location.href='gamedetail.html';
})
function timer1(){
	var timer=setTimeout(function(){
		$('#footer').show();
	},500)
}
$('#zanLeft').on('tap',function(){
	$(this).find('span').show();
	var handler = function(){   
		$('#zanLeft').find('span').hide();		
	}    
	var timer = setTimeout( handler , 700);
	$(this).find('dd').text(100+1);
})
$('#zanRight').on('tap',function(){
	$(this).find('span').show();
	var handler = function(){   
		$('#zanRight').find('span').hide();		
	}    
	var timer = setTimeout( handler , 700);
	$(this).find('dd').text(99+1);
})
$('#zanList').on('tap',function(){
	//$('#commentsBox').hide();
	//$('#zanBox').hide();
	
	/*common.access_sever('http://localhost:8080/oa/rest/v1.0/praise/modPraise',postDate,function(rs){
        	console.log(rs)
        });*/
	 
	window.location.href="zanlist.html";
	

})
$('#listReturnBtn').on('tap',function(){
	window.location.href="gamedetail.html";
})