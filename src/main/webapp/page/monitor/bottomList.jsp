<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div class="tab_bottom">
	<div id="con">
		<ul id="tags">
			<li class="selectTag"><a onclick="selectTag('tagContent0',this)"
				href="javascript:void(0)"><fmt:message key="basic"/></a></li>
			<li class="" id="bottomAlarm" style="display:none"><a onclick="selectTag('tagContent1',this)"
				href="javascript:void(0)"><fmt:message key="alarmInformation"/></a></li><!--  <span class="badge">42</span> -->
			<li class="" id="bottomNotice" style="display:none"><a onclick="selectTag('tagContent2',this)"
				href="javascript:void(0)"><fmt:message key="officialNews"/></a></li>
		</ul>
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0" style="display: block;">
				<jsp:include page="deviceList.jsp"></jsp:include>
			</div>
			<div class="tagContent" id="tagContent1" style="display: none;">
				<jsp:include page="alarmList.jsp"></jsp:include>
			</div>
			<div class="tagContent" id="tagContent2" style="display: none;">
				<jsp:include page="announceList.jsp"></jsp:include>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		$(function(){
			
			if($("a.notice_a").length>0) {
				$(".tab_bottom #bottomNotice").show();
			}
			if($("a.alarm_a").length>0){
				$(".tab_bottom #bottomAlarm").show();
			}
			
		})	
	
		function selectTag(showContent, selfObj) {
			// 操作标签
			var tag = document.getElementById("tags")
					.getElementsByTagName("li");
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
	</script>
	<div class="shrin shrin_transform">
		<img src="content/skin/css/images/map/shrin.png" width="10" height="13">
	</div>
</div>