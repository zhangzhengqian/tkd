var memberApp = angular.module('memberApp',['ngRoute','crm.pagination'])
memberApp.factory('MemberService',['$http','$route',function($http,$route,$routeParams){
    return {
    	memberlist:function(pageVo){
	    	return $http.post('/admin/member/list',pageVo,{cache:false});
	    },
	    saveMember:function(member){
        	return $http.post('/admin/member/saveMember',member);
        },
	    loadMember:function(){
            var memberId = $route.current.params.id;
            return $http.post('/admin/member/'+memberId,{cache:false});
        },
        updateMember:function(member){
        	return $http.post('/admin/member/updateMember',member);
        },
        levellist:function(){
        	return $http.post('/admin/memberLevel/list',{cache:false});
        },
        saveMemberLevel:function(level){
        	return $http.post('/admin/memberLevel/saveMemberLevel',level);
        },
        deleteMember:function(id){
        	return $http.post('/admin/member/deleteMember/'+id);
        },
        deleteMemberLevel:function(leverId){
        	return $http.post('/admin/memberLevel/deleteMemberLevel/'+leverId);
        },
        loadLevel:function(){
            var levelId = $route.current.params.id;
            return $http.post('/admin/memberLevel/getLevel/'+leverId,{cache:false});
        },
        charge:function(memberId,chargeMoney,giveMoney,chargeType){
            return $http.post('/admin/member/charge/'+memberId+'/'+chargeMoney+'/'+giveMoney+'/'+chargeType);
        },
        memberAccount:function(pageVo){
        	var memberId = $route.current.params.id;
        	return $http.post('/admin/member/getMemberAccount/'+memberId,pageVo,{cache:false});
        },
        levelTypes:function(){
        	return $http.post('/admin/member/getLevelTypes',{cache:false});
        },
        memberCardNo:function(cardNo){
        	return $http.post('/admin/member/getMemberCardNo/'+cardNo,{cache:false});
        },
        memberPhone:function(phone){
        	return $http.post('/admin/member/getMemberPhone/'+phone,{cache:false});
        },
        importMembers:function(){
        	var path=document.getElementById("#myfile").val();
        	return $http.post('/admin/member/importMembers/'+path);
        }
    }
}]);
//订单类型过滤器
memberApp.filter("orderTypeFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(!input){
    		result = '';
    	}
    	if(input===0){
    		result = '充值';
    	}
    	if(input===1){
    		result = '订场';
    	}
    	if(input===2){
    		result = '消费';
    	}
    	return result;
    };
    return filterfun;
});
//支付类型过滤器
memberApp.filter("payTypeFilter", function() {
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

//会员列表
memberApp.controller('loadMembersController',['$scope','MemberService','messageService','$location',function($scope,MemberService,messageService,$location) {
	messageService.publish('breadcrumb','memberIco');
	$scope.paginationConf = {
			currentPage: 1,
			itemsPerPage: 15,
			numberOfPage:0,
			pagesLength: 15,
			perPageOptions: [10, 20, 30, 40, 50],
			pagePromise:{},
			onChange: function(){
				var memberPromise = MemberService.memberlist({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
				$scope.paginationConf.pagePromise = memberPromise;
			}
	};
	//会员等级类型
	var levelTypePromise = MemberService.levelTypes();
	levelTypePromise.success(function(data){
		$scope.levelNames = data["data"];
	}).error(function(error){
        console.log(error);
    });
	//充值
	$scope.open=function(index){
		$("#chargeModal").modal(index);
		var memberId=$scope.objs[index]['id'];
		$("#memberId").val(memberId);
	}
	saveCharge=function(){
		$("#chargeModal").modal("hide");
		var memberId=$("#memberId").val();
		var chargeMoney=$("#chargeMoney").val();
		var giveMoney=$("#giveMoney").val();
		var chargeType=$("#chargeType").val();
		if(!chargeMoney||chargeMoney=="") chargeMoney="0";
		if(!giveMoney||chargeMoney=="") giveMoney="0";
		var chargePromise = MemberService.charge(memberId,chargeMoney,giveMoney,chargeType);
		$("#chargeMoney").val("");
		$("#giveMoney").val("");
		chargePromise.success(function(data){
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["充值成功！","success"]);
    			$location.path('members');
    		}else{
    			messageService.publish('notifyMessage',["充值失败！","error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["充值失败！","error"]);
        });
	}
	//批量导入会员
	$scope.importMembers=function(){
		$("#importMembersModal").modal();
	}
	downloadTemplate=function(){
		alert();
		window.location.href="/static/file/批量导入会员模板.xls";
	}
	//删除会员
	$scope.deleteMember = function(index){
		swal({
			title: "您确定要删除该会员吗？",
			text: "你将要删除会员!",
			type: "warning",
			showCancelButton: true,
			cancelButtonText:"取消",
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定",
			closeOnConfirm: true
		}, function () {
			var delMemberPromise = MemberService.deleteMember($scope.objs[index]['id']);
	    	delMemberPromise.success(function(data){
	    		if(data["result"]=='success'){
	    			messageService.publish('notifyMessage',["删除会员成功！","success"]);
	    			$scope.objs.splice(index,1);
	    		}else{
	    			messageService.publish('notifyMessage',["删除会员失败！","error"]);
	    		}
	        }).error(function(error){
	        	messageService.publish('notifyMessage',["删除会员失败！","error"]);
	        });
		});
    };
}]);
//新建会员
memberApp.controller('saveMemberController',['$scope','MemberService','messageService','$location',function($scope,MemberService,messageService,$location) {
    messageService.publish('breadcrumb','memberIco');
    $scope.disabled = true;
    $scope.saveMember = function(){
    	if($scope.member.cardNo==undefined || $scope.member.cardNo==''){
    		swal({
                title: "警告",
                text: "卡号不能为空！"
            });
    		return;
    	}
    	if($scope.member.name==undefined || $scope.member.name==''){
    		swal({
                title: "警告",
                text: "姓名不能为空！"
            });
    		return;
    	}
    	if($scope.member.phone==undefined || $scope.member.phone==''){
    		swal({
                title: "警告",
                text: "手机号不能为空！"
            });
    		return;
    	}
    	var cardNoPromise = MemberService.memberCardNo($scope.member.cardNo);
    	cardNoPromise.success(function(data){
    		if(data["result"]=='success'){
    			var phonePromise = MemberService.memberPhone($scope.member.phone);
    	    	phonePromise.success(function(data){
    	    		if(data["result"]=='success'){
    	    			var memberPromise = MemberService.saveMember($scope.member);
    	    	    	memberPromise.success(function(data){
    	    	    		if(data["result"]=='success'){
    	    	    			messageService.publish('notifyMessage',["添加会员成功！","success"]);
    	    	    			$location.path('members');
    	    	    		}else{
    	    	    			messageService.publish('notifyMessage',["添加会员失败！","error"]);
    	    	    		}
    	    	        }).error(function(error){
    	    	        	messageService.publish('notifyMessage',["添加会员失败！","error"]);
    	    	        });
    	    		}else{
    	    			swal({
    	                    title: "警告",
    	                    text: "手机号不能重复！"
    	                });
    	    		}
    	    	});
    		}else{
    			swal({
                    title: "警告",
                    text: "会员卡号不能重复！"
                });
    		}
    	});
    };
}]);
//修改会员信息
memberApp.controller('loadMemberController',['$scope','MemberService','messageService','$location',function($scope,MemberService,messageService,$location) {
    messageService.publish('breadcrumb','memberIco');
    var memberPromise = MemberService.loadMember();
    memberPromise.success(function(member){
        $scope.member = member["data"];
    }).error(function(error){
        console.log(error);
    });
    $scope.disabled = true;
    $scope.updateMember = function(){
    	if($scope.member.cardNo==undefined || $scope.member.cardNo==''){
    		swal({
                title: "警告",
                text: "卡号不能为空！"
            });
    		return;
    	}
    	if($scope.member.name==undefined || $scope.member.name==''){
    		swal({
                title: "警告",
                text: "姓名不能为空！"
            });
    		return;
    	}
    	if($scope.member.phone==undefined || $scope.member.phone==''){
    		swal({
                title: "警告",
                text: "手机号不能为空！"
            });
    		return;
    	}
    	var memberPromise = MemberService.updateMember($scope.member);
    	memberPromise.success(function(data){
    		alert(data["result"]);
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["会员修改成功！","success"]);
    			$location.path('members');
    		}else{
    			messageService.publish('notifyMessage',["会员修改失败！","error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["会员修改失败！","error"]);
        });
    };
}]);
//会员账户明细
memberApp.controller('loadMemberAccountController',['$scope','MemberService','messageService','$location','$route',function($scope,MemberService,messageService,$location,$route) {
	messageService.publish('breadcrumb','memberIco');
	$("#memberId").val($route.current.params.id);
	$("#cardNumber").html($route.current.params.cardNo);
	$scope.paginationConf = {
			currentPage: 1,
			itemsPerPage: 15,
			numberOfPage:0,
			pagesLength: 15,
			perPageOptions: [10, 20, 30, 40, 50],
			pagePromise:{},
			onChange: function(){
				var accountPromise = MemberService.memberAccount({page:$scope.paginationConf.currentPage,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
				console.log(accountPromise);
				$scope.paginationConf.pagePromise = accountPromise;
			}
	};
}]);
//会员等级
memberApp.controller('loadLevelsController',['$scope','MemberService','messageService','$location',function($scope,MemberService,messageService,$location) {
	messageService.publish('breadcrumb','memberIco');
	//会员等级列表
	var memberLevelPromise = MemberService.levellist();
	$scope.level = {};
	memberLevelPromise.success(function(res){
		if(res["result"]=='success'){
			$scope.levels = res['data'];
		}else{
			messageService.publish('notifyMessage',["等级列表加载失败！","error"]);
		}
    }).error(function(error){
    	messageService.publish('notifyMessage',["等级列表加载失败！","error"]);
    });
	$scope.updateLevel = function(index){
		$scope.level = $scope.levels[index];
	}
	//添加会员等级
	$scope.saveMemberLevel = function(){
    	if($scope.level.name==undefined || $scope.level.name==''){
    		swal({
                title: "警告",
                text: "等级名称不能为空！"
            });
    		return;
    	}
    	if($scope.level.chargeAmount==undefined || $scope.level.chargeAmount==''){
    		swal({
                title: "警告",
                text: "充值金额不能为空！"
            });
    		return;
    	}
    	if($scope.level.rebate==undefined || $scope.level.rebate==''){
    		swal({
                title: "警告",
                text: "折扣比例不能为空！（不打折请设置为1）"
            });
    		return;
    	}
    	var levelPromise = MemberService.saveMemberLevel($scope.level);
    	levelPromise.success(function(data){
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["新增/修改等级成功！","success"]);
    			$location.path('memberLevels');
    		}else{
    			messageService.publish('notifyMessage',["新增/修改等级失败！","error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["新增/修改等级失败！","error"]);
        });
    };
    //删除会员等级
	$scope.deleteMemberLevel = function(index){
		swal({
			title: "您确定要删除该会员等级吗？",
			text: "你将要删除会员等级设置!",
			type: "warning",
			showCancelButton: true,
			cancelButtonText:"取消",
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "确定",
			closeOnConfirm: true
		}, function () {
			var levelPromise = MemberService.deleteMemberLevel($scope.levels[index]['id']);
	    	levelPromise.success(function(data){
	    		if(data["result"]=='success'){
	    			messageService.publish('notifyMessage',["删除等级成功！","success"]);
	    			$scope.levels.splice(index,1);
	    		}else{
	    			messageService.publish('notifyMessage',["删除等级失败！","error"]);
	    		}
	        }).error(function(error){
	        	messageService.publish('notifyMessage',["删除等级失败！","error"]);
	        });
		});
    };
}]);