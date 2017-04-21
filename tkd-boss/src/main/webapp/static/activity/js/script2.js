$(document).on('touchmove',function(e){e.preventDefault();})
$(function(){
	 $.ajax({
         url:'/rest/v1.0/event/list',
         type:'get',
         async:false,
         dataType:'json',
         success:function(rs){
             //renderPage(rs.result.ball_list);
        	 renderPage(rs);
         },
         error:function(){
             $('#match').html("网络繁忙，请刷新重试！");
         }
     });
	 
	var cityScroll=new iScroll('iscroll',{
		onBeforeScrollStart:function(e){
			var target=e.target;
			while(target.nodeType!=1){
				target=target.parentNode;
			}
			if(target.nodeName.toLowerCase()!='input' && target.nodeName.toLowerCase()!='select' && target.nodeName.toLowerCase()!='textarea'){
				e.preventDefault();
			}
		}
	})
	$('#search').on('tap',function(){
		$(this).css({'background':'none'})
	})

	$('#h_section li>a').on('tap',function(){
		$(this).addClass('high').siblings().removeClass('high');
	})
	$('#match>div.matchBox').on('tap',function(){
		location.href="gamedetail.html";
	})
	$('#return').on('tap',function(){
		//location.href="";
		window.qiuyouzone.clickOnAndroid();
	})
	$('#h_top>p').on('tap',function(){
		$('#mark').show().css({
            '-webkit-transition':'transform 0.3s linear',
            '-webkit-transform':'translate3d(0,0,0)'
        });
	})
	$('#balls_header>p').on('tap',function(){
		$('#mark').css({
            '-webkit-transition':'transform 0.3s linear',
            '-webkit-transform':'translate3d(0,100%,0)'
        });
	})
	$('table tr>td').on('tap',function(){
		$(this).toggleClass('balls_high');
	})

	
	$('#sure').on('tap',function(){
		$('#mark').css({
            '-webkit-transition':'transform 0.3s linear',
            '-webkit-transform':'translate3d(0,100%,0)'
        }); 
        
       var data={};
		$('table tr td').each(function(i,val) {
        	$(this).attr('id',i);
        	data[i]=$(this).attr('id');
        	//console.log(id)
		});
		
		
		//console.log(data)
        $.ajax({
            url:'/rest/v1.0/event/list',
            type:'get',
            data:data,
            async:false,
            dataType:'json',
            success:function(rs){
                //renderPage(rs.result.ball_list);
                console.log(rs)
            },
            error:function(){
                $('#match').html("网络繁忙，请刷新重试！");
            }
        });
	});
	

	function renderPage(rs){
		var html= new Array();
		var data = rs.result;
		 
		$.each(data,function(i,obj){
			html.push('<div class="matchBox">');
			html.push('<div class="times">');
			html.push('<p>'+obj.year+'</p>');
			html.push('<div class="matchBox">');
			html.push('<div class="matchBox">');
			html.push('<div class="matchBox">');
			html+='<div class="matchBox">'
						+'<div class="times">'
						+'<p>'+obj.year+'</p>'
						+'<p>'+obj.week+'</p>'
						+'<p>'+obj.time+'<em>'+obj.sports+'</em></p></div>'
						+'<ul class="competition">'
						+'<li><ol>'
							+'<li><img src="images/'+obj.image0+'" alt=""></li>'
							+'<li>'+obj.competitor0+'</li>'
						+'</ol></li>'
						+'<li><a href="#">VS</a></li>'
						+'<li><ol>'
							+'<li><img src="images/'+obj.image1+'" alt=""></li>'
							+'<li>'+obj.competitor1+'</li>'
						+'</ol></li></ul>'
						+'<div class="area">'
							+'<p>'+obj.address+'</p>'
							+'<p>'+obj.distance+'m</p>'
						+'</div></div>'
			console.log(html)
		});
		$(html.join("")).appendTo($('#match'));

	}
	

})