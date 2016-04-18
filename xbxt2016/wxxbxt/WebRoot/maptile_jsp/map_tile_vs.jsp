<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
 	 	<link rel="stylesheet" type="text/css" href="/map/css/css.css">
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_query_tools.js"></script>	
		<script type="text/javascript">
		var marker_center_lat='${session.userInfo.lat}';
		var marker_center_lng='${session.userInfo.lng}';
		var url = config.url;
		var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
		var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
		var map;
 		window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
		<script type="text/javascript" src="/maptile_jsp/js/map_vs.js"></script>
	</head>
	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');"  class="easyui-layout">
		<div data-options="region:'center',split:true" title="地图" style="background: #fafafa; overflow: hidden;">
			<div id="map_canvas"  style=" width:100%; height:100%;  "></div>
		</div>
		<div data-options="region:'east',split:true" title="信息展示" style="width: 183px;">
			<fieldset>
              	<legend>
              		时间查询：
              	</legend>
              	<input type="button" id="btn1" name="btn1" value="开始获取经纬度" onClick="getLatLng();">
              	<div>ZOOM：</div>
              	<div><input type="text" id="text1" name="text1" ></input></div>
              	<div>LAT：</div>
              	<div><input type="text" id="text2" name="text2" ></input></div>
              	<div>LNG：</div>
              	<div><input type="text" id="text3" name="text3" ></input></div>
              	<div>X：</div>
              	<div><input type="text" id="text4" name="text4" ></input></div>
              	<div>Y：</div>
              	<div><input type="text" id="text5" name="text5" ></input></div>
			</fieldset>
		</div>
	</body>
</html>
