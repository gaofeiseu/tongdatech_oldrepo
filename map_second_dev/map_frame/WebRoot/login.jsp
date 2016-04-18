<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
	<head>
	   <title>${application.projName}</title>
    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
	
		<link rel="shortcut icon" href="${application.projIcon}" />
 
	 	<link rel="stylesheet" type="text/css" href="/weblib/js/jquery-easyui-1.3.5/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="/weblib/js/jquery-easyui-1.3.5/themes/icon.css">
	
        <link href="/weblib/css/main.css" rel="stylesheet" type="text/css">
		<link href="/weblib/css/head.css" rel="stylesheet" type="text/css">
 		<link href="/weblib/login/front.css" media="screen, projection" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.min.js"></script>
		<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/map_demo/js/map_config.js"></script>
		<script type="text/javascript" src="/map_demo/js/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map_demo/js/mapmain.js"></script>

		<script type="text/javascript">
 			var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
 			var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
	function doSubmit() {
		uname=document.getElementById('username').value;
	    if(uname==''){
		    document.getElementById("show_message").innerHTML="请输入用户名";
		    document.getElementById("username").value='';
		    document.getElementById("username").focus();
		    return false;
	    }   
	    var psd=document.getElementById('password').value;
	    if(psd==''){
		    document.getElementById("show_message").innerHTML="请输入密码";
		    document.getElementById("password").value='';
		    document.getElementById("password").focus();
		    return false;
		 }
		document.loginform.submit();
	}
	function reset() {
		window.location.href = "/";
	}

	function gopwsd(event) {
		if (event.keyCode == 13) {
			$("#password")[0].focus();
			return false;
		}
		return true;

	}
	function gosub(event) {
		if (event.keyCode == 13) {
			doSubmit();
		}
		return true;

	}
	function showLoginDlg(){
	$('#login_dlg').dialog('open');
	 
	}


     $(document).ready(function() {

            $(".signin").click(function(e) {          
				e.preventDefault();
				    document.getElementById("show_message").innerHTML="";
                $("fieldset#signin_menu").toggle();
				$(".signin").toggleClass("menu-open");
            });
			
			$("fieldset#signin_menu").mouseup(function() {
				return false
			});
			$(document).mouseup(function(e) {
				if($(e.target).parent("a.signin").length==0) {
					$(".signin").removeClass("menu-open");
					$("fieldset#signin_menu").hide();
				}
			});			
			
        });
</script>
	</head>
	<body leftmargin="0" topmargin="0"   onload="initialize('map_canvas',config.maxZoon,'','')" class="easyui-layout">
		<div id=head>
			<div id="logo" style="background-image: url(${application.projLogoImg}); "></div>
			<div id="info1" style="text-align: right; ">
				  <div id="topnav" class="topnav">  <a href="login" class="signin"><span>登录</span></a></div>
				  <fieldset id="signin_menu">
					<s:form action="login"  namespace="/" theme="simple" id="loginform" name="loginform"> 
				    <p>
				      <label for="username">用户名</label><span id="show_message" style="font-size: 12px;color: #DA1111;padding-left:20px;"></span> 
				      <input id="username" name="username" value="" title="username"  onfocus="this.select()" onkeydown="return gosub(event)" tabindex="4" type="text">
				      </p>
				      <p>
				        <label for="password">密&nbsp;&nbsp;码</label><span  style="font-size: 12px;color: #DA1111;padding-left:20px;"></span> 
				        <input id="password" name="password" value="" title="password"  onfocus="this.select()" onkeydown="return gosub(event)" tabindex="5" type="password">
				      </p>
				      <p class="remember">
				        <input id="signin_submit" value="登录" tabindex="6" onclick="doSubmit();" type="button">
				        <input id="signin_submit" value="重置" tabindex="6" type="reset">
				      </p>
				 </s:form>
				  </fieldset>
			</div>
		
		</div>
		<div id="online">
			<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action" target="_blank">工信部备案号: 苏ICP备10006317号</a>  版权所有：江苏省邮政电子商务有限公司
		</div>
	  	<div>
			 <div id="map_canvas"  style="z-index:9;float:right; width:100%; height:100%;  "></div>
			 
		</div>
 
 	 
	
	
	</body>
 
</html>
