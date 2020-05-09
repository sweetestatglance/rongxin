<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>企业管理首页</title>
    <script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/sysEnterprise.js"></script>

  </head>
  
  <body>
	<div class="right_user" style="left:15px;">
		<div class="operate">
			<ul>
				<li class="add" id="show" onclick="addEnterprise()"><fmt:message key="added"/></li>
				<li class="remote" onclick="editEnterPrise()"><fmt:message key="edit"/></li>
			</ul>
			<div class="search">
				<input type="text" value="<fmt:message key='enterpriseName'/>" id="enterName"  onfocus='if(this.value == &quot;<fmt:message key="enterpriseName"/>&quot;) this.value = "";' onblur="if(this.value =='') this.value = '<fmt:message key="enterpriseName"/>';" /><a id="btnSearch" onclick="searchEnterPrise()"></a>
			</div>
		</div>
		<div class="device" id="sysEnterpriseList">
			<table cellspacing='0' cellpadding='3' class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"/></th>
                        <th><fmt:message key="enterpriseCode"/></th>
				 	    <th><fmt:message key="enterpriseName"/></th>
				 	    <th><fmt:message key="enterpriseType"/></th>
				 	    <th><fmt:message key="devAuthNum"/></th>
				 	    <th><fmt:message key="msgNum"/></th>
				 	    <th><fmt:message key="contact"/></th>
				 	    <th><fmt:message key="contactTel"/></th>
				 	    <th><fmt:message key="fax"/></th>
				 	    <th><fmt:message key="enterpriseState"/></th>
				 	    <th><fmt:message key="remarks"/></th>
				 	    <th><fmt:message key="operating"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item"  items="${enterpriseList}" varStatus="vs">
				 		 <tr class="${vs.index%2==0?'singular':'double'}">
				             <td><input type="checkbox" id="${item.id}" name="chk" value="${item.id}"></td>
				 			 <td>${item.enterprisecode}</td>
				 			 <td>${item.enterprisename}</td>
				 			 <td>
				 			 	<c:choose>
									<c:when test="${item.enterprisetype==1}"><fmt:message key="enterpriseType_1"/></c:when>
									<c:when test="${item.enterprisetype==0}"><fmt:message key="enterpriseType_0"/></c:when>
									<c:otherwise>&nbsp;</c:otherwise>
			   					</c:choose>
				 			 </td>
				 			 <td>${item.enterprisedevicenum}</td>
				 			 <td>${item.enterprisesmsnum}</td>
				 			 <td>${item.enterpriselinkman}</td>
				 			 <td>${item.enterprisetel}</td>
				 			 <td>${item.enterprisefax}</td>
				 			 <td>
				 			   <c:choose>
									<c:when test="${item.enabledstate==-1}"><fmt:message key="delete"/></c:when>
									<c:when test="${item.enabledstate==0}"><font color="red"><fmt:message key="disabled"/></font></c:when>
									<c:when test="${item.enabledstate==1}"><fmt:message key="enabled"/></c:when>
									<c:otherwise>&nbsp;</c:otherwise>
			   					</c:choose>
				 			 
				 			 </td>
				 			 <td>${item.enterpriseremark}</td>
				 			 <td><a href="javascript:void(0)" onclick="setSeeMenuInfo('${item.id}');return false;"><font color="#3595b0" style="font-size:12px;"><fmt:message key="menuSetting"/></font></a><a href="javascript:void(0)" onclick="factorConfig('${item.id}','${item.enterprisename}')"></a></td>
				    </tr>
				</c:forEach> 
				</tbody>

			</table>
<div class="list-page"><div id="pagination"></div></div>
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
