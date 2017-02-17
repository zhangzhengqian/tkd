var userApp = angular.module('userApp',['ngRoute','crm.pagination'])

userApp.factory('UserService',['$http','$route',function($http,$route,$routeParams){
    return {
        loadUser:function(){
            var userId = $route.current.params.id;
            return $http.post('/admin/user/'+userId,{cache:false});
        },
        initUser:function(){
            return $http.post('/admin/user/init',{cache:false});
        },
        loadUsers:function(page,size){
            return $http.post('/admin/user',{page:page,size:size},{cache:false});
        },
        saveUser:function(user){
        	return $http.post('/admin/user/save',user);
        },
        updateUser:function(user){
        	return $http.post('/admin/user/update',user);
        },
        deleteUser:function(userId){
        	return $http.post('/admin/user/delete/'+userId,{cache:false});
        },
        getUser:function(){
        	return $http.post('/admin/user/get/',{cache:false});
        },
        checkUserName:function(userName){
        	return $http.post('/admin/user/check/'+userName,{cache:false});
        }
    }
}]);

userApp.controller('loadUsersController',['$scope','UserService','messageService','$location',function($scope,UserService,messageService,$location) {
    messageService.publish('breadcrumb','perCenterIcon');
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
            var userPromise = UserService.loadUsers($scope.paginationConf.currentPage-1,$scope.paginationConf.itemsPerPage);
            $scope.paginationConf.pagePromise = userPromise;
        }
    };
    $scope.deleteUser = function(id){
    	var user = $scope.objs[id];
    	swal({
            title: "您确定要删除该员工吗？",
            text: "你将要删除员工信息!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var userPromise = UserService.deleteUser(user["userId"]);
        	userPromise.success(function(data){
        		if(data["result"]=="success"){
        			messageService.publish('notifyMessage',["删除员工信息成功！","success"]);
        			$scope.objs.splice(id,1);
        		}else{
        			messageService.publish('notifyMessage',["删除员工信息失败！","success"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["删除员工信息失败！","success"]);
            });
        });
    }
    /*$scope.safeApply = function(fn) {
        var phase = this.$root.$$phase;
        if (phase == '$apply' || phase == '$digest') {
            if (fn && (typeof(fn) === 'function')) {
                fn();
            }
        } else {
            this.$apply(fn);
        }
    };*/

}]);

userApp.controller('loadUserController',['$scope','UserService','messageService','$location',function($scope,UserService,messageService,$location) {
    messageService.publish('breadcrumb','perCenterIcon');
    var userPromise = UserService.loadUser();
    userPromise.success(function(user){
        $scope.user = user["data"];
    }).error(function(error){
        console.log(error);
    });
    $scope.disabled = true;
    $scope.saveUser = function(){
    	if($scope.user.realname==undefined || $scope.user.realname==''){
    		swal({
                title: "警告",
                text: "姓名不能为空！"
            });
    		return;
    	}
    	if($scope.user.loginName==undefined || $scope.user.loginName==''){
    		swal({
                title: "警告",
                text: "手机号不能为空！"
            });
    		return;
    	}
    	if($scope.user.custId==undefined || $scope.user.custId==''){
    		swal({
                title: "警告",
                text: "员工号不能为空！"
            });
    		return;
    	}
    	var userPromise = UserService.saveUser($scope.user);
    	userPromise.success(function(data){
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["用户修改成功！","success"]);
    			$location.path('user');
    		}else{
    			messageService.publish('notifyMessage',["用户修改失败！","error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["用户修改失败！","error"]);
        });
    };
}]);

userApp.controller('initUserController',['$scope','UserService','messageService','$location',function($scope,UserService,messageService,$location) {
    messageService.publish('breadcrumb','perCenterIcon');
    var userPromise = UserService.initUser();
    userPromise.success(function(user){
        $scope.user = user["data"];
    }).error(function(error){
        console.log(error);
    });
    $scope.saveUser = function(){
    	if($scope.user.realname==undefined || $scope.user.realname==''){
    		swal({
                title: "警告",
                text: "姓名不能为空！"
            });
    		return;
    	}
    	if($scope.user.loginName==undefined || $scope.user.loginName==''){
    		swal({
                title: "警告",
                text: "手机号不能为空！"
            });
    		return;
    	}
    	var checkPromise = UserService.checkUserName($scope.user.loginName);
    	checkPromise.success(function(data){
    		if(data["result"]=='success'){
    			if($scope.user.custId==undefined || $scope.user.custId==''){
    	    		swal({
    	                title: "警告",
    	                text: "员工号不能为空！"
    	            });
    	    		return;
    	    	}
    	    	var userPromise = UserService.saveUser($scope.user);
    	    	userPromise.success(function(data){
    	    		if(data["result"]=='success'){
    	    			messageService.publish('notifyMessage',["用户保存成功！","success"]);
    	    			$location.path('user');
    	    		}else{
    	    			messageService.publish('notifyMessage',["用户保存失败！","error"]);
    	    		}
    	        }).error(function(error){
    	        	messageService.publish('notifyMessage',["用户保存失败！","error"]);
    	        });
    		}else{
    			if(!data.data){
    				swal({
                        title: "警告",
                        text: "手机号已存在！"
                    });
    			}else{
    				swal({
                        title: "警告",
                        text: data.data
                    });
    			}
    		}
        }).error(function(error){
        	
        });
    }
}]);

userApp.controller('loadUserCenterController',['$scope','UserService','messageService','$location',function($scope,UserService,messageService,$location) {
    messageService.publish('breadcrumb','perCenterIcon');
    var userPromise = UserService.getUser();
    userPromise.success(function(user){
        $scope.user = user["data"];
    }).error(function(error){
        console.log(error);
    });
    $scope.updateUser = function(){
    	if($scope.user.password==undefined || $scope.user.password==''){
    		swal({
                title: "警告",
                text: "原密码不能为空！"
            });
    		return;
    	}
    	if($scope.user.newPassword==undefined || $scope.user.newPassword==''){
    		swal({
                title: "警告",
                text: "新密码不能为空！"
            });
    		return;
    	}
    	if($scope.user.newPassword==$scope.user.password){
    		swal({
                title: "警告",
                text: "原密码和新密码不能相同！"
            });
    		return;
    	}
    	var userPromise = UserService.updateUser($scope.user);
    	userPromise.success(function(data){
    		if(data.result=='success'){
    			$scope.user.password="";
    			$scope.user.newPassword="";
    			messageService.publish('notifyMessage',["密码修改成功！","success"]);
    		}else{
    			swal({
                    title: "警告",
                    text: data.data
                });
    			messageService.publish('notifyMessage',["密码修改失败！","error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["密码修改失败！","error"]);
        });
    }
}]);