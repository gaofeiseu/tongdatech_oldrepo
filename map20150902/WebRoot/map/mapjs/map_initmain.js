/**
 * @author Mr.GaoFei
 */
var list_textarea_marker = [];
var if_clicked = false;
function showTextarea(){
	if(!if_clicked){
		getTextAreaMarkersForZoom(map.getZoom());
		google.maps.event.addListener(map,'zoom_changed',function(event){
			clearAllTextAreaMarkers();
			getTextAreaMarkersForZoom(map.getZoom());
		});
		if_clicked = true;
	}else{
		clearAllTextAreaMarkers();
		$.messager.show({title: "��ʾ",msg: "�ı�����ʾ�Ѿ���ȡ����"});
		google.maps.event.clearListeners(map,'zoom_changed');
		if_clicked = false;
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
					$.messager.show({title: "�ɹ�",msg: msg.msg});
				}else{
					$.messager.show({title: "ʧ��",msg: msg.msg});
					return;
				}
			}
			catch (exception) {
				alert(exception);
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
}


function changeToSa(){
	map.setMapTypeId('localSaMap');
}