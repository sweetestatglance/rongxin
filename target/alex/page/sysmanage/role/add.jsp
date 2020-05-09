<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysRoleForm">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
      <tr>
			<td class="table-left"><label for="rolecode"><fmt:message key="byEnterprise"/></label>：</td>
			<td class="table-right"><input type="hidden" id="enterpriseid" name="enterpriseid" value="${enterprise.id}"> ${enterprise.enterprisename}</td>
		</tr>
		<tr>
			<td class="table-left"><label for="rolecode"><fmt:message key="roleCode"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="rolecode" name="rolecode" maxlength="32"></td>
		</tr>
		<tr>
			<td class="table-left"><label for="rolename"><fmt:message key="roleName"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="rolename" name="rolename" maxlength="32"></td>
		</tr>
		<tr>
			<td class="table-left"><label for="enabledstate"><fmt:message key="roleState"/></label>：</td>
			<td class="table-right">
				<input class="" type="checkbox" id="enabledstate" name="enabledstate" value="1" checked="checked">
				<label for="roleflag"><fmt:message key="enabled"/></label>
				</td>
		</tr>
		<tr>
			<td class="table-left"><label for="roleremark"><fmt:message key="remarks"/></label>：</td>
			<td class="table-right"><textarea  rows="5" cols="21" class="pop-textarea" id="roleremark" name="roleremark" rows="2" style="height:50px;" maxlength="256"></textarea></td>
		</tr>
	</table>
</form>
