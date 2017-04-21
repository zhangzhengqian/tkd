<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户统计</title>
		<style type="text/css">
		${demo.css}
		</style>
  <script>
    $(function() {
		menu.active('#ssouser-statistics');
		$('#adminFooter').hide();
		
		$("#createTime").focus(function() {
            WdatePicker({		
			language : 'zh-CN',
			autoclose : 1,
			forceParse : 0,
			dateFmt:'yyyy-MM'
            });
        });
		
		var month = '${param.createTime }';
		if(month == null || month == ""){month = "本";}
		 $('#container').highcharts({
			  credits: {
		            enabled: false
		        },
		        title: {
		            text: '月注册量',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '',
		            x: -20
		        },
		        xAxis: {
		           title: {text: month+'月'},
		           categories: eval("["+$('#dataArray').val()+"]")
		        },
		        yAxis: {
		            title: {
		                text: '人数 (个)'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }],
		            allowDecimals:false
		        },
		        tooltip: {
		            valueSuffix: '个'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: '注册量',
		            data: eval("["+$('#countArray').val()+"]")
		        }]
		    });
		});
   </script>
   </head>
<body>

<div class="panel panel-default">
<input type="hidden" id="dataArray" value="${dataArray}" />
<input type="hidden" id="countArray" value="${countArray}" /> 
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 用户统计  (注册总数:${total}人)</li>
    </ul>
  </div><!-- / 右侧标题 -->
		  <div class="panel-body"><!-- 右侧主体内容 -->
		    <div class="row mtb10"><!-- 查询条件 -->
			<div class="col-md-10">
		      <form class="form-inline">
		        <div class="form-group">
		          <label class="sr-only" for="createTime">统计月份：</label>
		          <input class="form-control input-sm" type="text" name="createTime" id="createTime" value="${param.createTime }" data-date-format="yyyy-mm" placeholder="本月" />
		        </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 统计</button>
                </div>
		      </form>
			</div>
		</div><!-- /查询条件 -->
		  
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		    
		  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">

 
</script>

</body>
</html>
