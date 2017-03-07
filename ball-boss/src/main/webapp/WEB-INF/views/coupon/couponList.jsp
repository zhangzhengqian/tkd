<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>卡劵管理</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home active"></span> 运营管理</li>
        <li class="active" >卡券管理</li>
    </ul>
  </div><!-- / 右侧标题 -->
  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
		      <form class="form-horizontal" action="${ctx }/coupon/list" id="actionForm" method="post">
			  <input type="hidden" id="result" value="${result }"/>
			  <input type="hidden" id="reason" value="${reason}"/>
			  
			  
			  <div class="form-group form-group-sm">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
		         	<div class="col-md-5">
			        	<input type="text" class="form-control input-sm" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="输入优惠券名称">
			       	</div>
	  				<div class="col-md-5">
	  					<input type="hidden" id="status" value="${param.search_EQ_status }"/>
						<select class="form-control" name="search_EQ_status" id="search_EQ_status" >
							<option id="status_" value="" >--优惠券状态--</option>
							<option id="status_0" value="0" >--未开始--</option>
							<option id="status_1" value="1" >--启用--</option>
							<option id="status_2" value="2" >--停用--</option>
							<option id="status_3" value="3" >--结束--</option>
						</select>
			  		</div>
		      </div>
		      
		      <div class="form-group form-group-sm query-more">
          			<lable class="control-label col-md-1 sr-only"></lable>
          			<div class="col-md-5">
            			<input type="text" class="form-control Wdate " id="search_GTE_startTime" name="search_GTE_startTime" value="${param.search_GTE_startTime }" placeholder="发布日期" onfocus="WdatePicker({firstDayOfWeek:1,maxDate:'#F{$dp.$D(\'search_LTE_endTime\')||\'%y-%M-%d\'}'})">
          			</div>
          			<div class="col-md-5">
            			<input type="text" class="form-control Wdate " id="search_LTE_endTime" name="search_LTE_endTime" value="${param.search_LTE_endTime }" placeholder="过期日期" onfocus="WdatePicker({firstDayOfWeek:1,minDate:'#F{$dp.$D(\'search_GTE_startTime\')}'})">
          			</div>         
        		</div>
		      
		      <div class="form-group form-group-sm query-more">
		         	<label class="control-label col-md-1 sr-only" for="custName"></label>
	  				<div class="col-md-5">
	  					<input type="hidden" id="amountType" value="${param.search_EQ_amountType }"/>
						<select class="form-control" name="search_EQ_amountType" id="search_EQ_amountType" >
							<option id="amountType_" value="" >--优惠券发放方式--</option>
							<option id="amountType_1" value="1" >--随机发放--</option>
							<option id="amountType_2" value="2" >--定额发放--</option>
						</select>
			  		</div>
			  		<div class="col-md-5">
						<select class="form-control"  id="search_ballType" name="search_ballType">
						    		<option id="couponType_" value="">--优惠券类型--</option>
									<option id="couponType_0" value="0" <c:if test="${param.search_ballType==0}">selected='selected'</c:if> >--通用--</option>
									<option id="couponType_1" value="1" <c:if test="${param.search_ballType==1}">selected='selected'</c:if> >--羽毛球--</option>
									<option id="couponType_2" value="2" <c:if test="${param.search_ballType==2}">selected='selected'</c:if> >--网球--</option>
									<option id="couponType_0" value="3" <c:if test="${param.search_ballType==3}">selected='selected'</c:if> >--篮球--</option>
									<option id="couponType_1" value="4" <c:if test="${param.search_ballType==4}">selected='selected'</c:if> >--乒乓球--</option>
									<option id="couponType_2" value="5" <c:if test="${param.search_ballType==5}">selected='selected'</c:if> >--高尔夫--</option>
									<option id="couponType_0" value="6" <c:if test="${param.search_ballType==6}">selected='selected'</c:if> >--足球--</option>
									<option id="couponType_1" value="7" <c:if test="${param.search_ballType==7}">selected='selected'</c:if> >--台球--</option>
									<option id="couponType_2" value="8" <c:if test="${param.search_ballType==8}">selected='selected'</c:if> >--保龄球--</option>
									<option id="couponType_0" value="9" <c:if test="${param.search_ballType==9}">selected='selected'</c:if> >--预约教练--</option>
									<option id="couponType_1" value="10" <c:if test="${param.search_ballType==10}">selected='selected'</c:if> >--活动报名--</option>
									<option id="couponType_1" value="11" <c:if test="${param.search_ballType==11}">selected='selected'</c:if> >--赛事报名--</option>
						     	</select>
			  		</div>
		      </div>
		      
		      <div class="form-group form-group-sm">
	  				<div class="col-md-12 text-center">
	    				<button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
	   	 				&nbsp;&nbsp;
	    				<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
	    				&nbsp;&nbsp;
	    				<button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button>
	  				</div>
				</div>
			  
		      </form>
			</div>
		</div><!-- /查询条件 -->

	 	<div class="row"><!-- 操作按钮组 -->
   			<div class="col-md-12">
	      		<div class="btn-group-sm pull-right mtb10">
	      		<shiro:hasPermission name="coupon:sign">
	        		<a class="btn btn-primary"  href="${ctx }/coupon/sign?action=create" id="createCouponDialog"><span class="glyphicon glyphicon-plus"></span> 新增优惠券</a>
	        	</shiro:hasPermission>
	     		</div>
	   		</div>
	  	</div><!-- /操作按钮组 --> 
	  	<div class="row">
    	<div class="col-table col-md-12" >
			<table id="contentTable" class="table table-bordered table-condensed table-hover">
				<thead class="thead">
					<tr>
		                <th width="50">序号</th>
		                <th class="text-center">优惠券号</th>
		                <th class="text-center">优惠券描述</th>
		                <th class="text-center">优惠券名称</th>
		                <th class="text-center">优惠券用途</th>
		                <th class="text-center">使用城市</th>
		                <th class="text-center">优惠券有效期(天)</th>
		                <th class="text-center">预计总金额(元)</th>
	                	<th class="text-center">领取消费金额(元)</th>
	                	<th class="text-center">产生最大金额(元)</th>
		                <th class="text-center">领取数量</th>
		                <th class="text-center">优惠券状态</th>
		                <th class="text-center">上传时间</th>
		                <th class="text-center">上传人员</th>
		                <th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${data.content}" var="itm" varStatus="stat">
					<tr>
					  	<td class="text-center">${stat.count }</td>
						<td><a href="${ctx }/coupon/couponInfosByCouponId/${itm.couponVoId}" >${itm.couponVoId}</a></td>
						<td>${itm.couponVoDescribe}</td>
						<td>${itm.couponVoName}</td>
						<td>${itm.couponInfoType}</td>	
						<c:if test="${itm.city != '0' }">
						       <td>${itm.city}</td>
						</c:if>
						<c:if test="${itm.city == '0' }">
						       <td>通用</td>
						</c:if>
						<td>${itm.duration}</td>
						<td>
						<c:if test="${!empty itm.voTotalAmount }">
						       ${itm.voTotalAmount}
						</c:if>
						<c:if test="${empty itm.voTotalAmount }">
						      0 
						</c:if>
						</td>
						<td>
							<c:if test="${!empty itm.realAmount }">
							       ${itm.realAmount}
							</c:if>
							<c:if test="${empty itm.realAmount }">
							      0 
							</c:if>
						</td>
						<td>
							<c:if test="${!empty itm.maxAmount }">
							       ${itm.maxAmount}
							</c:if>
							<c:if test="${empty itm.maxAmount }">
							      0 
							</c:if>
						</td>
						<td>${itm.getCount}</td>
						<td>
							<c:if test="${itm.voStatus == 0}">未开始 </c:if>
							<c:if test="${itm.voStatus == 1}">启用</c:if>
							<c:if test="${itm.voStatus == 2}">停用</c:if>
							<c:if test="${itm.voStatus == 3}">结束</c:if>
						</td>
						<td><fmt:formatDate value="${itm.voCreateTime}" pattern="yyyy-MM-dd "/></td>
						<td>${itm.voCreatePerson}</td>
	                    <td>
	                    	<shiro:hasPermission name="coupon:edit">
	                    	<c:if test="${itm.getCount==0 }">
	                    		<a href="${ctx }/coupon/sign?action=edit&id=${itm.couponVoId}" ></span>修改</a>
	                    	</c:if>
	                    	</shiro:hasPermission>
		                    <a href="${ctx }/coupon/sign?action=view&id=${itm.couponVoId}" ></span>查看</a>
							<shiro:hasPermission name="coupon:updateStatus">
			                    <c:if test="${itm.voStatus == 0 || itm.voStatus == 2 }">
							    	<a href="#" onclick="changeStatus('${itm.couponVoId}','1');" ></span>启动</a>
								</c:if>
							    <c:if test="${itm.voStatus == 1 }">
								    <a href="#" onclick="changeStatus('${itm.couponVoId}','2');"  ></span>停止</a>
								</c:if>
							</shiro:hasPermission>
							<shiro:hasPermission name="coupon:deleteCoupon">
								<%-- <c:if test="${itm.voStatus == 3 }"> --%>
									<a href="#" onclick="deleteCoupon('${itm.couponVoId}');"></span>删除</a>
								<%-- </c:if> --%>
							</shiro:hasPermission>
	                    </td>
					</tr>
				</c:forEach>
				
				</tbody>		
			</table>
		 </div><!-- end col-table -->
		 </div><!-- end row -->
        <tags:pagination page="${data}" />
        <tags:errors />
  </div><!-- /右侧主体内容 -->
</div>

<script type="text/javascript">
  $(function() {
	  //重置查询条件
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  
	//更新失败原因 
	if($("#result").val()== "false"){
	    alert($("#reason").val());
	}  
	if($("#result").val()== "true"){
	    alert($("#reason").val());
	} 
	menu.active('#coupon-man');
	$('#adminFooter').hide();
	  
    $("#search_EQ_startTime").focus(function() {
      	WdatePicker({dateFmt:'yyyy-MM-dd'});
    });
    $("#search_EQ_endTime").focus(function() {
      	WdatePicker({dateFmt:'yyyy-MM-dd'});
    });
     
    //查询条件值设置
	var status_value =$("#status").val();
	var ballType_value =$("#ballType").val();
	var amount_value =$("#amountType").val();
	$("#status_"+status_value).attr("selected","selected");
	$("#ballType_"+ballType_value).attr("selected","selected");
	$("#amountType_"+amount_value).attr("selected","selected");
	 
    
	//查询条件切换—优惠券类型： amountType = 1 随机发放  ；amountType =2 固定金额  
	var amountTypeValue=$("#amountType").val();
	if (amountTypeValue=='1'){
		// 随机发放
		$('#search_EQ_amount').parents('div.form-group').hide();
		$('#search_GTE_endAmount').parents('div.form-group').show();
		$('#search_LTE_startAmount').parents('div.form-group').show();
		$('#search_EQ_amount').val("");
	}else if(amountTypeValue=='2'){
		// 固定金额
		$('#search_EQ_amount').parents('div.form-group').show();
		$('#search_GTE_endAmount').parents('div.form-group').hide();
		$('#search_LTE_startAmount').parents('div.form-group').hide();
		$('#search_GTE_endAmount').val("");
		$('#search_LTE_startAmount').val("");
	}else{
		$('#search_EQ_amount').parents('div.form-group').show();
		$('#search_GTE_endAmount').parents('div.form-group').hide();
		$('#search_LTE_startAmount').parents('div.form-group').hide();
		$('#search_GTE_endAmount').val("");
		$('#search_LTE_startAmount').val("");
		
	}
	$("#search_EQ_amountType").change(function(){
		var amountTypeSelected=$("#search_EQ_amountType").children('option:selected').val();
 		if (amountTypeSelected=='1'){
 			// 随机发放
 			$('#search_EQ_amount').val("");
 			$('#search_EQ_amount').parents('div.form-group').hide();
 			$('#search_GTE_endAmount').parents('div.form-group').show();
 			$('#search_LTE_startAmount').parents('div.form-group').show();
 		}else if(amountTypeSelected=='2'){
 			// 固定金额
 			$('#search_EQ_amount').parents('div.form-group').show();
 			$('#search_GTE_endAmount').parents('div.form-group').hide();
 			$('#search_LTE_startAmount').parents('div.form-group').hide();
 			$('#search_GTE_endAmount').val("");
 			$('#search_LTE_startAmount').val("");
 		}else{
 			$('#search_EQ_amount').parents('div.form-group').show();
 			$('#search_GTE_endAmount').parents('div.form-group').hide();
 			$('#search_LTE_startAmount').parents('div.form-group').hide();
 			$('#search_GTE_endAmount').val("");
 			$('#search_LTE_startAmount').val("");
 		}
 	});
	

  });
  
  
  function deleteCoupon(couponid){
	  var msg = "您确定要删除大礼包吗？";
	  bootbox.confirm(msg, function(result) {
		    if(result) {
				var $form = $('#actionForm');
				$form.attr('action', '${ctx }/coupon/deleteCoupon?couponId='+couponid);
				$form[0].submit();
		    }
		  }) ;

  }
  
  function changeStatus(couponid,statusValue){
	  var msg = "您确定要停止优惠券吗？"
	  if(statusValue == 1){
		  msg = "您确定要启动优惠券吗？"
	  }
	  bootbox.confirm(msg, function(result) {
	    if(result) {
			var $form = $('#actionForm');
			$form.attr('action', '${ctx }/coupon/updateStatus?couponId='+couponid+'&status='+statusValue);
			$form[0].submit();
	    }
	  }) ;
  }

</script>

</body>
</html>
