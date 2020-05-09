<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="stModelForm">
    <input type="hidden" name="id" id="id" value="${stModel.id}">
    <input type="hidden" id="pmid" name="pmid" value="${stModel.pmid}">
    <input type="hidden" id="enterpriseid" name="enterpriseid" value="${stModel.enterpriseid}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
      <tr> 
         <td class="table-left"><label for="name"><fmt:message key="name" /></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="name" name="name" value="${stModel.name}" maxlength="25">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="remark"><fmt:message key="remarks" /></label>： </td>
          <td class="table-right">
          	<textarea rows="5" cols="21" class="pop-textarea" id="remark" name="remark" maxlength="200">${stModel.remark}</textarea>
          </td>
      </tr>
	</table>
</form>
