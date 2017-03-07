<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单管理</title>
	<style type="text/css">
		ul {list-style: none;}
		.order_price{
			position:absolute;
			bottom: 120px;
			right: 50px;
		}
	</style>
</head>

<body>

<div class="panel panel-default">

  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 订单管理</li>
        <li class="active">
        订单详情
        </li>
    </ul>
  </div><!-- / 右侧标题 -->
  
  <div class="panel-body"><!-- 右侧主体内容  style="background-color: #CDFFFF;"  -->
		<div class="panel panel-default">
  <div class="panel-heading">订单信息</div>
  <div class="panel-body">
    <div class="row">
    	<div class="col-md-2 text-right">订单号：</div>
  		<div class="col-md-6">${order.id}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单状态：</div>
  		<div class="col-md-6">
  			<c:choose>
                <c:when test="${order.status == 'ORDER_NEW'}">待付款</c:when>
                <c:when test="${order.status == 'ORDER_CANCELED'}">交易关闭</c:when>
                <c:when test="${order.status == 'ORDER_PLAYING'}">交易成功</c:when>
                <c:when test="${order.status == 'ORDER_PAIED'}">已付款</c:when>
                <c:when test="${order.status == 'ORDER_REFUNDING'}">退款中</c:when>
                <c:when test="${order.status == 'ORDER_REFUNDED'}">已退款</c:when>
                <c:when test="${order.status == 'ORDER_VERIFY'}">已确认</c:when>
        	</c:choose>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单类型：</div>
  		<div class="col-md-6">
  			<c:choose>
  				<c:when test="${order.ordersType == '0'}">订场订单</c:when>
                <c:when test="${order.ordersType == '1'}">教陪练订单</c:when>
                <c:when test="${order.ordersType == '2'}">活动订单</c:when>
                <c:when test="${order.ordersType == '3'}">约球订单</c:when>
				<c:when test="${order.ordersType == '3'}">白金赛订单</c:when>
  			</c:choose>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单金额：</div>
  		<div class="col-md-6">${lf:formatMoney(order.fee) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">优惠券抵扣：</div>
  		<div class="col-md-6">
  			<c:if test="${empty order.couponAmount}">
  			0元
  			</c:if>
  			<c:if test="${!empty order.couponAmount}">
  			${lf:formatMoney(order.fee)-lf:formatMoney(order.finalFee)}元
  			</c:if>
  		</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">实付金额：</div>
  		<div class="col-md-6">${lf:formatMoney(order.finalFee) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">成本金额：</div>
  		<div class="col-md-6">${lf:totalCostPrice(order.id) }元</div>
    </div>
   	<div class="row">
    	<div class="col-md-2 text-right">补贴金额：</div>
  		<div class="col-md-6">${lf:totalSubsidies(order.id) }元</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">订单创建时间：</div>
  		<div class="col-md-6"><fmt:formatDate value="${order.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">付款人：</div>
  		<div class="col-md-6">${order.userName}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">付款人联系电话：</div>
  		<div class="col-md-6">${order.userPhone}</div>
    </div>
    <div class="row">
    	<div class="col-md-2 text-right">支付方式：</div>
  		<div class="col-md-6">
  		<c:choose>
  			<c:when test="${order.payType == '1'}">支付宝付款</c:when>
            <c:when test="${order.payType == '2'}">微信付款</c:when>
            <c:when test="${order.payType == '3'}">公众平台付款</c:when>
            <c:when test="${order.payType == '4'}">球友圈付款</c:when>
  		</c:choose>
  		</div>
    </div>
    <c:if test="${order.payType == '1' or order.payType == '2'}">
    <div class="row">
    	<div class="col-md-2 text-right">
    		<c:if test="${order.payType == '1'}">
    			支付宝交易号：
    		</c:if>
    		<c:if test="${order.payType == '2'}">
    			微信交易号：
    		</c:if>
    	</div>
  		<div class="col-md-6">
  			${order.number}
  		</div>
    </div>
    </c:if>
    <br/>
    <table class="table table-bordered table-condensed table-hover">
    <c:if test="${order.ordersType == '0'||order.ordersType == '1'}">
			    <thead>
					<tr>
						<th style="border-bottom-width: 0px;">编号</th>
						<th style="border-bottom-width: 0px;">球馆名称</th>
						<th style="border-bottom-width: 0px;">预订场号</th>
						<th style="border-bottom-width: 0px;">预约时间</th>
						<th style="border-bottom-width: 0px;">球馆联系电话</th>
						<th style="border-bottom-width: 0px;">所在城市</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${order.orderItemVoList}" var="item" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>${item.statiumDetail.name }</td>
						<td>${item.spaceName }【${item.spaceCode }】</td>
						<td>${item.orderTimeStr }</td>
						<td>${item.statiumDetail.tel }</td>
						<td>${order.areaStr }</td>
						</tr>
					</c:forEach>
				</tbody>
	</c:if>	
	</table>
	<div class="row">
		<div class="col-md-2 text-left">
			<input class="form-control" type="text" id="orderDate_" value="${order.orderDate }">
		</div>
	</div>
	<div class="spaceDetTwo">
        <div class="twoLeft">
            <div class="spaceDate">
                <ul id="selectDate">
                	<c:forEach items="${orderDate}" var="data" varStatus="vs">
                		<li <c:if test="${order.orderDate==data.dateValue}"> class="active" </c:if> >
	                        <span data-time="${data.dateValue}">${data.dateDisplay}</span>
	                        <strong>${data.week}</strong>
                    	</li>
                	</c:forEach>
                </ul>
            </div>

            <div class="dateTime" id="dateTime">
                <div class="dateTimCont">
                    <ul id="dateUl">
                        <!-- <li>08:00</li> -->
                    </ul>
                </div>
            </div>

            <div class="groundManager">
                <div class="groundCont" id="groundCont">
                    <div class="groundLeft" id="groundLeft">
                        <div class="groundLCont" id="groundLCont">
                            <!-- <div class="first-child">羽毛球1号场</div> -->
                        </div>
                    </div>
                    <div class="groundRight" id="groundRight">
                        <div class="groundRCont" id="scroller">
                            <div class="spaceGround">
                                <div price="27.0" begin-time-data="08:00" ce1="羽毛球2号场" class="cells_ cantBook"><span><a></a></span></div>
                            </div>
                            <div class="spaceGround">
                                <div price="27.0" begin-time-data="08:00" ce1="羽毛球2号场" class="cells_ cantBook"><span><a></a></span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="twoRight">
            <div class="spaceOrderMeg">
                <ul id="selShow">
                    <!-- <li><strong>11:00-14:00</strong><span>3号场</span></li>
                    <li><strong>11:00-14:00</strong><span>3号场</span></li> -->
                </ul>
            </div>
        </div>
    </div>
	<div class="order_price">订单金额：<span id="orderPrice">0</span> 元</div>
  </div>
  <form id="orderForm" action="${ctx}/orders/modify" method="post">
  	<input type="hidden" name="orderId" value="${order.id}">
  	<input type="hidden" id="orderDate" name="orderDate" value="${order.orderDate}">
  	<input type="hidden" name="orderParam" id="orderParam"> 
  </form>
  <div class="panel-footer">
			<div class="row">
				<div class="col-sm-12 text-right">
						 <a class="btn btn-sm btn-default" href="javascript:history.go(-1)"><span class="glyphicon glyphicon-remove"></span> 返回</a>
						 <a class="btn btn-sm btn-default" href="javascript:;" onclick="modify()"><span class="glyphicon glyphicon-edit"></span> 修改</a>
				</div>
			</div>	
	</div>
</div>

  
</div>
</div>
<script src="${ctx}/static/js/iscroll-probe.js"></script>
<script type="text/javascript">

function modify(){
	var total_price = parseInt($("#orderPrice").text());
	if(total_price==0){
		myAlert("请选择场地！");
		return;
	}
	bootbox.confirm('确定要修改订单吗？', function(result) {
	    if(result) {
	      var items = $("#selShow").find("li");
	      var orderParams = new Array();
	      $.each(items,function(){
			var that = $(this);
			var price = that.attr("data-price");
			var space_id = that.attr("data-id");
			var time = that.children("strong").text();
			var start = time.split("-")[0];
			var end = time.split("-")[1];
			var sportType='${order.sportType}';
			var startDate = $("#orderDate_").val();
			$("#orderDate").val(startDate);
			orderParams.push({'startTime':start,'endTime':end,'spaceId':space_id,'douPrice':price,'sportType':sportType});
		  })
		  $("#orderParam").val(JSON.stringify(orderParams));
	      $("#orderForm").submit();
	    }
	  });
}

$(function(){
	var order_fee = parseInt('${lf:formatMoney(order.fee) }',10);
	var myScroll;
	
	function loaded () {
	    myScroll = new IScroll('#groundRight', {
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
	
    
	var SUMITER = {
    		hasProtoType: false,
    		NEWSUMITER: function() {
    			if (this.hasProtoType == false) {
    				this.hasProtoType = true;
    				this.INIT.prototype = SUMITER;
    			}
    			return this.INIT();
    		},
    		LoadData: function(sDate){
    			var that = this;
    			var sportType = '${order.sportType}';
    			var timelineHTML = [];
    			var groundLCont = $("#groundLCont");
    			var groundArr = [];
    			var spaceArr = [];
    			var spaceList = [];
    			var spaceHtml = "";
    			var spaceLiHtml = "";
    			var timeData = "";
    			if(sDate){
    				timeData = sDate;
    			}else{
    				timeData = $("#selectDate").find(".active:eq(0)").find("span:eq(0)").attr("data-time");
    			}
    			if(timeData==undefined){
    				timeData = $("#selectDate").find("li:eq(0)").find("span:eq(0)").attr("data-time");
    				$("#selectDate").find("li:eq(0)").addClass("active");
    			}
    			$.ajax({
    				url:"${ctx}/statium/statiumSpaceList/${order.statiumId}/"+sportType+"/"+timeData+"",
    				success: function(data){
    					var data = JSON.parse(data);
    					var beginTime = parseInt(data.beginTime);
    	    			var endTime = parseInt(data.endTime);
    					var timelineAry = that.GetTimelineAry(beginTime, endTime);
    					var spaceIdMap = data.spaceIdMap;//场地ID
    					var spaceNameMap = data.spaceNameMap;//场地名称
    					var spaceUseMap = data.spaceUseMap;//已使用场地
    					var time = data.time;
    					var nowDate = time.split(" ")[0];
    					var nowTime = time.split(" ")[1];
    					var nowHour = nowTime.split(":")[0];
    					console.log(timelineAry);
    					/**拼接时间***/
    					$.each(timelineAry,function(key,value){
    						timelineHTML.push('<li>'+value+':00</li>');
    					});
    					$("#dateUl").html(timelineHTML.join(''));
    					/**拼接场地名称***/
    					$.each(data.spaceList,function(key,value){
    						//通过索引查询场地名称
    						$.each(value,function(key1,value1){
    							var price = value1.split(',');
    							groundArr.push('<div class="first-child">'+key1+spaceNameMap[key1]+'</div>');
    							$.each(timelineAry,function(key2,value2){
    								if(timeData == nowDate){//今天
    									if(nowHour >= value2||parseInt(price[value2],10)==0){//今天过期时间
    										spaceLiHtml= '<div price="'+price[value2]+'" data-useTime="'+value2+'" begin-time-data="'+value2+':00" end-time-data="'+(value2+1)+':00" data-space="'+key1+'" cel-id="'+spaceIdMap[key1]+'" ce1="'+spaceNameMap[key1]+'" class="cells_ cantBook"><span>￥'+price[value2]+'</span></div>';	
    									}else{
    										spaceLiHtml= '<div price="'+price[value2]+'" data-useTime="'+value2+'" begin-time-data="'+value2+':00" end-time-data="'+(value2+1)+':00" data-space="'+key1+'" cel-id="'+spaceIdMap[key1]+'" ce1="'+spaceNameMap[key1]+'" class="cells_ canBook"><span>￥'+price[value2]+'</span></div>';
    									}
    								}else if(timeData < nowDate){//过期的日期
    									spaceLiHtml= '<div price="'+price[value2]+'" data-useTime="'+value2+'" begin-time-data="'+value2+':00" end-time-data="'+(value2+1)+':00" data-space="'+key1+'" cel-id="'+spaceIdMap[key1]+'" ce1="'+spaceNameMap[key1]+'" class="cells_ cantBook"><span>￥'+price[value2]+'</span></div>';
    								}else{//未来的日期 
    									if(parseInt(price[value2],10)==0){
	    									spaceLiHtml= '<div price="'+price[value2]+'" data-useTime="'+value2+'" begin-time-data="'+value2+':00" end-time-data="'+(value2+1)+':00" data-space="'+key1+'" cel-id="'+spaceIdMap[key1]+'" ce1="'+spaceNameMap[key1]+'" class="cells_ cantBook"><span>￥'+price[value2]+'</span></div>';
    									}else{
	    									spaceLiHtml= '<div price="'+price[value2]+'" data-useTime="'+value2+'" begin-time-data="'+value2+':00" end-time-data="'+(value2+1)+':00" data-space="'+key1+'" cel-id="'+spaceIdMap[key1]+'" ce1="'+spaceNameMap[key1]+'" class="cells_ canBook"><span>￥'+price[value2]+'</span></div>';
    									}
    								}
    								spaceList.push(spaceLiHtml);
    								
    							});
    							
    						});
    						spaceHtml = '<div class="spaceGround">'+spaceList.join('')+'</div>';
    						spaceList = [];
    						spaceArr.push(spaceHtml);
    					});
    					groundLCont.html(groundArr.join(""));
    					$("#scroller").html(spaceArr.join(""));
    					var canBook = $("#scroller").find(".canBook");
    					$.each(spaceUseMap,function(key,value){
    						if(value.length>0){
	    						$.each(value,function(key1,value1){
	    							$.each(canBook,function(){
	    	    						if($(this).attr("data-space")== key&&$(this).attr("data-usetime")== value1){
	    	    							$(this).removeClass("canBook").addClass("cantBook");
	    	    						}
	    	    					})
	    						});
    						}
    					});
    					setWidth();//动态设置宽度和高度
    				}
    			});
    		},
    		GetTimelineAry: function(beginTime, endTime) {
    			
    			var that = this;
    			var timeAry = []; //营业时间数组
    			if(beginTime <= endTime){
    				for (var i = beginTime; i <= endTime; i++) {
    					timeAry.push(i);
    				}
    			}else{
    				var endIndex=24;
    				var beginIndex=0;
    				for (var i = 0; i < endTime; i++) {
    					
    					timeAry.push(i);
    					
    				}
    				for (var i = beginTime; i < 24; i++) {
    					timeAry.push(i);
    				}
    			}
    			return timeAry;
    		},
    		BindEvent:function(){
    			var that = this;
    			$("#selectDate").on('click', 'li', function(e) {
    				var obj = $(this);
    				$("#selectDate").find("li").removeClass("active");
    				obj.addClass("active");
    				var sDate = obj.find("span:eq(0)").attr("data-time");
    				that.LoadData(sDate);
    				$("#selShow").find("li").remove();
    				$("#orderPrice").text("0");
    				$("#orderDate_").val(sDate);
    			});
    			
    			//日期选择控件
    			$("#orderDate_").on('click', function() {
    				var $e = $(this);
    				WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){
    					if ($e.val()) {
    						if ($e.attr('last-data') == $e.val()) {
    							//第二次触发
    						} else {
    							//第一次触发
    							$e.attr('last-data', $e.val());

    							$("#selectDate").find("li").removeClass("active");
    		    				$("#selectDate").find("span[data-time='"+$e.val()+"']").parent().addClass("active");
    		    				that.LoadData($e.val());
    		    				$("#selShow").find("li").remove();
    		    				$("#orderPrice").text("0");
    						}
    					}
    				}})
    				
    				
    			});
    			
    			$("#scroller").on('click','.canBook', function(){
    				var obj = $(this);
    				var price = obj.attr("price");
    				var price_int = parseInt(price,10);
    				var total_price = $("#orderPrice").text();
    				var total_int = parseInt(total_price,10);
    				var oSpan = obj.find("span:eq(0)");
    				if(oSpan.hasClass("select")){
    					oSpan.removeClass("select");
    					var dataId = obj.attr("cel-id");
    					var beginTime = obj.attr("begin-time-data");
    					var space = obj.attr("data-space");
    					var aLi = $("#selShow").find("li");
    					$.each(aLi,function(){
    						if($(this).attr("data-time")== beginTime&&$(this).attr("data-space")== space){
    							$(this).remove();
    						}
    					})
    					total_int = total_int-price_int;
    				}else{
    					total_int = total_int+price_int;
    					if(order_fee<total_int){
        					myAlert("修改金额不能超过订单金额！");
        					return;
        				}
    					if($("#selShow").find("li").length>=4){
    						myAlert("最多只能选择4场");
        					return;
        				}
    					oSpan.addClass("select");
    					$("#selShow").prepend("<li data-price="+obj.attr("price")+" data-id="+obj.attr("cel-id")+" data-time="+obj.attr("begin-time-data")+" data-space="+obj.attr("data-space")+"><strong>"+obj.attr("begin-time-data")+"-"+obj.attr("end-time-data")+"</strong><span>"+obj.attr("data-space")+"</span></li>");
    				}
    				$("#orderPrice").text(""+total_int);
    				
    			});
    		},
    		INIT: function() {
    			var that = this;
    			that.timeRangeAry = ["00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"];
    			that.LoadData();
    			that.BindEvent();
    			//that.dataTab = $("#selectDate");
    		}
    	}

	SUMITER.NEWSUMITER();
    function setWidth(){
        var timeLen = $("#dateTime").find("li").length;
        var timeW = $("#dateTime").find("li:eq(0)").width();
        $("#dateTime").find("ul:eq(0)").width(timeLen*timeW);
        $("#groundRight").width($("#groundCont").width()-$("#groundLeft").width());
        var spaceCell = $("#scroller").find(".spaceGround:eq(0)");
        var cell = $(spaceCell).find(".cells_:eq(0)");
        var cellW = cell.width()+1;
        var cellLen = spaceCell.find(".cells_").length;

        $("#scroller").width(cellW*cellLen);
        myScroll.refresh();

    }
});
</script>
</body>
</html>
