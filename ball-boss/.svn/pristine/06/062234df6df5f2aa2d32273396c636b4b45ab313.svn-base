<%@page import="com.lc.zy.ball.boss.common.SessionUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<% 
java.util.Date now = new java.util.Date();
String nickname = SessionUtil.currentUser().getNickname();
String audit_userId = SessionUtil.currentUserId();

request.setAttribute("now", now);
request.setAttribute("nickname", nickname);
request.setAttribute("audit_userId", audit_userId);
%>
<div class="panel panel-default">


  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <c:choose>
        	<c:when test="${param._from=='admin' }">
        		<li> <span class="glyphicon glyphicon-home"></span> 我管理的球馆</li>
        	</c:when>
        	<c:otherwise>
        		<li> <span class="glyphicon glyphicon-home"></span> 场馆管理</li>
        	</c:otherwise>
        </c:choose>
        <c:choose>
        	<c:when test="${param.action=='view' }">
		        <li class="active">查看</li>
        	</c:when>
        	<c:otherwise>
		        <li class="active">场馆审核</li>
        	</c:otherwise>
        </c:choose>
    </ul>
  </div><!-- / 右侧标题 -->
	
  <div class="panel-body"><!-- 右侧主体内容 -->
  
  		<div class="row">
		    <div class="col-xs-10 col-xs-offset-1">
				<form class="form-horizontal" method="post" action="${ctx }/admin/statium/audit">
				  <input type="hidden" name="userId" value="${statium.cb }" />
				  <%-- 
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
       					 <c:choose>
       					 	<c:when test="${param._from=='admin' }">
									<a href="${ctx }/admin/statium/manager" class="btn btn-default" > 返回 </a>
       					 	</c:when>
       					 	<c:otherwise>
								    <a href="${ctx }/admin/statium" class="btn btn-default" > 返回 </a>
       					 	</c:otherwise>
       					 </c:choose> 
				      	&nbsp;
				      	&nbsp;
						<c:if test="${param.action!='view' }">
				      		<input type="submit" class="btn btn-warning" name="audit" value="打回重填" />
				      		&nbsp;
				      		&nbsp;
				      		<input type="submit" class="btn btn-success" name="audit" value="审核通过" />
				      	</c:if>
				    </div>
				  </div>
				  --%>
				</form>
			</div>
		</div>	
  
		<div class="row">
		    <div class="col-xs-10 col-xs-offset-1">
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
					<tr>
						<td align="right">营业执照有效期：</td>		
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
					<tr><td align="right">开户银行许可证：</td>		<td><img height="150px;" src="${statium.bankLicenseImg }" /></td></tr>
					
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
					<tr>
						<td align="right">场馆图片：</td>			
						<td>	         	
							<c:if test="${not empty statium.photos }">
	         					<c:forEach items="${fn:split(statium.photos,'__') }" var="itm">
	         					<img alt="" src="${itm }" height="100">
	         					</c:forEach> 
	         				</c:if>
	         			</td>
					</tr>
					<tr><td align="right">身份证正面：</td>		<td><img height="150px;" src="${statium.masterIdCardImg1 }" /></td></tr>
					<tr><td align="right">身份证背面：</td>		<td><img height="150px;" src="${statium.masterIdCardImg2 }" /></td></tr>
				</table>
				
		    </div>
	  	</div>  

	
		<div class="row">
		    <div class="col-xs-10 col-xs-offset-1">
				<form class="form-horizontal" method="post" action="${ctx }/admin/statium/audit">
				  <input type="hidden" name="userId" value="${statium.cb }" />
				  <input type="hidden" name="audit_userId" value="${audit_userId }" />
				  <input type="hidden" name="statium_Id" value="${statium.id }" />
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <a href="${ctx }/admin/statium" class="btn btn-default" > 返回 </a>
				      &nbsp;
				      &nbsp;
				      <c:if test="${param.action!='view' }">
				      <input type="submit" class="btn btn-warning" name="audit" value="打回重填" />
				      &nbsp;
				      &nbsp;
				      <input type="submit" class="btn btn-success" name="audit" value="审核通过" />
				      </c:if>
				    </div>
				  </div>
				</form>
			</div>
		</div>		
  </div>
	
</div>

<script type="text/javascript">
$(function() {
	$("#adminFooter").hide();
<c:choose>
	<c:when test="${param._from=='admin' }">
		menu.active('#statium-manager-man');
	</c:when>
	<c:otherwise>
		menu.active('#statium-man');
	</c:otherwise>
</c:choose>	
	
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

</script>
<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>

