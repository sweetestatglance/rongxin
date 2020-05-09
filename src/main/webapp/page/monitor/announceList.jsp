<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysAnnounce.js"></script>
<style>
<!--
div.devList{
	position: absolute;
    left: 10px;
    top: 45px;
    right: 10px;
    bottom: 5px;
    overflow: hidden;
    overflow-y: auto;
}

table.list-table thead tr th, table.list-table tbody tr td {
    font-size: 12px;
    height: 30px;
    border-bottom: 1px solid #d0d9de;
    border-right: 1px solid #d0d9de;
}
-->
</style>

<div class="devList" id="stBprpBDiv">
		<table id="monNoticeList" cellspacing='0' id="reportTable" cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th width="20%"><fmt:message key="theme"/></th>
					<th width="10%"><fmt:message key="publisher"/></th>
					<th width="10%"><fmt:message key="time"/></th>
					<th width="50%"><fmt:message key="content"/></th>
					<th width="5%"><fmt:message key="operating"/></th>
				</tr>
			</thead>
			<tbody id="list-content">
				<c:forEach var="item" items="${alarmList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
							<td>${item.title}</td>
							<td>${item.admin}</td>
						<td><fmt:formatDate value="${item.moditime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td title="${item.content}">
								<c:choose>
									<c:when test="${fn:length(item.content)>40}">${fn:substring(item.content,0,40)}...</c:when>
									<c:otherwise>${item.content}</c:otherwise>
								</c:choose>
							</td>
							<td>
								<button class="view" type="button" title="<fmt:message key="toView"/>" onclick="viewAnnounce('${item.id}')"></button>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<div class="list-page" style="padding-top: 5px; padding-bottom: 10px;">
	<div id="announcePager"></div>
</div>

<script type="text/javascript">
	var pageSize=2;
$(function(){
	var totalRecords = noticeResultArray==undefined?0:noticeResultArray.length;
	var totalPage = totalRecords%pageSize==0?totalRecords/pageSize:(totalRecords/pageSize)+1;
	var pageNo = 1;
	if(!pageNo){
		pageNo = 1;
	}
	
	//生成分页
	$.Pagination.generPageHtml({
		//填充的id
		 pagerid : "announcePager",
		//当前页
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',
		click : function(n){
			//分页执行方法
			//changePage(n);
			$.Pagination.selectPage(n,"announcePager");
			noticePageselectCallback(n-1);
			return true;
		}
	});
	noticePageselectCallback(0);
});

/**
 * 分页
 * @param page_index 当前页数
 * @param jq
 * @returns {Boolean}
 */
function noticePageselectCallback(page_index){
	var $tableTbody = $("#monNoticeList tbody");
	$tableTbody.html("");
    var max_elem = Math.min((page_index+1) * pageSize, noticeResultArray.length);
    var newcontent = '';

    for(var i=page_index*pageSize;i<max_elem;i++)
    {
    	newcontent += "<tr class='" + (i%2==0?'singular':'double') + "'>";
    	
		newcontent += "<td>" + noticeResultArray[i].title + "</td>";
		newcontent += "<td>" + noticeResultArray[i].admin + "</td>";
		newcontent += "<td>";
		newcontent += noticeResultArray[i].moditime!=undefined?new Date(noticeResultArray[i].moditime).format('yyyy-MM-dd hh:mm:ss'):'';
		newcontent += "</td>";
		newcontent += "<td>"+ noticeResultArray[i].content + "</td>";
		
		var optHtml = "<td>" +  "<button class='view' type='button' title='<fmt:message key='toView'/>' " + " onclick='" + "viewAnnounce(\"" + noticeResultArray[i].id + "\")'></button>" ;
		optHtml +="</td>";
		newcontent += optHtml;
		newcontent += "</tr>";
    	
    }
    $tableTbody.html(newcontent)
    return false;
}
 
</script>
</div>