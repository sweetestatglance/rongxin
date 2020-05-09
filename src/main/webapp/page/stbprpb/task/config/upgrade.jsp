<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>远程升级</title>
	<meta http-equiv="pragma" content="no-cache">

  </head>
  
  <body>
  <form id="upgradeForm">
  <input type="hidden" id="deviceId" name="deviceId" value="${ids}"></input>
    <table border="0" cellspacing="10" cellpadding="3" class="pop-table" >
       <tr>
      	  <td class="table-left"><label for="centerAddress"><fmt:message key="centerServerAddress"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="address" name="address">
          </td>
      </tr>
       <tr>
         <td class="table-left"><label for="pwd"><fmt:message key="centerListeningPort"/></label>： </td>
         <td class="table-right">
          	<input class="pop-input-common" type="text" id="port" name="port">
          </td>
       </tr>
       <tr>
      	 <td class="table-left"><label for="centerAddress"><fmt:message key="firmwareVersionFile"/></label>： </td>
         <td class="table-right">
          	<input class="pop-input-common" type="text" id="file" name="file">
          </td>
      </tr>
      <tr>
        <td colspan="2" align=center>
        	<button type="button"  class="homebg popup-confirm"  style="color:#ffffff"  onclick="upgradeDevice()"><fmt:message key="issued"/></button>
        </td>
      </tr>
    </table>
    </form>
  </body>
</html>
