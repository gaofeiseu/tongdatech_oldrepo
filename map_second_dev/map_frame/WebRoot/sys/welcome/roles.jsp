<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${application.projName}</title>

		<meta http-equiv=Content-Type content=text/html;charset=GBK>

		<link href="weblib/css/main.css" rel="stylesheet" type="text/css">
		<link href="weblib/css/roles.css" rel="stylesheet" type="text/css">
		<link href="weblib/css/head.css" rel="stylesheet" type="text/css">
		<link rel="shortcut icon" href="${application.projIcon}"/>
		<!-- 支持IE6 PNG图片透明 -->
        <!--[if lte IE 6]>
        <script src="weblib/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
            <script type="text/javascript">
                DD_belatedPNG.fix('div, ul, img, li, input , a');
            </script>
        <![endif]--> 		
		<script type="text/javascript">
		 function login(user_id,role_id,role_name,brch_no,brch_name,brch_flag,role_flag,brch_mode,area_no,area_name){
			 if(brch_flag!=1)alert("机构被注销,不能以"+brch_name+"的"+role_name+"角色进入系统!");
			 else if(role_flag!=1)alert("角色被注销,不能以"+role_name+"角色进入系统!");
			 else{
			 //window.location.href="/welcome.action?user_id="+user_id+"&role_id="+role_id+"&role_name="+role_name+"&brch_no="+brch_no+"&brch_name="+brch_name+"&brch_level="+brch_level;
			 document.getElementById("user_id").value=user_id;
			 document.getElementById("role_id").value=role_id;
			 document.getElementById("role_name").value=role_name;
			 document.getElementById("brch_no").value=brch_no;
			 document.getElementById("brch_name").value=brch_name;
			 document.getElementById("brch_mode").value=brch_mode;
			 document.getElementById("area_no").value=area_no;
			 document.getElementById("area_name").value=area_name;
			 document.forms["fm"].submit();
			 }
		 }
		</script>


	</head>
	<body leftmargin="0" topmargin="0">
	    
		<div id=head>
		   <div id="logo"/ style="background-image: url(${application.projLogoImg});"></div>
		   <div id="info" style="position:absolute;	right:20px;	top:52px;z-index:20;">
           	  <ul class="MM">
                 <li>欢迎:&nbsp;&nbsp;${session.userInfo.nick_name}&nbsp;&nbsp;</li>
                 <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
                 <li><a href="/loginout.action" >退出</a></li>
             </ul>
           </div>
		</div>
		<form id="fm" action="welcome.action" method="post">
		<input type="hidden" id="user_id" name="user_id" />
		<input type="hidden" id="role_id" name="role_id" />
		<input type="hidden" id="role_name" name="role_name" />
		<input type="hidden" id="brch_no" name="brch_no" />
		<input type="hidden" id="brch_name" name="brch_name" />	
		<input type="hidden" id="brch_mode" name="brch_mode" />
		<input type="hidden" id="area_no" name="area_no" />	
		<input type="hidden" id="area_name" name="area_name" />
		</form>
		<div id=body>
			<h3 class="category ">
				角色<span>(<s:property value="roles.size" />)</span>
			</h3>
			<s:iterator value='roles' id="role" status="st">

				<div class="grid">
              
					<div class="wowcharacter">

						<a href="###" onclick="javascript:login('${role.user_id}','${role.role_id}','${role.role_name}','${role.brch_no}','${role.brch_name}','${role.brch_flag}','${role.role_flag}','${role.brch_mode}','${role.area_no}','${role.area_name}');" 
						        class="icon-frame frame-56 thumbnail"> <img
								width="56" height="56" src="<s:property value="#role.role_img"/>" alt="" /> </a>
						<a href="###" onclick="javascript:login('${role.user_id}','${role.role_id}','${role.role_name}','${role.brch_no}','${role.brch_name}','${role.brch_flag}','${role.role_flag}','${role.brch_mode}','${role.area_no}','${role.area_name}');" 
						         class="al"> <strong><s:property value="#role.brch_name"/></strong> </a>
						<br />
						<s:property value="#role.role_name"/>
						<br />
						
						<div   class="grayfont">NO.<s:property value="#st.index+1"/></div>
						<s:if test="#role.brch_flag!=1||#role.role_flag!=1">
						 <div  style="float:left;"><img src="weblib/img/luck.gif"/></div>
						</s:if>
						<span class="clear">
							<!-- -->
						</span>
					</div>
				</div>

			</s:iterator>
		</div>
		<div>
		  
		</div>

	</body>
</html>
