<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<form id="priceForm" action="${ctx }/member/price/update" method="post">
    <input type="hidden" name="id" value="${statiumPrice.id }">
    <input type="hidden" name="statiumId" value="${statiumPrice.statiumId }">
    <input type="hidden" name="sportType" value="${param.sportType }">
    <div class="form-group">
        <label for="fee" class="control-label">星期一:</label>
        <input type="text" class="form-control" name="douMonday"value="${lf:formatMoney(statiumPrice.monday) }">
    </div>
    <div class="form-group">
        <label for="fee" class="control-label">星期二:</label>
        <input type="text" class="form-control" name="douTuesday" value="${lf:formatMoney(statiumPrice.tuesday) }">
    </div>
    <div class="form-group">
        <label for="fee" class="control-label">星期三:</label>
        <input type="text" class="form-control" name="douWensday" value="${lf:formatMoney(statiumPrice.wensday) }">
    </div>
    <div class="form-group">
        <label for="fee" class="control-label">星期四:</label>
        <input type="text" class="form-control" name="douThursday" value="${lf:formatMoney(statiumPrice.thursday) }">
    </div>
    <div class="form-group">
        <label for="fee" class="control-label">星期五:</label>
        <input type="text" class="form-control" name="douFriday" value="${lf:formatMoney(statiumPrice.friday) }">
    </div>
    <div class="form-group">
        <label for="fee" class="control-label">星期六:</label>
        <input type="text" class="form-control" name="douSaturday" value="${lf:formatMoney(statiumPrice.saturday) }">
    </div>
    <div class="form-group">
        <label for="fee" class="control-label">星期日:</label>
        <input type="text" class="form-control" name="douSunday" value="${lf:formatMoney(statiumPrice.sunday) }">
    </div>
</form>

<script type="text/javascript">
    $(function() {
    	$("#priceForm").validate({
            rules : {
                douMonday : {
                    required : true
                },
                douTuesday : {
                    required : true
                },
                douWensday : {
                    required : true
                },
                douThursday : {
                    required : true
                },
                douFriday : {
                    required : true
                },
                douSaturday : {
                    required : true
                },
                douSunday : {
                    required : true
                }
            },
            messages : {
                douMonday : {
                    required : "请输入星期一的价格!"
                },
                douTuesday : {
                    required : "请输入星期二的价格!"
                },
                douWensday : {
                    required : "请输入星期三的价格!"
                },
                douThursday : {
                    required : "请输入星期四的价格!"
                },
                douFriday : {
                    required : "请输入星期五的价格!"
                },
                douSaturday : {
                    required : "请输入星期六的价格!"
                },
                douSunday : {
                    required : "请输入星期日的价格!"
                }
            }
        });
    	$('#footer').hide();
    });
    
</script>