<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

 
<form id="form1" action="${ctx}/admin/org/save" method="post" class="form-horizontal">
    <zy:token/>
    <input type="hidden" name="id" value="${org.id}"/>
    <input type="hidden" name="pid" value="${org.pid}"/>

	<fieldset>
		<legend>
			<small>组织信息</small>
		</legend>

		<div class="form-group form-group-sm">
			<label for="orgName" class="col-md-3 control-label"><span class="text-red">* </span>组织名称:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="orgName" name="orgName" value="${org.orgName}" maxlength="15"  />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label for="action" class="col-md-3 control-label"><span class="text-red">* </span>组织编码:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="orgCode" name="orgCode" value="${org.orgCode}" placeholder="全局唯一，不允许重复" maxlength="128" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>排序号:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="seq" name="seq" value="${org.seq}" placeholder="输入 [0-99]的整数" maxlength="2" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span></label>
			<div class="col-md-6 has-feedback">
					<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span>
					&nbsp;&nbsp;
					<a class="label label-default" id="account" onclick="accountSet();">支付账号设定</a>
			</div>
	    </div>
		
		<div id="accountGroup" style="display:none">
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span></label>
			<div class="col-md-6 has-feedback">
				支付宝
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>合作者身份ID(partner):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="partner" name="partner" value="${org.partner}" placeholder="签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>卖家支付宝账号(sellerId):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="sellerId" name="sellerId" value="${org.sellerId}" placeholder="卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>商户私钥(privateKey):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="privateKey" name="privateKey" value="${org.privateKey}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>公钥(aliPublicKey):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="aliPublicKey" name="aliPublicKey" value="${org.aliPublicKey}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span></label>
			<div class="col-md-6 has-feedback">
				微信
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信公众账号ID(appid):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="appid" name="appid" value="${org.appid}" placeholder="微信分配的公众账号ID(wx8888888888888888)" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信商户号(mchId):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="mchId" name="mchId" value="${org.mchId}" placeholder="微信支付分配的商户号(1900000109)" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信密钥(partnerKey):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="partnerKey" name="partnerKey" value="${org.partnerKey}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信应用id密钥(appSecret):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="appSecret" name="appSecret" value="${org.appSecret}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信公用key(appKey):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="appKey" name="appKey" value="${org.appKey }" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>HTTPS证书的本地路径:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="certlocalPath " name="certlocalPath" value="${org.certlocalPath}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>HTTPS证书密码(certPassword):</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="certPassword" name="certPassword" value="${org.certPassword}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span></label>
			<div class="col-md-6 has-feedback">
				微信公众平台
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信公众平台账号ID:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="wxAppid" name="wxAppid" value="${org.wxAppid}" placeholder="微信分配的公众账号ID(wx8888888888888888)" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信公众平台商户号:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="wxMchId" name="wxMchId" value="${org.wxMchId}" placeholder="微信支付分配的商户号(1900000109)" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信公众平台公用key:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="wxAppKey" name="wxAppKey" value="${org.wxAppKey}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>HTTPS证书的本地路径:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="wxCertlocalPath " name="wxCertlocalPath" value="${org.wxCertlocalPath}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>HTTPS证书密码:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="wxCertPassword" name="wxCertPassword" value="${org.wxCertPassword}" placeholder="" />
			</div>
		</div>
		
		<div class="form-group form-group-sm">
			<label for="seq" class="col-md-3 control-label"><span class="text-red"> </span>微信公众平台应用id密钥:</label>
			<div class="col-md-6 has-feedback">
				<input type="text" class="form-control" id="wxAppSecret" name="wxAppSecret" value="${org.wxAppSecret}" placeholder="" />
			</div>
		</div>
		
		</div>

		<div class="form-group">
			<div class="col-md-offset-3 col-md-2">
				<button type="reset" class="btn btn-default btn-block" >
				    <span class="glyphicon glyphicon-repeat"></span> 重置
				 </button>
			</div>
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary btn-block" id="submit_btn">
					<span class="glyphicon glyphicon-ok"></span> 保存
				</button>
			</div>
			
			<c:if test="${!empty org.id }">
			<div class="col-md-2">
				<button type="button" class="btn btn-warning btn-block" id="del_btn">
					<span class="glyphicon glyphicon-floppy-remove"></span> 删除
				</button>
			</div>
			</c:if>
		</div>

	</fieldset>

</form>



<script type="text/javascript" >
var oldName = encodeURIComponent('${org.orgName}');
$('#form1').validate({
	rules: {
		orgName: {
			required: true
			, rangelength: [3, 20]
			, remote : '${ctx}/admin/org/checkName?oldName=' + oldName + '&pid=${org.pid}'
		},
		orgCode: {
			required: true
			, rangelength: [2, 100]
		},
		seq: {
			digits: true
		}
	},
	messages: {
		orgName: {
			remote: '名称已经存在，请重新输入！'
		}
	}
});

$('#del_btn').on('click', function() {
    func.delFunction('${org.id}', '${org.orgName}');
});

function accountSet(){
	$('#account').toggleClass('label-primary');
	$('#accountGroup').toggle();
}
</script>