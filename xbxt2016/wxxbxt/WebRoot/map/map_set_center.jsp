<%@ page language="java" import="java.util.*" %> 
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>

<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=gb2312" />
	 
		<title>Map</title>

		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapmain.js"></script>
	   	<link rel="stylesheet" type="text/css" href="/map/css/css.css">
		<script type="text/javascript">
	 	var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
	 	var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
		var map;
		 
		 
		var markerListener = null;//ȫ������ 
		var array_point_markers = [];//��¼�����е�ı�ǵ�����
		var lat="";
		var lng="";
		function add_marker_point(){
			if(markerListener)
			{
				 google.maps.event.removeListener(markerListener);
		  		 markerListener= null;	
		  		 clearPoint(array_point_markers);
				lat="";
				lng="";
				return false;
			}
			markerListener = google.maps.event.addListener(map,'click',function(event){//�ı������ʽ//addListenerOnce
				clearPoint(array_point_markers);
				lat="";
				lng="";
		
				var myOptions = {
						position : event.latLng,
						draggable :false,
						icon :'/mapfiles/markers/0green-ball.png',
						map : map,
						title:'\n'+'����:'+event.latLng.lng()+' \nγ��:'+event.latLng.lat()
				};
				var marker = new google.maps.Marker(myOptions);
				array_point_markers.push(marker);
				lat=event.latLng.lat();
				lng=event.latLng.lng();
				var infowindow= new google.maps.InfoWindow();
				var contentString1="<p>������:"+lng+" </p>"+"<p>γ����:"+lat+" </p>"; 
				infowindow.setContent(contentString1);
				infowindow.open(map,marker);
			 
			});
		}
		 //������
		function clearPoint(array){
			if (array) {  
				for (i in array) {  
					array[i].setMap(null);  
				}  
				array.length = 0;  
			}
		 }
		 
		 function sub()
		 {
		// 	 if(lat==""){
		// 		  window.returnValue="no|"+lat+"|"+lng;
		/// 	 }
		// 	 else{
		// 		 window.returnValue="yes|"+lat+"|"+lng;
		// 	 }
		 	window.opener.document.getElementById("setlat").value=lat;
		 	window.opener.document.getElementById("setlng").value=lng;
			
	         window.close();
		 }
		</script>
	</head>
		<body onload="initialize('map_canvas',10,'${session.userInfo.lat}','${session.userInfo.lng}');" >
			<div id="content">
	 			
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" class="condit">
				<tr class="title">
				
					<td width="10%" nowrap align="center">
						<input type="button" id="show" onclick="add_marker_point();" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="��������"/>&nbsp;
					 	
						<input type="button" id="show" onclick="sub();" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="ȷ��"/>&nbsp;
					 	<input type="button" id="seldata" onclick="window.close();" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="�ر�"/>&nbsp; 
				</td>
			</tr>
				</table>
			<table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" class="condit">
			
				<tr class="title">
			 
			<div id='map_canvas' style="width:100%;height:100%"> </div>
		 
			</tr>
		</table>
		
			
		</div>
		</body>
</html>