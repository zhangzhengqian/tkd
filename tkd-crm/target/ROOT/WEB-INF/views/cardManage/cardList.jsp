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

<form  id="cardForm" class="form-horizontal" action="${ctx }/cardManage/cardList" method="post" name="id">
    <div class="orderSearch myVipOrderSearch">
        <ul>
            <li class="timeLi subSearchLi1">
                <span>卡片类型：</span>
                <select id="search_EQ_cardType" name="search_EQ_cardType">
                    <option value="">请选择</option>
                    <option value="1">储值卡</option>
                    <option value="2">期限卡</option>
                </select>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardSubmit()" >查询</a>
            </li>

            <li class="subSearch subSearchLi">
            </li>
            <li class="subSearch subSearchLi">
                <a class="submit" href="${ctx }/cardManage/initCardForm">添加会员卡 </a>
            </li>

        </ul>
    </div>
</form>

<div class="orderResult">
    <table>
        <tr>
            <th>No.</th>
            <th>卡片名称</th>
            <th>面值(应交金额)</th>
            <th>赠送金额</th>
            <th>卡种</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${data.content }" var="card" varStatus="stat">
            <tr>
                <td>${stat.count }</td>
                <td>${card.cardName }</td>
                <td><fmt:formatNumber type="number" value="${card.cardAmount/100 }" maxFractionDigits="0"/></td>
                <td><fmt:formatNumber type="number" value="${card.cardGift/100 }" maxFractionDigits="0"/></td>
                <c:choose>
                    <c:when test="${card.cardType == 1}">
                        <td class="warning">储值卡</td>
                    </c:when>
                    <c:when test="${card.cardType == 2}">
                        <td class="info">期限卡</td>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${card.status == 0}">
                        <td class="warning">未激活</td>
                    </c:when>
                    <c:when test="${card.status == 1}">
                        <td class="info">已激活</td>
                    </c:when>
                </c:choose>
                <td>
                    <a class="btn btn-default btn-sm" href="${ctx }/cardManage/cardForm?cardId=${card.id }"><i class="glyphicon glyphicon-edit"></i> 修改</a>
                    <c:choose>
                        <c:when test="${card.status == 0}">
                            <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="activeCard('${card.id}')"><i class="glyphicon glyphicon-edit"></i> 激活</a>
                        </c:when>
                        <c:when test="${card.status == 1}">
                            <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="freezeCard('${card.id}')"><i class="glyphicon glyphicon-edit"></i> 冻结</a>
                        </c:when>
                    </c:choose>
                    <a class="btn btn-default btn-sm" href="${ctx }/cardManage/cardHistoryList/${card.id }"><i class="glyphicon glyphicon-list"></i> 购卡记录</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- 分页 -->
<tags:pagination page="${data}" />
<tags:errors />

<script type="text/javascript">
    $(function() {
        // 样式
        $('#card-man').addClass("active");
        $('#CARD_TYPE').addClass("active");

        // select状态
        // 卡片类型
        var cardType = '${param.search_EQ_cardType }';
        if(cardType){
            $("#search_EQ_cardType option[value="+cardType+"]").attr("selected","selected");
        }
    });

    // 查询
    function cardSubmit() {
        $('#cardForm').submit();
    }

    // 激活
    function activeCard(v) {
        swal({
            title: "",
            text: "您确定激活此卡片",
            type: "warning",
            showCancelButton: "true",
            showConfirmButton: "true",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            animation: "slide-from-top"
        }, function () {
            $('#loading').show();
            $.ajax({
                url : "${ctx }/cardManage/activeCard",
                method : "POST",
                data : {"cardId" : v},
                dataType: 'json',
                success: function(data){
                    $('#loading').hide();
                    if(data.result=='success'){
                        location.href = "${ctx }/cardManage/cardList";
                        /*swal({
                            title: "提示",
                            text: "激活成功",
                            showConfirmButton: "true",
                            confirmButtonText: "确定",
                            animation: "slide-from-top"
                        }, function () {
                            location.href = "${ctx }/cardManage/cardList";
                        })*/
                    } else {
                        swal({
                            title: "警告",
                            text: data.data
                        })
                    }
                }
            });
        })
    }

    // 冻结
    function freezeCard(v) {
        swal({
            title: "",
            text: "您确定冻结此卡片",
            type: "warning",
            showCancelButton: "true",
            showConfirmButton: "true",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            animation: "slide-from-top"
        }, function () {
            $('#loading').show();
            $.ajax({
                url : "${ctx }/cardManage/freezeCard",
                method : "POST",
                data : {"cardId" : v},
                dataType: 'json',
                success: function(data){
                    $('#loading').hide();
                    if(data.result=='success'){
                        location.href = "${ctx }/cardManage/cardList";
                        /*swal({
                            title: "提示",
                            text: "冻结成功",
                            showConfirmButton: "true",
                            confirmButtonText: "确定",
                            animation: "slide-from-top"
                        }, function () {
                            location.href = "${ctx }/cardManage/cardList";
                        })*/
                    } else {
                        swal({
                            title: "警告",
                            text: data.data
                        })
                    }
                }
            });
        })
    }

</script>

</body>
</html>