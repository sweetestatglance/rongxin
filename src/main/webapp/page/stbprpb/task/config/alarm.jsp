<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>报警设定</title>
    <meta http-equiv="pragma" content="no-cache">
  </head>
  
  <body>
  <form id="alarmForm">
   <input type="hidden" id="stcdId" name="stcdId" value="${stcdId}"></input>
   <input type="hidden" id="updateAlarmForm" name="updateAlarmForm"></input>
     <table border="0" cellspacing="10" cellpadding="3" class="pop-detail">
		<tr>
			<c:forEach items="${factoryMap}" var="factoryMap" varStatus="vs">
				<td class="pop-statusdetail-title">${factoryMap.key}：</td>
				<td class="pop-statusdetail-input">${factoryMap.value}</td>
				<!-- 2列换一行 -->
				<c:if test="${vs.count%2==0}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
   </form>
   <script type="text/javascript">
   $(function() { //监听表单input元素的值变化
   	var arrayObj = new Array();
   	$(":input").change(function (){ 
   		var id = $(this).attr("id");
   		var value = $(this).val();
   		var contant = $.inArray(id, arrayObj); 
   		if(contant==-1){
   			arrayObj.push(id);
   		}
   		$("#updateAlarmForm").attr("value",arrayObj);
   		$('#alarmForm').data('changed',true);
   	});
	});
   
   </script>
  </body>
</html>
