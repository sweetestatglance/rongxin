<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>未授权页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
       #box{width:650px; height:370px; margin-left:-512px; margin-top:-215px; position:absolute; left:50%; top:50%; background:url(${ctx}/content/skin/css/images/licensing.png) no-repeat; font-family:微软雅黑; font-size:20px; color:#333; padding-left:374px; padding-top:61px; line-height:40px; }
    </style>

  </head>
  
  <body>
      <div id="box">您访问的地址未授权，或者授权期限已经过期了，<br />请联系我们<span style="color:#ec6d06">400-8838-199</span>
  </body>
</html>
