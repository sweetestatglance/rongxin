<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户管理首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysUser.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysOrganization.js"></script>
</head>

<body>
<input type="hidden" id="enterId" value="${enterId}">
	<div class="left">
		<div class="orgTitle" style="padding-top:35px;">
			<ul>
				<li class="add" onclick="addOrgan('${enterId}')"><fmt:message key="added"/></li>
				<li class="remote" onclick="editOrgan('${enterId}')"><fmt:message key="edit"/></li>
				<li class="del" onclick="delOrgan('${enterId}')"><fmt:message key="delete"/></li>
			</ul>
		</div>
		<div class="tree" style="padding-left:0px;margin-top:75px;width:212px;overflow-x: auto;">
			<ul id="organTree" class="ztree"></ul>
		</div>
    </div>
	<div class="leftnav">
		<a href="javascript:void(0);"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right_user" id="userRight"></div> 
</body>
</html>
<script type="text/javascript">
    $(function () {
    	 ztreeFun($("#organTree"),"sysOrganization/organListToUser.do?enterId=${enterId } ",userList);
        $(".leftnav").click(function () {
            if ($(".left").css("display") == "none") {
                $(".leftnav").css("left", "247px"); $(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
                $(".right_user").css("left", "275px");
            } else {
                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
                $(".right_user").css("left", "20px");
            }
            $(".left").toggle();
            $(".left-bottom").toggle();
        });
    });
</script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysUser.js"></script>