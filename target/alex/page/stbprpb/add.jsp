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
</style>
<form id="stStbprpBForm">
    <input type="hidden" id="factorList" value="${fn:length(factorList)}"></input>
	<input type="hidden" id="model" name="model"></input> <input type="hidden" id="addvcd" name="addvcd" value="${addvcdId}"></input> <input type="hidden" id="enterpriseid" name="enterpriseid" value="${enterpriseid}"></input>
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
		<tr>
			<td class="table-left"><label for="stcd"><fmt:message key="stationCode"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="stcd" name="stcd" maxlength="10"></td>
			<td class="table-left"><label for="stnm"><fmt:message key="stationName"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="stnm" name="stnm" maxlength="10"></td>


		</tr>
		<tr>
			<td class="table-left"><label for="pwd"><fmt:message key="communicationCode"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="pwd" name="pwd" maxlength="30" value="40960"></td>

			<td class="table-left"><label for="model"><fmt:message key="productModel"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" readonly="readonly" id="modelName" name="modelName" maxlength="30"><a id="btnModel" onclick="getModel('${enterpriseid}')"><fmt:message key="choice"/></a></td>
		</tr>

		<tr>
			<td class="table-left"><label for="tel"><fmt:message key="siteType"/></label>：</td>
			<td class="table-right"><select id="sttp" name="sttp" style="width:200px;" <!-- onchange="sttpCha(this.value)" -->>
					<option value="2"><fmt:message key="sttp2"/></option>
					<option value="0"><fmt:message key="sttp0"/></option>
					<option value="1"><fmt:message key="sttp1"/></option>
					<option value="3"><fmt:message key="sttp3"/></option>
					<option value="4"><fmt:message key="sttp4"/></option>
			</select></td>
			
			
			<td class="table-left"><label for="commode">通信方式</label>：</td>
			<td class="table-right"><select id="commode" name="commode" style="width:200px;">
					<option value="0">GPRS</option>
					<option value="1">北斗通信</option>
			     </select>
			</td>

		<%-- 	<td class="table-left"><label for="type"><fmt:message key="submissionType"/></label>：</td>
			<td class="table-right" style="padding: 5px 0 5px 0;">
				<div id="viewFactor" style="width: 400px;">
					<input type="checkbox" id="zfl" name="zfl" value="1"><label style="float:left;"><fmt:message key="waterLevel"/></label></input> <input type="checkbox" id="pfl" name="pfl" value="1"><label style="float:left;"><fmt:message key="rainfall"/></label></input> <input type="checkbox" id="iffl" name="iffl"
						value="1"><label style="float:left;"><fmt:message key="flow"/></label></input> <input type="checkbox" id="vfl" name="vfl" value="1"><label style="float:left;"><fmt:message key="voltage"/></label></input> <input type="checkbox" id="wq" name="wq" value="1" onchange="reportWaterQuality()" onClick="this.blur();"><label
						style="float:left;"><fmt:message key="waterQuality"/></label></input>
				</div>
				<div id="waterQuality" style="width: 370px; height: 30px;display: none">
					<input type="checkbox" id="phfl" name="phfl" value="1"><label style="float:left;"><fmt:message key="ph"/></label></input> <input type="checkbox" id="tufl" name="tufl" value="1"><label style="float:left;"><fmt:message key="turbidity"/></label></input> <input type="checkbox" id="dofl"
						name="dofl" value="1"><label style="float:left;"><fmt:message key="dissolvedOxygen"/></label></input> <input type="checkbox" id="sufl" name="sufl" value="1"><label style="float:left;"><fmt:message key="conductivity"/></label></input> <input type="checkbox" id="tefl" name="tefl" value="1"><label
						style="float:left;"><fmt:message key="temperature"/></label></input>
				</div>
			</td> --%>

		</tr>

		<tr>
			<td class="table-left ishd hdParm"><label for="dvrcode"><fmt:message key="warningLevel"/>(m)</label>：</td>
			<td class="table-right ishd hdParm"><input class="pop-input-common" type="text" id="wrz" name="wrz"></td>
			<td class="table-left ishd hdParm"><label for="dvrcode"><fmt:message key="guaranteedLevel"/>(m)</label>：</td>
			<td class="table-right ishd hdParm"><input class="pop-input-common" type="text" id="grz" name="grz"></td>
		</tr>

		<tr>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="designFloodLevel"/>(m)</label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="dsflz" name="dsflz"></td>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="normalHighWaterLevel"/>(m)</label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="normz" name="normz"></td>
		</tr>

		<tr>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="deadWaterLevel"/>(m)</label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="ddz" name="ddz"></td>
			<td class="table-left issk skParm"><label for="dvrcode"><fmt:message key="crestElevation"/></label>：</td>
			<td class="table-right issk skParm"><input class="pop-input-common" type="text" id="damel" name="damel"></td>
		</tr>

		<tr>
			<td class="table-left"><label for="iscamera"><fmt:message key="includeCamera"/></label>：</td>
			<td class="table-right"><input type="radio" id="iscamera1" name="iscamera" value="0" checked="checked" onClick="viewCamera(this.value,'add')"><label style="float:left;"><fmt:message key="no"/></label></input> <input type="radio" id="iscamera2" name="iscamera"
				value="1" onClick="viewCamera(this.value,'add')"><label style="float:left;"><fmt:message key="yes"/></label></input></td>
			<td class="table-left"><label for="stlc"><fmt:message key="installationLocation"/></label>：</td>
			<td class="table-right"><input type="text" class="pop-input-common" readonly="readonly" id="stlc" name="stlc" maxlength="50" /><a id="btnLocate" onclick="getLocation('${addvcdId}')"><fmt:message key="location"/></a></td>
		</tr>

		<tr>
			<td class="table-left"><label for="stlc"><fmt:message key="coordinateSystem"/></label>：</td>
			<td class="table-right"><input type="hidden" id="devicelng"> <input type="hidden" id="devicelat"> &nbsp;&nbsp;&nbsp;<span id="lgtd" name="lgtd">0</span>, <span id="lttd" id="lttd">0</span></td>
			<td class="table-left"><label for="adminperson"><fmt:message key="area"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="area" name="area"></td>

		</tr>
			
		<tr>
			<td class="table-left"><label for="workername"><fmt:message key="contact"/></label>：</td>
			<td class="table-right"><input type="text" class="pop-input-common"  id="workername" name="workername" /></td>
			<td class="table-left"><label for="workerphone"><fmt:message key="contactTel"/></label>：</td>
			<td class="table-right"><input type="text" class="pop-input-common"  id="workerphone" name="workerphone" /></td>
		</tr>

		<tr>
			<td class="table-left"><label for="adminemail"><fmt:message key="watershed"/></label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="basin" name="basin"></td>
			<td class="table-left"><label for="comments"><fmt:message key="remarks"/></label>：</td>
			<td class="table-right"><textarea rows="2" cols="25" id="comments" name="comments"  style="max-width:250px;min-width:250px;max-height:50px;min-height:50px;" maxlength="200"></textarea></td>
		</tr>


		<tr>
			<td class="table-left isCamera cameraParm"><label for="cameratype"><fmt:message key="cameraType"/></label>：</td>
			<td class="table-right isCamera cameraParm"><input type="radio" id="cameratype1" name="cameratype" value="1" checked="checked" onClick="viewDvrParams(this.value)"><label style="float:left;"><fmt:message key="rtuCamera"/></label></input> <input type="radio"
				id="cameratype2" name="cameratype" value="2" onClick="viewDvrParams(this.value)"><label style="float:left;"><fmt:message key="dvrCamera"/></label></input></td>

			<td class="table-left isdvrCamera dvrParm"><label for="dvraddr"><fmt:message key="signalingServerAddress"/></label>：</td>
			<td class="table-right isdvrCamera dvrParm"><input class="pop-input-common" type="text" id="dvraddr" name="dvraddr"></td>
		</tr>

		<tr id="sendChannel">

			<td class="table-left isdvrCamera dvrParm"><label for="dvrcode"><fmt:message key="dvrCoding"/></label>：</td>
			<td class="table-right isdvrCamera dvrParm"><input class="pop-input-common" type="text" id="dvrcode" name="dvrcode"></td>

			<td class="table-left isdvrCamera dvrParm"><label for="videochannel"><fmt:message key="channelNumber"/></label>：</td>
			<td class="table-right isdvrCamera dvrParm"><c:forEach begin="1" end="4" step="1" var="item">
					<input style="margin-left: 5px;" type="checkbox" id="videochannel${item}" name="videochannel" value="${item}">
					<label for="videochannel${item}" style="float:left"><fmt:message key="channel"/>${item}</label>
				</c:forEach></td>
		</tr>
		
		<tr  id="sendType">
			<td class="table-left"><label for="type">报送类型</label>：</td>
			<td class="table-right" colspan="3" style="padding: 5px 0 5px 0;">
			<c:forEach var="item" items="${factorList}">
					<c:forEach var="map" items="${item}" varStatus="vs">
						<c:if test="${vs.index%7==0}">
							<div style="width: 650px; height: 30px;">
						</c:if>
						<input type="checkbox" id="${map.key}" name="${map.key}" value="1" checked="checked" />
						<label style="float:left;" for="${map.key}">${map.value.name}</label>
						<c:if test="${vs.index%7==6 || vs.last}">
							</div>
						</c:if>
					</c:forEach>
				</c:forEach></td>
		</tr>
	</table>
</form>

