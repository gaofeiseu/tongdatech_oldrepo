 
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
	img.src = strURL;
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

function initialize(id, defaultZoom,marker_center_lat,marker_center_lng) {
	if(marker_center_lat!="")
	{
		var myLatlng = new google.maps.LatLng(marker_center_lat,marker_center_lng);
	}else
	{
		var myLatlng = new google.maps.LatLng(marker_center_lat,marker_center_lng);//�Ͼ�--->32.0576115, 118.7843468  ����-------->31.490644,120.311139
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
		// �����ͼ����
		}
	};
	map = new google.maps.Map(document.getElementById(id), myOptions);

	map.mapTypes.set('locaMap', localMapType); // �󶨱��ص�ͼ����
	map.setMapTypeId('locaMap'); // ָ����ʾ���ص�ͼ
	map.mapTypes.set('localSaMap', localSaMap);// ���������ǵ�ͼ
	
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

function INDEXOF(str,array)//�ж�str�Ƿ���array��
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
    // gif��IE�������ʱ�޷���ʾ
    if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
        alert("�ļ�����ΪͼƬ��"); return;
    }
    // IE�����
    if (document.all) {

        file.select();
        var reallocalpath = document.selection.createRange().text;
        var ie6 = /msie 6/i.test(navigator.userAgent);
        // IE6���������img��srcΪ����·������ֱ����ʾͼƬ
        if (ie6) pic.src = reallocalpath;
        else {
            // ��IE6�汾��IE���ڰ�ȫ����ֱ������img��src�޷���ʾ����ͼƬ�����ǿ���ͨ���˾���ʵ��
            pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
            // ����img��srcΪbase64�����͸��ͼƬ ȡ����ʾ�����Ĭ��ͼƬ
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

//������
function clearPoint(array){
	if (array) {  
		for (i in array) {  
			array[i].setMap(null);  
		}  
		array.length = 0;  
	}
 }

//������
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
    //�Ȱѷ����ֵĶ��滻�����������ֺ�.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //���뱣֤��һ��Ϊ���ֶ�����.
    obj.value = obj.value.replace(/^\./g,"");
    //��ֻ֤�г���һ��.��û�ж��.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //��֤.ֻ����һ�Σ������ܳ�����������
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}


var query_type="1";//
