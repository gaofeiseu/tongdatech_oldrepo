/**************************************************************************************************/
function MyMarker(map,options){
	//initialize all properties.
	this.latlng = options.latlng; //����ͼ���λ��
	this.image_ = options.image;  //����ͼ���ͼƬ
	this.labelText = options.labelText || '���';
	this.labelClass = options.labelClass || 'shadow';//�������ֵ���ʽ
	this.clickFun = options.clickFun ;//ע�����¼�
	//this.labelOffset = options.labelOffset || new google.maps.Size(8, -33);
	this.map_ = map;
	this.div_ = null;
	//call setMap() on this overlay
	this.setMap(map);
	this._border_weight = options.border_weight||'2';
	this._border_color = options.border_color||'#FF0000';
	this._inner_color = options.inner_color||'#FFFF00';
}
MyMarker.prototype = new google.maps.OverlayView();
//��ʼ��ͼ��  
MyMarker.prototype.onAdd = function(){
    var div = document.createElement('DIV');//�������ͼƬ�����ֵ�div
    div.style.border = "none";
    div.style.borderWidth = "0px";
    div.style.position = "absolute";
    div.style.cursor = "hand";
    div.onclick = this.clickFun ||function(){};//ע��click�¼���û�ж����Ϊ�պ���    
    var img = document.createElement("img");//����ͼƬԪ��
    img.src = this.image_;
    img.style.width = "100%";
    img.style.height = "100%";
    //��ʼ�����ֱ�ǩ
    var label = document.createElement('div');//�������ֱ�ǩ
    label.className = this.labelClass;
//    label.innerHTML = '<a href="#">'+this.labelText+'</a>';
    label.innerHTML = '<table style="word-break:break-all;word-wrap:break-word;" ><tr><td>'+this.labelText+'</td></tr></table>';
    //console.log('ssL::'+label.innerHTML);
    label.style.position = 'absolute';
    label.style.width = '150px';
    label.style.fontWeight = "normal";//normal--bold--900
    label.style.textAlign = 'left';
    label.style.padding = "2px";
    label.style.fontSize = "15px";
    label.style.fontFamily = "Courier New";
    div.appendChild(img);
    div.appendChild(label);
    this.div_ = div;
    var panes = this.getPanes();
    panes.overlayMouseTarget.appendChild(div);
};
//����ͼ�꣬��Ҫ���ڿ���ͼ���λ��  
MyMarker.prototype.draw = function(){
	var overlayProjection = this.getProjection();
	var position = overlayProjection.fromLatLngToDivPixel(this.latlng);//����������ת������Ļ����
	//var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_.getNorthEast());
	//����image��DIV��ǩ�Ĵ�С����Ӧҳ��
	var div = this.div_;
	div.style.left = position.x + 'px';
	div.style.top = position.y-20 + 'px';
	//����ͼ��Ĵ�С
	div.style.width = '1px';
	div.style.height ='1px';
};
MyMarker.prototype.onRemove = function(){
	this.div_.parentNode.removeChild(this.div_);
	this.div_ = null;
};

//ע�⣬�ɼ����Ա������ڱպ������е��ַ���
MyMarker.prototype.hide = function(){
	if (this.div_){
		this.div_.style.visibility = "hidden";
	}
};
MyMarker.prototype.show = function(){
	if (this.div_){
		this.div_.style.visibility = "visible";
	}
};
//��ʾ������ͼ��
MyMarker.prototype.toggle = function(){
	if (this.div_){
		if (this.div_.style.visibility == "hidden"){
			this.show();
        }
		else{
			this.hide();
        }
    }
};