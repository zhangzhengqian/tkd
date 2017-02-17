//定义模块，引入route、resource内置服务
var app = angular.module('qiuyouZoneCrm', ['ngRoute','ngCookies','userApp','orderApp','statiumApp','memberApp','spaceApp','billApp','statiumUseApp','notifyApp','spaceLockApp','unauthApp','gameApp','mallApp','prizeApp']).run(function($rootScope,$cookieStore,StatiumNameService,messageService,$location) {
	$rootScope.sportTypeMap = {"BASKETBALL":"篮球","FOOTBALL":"足球","BADMINTON":"羽毛球","TENNIS":"网球","TABLE_TENNIS":"乒乓球","BILLIARDS":"台球","BOWLING":"保龄球","GOLF":"高尔夫"};
	var login_flag = $cookieStore.get("isLogin");
	if(login_flag!='1'){
		window.location.href="/login";
	}
	//websocket
	var websocketUrl = $cookieStore.get("websocketUrl");
	//ws = new WebSocket("ws://localhost:6080/websocket");
	var statium_Id = $cookieStore.get("statiumId");
	try{
		ws = new WebSocket(websocketUrl);
	}catch(e){
		ws = new WebSocket(websocketUrl);
	}
	ws.onopen = function() {
		ws.send('{loginfo:"'+statium_Id+'"}');
	};
	ws.onmessage = function(evt) {
		
//		var data ={
//					id:'fdsfds',
//					type:'order',
//					flag:'online/offline',
//					ct:'2015-09-30 11:12:13',
//					obj:{
//						"orderDate":"2015-09-25",
//						"type":"new",
//						"spaces":[{"spaceId":"41740cc113444143b96c7494569d995b","time":"12:00"},
//						          {"spaceId":"41740cc113444143b96c7494569d995b","time":"12:30"},
//						          {"spaceId":"41740cc113444143b96c7494569d995b","time":"13:00"},
//						          {"spaceId":"41740cc113444143b96c7494569d995b","time":"13:30"}],
//						"statiumId":"015045c16a524e97ab69a44034c9440e"
//						}
//					} 
		
		
		var data = JSON.parse(evt.data);
		/*if(data.type=='draw'){
			var token = data.id;
			var prizes = data.obj;
			for(var i=0;i<prizes.length;i++){
				var prize = prizes[i];
				$("#p"+(i+1)).text(prize.pn);
				$("#p"+(i+1)).attr("pt",prize.pt);
			}
			$("#lottery").show();
			$("#ptrs").hide();
			$($("#lottery").find(".active")[0]).removeClass('active');
			$("#lotteryModal").modal();
			lottery.init('lottery');
			$("#lottery a").click(function(){
				if (click) {
					return false;
				}else{
					$.ajax({
						type : "post",
						url : "/admin/prize/draw",
						contentType:'application/json;charset=utf-8',
						method:"post",
						cache:false,
						data : {},
						success : function(res) {
							if(res.result=='success'){
								lottery.speed=100;
								var pt = res.data;
								lottery.pt=pt;
								lottery.prize=$("#lottery").find(".lottery-unit[pt='"+pt+"']").attr("index");
								roll();
								click=true;
							}else{
								if(res.data=='timeout'){
									
								}else if(res.data=='error'){
									
								}
							}
						}
					});
				}
			});
			return;
		}*/
		if(data.obj.type != "deleteB"){
			if(data.flag == 'online'){
				$("#audioBox").html('<audio style="display:none;" autoplay="autoplay" src="static/song/1.mp3" controls="controls"></audio>');
			}
			var unreadNum = $("#unreadNum").html();
			unreadNum = parseInt(unreadNum,10);
			unreadNum = unreadNum+1;
			$("#unreadNum").html(unreadNum);
		}
		
		ws.send('{loginfo:"'+statium_Id+'",ack:"'+data.id+'"}');
		
		if(data.type=="order"){
			if(data.obj.type == "new"){
				$("#orderTip").find("p:eq(0)").html("您有新场地预定");
			}else if(data.obj.type == "cancel"){
				$("#orderTip").find("p:eq(0)").html("您有场地已取消");
			}else if(data.obj.type == "pay"){
				$("#orderTip").find("p:eq(0)").html(data.obj.orderId+"订单已支付");
			}else if(data.obj.type == "update"){
				$("#orderTip").find("p:eq(0)").html(data.obj.orderId+"订单已修改");
			}else{
				
			}
			
			$("#orderTip").fadeIn();
			if((window.location.href).indexOf("statiumUse")!=-1||(window.location.href).lastIndexOf("/#/")!=-1){
				var selectDate = $("#input_date").val();
				if(selectDate == data.obj.orderDate){
					if(data.obj.type == "new"){//新订单未支付
						$.each(data.obj.spaces,function(key,value){
							var spaceId = $("#"+value.spaceId);
							var beginNode = $(spaceId).find("div[begin-time-data='"+value.time+"']");
							beginNode.attr("orderid-data",data.obj.orderId);
							beginNode.attr("title","球友圈未支付订单");
							beginNode.removeClass("canBook").addClass("noPayOrder").addClass("appOrder");
							beginNode.find("a:eq(0)").removeClass().addClass("noPayIcoQiu");
						});
					}else if(data.obj.type == "cancel"){//取消订单
						$.each(data.obj.spaces,function(key,value){
							var spaceId = $("#"+value.spaceId);
							var beginNode = $(spaceId).find("div[begin-time-data='"+value.time+"']");
							beginNode.removeAttr("orderid-data");
							beginNode.removeAttr("title");
							beginNode.removeClass("appOrder").removeClass("noPayOrder").addClass("canBook");
							beginNode.find("a:eq(0)").removeAttr("style");
							beginNode.find("a:eq(0)").removeClass();
						});
					}else if(data.obj.type == "pay"){//已支付订单
						$.each(data.obj.spaces,function(key,value){
							console.log(value);
							var spaceId = $("#"+value.spaceId);
							var beginNode = $(spaceId).find("div[begin-time-data='"+value.time+"']");
							beginNode.removeClass("noPayOrder").addClass("isBooked");
							beginNode.attr("title","球友圈已支付订单");
							beginNode.find("a:eq(0)").removeClass();
							beginNode.find("a:eq(0)").addClass("yelbot");
						});
					}else if(data.obj.type == "update"){//已支付订单
						$.each(data.obj.spaces,function(key,value){
							var spaceId = $("#"+value.spaceId);
							var beginNode = $(spaceId).find("div[begin-time-data='"+value.time+"']");
							beginNode.removeClass("noPayOrder").addClass("isBooked");
							beginNode.attr("title","球友圈已支付订单");
							beginNode.find("a:eq(0)").removeClass();
							beginNode.find("a:eq(0)").addClass("yelbot");
						});
					}else if(data.obj.type == "deleteB"){//取消原场地
						$.each(data.obj.spaces,function(key,value){
							var spaceId = $("#"+value.spaceId);
							var beginNode = $(spaceId).find("div[begin-time-data='"+value.time+"']");
							beginNode.removeAttr("orderid-data");
							beginNode.removeAttr("title");
							beginNode.removeClass("appOrder").removeClass("noPayOrder").addClass("canBook");
							beginNode.find("a:eq(0)").removeAttr("style");
							beginNode.find("a:eq(0)").removeClass();
						});
					}
				}
			}
			
		}else if(data.type=="bill"){
			$("#orderTip").fadeIn();
			$("#orderTip").find("p:eq(0)").html(data.obj.billTitle);
		}
		
		
		
		
		
		
		
		//messageOper(evt.data);
		
	};
	ws.onclose = function(evt) {
		console.log("WebSocketClosed!");
		try{
			ws = new WebSocket(websocketUrl);
		}catch(e){
			ws = new WebSocket(websocketUrl);
		}
	};
	ws.onerror = function(evt) {
		console.log("WebSocketError!");
		try{
			ws = new WebSocket(websocketUrl);
		}catch(e){
			ws = new WebSocket(websocketUrl);
		}
	};
	$("#orderTipClose").on("click", function() {
		$("#orderTip").fadeOut();
	});
	
	//上传会员表
	var upload=function(option){
		$("#"+option.id).uploadify({
			fileObjName   : 'upfile',
			buttonText    : '选择文件',
			height        : 30,
			multi         : false,
			swf           : '/static/uploadify/uploadify.swf',
		    uploader      : '/uploadMember',
			width         : 100,
			fileTypeExts  : '*.xls;',
		    onUploadSuccess : function(file, data, response) {
		    	if(response){
		    		messageService.publish('notifyMessage',["导入成功！","success"]);
		    		window.location.reload();
		    	}else{
		    		messageService.publish('notifyMessage',["导入失败！","success"]);
		    	}
		    }  
		});
	}
	upload({'id':'myfile'});
	
	//操作提示
	messageService.subscribe('notifyMessage', function(e, notifyMessage) {
    	toastr.options.positionClass = "toast-top-right";
    	toastr.options.timeOut=3000;
    	toastr.options.extendedTimeOut=1000;
    	toastr.options.closeButton=true;
    	if(notifyMessage[1]=='success'){
    		toastr.success(notifyMessage[0]);
    	}else{
    		toastr.error(notifyMessage[0]);
    	}
    });
});

//获取场馆名
app.factory('StatiumNameService',['$http','$route',function($http,$route,$routeParams){
    return {
        getStatiumName:function(){
            return $http.post('/admin/statium/get',{cache:false});
        }
    }
}]);

//获取未读消息数
app.factory('MsgCountService',['$http','$route',function($http,$route,$routeParams){
    return {
    	getMsgCount:function(){
            return $http.post('/admin/messages/getMsgCount',{cache:false});
        }
    }
}]);

//权限验证
app.factory('PermissionService',["$rootScope","$http","$location","$q",function($rootScope,$http,$location,$q){
    return {
    	permissionModel: {permission: [], isPermissionLoaded: false },
    	permissionCheck:function(action){
    		var deferred = $q.defer();
    		var that = this;
    		if (that.permissionModel.isPermissionLoaded) {
    			that.getPermission(that.permissionModel, action, deferred);
    		}else{
    			$http.post('/admin/user/getPermissions',{cache:false}).success(function(data){
    				that.permissionModel.permission = data["data"];
    				that.permissionModel.isPermissionLoaded = true;
    				that.getPermission(that.permissionModel, action, deferred);
    			});
    		}
            return 
        },
        getPermissions:function(){
        	return $http.post('/admin/user/getPermissions',{cache:false});
        },
        getPermission:function(permissionModel, action, deferred){
        	var ifPermissionPassed = false;
        	
        	angular.forEach(permissionModel.permission, function (permission) {
        		if(permission.action==action){
        			ifPermissionPassed = true;
        		}
        	});
        	if (!ifPermissionPassed) {
                // 如果用户没有必须的权限，我们把用户引导到无权访问页面
                $location.path("/401");
                /*swal({
                    title: "警告",
                    text: "没有权限！"
                });*/
                //history.go(-1);
                // 由于这个处理会有延时，而这期间页面位置可能发生改变， 
                // 我们会一直监视 $locationChangeSuccess 事件
                // 并且当该事件发生的时，就把掉承诺解决掉。
                $rootScope.$on('$locationChangeSuccess', function (next, current) {
                    deferred.resolve();
                });
            } else {
            	$rootScope.action = action;
                deferred.resolve();
            }
        }
    }
}]);

//http拦截器
app.factory('timestampMarker', ["$rootScope", '$cookieStore',function ($rootScope,$cookieStore) {
    var timestampMarker = {
        request: function (config) {
        	$("#loading").show();
            config.requestTimestamp = new Date().getTime();
            return config;
        },
        response: function (response) {
        	$("#loading").hide();
        	var login_flag = $cookieStore.get("isLogin");
        	if(login_flag!='1'){
        		window.location.href="/login";
        	}
            response.config.responseTimestamp = new Date().getTime();
            return response;
        }
    };
    return timestampMarker;
}]);

//面包屑
app.service('messageService', ['$rootScope', function($rootScope) {

    return {
        publish: function(name, parameters) {
            $rootScope.$emit(name, parameters);
        },
        subscribe: function(name, listener) {
            $rootScope.$on(name, listener);
        }
    };
}]);


//路由配置
app.config(function($routeProvider, $compileProvider ,$controllerProvider, $httpProvider) {

    $routeProvider
        .when('/', {
        	 templateUrl : 'view/statiumuse/statiumUse.html?ver=1.0',
             controller  : 'loadStatiumUseController',
             caseInsensitiveMatch:true,
             resolve: {
                 permission: function (PermissionService) {
                	 return PermissionService.permissionCheck("#statiumUse"); 
                 }
             }
        })
        .when('/401/', {
       	 	templateUrl : '401.html?ver=1.0',
       	 	controller  : 'unauthController',
       	 	caseInsensitiveMatch:true
        })
        .when('/statiumEdit/', {
            templateUrl : 'view/statium/statiumForm.html?ver=1.2',
            controller  : 'loadStatiumController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#statiumEdit"); 
                }
            }
        })
        .when('/user/', {
            templateUrl : 'view/user/userList.html?ver=1.0',
            controller  : 'loadUsersController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }
        })
        .when('/mall/orders/', {
            templateUrl : 'view/mall/orders.html?ver=1.0',
            controller  : 'ordersController',
            caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/mall/storageSize/', {
            templateUrl : 'view/mall/storageSize.html?ver=1.0',
            controller  : 'storageSizeController',
            caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/mall/storageLogs/', {
        	templateUrl : 'view/mall/storageLogs.html?ver=1.0',
        	controller  : 'storageLogsController',
        	caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/mall/getOrder/:id/', {
            templateUrl : 'view/mall/order.html?ver=1.0',
            controller  : 'orderController',
            caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/mall/orderInfo/:id/', {
            templateUrl : 'view/mall/orderInfo.html?ver=1.0',
            controller  : 'orderInfoController',
            caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/mall/goods/', {
            templateUrl : 'view/mall/goods.html?ver=1.0',
            controller  : 'goodsController',
            caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/prize/list/', {
            templateUrl : 'view/prize/list.html?ver=1.0',
            controller  : 'prizesController',
            caseInsensitiveMatch:true/*,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }*/
        })
        .when('/userCenter/', {
            templateUrl : 'view/user/userCenter.html?ver=1.0',
            controller  : 'loadUserCenterController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#userCenter"); 
                }
            }
        })
        .when('/userSave/', {
            templateUrl : 'view/user/userForm.html?ver=1.0',
            controller  : 'initUserController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }
        })
        .when('/userEdit/:id/', {
            templateUrl : 'view/user/userForm.html?ver=1.0',
            controller  : 'loadUserController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#user"); 
                }
            }
        })
        .when('/order/', {
            templateUrl : 'view/order/orderList.html?ver=1.0',
            controller  : 'loadOrdersController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#order"); 
                }
            }
        })
        .when('/bill/', {
            templateUrl : 'view/bill/billList.html?ver=1.0',
            controller  : 'loadBillsController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#bill"); 
                }
            }
        })
        .when('/bill/:id/', {
            templateUrl : 'view/bill/billItem.html?ver=1.0',
            controller  : 'loadBillController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#bill"); 
                }
            }
        })
        .when('/bill/getOrder/:id/', {
            templateUrl : 'view/bill/orderList.html?ver=1.0',
            controller  : 'loadBillOrderController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#bill"); 
                }
            }
        })
        .when('/space/', {
            templateUrl : 'view/space/spaceList.html?ver=1.3',
            controller  : 'loadSpacesController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#space"); 
                }
            }
        })
        .when('/spaceSave/', {
            templateUrl : 'view/space/spaceForm.html?ver=1.0',
            controller  : 'initSpaceController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#space"); 
                }
            }
        })
        .when('/spaceEdit/:id/', {
            templateUrl : 'view/space/spaceEdit.html?ver=1.0',
            controller  : 'loadSpaceController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#space"); 
                }
            }
        })
        .when('/members/', {
            templateUrl : 'view/member/list.html?ver=1.1',
            controller  : 'loadMembersController',
            caseInsensitiveMatch:true
        })
        .when('/addMember/', {
            templateUrl : 'view/member/memberForm.html?ver=1.0',
            controller  : 'saveMemberController',
            caseInsensitiveMatch:true
        })
        .when('/member/:id/', {
            templateUrl : 'view/member/member.html?ver=1.0',
            controller  : 'loadMemberController',
            caseInsensitiveMatch:true,
        })
        .when('/memberAccount/:id/:cardNo', {
            templateUrl : 'view/member/memberAccount.html?ver=1.0',
            controller  : 'loadMemberAccountController',
            caseInsensitiveMatch:true,
        })
        .when('/memberLevels/', {
            templateUrl : 'view/member/levelList.html?ver=1.0',
            controller  : 'loadLevelsController',
            caseInsensitiveMatch:true
        })
        .when('/statiumUse/', {
            templateUrl : 'view/statiumuse/statiumUse.html?ver=1.0',
            controller  : 'loadStatiumUseController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#statiumUse"); 
                }
            }
        })
        .when('/messages/',{
        	templateUrl : 'view/notify/notify.html?ver=1.0',
            controller  : 'loadNotifyController',
            caseInsensitiveMatch:true
            /*,resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#notify"); 
                }
            }*/
        })
        .when('/game/',{
        	templateUrl : 'view/game/gameList.html?ver=1.1',
            controller  : 'loadGamesController',
            caseInsensitiveMatch:true
            /*,resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#notify"); 
                }
            }*/
        })
        .when('/gameAdd/',{
        	templateUrl : 'view/game/gameForm.html?ver=1.1',
            controller  : 'gameFormController',
            caseInsensitiveMatch:true
            /*,resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#notify"); 
                }
            }*/
        })
        .when('/viewGame/:id/', {
            templateUrl : 'view/game/gameForm.html?ver=1.1',
            controller  : 'gameFormController',
            caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
        .when('/gameMembers/:id/', {
            templateUrl : 'view/game/gameMembers.html?ver=1.1',
            controller  : 'gameMembersController',
            caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
        .when('/inputScore/:id/', {
        	templateUrl : 'view/game/inputScore.html?ver=1.1',
        	controller  : 'inputScoreController',
        	caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
        .when('/scheduleList/:id/', {
        	templateUrl : 'view/game/scheduleList.html?ver=1.1',
        	controller  : 'scheduleListController',
        	caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
        .when('/orderSeeds/:id/', {
            templateUrl : 'view/game/seeds.html?ver=1.1',
            controller  : 'orderSeedsController',
            caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
        .when('/setGameSchedule/:id/', {
        	templateUrl : 'view/game/gameSchedule.html?ver=1.1',
        	controller  : 'gameScheduleController',
        	caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
        .when('/getRank/:id/', {
        	templateUrl : 'view/game/gameRank.html?ver=1.1',
        	controller  : 'gameRankController',
        	caseInsensitiveMatch:true
//            resolve: {
//                permission: function (PermissionService) {
//               	 return PermissionService.permissionCheck("#space"); 
//                }
//            }
        })
	    .when('/spaceLock/', {
	        templateUrl : 'view/spaceLock/spaceLockList.html?ver=1.1',
	        controller  : 'loadSpaceLockController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#spaceLock"); 
                }
            }
	    })
	    .when('/spaceLockSave/', {
	        templateUrl : 'view/spaceLock/spaceLockForm.html?ver=1.2',
	        controller  : 'initSpaceLockController',
            caseInsensitiveMatch:true,
            resolve: {
                permission: function (PermissionService) {
               	 return PermissionService.permissionCheck("#spaceLock"); 
                }
            }
	    })
	    .otherwise({  
            redirectTo: '/'  
        });
    	if (!$httpProvider.defaults.headers.get) {
    		$httpProvider.defaults.headers.get = {};
    	}
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
        $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    	$httpProvider.interceptors.push('timestampMarker');
});

app.controller('loginUserCTL',['$rootScope','$scope','StatiumNameService','MsgCountService','PermissionService','messageService',function($rootScope,$scope,StatiumNameService,MsgCountService,PermissionService,messageService) {
	var statiumNamePromise = StatiumNameService.getStatiumName();
	statiumNamePromise.success(function(data){
		$scope.statiumName = data["data"];
		$scope.sportType_tennis = data["sportType"];
		$scope.mall_privilege = data["mall_privilege"];
	}).error(function(error){
        console.log(error);
    });
	
	var msgCountService = MsgCountService.getMsgCount();
	msgCountService.success(function(data){
		$scope.msgCount = data["data"];
	}).error(function(error){
        console.log(error);
    });
	
	var permissionPromise = PermissionService.getPermissions();
	permissionPromise.success(function(data){
		$scope.permissions = data["data"];
		$rootScope.userPermissions = data["data"];
	}).error(function(error){
        console.log(error);
    });
	
	messageService.subscribe('breadcrumb', function(e, breadcrumb) {
        $scope.breadcrumb = breadcrumb;
    });
}]);

Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}
function messageOper(data){
//	var data = {
//			"orderDate":"2015-09-25",
//			"type":"new",
//			"spaces":[{"spaceId":"41740cc113444143b96c7494569d995b","time":"12:00"},
//			          {"spaceId":"41740cc113444143b96c7494569d995b","time":"12:30"},
//			          {"spaceId":"41740cc113444143b96c7494569d995b","time":"13:00"},
//			          {"spaceId":"41740cc113444143b96c7494569d995b","time":"13:30"}],
//			"statiumId":"015045c16a524e97ab69a44034c9440e"
//			}
	var data = JSON.parse(data);
	if(data.type == "new"){
		$("#orderTip").find("p:eq(0)").html("您有新场地预定");
	}else if(data.type == "cancel"){
		$("#orderTip").find("p:eq(0)").html("您有场地已取消");
	}else if(data.type == "pay"){
		$("#orderTip").find("p:eq(0)").html(data.orderId+"订单已支付");
	}else{
		
	}
	$("#audioBox").html('<audio style="display:none;" autoplay="autoplay" src="static/song/1.mp3" controls="controls"></audio>');
	$("#orderTip").fadeIn();
	var unreadNum = $("#unreadNum").html();
	unreadNum = parseInt(unreadNum,10);
	unreadNum = unreadNum+1;
	$("#unreadNum").html(unreadNum);
	if((window.location.href).indexOf("statiumUse")!=-1||(window.location.href).lastIndexOf("/#/")!=-1){
		var selectDate = $("#selectDate").html();
		if(selectDate == data.orderDate){
			if(data.type == "new"){//新订单未支付
				$.each(data.spaces,function(key,value){
					var spaceId = $("#"+value.spaceId);
					var beginNode = $(spaceId).find("[begin-time-data]");
					$.each(beginNode,function(){
						if($(this).attr("begin-time-data") == value.time){
							$(this).find("a:eq(0)").addClass("bluebot");
						}
					});
				});
			}else if(data.type == "cancel"){//取消订单
				$.each(data.spaces,function(key,value){
					var spaceId = $("#"+value.spaceId);
					var beginNode = $(spaceId).find("[begin-time-data]");
					$.each(beginNode,function(){
						if($(this).attr("begin-time-data") == value.time){
							$(this).find("a:eq(0)").removeClass();
						}
					});
				});
			}else if(data.type == "pay"){//已支付订单
				$.each(data.spaces,function(key,value){
					var spaceId = $("#"+value.spaceId);
					var beginNode = $(spaceId).find("[begin-time-data]");
					$.each(beginNode,function(){
						if($(this).attr("begin-time-data") == value.time){
							$(this).find("a:eq(0)").removeClass();
							$(this).find("a:eq(0)").addClass("yelbot");
						}
					});
				});
			}
		}
	}
}
function intervalExecude(time){
	var nowDate = new Date();
	if((window.location.href).indexOf("statiumUse")!=-1||(window.location.href).lastIndexOf("/#/")!=-1){
		if($("#input_date").val()==nowDate.format("yyyy-MM-dd")){
			var spaceGrounds = $(".spaceGround");
			$.each(spaceGrounds, function(){
				var obj = $(this);
				var childs = obj.children("div[begin-time-data='"+time+"']");
				childs.removeClass("canBook").addClass("cantBook");
				childs.find("a").removeClass("selectIco").removeClass("Booking");
			});
		}
	}
	var nowHour = time.split(":")[0];
	var nowMin = time.split(":")[1];
	if(parseInt(nowMin)<30){
		nowMin = 30;
	}else{
		nowMin = 0;
		nowHour = parseInt(nowHour)+1;
	}
	var dateInterval = new Date();
	dateInterval.setHours(nowHour);
	dateInterval.setMinutes(nowMin);
	dateInterval.setSeconds(0);
	setTimeout('intervalExecude("'+dateInterval.format("hh:mm")+'")',30*60*1000);
}
$(function(){
	var nowDate = new Date();
	var nowHour = nowDate.format("hh");
	var nowMin = nowDate.format("mm");
	if(parseInt(nowMin)<30){
		nowMin = 30;
	}else{
		nowMin = 0;
		nowHour = parseInt(nowHour)+1;
	}
	var dateInterval = new Date();
	dateInterval.setHours(nowHour);
	dateInterval.setMinutes(nowMin);
	dateInterval.setSeconds(0);
	var interval = dateInterval-nowDate;
	setTimeout('intervalExecude("'+dateInterval.format("hh:mm")+'")',interval);
})
