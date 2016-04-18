/**
 * @author Mr.GaoFei
 */

var old_class_name = '';
var old_class_css = '';
function changeCssForClick(className,num_s){
	if(old_class_name!=''&&old_class_name!=undefined&&old_class_css!=''&&old_class_css!=undefined){
		$(old_class_name).css('background-image',old_class_css);
	}
	var css_c = '';
	var class_name = '.'+className;

	switch(num_s){
	case '1':
		css_c = 'url(/mapfiles/markers3/getDistance2-64.png)';
		break;
	case '2':
		css_c = 'url(/mapfiles/markers3/getDistance-64.png)';
		break;
	case '3':
		css_c = 'url(/mapfiles/markers3/getDistance2-64-b.png)';
		break;
	case '4':
		css_c = 'url(/mapfiles/markers3/fdce244eba_64.png)';
		break;
	case '5':
		css_c = 'url(/mapfiles/markers3/save_screen-64.png)';
		break;
	case '6':
		css_c = 'url(/mapfiles/markers3/ee11ee1_64.png)';
		break;
	default:
		css_c = 'url(/mapfiles/markers3/getDistance2-64-b.png)';
		break;
	}
	old_class_name = class_name;
	old_class_css = $(class_name).css('background-image');
	$(class_name).css('background-image',css_c);
	doWhatOperation(num_s);
}
function changeCssForBtnUp(className,num_s){
	var css_c = '';
	var class_name = '.'+className;
	switch(num_s){
	case '1':
		css_c = 'url(/mapfiles/markers3/getDistance2-64-b.png)';
		break;
	case '2':
		css_c = 'url(/mapfiles/markers3/getDistance-64-b.png)';
		break;
	case '3':
		css_c = 'url(/mapfiles/markers3/getDistance2-64-b.png)';
		break;
	case '4':
		css_c = 'url(/mapfiles/markers3/fdce244eba_64_b.png)';
		break;
	case '5':
		css_c = 'url(/mapfiles/markers3/save_screen-64-b.png)';
		break;
	case '6':
		css_c = 'url(/mapfiles/markers3/ee11ee1_64-b.png)';
		break;
	default:
		css_c = 'url(/mapfiles/markers3/getDistance2-64-b.png)';
		break;
	}
	$(class_name).css('background-image',css_c);
}
function doWhatOperation(num_s){
	switch(num_s){
	case '1':
		endGetAreaNum();
		endGetScreen();
		clearAllTextAreaMarkers();
		endGetLatLng(); 
		clearCircleListener();
		if_click_2 = false;
		if_click_4 = false;
		if_click_5 = false;
		if_click_6 = false;
		startGetDistance();
		break;
	case '2':
		endGetDistance();
		endGetScreen();
		clearAllTextAreaMarkers();
		endGetLatLng();
		clearCircleListener();
		if_click_1 = false;
		if_click_4 = false;
		if_click_5 = false;
		if_click_6 = false;
		startGetAreaNum();
		break;
	case '3':
		
		break;
	case '4':
		//显示文本框
		endGetScreen();
		endGetLatLng();
		endGetDistance();
		endGetAreaNum();
		clearCircleListener();
		if_click_1 = false;
		if_click_2 = false;
		if_click_5 = false;
		if_click_6 = false;
		showTextarea();
		break;
	case '5':
		endGetLatLng();
		endGetDistance();
		endGetAreaNum();
		clearAllTextAreaMarkers();
		clearCircleListener();
		if_click_1 = false;
		if_click_2 = false;
		if_click_4 = false;
		if_click_6 = false;
		startGetScreen();
		break;
	case '6':
		endGetDistance();
		endGetAreaNum();
		clearAllTextAreaMarkers();
		endGetScreen();
		clearCircleListener();
		if_click_1 = false;
		if_click_2 = false;
		if_click_4 = false;
		if_click_5 = false;
		startGetLatLng();
		break;
	default:
		break;
	}
}

var getdistance_listenner;
var marker_array_for_getdistance=[];
var latlng_for_getdistance=[];
var total_distance_for_map=0;
var line_array_for_getdistance=[];
var if_click_1 = false;
function startGetDistance(){
	if(!if_click_1){
		marker_array_for_getdistance.length=0;
		latlng_for_getdistance.length=0;
		total_distance_for_map=0;
		getdistance_listenner = google.maps.event.addListener(map,'click',function(event){
			latlng_for_getdistance.push(event.latLng);
			if(latlng_for_getdistance.length>=2){
				if(latlng_for_getdistance.length==2){//当操作的点只有2个的时候
					var line_st_marker = new google.maps.Marker({
					      position: latlng_for_getdistance[0],
					      map: map,
					      icon: "/mapfiles/markers3/dd-start.png",
					      title:"起点"
					});
					var line_ed_marker = new google.maps.Marker({
					      position: latlng_for_getdistance[latlng_for_getdistance.length-1],
					      map: map,
					      icon: "/mapfiles/markers3/dd-end.png",
					      title:"2个点的终点"
					});
					var dist = google.maps.geometry.spherical.computeDistanceBetween(latlng_for_getdistance[0],latlng_for_getdistance[1]);
					total_distance_for_map+=dist;
					//line_ed_marker.setOptions({title:total_distance_for_map.toFixed(2)+"米"});
					
					marker_array_for_getdistance.push(line_st_marker);
					marker_array_for_getdistance.push(line_ed_marker);
					
					showDisInfowindow(line_ed_marker,marker_array_for_getdistance.length-1,total_distance_for_map.toFixed(2));
					
				
					 
				}else if(latlng_for_getdistance.length>2){
					var line_nn_marker = new google.maps.Marker({
					      position: latlng_for_getdistance[latlng_for_getdistance.length-1],
					      map: map,
					      icon: "/mapfiles/markers3/dd-end.png",
					      title:"终点"
					});
					var dist = google.maps.geometry.spherical.computeDistanceBetween(latlng_for_getdistance[latlng_for_getdistance.length-2],latlng_for_getdistance[latlng_for_getdistance.length-1]);
					total_distance_for_map+=dist;
					//line_nn_marker.setOptions({title:total_distance_for_map.toFixed(2)+"米"});  
					marker_array_for_getdistance.push(line_nn_marker);
					showDisInfowindow(line_nn_marker,marker_array_for_getdistance.length-1,total_distance_for_map.toFixed(2));
				
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
		if_click_1 = true;
		$.messager.show({title: "提示",msg: "测距功能开启！"});
	}
	else{
		google.maps.event.removeListener(getdistance_listenner);//结束画线流程
		
		total_distance_for_map=0;
		marker_array_for_getdistance.length=0;
		latlng_for_getdistance.length=0;
		line_array_for_getdistance.length=0;
		changeCssForBtnUp('map_markers_t1','1');
		if_click_1 = false;
		$.messager.show({title: "提示",msg: "测距功能关闭！"});
	}
}
function showDisInfowindow(marker,n,dis)
{
	var temparray=[];
	temparray=marker_array_for_getdistance;
	temparray[n].infowindow= new google.maps.InfoWindow();
	for(var i=1;i<temparray.length;i++)
	{
		temparray[i].infowindow.close();
	} 
	var contentString1="距离是:"+dis+"米";
	temparray[n].infowindow.setContent(contentString1);
	temparray[n].infowindow.open(map,marker);
	google.maps.event.addListener(marker,'click',function(event){
		temparray[n].infowindow.setContent(contentString1);
		temparray[n].infowindow.open(map,marker);
	});
	
	
}
function endGetDistance(){
	google.maps.event.removeListener(getdistance_listenner);//结束画线流程
	
	total_distance_for_map=0;
	marker_array_for_getdistance.length=0;
	latlng_for_getdistance.length=0;
	line_array_for_getdistance.length=0;
}
var marker_array_for_getareanum=[];
var getareanum_listenner;
var latlng_for_getareanum=[];
var area_array_for_getareanum=[];
var if_click_2 = false;
function startGetAreaNum(){
	if(!if_click_2){
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
				      title:"信息点"
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
			//	area_info_marker.setOptions({title:areanum+"平方米"});
				marker_array_for_getareanum.push(area_info_marker);
				var temparray=[];
				var n=latlng_for_getareanum.length-1;
				temparray=latlng_for_getareanum;
				temparray[n].infowindow= new google.maps.InfoWindow();
				for(var i=2;i<temparray.length;i++)
				{
					temparray[i].infowindow.close();
				}
				var contentString1="面积是:"+areanum+"平方米"; 
				temparray[n].infowindow.setContent(contentString1);
				temparray[n].infowindow.open(map,area_info_marker);
				google.maps.event.addListener(area_info_marker,'click',function(event){
					temparray[n].infowindow.setContent(contentString1);
					temparray[n].infowindow.open(map,area_info_marker);
				});
			}
		});
		if_click_2 = true;
		$.messager.show({title: "提示",msg: "测面积功能开启！"});
	}else{
		google.maps.event.removeListener(getareanum_listenner);
		
		marker_array_for_getareanum.length=0;
		latlng_for_getareanum.length=0;
		if(area_array_for_getareanum[0]!=undefined&&area_array_for_getareanum[0]!=null){
			area_array_for_getareanum[0].setEditable(false);
		}
		area_array_for_getareanum.length=0;
		if_click_2 = false;
		$.messager.show({title: "提示",msg: "测面积功能关闭！"});
		changeCssForBtnUp('map_markers_t2','2');
	}
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
var getlatlng_listenner;
var marker_arr_getlatlng = [];
var show_str1;
var if_click_6 = false;
function startGetLatLng(){
	if(!if_click_6){
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
				      title:"开始坐标点\n"+"纬度："+event.latLng.lat()+"\n经度："+event.latLng.lng()
				});
				s1 = event.latLng;
				marker_arr_getlatlng.push(area_info_marker);
				var infowindow= new google.maps.InfoWindow();
			 
				var contentString1="纬度："+event.latLng.lat()+"\n经度："+event.latLng.lng(); 
				infowindow.setContent(contentString1);
				infowindow.open(map,area_info_marker);
				google.maps.event.addListener(area_info_marker,'click',function(event){
					infowindow.setContent(contentString1);
					infowindow.open(map,area_info_marker);
				});
				i++;
			}else if(i==2){
				var area_info_marker = new google.maps.Marker({
				      position: event.latLng,
				      map: map,
				      icon: "/mapfiles/markers3/dd-end.png",
				      title:"结束坐标点\n"+"纬度："+event.latLng.lat()+"\n经度："+event.latLng.lng()
				});
				var infowindow= new google.maps.InfoWindow();
				 
				var contentString1="纬度："+event.latLng.lat()+"\n经度："+event.latLng.lng(); 
				infowindow.setContent(contentString1);
				infowindow.open(map,area_info_marker);
				google.maps.event.addListener(area_info_marker,'click',function(event){
					infowindow.setContent(contentString1);
					infowindow.open(map,area_info_marker);
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
				show_str1 = "开始坐标经度:"+s1.lng()+" 纬度："+s1.lat()+" 结束坐标经度："+s4.lng()+" 纬度："+s4.lat();
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
		if_click_6 = true;
		$.messager.show({title: "提示",msg: "经纬度查询开启！"});
	}else{
		google.maps.event.removeListener(getlatlng_listenner);
		clearAllMarkers(marker_arr_getlatlng);
		
		if_click_6 = false;
		$.messager.show({title: "提示",msg: "经纬度查询关闭！"});
		changeCssForBtnUp('map_markers_t6','6');
	}
	
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

var list_textarea_marker = [];
var if_clicked = false;
var listener_textarea;
function showTextarea(){
	if(!if_clicked){
		getTextAreaMarkersForZoom(map.getZoom());
		listener_textarea = google.maps.event.addListener(map,'zoom_changed',function(event){
			$.messager.show({title: "提示",msg: "地图图层："+map.getZoom()});
			clearAllTextAreaMarkers();
			getTextAreaMarkersForZoom(map.getZoom());
		});
		if_clicked = true;
		$.messager.show({title: "提示",msg: "文本框显示开启！"});
	}else{
		clearAllTextAreaMarkers();
		$.messager.show({title: "提示",msg: "文本框显示关闭！"});
		google.maps.event.clearListeners(map,'zoom_changed');
		if_clicked = false;
		changeCssForBtnUp('map_markers_t4','4');
	}
}

function getTextAreaMarkersForZoom(zoom){
	var url="map_markersaddition_getTextarea.action";
	var json = {};
	json["now_map_zoom"] = zoom;
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg){
			try {
				if(msg.if_success){
					for(var i=0;i<msg.list_map.length;i++){
						var marker = new MyMarker(map,
								{
									latlng:new google.maps.LatLng(msg.list_map[i].lat,msg.list_map[i].lng),
									clickFun:textAreaClickFunc,
									labelText:msg.list_map[i].text_content
								});
						list_textarea_marker.push(marker);
					}
					map.panTo(new google.maps.LatLng(msg.list_map[0].lat,msg.list_map[0].lng));
					//$.messager.show({title: "成功",msg: msg.msg});
				}else{
					//$.messager.show({title: "失败",msg: msg.msg});
					return;
				}
			}
			catch (exception) {
				//alert(exception);
				clearAllTextAreaMarkers();
				changeCssForBtnUp('map_markers_t4','4');
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function clearAllTextAreaMarkers(){
	if(list_textarea_marker.length!=0){
		for(var i=0;i<list_textarea_marker.length;i++){
			list_textarea_marker[i].setMap(null);
		}
		list_textarea_marker.length=0;
	}
//	google.maps.event.removeListener(listener_textarea);
//	if_clicked = false;
}

var if_click_5 = false;
function startGetScreen(){
	if(!if_click_5){
		capture();
		$.messager.show({title: "提示",msg: "截屏功能开启！"});
		if_click_5 = true;
	}else{
		$.messager.show({title: "提示",msg: "截屏功能关闭！"});
		if_click_5 = false;
		changeCssForBtnUp('map_markers_t5','5');
	}
}

function endGetScreen(){
	//进行截屏清理工作
}