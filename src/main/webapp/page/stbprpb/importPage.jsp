<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>批量导入设备</title>
	<meta http-equiv="pragma" content="no-cache">
	<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/jquery-form.js"></script>
  </head>
  
  <body>
   <!-- 文件上传 采用ajax -->
   <div id="upload">
	   <form name="submitForm"  id="submitForm"  method="POST" enctype="multipart/form-data">
	      <table border="0" cellspacing='10' cellpadding='3'>
	        <tr>
	         <td><input type="file" name="file" id="file" accept="application/vnd.ms-excel"></input></td>
	         <td colspan="2"></td>
	        </tr>
		    <tr>
		      <td colspan="3"><a href="stStbprpB/downLoadFile.do"><font color="red"><fmt:message key="downloadTemplate"/></font></a></td>
		    </tr>
		     <tr>
		      <td colspan="3">
		         <input class="homebg popup-confirm" value="<fmt:message key="determine"/>" type="button" onclick="ajaxFileUpload('${addvcdId}')">
		         <input class="homebg popup-cancel"  value="<fmt:message key="close"/>" type="button" onclick="closeImportPage()">
		      </td>
		    </tr>
		 </table>
		 </form>
   </div>  
     <div id="view" style="display: none;padding-top:28px">
        <div id="viewContent">
        </div>
        </br>
        <input class="homebg popup-confirm"   onclick="returnUpload()" value="<fmt:message key="return"/>" type="button" style="margin-left: 50px; border: none;">
        <input class="homebg popup-confirm"   onclick="closeUpload()" value="<fmt:message key="close"/>" type="button" style="margin-left: 50px; border: none;">
   </div>
  </body>
</html>
