<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备远程配置</title>
	<meta http-equiv="pragma" content="no-cache">

  </head>
  
  <body>
  <form id="distanceForm">
  <input type="hidden" id="groupId" name="groupId" value="${ids}"></input>
  <input type="hidden" id="updateForm" name="updateForm"></input>
    <table border="0"  style="width:820px;" cellspacing="20" cellpadding="3" class="pop-table" >
       <tr>
      	<td class="table-left"><label for="centerAddress"><fmt:message key="centralStationAddress"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="centerAddress" name="centerAddress" value="${TaskDistance.centerAddress}">
          </td>
          <td class="table-left"><label for="pwd"><fmt:message key="communicationPassword"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="pwd" name="pwd" value="${TaskDistance.pwd}">
          </td>
      </tr>
      <tr>
          <td class="table-left"><label for="number"><fmt:message key="communicationNumber"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="number" name="number" value="${TaskDistance.number}">
          </td>
          <td class="table-left"><label for="workModel"><fmt:message key="workingMode"/></label>： </td>
          <td class="table-right">
          	   <select class="pop-select" id="workModel" name="workModel">
          	     <option value="0"></option>
                 <option value="1" <c:if test="${'1' eq TaskDistance.workModel}">selected</c:if>><fmt:message key="selfReport"/></option>
                 <option value="2" <c:if test="${'2' eq TaskDistance.workModel}">selected</c:if>><fmt:message key="compatible"/></option>
                 <option value="3" <c:if test="${'3' eq TaskDistance.workModel}">selected</c:if>><fmt:message key="query"/></option>
                 <option value="4" <c:if test="${'4' eq TaskDistance.workModel}">selected</c:if>><fmt:message key="debugging"/></option>
               </select>
          </td>
      </tr>
      <tr>
      	<td class="table-left"><label for="main1Type"><fmt:message key="centralStation"/>1<fmt:message key="mainChannelType"/></label>： </td>
          <td class="table-right">
               <select class="pop-select" id="main1Type" name="main1Type" onchange="main1Change()">
          	     <option value="0" <c:if test="${'0' eq TaskDistance.main1Type}">selected</c:if>><fmt:message key="disabled"/></option>
                 <option value="2" <c:if test="${'2' eq TaskDistance.main1Type}">selected</c:if>>IPV4</option>
               </select>
          </td>
          <td class="table-left"><label for="main1Address"><fmt:message key="centralStation"/>1<fmt:message key="mainChannelAddress"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="main1Address" name="main1Address" value="${TaskDistance.main1Address}">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="main2Type"><fmt:message key="centralStation"/>2<fmt:message key="mainChannelType"/></label>： </td>
          <td class="table-right">
          	  <select class="pop-select" id="main2Type" name="main2Type" onchange="main2Change()">
          	     <option value="0" <c:if test="${'0' eq TaskDistance.main2Type}">selected</c:if>><fmt:message key="disabled"/></option>
                 <option value="2" <c:if test="${'2' eq TaskDistance.main2Type}">selected</c:if>>IPV4</option>
               </select>
          </td>
          <td class="table-left"><label for="main2Address"><fmt:message key="centralStation"/>2<fmt:message key="mainChannelAddress"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="main2Address" name="main2Address" value="${TaskDistance.main2Address}">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="main3Type"><fmt:message key="centralStation"/>3<fmt:message key="mainChannelType"/></label>： </td>
          <td class="table-right">
          	  <select class="pop-select" id="main3Type" name="main3Type" onchange="main3Change()">
          	     <option value="0" <c:if test="${'0' eq TaskDistance.main3Type}">selected</c:if>><fmt:message key="disabled"/></option>
                 <option value="2" <c:if test="${'2' eq TaskDistance.main3Type}">selected</c:if>>IPV4</option>
               </select>
          </td>
          <td class="table-left"><label for="main3Address"><fmt:message key="centralStation"/>3<fmt:message key="mainChannelAddress"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="main3Address" name="main3Address" value="${TaskDistance.main3Address}">
          </td>
      </tr>
      <tr>
      	<td class="table-left"><label for="main4Type"><fmt:message key="centralStation"/>4<fmt:message key="mainChannelType"/></label>： </td>
          <td class="table-right">
          	  <select class="pop-select" id="main4Type" name="main4Type" onchange="main4Change()">
          	     <option value="0" <c:if test="${'0' eq TaskDistance.main4Type}">selected</c:if>><fmt:message key="disabled"/></option>
                 <%-- <option value="1" <c:if test="${'1' eq TaskDistance.main4Type}">selected</c:if>>短信</option> --%>
                 <option value="2" <c:if test="${'2' eq TaskDistance.main4Type}">selected</c:if>>IPV4</option>
                 <%-- <option value="3" <c:if test="${'3' eq TaskDistance.main4Type}">selected</c:if>>北斗</option>
                 <option value="4" <c:if test="${'4' eq TaskDistance.main4Type}">selected</c:if>>海事卫星</option>
                 <option value="5" <c:if test="${'5' eq TaskDistance.main4Type}">selected</c:if>>PSTN</option>
                 <option value="6" <c:if test="${'6' eq TaskDistance.main4Type}">selected</c:if>>超短波</option> --%>
               </select>
          </td>
          <td class="table-left"><label for="main4Address"><fmt:message key="centralStation"/>4<fmt:message key="mainChannelAddress"/></label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="main4Address" name="main4Address" value="${TaskDistance.main4Address}">
          </td>
      </tr>
      <tr>
        <td colspan="4" align=center>
        	<button type="button"  class="homebg popup-confirm"  style="color:#ffffff"  onclick="saveDistance()"><fmt:message key="issued"/></button>
	    	<!-- <button type="button"  class="homebg popup-cancel"   style="color:#ffffff"  onclick="closeImportPage()">取消</button> -->
        </td>
      </tr>
    </table>
    </form>
    <script>
    $(function() { //监听表单input元素的值变化
    	var arrayObj = new Array();
    	$(":input").change(function (){ 
    		var id = $(this).attr("id");
    		var value = $(this).val();
    		var contant = $.inArray(id, arrayObj); 
    		if(contant==-1){
    			arrayObj.push(id);
    		}
    		$("#updateForm").attr("value",arrayObj);
    		$('#distanceForm').data('changed',true);
    	});
    	var main1Type  = $("#main1Type").val();
    	var main2Type  = $("#main2Type").val();
    	var main3Type  = $("#main3Type").val();
    	var main4Type  = $("#main4Type").val();
    	if(main1Type==0){
    		$("#main1Address").attr("readonly","readonly");
    	}
    	if(main2Type==0){
    		$("#main2Address").attr("readonly","readonly");
    	}
    	if(main3Type==0){
    		$("#main3Address").attr("readonly","readonly");
    	}
    	if(main4Type==0){
    		$("#main4Address").attr("readonly","readonly");
    	}
    	
	});
    function main1Change(){
    	var main1Type  = $("#main1Type").val();
    	if(main1Type==0){
    		$("#main1Address").attr("value","");
    		$("#main1Address").attr("readonly","readonly");
    	}else{
    		$("#main1Address").removeAttr("readonly");
    	}
    }
    function main2Change(){
    	var main2Type  = $("#main2Type").val();
    	if(main2Type==0){
    		$("#main2Address").attr("value","");
    		$("#main2Address").attr("readonly","readonly");
    	}else{
    		$("#main2Address").removeAttr("readonly");
    	}
    }
    function main3Change(){
    	var main3Type  = $("#main3Type").val();
    	if(main3Type==0){
    		$("#main3Address").attr("value","");
    		$("#main3Address").attr("readonly","readonly");
    	}else{
    		$("#main3Address").removeAttr("readonly");
    	}
    }
    function main4Change(){
    	var main4Type  = $("#main4Type").val();
    	if(main4Type==0){
    		$("#main4Address").attr("value","");
    		$("#main4Address").attr("readonly","readonly");
    	}else{
    		$("#main4Address").removeAttr("readonly");
    	}
    }
    </script>
  </body>
</html>
