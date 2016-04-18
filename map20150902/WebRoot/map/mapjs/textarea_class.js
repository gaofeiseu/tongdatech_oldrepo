/**************************************************************************************************/
function MyMarker(map,options){
	//initialize all properties.
	this.latlng = options.latlng; //设置图标的位置
	this.image_ = options.image;  //设置图标的图片
	this.labelText = options.labelText || '标记';
	this.labelClass = options.labelClass || 'shadow';//设置文字的样式
	this.clickFun = options.clickFun ;//注册点击事件
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
//初始化图标  
MyMarker.prototype.onAdd = function(){
    var div = document.createElement('DIV');//创建存放图片和文字的div
    div.style.border = "none";
    div.style.borderWidth = "0px";
    div.style.position = "absolute";
    div.style.cursor = "hand";
    div.onclick = this.clickFun ||function(){};//注册click事件，没有定义就为空函数    
    var img = document.createElement("img");//创建图片元素
    img.src = this.image_;
    img.style.width = "100%";
    img.style.height = "100%";
    //初始化文字标签
    var label = document.createElement('div');//创建文字标签
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
//绘制图标，主要用于控制图标的位置  
MyMarker.prototype.draw = function(){
	var overlayProjection = this.getProjection();
	var position = overlayProjection.fromLatLngToDivPixel(this.latlng);//将地理坐标转换成屏幕坐标
	//var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_.getNorthEast());
	//调整image的DIV标签的大小来适应页面
	var div = this.div_;
	div.style.left = position.x + 'px';
	div.style.top = position.y-20 + 'px';
	//控制图标的大小
	div.style.width = '1px';
	div.style.height ='1px';
};
MyMarker.prototype.onRemove = function(){
	this.div_.parentNode.removeChild(this.div_);
	this.div_ = null;
};

//注意，可见属性必须是在闭合括号中的字符串
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
//显示或隐藏图标
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