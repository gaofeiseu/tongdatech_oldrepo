<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
	<head>
		<title></title>

		<meta http-equiv=Content-Type content=text/html;charset=gb2312>
		<!--  <meta http-equiv="refresh" content="60"> -->
		<!--[if lte IE 6]>
        <script src="weblib/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
        <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
        
        </script>
        <![endif]-->
        <link href="weblib/css/main.css" rel="stylesheet" type="text/css">
		<link href="weblib/css/head.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.min.js"></script>

		<script type="text/javascript">
	
	online();
    window.setInterval("online()", 60*1000);
    function online(){

        $.ajax({
          type: "POST",
          url: "wel_online",
          success: function(result){
            $("#count").html(result.online);
            if(window.parent.main.sys_staylogin){
               window.parent.main.sys_staylogin(Number(result.remain_time));
            };
            
	      },
	      headers: {"NoTimeOut" : "true"},
          dataType: "json"
       });
    }
	function MenuShow(type) {

		if (type == 1) {
			parent.document.getElementById("frame").cols = "0,*";
			document.getElementById("show").style.visibility="visible";
			document.getElementById("hide").style.visibility="hidden";
		} else {
			parent.document.getElementById("frame").cols = "200,*";
			document.getElementById("show").style.visibility="hidden";
			document.getElementById("hide").style.visibility="visible";

		}

	}
	function wel(){
        window.top.location.href="welcome.action";
    }
	function loginoff(){
		window.top.location.href="loginoff.action";
	}
	function loginout(){
		window.top.location.href="loginout.action";
	}
</script>
	</head>
	<body leftmargin="0" topmargin="0" onload="	MenuShow(0);">
		<div id=head>
			<div id="logo"/ style="background-image: url(${application.projLogoImg});"></div>
			<div id="info">
				<ul class="MM">

					<li title="${session.userInfo.user_name}|${session.userInfo.brch_no}|${session.userInfo.role_id}">
						欢迎:&nbsp;&nbsp;${session.userInfo.nick_name}&nbsp;&nbsp;(${session.userInfo.brch_name}|${session.userInfo.role_name})
					</li>
					<li>
						<a href="###" onclick="javascript:wel();">首页</a>
					</li>
					<li>
						<a href="user_changePwd" target="main">修改密码</a>
					</li>
					<li>
						<a href="###" onclick="javascript:loginoff();">注销</a>
					</li>
					<li>
						<a href="loginout.action" onclick="javascript:loginout();">退出</a>
					</li>
				</ul>

			</div>
		</div>
		<div id="online">
			<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action" target="_blank">工信部备案号: 苏ICP备10006317号</a>  版权所有：江苏省邮政电子商务有限公司     在线人数:<span id="count"></span>
		</div>
		<div id="arrow">
			<span id="show"><a href="###"
				onclick="javascript:MenuShow(0);"><img alt=""
						src="weblib/img/arrowRight.png">
			</a>
			</span>
			<span id="hide"><a href="###"
				onclick="javascript:MenuShow(1);"><img alt=""
						src="weblib/img/arrowLeft.png">
			</a>
			</span>
		</div>
	</body>
</html>
