<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="x-ua-compatible" content="ie=9"/>
    <title>水利信息化应用管理平台</title>
    <link href="${ctx}/content/skin/css/core/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.enplaceholder.js"></script>
    <style>
       body { margin: 0; padding: 0; overflow: auto; font-size: 12px; color: #383939; font-family:微软雅黑; background:url(${ctx}/content/skin/css/images/login/bj.jpg) no-repeat bottom center #48c5e8; -webkit-text-size-adjust: none; height: 100%; overflow: hidden; }
      .msg_error{width:354px; height:25px; float:left;padding-left:28px;}
       </style>
       
  <script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"49817",secure:"50479"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script></head>
  
  <body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc2-33" data-genuitec-path="/BJ_YT/src/main/webapp/login.jsp">
      <div class="login" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc2-33" data-genuitec-path="/BJ_YT/src/main/webapp/login.jsp">
		<div class="login_logo"></div>
		<div class="login_title"><fmt:message key="userLogin" /></div>
		<div class="login_content">
		<div class="msg_error"><label id="pwderror" style="display:none;color:red;float:left;"></label></div>
		<ul>
		<li><input id="username" name="username" type="text"  placeholder="<fmt:message key="account"/>" class="user" border="0"  value=""/></li>
		<li><input id="password" name="password" type="password"  placeholder="<fmt:message key="password"/>" class="password" value=""/>
		</li>
		<li style="height:50px;"><input id="remember" name="remember" type="checkbox" style="margin-left:50px;">&nbsp;<fmt:message key="rememberPwd"></fmt:message></input></li>
		<li><input name="" type="button" class="login_button" id="login_button" value="<fmt:message key="signIn"/>" onclick="login()"/></li>
		</ul>
		</div>
    </div>
    <div class="login_bottom"><%-- <fmt:message key="loginRemark"/> --%>
    建议您使用IE9+、FireFox、Google Chrome，分辨率1280*800及以上获得更好用户体验
    </div>
  </body>
  <script type="text/javascript">
	$(function() {  
	   var userLogin ="<fmt:message key='userLogin' />";
       if(userLogin == "User login"){
    	  $(".login_logo").css("background","url(content/skin/css/images/login/en/logo.png) no-repeat center top");
       }else{
    	  $(".login_logo").css("background","url(content/skin/css/images/login/logo.png) no-repeat center top"); 
       }
	      
		//记住密码功能
        var str = getCookie("loginInfo");
		if(str!="")
		{
	       str = str.substring(1,str.length-1);
	       var username = str.split(",")[0];
	       var password = str.split(",")[1];
	       var flagRemember = str.split(",")[2];
	       //自动填充用户名和密码
	       $("#username").val(username);
	       $("#password").val(password);
	       
	       if(flagRemember==1){
	       	$("#remember").attr("checked",true);
	       }
		}else{
			$("#remember").removeAttr("checked");
		}
  		//回车登录
        $("#username").keyup(function(event){
          if(event.keyCode == 13){  
            login();  
          }
        });
        $("#password").keyup(function(event){
          if(event.keyCode == 13){  
            login();  
          }
        });
      //通过插入元素模拟placeholder
      $('#username, #password').placeholder({isUseSpan:true});
      $('#username').focus();
    }); 
	
  function login() {
	    var flagRemember = 0;
	    if($("#remember").attr("checked")=="checked"){
	    	flagRemember = 1;
	    }
		$("#login_button").removeAttr("onclick");
		$("#login_button").val("<fmt:message key='loggingIn'/>...");
		document.getElementById( "pwderror" ).style.display = "none";
		var turl;
		var tdata;
		var username = $("#username").val();
		if(username=="<fmt:message key='account'/>"){
			username = "";
		}
		var password = $("#password").val();
		if(password=="<fmt:message key='password'/>"){
			password = "";
		}
		if(username=="" || password==""){
			$("#pwderror").html("<fmt:message key='noEmpty'/>");
			document.getElementById( "pwderror" ).style.display = "inline";
			$("#login_button").attr("onclick","login()");
			$("#login_button").val("<fmt:message key='logOn'/>");
			return;
		}
		turl = '<%=basePath%>/userLogin.do';
		
		tdata = {
			userName : username,
			passWord : password,
			flagRemember : flagRemember
		};
		$.ajax({
			url : turl,
			type : 'POST',
			data : tdata,
			dataType : 'json',
			cache : false,
			success : function(data) {
				if (!data.success) {
					$("#pwderror").html(data.msg);
					document.getElementById( "pwderror" ).style.display = "inline";
					$("#login_button").attr("onclick","login()");
					$("#login_button").val("<fmt:message key='logOn'/>");
					return;
				} else {
					if(data.isSimplePwd){
						
						$("#pwderror").html("<fmt:message key='pwdHint'/>");
						document.getElementById( "pwderror" ).style.display = "inline";
						setTimeout("reload()",500);
					}else{
						reload();
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(XMLHttpRequest.status);
				$("#login_button").attr("onclick","login()");
				$("#login_button").val("<fmt:message key='logOn'/>");
			}
		});
	}
    function reload(){
		window.location.href = '<%=basePath%>';
	}
    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    }
  </script>
</html>
