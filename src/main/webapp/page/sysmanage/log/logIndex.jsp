<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>日志信息管理</title>
      <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysLog.js"></script>
	<style type="text/css">
		#log_container input{
			background: #fff;
			padding-left:5px;
			border: 1px solid #d1d0d0;
			height:25px;
		}
	</style>
  </head>
  
  <body>
    <div class="right_user" style="left:15px;">
		<div class="operate">
				<div class="" style="text-align: left;margin:0px 0 0 10px;">
					
					<span><fmt:message key="from"/>:</span>
			    	<!-- onfocus="WdatePicker({onpicked:checkAndSetEndTime,dateFmt:'yyyy-M-d H:mm'})" -->
			   		<input id="query_beginTime" class="Wdate" value="${query_beginTime}" type="text" onfocus="WdatePicker({onpicked:setHiddenBeginTime,dateFmt:'yyyy-M-d H:mm:ss',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m\' }'})"/>
			   		<span><fmt:message key="to"/></span>
			   		<input id="query_endTime" class="Wdate" value="${query_endTime}" type="text" onfocus="WdatePicker({onpicked:setHiddenEndTime,dateFmt:'yyyy-M-d H:mm:ss',minDate:'#F{$dp.$D(\'query_beginTime\');}',maxDate:'%y-%M-%d 23:59'})"/>
					
					&nbsp;&nbsp;&nbsp;
					<span><fmt:message key="keyword"/>:</span>
					<input type="text" style="color:#adadad"  value="${query_keyword}" id="query_keyword" onfocus='if(this.value == &quot;<fmt:message key="keyword"/>&quot;) this.value = "";'
						onblur="if(this.value =='') this.value = '<fmt:message key="keyword"/>';">
						
					&nbsp;&nbsp;&nbsp;
					<span><fmt:message key="loginAddr"/>:</span>
					<input type="text" style="color:#adadad" value="${query_area_keyword}" id="query_area_keyword" onfocus='if(this.value == &quot;<fmt:message key="loginAddr"/>&quot;) this.value = "";'
						onblur="if(this.value =='') this.value = '<fmt:message key="loginAddr"/>';">
						
					&nbsp;&nbsp;&nbsp;
			    	<button type="button" class="btn-search" onclick="searchLogList()" style="height:25px;"><fmt:message key="query"/></button>
					&nbsp;&nbsp;&nbsp;
			    	<button type="button" class="btn-reset" onclick="resetLog()" style="height:25px;"><fmt:message key="reset"/></button>
				</div>
		</div>
		<div class="device" id="sysLogDiv">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th style="width: 40px;display:none"><input type="checkbox" id="checkAll"></th>
						<th><fmt:message key="userName"/></th>
						<th><fmt:message key="loginTime"/></th>
						<th><fmt:message key="loginIP"/></th>
						<th><fmt:message key="loginAddr"/></th>
						<th><fmt:message key="content"/></th>
						<th><fmt:message key="operateTime"/></th>
					</tr>
				</thead>
				<tbody>
				       <c:forEach var="item" items="${logList}" varStatus="vs">
							<tr class="">
							    <td>${item.username}</td>
							    <td><fmt:formatDate value="${item.logintime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
							    <td>${item.loginip}</td>
							    <td><font color="red">${item.loginarea}</font></td>
								<td style="display:none"><input type="checkbox" id="${item.id}" value="${item.id}" /></td>
								<td>${item.logcontent}</td>
								<td><fmt:formatDate value="${item.logtime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
							</tr>
						</c:forEach>
				</tbody>

			</table>
			<div class="list-page">
				<div id="pagination"></div>
			</div>
		</div>
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
