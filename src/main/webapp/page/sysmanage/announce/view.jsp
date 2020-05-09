<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysAnnounceForm">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="padding: 10px;">
        <tr>
			<td class="table-left"><label for="title"><fmt:message key="theme"/></label>：</td>
			<td class="table-right"><input type="text" id="title" name="title" value="${model.title}" readonly style="width: 402px;height: 20px;border: 1px solid #E5E5E5;"></input></td>
		</tr>
		<tr>
			<td class="table-left"><label for="content"><fmt:message key="content"/></label>：</td>
			<td class="table-right"><textarea  rows="10" cols="55" id="content" name="content" rows="2" readonly style=" width: 400px;border: 1px solid #E5E5E5;resize: none;">${model.content}</textarea></td>
		</tr>
	</table>
</form>
