<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>运维监控列表</title>

  </head>
  
  <body>
		<div class="map_button">
			列<br />表<br />数<br />据
		</div>
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
				    <th>传感器状态</th>
					<th>站点名称</th>
					<th>预警状态</th>
					<th>数据正确率</th>
					<th>通信异常</th>
					<th>操作</th>

				</tr>
			</thead>
			<tbody>
		       <c:forEach var="item"  items="${stbprpBList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
					    <td>
			       	        <c:choose>
								<c:when test="${item.dsfl==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img></c:when>
								<c:when test="${item.dsfl==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img></c:when>
								<c:when test="${item.dsfl==2}"><img src='${ctx}/content/skin/css/images/icon/icon-dormant.png'></img></c:when>
								<c:when test="${item.dsfl==3}"><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:when>
								<c:otherwise><img src='images/icon/icon-upgrade.gif'></img></c:otherwise>
	   				        </c:choose>
					    </td>
						<td>${item.stcd}</td>
						<td>${item.earlywarn}</td>
						<td>&nbsp;</td>
						<td>${item.abcount}</td>
						<td><a href="#">中心</a> | <a href="#">查询</a> | <a href="#">报警</a> | <a href="#">录像策略</a></td>
					</tr>
		    	</c:forEach>
			</tbody>
		</table>
		<div class="list-page">
				<div id="pagination"></div>
			</div>
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
			changePage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>
  </body>
</html>
