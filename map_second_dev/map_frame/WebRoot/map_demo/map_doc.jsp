<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title></title>
		<script type="text/javascript" src="/map_demo/js/syntaxhighlighter_3.0.83/scripts/shCore.js"></script>
		<script type="text/javascript" src="/map_demo/js/syntaxhighlighter_3.0.83/scripts/shBrushJScript.js"></script>
		<script type="text/javascript" src="/map_demo/js/syntaxhighlighter_3.0.83/scripts/shBrushJava.js"></script>
		<script type="text/javascript" src="/map_demo/js/syntaxhighlighter_3.0.83/scripts/shBrushXml.js"></script>
		<link type="text/css" rel="stylesheet" href="/map_demo/js/syntaxhighlighter_3.0.83/styles/shCoreDefault.css"/>
		<script type="text/javascript">SyntaxHighlighter.all();</script>
	</head>
	<body>
		<h1>地图系统说明文档</h1>
		<h2>地图主要配置文件路径：</h2>
		<h3>/WebRoot/map/mapjs/map_config.js</h3>
		<pre class="brush: js;">
		//以下参数修改后需要浏览器重新加载才会生效
		var config=[];
		config.url = "/";//地图路径的起始相对路径，一般不需要修改
		config.maxZoon = 13;//地图显示层级的最大值
		config.minZoon = 1;//地图显示层级的最小值
		config.url_icon = "/mapfiles/";//标注图标的相对路径，一般不需要修改
		</pre>
		<h3>/WebRoot/map/mapjs/mapmain.js</h3>
		<pre class="brush: js;">
		//以下参数修改后需要浏览器重新加载才会生效
		function initialize(id, defaultZoom,lat,lng) {
			if(lat!="")
			{
				var myLatlng = new google.maps.LatLng(lat,lng);
			}else
			{
				var myLatlng = new google.maps.LatLng(32.0576115, 118.7843468);//在这个地方设置默认的地图显示中心点
			}
			//....
		}
		</pre>
		<h2>地图下载工具mapdownloader.jar的使用方法：</h2>
		<h3>/mapdownloader/mapdownloader.jar</h3>
		<h4>通过下面的代码，可以直接在windows命令行或linux上查看该下载工具的详细使用规则</h4>
		<pre class="brush: js;">
		java -jar mapdownloader.jar -help
		</pre>
		<h4>需要注意的是，当文件下载好之后，你需要在/WebRoot/WEB-INF/weblogic.xml中对图片地址进行设置</h4>
		<pre class="brush: xml;">
		<virtual-directory-mapping>
			<!-- 假设图片的保存路径是f:/map1234，其目录结构应为：map1234/mapabc map1234/mapSa map1234/mapSaDL --> 
        	<local-path>f:/map1234</local-path>
        	<!-- 需要改动的地方是上面的部分，下面不用改 -->
        	<url-pattern>/maptile/*</url-pattern>  
    	</virtual-directory-mapping>
		</pre>
		<h2>如果想把地图系统迁移到本框架的其他已有工程中，需要做什么？</h2>
		<pre class="brush: js;">
		1、拷贝src/map_demo文件夹
		2、拷贝WebRoot/map_demo文件夹
		3、拷贝WebRoot/mapdownloader文件夹
		4、拷贝WebRoot/mapfiles文件夹
		5、遵照上述的方法，修改weblogic.xml，使系统可以正常获取地图图片
		6、如果没有地图图片，请依然遵照上面的方法进行图片的下载。
		</pre>
		<h2>如果我想自己对地图系统进行开发，需要做什么？</h2>
		<pre class="brush: js;">
		1、在上一步完成之后，确保系统能够正常使用的情况下
		2、参照实例文件/WebRoot/map_demo/map_demo.jsp
			调用方法如下：
			//添加点，传入的参数示例
			var options = {
				draggable:false,//是否支持拖动
				icon_uri:"/mapfiles/markers/0marker_green.png",//标注展示的图片URI
				text:"test_shown",//鼠标移动到标注上的显示内容
				have_infowindow:true,//是否设置点击弹出infowindow，true or false
				html:"<div>姓名：张三</div><br/><div>国籍：中国</div>"//展示在infowindow上的html代码
			};
			MapDemo.add_points(options);
			//添加线，传入的参数示例
			var st = {//点的options，与上面的示例一致
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
			var point_array = [st,ed];//想要展示在线上的点的集合
			var point_index_array = [1,2];//想要展示在线上的点对应先上的点的序号，如此示例，传入的两个点标注将展示在线上的第一个折点和第二个折点上
			var options = {
				strokeColor:'#F00000',//线的颜色
				strokeOpacity:1.0,//线的透明度 0到1的double
				strokeWeight:8,//线的粗细，越大越粗，int
				can_click:false,//是否支持点击
				have_points:true,//定义是否包含用于展示的点标注，如果设置为false，则下面两个参数就不需要了
				point_index_array:point_index_array,
				point_array:point_array
			};
			MapDemo.add_lines(options);
			//添加面，参数的含义分别为：
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
			var open_index = 1;//如此时，将在point1上打开infowindow
			var options = {
				strokeColor:'#3300CC',//面的边线的颜色
				strokeOpacity:0.8,//面的边线的透明度
				strokeWeight:4,//面的边线的粗细
				fillColor:'#990099',//面的填充颜色
				fillOpacity:0.3,//面的填充透明度
				can_edit:false,//是否支持二次编辑
				can_click:false,//是否可以点击，如果设置为true,则点击面时将会在open_index指定的point上打开infowindow
				have_points:true,
				point_index_array:point_index_array,
				point_array:point_array,
				open_index:open_index
			};
			MapDemo.add_areas(options);
			//清除所有已经添加的标注内容
			MapDemo.hide_all_markers();
		</pre>
	</body>
</html>