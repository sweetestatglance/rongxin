<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysMenuForm">
     <input type="hidden"  id="pid" name="pid" value="${parentMenuId}">
     <input type="hidden"  id="menulevel" name="menulevel" value="${level}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
       <tr>
      	<td class="table-left"><label for="byid"><fmt:message key="parentMenu"/></label>： </td>
          <td class="table-right">
          	 ${menuName}
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menucode"><fmt:message key="menuCode"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menucode" name="menucode" maxlength="32">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menuname"><fmt:message key="menuName"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuname" name="menuname" maxlength="32">
          </td>
      </tr>
      <tr>
      	<td class="table-left"><label for="enabledstate">菜单状态</label>： </td>
          <td class="table-right">
          	 <input type="checkbox" id="enabledstate" name="enabledstate" value="1" checked="checked"></input>
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menuorder"><fmt:message key="menuOrder"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuorder" name="menuorder" value="${order}">
          </td>
      </tr>
      <tr>
      	<td class="table-left"><label for="menuurl"><fmt:message key="menuLink"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuurl" name="menuurl" maxlength="128">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menuicon"><fmt:message key="menuIcon"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuicon" name="menuicon">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menuremark"><fmt:message key="remarks"/></label>： </td>
          <td class="table-right">
            <textarea rows="5" cols="21" class="pop-textarea" id="menuremark" name="menuremark" maxlength="256"></textarea>
          </td>
      </tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
	  //$('.combobox').combobox();
	});
</script>