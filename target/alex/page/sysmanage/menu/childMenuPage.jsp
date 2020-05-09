<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>子菜单管理</title>
<style type="text/css">
.treeMenu {
	width: 275px;
	margin: 50px 0 0 50px;
	color: #ffffff;
}
</style>
</head>

<body>
	<div>
		<div class="operate" style="background:url() repeat-x scroll 0% 0%">
			<ul>
				<li class="add" onclick="addChildMenu('${menuId}')">新增</li>
				<li class="remote" onclick="editChildMenu('${menuId}')">修改</li>
				<li class="del" onclick="delChildMenu('${menuId}')">删除</li>
			</ul>
		</div>
	</div>

	<div class="treeMenu">
		<ul id="menuTree" class="ztree"></ul>
	</div>
	<script type="text/javascript">
           </script>
</body>
<script type="text/javascript">
	    $(document).ready(function(){  
	    	 ztreeFun($("#menuTree"),"sysMenu/getChildMenuList.do?menuId=${menuId}",null);
	   }); 
	</script>
</html>
