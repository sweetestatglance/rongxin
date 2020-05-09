<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备列表</title>
<style>
.bg_td{
   color:white;
   background-color: #44c4e7;
}
</style>
</head>

<body>
  <div class="stbprpbList" >
	<c:if test="${fn:length(stbprpbList)>0}">
		<table border="0" style="font-size:12px;text-align:center;border-collapse:collapse;cursor:pointer;" cellpadding="8" cellspacing="10">
			<c:forEach var="item" items="${stbprpbList}" varStatus="vs">
				<c:if test="${vs.count eq 1 || (vs.count-1) % 3 eq 0}">
					<tr>
				</c:if>
				
				<td width="33%" <c:if test="${stcd==item.stcd}">class="bg_td"</c:if> style="border:1px solid #666;" onclick="stbprpImgChange('${item.stcd}','${item.stnm}','${item.sttp}')">${item.stnm}</td>
				<c:if test="${vs.count % 3 eq 0 || vs.count eq 3}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${fn:length(stbprpbList)==0}">
		<font color="#a8a8a8"> <label style="float:left;padding-left:15px"><fmt:message key="noData"/></label></font>
	</c:if>
	</div>
</body>
</html>
