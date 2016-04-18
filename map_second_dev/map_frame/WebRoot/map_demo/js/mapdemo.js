/*!
 * Operation JS For Google Map
 * 
 * Copyright Gaofei and other contributors
 * Released under the MIT license
 * 
 * Date: 2016-1-12
 * Location: Nanjing
 */
var MapDemo = (function(){
	var _infowindow_width = 120;
	var _infowindow_higth = 120;
	var _infowindow_zindex = 20;

	var _marker_listener;
	var _infowindow_array = [];
	var _markers_array = [];
	var _line_points_array = [];
	var _area_points_array = [];
	var _google_maps;
	var _map;
	
	var __point_options = {};
	var __line_options = {};
	var __area_options = {};
	
	/***SYS***/
	var init = function(__map,__google_maps){
		_map = __map;
		_google_maps = __google_maps;
	};
	var getTotalMarkersNum = function(){
		if(_markers_array==undefined||_markers_array==null){
			return 0;
		}else{
			return _markers_array.length;
		}
	};
	var hide_all_markers = function(){
		if(_markers_array!=undefined&&_markers_array.length>0){
			for(var i=0;i<_markers_array.length;i++){
				_markers_array[i].setMap(null);
			}
			_markers_array.length = 0;
		}
	};
	var disable_mapclick_listener = function(){
		_google_maps.event.clearListeners(_map,'click');
	};
	var disable_mapdoubleleftclick_listener = function(){
		_google_maps.event.clearListeners(_map,'dblclick');
	};
	var _pre_infowindow_forshow = function(_marker_listen,_marker_open,_zindex,_infowindow){
		_google_maps.event.addListener(_marker_listen,'click',function(event){
			_cleanAllInfoWindow();
			_infowindow.open(_map,_marker_open);//this
			_infowindow.setZIndex(_zindex);
			_infowindow_array.push(_infowindow);
		})
	};
	var _cleanAllInfoWindow = function(){
		if(_infowindow_array.length>0){
			for(var i=0;i<_infowindow_array.length;i++){
				_infowindow_array[i].close();
			}
			_infowindow_array.length=0;
		}
	};
	/***SYS***/
	/***POINT***/
	var add_points = function(_point_option) {
		if(_point_option instanceof Object){
			_cache_point_optioninfo(_point_option);
			_marker_listener = _google_maps.event.addListenerOnce(_map,'click',function(event){
				_cleanAllInfoWindow();
				_ajax_for_pointsinfo();
				var marker = draw_point_marker(event.latLng.lat(),event.latLng.lng(),__point_options);
				_markers_array.push(marker);
				if(__point_options.have_infowindow){
					var _infowindow = _box_infowindow(__point_options.html+"<br/><div>lng:"+event.latLng.lng()+"</div><br/><div>lat:"+event.latLng.lat()+"</div>");
					_pre_infowindow_forshow(marker,marker,_infowindow_zindex,_infowindow);
				}
			});
		}
	};
	var _box_infowindow = function(_content){
		return new _google_maps.InfoWindow({
			content : _content,
			size : new _google_maps.Size(_infowindow_width,_infowindow_higth)
		});
	};
	//when you already have organized data,you can start from here to add all points into map without init listener
	var draw_point_marker = function(_lat,_lng,_point_option){
		return new _google_maps.Marker(_box_point_option(_lat,_lng,_point_option));
	};
	var _box_point_option = function(_lat,_lng,_point_option){
		return _getPointOptions(new _google_maps.LatLng(_lat,_lng),_point_option);
	};
	var _getPointOptions = function(_position,_point_option){
		var mOptions = {
			position : _position,
			draggable : _point_option.draggable,
			icon : _point_option.icon_uri,
			map : _map,
			title: _point_option.text+"\r\n("+_position.lat()+","+_position.lng()+")"//the text show in markers
		};
		return mOptions;
	};
	var _cache_point_optioninfo = function(_point_options){
		__point_options = _point_options;
	};
	var _ajax_for_pointsinfo = function(){

	};
	/***POINT***/
	/***LINE***/
	var add_lines = function(_line_options){
		_cache_line_optioninfo(_line_options);
		_line_points_array.length = 0;
		var _line;
		//define click for line's normal addition
		_marker_listener = _google_maps.event.addListener(_map,'click',function(event){
			_line_points_array.push(event.latLng);
			if(_line_points_array.length>=2){//want to draw a line?you need at least two points
				if(_line!=null&&_line!=undefined){
					_line.setMap(null);//it is necessary
				}
				_line = draw_line_marker(_line_points_array,__line_options);
			}
		});
		//define double left click for line's finish hit
		_google_maps.event.addListenerOnce(_map,'dblclick',function(event){
			_google_maps.event.removeListener(_marker_listener);//finish draw line with this
			_markers_array.push(_line);//store it
			//here you may can draw point at the any point of area like start point or end point
			//how to ? just call a function inside a function
			if(__line_options.have_points){
				if((__line_options.point_index_array instanceof Array)&&(__line_options.point_array instanceof Array)&&(__line_options.point_index_array.length==__line_options.point_array.length)&&(__line_options.point_index_array.length<_line_points_array.length)){
					for(var i=0;i<__line_options.point_array.length;i++){
						var marker = draw_point_marker(_line_points_array[Number(__line_options.point_index_array[i])-1].lat(),_line_points_array[Number(__line_options.point_index_array[i])-1].lng(),__line_options.point_array[i]);
						_markers_array.push(marker);
						if(__line_options.point_array[i].have_infowindow){
							var _infowindow = _box_infowindow(__line_options.point_array[i].html);
							_pre_infowindow_forshow(marker,marker,_infowindow_zindex,_infowindow);
						}
					}
				}
			}
			_line_points_array.length=0;
		});
	};
	//how to use ? see comments for function draw_point_marker()
	var draw_line_marker = function(_line_points_array,_line_options){
		return new _google_maps.Polyline(_box_line_option(_line_points_array,_line_options));
	};
	var _box_line_option = function(_line_points_array,_line_options){
		return _getLineOptions(_line_points_array,_line_options);
	};
	var _getLineOptions = function(_line_points_array,_line_options){
		//var lineSymbol = {path: _google_maps.SymbolPath.FORWARD_CLOSED_ARROW};//want to line with arrow? release this lineSymbol comments,and the icons comments below
		var mOptions = {
		    path : _line_points_array,
		    strokeColor : _line_options.strokeColor,
		    strokeOpacity : _line_options.strokeOpacity,//opacity range from 0.0 to 1.0
		    strokeWeight : _line_options.strokeWeight,//weight measure unit is px
		    clickable : _line_options.can_click,//boolean
		    //icons: [{icon: lineSymbol,offset: '100%'}],//this is what? look above
		    map: _map
		};
		return mOptions;
	};
	var _cache_line_optioninfo = function(_line_options){
		__line_options = _line_options;
	};
	var _ajax_for_linesinfo = function(){

	};
	/***LINE***/
	/***AREA***/
	var add_areas = function(_area_options){//_strokeColor,_strokeOpacity,_strokeWeight,_fillColor,_fillOpacity,_can_edit,_can_click
		_cache_area_optioninfo(_area_options);
		_area_points_array.length = 0;
		if(__area_options.can_click){
			__area_options.can_click = false;
			__area_options.set_can_click_true_later = true;
		}else{
			__area_options.set_can_click_true_later = false;
		}
		var _area;
		_marker_listener = _google_maps.event.addListener(_map,'click',function(event){
			_area_points_array.push(event.latLng);
			if(_area_points_array.length>=3){//want to draw a area?you need at least three points
				if(_area!=null&&_area!=undefined){
					_area.setMap(null);//it is necessary
				}
				_area = draw_area_marker(_area_points_array,__area_options);
			}
		});
		_google_maps.event.addListenerOnce(_map,'dblclick',function(event){
			_google_maps.event.removeListener(_marker_listener);//finish draw line with this
			if(__area_options.set_can_click_true_later){
				_area.setOptions({clickable:true});
				__area_options.can_click = true;
			}
			_markers_array.push(_area);//store it
			//here you may can draw point at the any point of area
			//how to ? just call a function inside a function
			if(__area_options.have_points){
				if((__area_options.point_index_array instanceof Array)&&(__area_options.point_array instanceof Array)&&(__area_options.point_index_array.length==__area_options.point_array.length)&&(__area_options.point_index_array.length<_area_points_array.length)){
					for(var i=0;i<__area_options.point_array.length;i++){
						var marker = draw_point_marker(_area_points_array[Number(__area_options.point_index_array[i])-1].lat(),_area_points_array[Number(__area_options.point_index_array[i])-1].lng(),__area_options.point_array[i]);
						_markers_array.push(marker);
						if(__area_options.point_array[i].have_infowindow){
							var _infowindow = _box_infowindow(__area_options.point_array[i].html);
							_pre_infowindow_forshow(marker,marker,_infowindow_zindex,_infowindow);
							if(__area_options.can_click){
								if(Number(__area_options.open_index)==(Number(i)+1)){
									_pre_infowindow_forshow(_area,marker,_infowindow_zindex,_infowindow);
								}
							}
						}
					}
				}
			}
			_area_points_array.length=0;
		});
	};
	//how to use ? see comments for function draw_point_marker()
	var draw_area_marker = function(_area_points_array,_area_options){
		return new _google_maps.Polygon(_box_area_option(_area_points_array,_area_options));
	};
	var _box_area_option = function(_area_points_array,_area_options){
		return _getAreaOptions(_area_points_array,_area_options);
	};
	var _getAreaOptions = function(_area_points_array,_area_options){
		var mOptions = {
			paths: _area_points_array,
			strokeColor: _area_options.strokeColor,
			strokeOpacity: _area_options.strokeOpacity,
			strokeWeight: _area_options.strokeWeight,
			fillColor: _area_options.fillColor,
			fillOpacity: _area_options.fillOpacity,
			editable:_area_options.can_edit,
			map:_map,
			clickable:_area_options.can_click
		};
		return mOptions;
	};
	var _cache_area_optioninfo = function(_area_options){
		__area_options = _area_options;
	};
	var _ajax_for_areas = function(){

	};
	/***AREA***/
	/***BOX***/
	return {
		init:init,
		getTotalMarkersNum:getTotalMarkersNum,
		hide_all_markers:hide_all_markers,
		add_points:add_points,
		draw_point_marker:draw_point_marker,
		add_lines:add_lines,
		draw_line_marker:draw_line_marker,
		add_areas:add_areas,
		draw_area_marker:draw_area_marker
	};
	/***BOX***/
})();