var spaceLockApp = angular.module('spaceLockApp',['ngRoute','crm.pagination'])
//定义后台访问方法
spaceLockApp.factory('SpaceLockService',['$http','$route',function($http,$route,$routeParams){
    return {

        loadSpaceLock:function(page,size){
            return $http.post('/admin/spacelock/list',{page:page,size:size},{cache:false});
        },
        initSpaceLock:function(){
            return $http.post('/admin/spacelock/init',{cache:false});
        },
        getSpaceBySportType:function(spaceType){
            return $http.post('/admin/spacelock/getSpaceBySportType',spaceType);
        },
        checkSpaceIsLock:function(spaceLock){
            return $http.post('/admin/spacelock/checkSpaceIsLock',spaceLock);
        },
        lockSpaceBatch:function(spaceLock){
            return $http.post('/admin/spacelock/lockSpaceBatch',spaceLock);
        },
        deleteLockSpaceBatch:function(id){
            return $http.post('/admin/spacelock/deleteLockSpaceBatch',id);
        }
        
        
    
        
    }
}]);
//定义前台显示过滤器
spaceLockApp.filter("weekFilter", function() {
	 var filterfun = function(input) {
        if(input==1){
        	return "星期日";
        }else if(input==2){
        	return "星期一";
        }else if(input==3){
        	return "星期二";
        }else if(input==4){
        	return "星期三";
        }else if(input==5){
        	return "星期四";
        }else if(input==6){
        	return "星期五";
        }else if(input==7){
        	return "星期六";
        }else if(input==0){
        	return "全部";
        }
    };
    return filterfun;
});

spaceLockApp.filter("transSportType", function($rootScope) {
    var filterfun = function(input) {
    	return $rootScope.sportTypeMap[input];
    };
    return filterfun;
});

//控制器
spaceLockApp.controller('loadSpaceLockController',['$scope','SpaceLockService','messageService','$location','$rootScope',function($scope,SpaceLockService,messageService,$location,$rootScope) {
    messageService.publish('breadcrumb',"bathchLockIco");
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
            var spacePromise = SpaceLockService.loadSpaceLock($scope.paginationConf.currentPage-1,$scope.paginationConf.itemsPerPage);
            $scope.paginationConf.pagePromise = spacePromise;
        }
    };
    $scope.deleteLockSpaceBatch = function(id){
      var spaceLock = $scope.objs[id];
      swal({
          title: "确定要解锁吗?",
          text: "你将要解锁该批量锁定!",
          type: "warning",
          showCancelButton: true,
          cancelButtonText:"取消",
          confirmButtonColor: "#DD6B55",
          confirmButtonText: "确定",
          closeOnConfirm: true
      }, function () {
	    var spacePromise = SpaceLockService.deleteLockSpaceBatch(spaceLock["id"]);
      	spacePromise.success(function(data){
      		if(data["result"]=="success"){
      			messageService.publish('notifyMessage',["解除锁定成功！","success"]);
      			$scope.objs.splice(id,1);
      		}else{
      			messageService.publish('notifyMessage',["解除锁定失败！","error"]);
      		}
          }).error(function(error){
        	  messageService.publish('notifyMessage',["解除锁定失败！","error"]);
          });
      });
     };
}]);

//控制器
spaceLockApp.controller('initSpaceLockController',['$scope','SpaceLockService','messageService','$location','$rootScope',function($scope,SpaceLockService,messageService,$location,$rootScope) {
	messageService.publish('breadcrumb',"bathchLockIco");
    $scope.sportTypesMap = $rootScope.sportTypeMap;
    var spacePromise = SpaceLockService.initSpaceLock();
    spacePromise.success(function(spaceLock){
        $scope.spaceLock = spaceLock["data"];
        $scope.sportTypes = $scope.spaceLock.sportTypes.substring(0,$scope.spaceLock.sportTypes.length-2).split(";;");
        //下拉框默认第一个
        $scope.spaceLock.sportType = $scope.sportTypes[0];
        //场地默认第一类
        $scope.changeSportType($scope.sportTypes[0]);
        $scope.spaceLock.endHour="1";
        $scope.spaceLock.startHour="0";
        $scope.spaceLock.weekDay="0";
    }).error(function(error){
        console.log(error);
    });
    $scope.sportTypeMap = {"篮球":"L","足球":"Z","羽毛球":"Y","网球":"W","乒乓球":"P","台球":"T","保龄球":"B","高尔夫":"G"};
    $scope.changeSportType = function(sportType){
	   var spacePromise = SpaceLockService.getSpaceBySportType(sportType);
	    spacePromise.success(function(space){
	    	if(space["result"]=="success"){
	    		$scope.spaceLock.spaceList = space["data"];
	    	}else{
	    		swal({
	                title: "警告",
	                text: "获取场地列表失败！"
	            });
	    	}
	    }).error(function(error){
	    	swal({
                title: "警告",
                text: "获取场地列表失败！"
            });
	    });
    }
    $scope.checkSpaceIsLock = function(){
    	if($scope.spaceLock.startTime==undefined || $scope.spaceLock.startTime==''){
    		swal({
                title: "警告",
                text: "请选择开始日期！"
            });
    		return;
    	}
    	if($scope.spaceLock.endTime==undefined || $scope.spaceLock.endTime==''){
    		swal({
                title: "警告",
                text: "请选择结束日期！"
            });
    		return;
    	}
    	if($scope.spaceLock.startHour==undefined || $scope.spaceLock.startHour==''){
    		swal({
                title: "警告",
                text: "请选择开始时段！"
            });
    		return;
    	}
    	if($scope.spaceLock.endHour==undefined || $scope.spaceLock.endHour==''){
    		swal({
                title: "警告",
                text: "请选择结束时段！"
            });
    		return;
    	}
    	if($scope.spaceLock.weekDay==undefined || $scope.spaceLock.weekDay==''){
    		swal({
                title: "警告",
                text: "请选择星期几！"
            });
    		return;
    	}
    	var spaceList = $scope.spaceLock.spaceList;
    	var flag = false;
    	for(var ele in spaceList){
    		if(spaceList[ele].checked){
    			flag = true
    		}
    	}
    	if(!flag){
    		swal({
                title: "警告",
                text: "请选择要锁定的场地！"
            });
    		return;
    	}
 	   var spacePromise = SpaceLockService.checkSpaceIsLock($scope.spaceLock);
 	    spacePromise.success(function(space){
 	    	if(space["result"]=='success'){
 	    		$('#lockButton').attr('disabled',false); 
 	    	}else{
 	    		$('#lockButton').attr('disabled',true); 
 	    	}
 	       swal({
               title: "提示",
               text: space["data"]
           });
 	    }).error(function(error){
 	        console.log(error);
 	    });
     }
    $scope.lockSpaceBatch = function(){
  	   var spacePromise = SpaceLockService.lockSpaceBatch($scope.spaceLock);
  	    spacePromise.success(function(space){
  	    	if(space["result"]=='fail'){
  	    		$('#lockButton').attr('disabled',true);
  	    		swal({
  	                title: "警告",
  	                text: space["data"]
  	            });
  	    		messageService.publish('notifyMessage',["批量锁定失败！","error"]);
 	    	}else{
 	    		messageService.publish('notifyMessage',["批量锁定成功！","success"]);
  	  	    	$location.path('spaceLock');
 	    	}
  	    	
  	      
  	    }).error(function(error){
  	    	messageService.publish('notifyMessage',["批量锁定失败！","error"]);
  	    });
      };
    

}]);


