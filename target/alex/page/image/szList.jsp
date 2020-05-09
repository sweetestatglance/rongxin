<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="imageTitle"/>---<fmt:message key="sttp4"/></title>
<style>
.imgMasks {
	position: absolute;
	text-align: left;
	color: white;
	font-weight: bold;
	top: 138px;
	left: 35px;
	display: block;
	width: 185px;
	height: 120px;
	background: rgba(0, 0, 0, 0.4);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#66000000,endColorstr=#66000000);
}

span {
	font-size: 12px;
}
</style>
</head>

<body>
	<div class="image">
		<ul>

			<c:choose>
				<c:when test="${fn:length(imgList)==0}">
					<font color="red"><label style="float:left;padding-left:15px"><fmt:message key="noData"/></label></font>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${imgList}" varStatus="vs">
						<li>
							<div style="position:absolute;">
								<div class="container">
									<div class="example col-xs-3 col-xs-3">
										<div style="border:1px solid #aab1b5;width:194px">
											<c:choose>
												<c:when test="${item.iscamera==1}">
													<c:choose>
														<c:when test="${item.imgurl==null}">
															<img src="${ctx}/content/skin/css/images/gap.jpg" width="200" height="260" style="padding:2px"></img>
														</c:when>
														<c:otherwise>
															<img src="${item.imgurl}" width="200" height="260" style="padding:2px" />
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<img src="${ctx}/content/skin/css/images/no-camera.jpg" width="200" height="260" style="padding:2px"></img>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="imgMasks">
									<span style="margin-left:10px;"><a href="javascript:void(0)" onclick="showReportDialog('${item.stcd}',2)">${item.stnm}</a></span> </br> <span style="margin-left:10px;"><fmt:message key="ph"/>：<c:if test="${item.ph==null}">--</c:if>
										<c:if test="${item.ph!=null}">
											<fmt:formatNumber value="${item.ph}" maxFractionDigits="3" />
										</c:if> 
									</span> <%-- </br> <span style="margin-left:10px;"><fmt:message key="turbidity"/>：<c:if test="${item.turbidimeter==null}">--</c:if>
										<c:if test="${item.turbidimeter!=null}">
											<fmt:formatNumber value="${item.turbidimeter}" maxFractionDigits="3" />
										</c:if> NTU
									</span> </br> <span style="margin-left:10px;"><fmt:message key="dissolvedOxygen"/>：<c:if test="${item.oxygen==null}">--</c:if>
										<c:if test="${item.oxygen!=null}">
											<fmt:formatNumber value="${item.oxygen}" maxFractionDigits="3" />
										</c:if> mg/L
									</span> </br> <span style="margin-left:10px;"><fmt:message key="conductivity"/>：<c:if test="${item.surfactants==null}">--</c:if>
										<c:if test="${item.surfactants!=null}">
											<fmt:formatNumber value="${surfactants.surfactants}" maxFractionDigits="3" />
										</c:if> μS/cm
									</span> </br> <span style="margin-left:10px;"><fmt:message key="ph"/>：<c:if test="${item.temperature==null}">--</c:if>
										<c:if test="${item.temperature!=null}">
											<fmt:formatNumber value="${item.temperature}" maxFractionDigits="3" />
										</c:if> ℃
									</span> --%>
								</div>
							</div>
					</c:forEach>
					</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
	<div class="list-page">
		<div id="pagination"></div>
	</div>
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
			$('.example img').zoomify();
		});
	</script>
</body>
</html>
