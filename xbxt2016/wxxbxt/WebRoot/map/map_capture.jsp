<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<applet code="com.tongdatech.map.applet.MapCapture.class"
	name="MapCapture" id="MapCapture" codebase="/map/applet/" width="0"
	height="0"> </applet>

<script type="text/javascript">
	function capture() {
		var mainFrame = window.parent.window.document.getElementById("main");

		//��ȡ��������Ͻ�����
		var browserIndex = {
			browserX : window.screenX,
			browserY : window.screenY
		};
		//alert("browserIndex.browserX:"+browserIndex.browserX+"browserIndex.browserY:"+browserIndex.browserY);

		//��ȡ�������С
		var browserSize = {
			browserWidth : window.outerWidth,
			browserHeight : window.outerHeight
		};
		//alert("browserSize.browserWidth:"+browserSize.browserWidth+"browserSize.browserHeight:"+browserSize.browserHeight);

		//��ȡ�ӿڴ�С
		var viewporSize = {
			viewporWidth : window.parent.window.document.documentElement.clientWidth,
			viewporHeight : window.parent.window.document.documentElement.clientHeight
		};
		//alert("viewporSize.viewporWidth:"+viewporSize.viewporWidth+"viewporSize.viewporHeight:"+viewporSize.viewporHeight);

		//��ȡmainFrame���Ͻ�������ӿ����Ͻ�(������)����
		var mainFrameRelCoord = {
			relCoordX : $(mainFrame).offset().left,
			relCoordY : $(mainFrame).offset().top
		};
		//alert("mainFrameRelCoord.relCoordX:"+mainFrameRelCoord.relCoordX+"mainFrameRelCoord.relCoordY:"+mainFrameRelCoord.relCoordY);

		//��ȡmap_canvas���Ͻ������mainFrame���Ͻ�(������)����
		var mapCanvasRelCoord = {
			relCoordX : $('#map_canvas').offset().left,
			relCoordY : $('#map_canvas').offset().top
		};
		//alert("mapCanvasRelCoord.relCoordX:"+mapCanvasRelCoord.relCoordX+"mapCanvasRelCoord.relCoordY:"+mapCanvasRelCoord.relCoordY);

		//��ȡmap_canvas��С
		var mapCanvasSize = {
			mapCanvasWidth : $('#map_canvas').width(),
			mapCanvasHeight : $('#map_canvas').height()
		};
		//alert("mapCanvasSize.mapCanvasWidth:"+mapCanvasSize.mapCanvasWidth+"mapCanvasSize.mapCanvasHeight:"+mapCanvasSize.mapCanvasHeight);

		//��������������ұ߿�͹�������С
		var browserBorder = (browserSize.browserWidth - viewporSize.viewporWidth) / 2;
		var browserHeadHeight = browserSize.browserHeight - browserBorder
				- viewporSize.viewporHeight;
		//alert("browserBorder:"+browserBorder+"browserHeadHeight:"+browserHeadHeight);

		//����map_canvas���Ͻ��������Ļ������
		var mapCanvasRelCoordScreen = {
			relCoordX : browserIndex.browserX + browserBorder
					+ mainFrameRelCoord.relCoordX + mapCanvasRelCoord.relCoordX,
			relCoordY : browserIndex.browserY + browserHeadHeight
					+ mainFrameRelCoord.relCoordY + mapCanvasRelCoord.relCoordY
		};
		//alert("mapCanvasRelCoordScreen.relCoordX:"+mapCanvasRelCoordScreen.relCoordX+"mapCanvasRelCoordScreen.relCoordY:"+mapCanvasRelCoordScreen.relCoordY);

		//���ض���
		var mapCanvasInfo = {
			indexX : mapCanvasRelCoordScreen.relCoordX,
			indexY : mapCanvasRelCoordScreen.relCoordY,
			width : mapCanvasSize.mapCanvasWidth,
			height : mapCanvasSize.mapCanvasHeight
		};

		//ִ��applet���н���
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
	<input type="button" id="capture" value="��ͼ" onclick="capture()" />
</div> -->