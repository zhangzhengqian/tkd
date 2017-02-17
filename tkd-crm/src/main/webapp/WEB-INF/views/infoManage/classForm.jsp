<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>会员卡列表</title>
</head>
<body>

<!-- 导航 -->
<%@include file="infoNav.jsp"%>

<form  id="classForm" class="form-horizontal" action="${ctx }/infoManage/savaClass" method="post" name="id">
    <div class="modulHead">
        <p>课程管理 》添加课程</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="id" name="id" value="${statiumClass.id }">
        <ul>
            <li>
                <span class="title">课程名称：</span>
                <input type="text" id="classTitle" name="classTitle" value="${statiumClass.classTitle }">
            </li>
            <li>
                <span class="title">课程介绍：</span>
                <textarea style="width: 400px" class="form-control" rows="4" id="classIntroduce" name="classIntroduce">${statiumClass.classIntroduce }
                </textarea>
            </li>
            <li>
                <span class="title">原价：</span>
                <input type="text" id="price" name="price" value="${statiumClass.price }">
            </li>
            <li>
                <span class="title">签约价：</span>
                <input type="text" id="discountPrice" name="discountPrice" value="${statiumClass.discountPrice }">
            </li>
            <li>
                <span class="title">执教类型：</span>
                <div class="btn-group" id="teachDiv" data-toggle="buttons">
                    <label class="btn btn-default<c:if test="${statiumClass.type == 0 || statiumClass.type == null}"> active</c:if>">
                        <input type="radio" name="type" id="type" value="0"  autocomplete="off"
                               <c:if test='${statiumClass.type == 0 || statiumClass.type == null}'>checked="checked"</c:if> >
                        大课
                    </label>
                    <label class="btn btn-default<c:if test="${statiumClass.type == 1}"> active</c:if>">
                        <input type="radio" name="type" id="type" value="1" autocomplete="off"
                               <c:if test='${statiumClass.type == 1}'>checked="checked"</c:if> >
                        私教
                    </label>
                </div>
            </li>
            <li>
                <span class="title">最大人数：</span>
                <input type="text" id="maxPeople" name="maxPeople" value="${statiumClass.maxPeople}" >
            </li>
        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveClassForm()">保存</a>
        </div>

    </div>
</form>

<script type="text/javascript">
    $(function() {
        // 样式
        $('#info-man').addClass("active");
        $('#STATIUM_CLASS').addClass("active");
    });

    // 表单提交
    function saveClassForm() {
        $('#classForm').submit();
    }

</script>

</body>
</html>