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
		<h1>��ͼϵͳ˵���ĵ�</h1>
		<h2>��ͼ��Ҫ�����ļ�·����</h2>
		<h3>/WebRoot/map/mapjs/map_config.js</h3>
		<pre class="brush: js;">
		//���²����޸ĺ���Ҫ��������¼��زŻ���Ч
		var config=[];
		config.url = "/";//��ͼ·������ʼ���·����һ�㲻��Ҫ�޸�
		config.maxZoon = 13;//��ͼ��ʾ�㼶�����ֵ
		config.minZoon = 1;//��ͼ��ʾ�㼶����Сֵ
		config.url_icon = "/mapfiles/";//��עͼ������·����һ�㲻��Ҫ�޸�
		</pre>
		<h3>/WebRoot/map/mapjs/mapmain.js</h3>
		<pre class="brush: js;">
		//���²����޸ĺ���Ҫ��������¼��زŻ���Ч
		function initialize(id, defaultZoom,lat,lng) {
			if(lat!="")
			{
				var myLatlng = new google.maps.LatLng(lat,lng);
			}else
			{
				var myLatlng = new google.maps.LatLng(32.0576115, 118.7843468);//������ط�����Ĭ�ϵĵ�ͼ��ʾ���ĵ�
			}
			//....
		}
		</pre>
		<h2>��ͼ���ع���mapdownloader.jar��ʹ�÷�����</h2>
		<h3>/mapdownloader/mapdownloader.jar</h3>
		<h4>ͨ������Ĵ��룬����ֱ����windows�����л�linux�ϲ鿴�����ع��ߵ���ϸʹ�ù���</h4>
		<pre class="brush: js;">
		java -jar mapdownloader.jar -help
		</pre>
		<h4>��Ҫע����ǣ����ļ����غ�֮������Ҫ��/WebRoot/WEB-INF/weblogic.xml�ж�ͼƬ��ַ��������</h4>
		<pre class="brush: xml;">
		<virtual-directory-mapping>
			<!-- ����ͼƬ�ı���·����f:/map1234����Ŀ¼�ṹӦΪ��map1234/mapabc map1234/mapSa map1234/mapSaDL --> 
        	<local-path>f:/map1234</local-path>
        	<!-- ��Ҫ�Ķ��ĵط�������Ĳ��֣����治�ø� -->
        	<url-pattern>/maptile/*</url-pattern>  
    	</virtual-directory-mapping>
		</pre>
		<h2>�����ѵ�ͼϵͳǨ�Ƶ�����ܵ��������й����У���Ҫ��ʲô��</h2>
		<pre class="brush: js;">
		1������src/map_demo�ļ���
		2������WebRoot/map_demo�ļ���
		3������WebRoot/mapdownloader�ļ���
		4������WebRoot/mapfiles�ļ���
		5�����������ķ������޸�weblogic.xml��ʹϵͳ����������ȡ��ͼͼƬ
		6�����û�е�ͼͼƬ������Ȼ��������ķ�������ͼƬ�����ء�
		</pre>
		<h2>��������Լ��Ե�ͼϵͳ���п�������Ҫ��ʲô��</h2>
		<pre class="brush: js;">
		1������һ�����֮��ȷ��ϵͳ�ܹ�����ʹ�õ������
		2������ʵ���ļ�/WebRoot/map_demo/map_demo.jsp
			���÷������£�
			//��ӵ㣬����Ĳ���ʾ��
			var options = {
				draggable:false,//�Ƿ�֧���϶�
				icon_uri:"/mapfiles/markers/0marker_green.png",//��עչʾ��ͼƬURI
				text:"test_shown",//����ƶ�����ע�ϵ���ʾ����
				have_infowindow:true,//�Ƿ����õ������infowindow��true or false
				html:"<div>����������</div><br/><div>�������й�</div>"//չʾ��infowindow�ϵ�html����
			};
			MapDemo.add_points(options);
			//����ߣ�����Ĳ���ʾ��
			var st = {//���options���������ʾ��һ��
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
			var point_array = [st,ed];//��Ҫչʾ�����ϵĵ�ļ���
			var point_index_array = [1,2];//��Ҫչʾ�����ϵĵ��Ӧ���ϵĵ����ţ����ʾ����������������ע��չʾ�����ϵĵ�һ���۵�͵ڶ����۵���
			var options = {
				strokeColor:'#F00000',//�ߵ���ɫ
				strokeOpacity:1.0,//�ߵ�͸���� 0��1��double
				strokeWeight:8,//�ߵĴ�ϸ��Խ��Խ�֣�int
				can_click:false,//�Ƿ�֧�ֵ��
				have_points:true,//�����Ƿ��������չʾ�ĵ��ע���������Ϊfalse�����������������Ͳ���Ҫ��
				point_index_array:point_index_array,
				point_array:point_array
			};
			MapDemo.add_lines(options);
			//����棬�����ĺ���ֱ�Ϊ��
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
			var open_index = 1;//���ʱ������point1�ϴ�infowindow
			var options = {
				strokeColor:'#3300CC',//��ı��ߵ���ɫ
				strokeOpacity:0.8,//��ı��ߵ�͸����
				strokeWeight:4,//��ı��ߵĴ�ϸ
				fillColor:'#990099',//��������ɫ
				fillOpacity:0.3,//������͸����
				can_edit:false,//�Ƿ�֧�ֶ��α༭
				can_click:false,//�Ƿ���Ե�����������Ϊtrue,������ʱ������open_indexָ����point�ϴ�infowindow
				have_points:true,
				point_index_array:point_index_array,
				point_array:point_array,
				open_index:open_index
			};
			MapDemo.add_areas(options);
			//��������Ѿ���ӵı�ע����
			MapDemo.hide_all_markers();
		</pre>
	</body>
</html>