<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/common.jsp"%>

<div class="modal-header">
	<button type="button" class="close" style="margin:-7px 0px 0px 0px" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<div class="panel panel-default">
	  <div class="panel-heading">
	    <h3 class="panel-title">参赛详情</h3>
	  </div>
  		<div class="panel-body">
  		
  		<div class="row">
			<div class="col-table col-md-12">
		<c:forEach var="item" items="${result}"> 
  		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<thead class="thead">
			<tr>
				<th colspan="6" >${item.key}</th>
			</tr>
			<tr>
				<th colspan="2">对阵队伍</th>
				<th>时间</th>
				<th>比分</th>
				<th>胜负</th>
				<th>积分</th>
			</tr>
			</thead>
			<tbody id="content_table">
				<c:forEach items="${item.value}" var="it" varStatus="stat">
  				<tr>
  					 <td>${it.name1 }</td>
  					 <td>${it.name2 }</td>
  					 <td>${it.gameTime }</td>
  					 <td>${it.score1 }:${it.score2 }</td>
  					 <td>${it.victory}</td>
  					 <td>${it.integration }</td>
  				</tr>
  				</c:forEach>
  		    </tbody>
  		 </table>
  		</c:forEach>
  		 </div>
  		 </div>
       </div>
   </div>
</div>