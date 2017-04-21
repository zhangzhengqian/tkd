<%@page import="com.lc.zy.ball.boss.common.SessionUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<% 
java.util.Date now = new java.util.Date();
String nickname = SessionUtil.currentUser().getNickname();

request.setAttribute("now", now);
request.setAttribute("nickname", nickname);
request.setAttribute("status",com.lc.zy.ball.boss.common.SessionUtil.currentUser().getStatus());	
%>
<div class="panel panel-default">

    <div class="panel-heading"><!-- 右侧标题 -->

		<div class="row">
		    <div class="col-xs-10 col-xs-offset-1">
		        <div class="progress progress-chart">
		            <span class="progress-chart-1st"></span>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            
		            <c:if test="${'AUDIT' eq status }" >
		            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            </c:if>
		            <c:if test="${'NEW' eq status or 'UNPASS' eq status }" >
		            <div class="progress-chart-nonestyle" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:20%;">
		                <span><!-- 节点 --></span>
		            </div>
		            </c:if>
		            
		        </div><!--// progress:end -->
		        <div class="progress-chart-text">
		            <span class="finish">注册账号</span>
		            <span class="finish">公司资质</span>
		            <span class="finish">营业资质</span>
		            <span class="finish">球馆信息</span>
		            <span class="finish">提交审核</span>
		             
		            <c:if test="${'AUDIT' eq status }" >
		            	<span class="progress-chart-text-last finish">完成</span>   
		           	</c:if> 
		            <c:if test="${'NEW' eq status or 'UNPASS' eq status }" >
		           		<span class="progress-chart-text-last">完成</span>   
		           	</c:if>
		        </div>
		    </div>
	  	</div>
	
  </div><!-- / 右侧标题 -->
   	<c:if test="${'AUDIT' eq status }" >
  	<div class="panel-heading"><!-- 右侧标题 -->
  	
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1" align="center">
				<h1>资料已完成提交，请耐心等待审核人员处理</h1>
			</div>
		</div>
	</div>	
 	</c:if>
  <div class="panel-body"><!-- 右侧主体内容 -->
  
		<div class="row">
		    <div class="col-xs-8 col-xs-offset-2">
		      	<table class="table table-bordered">
					<tr style="background-color:#f5f5f5 ">
						<td width="30%">公司法人信息</td>
						<td></td>
					</tr>
					<tr><td align="right">公司名称：</td>		<td>${company.name }</td></tr>
					<tr><td align="right">公司所在地：</td>	<td>${company.areaCode }</td></tr>
					<tr><td align="right">公司电话：</td>		<td>${company.tel }</td></tr>
					<tr><td align="right">公司邮箱：</td>		<td>${company.email }</td></tr>
					<tr><td align="right">法人姓名：</td>		<td>${company.liablePersion }</td></tr>
					<tr><td align="right">法人电话：</td>		<td>${company.liableTel }</td></tr>
					
					
					<tr style="background-color:#f5f5f5 ">
						<td>营业执照信息</td>
						<td></td>
					</tr>
					<tr><td align="right">组织机构代码：</td>		<td>${business.orgCode }</td></tr>
					<tr><td align="right">营业执照号：</td>		<td>${business.bizLicenseCode }</td></tr>
					<tr><td align="right">营业执照所在地：</td>		<td>${business.bizLicenseAddress }</td></tr>
					<tr><td align="right">营业执照有效期：</td>		
						<td>
							<fmt:formatDate value="${business.bizLicenseBeginTime }" pattern="yyyy-MM-dd" />
							~
							<fmt:formatDate value="${business.bizLicenseEndTime }" pattern="yyyy-MM-dd" />
						</td>	
					</tr>
					
					<tr><td align="right">法定经营范围：</td>		<td>${business.bizScope }</td></tr>
					<tr><td align="right">组织机构代码证：</td>		<td><img height="150px;" src="${business.orgImg }" /></td></tr>
					<tr><td align="right">营业执照副本：</td>		<td><img height="150px;" src="${business.bizLicenseImg }" /></td></tr>
					<tr><td align="right">一般纳税人证明：</td>		<td><img height="150px;" src="${business.taxpayerProveImg }" /></td></tr>
					
					
					<tr style="background-color:#f5f5f5 ">
						<td>开户银行信息</td>
						<td></td>
					</tr>
					<tr><td align="right">银行开户名：</td>		<td>${statium.bankAccountName }</td></tr>
					<tr><td align="right">公司银行账号：</td>		<td>${statium.bankAccountNo }</td></tr>
					<tr><td align="right">开户行支行名称：</td>		<td>${statium.bankAccountBranchName }</td></tr>
					<tr><td align="right">支行联行号：</td>		<td>${statium.bankAccountBranchNo }</td></tr>
					<tr><td align="right">开户银行许可证：</td>		<td><c:if test="${not empty statium.bankLicenseImg}"><img height="150px;" src="${statium.bankLicenseImg }" /></c:if></td></tr>
					
					<tr style="background-color:#f5f5f5 ">
						<td>球馆经营信息</td>
						<td></td>
					</tr>
					<tr><td align="right">球馆名称：</td>			<td>${statium.name }</td></tr>
					<tr><td align="right">场馆地址：</td>			<td>${statium.address }</td></tr>
					<tr><td align="right">场馆电话：</td>			<td>${statium.tel }</td></tr>
					<tr>
						<td align="right">运动类型：</td>			
						<td>
							<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
								<c:forEach items="${fn:split(statium.sportType,';;') }" var="obj" >
									<c:if test="${obj eq item.itemCode}">
										${item.itemName} &nbsp;&nbsp; 
									</c:if>	
								</c:forEach>
		       				</c:forEach>	
						</td>
					</tr>
					<tr><td align="right">管理员姓名：</td>		<td>${statium.masterName }</td></tr>
					<tr><td align="right">管理员手机号：</td>		<td>${statium.masterTel }</td></tr>
					<tr><td align="right">管理员身份证：</td>		<td>${statium.masterIdCard }</td></tr>
					<tr><td align="right">身份证正面：</td>		<td><c:if test="${not empty statium.masterIdCardImg1}"><img height="150px;" src="${statium.masterIdCardImg1 }" /></c:if></td></tr>
					<tr><td align="right">身份证背面：</td>		<td><c:if test="${not empty statium.masterIdCardImg2}"><img height="150px;" src="${statium.masterIdCardImg2 }" /></c:if></td></tr>
					
				</table>
				
				<c:if test="${'NEW' eq status or 'UNPASS' eq status }" >
				<table class="table table-bordered">
					<tr style="background-color:#ddd ">
						<td align="left">
							<a href="#" >查看协议</a>&nbsp;&nbsp;
							<input type="checkbox" checked="checked"  id="agree"/>同意注册协议
						</td>
						<td align="center">
							签署日期: 
							<fmt:formatDate value="${now }" pattern="yyyy-MM-dd"/>
						</td>
						<td align="right">
							签署人: ${nickname }
						</td>
					</tr>
				</table>
				</c:if>	
		    </div>
	  	</div>  
		<c:if test="${'NEW' eq status or 'UNPASS' eq status }" >
		<form id="inputForm" onsubmit="return check()"  action="${ctx}/register/pushForm" method="post" class="form-horizontal">
			<input type="hidden" name="_pass" value="true" />
			<fieldset>
				<div class="form-group">
					<div class="col-md-offset-3 col-md-2">
					</div>
					<div class="col-md-2">
					  <button type="submit" class="btn btn-primary btn-block" id="submit_btn"> 提交审核 </button>
					</div>
				</div>
			</fieldset>
		</form>
		</c:if>	
  </div>
	
</div>

<script type="text/javascript">
$(function() {
	$("#nav_register").attr("class","active");
	$('#inputForm').validate({
		rules: {
			nickname: {
				required: true
				, rangelength : [2, 16]
			}
		},
		messages: {
		}
	});
});

 function check(){
	 if($("input[type='checkbox']").is(':checked')){
	        return true;
	    }else{
	    	alert("请选中同意注册协议");
	        return false;
	    }
 }
</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

