<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>图像列表</title>

<meta http-equiv="pragma" content="no-cache">
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/devicePhoto.js"></script>
<link href="${ctx}/content/skin/css/baguettebox/baguetteBox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/baguettebox/baguetteBox.js"></script>
</head>
<body>
<hr style="height:1px;border:none;" /></label>
   <div id="photoContent">
     <input type="hidden" id="deviceId" name="stcd" value="${stcd}"></input>
     <div style="height:40px;">
	     <table border="0" cellspacing="1" id="photoTable" cellpadding="3" class="pop-table" style="font-size:14px;">
	     <tr>
	      	<td class="table-left" style="width: 70px;"><label for="centerAddress"><fmt:message key="channel"/>1</label>： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo1" name="photo1" value="01" ></input>
	          </td>
	          <td class="table-left" style="width: 70px;"><label for="pwd"><fmt:message key="channel"/>2</label>： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo2" name="photo2" value="02"></input>
	          </td>
	      	  <td class="table-left" style="width: 70px;"><label for="centerAddress"><fmt:message key="channel"/>3</label>： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo3" name="photo3" value="03"></input>
	          </td>
	          <td class="table-left" style="width: 70px;"><label for="pwd"><fmt:message key="channel"/>4： </td>
	          <td class="table-right">
	          	  <input type="checkbox" id="photo4" name="photo4" value="04"></input>
	          </td>
	          <td>
	        	<button type="button"  style="margin-left:50px;"  class="homebg popup-confirm"  style="color:#ffffff"  onclick="photoSave()"><fmt:message key="issued"/></button>
	          </td>
	      </tr>
	      </table>
      </div>
      <hr style="height:1px;border:none;border-top:1px dashed #555555;" /></label>
	<div id="taskDiv" style="font-size:12px;width:830px;margin-top:25px;">
		<input type="hidden" id="deviceno" name="deviceno" value="${deviceNo}"></input>
		<div style="float: left;padding-left:20px;padding-bottom:10px;">
			<span><fmt:message key="shootingTime"/> ：</span> 
			<input id="create_beginComplateTime" class="Wdate" type="text" value="${beginTime}"
				onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'create_endComplateTime\')||\'2150-10-01\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <span><fmt:message key="toZ"/></span> <input id="create_endComplateTime"
				class="Wdate" type="text" value="${endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'create_beginComplateTime\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
			<span><fmt:message key="photoType"/>：</span>
			<select id="query_photoType" style="height:25px;width:150px;">
				<option value=""></option>
				<option value="1" <c:if test="${'1' eq query_photoType}">selected</c:if>><fmt:message key="capture"/></option>
				<option value="2" <c:if test="${'2' eq query_photoType}">selected</c:if>><fmt:message key="callThePolice"/></option>
				<option value="3" <c:if test="${'3' eq query_photoType}">selected</c:if>><fmt:message key="timing"/></option>
			</select> 
			<input class="homebg popup-confirm" type="button" onclick="photoQuery()" value="<fmt:message key="query"/>" style="margin-left: 20px; border: none;"> 
		    <input class="homebg popup-cancel" type="button" type="button" onclick="photoRefresh()" value="<fmt:message key="refresh"/>" style=" border: none;">
		</div>

		<div id="photoDiv" class="content baguetteBoxOne gallery">
			<table id="photo" border='0' cellspacing='1' cellpadding='1' class='list-table'>
				<thead>
					<th><fmt:message key="cameraNumber"/></th>
					<th><fmt:message key="type"/></th>
					<th><fmt:message key="cameraNumber"/></th>
					<th><fmt:message key="shootingTime"/></th>
					<th><fmt:message key="createTime"/></th>
					<th><fmt:message key="completionTime"/></th>
					<th><fmt:message key="operating"/></th>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(photoList)==0}">
							<tr>
								<td colspan="7"><font color="red"><label style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;暂无数据</label></font></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="item" items="${photoList}" varStatus="vs">
								<tr class="${vs.index%2==0?'singular':'double'}">
									<td>${item.stcd}</td>
									<td><c:choose>
											<c:when test="${item.phototype==1}"><fmt:message key="capture"/></c:when>
											<c:when test="${item.phototype==2}"><fmt:message key="callThePolice"/></c:when>
											<c:when test="${item.phototype==3}"><fmt:message key="timing"/></c:when>
											<c:otherwise>&nbsp;</c:otherwise>
										</c:choose></td>
									<td><c:choose>
											<c:when test="${item.photostatus==0}"><fmt:message key="noTaskPerformed"/></c:when>
											<c:when test="${item.photostatus==1}"><fmt:message key="dataTransmission"/></c:when>
											<c:when test="${item.photostatus==2}"><fmt:message key="theShootingEnds"/></c:when>
											<c:when test="${item.photostatus==3}"><fmt:message key="transmissionTimeout"/></c:when>
											<c:when test="${item.photostatus==4}"><fmt:message key="shootingFailed"/></c:when>
											<c:when test="${item.photostatus==5}"><fmt:message key="shootingOff"/></c:when>
										</c:choose></td>
									<td><fmt:formatDate value="${item.photoseetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${item.photocreatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><fmt:formatDate value="${item.photocomplatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
									<td><c:choose>
											<c:when test="${item.photostatus==2}">
												<a href="${item.photourl}" style="color:black"><fmt:message key="preview"/></a>
												<%-- <a href="javascript:void(0)" onclick="doPreview('${item.photourl}')" style="color:black">预览</a> --%>
											</c:when>
											<c:otherwise>
												<font color="gray"><fmt:message key="preview"/></font>
											</c:otherwise>
										</c:choose> &nbsp;&nbsp;&nbsp; <c:choose>
											<c:when test="${item.photostatus==1 && item.phototype==1}">
												<a href="javascript:void(0)" onclick="doDropped('${item.id}','${item.photoid}','${item.stcd}')" style="color:black"><fmt:message key="revoke"/></a>
											</c:when>
											<c:otherwise>
												<font color="gray"><fmt:message key="revoke"/></font>
											</c:otherwise>
										</c:choose></td>
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
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
$(function(){
	
	baguetteBox.run('.baguetteBoxOne', {
        buttons: false
    });
	
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
			changePhotoPage(n);
			//this.selectPage(n);
			return false;
		}
	});
});
</script>