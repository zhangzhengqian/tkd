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
			    
			    <div class="form-group" style="margin:0 0 0 60px">
                  <label class="sr-only" for="typeselect">类型：</label>
			          <select name="typeselect" class="form-control input-sm" id="typeselect">
			                    <option id="option10" value="0" <c:if test="${active==0}">selected='selected'</c:if> >--按球类统计--</option>
							    <option id="option11" value="1" <c:if test="${active==1}">selected='selected'</c:if> >--按支付类型统计--</option>
			          </select>
		        </div>
		        
		        <div class="form-group" id="costBlock">
		          <label class="sr-only" for="cost">价值：</label>
		          <input type="text" class="form-control input-sm" id="cost" name="cost" value="${cost}" placeholder="价值">
		        </div>
		        
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
    	
    	$("#costBlock").hide();
    	menu.active('#order-statistics');
    	$("#typeselect").change(function(){
    		if($(this).val()==0){
    			$("#staticticsform").attr("action","${ctx}/admin/order/summary/balltype");
    		}
			if($(this).val()==1){
				$("#staticticsform").attr("action","${ctx}/admin/order/summary/paytype");
    		}
			
    	});
    	
    	$('#adminFooter').hide();
    	
    	menu.active('#list-coupon-statictics');
    	
    });
    
        // 路径配置
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
                		
                		title : {
                	        text: ${title}
                	    },
                	    tooltip : {
                	        trigger: 'axis'
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
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {show: true, type: ['line']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            boundaryGap : false,
                	            data : [
                                          ${xrawValue}
                	                    ],
                	            name:${xrawName}
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value',
                	            name:${yrawName}
                	        }
                	    ],
                	    series : [
									<c:forEach items="${list}" var="item" varStatus="stat">
										<c:choose>  
                                    	<c:when test="${stat.index==0}">   
                                    	{name:${item.title},
                        	            type:'line',
                        	            data:[
                                    		${item.rawYValue}
                                    		 ],
                             	            markPoint : {
                             	                data : [
                             	                    {type : 'max', name: '最大值'},
                             	                    {type : 'min', name: '最小值'}
                             	                ]
                             	            },
                             	            markLine : {
                             	                data : [
                             	                    
                             	                ]
                             	            }
                                    	 }
                                    	</c:when>  
                                    	<c:otherwise>    
                                    	,{name:${item.title},
                            	            type:'line',
                            	            data:[
                                        		${item.rawYValue}
                                        		 ],
                                 	            markPoint : {
                                 	                data : [
                                 	                    {type : 'max', name: '最大值'},
                                 	                    {type : 'min', name: '最小值'}
                                 	                ]
                                 	            },
                                 	            markLine : {
                                 	                data : [
                                 	                    
                                 	                ]
                                 	            }
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