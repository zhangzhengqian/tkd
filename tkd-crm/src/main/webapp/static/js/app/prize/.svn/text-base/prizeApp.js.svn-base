var prizeApp = angular.module('prizeApp',['ngRoute','crm.pagination'])

prizeApp.factory('PrizeService',['$http','$route',function($http,$route,$routeParams){
    return {
    	list:function(pageVo){
	    	return $http.post('/admin/prize/list',pageVo,{cache:false});
	    }
    }
}]);

prizeApp.controller('prizesController',['$scope','PrizeService','messageService','$location',function($scope,PrizeService,messageService,$location) {
	messageService.publish('breadcrumb','perCenterIcon');
	$scope.paginationConf = {
			currentPage: 1,
			itemsPerPage: 15,
			numberOfPage:0,
			pagesLength: 15,
			perPageOptions: [10, 20, 30, 40, 50],
			pagePromise:{},
			onChange: function(){
				var prizePromise = PrizeService.list({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
				$scope.paginationConf.pagePromise = prizePromise;
			}
	};
}]);
