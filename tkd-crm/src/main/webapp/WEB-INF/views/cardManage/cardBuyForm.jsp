<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡购买</title>
</head>
<body>

<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="cardUserForm" class="form-horizontal" action="${ctx }/cardManage/saveCardUser" method="post" name="id">
    <div class="modulHead">
        <p>会员卡管理 》会员卡购买</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="cardId" name="cardId" value="${cardVo.cardId }">
        <input type="hidden" id="cardType" name="cardType" value="${cardVo.cardType }">
        <input type="hidden" id="userId" name="userId" value="${cardVo.userId }">
        <ul>
            <li>
                <span class="title">会员姓名：</span>
                <input type="text" id="name" name="name" value="${cardVo.name }">
            </li>
            <li>
                <span class="title">手机号码：</span>
                <input type="text" id="phone" name="phone" maxlength=11 value="${cardVo.phone }">
            </li>
            <li>
                <span class="title">会员卡：</span>
                <span id="captainName"></span> <input readonly type="text" class="form-control" id="cardName"
                                                      value="${cardVo.cardName }" placeholder="请选择卡片" style="width: 200px" />
                <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
            </li>
            <li>
                <span class="title">类型：</span>
                <div class="btn-group" id="teachDiv" data-toggle="buttons" >
                    <label class="btn btn-default<c:if test="${card.cardType == 1}"> active</c:if>" id="cType1">
                        <input type="radio" id="cardType1" value="1"  autocomplete="off"
                               <c:if test='${card.cardType == 1 || card.cardType == null}'>checked="checked"</c:if>>
                        储值卡
                    </label>
                    <label class="btn btn-default<c:if test="${card.cardType == 2}"> active</c:if>" id="cType2">
                        <input type="radio" id="cardType2" value="2" autocomplete="off"
                               <c:if test='${card.cardType == 2}'>checked="checked"</c:if>>
                        期限卡
                    </label>
                    <label style="margin-top: 10px; margin-left:10px; color: red">* 无需选择</label>
                </div>
            </li>
            <li>
                <span class="title">面值：</span>
                <input type="text" id="cardAmount" name="cardAmount" value="${cardVo.cardAmount }" readonly>
            </li>
            <li>
                <span class="title">赠送金额：</span>
                <input type="text" id="cardGift" name="cardGift" value="${cardVo.cardGift }" readonly>
            </li>
            <div id="dateDiv">
                <li>
                    <span class="title">开始日期：</span>
                    <input type="text" id="startDate" name="startDate" value="${cardVo.startDate }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})">
                </li>
                <li>
                    <span class="title">到期日期：</span>
                    <input type="text" id="endDate" name="endDate" value="${cardVo.endDate }"
                           onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})">
                </li>
            </div>


        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveCardUser()">保存</a>
        </div>

    </div>
</form>

<script type="text/javascript">
    $(function() {
        // 样式
        $('#card-man').addClass("active");
        $('#CARD_USER').addClass("active");

        // 期限卡控制域
        if (card.cardType == 1) {
            $('#dateDiv').hide();
        } else {
            $('#dateDiv').show();
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

    // 保存
    function saveCardUser() {
        // 判断会员姓名是否为空
        if ($('#name').val() == undefined || $('#name').val() == ''){
            swal({
                title: "警告",
                text: "会员姓名不能为空！"
            });
            return;
        }
        // 判断手机号是否为空
        if ($('#phone').val() == undefined || $('#phone').val() == ''){
            swal({
                title: "警告",
                text: "手机号不能为空！"
            });
            return;
        }
        // 判断套餐是否选择
        if ($('#cardType').val() == undefined || $('#cardType').val() == ''){
            swal({
                title: "警告",
                text: "请选择相应卡片！"
            });
            return;
        }
        // 期限卡
        if ($('#cardType').val() == 2) {
            // 判断开始日期是否为空
            if ($('#startDate').val() == undefined || $('#startDate').val() == ''){
                swal({
                    title: "警告",
                    text: "开始日期不能为空！"
                });
                return;
            }
            // 判断结束日期是否为空
            if ($('#endDate').val() == undefined || $('#endDate').val() == ''){
                swal({
                    title: "警告",
                    text: "结束日期不能为空！"
                });
                return;
            }
        }

        // 提交
        $('#cardUserForm').submit();
    }

</script>

</body>
</html>