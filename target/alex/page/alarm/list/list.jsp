<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
	<div class="device" id="warnDiv">
		<table cellspacing='0' id="reportTable" cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th width="5%"><input type="checkbox" id="checkAll" /></th>
					<th width="10%"><fmt:message key="alarmTime"/></th>
					<th width="10%"><fmt:message key="stationCode"/></th>
				<%-- 	<th width="20%"><fmt:message key="stationName"/></th> --%>
					<th width="10%"><fmt:message key="siteType"/></th>
					<th width="10%"><fmt:message key="alarmValue"/></th>
					<th width="10%"><fmt:message key="alarmType"/></th>
					<th width="8%"><fmt:message key="personLiable"/></th>
					<th width="5%"><fmt:message key="whetherSolve"/></th>
					<th width="9%"><fmt:message key="settlingsTime"/></th>
					<th width="8%"><fmt:message key="operating"/></th>
				</tr>
			</thead>
			<tbody id="list-content">
				<c:choose>
					<c:when test="${fn:length(alarmList)==0}">
						<tr height="40">
							<td colspan="10"><font color="#a8a8a8"> <label style="float:left;padding-left:15px"><fmt:message key="noData"/></label></font></td>
						</tr>
					</c:when>
					<c:otherwise>
				<c:forEach var="item" items="${alarmList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td><input type="checkbox" id="id" value="${item.id}"/></td>
						<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${item.stcd}</td>
						<%-- <td>${item.stnm}</td> --%>
						<td><c:choose>
								<c:when test="${item.sttp==0}"><fmt:message key="sttp0"/></c:when>
								<c:when test="${item.sttp==1}"><fmt:message key="sttp1"/></c:when>
								<c:when test="${item.sttp==2}"><fmt:message key="sttp2"/></c:when>
								<c:when test="${item.sttp==3}"><fmt:message key="sttp3"/></c:when>
								<c:when test="${item.sttp==4}"><fmt:message key="sttp4"/></c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose></td>
						<td>${item.factorvalue}</td>
						<td>
						  <c:forEach var="factorName"  items="${factorNameMap}" varStatus="vs">
						    <c:choose>
						      <%--  <c:when test="${item.type==factorName.key}">${factorName.value.name}报警</c:when> --%>
						       <c:when test="${factorName.key==fn:toLowerCase(item.type)}">${factorName.value.name}报警</c:when>
						       <c:otherwise></c:otherwise>
						    </c:choose>
						  </c:forEach>
						</td>   
						<%-- <c:choose>
								<c:when test="${item.type==0}"><fmt:message key="waterLevelAlarm"/></c:when>
								<c:when test="${item.type==1}"><fmt:message key="rainfallAlarm"/></c:when>
								<c:when test="${item.type==2}"><fmt:message key="waterLevelSensorFailure"/></c:when>
								<c:when test="${item.type==3}"><fmt:message key="lowVoltageAlarm"/></c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose> --%>
							
							<td>${item.solveperson}</td>
						<td><c:choose>
								<c:when test="${item.hassolved==false}"><fmt:message key="no"/></c:when>
								<c:when test="${item.hassolved==true}"><fmt:message key="yes"/></c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate value="${item.solvetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>
						   <c:if test="${item.hassolved==false}">
						    <button type="button"  style="width:60px;" class="btn-warn" onclick="settled('${item.id}');">
			                     <fmt:message key="solve"/>
					        </button>
						   </c:if>
						    <c:if test="${item.hassolved==true}">
						    <button type="button"  style="background-color:#2eae85;width:60px;" class="btn-warn" onclick="warnRecall('${item.id}');">
			                      <fmt:message key="revoke"/>
					        </button>
						   </c:if>
						   &nbsp;
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
</body>
</html>
<script type="text/javascript">
	$(function() {
		var totalPage = ${pagingBean.pageNum};
		var totalRecords = ${pagingBean.totalItems};
		var pageNo = ${pagingBean.pageNo};
		if (!pageNo) {
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
			click : function(n) {
				//分页执行方法
				changePage(n);
				//this.selectPage(n);
				return false;
			}
		});
	});
</script>
