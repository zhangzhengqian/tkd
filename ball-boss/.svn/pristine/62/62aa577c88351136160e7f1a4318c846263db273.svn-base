<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<style type="text/css">
		#allmap {width: 100%;height: 500px;margin:0;position:relative;}
		#golist {display: none;}
		@media (max-device-width: 780px){#golist{display: block !important;}}
	.img_close{
		position: relative;
		top: -110px;
		right: -115px;
		cursor: pointer;
		font-size: 25px;
		background-color: #FF6F00;
		height: 20px;
		width: 20px;
		border-radius: 50%;
		line-height: 20px;	
	}
	</style>
<div class="panel panel-default">
	<div class="panel-heading"><!-- 右侧标题 -->
	    <ul class="breadcrumb">
	        <li><span class="glyphicon glyphicon-home"></span><a href="${ctx }/statium/"> 场馆列表 </a></li>
	        <li class="active">球馆信息</li>
	    </ul>
  	</div><!-- / 右侧标题 -->
  
  <c:choose>
  	<c:when test="${param.action == 'edit' || param.action == 'create'}">
  		<c:set var="disable" value="false"/>
  	</c:when>
  	<c:otherwise>
		<c:set var="disable" value="true"/>
		<c:set var="readonly" value="readonly='readonly'"/>
  	</c:otherwise>
  </c:choose>  
  
  <div class="panel-body"><!-- 右侧主体内容 -->
  
	<h3>场馆信息</h3>
	<hr>
	<form id="inputForm" action="${ctx}/statium/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<input type="hidden" name="next_page" value="/admin/org/statiumForm/${userId }" />
		<input type="hidden" name="id" value="${statium.id}" />
		<input type="hidden" name="priceTemps" id="priceTemps"/>
		<fieldset>

 		    <div class="form-group form-group-sm">
		       <label for="name" class="col-md-3 control-label"><span class="text-red">* </span>球馆名称:</label>
		       <div class="col-md-6 has-feedback">
		         <input ${readonly } type="text" class="form-control" id="name" name="name" value="${statium.name }" placeholder="请输入球馆名称" />
		       </div>
		    </div>
           <input type="hidden" name="isRating" value="0" >
   		  <div class="form-group form-group-sm">
			 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>是否签约:</label>
		     <div class="col-md-6 has-feedback">
		    	    <label class="radio-inline"><input type="radio" name="isSigned" value="1"  <c:if test="${statium.isSigned != 0}"> checked="checked" </c:if> >是</label>
                    <label class="radio-inline"><input type="radio" name="isSigned" value="0"  <c:if test="${statium.isSigned == 0}"> checked="checked" </c:if>>否</label>
		     </div>
		 </div>
		 <c:if test="${not empty readonly }">
			 <div class="form-group form-group-sm">
				 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>签约人:</label>
			     <div class="col-md-6 has-feedback">
			     	<input ${readonly } type="text" class="form-control" value='<tags:getUserById id="${statium.sb }" />'  />
			     </div>
			 </div>
		 </c:if>
		  <%-- <div class="form-group form-group-sm">
			 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>预设关键字:</label>
		     <div class="col-md-6 has-feedback">
		    	    <input ${readonly } type="text" class="form-control" id="name" name="name" value="${statium.name }" placeholder="请输入球馆关键字，以逗号隔开"/>
		     </div>
		 </div>--%>
		 
			<%-- <div class="form-group form-group-sm">
				<label for="branchName" class="col-md-3 control-label"><span class="text-red"></span>分店名称:</label>
			    <div class="col-md-6 has-feedback">
			    	<input ${readonly } type="text" class="form-control" id="branchName" name="branchName" value="${statium.branchName }" />
			    </div>
			</div>--%>
      
	      <div class="form-group form-group-sm">
	         <label for="logoFile" class="col-md-3 control-label"><span class="text-red">* </span>球馆LOGO:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${not empty statium.logo && not empty readonly}">
	         		<img alt="" src="${statium.logo }" <c:if test="${not empty statium.logo }">height="100" </c:if>>
	         	</c:if>
				<c:if test="${empty readonly }">
					<!-- 上传控件 -->
					<input id="logoFile" type="file" multiple="false" />
					<!-- 保存图片 -->
					<input id="logo" name="logo" type="hidden" value="${statium.logo}" />
					<!-- 显示图片 -->
					<img alt="" src="${statium.logo}" id="logo_img" <c:if test="${not empty statium.logo }">height="100" </c:if>>
	    		</c:if>
	         </div>
	      </div>
	      
	      <div class="form-group form-group-sm">
	         <label for="areaCode" class="col-md-3 control-label"><span class="text-red">* </span>所在地区:</label>
	         <div id="div_areaCode" class="col-md-6 has-feedback form-inline" >
	         	<c:choose>
				  	<c:when test="${empty readonly }">
				  		<tags:zone id="areaCode" name="areaCode" value="${statium.areaCode }"  disabled="${disable }" />
				  	</c:when>
				  	<c:otherwise>
						<tags:zonemap code="${statium.areaCode }" />
				  	</c:otherwise>
				  </c:choose>  
				
	         </div>
	      </div>
	      
	     <div class="form-group form-group-sm">
	         <label for="" class="col-md-3 control-label"><span class="text-red">*</span>场馆坐标:</label>
	         <div class="col-md-6 has-feedback form-inline">

				<div class="input-group">
					<input ${readonly } readOnly type="text" class="form-control" id="lnglat" name="lnglat" value="<c:if test="${ not empty statium.lng}">${statium.lng },${statium.lat}</c:if>" placeholder="经度,纬度" />
		         	<span class="input-group-btn">
		         		<c:if test="${empty readonly }">
		            		<button id="coordinate" class="btn btn-primary btn-sm" type="button">坐标/地址 识取工具</button>
		            	</c:if>	
		          	</span>
		        </div>
		       <%--
		        <input ${readonly } readOnly type="text" class="form-control" id="lnglat" name="lnglat" value="<c:if test="${ not empty statium.lng}">${statium.lng },${statium.lat}</c:if>" placeholder="经度,纬度" />
				<c:if test="${empty readonly }">
					<!-- <a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="_blank">坐标识取工具</a> -->
					<a href="#" id="coordinate">坐标/地址 识取工具</a>
				</c:if>	
		       --%>
		       
        	 </div>
	    </div>
	    
	    <div class="form-group form-group-sm">
	         <label for="address" class="col-md-3 control-label"><span class="text-red">* </span>详细地址:</label>
	         <div class="col-md-6 has-feedback ">
	           <input ${readonly } type="text" class="form-control" id="address" name="address" value="${statium.address}" />
	         </div>
	    </div>
	    
	    <div class="form-group form-group-sm" style="display: none;" id="baiduMap">
	         <label for="" class="col-md-3 control-label"></label>
	         <div class="col-md-6 has-feedback form-inline">
	         	<div class="panel panel-default">
	         		<div class="panel-heading">
							<input type="text" class="form-control" id="keyword"/>
							<button type="button" class="btn btn-primary btn-sm" id="search"><span class="glyphicon glyphicon-search"></span> 搜 索</button>	         			
	         				<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	         		</div>
	         		<div class="panel-body">
				       	<div id="allmap"></div>
	         		</div>
	         	</div>
        	 </div>
	    </div>
	    
		<%--<div class="form-group form-group-sm">
			 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>场馆折扣:</label>
		     <div class="col-md-6 has-feedback">
		    	 <input ${readonly } type="text" class="form-control" id="qiuyouRating" name="qiuyouRating" value="${statium.qiuyouRating}" placeholder="折扣只能填一位小数,且值小于1" /><span id="qiuyouRating_span"  class="text-danger text-red"></span>
		     </div>
		 </div> --%>
		 
         <div class="form-group form-group-sm">
              <label for="startTime" class="col-md-3 control-label"><span class="text-red">* </span>营业时间:</label>
              <div class="col-md-6 has-feedback form-inline">
						<c:choose>
							<c:when test="${empty statium.startTime }">
								<c:set var="startTime" value="0" />
								<c:set var="endTime" value="24" />
							</c:when>
							<c:otherwise>
								<c:set var="startTime" value="${statium.startTime }" />
								<c:set var="endTime" value="${statium.endTime }" />
							</c:otherwise>
						</c:choose>
               		  <div class="input-group">
                      	<input type="number" ${readonly } value="${startTime}" name="startTime" id="startTime"  >
                      </div>
                      	至
                      <div class="input-group">
                      	<input type="number" ${readonly } value="${endTime}" name="endTime" id="endTime"   >
                      </div>
              </div>
          </div>
          
	      <div class="form-group form-group-sm">
	         <label for="tel" class="col-md-3 control-label"><span class="text-red">* </span>订场电话:</label>
	         <div class="col-md-6 has-feedback ">
	           <%--
	           <input ${readonly } type="text" style="width:100px;" maxLength="4"  class="form-control" id="telHead" name="telHead" placeholder="区号" value=""/>
	           --%>
	           <input ${readonly } type="text" class="form-control" id="tel" name="tel" value="${statium.tel }" placeholder="请输入场馆电话,格式: 区号-电话号"/>
	         </div>
	      </div>
	
	      <div class="form-group form-group-sm">
	         <label for="email" class="col-md-3 control-label"><span class="text-red"></span>电子邮箱:</label>
	         <div class="col-md-6 has-feedback">
	         	<input ${readonly } type="text" class="form-control" id="email" name="email" value="${statium.email }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterName" class="col-md-3 control-label"><span class="text-red">* </span>负责人姓名:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="masterName" name="masterName" value="${statium.masterName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="masterTel" class="col-md-3 control-label"><span class="text-red">* </span>联系电话:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="masterTel" name="masterTel" value="${statium.masterTel }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCard" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="masterIdCard" name="masterIdCard" value="${statium.masterIdCard }" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCardImg1File" class="col-md-3 control-label"><span class="text-red"></span>法人身份证上传:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${not empty statium.masterIdCardImg1 && not empty readonly}">
	         		<img alt="" src="${statium.masterIdCardImg1 }" <c:if test="${not empty statium.masterIdCardImg1 }">height="100" </c:if>>
	         	</c:if>
				<c:if test="${empty readonly }">
					<input id="masterIdCardImg1" type="file" multiple="false" />
					<input id="masterIdCardImg1File" name="masterIdCardImg1" type="hidden" value="${statium.masterIdCardImg1}" />
					<img alt="" src="${statium.masterIdCardImg1 }" id="masterIdCardImg1File_img" <c:if test="${not empty statium.masterIdCardImg2 }">height="100" </c:if>>
	    		</c:if>
	         </div>
	         
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="masterIdCardImg2File" class="col-md-3 control-label"><span class="text-red"></span>负责人身份证背面:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${!empty statium.masterIdCardImg2 && not empty readonly}">
	         		<img alt="" src="${statium.masterIdCardImg2 }" <c:if test="${not empty statium.masterIdCardImg2 }">height="100" </c:if>>
	         	</c:if>
				<c:if test="${empty readonly }">
					<input id="masterIdCardImg2" type="file" multiple="false" />
					<input id="masterIdCardImg2File" name="masterIdCardImg2" type="hidden" value="${statium.masterIdCardImg2}" />
					<img alt="" src="${statium.masterIdCardImg2 }" id="masterIdCardImg2File_img" <c:if test="${not empty statium.masterIdCardImg2 }">height="100" </c:if>>
	    		</c:if>
	         </div>
	      </div>

		<div class="form-group form-group-sm">
	         <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>场馆设施:</label>
	         <div class="col-md-9 has-feedback">
	           	<div class="btn-group" data-toggle="buttons">
	           		<c:set var="facilitys" value="储物柜,更衣室,热水淋浴,商店,室内,停车场,wifi,休息室,夜场灯光,银行卡,驻场裁判,驻场教练,租赁" />
	           		<c:set var="facilitysLen" value="${fn:length(fn:split(facilitys,','))}" />
	           		<c:choose> 
  						<c:when test="${facilitysLen % 2 == 0}">   
  							<c:set var="facilitysBr" value="${facilitysLen / 2}" />
  						</c:when> 
  						<c:otherwise>
  							<c:set var="facilitysBr" value="${(facilitysLen + 1) / 2}" />
  						</c:otherwise> 
					</c:choose> 
	           		<c:forEach items="${facilitys}" var="facility" varStatus="status">
	           			<c:set var="active" value="" />
						<c:set var="checked" value="" />	
						<c:forEach items="${fn:split(statium.facilities,',') }" var="obj" >
							<c:if test="${obj == facility}">
								<c:set var="active" value="active abc" />	
								<c:set var="checked" value="checked" />
							</c:if>	
						</c:forEach>
			       		<label class="btn btn-default ${active }">
					  		<input name="facilities" type="checkbox" autocomplete="off" ${checked } value="${facility }"> ${facility }	
						</label><c:if test="${status.index == facilitysBr}"></br></c:if>
					</c:forEach>
				</div>
	         </div>
	      </div>

		<div class="form-group form-group-sm">
	         <label for="introduce" class="col-md-3 control-label"><span class="text-red"></span>球馆介绍:</label>
	         <div class="col-md-6 has-feedback">
	         	<textarea  ${readonly } style="height: 100px;" class="form-control" rows="5" id="introduce" placeholder="球馆介绍" name="introduce">${statium.introduce }</textarea>
	         </div>
	      </div>

		<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">上传场馆图片:</label>
	         <div class="col-md-6" name="statium_img" style="width: 135px;">
	         	 <input id="photo1File" type="file" multiple="false" />
	         	<c:set var="count" value="1"/>
	         </div>
	         <span name="picSpan" style="color:red;line-height:30px;">（标准尺寸：640px * 300px） </span>
	      </div>
	      <div class="form-group form-group-sm">
	       <label for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="col-md-6" name="statium_img"  >
	         	<c:set var="count" value="1"/>
	         	<c:if test="${not empty statium.photos}">
	         		<c:forEach items="${fn:split(statium.photos,'__') }" var="itm" varStatus="s">
	         			<c:if test="${s.last}">
	         				<c:set var="count" value="${s.index + 2}"/>
	         			</c:if>
	         			<div style="float:left;margin-right:10px;">
							<input id="photo${s.index + 1}" name="photo" value="${itm }" type="hidden" />
	         				<img alt="" src="${itm}" id="photo_img${s.index + 1}" height="100" width="130" />
	         				<div aria-hidden="true" class="img_close">&times;</div>
	         			</div>
	         		</c:forEach>
		         	<c:forEach var="i" begin="${count}" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	<c:if test="${empty statium.photos}">
	         		<c:forEach var="i" begin="1" end="5" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="photo${i}" name="photo" type="hidden" />
							<img alt="" src="" id="photo_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	
	         	<c:if test="${param.action == 'create' }">
					<div style="float:left;margin-right:10px;">
						<input id="photo1" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo2" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo3" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo4" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img4"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="photo5" name="photo" type="hidden" />
						<img alt="" src="" id="photo_img5"/>
	           		</div>
	    		</c:if>
	         </div>
	      </div>
	      

		  <div class="form-group form-group-sm">
    		 <label for="accountType" class="col-md-3 control-label">*开户类型</label>
		     <div class="col-md-6 has-feedback form-inline">
				 <select class="form-control" value="${statium.accountType }" name="accountType" id="accountType" <c:if test="${'update' eq action}">disabled="disabled"</c:if>>
				 	 <option id="person_option"  value="person">--个人--</option>
					 <option id="company_option"  value="company">--企业--</option>
				 </select>
		  	 </div>
	      </div>

	      <div class="form-group form-group-sm" id="accountCert_div">
	         <label for="accountCert" class="col-md-3 control-label"><span class="text-red"></span>*开户行所登记的身份证:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="accountCert" name="accountCert" value="${statium.accountCert}" />
	         </div>
	      </div>

	      <div class="form-group form-group-sm">
	         <label for="bankAccountName" class="col-md-3 control-label"><span class="text-red"></span>银行开户名:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="bankAccountName" maxlength="50" name="bankAccountName" value="${statium.bankAccountName }" />
	         </div>
	      </div>
	      <div class="form-group form-group-sm">
	         <label for="bankAccountNo" class="col-md-3 control-label"><span class="text-red"></span>银行账号:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="bankAccountNo" name="bankAccountNo" value="${statium.bankAccountNo }" />
	         </div>
	      </div>
	      
	      
	      <div class="form-group form-group-sm">
	         <label for="bankAccountBranchName" class="col-md-3 control-label"><span class="text-red"></span>开户支行名称:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="bankAccountBranchName" maxlength="50" name="bankAccountBranchName" value="${statium.bankAccountBranchName }" />
	         </div>
	      </div>
<%-- 	      <div class="form-group form-group-sm">
	         <label for="bankAccountBranchNo" class="col-md-3 control-label"><span class="text-red"></span>支行联行号:</label>
	         <div class="col-md-6 has-feedback">
	           <input ${readonly } type="text" class="form-control" id="bankAccountBranchNo" name="bankAccountBranchNo" value="${statium.bankAccountBranchNo }" />
	         </div>
	      </div> --%>
	      <div class="form-group form-group-sm">
	         <label for="bankLicenseImgFile" class="col-md-3 control-label"><span class="text-red"></span>开户银行许可证:</label>
	         <div class="col-md-6 has-feedback">
	         	<c:if test="${!empty statium.bankLicenseImg && not empty readonly}">
	         		<img alt="" src="${statium.bankLicenseImg }"<c:if test="${not empty statium.bankLicenseImg }">height="100" </c:if>>
	         	</c:if>
				<c:if test="${empty readonly }">
	           		<input id="bankLicenseImg" type="file" multiple="false" />
					<input id="bankLicenseImgFile" name="bankLicenseImg" type="hidden" value="${statium.bankLicenseImg}" />
					<img alt="" src="${statium.bankLicenseImg}" id="bankLicenseImgFile_img" <c:if test="${not empty statium.bankLicenseImg }">height="100" </c:if>>
	    		</c:if>
	         </div>
	      </div>

		 <div class="form-group form-group-sm">
	         <label for="sportType" class="col-md-3 control-label"><span class="text-red">* </span>运动类别:</label>
	         <div class="col-md-6 has-feedback">
	         	<div class="btn-group" data-toggle="buttons">
	           	<c:forEach items="${lf:dicItems('SPORT_TYPE') }" var="item">
					<c:set var="active" value="" />	
					<c:set var="checked" value="" />	
					<c:forEach items="${fn:split(statium.sportType,';;') }" var="obj" >
						<c:if test="${obj eq item.itemCode}">
							<c:set var="active" value="active abc" />	
							<c:set var="checked" value="checked" />	
						</c:if>	
					</c:forEach>
					<label class="btn btn-default ${active } ball" ballTypeCode="${item.itemCode }" ballType="${item.itemName }">
				  		 ${item.itemName }
					</label>
		       	</c:forEach>
				</div>
	         </div>
	      </div>
		 <input name="sportType" value="${statium.sportType}" id="sportType" type="hidden">
		 <div class="form-group form-group-sm">
        	 <label class="col-md-3 control-label"></label>
         	<div class="col-md-6 has-feedback" >
				 <%@ include file="/WEB-INF/views/statium/priceTemp.jsp" %>
         	</div>
     	 </div>

		<div class="form-group form-group-sm">
	         <label for="notice" class="col-md-3 control-label"><span class="text-red"></span>球馆公告:</label>
	         <div class="col-md-6 has-feedback">
	         	<textarea  ${readonly } style="height: 100px;" class="form-control" rows="5" id="notice" placeholder="球馆公告" name="notice">${statium.notice}</textarea>
	         </div>
	      </div>

		<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label">球馆公告图片:</label>
	         <div class="col-md-6" name="statium_img"  style="width: 135px;">
	         		<input id="notice1File" type="file" multiple="false" />
	         </div>
	          <span name="picSpan" style="color:red;line-height:30px;">（标准尺寸：232px * 160px） </span>
	         
	      </div>
		<div class="form-group form-group-sm">
	         <label for="photosFile" class="col-md-3 control-label"> </label>
	         <div class="col-md-6" name="statium_img" >
	         	<c:if test="${param.action == 'edit' }">
	         	<c:set var="count" value="1"/>
	         	<c:if test="${not empty statium.noticeImg}">
	         		<c:forEach items="${fn:split(statium.noticeImg,'__') }" var="itm" varStatus="s">
	         			<c:if test="${s.last}">
	         				<c:set var="count" value="${s.index + 2}"/>
	         			</c:if>
	         			<div style="float:left;margin-right:10px;">
							<input id="noticeImgs${s.index + 1}" name="noticeImgs" value="${itm }" type="hidden" />
	         				<img alt="" src="${itm}" id="notice_img${s.index + 1}" height="100" width="130" />
	         				<div aria-hidden="true" class="img_close">&times;</div>
	         			</div>
	         		</c:forEach>
	         	</c:if>
		         	<c:forEach var="i" begin="${count}" end="4" step="1"> 
		         		<div style="float:left;margin-right:10px;">
	        				<input id="noticeImgs${i}" name="noticeImgs" type="hidden" />
							<img alt="" src="" id="notice_img${i}" />
		         		</div>
					</c:forEach>
	         	</c:if>
	         	
	         	<c:if test="${param.action == 'create' }">
	         	<div class="has-feedback">
					<div style="float:left;margin-right:10px;">
						<input id="noticeImgs1" name="noticeImgs" type="hidden" />
						<img alt="" src="" id="notice_img1"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="noticeImgs2" name="noticeImgs" type="hidden" />
						<img alt="" src="" id="notice_img2"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="noticeImgs3" name="noticeImgs" type="hidden" />
						<img alt="" src="" id="notice_img3"/>
					</div>
					<div style="float:left;margin-right:10px;">
						<input id="noticeImgs4" name="noticeImgs" type="hidden" />
						<img alt="" src="" id="notice_img4"/>
					</div>
	           	</div>				
	    		</c:if>
	         </div>
	      </div>
		<hr/>
		
		<div class="form-group">
			<c:if test="${empty readonly }">
				<div class="col-md-offset-3 col-md-3">	
		    		<a href="${ctx }/statium" class="btn btn-default btn-block" > 返回 </a>
				</div>
				<div class="col-md-3">
		    		<button type="submit" id="submit_btn" class="btn btn-primary btn-block" > 保存 </button>
				</div>
	    	</c:if>
		</div>
		</fieldset>
	</form>
  </div>
</div>

<%--
审核表单 >>>>
--%>
<c:set var="_audit" value="false" />
<shiro:hasRole name="customer">
	<c:set var="_audit" value="true" />
</shiro:hasRole>
<shiro:hasRole name="customer_manager">
	<c:set var="_audit" value="true" />
</shiro:hasRole>


<c:if test="${not empty readonly }">
<c:if test="${_audit and statium.status eq '0' }">
	<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon"></span> 场馆审核</li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
		<div class="panel-body">
		
			<form id="auditForm" action="${ctx}/statium/audit" method="post" class="form-horizontal"  >
				<input type="hidden" name="id" value="${statium.id}" />
				<fieldset>
					<div class="form-group form-group-sm">
						 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>结果:</label>
					     <div class="col-md-6 has-feedback">
			                    <select class="form-control"  id="audit" name="audit" >
			                    	<option value="1">审核通过</option>
			                    	<option value="2">审核拒绝</option>
			                    </select>
					     </div>
					 </div>	
					 
					 <div class="form-group form-group-sm">
						 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>签约人:</label>
					     <div class="col-md-6 has-feedback">
		                     <select class="form-control"  id="userId" name="userId" >
		                     	<option value="">无签约人</option>
							 	<c:forEach items="${signUser}" var="user" varStatus="stat">
							 		<option value="${user.userId}" <c:if test="${user.userId == statium.sb }">selected</c:if>>${user.nickname}--${user.loginName}</option>
							 	</c:forEach>
		                     </select>
					     </div>
					 </div>	
					 
		 		    <div class="form-group form-group-sm">
				       <label for="name" class="col-md-3 control-label">原因:</label>
				       <div class="col-md-6 has-feedback">
				       		<textarea class="form-control" rows="3" style="height: 50px;" id="reason" name="reason" ></textarea>
				       </div>
				    </div>
					<hr />
					<div class="form-group">
						<div class="col-md-offset-1 col-md-3">	
				    		<button type="reset" class="btn btn-default btn-block" > 重置 </button>
						</div>
						<div class="col-md-3">
				    		<button type="button" id="audit_btn" class="btn btn-primary btn-block" > 确认 </button>
						</div>
						<div class="col-md-3">
				    		<button type="button" id="batch_query_btn" class="btn btn-info btn-block" > 批量查看场地信息 </button>
						</div>
					</div>
					
				</fieldset>
			</form>
		</div>
	</div>
</c:if>
</c:if>
<%--
审核表单 <<<<
--%>

<%--
回访表单>>>>
--%>		
<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon"></span> 场馆回访</li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
		<div class="panel-body">
		
			<form id="visitForm" action="${ctx}/statium/visit" method="post" class="form-horizontal"  >
				<input type="hidden" name="id" value="${statium.id}" />
				<fieldset>
					<div class="form-group form-group-sm">
						 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>问题:</label>
					     <div class="col-md-6 has-feedback">
				       		<textarea class="form-control" rows="3" style="height: 50px;" id="visitReason" name="visitReason" ></textarea>
				       	 </div>
					 </div>	
					 
					 <div class="form-group form-group-sm">
						 <label for="qiuyouRating" class="col-md-3 control-label"><span class="text-red"></span>结果:</label>
					     <div class="col-md-6 has-feedback">
				       		<textarea class="form-control" rows="3" style="height: 50px;" id="visitResult" name="visitResult" ></textarea>
				       	 </div>
					 </div>	
					 
					<hr />
					<div class="form-group">
						<div class="col-md-offset-1 col-md-3">	
				    		<button type="reset" class="btn btn-default btn-block" > 重置 </button>
						</div>
						<div class="col-md-3">
				    		<button type="button" id="visit_btn" class="btn btn-primary btn-block" > 确认 </button>
						</div>
					</div>
					
				</fieldset>
			</form>
		</div>
	</div>	
<%--
回访表单>>>>
--%>	
	
<%--
审核日志 >>>>
--%>
	<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon"></span> 审核记录 </li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
		<div class="panel-body">
   			<table class="table table-hover">
			    <thead>
					<tr>
						<th>#</th>
						<th>动作</th>
						<th>描述</th>
						<th>操作人</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${auditLog}" var="itm" varStatus="stat">
						<tr>
							<td>${stat.index+1 }</td>
							<td>${itm.action }</td>						
							<td>${itm.description }</td>						
							<td><tags:getUserById id="${itm.cb }" /></td>						
							<td>
								<fmt:formatDate value="${itm.ct }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>						
						</tr>	
					</c:forEach>
				</tbody>
   			 </table>
						
		</div>
	</div>
<%--
审核日志 <<<<
--%>

<%--
回访记录 >>>>
--%>
	<div class="panel panel-default">
		<div class="panel-heading"><!-- 右侧标题 -->
		    <ul class="breadcrumb">
		        <li><span class="glyphicon"></span> 回访记录  </li>
		    </ul>
	  	</div><!-- / 右侧标题 -->
		<div class="panel-body">
   			<table class="table table-hover">
			    <thead>
					<tr>
						<th>#</th>
						<th>问题</th>
						<th>结果</th>
						<th>回访人</th>
						<th>回访时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${visitLog}" var="itm" varStatus="stat">
						<tr>
							<td>${stat.index+1 }</td>
							<td>${itm.visitReason }</td>						
							<td>${itm.visitResult }</td>						
							<td><tags:getUserById id="${itm.cb }" /></td>						
							<td>
								<fmt:formatDate value="${itm.ct }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>						
						</tr>	
					</c:forEach>
				</tbody>
   			 </table>
						
		</div>
	</div>
<%--
回访记录 <<<<
--%>

<script src="${ctx}/static/js/bootstrap-validation/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-validation/messages_zh.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?ak=89jrTPxQwh49QGwzQe1g6azM&v=2.0"></script>
<script src="${ctx}/static/js/jquery-jtemplates.js"></script>
<script src="${ctx}/static/js/project_js.js"></script>
<script src="${ctx}/static/js/bankData.js"></script>
<script type="text/javascript">
function trim(str) {
	return str.replace(/(\s)/g, "");
}
$(function() {
	menu.active('#statium-man');
	$("#myDlgBody_lg").dragmove();
	$('#bankAccountNo').on('blur',function(){
		for(var index=0;index<bankData.length;index++){
			if(trim($(this).val()).indexOf(bankData[index]['bin'])==0){
				$('#bankAccountBranchName').val(bankData[index]['bankName'].substring(0,bankData[index]['bankName'].indexOf('-')));
				return;
			}
		}
	})
	$("div[name = statium_img]").on("click",'.img_close',function(){
		$(this).parent().find("input").val("");
		$(this).parent().find("img").attr("src","").attr("height","0").attr("width","0");
		$(this).remove();
	});
	
	$("#audit_btn").click(function(){
		var audit = $("#audit").val();
		var reason = $("#reason").val();
		if(audit==2){
			if(!reason){
				myAlert("请填写拒绝原因.");			
				return ;
			}
		}
		$("#auditForm").submit();
	});
	
	$("#visit_btn").click(function(){
		var visitReason = $("#visitReason").val();
		var visitResult = $("#visitResult").val();
		if(!visitReason){
			myAlert("请填写回访问题.");			
			return ;
		}
		if(!visitResult){
			myAlert("请填写回访结果.");			
			return ;
		}
		$("#visitForm").submit();
	});
	
	$(".close").click(function(){
		$("#baiduMap").slideUp('slow');
	});
	$('#batch_query_btn').on('click',function(){
		$("#myDlgBody_lg").load("${ctx}/common/batch_query_dlg",{statiumId:'${statium.id}'});
		$("#myDlg_lg").modal();
	})
	$("#coordinate").click(function(){
		$("#baiduMap").show();
		var lnglat = $("#lnglat").val();
		var lng = 116.403867;
		var lat = 39.914113;
		var name = "天安门";
		if(lnglat){
			var lnglats = lnglat.split(",");
			lng = lnglats[0];
			lat = lnglats[1];
			var name = $("#name").val();
		}
		// 百度地图API功能
        map = new BMap.Map("allmap");
        map.centerAndZoom(new BMap.Point(lng,lat), 14);
        map.setCenter(new BMap.Point(lng,lat));
        var marker1 = new BMap.Marker(new BMap.Point(lng, lat));  //创建标注
        map.addOverlay(marker1);                 // 将标注添加到地图中
        var infoWindow1 = new BMap.InfoWindow(name);
        marker1.addEventListener("click", function (e) {
           this.openInfoWindow(infoWindow1, false);
        });
        
        $("#search").click(function(){
        	search($("#keyword").val(),map);
        });
        
        // 获取经纬度和地址
        map.addEventListener("click", function(e){
        	var lnglat = e.point.lng + "," + e.point.lat;
        	$("#lnglat").val(lnglat);
        	var gc = new BMap.Geocoder(); 
            gc.getLocation(e.point, function(rs) {
             	var addComp = rs.addressComponents; 
             	var address = '';
                address += addComp.province;
                address += addComp.city;
                address += addComp.district;
                address += addComp.street;
                address += addComp.streetNumber;
                $("#address").val(address);
                //alert("当前定位地址为：" + address);
             });
        });
	});	
	
	function search(name,map){
		 var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		}); 
		local.search(name);
	}
	
	<c:if test="${not empty spacePrices }">
		var data = JSON.parse('${spacePrices}');
		BindAndEditWrap(data,0);
		<c:if test="${not empty readonly}">
			$("#contentByTemplate").find("input,select,button").attr("disabled",true);
			$(".btn_close").hide();
		</c:if>
	</c:if>
	$("input[name=facilities]").parent("label").click(function(){
		if($(this).hasClass("abc")){
			$(this).removeClass("abc");
		}else{
			$(this).addClass("abc");
		}
		if($(this).parent("div").find("label[class*=abc]").size()==0){
			//只处理  消除error
			//$(this).parent("div").parent("div").removeClass("has-error").addClass("has-error");
			//$(this).parent("div").parent("div").find("p").remove();
			//$(this).parent("div").parent("div").append("<p id=\"sportTypes-error\" class=\"help-block\">必填字段</p>")
		}else{
			$(this).parent("div").parent("div").removeClass("has-error");
			$(this).parent("div").parent("div").find("p").remove();
		}
	});
	  $("#qiuyouRating").blur(function(){
	  	var value = $("#qiuyouRating").val();
	  	if(value!=""){
	  		var patrn= new RegExp("^0.[1-9]{1,2}$");
	  		if(!patrn.exec(value)){
	  			$("#qiuyouRating_span").html("<p id='qiuyouRating_p'>折扣只能填一位小数,且值小于0</p>"); 
	  		}else{
	  			$("#qiuyouRating_p").remove();	
	  		}
	  	}
	  	if(value == ""){
	  		$("#qiuyouRating_p").remove();
	  	}
	  });
});

$(function(){
	 $(window).load(function(){
		 	if('${isExist }'){
		 		alert('${isExist }');
		 	}
			var accountTypeValue ='${statium.accountType }';
			if(!accountTypeValue){
				accountTypeValue = 'person';
			}
			if(accountTypeValue == "person"){
				$("#accountCert_div").css("display","block");
				$("#person_option").attr("selected","selected");
				$("#accountCert").addClass("required");
			}
			if(accountTypeValue == "company"){
				$("#accountCert_div").css("display","none");
				$("#accountCert").val("");
				$("#company_option").attr("selected","selected");
			}	
			
			if('${statium.id }'){
				$("#logoFile").parent().parent().find("label span").html("");
			}else{
				$("#logoFile").addClass("required");
			}
		});
	$("#accountType").change(function(){
		var accountTypeValue =$("#accountType option:selected").val();
		if(accountTypeValue == "person"){
			$("#accountCert_div").css("display","block");
			$("#accountCert").addClass("required");
		}
		if(accountTypeValue == "company"){
			$("#accountCert_div").css("display","none");
			$("#accountCert").val("");
		}
	});
});

$(function() {
	$("#div_areaCode select:eq(2)").each(function(){
		$(this).addClass("required");
	});
	
	$('#inputForm').validate({
		submitHandler: function(form) {
			//表单验证成功时，锁住提交按钮 ,此操作可防治重复点击提交按钮
			getData(function(data){
				app.disabled("#submit_btn");
				$("#priceTemps").val(data);
				form.submit(); 
			});
		},
		rules: {
			name: {
				required: true,
				maxlength:30
				//,remote: "${ctx}/statium/checkName?id=${statium.id}"
			},
			/* branchName: {
				required: true
			},
			telHead: {
				required: true,
				integer:true
			},
			*/
			tel: {
				required: true,
				isPhoneCode : true
				//integer:true
			},
			/* email: {
				required: true
			}, */
/* 			areaCode: {
				required: true
			}, */
			address: {
				required: true
			},
			masterName: {
				required: true
			},
			masterTel: {
				required: true,
				integer:true,
				isMobile : true
			},
			sportType : {
				required: true
			},
            startTime : {
                required : true,
                time23 : true
            },
            endTime : {
                required : true,
                time23 : true
            },facilities : {
                required : true
            },sportTypes : {
                required : true
            },lnglat:{
				required: true,
				isCoordinate:true
			}
			/* masterIdCard : {
				required: true
			} */
		},
		messages: {
			
		}
	});
	
});

$(function() {
	upload({'id':'logoFile','icon_img':'logo_img','icon':'logo'});  // 球馆LOGO
	
	multipleUpload({'id':'photo1File','icon_img':'photo_img','icon':'photo','limit':5});  // 
	multipleUpload({'id':'notice1File','icon_img':'notice_img','icon':'noticeImgs','limit':4});  // 
		
	upload({'id':'masterIdCardImg1','icon_img':'masterIdCardImg1File_img','icon':'masterIdCardImg1File'});  // 身份证正面
	upload({'id':'masterIdCardImg2','icon_img':'masterIdCardImg2File_img','icon':'masterIdCardImg2File'});  // 身份证反面
	upload({'id':'bankLicenseImg','icon_img':'bankLicenseImgFile_img','icon':'bankLicenseImgFile'});  // 开户银行许可证
});

/**
*	option.id            上传元素id
*	option.icon_img      显示图片id
*	option.icon          保存图片的url的id
*	option.width         显示图片的宽度
*	option.height        显示图片的高度
*/
function upload(option){
	var id = option.id || "icon_upload";
	var height = option.height|| 40;
	var width = option.width || 120;
	var icon_img = option.icon_img || "icon_img";
	var icon = option.icon || "icon";
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
	    //按钮名称
		buttonText    : '选择图片',
		height        : 30,
		multi         :false,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploader',
		width         : 100,
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$('#'+icon_img).attr('src',data.url).css({width:'100px',height:'100px;'});
				$('#'+icon).val(data.url);
	       }
	    }  
	});
}

function multipleUpload(option){
	var id = option.id || "icon_upload";
	var height = option.height|| 40;
	var width = option.width || 120;
	var icon_img = option.icon_img || "icon_img";
	var icon = option.icon || "icon";
	var limit = option.limit || 1;
	$("#"+id).uploadify({
        //文件提交到 controller 里的文件对象的 key 
		fileObjName   : 'upfile',
		queueSizeLimit: limit,
		multi         :true,
	    //按钮名称	
		buttonText    : '选择图片',
		height        : 30,
		swf           : ctx + '/static/uploadify/uploadify.swf',
	    //提交到指定的 controller,写死即可，已封装
	    uploader      : ctx + '/uploader',
		width         : 100,
		fileTypeExts:'*.gif;*.jpg;*.jpeg;*.png',
		overrideEvents : [ 'onDialogClose','onSelectError' ],
		//上传成功后回调此函数
	    onUploadSuccess : function(file, data, response) {
	        //分析返回值，json格式：{"success":true/false,"id":"文件id","url":"文件url"}
			data = JSON.parse(data);
			if(data.success==true){
				$("input[name="+icon+"]").each(function(i){
					var photo = $(this).val();
					if(!photo){
						var index = i+1;
						$('#'+ icon_img + index).attr('src',data.url).attr({"height":"100","width":"130"});
						$('#'+ icon + index ).val(data.url);
						$('#'+ icon_img + index ).parent().append("<div aria-hidden='true' class='img_close'>&times;</div>");
						return false;
					}
				});
			}
	    },
	   onSelectError : uploadify_onSelectError,  
	});
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        myAlert(msgText);	
    };
</script>