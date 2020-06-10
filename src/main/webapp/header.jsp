<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div class="top">
<div class="top_content">
<div class="logo"></div>
<ul class="button">
 <c:forEach items="${menuList}" var="item" varStatus="vs">
	 <c:choose>
		 <c:when test="${item.id == '1122f4ebb1a54bc29de413df54739f11'}">
			 <li id="hqlinedata" class='<c:if test="${vs.index==1?'sell':''}" ></c:if>' style="cursor: pointer;"><a href="javascript:void(0)" onclick="showContent('${item.menuurl}?menuId=${item.id}','contain',{nodeIds:'0066668806',isDevice:true,rainRange:null,query_beginTime:null,query_beginTime_hour:0,query_endTime:null,query_endTime_hour:23,showOnLineDevice:false,query_stcdName:null,sttp:null})">
				 <img src="${ctx}/content/skin/css/images/menu/nav/${item.menuicon}.png" width="45" height="45" />
				 <br />${item.menuname}</a></li>
		 </c:when>
		 <c:otherwise>
			 <li class='<c:if test="${vs.index==1?'sell':''}" ></c:if>' style="cursor: pointer;"><a href="javascript:void(0)" onclick="showContent('${item.menuurl}?menuId=${item.id}','contain')"><img src="${ctx}/content/skin/css/images/menu/nav/${item.menuicon}.png" width="45" height="45" /><br />${item.menuname}</a></li>
		 </c:otherwise>
	 </c:choose>
</c:forEach>
</ul>
<div class="top_user">
<div style="color:#fff;text-align:right;margin-right: 10px;"> 
<div style="width:100%;/* float:right; *//* border:1px solid red; */"> 
	<c:if test="${hasNotice==true}">
	    <a class="notice_a" onclick="turnToNotice()" href="javascript:void(0)" style="font-size: 12px;"><fmt:message key="officialNews"/><span class="badge" style="display:none"></span></a>
	    <img src="${ctx}/content/skin/css/images/header_nav.jpg" style="margin-left:10px;margin-right:10px;">
	</c:if>
	<c:if test="${hasAlarm==true}">
	    <a class="alarm_a" onclick="turnToAlarm()" href="javascript:void(0)" style="font-size: 12px;"><fmt:message key="alarmInformation"/><span class="badge" style="display:none"></span></a>
	    <img src="${ctx}/content/skin/css/images/header_nav.jpg" style="margin-left:10px;margin-right:10px;">
	</c:if>
    <a onclick="editionInfo()" href="javascript:void(0)" style="font-size: 12px;"><fmt:message key="versionInformation"/></a>
</div>
</div>
<div style="margin-top:8px;float:right;">
<div class="top_user_left"></div>
<div class="top_user_med">
<ul>
	<li id="btn-nav-child">
		<a href="javascript:void(0);" onclick="showChildNav()">
			<img src="${ctx}/content/skin/css/images/user.png" width="10" height="12" />&nbsp;${login_user.username }<span class="caret" style="margin-left: 5px;"></span></a>
		<div id="nav-child" style="z-index: 50;">
            <ul>
           		<li style=" border-right: none;"><a href="javascript:void(0);" onclick="changePwd('${login_user.id}')"><img src="${ctx}/content/skin/css/images/password.png" width="11" height="14"/>&nbsp;<fmt:message key="modifyPassword"/></a></li>
            </ul>
        </div>
	</li>
<li style="border-right:0px;"><a href="javascript:void(0);" onclick="logout()"><img src="${ctx}/content/skin/css/images/exit.png" width="12" height="13"/>&nbsp;<fmt:message key="retreatSafely"/></a></li>
</ul>
</div>
<div class="top_user_right"></div>
</div>
</div>
</div>
</div>
 <!-- 用户所属企业标识 -->
  <input type="hidden" id="enterpriseId" value="${login_user.enterpriseid }" >
  <input type="hidden" id="user_role_flag" value="${login_user.sysrole.enabledstate}" >
  <input type="hidden" id="issupermanager" value="${login_user.issupermanager}" >
<script>
var issupermanager = $("#issupermanager").val();
$(function () {
	
    var userLogin ="<fmt:message key='userLogin' />";
    if(userLogin=="User login"){
   	  $(".top_content .logo").css("background","url(content/skin/css/images/logoEN.png) no-repeat");
    }else{
   	  $(".top_content .logo").css("background","url(content/skin/css/images/logo.png) no-repeat"); 
    }
       
	$("#nav-child").width(($("#nav-child").parent().width()<130?130:$("#nav-child").parent().width())+6);
	
    $(".button  li a").click(function () {
        var m = $(this), s = m.siblings();
        m.addClass("sell");
        m.parent().siblings().children("a").removeClass("sell");
    }); 
    //默认载入第一个菜单
    if(${fn:length(menuList)>1 }){
    	$(".button li a").eq(0).click();
    }else{
    	$(".button li a:first").click();
    } 
    
    initNoticeAndAlarmRefreshJS();
    
	$("#btn-nav-child").hover(function() {
			showChildNav(true);
		}, function() {
			showChildNav(false);
	});
});
	function logout() {
		$.get("logout.do", function(data) {
			top.location.reload();
		}, "json");
	}
	function changePwd(userId) {
		showChildNav(false);
		if (issupermanager == 'true') {
			$.Popup.create({
				title : "提示",
				content : "超级管理员密码禁止修改!"
			});
		} else {
			var contentMsg = {
				title : "<fmt:message key='changePassword'/>",
				url : "sysUser/changePwd.do",
				width : "500",
				paraData : {
					userId : userId
				},
				requestMethod : 'ajax',
				tbar : [ {
					text : "<fmt:message key='determine'/>",
					clsName : "homebg popup-confirm",
					handler : function(thisPop) {
						userFnChangePwdSubmit(thisPop);
					}
				} ]
			};
			$.Popup.create(contentMsg);
		}
	}

	function showChildNav(show) {
		if (show != undefined) {
			if (show)
				$("#nav-child").slideDown();
			else
				$("#nav-child").slideUp();
		} else {
			if ($("#nav-child").is(":hidden")) {
				$("#nav-child").slideDown();
			} else {
				$("#nav-child").slideUp();
			}
		}
	}

	function initNoticeAndAlarmRefreshJS() {
		if ($("a.notice_a").length > 0) {
			//getUnReadNoticeCount();
			//setInterval( getUnReadNoticeCount ,20*1000);
		}
		if ($("a.alarm_a").length > 0) {
			//getTodayAlarmCount();
			//setInterval( getTodayAlarmCount ,20*1000);
		}
	}

	function getUnReadNoticeCount() {
		$.getJSON("sysAnnounce/getUnReadNoticeCount.do", function(data) {
			if (data.success) {
				var count = data.obj;
				if (count == undefined || count == 0) {
					$("a.notice_a span").hide();
				} else {
					$("a.notice_a span").text(count);
					$("a.notice_a span").show();
				}
			} else {
				$("a.notice_a span").hide();
			}
		});
	}
	function getTodayAlarmCount() {
		$.getJSON("stAlarmInfo/getTodayAlarmCount.do", function(data) {
			if (data.success) {
				var count = data.obj;
				if (count == undefined || count == 0) {
					$("a.alarm_a span").hide();
				} else {
					$("a.alarm_a span").text(count);
					$("a.alarm_a span").show();
				}
			} else {
				$("a.alarm_a span").hide();
			}
		});
	}

	function turnToDevice() {
		$(".top ul.button li a[onclick*='stStbprpB']").click();
	}

	function turnToAlarm() {
		$(".top ul.button li a[onclick*='stAlarmInfo']").click();
	}

	function turnToNotice() {
		/* var s = $(".top ul.button li a[onclick*='sysManage']");
		s.addClass("sell");
		var m = s.parent().siblings().find("a");
		m.removeClass("sell"); */

		$(".top ul.button li a[onclick*='sysAnnounce']").click();
		//showContent('sysManage/index.do?menuId=867c52b699204045a39322b8b9176e02&menu=sysAnnounce','contain')
	}
	/**
	 * 版本信息
	 */
	function editionInfo() {
		var width = 500;
		var title ="<fmt:message key='versionInformation'/>";
		if(title=="Version")
			width = 650;
		var contentMsg = {
			title : title,
			width : width,
			url : "versionInfo.do",
			paraData : {},
			requestMethod : 'ajax',
			tbar : [ {
				text : "<fmt:message key='determine'/>",
				clsName : "homebg popup-confirm",
				handler : function(thisPop) {
					$.Popup.close(thisPop);
				}
			} ]
		};
		$.Popup.create(contentMsg);
	}
</script>