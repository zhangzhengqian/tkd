<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
			<div class="row">
				<div class="col-sm-6 text-left">
						<span class="glyphicon glyphicon-equalizer" aria-hidden="true"></span> 订单短信自动发送设定
						
						&nbsp;&nbsp;
						<c:forEach items="2016,2017,2018,2019,2020" var="itm">
							<a href="${ctx }/admin/orderSms?year=${itm}"  class="label  <c:if test="${not (year eq itm) }">label-default</c:if> <c:if test="${year eq itm }">label-primary</c:if>">${itm }</a>
						</c:forEach>
				</div>
				<div class="col-sm-6 text-right">
				</div>
			</div>
	</div>
	<div class="panel-body">
	
	
		<c:forEach items="01,02,03,04,05,06,07,08,09,10,11,12" var="mk" >
		<c:if test="${mk eq '01' or mk eq '03'   or mk eq '05'   or mk eq '07'   or mk eq '09'   or mk eq '11'   }">
		<div class="row">
		</c:if>
				<div class="col-sm-6">
					<div class="panel panel-info">
						<div class="panel-heading"> ${mk } 月 </div>
						<div class="panel-body">
							<table class="table table-bordered text-right">
								<tr class="warning">
									<th>周日</th><th>周一</th><th>周二</th><th>周三</th><th>周四</th><th>周五</th><th >周六</th>
								</tr>
								<c:forEach items="${months[mk].dayList}"  var="day"  varStatus="idx">
										
										<c:if test="${idx.index eq 0 }">
												<tr>
													<c:if test="${day.week eq '星期日' }"><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if>" >${day.day }</td></c:if>
													<c:if test="${day.week eq '星期一' }"><td></td><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if>" >${day.day }</td></c:if>
													<c:if test="${day.week eq '星期二' }"><td></td><td></td><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if>" >${day.day }</td></c:if>
													<c:if test="${day.week eq '星期三' }"><td></td><td></td><td></td><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if>" >${day.day }</td></c:if>
													<c:if test="${day.week eq '星期四' }"><td></td><td></td><td></td><td></td><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if> " >${day.day }</td></c:if>
													<c:if test="${day.week eq '星期五' }"><td></td><td></td><td></td><td></td><td></td><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if>" >${day.day }</td></c:if>
													<c:if test="${day.week eq '星期六' }"><td></td><td></td><td></td><td></td><td></td><td></td><td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if>" >${day.day }</td></c:if>
											<c:if test="${day.week eq '星期六' }">	
												</tr>
											</c:if>
										</c:if>
										
										<c:if test="${idx.index gt 0 and day.week eq '星期日' }"><tr></c:if>
										
											<c:if test="${idx.index gt 0 }">
												
												<td id="${day.year }-${day.month}-${day.day}" class="day_cell <c:if test="${ not empty orderSms[day.date] }">success</c:if> "  >
													<span >${day.day } </span]>
												</td>
											</c:if>
										
										<c:if test="${idx.index gt 0 and day.week eq '星期六' }"></tr></c:if>

								</c:forEach>								
								
							</table>
						</div>
					</div>	
				</div>
			<c:if test="${mk eq '02' or mk eq '04'   or mk eq '06'   or mk eq '08'   or mk eq '10'   or mk eq '12'   }">
			</div>
			</c:if>
			</c:forEach>
		
		
		
	</div>
</div>




<script>
	$(function() {
		menu.active('#orderSms-man');
		
		var setHoliday = function( action,date ){
			$.ajax({
				type : "post",
				url : "${ctx}/admin/orderSms/save",
				dataType : "text",
				data : {action:action,date:date},
				success : function(msg) {
					myAlert(msg);
				}
			});
		}
		
		$(".day_cell").on('click',function(){
			var date = $(this).attr('id');
			
			if( $(this).hasClass('success') ){
				//设置成非节假日
				$(this).removeClass('success');
				setHoliday('remove',date);
			}else{
				//设置成节假日
				$(this).addClass('success');
				setHoliday('add',date);
			}
		
		});
		
	});
</script>

