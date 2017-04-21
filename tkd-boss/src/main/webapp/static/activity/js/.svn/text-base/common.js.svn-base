//公用方法
function Common(){
    var self=this;
    //post请求的Ajax
    this.access_sever=function(url,options,callback){
        //加载载入动画
        self.showLoading();
        var isAsync=options.async?options.async:true;
        //异步请求
        $.ajax({
            url:url,
            type:'post',
            async:isAsync,
            dataType:'json',
            success:function(rs){
                self.hideLoading();
                callback(rs.result);
            },
            error:function(){
                self.warn('抱歉，网络繁忙，请<a href="javascript:;" onclick="location.reload()">刷新</a>否则返回')
            }
        })
    }
    //加载动画
    this.showLoading=function(){
        //动态创建一个div加到body的最后显示
        if($('#mark').length==0){
            var mark=$('<div class="mark"></div>');
            mark.attr('id','mark').appendTo($('body'));
        }
        //加载动画
        if($('#loading').length==0){
            var loading=$('<div class="loading"><img></div>');
            loading.attr('id','loading').appendTo($('body'));
            loading.find('img').attr('src','images/loading.gif')
        }
    }
    //删除加载动画
    this.hideLoading=function(){
        $('#mark').remove();
        $('#loading').remove();
    }

    //提示信息
    this.warn=function(info){
        if($('#message').length==0){
            $('#mark').remove();
            $('#loading').remove();
            var mark=$('<div class="mark"></div>');
            mark.attr('id','mark').appendTo($('body'));
            var message=$('<div class="message"></div>');
            var p=$('<p>').html(info);
            var btn=$('<span class="btn">确认</span>');
            btn.attr('id','btn');
            message.attr('id','message').appendTo($('body'));
            p.appendTo($('#message'));
            btn.appendTo($('#message'));
        }
        if($('#btn')){
            $('#btn').click(function(){
                $('#mark').remove();
                $('#message').remove();
                
            })
        }
    }
}

var common=new Common();


//解析地址栏中的参数，返回对象形式
function getParams (urlstr) {
	var obj={},arr,i;
	urlstr=urlstr.substr(1);
	if(urlstr){
		arr=urlstr.split('&');
		for(i=0;i<arr.length;i++){
			var tempArr=arr[i].split('=');
			obj[tempArr[0]]=tempArr[1];
		}
		return obj;
	}else{
		return false;
	}	
}

//计算要显示的日期
function get_date(i,option){
	var now=option?new Date(option.year,option.month,option.day):new Date();
	i=i?86400*1000*i:0;
	var futureDay=new Date(),
		futureTime=now.getTime()+i;
	futureDay.setTime(futureTime);
	var month=futureDay.getMonth()+1,
		day=futureDay.getDate();
	if(month<10){
		month='0'+month;
	}
	if(day<10){
		day='0'+day;
	}
	return futureDay.getFullYear()+'-'+month+'-'+day;
}

//显示日期插件
function showCalendar(obj,beginDate,maxDate,action){
	obj.calendar({
		minDate:beginDate,
		maxDate:maxDate,
		swipeable:true,
		hide:function(){
			changeDateout(action);
		}
	}).calendar('show');
	$('.shadow').remove();
   $('.ui-slideup-wrap').addClass('calenderbox');
   var shadow=$('<span class="shadow"></span>');
   $('.calenderbox').append(shadow);
   $('.ui-slideup').addClass('calender');
}

//将字符串日期转成数值型日期
function changeType(datestr){
	datestr=datestr.split('-');
	return datestr[0]+datestr[1]+datestr[2];
}

//列表页和内容页的显示日期
function renderDate(dIn,dOut){
	var inMonDay=removeYear(dIn),
		outMonDay=removeYear(dOut);

	$('#inText').text(inMonDay);
	$('#outText').text(outMonDay);

	$('#date-in').val(dIn);
	$('#date-out').val(dOut);
	//点击修改按钮
	$('#modify').on('tap',function(){
		var now=new Date(),
			beginDate=new Date(now.getFullYear(),now.getMonth(),now.getDate()),
			maxDate=new Date(now.getFullYear(),now.getMonth(),now.getDate()+90),
			ele=$('#date-in');

		showCalendar(ele,beginDate,maxDate);
	})
}

//日历组件隐藏时
function changeDateout(action){
	$('#inText') && ($('#inText').text(removeYear($('#date-in').val())));
	//转换为数字的入住日期
	var inDate=$('#date-in').val(),
		outDate=$('#date-out').val();
	
	var inNum=changeType(inDate);
	var outNum=changeType(outDate);
	var newOutDate;
	if(inNum>=outNum){
		newOutDate=get_date(1,{year:getArr(inDate)[0],month:getArr(inDate)[1]-1,day:getArr(inDate)[2]});	
	
	}else{
		newOutDate=outDate;
	}	
	//修改离店的隐藏域的值
	$('#date-out').val(newOutDate);
	//修改显示的离店日期
	$('#outText') && ($('#outText').text(removeYear(newOutDate)));
	//console.log(newOutDate);
	if(action){
		postDate.date_in=inDate;
		postDate.date_out=$('#date-out').val();
		common.access_sever('hotel.json',postDate)
	}
}

//将年月日转换为数组
function getArr(str){
	return str.split('-');
}


//去掉年月日中的0
function removeYear(dateArg){
	var arr=dateArg.split('-'),
		mon=arr[1].charAt(0)=='0'?arr[1].charAt(1):arr[1],
		day=arr[2].charAt(0)=='0'?arr[2].charAt(1):arr[2];

	return mon+'月'+day+'日';
}