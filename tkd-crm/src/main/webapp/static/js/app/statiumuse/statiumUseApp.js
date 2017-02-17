var statiumUseApp = angular.module('statiumUseApp',['ngRoute'])
statiumUseApp.factory('StatiumUseService',['$http','$route',function($http,$route,$routeParams){
    return {
    	loadStatiumUse:function(data){
        	return $http.post('/admin/space/list',data);
        },
        getStatiumSportTypes:function(){
            return $http.post('/admin/statium/sportTypes',{cache:false});
        },
        deblockingUse:function(data){
        	return $http.post('/admin/spacelock/single',data);
        },
        checkOrder:function(orderId){
        	return $http.post('/admin/order/checkOrder?orderId='+orderId,{cache:false});
        },
        createOrder:function(data){
        	return $http.post('/admin/order/create',data);
        },
        cancleOrder:function(orderId){
        	return $http.post('/admin/order/orderItem?orderId='+orderId,{cache:false});
        },
        appOrderItem:function(orderId){
        	return $http.post('/admin/order/appOrderItem?orderId='+orderId,{cache:false});
        },
        deleteOrder:function(orderId,reason){
        	return $http.post('/admin/order/cancel?orderId='+orderId+'&reason='+reason);
        },
        paySpaceOrder:function(data){
        	return $http.post('/admin/order/balance', data);
        },
        batchUnlock:function(type,date){
        	return $http.post('/admin/spacelock/batchUnlockSingle?type='+type+'&orderDate='+date,{cache:false});
        },
        searchMember:function(keyword){
        	return $http.post('/admin/order/searchMember?keyword='+keyword,{cache:false});
        },
        selling:function(){
        	return $http.post('/admin/mall/selling',{cache:false});
        }
    }
}]);


statiumUseApp.controller('loadStatiumUseController',['$rootScope','$scope','messageService','StatiumUseService',function($rootScope,$scope,messageService,StatiumUseService) {
	messageService.publish('breadcrumb','bookSpaceIco');
	var sportTypesPromise = StatiumUseService.getStatiumSportTypes();
	sportTypesPromise.success(function(data){
		$scope.sportTypes = data["data"];
		$scope.ballType=data["data"][0];
		SUMITER.NEWSUMITER();
	}).error(function(error){
        console.log(error);
    });
	
	var SUMITER = {
		hasProtoType: false,
		NEWSUMITER: function() {
			if (this.hasProtoType == false) {
				this.hasProtoType = true;
				this.INIT.prototype = SUMITER;
			}
			return this.INIT();
		},
		BindDateTimeList: function() {
			var that = this;
			var today = new Date();
			var day = today.getDate(); //日期 1开始
			var week = today.getDay(); //星期 0开始
			
			that.Globe_dataTime = that.FormateDate(today, 1); //当前选择的日期
			$("#input_date").val(that.FormateDate(today, 1));
			var arr = [];
			arr.push('<li class="active" date-data="' + that.FormateDate(today, 1) + '"><a href="javascript:;"><span>' + that.FormateDate(today, 2) + '</span><strong>今天</strong></a></li>');
		
			for (var i = 0; i < 6; i++) {
				today.setDate(today.getDate() + 1);
				day = today.getDate();
				week++; ///星期++
				if (week > 6) {
					week = 0;
				}
				arr.push('<li date-data="' + that.FormateDate(today, 1) + '"><a href="javascript:;"><span>' + that.FormateDate(today, 2) + '</span><strong>周'+ that.FormateDate(today, 4) +'</strong></a></li>');
				
			}
			that.$dateTimeList.html(arr.join(''));
			
		},
		LoadData: function() {
			var that = this;
			
			
			that.Globe_ballType = that.$ballType.val(); //选择的球类别
			that.Globe_ballTypeName = that.$ballType.children('option:selected').text();
			
			var date = that.Globe_dataTime;
			var ballType = that.Globe_ballType;
			var ballTypeName = that.Globe_ballTypeName;
			//get JSON from ajax
			var promise = StatiumUseService.loadStatiumUse({'startDate': date, 'sportType': $scope.ballType});
			promise.success(function(data){
				if (data && data['result']=='success') {
					var groundList = data['data']['spaceCodeList']; //场馆所有场地列表
					if(groundList.length==0){
						swal({
			                title: "警告",
			                text: "请维护场地或场地价格！"
			            });
						that.$GroundManager.html('<div>暂无数据！</div>');
					}else{
						that.BindGroundsInfo(data.data);//绑定场地信息
					}
		        } else {
		            that.$GroundManager.html('<div>暂无数据！</div>');
		        }
			}).error(function(error){
				that.$GroundManager.html('<div>网络忙！</div>');
			})
			
		},
		
		BindGroundsInfo: function(data) {
			var that = this;
			if (data) {
				that.$GroundManager.attr('date-data', data['time']).attr('sportType-data', data['sportType']); //场馆id
				
				$scope.dateToday = data['startDate']; //日期
				var groundList = data['spaceCodeList']; //场馆所有场地列表
				var groundListClone = groundList.concat(); //复制一个新的数组去操作
				var orderList = data['orderItemList']; //场馆场地使用情况
				var lockSpaces = data['lockSpaces'];//锁定场地list
				var batchLockSpaces = data['batchLockSpaces'];//批量锁定场地list
				var noPayOrder = data['orderNewList'];//锁定场地list
				//
				var beginTime = data["beginTime"]||'00:00'; //场馆营业时间
				$scope.endTime = data["endTime"]||'24:00'; //场馆营业时间
				if($scope.endTime=="00:00"){
					$scope.endTime = "24:00";
				}
				if($scope.endTime==beginTime){
					beginTime = "00:00";
					$scope.endTime = "24:00";
				}
				var timelineAry = that.GetTimelineAry(beginTime, $scope.endTime); //营业时间数组
				
				
				//console.log(timelineAry);
				//
				var timelineHTML = []; //营业时间html
				var groundsArr = []; //所有场地html
				var groundLCont = [];
				/************* 营业时间html开始 *************/
				$.each(timelineAry,function(key,value){
					if(value.toString().indexOf(':30') <= 0){
						timelineHTML.push('<li class="liList">'+ value +'</li>');
					}
				});
				$("#dateUl").html(timelineHTML.join(''));
				var totalTimes = new Array();
				for(var i=0;i<24;i++){
					var time = i<10?("0"+i):(i+"");
					totalTimes.push(time+":00");
					totalTimes.push(time+":30");
				}
				/************* 营业时间html结束 *************/
				
				/************* 场地使用情况html开始 *************/
				$.each(groundList,function(key,value){
					groundLCont.push('<div class="first-child">'+value.spaceCode+'</div>');
					var priceArr = value.prices.split(',');
					var groundHtml = '<div class="spaceGround" spacecode-data="'+value.spaceCode+'" spacename-data="'+value.spaceName+'" sporttype-data="'+data.sportType+'" id-data="'+value.id+'" id="'+value.id+'">';
					var cellArr = [];
						$.each(timelineAry,function(key2,value2){
							if (that.IsTodayAndTime($scope.dateToday, value2.toString())&&value2.toString()!=$scope.endTime&&parseInt(priceArr[totalTimes.indexOf(value2)],10)!=0) {
								cellArr.push('<div price="'+priceArr[totalTimes.indexOf(value2)]+'" begin-time-data="'+value2+'" ce1="'+value.spaceName+'" class="cells canBook"><span><a></a></span></div>');
							}else{
								cellArr.push('<div price="'+priceArr[totalTimes.indexOf(value2)]+'" begin-time-data="'+value2+'" ce1="'+value.spaceName+'" class="cells cantBook"><span><a></a></span></div>');
							}
							
						});
                        groundHtml = groundHtml+cellArr.join('')+'</div>';
                        groundsArr.push(groundHtml);
				});
				$("#groundLCont").html(groundLCont.join(''));
				$("#scroller").html(groundsArr.join(''));
				setWidth();
				
				$.each(orderList,function(key,value){//添加订单列表
					var orderSpaceCode = value.spaceCode;
					var orderType = value.type;
					var orderStartTime = value.startTime;
					var orderId = value.orderId;
					var scod = $("[spacecode-data="+orderSpaceCode+"]")[0];
					var orderBegin = $(scod).find("[begin-time-data]");
					$.each(orderBegin,function(){
						var obj = $(this);
						if(obj.attr("begin-time-data") == orderStartTime){
							if(orderType == "live"){
								obj.removeClass().addClass("cells").addClass("spaceOrder");
								obj.attr("title","取消已支付的订单");
								obj.attr("orderId-data",orderId);
								obj.find("a:eq(0)").removeClass().addClass("payIco");
							}else if(orderType == "app"){
								obj.removeClass().addClass("cells").addClass("isBooked appOrder");
								obj.attr("title","球友圈已支付订单");
								obj.attr("orderId-data",orderId);
								obj.find("a:eq(0)").removeClass().addClass("yelbot");
							}
						}
					});
				});
				
				$.each(noPayOrder,function(key,value){
					var lockSpaceCode = value.spaceCode;
					var orderType = value.type;
					var lockStartTime = value.startTime;
					var scod = $("[spacecode-data="+lockSpaceCode+"]")[0];
					var lockBegin = $(scod).find("[begin-time-data]");
					var orderId = value.orderId;
					$.each(lockBegin,function(){
						var obj = $(this);
						if(obj.attr("begin-time-data") == lockStartTime){
							if(orderType == "live"){
								obj.removeClass().addClass("cells").addClass("noPaySpaceOrder spaceOrder");
								obj.attr("title","取消未支付的订单");
								obj.attr("orderId-data",orderId);
								obj.find("a:eq(0)").removeClass().addClass("noPayIco");
							}else if(orderType == "app"){
								obj.removeClass().addClass("cells").addClass("noPayOrder appOrder");
								obj.attr("title","球友圈未支付订单");
								obj.attr("orderId-data",orderId);
								obj.find("a:eq(0)").removeClass().addClass("noPayIcoQiu");
							}
						}
					});
				});
				
				
				$.each(batchLockSpaces,function(key,value){
					var lockSpaceCode = value.spaceCode;
					var lockStartTime = value.startTime;
					var scod = $("#"+lockSpaceCode);
					var lockBegin = $(scod).find("[begin-time-data]");
					$.each(lockBegin,function(){
						var obj = $(this);
						if(obj.attr("begin-time-data") == lockStartTime){
							obj.removeClass().addClass("cells").addClass("lockBook");
							obj.attr("title","批量锁定点击解除锁定");
							obj.find("a:eq(0)").removeClass().addClass("batchLockIco");
						}
					});
				});
				
				$.each(lockSpaces,function(key,value){
					var lockSpaceCode = value.spaceCode;
					var lockStartTime = value.startTime;
					var scod = $("#"+lockSpaceCode);
					var lockBegin = $(scod).find("[begin-time-data]");
					$.each(lockBegin,function(){
						var obj = $(this);
						if(obj.attr("begin-time-data") == lockStartTime){
							obj.removeClass().addClass("cells").addClass("lockBook");
							obj.attr("title","已锁定点击解除锁定");
							obj.find("a:eq(0)").removeClass().addClass("lockIco");
						}
					});
				});
				
			}
		},
		//判断订单中的时间
		CheckContainsOrderTime: function(orderTimeAry, currentTime) {
			var flag = false;
			if (currentTime && orderTimeAry && Object.prototype.toString.call(orderTimeAry).indexOf('Array') > 0) {
				for (var i = 0, len = orderTimeAry.length; i < len; i++) {
					if (currentTime == orderTimeAry[i].toString()) {
						flag = true;
						break;
					}
				}
			}
			return flag;
		},
		GetTimelineAry: function(beginTime, endTime) {
			var that = this;
			var beginTimeIndex = that.GetTimelineIndex(beginTime); //开始时间索引
			var endTimeIndex = that.GetTimelineIndex(endTime); //结束时间索引
			var timeAry = []; //营业时间数组
			if(beginTimeIndex<=endTimeIndex){
				for (var i = beginTimeIndex; i < endTimeIndex; i++) {
					timeAry.push(that.timeRangeAry[i]);
				}
			}else{
				var endIndex=48;
				var beginIndex=0;
				for (var i = beginIndex; i < endTimeIndex; i++) {
					timeAry.push(that.timeRangeAry[i]);
				}
				for (var i = beginTimeIndex; i < endIndex; i++) {
					timeAry.push(that.timeRangeAry[i]);
				}
			}
			return timeAry;
		},
		GetTimelineIndex: function(timeStr) {
			var that = this;
			var index = 0;
			for (var i = 0, len = that.timeRangeAry.length; i < len; i++) {
				if (that.timeRangeAry[i].toString() == timeStr) {
					index = i;
					break;
				}
			}
			return index;
		},
		FormateDate: function(date, type) {
			var timeStr = "";
			var year, month, day, week;
			var week_ary = ["日", "一", "二", "三", "四", "五", "六"];
			if (date) {
				year = date.getFullYear(); //年
				month = parseInt(date.getMonth()) + 1; //月
				month = month.toString().length == 1 ? '0' + month : month;
				day = date.getDate(); //日
				day = day.toString().length == 1 ? '0' + day : day;
				week = date.getDay(); //星期
			}
			switch (type) {
				case 1:
					timeStr = year + '-' + month + '-' + day; //2015/07/30
					break;
				case 2:
					timeStr = month + '-' + day; //07/30
					break;
				case 3:
					timeStr = day; //30
					break;
				case 4:
					timeStr = week_ary[week]; //日
					break;
				default:
					timeStr = year + '-' + month + '-' + day;
					break;
			}
			return timeStr;
		},
		IsTodayAndTime: function(day, time) {
			//day 手动选择的日期
			//time 教陪练的时间段
			var that = this;
			var flag = false; //true为可以预定，false为不能预定
			var today = new Date(); //今天日期+时间
			var todayDate = that.FormateDate(today); //今天日期
			var todayTimeHours = today.getHours(); //当前小时
			var todayTimeMinutes = today.getMinutes(); //当前分钟
			todayTimeMinutes = parseInt(todayTimeMinutes) > 30 ? ':30' : ':00';
			var todayTime = todayTimeHours.toString().length == 1 ? '0' + todayTimeHours + todayTimeMinutes : todayTimeHours + todayTimeMinutes;
			if (day && (day == todayDate)) {
				//是当天日期
				if (that.GetTimelineIndex(time) > that.GetTimelineIndex(todayTime)) {
					//判断的时间大于当前时间,可以预定
					flag = true;
				} else {
					flag = false;
				}
			} else if (new Date(day).getTime() < today.getTime()) {
				//之前时间
				flag = false;
			} else {
				//之后时间
				flag = true;
			}
			return flag;
		},
		BindEvent: function() {
			var that = this;
			that.serverList = new Array();
			//取消场地人员下的单子包括已支付和未支付
			that.deleSpaceOrder.on('click', '.spaceOrder a', function(e) {
				//var obj = $(this);
				var startDate = $("#input_date").val();
				var orderId = $(this).closest(".cells").attr("orderid-data");
				var cancleAjax = StatiumUseService.cancleOrder(orderId);
				cancleAjax.success(function(data){
					if(data.result == "success"){
						var htmlArr = [];
						$.each(data.data.paras, function(key, value){
							var html = '<dd><span>'+value.spaceName+'</span><span>'+value.startDate+'&nbsp;'+value.startTime+'</span><span>'+value.douPrice/100+'</span></dd>';
							htmlArr.push(html);
							
						});
						$($(".yi-ding").find("span")[1]).text(data.data.fee/100);
						$($(".yi-ding").find("span")[3]).text(data.data.finalFee/100);
						var payType="";
						if(data.data.payType==0){
							payType = "现金";
						}else if(data.data.payType==1){
							payType = "支付宝";
						}else if(data.data.payType==2){
							payType = "微信";
						}else if(data.data.payType==3){
							payType = "余额";
						}else if(data.data.payType==4){
							payType = "刷卡";
						}
						$($(".yi-ding").find("span")[4]).text("支付方式："+payType);
						var member = data.data.member;
						if(member!=null){
							$($(".xinxi-zf").find("span")[0]).text(member.cardNo);
		    	    		$($(".xinxi-zf").find("span")[1]).text(member.name);
		    	    		$($(".xinxi-zf").find("span")[2]).text(member.phone);
		    	    		$($(".xinxi-zf").find("span")[3]).text(member.levelName);
		    	    		$($(".xinxi-zf").find("span")[4]).text('余额 ￥'+member.balance/100);
		    	    		$($(".xinxi-zf").find("span")[5]).text('折扣 '+member.rate/100);
						}else{
							$($(".xinxi-zf").find("span")[0]).text('');
		    	    		$($(".xinxi-zf").find("span")[1]).text('');
		    	    		$($(".xinxi-zf").find("span")[2]).text('');
		    	    		$($(".xinxi-zf").find("span")[3]).text('');
		    	    		$($(".xinxi-zf").find("span")[4]).text('');
		    	    		$($(".xinxi-zf").find("span")[5]).text('');
						}
						
						$("#deleOrder").attr("orderId-data",data.data.orderId);
						$("#payOrder").attr("orderId-data",data.data.orderId);
						var oDl = $("#cancleOrderList").find("dl:eq(0)");
						oDl.find("dd").remove();
						oDl.append(htmlArr.join(""));
						
						$("#cancleOrderModal").modal();
						$("body").css('padding-right','0px');
						if(data.data.status == "ORDER_NEW"){
							$("#payOrder").show();
						}else{
							$("#payOrder").hide();
						}
						$("#deleOrder").show();
					}else{
						swal({
			                title: "警告",
			                text: data.data
			            });
						return false;
					};
				});
			});
			that.deleSpaceOrder.on('click', '.appOrder a', function(e) {
				//var obj = $(this);
				var startDate = $("#input_date").val();
				var orderId = $(this).closest(".cells").attr("orderid-data");
				var cancleAjax = StatiumUseService.appOrderItem(orderId);
				cancleAjax.success(function(data){
					if(data.result == "success"){
						var htmlArr = [];
						$.each(data.data.paras, function(key, value){
							var html = '<dd><span>'+value.spaceName+'</span><span>'+value.startDate+'&nbsp;'+value.startTime+'</span><span>'+value.douPrice/100+'</span></dd>';
							htmlArr.push(html);
							
						});
						$("#orderInfo").html("<p>订单号："+data.data.orderId+"　　手机号："+data.data.phone+"</p>");
						$("#orderPress").html(data.data.finalFee/100);
						var oDl = $("#appOrderList").find("dl:eq(0)");
						oDl.find("dd").remove();
						oDl.append(htmlArr.join(""));
						
						$("#appOrderModal").modal();
						$("body").css('padding-right','0px');
					}else{
						swal({
			                title: "警告",
			                text: data.data
			            });
						return false;
					};
				});
			});
			that.mouseOrder.on({
				'mouseover':function(e) {
					var oParent = $(this).closest(".cells");
					var orderId = oParent.attr("orderid-data");
					var commonList = $("#groundRight").find('[orderid-data]');
					$.each(commonList,function(key,value){
						var obj = $(this);
						if(obj.attr("orderid-data") == orderId){
							
							obj.find("a:eq(0)").css("border",'1px solid #666');
						}
					});
				},
				'mouseout':function(e){
					var oParent = $(this).closest(".cells");
					var orderId = oParent.attr("orderid-data");
					var commonList = $("#groundRight").find('[orderid-data]');
					$.each(commonList,function(key,value){
						var obj = $(this);
						if(obj.attr("orderid-data") == orderId){
							
							obj.find("a:eq(0)").removeAttr('style');
						}
					});
				}
			},'.spaceOrder a,.appOrder a');
			
			that.deleOrder.off("click").on('click', function(){
				var orderId = $(this).attr("orderId-data");
				var reason = $("#delReason").val();
				reason=encodeURI(reason);
				reason=encodeURI(reason);//此处对reason进行了两次转码
				var deleAjax = StatiumUseService.deleteOrder(orderId,reason);
				deleAjax.success(function(data){
					if(data.result == "success"){
						var deleList = $("#groundRight").find('[orderid-data]');
						$.each(deleList,function(key,value){
							var obj = $(this);
							var orderStartTime = obj.attr("begin-time-data");
							if(obj.attr("orderid-data") == orderId){
								obj.removeAttr("orderid-data");
								obj.removeAttr("title");
								if(that.IsTodayAndTime($scope.dateToday, orderStartTime)&&orderStartTime!=$scope.endTime){
									obj.removeClass("spaceOrder").removeClass("noPaySpaceOrder").addClass("canBook");
								}else{
									obj.removeClass("spaceOrder").removeClass("noPaySpaceOrder").addClass("cantBook");
								}
								obj.find("a:eq(0)").removeAttr("class");
							}
						});
						$("#cancleOrderModal").modal('hide');
					}else{
						swal({
			                title: "警告",
			                text: data.data
			            });
						return false;
					};
				});
			});
			//场地支付订单
			that.payOrder.off("click").on('click', function(){
				var orderId = $(this).attr("orderId-data");
				var fee = parseFloat($("#cancleAcqTotal").html());
				var finalFee = parseFloat($("#canclePockTotal").val());
				var payAjax = StatiumUseService.paySpaceOrder({'orderId':orderId, 'fee': fee, 'finalFee': finalFee});
				
				payAjax.success(function(data){
					if(data.result == "success"){
						var deleList = $("#groundRight").find('[orderid-data]');
						$.each(deleList,function(key,value){
							var obj = $(this);
							if(obj.attr("orderid-data") == orderId){
								obj.removeClass("noPaySpaceOrder");
								obj.find("a:eq(0)").removeClass("noPayIco").addClass('payIco');
							}
						});
						$("#cancleOrderModal").modal('hide');
					}else{
						swal({
			                title: "警告",
			                text: data["data"]
			            });
						return false;
					};
				});
			});
			//解锁事件
			that.deblocking.on('click', '.lockBook a', function(e) {
				var $this = this;
				swal({
		            title: "您确定要解锁吗？",
		            text: "你将要解锁这块场地!",
		            type: "warning",
		            showCancelButton: true,
		            cancelButtonText:"取消",
		            confirmButtonColor: "#DD6B55",
		            confirmButtonText: "确定",
		            closeOnConfirm: true
		        }, function () {
		        	var obj = $($this);
					var oPar = obj.parent().parent();
					var lockOrderDate = $("#input_date").val();
					var lockSpaceId = obj.closest(".spaceGround").attr("id-data");
					var lockStartTime = oPar.attr("begin-time-data");
					var lockSpaces = [{'spaceCode':lockSpaceId, 'startTime':lockStartTime,'sportType':$scope.ballType}];
					var delockAjax = StatiumUseService.deblockingUse({'type': "unlock", 'orderDate': lockOrderDate, 'spaces':lockSpaces});
					
					delockAjax.success(function(data){
						if(data.result == "success"){
							oPar.find("a:eq(0)").removeAttr("title");
							oPar.removeAttr("title");
							if(that.IsTodayAndTime($scope.dateToday, lockStartTime)&&lockStartTime!=$scope.endTime){
								oPar.removeClass("lockBook").addClass("canBook");
							}else{
								oPar.removeClass("lockBook").addClass("cantBook");
							}
							obj.removeClass();
						};
					})
		        });
			});
			that.$check_order.on('click',function(e){
				$("#modal-lgb").load("/view/statiumuse/checkOrder.html?ver=1.0");
				$("#orderModal").modal();
				$("body").css('padding-right','0px');
			})
			
			that.$check_code.on('click',function(e){
				$("#modal-lgb").load("/view/statiumuse/checkCode.html?ver=1.12");
				$("#orderModal").modal();
				$("body").css('padding-right','0px');
			})
			
			
			
			that.$all_select.on('click',function(e){
				var allSelect = $("#groundRight").find(".canBook");
				if(allSelect.size()==0){
					return;
				}
				var text = $("#all_select").html();
				$.each(allSelect, function(){
					var obj = $(this);
					var selectA = obj.find("a");
					if(text=="全选"){
						selectA.addClass('selectIco').addClass('Booking');
					}else{
						selectA.removeClass('selectIco').removeClass('Booking');
					}
				});
				if(text=="全选"){
					$("#all_select").html("取消");
				}else{
					$("#all_select").html("全选");
				}
			});	

			// 锁定订单
			that.$lock_order.on('click', function(e) {
				var lockSqu = $("#groundRight").find(".selectIco");
				var lockArr = [];
				var lockOrderDate = $("#input_date").val();
				if(lockSqu.length <= 0){
					swal({
		                title: "警告",
		                text: "没有选择场地，请选择"
		            });
					return false;
				}
				$.each(lockSqu, function(){
					var obj = $(this);
					var oPar = obj.parent().parent();
					var lockSpaceId = obj.closest(".spaceGround").attr("id-data");
					var lockSportType = $scope.ballType;
					var lockStartTime = oPar.attr("begin-time-data");
					var serverinfo = {'sportType':lockSportType,'spaceCode':lockSpaceId, 'startTime':lockStartTime};
					lockArr.push(serverinfo);
				});
				
				var delockAjax = StatiumUseService.deblockingUse({'type': "lock", 'orderDate': lockOrderDate, 'spaces':lockArr});
				
				delockAjax.success(function(data){
					if(data.result == "success"){
						$("#all_select").html("全选");
						$.each(lockSqu, function(){
							var obj = $(this);
							var oPar = obj.parent().parent();
							oPar.removeClass("canBook").addClass("lockBook");
							obj.attr("title","解除锁定");
							obj.removeClass().addClass("lockIco");
						});
						
					};
				})
			});
			
			that.$unlock_order.on('click', function(e) {
				var lockOrderDate = $("#input_date").val();
				var lockSportType = $scope.ballType;
				var allSelect = $("#groundRight").find(".lockIco");
				if(allSelect.size()==0){
					swal({
		                title: "警告",
		                text: "没有可以解锁的场地！"
		            });
					return false;
				}
				swal({
		            title: "您确定要解锁所有的场地吗？",
		            text: "你将要解锁所有场地!",
		            type: "warning",
		            showCancelButton: true,
		            cancelButtonText:"取消",
		            confirmButtonColor: "#DD6B55",
		            confirmButtonText: "确定",
		            closeOnConfirm: true
		        }, function () {
					var unlockAjax = StatiumUseService.batchUnlock(lockSportType,lockOrderDate);
					unlockAjax.success(function(data){
						if(data.result == "success"){
							$.each(allSelect, function(){
								var obj = $(this);
								var oPar = obj.parent().parent();
								var orderStartTime = oPar.attr("begin-time-data");
								if(that.IsTodayAndTime($scope.dateToday, orderStartTime)&&orderStartTime!=$scope.endTime){
									oPar.removeClass("lockBook").addClass("canBook");
								}else{
									oPar.removeClass("lockBook").addClass("cantBook");
								}
								obj.removeAttr("title");
								obj.removeClass();
							});
						};
					});
		        });
				
			});
			
			that.$consumeGood.on('click',function(e){
				var sellingPromise = StatiumUseService.selling();
				$("#mem_kh").text("");
    			$("#mem_name").text("");
    			$("#mem_phone").text("");
    			$("#mem_bal").text("");
    			$("#mem_id").text("");
    			$("#hua-lun").html("");
    			$("#totalPrice").text("0.00元");
    			$("#totalPrice").attr("totalPrice","0");
    			$("#pay-type1").val("0");
    			carts = {};
    			totalPrice = 0;
				sellingPromise.success(function(response){
	    	    	if(response['result']){
	    	    		var goodsHtml = [];
	    	    		$.each(response.data,function(key,value){
	    	    			var good = value;
	    	    			goodsHtml.push('<div class="col-md-3">');
		    	    		goodsHtml.push('<div style="min-height:240px;" class="thumbnail">');
		    	    		goodsHtml.push('<img src="'+good.photo+'bigPicture" style="width: 160px;height: 120px" alt="通用的占位符缩略图">');
		    	    		goodsHtml.push('<div class="caption zi-xi">');
		    	    		goodsHtml.push('<p>￥'+good.price+'</p>');
		    	    		goodsHtml.push('<p>'+good.goodName+'</p>');
		    	    		goodsHtml.push('<p>'+good.bulk+'</p>');
		    	    		goodsHtml.push('<p class="add_cart zi-btn" onclick="newCart(\''+good.id+'\',\''+good.goodName+'\',\''+good.price+'\',\''+good.bulk+'\')">');
		    	    		goodsHtml.push('加入购物车');
		    	    		goodsHtml.push('</p>');
		    	    		goodsHtml.push('</div>');
		    	    	    goodsHtml.push('</div>');
		    	    	    goodsHtml.push('</div>');
	    	    		})
	    	    		$('.zi-quan').html(goodsHtml.join(""));
	    	    		$("#goodsModal").modal();
	    	    	}else{
	    	    		
	    	    	}
	    	    }).error(function(error){
	    	        console.log(error);
	    	    });
				
			})
			
			// 创建订单
			that.$create_order.on('click', function(e) {
				var creatSqu = $("#groundRight").find(".selectIco");
				var creatArr = [];
				var creatOrderDate = $("#selectDate").html();
				var startDate = $("#input_date").val();
				var htmlArr = [];
				var total = 0;
				if(creatSqu.length <=0 ){
					swal({
		                title: "警告",
		                text: "未选中场地，请选择！"
		            });
					return false;
				}
				var flags = [];
				$.each(creatSqu, function(){
					var obj = $(this);
					if((obj.closest(".spaceGround").find(".selectIco").size())==1){
						flags.push("1");
					}
					var oPar = obj.parent().parent();
					var creatSpaceCode = obj.closest(".spaceGround").attr("spacecode-data");
					var creatSportType = $scope.ballType;
					var creatStartTime = oPar.attr("begin-time-data");
					var creatPrice = parseFloat(oPar.attr("price"));
					var creatName = oPar.attr("ce1");
					
					total += creatPrice*100;
					
					var html = '<dd><span>'+creatName+'【'+creatSpaceCode+'】'+'</span><span>'+startDate+'&nbsp;'+creatStartTime+'</span><span>'+creatPrice+'</span></dd>';
					htmlArr.push(html);
				});
				if(flags.length!=0){
					swal({
		                title: "警告",
		                text: "每个场地至少选择一个小时！"
		            });
					return;
				}
				$("#member-search-value").val('');
				$($(".xin-ding").find("span")[1]).text(total/100);
				$($(".xin-ding").find("span")[3]).text(total/100);
				var oDl = $("#creatOrderList").find("dl:eq(0)");
				oDl.find("dd").remove();
				$(".xinxi-z").find("span").text('');
				$($(".xin-ding").find("span")[5]).text('0');
				$("#pay-type").val('0');
				oDl.append(htmlArr.join(""));
				$("#createOrderModal").modal();
				$("body").css('padding-right','0px');
			});
			
			that.$memberSearch.on('click',function(e){
				var keyword = $("#member-search-value").val();
				if(!keyword){
					return;
				}
				searchPromise = StatiumUseService.searchMember(keyword);
				searchPromise.success(function(response){
	    	    	if(response['result']){
	    	    		var member = response['data']
	    	    		$($(".xinxi-z").find("span")[0]).text(member.cardNo);
	    	    		$($(".xinxi-z").find("span")[1]).text(member.name);
	    	    		$($(".xinxi-z").find("span")[2]).text(member.phone);
	    	    		$($(".xinxi-z").find("span")[3]).text(member.levelName);
	    	    		$($(".xinxi-z").find("span")[4]).text('余额 ￥'+member.balance/100);
	    	    		$($(".xinxi-z").find("span")[5]).text('折扣 '+member.rate/100);
	    	    		$($(".xinxi-z").find("span")[6]).text(member.id);
	    	    		console.log($($(".xin-ding").find("span")[1]).text());
	    	    		$($(".xin-ding").find("span")[3]).text(($($(".xin-ding").find("span")[1]).text())*member.rate/100);
	    	    		$($(".xin-ding").find("span")[5]).text('1');
	    	    		$("#pay-type").val('3');
	    	    	}else{
	    	    		
	    	    	}
	    	    }).error(function(error){
	    	        console.log(error);
	    	    });
			})
			
			that.$memberSearch1.on('click',function(e){
				var keyword = $("#member-search-value1").val();
				if(!keyword){
					return;
				}
				searchPromise = StatiumUseService.searchMember(keyword);
				searchPromise.success(function(response){
	    	    	if(response['result']){
	    	    		var member = response['data'];
	    	    		if(member!=null){
	    	    			$("#mem_kh").text("卡号："+member.cardNo);
	    	    			$("#mem_name").text("姓名："+member.name);
	    	    			$("#mem_phone").text("手机号："+member.phone);
	    	    			$("#mem_bal").text("余额："+member.balance/100);
	    	    			$("#mem_id").text(member.id);
	    	    			$("#pay-type1").val('3');
	    	    		}
	    	    	}else{
	    	    		
	    	    	}
	    	    }).error(function(error){
	    	        console.log(error);
	    	    });
			})

			that.$subCreateOrder.off("click").on('click', function(e) {
				var creatSqu = $("#groundRight").find(".selectIco");
				var creatArr = [];
				var creatOrderDate = $("#selectDate").html();
				var startDate = $("#input_date").val();
				var total = 0;
				
				$.each(creatSqu, function(){
					var obj = $(this);
					var oPar = obj.parent().parent();
					var creatSpaceId = obj.closest(".spaceGround").attr("id");
					var creatSportType = $scope.ballType;
					var creatStartTime = oPar.attr("begin-time-data");
					var creatPrice = parseFloat(oPar.attr("price"));
					var creatName = oPar.attr("ce1");
					var douPrice = oPar.attr("price");
					total += creatPrice*100;
					
					var serverinfo = {'sportType':creatSportType,'douPrice':creatPrice, 'spaceId':creatSpaceId, 'startDate':startDate, 'startTime':creatStartTime};
					creatArr.push(serverinfo);
				});
				
				var pockTotal = $($(".xin-ding").find("span")[3]).text();
				console.log(pockTotal);
				var memberFlag = $($(".xin-ding").find("span")[5]).text();
				var memberId = '';
				if(memberFlag==='0'){//非会员
					
				}else{//会员
					memberId=$($(".xinxi-z").find("span")[6]).text();
				}
				var createAjax = StatiumUseService.createOrder({'type':'now', 'fee': total/100, 'finalFee': pockTotal, 'paras':creatArr,'memberId':memberId,"payType":$("#pay-type").val()});
				createAjax.success(function(data){
					if(data.result == "success"){
						$.each(creatSqu, function(){
							var obj = $(this);
							var oPar = obj.parent().parent();
							oPar.removeClass("canBook").addClass("spaceOrder");
							obj.removeClass().addClass("payIco");
							oPar.attr("orderid-data",data.data);
						});
						$('#createOrderModal').modal('hide');
					}else{
						swal({
			                title: "警告",
			                text: data.data
			            });
						return false;
					};
				});
			});
			
			that.$CreateOrderNoPay.off("click").on('click', function(e) {
				var creatSqu = $("#groundRight").find(".selectIco");
				var creatArr = [];
				var creatOrderDate = $("#selectDate").html();
				var startDate = $("#input_date").val();
				var total = 0;
				$.each(creatSqu, function(){
					var obj = $(this);
					var oPar = obj.parent().parent();
					var creatSpaceId = obj.closest(".spaceGround").attr("id");
					var creatSportType = $scope.ballType;
					var creatStartTime = oPar.attr("begin-time-data");
					var creatPrice = parseFloat(oPar.attr("price"));
					var creatName = oPar.attr("ce1");
					total += creatPrice*100;
					var douPrice = oPar.attr("price");
					var serverinfo = {'sportType':creatSportType,'douPrice':creatPrice, 'spaceId':creatSpaceId, 'startDate':startDate, 'startTime':creatStartTime};
					creatArr.push(serverinfo);
				});
				
				var pockTotal = $("#pockTotal").val();
				var createAjax = StatiumUseService.createOrder({'fee': total/100, 'finalFee': pockTotal, 'paras':creatArr});
				createAjax.success(function(data){
					if(data.result == "success"){
						$.each(creatSqu, function(){
							var obj = $(this);
							var oPar = obj.parent().parent();
							oPar.removeClass("canBook").addClass("noPaySpaceOrder spaceOrder");
							obj.removeClass().addClass("noPayIco");
							oPar.attr("orderid-data",data.data);
						});
						$('#createOrderModal').modal('hide');
					}else{
						swal({
			                title: "警告",
			                text: data.data
			            });
						return false;
					};
					
				});
			});
			//选择场地
			that.$GroundManager.on('click', '.canBook a', function(e) {
				var obj = $(this);
				var oPar = obj.parent().parent();
				obj.toggleClass('selectIco');
				obj.toggleClass('Booking');
				var oParNext = oPar.next()
				if(oParNext.hasClass("canBook")&&obj.hasClass("selectIco")&&obj.hasClass("Booking")){
					oParNext.find("a").addClass("selectIco").addClass("Booking")
				}
			});
			//选择时间
			that.$dateTimeList.on('click', 'li', function(e) {
				var obj = $(this);
				that.$dateTimeList.find("li").removeClass();
				$(obj).addClass("active");
				that.Globe_dataTime = $(obj).attr('date-data');
				//同步日期控件时间
				that.$input_date.val($(obj).attr('date-data'));
				//清除日历控件选中状态
				that.LoadData(); //ajax加载该场馆的场地情况
			});
			//选择球类别
			that.$ballType.on('change', function(e) {
				var $e = $(e.target || e.srcElement);
				that.Globe_ballType = $e.val();
				that.Globe_ballTypeName = $e.children('option:selected').text();
				that.LoadData(); //ajax加载该场馆的场地情况
			});
			
			
			//日期选择控件
			that.$input_date.on('click', function() {
				var $e = $(this);
				WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){
					if ($e.val()) {
						if ($e.attr('last-data') == $e.val()) {
							//第二次触发
						} else {
							//第一次触发
							$e.attr('last-data', $e.val());

							//赋值全局对象
							var selectedDateTime = $e.val().replace('/', '-').replace('/', '-');
							that.Globe_dataTime = selectedDateTime;

							//同步日历状态
							var dateTimeList = that.$dateTimeList.children('li');
							dateTimeList.removeClass('active');
							//console.log(selectedDateTime);
							$.each(dateTimeList, function(i, v) {
								if ($(v).attr('date-data') == selectedDateTime) {
									//选中列表中有的日期
									$(v).addClass('active').siblings('li').removeClass('active');
								}
							});
							//加载数据
							that.LoadData(); //ajax加载该场馆的场地情况
						}
					}
				}})
				
				
			});
		},
		INIT: function() {
			var that = this;
			that.timeRangeAry = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"];
			that.siteList = [];
			that.deblocking = $('#groundRight'); //场馆div
			that.deleSpaceOrder = $('#groundRight'); //场馆div
			that.$GroundManager = $('#groundRight'); //场馆div
			that.mouseOrder = $('#groundRight');
			that.$GroundManager_ = $('#groundRight'); //场馆div
			that.$lock_order = $('#lock_order'); //锁定订单
			that.$unlock_order = $("#unlock_order");
			that.$create_order = $('#create_order'); //创建订单
			that.$subCreateOrder = $('#subCreateOrder');
			that.$consumeGood = $('#consume_good');
			that.$memberSearch = $('#member-search');
			that.$memberSearch1 = $('#member-search1');
			that.$CreateOrderNoPay = $('#createOrderNoPay');
			that.$check_order = $('#check_order'); //创建订单
			that.$check_code = $('#check_code'); //创建订单
			that.$all_select = $('#all_select'); //创建订单
			that.deleOrder = $('#deleOrder'); //删除订单
			that.payOrder = $('#payOrder');//支付订单
			that.$ballType = $('#ballType'); //球类别
			that.$dateTimeList = $('#spaceDateTime'); //选择的日期
			that.URLParam = void 0; //url参数000000000000000000000000000000000000000列00000000000000表
			that.$ground_title = $('#ground_title'); //列表标题
			that.$input_date = $('#input_date'); //输入的日期

			that.Globe_cgId = void 0; //全局场馆id
			that.Globe_ballType = void 0; //全局的球类别
			that.Globe_ballTypeName = void 0; //全局的球类别名称
			that.Globe_dataTime = void 0; //全局的时间

			//that.ParseQueryObj(); //url参数对象
			that.BindDateTimeList(); //绑定日期信息
			that.LoadData(); //ajax加载该场馆的场地情况
			that.BindEvent();
			/*//‘quadratic’, ‘circular’, ‘back’, ‘bounce’, ‘elastic’滚动回弹效果*/
//			that.infoScroll = new IScroll('#groundManager', {
//				click: true,
//				scrollX: true,
//				scrollbars: 'custom',
//				bounceEasing: 'circular',
//				bounceTime: 700,
//				freeScroll: true,
//				deceleration: 0.001
//					//IScroll.utils.ease.elastic
//			});
		}
	}
	
	
	var myScroll;

    function loaded () {
        myScroll = new IScroll('#groundRight', {
        	click: true,
            scrollX: true,
            scrollY: true,
            momentum: false,
            snap: true,
            bounce:true,
            mouseWheel: true,
            probeType:3
        });

        myScroll.on('scrollStart', function () {
            $("#groundLCont").css('margin-top',this.y);
            $("#dateUl").css('margin-left',this.x);
        });
        myScroll.on('scroll', function(){
            $("#groundLCont").css('margin-top',this.y);
            $("#dateUl").css('margin-left',this.x);
        });
    }

    document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
    loaded();
    $(window).resize(function(){setWidth()});
    function setWidth(){
        var timeLen = $("#dateTime").find("li").length;
        var timeW = $("#dateTime").find("li:eq(0)").width();
        $("#dateTime").find("ul:eq(0)").width(timeLen*timeW);
        $("#groundRight").width($("#groundCont").width()-$("#groundLeft").width());
        $("#groundRight").height($(window).height()-430);
        var spaceCell = $("#scroller").find(".spaceGround:eq(0)");
        var cell = $(spaceCell).find(".cells:eq(0)");
        var cellW = cell.width()+1;
        var cellLen = spaceCell.find(".cells").length;

        $("#scroller").width(cellW*cellLen);
        myScroll.refresh();
    }
}]);
var carts = {};
var totalPrice = 0;
function newCart(id,name,price,bulk){
	if(id in carts){
		return;
	}
	if(parseFloat(price)==0){
		return;
	}
	carts[id] = {id:id,amount:1,price:parseFloat(price)};
	totalPrice+=parseFloat(price);
	$("#totalPrice").text(totalPrice+"元");
	$("#totalPrice").attr("totalPrice",totalPrice);
	var html = '<p style=\'position:relative;\' id="'+id+'"><span>'+name+':</span><span>'+bulk+'</span><input type="number" onkeyup="this.value=this.value.replace(/\D/g,\'\')" onchange="updateCart(\''+id+'\',this)" value="1"  style=\'width:46px;margin-left:10px;position:absolute;right:3px;text-align:center;\'></p>';
	$("#hua-lun").append(html);
}

function updateCart(id,obj){
	if($(obj).val()==='0'){
		delete carts[id];
		$(obj).parent().remove();
		return;
	}
	carts[id]["amount"] = $(obj).val();
	var totalPrice_=0;
	for(id in carts){
		totalPrice_ += carts[id]["amount"]*carts[id]["price"];
	}
	totalPrice=totalPrice_;
	$("#totalPrice").text(totalPrice+"元");
	$("#totalPrice").attr("totalPrice",totalPrice);
}

function balanceGoods(){
	var id=$("#mem_id").text();
	$.ajax( {  
	    url:'/admin/order/sell',// 跳转到 action  
	    data:JSON.stringify({  
	             memberId : id,  
	             carts : carts,
	             payType:$("#pay-type1").val()
	    }),  
	    type:'POST',  
	    cache:false,  
	    contentType: "application/json;charset=utf-8",
	    success:function(data) {
	    	data = eval("("+data+")");
	        if(data.result){  
	        	swal({
                    title: '购买成功！',
                });
	        	$("#goodsModal").modal('hide');
	        }else{
	        	if(data["data"]=='SYSTEM'){
	        		swal({
	                    title: '服务器出错，请重新再试！',
	                });
	        	}else if(data["data"]=='no_balance'){
	        		swal({
	                    title: '余额不足，请充值！',
	                });
	        	}
	        }  
	     }
	});
}
