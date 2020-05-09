<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<%
	String inputId = "query_Hour";
	String str = request.getParameter("inputId");
	if (str != null && !"".equals(str.trim())){
		inputId = str;
	}
	String index = "0";
	String indexStr = request.getParameter("index");
	if (indexStr != null && !"".equals(indexStr.trim())){
		index = indexStr;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Hour Select Page</title>
</head>

<body>
	<!-- Split button -->
	<div class="btn-group" style="height:26px;">
		<button id="<%=inputId%>" type="button" class="btn btn-xs btn-default" style="padding: 0px 5px;font-size: 13px;height:25px;width:40px;" data-toggle="dropdown" value="" ></button>
		<button type="button" style="height:25px;" class="btn btn-xs btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" style="height: 200px;width:85px;overflow-y: auto;padding: 0 0;">
			<c:forEach begin="0" end="23" var="item">
				<li><a href="javascript:void(0)" onclick="changeValue('${item}','<%=inputId%>')" >${item}<fmt:message key="hour"/></a></li>
				<li role="separator" class="divider" style="margin:1px 0"></li>
			</c:forEach>
		</ul>
	</div>
</body>

<script type="text/javascript">
	
	/**
	 * 点击选择项
	 */
	function changeValue(value,inputId) {
		$("#"+inputId).val(value);
		$("#"+inputId).text(value+"时");
	}
	
	$(function(){
		var inputId = '<%=inputId%>';
		var index = '<%=index%>';
		$("#"+inputId).nextAll().find("li a:eq(" + index +  ")").click();
	});
	
</script>

</html>