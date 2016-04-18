 
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
	img.src = strURL;
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

function initialize(id, defaultZoom,marker_center_lat,marker_center_lng) {
	if(marker_center_lat!="")
	{
		var myLatlng = new google.maps.LatLng(marker_center_lat,marker_center_lng);
	}else
	{
		var myLatlng = new google.maps.LatLng(marker_center_lat,marker_center_lng);//南京--->32.0576115, 118.7843468  无锡-------->31.490644,120.311139
	}
	var myOptions = {
		center : myLatlng,
		zoom : defaultZoom,
		streetViewControl : false,
		mapTypeControlOptions : {
//			mapTypeIds : [ google.maps.MapTypeId.ROADMAP,
//					google.maps.MapTypeId.HYBRID,
//					google.maps.MapTypeId.SATELLITE,
//					google.maps.MapTypeId.TERRAIN, 'locaMap','localSaMap']
			mapTypeIds : ['locaMap','localSaMap']
		// 定义地图类型
		}
	};
	map = new google.maps.Map(document.getElementById(id), myOptions);

	map.mapTypes.set('locaMap', localMapType); // 绑定本地地图类型
	map.setMapTypeId('locaMap'); // 指定显示本地地图
	map.mapTypes.set('localSaMap', localSaMap);// 绑定离线卫星地图
	
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

function INDEXOF(str,array)//判断str是否在array里
{
	for(var i=0;i<array.length;i++)
	{
		if(str.indexOf(array[i])!=-1)
		{
			return 1;
		}
	}
	return 0;
}
function change(obj) {
    var pic = document.getElementById("preview");
    var file = obj;
    document.getElementById("filename").value=file.value;
    
    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
    // gif在IE浏览器暂时无法显示
    if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
        alert("文件必须为图片！"); return;
    }
    // IE浏览器
    if (document.all) {

        file.select();
        var reallocalpath = document.selection.createRange().text;
        var ie6 = /msie 6/i.test(navigator.userAgent);
        // IE6浏览器设置img的src为本地路径可以直接显示图片
        if (ie6) pic.src = reallocalpath;
        else {
            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
            pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
            // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
            pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
        }
    }else{
        html5Reader(file);
    }
}

function html5Reader(file){
    var file = file.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
        var pic = document.getElementById("preview");
        pic.src=this.result;
    };
}

//清除标记
function clearPoint(array){
	if (array) {  
		for (i in array) {  
			array[i].setMap(null);  
		}  
		array.length = 0;  
	}
 }

//清除标记
function clearCircle(array){
	if (array) {  
		for (i in array) {  
			array[i].remove();  
		}  
		array.length = 0;  
	}
 }
function clearNoNum(obj)
{
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}


var query_type="1";//
