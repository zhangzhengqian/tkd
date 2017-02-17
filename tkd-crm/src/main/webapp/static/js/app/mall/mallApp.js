var mallApp = angular.module('mallApp',['ngRoute','crm.pagination'])

mallApp.factory('MallService',['$http','$route',function($http,$route,$routeParams){
    return {
    	goods:function(){
            return $http.post('/admin/mall/goods',{cache:false});
        },
	    subOrder:function(order){
	        return $http.post('/admin/mall/createOrder',order,{cache:false});
	    },
	    getOrder:function(){
	    	var orderId = $route.current.params.id;
	        return $http.post('/admin/mall/getOrder/'+orderId,{cache:false});
	    },
	    updateInfo:function(type,content){
	    	var orderId = $route.current.params.id;
	        return $http.post('/admin/mall/updateInfo/'+orderId+'/'+type+'/'+encodeURI(encodeURI(content)),{cache:false});
	    },
	    prepay:function(subject,body,orderId){
	        return $http.post('/admin/alipay/prepay/'+orderId+'/'+encodeURI(encodeURI(subject))+'/'+encodeURI(encodeURI(body)),{cache:false});
	    },
	    getOrderStatus:function(orderId){
	        return $http.post('/admin/mall/getOrderStatus/'+orderId,{cache:false});
	    },
	    orders:function(pageVo){
	    	return $http.post('/admin/mall/orders',pageVo,{cache:false});
	    },
	    orderInfo:function(){
	    	var orderId = $route.current.params.id;
	        return $http.post('/admin/mall/orderInfo/'+orderId,{cache:false});
	    },
	    cancelOrder:function(orderId){
	        return $http.post('/admin/mall/cancelOrder/'+orderId,{cache:false});
	    },
	    verifyOrder:function(orderId){
	    	return $http.post('/admin/mall/verifyOrder/'+orderId,{cache:false});
	    },
	    delayOrder:function(orderId){
	    	return $http.post('/admin/mall/delayOrder/'+orderId,{cache:false});
	    },
	    storageSize:function(){
	        return $http.post('/admin/mall/storageSize',{cache:false});
	    },
	    addStorage:function(goodId,number){
	    	return $http.post('/admin/mall/addStorage/'+goodId+'/'+number,{cache:false});
	    },
	    cutStorage:function(goodId,number){
	    	return $http.post('/admin/mall/cutStorage/'+goodId+'/'+number,{cache:false});
	    },
	    updateSell:function(goodId,number){
	    	return $http.post('/admin/mall/updateSell/'+goodId+'/'+number,{cache:false});
	    },
	    storageLogs:function(pageVo){
	    	return $http.post('/admin/mall/storageLogs',pageVo,{cache:false});
	    }
    }
}]);

mallApp.filter("mallOrderFilter", function() {
    var filterfun = function(input) {
        if(input==0){
        	return "待付款";
        }else if(input==1){
        	return "待确认";
        }else if(input==2){
        	return "已发货";
        }else if(input==3){
        	return "已收货";
        }else if(input==4){
        	return "已取消";
        }else if(input==5){
        	return "已退款";
        }
    };
    return filterfun;
});

mallApp.controller('goodsController',['$scope','MallService','messageService','$location',function($scope,MallService,messageService,$location) {
    messageService.publish('breadcrumb','MallIco');
    var goodsPromise = MallService.goods();
    goodsPromise.success(function(response){
        $scope.goods = response["data"];
        $scope.linkMan = response["linkMan"];
        $scope.linkPhone = response["linkPhone"];
        $scope.linkAddress = response["linkAddress"];
    }).error(function(error){
        console.log(error);
    });
    $scope.carts = {};
    $scope.addCart = function(index,goodId,goodName,price,bulk,amount,unit){
    	if(!$scope.carts[goodId]){
    		var cart = {goodId:goodId,goodName:goodName,price:price,amounts:1,bulk:bulk,amount:amount,unit:unit};
    		$scope.carts[goodId] = cart;
    		$scope.totalAmount+=price;
    	}
    }
    
    $scope.updateCarts = function(id,cart){
    	if(cart["amounts"]==null||cart["amounts"]==0){
    		delete $scope.carts[id];
    	}
    	$scope.totalAmount = 0;
		$.each($scope.carts,function(id,cart){
			$scope.totalAmount+=cart["amounts"]*cart["price"];
		});
    }
    
    $scope.updateMan = function(){
    	swal({
    		title:"联系人",
    	    type: "input",
    	    cancelButtonText:"取消",
            confirmButtonText: "确定",
    	    inputValue:$scope.linkMan,
    	    showCancelButton: true,
    	    closeOnConfirm: false
    	},
    	function(inputValue) {
    	    if (inputValue === false) return false;
    	    if (inputValue === "") {
    	        swal.showInputError("请输入联系人");
    	        return false
    	    }
    	    $scope.linkMan = inputValue;
    	    $scope.$digest();
    	    swal({
                title: "操作成功",
            });
    	});
    }
    
    $scope.updatePhone = function(){
    	swal({
    		title:"联系电话",
    	    type: "input",
    	    cancelButtonText:"取消",
            confirmButtonText: "确定",
    	    inputValue:$scope.linkPhone,
    	    showCancelButton: true,
    	    closeOnConfirm: false
    	},
    	function(inputValue) {
    	    if (inputValue === false) return false;
    	    if (inputValue === "") {
    	        swal.showInputError("请输入联系电话");
    	        return false
    	    }
    	    $scope.linkPhone = inputValue;
    	    $scope.$digest();
    	    swal({
                title: "操作成功",
            });
    	});
    }
    
    $scope.subOrder = function(){
    	if($scope.totalAmount==0){
    		return;
    	}
    	var order = {carts:$scope.carts,address:$scope.linkAddress,man:$scope.linkMan,phone:$scope.linkPhone,price:$scope.totalAmount};
    	console.log(order);
    	orderPromise = MallService.subOrder(order);
    	
    	orderPromise.success(function(response){
            var orderId = response["data"];
            $location.path('mall/getOrder/'+orderId);
        }).error(function(error){
            console.log(error);
        });
    }
    
    /*var watch = $scope.$watch("totalAmount",function(newValue,oldValue, scope){
    	
    });*/
    
}]);

mallApp.controller('orderController',['$scope','MallService','messageService','$location','$route',function($scope,MallService,messageService,$location,$route) {
	var orderId = $route.current.params.id;
	$scope.orderId = orderId;
	$scope.payType = 0;
    messageService.publish('breadcrumb','MallIco');
    var orderPromise = MallService.getOrder();
    orderPromise.success(function(response){
    	$scope.order = response["data"];
    }).error(function(error){
        console.log(error);
    });
    $scope.updateInfo = function(type){
    	if(type==0){
    		swal({
        		title:"联系人",
        	    type: "input",
        	    cancelButtonText:"取消",
                confirmButtonText: "确定",
        	    inputValue:$scope.order.man,
        	    showCancelButton: true,
        	    closeOnConfirm: false
        	},
        	function(inputValue) {
        	    if (inputValue === false) return false;
        	    if (inputValue === "") {
        	        swal.showInputError("请输入联系人");
        	        return false
        	    }
        	    var infoPromise = MallService.updateInfo(type,inputValue);
        	    infoPromise.success(function(response){
        	    	$scope.order.man = inputValue;
            	    swal({
                        title: "操作成功",
                    });
        	    }).error(function(error){
        	        console.log(error);
        	    });
        	});
    	}else{
    		swal({
        		title:"联系电话",
        	    type: "input",
        	    cancelButtonText:"取消",
                confirmButtonText: "确定",
        	    inputValue:$scope.order.phone,
        	    showCancelButton: true,
        	    closeOnConfirm: false
        	},
        	function(inputValue) {
        	    if (inputValue === false) return false;
        	    if (inputValue === "") {
        	        swal.showInputError("请输入联系电话");
        	        return false
        	    }
        	    
        	    var infoPromise = MallService.updateInfo(type,inputValue);
        	    infoPromise.success(function(response){
        	    	$scope.order.phone = inputValue;
            	    swal({
                        title: "操作成功",
                    });
        	    }).error(function(error){
        	        console.log(error);
        	    });
        	});
    	}
    }
    
    $scope.pay = function(){
    	var infoPromise = MallService.prepay("北京球友圈网络科技有限责任公司","北京球友圈网络科技有限责任公司",orderId);
	    infoPromise.success(function(response){
	    	if(response["result"]=="success"){
	    		window.open(response["data"]);
	    		swal({
	    		    title: "支付确认",
	    		    text: "如已经成功支付，请点击【确认】；如付款遇到问题，你可以点击【取消】重新付款",
	    		    type: "warning",
	    		    cancelButtonText:"取消",
	                confirmButtonText: "确定",
	    		    showCancelButton: true,
	    		    closeOnConfirm: false,   
	    		    closeOnCancel: false
	    		},
	    		function(flag) {
	    			var statusPromise = MallService.getOrderStatus(orderId);
	    			statusPromise.success(function(response){
	    				if(response["data"]==1){
	    					swal({
	    		                title: "提示",
	    		                text: "支付成功"
	    		            });
	    					$location.path('mall/orders');
		    		    }else{
		    		    	swal({
		    	                title: "警告",
		    	                text: "支付未成功，请重新支付"
		    	            });
		    		    }
	        	    }).error(function(error){
	        	        console.log(error);
	        	    });
	    		    
	    		});
	    	}else{
	    		swal({
	                title: "警告",
	                text: response["data"]
	            });
	    	}
	    }).error(function(error){
	        console.log(error);
	    });
    }
}]);

mallApp.controller('ordersController',['$scope','MallService','messageService','$location',function($scope,MallService,messageService,$location) {
    messageService.publish('breadcrumb','MallIco');
    $scope.statusMap = {0:"待付款",1:"待确认",2:"已发货",3:"已收货",4:"已取消",5:"已退款",6:"退款中"};
    $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 5,
            numberOfPage:0,
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            pagePromise:{},
            onChange: function(){
                var orderPromise = MallService.orders({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
                $scope.paginationConf.pagePromise = orderPromise;
            }
        };
    $scope.orderInfo = function(id){
    	$location.path('mall/orderInfo/'+id);
    }
    $scope.pay = function(orderId){
    	var infoPromise = MallService.prepay("北京球友圈网络科技有限责任公司","北京球友圈网络科技有限责任公司",orderId);
	    infoPromise.success(function(response){
	    	if(response["result"]=="success"){
	    		window.open(response["data"]);
	    		swal({
	    		    title: "支付确认",
	    		    text: "如已经成功支付，请点击【确认】；如付款遇到问题，你可以点击【取消】重新付款",
	    		    type: "warning",
	    		    cancelButtonText:"取消",
	                confirmButtonText: "确定",
	    		    showCancelButton: true,
	    		    closeOnConfirm: false,   
	    		    closeOnCancel: false
	    		},
	    		function(flag) {
	    			var statusPromise = MallService.getOrderStatus(orderId);
	    			statusPromise.success(function(response){
	    				if(response["data"]==1){
	    					swal({
	    		                title: "提示",
	    		                text: "支付成功"
	    		            });
	    					$location.path('mall/orders');
		    		    }else{
		    		    	swal({
		    	                title: "警告",
		    	                text: "支付未成功，请重新支付"
		    	            });
		    		    }
	        	    }).error(function(error){
	        	        console.log(error);
	        	    });
	    		    
	    		});
	    	}else{
	    		swal({
	                title: "警告",
	                text: response["data"]
	            });
	    	}
	    }).error(function(error){
	        console.log(error);
	    });
    }
    
    $scope.cancelOrder = function(orderId){
    	swal({
            title: "确定要取消订单吗?",
            text: "你将要取消订单!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var cancelPromise = MallService.cancelOrder(orderId);
        	cancelPromise.success(function(response){
            	$location.path('mall/orders');
            }).error(function(error){
                console.log(error);
            });
        });
    	
    };
    
    $scope.verify = function(orderId){
    	swal({
            title: "确认收货",
            text: "您正在执行确认收货操作，确认货物后钱款会打到物流方",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var verifyPromise = MallService.verifyOrder(orderId);
        	verifyPromise.success(function(response){
        		swal({
                    title: "确认",
                    text: "确认收货成功!"
                });
        		$location.path('mall/orders');
    	    }).error(function(error){
    	        console.log(error);
    	    });
        });
    	
    	
    }
    
}]);

mallApp.controller('orderInfoController',['$scope','MallService','messageService','$location',"$route",function($scope,MallService,messageService,$location,$route) {
	var orderId = $route.current.params.id;
	$scope.orderId = orderId;
	$scope.payType = 0;
	messageService.publish('breadcrumb','MallIco');
	var orderPromise = MallService.orderInfo();
    orderPromise.success(function(response){
    	$scope.order = response["data"];
    }).error(function(error){
        console.log(error);
    });
    
    $scope.cancel = function(){
    	var cancelPromise = MallService.cancelOrder(orderId);
    	cancelPromise.success(function(response){
        	$location.path('mall/orders');
        }).error(function(error){
            console.log(error);
        });
    };
    
    $scope.pay = function(){
    	var infoPromise = MallService.prepay("北京球友圈网络科技有限责任公司","北京球友圈网络科技有限责任公司",orderId);
	    infoPromise.success(function(response){
	    	if(response["result"]=="success"){
	    		window.open(response["data"]);
	    		swal({
	    		    title: "支付确认",
	    		    text: "如已经成功支付，请点击【确认】；如付款遇到问题，你可以点击【取消】重新付款",
	    		    type: "warning",
	    		    cancelButtonText:"取消",
	                confirmButtonText: "确定",
	    		    showCancelButton: true,
	    		    closeOnConfirm: false,   
	    		    closeOnCancel: false
	    		},
	    		function(flag) {
	    			var statusPromise = MallService.getOrderStatus(orderId);
	    			statusPromise.success(function(response){
	    				if(response["data"]==1){
	    					swal({
	    		                title: "提示",
	    		                text: "支付成功"
	    		            });
	    					$location.path('mall/orders');
		    		    }else{
		    		    	swal({
		    	                title: "警告",
		    	                text: "支付未成功，请重新支付"
		    	            });
		    		    }
	        	    }).error(function(error){
	        	        console.log(error);
	        	    });
	    		    
	    		});
	    	}else{
	    		swal({
	                title: "警告",
	                text: response["data"]
	            });
	    	}
	    }).error(function(error){
	        console.log(error);
	    });
    }
    
    $scope.delay = function(){
    	var delayPromise = MallService.delayOrder(orderId);
    	delayPromise.success(function(response){
    		swal({
                title: "确认",
                text: "收货时间已延长至"+response["data"]
            });
    		$scope.order.shTime=response["data"];
    		$scope.order.delayNum=1;
	    }).error(function(error){
	        console.log(error);
	    });
    }
    
    $scope.verify = function(){
    	
    	swal({
    		title: "确认收货",
            text: "您正在执行确认收货操作，确认货物后钱款会打到物流方",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var verifyPromise = MallService.verifyOrder(orderId);
        	verifyPromise.success(function(response){
        		swal({
                    title: "确认",
                    text: "确认收货成功!"
                });
        		$location.path('mall/orders');
    	    }).error(function(error){
    	        console.log(error);
    	    });
        });
    }
    
    
}]);

mallApp.controller('storageSizeController',['$scope','MallService','messageService','$location',function($scope,MallService,messageService,$location) {
	messageService.publish('breadcrumb','MallIco');
	var storagePromise = MallService.storageSize();
	storagePromise.success(function(response){
    	$scope.storages = response["data"];
    }).error(function(error){
        console.log(error);
    });
	$scope.add = function(index,goodId){
		swal({
    		title:"入库数量",
    	    type: "input",
    	    cancelButtonText:"取消",
            confirmButtonText: "确定",
    	    showCancelButton: true,
    	    closeOnConfirm: false
    	},
    	function(inputValue) {
    	    if (inputValue === false) return false;
    	    if (inputValue === "") {
    	        swal.showInputError("请输入入库数量");
    	        return false
    	    }
    	    
    	    try{
    	    	if(parseInt(inputValue,10)!=inputValue){
    	    		swal.showInputError("入库数量必须是整数");
        	        return false
    	    	}
    	    }catch (e) {
    	    	swal.showInputError("入库数量不合法");
    	        return false
			}
    	    
    	    if (parseInt(inputValue,10)<=0) {
    	        swal.showInputError("入库数量必须大于0");
    	        return false
    	    }
    	    
    	    var addPromise = MallService.addStorage(goodId,inputValue);
    	    addPromise.success(function(response){
    	    	$scope.storages[index].storageSize+=parseInt(inputValue,10);
        	    swal({
                    title: "操作成功",
                });
    	    }).error(function(error){
    	        console.log(error);
    	    });
    	});
	}
	$scope.cut = function(index,goodId){
		swal({
    		title:"出库数量",
    	    type: "input",
    	    cancelButtonText:"取消",
            confirmButtonText: "确定",
    	    showCancelButton: true,
    	    closeOnConfirm: false
    	},
    	function(inputValue) {
    	    if (inputValue === false) return false;
    	    if (inputValue === "") {
    	        swal.showInputError("请输入出库数量");
    	        return false
    	    }
    	    try{
    	    	if(parseInt(inputValue,10)!=inputValue){
    	    		swal.showInputError("出库数量必须是整数");
        	        return false
    	    	}
    	    }catch (e) {
    	    	swal.showInputError("出库数量不合法");
    	        return false
			}
    	    
    	    if (parseInt(inputValue,10)<=0) {
    	        swal.showInputError("出库数量必须大于0");
    	        return false
    	    }
    	    if($scope.storages[index].storageSize<parseInt(inputValue,10)){
    	    	swal.showInputError("库存已不足");
    	    	return false
    	    }
    	    var cutPromise = MallService.cutStorage(goodId,inputValue);
    	    cutPromise.success(function(response){
    	    	$scope.storages[index].storageSize-=parseInt(inputValue,10);
        	    swal({
                    title: "操作成功",
                });
    	    }).error(function(error){
    	        console.log(error);
    	    });
    	});
	}
	
	$scope.update = function(index,goodId){
		swal({
    		title:"售价",
    	    type: "input",
    	    cancelButtonText:"取消",
            confirmButtonText: "确定",
    	    showCancelButton: true,
    	    closeOnConfirm: false
    	},
    	function(inputValue) {
    	    if (inputValue === false) return false;
    	    if (inputValue === "") {
    	        swal.showInputError("请输入售价");
    	        return false
    	    }
    	    try{
    	    	parseFloat(inputValue)
    	    }catch (e) {
    	    	swal.showInputError("售价不合法");
    	        return false
			}
    	    
    	    if (parseFloat(inputValue)<=0) {
    	        swal.showInputError("售价必须大于0");
    	        return false
    	    }
    	    var updatePromise = MallService.updateSell(goodId,inputValue);
    	    updatePromise.success(function(response){
    	    	$scope.storages[index].price_=inputValue;
    	    	$scope.storages[index].sellPrice=inputValue;
        	    swal({
                    title: "操作成功",
                });
    	    }).error(function(error){
    	        console.log(error);
    	    });
    	});
	}
	
	
}]);

mallApp.controller('storageLogsController',['$scope','MallService','messageService','$location',function($scope,MallService,messageService,$location) {
	messageService.publish('breadcrumb','MallIco');
	$scope.typeMap = {0:"订货",1:"入库",2:"出库"};
	$scope.paginationConf = {
			currentPage: 1,
			itemsPerPage: 15,
			numberOfPage:0,
			pagesLength: 15,
			perPageOptions: [10, 20, 30, 40, 50],
			pagePromise:{},
			onChange: function(){
				var orderPromise = MallService.storageLogs({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
				$scope.paginationConf.pagePromise = orderPromise;
			}
	};
}]);
