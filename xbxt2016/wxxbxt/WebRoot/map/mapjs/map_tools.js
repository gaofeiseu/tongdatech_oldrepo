/**
 * 
 * @author Mr.GaoFei 
 *
 */

/**
 * 
 */
function changeImgTo(img_src){
	var i = 0;
	if(img_src.indexOf("/mapfiles/markers3/getDistance2-64-b.png")!=-1){
		i = 1;
		$("#getD").attr("src","/mapfiles/markers3/getDistance2-64.png");
		$("#getA").attr("src","/mapfiles/markers3/getDistance-64-b.png");
		$("#getS").attr("src","/mapfiles/markers3/save_screen-64-b.png");
	}else if(img_src.indexOf("/mapfiles/markers3/getDistance2-64.png")!=-1){
		i = 2;
		$("#getD").attr("src","/mapfiles/markers3/getDistance2-64-b.png");
	}else if(img_src.indexOf("/mapfiles/markers3/getDistance-64-b.png")!=-1){
		i = 3;
		$("#getD").attr("src","/mapfiles/markers3/getDistance2-64-b.png");
		$("#getA").attr("src","/mapfiles/markers3/getDistance-64.png");
		$("#getS").attr("src","/mapfiles/markers3/save_screen-64-b.png");
	}else if(img_src.indexOf("/mapfiles/markers3/getDistance-64.png")!=-1){
		i = 4;
		$("#getA").attr("src","/mapfiles/markers3/getDistance-64-b.png");
	}else if(img_src.indexOf("/mapfiles/markers3/save_screen-64-b.png")!=-1){
		i = 5;
		$("#getD").attr("src","/mapfiles/markers3/getDistance2-64-b.png");
		$("#getA").attr("src","/mapfiles/markers3/getDistance-64-b.png");
		$("#getS").attr("src","/mapfiles/markers3/save_screen-64.png");
	}else if(img_src.indexOf("/mapfiles/markers3/save_screen-64.png")!=-1){
		i = 6;
		$("#getS").attr("src","/mapfiles/markers3/save_screen-64-b.png");
	}else if(img_src.indexOf("/mapfiles/markers3/ee11ee1_64-b.png")!=-1){
		i = 7;
		$("#getLatLng").attr("src","/mapfiles/markers3/ee11ee1_64.png");
	}else if(img_src.indexOf("/mapfiles/markers3/ee11ee1_64.png")!=-1){
		i = 8;
		$("#getLatLng").attr("src","/mapfiles/markers3/ee11ee1_64-b.png");
	}else{
		i = 0;
		return;
	}
	doSomething(i);
}
function doSomething(i){
	switch(i){
	case 1:
		endGetAreaNum();
		endGetScreen();
		$("#div_status").text("��๦���ѿ�����");
		startGetDistance();
		break;
	case 2:
		endGetDistance();
		$("#div_status").text("��๦���ѹرգ�");
		break;
	case 3:
		endGetDistance();
		endGetScreen();
		$("#div_status").text("����������ѿ�����");
		startGetAreaNum();
		break;
	case 4:
		endGetAreaNum();
		$("#div_status").text("����������ѹرգ�");
		break;
	case 5:
		endGetDistance();
		endGetAreaNum();
		$("#div_status").text("���������ѿ�����");
		startGetScreen();
		break;
	case 6:
		endGetScreen();
		$("#div_status").text("���������ѹرգ�");
		break;
	case 7:
		endGetDistance();
		endGetAreaNum();
		endGetScreen();
		startGetLatLng();
		break;
	case 8:
		endGetLatLng();
		$("#div_status").text("��γ�Ȳ�ѯ������");
		break;
	default:
		//console.log('default!!!');
		break;
	}
}
var getdistance_listenner;
var marker_array_for_getdistance=[];
var latlng_for_getdistance=[];
var total_distance_for_map=0;
var line_array_for_getdistance=[];
function startGetDistance(){
	marker_array_for_getdistance.length=0;
	latlng_for_getdistance.length=0;
	total_distance_for_map=0;
	getdistance_listenner = google.maps.event.addListener(map,'click',function(event){
		latlng_for_getdistance.push(event.latLng);
		if(latlng_for_getdistance.length>=2){
			if(latlng_for_getdistance.length==2){//�������ĵ�ֻ��2����ʱ��
				var line_st_marker = new google.maps.Marker({
				      position: latlng_for_getdistance[0],
				      map: map,
				      icon: "/mapfiles/markers3/dd-start.png",
				      title:"���"
				});
				var line_ed_marker = new google.maps.Marker({
				      position: latlng_for_getdistance[latlng_for_getdistance.length-1],
				      map: map,
				      icon: "/mapfiles/markers3/dd-end.png",
				      title:"2������յ�"
				});
				var dist = google.maps.geometry.spherical.computeDistanceBetween(latlng_for_getdistance[0],latlng_for_getdistance[1]);
				total_distance_for_map+=dist;
				line_ed_marker.setOptions({title:total_distance_for_map.toFixed(2)+"��"});
				marker_array_for_getdistance.push(line_st_marker);
				marker_array_for_getdistance.push(line_ed_marker);
			}else if(latlng_for_getdistance.length>2){
				var line_nn_marker = new google.maps.Marker({
				      position: latlng_for_getdistance[latlng_for_getdistance.length-1],
				      map: map,
				      icon: "/mapfiles/markers3/dd-end.png",
				      title:"�յ�"
				});
				var dist = google.maps.geometry.spherical.computeDistanceBetween(latlng_for_getdistance[latlng_for_getdistance.length-2],latlng_for_getdistance[latlng_for_getdistance.length-1]);
				total_distance_for_map+=dist;
				line_nn_marker.setOptions({title:total_distance_for_map.toFixed(2)+"��"});
				marker_array_for_getdistance.push(line_nn_marker);
				for(var i=0;i<marker_array_for_getdistance.length;i++){
					if(i==0||i==(marker_array_for_getdistance.length-1)){
						continue;
					}else{
						marker_array_for_getdistance[i].setOptions({icon:"/mapfiles/markers3/mapdistance_point_lt.png"});
					}
				}
			}
			
			for(var i=0;i<line_array_for_getdistance.length;i++){
				line_array_for_getdistance[i].setMap(null);
			}
			line_array_for_getdistance.length=0;
			var linemarker = new google.maps.Polyline({
			    path: latlng_for_getdistance,
			    strokeColor: "#FF0000",
			    strokeOpacity: 1.0,
			    strokeWeight: 2,
			    map: map
			});
			line_array_for_getdistance.push(linemarker);
		}
	});
}
function endGetDistance(){
	google.maps.event.removeListener(getdistance_listenner);//������������
	
	total_distance_for_map=0;
	marker_array_for_getdistance.length=0;
	latlng_for_getdistance.length=0;
	line_array_for_getdistance.length=0;
}

var marker_array_for_getareanum=[];
var getareanum_listenner;
var latlng_for_getareanum=[];
var area_array_for_getareanum=[];
function startGetAreaNum(){
	marker_array_for_getareanum.length=0;
	latlng_for_getareanum.length=0;
	area_array_for_getareanum.length=0;
	getareanum_listenner = google.maps.event.addListener(map,'click',function(event){
		//console.log('latlng:'+event.latLng.lat()+'  '+event.latLng.lng());
		latlng_for_getareanum.push(event.latLng);
		if(latlng_for_getareanum.length>=3){
			var area_info_marker = new google.maps.Marker({
			      position: latlng_for_getareanum[0],
			      map: map,
			      icon: "/mapfiles/markers3/dd-start.png",
			      title:"��Ϣ��"
			});
			
			for(var i=0;i<area_array_for_getareanum.length;i++){
				area_array_for_getareanum[i].setMap(null);
			}
			area_array_for_getareanum.length=0;
			var areamarker = new google.maps.Polygon({
			    paths: latlng_for_getareanum,
			    strokeColor: "#FF0000",
			    strokeOpacity: 0.8,
			    strokeWeight: 3,
			    fillColor: "#FF0000",
			    fillOpacity: 0.35,
			    editable:true,
			    map:map
			});
			area_array_for_getareanum.push(areamarker);
			
			var areanum = google.maps.geometry.spherical.computeArea(latlng_for_getareanum);
			area_info_marker.setOptions({title:areanum+"ƽ����"});
			marker_array_for_getareanum.push(area_info_marker);
		}
	});
	
}
function endGetAreaNum(){
	google.maps.event.removeListener(getareanum_listenner);
	
	marker_array_for_getareanum.length=0;
	latlng_for_getareanum.length=0;
	if(area_array_for_getareanum[0]!=undefined&&area_array_for_getareanum[0]!=null){
		area_array_for_getareanum[0].setEditable(false);
	}
	area_array_for_getareanum.length=0;
}


function startGetScreen(){
	$.messager.show({title: "��ʾ",msg: "�����������������µ�360�����������ֱ��ʹ��Ctrl+Shift+X���������ͼ��Ctrl+M����ȫ��Ļ��ͼ��"});
}
function endGetScreen(){
	
}

var getlatlng_listenner;
var marker_arr_getlatlng = [];
var show_str1;
function startGetLatLng(){
	clearAllMarkers(marker_arr_getlatlng);
	var i=1;
	var s1;
	var s2;
	var s3;
	var s4;
	getlatlng_listenner = google.maps.event.addListener(map,'click',function(event){
		if(i==1){
			var area_info_marker = new google.maps.Marker({
			      position: event.latLng,
			      map: map,
			      icon: "/mapfiles/markers3/dd-start.png",
			      title:"��ʼ�����\n"+"γ�ȣ�"+event.latLng.lat()+"\n���ȣ�"+event.latLng.lng()
			});
			s1 = event.latLng;
			marker_arr_getlatlng.push(area_info_marker);
			i++;
		}else if(i==2){
			var area_info_marker = new google.maps.Marker({
			      position: event.latLng,
			      map: map,
			      icon: "/mapfiles/markers3/dd-end.png",
			      title:"���������\n"+"γ�ȣ�"+event.latLng.lat()+"\n���ȣ�"+event.latLng.lng()
			});
			s4 = event.latLng;
			marker_arr_getlatlng.push(area_info_marker);
			var latlng_arr = [];
			var s2 = new google.maps.LatLng(s1.lat(),s4.lng());
			var s3 = new google.maps.LatLng(s4.lat(),s1.lng());
			latlng_arr.push(s1);
			latlng_arr.push(s2);
			latlng_arr.push(s4);
			latlng_arr.push(s3);
			//console.log('1:'+s1.lat()+','+s1.lng());
			//console.log('2:'+s2.lat()+','+s2.lng());
			//console.log('4:'+s4.lat()+','+s4.lng());
			//console.log('3:'+s3.lat()+','+s3.lng());
			show_str1 = "��ʼ���꾭��:"+s1.lng()+" γ�ȣ�"+s1.lat()+" �������꾭�ȣ�"+s4.lng()+" γ�ȣ�"+s4.lat();
			$("#div_status").text(show_str1);
			//console.log('show_str1:'+show_str1);
			var areamarker = new google.maps.Polygon({
			    paths: latlng_arr,
			    strokeColor: "#FF0000",
			    strokeOpacity: 0.8,
			    strokeWeight: 3,
			    fillColor: "#FF0000",
			    fillOpacity: 0.35,
			    editable:false,
			    map:map
			});
			marker_arr_getlatlng.push(areamarker);
			google.maps.event.removeListener(getlatlng_listenner);
		}
	});
}
function endGetLatLng(){
	google.maps.event.removeListener(getlatlng_listenner);
	clearAllMarkers(marker_arr_getlatlng);
}


function clearAllMarkers(arr_1){
	for(var i=0;i<arr_1.length;i++){
		arr_1[i].setMap(null);
	}
	arr_1.length=0;
}