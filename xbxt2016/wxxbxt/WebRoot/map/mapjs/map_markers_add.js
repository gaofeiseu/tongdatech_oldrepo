/**
 * @author Mr.GaoFei
 */
var old_class_name = '';
var old_class_css = '';
function changeCssForClick(className,num_s){
	if(old_class_name!=''&&old_class_name!=undefined&&old_class_css!=''&&old_class_css!=undefined){
		$(old_class_name).css('background-position',old_class_css);
	}
	var css_c = '';
	var class_name = '.'+className;
	switch(num_s){
	case '1':
		css_c = '0 -52px';
		break;
	case '2':
		css_c = '-65px -52px';
		break;
	case '3':
		css_c = '-130px -52px';
		break;
	case '4':
		css_c = '-195px -52px';
		break;
	case '5':
		css_c = '-260px -52px';
		break;
	case '6':
		css_c = '-325px -52px';
		break;
	default:
		css_c = '0 -52px';
		break;
	}
	old_class_name = class_name;
	old_class_css = $(class_name).css('background-position');
	$(class_name).css('background-position',css_c);
	doWhatOperation(num_s);
}
function changeCssForBtnUp(className,num_s){
	var css_c = '';
	var class_name = '.'+className;
	switch(num_s){
	case '1':
		css_c = '0 0';
		break;
	case '2':
		css_c = '-65px 0';
		break;
	case '3':
		css_c = '-130px 0';
		break;
	case '4':
		css_c = '-195px 0';
		break;
	case '5':
		css_c = '-260px 0';
		break;
	case '6':
		css_c = '-325px 0';
		break;
	default:
		css_c = '0 0';
		break;
	}
	$(class_name).css('background-position',css_c);
}
function doWhatOperation(num_s){
	switch(num_s){
	case '1':
		cleanAllListeners_BackToMap();
		break;
	case '2':
		add_marker_point();
		break;
	case '3':
		add_marker_circle();
		break;
	case '4':
		addMarkerLine();
		break;
	case '5':
		addMarkerArea();
		
		break;
	case '6':
		//addTextAreaMarkers();//TODO
		addMarkerTextArea();
		break;
	default:
		break;
	}
}
var now_marker_type = '';
/**
 * 全局侦听
 */
var markerListener = null;
var infowindowArray_for_markers = [];
var message = null;
var marker_for_point_infowindow = null;
var array_point_markers = [];//记录了所有点的标记的数组
function add_marker_point(){//TODO
	markerListener = google.maps.event.addListener(map,'click',function(event){//改变监听方式//addListenerOnce
		clearAllInfoWindow();
		var myOptions = {
				position : event.latLng,
				draggable :false,
				icon :'/mapfiles/markers/0green-ball.png',
				map : map,
				title:'未保存！\n'+'lat:'+event.latLng.lat()+' \nlng:'+event.latLng.lng()
		};
		var marker = new google.maps.Marker(myOptions);
		array_point_markers.push(marker);
		now_marker_type = '1';
		var html = "<div><font size='3'><b>添加标记</b></font></div>" +
				"<div><hr></div>" +
				getChildClassSelect('1','1','map_select_childclass')+
				"<div><hr></div>" +
				"<div><input type='hidden' id='marker_point_whichicon' value='/mapfiles/markers/0green-ball.png'/></div>"+
				"<div style='float:right'>" +
				"<input type=\"button\" id=\"map_marker_point_icon_change\" value=\"     更换图标     \" onclick=\"markerPointChangeMarkerIcon();\"/>"+
				"<input type=\"button\" id=\"map_marker_point_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent();\"/>"+
				"<input type=\"button\" id=\"map_marker_point_delete\" value=\"     删除     \" onclick=\"display_select_point_marker();\"/>"+
				"</div>";
		
		message = new google.maps.InfoWindow({
			content : html,
			size : new google.maps.Size(120,120)
		});
		google.maps.event.addListener(marker, 'click', function(event) {
			clearAllInfoWindow();
			message.open(map,this);
			message.setZIndex(1000);
			marker_for_point_infowindow = this;
			infowindowArray_for_markers.push(message);
		});
	});
}
var list_map_for_line=[];//所有线的对象的数组
var now_op_line_marker=null;
function addMarkerLine(){
	cleanAllListeners_BackToMap();
	var flightPath;
	var marker_for_line=[];
	var lat_for_line;
	var lng_for_line;
	var listener_2;
	listener_2 = google.maps.event.addListener(map,'click',function(event){
		//开始线的标注
		marker_for_line.push(event.latLng);
		if(marker_for_line.length>=2){//当有2个或以上的点的时候，才进行画线操作
			//var lineSymbol = {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW};---------线带箭头
			if(flightPath!=null&&flightPath!=undefined){
				flightPath.setMap(null);
			}
			flightPath = new google.maps.Polyline({
			    path: marker_for_line,
			    strokeColor: "#F00000",
			    strokeOpacity: 1.0,//不透明度  0.0---1.0
			    strokeWeight: 2,//宽度  px为单位
			    clickable:false,
			    //icons: [{icon: lineSymbol,offset: '100%'}],----------线带箭头
			    map: map
			});
		}
	});
	google.maps.event.addListenerOnce(map,'dblclick',function(event){//TODO
		google.maps.event.removeListener(listener_2);
		//结束线的标注
		////console.log('结束线的标注');
		now_marker_type='2';
		var htmlSt = "<div><font size='3'><b>线路信息</b></font></div>" +
		"<div><hr></div>" +
		getChildClassSelect('2','1','map_select_childclass')+
		"<div><hr></div>" +
		"<div><input type='hidden' id='hidden_line_color' value='#FF0000||1.0||2'/></div>"+
		"<div style='float:right'>" +
		"<input type=\"button\" id=\"map_marker_line_color\" value=\"     更换颜色     \" onclick=\"changeLineMarkerColor();\"/>"+
		"<input type=\"button\" id=\"map_marker_line_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent2();\"/>"+//map_marker_point_add
		"<input type=\"button\" id=\"map_marker_line_delete\" value=\"     删除     \" onclick=\"deleteMarkerLine()\"/>"+
		"</div>";
		
		var messageInfoWindowForLineSt = new google.maps.InfoWindow({
			content : htmlSt,
			size : new google.maps.Size(120,120)
		});
		
		var line_st_marker = new google.maps.Marker({
		      position: marker_for_line[0],
		      map: map,
		      icon: "/mapfiles/markers3/dest_markers_14848ce_st.png",
		      title:"未保存状态"
		});
		var line_ed_marker = new google.maps.Marker({
		      position: marker_for_line[marker_for_line.length-1],
		      map: map,
		      icon: "/mapfiles/markers3/dest_markers_14848ce_ed.png",
		      title:"未保存状态"
		});
		for(var o=0;o<marker_for_line.length;o++){
			lat_for_line+=marker_for_line[o].lat()+'@';
			lng_for_line+=marker_for_line[o].lng()+'@';
		}
		
		var map_for_line = {};
		map_for_line["st_point"]=line_st_marker;
		map_for_line["ed_point"]=line_ed_marker;
		map_for_line["line"]=flightPath;
		map_for_line["line_lat"]=lat_for_line;
		map_for_line["line_lng"]=lng_for_line;
		
		list_map_for_line.push(map_for_line);
		google.maps.event.addListener(line_st_marker, 'click', function(event) {
			clearAllInfoWindow();
			messageInfoWindowForLineSt.open(map,this);
			messageInfoWindowForLineSt.setZIndex(1000);
			infowindowArray_for_markers.push(messageInfoWindowForLineSt);
			now_op_line_marker=this;
			map.panTo(line_st_marker.getPosition());
		});
		marker_for_line.length=0;
		changeCssForBtnUp('map_markers_t4','4');//图标重置
	});
}
var list_map_for_area=[];//所有面的各种对象参数的数组
var now_op_area_marker=null;
var if_click_3 = false;
var marker_for_area=[];//点击事件数组
var listener_2;
var lat_for_area;
var lng_for_area;
var trans_area_marker = null;
function addMarkerArea(){
	var area_marker;
	if(!if_click_3){
		trans_area_marker = null;
		cleanAllListeners_BackToMap();//清空地图上的click监听事件
		listener_2 = google.maps.event.addListener(map,'click',function(event){
			marker_for_area.push(event.latLng);
			if(marker_for_area.length>=3){//当有2个或以上的点的时候，才进行画线操作
				if(area_marker!=null&&area_marker!=undefined){
					area_marker.setMap(null);
				}
				area_marker = new google.maps.Polygon({
					    paths: marker_for_area,
					    strokeColor: "#FF0000",
					    strokeOpacity: 0.8,
					    strokeWeight: 3,
					    fillColor: "#FF0000",
					    fillOpacity: 0.35,
					    editable:true,
					    map:map,
					    clickable:false
				});
				trans_area_marker = area_marker;
			}
		});
		if_click_3 = true;
	}else{
		trans_area_marker.setOptions({editable:false});
		google.maps.event.removeListener(listener_2);
		//console.log('结束面的标注');
		now_marker_type='3';
		var htmlArea = "<div><font size='3'><b>区域信息</b></font></div>" +
		"<div><hr></div>" +
		getChildClassSelect('3','1','map_select_childclass')+
		"<div><hr></div>" +
		"<div><input type='hidden' id='hidden_area_color' value='#FF0000'/></div>"+
		"<div style='float:right'>" +
		"<input type=\"button\" id=\"map_marker_area_color\" value=\"     更换颜色     \" onclick=\"changeAreaMarkerColor();\"/>"+
		"<input type=\"button\" id=\"map_marker_area_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent3();\"/>"+//map_marker_point_add
		"<input type=\"button\" id=\"map_marker_area_delete\" value=\"     删除     \" onclick=\"doDeleteAreaMarker()\"/>"+
		"</div>";
		
		var messageInfoWindowForArea = new google.maps.InfoWindow({
			content : htmlArea,
			size : new google.maps.Size(120,80)
		});
		var area_point_marker = new google.maps.Marker({
		      position: marker_for_area[0],
		      map: map,
		      icon: "/mapfiles/markers/0point_markers_ball_red.png",
		      title:"未保存状态"
		});
		lat_for_area = null;
		lng_for_area = null;
		for(var o=0;o<marker_for_area.length;o++){
			lat_for_area+=marker_for_area[o].lat().toString().substr(0,9)+'@';//update by gaofei for can allow more points in one area
			lng_for_area+=marker_for_area[o].lng().toString().substr(0,9)+'@';
		}
		
		var map_for_area = {};
		map_for_area["area_point"]=area_point_marker;
		map_for_area["area"]=trans_area_marker;//area_marker
		//console.log('area_marker:'+trans_area_marker);
		map_for_area["area_lat"]=lat_for_area;
		map_for_area["area_lng"]=lng_for_area;
		list_map_for_area.push(map_for_area);
		
		google.maps.event.addListener(area_point_marker, 'click', function(event) {
			clearAllInfoWindow();
			messageInfoWindowForArea.open(map,this);
			messageInfoWindowForArea.setZIndex(1000);
			infowindowArray_for_markers.push(messageInfoWindowForArea);
			now_op_area_marker=this;
		});
		marker_for_area.length=0;
		changeCssForBtnUp('map_markers_t5','5');//图标重置
		
		if_click_3 = false;
	}
	
//	google.maps.event.addListenerOnce(map,'dblclick',function(event){
//		
//		
//	});
}

function cleanAllListeners_BackToMap(){
	google.maps.event.clearListeners(map,'click');
}

function clearAllInfoWindow(){
	if(infowindowArray_for_markers.length>0){
		for(var i=0;i<infowindowArray_for_markers.length;i++){
			infowindowArray_for_markers[i].close();
		}
		infowindowArray_for_markers.length=0;
	}
}
var s_area_brch_value='';
var s_area_childclass_value='';
function changeAreaMarkerColor(){
	s_area_brch_value = $("#map_select_brch").val();
	s_area_childclass_value = $("#map_select_childclass").val();
	var htmlArea = "<div><font size='3'><b>区域颜色选择</b></font></div>" +
	"<div><hr></div>" +
	"<div>颜色:<input type=\"text\" id=\"select_my_area_color\" name=\"select_my_area_color\" value=\"#FF0000\" /></div>" +
	"<div><div id=\"areacolorpicker\"></div></div>" +
	"<div>面透明度<input id=\"area_strokeOpacity\" name=\"area_strokeOpacity\" value=\"0.4\" /><font color=\"red\">0.0-1.0(1.0表示不透明)</font></div>"+
	"<div>面边线粗细度<input id=\"area_strokeWeight\" name=\"area_strokeWeight\" value=\"3\" /><font color=\"red\">最小为2(越大越粗)</font></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"area_color_confirm\" value=\"确认\" onclick=\"area_color_confirm();\"/>"+
	"</div>";
	infowindowArray_for_markers[0].setContent(htmlArea);
	$('#areacolorpicker').farbtastic('#select_my_area_color');
}

function area_color_confirm(){
	var a_strokeOpacity = $("#area_strokeOpacity").val();
	var a_strokeWeight = $("#area_strokeWeight").val();
	if(isNaN(a_strokeOpacity)){
		a_strokeOpacity=0.4;
	}else{
		if(a_strokeOpacity>1.0){
			a_strokeOpacity=1.0;
		}
	}
	if(isNaN(a_strokeWeight)){
		a_strokeWeight=3;
	}else{
		if(a_strokeWeight<2){
			a_strokeWeight=2;
		}
	}
	//console.log('area_color:'+$("#select_my_area_color").val());
	$.each(list_map_for_area,function(n,value){
        if(now_op_area_marker===value.area_point){
        	value.area.setOptions({fillColor: $("#select_my_area_color").val(),strokeColor:$("#select_my_area_color").val()});
			value.area.setOptions({fillOpacity:a_strokeOpacity});
			value.area.setOptions({strokeWeight:a_strokeWeight});
        }
    });
	
	var h_line_color = $("#select_my_area_color").val()+"||"+a_strokeOpacity+"||"+a_strokeWeight;
	
	var htmlArea = "<div><font size='3'><b>区域信息</b></font></div>" +
	"<div><hr></div>" +
	getChildClassSelect('3',s_area_brch_value,'map_select_childclass')+
	"<div><hr></div>" +
	"<div><input type='hidden' id='hidden_area_color' value='"+h_line_color+"'/></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"map_marker_area_color\" value=\"     更换颜色     \" onclick=\"changeAreaMarkerColor();\"/>"+
	"<input type=\"button\" id=\"map_marker_area_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent3();\"/>"+//map_marker_point_add
	"<input type=\"button\" id=\"map_marker_area_delete\" value=\"     删除     \" onclick=\"doDeleteAreaMarker()\"/>"+
	"</div>";
	
	infowindowArray_for_markers[0].setContent(htmlArea);
	$("#map_select_brch option[value='"+s_area_brch_value+"']").attr("selected",true);
	$("#map_select_childclass option[value='"+s_area_childclass_value+"']").attr("selected",true);
}

var s_point_brch_value;
var s_point_brch_text;
var s_point_childclass_value;
var s_point_childclass_text;
function markerPointChangeMarkerIcon(){
	s_point_brch_value = $("#map_select_brch").val();
	s_point_brch_text = $("#map_select_brch").find("option:selected").text();
	s_point_childclass_value = $("#map_select_childclass").val();
	s_point_childclass_text = $("#map_select_childclass").find("option:selected").text();
	
	clearAllInfoWindow();
	
	var url="map_markersaddition_getMyIconSelectHtml.action";
	var json = {};
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
					message = new google.maps.InfoWindow({
						content : msg.returnHtml,
						size : new google.maps.Size(120,80)
					});
						
					message.open(map,marker_for_point_infowindow);
					message.setZIndex(1000);
					infowindowArray_for_markers.push(message);
				}else{
					alert(msg.msg);
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function display_select_point_marker(){
	marker_for_point_infowindow.setMap(null);
	clearAllInfoWindow();
}

function setMarkerIconToMySelect(x){
	var tmp_array = [];
	tmp_array = x.split('/mapfiles/markers/');
	var my_url = '/mapfiles/markers/'+tmp_array[1];
	marker_for_point_infowindow.setIcon(my_url);
	returnToAddMarkersInfoWindow(my_url);
}

function refresh_icon_select_dlg(){
	var url="map_markersaddition_getMyIconSelectHtml.action";
	var json = {};
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
					message.setContent(msg.returnHtml);
				}else{
					alert(msg.msg);
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function open_icon_add_dlg(){
	$('#icon_add_dlg').dialog('open');
}

function do_icon_add(){
	var file_exe = $("#icon_viewfile").val().split(".")[$("#icon_viewfile").val().split(".").length-1];
	var file_name = $("#icon_viewfile").val();
	
	if(file_exe!='bmp'&&file_exe!='BMP'&&file_exe!='jpeg'&&file_exe!='JPEG'&&file_exe!='jpg'&&file_exe!='JPG'&&file_exe!='png'&&file_exe!='PNG'&&file_exe!='gif'&&file_exe!='GIF'){
		alert('只接受图片类型的文件上传！');
		return;
	}
	
	$("#upload_addicon").attr({"disabled":"disabled"});
	fmSubmit_4("#icon_add_frm","map_markersaddition_do_icon_add.action?add_icon_filename="
			+file_name,function(result){
		//将按钮可用
		$("#upload_addicon").removeAttr("disabled");
		if(result.if_success){
			$('#icon_add_dlg').dialog('close');
			refresh_icon_select_dlg();
		}
	});
}
function fmSubmit_4(fmSelector,urls,callback){
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

function returnToAddMarkersInfoWindow(x){
	clearAllInfoWindow();
	
	var html = "<div><font size='3'><b>添加标记</b></font></div>" +
	"<div><hr></div>" +
	getChildClassSelect('1',s_point_brch_value,'map_select_childclass')+
	"<div><hr></div>" +
	"<div><input type='hidden' id='marker_point_whichicon' value='"+x+"'/></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"map_marker_point_icon_change\" value=\"     更换图标     \" onclick=\"markerPointChangeMarkerIcon();\"/>"+
	"<input type=\"button\" id=\"map_marker_point_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent();\"/>"+
	"<input type=\"button\" id=\"map_marker_point_delete\" value=\"     删除     \" onclick=\"display_select_point_marker();\"/>"+
	"</div>";
	
	message = new google.maps.InfoWindow({
		content : html,
		size : new google.maps.Size(120,80)
	});
		
	message.open(map,marker_for_point_infowindow);
	message.setZIndex(1000);
	infowindowArray_for_markers.push(message);
	$("#map_select_brch option[value='"+s_point_brch_value+"']").attr("selected",true);
	$("#map_select_childclass option[value='"+s_point_childclass_value+"']").attr("selected",true);
}

function giveHiddenDivContent(){
	var dept_type=$("#map_select_brch").val();
	var child_class=$("#map_select_childclass").val();
	
	if(child_class=='0'){
		alert('没有对应的查询结果！');
		return;
	}
	
	var url="map_markersaddition_giveHiddenDivContent.action";
	var json = {};
	json["html_addpoint_select2"]=dept_type;
	json["html_addpoint_select3"]=child_class;
	
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
					$("#hidden_table_for_point_zishiying tr").empty();
					$("#hidden_table_for_line_zishiying tr").empty();
					$("#hidden_table_for_area_zishiying tr").empty();
					$("#hidden_table_for_circle_zishiying tr").empty();
					$("#hidden_table_for_textarea_zishiying tr").empty();
					$("#selecticon_num_11").val($("#marker_point_whichicon").val());
					$("#selectclass_tablename1").val(msg.returnString);
					for(var i=0;i<msg.list_map.length;i++){
						var newRow = '';
						if(msg.list_map[i].class_columntype=='4'){
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}else{
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}
						$("#hidden_table_for_point_zishiying tr:last").after(newRow);
						//console.log('newRow:'+newRow);
					}
					$("#point_zishiying_dlg").dialog("open");
				}else{
					$("#hidden_table_for_point_zishiying tr").empty();
					alert('没有对应的查询属性！');
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function addPointMarkerS(){
	var user_marker_point_marker_lat = marker_for_point_infowindow.getPosition().lat();
	var user_marker_point_marker_lon = marker_for_point_infowindow.getPosition().lng();
	
	var id_array=[];
	var value_array=[];
	var type_array=[];
	var nullable_array=[];
	var size_array=[];
	$("input.hidden_table_input").each(function(){
	    value_array.push($(this).val());
	    id_array.push($(this).attr("id"));
	});
	var i_null=0;
	for(var i=0;i<value_array.length;i++){
		if(value_array[i]==''||value_array[i]==undefined||value_array[i]==null){
			i_null++;
		}else{
			continue;
		}
	}
	if(i_null==value_array.length){
		alert('事实上，我们不允许所有属性字段都为空！');
		return;
	}
	$("input.hidden_table_input_type").each(function(){
		type_array.push($(this).val());
	});
	$("input.hidden_table_input_nullable").each(function(){
		nullable_array.push($(this).val());
	});
	$("input.hidden_table_input_size").each(function(){
		size_array.push($(this).val());
	});
	var if_success1=true;
	var if_success2=true;
	for(var i=0;i<id_array.length;i++){
		if(type_array[i]!='4'){
			if(value_array[i].indexOf('.jpg')>-1||value_array[i].indexOf('.JPG')>-1
					||value_array[i].indexOf('.png')>-1||value_array[i].indexOf('.PNG')>-1
					||value_array[i].indexOf('.bmp')>-1||value_array[i].indexOf('.BMP')>-1
					||value_array[i].indexOf('.gif')>-1||value_array[i].indexOf('.GIF')>-1
					||value_array[i].indexOf('.jpeg')>-1||value_array[i].indexOf('.JPEG')>-1){
				if_success2 = false;
				break;
			}
		}
		
		if(type_array[i]=='2'||type_array[i]=='3'){
			if(!isNaN(value_array[i])){
				
			}else{
				if_success1=false;
				break;
			}
		}
		if(nullable_array[i]=='2'){
			if(value_array[i]==undefined||value_array[i]==''){
				if_success1=false;
				break;
			}
		}
		if(size_array[i]!='NULL'){
			if(value_array[i].length>=Number(size_array[i])){
				if_success1=false;
				break;
			}
		}
	}
	if(if_success1&&if_success2){
		var value_str = JSON.stringify(value_array);
		var id_str = JSON.stringify(id_array);
		fmSubmit_1("#point_zishiying_frm","map_markersaddition_addPointMarkerS.action?html_id_str="
				+id_str+"&html_value_str="
				+value_str+"&html_table_name="
				+$("#selectclass_tablename1").val()+"&html_icon_num="
				+$("#selecticon_num_11").val()+"&html_location_lat="
				+user_marker_point_marker_lat+"&html_location_lon="
				+user_marker_point_marker_lon,function(result){
			//干点什么
		});
		
	}else if(!if_success1){
		alert('没有通过验证，请注意数值类型，内容是否可以为空，以及内容长度不要过长！');
	}else if(!if_success2){
		alert('请不要在不是图片的属性字段中包含.jpg .bmp .jpeg .png .gif字样！');
	}
}

function fmSubmit_1(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			var result = eval("("+result+")");
			if (result.if_success){
				$("#point_zishiying_dlg").dialog("close");
				message.close();
				marker_for_point_infowindow.setTitle(result.returnString);
				google.maps.event.removeListener(markerListener);
			} else {
				alert(msg.msg);
				return;
			}
		},
		error: function(){
			alert('error!!');
		}
	});
}

function changeHtmlForBrchChange(aa){
	var optionsArr = [];
	optionsArr = changeChildClassSelectOptions(now_marker_type,aa);
	$("#map_select_childclass").empty();
	for(var i=0;i<optionsArr.length;i++){
		$("#map_select_childclass").append(optionsArr[i]);
	}
}
var s_line_brch_value='';
var s_line_childclass_value='';
function changeLineMarkerColor(){
	s_line_brch_value = $("#map_select_brch").val();
	s_line_childclass_value = $("#map_select_childclass").val();
	var htmlchangecolor = "<div><font size='3'><b>线路颜色选择</b></font></div>" +
	"<div><hr></div>" +
	"<div>颜色:<input type=\"text\" id=\"select_my_line_color\" name=\"select_my_line_color\" value=\"#FF0000\" /></div>" +
	"<div><div id=\"linecolorpicker\"></div></div>" +
	"<div>透明度<input id=\"line_strokeOpacity\" name=\"line_strokeOpacity\" value=\"1.0\" /><font color=\"red\">0.0-1.0(1.0表示不透明)</font></div>"+
	"<div>粗细度<input id=\"line_strokeWeight\" name=\"line_strokeWeight\" value=\"2\" /><font color=\"red\">最小为2(越大越粗)</font></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"line_color_confirm\" value=\"确认\" onclick=\"line_color_confirm();\"/>"+
	"</div>";
	infowindowArray_for_markers[0].setContent(htmlchangecolor);
	$('#linecolorpicker').farbtastic('#select_my_line_color');
}

function deleteMarkerLine(){
	$.each(list_map_for_line,function(n,value){
        if(now_op_line_marker===value.st_point){
        	value.line.setMap(null);
        	value.st_point.setMap(null);
        	value.ed_point.setMap(null);
        }
    });
}

function doDeleteAreaMarker(){
	$.each(list_map_for_area,function(n,value){
        if(now_op_area_marker===value.area_point){
        	value.area_point.setMap(null);
        	value.area.setMap(null);
        }
    });
}

function line_color_confirm(){
	var l_strokeOpacity = $("#line_strokeOpacity").val();
	var l_strokeWeight = $("#line_strokeWeight").val();
	if(isNaN(l_strokeOpacity)){
		l_strokeOpacity=1.0;
	}else{
		if(l_strokeOpacity>1.0){
			l_strokeOpacity=1.0;
		}
	}
	if(isNaN(l_strokeWeight)){
		l_strokeWeight=2;
	}else{
		if(l_strokeWeight<2){
			l_strokeWeight=2;
		}
	}
	
	$.each(list_map_for_line,function(n,value){
        if(now_op_line_marker===value.st_point){
        	value.line.setOptions({strokeColor:$("#select_my_line_color").val()});
        	value.line.setOptions({strokeOpacity:l_strokeOpacity});
        	value.line.setOptions({strokeWeight:l_strokeWeight});
        }
    });
	var h_line_color = $("#select_my_line_color").val()+"||"+l_strokeOpacity+"||"+l_strokeWeight;
	
	//console.log('select_my_line_color---->'+$("#select_my_line_color").val());
	
	var htmlSt = "<div><font size='3'><b>线路信息</b></font></div>" +
	"<div><hr></div>" +
	getChildClassSelect('2',s_line_brch_value,'map_select_childclass')+
	"<div><hr></div>" +
	"<div><input type='hidden' id='hidden_line_color' value='"+h_line_color+"'/></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"map_marker_line_color\" value=\"     更换颜色     \" onclick=\"changeLineMarkerColor();\"/>"+
	"<input type=\"button\" id=\"map_marker_line_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent2();\"/>"+//map_marker_point_add
	"<input type=\"button\" id=\"map_marker_line_delete\" value=\"     删除     \" onclick=\"deleteMarkerLine()\"/>"+
	"</div>";

	infowindowArray_for_markers[0].setContent(htmlSt);
	$("#map_select_brch option[value='"+s_line_brch_value+"']").attr("selected",true);
	$("#map_select_childclass option[value='"+s_line_childclass_value+"']").attr("selected",true);
}

function giveHiddenDivContent2(){
	var big_type='2';
	var dept_type=$("#map_select_brch").val();
	var child_class=$("#map_select_childclass").val();
	
	if(child_class=='0'){
		alert('没有对应的查询结果！');
		return;
	}
	var url="map_markersaddition_giveHiddenDivContent2.action";
	var json = {};
	json["html_addline_select1"]=big_type;
	json["html_addline_select2"]=dept_type;
	json["html_addline_select3"]=child_class;
	
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
					$("#hidden_table_for_point_zishiying tr").empty();
					$("#hidden_table_for_line_zishiying tr").empty();
					$("#hidden_table_for_area_zishiying tr").empty();
					$("#hidden_table_for_circle_zishiying tr").empty();
					$("#hidden_table_for_textarea_zishiying tr").empty();
					$("#selecticon_num_22").val($("#hidden_line_color").val());
					$("#selectclass_tablename2").val(msg.returnString);
					for(var i=0;i<msg.list_map.length;i++){
						var newRow = "";
						if(msg.list_map[i].class_columntype=='4'){
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}else{
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}
//						//console.log('newRow:'+newRow);
						$("#hidden_table_for_line_zishiying tr:last").after(newRow);
					}
					$("#line_zishiying_dlg").dialog("open");
				}else{
					$("#hidden_table_for_line_zishiying tr").empty();
					alert('没有对应的查询属性！');
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function giveHiddenDivContent3(){
	var big_type='3';
	var dept_type=$("#map_select_brch").val();
	var child_class=$("#map_select_childclass").val();
	
	if(child_class=='0'){
		alert('没有对应的查询结果！');
		return;
	}
	var url="map_markersaddition_giveHiddenDivContent3.action";
	var json = {};
	json["html_addarea_select1"]=big_type;
	json["html_addarea_select2"]=dept_type;
	json["html_addarea_select3"]=child_class;
	
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
					$("#hidden_table_for_point_zishiying tr").empty();
					$("#hidden_table_for_line_zishiying tr").empty();
					$("#hidden_table_for_area_zishiying tr").empty();
					$("#hidden_table_for_circle_zishiying tr").empty();
					$("#hidden_table_for_textarea_zishiying tr").empty();
					$("#selecticon_num_33").val($("#hidden_area_color").val());
					$("#selectclass_tablename3").val(msg.returnString);
					for(var i=0;i<msg.list_map.length;i++){
						var newRow = "";
						if(msg.list_map[i].class_columntype=='4'){
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}else{
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}
//						//console.log('newRow:'+newRow);
						$("#hidden_table_for_area_zishiying tr:last").after(newRow);
					}
					$("#area_zishiying_dlg").dialog("open");
				}else{
					$("#hidden_table_for_area_zishiying tr").empty();
					alert('没有对应的查询属性！');
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function addLineMarkerS(){
	var lineColor=$("#selecticon_num_22").val();
	var id_array=[];
	var value_array=[];
	var type_array=[];
	var nullable_array=[];
	var size_array=[];
	var lat_for_line='';
	var lng_for_line='';
	$.each(list_map_for_line,function(n,value){
        if(now_op_line_marker===value.st_point){
        	if(value.line_lat==null||value.line_lat==undefined||value.line_lat==''||
        			value.line_lng==null||value.line_lng==undefined||value.line_lng==''){
        		alert('线路参数错误！');
        		return;
        	}
        	lat_for_line=value.line_lat;
        	lng_for_line=value.line_lng;
        	//console.log('line_lat:'+value.line_lat);
        	//console.log('line_lng:'+value.line_lng);
        }
    });
	
	$("input.hidden_table_input").each(function(){
	    value_array.push($(this).val());
	    id_array.push($(this).attr("id"));
	});
	var i_null=0;
	for(var i=0;i<value_array.length;i++){
		if(value_array[i]==''||value_array[i]==undefined||value_array[i]==null){
			i_null++;
		}else{
			continue;
		}
	}
	if(i_null==value_array.length){
		alert('事实上，我们不允许所有字段属性都为空！');
		return;
	}
	$("input.hidden_table_input_type").each(function(){
		type_array.push($(this).val());
	});
	$("input.hidden_table_input_nullable").each(function(){
		nullable_array.push($(this).val());
	});
	$("input.hidden_table_input_size").each(function(){
		size_array.push($(this).val());
	});
	
	var if_success1=true;
	var if_success2=true;
	for(var i=0;i<id_array.length;i++){
		//console.log('ID:'+id_array[i]+'\n 值：'+value_array[i]+'\n type:'+type_array[i]+'\n nullable:'+nullable_array[i]+'\n size:'+size_array[i]);
		if(type_array[i]!='4'){
			if(value_array[i].indexOf('.jpg')>-1||value_array[i].indexOf('.JPG')>-1
					||value_array[i].indexOf('.png')>-1||value_array[i].indexOf('.PNG')>-1
					||value_array[i].indexOf('.bmp')>-1||value_array[i].indexOf('.BMP')>-1
					||value_array[i].indexOf('.gif')>-1||value_array[i].indexOf('.GIF')>-1
					||value_array[i].indexOf('.jpeg')>-1||value_array[i].indexOf('.JPEG')>-1){
				if_success2 = false;
				break;
			}
		}
		
		if(type_array[i]=='2'||type_array[i]=='3'){
			if(!isNaN(value_array[i])){
				
			}else{
				if_success1=false;
				break;
			}
		}
		if(nullable_array[i]=='2'){
			if(value_array[i]==undefined||value_array[i]==''){
				if_success1=false;
				break;
			}
		}
		if(size_array[i]!='NULL'){
			if(value_array[i].length>=Number(size_array[i])){
				if_success1=false;
				break;
			}
		}
	}
	
	lineColor = lineColor.replace('#','@');
	
	if(if_success1&&if_success2){
		var value_str = JSON.stringify(value_array);
		var id_str = JSON.stringify(id_array);
		fmSubmit_2("#line_zishiying_frm","map_markersaddition_addLineMarkerS.action?html_id_str="
				+id_str+"&html_value_str="
				+value_str+"&html_table_name="
				+$("#selectclass_tablename2").val()+"&html_icon_num="
				+lineColor+"&html_location_lat="
				+lat_for_line+"&html_location_lon="
				+lng_for_line,function(result){
			
		});
	}else if(!if_success1){
		alert('没有通过验证，请注意数值类型，内容是否可以为空，以及内容长度！');
	}else if(!if_success2){
		alert('请不要在不是图片的属性字段中包含.jpg .bmp .jpeg .png .gif字样！');
	}
}

function addAreaMarkerS(){
	var areaColor=$("#selecticon_num_33").val();
	var id_array=[];
	var value_array=[];
	var type_array=[];
	var nullable_array=[];
	var size_array=[];
	var lat_for_area='';
	var lng_for_area='';
	$.each(list_map_for_area,function(n,value){
        if(now_op_area_marker===value.area_point){
        	if(value.area_lat==null||value.area_lat==undefined||value.area_lat==''||
        			value.area_lng==null||value.area_lng==undefined||value.area_lng==''){
        		alert('区域标注参数错误！');
        		return;
        	}
        	lat_for_area=value.area_lat;
        	lng_for_area=value.area_lng;
        	//console.log('lat_for_area:'+value.area_lat);
        	//console.log('lng_for_area:'+value.area_lng);
        }
    });
	
	$("input.hidden_table_input").each(function(){
	    value_array.push($(this).val());
	    id_array.push($(this).attr("id"));
	});
	var i_null=0;
	for(var i=0;i<value_array.length;i++){
		if(value_array[i]==''||value_array[i]==undefined||value_array[i]==null){
			i_null++;
		}else{
			continue;
		}
	}
	if(i_null==value_array.length){
		alert('事实上，我们不允许所有字段属性都为空！');
		return;
	}
	$("input.hidden_table_input_type").each(function(){
		type_array.push($(this).val());
	});
	$("input.hidden_table_input_nullable").each(function(){
		nullable_array.push($(this).val());
	});
	$("input.hidden_table_input_size").each(function(){
		size_array.push($(this).val());
	});
	
	var if_success1=true;
	var if_success2=true;
	for(var i=0;i<id_array.length;i++){
		//console.log('ID:'+id_array[i]+'\n 值：'+value_array[i]+'\n type:'+type_array[i]+'\n nullable:'+nullable_array[i]+'\n size:'+size_array[i]);
		if(type_array[i]!='4'){
			if(value_array[i].indexOf('.jpg')>-1||value_array[i].indexOf('.JPG')>-1
					||value_array[i].indexOf('.png')>-1||value_array[i].indexOf('.PNG')>-1
					||value_array[i].indexOf('.bmp')>-1||value_array[i].indexOf('.BMP')>-1
					||value_array[i].indexOf('.gif')>-1||value_array[i].indexOf('.GIF')>-1
					||value_array[i].indexOf('.jpeg')>-1||value_array[i].indexOf('.JPEG')>-1){
				if_success2 = false;
				break;
			}
		}
		if(type_array[i]=='2'||type_array[i]=='3'){
			if(!isNaN(value_array[i])){
				
			}else{
				if_success1=false;
				break;
			}
		}
		if(nullable_array[i]=='2'){
			if(value_array[i]==undefined||value_array[i]==''){
				if_success1=false;
				break;
			}
		}
		if(size_array[i]!='NULL'){
			if(value_array[i].length>=Number(size_array[i])){
				if_success1=false;
				break;
			}
		}
	}
	
	areaColor = areaColor.replace('#','@');
	
	if(if_success1&&if_success2){
		var value_str = JSON.stringify(value_array);
		var id_str = JSON.stringify(id_array);
		fmSubmit_3("#area_zishiying_frm","map_markersaddition_addAreaMarkerS.action?html_id_str="
				+id_str+"&html_value_str="
				+value_str+"&html_table_name="
				+$("#selectclass_tablename3").val()+"&html_icon_num="
				+areaColor+"&html_location_lat="
				+lat_for_area+"&html_location_lon="
				+lng_for_area,function(result){
			
		});
	}else if(!if_success1){
		alert('没有通过验证，请注意数值类型，内容是否可以为空，以及内容长度！');
	}else if(!if_success2){
		alert('请不要在不是图片的属性字段中包含.jpg .bmp .jpeg .png .gif字样！');
	}
}

function fmSubmit_2(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			//console.log('1111111111111');
			var result = eval("("+result+")");
			if(result.if_success){
				$("#line_zishiying_dlg").dialog("close");
				infowindowArray_for_markers[0].close();
				$.each(list_map_for_line,function(n,value){
			        if(now_op_line_marker===value.st_point){
			        	value.st_point.setTitle(result.returnString);
			        	value.ed_point.setTitle(result.returnString);
			        	google.maps.event.clearListeners(now_op_line_marker,'click');
			        }
			    });
			}else{
				alert(result.msg);
				return;
			}
		},
		error: function(){
			alert('error!!');
		}
	});
}

function fmSubmit_3(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
				$("#area_zishiying_dlg").dialog("close");
				infowindowArray_for_markers[0].close();
				$.each(list_map_for_area,function(n,value){
			        if(now_op_area_marker===value.area_point){
			        	value.area_point.setTitle(result.returnString);
			        	google.maps.event.clearListeners(now_op_area_marker,'click');
			        }
			    });
			}else{
				//用户"点"标记记录失败
				alert(result.msg);
				return;
			}
		},
		error: function(){
			alert('error!!');
		}
	});
}


function getUserTypeSelect(select_brch_id){
	var url = "map_markersaddition_getUserTypeSelect.action";
	var fmdata = {};
	fmdata["map_select_name"]=select_brch_id;
	var returnStr = '';
	$.ajax({
		url : url,
		type : "POST",
		async: false,
		data : fmdata,
		success : function(result){
			var result = eval("(" + result + ")");
			if (result.if_success) {
				returnStr = result.returnString;
			}else{
				returnStr = "";
			}
		},
		error : function(result) {
			alert('error!!');
		}
	});
	return returnStr;
}

function getChildClassSelect(main_class_type,u_type,select_childclass){
	var url = "map_markersaddition_getChildClassSelect.action";
	var fmdata = {};
	fmdata["map_select_main_class_type"]=main_class_type;
	fmdata["map_select_user_type"]=u_type;
	fmdata["map_select_childclass_id"]=select_childclass;
	var returnStr = '';
	$.ajax({
		url : url,
		type : "POST",
		async: false,
		data : fmdata,
		success : function(result){
			var result = eval("(" + result + ")");
			if (result.if_success) {
				returnStr = result.returnString;
			}else{
				returnStr = result.returnString;
				//returnStr = "";
			}
		},
		error : function(result) {
			alert('error!!');
		}
	});
	return returnStr;
}
/**
 * 
 * @param aa	主类型
 * @param bb	user_type
 */
function changeChildClassSelectOptions(aa,bb){
	var url = "map_markersaddition_getChildClassSelectOptions.action";
	var fmdata = {};
	fmdata["map_select_1_mainclass"]=aa;
	fmdata["map_select_1_usertype"]=bb;
	var optionsArr = [];
	$.ajax({
		url : url,
		type : "POST",
		async: false,
		data : fmdata,
		success : function(result){
			var result = eval("(" + result + ")");
			if (result.if_success) {
				optionsArr = result.list;
			}else{
				optionsArr = null;
			}
		},
		error : function(result) {
			alert('error!!');
		}
	});
	return optionsArr;
}

var list_map_for_textarea = [];
var now_op_textarea_marker;
function addTextAreaMarkers(){//TODO
	markerListener = google.maps.event.addListenerOnce(map,'click',function(event){
		var myOptions = {
				position : event.latLng,
				draggable :false,
				icon :'/mapfiles/markers/0point_markers_tip_red.png',
				map : map,
				title:'点击打开文本框内容输入界面。'
		};
		var marker = new google.maps.Marker(myOptions);
		
		var listener_1 = google.maps.event.addListener(marker, 'click', function(event) {
			now_op_textarea_marker = this;
			$('#textarea_input1').val('');
			$('#hidden_lat_1').val(event.latLng.lat());
			$('#hidden_lng_1').val(event.latLng.lng());
			$('#hidden_zoom_1').val(map.getZoom());
			$("#textarea_input_dlg").dialog("open");
		});
		
		changeCssForBtnUp('map_markers_t6','6');
	});
}
function submit_textarea_input(){
	var url="map_markersaddition_addtextarea.action";
	var json = {};
	json["hidden_lat_1"] = $('#hidden_lat_1').val();
	json["hidden_lng_1"] = $('#hidden_lng_1').val();
	json["textarea_input1"] = $('#textarea_input1').val();
	json["hidden_zoom_1"] = $('#hidden_zoom_1').val();
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
					var marker = new MyMarker(map,
							{
								latlng:now_op_textarea_marker.getPosition(),
								clickFun:textAreaClickFunc,
								labelText:$('#textarea_input1').val()
							});
					$("#textarea_input_dlg").dialog("close");
					google.maps.event.clearListeners(now_op_textarea_marker,'click');
					now_op_textarea_marker.setTitle('已完成！');
					//console.log('lat--->'+$('#hidden_lat_1').val()+'\nlng--->'+$('#hidden_lng_1').val()+'\ntext--->'+$('#textarea_input1').val()+'\nzoom--->'+$('#hidden_zoom_1').val());
					$.messager.show({title: "成功",msg: msg.msg});
				}else{
					$.messager.show({title: "失败",msg: msg.msg});
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}
function textAreaClickFunc(){
	
}
function add_marker_circle(){
	drawCircle11();
	now_marker_type = "4";
}

/***********************************STTTTTTTTTTTTTTTTTTTTTTTTTTTTTT******************************************/
var cirListener11 = null;    // 画圈侦听
function drawCircle11(){
	if (cirListener11){
		google.maps.event.removeListener(cirListener11);
		cirListener11= null;
		changeCssForBtnUp('map_markers_t3','3');//图标重置
		return false;
	}
	cirListener11 = google.maps.event.addListener(map,'click',function(event){
		if (event){
			createCircle11(new google.maps.LatLng(event.latLng.lat(),event.latLng.lng()),300,map);
		}
	});
}

  var metric = false;  
 function createCircle11(point, radius,myMap) {
  geoQuery11 = new GeoQuery11();  
  geoQuery11.initializeCircle(radius, point, myMap);   
  geoQuery11.render();  
  
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
function GeoQuery11() {

}

GeoQuery11.prototype.CIRCLE='circle';// 画的形状
GeoQuery11.prototype.COLORS=["#FFFF00", "#00ff00", "#ff0000"];// 圆的颜色
var COLORI=0;// 默认颜色为0

GeoQuery11.prototype = new GeoQuery11();  
GeoQuery11.prototype._map;  
GeoQuery11.prototype._type;  
GeoQuery11.prototype._radius;  
GeoQuery11.prototype._dragHandle;  
GeoQuery11.prototype._centerHandle;  
GeoQuery11.prototype._polyline;  
GeoQuery11.prototype._color;  
GeoQuery11.prototype._control;  
GeoQuery11.prototype._points;  
GeoQuery11.prototype._dragHandlePosition;  
GeoQuery11.prototype._centerHandlePosition;  
 

/**
 * 
 * 圆的加载
 * 
 */  
GeoQuery11.prototype.initializeCircle = function(radius, point, map) {
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
        icon:"/mapfiles/markers/0map-marker-md.png",
        title:"半径："+distance1*1000+"\n"+"中心纬度："+this._centerHandlePosition.lat()+"\n"+"中心经度："+this._centerHandlePosition.lng()
    });   
    
 // 随机获取颜色
    this._color = "#FF0000";//this.COLORS[COLORI++ % 3]
   
 // 在地图上加载圆形
    this._dragHandle.setMap(map);  
    this._centerHandle.setMap(map);  
   
    var myObject = this; 
//    if(my_circle11)
//    {
//    	my_circle11.remove();
//    }
    
	showCircleRadius(this._dragHandle,this._radius);
	google.maps.event.addListener(this._centerHandle, 'click', function(event) {
//		myObject.showTitleForCenterPoint();
//		myObject.changeColor("#678500");
		myObject.OpenSettingWindowForClick();
	});
    
    //showDest(this);
 // 扩大圆的点的拖拽停止事件
  google.maps.event.addListener(myObject._dragHandle, 'dragend', function() {  myObject.updateCircle(1); });  
  // 扩大圆的点的拖拽停止事件
 
 // 圆的中心点的拖拽停止事件
 google.maps.event.addListener(myObject._centerHandle, 'dragend', function() { myObject.updateCircle(2);});  
 // 圆的中心点的拖拽事件
 // google.maps.event.addListener(myObject._centerHandle, 'drag', function() {myObject.updateCircle(2);});  
};
var NowOpCircleObject;
GeoQuery11.prototype.OpenSettingWindowForClick = function (){
	NowOpCircleObject = this;
	var html = "<div><font size='3'><b>圆形标注信息</b></font></div>" +
	"<div><hr></div>" +
	getChildClassSelect('4','1','map_select_childclass')+
	"<div><hr></div>" +
	"<div><input type='hidden' id='hidden_circle_color' value='#FF0000||0.35||2'/></div>"+//线的颜色||边缘线的粗细||区域的透明度
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"map_marker_circle_color\" value=\"     更换颜色     \" onclick=\"changeCircleMarkerColor();\"/>"+
	"<input type=\"button\" id=\"map_marker_circle_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent4();\"/>"+//map_marker_point_add
	"<input type=\"button\" id=\"map_marker_circle_delete\" value=\"     删除     \" onclick=\"NowOpCircleObject.remove();\"/>"+
	"</div>";
	var messageInfoWindowForCircle = new google.maps.InfoWindow({
		content : html,
		size : new google.maps.Size(120,120)
	});
	clearAllInfoWindow();
	messageInfoWindowForCircle.open(this._map,this._centerHandle);
	infowindowArray_for_markers.push(messageInfoWindowForCircle);
};
var s_circle_brch_value='';
var s_circle_childclass_value='';
function changeCircleMarkerColor(){
	s_circle_brch_value = $("#map_select_brch").val();
	s_circle_childclass_value = $("#map_select_childclass").val();
	var htmlchangecolor = "<div><font size='3'><b>圆形标注颜色选择</b></font></div>" +
	"<div><hr></div>" +
	"<div>颜色:<input type=\"text\" id=\"select_my_circle_color\" name=\"select_my_circle_color\" value=\"#FF0000\" /></div>" +
	"<div><div id=\"circlecolorpicker\"></div></div>" +
	"<div>圆形区域透明度<input id=\"circle_strokeOpacity\" name=\"circle_strokeOpacity\" value=\"1.0\" /><font color=\"red\">0.0-1.0(1.0表示不透明)</font></div>"+
	"<div>圆形边线粗细度<input id=\"circle_strokeWeight\" name=\"circle_strokeWeight\" value=\"2\" /><font color=\"red\">最小为2(越大越粗)</font></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"circle_color_confirm\" value=\"确认\" onclick=\"circle_color_confirm();\"/>"+
	"</div>";
	infowindowArray_for_markers[0].setContent(htmlchangecolor);
	$('#circlecolorpicker').farbtastic('#select_my_circle_color');
}
function circle_color_confirm(){
	var c_strokeOpacity = $("#circle_strokeOpacity").val();
	var c_strokeWeight = $("#circle_strokeWeight").val();
	if(isNaN(c_strokeOpacity)){
		c_strokeOpacity=1.0;
	}else{
		if(c_strokeOpacity>1.0){
			c_strokeOpacity=1.0;
		}
	}
	if(isNaN(c_strokeWeight)){
		c_strokeWeight=2;
	}else{
		if(c_strokeWeight<2){
			c_strokeWeight=2;
		}
	}
	NowOpCircleObject._polyline.setOptions({strokeColor: $("#select_my_circle_color").val(),fillOpacity: c_strokeOpacity,strokeWeight: c_strokeWeight,fillColor: $("#select_my_circle_color").val()});
	NowOpCircleObject._color = $("#select_my_circle_color").val();
	
	var h_circle_color = $("#select_my_circle_color").val()+"||"+c_strokeOpacity+"||"+c_strokeWeight;
	
	//console.log('select_my_circle_color---->'+$("#select_my_circle_color").val());
	
	var htmlSt = "<div><font size='3'><b>圆形标注信息</b></font></div>" +
	"<div><hr></div>" +
	getChildClassSelect('4',s_circle_brch_value,'map_select_childclass')+
	"<div><hr></div>" +
	"<div><input type='hidden' id='hidden_circle_color' value='"+h_circle_color+"'/></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"map_marker_circle_color\" value=\"     更换颜色     \" onclick=\"changeCircleMarkerColor();\"/>"+
	"<input type=\"button\" id=\"map_marker_circle_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent4();\"/>"+//map_marker_point_add
	"<input type=\"button\" id=\"map_marker_circle_delete\" value=\"     删除     \" onclick=\"NowOpCircleObject.remove();\"/>"+
	"</div>";

	infowindowArray_for_markers[0].setContent(htmlSt);
	$("#map_select_brch option[value='"+s_circle_brch_value+"']").attr("selected",true);
	$("#map_select_childclass option[value='"+s_circle_childclass_value+"']").attr("selected",true);
}

function giveHiddenDivContent4(){
	var big_type='4';
	var dept_type=$("#map_select_brch").val();
	var child_class=$("#map_select_childclass").val();
	
	if(child_class=='0'){
		alert('没有对应的查询结果！');
		return;
	}
	var url="map_markersaddition_giveHiddenDivContent4.action";
	var json = {};
	json["html_addcircle_select1"]=big_type;
	json["html_addcircle_select2"]=dept_type;
	json["html_addcircle_select3"]=child_class;
	
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
					$("#hidden_table_for_point_zishiying tr").empty();
					$("#hidden_table_for_line_zishiying tr").empty();
					$("#hidden_table_for_area_zishiying tr").empty();
					$("#hidden_table_for_circle_zishiying tr").empty();
					$("#selecticon_num_44").val($("#hidden_circle_color").val());
					$("#selectclass_tablename4").val(msg.returnString);
					for(var i=0;i<msg.list_map.length;i++){
						var newRow = "";
						if(msg.list_map[i].class_columntype=='4'){
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}else{
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}
//						//console.log('newRow:'+newRow);
						$("#hidden_table_for_circle_zishiying tr:last").after(newRow);
					}
					$("#circle_zishiying_dlg").dialog("open");
				}else{
					$("#hidden_table_for_circle_zishiying tr").empty();
					alert('没有对应的查询属性！');
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function addCircleMarkerS(){
	var circleColor=$("#selecticon_num_44").val();
	var id_array=[];
	var value_array=[];
	var type_array=[];
	var nullable_array=[];
	var size_array=[];
	var lat_for_circle='';
	var lng_for_circle='';
	var radius = NowOpCircleObject._radius;
	lat_for_circle = NowOpCircleObject._centerHandlePosition.lat();
	lng_for_circle = NowOpCircleObject._centerHandlePosition.lng();
	
	$("input.hidden_table_input").each(function(){
	    value_array.push($(this).val());
	    id_array.push($(this).attr("id"));
	});
	var i_null=0;
	for(var i=0;i<value_array.length;i++){
		if(value_array[i]==''||value_array[i]==undefined||value_array[i]==null){
			i_null++;
		}else{
			continue;
		}
	}
	if(i_null==value_array.length){
		alert('事实上，我们不允许所有字段属性都为空！');
		return;
	}
	$("input.hidden_table_input_type").each(function(){
		type_array.push($(this).val());
	});
	$("input.hidden_table_input_nullable").each(function(){
		nullable_array.push($(this).val());
	});
	$("input.hidden_table_input_size").each(function(){
		size_array.push($(this).val());
	});
	
	var if_success1=true;
	var if_success2=true;
	for(var i=0;i<id_array.length;i++){
		//console.log('ID:'+id_array[i]+'\n 值：'+value_array[i]+'\n type:'+type_array[i]+'\n nullable:'+nullable_array[i]+'\n size:'+size_array[i]);
		if(type_array[i]!='4'){
			if(value_array[i].indexOf('.jpg')>-1||value_array[i].indexOf('.JPG')>-1
					||value_array[i].indexOf('.png')>-1||value_array[i].indexOf('.PNG')>-1
					||value_array[i].indexOf('.bmp')>-1||value_array[i].indexOf('.BMP')>-1
					||value_array[i].indexOf('.gif')>-1||value_array[i].indexOf('.GIF')>-1
					||value_array[i].indexOf('.jpeg')>-1||value_array[i].indexOf('.JPEG')>-1){
				if_success2 = false;
				break;
			}
		}
		if(type_array[i]=='2'||type_array[i]=='3'){
			if(!isNaN(value_array[i])){
				
			}else{
				if_success1=false;
				break;
			}
		}
		if(nullable_array[i]=='2'){
			if(value_array[i]==undefined||value_array[i]==''){
				if_success1=false;
				break;
			}
		}
		if(size_array[i]!='NULL'){
			if(value_array[i].length>=Number(size_array[i])){
				if_success1=false;
				break;
			}
		}
	}
	
	circleColor = circleColor.replace('#','@');
	
	if(if_success1&&if_success2){
		var value_str = JSON.stringify(value_array);
		var id_str = JSON.stringify(id_array);
		fmSubmit_6("#circle_zishiying_frm","map_markersaddition_addCircleMarkerS.action?html_id_str="
				+id_str+"&html_value_str="
				+value_str+"&html_table_name="
				+$("#selectclass_tablename4").val()+"&html_icon_num="
				+circleColor+"&html_location_lat="
				+lat_for_circle+"&html_location_lon="
				+lng_for_circle+"&html_circle_radius="+radius,function(result){
			
		});
	}else if(!if_success1){
		alert('没有通过验证，请注意数值类型，内容是否可以为空，以及内容长度！');
	}else if(!if_success2){
		alert('请不要在不是图片的属性字段中包含.jpg .bmp .jpeg .png .gif字样！');
	}
}
function fmSubmit_6(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
				$("#circle_zishiying_dlg").dialog("close");
				infowindowArray_for_markers[0].close();
				NowOpCircleObject._centerHandle.setTitle(result.returnString);
				google.maps.event.clearListeners(NowOpCircleObject._centerHandle,'click');
				google.maps.event.clearListeners(NowOpCircleObject._centerHandle,'dragend');
				google.maps.event.clearListeners(NowOpCircleObject._dragHandle,'dragend');
				NowOpCircleObject._centerHandle.setOptions({draggable:false});
				NowOpCircleObject._dragHandle.setOptions({draggable:false});
			}else{
				//用户"点"标记记录失败
				alert(result.msg);
				return;
			}
		},
		error: function(){
			alert('error!!');
		}
	});
}
GeoQuery11.prototype.changeColor = function(change_color) {  
	if (this._type == this.CIRCLE) {  
		this._polyline.setOptions({strokeColor: change_color,strokeOpacity: 0.8,strokeWeight: 2,fillColor: change_color});
		this._color = change_color;
	}  
};
	
GeoQuery11.prototype.showTitleForCenterPoint = function (){
	alert(this._centerHandle.getTitle());
};

/**
 * 
 * 寻找圆内的所有已经标注点
 * 
 */  

var dragHandleArray=[];

GeoQuery11.prototype.updateCircle = function (type) {
	this._polyline.setMap(null);  
    if (type==1) {  
    	this._dragHandlePosition = this._dragHandle.getPosition();  
    	this._radius = distance(this._centerHandlePosition, this._dragHandlePosition) * 1000;  
    	this.render(); 
    	// 重新计算圈内目标
    	showCircleRadius(this._dragHandle,this._radius);
    	//showDest(this);
    	var sstt = "半径："+this._radius+"\n"+"中心纬度："+this._centerHandlePosition.lat()+"\n"+"中心经度："+this._centerHandlePosition.lng();
    	this._centerHandle.setTitle(sstt);
	}
    else{  
    	this._centerHandlePosition = this._centerHandle.getPosition();
    	this.render();  
    	this._dragHandle.setPosition(this.getEast());  
    	showCircleRadius(this._dragHandle,this._radius);
    	//showDest(this);
    	var sstt = "半径："+this._radius+"\n"+"中心纬度："+this._centerHandlePosition.lat()+"\n"+"中心经度："+this._centerHandlePosition.lng();
    	this._centerHandle.setTitle(sstt);
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
GeoQuery11.prototype.render = function(){
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
      strokeColor: this._color,
      strokeOpacity: 0.8,  
      strokeWeight: 2,  
      fillColor: this._color,  
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
GeoQuery11.prototype.remove = function() {  
 this._polyline.setMap(null);  
 this._dragHandle.setMap(null);  
 this._centerHandle.setMap(null);  

};
  
GeoQuery11.prototype.getRadius = function() {  
    return this._radius;  
};
  
GeoQuery11.prototype.getHTML = function() {  
  return "<span><font color='"+ this._color + "''>" + this.getDistHtml() + "</font></span>";  
};
  
GeoQuery11.prototype.getDistHtml = function() {  
  result = "<img src='/mapfiles/iw_close.gif' onClick='myQueryControl.remove(" + this._control.getIndex(this) + ");'/>Radius ";  
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
  
GeoQuery11.prototype.getNorth = function() {  
  return this._points[0];  
};
  
GeoQuery11.prototype.getSouth = function() {  
  return this._points[(72/2)];  
};
  
GeoQuery11.prototype.getEast = function() {  
   
 var dis = distance(this._points[0],this._points[1])/2;  
 var newPoint = destination(this._points[0],180,dis);  
 return newPoint;   
  // return this._points[(72/4)];
};
  
GeoQuery11.prototype.getWest = function() {  
  return this._points[(72/4*3)];  
};

/** ************************显示圆圈内目标************************************************* */


/**************************************文本框的标注ST****************************************/
var now_op_textarea_marker = null;
var if_click_textarea = false;
var textarea_border_color;
var textarea_border_weight;
var textarea_inner_color;
var list_map_for_textarea_marker = [];
function addMarkerTextArea(){
	if(if_click_textarea){
		if_click_textarea = false;
		google.maps.event.removeListener(markerListener);
		changeCssForBtnUp('map_markers_t6','6');//图标重置
	}else{
		if(list_map_for_textarea_marker.length>0){
			list_map_for_textarea_marker.length=0;
		}
		now_marker_type = "5";
		textarea_inner_color = "#FFFF00";
		textarea_border_color = "#FF0000";
		textarea_border_weight = "2";
		markerListener = google.maps.event.addListener(map,'click',function(event){//改变监听方式//addListenerOnce
			clearAllInfoWindow();
			var myOptions = {
					position : event.latLng,
					draggable :false,
					icon :'/mapfiles/markers/0point_markers_tip_red.png',
					map : map,
					title:'未开始进行文本框添加操作！\n'+'lat:'+event.latLng.lat()+' \nlng:'+event.latLng.lng()
			};
			var marker = new google.maps.Marker(myOptions);
			var html = "<div><font size='3'><b>准备添加文本框</b></font></div>" +
					"<div><hr></div>" +
					getChildClassSelect('5','1','map_select_childclass')+
					"<div><hr></div>" +
//					"<div><input type='hidden' id='marker_point_whichicon' value='/mapfiles/markers/0green-ball.png'/></div>"+
					"<div><input type='hidden' id='hidden_textarea_color' value='#FF0000||#FFFF00||2'/></div>"+
					"<div style='float:right'>" +
//					"<input type=\"button\" id=\"map_marker_textarea_color\" value=\"     更换颜色     \" onclick=\"changeTextAreaMarkerColor();\"/>"+//changeLineMarkerColor();
					"<input type=\"button\" id=\"map_marker_textarea_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent5();\"/>"+//giveHiddenDivContent2();
					"<input type=\"button\" id=\"map_marker_textarea_delete\" value=\"     删除     \" onclick=\"deleteMarkerTextArea();\"/>"+//deleteMarkerLine();
					"</div>";
			
			
			message = new google.maps.InfoWindow({
				content : html,
				size : new google.maps.Size(120,120)
			});
			google.maps.event.addListener(marker, 'click', function(event) {
				clearAllInfoWindow();
				message.open(map,this);
				message.setZIndex(200);
				now_op_textarea_marker = this;
				infowindowArray_for_markers.push(message);
			});
			var textarea_marker = new MyMarker(map,
					{
						latlng:marker.getPosition(),
						clickFun:textAreaClickFunc,
						labelText:'未完成'
					});
			var map1 = {};
			map1["point_marker"]=marker;
			map1["textarea_marker"]=textarea_marker;
			map1["zoom"]=map.getZoom();
			map1["border_weight"]=textarea_border_weight;
			map1["border_color"]=textarea_border_color;
			map1["inner_color"]=textarea_inner_color;
			//console.log('zoom:'+map.getZoom());
			list_map_for_textarea_marker.push(map1);
		});
		if_click_textarea = true;
		
	}
}
var s_textarea_brch_value = '';
var s_textarea_childclass_value = '';
function changeTextAreaMarkerColor(){
	s_textarea_brch_value = $("#map_select_brch").val();
	s_textarea_childclass_value = $("#map_select_childclass").val();
	var htmlchangecolor = "<div><font size='3'><b>文本框颜色设置</b></font></div>" +
	"<div><hr></div>" +
	"<div>边线颜色:<input type=\"text\" id=\"select_my_textarea_color\" name=\"select_my_textarea_color\" value=\"#FF0000\" /></div>" +
	"<div><div id=\"textareacolorpicker\"></div></div>" +
	"<div>区域颜色:<input type=\"text\" id=\"select_my_textarea_color2\" name=\"select_my_textarea_color2\" value=\"#FFFF00\" /></div>" +
	"<div><div id=\"textareacolorpicker2\"></div></div>" +
	"<div>边线粗细度<input id=\"textarea_strokeWeight\" name=\"textarea_strokeWeight\" value=\"2\" /><font color=\"red\">最小为2(越大越粗)</font></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"textarea_color_confirm\" value=\"确认\" onclick=\"textarea_color_confirm();\"/>"+
	"</div>";
	infowindowArray_for_markers[0].setContent(htmlchangecolor);
	$('#textareacolorpicker').farbtastic('#select_my_textarea_color');
	$('#textareacolorpicker2').farbtastic('#select_my_textarea_color2');
}

function textarea_color_confirm(){
	var textarea_strokeWeight = $("#line_strokeWeight").val();
	if(isNaN(textarea_strokeWeight)){
		textarea_strokeWeight=2;
	}else{
		if(textarea_strokeWeight<2){
			textarea_strokeWeight=2;
		}
	}
	//$("#select_my_textarea_color").val()
	//$("#select_my_textarea_color2").val()
	//textarea_strokeWeight
	$.each(list_map_for_textarea_marker,function(n,value){
        if(now_op_textarea_marker===value.point_marker){
        	value.textarea_marker.hide();
        	var textarea_marker = new MyMarker(map,
					{
						latlng:now_op_textarea_marker.getPosition(),
						clickFun:textAreaClickFunc,
						labelText:'未完成',
						border_weight:textarea_strokeWeight,
						border_color:$("#select_my_textarea_color").val(),
						inner_color:$("#select_my_textarea_color2").val()
					});
        	value.textarea_marker = textarea_marker;
        	value.border_weight = textarea_strokeWeight;
        	value.border_color = $("#select_my_textarea_color").val();
        	value.inner_color = $("#select_my_textarea_color2").val();
        	
        }
    });
	var h_textarea_color = $("#select_my_textarea_color").val()+"||"+$("#select_my_textarea_color2").val()+"||"+textarea_strokeWeight;
	
	//console.log('h_textarea_color---->'+h_textarea_color);
	
	var htmlSt = "<div><font size='3'><b>准备添加文本框</b></font></div>" +
	"<div><hr></div>" +
	getChildClassSelect('5',s_textarea_brch_value,'map_select_childclass')+
	"<div><hr></div>" +
	"<div><input type='hidden' id='hidden_textarea_color' value='"+h_textarea_color+"'/></div>"+
	"<div style='float:right'>" +
	"<input type=\"button\" id=\"map_marker_textarea_color\" value=\"     更换颜色     \" onclick=\"changeTextAreaMarkerColor();\"/>"+
	"<input type=\"button\" id=\"map_marker_textarea_add\" value=\"     添加     \" onclick=\"giveHiddenDivContent5();\"/>"+//map_marker_point_add
	"<input type=\"button\" id=\"map_marker_textarea_delete\" value=\"     删除     \" onclick=\"deleteMarkerTextArea();\"/>"+
	"</div>";

	infowindowArray_for_markers[0].setContent(htmlSt);
	$("#map_select_brch option[value='"+s_textarea_brch_value+"']").attr("selected",true);
	$("#map_select_childclass option[value='"+s_textarea_childclass_value+"']").attr("selected",true);
}

function giveHiddenDivContent5(){
	var big_type='5';
	var dept_type=$("#map_select_brch").val();
	var child_class=$("#map_select_childclass").val();
	$("#textarea_textcontent").val("");
	if(child_class=='0'){
		alert('没有对应的查询结果！');
		return;
	}
	var url="map_markersaddition_giveHiddenDivContent5.action";
	var json = {};
	json["html_addtextarea_select1"]=big_type;
	json["html_addtextarea_select2"]=dept_type;
	json["html_addtextarea_select3"]=child_class;
	
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
					$("#hidden_table_for_point_zishiying tr").empty();
					$("#hidden_table_for_line_zishiying tr").empty();
					$("#hidden_table_for_area_zishiying tr").empty();
					$("#hidden_table_for_circle_zishiying tr").empty();
					$("#hidden_table_for_textarea_zishiying tr").empty();
					$("#selecticon_num_55").val($("#hidden_textarea_color").val());
					$("#selectclass_tablename5").val(msg.returnString);
					$("#hh_div1").text(map.getZoom());
					for(var i=0;i<msg.list_map.length;i++){
						var newRow = "";
						if(msg.list_map[i].class_columntype=='4'){
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"file\" name=\"upload\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"图片信息\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}else{
							if(msg.list_map[i].class_nullable=='1'){
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">可以空</font></td></tr>";
							}else{
								newRow = "<tr><td align=\"center\">"+msg.list_map[i].class_comments
								+"：</td><td align=\"center\"><input type=\"text\" id=\""
								+msg.list_map[i].columnname
								+"\" class=\"hidden_table_input\" value=\"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_type\" value=\""+msg.list_map[i].class_columntype+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_nullable\" value=\""+msg.list_map[i].class_nullable+"\" />" +
								"<input type=\"hidden\" class=\"hidden_table_input_size\" value=\""+msg.list_map[i].class_columnsize+"\" />" +
								"</td><td><font color=\"red\">不可空</font></td></tr>";
							}
						}
//						//console.log('newRow:'+newRow);
						$("#hidden_table_for_textarea_zishiying tr:last").after(newRow);
					}
					$("#textarea_zishiying_dlg").dialog("open");
				}else{
					$("#hidden_table_for_textarea_zishiying tr").empty();
					alert('没有对应的查询属性！');
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function deleteMarkerTextArea(){
	now_op_textarea_marker.setMap(null);
	$.each(list_map_for_textarea_marker,function(n,value){
        if(now_op_textarea_marker===value.point_marker){
        	value.textarea_marker.hide();
        }
    });
}

function fmSubmit_5(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
				$("#textarea_zishiying_dlg").dialog("close");
				infowindowArray_for_markers[0].close();
				now_op_textarea_marker.setTitle(result.returnString);
				google.maps.event.clearListeners(now_op_textarea_marker,'click');
				now_op_textarea_marker.setMap(null);
				$.each(list_map_for_textarea_marker,function(n,value){
			        if(now_op_textarea_marker===value.point_marker){
			        	value.textarea_marker.hide();
			        	var s_aa = $("#selecticon_num_55").val().split("||");
			        	var textarea_marker = new MyMarker(map,
								{
									latlng:now_op_textarea_marker.getPosition(),
									clickFun:textAreaClickFunc,
									labelText:$("#textarea_textcontent").val(),
									border_weight:s_aa[2],
									border_color:s_aa[0],
									inner_color:s_aa[1]
								});
			        	value.textarea_marker = textarea_marker;
			        }
			    });
				
			}else{
				//用户"点"标记记录失败
				alert(result.msg);
				return;
			}
		},
		error: function(){
			alert('error!!');
		}
	});
}


function addTextAreaMarkerS(){
	var textareaColor=$("#selecticon_num_55").val();
	var textarea_zoom = $("#hh_div1").text();
	var id_array=[];
	var value_array=[];
	var type_array=[];
	var nullable_array=[];
	var size_array=[];
	var lat_for_textarea='';
	var lng_for_textarea='';
	lat_for_textarea = now_op_textarea_marker.getPosition().lat();
    lng_for_textarea = now_op_textarea_marker.getPosition().lng();
	var textarea_textcontent = $("#textarea_textcontent").val();
	if(textarea_textcontent==''||textarea_textcontent==null){
		alert("文本框的文本内容不允许为空！");
		return;
	}
	$("input.hidden_table_input").each(function(){
	    value_array.push($(this).val());
	    id_array.push($(this).attr("id"));
	});
	var i_null=0;
	for(var i=0;i<value_array.length;i++){
		if(value_array[i]==''||value_array[i]==undefined||value_array[i]==null){
			i_null++;
		}else{
			continue;
		}
	}
	if(i_null==value_array.length){
		alert('事实上，我们不允许所有字段属性都为空！');
		return;
	}
	$("input.hidden_table_input_type").each(function(){
		type_array.push($(this).val());
	});
	$("input.hidden_table_input_nullable").each(function(){
		nullable_array.push($(this).val());
	});
	$("input.hidden_table_input_size").each(function(){
		size_array.push($(this).val());
	});
	
	var if_success1=true;
	var if_success2=true;
	for(var i=0;i<id_array.length;i++){
		//console.log('ID:'+id_array[i]+'\n 值：'+value_array[i]+'\n type:'+type_array[i]+'\n nullable:'+nullable_array[i]+'\n size:'+size_array[i]);
		if(type_array[i]!='4'){
			if(value_array[i].indexOf('.jpg')>-1||value_array[i].indexOf('.JPG')>-1
					||value_array[i].indexOf('.png')>-1||value_array[i].indexOf('.PNG')>-1
					||value_array[i].indexOf('.bmp')>-1||value_array[i].indexOf('.BMP')>-1
					||value_array[i].indexOf('.gif')>-1||value_array[i].indexOf('.GIF')>-1
					||value_array[i].indexOf('.jpeg')>-1||value_array[i].indexOf('.JPEG')>-1){
				if_success2 = false;
				break;
			}
		}
		if(type_array[i]=='2'||type_array[i]=='3'){
			if(!isNaN(value_array[i])){
				
			}else{
				if_success1=false;
				break;
			}
		}
		if(nullable_array[i]=='2'){
			if(value_array[i]==undefined||value_array[i]==''){
				if_success1=false;
				break;
			}
		}
		if(size_array[i]!='NULL'){
			if(value_array[i].length>=Number(size_array[i])){
				if_success1=false;
				break;
			}
		}
	}
	
	textareaColor = textareaColor.replaceAll('#','@');
	
	if(if_success1&&if_success2){
		var value_str = JSON.stringify(value_array);
		var id_str = JSON.stringify(id_array);
		fmSubmit_5("#textarea_zishiying_frm","map_markersaddition_addTextAreaMarkerS.action?html_id_str="
				+id_str+"&html_value_str="
				+value_str+"&html_table_name="
				+$("#selectclass_tablename5").val()+"&html_icon_num="
				+textareaColor+"&html_location_lat="
				+lat_for_textarea+"&html_location_lon="
				+lng_for_textarea+"&html_textarea_zoom="+textarea_zoom+"&html_textarea_textcontent="+textarea_textcontent,function(result){
			
		});
	}else if(!if_success1){
		alert('没有通过验证，请注意数值类型，内容是否可以为空，以及内容长度！');
	}else if(!if_success2){
		alert('请不要在不是图片的属性字段中包含.jpg .bmp .jpeg .png .gif字样！');
	}
}
String.prototype.replaceAll  = function(s1,s2){     
    return this.replace(new RegExp(s1,"gm"),s2);     
};
/**************************************文本框的标注ED****************************************/



/**************************************************************************************************/

/*

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
    label.innerHTML = '<table style="border-style: solid;border-width: '+this._border_weight+'px;border-color: '+this._border_color+';word-break:break-all;word-wrap:break-word;" bgcolor="'+this._inner_color+'"><tr><td>'+this.labelText+'</td></tr></table>';
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

*/
