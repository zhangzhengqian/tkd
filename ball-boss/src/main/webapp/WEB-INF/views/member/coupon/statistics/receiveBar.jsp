<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
      
      <div class="panel-body"><!-- 右侧主体内容 -->
		    <div class="row mtb10"><!-- 查询条件 -->
			<div class="col-md-10">
		      <form class="form-inline" method="get" id="staticticsform">
		      
		        <div class="form-group">
					<!-- <label class="col-md-1 control-label" style="width:100px;line-height:30px;"><span class="text-red"></span></label> -->
	                <div class="col-sm-4" style="padding:0;">
	                     <div class="input-group col-md-6 has-feedback">
	                         <input placeholder="开始时间" type="text" name="start" style="width:200px;" id="search_GTE_start" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM-dd 00:00:00"});' value="${param.start }" readonly>
	                         <label for="search_GTE_start" class="input-group-addon"><i class="fa fa-calendar"></i></label>
	                     </div>
	                </div>
				</div>
				至
				<div class="form-group">
					<!-- <label class="col-sm-1 control-label" style="width:100px;line-height:30px;"><span class="text-red"></span></label> -->
	                <div class="col-sm-6" style="padding:0;">
	                     <div class="input-group col-md-6 has-feedback">
	                         <input placeholder="结束时间" type="text" name="end" id="search_LTE_end" style="width:200px;" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM-dd 23:59:59"});' value="${param.end}" readonly>
	                         <label for="search_LTE_end" class="input-group-addon"><i class="fa fa-calendar"></i></label>
	                     </div>
	                </div>
				</div>		



				<div class="form-group" style="margin:0 0 0 60px" id="selectCouponId">
			          <select class="form-control input-sm" id="couponId" name="couponid">
			                 <option value="">--选择优惠券--</option>
			                  <c:forEach var="coupon" items="${couponlist}">
										<option value="${coupon.id}" data-describe="${coupon.describe}">--${coupon.name}--</option>
			                  </c:forEach>
			          </select>
		        </div>			    
			    <div class="form-group" style="margin:0 0 0 30px">
                  <label class="sr-only" for="typeselect">类型：</label>
			          <select name="typeselect" class="form-control input-sm" id="typeselect">
			                    <option id="option10" value="0" <c:if test="${active==0}">selected='selected'</c:if> >--优惠券领取统计--</option>
							    <option id="option11" value="1" <c:if test="${active==1}">selected='selected'</c:if> >--优惠券分享统计--</option>
							    <option id="option12" value="2" <c:if test="${active==2}">selected='selected'</c:if> >--优惠券使用统计--</option>
							    <option id="option13" value="3" <c:if test="${active==3}">selected='selected'</c:if> >--优惠券领取和消费统计--</option>
			          </select>
		        </div>
		        
		        <!--  <div class="form-group" id="costBlock">
		          <label class="sr-only" for="cost">价值：</label>
		          <input type="text" class="form-control input-sm" id="cost" name="cost" value="${cost}" placeholder="价值">
		        </div>-->
		        
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 统计</button>
                </div>
		      </form>
			</div>
		</div><!-- /查询条件 -->

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <!-- ECharts单文件引入 -->
    
    <script type="text/javascript">
    
    $(function(){
    	if("${active}"=="1"){
    		$("#selectCouponId").hide();
    	}
    	$("#costBlock").hide();
    	menu.active('#coupon-statistics');
    	$("#typeselect").change(function(){
    		if($(this).val()==0){
    			$("#selectCouponId").show();
    			$("#staticticsform").attr("action","${ctx}/admin/coupon/statictics/receive");
    		}
			if($(this).val()==1){
				$("#selectCouponId").hide();
				$("#staticticsform").attr("action","${ctx}/admin/coupon/statictics/share");
    		}
			if($(this).val()==2){
				$("#selectCouponId").show();
				$("#staticticsform").attr("action","${ctx}/admin/coupon/statictics/apply");
			}
			if($(this).val()==3){
				$("#selectCouponId").show();
				$("#staticticsform").attr("action","${ctx}/admin/coupon/statictics/rate");
			}
			
    	});
    	
    	$('#adminFooter').hide();
    	
    	menu.active('#list-coupon-statictics');
    	
    	menu.active('#list-coupon-statictics');
    	$("#couponId option").each(function(){
    		if($(this).val()=="${couponid}"){
    			$(this).attr("selected","selected");
    		}
    	})
    	$("#couponId").change(function(){
    		var data =  $(this).find("option:selected").attr("data-describe");
    		if(data){
    			alert("优惠券的描述:"+data);
    		}
    	});
    	
    });
    
    require.config({
        paths: {
            echarts: '${ctx}/static/js'
        }
    });
    
 // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line'
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main')); 
            
            var option = {
            		
            		tooltip : {
            	        trigger: 'axis',
            	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            	        }
            	    },
            	    legend: {
            	        data:[
            	              
<c:forEach items="${list}" var="item" varStatus="stat">
	<c:choose>  
		<c:when test="${stat.index==0}">    
	 		${item.title}
    </c:when>  
    <c:otherwise>    
		,${item.title}
    </c:otherwise>  
</c:choose>  
</c:forEach>
            	              
            	              ]
            	    },
            	    toolbox: {
            	        show : true,
            	        orient: 'vertical',
            	        x: 'right',
            	        y: 'center',
            	        feature : {
            	            mark : {show: true},
            	            dataView : {show: true, readOnly: false},
            	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'category',
            	            data : [
                                         ${xrawValue}
            	                    ]
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    series : [
            	              
            	              
            	              
<c:forEach items="${list}" var="item" varStatus="stat">
<c:choose>  
<c:when test="${stat.index==0}">   
            	        {
            	            name:${item.title},
            	            type:'bar',
            	            data:[${item.rawYValue}],
            	        }
            	        
            	        </c:when>  
                    	<c:otherwise>  
            	        ,{
            	            name:${item.title},
            	            type:'bar',
            	            data:[${item.rawYValue}],
            	        }
            	        </c:otherwise> 
                    	</c:choose> 
					</c:forEach>
            	    ]
            };
    
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        }
    );
            
            
            
    </script>
</body>