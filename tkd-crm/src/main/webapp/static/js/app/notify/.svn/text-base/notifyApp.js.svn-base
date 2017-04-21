var notifyApp = angular.module('notifyApp',['ngRoute','crm.pagination']);

notifyApp.factory('notifyService',['$http','$route',function($http,$route,$routeParams){
	return{
		loadMessagesList:function(page,size){
			return $http.post('/admin/messages',{page:page,size:size},{cache:false});
		},
		getMessageDetail:function(msgId){
			return $http.post('/admin/messages/'+msgId,{cache:false});
		},
		batchUpdateMessageStatus:function(){
			return $http.post('/admin/messages/updateBatch',{cache:false});
		}
	}
}]);

notifyApp.filter("filterIsRead",function(){
	var filterfun = function(input){
		if(input == 'new'){
			return "unread";
		}else{
			return "read";
		}
	};
	return filterfun;
});

notifyApp.filter('to_trusted', ['$sce', function ($sce) {
	return function (text) {
		return $sce.trustAsHtml(text);
	}
}]);

/*notifyApp.filter("filterTitle",function(){
	var filterfun = function(input){
		console.log(input);
		if(input.indexOf(",") != -1){
			return input.split(",")[0];
		}else{
			return input;
		}
	};
	return filterfun;
});*/
notifyApp.controller('loadNotifyController',['$scope','messageService','notifyService','$location',function($scope,messageService,notifyService,$location){
	 $scope.paginationConf = {
		        currentPage: 1,
		        itemsPerPage: 20,
		        numberOfPage:0,
		        pagesLength: 15,
		        perPageOptions: [10, 20, 30, 40, 50],
		        pagePromise:{},
		        onChange: function(){
		            var notifyPromise = notifyService.loadMessagesList($scope.paginationConf.currentPage-1,$scope.paginationConf.itemsPerPage);
		            $scope.paginationConf.pagePromise = notifyPromise;
		        }
		    };
	
    $scope.showDetailMsg = function(id){
    	var message = $scope.objs[id];
    	var status = message["status"];
    	var notifyPromise = notifyService.getMessageDetail(message["id"]);
    	notifyPromise.success(function(data){
    		if(data["result"]=="success"){
    			$('#msgTitle').html(data["data"].title);
    			$('#msgCt').html(data["data"].ct);
    			$('#msgConten').html(data["data"].content);
    			if(status=="new"){
    				message["status"]="read";
    				var unreadNum = $("#unreadNum").html();
    				unreadNum = parseInt(unreadNum,10)-1;
    				$("#unreadNum").html(unreadNum);
    			}
    		}else{
    			
    		}
        }).error(function(error){
            console.log(error);
        });
	}
    
    $scope.batchUpdate = function(){
    	var notifyPromise = notifyService.batchUpdateMessageStatus();
    	notifyPromise.success(function(data){
    		if(data["result"]=="success"){
    			angular.forEach($scope.objs,function(obj){
    				obj["status"]="read";
    			});
    			$("#unreadNum").html("0");
    		}else{
    			
    		}
        }).error(function(error){
            console.log(error);
        });
    }
    
}]);

