var billApp = angular.module('billApp',['ngRoute','crm.pagination'])

billApp.factory('BillService',['$http','$route',function($http,$route,$routeParams){
    return {
        loadBills:function(pageVo){
            return $http.post('/admin/bill',pageVo,{cache:false});
        },
	    getBill:function(){
	    	var billId = $route.current.params.id;
	        return $http.post('/admin/bill/'+billId,{cache:false});
	    },
	    loadOrders:function(pageVo){
            return $http.post('/admin/order',pageVo,{cache:false});
        }
    }
}]);

billApp.filter("dateToMill", function() {
    var filterfun = function(input) {
    	var newDate = new Date();
    	newDate.setTime(parseInt(input) * 100000);
    	return newDate.format("yyyy-MM-dd");
    };
    return filterfun;
});

billApp.filter("formatAmount", function() {
    var filterfun = function(input) {
    	return input/100;
    };
    return filterfun;
});

billApp.filter('to_trusted', ['$sce', function ($sce) {
	return function (text) {
		return $sce.trustAsHtml(text);
	}
}]);
billApp.controller('loadBillsController',['$scope','BillService','messageService','$location',function($scope,BillService,messageService,$location) {
    messageService.publish('breadcrumb',"billSearchIco");
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
            var billPromise = BillService.loadBills({page:$scope.paginationConf.currentPage-1,size:$scope.paginationConf.itemsPerPage});
            $scope.paginationConf.pagePromise = billPromise;
        }
    };
    $scope.safeApply = function(fn) {
        var phase = this.$root.$$phase;
        if (phase == '$apply' || phase == '$digest') {
            if (fn && (typeof(fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };
    
    $scope.reset = function(){
    	$scope.searcher = {};
    }
    
}]);
billApp.controller('loadBillController',['$scope','BillService','messageService','$route','$routeParams','$location',function($scope,BillService,messageService,$location,$routeParams) {
	messageService.publish('breadcrumb',"billSearchIco");
	var billPromise = BillService.getBill();
    $scope.billId = $routeParams["id"];
    billPromise.success(function(data){
    	var billItems = data["data"];
    	var view = billItems.pop();
    	$scope.totalFee = view["totalFee"]+view["subsidyAmount"];
        $scope.billItems = billItems;
    }).error(function(error){
        console.log(error);
    });
    
}]);

billApp.controller('loadBillOrderController',['$scope','BillService','messageService','$route','$routeParams','$location',function($scope,BillService,messageService,$location,$routeParams) {
	messageService.publish('breadcrumb',"billSearchIco");
	var ob = $routeParams["id"];
    var startDate = ob.split("_")[0];
    $scope.billId = ob.split("_")[1];
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
        	var searcher = {};
        	searcher["startDate"] = startDate+" 00:00:00";
        	searcher["endDate"] = startDate+" 23:59:59";
        	searcher["orderStatus"] = "ORDER_PLAYING";
            var billPromise = BillService.loadOrders({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify(searcher)});
            $scope.paginationConf.pagePromise = billPromise;
        }
    };
    $scope.safeApply = function(fn) {
        var phase = this.$root.$$phase;
        if (phase == '$apply' || phase == '$digest') {
            if (fn && (typeof(fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };
    
}]);