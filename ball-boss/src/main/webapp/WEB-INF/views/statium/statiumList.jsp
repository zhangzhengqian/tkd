<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>场馆列表</title>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading"><!-- 右侧标题 -->
    <ul class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span> 场馆列表</li>
    </ul>
  </div><!-- / 右侧标题 -->

  <div class="panel-body"><!-- 右侧主体内容 -->
		<div class="row"><!-- 查询条件 -->
			<div class="col-md-12">
			  	<form id="search_form" class="form-horizontal" action="${ctx}/statium" method="post">
			  		<input type="hidden" name="search_EQ_status" value="${param.search_EQ_status }" id="search_EQ_status"  />
			  		<input type="hidden" name="search_EQ_sb" value="${param.search_EQ_sb}" id="search_EQ_sb"  />
			  		<input type="hidden" name="search_EQ_top" value="${param.search_EQ_top}" id="search_EQ_top"  />
			  		<input type="hidden" name="msg" value="" id="search_EQ_msg"  />
			  		<input type="hidden" name="statiumIds" value="" id="statiumIds"  />
			  		<input type="hidden" name="id" value="" id="id"  />
			  		<input type="hidden" name="type" value="" id="type"  />
			        <div class="form-group form-group-sm">
			          <label class="control-label col-md-1 sr-only" for="option"></label>
				  	  <div class="col-md-5">
				  	  		<input  type="text" class="form-control input-sm" id="search_LIKE_address"  name="search_LIKE_address"  value="${param.search_LIKE_address }" placeholder="按场馆地址查询">
			       	  </div>
			       	  <div class="col-md-5">
			       	  	<select class="form-control" id="search_LIKE_sportType" name="search_LIKE_sportType">		
							<option  value="" >--请选择运动类别--</option>
							<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
								<option  value="${item.itemCode }" >--${item.itemName }--</option>
							</c:forEach>
					    </select>
							<%-- <input  type="text" class="form-control input-sm" id="search_LIKE_masterName"  name="search_LIKE_masterName"  value="${param.search_LIKE_masterName }" placeholder="按场馆负责人名称查询"> --%>
			       	  </div>	
			        </div>
			 
			        <div class="form-group form-group-sm">
			          <lable class="control-label col-md-1 sr-only"></lable>
			          	<div class=" col-md-5 ">
			          		
			          		<div class="input-group">
					          <input  type="text" class="form-control input-sm" id="orgName"  value="<tags:orgName orgCode="${param.search_EQ_orgCode }" />" placeholder="按组织查询">
					          <span class="input-group-btn">
					            <button class="btn btn-default btn-sm" type="button"  id="orgNameClean">清除</button>
					          </span>
					        </div>
							<input  type="hidden" class="form-control input-sm" id="search_EQ_orgCode" name="search_EQ_orgCode" value="${param.search_EQ_orgCode }" />
			          	</div>
			          	<div class=" col-md-5">
			        		<input  type="text" class="form-control input-sm" id="statiumName" name="statiumName" value="${param.statiumName}"  placeholder="按场馆名称查询">
			        		<input  type="hidden" class="form-control input-sm" id="statiumId"  name="search_EQ_id"  value="${param.search_EQ_id }">
			        	</div>         
			        </div>			 		
			        
			 		<!-- 带有约束的日期条件，开始－结束 --> 
			        <!-- 注意：每个 form-group 占一行，显示两个列，其中带有 query-more 样式的行默认是隐藏的  -->
			        <div class="form-group form-group-sm query-more">
			          	<lable class="control-label col-md-1 sr-only"></lable>
			          	<div class=" col-md-5 ">
			             	<input type="text" name="search_GTE_ct" placeholder="球馆录入开始时间" value="${param.search_GTE_ct }" id="search_GTE_ct" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM-dd"});'readonly>
			          	</div>
			          	<div class=" col-md-5">
			            	<input type="text" name="search_LTE_ct" placeholder="球馆录入结束时间" id="search_LTE_ct" value="${param.search_LTE_ct }" class="form-control" onclick='WdatePicker({"dateFmt":"yyyy-MM-dd"});'readonly>
			        	</div>         
			        </div>
			 		
			 		<div class="form-group form-group-sm query-more">
			          <lable class="control-label col-md-1 sr-only"></lable>
			          <div class="col-md-5 form-inline">
			               <tags:zone id="areaCode" name="areaCode" value="${param.areaCode }" disabled="${disable }" />
			          </div>
			          <div class="col-md-5">
		         	 		<select class="form-control" name="search_EQ_isSigned" id="search_EQ_isSigned" >
								<option value="" <c:if test="${ empty param.search_EQ_isSigned }">selected</c:if>>已签约/未签约</option>
								<option value="0" <c:if test="${param.search_EQ_isSigned == '0' }">selected</c:if>>未签约</option>
								<option value="1" <c:if test="${param.search_EQ_isSigned == '1' }">selected</c:if>>已签约</option>
							</select>
			          </div>
			        </div>
					<div class="form-group form-group-sm query-more">
						<lable class="control-label col-md-1 sr-only"></lable>
						<div class="col-md-10 form-inline">
								<select class="form-control" name="search_EQ_tpPoiId" id="search_EQ_tpPoiId" >
									<option value="" <c:if test="${ empty param.search_EQ_tpPoiId }">selected</c:if>>是否已同步到糯米</option>
									<option value="0" <c:if test="${param.search_EQ_tpPoiId == '0' }">selected</c:if>>未同步</option>
									<option value="1" <c:if test="${param.search_EQ_tpPoiId == '1' }">selected</c:if>>已同步</option>
								</select>
						</div>

					</div>

					<div class="form-group form-group-sm">
				  <div class="col-md-12 text-center">
				    <!-- <a href="javaScript:" onclick="updateStatium();" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-warning-sign"></i> 请勿操作</a> -->					   
				    <button type="reset" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-refresh"></span> 重 置</button>
				    &nbsp;&nbsp;
				    <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 搜 索</button>
				    &nbsp;&nbsp;
				    <button type="button" class="btn btn-link btn-sm" id="btn-query-more"><span class="glyphicon glyphicon-chevron-down"></span> 更多条件</button>
				  </div>
				</div>
   			 </form>
			</div>
		</div><!-- /查询条件 -->
	  <div class="row"><!-- 操作按钮组 -->
	    <div class="col-md-5 form-inline">
		    <div class="btn-group" role="group" aria-label="...">
				  <button value=""  type="button" class="searchStatus btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;全部状态</button>
				  <button value="0" type="button" class="searchStatus btn btn-warning btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;待审核</button>
				  <button value="1" type="button" class="searchStatus btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核通过</button>
				  <button value="2" type="button" class="searchStatus btn btn-info btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;审核拒绝</button>
			</div>
			<shiro:hasPermission name="statium:msg">
			<div class="btn-group" role="group" aria-label="...">
				  <a href="javascript:;" class="sendBatch btn btn-danger btn-sm"><span class="glyphicon glyphicon-send" aria-hidden="true"></span>&nbsp;短信批量发送</a>
			</div>
			<div class="btn-group" role="group" aria-label="...">
				  <a href="javascript:;" class="sendAll btn btn-primary btn-sm"><span class="glyphicon glyphicon-send" aria-hidden="true"></span>&nbsp;短信全部发送</a>
			</div>
			</shiro:hasPermission>
	    </div>
	    <div class="col-md-7 text-right">
			<div class="btn-group" role="group" aria-label="...">
			<shiro:hasPermission name="statium:top">
			  <button value="1" type="button" class="searchTop btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;置顶的场馆</button>
			  <button value="" type="button" class="searchTop btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;取消此条件</button>
			</shiro:hasPermission>
			  <button value="${currentUser.userId}" type="button" class="searchSb btn btn-success btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;我签约的球馆</button>
			  <button value="" type="button" class="searchSb btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;取消此条件</button>
			</div>
		      
		    <shiro:hasPermission name="statium:createForm">
		    	<a class="btn btn-primary btn-sm" href="${ctx }/statium/createForm?action=create" ><span class="glyphicon glyphicon-plus"></span> 添加球馆</a>
		    </shiro:hasPermission>
	    </div>
	  </div><!-- /操作按钮组 -->
	<br>	
				
	<div class="row">
    <div class="col-table col-md-12" >
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<shiro:hasPermission name="statium:msg">
				<th class="text-center"><input type="checkbox" name="chk_all"
							onclick="if(this.checked==true) { checkAll('chk_list'); } else { clearAll('chk_list'); }" /></th>
				</shiro:hasPermission>
				<th class="text-center">序号</th>
				<!-- <th>商户号</th> -->
				<th>球馆名称</th>
				<shiro:hasPermission name="statium:uploadToNuomi">
					<th>同步</th>
				</shiro:hasPermission>
				<th>上次回访时间</th>
				<th>签约状态</th>
				<th>场馆类型</th>
				<th>所在地区</th>
				<th>地址</th>
				<!--  
				<th>联系人姓名</th>
				-->
				<th>联系电话</th>
				<th>录入人</th>
				<th>录入时间</th>
				<!--  
				<th>签约时间</th>
				-->
				<th>签约人</th>
				<th>审核状态</th>
				<!--  
				<th>球馆状态</th>
				-->
				<th>置顶时间</th>
				<th>组织</th>
				<th>商城权限</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${data.content}" var="statium" varStatus="stat">
				<c:set var="status_class" value="" />
				<c:if test="${statium.status == 0}">
					<c:set var="status_class" value="warning" />
				</c:if>
				<c:if test="${statium.status == 1}">
					<c:set var="status_class" value="success" />
				</c:if>
				<c:if test="${statium.status == 2}">
					<c:set var="status_class" value="info" />
				</c:if>
				<tr class="${status_class }" >
				<shiro:hasPermission name="statium:msg">
				<td class="text-center">
					<c:choose>
						  	<c:when test="${statium.isSigned == '0' }">
						  	</c:when>
						  	<c:otherwise>
					<input type="checkbox"
								name="chk_list" id="chk_list_${stat.index }" value="${statium.id}" />
						  	</c:otherwise>
						  </c:choose>
					</td>
				</shiro:hasPermission>
					<td class="text-center">${stat.count}</td>
					<%-- <td>${statium.name}</td> --%>
					<td>
						<a href="${ctx }/statium/detailForm?id=${statium.id}"> ${statium.name}</a>
					</td>
					<shiro:hasPermission name="statium:uploadToNuomi">
					<td>
						<a target="_blank"  href="${ctx }/statium/openUpload?statiumId=${statium.id}">同步到糯米</a>
					</td>
					</shiro:hasPermission>
					<c:if test="${statium.isVisit == 0 || empty statium.isVisit}">
						<td>${statium.visitDate }</td>
					</c:if>
					<c:if test="${statium.isVisit == 1 }">
						<td style="color:#ff0000; font-weight:bold;">${statium.visitDate }</td>
					</c:if>
					<td>
						<c:choose>
						  	<c:when test="${statium.isSigned == '0' }">
						  		未签约
						  	</c:when>
						  	<c:otherwise>
								已签约
						  	</c:otherwise>
						  </c:choose>  
					</td>
					<td>
						<c:forEach items="${fn:split(statium.sportType,';;') }" var="s" >
							<c:if test="${s == 'BOWLING'}">
								保龄球 &nbsp;
							</c:if>	
							<c:if test="${s == 'BILLIARDS'}">
								台球 &nbsp;
							</c:if>	
							<c:if test="${s == 'TABLE_TENNIS'}">
								乒乓球 &nbsp;
							</c:if>	
							<c:if test="${s == 'FOOTBALL'}">
								足球 &nbsp;
							</c:if>
							<c:if test="${s == 'BASKETBALL'}">
								篮球 &nbsp;
							</c:if>
							<c:if test="${s == 'TENNIS'}">
								网球 &nbsp;
							</c:if>
							<c:if test="${s == 'GOLF'}">
								高尔夫 &nbsp;
							</c:if>
							<c:if test="${s == 'BADMINTON'}">
								羽毛球 &nbsp;
							</c:if>
						</c:forEach>
					</td>
					<%-- <td><tags:zone value="${statium.areaCode}" clazz="false" disabled="disabled" /></td> --%>
					<td> <tags:zonemap code="${statium.areaCode }" /></td>
					<td>${statium.address }</td>
					<!--  
					<td>${statium.masterName}</td>
					-->
					<td>${statium.masterTel}</td>
					<td>
						<tags:getUserById id="${statium.cb }" />
					</td>
					<td><fmt:formatDate value="${statium.ct}" pattern="yyyy-MM-dd"/></td>
					<!--  
					<td>签约时间</td>
					-->
					<td>
						<tags:getUserById id="${statium.sb }" />
					</td>

					<c:if test="${statium.status == 0}">
					<td class="warning">待审核</td>
					</c:if>
					<c:if test="${statium.status == 1}">
					<td class="success">审核通过</td>
					</c:if>
					<c:if test="${statium.status == 2}">
					<td class="info">审核拒绝</td>
					</c:if>
					<c:if test="${empty statium.status }">
					<td >无状态</td>
					</c:if>
					<!--  
					<td>
						<c:choose>
						  	<c:when test="${statium.isRating == 0 }">
						  		收费场馆
						  	</c:when>
						  	<c:otherwise>
								免费场馆
						  	</c:otherwise>
						  </c:choose>  
					</td>
					-->
					<td><fmt:formatDate value="${statium.topTime}" pattern="yyyy-MM-dd"/></td>
					<td>
						<tags:orgName orgCode="${statium.orgCode }" />
					</td>
					<c:if test="${statium.ctaFlag == null||statium.ctaFlag == 0}">
						<td>无</td>
					</c:if>
					<c:if test="${statium.ctaFlag == 1}">
						<td>有</td>
					</c:if>
					<td>
					   <shiro:hasPermission name="statium:detailForm">
					   		<a class="btn btn-default btn-sm" href="${ctx }/statium/detailForm?id=${statium.id}&action=edit"><i class="glyphicon glyphicon-edit"></i> 修改</a></a>
					   </shiro:hasPermission>
					    <!--  
					   <a href="#" class="btn btn-default btn-sm" ><i class="glyphicon glyphicon-arrow-up"></i> 置顶</a></a>
					    <c:if test="${statium.status == 0}">
				   		 	<a href="#" class="btn btn-default btn-sm" ><i class="glyphicon glyphicon-ban-circle"></i> 冻结</a></a>
				   		 </c:if>
				   		<c:if test="${statium.status == 1}">
				   		 	<a href="#" class="btn btn-default btn-sm" ><i class="glyphicon glyphicon-ok-circle"></i> 解冻</a></a>
				   		 </c:if>
					    -->
				   	   <shiro:hasPermission name="statium:space">
					   		<a href="${ctx }/statium/space?statiumId=${statium.id}" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-cog"></i> 场地管理</a>
					   </shiro:hasPermission>
					   <shiro:hasPermission name="space:usage">
					   		<a href="${ctx }/statium/space/usage?statiumId=${statium.id}" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-tasks"></i> 预约情况</a>
					   </shiro:hasPermission>
					   	<a class="btn btn-default btn-sm" href="#" onclick="updateRating('${statium.id}',1)"><i class="glyphicon glyphicon-edit"></i>更为免费</a>
					   	<a href="${ctx }/statium/crmManagerForm?statiumId=${statium.id}"  class="btn btn-default btn-sm"> <i class="glyphicon glyphicon-edit"></i>场馆管理员</a>
					   <shiro:hasPermission name="statium:top">
					   <c:if test="${statium.top == 0}">
							<a href="javaScript:" onclick="statiumTop('${statium.id}',0);" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-arrow-up"></i> 置顶</a>					   
					   </c:if>
					   <c:if test="${statium.top == 1}">
							<a href="javaScript:" onclick="statiumTop('${statium.id}',1);" class="btn btn-default btn-sm"><i class="glyphicon glyphicon glyphicon-remove"></i> 取消置顶</a>					   
					   </c:if>
					   </shiro:hasPermission>
					   <c:if test="${statium.ctaFlag == null||statium.ctaFlag == 0}">
							<a  href="javascript:;" onclick="setCta('${statium.id}',1)" class="btn btn-default btn-sm"><i class="glyphicon glyphicon"></i> 授予商城权限</a>
						</c:if>
						<c:if test="${statium.ctaFlag == 1}">
							<a  href="javascript:;" onclick="setCta('${statium.id}',0)" class="btn btn-default btn-sm"><i class="glyphicon glyphicon"></i> 取消商城权限</a>
						</c:if>
					   <shiro:hasPermission name="statium:delete">
					   		<a class="btn btn-danger btn-sm" href="#" onclick="deleteById('${statium.id}')"> <i class="glyphicon glyphicon-trash"></i> 删除</a>
					   </shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		    </div><!-- end col-table -->
</div><!-- end row -->
		<tags:pagination page="${data}" />
    <form id="actionForm" action="" method="post">
       <input type="hidden" id="ids" name="ids">
    </form>
  </div><!-- /右侧主体内容 -->
</div>
<div class="modal fade" id="msgModel" order_id="" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">短信内容</h4>
				</div>
				<div class="modal-body" class="">
					<div  class="form-group">
						<textarea rows="5" cols="10" id="msg" class="form-control">${param.search_EQ_msg}</textarea>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn" data-dismiss="modal"
						aria-hidden="true">取消</a> <a href="javascript:;"
						class="btn btn-primary alert-to-ok" onClick="sendMsg()">发送</a>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">
$(function() {
	  menu.active('#statium-man');
	  
	  var v_search_LIKE_sportType = '${param.search_LIKE_sportType}';
	  if(v_search_LIKE_sportType){
		  $('#search_LIKE_sportType option[value='+v_search_LIKE_sportType+']').attr('selected','selected');
	  }
		
	  /* 按状态查询 */
	  $(".searchStatus").click(function(){
		  var v = $(this).val();
		  $("#search_EQ_status").val(v);
		  $("#search_form").submit();
	  });
	  
	  /* 按状态查询 */
	  $(".searchSb").click(function(){
		  var v = $(this).val();
		  $("#search_EQ_sb").val(v);
		  $("#search_form").submit();
	  });
	  
	  /* 按置顶场馆查询 */
	  $(".searchTop").click(function(){
		  var v = $(this).val();
		  $("#search_EQ_top").val(v);
		  $("#search_form").submit();
	  });
	  
	 /* 按照组织查询  */
	 $("#orgName").click(function() {
		$("#myDlgBody").load("${ctx}/common/search_org_dlg");
		$("#myDlg").modal();
	 });
	 $("#orgNameClean").click(function(){
		$("#orgName").val('');
		$("#search_EQ_orgCode").val('');
 	});
	  
	  $('#adminFooter').hide();
		
	  var typeValue = '${param.search_EQ_type }';
	
	  if(typeValue){
	  	$("select[name=search_EQ_type] option[value="+typeValue+"]").attr("selected","selected");
	  }
	  
	  
	    //====================================================
		// 自动匹配 场馆名称 >>>>
		//====================================================
		$('#statiumName').autocomplete('${ctx}/common/search_statium_by_name?flag=true',{
			dataType:'json',
			minChars: 2,                   //最少输入字条
            max: 30,
            autoFill: false,
            mustMatch: false,               //是否全匹配, 如数据中没有此数据,将无法输入
            matchContains: true, 
            scrollHeight: 220,
            width: $('#statiumName').outerWidth(),
            multiple: false,
            formatItem: function (row, i, max) {                    //显示格式
                return "【"+row.name+"】【"+row.area+"】【"+row.address+"】";
            },
            formatResult: function (row) {                      //返回结果
                return row.name;
            },
            handleValue:function(id){
            	$('#statiumId').val(id);
            },
            parse:function(data){
            	var parsed = [];
        		var rows = data;
        		for (var i=0; i < rows.length; i++) {
        			var row = rows[i];
        			if (row) {
        				parsed[parsed.length] = {
        					data: row,
        					value: row["id"],
        					result: this.formatResult(row)
        				};
        			}
        		}
        		return parsed;
            }
		});
		//====================================================
		// 自动匹配 场馆名称 <<<<
		//====================================================
			
	  $("button[type=reset]").click(function(){
		  $(this).closest("form").find("input").attr("value","");
		  $(this).closest("form").find("select option:selected").attr("selected",false);
		  $(this).closest("form").find("select option:first").attr("selected",true);
	  });
	  
	  $(".sendBatch").on("click",function(e){
		  var ids = getSelected();
		  if (ids.length == 0) {
			bootbox.alert('请选择要发送短信的场馆！');
			return false;
		  }
		  ids = ids.join(",");
		  $("#statiumIds").val(ids);
		  $("#msgModel").modal();
	  });
	  
	  $(".sendAll").on("click",function(e){
		  var areaCode = $("#areaCode").val();
		  if(areaCode==''){
			  bootbox.alert('请按地区筛选场馆！');
			  return false;
		  }
		  $("#msgModel").modal();
	  });
});
  function sendMsg(){
	  var msg = $("#msg").val();
	  if(msg==''){
		  bootbox.alert('请编辑短信内容！');
		  return;
	  }
	  $("#search_EQ_msg").val(msg);
	  $("#search_form").attr("action","${ctx}/statium/sendMsg");
	  $("#search_form").submit();
  } 
  function getSelected() {
	    var ids = [];
	    var checked = $('input:checked');
	    if (checked.length) {
	      checked.each(function() {
	        if ($(this).attr('name') != 'chk_all') {
	          ids.push($(this).val());
	        }
	      });
	    }
	    return ids;
	}
  
  
	  
	function deleteById(id) {
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要删除该场馆吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/statium/delete', function(data){
	      	 if(data.result){
		      	 myAlert("场馆删除成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("场馆删除失败","error");
			 }
	      }, null, {"id":id}, 'post');
	    }
	  });
	  return false;
	}
	
   //更新免费状态
  function updateRating(id,rating){
	  var $form = $('#actionForm');
	  bootbox.confirm('您确定要将场馆改成免费的吗？', function(result) {
	    if(result) {
	      Util.getData(ctx + '/statium/updateRating', function(data){
	      	 if(data.result){
		      	 myAlert("修改成功");
		      	 window.location.reload()
		     }else{
		    	 myAlert("场馆修改失败","error");
			 }
	      }, null, {"id":id,"isRating":rating}, 'post');
	    }
	  });
	  return false;
  }
   
   function setCta(id,type){
	                                                                                                                                    $("#search_form").attr("action","${ctx}/statium/setCta");
	   $("#id").val(id)
	   $("#type").val(type)
	   $("#search_form").submit();
   }
  
  // 置顶
  function statiumTop(id,type){
	  if(!id){
		  myAlert("场馆id不能为空","error");
		  return false;
	  }
	  Util.getData(ctx + '/statium/top', function(data){
      	 if(data.result){
	      	 myAlert("置顶成功");
	      	 window.location.reload()
	     }else{
	    	 myAlert(data.reason,"error");
	    	 return false;
		 }
      }, null, {"id":id,"type":type}, 'get');
  } 
  
  function updateStatium(){
	  Util.getData(ctx + '/statium/updateStatium', function(data){
      	 if(data.result){
	      	 myAlert("修改成功");
	      	 window.location.reload()
	     }else{
	    	 myAlert(data.reason,"error");
	    	 return false;
		 }
      }, null, {"statiumId":""}, 'get');
  }

</script>

</body>
</html>