<#list corpsList as corps >
                <tr>
					<td class="text-center"><input type="radio" name="corpsId" value="${corps.id}_${corps.name}_${corps.logo}"></td>
					<td>${corps.name}</td>
					<td>${corps.captainStr}</td>
					<td>${corps.area}&nbsp;${corps.activityArea}</td>
					<td>${corps.currentNumber}</td>
					<td>${corps.sportType}</td>
					<td>${corps.integralNum}</td>
					<td>${corps.phone}</td>
					<td><fmt:formatDate value="${corps.ct}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${corps.gameCount}</td>
					<td>${corps.integral}</td>
				</tr>
</#list>