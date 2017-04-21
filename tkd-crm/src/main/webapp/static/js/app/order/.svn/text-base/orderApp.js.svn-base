var orderApp = angular.module('orderApp',['ngRoute','crm.pagination'])

orderApp.factory('OrderService',['$http','$route',function($http,$route,$routeParams){
    return {
        loadOrders:function(pageVo){
            return $http.post('/admin/order',pageVo,{cache:false});
        },
    	deleteGoodOrder:function(id){
    		return $http.post('/admin/order/deleteGoodsOrder/'+id);
    	}
    }
}]);
orderApp.filter("orderStatusFilter", function() {
    var filterfun = function(input) {
        if(input=='ORDER_PLAYING'){
        	return "交易完成";
        }else if(input=='ORDER_CANCELED'){
        	return "交易关闭";
        }else if(input=='ORDER_VERIFY'){
        	return "已确认";
        }else if(input=='ORDER_PAIED'){
        	return "已支付";
        }else if(input=='ORDER_NEW'){
        	return "待支付";
        }else if(input=='ORDER_REFUNDING'){
        	return "退款中";
        }else if(input=='ORDER_REFUNDED'){
        	return "已退款";
        }
    };
    return filterfun;
});
//支付类型过滤器
orderApp.filter("payTypeFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(!input){
    		result = '';
    	}
    	if(input===0){
    		result = '现金';
    	}
    	if(input===1){
    		result = '支付宝';
    	}
    	if(input===2){
    		result = '微信';
    	}
    	if(input===3){
    		result = '余额';
    	}
    	if(input===4){
    		result = '刷卡';
    	}
    	return result;
    };
    return filterfun;
});
orderApp.filter('to_trusted', ['$sce', function ($sce) {
	return function (text) {
		return $sce.trustAsHtml(text);
	}
}]);
orderApp.controller('loadOrdersController',['$scope','OrderService','messageService','$location',function($scope,OrderService,messageService,$location) {
    messageService.publish('breadcrumb',"orderMangeIco");
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 15,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
        	var orderPromise = OrderService.loadOrders({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
            $scope.paginationConf.pagePromise = orderPromise;
        }
    };
    $scope.statusMap = {"ORDER_NEW":"待支付","ORDER_PAIED":"已支付","ORDER_VERIFY":"已确认","ORDER_PLAYING":"交易完成","ORDER_CANCELED":"交易关闭","ORDER_REFUNDING":"退款中","ORDER_REFUNDED":"已退款"};
    $scope.changeOrderType = function(orderType){
    	$("#"+orderType).addClass("active");
    	$("#"+orderType).siblings().removeClass("active");
    	$scope.searcher.orderType = orderType;
    	$scope.search();
    }
    //删除商品订单
	$scope.deleteGoodOrder = function(index){
		swal({
			title: "您确定要删除该订单吗？",
			text: "你将要删除订单，且为会员退款!",
			type: "warning",
			showCancelButton: true,
			cancelButtonText:"取消",
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定",
			closeOnConfirm: true
		}, function () {
			var delOrderPromise = OrderService.deleteGoodOrder($scope.objs[index]['id']);
	    	delOrderPromise.success(function(data){
	    		if(data["result"]=='success'){
	    			messageService.publish('notifyMessage',["删除订单成功！","success"]);
	    			$scope.objs.splice(index,1);
	    		}else{
	    			messageService.publish('notifyMessage',["删除订单失败！","error"]);
	    		}
	        }).error(function(error){
	        	messageService.publish('notifyMessage',["删除订单失败！","error"]);
	        });
		});
    };
}]);