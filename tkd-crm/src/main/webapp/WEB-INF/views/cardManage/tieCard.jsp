<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡绑卡</title>
</head>
<body>

<!-- 导航 -->
<%@include file="cardNav.jsp"%>

<form  id="turnCardForm" class="form-horizontal" action="${ctx }/cardManage/saveTieCard" method="post">
    <div class="modulHead">
        <!-- <p>会员卡管理 》会员卡转卡</p> -->
    </div>
    <div class="formTable">
       <%--  <input type="hidden" id="cardId" name="cardId" value="${userVo.cardId }">
        <input type="hidden" id="cardType" name="cardType" value="${userVo.cardType }">
        <input type="hidden" id="oldUserId" name="oldUserId" value="${userVo.userId }"> --%>
        <input type="hidden" id="id" name="id" value="${userVo.accountId }">
        <%-- <input type="hidden" id="oldBalance" name="oldBalance" value="${userVo.balance }"> --%>

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
                <span class="title">头像：</span>
                <img alt="" src="${userVo.photo }" height="100" >
            </li>
            <li>
                <span class="title">性别：</span>
                <div class="turn_card">${userVo.sex }</div>
            </li>
            <li>
                <span class="title">住址：</span>
                <div class="turn_card">${userVo.address }</div>
            </li>
            <li>
                <span class="title">邮箱：</span>
                <div class="turn_card">${userVo.email }</div>
            </li>
            <li>
                <span class="title">身份证号：</span>
                <div class="turn_card">${userVo.cardNo }</div>
            </li>
            <li>
                <span class="title">类型：</span>
                <div class="turn_card">
                    <c:if test="${userVo.cardType == 1 }">储值卡</c:if>
                    <c:if test="${userVo.cardType == 2 }">期限卡</c:if>
                </div>
            </li>
            <li id="balanceArea" >
                <span class="title">会员卡卡余额：</span>
                <div class="turn_card">
                    <fmt:formatNumber type="number" value="${userVo.balance/100}" maxFractionDigits="0"/>
                </div>
            </li>
            <li>
                <span class="title">卡号：</span>
                 <%--  --%>
                <input type="text" id="cardNum" name="cardNum" value="${userVo.cardNum}"
                 <c:if test="${not empty userVo.cardNum}">readonly=true</c:if>>
            </li>


        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveCardNum()">绑卡</a>
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
        /* if (${userVo.cardType == 1}) {
            $('#balanceArea').show();
            $('#dateDiv').hide();
        } else {
            $('#timeArea').show();
        } */


    });
    setTimeout(function(){
    	$('#cardNum').focus();
    },100)
    // 保存
    function saveCardNum() {
    	
        var regs = /^[0-9]\d*$/;
        //卡号
		var cardNo = document.getElementById("cardNum").value.trim();
        if(cardNo.length != 6){
        	swal({
                title: "警告",
                text: "卡号错误！"
            });
            return;
        }
	    if(!regs.test(cardNo)){
	    	swal({
                title: "警告",
                text: "卡号请输入阿拉伯数字！"
            });
            return; 
	    }
        // 判断原因是否为空
        if ($('#cardNum').val() == undefined || $('#cardNum').val() == ''){
            swal({
                title: "警告",
                text: "卡号不能为空！"
            });
            return;
        }
	   $.ajax({
		   url:"${ctx}/cardManage/getCardNum/"+cardNo,
		   method:"POST",
		   dataType:"json",
		   success:function(response){
			   if(response.data == null){
				   $('#turnCardForm').submit();
			   }else{
				   swal({
						title: "提示",
						text: "卡号已被使用",
						showConfirmButton: "true",
						confirmButtonText: "确定"
						//animation: "slide-from-top"
					})
					return;
			   }
		   }
	   });
       
	   } 

</script>

</body>
</html>