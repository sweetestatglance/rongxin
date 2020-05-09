<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/channelManage.js"></script>

<div class="right_user" style="left:15px; padding: 20px; padding-top: 10px; background: #fff; border: 1px solid #e0dede; margin-bottom: 10px; margin-left: 20px; margin-right: 20px;padding-bottom:30px;">
<form id="channelManage">
<input type="hidden" id="sfId" name="stFactorName.id" value="${factorNameId}"></input>
<input type="hidden" id="seId" name="stEnterFactor.id" value="${enterFactorId}"></input>

<div style="height: 35px; line-height: 35px;">
	<font size="4" style="font-weight: bold;float: left;"><fmt:message key="channel1To10"/></font>
	
	<div style="color: #40b5d6; float: left;font-size: 12px;">
		<span style="margin-left: 15px;"><fmt:message key="selected"/>: </span>
		<span id="selCount" style="margin-left: 5px;">0</span>
		<span style="">/10</span>
	</div>
	
	<span style="float: right;">
	<fmt:message key="recentlyUpdated"/> : <fmt:formatDate value="${tm}" pattern="yyyy-MM-dd HH:mm:ss" /></input>
	</span>
	
</div>
	
	<div style="margin-top: 10px; height: 35px; line-height: 35px;">
		<a style="color: #23b4dc; cursor: pointer;font-size: 15px;" href="javascript:void(0)" onclick="toggle('waterRain')" ><fmt:message key="waterRain"/></a>
		<img id="waterRainImg" src="${ctx}/content/skin/css/images/arrow.png" style="padding-bottom: 3px; padding-left: 0px;"/>
	</div>
	
	<div id="waterRain" style="height:85px">
	
		<table border="0" cellspacing="5" cellpadding="3" class="pop-table">
		
			<c:forEach items="${enterFactor}" var="item" varStatus="vs" begin="0" end="7">
	        	
	        	<c:if test="${vs.index%5==0}">
		        	<tr>
	        	</c:if>
	        		<td style="text-align: left; width:20%;">
	        			<input type="checkbox" onclick="selCheck()" value="1" id="${item.key}" name="${item.key}" <c:if test="${item.value==1}">checked="checked"</c:if>>
	        			<label for="${item.key}" style="padding-left:2px">${factorNameMap[item.key]}</label>
	        		</td>
	        	
	        	<c:if test="${vs.index%5==4 || vs.index==7}">
		        	</tr>
	        	</c:if>
	        	
	        </c:forEach>
		</table>
	</div>
	
	<div style="height: 35px; line-height: 35px;">
		<a style="color: #23b4dc; cursor: pointer;font-size: 15px;" href="javascript:void(0)" onclick="toggle('weather')" ><fmt:message key="weatherStation"/></a>
		<img id="weatherImg" src="${ctx}/content/skin/css/images/arrow.png" style="padding-bottom: 3px; padding-left: 0px;"/>
	</div>
	
	<div id="weather" style="height:85px">
	
		<table border="0" cellspacing="5" cellpadding="3" class="pop-table">
		
			<c:forEach items="${enterFactor}" var="item" varStatus="vs" begin="8" end="16">
	        	
	        	<c:if test="${(vs.index-8)%5==0}">
		        	<tr>
	        	</c:if>
	        		<td style="text-align: left; width:20%;">
	        			<input type="checkbox" onclick="selCheck()" value="1" id="${item.key}" name="${item.key}" <c:if test="${item.value==1}">checked="checked"</c:if>>
	        			<label for="${item.key}" style="padding-left:2px">${factorNameMap[item.key]}</label>
	        		</td>
	        	
	        	<c:if test="${(vs.index-8)%5==4 || vs.index==16}">
		        	</tr>
	        	</c:if>
	        	
	        </c:forEach>
		</table>
	</div>
	
	<!-- 土壤墒情 -->
	<div style="height: 35px; line-height: 35px;">
		<a style="color: #23b4dc; cursor: pointer;font-size: 15px;" href="javascript:void(0)" onclick="toggle('soli')" ><fmt:message key="soilMoisture"/></a>
		<img id="soliImg" src="${ctx}/content/skin/css/images/arrow.png" style="padding-bottom: 3px; padding-left: 0px;"/>
	</div>
	
	<div id="soli" style="height:85px">
	
		<table border="0" cellspacing="5" cellpadding="3" class="pop-table">
		
			<c:forEach items="${enterFactor}" var="item" varStatus="vs" begin="17" end="25">
	        	
	        	<c:if test="${(vs.index-17)%5==0}">
		        	<tr>
	        	</c:if>
	        		<td style="text-align: left; width:20%;">
	        			<input type="checkbox" onclick="selCheck()" value="1" id="${item.key}" name="${item.key}" <c:if test="${item.value==1}">checked="checked"</c:if>>
	        			<label for="${item.key}" style="padding-left:2px">${factorNameMap[item.key]}</label>
	        		</td>
	        	
	        	<c:if test="${(vs.index-17)%5==4 || vs.index==25}">
		        	</tr>
	        	</c:if>
	        	
	        </c:forEach>
		</table>
	</div>
	
	<!-- 水资源 -->
	<div style="height: 35px; line-height: 35px;">
		<a style="color: #23b4dc; cursor: pointer;font-size: 15px;" href="javascript:void(0)" onclick="toggle('water')" ><fmt:message key="waterResources"/></a>
		<img id="waterImg" src="${ctx}/content/skin/css/images/arrow.png" style="padding-bottom: 3px; padding-left: 0px;"/>
	</div>
	
	<div id="water" style="height:85px">
	
		<table border="0" cellspacing="5" cellpadding="3" class="pop-table">
		
			<c:forEach items="${enterFactor}" var="item" varStatus="vs" begin="26" end="33">
	        	
	        	<c:if test="${(vs.index-26)%5==0}">
		        	<tr>
	        	</c:if>
	        		<td style="text-align: left; width:20%;">
	        			<input type="checkbox" onclick="selCheck()" value="1" id="${item.key}" name="${item.key}" <c:if test="${item.value==1}">checked="checked"</c:if>>
	        			<label for="${item.key}" style="padding-left:2px">${factorNameMap[item.key]}</label>
	        		</td>
	        	
	        	<c:if test="${(vs.index-26)%5==4 || vs.index==33}">
		        	</tr>
	        	</c:if>
	        	
	        </c:forEach>
		</table>
	</div>
	
	<div style="height: 35px; line-height: 35px; padding-top: 5px;">
		<font size="4" style="font-weight: bold;"><fmt:message key="channel11To24"/></font>
	</div>

	<!-- 扩展通道 -->
	<div style="height: 35px; line-height: 35px;">
		<a style="color: #23b4dc; cursor: pointer;font-size: 15px;" href="javascript:void(0)" onclick="toggle('extend')" ><fmt:message key="extendedChannel"/></a>
		<img id="extendImg" src="${ctx}/content/skin/css/images/arrow.png" style="padding-bottom: 3px; padding-left: 0px;"/>
	</div>
	
	<div id="extend" style="height:150px">
	
		<table border="0" cellspacing="5" cellpadding="3" class="pop-table" name="extendInfotab" id="extendInfotab">
			<c:forEach items="${enterFactor}" var="item" varStatus="vs" begin="34" end="47">
	        	
	        	<c:if test="${(vs.index-34)%5==0}">
		        	<tr>
	        	</c:if>
	        		<td style="text-align: left;">
	        			<input type="checkbox" value="1" style="height: 25px;" id="${item.key}" name="${item.key}" <c:if test="${item.value==1}">checked="checked"</c:if>>
	        			<label for="${item.key}" style="padding-left:2px; line-height: 29px;float:left"><fmt:message key="channel"/>${fn:replace(item.key,'td','')}: </label>
	        			<c:if test="${item.key =='td11'}">
	        			  <input type="text" readonly="readonly"  name="${fn:replace(item.key,'td','factorviewname')}" value="雨雪标识" style="margin-left:5px;padding-left: 2px; height: 25px; line-height: 25px; width:150px;"/>
	        			</c:if>
	        			<c:if test="${item.key !='td11'}">
	        			  <input type="text" name="${fn:replace(item.key,'td','factorviewname')}" value="${factorName[fn:replace(item.key,'td','factorviewname')]}" style="margin-left:5px;padding-left: 2px; height: 25px; line-height: 25px; width:150px;"/>
	        			</c:if>
	        			
	        		</td>
	        	
	        	<c:if test="${(vs.index-34)%5==4 || vs.last}">
		        	</tr>
	        	</c:if>
	        	
	        </c:forEach>
		</table>
	</div>
<div>
	
</div>

</form>
<div style="border-bottom:0px; text-align: center; margin-top: 10px;">
	<input class="homebg popup-confirm" sort="0" value="<fmt:message key="configuration"/>" type="button" onclick="saveChannelManage()" style="color:#ffffff"></input>
</div>
</div>
