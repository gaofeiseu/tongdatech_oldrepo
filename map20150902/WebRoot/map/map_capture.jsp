<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<applet code="com.tongdatech.map.applet.MapCapture.class"
	name="MapCapture" id="MapCapture" codebase="/map/applet/" width="0"
	height="0"> </applet>

<script type="text/javascript">
	function capture() {
		var mainFrame = window.parent.window.document.getElementById("main");

		//获取浏览器左上角坐标
		var browserIndex = {
			browserX : window.screenX,
			browserY : window.screenY
		};
		//alert("browserIndex.browserX:"+browserIndex.browserX+"browserIndex.browserY:"+browserIndex.browserY);

		//获取浏览器大小
		var browserSize = {
			browserWidth : window.outerWidth,
			browserHeight : window.outerHeight
		};
		//alert("browserSize.browserWidth:"+browserSize.browserWidth+"browserSize.browserHeight:"+browserSize.browserHeight);

		//获取视口大小
		var viewporSize = {
			viewporWidth : window.parent.window.document.documentElement.clientWidth,
			viewporHeight : window.parent.window.document.documentElement.clientHeight
		};
		//alert("viewporSize.viewporWidth:"+viewporSize.viewporWidth+"viewporSize.viewporHeight:"+viewporSize.viewporHeight);

		//获取mainFrame左上角相对于视口左上角(父容器)坐标
		var mainFrameRelCoord = {
			relCoordX : $(mainFrame).offset().left,
			relCoordY : $(mainFrame).offset().top
		};
		//alert("mainFrameRelCoord.relCoordX:"+mainFrameRelCoord.relCoordX+"mainFrameRelCoord.relCoordY:"+mainFrameRelCoord.relCoordY);

		//获取map_canvas左上角相对于mainFrame左上角(父容器)坐标
		var mapCanvasRelCoord = {
			relCoordX : $('#map_canvas').offset().left,
			relCoordY : $('#map_canvas').offset().top
		};
		//alert("mapCanvasRelCoord.relCoordX:"+mapCanvasRelCoord.relCoordX+"mapCanvasRelCoord.relCoordY:"+mapCanvasRelCoord.relCoordY);

		//获取map_canvas大小
		var mapCanvasSize = {
			mapCanvasWidth : $('#map_canvas').width(),
			mapCanvasHeight : $('#map_canvas').height()
		};
		//alert("mapCanvasSize.mapCanvasWidth:"+mapCanvasSize.mapCanvasWidth+"mapCanvasSize.mapCanvasHeight:"+mapCanvasSize.mapCanvasHeight);

		//计算浏览器左下右边框和工具栏大小
		var browserBorder = (browserSize.browserWidth - viewporSize.viewporWidth) / 2;
		var browserHeadHeight = browserSize.browserHeight - browserBorder
				- viewporSize.viewporHeight;
		//alert("browserBorder:"+browserBorder+"browserHeadHeight:"+browserHeadHeight);

		//计算map_canvas左上角相对于屏幕的坐标
		var mapCanvasRelCoordScreen = {
			relCoordX : browserIndex.browserX + browserBorder
					+ mainFrameRelCoord.relCoordX + mapCanvasRelCoord.relCoordX,
			relCoordY : browserIndex.browserY + browserHeadHeight
					+ mainFrameRelCoord.relCoordY + mapCanvasRelCoord.relCoordY
		};
		//alert("mapCanvasRelCoordScreen.relCoordX:"+mapCanvasRelCoordScreen.relCoordX+"mapCanvasRelCoordScreen.relCoordY:"+mapCanvasRelCoordScreen.relCoordY);

		//返回对象
		var mapCanvasInfo = {
			indexX : mapCanvasRelCoordScreen.relCoordX,
			indexY : mapCanvasRelCoordScreen.relCoordY,
			width : mapCanvasSize.mapCanvasWidth,
			height : mapCanvasSize.mapCanvasHeight
		};

		//执行applet进行截屏
		document.MapCapture
				.mapCapture(mapCanvasInfo.indexX, mapCanvasInfo.indexY,
						mapCanvasInfo.width, mapCanvasInfo.height);
	}

	function combine() {
		var softwarePath = "D:\\software_setup\\PhotoStitch";
		var softwareName = "stitch.exe";
		document.MapCapture.mapCombine(softwarePath, softwareName);
	}
</script>

<!-- <div style="margin: 20px 10px 10px 20px ;width:150px;overflow: hidden">
	<input type="button" id="capture" value="截图" onclick="capture()" />
</div> -->