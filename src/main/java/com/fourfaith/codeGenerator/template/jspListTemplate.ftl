<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${ftl_description}管理</title>
</head>
<body>
<div class="allTable">
		<div class="temperature">
		<form id="searchForm" action="list.do">
			<div class="tab-tool">
				
					<ul>
						<li><input name="" type="button" class="add_button"
							value="<fmt:message key="add"></fmt:message>" style="border:0px;" onclick="addWindow();" /></li>
						<li><fmt:message key=""></fmt:message>：<input name="" id="" type="text" class="ck"/></li>
						<li><input name="search" type="submit" class="search-button" 
							value="<fmt:message key="search"></fmt:message>" style="border:0px;" /></li>
						
					</ul>
				
		    </div>
	    </form>
	    <div class="tab-content">
				<table>
					<thead class="tab-top">
						<tr>
						    <#list columns as po>
								<th><fmt:message key=""></fmt:message></th>
							</#list>
						</tr>
					</thead>
					<tbody id="tab">
						  <c:forEach var="item" items="" varStatus="vs">
						    <tr>
							     <#list columns as po>
						 			 <td>${po.fieldName}</td>
						 		  </#list>
						    </tr>
						  </c:forEach>
					  </tbody>
					<tfoot>
					</tfoot>
				</table>
				</div>
			<div class="tab-bottom"><jsp:include page="/page.jsp"></jsp:include></div>
			</div>
			</div>
	<jsp:include
			page="add.jsp"></jsp:include>

</body>
</html>