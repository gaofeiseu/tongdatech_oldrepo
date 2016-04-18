
var cirListener = null;    // 画圈侦听

function changeCSS(className)
{
	endGetDistance();
	endGetScreen();
	clearAllTextAreaMarkers();
	endGetLatLng();
	endGetAreaNum();
	changeCssForClick();
	var css_c = '';
	var class_name = '.'+className;
	css_c = '-130px -52px';//-130px 0; 
	$(class_name).css('background-position',css_c);	
	$(class_name).attr("onclick","changeCSSs('"+className+"');drawCircle();");
    if (query_zoom_changed_Listener) {
		   google.maps.event.removeListener(query_zoom_changed_Listener);
		   query_zoom_changed_Listener= null;
		 
	}
    if (query_dragend_Listener) {
		   google.maps.event.removeListener(query_dragend_Listener);
		   query_dragend_Listener= null;
	 
	}
	clearPoint(dragHandleArray);
}

function clearCircleListener()
{
	
	var css_c = '';
	var class_name = '.'+'map_markers_t3';
	css_c = '-130px 0';//
	$(class_name).css('background-position',css_c);	
	clearPoint(queryPointArray);//清除点
	clearPoint(queryLineArray);//清除线
	clearPoint(queryAreaArray);//清除面
	clearCircle(queryCircleArray);//清除圆
	if(my_circle)
	{
		my_circle.remove();
	}
	$(class_name).attr("onclick","changeCSS('map_markers_t3');drawCircle();");

    if (query_zoom_changed_Listener) {
		   google.maps.event.removeListener(query_zoom_changed_Listener);
		   query_zoom_changed_Listener= null;
		 
	}
    if (query_dragend_Listener) {
		   google.maps.event.removeListener(query_dragend_Listener);
		   query_dragend_Listener= null;
	 
	}
    if(cirListener)
    {
	google.maps.event.removeListener(cirListener);
	cirListener= null;
    }

	clearPoint(dragHandleArray);
	return;
}
function changeCSSs(className)
{
	endGetDistance();
	endGetScreen();
	clearAllTextAreaMarkers();
	endGetLatLng();
	endGetAreaNum();
	changeCssForClick();
	var css_c = '';
	var class_name = '.'+className;
	css_c = '-130px 0';//
	$(class_name).css('background-position',css_c);	
	clearPoint(queryPointArray);//清除点
	clearPoint(queryLineArray);//清除线
	clearPoint(queryAreaArray);//清除面
	clearCircle(queryCircleArray);//清除圆
	if(my_circle)
	{
		my_circle.remove();
	}
	$(class_name).attr("onclick","changeCSS('"+className+"');drawCircle();");
    if (query_zoom_changed_Listener) {
		   google.maps.event.removeListener(query_zoom_changed_Listener);
		   query_zoom_changed_Listener= null;
		 
	}
    if (query_dragend_Listener) {
		   google.maps.event.removeListener(query_dragend_Listener);
		   query_dragend_Listener= null;
	 
	}
	clearPoint(dragHandleArray);
	map_query();
}

function drawCircle(){
 
 
   if (cirListener) {
   google.maps.event.removeListener(cirListener);
   cirListener= null;
  // clearDis();
   return false;
   }

	cirListener = google.maps.event.addListener(map,'click',function(event){
	if (event){
		createCircle(new google.maps.LatLng(event.latLng.lat(),event.latLng.lng()),1000,map);
	}
	});
}

  var metric = false;  
  var singleClick = false;  
  var infowindowsssss = new google.maps.InfoWindow({  
          content: ""  
     });  
  
 function createCircle(point, radius,myMap) {
  singleClick = false;  
  geoQuery = new GeoQuery();  
  geoQuery.initializeCircle(radius, point, myMap);   
  geoQuery.render();  
  
}

 
 
 
/**
 * 
 * 算法:通过原点、角度和距离计算目标点的坐标 orig:原点坐标 hdng:角度 dist:原点的到目标点的距离
 * 
 * @return 目标点坐标
 * 
 */  
function destination(orig, hdng, dist) {  
	var R = 6371;// earth's mean radius in km
	var oX, oY;
	var x, y;
	var d = dist/R;  // d = angular distance covered on earth's surface
	hdng = hdng * Math.PI / 180; // degrees to radians
	oX = orig.lng() * Math.PI / 180;
	oY = orig.lat() * Math.PI / 180;
	y = Math.asin( Math.sin(oY)*Math.cos(d) + Math.cos(oY)*Math.sin(d)*Math.cos(hdng) );
	x = oX + Math.atan2(Math.sin(hdng)*Math.sin(d)*Math.cos(oY), Math.cos(d)-Math.sin(oY)*Math.sin(y));
	y = y * 180 / Math.PI;
	x = x * 180 / Math.PI;
	return new google.maps.LatLng(y, x);
}  
  
/**
 * 
 * 算法:计算两个坐标点的距离 point1:坐标点1 point2:坐标点2
 * 
 * @return 两点的距离
 * 
 */  
function distance(point1, point2) {

  var R = 6378.137;// earth's mean radius in km
  var lon1 = point1.lng()* Math.PI / 180;
  var lat1 = point1.lat() * Math.PI / 180;
  var lon2 = point2.lng() * Math.PI / 180;
  var lat2 = point2.lat() * Math.PI / 180;
  
  var deltaLat = lat1 - lat2;
  var deltaLon = lon1 - lon2;
  
  var step1 = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(lat2) * Math.cos(lat1) * Math.pow(Math.sin(deltaLon/2), 2);
  var step2 = 2 * Math.atan2(Math.sqrt(step1), Math.sqrt(1 - step1));
  return step2 * R;
}

  
/**
 * 画圆的类
 * 
 */  
function GeoQuery() {

}

GeoQuery.prototype.CIRCLE='circle';// 画的形状
GeoQuery.prototype.COLORS=["#0000ff", "#00ff00", "#ff0000"];// 圆的颜色
var COLORI=0;// 默认颜色为0

GeoQuery.prototype = new GeoQuery();  
GeoQuery.prototype._map;  
GeoQuery.prototype._type;  
GeoQuery.prototype._radius;  
GeoQuery.prototype._dragHandle;  
GeoQuery.prototype._centerHandle;  
GeoQuery.prototype._polyline;  
GeoQuery.prototype._color;  
GeoQuery.prototype._control;  
GeoQuery.prototype._points;  
GeoQuery.prototype._dragHandlePosition;  
GeoQuery.prototype._centerHandlePosition;  
  
/**
 * 
 * 圆的加载,可拖动
 * 
 */  
GeoQuery.prototype.initializeCircle = function(radius, point, map) {
    this._type = this.CIRCLE;    // 圆形
    this._radius = radius;     // 半径
    this._map = map;      // 地图
   
    // 计算扩大圆的点的位置
    var distance1 = this._radius/1000;  
 	var newPoint = destination(point,180,distance1);  
 	distance1 = distance(point,newPoint);  
      
    this._dragHandlePosition = destination(point, 90, distance1);   
    // 设置扩大圆的点的样式
    this._dragHandle = new google.maps.Marker({  
        position: this._dragHandlePosition,   
        map: map,  
        draggable:true,  
        icon:"/mapfiles/rotate2.png"  
    });    
   
 // 中心点的位置
    this._centerHandlePosition = point;  
 // 设置中心点的样式
    this._centerHandle = new google.maps.Marker({  
        position: this._centerHandlePosition,   
        map: map,  
        draggable:true,  
        icon:"/mapfiles/markers/spotmkrs_2b70b09.png"  
    });   
    
 // 随机获取颜色
    this._color = this.COLORS[COLORI++ % 3];  
   
 // 在地图上加载圆形
    this._dragHandle.setMap(map);  
    this._centerHandle.setMap(map);  
   
    var myObject = this; 
    if(my_circle)
    {
    	my_circle.remove();
    }

	showCircleRadius(this._dragHandle,this._radius);
    my_circle=this;
    showDest(this);
 // 扩大圆的点的拖拽停止事件
  google.maps.event.addListener(myObject._dragHandle, 'dragend', function() {  myObject.updateCircle(1); });  
  // 扩大圆的点的拖拽停止事件
 
 // 圆的中心点的拖拽停止事件
 google.maps.event.addListener(myObject._centerHandle, 'dragend', function() { myObject.updateCircle(2);});  
 // 圆的中心点的拖拽事件
 // google.maps.event.addListener(myObject._centerHandle, 'drag', function() {myObject.updateCircle(2);});  
};  

/**
 * 
 * 圆的加载,用于显示
 * 
 */  
GeoQuery.prototype.initializeCircleRead = function(radius, point, map) {
    this._type = this.CIRCLE;    // 圆形
    this._radius = radius;     // 半径
    this._map = map;      // 地图
   
    // 计算扩大圆的点的位置
    var distance1 = this._radius/1000;  
 	var newPoint = destination(point,180,distance1);  
 	distance1 = distance(point,newPoint);  
      
  // this._dragHandlePosition = destination(point, 90, distance1);   
    // 设置扩大圆的点的样式
  //  this._dragHandle =null;    
   
 // 中心点的位置
    this._centerHandlePosition = point;  
 // 设置中心点的样式
    this._centerHandle = new google.maps.Marker({  
        position: this._centerHandlePosition,   
        map: map,  
        draggable:true,  
        icon:"/mapfiles/markers/spotmkrs_2b70b09.png"  
    });   
	queryPointArray.push(this._centerHandle);
 // 随机获取颜色
    this._color = this.COLORS[COLORI++ % 3];  
   
 // 在地图上加载圆形
//    this._dragHandle.setMap(map);  
    this._centerHandle.setMap(map);  
   
    var myObject = this; 


//	showCircleRadius(this._dragHandle,this._radius);

 // 圆的中心点的拖拽停止事件
 //google.maps.event.addListener(myObject._centerHandle, 'click', function() { myObject.updateCircle(2);});  

};  

/**
 * 
 * 圆的渲染，用于显示
 * 
 */  
GeoQuery.prototype.renderRead = function(k,marker_sn,table_name,str,color,strokeWeight,fillOpacity) {  
  if (this._type == this.CIRCLE) {  
    this._points = [];  
    
 // var dis = distance(this._points[0],this._points[1])/2;
 // var newPoint = destination(this._points[0],180,dis);
   
   
    var distance = this._radius/1000;  
      
 for (i = 0; i < 72; i++) {  
      this._points.push(destination(this._centerHandlePosition, i * 360/72, distance) );  
    }  
    this._points.push(destination(this._centerHandlePosition, 0, distance) );  
    
 this._polyline = new google.maps.Polygon({  
      paths: this._points,  
      strokeColor: color,  
      strokeOpacity: 0.8,  
      strokeWeight: strokeWeight,  
      fillColor: color,  
      fillOpacity: fillOpacity,
      clickable:clickable
    });   
 	this._polyline.setMap(this._map);   
 	

	showinfomessage(this._polyline,k,marker_sn,table_name,str,"2");//调用方法显示infowindow
	

  }   
};

/**
 * 
 * 寻找圆内的所有已经标注点
 * 
 */  

var dragHandleArray=[];

GeoQuery.prototype.updateCircle = function (type) {  
	this._polyline.setMap(null);  
    if (type==1) {  
    	this._dragHandlePosition = this._dragHandle.getPosition();  
    	this._radius = distance(this._centerHandlePosition, this._dragHandlePosition) * 1000;  
    	this.render(); 
    	// 重新计算圈内目标
    
    	showCircleRadius(this._dragHandle,this._radius);
    	
    	my_circle=this;
    	showDest(this);
	}
    else{  
    	this._centerHandlePosition = this._centerHandle.getPosition();  
    	this.render();  
    	this._dragHandle.setPosition(this.getEast());  
    
    	showCircleRadius(this._dragHandle,this._radius);
    	my_circle=this;
    	showDest(this);
    }
}; 
function showCircleRadius(marker,radius)
{
 
   	if(dragHandleArray.length>0)
   	{
   		dragHandleArray[0].infowindow.close();
   	}

	dragHandleArray.push(marker);  
   	dragHandleArray[0].infowindow= new google.maps.InfoWindow(); 
	var contentString1="半径是:"+radius+"米"; 
	dragHandleArray[0].infowindow.setContent(contentString1);
	dragHandleArray[0].infowindow.open(map,marker);
}
/**
 * 
 * 圆的渲染
 * 
 */  
GeoQuery.prototype.render = function() {  
  if (this._type == this.CIRCLE) {  
    this._points = [];  
    
 // var dis = distance(this._points[0],this._points[1])/2;
 // var newPoint = destination(this._points[0],180,dis);
   
   
    var distance = this._radius/1000;  
      
 for (i = 0; i < 72; i++) {  
      this._points.push(destination(this._centerHandlePosition, i * 360/72, distance) );  
    }  
    this._points.push(destination(this._centerHandlePosition, 0, distance) );  
    
 this._polyline = new google.maps.Polygon({  
      paths: this._points,  
      strokeColor: "#FF0000",  
      strokeOpacity: 0.8,  
      strokeWeight: 2,  
      fillColor: "#FF0000",  
      fillOpacity: 0.35  
    });   
 this._polyline.setMap(this._map);   
  }   
};
  
  
/**
 * 
 * 圆的删除
 * 
 */  
GeoQuery.prototype.remove = function() {  
 this._polyline.setMap(null);  
 if(this._dragHandle!=null)
 {
	 this._dragHandle.setMap(null);  
 }
 this._centerHandle.setMap(null);  

};
  
GeoQuery.prototype.getRadius = function() {  
    return this._radius;  
};
  
GeoQuery.prototype.getHTML = function() {  
  return "<span><font color='"+ this._color + "''>" + this.getDistHtml() + "</font></span>";  
};
  
GeoQuery.prototype.getDistHtml = function() {  
  result = "<img src='http://198.10.1.142:7001/mapfiles/iw_close.gif' onClick='myQueryControl.remove(" + this._control.getIndex(this) + ");'/>Radius ";  
  if (metric) {  
    if (this._radius < 1000) {  
      result += "in meters : " + this._radius.toFixed(1);  
    } else {  
      result += "in kilometers : " + (this._radius / 1000).toFixed(1);  
    }  
  } else {  
    var radius = this._radius * 3.2808399;  
    if (radius < 5280) {  
      result += "in feet : " + radius.toFixed(1);  
    } else {  
      result += "in miles : " + (radius / 5280).toFixed(1);  
    }  
  }  
  return result;     
};
  
GeoQuery.prototype.getNorth = function() {  
  return this._points[0];  
};
  
GeoQuery.prototype.getSouth = function() {  
  return this._points[(72/2)];  
};
  
GeoQuery.prototype.getEast = function() {  
   
 var dis = distance(this._points[0],this._points[1])/2;  
 var newPoint = destination(this._points[0],180,dis);  
 return newPoint;   
  // return this._points[(72/4)];
};
  
GeoQuery.prototype.getWest = function() {  
  return this._points[(72/4*3)];  
};

/** ************************显示圆圈内目标************************************************* */

var my_circle;
// 距离标记数组
var lenArray = []; 
function showDest(geoQuery){


	var url="/map_query_data.action";
 
	var nodes = $('#mapTree').tree('getChecked'); 
	var cnode = ''; 
	var x=0;
	for( var i = 0; i < nodes.length; i++) {
	   
	   if(nodes[i].id.indexOf("x")<0)
	   {
	   		cnode = cnode+","+nodes[i].id;
	   		x++;
	   }
	} 

	var json = {};
	json["SN"]=cnode;
	var bounds = map.getBounds();
	var noea=bounds.getNorthEast();
	var sw=bounds.getSouthWest();
	json["startx"]=sw.lat();
	json["starty"]=sw.lng();
	json["endx"]=noea.lat();
	json["endy"]=noea.lng();
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
				
				if(msg.if_success){
					clearPoint(queryPointArray);//清除点
					clearPoint(queryLineArray);//清除线
					clearPoint(queryAreaArray);//清除面
					clearCircle(queryCircleArray);//清除圆
					var listMap=msg.list_map;//返回的坐标信息
					var k=0;
			 
		 
					for(var i=0;i<listMap.length;i++ ){
						var pathpoint=listMap[i];
					 
			
					if(pathpoint.marker_type=="1")//点
					{ 
						
							var markerpoint = new google.maps.LatLng(pathpoint.lat,pathpoint.lng);
							if(distance(markerpoint,geoQuery._centerHandlePosition)*1000<=geoQuery._radius)
							{ 
								var marker = new google.maps.Marker({
										position: markerpoint,
										map: map,
										draggable:false,//禁止拖拽
										icon:pathpoint.mc_markers
								});  
							  queryPointArray.push(marker); 
							  showinfomessage(marker,k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,"2");
								
							  k=k+1;
						
							}
					
					} 
					
					if(pathpoint.marker_type=="2")//线
					{
						 
							//线组装
							var line_lat_Array = [];
							var line_lng_Array = [];
							line_lat_Array=pathpoint.lat.split('#');
							line_lng_Array=pathpoint.lng.split('#');
							line_lat_Array.pop();
							line_lng_Array.pop();
							if(checkDest(line_lat_Array,line_lng_Array,geoQuery))
							{
								var line_Array = [];
								for(var j=0;j<line_lat_Array.length;j++){
									line_Array.push(new google.maps.LatLng(line_lat_Array[j], line_lng_Array[j]));
								}
								var mc_markers=pathpoint.mc_markers;
								var color=mc_markers.split("||")[0];
								var strokeOpacity=mc_markers.split("||")[1];
								var strokeWeight=mc_markers.split("||")[2];
								var my_line = new google.maps.Polyline({
									path: line_Array,
									strokeColor:color,
									strokeOpacity: strokeOpacity,
									strokeWeight: strokeWeight,
									map:map,
									clickable:clickable
								});
								queryLineArray.push(my_line);
								//起点组装
								var stPointLatLng = new google.maps.LatLng(line_lat_Array[0],line_lng_Array[0]);
								var stIconurl="/mapfiles/markers/spotmkrs_2b70b09.png";
								var stMarker = new google.maps.Marker({
									position: stPointLatLng,
									map: map,
									draggable:false,//禁止拖拽
									icon:stIconurl
								});
								queryPointArray.push(stMarker);
							
								showinfomessage(my_line,k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,"2");
								k=k+1;
								//终点组装
								var edPointLatLng = new google.maps.LatLng(line_lat_Array[line_lat_Array.length-1],line_lng_Array[line_lng_Array.length-1]);
								var edIconurl="/mapfiles/markers/spotmkrs_2b70b09.png";
								var edMarker = new google.maps.Marker({
									position: edPointLatLng,
									map: map,
									draggable:false,//禁止拖拽
									icon:edIconurl
								});
								queryPointArray.push(edMarker);
							
								
							    k=k+1;
							}
						
					}
					if(pathpoint.marker_type=="3")//面
					{
						
						 
							//面组装
							var area_lat_Array = [];
							var area_lng_Array = [];
							area_lat_Array=pathpoint.lat.split('#');
							area_lng_Array=pathpoint.lng.split('#');
							area_lat_Array.pop();//去除最后一个空字符
							area_lng_Array.pop();//去除最后一个空字符
							if(checkDest(area_lat_Array,area_lng_Array,geoQuery))
							{
								var area_Array = [];
								for(var j=0;j<area_lat_Array.length;j++){
									area_Array.push(new google.maps.LatLng(area_lat_Array[j], area_lng_Array[j]));
								}
								
								var mc_markers=pathpoint.mc_markers.split("||");
								

								var color=mc_markers[0];
								var fillOpacity=0.3;
								var strokeWeight=3;
								if(mc_markers.length>1)
								{
									fillOpacity=mc_markers[1];
									strokeWeight=mc_markers[2];
								}
						
								
								var area_marker = new google.maps.Polygon({
									paths: area_Array,
									strokeColor:  color,
									strokeOpacity: 0.8,
									strokeWeight: strokeWeight,
									fillColor: color,
									fillOpacity: fillOpacity,
									editable:false,
									map:map,
									clickable:clickable
								});
							 
								queryAreaArray.push(area_marker);
								//面源点组装
								var areaPonitLatLng = new google.maps.LatLng(area_lat_Array[0],area_lng_Array[0]);
								var Iconurl="/mapfiles/markers/point_markers_ball_red.png";
								var areaPointMarker = new google.maps.Marker({
									position: areaPonitLatLng,
									map: map,
									draggable:false,//禁止拖拽
									icon:"/mapfiles/markers/spotmkrs_2b70b09.png"
								});
								queryPointArray.push(areaPointMarker);
						
								showinfomessage(area_marker,k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,"2");//调用方法显示infowindow
								
								k=k+1;
							}
						
					}
					
					if(pathpoint.marker_type=="4")//圆
					{
						
							var markerpoint = new google.maps.LatLng(pathpoint.lat,pathpoint.lng); 
					
							if(distance(markerpoint,geoQuery._centerHandlePosition)*1000<geoQuery._radius-pathpoint.radius)
							{
						    sgeoQuery = new GeoQuery();  
						    sgeoQuery.initializeCircleRead(pathpoint.radius, markerpoint, map);  
							var temps=(pathpoint.mc_markers).split("||");
						
					
						    sgeoQuery.renderRead(k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,temps[0],temps[2],temps[1]);  
						    queryCircleArray.push(sgeoQuery);
							k=k+1;
							
							}
					}
					if(pathpoint.marker_type=="5")
					{
					
						
							var markerpoint = new google.maps.LatLng(pathpoint.lat,pathpoint.lng);
							if(distance(markerpoint,geoQuery._centerHandlePosition)*1000<=geoQuery._radius)
							{
							var temps=(pathpoint.mc_markers).split("||");
							var textarea_marker = new MyMarker(map,
									{
										latlng:markerpoint,
										clickFun:textAreaClickFunc,
										labelText:pathpoint.show_text,
										border_weight:temps[2],
										border_color:temps[0],
										inner_color:temps[1]
									});
							
 
							  queryPointArray.push(textarea_marker);
							  k=k+1;
							}
					} 
						
				}
 
				}
			} 
			catch (exception) {
				alert(exception);
			}
		}
	});
}
function checkDest(lat_array,lng_array,geoQuery)
{
	for(var i=0;i<lat_array.length;i++)
	{
		var markerpoint = new google.maps.LatLng(lat_array[i],lng_array[i]);
		if(distance(markerpoint,geoQuery._centerHandlePosition)*1000>geoQuery._radius)
		{
			return false;
		}
	}
	return true;
}
