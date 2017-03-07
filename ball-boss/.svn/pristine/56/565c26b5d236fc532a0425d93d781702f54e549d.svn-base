$(document).ready(function() {
	
	// Set custom style, close if clicked, change title type and overlay color
	$(".fancybox-effects-c").fancybox({
		wrapCSS    : 'fancybox-custom',
		closeClick : true,

		openEffect : 'elastic',

		helpers : {
			title : {
				type : 'inside'
			}
		}
	});
	
	$('input:radio[name="auditStatus"]').change(function() { 
		var val=$('input:radio[name="auditStatus"]:checked').val();
		if(val == 'AUDITING'){
			$("#reasonDiv1").addClass("hidden");
			$("#reasonDiv2").addClass("hidden");
			$("#reasonDiv3").addClass("hidden");
		}else {
			$("#reasonDiv1").removeClass("hidden");
			$("#reasonDiv2").removeClass("hidden");
			$("#reasonDiv3").removeClass("hidden");
		}
	}); 
	
	
	

});
