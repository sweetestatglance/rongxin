<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="taskForm" style="height:350px;">
	<input type="hidden" name="id" value="${deviceTask.id}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="table-layout:fixed;word-wrap: break-word; word-break: break-all;">
		<tr>
			<td class="table-left" style="width:100px;"><label for="deviceno"><fmt:message key="stationCode"/></label>：</td>
			<td class="table-right" ><input class="pop-input-common" type="text" value="${deviceTask.stcd}" style="border: 0;line-height: 30px;margin-left:0px;" readonly="readonly"></td>
		    <td class="table-right" style="width:30px;">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskid"><fmt:message key="functionCode"/></label>：</td>
			<td class="table-right">${deviceTask.taskcode}</td>
			<td class="table-right">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskcreatetime"><fmt:message key="createTime"/></label>：</td>
			<td class="table-right"><fmt:formatDate value="${deviceTask.taskcreatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
		    <td class="table-right">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskcomplatetime"><fmt:message key="completionTime"/></label>：</td>
			<td class="table-right"><fmt:formatDate value="${deviceTask.taskcomplatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
		    <td class="table-right">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskcontent"><fmt:message key="sendContent"/></label>：</td>
			<td class="table-right">${deviceTask.taskcontent}</td>
			<td class="table-right">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskresultoriginal"><fmt:message key="downlinkMessage"/></label>：</td>
			<td class="table-right">${deviceTask.downlinkpacket}</td>
			<td class="table-right">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskresultparsing"><fmt:message key="uplinkMessage"/></label>：</td>
			<td class="table-right">${deviceTask.uplinkpacket}</td>
			<td class="table-right">&nbsp;</td>
		</tr>
		<tr>
			<td class="table-left"><label for="taskstatus"><fmt:message key="taskStatus"/></label>：</td>
			<td class="table-right"><c:choose>
					<c:when test="${deviceTask.taskstatus==0}"><fmt:message key="taskNotImplemented"/></c:when>
					<c:when test="${deviceTask.taskstatus==1}"><fmt:message key="inExecution"/></c:when>
					<c:when test="${deviceTask.taskstatus==2}"><fmt:message key="endOfExecution"/></c:when>
					<c:when test="${deviceTask.taskstatus==3}"><fmt:message key="taskTimeout"/></c:when>
					<c:when test="${deviceTask.taskstatus==4}"><fmt:message key="executionFailure"/></c:when>
					<c:otherwise></c:otherwise>
				</c:choose></td>
			<td class="table-right">&nbsp;</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
	  //$('.combobox').combobox();
	});
</script>