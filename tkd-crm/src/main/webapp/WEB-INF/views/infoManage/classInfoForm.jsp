<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>课时列表</title>
</head>
<body>

<!-- 导航 -->
<%@include file="infoNav.jsp"%>

<form  id="classInfoForm" class="form-horizontal" action="${ctx }/infoManage/savaClassInfo" method="post" name="id">
    <div class="modulHead">
        <p>课程管理 》课时管理 》 添加课时</p>
    </div>
    <div class="formTable">
        <input type="hidden" id="coachId" name="coachId" value="${statiumClassInfo.coachId }" />
        <input type="hidden" id="id" name="id" value="${statiumClassInfo.id }" />
        <input type="hidden" name="classId" value="${classId }" />
        <ul>
            <li>
                <span class="title">上课日期：</span>
                <input type="text" class="form-control Wdate " id="cDate" name="cDate" value="${statiumClassInfo.cDate }"
                       onfocus="WdatePicker({readOnly:true,doubleCalendar:true})">
            </li>
            <li>
                <span class="title">上课教练：</span>
                <span id="captainName"></span> <input readonly type="text" class="form-control" id="coachName" name="coachName"
                                                      value="${statiumClassInfo.coachName }" placeholder="请选择上课教练" style="width: 200px" />
                <a id="sel_captain" class="btn btn-default btn-primary">选择</a>
            </li>
            <li>
                <span class="title">上课时间：</span>
                <input type="text" id="classStartTime" name="classStartTime" value="${statiumClassInfo.classStartTime }"
                       onClick="WdatePicker({readOnly:true,dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\'classEndTime\')}'})">
            </li>
            <li>
                <span class="title">下课时间：</span>
                <input type="text" id="classEndTime" name="classEndTime" value="${statiumClassInfo.classEndTime }"
                       onClick="WdatePicker({readOnly:true,dateFmt:'HH:mm',minDate:'#F{$dp.$D(\'classStartTime\')}'})">
            </li>
        </ul>
        <div class="formSubDiv">
            <a href="javascript:history.go(-1)" class="saveBtnBot">返回</a>
            <a class="saveBtnBot" href="javascript:saveClassInfoForm()">保存</a>
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
    function saveClassInfoForm() {
        $('#classInfoForm').submit();
    }

    // 选取教练
    $('#sel_captain').on('click', function() {
        $("#myDlgBody_lg").load("${ctx}/infoManage/coach_query_dlg", {
        });
        $("#myDlg_lg").modal();
    })
    function captainAddCallBack(userId, nickName) {
        $("#coachId").val(userId);
        $("#coachName").val(nickName);
    }

</script>

</body>
</html>