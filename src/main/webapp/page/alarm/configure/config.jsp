<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
.alarm_button{width:133px; height:41px; background:url(content/skin/css/images/login/button.png) no-repeat; text-align:center; color:#fff; line-height:41px; font-size:20px; border:0px; cursor:pointer; font-family:微软雅黑; }
.alarm_button:hover{ background:url(content/skin/css/images/login/button_hover.png) center}
</style>
<head>
	<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.cookie.js"></script>
</head>
<form id="alarmConfigForm">

	<input type="hidden" id="stcd" name="stcd" value="${stcd}"/>
    <input type="hidden" id="personId" name="personId" value="${userIdList}"></input>
    <input type="hidden" name="id" value="${stAlarmConfigure.id}"></input>
	<table border="0" cellspacing="10" cellpadding="10" class="pop-table" style="font-size:14px;">
		<tr>
			<td class="table-left"><fmt:message key="waterLevelVar" />(m)：</td>
			<td class="table-right"><input type="text" id="waterranges" name="waterranges" style="width:460px;height:30px;" value="${stAlarmConfigure.waterranges}"></input>&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><fmt:message key="rainFallAmp" />(mm)：</td>
			<td class="table-right"><input type="text" id="rainranges" name="rainranges" style="width:460px;height:30px;" value="${stAlarmConfigure.rainranges}"></input>&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><fmt:message key="contacts" />：</td>
			<td class="table-right"><textarea  style="resize:none;width:460px;height:50px;" readonly="readonly" id="person" name="person">${userNameList}
			</textarea><button type="button"  style="margin-left:10px;background-color:#44c4e7;float:left;height:35px;margin-top:7px;" onclick="selectAlarmPerson('${stcd}','${userIdList}','${userNameList}');" class="btn-warn"><fmt:message key="choice" /></button></td>
		</tr>
		<tr>
			<td class="table-left"><fmt:message key="SMSContent" />：</td>
			<td class="table-right"><textarea  style="resize:none;width:460px;height:200px;" id="content" name="content">${stAlarmConfigure.content}</textarea></td>
		</tr>
		<tr>
			<td class="table-left"><fmt:message key="SMSFunction" />：</td>
			<td class="table-right">
			<div style="width:70px;float:left;">
				<input type="radio" id="isopen" name="isopen" style="text-align: left;" value="1"
		    	<c:if test="${stAlarmConfigure.isopen==1 || smsAlarm.isopen==null }">checked="checked"</c:if>><fmt:message key="close" /></input>
		    </div>
			<div style="width:70px;float:left;">
				<input type="radio" id="isopen" name="isopen" style="text-align: left;" value="0" <%--disabled="disabled"--%>
				<c:if test="${stAlarmConfigure.isopen==0}">checked="checked"</c:if>><fmt:message key="open" /></input>
			</div>
			  <%--<font style="color:red"><fmt:message key="smsContentAlarm" /></font>--%>
		    </td>
		</tr>
	</table>
	<input type="button" class="alarm_button" onclick="saveAlarmConfig()"  value="保 存"></input>
</form>