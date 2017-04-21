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

<form  id="cardForm" class="form-horizontal" action="${ctx }/coachCard/list" method="post" name="id">
    <div class="orderSearch myVipOrderSearch">
        <ul>
            <li class="timeLi subSearchLi1">
                <span style="width:120px">单项(次)卡名称:</span>
                <input name="search_LIKE_cardName" id="search_LIKE_cardName" value="${param.search_LIKE_cardName }"/>
            </li>
            <li class="subSearch subSearchLi1">
                <a class="submit" href="javascript:cardSubmit()" >查询</a>
            </li>

            <li class="subSearch subSearchLi">
            </li>
            <li class="subSearch subSearchLi">
                <a class="submit" style="width:150px" href="${ctx }/userClassManage/initCardForm">添加单项(次)卡 </a>
            </li>

        </ul>
    </div>
</form>

<div class="orderResult">
    <table>
        <tr>
            <th>No.</th>
            <th>卡片名称</th>
            <th>课程名称</th>
            <th>面值(应交金额)</th>
			<th>次数</th>
			<th>赠送次数</th>
			<th>有效期</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${data.content }" var="coachCard" varStatus="stat">
            <tr>
                <td>${stat.count }</td>
                <td>${coachCard.cardName }</td>
                <td>${coachCard.classTitle }</td>
                <td><fmt:formatNumber type="number" value="${coachCard.discountPrice/100 }" maxFractionDigits="0"/></td>
                <td>${coachCard.frequency }</td>
                <td>${coachCard.giftFrequency }</td>
                <td>${coachCard.period }</td>
                <c:choose>
                    <c:when test="${coachCard.status == 0}">
                        <td class="warning">未激活</td>
                    </c:when>
                    <c:when test="${coachCard.status == 1}">
                        <td class="info">已激活</td>
                    </c:when>
                </c:choose>
                <td>
                    <a class="btn btn-default btn-sm" href="${ctx }/userClassManage/cardForm?cardId=${coachCard.id }"><i class="glyphicon glyphicon-edit"></i> 修改</a>
                    <c:choose>
                        <c:when test="${coachCard.status == 0}">
                            <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="activeCard('${coachCard.id}')"><i class="glyphicon glyphicon-edit"></i> 激活</a>
                        </c:when>
                        <c:when test="${coachCard.status == 1}">
                            <a class="btn btn-default btn-sm" href="javascript:void(0);" onclick="freezeCard('${coachCard.id}')"><i class="glyphicon glyphicon-edit"></i> 冻结</a>
                        </c:when>
                    </c:choose>
                    <a class="btn btn-default btn-sm" href="${ctx }/userClassManage/cardHistoryList/${coachCard.id }"><i class="glyphicon glyphicon-list"></i> 购卡记录</a>
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
        $('#coacchCard-man').addClass("active");
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
                url : "${ctx }/userClassManage/activeCard",
                method : "POST",
                data : {"cardId" : v},
                dataType: 'json',
                success: function(data){
                    $('#loading').hide();
                    if(data.result=='success'){
                        location.href = "${ctx }/userClassManage/list";
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
                url : "${ctx }/userClassManage/freezeCard",
                method : "POST",
                data : {"cardId" : v},
                dataType: 'json',
                success: function(data){
                    $('#loading').hide();
                    if(data.result=='success'){
                        location.href = "${ctx }/userClassManage/list";
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