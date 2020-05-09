<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设备状态查询页面</title>
<style>
.pop-statusdetail-title {
	width: 100px;
	text-align: right;
}

.pop-statusdetail-input {
	height: 25px;
	text-align: left;
	word-wrap: break-word;
	word-break: break-all;
}
</style>

</head>

<body>
	<table border="0" cellspacing="10" cellpadding="3" class="pop-detail">
		<tr>
			<c:forEach items="${factoryMap}" var="factoryMap" varStatus="vs">
				<td class="pop-statusdetail-title">${factoryMap.key}：</td>
				<td class="pop-statusdetail-input" style="width: 120px;">${factoryMap.value}</td>
				<!-- 3列换一行 -->
				<c:if test="${vs.count%3==0}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>
