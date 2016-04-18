<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=UTF-8>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<link href="mapfiles/css/default.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="mapjs/map_config.js"></script>
		<script type="text/javascript" src="mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="mapjs/mapmain.js"></script>
		<script type="text/javascript" src="/map/mapjs/map-query-1.0.js"></script>
		<script type="text/javascript">
			var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1); 
		</script>
	</head>
	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');" class="easyui-layout">
		 <div id="map_canvas"  style="z-index:9;float:right; width:100%; height:100%;  "></div>
	</body>
</html>