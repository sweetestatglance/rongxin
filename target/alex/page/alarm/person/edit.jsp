<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysAlarmPersonForm">
    <input type="hidden" id="id" name="id" value="${alarmPerson.id}"></input>
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
        <tr>
			<td class="table-left"><label for="title">姓名：</td>
			<td class="table-right"><input type="text" id="name" name="name" value="${alarmPerson.name}"></input></td>
		</tr>
		<tr>
			<td class="table-left"><label for="content">手机号</label>：</td>
			<td class="table-right"><input type="text" id="phone" name="phone" value="${alarmPerson.phone}"></input></td>
		</tr>
	</table>
</form>
