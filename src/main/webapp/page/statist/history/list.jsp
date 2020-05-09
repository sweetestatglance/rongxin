<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>历史报表列表</title>
</head>

<body>
	<div id="container">
	<div class="data-grid" id="dataOverId" style="top:95px;">
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th><fmt:message key="stationCode"/></th>
					<th><fmt:message key="stationName"/></th>
					<th>水文站</th>
					<th>水文局</th>
					<th>日累计将水量</th>
					<c:forEach var="item"  items="${viewHead}" varStatus="vs">
						<c:if test="${item == '时段降水量' }"><th>五分钟降水量</th></c:if>
						<c:if test="${item != '时段降水量' }"><th>${item}</th></c:if>
			 		</c:forEach>
					<th><fmt:message key="acquisitionTime"/></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(allDeviceFactorList)==0}">
						<tr height="40">
							<td colspan="${fn:length(viewHead) +6}"><font color="#a8a8a8"> <label style="float:left;padding-left:15px"><fmt:message key="noData"/></label></font></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${allDeviceFactorList}" varStatus="vs">
							<tr class="${vs.index%2==0?'singular':'double'}">
								<td>${item.stcd}</td>
								<td>${item.stnm}</td>
								<td><c:if test="${empty item.addvnm1}">-</c:if><c:if test="${not empty item.addvnm1}">${item.addvnm1 }</c:if></td>
								<td><c:if test="${empty item.addvnm2}">-</c:if><c:if test="${not empty item.addvnm2}">${item.addvnm2 }</c:if></td>
								<td>
									<c:if test="${item.pJ==null}">--</c:if>
									<c:if test="${item.pJ!=null}"><fmt:formatNumber type="number" pattern="0.0" value="${item.pJ}" /></c:if>
								</td>
								<c:forEach var="factorItem" items="${sysUserFactorList}" varStatus="dvs">
									<td>
										<c:set var="factor" scope="page" value="${factorItem.factor}"/>
										<c:choose>
											<c:when test="${item[factor]!=null}">
												
												<c:choose>
													<c:when test="${factor=='voltage'}">
														
														<c:choose>
														
															<c:when test="${item[factor]<=10.8 || item[factor]>=36 }"><fmt:message key="abnormal"/></c:when>
															<c:otherwise>
																<img src='${ctx}/content/skin/css/images/state/battery1.png' />&nbsp;<font style='vertical-align:middle'><fmt:formatNumber value="${item[factor]}" pattern="0.0" maxFractionDigits="3" /></font>
															</c:otherwise>
														</c:choose>
													
													</c:when>
													
													<c:when test="${factor=='signalinten'}">
													
														<c:choose>
															<c:when test="${item[factor]<1 || item[factor]>31 }"><fmt:message key="abnormal"/></c:when>
															<c:when test="${item[factor]>=1 && item[factor]<=10 }">
																<img src='${ctx}/content/skin/css/images/state/wifi3.png' />&nbsp;<font style='vertical-align:middle'><fmt:formatNumber value="${item[factor]}" pattern="0.0" maxFractionDigits="3" /></font>
															</c:when>
															<c:when test="${item[factor]>=11 && item[factor]<=20 }">
																<img src='${ctx}/content/skin/css/images/state/wifi2.png' />&nbsp;<font style='vertical-align:middle'><fmt:formatNumber value="${item[factor]}" pattern="0.0" maxFractionDigits="3" /></font>
															</c:when>
															<c:otherwise>
																<img src='${ctx}/content/skin/css/images/state/wifi1.png' />&nbsp;<font style='vertical-align:middle'><fmt:formatNumber value="${item[factor]}" pattern="0.0" maxFractionDigits="3" /></font>
															</c:otherwise>
														</c:choose>
													
													</c:when>
													
													
													<c:when test="${factor=='td11'}">
														<c:choose>
															<c:when test="${item[factor]<=0}"><img src='${ctx}/content/skin/css/images/state/rain.png' /></c:when>
															<c:when test="${item[factor]>0}"><img src='${ctx}/content/skin/css/images/state/snow.png' /></c:when>
															<c:otherwise>
																 &nbsp;
															</c:otherwise>
														</c:choose>
													
													</c:when>
													
													
													<c:otherwise><fmt:formatNumber value="${item[factor]}" pattern="0.0" maxFractionDigits="3" /></c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>--</c:otherwise>
										</c:choose>
									</td>
						 		</c:forEach>
								
								<td><c:if test="${item.tm==null}">--</c:if>
									<c:if test="${item.tm!=null}">
										<fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm" />
									</c:if></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="list-page"><div id="pagination"></div></div>
	</div>
	</div>
</body>
</html>
<script type="text/javascript">
$(function(){
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
			changeDetailPage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>