//var ctx = $('#ctx').val();

// 域名解析
$('#batch_resolving').click(function(){
	var s = '';
	var temps='';
	var chk = $('input:checkbox[name=chk_list]:checked').each(function() {
		s += this.value.split(":")[1] + ":" + this.value.split(":")[3] +';';
		temps += this.value.split(":")[3] + ":" + this.value.split(":")[4] +';';
	});
	if(s == ''){
		bootbox.alert('请勾选需要解析的域名!');
	}else{
		var subFlag=false;
		var t=temps.split(";");
		for(var j=0;j<t.length;j++){
			var domainName=t[j].split(":")[0];
			var flag=t[j].split(":")[1];
			if('0'==flag){
				bootbox.alert(domainName+'暂不支持域名解析,请您尝试修改DNS服务器!');
			    break;
			}else if('1'==flag){
				subFlag=true;
			}
		}
		if(subFlag){
			$('#str').val(s);
			var $form = $('#batch_operationForm');
			$form.attr('action',ctx + '/agent/domain/resolvingList/addDomainResolvingBatchList');
			$form.submit();
		}
	}
});

// 批量域名续费
$('#batch_renew').click(function(){
	var s = '';
	var chk = $('input:checkbox[name=chk_list]:checked').each(function() {
		s += this.value.split(":")[0] + ":" + this.value.split(":")[1] + ":" + this.value.split(":")[2] + ":" + this.value.split(":")[3] +';';
	});
	if(s == ''){
		bootbox.alert('请勾选需要续费的域名!');
	}else{
		$('#products').val(s);
		var $form = $('#batch_operationForm');
		$form.attr('action',ctx + '/agent/order/generateRenewOrders');
		$form.submit();
	}
});

// 单个域名续费
$('a.renewSingle').click(function(){
	var hi = $(this).nextAll('input').val();
	
	$('#products').val(hi);
	var $form = $('#single_operationForm');
	$form.attr('action',ctx + '/agent/order/generateRenewOrders');
	$form.submit();
});

//批量删除域名
$('#btn-remove').click(function() {
	var domainId = [];
	var domainName = [];
	var domainIdAndName = [];
	
	$('input:checkbox[name=chk_list]:checked').each(function() {
		var values = this.value.split(':');
		domainId.push(values[0]);
		domainName.push(values[3]);
		domainIdAndName.push(values[0]+":"+values[3]);
	});
	
	if (domainId.length < 1) {
		bootbox.alert('请勾选需要删除的域名!');
		return false;
	}
	
	var msg = domainName.join(' | ');
	msg = msg + "<p class='text-danger'>删除域名为不可逆操作，请确认！</p>";
	
	bootbox.custom('以下域名将被删除', msg, function() {
		// 执行删除逻辑
		var $form = $('#batch_operationForm');
		$form.attr('action',ctx + '/agent/domain/batchDelete');
		var domainIdAndNames = domainIdAndName.join(",");
		$form.append("<input type=\"hidden\" name=\"domainIdAndNames\" value=\""+domainIdAndNames+"\">");
		$form.submit();
	}, true);
});


//删除域名
$('a.remove-domain').click(function() {
	var domain = $(this).nextAll('input').val();
	var msg =  domain.split(':')[3] + "<p class='text-danger'>删除域名为不可逆操作，请确认！</p>";
	bootbox.custom('操作确认', msg, function() {
		// 执行删除逻辑
		var values = domain.split(':');
		var domainIdAndName = values[0]+":"+values[3];
		var $form = $('#single_operationForm');
		$form.attr('action',ctx + '/agent/domain/delete');
		$form.append("<input type=\"hidden\" name=\"domainIdAndName\" value=\""+domainIdAndName+"\">");
		$form.submit();
	}, true);
});


var domainList = {};

domainList.getCheckedDomains = function() {//获取勾选的数据行
	var items = [];
	var item;
	$('input:checkbox[name=chk_list]:checked').each(function() {
		//this.value的格式：
		//domainId:prodId:custId:domain:ifCanResovingFlag:changeAgentFlag
		item = this.value.split(':');
		items.push({id:item[0], name:item[3], change:item[5]});
	});
	
	return items;
}


//转移代理商功能 -------------------------------------------------->>>
$('#btn-change-agent').click(function() {
	var items = domainList.getCheckedDomains();
	if (items.length > 0) {
		//清空上一次的表单状态
		$('#change-agent-modal form').find('input').each(function() {
			this.value = '';
		});
		$('#change-agent-modal .has-feedback').removeClass('has-error');
		
		var ids = [];
		var names = [];
		for(var i=0; i < items.length; i++) {
			if (items[i].change != 1) {
				common.showMessage('域名<strong>' + items[i].name + 
						'</strong>不能转移, <strong>注册超过七天</strong>并且状态<strong>正常</strong>的域名才能转移代理商!', 'warn')
				return;
			}
			ids.push(items[i].id);
			names.push(items[i].name);
		}
		$('#domains').empty().append(names.join(' | '));
		
		$('#domainIds').val(ids.join(';'));
		
		common.showModal('#change-agent-modal');
		
	} else {
		common.showMessage('请选择需要转移的域名!', 'warn');
	}
});

//由选择代理商模态框回调
function setAgent(obj) {
	//common.log('id=' + obj.id);
	//common.log('name=' + obj.name);
	common.showModal('#change-agent-modal');
	$('#agentId').val(obj.id);
	$('#agentName').val(obj.name);
}

//选择代理商按钮
$('#btn-select-agent').click(function() {
	common.hideModal('#change-agent-modal');
	var agentId = $('#agentId').val();
	var id='';
	if ($.trim(agentId).length > 0) id = '&id=' + agentId;
	
	$.get(ctx + '/common/customer/modal/agent?plain&f=setAgent&size=5' + id, function(data) {
		$('#cust-modal').remove();//清理上次打开的对话框
		$('body').append(data);
		common.showModal('#cust-modal');
	});
});

//校验转移表单
function validateChangeAgentForm() {
	var agentId = $('#agentId').val();
	var domainIds = $('#domainIds').val();
	//common.log('agentId=' + agentId);
	//common.log('domainIds=' + domainIds);
	if ($.trim(agentId) == '' || $.trim(domainIds) == '') {
		return false;
	}
		
	return true;
}

//转移表单提交时
$('#change-agent-form').submit(function() {
	return validateChangeAgentForm();
});

//取消转移
$('#change-agent-modal-cancel').click(function() {
	common.hideModal('#change-agent-modal')
});

//确认转移
$('#change-agent-modal-ok').click(function() {
	if (! validateChangeAgentForm()) {
		$('#agentName').parents('.has-feedback').addClass('has-error');
	} else {
		//提交表单
		$('#change-agent-form')[0].submit();
	}
	
});

//转移代理商功能 --------------------------------------------------<<<

