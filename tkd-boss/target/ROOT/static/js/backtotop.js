// Back to top

$(document).ready(function(){
	$('#roll').hide();
	$(window).scroll(function() {
		if($(window).scrollTop() >= 100){
			$('#roll').fadeIn(400);
    }
    else
    {
    $('#roll').fadeOut(200);
    }
  });
  $('#roll_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});
  $('#roll_bottom').click(function(){$('html,body').animate({scrollTop:$('#bottombox').offset().top}, 800);});
});