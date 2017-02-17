<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡充值/延期</title>
</head>
<body>

<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="rechargeCardForm" class="form-horizontal" action="${ctx }/cardManage/saveRechargeCard" method="post">
    <div class="modulHead">
        <p>会员卡管理 》账户充值 / 延期</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="accountId" name="accountId" value="${userVo.accountId }">
        <input type="hidden" id="balance" name="balance" value="${userVo.balance }">
        <input type="hidden" id="userId" name="userId" value="${userVo.userId }">
        <input type="hidden" id="cardId" name="cardId">
        <input type="hidden" id="cardType" name="cardType" value="${userVo.cardType }">
        <input type="hidden" id="payType" name="payType" value="1">
        <input type="hidden" id="oldStartDate" name="oldStartDate" value="${userVo.oldStartDate }">

        <ul>
            <li>
                <span class="title">姓名：</span>
                <div class="turn_card">${userVo.name }</div>
            </li>
            <li>
                <span class="title">手机号：</span>
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
                <span class="title">余额：</span>
                <div class="turn_card">
                    <fmt:formatNumber type="number" value="${userVo.balance/100 }" maxFractionDigits="0"/>
                </div>
            </li>
            <li id="timeArea" style="display: none">
                <span class="title">期限：</span>
                <div class="turn_card">${userVo.cardTime }</div>
            </li>
            <div id="rechargeType" style="display: none">
                <li>
                    <span class="title">充值方式：</span>
                    <div class="btn-group" id="rechargeDiv" data-toggle="buttons">
                        <label class="btn btn-default<c:if test="${userVo.cardType == 1 || userVo.cardType == null}"> active</c:if>">
                            <input type="radio" name="pType" id="pType" value="1"  autocomplete="off"
                                   <c:if test='${userVo.cardType == 1 || userVo.cardType == null}'>checked="checked"</c:if> >
                            随机
                        </label>
                        <label class="btn btn-default<c:if test="${userVo.cardType == 2}"> active</c:if>">
                            <input type="radio" name="pType" id="pType" value="2" autocomplete="off"
                                   <c:if test='${userVo.cardType == 2}'>checked="checked"</c:if> >
                            套餐
                        </label>
                    </div>
                </li>
            </div>
            <div id="amountDiv" style="display: none">
                <li>
                    <span class="title">充值金额：</span>
                    <input type="text" id="amount" name="amount" value="0" onkeypress="keyPress()">
                </li>
            </div>
            <div id="cardPac" style="display: none">
                <li>
                    <span class="title">会员卡：</span>
                    <span id="captainName"></span> <input readonly type="text" class="form-control" id="cardName"
                                                          value="${cardVo.cardName }" placeholder="请选择卡片" style="width: 200px" />
                    <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
                </li>
                <li>
                    <span class="title">面值：</span>
                    <input type="text" id="cardAmount" name="cardAmount" value="0" readonly>
                </li>
                <li>
                    <span class="title">赠送金额：</span>
                    <input type="text" id="cardGift" name="cardGift" value="0" readonly>
                </li>
            </div>
            <div id="dateDiv">
                <li style="display: none">
                    <span class="title">开始日期：</span>
                    <input type="text" id="newStartDate" name="newStartDate" value="${userVo.oldEndDate }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'newEndDate\')}'})">
                </li>
                <li>
                    <span class="title">延期日期：</span>
                    <input type="text" id="endDate" name="endDate"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'newStartDate\')}'})">
                </li>
            </div>


        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <c:choose>
                <c:when test="${userVo.cardType == 1 }">
                    <a class="saveBtnBot" href="javascript:saveRechargeCard()">充值</a>
                </c:when>
                <c:when test="${userVo.cardType == 2 }">
                    <a class="saveBtnBot" href="javascript:saveRechargeCard()">延期</a>
                </c:when>
            </c:choose>
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
            $('#rechargeType').show();
            $('#amountDiv').toggle();
        } else {
            $('#amountDiv').toggle();
            $('#amount').val(0);
            $('#timeArea').show();
        }


    });

    // 选取卡片类型
    $('#sel_captain').on('click', function() {
        $("#myDlgBody_lg").load("${ctx}/cardManage/card_query_dlg", {
        });
        $("#myDlg_lg").modal();
    })
    function captainAddCallBack(cardId, cardName, cardType, cardAmount, cardGift) {
        $("#cardId").val(cardId);
        $("#cardName").val(cardName);
        $('#cardType').val(cardType);
        $('#cardAmount').val(cardAmount/100);
        $('#cardGift').val(cardGift/100);
        if (cardType == 1) {
            $("#cardType1").attr("checked", "checked");
            $('#cType1').addClass("active");
            $('#cType2').removeClass("active");
            $('#dateDiv').hide();
        } else {
            $("#cardType2").attr("checked", "checked");
            $('#cType2').addClass("active");
            $('#cType1').removeClass("active");
            $('#dateDiv').show();
        }
    }

    // 充值方式选择
    $("#rechargeDiv").on("click","label",function(){
        if($(this).find("input[name=pType]").val() == '1'){
            $("#cardPac").hide();
            $('#amountDiv').toggle();
            $('#payType').val(1);
        } else {
            $("#cardPac").toggle();
            $('#amountDiv').hide();
            $('#payType').val(2);
        }
    });

    // 保存
    function saveRechargeCard() {
        // 套餐
        if ($('#payType').val() == 2) {
            // 判断卡片名称是否为空
            if ($('#cardName').val() == undefined || $('#cardName').val() == ''){
                swal({
                    title: "警告",
                    text: "卡片名称不能为空！"
                });
                return;
            }
        }
        if ($('#cardType').val() == 2) {
            // 判断期限卡延期是否为空
            if ($('#endDate').val() == undefined || $('#endDate').val() == ''){
                swal({
                    title: "警告",
                    text: "延期日期不能为空！"
                });
                return;
            }
        }

        // 提交
        $('#rechargeCardForm').submit();
    }

</script>

</body>
</html>