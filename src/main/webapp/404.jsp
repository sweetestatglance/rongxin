<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equivmetahttp-equiv="x-ua-compatible"content="IE=7"/> 
<head>
<title>404页面</title>
<link href="${ctx}/content/skin/css/core/core.css" rel="stylesheet" type="text/css" />
</head>

<body style="background:url(${ctx}/content/skin/css/images/bj.jpg) no-repeat top center #e8e8e8">
	<div class="wrapPage">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="138" valign="top"><img src="${ctx}/content/skin/css/images/404.jpg" width="416" height="138" /></td>
			</tr>
			<tr>
				<td height="93">抱歉，您访问的页面不存在……</td>
			</tr>
			<tr>
				<td><a href="<%=basePath%>"><input name="" type="button" class="buttonpage" value="返回主页" /></a></td>
			</tr>
		</table>

	</div>
</body>
</html>
