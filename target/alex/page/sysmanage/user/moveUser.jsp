<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><fmt:message key="moveUser"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<style>
		.treeArea {
			width: 250px;
			margin: 20px 0 20px 50px;
			color: #ffffff;
		}
	</style>

  </head>
  
  <body>
      <div class="treeArea">
                    <ul id="ztreeMoveUser" class="ztree" style="max-height: 350px;overflow:auto;"></ul>
      </div>
      
      <script type="text/javascript">
        $(document).ready(function(){  
	    	 ztreeFun($("#ztreeMoveUser"),"sysUser/moveUserfo.do?enterId=${enterId}",null);
	    }); 
      </script>
  </body>
</html>
