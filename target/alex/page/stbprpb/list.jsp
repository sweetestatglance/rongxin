<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="deviceTitle"/></title>
<style>
.buttonTask {
	display: inline-block;
	position: relative;
	padding: 0 20px;
	text-align: center;
	text-decoration: none;
	font: bold 12px/25px Arial, sans-serif;
	text-shadow: 1px 1px 1px rgba(255,255,255, .22);
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;

}
.blue {
	color: #19667d;
	background: #44c4e7;
}
 a:hover { color:white; } 
</style>
</head>

<body>
	<div class="operate">
		<ul>
			<li class="add" onclick="addStBprpB()">&nbsp;<fmt:message key="added"/></li>
		<!-- 	<li class="move" onclick="moveStBprpB()">&nbsp;移动</li> -->
			<li class="del" onclick="delStBprpB()">&nbsp;<fmt:message key="delete"/></li>
			<li class="import" onclick="importStBprpB()">&nbsp;<fmt:message key="import"/></li>
			<li class="export" onclick="exportStBprpB()">&nbsp;<fmt:message key="export"/></li>
			<li class="refresh" onclick="refresh()">&nbsp;<fmt:message key="refresh"/></li>
		</ul>
		<div class="search">
			<input type="text" value="${stcd==null?'':stcd}" id="dstcd"  onfocus="if(this.value == '<fmt:message key="code"/>') this.value = '';" onblur="if(this.value =='') this.value = '<fmt:message key="code"/>';" /><a id="btnSearch" onclick="searchStcd()"></a>
		</div>
		<div style="margin:10px 0 0 15px;width:138px;height:24px;float:left;">
			<font color="#036f8b"><fmt:message key="onlineEquipment"/>:&nbsp;${onlineCount}/${sbcount}</font>
		</div>
		<div style="float:left;margin:5px 0 0 25px">
			<!-- <img src="content/skin/css/images/task.png" onclick="taskList()"></img> -->
			<a href="javascript:void(0)" class="buttonTask blue" onclick="taskList()"><fmt:message key="taskList"/></a>
		</div>
		<span class="siteNow" style="color:#a6a6a6"><fmt:message key="currentLocation"/>：${localName}&nbsp;&nbsp;&nbsp;</span>
	</div>
	<div class="device" id="stBprpBDiv">
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll" /></th>
					<th width="6%"><fmt:message key="status"/></th>
					<th width="15%"><fmt:message key="stationCode"/></th>
					<th width="15%"><fmt:message key="stationName"/></th>
					<th width="8%">水文站</th>
					<th width="8%">水文局</th>
					<th width="15%"><fmt:message key="siteType"/></th>
					<th width="8%">通信模式</th>
					<th width="8%"><fmt:message key="camera"/></th>
					<th width="10%">更新时间</th>
					<th width="10%"><fmt:message key="operating"/></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(stbprpBList)==0}">
						<tr height="40">
							<td colspan="11"><font color="#a8a8a8"> <label style="float:left;padding-left:15px"><fmt:message key="noData"/></label></font></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${stbprpBList}" varStatus="vs">

							<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="stcd" value="${item.stcd}" /></td>
								<td><c:choose>
										<c:when test="${item.dsfl==0}">
											<img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>
										</c:when>
										<c:when test="${item.dsfl==1}">
											<img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>
										</c:when>
										<c:when test="${item.dsfl==2}">
											<img src='${ctx}/content/skin/css/images/icon/icon-dormant.png'></img>
										</c:when>
										<c:when test="${item.dsfl==3}">
											<img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>
										</c:when>
										<c:otherwise>
											<img src='images/icon/icon-upgrade.gif'></img>
										</c:otherwise>
									</c:choose></td>
								<td>${item.stcd}</td>
								<td>${item.stnm}</td>
								<td><c:if test="${empty item.addvnm1}">-</c:if><c:if test="${not empty item.addvnm1}">${item.addvnm1 }</c:if></td>
								<td><c:if test="${empty item.addvnm2}">-</c:if><c:if test="${not empty item.addvnm2}">${item.addvnm2 }</c:if></td>
								<td><c:choose>
										<c:when test="${item.sttp==0}"><fmt:message key="sttp0"/></c:when>
										<c:when test="${item.sttp==1}"><fmt:message key="sttp1"/></c:when>
										<c:when test="${item.sttp==2}"><fmt:message key="sttp2"/></c:when>
										<c:when test="${item.sttp==3}"><fmt:message key="sttp3"/></c:when>
										<c:when test="${item.sttp==4}"><fmt:message key="sttp4"/></c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${item.commode==0}">GPRS</c:when>
										<c:when test="${item.commode==1}">北斗通信</c:when>
										<c:otherwise></c:otherwise>
									</c:choose></td>
								<td><c:if test="${item.iscamera==1}">
										<a href="javascript:void(0)" onclick="capture('${item.stcd}')"><img src="${ctx}/content/skin/css/images/camera.png"></img></a>
									</c:if></td>
								<td><fmt:formatDate value="${item.moditime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td width="50">
									<ul>
										<li class="edit" title="<fmt:message key="edit"/>" onclick="editStBprpB('${item.stcd}')"></li>
										<li class="del" title="<fmt:message key="delete"/>" onclick="delStBprpB('${item.stcd}')"></li>
										<li class="data-operate" title="<fmt:message key="remoteConfiguration"/>" title="<fmt:message key="operating"/>" onclick="configuration('${item.stcd}','${item.dsfl}','${item.commode}')"></li>
									</ul>
								</td>

							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>

		</table>
		<div class="list-page">
			<div id="pagination"></div>
		</div>
	</div>
	<script type="text/javascript">
$(function(){
	$("#dstcd").blur();
	var totalPage = ${pagingBean.pageNum};
	var totalRecords = ${pagingBean.totalItems};
	var pageNo = ${pagingBean.pageNo};
	if(!pageNo){
		pageNo = 1;
	}
	//生成分页
	$.Pagination.generPageHtml({
		//填充的id
		 pagerid : "pagination",
		//当前页
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',
		click : function(n){
			//分页执行方法
			changePage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>
</body>
</html>
