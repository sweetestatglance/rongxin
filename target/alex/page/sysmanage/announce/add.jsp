<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysAnnounceForm">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
        <tr>
			<td class="table-left"><label for="title"><fmt:message key="theme"/></label>：</td>
			<td class="table-right"><input type="text" id="title" size="70" name="title" style="width: 452px;" maxlength="50"></input></td>
		</tr>
		<tr>
			<td class="table-left"><label for="content"><fmt:message key="content"/></label>：</td>
			<td class="table-right"><textarea  rows="8" cols="55" id="content" name="content" rows="2" style="width: 450px; "></textarea></td>
		</tr>
	</table>
</form>
