<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备型号</title>

  </head>
  
  <body>
    	<div class="leftTree" style="padding-left:20px;height:300px;overflow-y:auto; overflow-x:hidden;">
			<ul id="zModelTree" class="ztree"></ul>
		</div>
  </body>
</html>
<script type="text/javascript">
$(function () {
   // ztreeFun($("#ztree"),"stModel/list.do?enterpriseid=${enterpriseid}",null);
  //设备型号
   modelTreeInit('${enterpriseid}');
});
</script>
