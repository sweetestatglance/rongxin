<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">
<!--
 $(function(){
	 var bTop = $(".map_button").position().top;
	 var bHeight = $(".map_button").height();
	 $(".map_right").css("top",bTop+bHeight-10);
 })
//-->
</script>

<script type="text/javascript" src="${ctx}/content/skin/js/module.js"></script>
	<input type="hidden" id="stcd" value="${stcd}">
	<input type="hidden" id="topNodeId" value="${topNodeId}">
	<div class="map_button" onclick="changeListPage()" style="top: 40%;z-index:2">
		列<br>表<br>数<br>据
	</div>
	
	<div class="map_right" style="display:none;top:485px;">
	<div class="map_right_top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody><tr>
				<td width="98%" style="font-size:14px; font-weight:bold">列表数据</td>
				<td width="2%"><img src="/FF_SSW/content/skin/css/images/close.png" width="10" onclick="changeListPage()" style="cursor: pointer;" height="10"></td>
			</tr>
		</tbody></table>
	</div>
	
	<div class="map_right_content" style="background: #E9F5FD;"> </div>
	</div>
	
	<div class="right_home">
		<DIV id=con>
			<UL id=tags>
				<LI class=selectTag><A onClick="selectTag('tagContent0',this)"
					href="javascript:void(0)">站点数据</A></LI>
				<LI><A onClick="selectTag('tagContent1',this)"
					href="javascript:void(0)">智能报表</A></LI>
				<LI><A onClick="selectTag('tagContent2',this)"
					href="javascript:void(0)">站点信息</A></LI>
			</UL>
			<DIV id=tagContent>
				<jsp:include page="siteData.jsp"></jsp:include>
				<jsp:include page="siteReport.jsp"></jsp:include>
				<jsp:include page="siteInfo.jsp"></jsp:include>
			</DIV>
		</DIV>

		<SCRIPT type=text/javascript>
			function selectTag(showContent, selfObj) {
				// 操作标签
				var tag = document.getElementById("tags").getElementsByTagName(
						"li");
				var taglength = tag.length;
				for (i = 0; i < taglength; i++) {
					tag[i].className = "";
				}
				selfObj.parentNode.className = "selectTag";
				// 操作内容
				for (i = 0; j = document.getElementById("tagContent" + i); i++) {
					j.style.display = "none";
				}
				document.getElementById(showContent).style.display = "block";

			}
		</SCRIPT>

	</div>
