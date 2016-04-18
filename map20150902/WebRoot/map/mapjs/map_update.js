/**
*
*@author Mr.GaoFei
*
**/

function load_func(){
	initialize('map_canvas',config.maxZoon,t_user_lat,t_user_lng);
	map.setZoom(10);
//	var s1 = decodeURIComponent(download_obj[0].value);
//	$("#download_url").val(s1);
}

//function change_downloadurl(pp){
//	if(pp=='1'){
//		var s1 = decodeURIComponent(download_obj[0].value);
//		//console.log('s1:'+s1);
//		$("#download_url").val(s1);
//	}else if(pp=='2'){
//		var s1 = decodeURIComponent(sa_download_obj[0].value);
//		//console.log('s1:'+s1);
//		$("#download_url").val(s1);
//	}else if(pp=='3'){
//		var s1 = decodeURIComponent(sadl_download_obj[0].value);
//		//console.log('s1:'+s1);
//		$("#download_url").val(s1);
//	}
//}

function dodownload(){
	var z = $('#map_zoom').numberspinner('getValue');
	var st_lng = $('#st_lng').val();
	var st_lat = $('#st_lat').val();
	var ed_lng = $('#ed_lng').val();
	var ed_lat = $('#ed_lat').val();
	
	if(z==null||z==undefined||z==''){
		alert('图层数不能为空！');
		return;
	}
	if(st_lng==null||st_lng==undefined||st_lng==''){
		alert('起始经度不能为空！');
		return;
	}
	if(st_lat==null||st_lat==undefined||st_lat==''){
		alert('起始纬度不能为空！');
		return;
	}
	if(ed_lng==null||ed_lng==undefined||ed_lng==''){
		alert('结束经度不能为空！');
		return;
	}
	if(ed_lat==null||ed_lat==undefined||ed_lat==''){
		alert('结束纬度不能为空！');
		return;
	}
	
	$('#down_btn').linkbutton('disable');
	$("#loading_img").show();
    fmSubmit("#queryfm","/map_update_dodownload.action",function(result){
//    	$('#down_btn').linkbutton('enable');
    	$("#loading_img").hide();
	});
}

function fmSubmit(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			var result = eval("("+result+")");
			if (result.if_success){
				$.messager.show({title: "成功",msg: result.msg});
				callback(result);
			} else {
				$.messager.show({title: "错误",msg: result.msg});
				callback(result);
			}
		}
	});
}

var click_num = 0;
var latlng_markers_arr = [];
function startSetLatLng(){
	if(latlng_markers_arr.length!=0){
		for(var i=0;i<latlng_markers_arr.length;i++){
			latlng_markers_arr[i].setMap(null);
		}
		latlng_markers_arr.length=0;
	}
	click_num=0;
	var s1;
	var s2;
	var s3;
	var s4;
	var st_lat;
	var st_lng;
	var ed_lat;
	var ed_lng;
	markerListener = google.maps.event.addListener(map,'click',function(event){
		if(click_num==0){
			//console.log('第一次点击，起点经度：'+event.latLng.lng()+'  起点纬度：'+event.latLng.lat());
			var myOptions = {
					position : event.latLng,
					draggable :false,
					icon :'/mapfiles/markers3/dest_markers_14848ce_st.png',
					map : map,
					title:'起始纬度:'+event.latLng.lat()+' \n起始经度:'+event.latLng.lng()
			};
			var marker = new google.maps.Marker(myOptions);
			latlng_markers_arr.push(marker);
			s1 = event.latLng;
			st_lat = event.latLng.lat();
			st_lng = event.latLng.lng();
			click_num++;
		}else if(click_num==1){
			//console.log('第2次点击，结束经度：'+event.latLng.lng()+'  结束纬度：'+event.latLng.lat());
			var myOptions = {
					position : event.latLng,
					draggable :false,
					icon :'/mapfiles/markers3/dest_markers_14848ce_ed.png',
					map : map,
					title:'结束纬度:'+event.latLng.lat()+' \n结束经度:'+event.latLng.lng()
			};
			var marker = new google.maps.Marker(myOptions);
			latlng_markers_arr.push(marker);
			s4 = event.latLng;
			ed_lat = event.latLng.lat();
			ed_lng = event.latLng.lng();
			if(ed_lat>st_lat){
				ed_lat = st_lat;
				st_lat = event.latLng.lat();
				ed_lng = st_lng;
				st_lng = event.latLng.lng();
			}
			click_num++;
			var latlng_arr = [];
			var s2 = new google.maps.LatLng(s1.lat(),s4.lng());
			var s3 = new google.maps.LatLng(s4.lat(),s1.lng());
			latlng_arr.push(s1);
			latlng_arr.push(s2);
			latlng_arr.push(s4);
			latlng_arr.push(s3);
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
			latlng_markers_arr.push(areamarker);
			
			google.maps.event.clearListeners(map,'click');
			$('#st_lng').val(st_lng);
			$('#st_lat').val(st_lat);
			$('#ed_lng').val(ed_lng);
			$('#ed_lat').val(ed_lat);
		}
		
	});
}
