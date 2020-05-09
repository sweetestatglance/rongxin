<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色树</title>
<meta http-equiv="pragma" content="no-cache">
<style type="text/css">
.treeRoleInfo {
	width: 250px;
	margin: 20px 0 20px 50px;
	color: #ffffff;
}
</style>
</head>

<body>
	<div>
		<div class="treeRoleInfo" style="height:480px;width:430px;overflow-y:auto; overflow-x:auto; ">
			<ul id="roleInfoTree" class="ztree"></ul>
		</div>
	</div>
	<script type="text/javascript">
		   $(function(){
			   roleTreeInit('${userId}','${userName}','${enterId}');
		   });
	</script>

</body>
</html>
