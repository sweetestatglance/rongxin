<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统管理首页</title>
</head>

<body>
      <!--二级头部  -->
	   <div class="header">
		 <jsp:include page="/twoHeader.jsp"></jsp:include> 
	   </div>
	   <!--内容  -->
	   <div id="twoContain"></div>
</body>
</html>
<script type="text/javascript">
    $(function () {
        $(".leftnav").click(function () {
            if ($(".left").css("display") == "none") {
                $(".leftnav").css("left", "247px"); $(".leftnav img").attr("src", "content/skin/css/images/botton-closeLeft.gif");
                $(".right").css("left", "275px");
            } else {
                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "content/skin/css/images/botton-closeRight.gif");
                $(".right").css("left", "20px");
            }
            $(".left").toggle();
            $(".left-bottom").toggle();
        });
    });
</script>
