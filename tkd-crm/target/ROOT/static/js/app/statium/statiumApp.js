var statiumApp = angular.module('statiumApp',['crm.uploadify'])

statiumApp.factory('StatiumService',['$http',function($http){
    return {
        loadZoneCode:function(){
            return $http.post('/static/js/app/common/zoneCode.json',{cache:true});
        },
        loadZoneMap:function(){
            return $http.post('/static/js/app/common/zoneMap.json',{cache:true});
        },
        saveStatium:function(data){
        	return $http.post('/admin/statium/save',data);
        },
        loadStatium:function(data){
        	return $http.post('/admin/statium',data);
        },
    }
}]);

statiumApp.filter("bisTimeFilter", function() {
    var filterfun = function(input,seq) {
        return "12"
    };
    return filterfun;
});


statiumApp.controller('loadStatiumController',['$scope','$rootScope','messageService','StatiumService','$q','$timeout',function($scope,$rootScope,messageService,StatiumService,$q,$timeout) {
    messageService.publish('breadcrumb',"perCenterIcon");
    var statiumPromise = StatiumService.loadStatium();
    var zoneCodePromise = StatiumService.loadZoneCode();
    var zoneMapPromise = StatiumService.loadZoneMap();
    $q.all([zoneCodePromise,zoneMapPromise,statiumPromise]).then(function(datas){
    	$scope.provinceCodes = datas[0]["data"]["000000"].sort();
    	$scope.zoneCodes = datas[0]["data"];
    	$scope.zoneMap = datas[1]["data"];
    	$scope.statium = datas[2]["data"]["data"];
    	messageService.publish('statiumName',$scope.statium.name);
    	if(!$scope.statium.facilities){
        	$scope.statium.facilities="";
        }
        if(!$scope.statium.sportType){
        	$scope.statium.sportType="";
        }
        if($scope.statium.id){
        	var areaCode = $scope.statium.areaCode;
            var provincePart = new RegExp("[0-9]{2}0000");
            var cityPart = new RegExp("[0-9]{4}00");
            if(provincePart.test(areaCode)){
            	$scope.provinceSel = areaCode;
            }else if(cityPart.test(areaCode)){
            	$scope.provinceSel = areaCode.substring(0,areaCode.length-4)+"0000";
            	$scope.citySel = areaCode;
            }else{
            	$scope.provinceSel = areaCode.substring(0,areaCode.length-4)+"0000";
            	$scope.citySel = areaCode.substring(0,areaCode.length-2)+"00";
            	$scope.areaSel = areaCode;
            }
            $scope.photos=$scope.statium.photos.split("__");
            if($scope.statium.statiumPriceTempVos.length!=0){
            	$scope.BindAndEditWrap($scope.statium.statiumPriceTempVos);
            }else{
            	var sportTypeArray = $scope.statium.sportType.split(";;");
            	for(var index=0;index<sportTypeArray.length-1;index++){
            		$scope.initTemp(sportTypeArray[index]);
            	}
            }
        }
        $scope.selectFacilitie = function(facilitie){
        	if($scope.statium.facilities.indexOf(facilitie+",")!=-1){
        		$scope.statium.facilities = $scope.statium.facilities.replace(facilitie+",","");
        	}else if($scope.statium.facilities.indexOf(facilitie)!=-1){
        		$scope.statium.facilities = $scope.statium.facilities.replace(facilitie,"");
        	}else{
        		if($scope.statium.facilities.lastIndexOf(",")==$scope.statium.facilities.length-1){
        			$scope.statium.facilities+=facilitie+",";
        		}else{
        			$scope.statium.facilities+=","+facilitie+",";
        		}
        	}
        }
        
        $scope.isFacilitieChecked = function(facilitie){
        	return $scope.statium.facilities.indexOf(facilitie);
        }
        
        $scope.selectSportType = function(sportType){
        	var sportTypeCheckeds = $scope.statium.sportType.split(";;"); 
        	if(sportTypeCheckeds.indexOf(sportType)!=-1){
        		
        	}else{
        		$scope.statium.sportType+=sportType+";;";
        		$scope.initTemp(sportType);
        	}
        }
        
        $scope.isSportTypeChecked = function(sportType){
        	var sportTypeCheckeds = $scope.statium.sportType.split(";;"); 
        	return sportTypeCheckeds.indexOf(sportType);
        }
        
    },function(error){
    	console.log(error);
    });
    $scope.facilities = ["储物柜","更衣室","热水淋浴","商店","室内","停车场","wifi","休息室","夜场灯光","pos","主场裁判","主场教练","租赁"];
    
    $scope.showMap = function(){
    	$scope.isShow = true;
    	$timeout(function(){
    		$scope.initMap();
    	});
	};
	$scope.initMap = function(){
		var lng = $scope.statium.lng;
		var lng_ = 116.403867;
		var lat_ = 39.914113;
		var name_ = "天安门";
		if(lng){
			lat_ = $scope.statium.lat;
			lng_ = $scope.statium.lng;
			name_ = $scope.statium.name;
		}
		// 百度地图API功能
        var map_ = new BMap.Map("allmap");
        var point = new BMap.Point(lng_, lat_);
        map_.centerAndZoom(point, 15);
        map_.enableScrollWheelZoom();
        var marker = new BMap.Marker(point);  //创建标注
        map_.addOverlay(marker);                 // 将标注添加到地图中
        var infoWindow1 = new BMap.InfoWindow(name_);
        marker.addEventListener("click", function (e) {
           this.openInfoWindow(infoWindow1, false);
        });
        $scope.searchAddr = function(){
        	var local = new BMap.LocalSearch(map_, {
    			renderOptions:{map: map_}
    		}); 
    		local.search($scope.keyword);
        };
        
        // 获取经纬度和地址
        map_.addEventListener("click", function(e){
        	$scope.$apply(function(){
            	$scope.statium.lng = e.point.lng 
            	$scope.statium.lat = e.point.lat;
            });
        	var gc = new BMap.Geocoder(); 
            gc.getLocation(e.point, function(rs) {
             	var addComp = rs.addressComponents; 
             	var address = '';
                address += addComp.province;
                address += addComp.city;
                address += addComp.district;
                address += addComp.street;
                address += addComp.streetNumber;
                $scope.$apply(function(){
                	$scope.statium.address = address;
                });
             });
        });
	};
	$scope.sportTypes    =    ["篮球","足球","羽毛球","网球","乒乓球","台球","保龄球","高尔夫"];
	$scope.sportTypesKey =    ["BASKETBALL","FOOTBALL","BADMINTON","TENNIS","TABLE_TENNIS","BILLIARDS","BOWLING","GOLF"];
  //初始化数据
    $scope.data = {
        ball_type: '',
        ball_type_code: '',
        guidFlag_1: '',
        guidFlag_2: '',
        guidFlag_3: '',
        guidFlag_4: '',
        guidFlag_5: '',
        guidFlag_6: '',
        guidFlag_7: '',
        guidFlag_8: '',
        guidFlag_9: '',
        guidFlag_10: '',
        guidFlag_11: '',
        guidFlag_12: ''
    };
    //点击按钮创建模板，并填入数据
    $scope.initTemp = function (ball_type) {
        var timeNow = new Date().getTime().toString();//当前时间戳
        $scope.data['ball_type'] = $rootScope.sportTypeMap[ball_type];
        $scope.data['ball_type_code'] = ball_type;
        $scope.data['guidFlag_1'] = 'cls1_' + timeNow;
        $scope.data['guidFlag_2'] = 'cls2_' + timeNow;
        $scope.data['guidFlag_3'] = 'cls3_' + timeNow;
        $scope.data['guidFlag_4'] = 'cls4_' + timeNow;
        $scope.data['guidFlag_5'] = 'cls5_' + timeNow;
        $scope.data['guidFlag_6'] = 'cls6_' + timeNow;
        $scope.data['guidFlag_7'] = 'cls7_' + timeNow;
        $scope.data['guidFlag_8'] = 'cls8_' + timeNow;
        $scope.data['guidFlag_9'] = 'cls9_' + timeNow;
        $scope.data['guidFlag_10'] = 'cls10_' + timeNow;
        $scope.data['guidFlag_11'] = 'cls11_' + timeNow;
        $scope.data['guidFlag_12'] = 'cls12_' + timeNow;
        var html_old = $('#contentByTemplate').html(); //旧html字符串
        // 附上模板
        $("#contentByTemplate").setTemplateElement("template");
        // 给模板加载数据
        $("#contentByTemplate").processTemplate($scope.data);
        var html_new = $('#contentByTemplate').html(); //新html字符串
        $('#contentByTemplate').html(html_old + html_new);
      //新增时间段tr
        
    };

    $('#contentByTemplate').on('click', '.wrap .btn_add_row', function (e) {
        var timeNow = new Date().getTime().toString();//当前时间戳
        var e = e.target || e.srcElement;
        var $e = $(e);
        var $eTbody = $e.parents('thead').siblings('tbody'); //tbody
        var $time_others_tr = $eTbody.children('.time_others_tr'); //其他时间段tr

        //遍历dom,获取select中的值的集合
        var selectValueAry = []; //select值的集合
        var TrAry = $eTbody.children('tr'); //所有tr
        $.each(TrAry, function (iTr, vTr) {
            var TdAry = $(vTr).children('td'); //当前tr中所有td
            $.each(TdAry, function (iTd, vTd) {
                var selectOfvTd = $(vTd).find('select'); //当前td中的select
                if (selectOfvTd && selectOfvTd.length) {
                    selectValueAry.push(selectOfvTd.children('option:selected').val()); //当前table中所有的select中选中的值
                }
            })
        });
        var maxSelectedValue = selectValueAry[selectValueAry.length - 1];//集合中的最大值
        maxSelectedValue = maxSelectedValue ? maxSelectedValue : '24';//集合中的最大值
        if (!selectValueAry.length) {
            maxSelectedValue = '0';
        }
        var htmlOptionFirst = '', htmlOptionSecond = '', iTmp = '', jTmp = '', maxValueNext = '';
        if (maxSelectedValue != '24') {
            for (var i = maxSelectedValue; i <= 24; i++) {
                //遍历并绑定数据
                iTmp = i;
                iTmp = iTmp.toString().length == 1 ? '0' + iTmp : iTmp;
                htmlOptionFirst += '<option value="' + iTmp + '">' + iTmp + ':00' + '</option>';
            }
            maxValueNext = parseInt(maxSelectedValue) + 1;//后面的select从下一时刻开始
            for (var j = maxValueNext; j <= 24; j++) {
                jTmp = j;
                jTmp = jTmp.toString().length == 1 ? '0' + jTmp : jTmp;
                htmlOptionSecond += '<option value="' + jTmp + '">' + jTmp + ':00' + '</option>';
            }
        } else {
            htmlOptionFirst = '';
            htmlOptionSecond = '';
        }

        var html = [];
        html.push('<tr>');
        html.push('<td><input type="text" name="title" value=""/></td>');
        html.push('<td><select name="startTime" guidFlag="' + 'cls1_' + timeNow + '">' + htmlOptionFirst + '</select></td>');
        html.push('<td><select name="endTime" guidFlag="' + 'cls2_' + timeNow + '">' + htmlOptionSecond + '</select></td>');
        html.push('<td><input type="text" name="price" value="80"/></td>');
        html.push('<td><button class="btn_del_row">删除</button></td>');
        html.push('</tr>');
        $(html.join('')).fadeIn(600).insertBefore($time_others_tr);
        return false;
    });

    //删除时间段tr
    $('#contentByTemplate').on('click', '.wrap .btn_del_row', function (e) {
        var e = e.target || e.srcElement;
        var $e = $(e);
        var $eTr = $e.parents('tr');
        $eTr.fadeOut(600);
        setTimeout(function () {
            $eTr.remove();
        }, 800);
        return false;
    });

    //关闭
    $('#contentByTemplate').on('click', '.wrap .btn_close', function (e) {
        var e = e.target || e.srcElement;
        var $e = $(e);
        var $eDiv = $e.parents('div.wrap');
        var balltype = $eDiv.attr("ballTypeCode");
        $scope.$apply(function(){
        	$scope.statium.sportType = $scope.statium.sportType.replace(balltype+";;","");
        });
        $eDiv.fadeOut(600);
        setTimeout(function () {
            $eDiv.remove();
        }, 800);
    });

    //select change
    $('#contentByTemplate').on('change', '.wrap select', function (e) {
        var e = e.target || e.srcElement;
        $scope.DomChangeInThisTable($(e));
    });
    
    //dom改变,委托
    $scope.DomChangeInThisTable = function($e) {
        //$e为源select
        var $eTBody = $e.parents('tbody');
        var TrAry = $eTBody.children('tr'); //所有tr
        //var ary = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24];
        var selectAry = []; //select的集合
        $.each(TrAry, function (iTr, vTr) {
            var TdAry = $(vTr).children('td'); //当前tr中所有td
            $.each(TdAry, function (iTd, vTd) {
                var selectOfvTd = $(vTd).find('select'); //当前td中的select
                if (selectOfvTd && selectOfvTd.length) {
                    selectAry.push(selectOfvTd); //当前table中所有的select
                }
            })
        });
        if (selectAry && selectAry.length) {
            var curSelectedVal = parseInt($e.children('option:selected').val()); //选中的那个select的value
            for (var i = 0, selectAryLen = selectAry.length; i < selectAryLen; i++) {
                //判断当前select在ary中的索引,取出索引，给后面的select的option循环赋值
                if ($e.attr('guidFlag') == selectAry[i].attr('guidFlag')) {
                    //alert(i + '==>' + curSelectedVal);//i 为当前select索引，curSelectedVal为当前select选中的值
                	$scope.ChangeSelectValue(selectAry, i, curSelectedVal); //i 为当前select索引，curSelectedVal为当前select选中的值
                }
            }
        }
    }

    $scope.ChangeSelectValue = function(ary, beginIndex, beginValue) {
        if (ary && Object.prototype.toString.call(ary).indexOf('Array') > 0) {
            var aryLen = ary.length;

            var startIndex = beginIndex;//存储最初传进来的值
            var startValue = beginValue;//存储最初传进来的值
            //var startIndexNext = beginIndex == (aryLen - 1) ? beginIndex : beginIndex + 1;//本次开始的索引
            var startIndexNext = beginIndex + 1;
            var startValueNext = beginValue == 24 ? beginValue : beginValue + 1;//本次开始的option值

            var optionHTML = ''; //option的html

            for (var i = startIndexNext; i < aryLen; i++) {
                optionHTML = '';
                if (startValue == 24) {
                    //如果当前为选中的是最后一个select
                    if (startIndex == (aryLen - 1)) {
                        optionHTML = '<option value="' + startValue + '">' + startValue + ':00' + '</option>';
                    }
                } else {
                    if (startIndexNext % 2 == 0) {//偶数索引位置+1，奇数索引位置值不变
                        if (startValueNext == 25) {

                        } else {
                            startValueNext--;
                        }
                    }
                    for (var j = startValueNext; j <= 24; j++) {
                        j = j.toString().length == 1 ? '0' + j : j;
                        optionHTML += '<option value="' + j + '">' + j + ':00' + '</option>';
                    }
                    startIndexNext++;
                    startValueNext++;
                }
                ary[i].html(optionHTML);//数组中的每一项都是$对象
            }
        }
    }

    $scope.CheckInput = function (title, beginTime, endTime, price, globalFlag, errFlag, errCount, isLastTr) {
        if (!title || !title.length) {
            globalFlag = false;
            errFlag = 1;
            errCount++;
        }
        if (!beginTime || !beginTime.length) {
            if (isLastTr) {
                //最后一个tr，不用判断
            } else {
                globalFlag = false;
                errFlag = 2;
                errCount++;
            }
        }
        if (!endTime || !endTime.length) {
            if (isLastTr) {
                //最后一个tr，不用判断
            } else {
                globalFlag = false;
                errFlag = 2;
                errCount++;
            }
        }
        if (!price || !price.length || price < 0 || price.toString().indexOf('.') > 0) {
            globalFlag = false;
            errCount++;
            if (!price || !price.length) {
                errFlag = 3;
            } else if (price < 0) {
                errFlag = 4;
            } else if (price.toString().indexOf('.') > 0) {
                errFlag = 5;
            }
        }
        return {'globalFlag': globalFlag, 'errFlag': errFlag, 'errCount': errCount};
    }
    
    $scope.submitForm = function(){
    	if($scope.statium.name==undefined || $scope.statium.name==''){
    		swal({
                title: "警告",
                text: "场馆名称不能为空！"
            });
    		return;
    	}
    	
    	if($scope.statium.logo==undefined || $scope.statium.logo==''){
    		swal({
                title: "警告",
                text: "请上传球馆LOGO！"
            });
    		return;
    	}
    	
    	if($scope.statium.startTime==undefined || $scope.statium.startTime==''||$scope.statium.endTime==undefined || $scope.statium.endTime==''){
    		swal({
                title: "警告",
                text: "场馆营业时间不能为空！"
            });
    		return;
    	}
    	
    	var reg_num = new RegExp("^[0-9]*$");
    	if(!reg_num.test($scope.statium.startTime)||!reg_num.test($scope.statium.endTime)){
    		swal({
                title: "警告",
                text: "营业时间必须是数字！"
            });
    		return;
    	}
    	if(parseInt($scope.statium.startTime,10)>=24||parseInt($scope.statium.endTime,10)>=24){
    		swal({
                title: "警告",
                text: "营业时间必须小于24！"
            });
    		return;
    	}
    	$scope.statium.province = $scope.provinceSel?$scope.zoneMap[$scope.provinceSel]:"";
    	$scope.statium.city = $scope.citySel?$scope.zoneMap[$scope.citySel]:"";
    	$scope.statium.area = $scope.areaSel?$scope.zoneMap[$scope.areaSel]:"";
    	$scope.statium.areaCode = $scope.areaSel?$scope.areaSel:($scope.citySel?$scope.citySel:($scope.provinceSel?$scope.provinceSel:""));
    	if($scope.statium.areaCode==undefined || $scope.statium.areaCode==''){
    		swal({
                title: "警告",
                text: "请选择球馆所在地区！"
            });
    		return;
    	}
    	if($scope.statium.lng==undefined || $scope.statium.lng==''||$scope.statium.lat==undefined || $scope.statium.lat==''){
    		swal({
                title: "警告",
                text: "球馆坐标不能为空！"
            });
    		return;
    	}
    	if($scope.statium.address==undefined || $scope.statium.address==''){
    		swal({
                title: "警告",
                text: "球馆详细地址不能为空！"
            });
    		return;
    	}
    	if($scope.statium.tel==undefined || $scope.statium.tel==''){
    		swal({
                title: "警告",
                text: "球馆订场电话不能为空！"
            });
    		return;
    	}
    	if($scope.statium.masterName==undefined || $scope.statium.masterName==''){
    		swal({
                title: "警告",
                text: "球馆负责人姓名不能为空！"
            });
    		return;
    	}
    	if($scope.statium.masterTel==undefined || $scope.statium.masterTel==''){
    		swal({
                title: "警告",
                text: "联系电话不能为空！"
            });
    		return;
    	}
    	if($scope.statium.facilities==undefined || $scope.statium.facilities==''){
    		swal({
                title: "警告",
                text: "请选择场馆设施！"
            });
    		return;
    	}
    	$scope.statium.facilities = $scope.statium.facilities.substr(0,$scope.statium.facilities.length-1);
    	if($scope.statium.introduce==undefined || $scope.statium.introduce==''){
    		swal({
                title: "警告",
                text: "场馆介绍不能为空！"
            });
    		return;
    	}
    	$scope.statium.photos = $scope.photos.join("__");
    	if($scope.statium.photos==undefined || $scope.statium.photos==''){
    		swal({
                title: "警告",
                text: "请上传球馆图片！"
            });
    		return;
    	}
    	if($scope.statium.sportType==undefined || $scope.statium.sportType==''){
    		swal({
                title: "警告",
                text: "请选择球馆运动类型！"
            });
    		return;
    	}
    	if(!$scope.getPriceTempData()){
    		return false;
    	}
    	StatiumService.saveStatium($scope.statium).success(function(data){
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["场馆保存成功！","success"]);
    		}else{
    			messageService.publish('notifyMessage',[data["data"],"error"]); 
    		}
    	}).error(function(error){
    		messageService.publish('notifyMessage',["场馆保存失败！","error"]);
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


    //遍历得到JSON
    $scope.getPriceTempData = function () {
        var wraps = $('#contentByTemplate>.wrap');//wrap集合
        var dataWrap = [];//存储数据的数组
        var globalFlag = true;//全局标识
        var errFlag = 0;//1:title为空;2:时间为空;3:价格为空;4:价格为负数;5:价格为小数;6:签约价大于原价
        var errCount = 0;//错误计数
        if (wraps && wraps.length) {
            $.each(wraps, function (i, v) {

                var workingDaysAry = [];//存储工作日的信息
                var noWorkingDaysAry = [];//存储非工作日的信息
                var itemWrap = {'ballType': '', workingDays: workingDaysAry, noWorkingDays: noWorkingDaysAry};//data中的每一项
                var itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'seq': ''};//列表中的每一项

                var $v = $(v);
                itemWrap['ballType'] = $v.attr('balltypecode');//类型

                //遍历获取每一项
                var workingTrs = $v.find('.working-days-layer tbody>tr');//工作日table
                var noWorkingTrs = $v.find('.no-working-days-layer tbody>tr');//非工作日table

                $.each(workingTrs, function (iTr, vTr) {
                    var $vTr = $(vTr);//每一行的tr
                    itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'seq': ''};//每次都New一个对象

                    var title = $vTr.find('[name=title]').val();
                    var beginTime = $vTr.find('[name=startTime]').children('option:selected').val();
                    var endTime = $vTr.find('[name=endTime]').children('option:selected').val();
                    var price = $vTr.find('[name=price]').val();

                    var rst = $scope.CheckInput(title, beginTime, endTime, price, globalFlag, errFlag, 0, (iTr == workingTrs.length - 1) ? true : false);
                    globalFlag = rst['globalFlag'];
                    errFlag = rst['errFlag'];
                    errCount += parseInt(rst['errCount']);//累加错误计数

                    if (globalFlag == true && errFlag == 0 && errCount == 0) {
                        itemList['title'] = title;
                        itemList['startTime'] = beginTime;
                        itemList['endTime'] = endTime;
                        itemList['price'] = price;
                        itemList['seq'] = iTr;
                        workingDaysAry.push(itemList);
                    }
                });

                $.each(noWorkingTrs, function (itemTr, valueTr) {
                    var $vTr = $(valueTr);//每一行的tr
                    itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '',  'seq': ''};//每次都New一个对象

                    var title = $vTr.find('[name=title]').val();
                    var beginTime = $vTr.find('[name=startTime]').children('option:selected').val();
                    var endTime = $vTr.find('[name=endTime]').children('option:selected').val();
                    var price = $vTr.find('[name=price]').val();

                    var rst = $scope.CheckInput(title, beginTime, endTime, price, globalFlag, errFlag, 0, (itemTr == noWorkingTrs.length - 1) ? true : false);
                    globalFlag = rst['globalFlag'];
                    errFlag = rst['errFlag'];
                    errCount += parseInt(rst['errCount']);//累加错误计数

                    if (globalFlag == true && errFlag == 0 && errCount == 0) {
                        itemList['title'] = $vTr.find('[name=title]').val();
                        itemList['startTime'] = $vTr.find('[name=startTime]').children('option:selected').val();
                        itemList['endTime'] = $vTr.find('[name=endTime]').children('option:selected').val();
                        itemList['price'] = $vTr.find('[name=price]').val();
                        itemList['seq'] = itemTr;
                        noWorkingDaysAry.push(itemList);
                    }
                });
                dataWrap.push(itemWrap);
            });
            if (globalFlag && errFlag == 0 && errCount == 0) {
            	$scope.statium.statiumPriceTempVos = dataWrap;
            	return true;
            } else {
                //有错
                var errmsg = '';
                switch (errFlag) {
                    case 1:
                        errmsg = '时段名称不能为空！';
                        break;
                    case 2:
                        errmsg = '时间不能为空！';
                        break;
                    case 3:
                        errmsg = '价格不能为空！';
                        break;
                    case 4:
                        errmsg = '价格不能为负数！';
                        break;
                    case 5:
                        errmsg = '价格不能为小数！';
                        break;
                    case 6:
                        errmsg = '签约价不能大于原价！';
                        break;
                }
                swal({
                    title: "警告",
                    text: errmsg
                });
                return false;
            }
        }else{
        	$scope.statium.statiumPriceTempVo = [];
        	return false;
        }
    };

    //绑定json
    $scope.BindAndEditWrap = function (data){
        var html = [];//保存html
        var timeNow = new Date().getTime().toString();//当前时间戳
        if (data && data.length && (Object.prototype.toString.call(data).indexOf('Array') > 0)) {
            $.each(data, function (i, v) {
                html.push('<div class="wrap" ballTypeCode="' + v['ballType'] + '">');
                html.push('<div class="ball-type-title">' + $rootScope.sportTypeMap[v['ballType']] + '<span class="btn_close">关闭</span></div>');
                if (v['workingDays'] && v['workingDays'].length && (Object.prototype.toString.call(v['workingDays']).indexOf('Array') > 0)) {
                    html.push('<div class="working-days-layer">');
                    html.push('<table class="table table-bordered user-reset">');
                    html.push('<caption>工作日</caption>');
                    html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>价格</th><th><button class="btn_add_row">新增</button></th></tr></thead>');
                    html.push('<tbody>');

                    $.each(v['workingDays'], function (item, val) {
                        if (val['title'] != '其他时段') {
                            html.push('<tr>');
                            html.push('<td><input type="text" name="title" value="' + val['title'] + '" /></td>');

                            //开始时间
                            html.push('<td><select name="startTime" guidFlag="workingDays' + parseInt(item) + parseInt(val['startTime']) + '_' + timeNow + '">');
                            for (var i = parseInt(val['startTime']); i <= 24; i++) {
                                var iTmp = i;
                                iTmp = iTmp.toString().length == 1 ? '0' + iTmp : iTmp;
                                html.push('<option value="' + iTmp + '">' + iTmp + ':00</option>');
                            }
                            html.push('</select></td>');

                            //结束时间
                            html.push('<td><select name="endTime" guidFlag="workingDays' + parseInt(item) + parseInt(val['endTime']) + '_' + timeNow + '">');
                            for (var j = parseInt(val['endTime']); j <= 24; j++) {
                                var jTmp = j;
                                jTmp = jTmp.toString().length == 1 ? '0' + jTmp : jTmp;
                                html.push('<option value="' + jTmp + '">' + jTmp + ':00</option>');
                            }
                            html.push('</select></td>');

                            html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                            html.push('<td><button class="btn_del_row">删除</button></td>');
                            html.push('</tr>');
                        } else {
                            html.push('<tr class="time_others_tr">');
                            html.push('<td><input type="hidden" name="title" value="' + val['title'] + '">' + val['title'] + '</td>');
                            html.push('<td></td>');
                            html.push('<td></td>');
                            html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                            html.push('<td></td>');
                            html.push('</tr>');
                        }
                    });
                    html.push('</tbody>');
                    html.push('</table>');
                    html.push('</div>');
                }


                html.push('<hr />');


                if (v['noWorkingDays'] && v['noWorkingDays'].length && (Object.prototype.toString.call(v['noWorkingDays']).indexOf('Array') > 0)) {
                    html.push('<div class="no-working-days-layer">');
                    html.push('<table class="table table-bordered user-reset">');
                    html.push('<caption>非工作日</caption>');
                    html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>价格</th><th><button class="btn_add_row">新增</button></th></tr></thead>');
                    html.push('<tbody>');

                    $.each(v['noWorkingDays'], function (item, val) {
                        if (val['title'] != '其他时段') {
                            html.push('<tr>');
                            html.push('<td><input type="text" name="title" value="' + val['title'] + '" /></td>');

                            //开始时间
                            html.push('<td><select name="startTime" guidFlag="noWorkingDays' + parseInt(item) + parseInt(val['startTime']) + '_' + timeNow + '">');
                            for (var i = parseInt(val['startTime']); i <= 24; i++) {
                                var iTmp = i;
                                iTmp = iTmp.toString().length == 1 ? '0' + iTmp : iTmp;
                                html.push('<option value="' + iTmp + '">' + iTmp + ':00</option>');
                            }
                            html.push('</select></td>');

                            //结束时间
                            html.push('<td><select name="endTime" guidFlag="noWorkingDays' + parseInt(item) + parseInt(val['endTime']) + '_' + timeNow + '">');
                            for (var j = parseInt(val['endTime']); j <= 24; j++) {
                                var jTmp = j;
                                jTmp = jTmp.toString().length == 1 ? '0' + jTmp : jTmp;
                                html.push('<option value="' + jTmp + '">' + jTmp + ':00</option>');
                            }
                            html.push('</select></td>');

                            html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                            html.push('<td><button class="btn_del_row">删除</button></td>');
                            html.push('</tr>');
                        } else {
                            html.push('<tr class="time_others_tr">');
                            html.push('<td><input type="hidden" name="title" value="' + val['title'] + '">' + val['title'] + '</td>');
                            html.push('<td></td>');
                            html.push('<td></td>');
                            html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                            html.push('<td></td>');
                            html.push('</tr>');
                        }
                    });
                    html.push('</tbody>');
                    html.push('</table>');
                    html.push('</div>');
                }
                html.push('</div>');
            });
        }
        $('#contentByTemplate').html(html.join(''));
    }
    
    
    
}]);