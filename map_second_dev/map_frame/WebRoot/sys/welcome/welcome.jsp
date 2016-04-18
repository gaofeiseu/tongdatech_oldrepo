<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>${application.projName}</title>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
<link rel="shortcut icon" href="${application.projIcon}" />
<script type="text/javascript">
</script>
</head>


<frameset id="all" rows="88,*"  frameborder="NO" border="0" framespacing="0" >
	<frame src="/wel_head" noresize="noresize" frameborder="0"  id="topFrame" name="topFrame" scrolling="no" marginwidth="0" marginheight="0"  />
  <frameset cols="200,*"   id="frame">
	<frame src="/menu_init" id="leftFrame" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0" scrolling="no"  />
	<frame src="/wel_main" id="main" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto"  />
	</frameset>
	<noframes>
  <body>您的浏览器无法处理框架！</body>
  </noframes>
</frameset>

</html>
