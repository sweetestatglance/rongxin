<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=9"/>
<title>水利信息化应用管理平台</title>
<link href="${ctx}/content/skin/css/core/core.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/content/skin/css/core/popup.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/adapters/pagination/pagination_blue.css" />
<link href="${ctx}/content/skin/adapters/bootstrap/bootstrap-select.css" rel="stylesheet" />
<link href="${ctx}/content/skin/adapters/ztree/css/zTreeStyle.css" rel="stylesheet" />
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b6c49003c13af335bfa5f923966cbde1"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.i18n.properties.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.easydropdown.min.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.validate.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/UI/jquery.SLWeb.MapHelper.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/UI/jquery.SLWeb.PopupHelper.js"></script>
<script src="${ctx}/content/skin/adapters/ztree/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/content/skin/adapters/ztree/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/content/skin/adapters/ztree/jquery.ztree.exedit-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/content/skin/adapters/ztree/jquery.ztree.exhide-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/common.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/ztree.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/js/validate.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/pagination/pagination.js"></script>
<script src="${ctx}/content/skin/adapters/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/content/skin/adapters/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/ajaxFontFun.js"></script>

<%-- <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/echarts/echarts.min.js"></script> --%>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/echarts/echarts.common.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/echarts/newline-echarts.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.widgets.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.gantt.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/adapters/fusioncharts/fusioncharts.theme.fint.js"></script>
</head>
   <div class="popup"></div>
   <div class="mask"></div>
   <div>
      <div class="header">
		 <jsp:include page="/header.jsp"></jsp:include> 
	  </div>
	   <!--内容  -->
	   <div id="contain"></div>
   </div>
<body>
</body>
</html>

<script>
	var isIE8;
</script>
<!--[if IE 8]> 
<script> var isIE8=true;</script>
 <![endif]-->
 
<script type="text/javascript">
    $(function () {
        $(".leftnav").click(function () {

            if ($(".left").css("display") == "none") {
                $(".leftnav").css("left", "247px"); $(".leftnav img").attr("src", "${ctx}/content/skin/images/botton-closeLeft.gif");
                $(".right").css("left", "275px");
            } else {
                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "${ctx}/content/skin/images/botton-closeRight.gif");
                $(".right").css("left", "20px");
            }
            $(".left").toggle();
            $(".left-bottom").toggle();
        });
    });
</script>
