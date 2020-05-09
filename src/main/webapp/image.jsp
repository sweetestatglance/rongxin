<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/adapters/viewer/viewer.min.css" />
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/viewer/viewer.min.js"></script>
<style>
* { margin: 0; padding: 0;}
#dowebok { width: 700px; margin: 0 auto; font-size: 0;}
#dowebok li { display: inline-block; width: 32%; margin-left: 1%; padding-top: 1%;}
#dowebok li img { width: 100%;}
</style>
</head>

<body>
	<ul id="dowebok">
		<li><img data-original="http://192.168.8.251:9090/sl_photo/01.jpg" src="http://192.168.8.251:9090/sl_photo/06.jpg" alt="图片1"></li>
		<li><img data-original="http://192.168.8.251:9090/sl_photo/02.jpg" src="http://192.168.8.251:9090/sl_photo/02.jpg" alt="图片2"></li>
		<li><img data-original="http://192.168.8.251:9090/sl_photo/02.jpg" src="http://192.168.8.251:9090/sl_photo/03.jpg" alt="图片3"></li>
		<li><img data-original="http://192.168.8.251:9090/sl_photo/04.jpg" src="http://192.168.8.251:9090/sl_photo/04.jpg" alt="图片4"></li>
		<li><img data-original="http://192.168.8.251:9090/sl_photo/05.jpg" src="http://192.168.8.251:9090/sl_photo/05.jpg" alt="图片5"></li>
		<li><img data-original="http://192.168.8.251:9090/sl_photo/06.jpg" src="http://192.168.8.251:9090/sl_photo/06.jpg" alt="图片6"></li>
	</ul>
</body>
</html>
<script>
$(function(){
	$('#dowebok').viewer({
	  /*   url: 'data-original', */
	    
	});
});
</script>
