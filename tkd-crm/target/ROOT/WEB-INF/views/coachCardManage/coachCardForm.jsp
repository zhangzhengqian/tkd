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

<form  id="cardForm" action="${ctx }/userClassManage/saveCoachCard" method="post" class="form-horizontal">
    <div class="modulHead">
        <p>私教卡管理 》添加私教卡</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="id" name="id" value="${coachCard.id }">
        <input type="hidden" id="classId" name="classId" value="${coachCard.classId }">
         <ul>
        
        	<li>
                <span class="title">课程：</span>
                <span id="captainName"></span><input readonly type="text" class="form-control" name="classTitle" id="classTitle"
                                                      value="${coachCard.classTitle }" placeholder="请选择课程" style="width: 200px" />
                <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
            </li>
            
           
            <li>
                <span class="title">课程单价：</span>
                <input readonly type="text" id="unitPrice" name="" value="${unitPrice }" ">
            </li>
           
            <li>
                <span class="title">私教卡名称：</span>
                <input type="text" id="cardName" name="cardName" value="${coachCard.cardName }">
            </li>
            
            
            <li>
                <span class="title">私教卡价格：</span>
                <input type="text" id="discountPrice" name="discountPrice" value="${coachCard.discountPrice}" onkeypress="keyPress()"><label style="margin-top: 10px; margin-left:10px; color: red">* 请输入整数</label>
            </li>
            
            <li>
                <span class="title">私教卡次数：</span>
                <input type="text" id="frequency" name="frequency" value="${coachCard.frequency }" onkeypress="keyPress()">
            </li>
            
             <li>
                <span class="title">私教卡赠送次数：</span>
                <input type="text" id="giftFrequency" name="giftFrequency" value="${coachCard.giftFrequency }" onkeypress="keyPress()">
            </li>
            
            <li>
                <span class="title">有效期：</span>
                <input type="text" id="period" name="period" value="${coachCard.period }" onkeypress="keyPress()" style="width:50px">
                	<label style="margin-top: 10px; margin-left:10px; color: red">* 个月</label>
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
        $('#coacchCard-man').addClass("active");
        $('#CARD_TYPE').addClass("active");
    });
    
    
    
    //选择课程
    $('#sel_captain').on('click', function() {
        $("#myDlgBody_lg").load("${ctx}/userClassManage/statium_query_dlg", {
        });
        $("#myDlg_lg").modal();
    })
    
    function captainAddCallBack(classId, cardTitle, discountPrice){
    	$("#classTitle").val(cardTitle);
    	$("#classId").val(classId);
    	$("#cardTitle").val(cardTitle);
    	$("#unitPrice").val(discountPrice/100);
    }
    
    // 保存
    function saveCardForm() {
    	
    	 //次数校验
 		 var reg = /^[0-9]\d*$/; 
	     var obj = document.getElementById("frequency").value.trim();
	     var dis = document.getElementById("giftFrequency").value.trim();
	     var maxPeople = $('input[name="maxPeople"]').val();
	    if(!reg.test(obj)){
	    	swal({
                title: "警告",
                text: "次数请输入阿拉伯数字！"
            });
            return; 
	    }
	    
	    if(!reg.test(dis)){
	    	swal({
                title: "警告",
                text: "赠送次数请输入阿拉伯数字！"
            });
            return; 
	    }
    	
        // 判断课程名称是否为空
        if ($('#classTitle').val() == undefined || $('#classTitle').val() == ''){
            swal({
                title: "警告",
                text: "请选择课程！"
            });
            return;
        }
        // 判断私教卡名称是否为空
        if ($('#cardName').val() == undefined || $('#cardName').val() == ''){
            swal({
                title: "警告",
                text: "请填写私教卡名称！"
            });
            return;
        }
        
        // 判断赠送金额是否为空
        if($('#frequency').val() == undefined || $('#frequency').val() == ''){
           swal({
                title: "警告",
                text: "请填写次数"
            });
            return;
        }
        
        if($('#period').val() == undefined || $('#period').val() == ''){
        	 swal({
                 title: "警告",
                 text: "请填写有效期"
             });
             return;
        }
        
        if($('#giftFrequency').val() == undefined || $('#giftFrequency').val() == ''){
        	$("#giftFrequency").val(0)
        }
        // 提交
        $('#cardForm').submit();
    }

</script>

</body>
</html>