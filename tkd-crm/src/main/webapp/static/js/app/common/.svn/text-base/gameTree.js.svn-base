angular.module('crm.gametree', []).directive('crmGametree',['messageService',function(messageService){
    return{
        restrict: 'EA',
        template:'<div class="gameTree"></div>',
        replace:true,
        scope:{
            conf:'='
        },
        link:function(scope, element, attrs){
        	var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.1'});
			$(element).gameTree(temp,scope.conf);
			var treeNodeLength = $(element).find("li:eq(0)").attr("turn");
			var turn1 = Math.pow(2,treeNodeLength-1);
			$(element).css("width",turn1*200+"px");
			$(element).find("a").removeAttr("onclick");
        }
    }
}]);