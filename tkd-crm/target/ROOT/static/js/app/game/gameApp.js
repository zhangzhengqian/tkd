var gameApp = angular.module('gameApp',['ngRoute','crm.pagination','crm.drag','crm.ue','crm.gametree','crm.gameview'])

gameApp.factory('GameService',['$http','$route',function($http,$route,$routeParams){
    return {
        loadGames:function(pageVo){
            return $http.post('/admin/game',pageVo,{cache:false});
        },
        initGame:function(){
            return $http.post('/admin/game/init',null,{cache:false});
        },
        saveGame:function(game){
            return $http.post('/admin/game/save',game,{cache:false});
        },
        subGame:function(id){
            return $http.post('/admin/game/submit/'+id,null,{cache:false});
        },
        delGame:function(id){
            return $http.post('/admin/game/delete/'+id,null,{cache:false});
        },
        endSign:function(id,type){
        	return $http.post('/admin/game/endSign/'+id+'/'+type,null,{cache:false});
        },
        getGame:function(id){
        	return $http.post('/admin/game/get/'+id,null,{cache:false});
        },
        initGameSchedule:function(id){
        	return $http.post('/admin/game/initGameSchedule/'+id,null,{cache:false});
        },
        members:function(pageVo){
        	return $http.post('/admin/game/members',pageVo,{cache:false});
        },
        gameRank:function(id){
        	return $http.post('/admin/game/gameRank/'+id,null,{cache:false});
        },
        seeds:function(id){
        	return $http.post('/admin/game/seeds/'+id,null,{cache:false});
        },
        auditMember:function(id,op,remark){
        	return $http.post('/admin/game/audit/'+id+'/'+op+'/'+remark,null,{cache:false});
        },
		remarkMember:function(id,remark){
			return $http.post('/admin/game/remark/'+id+'/'+remark,null,{cache:false});
		},
        setSeed:function(gameId,id,op){
        	return $http.post('/admin/game/setSeed/'+gameId+'/'+id+'/'+op,null,{cache:false});
        },
        setGameSchedule:function(gameSchedules){
        	return $http.post('/admin/game/setGameSchedule/',gameSchedules,{cache:false});
        },
        setSeedSort:function(seeds){
        	return $http.post('/admin/game/setSeedSort/',seeds,{cache:false});
        },
        saveOutSchedule:function(gameSchedule,gameId){
        	return $http.post('/admin/game/saveOutSchedule/'+gameId,gameSchedule,{cache:false});
        },
        saveLoopSchedule:function(group,gameSchedule,gameId){
        	return $http.post('/admin/game/saveLoopSchedule/'+gameId,{"groupsView":group,"fields":gameSchedule},{cache:false});
        },
        saveSchedule:function(gameSchedule,gameId,gameSchedules){
        	gameSchedule["games"] = gameSchedules;
        	return $http.post('/admin/game/saveSchedule/'+gameId,{"gameSchedule":gameSchedule},{cache:false});
        },
        saveGroupSchedule:function(group,gameSchedule,gameId){
        	return $http.post('/admin/game/saveGroupSchedule/'+gameId,{"groupsView":group,"fields":gameSchedule},{cache:false});
        },
        getSchedule:function(gameId){
        	return $http.post('/admin/game/getSchedule/'+gameId,null,{cache:false});
        },
        getScheduleByType:function(gameId){
        	return $http.post('/admin/game/getScheduleByType/'+gameId,null,{cache:false});
        },
        getSchedules:function(gameId){
        	return $http.post('/admin/game/getSchedules/'+gameId,null,{cache:false});
        },
        getScheduleBy:function(gameId,type){
	    	return $http.post('/admin/game/getScheduleBy/'+gameId+'/'+type,null,{cache:false});
	    },
	    saveGroupScore:function(schedule){
	    	return $http.post('/admin/game/saveGroupScore/',schedule,{cache:false});
	    },
	    saveAllGroupScore:function(schedule){
	    	return $http.post('/admin/game/saveAllGroupScore/',schedule,{cache:false});
	    },
	    beginOut:function(gameId,drawResult){
	    	return $http.post('/admin/game/beginOut/'+gameId,drawResult,{cache:false});
	    },
	    subGamePoint:function(gameId){
	    	return $http.post('/admin/game/subGamePoint/'+gameId,null,{cache:false});
	    },
	    enterOutScore:function(outScore){
	    	return $http.post('/admin/game/enterOutScore/',outScore,{cache:false});
	    }
    }
}]);

gameApp.filter("typeFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(input==1){
    		result = '男子单打';
    	}else if(input==2){
    		result = '女子单打';
    	}else if(input==3){
    		result = '男子双打';
    	}else if(input==4){
    		result = '女子双打';
    	}else if(input==6){
    		result = '混合单打';
    	}else if(input==5){
    		result = '混合双打';
    	}else if(input==7){
    		result = '无性别限制双打';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.filter("formatFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(input==3){
    		result = '单循环';
    	}else if(input==1){
    		result = '循环+淘汰';
    	}else if(input==2){
    		result = '单淘汰';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.filter("stateFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(input===undefined||input===null||input===''){
    		result = '未提交';
    	}else if(input===0){
    		result = '待审核';
    	}else if(input===1){
    		result = '通过';
    	}else if(input===2){
    		result = '未通过';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.filter("memberStateFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(input===0){
    		result = '未审核';
    	}else if(input===1){
    		result = '通过';
    	}else if(input===2){
    		result = '拒绝';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.filter("levelFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(!input){
    		result = '';
    	}
    	if(input===1){
    		result = '乐享一级赛';
    	}
    	if(input===2){
    		result = '乐享二级赛';
    	}
    	if(input===3){
    		result = '乐享三级赛';
    	}
    	if(input===4){
    		result = '乐享四级赛';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.filter("seedFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(input===0){
    		result = '非种子';
    	}else if(input===1){
    		result = '种子';
    	}else{
    		result = '非种子';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.filter("seedNumFilter", function() {
    var filterfun = function(input) {
    	var result = '';
    	if(input===undefined||input===0){
    		result = '';
    	}else {
    		result = input+'号种子';
    	}
    	return result;
    };
    return filterfun;
});

gameApp.controller('loadGamesController',['$scope','GameService','messageService','$location',function($scope,GameService,messageService,$location) {
	messageService.publish('breadcrumb',"CTAgameIco");
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
            var gamePromise = GameService.loadGames({page:$scope.paginationConf.currentPage-1,size:$scope.paginationConf.itemsPerPage});
            $scope.paginationConf.pagePromise = gamePromise;
        }
    };
    //提交赛事
    $scope.subGame = function(index){
    	console.log($scope.objs[index]);
    	var game = $scope.objs[index];
    	swal({
            title: "确定要提交赛事信息吗?",
            text: "你将要提交赛事信息!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var subPromise = GameService.subGame(game["id"]);
        	subPromise.success(function(data){
        		if(data["result"]=="success"){
        			messageService.publish('notifyMessage',["赛事提交成功！","success"]);
        			$location.path('game');
        		}else{
        			messageService.publish('notifyMessage',["赛事提交失败！","error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["赛事提交失败！","error"]);
            });
        });
    }
    
  //删除赛事
    $scope.delGame = function(index){
    	console.log($scope.objs[index]);
    	var game = $scope.objs[index];
    	swal({
            title: "确定要删除赛事信息吗?",
            text: "你将要删除赛事信息!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var subPromise = GameService.delGame(game["id"]);
        	subPromise.success(function(data){
        		if(data["result"]=="success"){
        			messageService.publish('notifyMessage',["赛事删除成功！","success"]);
        			$location.path('game');
        		}else{
        			messageService.publish('notifyMessage',["赛事删除失败！","error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["赛事删除失败！","error"]);
            });
        });
    }
    //结束报名
    $scope.endSign = function(index,type){
    	console.log($scope.objs[index]);
    	var game = $scope.objs[index];
    	var tip = type==0?"恢复":"暂停";
    	swal({
    		title: "确定要"+tip+"报名吗?",
    		text: "你将要"+tip+"报名!",
    		type: "warning",
    		showCancelButton: true,
    		cancelButtonText:"取消",
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "确定",
    		closeOnConfirm: true
    	}, function () {
    		var subPromise = GameService.endSign(game["id"],type);
    		subPromise.success(function(data){
    			if(data["result"]=="success"){
    				messageService.publish('notifyMessage',["赛事"+tip+"报名成功！","success"]);
    				$location.path('game');
    			}else{
    				messageService.publish('notifyMessage',["赛事"+tip+"报名失败！","error"]);
    			}
    		}).error(function(error){
    			messageService.publish('notifyMessage',["赛事"+tip+"报名失败！","error"]);
    		});
    	});
    }
    
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

gameApp.controller('gameMembersController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 128,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
        	$scope.searcher = {};
        	$scope.searcher["gameId"] = gameId;
            var gamePromise = GameService.members({page:$scope.paginationConf.currentPage-1,size:$scope.paginationConf.itemsPerPage,searcherJson:JSON.stringify($scope.searcher)});
            $scope.paginationConf.pagePromise = gamePromise;
        }
    };
    $scope.auditMember = function(index){
    	console.log($scope.objs[index]);
    	var member = $scope.objs[index];
    	swal({
            title: "确定要拒绝报名吗?",
            text: "请输入拒绝原因!",
            type: "input",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: false
        }, function (inputValue) {
        	if(inputValue===false){
        		return false;
        	}
        	if(inputValue===""){
        		swal({
                    title: "警告",
                    text: "请输入拒绝原因！"
                });
        		return false;
        	}
        	if(inputValue.length>25){
        		swal({
                    title: "警告",
                    text: "字数请少于25个！"
                });
        		return false;
        	}
        	var subPromise = GameService.auditMember(member["id"],2,encodeURI(encodeURI(inputValue)));
        	subPromise.success(function(data){
        		if(data["result"]=="success"){
        			swal({
                        title: "操作成功",
                    });
        			$location.path('gameMembers/'+gameId);
        		}else{
        			swal({
                        title: "操作失败",
                    });
        		}
            }).error(function(error){
            	swal({
                    title: "操作失败",
                });
            });
        });
    }
    
    $scope.remarkMember = function(index){
    	console.log($scope.objs[index]);
    	var member = $scope.objs[index];
    	swal({
            title: "确定要修改原因吗?",
            text: "请输入拒绝原因!",
            type: "input",
            inputValue:member.remark,
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: false
        }, function (inputValue) {
        	if(inputValue===false){
        		return false;
        	}
        	if(inputValue===""){
        		swal({
                    title: "警告",
                    text: "请输入拒绝原因！"
                });
        		return false;
        	}
        	if(inputValue.length>25){
        		swal({
                    title: "警告",
                    text: "字数请少于25个！"
                });
        		return false;
        	}
        	var subPromise = GameService.remarkMember(member["id"],encodeURI(encodeURI(inputValue)));
        	subPromise.success(function(data){
        		if(data["result"]=="success"){
        			swal({
                        title: "操作成功",
                    });
        			$location.path('gameMembers/'+gameId);
        		}else{
        			swal({
                        title: "操作失败",
                    });
        		}
            }).error(function(error){
            	swal({
                    title: "操作失败",
                });
            });
        });
    }
    
    $scope.setSeed = function(index,type){
    	console.log($scope.objs[index]);
    	var member = $scope.objs[index];
    	var text = type==0?"取消种子选手":"设置种子选手";
    	swal({
            title: "确定要"+text+"吗?",
            text: "你将要"+text+"!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
            	var subPromise = GameService.setSeed(gameId,member["id"],type);
            	subPromise.success(function(data){
            		if(data["result"]=="success"){
            			messageService.publish('notifyMessage',["设置成功！","success"]);
            			$location.path('gameMembers/'+gameId);
            		}else{
            			messageService.publish('notifyMessage',[data["data"],"error"]);
            		}
                }).error(function(error){
                	messageService.publish('notifyMessage',["设置失败！","error"]);
                });
        });
    }
    
    $scope.setGameSchedule = function(){
    	var seedNum = $scope.searcher.memberStatic["seed"];
    	var subNum = $scope.searcher.memberStatic["subed"];
    	if(subNum<8){
    		swal({
                title: "警告",
                text: "通过人数必须大于8人！"
            });
    		return;
    	}
    	if(seedNum!=0&&(seedNum==1||parseInt(Math.log(seedNum)/Math.log(2),10)!=Math.log(seedNum)/Math.log(2))){
    		swal({
                title: "警告",
                text: "种子数必须是2的指数！"
            });
    	}else{
    		$location.path('setGameSchedule/'+gameId);
    	}
    }
}]);

gameApp.controller('orderSeedsController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
	var promise = GameService.seeds(gameId);
	$scope.gameId = gameId;
	promise.success(function(res){
		if(res["result"]=="success"){
			$scope.seeds = res["data"]["members"];
			$scope.doubleFlag = res["data"]["doubleFlag"];
		}else{
			messageService.publish('notifyMessage',["获取种子选手失败！","error"]);
		}
    }).error(function(error){
    	messageService.publish('notifyMessage',["获取种子选手失败！","error"]);
    });
	$scope.setSeedSort = function(){
		swal({
            title: "确定要设定种子排位吗?",
            text: "你将要设定种子排位!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var sortPromise = GameService.setSeedSort($scope.seeds);
        	sortPromise.success(function(data){
        		if(data["result"]=="success"){
        			messageService.publish('notifyMessage',["种子排位设定成功！","success"]);
        			$location.path('gameMembers/'+gameId);
        		}else{
        			messageService.publish('notifyMessage',["种子排位设定失败！","error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["种子排位设定失败！","error"]);
            });
        });
	}
}]);

gameApp.controller('gameRankController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
	var promise = GameService.gameRank(gameId);
	promise.success(function(res){
		if(res["result"]=="success"){
			$scope.members = res["data"];
		}else{
			messageService.publish('notifyMessage',["获取选手排名失败！","error"]);
		}
    }).error(function(error){
    	messageService.publish('notifyMessage',["获取选手排名失败！","error"]);
    });
}]);

gameApp.controller('inputScoreController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
	var promise = GameService.getSchedule(gameId);
	var availableScoreFor4 = ["4:0","4:1","4:2","0:4","1:4","2:4","4:3","3:4"];
	var availableScoreFor6 = ["6:0","6:1","6:2","6:3","6:4","0:6","1:6","2:6","3:6","4:6","7:5","5:7","7:6","6:7"];
	$scope.searchType = 1;
	var gameFormat1 = 0;
	var end = false;
	var isScoreNotice = 0;
	promise.success(function(res){
		if(res["result"]=='success'){
			var schedule = res["data"];
			$scope.schedule = schedule;
			var group_temp = new EJS({url: 'static/js/app/game/groupSchedule.ejs?ver=1.7'});
			var group_html = group_temp.render({schedule:schedule});
			$("#groupSchedule").html(group_html);
			$("#groupSchedule table td").css({"width":"120px","height":"40px"})
			gameFormat1 = schedule["gameFormat1"];
			end = schedule["end"];
			isScoreNotice = schedule["isScoreNotice"];
			if(schedule.gameFormat==2||(schedule.gameFormat==1&&schedule.gameState==2)){
				var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.2'});
				$(".gameTree").gameTree(temp,schedule["tree"]);
				gameTreeForCanvas(schedule["tree"]);
				var treeNodeLength = $(".gameTree").find("li:eq(0)").attr("turn");
				var turn1 = Math.pow(2,treeNodeLength-1);
				$(".gameTree").css("width",turn1*250+"px")
				saveOutScore = function(id){
					var curNode = $("#"+id);
					var lid = curNode.attr("lid");
					var rid = curNode.attr("rid");
					var lscore = curNode.attr("lscore");
					var rscore = curNode.attr("rscore");
					var gameTime = curNode.attr("gameTime");
					var smallscore = curNode.attr("smallscore");
					var scoreTable = $("#outScore");
					var name1 = $("#"+lid).children("a").text();
					var name2 = $("#"+rid).children("a").text();
					if(name1=="?"||name2=="?"||name1=='轮空'||name2=='轮空'){
						return;
					}
					scoreTable.find("span:eq(0)").html(name1);
					if(smallscore){
						scoreTable.find("span:eq(4)").html(name2+'<a style="margin-left:5px;color:red">('+smallscore+')</a>');
					}else{
						scoreTable.find("span:eq(4)").html(name2);
					}
					scoreTable.find("span:eq(1) input").val(lscore);
					scoreTable.find("span:eq(3) input").val(rscore);
					$("#nodeId").val(id);
					if(end||isScoreNotice!=0){
						$("#enterScoreBtn").hide();
					}
					$("#lnodeId").val($("#"+lid).attr("winner"));
					$("#rnodeId").val($("#"+rid).attr("winner"));
					$("#outScoreInput").modal();
					$("body").css('padding-right','0px');
				}
				enterScore = function(){
					var scoreTable = $("#outScore");
					var score1 = scoreTable.find("span:eq(1) input").val();
					var score2 = scoreTable.find("span:eq(3) input").val();
					var smallscore = scoreTable.find("span:eq(4) a").text();
					var id = $("#nodeId").val();
					var lid = $("#lnodeId").val();
					var rid = $("#rnodeId").val();
					var winner = "";
					if(gameFormat1===0){
						if(availableScoreFor4.indexOf(score1+":"+score2)==-1){
							swal({
				                title: "警告",
				                text: "比分不符合4局制！"
				            });
				    		return;
						}
					}else{
						if(availableScoreFor6.indexOf(score1+":"+score2)==-1){
							swal({
				                title: "警告",
				                text: "比分不符合6局制！"
				            });
				    		return;
						}
					}
					if(score1>score2){
						winner = lid;
					}else{
						winner = rid;
					}
					if("4:3".indexOf(score1+":"+score2)!=-1||"3:4".indexOf(score1+":"+score2)!=-1
							||"7:6".indexOf(score1+":"+score2)!=-1||"6:7".indexOf(score1+":"+score2)!=-1){
						$("#outScoreInput").modal("hide");
						swal({
				            title: "提示",
				            text: "请输入抢七分数!",
				            type: "input",
				            inputValue:smallscore.replace(/[(|)]/g,''),
				            showCancelButton: true,
				            cancelButtonText:"取消",
				            confirmButtonColor: "#DD6B55",
				            confirmButtonText: "确定",
				            closeOnConfirm: false
				        }, function (inputValue) {
				        	if(inputValue===false){
				        		
				        	}else{
				        		if(inputValue==''){
				        			swal({
				    	                title: "警告",
				    	                text: "请填写抢七分！"
				    	            });
					        	}else{
					        		var outScore = {"winner":winner,"lscore":score1,"rscore":score2,"id":id,"gameId":gameId,"smallscore":inputValue};
									var outScoreSave = GameService.enterOutScore(outScore);
									outScoreSave.success(function(res){
										if(res["result"]=="success"){
											swal({
						    	                title: "警告",
						    	                text: "保存比分成功！"
						    	            });
											console.log(res["data"]);
											var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.3'});
											$(".gameTree").gameTree(temp,res["data"]);
											gameTreeForCanvas(res["data"]);
											var treeNodeLength = $(".gameTree").find("li:eq(0)").attr("turn");
											var turn1 = Math.pow(2,treeNodeLength-1);
											$(".gameTree").css("width",turn1*250+"px")
										}else{
											swal({
						    	                title: "警告",
						    	                text: "保存比分失败！"
						    	            });
										}

									}).error(function(){
										swal({
					    	                title: "警告",
					    	                text: "保存比分失败！"
					    	            });
									})
					        	}
				        	}
				        });
					}else{
						var outScore = {"winner":winner,"lscore":score1,"rscore":score2,"id":id,"gameId":gameId};
						var outScoreSave = GameService.enterOutScore(outScore);
						outScoreSave.success(function(res){
							if(res["result"]=="success"){
								messageService.publish('notifyMessage',["保存比分成功！","success"]);
								var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.1'});
								$(".gameTree").gameTree(temp,res["data"]);
								gameTreeForCanvas(res["data"]);
								var treeNodeLength = $(".gameTree").find("li:eq(0)").attr("turn");
								var turn1 = Math.pow(2,treeNodeLength-1);
								$(".gameTree").css("width",turn1*250+"px");
								$("#outScoreInput").modal("hide");
							}else{
								messageService.publish('notifyMessage',["保存比分失败！","error"]);
							}

						}).error(function(){
							messageService.publish('notifyMessage',["保存比分失败！","error"]);
						})
					}
				}
			}
		}else{
			messageService.publish('notifyMessage',["赛程加载失败！","error"]);
		}
    }).error(function(error){
    	messageService.publish('notifyMessage',["赛程加载失败！","error"]);
    });
	$scope.getScheduleBy = function(type){
		var promise_ = GameService.getScheduleBy(gameId,type);
		promise_.success(function(res){
			if(res["result"]=='success'){
				$scope.searchType = type;
				$("#searchSch"+type).addClass("active");
				$("#searchSch"+type).siblings().removeClass("active");
				$scope.schedule = res["data"];
				if(type==2){
					$scope.schedule["fields"] = $scope.schedule["fieldsGroup"];
				}
			}else{
				messageService.publish('notifyMessage',["赛程加载失败！","error"]);
			}
	    }).error(function(error){
	    	messageService.publish('notifyMessage',["赛程加载失败！","error"]);
	    });
	}
	$scope.checkScore = function(field,index){
		var schedule = $scope.schedule["fields"][field][index];
		if(schedule.score1===undefined || schedule.score1===''){
			$("#"+schedule.clasli1+schedule.turn).focus();
    		return;
    	}
		if(schedule.score2===undefined || schedule.score2===''){
			$("#"+schedule.clasli2+schedule.turn).focus();
    		return;
    	}
		if(gameFormat1===0){
			if(availableScoreFor4.indexOf(schedule.score1+":"+schedule.score2)==-1){
				swal({
	                title: "警告",
	                text: "比分不符合4局制！"
	            },function(){
	            	$("#"+schedule.clasli1+schedule.turn).focus();
	            });
	    		return;
			}
		}else{
			if(availableScoreFor6.indexOf(schedule.score1+":"+schedule.score2)==-1){
				swal({
	                title: "警告",
	                text: "比分不符合6局制！"
	            },function(){
	            	$("#"+schedule.clasli1+schedule.turn).focus();
	            });
	    		return;
			}
		}
		if("4:3".indexOf(schedule.score1+":"+schedule.score2)!=-1||"3:4".indexOf(schedule.score1+":"+schedule.score2)!=-1
				||"7:6".indexOf(schedule.score1+":"+schedule.score2)!=-1||"6:7".indexOf(schedule.score1+":"+schedule.score2)!=-1){
			swal({
	            title: "提示",
	            text: "请输入抢七分数!",
	            type: "input",
	            inputValue:$scope.schedule["fields"][field][index]["smallscore"],
	            showCancelButton: true,
	            cancelButtonText:"取消",
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function (inputValue) {
	        	if(inputValue===false){
	        		
	        	}else{
	        		if(inputValue==''){
	        			swal({
	    	                title: "警告",
	    	                text: "请填写抢七分！"
	    	            },function(){
	    	            	$("#"+schedule.clasli2+schedule.turn).focus();
	    	            });
		        	}else{
		        		schedule["smallscore"] = inputValue;
		        		$scope.schedule["fields"][field][index]["smallscore"]=inputValue;
		        		var savePromise = GameService.saveGroupScore(schedule);
		        		savePromise.success(function(res){
		            		if(res["result"]=="success"){
		            			swal({
		                            title: "保存比分成功！",
		                        });
		            			$scope.schedule["groupsView"] = res["data"];
		            		}else{
		            			swal({
		                            title: "保存比分失败！",
		                        });
		            		}
		                }).error(function(error){
		                	swal({
	                            title: "保存比分失败！",
	                        });
		                });
		        	}
	        	}
	        });
		}else{
			schedule["smallscore"] = null;
    		$scope.schedule["fields"][field][index]["smallscore"]=null;
    		var savePromise = GameService.saveGroupScore(schedule);
    		savePromise.success(function(res){
        		if(res["result"]=="success"){
        			messageService.publish('notifyMessage',["保存比分成功！","success"]);
        			$scope.schedule["groupsView"] = res["data"];
        		}else{
        			messageService.publish('notifyMessage',["保存比分失败！","error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["保存比分失败！","error"]);
            });
		}
	}
	$scope.batchSaveScore = function(){
		var schedules = $scope.schedule["fields"];
		var tipTable = {};
		var breakFlag = false;
		$.each(schedules,function(key1,item){
			$.each(item,function(key2,schedule){
				if(schedule.score1===undefined || schedule.score1===''){
					tipTable = {tip:"请输入比分！",field:key1,dz:schedule.p1+"vs"+schedule.p2,focus:schedule.clasli1+schedule.turn};
					breakFlag = true;
		    		return false;
		    	}
				if(schedule.score2===undefined || schedule.score2===''){
					tipTable = {tip:"请输入比分！",field:key1,dz:schedule.p1+"vs"+schedule.p2,focus:schedule.clasli2+schedule.turn};
					breakFlag = true;
		    		return false;
		    	}
				if(gameFormat1===0){
					if(availableScoreFor4.indexOf(schedule.score1+":"+schedule.score2)==-1){
						tipTable = {tip:"比分不符合4局制！",field:key1,dz:schedule.p1+"vs"+schedule.p2,focus:schedule.clasli1+schedule.turn};
						breakFlag = true;
			    		return false;
					}
				}else{
					if(availableScoreFor6.indexOf(schedule.score1+":"+schedule.score2)==-1){
						tipTable = {tip:"比分不符合6局制！",field:key1,dz:schedule.p1+"vs"+schedule.p2,focus:schedule.clasli1+schedule.turn};
						breakFlag = true;
			    		return false;
					}
				}
				if("4:3".indexOf(schedule.score1+":"+schedule.score2)!=-1||"3:4".indexOf(schedule.score1+":"+schedule.score2)!=-1
						||"7:6".indexOf(schedule.score1+":"+schedule.score2)!=-1||"6:7".indexOf(schedule.score1+":"+schedule.score2)!=-1){
					if(!schedule.smallscore){
						tipTable = {tip:"抢七必须录入小分！",field:key1,dz:schedule.p1+"vs"+schedule.p2,focus:schedule.clasli1+schedule.turn};
						breakFlag = true;
			    		return false;
					}
				}
			})
			if(breakFlag){
				return false;
			}
		})
		if(breakFlag){
			if($scope.searchType==1){
				swal({
		            title: "警告",
		            text: tipTable["field"]+"号场地:"+tipTable["dz"]+":"+tipTable["tip"]
		        });
			}else{
				swal({
		            title: "警告",
		            text: tipTable["field"]+"组:"+tipTable["dz"]+":"+tipTable["tip"]
		        });
			}
		}else{
			var savePromise = GameService.saveAllGroupScore($scope.schedule["fields"]);
			savePromise.success(function(res){
	    		if(res["result"]=="success"){
	    			messageService.publish('notifyMessage',["保存比分成功！","success"]);
	    			$scope.schedule["groupScheduleList"] = res["data"];
	    		}else{
	    			messageService.publish('notifyMessage',["保存比分失败！","error"]);
	    		}
	        }).error(function(error){
	        	messageService.publish('notifyMessage',["保存比分失败！","error"]);
	        });
		}
	}
	$scope.saveScore = function(field,index){
		var schedule = $scope.schedule["fields"][field][index];
		if(schedule.score1===undefined || schedule.score1===''){
    		swal({
                title: "警告",
                text: "请输入比分！"
            },function(){
            	$("#"+schedule.clasli1+schedule.turn).focus();
            });
    		return;
    	}
		if(schedule.score2===undefined || schedule.score2===''){
    		swal({
                title: "警告",
                text: "请输入比分！"
            },function(){
            	$("#"+schedule.clasli2+schedule.turn).focus();
            });
    		return;
    	}
		if(gameFormat1===0){
			if(availableScoreFor4.indexOf(schedule.score1+":"+schedule.score2)==-1){
				swal({
	                title: "警告",
	                text: "比分不符合4局制！"
	            },function(){
	            	$("#"+schedule.clasli1+schedule.turn).focus();
	            });
	    		return;
			}
		}else{
			if(availableScoreFor6.indexOf(schedule.score1+":"+schedule.score2)==-1){
				swal({
	                title: "警告",
	                text: "比分不符合6局制！"
	            },function(){
	            	$("#"+schedule.clasli1+schedule.turn).focus();
	            });
	    		return;
			}
		}
		if("4:3".indexOf(schedule.score1+":"+schedule.score2)!=-1||"3:4".indexOf(schedule.score1+":"+schedule.score2)!=-1
				||"7:6".indexOf(schedule.score1+":"+schedule.score2)!=-1||"6:7".indexOf(schedule.score1+":"+schedule.score2)!=-1){
			swal({
	            title: "提示",
	            text: "请输入抢七分数!",
	            type: "input",
	            inputValue:$scope.schedule["fields"][field][index]["smallscore"],
	            showCancelButton: true,
	            cancelButtonText:"取消",
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function (inputValue) {
	        	if(inputValue===false){
	        		
	        	}else{
	        		if(inputValue==''){
	        			swal({
	    	                title: "警告",
	    	                text: "请填写抢七分！"
	    	            },function(){
	    	            	$("#"+schedule.clasli2+schedule.turn).focus();
	    	            });
		        	}else{
		        		schedule["smallscore"] = inputValue;
		        		$scope.schedule["fields"][field][index]["smallscore"]=inputValue;
		        		var savePromise = GameService.saveGroupScore(schedule);
		        		savePromise.success(function(res){
		            		if(res["result"]=="success"){
		            			swal({
		                            title: "保存比分成功！",
		                        });
		            			$scope.schedule["groupsView"] = res["data"];
		            		}else{
		            			swal({
		                            title: "保存比分失败！",
		                        });
		            		}
		                }).error(function(error){
		                	swal({
	                            title: "保存比分失败！",
	                        });
		                });
		        	}
	        	}
	        });
		}else{
			schedule["smallscore"] = null;
    		$scope.schedule["fields"][field][index]["smallscore"]=null;
    		var savePromise = GameService.saveGroupScore(schedule);
    		savePromise.success(function(res){
        		if(res["result"]=="success"){
        			messageService.publish('notifyMessage',["保存比分成功！","success"]);
        			$scope.schedule["groupsView"] = res["data"];
        		}else{
        			messageService.publish('notifyMessage',["保存比分失败！","error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["保存比分失败！","error"]);
            });
		}
	}
	var drawResult = {};
	$scope.beginOutGame = function(){
		var outPromise = GameService.beginOut(gameId,drawResult);
		outPromise.success(function(res){
    		if(res["result"]=="success"){
    			if(res["data"]["sameScores"]!=null){
    				var sameScores = res["data"]["sameScores"];
    				var drawPlaysHtml="";
    				angular.forEach(sameScores,function(item,key){
    					var drawPlayHtml = '<dd>';
        				angular.forEach(item,function(item_,key_){
        					var tip = "";
        					if(key_=='2'){
        						tip = "抽第二名";
        					}else{
        						tip = "抽一、二名";
        					}
        					drawPlayHtml = drawPlayHtml+ '<span class="group_name" draw_type="'+key_+'" group_id="'+key+'">'+key+'组('+tip+')</span>';
        					angular.forEach(item_,function(it){
        						drawPlayHtml = drawPlayHtml+ '<span class="drawPlayer" member_id="'+it["memberId"]+'"><button draw_type="'+key_+'" class="btn">'+it["name"]+'</button></span>';
        					});
        				})
    					drawPlayHtml = drawPlayHtml+ '</dd>';
        				drawPlaysHtml = drawPlaysHtml+drawPlayHtml;
    				});
    				$(".drawPlayers dl").html(drawPlaysHtml);
    				$(".drawPlayer .btn").on("click",function(){
    					if($(this).attr("draw_type")=='12'){
    						if($(this).hasClass("active")){
        						$(this).find("i").remove();
        						var i = $(this).closest("dd").find("i");
        						if(i.size()==1&&i.text()=="2"){
        							i.text("1");
        						}
        					}else{
        						var i = $(this).closest("dd").find("i");
        						if(i.size()==0){
        							$(this).append('<i class="badge pull-right" style="margin-right:-10px;">1</i>');
        						}else if(i.size()==1){
        							$(this).append('<i class="badge pull-right" style="margin-right:-10px;">2</i>');
        						}else{
        							$(this).append('<i class="badge pull-right" style="margin-right:-10px;">1</i>');
        							$(this).parent().siblings().find("i").remove();
        							$(this).parent().siblings().find("button").removeClass("active");
        						}
        					}
    					}
    					$(this).toggleClass("active");
    				})
    				$("#drawModal").modal();
					$("body").css('padding-right','0px');
					drawPlayer = function(){
						var flag_ = true;
						$(".drawPlayers dl").children("dd").each(function(index,item){
							var draw_type = $(item).children(".group_name").attr("draw_type");
							var selecSize = $(item).find(".active").size();
							if(selecSize==0){
								swal({
					                title: "警告",
					                text: "请抽签！"
					            });
								flag_ = false;
								return false;
							}
							if(draw_type=='2'){
								if(selecSize!=1){
									swal({
						                title: "警告",
						                text: "抽第二名时只能选择一个！"
						            });
									flag_ = false;
									return false;
								}
							}else{
								if(selecSize!=2){
									swal({
						                title: "警告",
						                text: "抽第一、二名时必须选择两个！"
						            });
									flag_ = false;
									return false;
								}
							}
							if(flag_){
								var groupId = $(item).children(".group_name").attr("group_id");
								var member_id = '';
								if(draw_type=='2'){
									member_id = $(item).find(".active").parent().attr("member_id");
								}else{
									var member_ids = new Array(2);
									$(item).find(".active").each(function(index,obj){
										if($(obj).find("i").text()=='1'){
											member_ids[0] = $(obj).parent().attr("member_id");
										}else{
											member_ids[1] = $(obj).parent().attr("member_id");
										}
									});
									member_id = member_ids.join();
								}
								drawResult[groupId] = {"draw_type":draw_type,"member_id":member_id};
							}
						});
						if(flag_){
							$("#drawModal").modal("hide");
							$scope.beginOutGame();
						}
					}
    				return;
    			}
    			$scope.schedule.gameState=2;
    			var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.1'});
				$(".gameTree").gameTree(temp,res["data"]);
				gameTreeForCanvas(res["data"]);
				var treeNodeLength = $(".gameTree").find("li:eq(0)").attr("turn");
				var turn1 = Math.pow(2,treeNodeLength-1);
				$(".gameTree").css("width",turn1*250+"px")
				saveOutScore = function(id){
					var curNode = $("#"+id);
					var lid = curNode.attr("lid");
					var rid = curNode.attr("rid");
					var lscore = curNode.attr("lscore");
					var rscore = curNode.attr("rscore");
					var gameTime = curNode.attr("gameTime");
					var smallscore = curNode.attr("smallscore");
					var scoreTable = $("#outScore");
					var name1 = $("#"+lid).children("a").text();
					var name2 = $("#"+rid).children("a").text();
					if(name1=="?"||name2=="?"||name1=='轮空'||name2=='轮空'){
						return;
					}
					if(smallscore){
						scoreTable.find("span:eq(4)").html(name2+'<a style="margin-left:5px;color:red">('+smallscore+')</a>');
					}else{
						scoreTable.find("span:eq(4)").html(name2);
					}
					scoreTable.find("span:eq(1)").html(name1);
					scoreTable.find("span:eq(5)").html(name2);
					scoreTable.find("span:eq(0) input").val(gameTime==undefined?"":gameTime);
					scoreTable.find("span:eq(2) input").val(lscore);
					scoreTable.find("span:eq(4) input").val(rscore);
					$("#nodeId").val(id);
					$("#lnodeId").val($("#"+lid).attr("winner"));
					$("#rnodeId").val($("#"+rid).attr("winner"));
					$("#outScoreInput").modal();
					$("body").css('padding-right','0px');
				}
				enterScore = function(){
					var scoreTable = $("#outScore");
					var score1 = scoreTable.find("span:eq(1) input").val();
					var score2 = scoreTable.find("span:eq(3) input").val();
					var id = $("#nodeId").val();
					var lid = $("#lnodeId").val();
					var rid = $("#rnodeId").val();
					var winner = "";
					if(gameFormat1===0){
						if(availableScoreFor4.indexOf(score1+":"+score2)==-1){
							swal({
				                title: "警告",
				                text: "比分不符合4局制！"
				            });
				    		return;
						}
					}else{
						if(availableScoreFor6.indexOf(score1+":"+score2)==-1){
							swal({
				                title: "警告",
				                text: "比分不符合6局制！"
				            });
				    		return;
						}
					}
					if(score1>score2){
						winner = lid;
					}else{
						winner = rid;
					}
					if("4:3".indexOf(score1+":"+score2)!=-1||"3:4".indexOf(score1+":"+score2)!=-1
							||"7:6".indexOf(score1+":"+score2)!=-1||"6:7".indexOf(score1+":"+score2)!=-1){
						$("#outScoreInput").modal("hide");
						swal({
				            title: "提示",
				            text: "请输入抢七分数!",
				            type: "input",
				            inputValue:smallscore.replace(/[(|)]/g,''),
				            showCancelButton: true,
				            cancelButtonText:"取消",
				            confirmButtonColor: "#DD6B55",
				            confirmButtonText: "确定",
				            closeOnConfirm: false
				        }, function (inputValue) {
				        	if(inputValue===false){
				        		
				        	}else{
				        		if(inputValue==''){
				        			swal({
				    	                title: "警告",
				    	                text: "请填写抢七分！"
				    	            });
					        	}else{
					        		var outScore = {"winner":winner,"lscore":score1,"rscore":score2,"id":id,"gameId":gameId,"smallscore":inputValue};
									var outScoreSave = GameService.enterOutScore(outScore);
									outScoreSave.success(function(res){
										if(res["result"]=="success"){
											swal({
						    	                title: "警告",
						    	                text: "保存比分成功！"
						    	            });
											var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.3'});
											$(".gameTree").gameTree(temp,res["data"]);
											gameTreeForCanvas(res["data"]);
											var treeNodeLength = $(".gameTree").find("li:eq(0)").attr("turn");
											var turn1 = Math.pow(2,treeNodeLength-1);
											$(".gameTree").css("width",turn1*250+"px")
										}else{
											swal({
						    	                title: "警告",
						    	                text: "保存比分失败！"
						    	            });
										}

									}).error(function(){
										swal({
					    	                title: "警告",
					    	                text: "保存比分失败！"
					    	            });
									})
					        	}
				        	}
				        });
					}else{
						var outScore = {"winner":winner,"lscore":score1,"rscore":score2,"id":id,"gameId":gameId};
						var outScoreSave = GameService.enterOutScore(outScore);
						outScoreSave.success(function(res){
							if(res["result"]=="success"){
								messageService.publish('notifyMessage',["保存比分成功！","success"]);
								var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.1'});
								$(".gameTree").gameTree(temp,res["data"]);
								gameTreeForCanvas(res["data"]);
								var treeNodeLength = $(".gameTree").find("li:eq(0)").attr("turn");
								var turn1 = Math.pow(2,treeNodeLength-1);
								$(".gameTree").css("width",turn1*250+"px")
								$("#outScoreInput").modal("hide");
							}else{
								messageService.publish('notifyMessage',["保存比分失败！","error"]);
							}

						}).error(function(){
							messageService.publish('notifyMessage',["保存比分失败！","error"]);
						})
					}
				}
    		}else{
    			swal({
                    title: "警告",
                    text: res["data"]
                });
    		}
        }).error(function(error){

        });
	}
	
	$scope.subGamePoint = function(){
		swal({
            title: "确定要提交比分吗?",
            text: "比分提交后将不能修改!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var sortPromise = GameService.subGamePoint(gameId);
        	sortPromise.success(function(data){
        		if(data["result"]=="success"){
        			messageService.publish('notifyMessage',["提交比分成功！","success"]);
        			$location.path('game');
        		}else{
        			messageService.publish('notifyMessage',[data["data"],"error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["提交比分失败！","error"]);
            });
        });
	}
}]);


gameApp.controller('scheduleListController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
	$scope.gameId = gameId;
	var promise = GameService.getSchedules(gameId);
	promise.success(function(res){
		if(res["result"]=='success'){
			$scope.schedules = res["data"];
		}else{
			messageService.publish('notifyMessage',["赛程/结果获取失败！","error"]);
		}
    }).error(function(error){
    	messageService.publish('notifyMessage',["赛程/结果获取失败！","error"]);
    });
	$scope.downSchedules = function(){
		$(".schedule-list").each(function(index,obj){
			html2canvas(obj).then(function(canvas) {
				saveFile(canvas.toDataURL(),"场地编排.png");
			});
		})
	}
	$scope.downBody = function(){
		html2canvas(document.body).then(function(canvas) {
			saveFile(canvas.toDataURL(),"场地编排.png");
		});
	}
	
}]);

gameApp.controller('gameScheduleController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
	$scope.gameId = gameId;
	$scope.gameFormatMap = {1:"循环+淘汰",2:"单淘汰"};
	$scope.gameFormat1Map = {0:"抢四",1:"抢六"};
	$scope.gameSchedule = {};
	$scope.saveFlag = false;
	
	var promise_ = GameService.initGameSchedule(gameId);
	promise_.success(function(res){
		if(res["result"]=='success'){
			$scope.gameSchedules = res["data"];
		}else{
			$scope.saveFlag = false;
			messageService.publish('notifyMessage',["赛程生成初始化失败！","error"]);
		}
    }).error(function(error){
    	$scope.saveFlag = false;
    	messageService.publish('notifyMessage',["赛程生成初始化失败！","error"]);
    });
	
	$scope.setSchedule = function(){
		if($scope.gameSchedule.gameField==undefined || $scope.gameSchedule.gameField==''){
			swal({
				title: "警告",
				text: "请填写场地数！"
			});
			return;
		}
		$scope.gameSchedules[0].gameField = $scope.gameSchedule.gameField;
		var promise = GameService.setGameSchedule($scope.gameSchedules);
		promise.success(function(res){
    		if(res["result"]=='success'){
    			$scope.saveFlag = true;
    			messageService.publish('notifyMessage',["赛程生成成功！","success"]);
    			/*if($scope.gameSchedule.gameFormat==3){
    				$scope.schedules = res["data"]["fields"];
    				$scope.groups = res["data"]["groupsView"];
    			}else if($scope.gameSchedule.gameFormat==1){
    				$scope.schedules = res["data"]["fields"];
    				$scope.groups = res["data"]["groupsView"];
    			}else if($scope.gameSchedule.gameFormat==2){
    				$scope.vs = res["data"];
    				var temp = new EJS({url: 'static/js/app/game/treeNode.ejs?ver=1.1'});
    				$("#gameTree").gameTree(temp,res["data"]);
    				var treeNodeLength = $("#gameTree").find("li:eq(0)").attr("turn");
    				var turn1 = Math.pow(2,treeNodeLength-1);
    				$("#gameTree").css("width",turn1*250+"px")
    			}*/
    			$scope.schedules = res["data"];
    		}else{
    			$scope.saveFlag = false;
    			messageService.publish('notifyMessage',["赛程生成失败！","error"]);
    		}
        }).error(function(error){
        	$scope.saveFlag = false;
        	messageService.publish('notifyMessage',["赛程生成失败！","error"]);
        });
	}
	
	$scope.saveSchedule = function(){
		if($scope.saveFlag){
			swal({
	            title: "确定要保存当前赛程吗?",
	            text: "你将要保存当前赛程，保存成功不可更改!",
	            type: "warning",
	            showCancelButton: true,
	            cancelButtonText:"取消",
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定",
	            closeOnConfirm: true
	        }, function () {
	        	var savePromise = GameService.saveSchedule($scope.schedules,gameId,$scope.gameSchedules);
	        	/*if($scope.gameSchedule.gameFormat==3){
	        		savePromise = GameService.saveLoopSchedule($scope.groups,$scope.schedules,gameId);
				}else if($scope.gameSchedule.gameFormat==1){
					savePromise = GameService.saveGroupSchedule($scope.groups,$scope.schedules,gameId);
				}else if($scope.gameSchedule.gameFormat==2){
					savePromise = GameService.saveOutSchedule($scope.vs,gameId);
				}*/
	        	savePromise.success(function(data){
	        		if(data["result"]=="success"){
	        			messageService.publish('notifyMessage',["保存赛程成功！","success"]);
	        			$location.path('game');
	        		}else{
	        			messageService.publish('notifyMessage',["保存赛程失败！","error"]);
	        		}
	            }).error(function(error){
	            	messageService.publish('notifyMessage',["保存赛程失败！","error"]);
	            });
	        });
		}else{
			swal({
				title: "警告",
				text: "请先设置赛程！"
			});
		}
	}
}]);

gameApp.controller('gameFormController',['$scope','GameService','messageService','$location','$route',function($scope,GameService,messageService,$location,$route,$routeParams) {
	messageService.publish('breadcrumb',"CTAgameIco");
	var gameId = $route.current.params.id;
	$scope.gameFormatMap = {1:"循环+淘汰",2:"单淘汰"};
	$scope.gameScoreTypeMap = {0:"抢四",1:"抢六"};
	$scope.gameLevelMap = {1:"乐享一级赛",2:"乐享二级赛",3:"乐享三级赛",4:"乐享四级赛"};
	$scope.gameLimitMap = {0:"不限制",8:"8",16:"16",32:"32"};
	$scope.itemLevelMap = {"1.0":"1.0","1.5":"1.5","2.0":"2.0","2.5":"2.5","3.0":"3.0","3.5":"3.5","4.0":"4.0","4.5":"4.5","5.0":"5.0","5.5":"5.5","6.0":"6.0","6.5":"6.5","7.0":"7.0","7.5":"7.5","8.0":"8.0","8.5":"8.5","9.0":"9.0"};
	if(gameId!=undefined){
		var promise = GameService.getGame(gameId);
		promise.success(function(res){
			if(res["result"]=="success"){
				$scope.game = res["data"];
				var level = $scope.game.gameLevel;
				$scope.game.gameLevel = $scope.game.gameLevel+"";
				if(level==1){
					$("#gameTip").text("最少选择四项赛事");
				}else if(level==2){
					$("#gameTip").text("必须选择三项赛事");
				}else if(level==3){
					$("#gameTip").text("必须选择两项赛事");
				}else if(level==4){
					$("#gameTip").text("必须选择一项赛事");
				}
			}
		})
	}else{
		var promise = GameService.initGame();
		$scope.game = {};
		promise.success(function(res){
			if(res["result"]=="success"){
				$scope.game = res["data"];
			}else{
				swal({
	                title: "警告",
	                text: "举办次数已达最大次数！"
	            });
				$location.path('game');
			}
		});
	}
	$scope.changeTip = function(){
		var level = $scope.game.gameLevel;
		if(level==1){
			$("#gameTip").text("最少选择四项赛事");
		}else if(level==2){
			$("#gameTip").text("必须选择三项赛事");
		}else if(level==3){
			$("#gameTip").text("必须选择两项赛事");
		}else if(level==4){
			$("#gameTip").text("必须选择一项赛事");
		}
	}
	$scope.submitForm = function(){
		if($scope.game.gameLevel==undefined || $scope.game.gameLevel==''){
    		swal({
                title: "警告",
                text: "请选择级别！"
            });
    		return;
    	}
		
		var checkedGameNum = 0;
		var breakFlag = false;
		var wrongFee = false;
		var minFee = 0;
		var level = $scope.game.gameLevel;
		if(level==1){
			minFee = 40;
		}else if(level==2){
			minFee = 30;
		}else if(level==3){
			minFee = 20;
		}else if(level==4){
			minFee = 10;
		}
		angular.forEach($scope.game.items,function(item){
			if(item.checked){
				if((item.fee==undefined||item.fee=='')||(item.level==undefined||item.level=='')||(item.format==undefined||item.format=='')||(item.scoreType==undefined||item.scoreType=='')){
					breakFlag = true;
					return false;
				}
				if(item.fee<minFee){
					wrongFee = true;
		    		return false;
		    	}
				checkedGameNum++;
			}
		});
		if(breakFlag){
			swal({
                title: "警告",
                text: "参赛项目信息不完整！"
            });
    		return;
		}
		
		if(wrongFee){
			swal({
                title: "警告",
                text: "报名费用最低"+minFee+"元！"
            });
			return;
		}
		
		var level = $scope.game.gameLevel;
		if(level==1){
			if(checkedGameNum<4){
				swal({
	                title: "警告",
	                text: "乐享一级赛，最少选择四项赛事！"
	            });
	    		return;
			}
		}else if(level==2){
			if(checkedGameNum!=3){
				swal({
	                title: "警告",
	                text: "乐享二级赛，必须选择三项赛事！"
	            });
	    		return;
			}
		}else if(level==3){
			if(checkedGameNum!=2){
				swal({
	                title: "警告",
	                text: "乐享三级赛，必须选择两项赛事！"
	            });
	    		return;
			}
		}else if(level==4){
			if(checkedGameNum!=1){
				swal({
	                title: "警告",
	                text: "乐享四级赛，必须选择一项赛事！"
	            });
	    		return;
			}
		}
		
		if($scope.game.startTime==undefined || $scope.game.startTime==''){
    		swal({
                title: "警告",
                text: "开始时间不能为空！"
            });
    		return;
    	}
		if($scope.game.endTime==undefined || $scope.game.endTime==''){
    		swal({
                title: "警告",
                text: "结束时间不能为空！"
            });
    		return;
    	}
		if($scope.game.expiryDate==undefined || $scope.game.expiryDate==''){
    		swal({
                title: "警告",
                text: "报名截止时间不能为空！"
            });
    		return;
    	}
		if($scope.game.chiefJudge==undefined || $scope.game.chiefJudge==''){
    		swal({
                title: "警告",
                text: "联系人不能为空！"
            });
    		return;
    	}
		if($scope.game.tel==undefined || $scope.game.tel==''){
    		swal({
                title: "警告",
                text: "联系电话不能为空！"
            });
    		return;
    	}
		delete $scope.game.isExpiry
		console.log(JSON.stringify($scope.game))
		var savePromise = GameService.saveGame($scope.game);
		savePromise.success(function(res){
    		if(res["result"]=='success'){
    			messageService.publish('notifyMessage',["赛事保存成功！","success"]);
    			$location.path('game');
    		}else{
    			messageService.publish('notifyMessage',[res["data"],"error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["赛事保存失败！","error"]);
        });
	}
	
}]);