<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span>优惠券</li>
    	        <c:if test="${param.action == 'create' }">
		       		 <li class="active">添加</li>
	       		</c:if>
		        <c:if test="${param.action == 'edit' }">
			        <li class="active" >修改</li>
		        </c:if>
		        <c:if test="${param.action == 'view' }">
			        <li class="active" >查看</li>
		        </c:if>
	    </ul>
  	</div><!-- / 右侧标题 --> 
    <c:choose>
  	<c:when test="${param.action == 'create' }">
  		<c:set var="disable" value="false"/>
		<c:set var="readonly" value=" "/>
  	</c:when>
  	<c:when test="${param.action == 'edit' }">
  		<c:set var="disable" value="false"/>
		<c:set var="readonly" value=" "/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly' "/>
  	</c:otherwise>
  </c:choose>
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<fieldset> <legend><small>优惠券信息</small></legend>
	<form id="inputForm" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
			<input type="hidden" name="next_page" value="/member/customer" />
			<input type="hidden" name="id" id="id" value="${coupon.id }" />
		<fieldset>
		
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>优惠券名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly }  placeholder="12汉字以内" type="text" class="form-control" id="name" name="name" style="width:645px" value="${coupon.name}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>优惠券描述:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly }  placeholder="10汉字以内" type="text" class="form-control" id="couponDesc" name="couponDesc" style="width:645px" value="${coupon.couponDesc}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="action" class="col-md-3 control-label"><span class="text-red"></span></label>
				<div class="col-md-6 has-feedback">
					<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
					&nbsp;&nbsp;
					<a class="label label-default label-primary" id="cityBtn" onclick="citySet();">地区</a>
					&nbsp;&nbsp;
					<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
					&nbsp;&nbsp;
					<a class="label label-default" id="commonBtn" onclick="commonSet();">通用</a>
				</div>
			</div>
			
			<div class="form-group form-group-sm form-inline" id="cityArea">
		        <label for="name" class="col-md-3 control-label"><span class="text-red"></span>地区:</label>
			     <div class="col-md-9">
			     <div class="form-inline">
					<tags:zoneCity name="areaCode" value="${coupon.areaCode}" id="areaCode" />
			     </div>
				 </div>
		    </div>
			
			<c:if test="${param.areaCode != null}" >
				citySet();
			</c:if>
						
			<%-- <div class="form-group form-group-sm">
				<label for="type" class="col-md-3 control-label"><span class="text-red"></span>优惠券用途:</label>
			    <div class="col-md-6 has-feedback form-inline">
			        <input type="hidden"  value="${coupon.type}" id="typeValue">
					<select ${readonly }  class="form-control" name="type" id="type" style="width:645px" >
						<option id="op_typeValue0" value="0" >--首单福利--</option>
						<option id="op_typeValue1" value="1" >--支付成功分享返券--</option>
					</select>
			  	</div>
			</div> --%>
				      	
			<%-- <div class="form-group form-group-sm">
				<label for="duration" class="col-md-3 control-label"><span class="text-red"></span>限制条件:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } placeholder="城市限制" type="text" class="form-control" id="cityLimit" name="cityLimit" style="width:645px"  value="${coupon.cityLimit}"/>
			    </div>
			</div>	
			
			<div class="form-group form-group-sm">
				<label for="startAmount" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } placeholder="场馆限制" type="text" class="form-control" id="statiumLimit" name="statiumLimit" style="width:645px"  value="${coupon.statiumLimit}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="startAmount" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } placeholder="活动限制" type="text" class="form-control" id="activityLimit" name="activityLimit" style="width:645px"  value="${coupon.activityLimit}"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="startAmount" class="col-md-3 control-label"><span class="text-red"></span></label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } placeholder="赛事限制" type="text" class="form-control" id="raceLimit" name="raceLimit" style="width:645px"  value="${coupon.raceLimit}"/>
			    </div>
			</div>
			<div class="form-group form-group-sm">
				<label for="" class="col-md-3 control-label"><span class="text-red"></span></label>
				<div class="col-md-6 has-feedback form-inline">
					<span style="color:red; font-weight:bold;">注意：不填则无限制条件，多个限制条件请用英文逗号隔开</span>
				</div>
			</div> --%>
			
			<div id="durationDiv" class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>优惠券有效期:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly }  placeholder="请输入一个正整数，如15" type="text" class="form-control" id="duration" name="duration" style="width:645px" value="${coupon.duration}"/>
			    </div>
			</div>
			
<%-- 			<div class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red"></span>使用有效期:</label>
                <div class="col-sm-4">
                     <div class="input-group col-md-12 has-feedback">
                         <input ${readonly }  type="text" name="voStartTime" id="voStartTime" style="width:230px" class="form-control" onclick="WdatePicker({'dateFmt':'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'voEndTime\')}'})"  value="${couponVo.voStartTime}" readonly>
                         <label for="voStartTime" class="input-group-addon"><i class="fa fa-calendar"></i></label> 
                         <input ${readonly }  type="text" name="voEndTime" id="voEndTime" style="width:230px" class="form-control" onclick="WdatePicker({'dateFmt':'yyyy-MM-dd',minDate:'#F{$dp.$D(\'voStartTime\')}'})"  value="${couponVo.voEndTime}" readonly>
                         <label for="voEndTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                     <span id="startTime_span" class="text-danger text-red">${start_time_error}</span>
                     <span id="endTime_span" class="text-danger text-red">${end_time_error}</span>
                </div>
			</div> --%>
			
			<div id="voEndTimeDiv" class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red"></span>使用有效期:</label>
                <div class="col-sm-4" style="width: 260px;" >
                     <div class="input-group col-md-12 has-feedback">
                         <input ${readonly }  type="text" name="voEndTime" id="voEndTime" class="form-control"
                         		onclick="WdatePicker({'dateFmt':'yyyy-MM-dd',minDate:'%y-%M-{%d+1}'})" value="${couponVo.voEndTime}">
                         <label for="voEndTime" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                     <span id="endTime_span" class="text-danger text-red">${end_time_error}</span>
                </div>
			</div>	
			
			<div class="form-group form-group-sm">
				<label for="totalAmount" class="col-md-3 control-label"><span class="text-red"></span>发放总额:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } placeholder="请输入一个整数金额，如50000"  type="text" class="form-control" id="totalAmount" name="totalAmount" style="width:645px" value="${couponVo.tAmount}"/>
			    	(元)
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="name" class="col-md-3 control-label"><span class="text-red"></span>优惠券使用时长:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly }  placeholder="请输入一个正整数，不超过2位。如3" type="text" class="form-control" id="type" name="type" style="width:645px" value="${coupon.type}"/>
			    </div>
			</div>
						
			<div class="form-group form-group-sm">
				<label for="amountType" class="col-md-3 control-label"><span class="text-red"></span>发放方式:</label>
			    <div class="col-md-6 has-feedback form-inline">
			    <input type="hidden" value="${coupon.amountType}" id="amountTypeValue">
					<select class="form-control" name="amountType" id="amountType" style="width:645px" ${readonly } >
						<!-- <option id="op_AmountType1" value="1" >--随机发放--</option> -->
						<option id="op_AmountType2" value="2" >--定额发放--</option>
					</select>
			  	</div>
			</div>

			<%-- <div class="form-group form-group-sm">
				<label for="startAmount" class="col-md-3 control-label"><span class="text-red"></span>随机发放区间:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly }  placeholder="最小值，仅限整数" type="text" class="form-control" id="startAmount" name="startAmount" style="width:270px" value="${coupon.startAmount}"/>
			    	(元)~
			    	<input ${readonly }  placeholder="最大值，仅限整数" type="text" class="form-control" id="endAmount" name="endAmount" style="width:270px" value="${coupon.endAmount}"/>
			    	(元)
			    </div>
			</div> --%>
			
			<div class="form-group form-group-sm">
	         <label class="col-md-3 control-label"><span class="text-red">* </span>优惠券类型:</label>
	         <div class="col-md-6 has-feedback">
	         	<div class="btn-group">
	           	<c:forEach items="${couponTotalTypes}" var="item">
					<label class="btn btn-default <c:if test="${fn:contains(couponTotalType,item['t'])}">active</c:if> couponTotalType" ballTypeCode="${item['t']}" ballType="${item['v']}">
				  		 ${item['v']}
					</label>
		       	</c:forEach>
				</div>
	         </div>
	      </div>

			<div class="form-group form-group-sm">
				<label for="amountTable" class="col-md-3 control-label"><span class="text-red"></span></label>
				<div id="couponTable" class="col-md-9 has-feedback">
				
				</div>
			</div>	
			
			<div class="form-group form-group-sm">
				<label id="prompt" class="col-md-3 control-label"><span class="text-red"></span></label>
				<div class="col-md-6 has-feedback form-inline">
					<span style="color:red; font-weight:bold;">注意：选取相应优惠券类型，双击单元格即可更改相应值，现只开放8大球优惠券面值、下单成功分享输入，其他功能输入暂不开放</span>
				</div>
			</div>
		<hr>
      	  <div class="form-group form-group-sm">
      	  	<c:if test="${readonly==' ' }">
			  	<div class="col-md-offset-3 col-md-2">
				   <a class="btn btn-default btn-block" href="javascript:window.history.go(-1);"><span class="glyphicon glyphicon-remove"></span> 返回</a>
				</div>         
		      	<div class="col-md-2">
			    	<button id = "submit_button" type="button" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
	    	<c:if test="${readonly!=' '}">
	    		<div class="col-md-offset-3 col-md-2">	
		    		<a href="javascript:window.history.go(-1);" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<shiro:hasPermission name="coupon:edit">
					<div class="col-md-2">
						<a href="${ctx }/coupon/sign?action=edit&id=${coupon.id}" class="btn btn-primary btn-block" > 修改 </a>
					</div>
				</shiro:hasPermission>
	    	</c:if>
      	  

		  </div>			 		 					
		</fieldset>
	</form>
  </div>
  <div class="panel-footer">
	<div class="row text-right">
		<div class="col-sm-12">
		</div>
	</div>	
  </div>	
</div>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	//城市券不显示此输入框
	$('#voEndTimeDiv').hide();
	
	// 菜单栏显示底色（选中）
	menu.active('#coupon-man');
	var amountType_value = $("#amountTypeValue").val();
	var type_value = $("#typeValue").val();
	var ballType_value = $("#ballTypeValue").val();
	var timeType_value = $("#timeTypeValue").val();
	var couponTypes_ = '${couponTypes}';
	var couponInfoMap_ = '${couponInfoMap}';
	var couponTypes = eval("("+couponTypes_+")");
	var couponTotalTypeMap_ = '${couponTotalTypeMap}';
	var couponTotalTypeMap = eval("("+couponTotalTypeMap_+")");
	if(couponInfoMap_){
		var couponInfoMap = eval("("+couponInfoMap_+")");
		$.each(couponInfoMap,function(key,value){
			initTemp(couponTypes,value,key,couponTotalTypeMap[key]);
		});
	}
	//发放方式 ：1随机 ,2固定金额(初始化页面)
	$("#op_AmountType"+amountType_value).attr("selected","selected");
	if(amountType_value == "2"){
		// 定额发放
		$('#editable').parents('div.form-group-sm').show();
		$('#prompt').parents('div.form-group-sm').show();
		$('#startAmount').parents('div.form-group-sm').hide();
	}else{
		// 随机发放
		$('#editable').parents('div.form-group-sm').show();
		$('#prompt').parents('div.form-group-sm').show();
		$('#startAmount').parents('div.form-group-sm').show();
	}
	
	//优惠券类型 ：0收单福利   ,1支付成功分享返券
	$("#op_typeValue"+type_value).attr("selected","selected");
	//适用球类
	$("#op_ballType"+ballType_value).attr("selected","selected");
	//时间类型
	$("#op_timeType"+timeType_value).attr("selected","selected");
	
	// 控件校验
 	$('#inputForm').validate({
		rules: {
			// 优惠券名称
			"name": {
				required: true,
				maxlength: 12
			},
			// 描述
			"couponDesc": {
				required: true,
				maxlength: 10
			},
			"type": {
				required: true,
				maxlength: 2
			},
			// 开始时间
			/* "startTime":{
				required: true
			},
			// 结束时间
			"endTime":{
				required: true
			}, */
			// 发放总额
			"totalAmount":{
				required: true,
				digits: true,
				range:[0,99999999],
			}
		},
		messages: {
			totalAmount: {
				digits:"必须为整数",
				range: "请输入一个介于 {0} 和 {99999999} 之间的值"
			},
			type: {
				digits:"必须为整数",
				range: "请输入一个介于 {0} 和 {99} 之间的值"
			},
			name: {
				maxlength:"不多于12个汉字"
			},
			couponDesc: {
				maxlength:"不多于20个汉字"
			}
		}
	}); 
	
	// 保存
 	$("#submit_button").click(function(){
 		checkValue();
 		var objContent = getTableValue();
 	// 判断输入优惠券金额是否大于总金额
		/* if(parseInt($("#totalAmount").val())<parseInt(couponAmout)){
			    common.showMessage("优惠券金额大于总金额，请重新输入");
	 	  		return false;
	 	  	} else { */
		 		if(objContent.length){
			 		var url = '${ctx }' + "/coupon/couponForm/" + JSON.stringify(objContent);
			 		$("#inputForm").attr('action', url);
			 		console.log(JSON.stringify(objContent));
			 		$("#inputForm").submit(); 
		 		} else {
		 			common.showMessage("请填写优惠券");
		 			return false;
		 		} 
	 	  	// } 
	});
	
 	// 低栏隐藏
	$("#adminFooter").hide();
 	
 	// 优惠券发放类型：随机--开始金额、结束金额显示;定额--优惠券金额显示
 	$("#amountType").change(function(){
 		var amountTypeSelected=$("#amountType").children('option:selected').val();
 		if (amountTypeSelected=='1'){
 			// 随机发放
 			$('#editable').parents('div.form-group-sm').show();
 			$('#prompt').parents('div.form-group-sm').show();
 			$('#startAmount').parents('div.form-group-sm').show();
 		}else if(amountTypeSelected=='2'){
 			// 定额发放
 			$('#editable').parents('div.form-group-sm').show();
 			$('#prompt').parents('div.form-group-sm').show();
 			$('#startAmount').parents('div.form-group-sm').hide();
 		}else{
 			$('#editable').parents('div.form-group-sm').show();
 			$('#prompt').parents('div.form-group-sm').show();
 			$('#startAmount').parents('div.form-group-sm').show();
 		}
 	});
 	
 	// 校验
 	function checkValue(){
 		// 优惠券发放类型
 		var aTypeSelected=$("#amountType").children('option:selected').val();
 	 	if (aTypeSelected=='1'){
 	 		// 开始金额
 	 		$("#startAmount").rules("add", {    
	            required: true,    
	            digits: true, 
	            range:[0,99999999],
	            messages: {    
	                digits: "必须为整数" ,
	                range: "请输入一个介于 {0} 和 {99999999} 之间的值"
	            }    
        	});   
 	 		// 结束金额
 	 		$("#endAmount").rules("add", {    
	            required: true,    
	            digits: true,    
	            range:[0,99999999],
	            messages: {    
	                digits: "必须为整数" ,
	                range: "请输入一个介于 {0} 和 {99999999} 之间的值"
	            }  
 	 		});
 	 	  	// 开始金额、结束金额校验
 	 	  	if(parseInt($("#startAmount").val())>parseInt($("#endAmount").val())){
 	 	  		common.showMessage("结束金额必须大于开始金额");
 	 	  		return false;
 	 	  	}
 	 	  $("#amount").val(0);
 	 	} else if (aTypeSelected=='2'){
 	 	 	$("#startAmount").val(0);
 	 	 	$("#endAmount").val(0);
 	 	}
 	 	
		return true;
 	}
 	
 	// table编辑
 	$("#couponTable").on("dblclick",".stats",function(e){
 		editCell();
 	});
 	
 	$('.couponTotalType').on("click",function(e){
 		var code = $(this).attr("ballTypeCode");
 		var name = $(this).attr("ballType");
 		if($(this).hasClass("active")){
 			$(this).removeClass("active");
 			$("table[types="+code+"]").remove();
 		}else{
 			$(this).addClass("active");	
	 		initTemp(couponTypes,null,code,name);
 		}
 		
 	});
 	
 	function editCell(event){
 		var currentCell;
 		
 		if(event == null){
 			currentCell = window.event.srcElement;
 		}else{
 			currentCell = event.target;	
 		}
 		var $firstTd=$(currentCell.parentNode).find('td').eq(0).find('input');
 		if(currentCell.cellIndex!=0 && $firstTd.is(':checked')){
	 		//if(currentCell.tagName.toLowerCase() == "td"){
	 		if(currentCell.cellIndex==2 || currentCell.cellIndex==4){
	 			var input = document.createElement("input");
	 	        input.type = 'text';
	 	        input.setAttribute('class', 'editable');
	 	        input.value = currentCell.innerHTML;
	 	        input.width = currentCell.style.width;
	 	        input.style.backgroundColor='#EEEEEE';
	 	        // 失去焦点
	 	        input.onblur = function(){
	 	            currentCell.innerHTML = input.value;
	 	            if(input.value){
		 	            if(!checkNum(input)){
		 	            	currentCell.style.backgroundColor='#EF5252';
		 	            } else {
		 	            	var couponValue=$(($(currentCell.parentNode).find('td'))[2]).text();
		 	            	var giveValue=$(($(currentCell.parentNode).find('td'))[3]).text();
		 	            	var orderValue=$(($(currentCell.parentNode).find('td'))[4]).text();
		 	            	var countValue=$(($(currentCell.parentNode).find('td'))[5]).text();
		 	            	if(couponValue != ''){
		 	            		couponValue = parseInt(couponValue);
		 	            	} else {
		 	            		couponValue = 0;
		 	            	}
		 	            	if(giveValue != ''){
		 	            		giveValue = parseInt(giveValue);
		 	            	} else {
		 	            		giveValue = 0;
		 	            	}
		 	            	if(orderValue != ''){
		 	            		orderValue = parseInt(orderValue);
		 	            	} else {
		 	            		orderValue = 0;
		 	            	}
		 	            	if(countValue != ''){
		 	            		countValue = parseInt(countValue);
		 	            	} else {
		 	            		countValue = 0;
		 	            	}
		 	            	if (countValue != 0){
		 	            		$(($(currentCell.parentNode).find('td'))[7]).text((couponValue+giveValue+orderValue)*countValue);
		 	            	}
		 	            	currentCell.style.backgroundColor='#FFF';
		 	            }
	 	            }else{
	 	            	currentCell.style.backgroundColor='#FFF';
	 	            }
	 	            //currentCell.removeChild(input);
	 	        };
	 	        // 按下键盘事件
	 	        input.onkeydown = function(event){
	 	            if(event.keyCode == 13){
	 	                input.blur();
	 	            }
	 	        };
	 	        currentCell.innerHTML = '';
	 	        currentCell.appendChild(input);
	 	        input.focus();
	 		}	
 		}
 	}
 	
 	$(".stats [type=checkbox]").change(function(e){ 		
 		var currentCell=e.target || e.srcElement;
 		
 		if(!$(currentCell).is(':checked')){
 			var currentParent=$(currentCell).parents('td');
 			$(currentParent).siblings().css({'backgroundColor':''}).text('');
 		}
 	});
 	
 	
 	// 获取table值
 	function getTableValue(){
 		var objContent=[];
		var objItem=[];
		var tbl=$('.stats');
		$.each(tbl,function(key,element){
			var tblTr = $(element).find("tbody tr");
			var code = $(element).attr("types");
			var objContent_ = [];
			for(var i=1;i<tblTr.length;i++){
				var $firstTd=$(tblTr[i]).find('td').eq(0).find('input');
				if($firstTd.is(':checked')){
					objItem=[];
					for(var j=0;j<$(tblTr[i]).find('td').length;j++){
						var $tdTmp=$(($(tblTr[i]).find('td'))[j]);
						if(j!=0){
							if(j==5){
								/* if($tdTmp.text()){ */
									//objItem.push($tdTmp.text());
									objItem.push(0);
								/* } else {
									common.showMessage("请输入优惠券数量");
									return false;
								} */
							} else if(j==1 || j==2 || j==3 || j==4) {
								objItem.push($tdTmp.text()?parseInt($tdTmp.text(),10):0);
							} else if(j==7){
								
							} else {
								objItem.push($tdTmp.text()?$tdTmp.text():"");
							}
						}
					}
					objContent_.push(objItem);
				}
			}
			objContent.push({"code":code,"obj":objContent_});
		});
		console.log(objContent);
		return objContent;
 	}
 	
 	//判断正整数  
 	function checkNum(input){  
 	     var re = /^([1-9]\d*|[0]{1,1})$/ ; 
 	     if (!re.test(input.value)){  
 	    	common.showMessage("请输入正整数");
 	        return false;  
 	     }  
 	     return true;
 	}  
	
});
function initTemp(couponTypes_,couponInfoMap_,code_,name_){
	var temp = new EJS({url: '${ctx}/static/template/coupon_info_tpl.ejs?ver=1.1'});
	var html = temp.render({"couponTypes":couponTypes_,"couponInfos":couponInfoMap_,"code":code_,"name":name_});	
	$("#couponTable").append(html);
}

function commonSet(){
	$('#voEndTimeDiv').toggle();
	$('#commonBtn').toggleClass('label-primary');
	$('#cityBtn').removeClass('label-primary');
	if (!$("#id").val()){
		$("#cityArea").find("select").val("");
	}
	$('#cityArea').hide();
	$('#durationDiv').toggle();
}

function citySet(){
	$('#voEndTimeDiv').toggle();
	$('#cityBtn').toggleClass('label-primary');
	$('#commonBtn').removeClass('label-primary');
	$('#cityArea').toggle();
	$('#durationDiv').toggle();
}
</script>