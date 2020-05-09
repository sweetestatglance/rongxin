<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="stAddvcdDForm">
    <input type="hidden" id="faddvcd" name="faddvcd" value="${pId}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
    <!--   <tr>
       <td class="table-left"><label for="addvcd">行政区划码</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="addvcd" name="addvcd" maxlength="6">
          </td>
      </tr> -->
      <tr> 
         <td class="table-left"><label for="addvnm"><fmt:message key="name"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="addvnm" name="addvnm" maxlength="50">
          </td>
      </tr>
<!--  <tr> 
         <td class="table-left"><label for="addnum">区号</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="addnum" name="addnum" maxlength="10">
          </td>
      </tr> -->
      
       <tr>
      	<td class="table-left"><label for="comments"><fmt:message key="remarks"/></label>： </td>
          <td class="table-right">
            <textarea rows="5" cols="21" class="pop-textarea" id="comments" name="comments" maxlength="200"></textarea>
          </td>
      </tr>
	</table>
</form>
