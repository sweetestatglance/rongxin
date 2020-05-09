<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="imageTitle"/></title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/image.js"></script>
<link href="${ctx}/content/skin/css/zoomify/bootstrap-grid.min.css" rel="stylesheet" />
<link href="${ctx}/content/skin/css/zoomify/zoomify.min.css" rel="stylesheet" />
<link href="${ctx}/content/skin/css/zoomify/image.css" rel="stylesheet" />
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/zoomify.min.js"></script>
</head>

<body>
	<div class="left">
		<div class="tree">
			<ul id="ztree" class="ztree"  style="position: absolute;top: 0px; bottom: 5px;width: 232px; overflow-y: auto;overflow-x: auto;" ></ul>
		</div>
	</div>
	<div class="leftnav">
		<a href="javascript:void(0)"> <img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" /></a>
	</div>
	<div class="right" style="background:#fff;border:1px solid #e0dede">
		<div style="height:50px;padding-top:10px;padding-left:20px;">
			<div>
				<fmt:message key="siteType"/>： <select id="sttp" class="dropdown" onchange="searchImg()" style="height:23px">
					<option value="" class="label"><fmt:message key="all"/></option>
					<option value="3"><fmt:message key="sttp3"/></option>
					<option value="0"><fmt:message key="sttp0"/></option>
					<option value="1"><fmt:message key="sttp1"/></option>
					<option value="2"><fmt:message key="sttp2"/></option>
					<option value="4"><fmt:message key="sttp4"/></option>
				</select> &nbsp;&nbsp;&nbsp;&nbsp; 
				
					<input type="text" style="color:#adadad;width:148px; height:19px; border:1px solid #d5d5d5" value="${stcd==null?'':stcd}" id="query_stcdName" value="<fmt:message key="codeOrName"/>"
					onfocus="if(this.value == '<fmt:message key="codeOrName"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="codeOrName"/>';" /> <input name="" type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="searchImg()" />
			</div>
		</div>
		<div id="imageContent">
			
		</div>

	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$("#query_stcdName").blur();
		ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true&showOnLineDevice=false",imgList);
		$(".leftnav").click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "247px");
				$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
				$(".right").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
				$(".right").css("left", "20px");
			}
			$(".left").toggle();
			$(".left-bottom").toggle();
		});
	});
   function imgList(node){
	    var aryIds = getParentNodeIdsByTreeId("ztree");
	    var query_stcdName = $("#query_stcdName").val();
		if(query_stcdName=="<fmt:message key='codeOrName'/>"){
			query_stcdName = "";
		}
		var params = {
			sttp: $("#sttp").val(),
			nodeIds : aryIds.toString(),
			query_stcdName:encodeURI(query_stcdName),
			isDevice: node.isDevice
			
		};
	  	loadImageList(params,false);
   }
</script>