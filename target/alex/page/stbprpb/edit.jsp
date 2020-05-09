<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
.isCamera {
	display: none
}

.isdvrCamera {
	display: none
}

.ishd {
	display: none
}

.issk {
	display: none
}

table.pop-table input,table.pop-table select,table.pop-table textarea {
	margin-left: 0px;
}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/mapDialog.css">
<form id="stStbprpBForm">
	<input type="hidden" id="model" name="model" value="${stBprpB.model}"></input> <input type="hidden" id="stcd" name="stcd" value="${stBprpB.stcd}"> <input type="hidden" id="addvcd" name="addvcd" value="${stBprpB.addvcd}"></input> <input type="hidden"
		id="enterpriseid" name="enterpriseid" value="${stBprpB.enterpriseid}"></input>
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="width:840px;">
		<tr>
			<td class="table-left"><label for="stcd"><fmt:message key="stationCode"/></label>：</td>
			<td class="table-right">${stBprpB.stcd}</td>
			<td class="table-left"><label for="stnm"><fmt:message key="stationName"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="stnm" name="stnm" maxlength="10" value="${stBprpB.stnm}"></td>


		</tr>
		<tr>
			<td class="table-left"><label for="pwd"><fmt:message key="communicationCode"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="pwd" name="pwd" maxlength="30" value="${stBprpB.pwd}"></td>

			<td class="table-left"><label for="model"><fmt:message key="productModel"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" readonly="readonly" id="modelName" value="${modelName}" name="modelName" maxlength="30"><a id="btnModel" onclick="getModel('${enterpriseid}')">选择</a></td>
		</tr>

		<tr>
			<td class="table-left"><label for="tel"><fmt:message key="siteType"/></label>：</td>
			<td class="table-right"><select id="sttp" name="sttp" style="width:200px;" <!-- onchange="sttpCha(this.value)" -->>
					<option value="2" <c:if test="${'2' eq stBprpB.sttp}">selected</c:if>><fmt:message key="sttp2"/></option>
					<option value="0" <c:if test="${'0' eq stBprpB.sttp}">selected</c:if>><fmt:message key="sttp0"/></option>
					<option value="1" <c:if test="${'1' eq stBprpB.sttp}">selected</c:if>><fmt:message key="sttp1"/></option>
					<option value="3" <c:if test="${'3' eq stBprpB.sttp}">selected</c:if>><fmt:message key="sttp3"/></option>
					<option value="4" <c:if test="${'4' eq stBprpB.sttp}">selected</c:if>><fmt:message key="sttp4"/></option>
			</select></td>
			
			<td class="table-left"><label for="commode">通信方式</label>：</td>
			<td class="table-right"><select id="commode" name="commode" style="width:200px;">
					<option value="0" <c:if test="${'0' eq stBprpB.commode}">selected</c:if>>GPRS</option>
					<option value="1" <c:if test="${'1' eq stBprpB.commode}">selected</c:if>>北斗通信</option>
			     </select>
			</td>

		<%-- 	<td class="table-left"><label for="type"><fmt:message key="submissionType"/></label>：</td>
			<td class="table-right" style="padding: 5px 0 5px 0;">
				<div id="viewFactor" style="width: 400px;">
					<input type="checkbox" id="zfl" name="zfl" value="1" <c:if test="${zfl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="waterLevel"/></label></input> <input type="checkbox" id="pfl" name="pfl" value="1"
						<c:if test="${pfl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="rainfall"/></label></input> <input type="checkbox" id="iffl" name="iffl" value="1" <c:if test="${iffl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="flow"/></label></input>
					<input type="checkbox" id="vfl" name="vfl" value="1" <c:if test="${vfl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="voltage"/></label></input>
					<input type="checkbox" id="wq" name="wq" value="1" onchange="reportWaterQuality()" onClick="this.blur();"><label
						style="float:left;"><fmt:message key="waterQuality"/></label></input>
				</div>
				<div id="waterQuality" style="width: 370px; height: 30px;display: none">
					<input type="checkbox" id="phfl" name="phfl" value="1" <c:if test="${phfl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="ph"/></label></input> <input type="checkbox" id="tufl" name="tufl" value="1"
						<c:if test="${tufl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="turbidity"/></label></input> <input type="checkbox" id="dofl" name="dofl" value="1" <c:if test="${dofl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="dissolvedOxygen"/></label></input>
					<input type="checkbox" id="sufl" name="sufl" value="1" <c:if test="${sufl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="conductivity"/></label></input> <input type="checkbox" id="tefl" name="tefl" value="1"
						<c:if test="${tefl==1}">checked="checked"</c:if>><label style="float:left;"><fmt:message key="temperature"/></label></input>
				</div>
			</td> --%>

		</tr>

		<tr>
			<td class="table-left ishd hdParm"><label for="dvrcode"><fmt:message key="warningLevel"/>(m)</label>：</td>
			<td class="table-right ishd hdParm"><input class="pop-input-common" type="text" id="wrz" name="wrz" value="${stBprpB.wrz}"></td>
			<td class="table-left ishd hdParm"><label for="dvrcode"><fmt:message key="guaranteedLevel"/>(m)</label>：</td>
			<td class="table-right ishd hdParm"><input class="pop-input-common" type="text" id="grz" name="grz" value="${stBprpB.grz}"></td>
		</tr>

		<tr>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="designFloodLevel"/>(m)</label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="dsflz" name="dsflz" value="${stBprpB.dsflz}"></td>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="normalHighWaterLevel"/>(m)</label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="normz" name="normz" value="${stBprpB.normz}"></td>
		</tr>

		<tr>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="deadWaterLevel"/>(m)</label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="ddz" name="ddz" value="${stBprpB.ddz}"></td>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="crestElevation"/></label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="damel" name="damel" value="${stBprpB.damel}"></td>
		</tr>

		<tr>
			<td class="table-left"><label for="iscamera"><fmt:message key="includeCamera"/></label>：</td>
			<td class="table-right"><input type="radio" id="iscamera1" name="iscamera" value="0" <c:if test="${stBprpB.iscamera==0}">checked="checked"</c:if> onClick="viewCamera(this.value,'edit')"><label style="float:left;">否</label></input> <input
				type="radio" id="iscamera2" name="iscamera" value="1" <c:if test="${stBprpB.iscamera==1}">checked="checked"</c:if> onClick="viewCamera(this.value,'edit')"><label style="float:left;">是</label></input></td>
			<td class="table-left"><label for="stlc"><fmt:message key="installationLocation"/></label>：</td>
			<td class="table-right"><input type="text" class="pop-input-common" readonly="readonly" id="stlc" name="stlc" maxlength="50" value="${stBprpB.stlc}" /></td>
		</tr>

		<tr>
			<td class="table-left"><label for="stlc"><fmt:message key="coordinateSystem"/></label>：</td>
			<td class="table-right"><input type="hidden" id="devicelng" value="${stBprpB.lgtd}"> <input type="hidden" id="devicelat" value="${stBprpB.lttd}"> &nbsp;&nbsp;&nbsp;<span id="lgtd" name="lgtd">${stBprpB.lgtd}</span>, <span
				id="lttd" id="lttd">${stBprpB.lttd}</span></td>
			<td class="table-left"><label for="adminperson"><fmt:message key="area"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="area" name="area" value="${stBprpB.area}"></td>

		</tr>
		
		<tr>
			<td class="table-left"><label for="workername"><fmt:message key="contact"/></label>：</td>
			<td class="table-right"><input type="text" class="pop-input-common"  id="workername" name="workername" value="${stBprpB.workername}"/></td>
			<td class="table-left"><label for="workerphone"><fmt:message key="contactTel"/></label>：</td>
			<td class="table-right"><input type="text" class="pop-input-common"  id="workerphone" name="workerphone" value="${stBprpB.workerphone}"/></td>
		</tr>

		<tr>
			<td class="table-left"><label for="adminemail"><fmt:message key="watershed"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="basin" name="basin" value="${stBprpB.basin}"></td>
			<td class="table-left"><label for="comments"><fmt:message key="remarks"/></label>：</td>
			<td class="table-right"><textarea rows="2" cols="25" id="comments" name="comments" style="max-width:250px;min-width:250px;max-height:50px;min-height:50px;" maxlength="200">${stBprpB.comments}</textarea></td>
		</tr>
		

		<tr>
			<td class="table-left isCamera cameraParm"><label for="cameratype"><fmt:message key="cameraType"/></label>：</td>
			<td class="table-right isCamera cameraParm"><input type="radio" id="cameratype1" name="cameratype" value="1" <c:if test="${stBprpB.cameratype==1 ||  stBprpB.cameratype==0}">checked="checked"</c:if> onClick="viewDvrParams(this.value)"><label
				style="float:left;"><fmt:message key="rtuCamera"/></label></input> <input type="radio" id="cameratype2" name="cameratype" value="2" <c:if test="${stBprpB.cameratype==2}">checked="checked"</c:if> onClick="viewDvrParams(this.value)"><label style="float:left;"><fmt:message key="dvrCamera"/></label></input>
			</td>

			<td class="table-left isdvrCamera dvrParm"><label for="dvraddr"><fmt:message key="signalingServerAddress"/></label>：</td>
			<td class="table-right isdvrCamera dvrParm"><input class="pop-input-common" type="text" id="dvraddr" name="dvraddr" value="${stBprpB.dvraddr}"></td>
		</tr>

		<tr id="sendChannel">

			<td class="table-left isdvrCamera dvrParm"><label for="dvrcode"><fmt:message key="dvrCoding"/></label>：</td>
			<td class="table-right isdvrCamera dvrParm"><input class="pop-input-common" type="text" id="dvrcode" name="dvrcode" value="${stBprpB.dvrcode}"></td>

			<td class="table-left isdvrCamera dvrParm"><label for="videochannel"><fmt:message key="channelNumber"/></label>：</td>
			<td class="table-right isdvrCamera dvrParm"><c:forEach begin="1" end="4" step="1" var="item">
					<input style="margin-left: 5px;" type="checkbox" value="${item}" ${fn:contains(stBprpB.videochannel,item)?'checked':''} id="videochannel${item}" name="videochannel">
					<label for="videochannel${item}" style="float:left"><fmt:message key="channel"/>${item}</label>
				</c:forEach></td>
		</tr>
		
		  <tr id="sendType">
       		<td class="table-left"><label for="type">报送类型</label>：</td>
			<td class="table-right" colspan="3" style="padding: 5px 0 5px 0;">
			<c:forEach var="item"  items="${factorList}">
			  <c:forEach var="map"  items="${item}" varStatus="vs">
			   		<c:if test="${vs.index%7==0}">
			    		<div style="width: 650px; height: 30px;">
			    	</c:if>
			    		<input type="checkbox" <c:if test="${factorMap[map.key]==1}">checked="checked"</c:if> id="${map.key}" name="${map.key}" value="1"/>
			    		<label style="float:left;" for="${map.key}">${map.value.name}</label>
			    	<c:if test="${vs.index%7==6 || vs.last}">
			    		</div>
			    	</c:if>
		    	</c:forEach>
		    </c:forEach>
			</td>
       </tr>
	</table>
</form>

<input type="hidden" id="addvcdName" value="${addvcdName}">
<div id="mapContainer" style="float:right;height:365px;width:390px;border: 1px solid #dcdddd;position: relative;margin-right: 20px;">
	<input type="hidden" id="localLng"> <input type="hidden" id="localLat"> <input type="hidden" id="localAddress">
	<!-- 关键字查询 -->
	<div id="searchArea" style="z-index:901;top:0px;min-height:55px;min-width:385px;">
		<b><fmt:message key="keyWords"/>：</b> <input type="text" id="keyword" name="keyword" value="" onkeydown='keydown(event)' style="width: 60%;" /> <input type="button" value="查 询" onclick="keywordSearch()">
		<div id="result1" name="result1"></div>
	</div>
</div>


<script type="text/javascript" src="${ctx}/content/skin/js/amap/ZMap.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/js/amap/dialogMap.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//ie下需要设置div为固定高，否则不显示
		 if(isIE()){ 
			$("#mapContainer").css("height","365px");
		 }
		 var sttp = '${stBprpB.sttp}';
		 if(sttp=="4"){
			 $("#wq").attr("checked","checked"); 
			 $("#waterQuality").css("display","block");
			 $("#viewFactor").css("height","30px");
		 }
		 if(sttp=="0" || sttp=="3"){
			 $("td.hdParm").removeClass("ishd");
		 }
		 if(sttp=="1"){
			 $("td.skParm").removeClass("issk");
		 }
	     var  iscamera = '${stBprpB.iscamera}';
	     if(iscamera=="1"){
	    	/* $("#stStbprpBTb").css("width","750");
	     	$("#editStBprpBID").children(".popup-box").css("width", "1270"); */
	     	$("td.cameraParm").removeClass("isCamera");
	 	 }else{
	 		/* $("#stStbprpBTb").css("width","450");
	 		$("#editStBprpBID").children(".popup-box").css("width", "930"); */
	 		$("td.cameraParm").addClass("isCamera");
	 		if(!$("td.dvrParm").hasClass("isdvrCamera")){
	 			$("#cameratype1").attr("checked","checked");
	 			$("td.dvrParm").addClass("isdvrCamera");
	 		}
	 	 }
	    var cameratype = '${stBprpB.cameratype}';
	    if(cameratype=="2"){
		    $("td.dvrParm").removeClass("isdvrCamera");
		}else{
			$("td.dvrParm").addClass("isdvrCamera");
		} 
	    var sttp = '${stBprpB.sttp}';
	    if(sttp=="ZQ"){
	    	$("#zqTr").css("display","table-row");
	    }else if(sttp=="RR"){
	    	$("#rrTr1").css("display","table-row");
			$("#rrTr2").css("display","table-row");
	    }
	    
	    var wqfl='${wqfl}';
	    if(wqfl==1) {
	    	$("input#wq").attr("checked","checked");
	    	$("#waterQuality").css("display","inline-block");
	    }
	});
</script>