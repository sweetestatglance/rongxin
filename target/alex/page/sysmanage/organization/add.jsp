<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysOrganizationForm">
    <input type="hidden" id="enterpriseid" name="enterpriseid" value="${enterId}"></input>
    <input type="hidden" id="pid" name="pid" value="${organId}"></input>
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
	  <tr>
      	<td class="table-left"><label for="byid"><fmt:message key="parentOrgan"/></label>： </td>
          <td class="table-right">
          	${organName}
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="organcode"><fmt:message key="organCode"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="organcode" name="organcode" maxlength="32">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="organname"><fmt:message key="organName"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="organname" name="organname" maxlength="32">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="organremark"><fmt:message key="remarks"/></label>： </td>
          <td class="table-right">
            <textarea rows="5" cols="21" class="pop-textarea"  id="organremark" name="organremark" maxlength="256"></textarea>
          </td>
      </tr>
	</table>
</form>
