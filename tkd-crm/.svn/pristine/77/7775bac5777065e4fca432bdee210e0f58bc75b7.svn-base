var spaceApp = angular.module('spaceApp',['ngRoute','crm.pagination'])

spaceApp.factory('SpaceService',['$http','$route',function($http,$route,$routeParams){
    return {
        loadSpace:function(){
            var spaceId = $route.current.params.id;
            return $http.post('/admin/space/'+spaceId,{cache:false});
        },
        initSpace:function(){
            return $http.post('/admin/space/init',{cache:false});
        },
        loadSpaces:function(page,size){
            return $http.post('/admin/space',{page:page,size:size},{cache:false});
        },
        batchSaveSpace:function(space){
        	return $http.post('/admin/space/batchSave',space);
        },
        saveSpace:function(space){
        	return $http.post('/admin/space/save',space);
        },
        deleteSpace:function(spaceId){
        	return $http.post('/admin/space/delete/'+spaceId,{cache:false});
        },
        changeStatus:function(spaceId,status){
        	return $http.post('/admin/space/change/',{id:spaceId,status:status},{cache:false});
        }
        
    }
}]);

spaceApp.filter("statusFilter", function() {
    var filterfun = function(input,seq) {
        if(input==1||input==undefined){
        	if(seq=='display'){
        		return "启用"
        	}else{
        		return "停用"
        	}
        }else{
        	if(seq=='display'){
        		return "停用"
        	}else{
        		return "启用"
        	}
        }
    };
    return filterfun;
});

spaceApp.filter("transSportType", function($rootScope) {
    var filterfun = function(input) {
    	return $rootScope.sportTypeMap[input];
    };
    return filterfun;
});


spaceApp.controller('loadSpacesController',['$scope','SpaceService','messageService','$location','$rootScope',function($scope,SpaceService,messageService,$location,$rootScope) {
	messageService.publish('breadcrumb',"perCenterIcon");
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        numberOfPage:0,
        pagesLength: 15,
        perPageOptions: [10, 20, 30, 40, 50],
        pagePromise:{},
        onChange: function(){
            var spacePromise = SpaceService.loadSpaces($scope.paginationConf.currentPage-1,$scope.paginationConf.itemsPerPage);
            $scope.paginationConf.pagePromise = spacePromise;
        }
    };
    $scope.deleteSpace = function(id){
    	var space = $scope.objs[id];
    	swal({
            title: "确定要删除吗?",
            text: "你将要删除这块场地!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var spacePromise = SpaceService.deleteSpace(space["id"]);
        	spacePromise.success(function(data){
        		if(data["result"]=="success"){
        			messageService.publish('notifyMessage',["场地删除成功！","success"]);
        			$scope.objs.splice(id,1);
        		}else{
        			messageService.publish('notifyMessage',[data["data"],"error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["场地删除失败！","error"]);
            });
        });
    }
    $scope.changeStatus = function(id){
    	var space = $scope.objs[id];
    	var status=space.status==1||space.status==undefined?0:(space.status+1);
    	var tip = space.status==1||space.status==undefined?"停用":"启用";
    	swal({
            title: "确定要"+tip+"吗?",
            text: "你将要"+tip+"这块场地!",
            type: "warning",
            showCancelButton: true,
            cancelButtonText:"取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
        	var spacePromise = SpaceService.changeStatus(space.id,status);
        	spacePromise.success(function(data){
        		if(data["result"]=='success'){
        			messageService.publish('notifyMessage',["场地状态"+tip+"成功！","success"]);
        			$scope.objs[id]["status"]=status;
        		}else{
        			messageService.publish('notifyMessage',[data["data"],"error"]);
        		}
            }).error(function(error){
            	messageService.publish('notifyMessage',["场地状态"+tip+"失败！","error"]);
            });
        });
    }
}]);

spaceApp.controller('loadSpaceController',['$scope','SpaceService','messageService','$location','$rootScope',function($scope,SpaceService,messageService,$location,$rootScope) {
	messageService.publish('breadcrumb',"perCenterIcon");
    var spacePromise = SpaceService.loadSpace();
    spacePromise.success(function(space){
        $scope.space = space["data"];
        $scope.BindAndEditWrap($scope.space["statiumPriceTempVo"]);
    }).error(function(error){
        console.log(error);
    });
    $scope.saveSpace = function(){
    	if($scope.space.spaceName==undefined || $scope.space.spaceName==''){
    		swal({
                title: "警告",
                text: "场地名称不能为空！"
            });
    		return;
    	}
    	if(!$scope.getPriceTempData()){
    		return false;
    	}
    	var spacePromise = SpaceService.saveSpace($scope.space);
    	spacePromise.success(function(data){
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["场地修改成功！","success"]);
    			$location.path('space');
    		}else{
    			messageService.publish('notifyMessage',[data["data"],"error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["场地修改失败！","error"]);
        });
    };
    
    
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
            var workingDaysAry = [];//存储工作日的信息
            var noWorkingDaysAry = [];//存储非工作日的信息
            var itemWrap = {'ballType': '', workingDays: workingDaysAry, noWorkingDays: noWorkingDaysAry};//data中的每一项
            var itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'seq': ''};//列表中的每一项

            var $v = $(wraps[0]);
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
            if (globalFlag && errFlag == 0 && errCount == 0) {
            	$scope.space.statiumPriceTempVo = itemWrap;
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
        	$scope.statium.statiumPriceTempVo = {};
        	return false;
        }
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


    //select change
    $('#contentByTemplate').on('change', '.wrap select', function (e) {
        var e = e.target || e.srcElement;
        $scope.DomChangeInThisTable($(e));
    });
    
  //绑定json
    $scope.BindAndEditWrap = function (data){
        var html = [];//保存html
        var timeNow = new Date().getTime().toString();//当前时间戳
        if (data) {
            html.push('<div class="wrap" ballTypeCode="' + data['ballType'] + '">');
            html.push('<div class="ball-type-title">' + $rootScope.sportTypeMap[data['ballType']] + '<span class="btn_close">关闭</span></div>');
            if (data['spaceWorkingDays'] && data['spaceWorkingDays'].length && (Object.prototype.toString.call(data['spaceWorkingDays']).indexOf('Array') > 0)) {
                html.push('<div class="working-days-layer">');
                html.push('<table class="table table-bordered user-reset">');
                html.push('<caption>工作日</caption>');
                html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>价格</th><th><button class="btn_add_row">新增</button></th></tr></thead>');
                html.push('<tbody>');

                $.each(data['spaceWorkingDays'], function (item, val) {
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


            if (data['spaceNoWorkingDays'] && data['spaceNoWorkingDays'].length && (Object.prototype.toString.call(data['spaceNoWorkingDays']).indexOf('Array') > 0)) {
                html.push('<div class="no-working-days-layer">');
                html.push('<table class="table table-bordered user-reset">');
                html.push('<caption>非工作日</caption>');
                html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>价格</th><th><button class="btn_add_row">新增</button></th></tr></thead>');
                html.push('<tbody>');

                $.each(data['spaceNoWorkingDays'], function (item, val) {
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
    	}
        $('#contentByTemplate').html(html.join(''));
    };

}]);

spaceApp.controller('initSpaceController',['$rootScope','$scope','SpaceService','messageService','$location',function($rootScope,$scope,SpaceService,messageService,$location) {
	messageService.publish('breadcrumb',"perCenterIcon");
    var spacePromise = SpaceService.initSpace();
    $scope.sportTypesMap = $rootScope.sportTypeMap;
    spacePromise.success(function(space){
        $scope.space = space["data"];
        $scope.sportTypes = $scope.space.sportTypes.substring(0,$scope.space.sportTypes.length-2).split(";;");
    }).error(function(error){
        console.log(error);
    });
    $scope.sportPrefixMap = {"BASKETBALL":"B","FOOTBALL":"F","BADMINTON":"B","TENNIS":"T","TABLE_TENNIS":"T","BILLIARDS":"B","BOWLING":"B","GOLF":"G"};
    $scope.saveBatchSpace = function(){
    	if($scope.space.sportType==undefined || $scope.space.sportType==''){
    		swal({
                title: "警告",
                text: "请选择场地类型！"
            });
    		return;
    	}
    	$scope.space.sportPrix = $scope.sportPrefixMap[$scope.space.sportType];
    	var spaceNum = $scope.space.spaceNum;
    	if(spaceNum==undefined || spaceNum==''){
    		swal({
                title: "警告",
                text: "请输入场地数量！"
            });
    		return;
    	}
    	var reg_num = new RegExp("^[0-9]*$");
    	if(!reg_num.test(spaceNum)){
    		swal({
                title: "警告",
                text: "请输入正确的场地数量！"
            });
    		return;
    	}
    	if(parseInt(spaceNum)>20){
    		swal({
                title: "警告",
                text: "批量建场地的数量不能超过20个！"
            });
    		return;
    	}
    	if(!$scope.getPriceTempData()){
    		return false;
    	}
    	$scope.space.priceTempVos = null;
    	var spacePromise = SpaceService.batchSaveSpace($scope.space);
    	spacePromise.success(function(data){
    		if(data["result"]=='success'){
    			messageService.publish('notifyMessage',["场地保存成功！","success"]);
    			$location.path('space');
    		}else{
    			messageService.publish('notifyMessage',[data["data"],"error"]);
    		}
        }).error(function(error){
        	messageService.publish('notifyMessage',["场地保存失败！","error"]);
        });
    };
    
    $scope.changeSportType = function(sportType){
    	var index = $scope.sportTypes.indexOf(sportType);
    	$scope.space.sportPrix=$scope.sportTypeMap[sportType];
    	var data = {"ballType":sportType};
    	$scope.BindAndEditWrap($scope.space.priceTempVos[index]);
    }
    
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
            var workingDaysAry = [];//存储工作日的信息
            var noWorkingDaysAry = [];//存储非工作日的信息
            var itemWrap = {'ballType': '', workingDays: workingDaysAry, noWorkingDays: noWorkingDaysAry};//data中的每一项
            var itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'seq': ''};//列表中的每一项

            var $v = $(wraps[0]);
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
            if (globalFlag && errFlag == 0 && errCount == 0) {
            	$scope.space.statiumPriceTempVo = itemWrap;
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
        	$scope.statium.statiumPriceTempVo = {};
        	return false;
        }
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


    //select change
    $('#contentByTemplate').on('change', '.wrap select', function (e) {
        var e = e.target || e.srcElement;
        $scope.DomChangeInThisTable($(e));
    });
    
  //绑定json
    $scope.BindAndEditWrap = function (data){
        var html = [];//保存html
        var timeNow = new Date().getTime().toString();//当前时间戳
        if (data) {
            html.push('<div class="wrap" ballTypeCode="' + data['ballType'] + '">');
            html.push('<div class="ball-type-title">' + $rootScope.sportTypeMap[data['ballType']] + '<span class="btn_close">关闭</span></div>');
            if (data['workingDays'] && data['workingDays'].length && (Object.prototype.toString.call(data['workingDays']).indexOf('Array') > 0)) {
                html.push('<div class="working-days-layer">');
                html.push('<table class="table table-bordered user-reset">');
                html.push('<caption>工作日</caption>');
                html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>价格</th><th><button class="btn_add_row">新增</button></th></tr></thead>');
                html.push('<tbody>');

                $.each(data['workingDays'], function (item, val) {
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


            if (data['noWorkingDays'] && data['noWorkingDays'].length && (Object.prototype.toString.call(data['noWorkingDays']).indexOf('Array') > 0)) {
                html.push('<div class="no-working-days-layer">');
                html.push('<table class="table table-bordered user-reset">');
                html.push('<caption>非工作日</caption>');
                html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>价格</th><th><button class="btn_add_row">新增</button></th></tr></thead>');
                html.push('<tbody>');

                $.each(data['noWorkingDays'], function (item, val) {
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
    	}
        $('#contentByTemplate').html(html.join(''));
    };

}]);