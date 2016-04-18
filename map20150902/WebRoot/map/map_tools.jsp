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
		<title>地图工具</title>
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapmain.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_tools.js" charset="GBK"></script>
		<script type="text/javascript">
			var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
			var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
			var map;// 全局地图变量
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');" class="easyui-layout">
		<div data-options="region:'west',split:true" title="工具栏" style="width: 132px;">
			<fieldset>
				<legend align="center">测距</legend>
				<div align="center"><img id="getD" src="/mapfiles/markers3/getDistance2-64-b.png" onclick="changeImgTo(this.src);"></div>
			</fieldset>
			<fieldset>
				<legend align="center">测面积</legend>
				<div align="center"><img id="getA" src="/mapfiles/markers3/getDistance-64-b.png" onclick="changeImgTo(this.src);"></div>
			</fieldset>
			<fieldset>
				<legend align="center">截图</legend>
				<div align="center"><img id="getS" src="/mapfiles/markers3/save_screen-64-b.png" onclick="changeImgTo(this.src);"></div>
			</fieldset>
			<fieldset>
				<legend align="center">查看经纬度</legend>
				<div align="center"><img id="getLatLng" src="/mapfiles/markers3/ee11ee1_64-b.png" onclick="changeImgTo(this.src);"></div>
			</fieldset>
			<hr>
			<div id="div_status" align="center" style="width: 120px;word-break:normal;">
				
			</div>
			<div id="div_msg" align="center" style="width: 120px;word-break:normal;">
				
			</div>
		</div>
		<div data-options="region:'center',split:true" title="地图" style="background: #fafafa; overflow: hidden;">
			<div id="map_canvas"  style="z-index:9;float:right; width:100%; height:100%;"></div>
		</div>
	</body>
</html>