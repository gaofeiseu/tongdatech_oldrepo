
/*******************************************************************************
 * 地图定义函数
 */
function LocalMapType(url, maxZoom, minZoom) {
	this.url = url;
	this.maxZoom = maxZoom;
	this.minZoom = minZoom;
}
LocalMapType.prototype.tileSize = new google.maps.Size(256, 256);
LocalMapType.prototype.maxZoom = this.maxZoom; // 地图显示最大级别
LocalMapType.prototype.minZoom = this.minZoom; // 地图显示最小级别
LocalMapType.prototype.name = "离线地图";
LocalMapType.prototype.alt = "显示离线地图数据";
LocalMapType.prototype.getTile = function(coord, zoom, ownerDocument) {
	var img = ownerDocument.createElement("img");
	img.style.width = this.tileSize.width + "px";
	img.style.height = this.tileSize.height + "px";
	// 地图存放路径
	// 谷歌矢量图 maptile/googlemaps/roadmap
	// 谷歌影像图 maptile/googlemaps/roadmap
	// MapABC地图 maptile/mapabc/
	var strURL = this.url + "maptile/mapabc/";
	strURL += zoom + "/" + coord.x + "/" + coord.y + ".png";
	
//	var strURL = this.url + "map_tile_doo.action?";
//	strURL += "select_Type="+"mapabc";
//	strURL += "&select_Zoom="+zoom;
//	strURL += "&select_X="+coord.x;
//	strURL += "&select_Y="+coord.y;
	
	
	img.src = strURL;//     http://198.10.1.253:7001/maptile/mapabc/15/27194/13299.png
	return img;
};

function LocalSaMapType(url, maxZoom, minZoom) {
	this.url = url;
	this.maxZoom = maxZoom;
	this.minZoom = minZoom;
}
LocalSaMapType.prototype.tileSize = new google.maps.Size(256, 256);
LocalSaMapType.prototype.maxZoom = this.maxZoom; // 地图显示最大级别
LocalSaMapType.prototype.minZoom = this.minZoom; // 地图显示最小级别
LocalSaMapType.prototype.name = "离线卫星";
LocalSaMapType.prototype.alt = "显示离线卫星地图";
LocalSaMapType.prototype.getTile = function(coord, zoom, ownerDocument) {
	var img = ownerDocument.createElement("img");
	img.style.width = this.tileSize.width + "px";
	img.style.height = this.tileSize.height + "px";
	// 地图存放路径
	// 谷歌矢量图 maptile/googlemaps/roadmap
	// 谷歌影像图 maptile/googlemaps/roadmap
	// MapABC地图 maptile/mapabc/
	var strURL = this.url + "maptile/mapSa/";
	strURL += zoom + "/" + coord.x + "/" + coord.y;
		strURL += ".png";
	/*
	try{
		if(Number(zoom)>17){
			strURL += ".jpg";//jpg
		}else{
			strURL += ".png";
		}
	}
	catch(e){
		consolg.log(e.message);
	}
	*/
	img.src = strURL;
	return img;
};

/**************************ADD*****************************/


function LocalSaDLMapType(url, maxZoom, minZoom) {
	this.url = url;
	this.maxZoom = maxZoom;
	this.minZoom = minZoom;
}
LocalSaDLMapType.prototype.tileSize = new google.maps.Size(256, 256);
LocalSaDLMapType.prototype.maxZoom = this.maxZoom; // 地图显示最大级别
LocalSaDLMapType.prototype.minZoom = this.minZoom; // 地图显示最小级别
LocalSaDLMapType.prototype.name = "离线卫星道路";
LocalSaDLMapType.prototype.alt = "显示离线卫星道路地图";
LocalSaDLMapType.prototype.getTile = function(coord, zoom, ownerDocument) {
	var strURL = this.url + "maptile/mapSaDL/";
	strURL += zoom + "/" + coord.x + "/" + coord.y + ".png";
	
	var div = ownerDocument.createElement('div');
	  div.innerHTML = "<img src=\""+strURL+"\" style=\"width:"+this.tileSize.width + "px"+";height:"+this.tileSize.height + "px"+"\" />";//coord
	  div.style.width = this.tileSize.width + 'px';
	  div.style.height = this.tileSize.height + 'px';
//	  div.style.fontSize = '10';
//	  div.style.borderStyle = 'solid';
//	  div.style.borderWidth = '1px';
//	  div.style.borderColor = '#FF00FF';
	  return div;
};
var localSaDLMapType = new LocalSaDLMapType(config.url,config.maxZoon,config.minZoon);


/**************************ADD*****************************/



function initialize(id, defaultZoom,lat,lng) {

	if(lat!="")
	{
		var myLatlng = new google.maps.LatLng(lat,lng);
	}else
	{
		
	    /*****在此设置地图初始地图中心点***********/
		//南京--->32.0576115, 118.7843468 
		//无锡-------->31.490644,120.311139
		var myLatlng = new google.maps.LatLng(32.0576115, 118.7843468);
	}
	
	var myOptions = {
		center : myLatlng,
		zoom : defaultZoom,
		streetViewControl : false,
		mapTypeControlOptions : {
//			mapTypeIds : [ google.maps.MapTypeId.ROADMAP,
//					google.maps.MapTypeId.SATELLITE,
//					google.maps.MapTypeId.TERRAIN, 'locaMap',
//					'localSaMap']
			mapTypeIds : ['locaMap','localSaMap']
		// 定义地图类型
		}
	};
	map = new google.maps.Map(document.getElementById(id), myOptions);

	map.mapTypes.set('locaMap', localMapType); // 绑定本地地图类型
	map.mapTypes.set('localSaMap', localSaMap);// 绑定离线卫星地图
	map.setMapTypeId('locaMap'); // 指定显示本地地图
	/***********************************************************************/
	google.maps.event.addListener(map,'maptypeid_changed',function(){
		if(map.getMapTypeId()=='locaMap'){
			map.overlayMapTypes.removeAt(0);
		}else if(map.getMapTypeId()=='localSaMap'){
			map.overlayMapTypes.insertAt(0, localSaDLMapType);
		}
	});
	/***********************************************************************/	
	
}

