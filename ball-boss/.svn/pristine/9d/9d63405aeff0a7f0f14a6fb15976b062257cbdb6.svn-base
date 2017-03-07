<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<%@page import="com.lc.zy.ball.boss.common.SessionUtil" %>
<%String id = SessionUtil.currentUserId(); %>  
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span> 企业用户</li>
	        	<li>企业用户列表</li>
				<li>企业员工列表</li>
		       	<li class="active">员工详情</li>
	    </ul>
  	</div><!-- / 右侧标题 --> 
  
 	<c:set var="readonly" value="readonly='readonly' "/>
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  	<fieldset> <legend><small>用户信息详情</small></legend>
	<form id="inputForm" action="#" method="post" class="form-horizontal" enctype="multipart/form-data">
        <zy:token/>
        <input type="hidden" id="id" name="id" value="${ssoUser.id }"/>
		<fieldset>
			<c:if test="${readonly!=' '}">
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red"></span>个人头像:</label>
				    <div class="col-md-1 has-feedback">
				    	<img src="${ssoUser.photo }"style="width:128px;height:128px;display:block;margin-left: auto;margin-right: auto;"/>
				    </div>
				</div>
			</c:if>
			
			<c:if test="${readonly==' '}">
				<div class="form-group form-group-sm">
					<label for="name" class="col-md-3 control-label"><span class="text-red"></span>个人头像:</label>
					<div id="title" class="col-md-1 has-feedback">
						<img id="icon_img" src="${ssoUser.photo }" style="width:128px;height:128px;display:block;margin-left: auto;margin-right: auto;"/>
					</div>
				</div>
				
				<div class="form-group form-group-sm">
					<label for="sort" class="col-md-3 control-label"><span class="text-red"></span></label>
				    <div class="col-md-6 has-feedback">
				    	<input type="file" class="form-control" id="icon_upload" name="icon_upload" multiple="false" />
				    	<input type="hidden" id="photo" name="photo" value=""/>
				    </div>
				</div>
			
			</c:if>
		
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>球友号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="qiuyouNo" name="qiuyouNo" style="width:480px" value="${ssoUser.qiuyouno }" readonly />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>用户昵称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="nickName" name="nickName" style="width:480px" value="${ssoUser.nickName }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>用户姓名:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="name" name="name" style="width:480px" value="${ssoUser.name }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>用户性别:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="sex" name="sex" style="width:480px" value="${ssoUser.sex }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>会员等级:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="level" name="level" style="width:480px" value="${ssoUser.level }" readonly />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label class="col-md-3 control-label"><span class="text-red"></span>出生日期:</label>
                <div class="col-sm-4">
                     <div class="input-group col-md-6 has-feedback">
                         <input value="${ssoUser.birthday }" type="text" name="bDate" id="bDate" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                         <label for="bDate" class="input-group-addon"><i class="fa fa-calendar"></i></label>
                     </div>
                </div>
			</div>		
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>所在地区:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="city" name="city" style="width:480px" value="${ssoUser.city }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>家庭住址:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="address" name="address" style="width:480px" value="${ssoUser.address }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>身份证号:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="cardId" name="cardId" style="width:480px" value="${ssoUser.cardId }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>注册手机:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="phone" name="phone" style="width:480px" value="${ssoUser.phone }" readonly />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>电子邮箱:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="email" name="email" style="width:480px" value="${ssoUser.email }"/>
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>爱好:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" id="tags" name="tags" style="width:480px" value="${ssoUser.tags }" readonly />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>累计消费:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" style="width:480px" value="${orderAmount }" readonly />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>账户余额:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" style="width:480px" value="${accountAmount }" readonly />
			    </div>
			</div>
			
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>球友卡余额:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" style="width:480px" value="${qiuyouAmount }" readonly />　<a href="${ctx}/ssouser/viewAccount?id=${ssoUser.id }">查看账户</a>
			    </div>
			</div>
			<div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>剩余积分:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" style="width:480px" value="${integralAccount }" readonly />　<a href="${ctx}/ssouser/viewQiuyouAccount?id=${ssoUser.id }">查看球友卡</a>
			    </div>
			</div>
			<div class="row">
		<div class="col-md-1">
		</div>
		<fieldset> <legend><small>积分详情</small></legend>
    	<div class="col-md-8">
    		<table class="table table-bordered table-condensed table-hover">
    			<thead>
					<tr>
						<th style="border-bottom-width: 0px;">序号</th>
						<th style="border-bottom-width: 0px;">积分数</th>
						<th style="border-bottom-width: 0px;">积分来源</th>
						<th style="border-bottom-width: 0px;">时间</th>
						<!-- <th style="border-bottom-width: 0px;">有效期至</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${integrals}" var="item" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count }</td>
						<td>${item.integral }</td>
						<td>${item.description }</td>
						<td>${item.ct }</td>
						</tr>
					</c:forEach>
				</tbody>
    		</table>
    	</div>
  </div>
  <div class="form-group form-group-sm">
				<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>所属企业:</label>
			    <div class="col-md-6 has-feedback">
			    	<input type="text" class="form-control" style="width:480px" value="${companyName }" readonly />
			    </div>
		</div>
		<div class="form-group form-group-sm">
			<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>企业基金:</label>
		    <div class="col-md-6 has-feedback">
		    	<input type="text" class="form-control" style="width:480px" value="${limitAmount/100 }" readonly />
		    </div>
		</div>
		<div class="form-group form-group-sm">
			<label for="sort" class="col-md-3 control-label"><span class="text-red"></span>已用基金:</label>
		    <div class="col-md-6 has-feedback">
		    	<input type="text" class="form-control" style="width:480px" value="${useAmount/100 }" readonly />
		    </div>
		</div>
		<div class="col-md-1">
		</div>
		  	<fieldset> <legend><small>消费记录</small></legend>
    	<div class="col-md-8">
    		<table class="table table-bordered table-condensed table-hover">
    			<thead>
					<tr>
						<th style="border-bottom-width: 0px;">序号</th>
						<th style="border-bottom-width: 0px;">订单号</th>
						<th style="border-bottom-width: 0px;">姓名</th>
						<th style="border-bottom-width: 0px;">手机号码</th>
						<th style="border-bottom-width: 0px;">订单时间</th>
						<th style="border-bottom-width: 0px;">类别</th>
						<th style="border-bottom-width: 0px;">金额</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${staffAccount}" var="info" varStatus="stat">
						<tr>
						<td class="text-center">${stat.count}</td>
								<td>
									 ${info.orderId}
								</td>
								<td>
									 ${info.userName}
								</td>
								<td>
									 ${info.phone}
								</td>
								<td><fmt:formatDate value="${info.ct}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									${info.ordersType }
								</td>
								<td>
									 ${info.useAmout/100}元
								</td>
						</tr>
					</c:forEach>
				</tbody>
    		</table>
    	</div>
			<div class="form-group form-group-sm">
		    		<div class="col-md-offset-3 col-md-2">	
			    		<a href="javascript:window.history.go(-1);" class="btn btn-default btn-block" > 返回 </a>
					</div>
		  </div>		
		</fieldset>
	</form>
</div>

<script type="text/javascript">
$(function(){
	// 菜单栏显示底色（选中）
	menu.active('#ssouser-man');
 	// 低栏隐藏
	$("#adminFooter").hide();
});

</script>