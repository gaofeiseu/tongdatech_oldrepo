var listen;
var if_do = true;
function getLatLng(){
	if(if_do){
		if_do = false;
		var _z = map.getZoom();
		$("#text1").val(_z);
		$("#btn1").val("结束获取经纬度");
		listen = google.maps.event.addListener(map,'click',function(event){//改变监听方式//addListenerOnce
			$("#text2").val(event.latLng.lat());
			$("#text3").val(event.latLng.lng());
			$("#text4").val(Math.floor(long2tile(event.latLng.lng(),_z),1));
			$("#text5").val(Math.floor(lat2tile(event.latLng.lat(),_z),1));
		});
	}else{
		if_do = true;
		$("#btn1").val("开始获取经纬度");
		google.maps.event.clearListeners(map,'click');
	}
}
var PI = 3.14159265358979324;
function long2tile(lon1,zoom1){
	return (Math.floor((lon1 + 180) / 360 * Math.pow(2, zoom1)));
}
function lat2tile(lat1,zoom2){
	return (Math.floor((1 - Math.log(Math.tan(lat1 * PI / 180) + 1/ Math.cos(lat1 * PI / 180))/ PI)/ 2 * Math.pow(2, zoom2)));
}