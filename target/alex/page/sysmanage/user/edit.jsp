<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="sysUserForm">
   <input type="hidden" name="id" id="id" value="${model.id }">
   <input type="hidden" name="usercode" id="usercode" value="${model.usercode }"></input>
   <input type="hidden" id="username" name="username" value="${model.username}"></input>
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
       <tr>
      	<td class="table-left"><label for="enterpriseid"><fmt:message key="byEnterprise"/></label>： </td>
          <td class="table-right">
          	<input type="hidden" id="enterpriseid" name="enterpriseid" value="${enterprise.id}">
          	           ${enterprise.enterprisename}
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="organizationid"><fmt:message key="byOrganization"/></label>： </td>
          <td class="table-right">
          	<input type="hidden" id="organizationid" name="organizationid" value="${organization.id}">
          	           ${organization.organname}
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="usercode"><fmt:message key="userCode"/></label>： </td>
          <td class="table-right">
               ${model.usercode }
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="username"><fmt:message key="userName"/></label>： </td>
          <td class="table-right">
               ${model.username}
          </td>
      </tr>
      <tr>
			<td class="table-left"><label for="enabledstate"><fmt:message key="userState"/></label>：</td>
			<td class="table-right">
				<input class="" type="checkbox" id="enabledstate" name="enabledstate" value="1" <c:if test="${model.enabledstate==1}">checked="checked"</c:if>>
				<label for="userflag"><fmt:message key="enabled"/></label>
				</td>
		</tr>
      <tr>
      	<td class="table-left"><label for="usertel"><fmt:message key="phoneNumber"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="usertel" name="usertel" value="${model.usertel}" maxlength="32">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="useremail"><fmt:message key="email"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="useremail" name="useremail" value="${model.useremail}" maxlength="32">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="userremark"><fmt:message key="remarks"/></label>： </td>
          <td class="table-right">
          	<textarea rows="5" cols="21" class="pop-textarea" id="userremark" name="userremark" rows="2" style="height:50px;" maxlength="256">${model.userremark}</textarea>
          </td>
      </tr>
	</table>
</form>
