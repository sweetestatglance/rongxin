<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>运维监控首页</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/operation.js"></script>
</head>

<body>
	<div class="left">
		<div class="tree">
			<ul id="ztree" class="ztree"
				style="min-height: 450px;padding-top:5px;"></ul>
		</div>
	</div>
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right_type">
	   <ul class="show-type">
	       <li class="sell">接入状态</li>
	       <li>维护记录</li>
	       <li>信息推送</li>
	    </ul>
	</div>
    <div class="right_user" id="operationContent"></div> 
</body>
</html>
<script type="text/javascript">
    $(function () {
    	ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=false&showOnLineDevice=false",operationList);
        $(".leftnav").click(function () {

            if ($(".left").css("display") == "none") {
                $(".leftnav").css("left", "247px"); $(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
                $(".right").css("left", "275px");
            } else {
                $(".leftnav").css("left", "0px"); $(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
                $(".right").css("left", "20px");
            }
            $(".left").toggle();
            $(".left-bottom").toggle();
        });
    });
    function operationList(node){
	    var params = {
	  			id:node.id
		};
	  	loadOperationList(params,false);
   }
</script>
