<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡列表</title>
</head>
<body>

<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="cardForm" class="form-horizontal" action="${ctx }/cardManage/saveCard" method="post" name="id">
    <div class="modulHead">
        <p>会员卡管理 》添加会员卡</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="id" name="id" value="${card.id }">
        <ul>
            <li>
                <span class="title">卡片名称：</span>
                <input type="text" id="cardName" name="cardName" value="${card.cardName }">
            </li>
            <li>
                <span class="title">面值：</span>
                <input type="text" id="cardAmount" name="cardAmount" value="${card.cardAmount }" onkeypress="keyPress()">
            </li>
            <li>
                <span class="title">赠送金额：</span>
                <input type="text" id="cardGift" name="cardGift" value="${card.cardGift }" onkeypress="keyPress()">
            </li>
            <li>
                <span class="title">卡片类型：</span>
                <div class="btn-group" id="teachDiv" data-toggle="buttons">
                    <label class="btn btn-default<c:if test="${card.cardType == 1 || card.cardType == null}"> active</c:if>">
                        <input type="radio" name="cardType" id="cardType" value="1"  autocomplete="off"
                               <c:if test='${card.cardType == 1 || card.cardType == null}'>checked="checked"</c:if> >
                        储值卡
                    </label>
                    <label class="btn btn-default<c:if test="${card.cardType == 2}"> active</c:if>">
                        <input type="radio" name="cardType" id="cardType" value="2" autocomplete="off"
                               <c:if test='${card.cardType == 2}'>checked="checked"</c:if> >
                        期限卡
                    </label>
                </div>
            </li>
        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveCardForm()">保存</a>
        </div>

    </div>
</form>

<script type="text/javascript">
    $(function() {
        // 样式
        $('#card-man').addClass("active");
        $('#CARD_TYPE').addClass("active");
    });
    
    // 保存
    function saveCardForm() {
        // 判断卡片名称是否为空
        if ($('#cardName').val() == undefined || $('#cardName').val() == ''){
            swal({
                title: "警告",
                text: "卡片名称不能为空！"
            });
            return;
        }
        // 判断面值是否为空
        if ($('#cardAmount').val() == undefined || $('#cardAmount').val() == ''){
            swal({
                title: "警告",
                text: "面值不能为空！"
            });
            return;
        }
        // 判断赠送金额是否为空
        if($('#cardGift').val() == undefined || $('#cardGift').val() == ''){
            $('#cardGift').val(0);
           /* swal({
                title: "警告",
                text: "赠送金额不能为空！"
            });
            return;*/
        }
        // 提交
        $('#cardForm').submit();
    }

</script>

</body>
</html>