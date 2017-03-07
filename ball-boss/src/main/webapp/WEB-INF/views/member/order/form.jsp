<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>预约</title>
	<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
    <style>
    .form-control {
        width: 100%;
    }
    </style>
</head>
<body>
    <div class="panel panel-default">
        <div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon glyphicon-home"></span> 预约管理</li>
		        <li class="active">
		          预约
		        </li>
		    </ul>
        </div><!-- / 右侧标题 -->
        
        <div class="panel-body">
            <div class="row">
                <form id="inputForm" action="${ctx}/member/order/create" method="post" class="form-horizontal">
                    <zy:token/>
                    <div class="col-xs-4">
                    <input type="hidden" name="statiumId" id="statiumId" value="${statiumId}">
                     <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>联系电话:</label>
                            <div class="col-xs-7">
                                <input type="text" name="phone" id="phone" class="form-control" placeholder="请输入手机号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>会员姓名:</label>
                            <div class="col-xs-7">
                                <input type="text" name="realName" id="realName" class="form-control" placeholder="请输入会员姓名">
                            </div>
                        </div>
                         <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>订单类型:</label>
                            <div class="col-xs-7">
                                 <select  class="form-control" id="ordersType" name="ordersType">
				                   <option value="0" selected="selected" >场馆</option>
				                   <option value="1"> 教/陪练</option>
				                   <option value="2">活动</option>
					            </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>场地类型:</label>
                            <div class="col-xs-7">
                                <select class="form-control" id="sportType" name="sportType">
                                <c:forEach items="${lf:mySportTypes() }" var="item">
                                    <option value="${item.itemCode }">${item.itemName }</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>场地名称:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                   <select class="form-control" name="code" id="type">
                                    
                                   </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>开场日期:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input type="text" name="strStartDate" id="strStartDate" class="form-control" readonly>
                                    <label for="strStartDate"  class="input-group-addon"><i class="fa fa-calendar"></i></label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>开始时间:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input type="text" name="strStartTime" id="strStartTime" class="form-control" placeholder="从什么时候开始"  onClick="WdatePicker({dateFmt:'H:mm',maxDate:'#F{$dp.$D(\'strEndTime\')}'})" oninput="loadIdleSpaces()"   readonly>
                                    <label for="strStartTime" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>结束时间:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input type="text" name="strEndTime" id="strEndTime" class="form-control" placeholder="到什么时间结束"  onClick="WdatePicker({dateFmt:'H:mm',minDate:'#F{$dp.$D(\'strStartTime\')}'})" oninput="loadIdleSpaces()"  readonly>
                                    <label for="strEndTime" class="input-group-addon"><i class="fa fa-clock-o"></i></label>
                                </div>
                            </div>
                        </div>
                       <!-- <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>场地价格:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input type="text" name="douPrice" id="douPrice" class="form-control" placeholder="25">
                                    <span class="input-group-addon">元/小时</span>
                                </div>
                            </div>
                        </div>-->
                <!--         <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>保留时间:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input type="text" name="waitTime" class="form-control" placeholder="保留该场地多久">
                                    <span class="input-group-addon">分钟</span>
                                </div>
                            </div>
                        </div> -->
                        <div class="form-group">
                            <label class="col-xs-5 control-label"><span class="text-red">* </span>预定方式:</label>
                            <div class="col-xs-7">
                                <label class="radio-inline"><input id="orderType0" type="radio" name="orderType" value="BOOK_LIVE" >现场消费</label>
                                <label class="radio-inline"><input id="orderType1" type="radio" name="orderType" value="BOOK_TELEPHONE" >电话预定</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-5 control-label">预付押金:</label>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input type="text" name="douDeposit" class="form-control" placeholder="500(电话预定不用填)">
                                    <span class="input-group-addon">元</span>
                                </div>
                            </div>
                        </div>
		                <div class="form-group">
		                    <div class="col-md-offset-3 col-md-4">
		                        <a class="btn btn-default btn-block" href="${ctx}/member/order"><span class="glyphicon glyphicon-remove"></span> 返回</a>
		                    </div>
		                    <div class="col-md-4">
		                        <button type="submit" class="btn btn-primary btn-block" id="submit_btn" ><span class="glyphicon glyphicon-ok"></span> 保存</button>
		                    </div>
		                </div>
                        
                    </div>
                
	                <div id="idleSpaces" class="col-xs-8">
	                </div>        
                </form>
            </div>
        </div>        
	</div>

	<script type="text/javascript">
		$(function() {
			menu.active('#order-man');
			$('#footer').hide();

			$('#inputForm').validate({
				rules : {
				//	douPrice : {
				//		required : true
				//	},
					spaceId : {
						required : true
                    },
                    strStartDate : {
                        required : true
                    },
                    strStartTime : {
                        required : true
                    },
                    strEndTime : {
                        required : true
                    },
                    waitTime : {
                        required : true
                    },
                    phone : {
                        required : true
                    },
                    realName : {
                        required : true
					}
				},
				messages : {
				//	douPrice : {
				//		required : '请输入场地价格！'
				//	},
                    spaceId : {
						required : '请输入场地编码！'
                    },
                    strStartDate : {
                        required : '请选择开场日期！'
                    },
                    strStartTime : {
                        required : '请选择开始时间！'
                    },
                    strEndTime : {
                        required : '请选择结束时间！'
                    },
                    waitTime : {
                        required : '请输入等待时间！'
                    },
                    phone : {
                        required : '请输入联系电话！'
					},
					realName : {
						required : '请输入会员姓名！'
					}
				}
			});
			
            $("#strStartTime").focus(function() {
                WdatePicker({dateFmt:'H:mm',onpicked:function() {
                	$("#strStartTime").change();
                }});
            });
            
            $("#strEndTime").focus(function() {
                WdatePicker({dateFmt:'H:mm',onpicked:function() {
                	$("#strEndTime").change();
                }});
            });
			            
            $("#phone").change(function() {
            	var phone = $("#phone").val();
            	if (!!phone) {
                    $.getJSON("${ctx}/member/customer/data?plain", {
                    	phone: phone
                    }, function(data){
                    	$("#realName").val(data.realName);
                    });
            	}
            });
             //变更场地类型
             $("#sportType").change(function() {
            	 loadSpace();
             });
             //变更场地名称
             $("#type").change(function(){
            	 loadIdleSpaces();
             });
             //获取场地信息
             function loadIdleSpaces() {
                 if (!$("#strStartDate").val()) {
                     return;
                 }
                
                 if (!$("#strStartTime").val()) {
                     return;
                 }
                 
                 if (!$("#strEndTime").val()) {
                     return;
                 }
                 
              	 $("#idleSpaces").load("${ctx}/member/space/idle?plain", {
              		 code: $("#type").val(),
              		 sportType: $("#sportType").val(),
              		 startDate: $("#strStartDate").val(),
              		 startTime: $("#strStartTime").val(),
              		 endTime: $("#strEndTime").val(),
              		 }, function(){});
             }
             
             $("#strStartDate").focus(function() {
                 WdatePicker({minDate:'#F{\'%y-%M-%d\'}',onpicked:function() {
                     $("#strStartDate").change();
                 }});
             });
             
             $("#strStartDate").change(function() {
            	 loadIdleSpaces();
             });
             
             $("#strStartTime").change(function() {
                 if (!$("#strEndTime").val()) {
                     return;
              	 }else{
                	 loadIdleSpaces();
              	 }
             });
             
             $("#strEndTime").change(function() {
                 if (!$("#strEndTime").val()) {
                     return;
              	 }else{
              		loadIdleSpaces();
              	 }
             });
             
     		$(window).load(function() {
    			loadSpace();
    			$("#orderType1").attr("checked",true);
            })
    		
    		function loadSpace(){
    			$.post("${ctx}/member/price/space?plain", {
    				sportType : $("#sportType").val(),
    				statiumId : $("#statiumId").val()
    			}, function(space) {
    				//解析json
    				var obj = eval(space);
    				var html = "";
    				for (var i = 0; i < obj.length; i++) {
    				 	html += '<option value='+obj[i].spaceCode+' class="opclass">' + obj[i].spaceName +'</option>';
    				}
    				$(".opclass").remove();
    				$("#type").append(html);
    				loadIdleSpaces();
    			});
    		}
		});
	
	</script>
</body>
</html>
