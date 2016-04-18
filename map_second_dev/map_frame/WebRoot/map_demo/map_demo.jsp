<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=GBK>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<link href="mapfiles/css/default.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="/map_demo/js/map_config.js"></script>
		<script type="text/javascript" src="/map_demo/js/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map_demo/js/mapmain.js"></script>
		<script type="text/javascript" src="/map_demo/js/mapdemo.js"></script>
		<script type="text/javascript">
			var map;
			$(function(){
				initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');
				MapDemo.init(map,google.maps);
			});
			var options;
			function add_point(){
				options = {
					draggable:false,
					icon_uri:"/mapfiles/markers/0marker_green.png",
					text:"test_shown",
					have_infowindow:true,
					html:"<div>姓名：张三</div><br/><div>国籍：中国</div>"
				};
				MapDemo.add_points(options);
			}
			function add_line(){
				var st = {
					draggable:false,
					icon_uri:"/mapfiles/markers/0marker_green.png",
					text:"st",
					have_infowindow:true,
					html:"<div>st</div>"
				};
				var ed = {
					draggable:false,
					icon_uri:"/mapfiles/markers/0marker_green.png",
					text:"ed",
					have_infowindow:true,
					html:"<div>ed</div>"
				};
				var point_array = [st,ed];
				var point_index_array = [1,2];
				options = {
					strokeColor:'#F00000',
					strokeOpacity:1.0,
					strokeWeight:8,
					can_click:false,
					have_points:true,
					point_index_array:point_index_array,
					point_array:point_array
				};
				MapDemo.add_lines(options);
			}
			function add_area(){
				var point1 = {
					draggable:false,
					icon_uri:"/mapfiles/markers/0marker_green.png",
					text:"point1",
					have_infowindow:true,
					html:"<div>point1</div>"
				};
				var point2 = {
					draggable:false,
					icon_uri:"/mapfiles/markers/0marker_green.png",
					text:"point2",
					have_infowindow:true,
					html:"<div>point2</div>"
				};
				var point_array = [point1,point2];
				var point_index_array = [1,3];
				var open_index = 2;
				options = {
					strokeColor:'#3300CC',
					strokeOpacity:0.8,
					strokeWeight:4,
					fillColor:'#990099',
					fillOpacity:0.3,
					can_edit:false,
					can_click:true,
					have_points:true,
					point_index_array:point_index_array,
					point_array:point_array,
					open_index:open_index
				};
				MapDemo.add_areas(options);
			}
			function clean_allmarkers(){
				MapDemo.hide_all_markers();
			}
		</script>
	</head>
	<body class="easyui-layout">
        <div data-options="region:'west',title:'地图操作区域'" style="width:300px;">
            <div>
				<input type="button" value="active add point for click(only once)" onclick="add_point();" />
			</div>
			<div>
				<input type="button" value="active add line(finish with double left click)" onclick="add_line();" />
			</div>
			<div>
				<input type="button" value="active add area(finish with double left click)" onclick="add_area();" />
			</div>
			<div>
				<input type="button" value="clean all markers on map" onclick="clean_allmarkers();" />
			</div>
        </div>
        <div data-options="region:'center',title:'地图显示区域'" >
        	<div id="map_canvas"  style="z-index:9;float:right; width:100%; height:100%;  "></div>
        </div>
	</body>
</html>