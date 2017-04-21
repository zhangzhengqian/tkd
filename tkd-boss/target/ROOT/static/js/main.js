var main = {};

main.adjustHeight = function() {
    var $content = $('#maincontent');
    var $footer = $('#footer');

    var h = $(window).height();
    //common.log('window height:' + h);
    
    var fh = 0;
    if ($footer.length) {
      fh = $footer.outerHeight();
    }
    
    if ($footer.length && $content.length) {
      var th = h - $content.offset().top - fh;
      $content.css('min-height', th);
    }
}

$(window).resize(function(){
	main.adjustHeight();
});

$(function() {
	main.adjustHeight();
});