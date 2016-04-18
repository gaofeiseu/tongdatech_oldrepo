<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html>
<html>
  <head>
  
    
    <title>${application.projName}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="shortcut icon" href="${application.projIcon}" />
	<link href="/weblib/login/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.min.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {

		$("#username")[0].focus();

	});

	function doSubmit() {
		document.getElementById("username").value = html_encode(document.getElementById("username").value);
		document.getElementById("password").value = html_encode(document.getElementById("password").value);
		$("#loginform").submit();
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
	function html_encode(str)   
	{   
 	 var s = "";   
  	if (str.length == 0) return "";   
 	 s = str.replace(/&/g, "");   
 	 s = s.replace(/</g, "");   
 	 s = s.replace(/>/g, "");   
 	 s = s.replace(/ /g, "");   
 	 s = s.replace(/\'/g, "");   
 	 s = s.replace(/\"/g, "");   
 	 s = s.replace(/\n/g, "");
 	 //s = s.replace(/o/g, ""); 
 	 s = s.replace(/r/g, ""); 
 	  s = s.replace(/\=/g, "");  
  	return s;   
	}  
	
	</script>


	</head>
  
    

<body>

    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="/weblib/login/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left" style="background:url(${application.projLoginImg});"></div>
			  <div id="center_middle">
			       <s:form action="login"  namespace="/" theme="simple" id="loginform" name="loginform"> 
			       <div id="user1">用 户
			         <s:textfield id="username" name="username" label="username" cssStyle="font-size:14px" onfocus="this.select()" onkeydown="return gopwsd(event)"></s:textfield>
			       </div>
				   <div id="password1">密 码
				     <s:password id="password" name="password" label="password" cssStyle="font-size:14px" onfocus="this.select()" onkeydown="return gosub(event)"></s:password>
				   </div>

				   <div id="btn"><a id="sub" href="javascript:doSubmit();">登录</a><a href="javascript:reset();">清空</a></div>

			       </s:form>
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">  
			      <div id="inf">
                       <span class="inf_text">相关信息</span>
					   <span class="copyright">系统建议使用IE8以上或其他W3C标准浏览器进行浏览</span>
			      </div>
			  </div>
			  <div id="down_center">
			       <div id="error">
				      <s:fielderror cssStyle="color:red"></s:fielderror>
				   </div>
			  </div>		 
		 </div>

	</div>
</body>
</html>
