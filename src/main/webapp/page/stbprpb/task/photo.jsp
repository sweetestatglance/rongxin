<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>拍照</title>
    <style>
     .dotline {
			BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted
       }
    </style>
  </head>
  
  <body>
     <input type="hidden" id="deviceId" name="stcd" value="${stcd}"></input>
     <div style="height:50px;">
	     <table border="0" cellspacing="1" id="photoTable" cellpadding="3" class="pop-table" style="font-size:14px;">
	     <tr>
	      	<td class="table-left" style="width: 70px;"><label for="centerAddress"><fmt:message key="channel"/>1</label>： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo1" name="photo1" value="01" ></input>
	          </td>
	          <td class="table-left" style="width: 70px;"><label for="pwd"><fmt:message key="channel"/>2</label>： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo2" name="photo2" value="02"></input>
	          </td>
	      	  <td class="table-left" style="width: 70px;"><label for="centerAddress"><fmt:message key="channel"/>3</label>： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo3" name="photo3" value="03"></input>
	          </td>
	          <td class="table-left" style="width: 70px;"><label for="pwd"><fmt:message key="channel"/>4： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo4" name="photo4" value="04"></input>
	          </td>
	            <c:if test="${flag=='task'}">
			        <td>
			        	<button type="button"  style="margin-left:50px;"  class="homebg popup-confirm"  style="color:#ffffff"  onclick="photoSave()">保存</button>
			        </td>
	      		</c:if>
	      </tr>
	      </table>
      </div>
      <div style="margin-top: 15px;" id="photolist"><%-- <jsp:include page="${ctx}/page/stbprpb/task/config/photolist.jsp"></jsp:include> --%></div>
   <%--    <c:if test="${flag=='task'}">
       <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" /></label>
       <div style="margin-top: 80px;"><jsp:include page="${ctx}/page/stbprpb/task/config/photolist.jsp"></jsp:include></div>
      </c:if> --%>
  </body>
</html>
