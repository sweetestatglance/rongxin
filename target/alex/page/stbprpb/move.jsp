<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>移动设备分组</title>
    
	<meta http-equiv="pragma" content="no-cache">

  </head>
  
  <body>
      <input type="hidden" id="dids" name="dids" value="${dids}"></input>
      <div class="device-tree" style="float: right">
                <ul id="ztreeMove" class="ztree" style="max-height: 350px;overflow:auto;"></ul>
      </div>
      
      <script type="text/javascript">
        $(document).ready(function(){  
	    	 ztreeFun($("#ztreeMove"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=false",null);
	    }); 
      </script>
  </body>
</html>
