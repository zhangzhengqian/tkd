<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场地价格管理</title>
</head>
<body>

<style>
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
    vertical-align: middle;
    font-size: 16px;
}
.btn-toolbar {
    display: inline-block;
    cursor: pointer;
}
</style>

	<div class="panel panel-default">
	    <div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon glyphicon-home active"></span> 场地价格管理</li>
		    </ul>
		</div><!-- / 右侧标题 -->
		<table id="contentTable" class="table table-bordered table-hover">
			<thead class="thead">
				<tr>
	                <th class="text-center">场地类型</th>
	                <th class="text-center">类型</th>
                    <th class="text-center">价格</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${lf:mySportTypes()}" var="sportType">
				<tr>
					<td rowspan="2" class="text-center">${sportType.itemName}</td>
					<td class="text-center">工作日</td>
                    <td class="text-center">
                        <c:set var="spacePrice" value="${lf:spacePrice(sportType.itemCode, true) }" />
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" 
                            data-id="${spacePrice.id }" 
                            data-statium-id="${spacePrice.statiumId }" 
                            data-prices="${spacePrice.price }" 
                            data-is-workday="true" 
                            data-sport-type="${sportType.itemCode }" >
                            <c:forEach items="${lf:spacePriceInfos(sportType.itemCode, true)}" var="priceInfo">
							<div class="btn-group" role="group" aria-label="First group" 
                                title="${priceInfo.start}~${priceInfo.end}时价格"
                                data-content=" ${lf:formatMoney(priceInfo.price) }元/小时" 
                                data-placement="top" data-toggle="popover" data-trigger="hover" 
							    data-price-start="${priceInfo.start }" data-price-end="${priceInfo.end }" 
							    data-price="${lf:formatMoney(priceInfo.price) }">
                                <c:forEach items="${lf:hours(priceInfo.start, priceInfo.end)}" var="hour">
								<button type="button" class="btn btn-default disabled <c:if test='${priceInfo.price > 0 }'>btn-success</c:if>">${hour }</button>
								</c:forEach>
							</div>
							</c:forEach>
                        </div>
                    </td>
				</tr>
                <tr>
                    <td class="text-center">节假日</td>
                    <td class="text-center">
                        <c:set var="spacePrice" value="${lf:spacePrice(sportType.itemCode, false) }" />
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" 
                            data-id="${spacePrice.id }" 
                            data-statium-id="${spacePrice.statiumId }" 
                            data-prices="${spacePrice.price }" 
                            data-is-workday="false" 
                            data-sport-type="${sportType.itemCode }" >
                            <c:forEach items="${lf:spacePriceInfos(sportType.itemCode, false)}" var="priceInfo">
                            <div class="btn-group" role="group" aria-label="First group" 
                                title="${priceInfo.start}~${priceInfo.end}时价格"
                                data-content=" ${lf:formatMoney(priceInfo.price) }元/小时" 
                                data-placement="top" data-toggle="popover" data-trigger="hover" 
                                data-price-start="${priceInfo.start }" data-price-end="${priceInfo.end }" 
                                data-price="${lf:formatMoney(priceInfo.price) }">
                                <c:forEach items="${lf:hours(priceInfo.start, priceInfo.end)}" var="hour">
                                <button type="button" class="btn btn-default disabled <c:if test='${priceInfo.price > 0 }'>btn-success</c:if>">${hour }</button>
                                </c:forEach>
                            </div>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
			</c:forEach>
			</tbody>		
		</table>
		
        <tags:errors />
    </div>

    <div id="priceDialog" class="modal fade">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">场地价格</h4>
                </div>
                <div class="modal-body">
					<form id="priceForm" action="${ctx }/member/price/update" method="post">
					    <input type="hidden" id="id" name="id">
					    <input type="hidden" id="statiumId" name="statiumId">
					    <input type="hidden" id="sportType" name="sportType">
                        <input type="hidden" id="isWorkday" name="isWorkday">
                        
					    <div class="form-group">
					        <label for="fee" class="control-label">开始时间:</label>
					        <select class="form-control" id="start" name="start">
					            <c:forEach items="${lf:hours(0, 24)}" var="hour">
					            <option value="<fmt:formatNumber value='${hour }' pattern='#'/>">${hour }</option>
					            </c:forEach>        
					        </select>
					    </div>
					    <div class="form-group">
					        <label for="fee" class="control-label">结束时间:</label>
					        <select class="form-control" id="end" name="end">
					            <c:forEach items="${lf:hours(1, 25)}" var="hour">
					            <option value="<fmt:formatNumber value='${hour }' pattern='#'/>">${hour }</option>
					            </c:forEach>        
					        </select>
					    </div>
					    <div class="form-group">
					        <label for="fee" class="control-label">价格:</label>
					        <input type="text" class="form-control required" id="douPrice" name="douPrice">
					    </div>
					</form>                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="btnSubmit" class="btn btn-primary">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

<script type="text/javascript">
	$(function() {
		menu.active('#price-man');
		
		$("#btnSubmit").click(function() {
			$("#priceForm").submit();
		});
		$('#footer').hide();
		
		$('[data-toggle="popover"]').popover();
		
        $(".btn-group").click(function() {
            var start = $(this).attr("data-price-start");
            var end = $(this).attr("data-price-end");
            var price = $(this).attr("data-price");
            var parent = $(this).parent();
            
            $("#start").val(start);
            $("#end").val(end);
            $("#douPrice").val(price);
            
            $("#id").val($(parent).attr("data-id") + "");
            $("#statiumId").val($(parent).attr("data-statium-id"));
            $("#sportType").val($(parent).attr("data-sport-type"));
            $("#isWorkday").val($(parent).attr("data-is-workday"));
            
        	$("#priceDialog").modal();
        });
        
        jQuery.validator.addMethod("compareAB",function(value, element, param) {
        	return parseInt(value) < parseInt($(param).val());
        });
        
        $("#priceForm").validate({
            rules : {
            	start : {
            		compareAB : "#end"
            	},
            	douPrice : {
            		isMoney : true
            	}
            },
            messages : {
            	start :{
            		compareAB : "开始时间应早于结束时间!"
            	}
            },  
            /* 重写错误显示消息方法,以alert方式弹出错误消息 */  
            showErrors : function(errorMap, errorList) {  
             var msg = "";  
             $.each(errorList, function(i, v) {  
                msg += (v.message + "\r\n");  
                return false; 
             });  
             if (msg != "")alert(msg);  
            },  
            /* 失去焦点时不验证 */  
            onfocusout : false  ,
            onfocus : false ,
            onkeyup : false ,
            onkeydown : false,
            onchange:false,
            onblur:false,
            onclick:false
        });
	});
	
	function load(sportType, title) {
		$(".modal-title").html(title + "场地价格");
		$(".modal-body").load("${ctx}/member/price/update?plain&sportType=" + sportType, function() {
			$("#priceDialog").modal();
		});
	}
</script>

</body>
</html>
