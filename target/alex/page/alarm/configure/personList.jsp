<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.cookie.js"></script>
<title>预警人员列表</title>
</head>

<body>
<input type="hidden" value="${ChoosedPersonList }" id="ChoosedPersonList">
    <div id="personList" >
	 <div  class="right_user" style="left:15px;top:65px;background-color:transparent;position:static">
	  <font style="float:right;color:red;font-size:12px;margin-top:7px;margin-right:10px;"><fmt:message key="warnUserPrompt" /></font>
		<div class="operate" style="padding-top:5x;position:static">
			<div id="search">
			    <input type="text"  style="width:200px;color:#adadad;border:1px solid #d5d5d5;height:19px;margin-top:6px;"  value="${name==null?'':name}" id="name"  onfocus="if(this.value == '请输入预警用户') this.value = '';" onblur="if(this.value =='') this.value = '请输入预警用户';" />&nbsp; <input
					type="button" class="btn-search" value="<fmt:message key="query"/>" onclick="searchAlarmPerson()">
			</div>
		</div>
		<div class="device" id="personList" style="position:static;margin-top:20px;">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th width="5%"><!-- <input type="checkbox" id="checkPersonAll" /> --></th>
						<th width="25%"><fmt:message key="warnUser" /></th>
						<th width="20%"><fmt:message key="contactInfo" /></th>
						<th width="15%"><fmt:message key="time" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${personList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>
								<c:choose>
									<c:when test="${fn:contains(ChoosedPersonList,item.id)}">
										<input type="checkbox" class="selectPersonCheckBox" checked="checked" id="${item.id}" name="${item.name}" value="${item.id}" />
									</c:when>
									<c:otherwise>
										<input type="checkbox" class="selectPersonCheckBox" id="${item.id}" name="${item.name}" value="${item.id}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${item.name}</td>
							<td>${item.phone}</td>
							<td><fmt:formatDate value="${item.tm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<div class="list-page">
				<div id="pagination"></div>
			</div>
		</div>
	</div> 
	</div>
	<script type="text/javascript">
$(function(){
	personaList.selectAlarmPerson();
	$("#name").blur();
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
			changePersonPage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>
</body>
</html>
