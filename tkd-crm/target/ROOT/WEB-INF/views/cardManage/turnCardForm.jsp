<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡转卡</title>
</head>
<body>

<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="turnCardForm" class="form-horizontal" action="${ctx }/cardManage/saveTurnCard" method="post">
    <div class="modulHead">
        <p>会员卡管理 》会员卡转卡</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="cardId" name="cardId" value="${userVo.cardId }">
        <input type="hidden" id="cardType" name="cardType" value="${userVo.cardType }">
        <input type="hidden" id="oldUserId" name="oldUserId" value="${userVo.userId }">
        <input type="hidden" id="oldAccountId" name="oldAccountId" value="${userVo.accountId }">
        <input type="hidden" id="oldBalance" name="oldBalance" value="${userVo.balance }">

        <ul>
            <li>
                <span class="title">原卡姓名：</span>
                <div class="turn_card">${userVo.name }</div>
            </li>
            <li>
                <span class="title">原手机号：</span>
                <div class="turn_card">${userVo.phone }</div>
            </li>
            <li>
                <span class="title">类型：</span>
                <div class="turn_card">
                    <c:if test="${userVo.cardType == 1 }">储值卡</c:if>
                    <c:if test="${userVo.cardType == 2 }">期限卡</c:if>
                </div>
            </li>
            <li id="balanceArea" style="display: none">
                <span class="title">原卡余额：</span>
                <div class="turn_card">
                    <fmt:formatNumber type="number" value="${userVo.balance/100 }" maxFractionDigits="0"/>
                </div>
            </li>
            <li id="timeArea" style="display: none">
                <span class="title">原卡期限：</span>
                <div class="turn_card">${userVo.cardTime }</div>
            </li>
            <li>
                <span class="title">新卡姓名：</span>
                <input type="text" id="newName" name="newName">
            </li>
            <li>
                <span class="title">新手机号：</span>
                <input type="text" id="newPhone" name="newPhone">
            </li>
            <li>
                <span class="title">成本：</span>
                <input type="text" id="cardCost" name="cardCost"  onkeypress="keyPress()">
            </li>
            <li>
                <span class="title">原因：</span>
                <input type="text" id="turnReason" name="turnReason">
            </li>
            <div id="dateDiv">
                <li>
                    <span class="title">开始日期：</span>
                    <input type="text" id="newStartDate" name="newStartDate" value="${userVo.oldStartDate }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'newEndDate\')}'})">
                </li>
                <li>
                    <span class="title">到期日期：</span>
                    <input type="text" id="newEndDate" name="newEndDate" value="${userVo.oldEndDate }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'newStartDate\')}'})">
                </li>
            </div>


        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveTurnCard()">转卡</a>
        </div>

    </div>
</form>

<script type="text/javascript" src="${ctx }/static/lib/numValidation.js"></script>
<script type="text/javascript">
    $(function() {
        // 样式
        $('#card-man').addClass("active");
        $('#CARD_USER').addClass("active");

        // 卡片类型不同、展示不同
        if (${userVo.cardType == 1}) {
            $('#balanceArea').show();
            $('#dateDiv').hide();
        } else {
            $('#timeArea').show();
        }


    });

    // 保存
    function saveTurnCard() {
        // 判断新卡姓名是否为空
        if ($('#newName').val() == undefined || $('#newName').val() == ''){
            swal({
                title: "警告",
                text: "新卡姓名不能为空！"
            });
            return;
        }
        // 判断新手机号是否为空
        if ($('#newPhone').val() == undefined || $('#newPhone').val() == ''){
            swal({
                title: "警告",
                text: "新手机号不能为空！"
            });
            return;
        }
        // 判断成本是否为空
        if ($('#cardCost').val() == undefined || $('#cardCost').val() == ''){
            swal({
                title: "警告",
                text: "成本不能为空！"
            });
            return;
        }
        // 判断原因是否为空
        if ($('#turnReason').val() == undefined || $('#turnReason').val() == ''){
            swal({
                title: "警告",
                text: "原因不能为空！"
            });
            return;
        }
       $('#turnCardForm').submit();
    }

</script>

</body>
</html>