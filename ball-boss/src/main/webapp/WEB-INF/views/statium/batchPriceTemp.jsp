<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/common.jsp" %>
<link rel="stylesheet" href="${ctx}/static/css/project.css"/>

<div class="contentByTemplate" id="contentByTemplate_${space.id}"></div>
<!-- 模板内容 -->
		<textarea id="template" style="display:none">
			<div class="wrap" ballTypeCode="{$T.ball_type_code}">
                <div class="ball-type-title">{$T.ball_type}<span class="btn_close">关闭</span></div>
               <table class="table table-bordered user-reset">
                <tr>
                	<td>补贴金额 </td>
                	<td> <input type="text" value="{$T.subsidies}"  name = "subsidies"/></td>
                </tr>
				</table>
                <hr/>
                <div class="working-days-layer">
                    <table class="table table-bordered user-reset">
                        <caption>工作日</caption>
                        <thead>
                        <tr>
                            <th>时段名称</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>原价</th>
                            <th>销售价</th>
                            <th>成本价</th>
                            <!-- <th>
                                <button class="btn_add_row">新增</button>
                            </th> -->
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input type="text" name="title" value="早"/>
                            </td>
                            <td>
                                <select name="startTimes" guidFlag="{$T.guidFlag_1}">
                                    <option value="00">00:00</option>
                                    <option value="01">01:00</option>
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTimes" guidFlag="{$T.guidFlag_2}">
                                    <option value="01">01:00</option>
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                                                        <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                            <!-- <td>
                                <button class="btn_del_row">删除</button>
                            </td> -->
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="title" value="中"/>
                            </td>
                            <td>
                                <select name="startTimes" guidFlag="{$T.guidFlag_3}">
                                    <option value="01">01:00</option>
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTimes" guidFlag="{$T.guidFlag_4}">
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                            <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                            <!-- <td>
                                <button class="btn_del_row">删除</button>
                            </td> -->
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="title" value="晚"/>
                            </td>
                            <td>
                                <select name="startTimes" guidFlag="{$T.guidFlag_5}">
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTimes" guidFlag="{$T.guidFlag_6}">
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                            <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                           <!--  <td>
                                <button class="btn_del_row">删除</button>
                            </td> -->
                        </tr>
                        <tr class="time_others_tr">
                            <td>
                                <input type="hidden" name="title" value="其他时段"/>其他时段
                            </td>
                            <td></td>
                            <td></td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                            <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                            <!-- <td></td> -->
                        </tr>
                        </tbody>
                    </table>
                </div>
                <hr/>
                <div class="no-working-days-layer">
                    <table class="table table-bordered user-reset">
                        <caption>非工作日</caption>
                        <thead>
                        <tr>
                            <th>时段名称</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>原价</th>
                            <th>销售价</th>
                            <th>成本价</th>
                            <!-- <th>
                                <button class="btn_add_row">新增</button>
                            </th> -->
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input type="text" name="title" value="早"/>
                            </td>
                            <td>
                                <select name="startTimes" guidFlag="{$T.guidFlag_7}">
                                    <option value="00">00:00</option>
                                    <option value="01">01:00</option>
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTimes" guidFlag="{$T.guidFlag_8}">
                                    <option value="01">01:00</option>
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                            <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                            <!-- <td>
                                <button class="btn_del_row">删除</button>
                            </td> -->
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="title" value="中"/>
                            </td>
                            <td>
                                <select name="startTimes" guidFlag="{$T.guidFlag_9}">
                                    <option value="01">01:00</option>
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTimes" guidFlag="{$T.guidFlag_10}">
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                           <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                            <!-- <td>
                                <button class="btn_del_row">删除</button>
                            </td> -->
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="title" value="晚"/>
                            </td>
                            <td>
                                <select name="startTimes" guidFlag="{$T.guidFlag_11}">
                                    <option value="02">02:00</option>
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <select name="endTimes" guidFlag="{$T.guidFlag_12}">
                                    <option value="03">03:00</option>
                                    <option value="04">04:00</option>
                                    <option value="05">05:00</option>
                                    <option value="06">06:00</option>
                                    <option value="07">07:00</option>
                                    <option value="08">08:00</option>
                                    <option value="09">09:00</option>
                                    <option value="10">10:00</option>
                                    <option value="11">11:00</option>
                                    <option value="12">12:00</option>
                                    <option value="13">13:00</option>
                                    <option value="14">14:00</option>
                                    <option value="15">15:00</option>
                                    <option value="16">16:00</option>
                                    <option value="17">17:00</option>
                                    <option value="18">18:00</option>
                                    <option value="19">19:00</option>
                                    <option value="20">20:00</option>
                                    <option value="21">21:00</option>
                                    <option value="22">22:00</option>
                                    <option value="23">23:00</option>
                                    <option value="24">24:00</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                            <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                            <!-- <td>
                                <button class="btn_del_row">删除</button>
                            </td> -->
                        </tr>
                        <tr class="time_others_tr">
                            <td>
                                <input type="hidden" name="title" value="其他时段"/>其他时段
                            </td>
                            <td></td>
                            <td></td>
                            <td>
                                <input type="text" name="price" value="80"/>
                            </td>
                            <td>
                                <input type="text" name="signPrice" value="75"/>
                            </td>
                            <td>
                                <input type="text" name="costPrice" value="75"/>
                            </td>
                           <!--  <td></td> -->
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
		</textarea>
<!-- 模板内容 -->