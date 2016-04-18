
/*******************************************************************************
 * ��ͼ���庯��
 */
function LocalMapType(url, maxZoom, minZoom) {
	this.url = url;
	this.maxZoom = maxZoom;
	this.minZoom = minZoom;
}
LocalMapType.prototype.tileSize = new google.maps.Size(256, 256);
LocalMapType.prototype.maxZoom = this.maxZoom; // ��ͼ��ʾ��󼶱�
LocalMapType.prototype.minZoom = this.minZoom; // ��ͼ��ʾ��С����
LocalMapType.prototype.name = "���ߵ�ͼ";
LocalMapType.prototype.alt = "��ʾ���ߵ�ͼ����";
LocalMapType.prototype.getTile = function(coord, zoom, ownerDocument) {
	var img = ownerDocument.createElement("img");
	img.style.width = this.tileSize.width + "px";
	img.style.height = this.tileSize.height + "px";
	// ��ͼ���·��
	// �ȸ�ʸ��ͼ maptile/googlemaps/roadmap
	// �ȸ�Ӱ��ͼ maptile/googlemaps/roadmap
	// MapABC��ͼ maptile/mapabc/
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
LocalSaMapType.prototype.maxZoom = this.maxZoom; // ��ͼ��ʾ��󼶱�
LocalSaMapType.prototype.minZoom = this.minZoom; // ��ͼ��ʾ��С����
LocalSaMapType.prototype.name = "��������";
LocalSaMapType.prototype.alt = "��ʾ�������ǵ�ͼ";
LocalSaMapType.prototype.getTile = function(coord, zoom, ownerDocument) {
	var img = ownerDocument.createElement("img");
	img.style.width = this.tileSize.width + "px";
	img.style.height = this.tileSize.height + "px";
	// ��ͼ���·��
	// �ȸ�ʸ��ͼ maptile/googlemaps/roadmap
	// �ȸ�Ӱ��ͼ maptile/googlemaps/roadmap
	// MapABC��ͼ maptile/mapabc/
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
LocalSaDLMapType.prototype.maxZoom = this.maxZoom; // ��ͼ��ʾ��󼶱�
LocalSaDLMapType.prototype.minZoom = this.minZoom; // ��ͼ��ʾ��С����
LocalSaDLMapType.prototype.name = "�������ǵ�·";
LocalSaDLMapType.prototype.alt = "��ʾ�������ǵ�·��ͼ";
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
		
	    /*****�ڴ����õ�ͼ��ʼ��ͼ���ĵ�***********/
		//�Ͼ�--->32.0576115, 118.7843468 
		//����-------->31.490644,120.311139
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
		// �����ͼ����
		}
	};
	map = new google.maps.Map(document.getElementById(id), myOptions);

	map.mapTypes.set('locaMap', localMapType); // �󶨱��ص�ͼ����
	map.mapTypes.set('localSaMap', localSaMap);// ���������ǵ�ͼ
	map.setMapTypeId('locaMap'); // ָ����ʾ���ص�ͼ
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

