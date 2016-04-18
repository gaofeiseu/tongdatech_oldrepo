<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="/mapfiles/css/default.css" rel="stylesheet" type="text/css" />
		<title>Map</title>
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapmain.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_markers_add.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_initmain.js" charset="GBK"></script>
		<script type="text/javascript">
			var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
			var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
			var map;
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}')" class="easyui-layout">
		 <div id="map_canvas"  style="z-index:9;float:right; width:100%; height:100%;  "></div>
		 <!-- 
		 <div class="map_markers_show" style="z-index:50;position:absolute;buttom:0px;right:250px;width:57px;height:57px;background-image:url(/mapfiles/markers3/bubble.png);background-position:0 0;" onclick="changeToSa();"></div>
		  -->
	</body>
</html>