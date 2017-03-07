<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>


<div id="dic-modal" class="modal fade" tabindex="-1" role="dialog"
  aria-labelledby="newDicModalLabel" aria-hidden="true">

    <form id="dic-form" action="${ctx}/admin/dic/save" method="post" class="form-horizontal">
    <zy:token/>

  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header"> <!-- modal-header -->
      
        <button type="button" class="close" data-dismiss="modal"aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="newDicModalLabel">
        <c:if test="${empty dic.dicId}">新建</c:if>
        <c:if test="${!empty dic.dicId}">修改</c:if>-字典类别</h4>
        
      </div> <!-- /modal-header -->
      
      <div class="modal-body"><!-- modal-body -->
      
          <input id="dicId" type="hidden" name="dicId" value="${dic.dicId }" />
          
          <div class="form-group form-group-sm">
            <label for="dicCode" class="col-sm-3 control-label"><span class="text-red">* </span>字典编码:</label>
            <div class="col-sm-6 has-feedback">
              <input type="text" class="form-control required" id="dicCode" name="dicCode" value="${dic.dicCode }"
                 maxLength="20" <c:if test="${!empty dic.dicId }">readonly</c:if> />
            </div>
          </div>
          <div class="form-group form-group-sm">
            <label for="dicName" class="col-sm-3 control-label"><span class="text-red">* </span>字典名称:</label>
            <div class="col-sm-6 has-feedback">
              <input type="text" class="form-control" id="dicName" name="dicName" value="${dic.dicName }" />
            </div>
          </div>
        
      </div> <!-- /modal-body -->
      
      <div class="modal-footer"> <!-- modal-footer -->
        <button type="button" id="dic-form-cancel" class="btn">
          <span class="glyphicon glyphicon-remove"></span> 取消
        </button>
        <button type="submit" id="dic-form-save" class="btn btn-primary">
          <span class="glyphicon glyphicon-ok"></span> 保存
        </button>
      </div> <!-- /modal-footer -->
      
    </div>
  </div> <!-- /modal-dialog -->
  
  </form>

</div>




<script type="text/javascript">

var checkDicNameUrl = '${ctx}/admin/dic/checkDicName?oldName=${dic.dicName}';
var checkDicCodeUrl = '${ctx}/admin/dic/checkDicCode?oldCode=${dic.dicCode}';

var dicValidator = $('#dic-form').validate({
	submitHandler: function(form) {
	    var $form = $(form);
	    var url = $form.attr('action');
	    $.post(url, $form.serialize(), function(data) {
	      common.hideModal('#dic-modal');
	      if (data == true) {
	    	   common.showMessage("保存成功！");
	      } else {
	    	   common.showMessage("保存失败！", 'warn');
	      }
	      zTree.getZTreeObj("dicTree").reAsyncChildNodes(null, "refresh");
	      var itemTree = zTree.getZTreeObj("dicItemTree");
	      if (itemTree) {
	        itemTree.reAsyncChildNodes(null, "refresh");
	      }
	    });
  },
  rules: {
    dicName: {
      required: true,
      remote: checkDicNameUrl
    },
    dicCode: {
    	required: true,
    	regex: /[a-zA-Z0-9_]+/,
    	remote: checkDicCodeUrl
    }
  },
  messages: {
    dicName: {
      remote: '字典名称已存在'
    },
    dicCode: {
    	regex:'编码只能包含英文字母、数字和下划线',
        remote: '字典编码已经存在'
    }
  }
});


//取消表单
$('#dic-form-cancel').click(function( event ) {
  common.hideModal('#dic-modal');
});

</script>