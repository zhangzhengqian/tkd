angular.module('crm.drag', []).directive('crmDrag',[function(){
	return {  
        require: '?ngModel',  
        restrict: 'A',  
        link: function ($scope, element, attrs, ngModel) {
    		element.dragsort({dragSelectorExclude:"#sorthead",dragEnd: function(){
    			var seedsNew = [];
    			element.find("tr[id!=sorthead]").each(function(index,obj){
    				seedsNew[index] = $scope.seeds[$(obj).attr("seed_id")-1];
    			});
    			$scope.seeds = seedsNew;
    			$scope.$digest();
    		}, dragBetween: false});
        }  
    };
}]);