<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysEnterpriseForm">
     <input type="hidden" name="id" id="id" value="${sysEnterprise.id}">
   
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
       <tr>
      	<td class="table-left"><label for="enterprisecode"><fmt:message key="enterpriseCode"/></label>： </td>
          <td class="table-right">
            <c:if test="${sysEnterprise.id==null}"><input class="pop-input-common" type="text" id="enterprisecode" name="enterprisecode" maxlength="32"></c:if>
          	<c:if test="${sysEnterprise.id!=null}">${sysEnterprise.enterprisecode }  <input type="hidden" name="enterprisecode" id="enterprisecode" value="${sysEnterprise.enterprisecode}"></c:if>
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterprisename"><fmt:message key="enterpriseName"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="enterprisename" name="enterprisename" maxlength="32"  value="${sysEnterprise.enterprisename }">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterprisetype"><fmt:message key="enterpriseType"/></label>： </td>
          <td class="table-right">
            <input type="radio" id="enterprisetype1" name="enterprisetype" value="1" checked="checked" onchange="viewPriseExpired(this.value)" <c:if test="${sysEnterprise.enterprisetype==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="enterpriseType_1"/></label></input>
            <input type="radio" id="enterprisetype2" name="enterprisetype" value="0" onchange="viewPriseExpired(this.value)" <c:if test="${sysEnterprise.enterprisetype==2}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="enterpriseType_0"/></label></input>
          </td>
      </tr>
       <tr style="display: none" id="priseexpired">
      	<td class="table-left" ><label for="expiredtime"><fmt:message key="enterpriseExpTime"/></label>： </td>
          <td class="table-right">
             <input id="expiredtime"  name="expiredtime" class="Wdate" type="text" value="${expiredTime}" onFocus="WdatePicker({maxDate:'\'2150-10-01\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterprisedevicenum"><fmt:message key="devAuthNum"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text"  placeholder="<fmt:message key='devAuthNumMsg'/>" id="enterprisedevicenum" name="enterprisedevicenum" value="${sysEnterprise.enterprisedevicenum }">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterprisesmsnum"><fmt:message key="msgNum"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="enterprisesmsnum" name="enterprisesmsnum" value="${sysEnterprise.enterprisesmsnum }">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterpriselinkman"><fmt:message key="contact"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="enterpriselinkman" name="enterpriselinkman" maxlength="32" value="${sysEnterprise.enterpriselinkman}">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterprisetel"><fmt:message key="contactTel"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="enterprisetel" name="enterprisetel" maxlength="32" value="${sysEnterprise.enterprisetel}">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterprisefax"><fmt:message key="fax"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="enterprisefax" name="enterprisefax" maxlength="32" value="${sysEnterprise.enterprisefax }">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="enterpriseflag"><fmt:message key="enterpriseState"/></label>： </td>
          <td class="table-right">
            <input type="checkbox"  id="enabledstate" name="enabledstate" value="1" <c:if test="${sysEnterprise.enabledstate==1}">checked="checked"</c:if>><fmt:message key="enabled"/></input>
          </td>
      </tr>
      
       <tr>
      	<td class="table-left"><label for="enterpriseremark"><fmt:message key="remarks"/></label>： </td>
          <td class="table-right">
            <textarea rows="5" cols="21" class="pop-textarea" id="enterpriseremark" name="enterpriseremark" maxlength="256">${sysEnterprise.enterpriseremark }</textarea>
          </td>
      </tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
		var type =$('input[name="enterprisetype"]:checked').val();
		if(type=="2"){
	       if(window.ActiveXObject)
			  $("#priseexpired").css("display","block");
           else
			  $("#priseexpired").css("display","table-row");
		}
	});
</script>
