$(function () {

    //初始化数据
    var data = {
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
        guidFlag_12: '',
        data: [{
            id: 1,
            name: 'Rain',
            age: 22,
            mail: 'cssrain@domain.com'
        }, {
            id: 2,
            name: '皮特',
            age: 24,
            mail: 'cssrain@domain.com'
        }, {
            id: 3,
            name: '卡卡',
            age: 20,
            mail: 'cssrain@domain.com'
        }, {
            id: 4,
            name: '戏戏',
            age: 26,
            mail: 'cssrain@domain.com'
        }, {
            id: 5,
            name: '一揪',
            age: 25,
            mail: 'cssrain@domain.com'
        }],
    };

    $('#contentByTemplate').on('change','select', function (e) {
    	 $(this).find("option[value="+$(this).val()+"]").attr("selected", true);
    });
    
    $('#contentByTemplate').on('change','input[type=text]', function (e) {
    	$(this).attr("value",$(this).val());
    });
    
    //点击按钮创建模板，并填入数据
    $('.ball').on('click', function (e) {
        var timeNow = new Date().getTime().toString();//当前时间戳
        var e = e.target || e.srcElement;
        var $e = $(e);
        var ball_type = $e.attr('ballType'); //球类别
        var ball_type_code = $e.attr('ballTypeCode'); //类样式区分
        var subsidies = $e.attr('subsidies'); //补贴金额
        if($e.attr('class').indexOf("active")!=-1){
        	
        }else{
        	$("#sportType").val($("#sportType").val()+ball_type_code+";;");
        	$e.addClass("active");
        }
        var hasThisTypeWrap = false; //是否有这个球类别的wrap

        data['subsidies'] = 0;
        data['ball_type'] = ball_type;
        data['ball_type_code'] = ball_type_code;
        data['guidFlag_1'] = 'cls1_' + timeNow;
        data['guidFlag_2'] = 'cls2_' + timeNow;
        data['guidFlag_3'] = 'cls3_' + timeNow;
        data['guidFlag_4'] = 'cls4_' + timeNow;
        data['guidFlag_5'] = 'cls5_' + timeNow;
        data['guidFlag_6'] = 'cls6_' + timeNow;
        data['guidFlag_7'] = 'cls7_' + timeNow;
        data['guidFlag_8'] = 'cls8_' + timeNow;
        data['guidFlag_9'] = 'cls9_' + timeNow;
        data['guidFlag_10'] = 'cls10_' + timeNow;
        data['guidFlag_11'] = 'cls11_' + timeNow;
        data['guidFlag_12'] = 'cls12_' + timeNow;

        var wraps = $('#contentByTemplate').children('div.wrap');
        $.each(wraps, function (i, v) {
            if (v && ($(v).attr('ballTypeCode').toString() == ball_type_code)) {
                //有这个球类别的wrap
                hasThisTypeWrap = true;
            }
        });
        if (!hasThisTypeWrap) {
            var html_old = $('#contentByTemplate').html(); //旧html字符串
            // 附上模板
            $("#contentByTemplate").setTemplateElement("template");
            // 给模板加载数据
            $("#contentByTemplate").processTemplate(data);
            var html_new = $('#contentByTemplate').html(); //新html字符串
            $('#contentByTemplate').html(html_old + html_new);
        }
        return false;
    });

    //新增时间段tr
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

        console.log(selectValueAry);//已有select的值的集合
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
        html.push('<td><select name="startTimes" guidFlag="' + 'cls1_' + timeNow + '">' + htmlOptionFirst + '</select></td>');
        html.push('<td><select name="endTimes" guidFlag="' + 'cls2_' + timeNow + '">' + htmlOptionSecond + '</select></td>');
        html.push('<td><input type="text" name="price" value="80"/></td>');
        html.push('<td><input type="text" name="signPrice" value="75"/></td>');
        //增加成本价
        html.push('<td><input type="text" name="costPrice" value="74"/></td>');
        /*html.push('<td><button class="btn_del_row">删除</button></td>');*/
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
        var sportType = $eDiv.attr("balltypecode");
        $(".ball[balltypecode="+sportType+"]").removeClass("active abc");
        var sportType =$("#sportType").val().replace(sportType+";;","");
        $("#sportType").val(sportType);
        $eDiv.fadeOut(600);
        setTimeout(function () {
            $eDiv.remove();
        }, 800);
    });

    //select change
    $('#contentByTemplate').on('change', '.wrap select', function (e) {
        var e = e.target || e.srcElement;
        DomChangeInThisTable($(e));
    });
    //dom改变,委托
    function DomChangeInThisTable($e) {
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
                    ChangeSelectValue(selectAry, i, curSelectedVal); //i 为当前select索引，curSelectedVal为当前select选中的值
                }
            }
        }
    }

    function ChangeSelectValue(ary, beginIndex, beginValue) {
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


    //遍历得到JSON
    $('#getJSON').on('click', function () {
    	var wraps = $('#contentByTemplate>.wrap');//wrap集合
        var dataWrap = [];//存储数据的数组
        if (wraps && wraps.length) {
            $.each(wraps, function (i, v) {

                var workingDaysAry = [];//存储工作日的信息
                var noWorkingDaysAry = [];//存储非工作日的信息
                var itemWrap = {'ballType': '', workingDays: workingDaysAry, noWorkingDays: noWorkingDaysAry,'subsidies':''};//data中的每一项
                var itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'signPrice': '','costPrice':'', 'seq': ''};//列表中的每一项

                var $v = $(v);
                itemWrap['ballType'] = $v.attr('balltypecode');//类型
                itemWrap['subsidies'] = $v.find('[name=subsidies]').val(); //补贴金额
                
                //遍历获取每一项
                var workingTrs = $v.find('.working-days-layer tbody>tr');//工作日table
                var noWorkingTrs = $v.find('.no-working-days-layer tbody>tr');//非工作日table

                $.each(workingTrs, function (iTr, vTr) {
                    var $vTr = $(vTr);//每一行的tr
                    itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'signPrice': '','costPrice':'', 'seq': ''};//每次都New一个对象
                    itemList['title'] = $vTr.find('[name=title]').val();
                    itemList['startTime'] = $vTr.find('[name=startTimes]').children('option:selected').val();
                    itemList['endTime'] = $vTr.find('[name=endTimes]').children('option:selected').val();
                    itemList['price'] = $vTr.find('[name=price]').val();
                    itemList['signPrice'] = $vTr.find('[name=signPrice]').val();
                    //增加成本价
                    itemList['costPrice'] = $vTr.find('[name = costPrice]').val();
                    itemList['seq'] = iTr;
                    workingDaysAry.push(itemList);
                });

                $.each(noWorkingTrs, function (itemTr, valueTr) {
                    var $vTr = $(valueTr);//每一行的tr
                    itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'signPrice': '','costPrice':'', 'seq': ''};//每次都New一个对象
                    itemList['title'] = $vTr.find('[name=title]').val();
                    itemList['startTime'] = $vTr.find('[name=startTimes]').children('option:selected').val();
                    itemList['endTime'] = $vTr.find('[name=endTimes]').children('option:selected').val();
                    itemList['price'] = $vTr.find('[name=price]').val();
                    itemList['signPrice'] = $vTr.find('[name=signPrice]').val();
                    //增加成本价
                    itemList['costPrice'] = $vTr.find('[name = costPrice]').val();
                    itemList['seq'] = itemTr;
                    noWorkingDaysAry.push(itemList);
                });
                dataWrap.push(itemWrap);
            });
        }
    })
});

function getData(cb){
	var wraps = $('#contentByTemplate>.wrap');//wrap集合
    var dataWrap = [];//存储数据的数组
    var globalFlag = true;//全局标识
    var errFlag = 0;//1:title为空;2:时间为空;3:价格为空;4:价格为负数;5:价格为小数;6:签约价大于原价 7:成本价大于销售价即成本价大于原签约价
    var errCount = 0;//错误计数
    if (wraps && wraps.length) {
        $.each(wraps, function (i, v) {

            var workingDaysAry = [];//存储工作日的信息
            var noWorkingDaysAry = [];//存储非工作日的信息
            var itemWrap = {'ballType': '', workingDays: workingDaysAry, noWorkingDays: noWorkingDaysAry,'subsidies':''};//data中的每一项
            var itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'signPrice': '','costPrice':'', 'seq': ''};//列表中的每一项

            var $v = $(v);
            itemWrap['ballType'] = $v.attr('balltypecode');//类型
            itemWrap['subsidies'] =  $v.find('[name=subsidies]').val(); //补贴金额

            //遍历获取每一项
            var workingTrs = $v.find('.working-days-layer tbody>tr');//工作日table
            var noWorkingTrs = $v.find('.no-working-days-layer tbody>tr');//非工作日table

            $.each(workingTrs, function (iTr, vTr) {
                var $vTr = $(vTr);//每一行的tr
                itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'signPrice': '','costPrice':'', 'seq': ''};//每次都New一个对象

                var title = $vTr.find('[name=title]').val();
                var startTime = $vTr.find('[name=startTimes]').children('option:selected').val();
                var endTime = $vTr.find('[name=endTimes]').children('option:selected').val();
                var price = $vTr.find('[name=price]').val();
                var signPrice = $vTr.find('[name=signPrice]').val();
                //成本价
                var costPrice = $vTr.find('[name=costPrice]').val();

                var rst = CheckInput(title, startTime, endTime, price, signPrice,costPrice, globalFlag, errFlag, 0, (iTr == workingTrs.length - 1) ? true : false);
                globalFlag = rst['globalFlag'];
                errFlag = rst['errFlag'];
                errCount += parseInt(rst['errCount']);//累加错误计数

                if (globalFlag == true && errFlag == 0 && errCount == 0) {
                    itemList['title'] = title;
                    itemList['startTime'] = startTime;
                    itemList['endTime'] = endTime;
                    itemList['price'] = price;
                    itemList['signPrice'] = signPrice;
                    //成本价
                    itemList['costPrice'] = costPrice;
                    itemList['seq'] = iTr;
                    workingDaysAry.push(itemList);
                }
            });

            $.each(noWorkingTrs, function (itemTr, valueTr) {
                var $vTr = $(valueTr);//每一行的tr
                itemList = {'title': '', 'startTime': '', 'endTime': '', 'price': '', 'signPrice': '','costPrice':'', 'seq': ''};//每次都New一个对象

                var title = $vTr.find('[name=title]').val();
                var startTime = $vTr.find('[name=startTimes]').children('option:selected').val();
                var endTime = $vTr.find('[name=endTimes]').children('option:selected').val();
                var price = $vTr.find('[name=price]').val();
                var signPrice = $vTr.find('[name=signPrice]').val();
                //补贴价
                var costPrice = $vTr.find('[name = costPrice]').val();

                var rst = CheckInput(title, startTime, endTime, price, signPrice,costPrice, globalFlag, errFlag, 0, (itemTr == noWorkingTrs.length - 1) ? true : false);
                globalFlag = rst['globalFlag'];
                errFlag = rst['errFlag'];
                errCount += parseInt(rst['errCount']);//累加错误计数

                if (globalFlag == true && errFlag == 0 && errCount == 0) {
                    itemList['title'] = $vTr.find('[name=title]').val();
                    itemList['startTime'] = $vTr.find('[name=startTimes]').children('option:selected').val();
                    itemList['endTime'] = $vTr.find('[name=endTimes]').children('option:selected').val();
                    itemList['price'] = $vTr.find('[name=price]').val();
                    itemList['signPrice'] = $vTr.find('[name=signPrice]').val();
                    itemList['costPrice'] = $vTr.find('[name = costPrice]').val();
                    itemList['seq'] = itemTr;
                    noWorkingDaysAry.push(itemList);
                }
            });
            dataWrap.push(itemWrap);
        });
        if (globalFlag && errFlag == 0 && errCount == 0) {
        	cb.call(this,JSON.stringify(dataWrap));
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
                case 7:
                	errmsg = '成本价格不能大于销售价！';
            }
            myAlert(errmsg,"error");
            return false;
        }
    }
}

function CheckInput(title, startTime, endTime, price, signPrice,costPrice, globalFlag, errFlag, errCount, isLastTr) {
    if (!title || !title.length) {
        globalFlag = false;
        errFlag = 1;
        errCount++;
    }
    if (!startTime || !startTime.length) {
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
    if (!price || !price.length || parseFloat(price) < 0 || price.toString().indexOf('.') > 0) {
        globalFlag = false;
        errCount++;
        if (!price || !price.length) {
            errFlag = 3;
        } else if (parseFloat(price) < 0) {
            errFlag = 4;
        } else if (price.toString().indexOf('.') > 0) {
            errFlag = 5;
        }
    }
    if (!signPrice || !signPrice.length || parseFloat(signPrice) < 0 || signPrice.toString().indexOf('.') > 0) {
        globalFlag = false;
        errCount++;
        if (!signPrice || !signPrice.length) {
            errFlag = 3;
        } else if (parseFloat(signPrice) < 0) {
            errFlag = 4;
        } else if (signPrice.toString().indexOf('.') > 0) {
            errFlag = 5;
        }
    }
    
    if (parseInt(signPrice) > parseInt(price)) {
        globalFlag = false;
        errFlag = 6;
        errCount++;
    }
    
    //成本价格不能为空
    if(!costPrice || !costPrice.length || parseFloat(costPrice) < 0 || costPrice.toString().indexOf('.') > 0){
    	globalFlag = false;
    	errCount++;
    	if(!costPrice || !costPrice.length){
    		errFlag = 3;  //不能为空
    	}else if(parseFloat(costPrice) < 0){
    		errFlag = 4;  //不能小于0
    	}else if(costPrice.toString.indexOf('.') > 0){
    		errFlag = 5;  //必须是整数
    	}
    }
    
 /**   //成本价格不能大于销售价格（销售价格即签约价）
    if(parseInt(costPrice) > parseInt(signPrice)){
    	globalFlag = false;
    	errFlag = 7;
    	errCount++;
    }**/
    
    return {'globalFlag': globalFlag, 'errFlag': errFlag, 'errCount': errCount};
}

//绑定json
function BindAndEditWrap(data,type){
    var html = [];//保存html
    var timeNow = new Date().getTime().toString();//当前时间戳
    if (data && data.length && (Object.prototype.toString.call(data).indexOf('Array') > 0)) {
        $.each(data, function (i, v) {
            html.push('<div class="wrap" ballTypeCode="' + v['ballType'] + '">');
            html.push('<div class="ball-type-title">' + v['ballName'] + '<span class="btn_close">关闭</span></div>');
            html.push(' <table class="table table-bordered user-reset">  <tr> <td>补贴金额 </td> <td> <input type="text" value="'+v['subsidies']+'" ');
            if(type != 0){
                html.push('  readonly="readonly" ');
            }
            html.push('  id="subsidies" name = "subsidies"/></td>  </tr> </table>');

            if (v['workingDays'] && v['workingDays'].length && (Object.prototype.toString.call(v['workingDays']).indexOf('Array') > 0)) {
                html.push('<div class="working-days-layer">');
                html.push('<table class="table table-bordered user-reset">');
                html.push('<caption>工作日</caption>');
                /*html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>原价</th><th>签约价</th><th><button class="btn_add_row">新增</button></th></tr></thead>');*/
                html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>原价</th><th>销售价</th><th>成本价</th></tr></thead>');
                html.push('<tbody>');
                $.each(v['workingDays'], function (item, val) {
                	 if (val['startTime'] && val['endTime']) {
                     //if (val['title'] != '其他时段') {
                        html.push('<tr>');
                        html.push('<td><input type="text" name="title" value="' + val['title'] + '" /></td>');
                        //开始时间
                        html.push('<td><select name="startTimes" guidFlag="workingDays' + parseInt(item) + parseInt(val['startTime']) + '_' + timeNow + '">');
                        for (var i = parseInt(val['startTime']); i <= 24; i++) {
                            var iTmp = i;
                            iTmp = iTmp.toString().length == 1 ? '0' + iTmp : iTmp;
                            html.push('<option value="' + iTmp + '">' + iTmp + ':00</option>');
                        }
                        html.push('</select></td>');
                        //结束时间
                        html.push('<td><select name="endTimes" guidFlag="workingDays' + parseInt(item) + parseInt(val['endTime']) + '_' + timeNow + '">');
                        for (var j = parseInt(val['endTime']); j <= 24; j++) {
                            var jTmp = j;
                            jTmp = jTmp.toString().length == 1 ? '0' + jTmp : jTmp;
                            html.push('<option value="' + jTmp + '">' + jTmp + ':00</option>');
                        }
                        html.push('</select></td>');
                        html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                        html.push('<td><input type="text" name="signPrice" value="' + val['signPrice'] + '" /></td>');
                        html.push('<td><input type="text" name="costPrice" value="' + val['costPrice'] + '" /></td>');
                        /*html.push('<td><button class="btn_del_row">删除</button></td>');*/
                        html.push('</tr>');
                    } else {
                        html.push('<tr class="time_others_tr">');
                        html.push('<td><input type="hidden" name="title" value="' + val['title'] + '">' + val['title'] + '</td>');
                        html.push('<td></td>');
                        html.push('<td></td>');
                        html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                        html.push('<td><input type="text" name="signPrice" value="' + val['signPrice'] + '" /></td>');
                        html.push('<td><input type="text" name="costPrice" value="' + val['costPrice'] + '" /></td>');
                        //html.push('<td></td>');
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
                /*html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>原价</th><th>签约价</th><th><button class="btn_add_row">新增</button></th></tr></thead>');*/
                html.push('<thead><tr><th>时段名称</th><th>开始时间</th><th>结束时间</th><th>原价</th><th>销售价</th><th>成本价</th></tr></thead>');
                html.push('<tbody>');
                $.each(v['noWorkingDays'], function (item, val) {
                	if (val['startTime'] && val['endTime']) {
                    //if (val['title'] != '其他时段') {
                        html.push('<tr>');
                        html.push('<td><input type="text" name="title" value="' + val['title'] + '" /></td>');
                        //开始时间
                        html.push('<td><select name="startTimes" guidFlag="noWorkingDays' + parseInt(item) + parseInt(val['startTime']) + '_' + timeNow + '">');
                        for (var i = parseInt(val['startTime']); i <= 24; i++) {
                            var iTmp = i;
                            iTmp = iTmp.toString().length == 1 ? '0' + iTmp : iTmp;
                            html.push('<option value="' + iTmp + '">' + iTmp + ':00</option>');
                        }
                        html.push('</select></td>');
                        //结束时间
                        html.push('<td><select name="endTimes" guidFlag="noWorkingDays' + parseInt(item) + parseInt(val['endTime']) + '_' + timeNow + '">');
                        for (var j = parseInt(val['endTime']); j <= 24; j++) {
                            var jTmp = j;
                            jTmp = jTmp.toString().length == 1 ? '0' + jTmp : jTmp;
                            html.push('<option value="' + jTmp + '">' + jTmp + ':00</option>');
                        }
                        html.push('</select></td>');
                        html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                        html.push('<td><input type="text" name="signPrice" value="' + val['signPrice'] + '" /></td>');
                        html.push('<td><input type="text" name="costPrice" value="' + val['costPrice'] + '" /></td>');
                      /*  html.push('<td><button class="btn_del_row">删除</button></td>');*/
                        html.push('</tr>');
                    } else {
                        html.push('<tr class="time_others_tr">');
                        html.push('<td><input type="hidden" name="title" value="' + val['title'] + '">' + val['title'] + '</td>');
                        html.push('<td></td>');
                        html.push('<td></td>');
                        html.push('<td><input type="text" name="price" value="' + val['price'] + '" /></td>');
                        html.push('<td><input type="text" name="signPrice" value="' + val['signPrice'] + '" /></td>');
                        html.push('<td><input type="text" name="costPrice" value="' + val['costPrice'] + '" /></td>');
                        //html.push('<td></td>');
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