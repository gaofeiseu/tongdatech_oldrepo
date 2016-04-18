<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>百度离线版DEMO</title>
		<script type="text/javascript" src="/baidu_map/js/apiv1.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/baidu_map/css/bmap.css"/>
	</head>
	<body>
		<div style="width: 700px; height: 450px;border: 1px solid gray" id="container"></div>
		<!--   -->
	</body>
</html>
<script type="text/javascript">
	var map = new BMap.Map("container",{mapType: BMAP_NORMAL_MAP});      //设置卫星图为底图
	var point = new BMap.Point(116.404, 39.915);    // 创建点坐标
	map.centerAndZoom(point,5);                     // 初始化地图,设置中心点坐标和地图级别。
	map.addControl(new BMap.MapTypeControl());
	map.addControl(new BMap.NavigationControl());
	map.enableScrollWheelZoom();                  // 启用滚轮放大缩小。
	map.enableKeyboard();                         // 启用键盘操作。  
	//map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
</script>
