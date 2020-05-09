<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="stModelForm">
    <input type="hidden" id="pmid" name="pmid" value="${pId}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
      <tr> 
         <td class="table-left"><label for="name"><fmt:message key="remarks" /><fmt:message key="name" /></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="name" name="name" maxlength="25">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="remark"><fmt:message key="remarks" /></label>： </td>
          <td class="table-right">
            <textarea rows="5" cols="21" class="pop-textarea" id="remark" name="remark" maxlength="200"></textarea>
          </td>
      </tr>
	</table>
</form>
