/*
 * 
 * 
 * 地图查询
 * 2014-07-02
 * 
 * 
 */

var queryPointArray=[];//地图查询  点数组
var queryLineArray=[];//地图查询  线数组
var queryAreaArray=[];//地图查询  面数组
var queryCircleArray=[];//地图查询  园数组

var query_zoom_changed_Listener = null; 
var query_dragend_Listener = null; 

function map_query(node)
{

 
	
    if (query_zoom_changed_Listener) {
		   google.maps.event.removeListener(query_zoom_changed_Listener);
		   query_zoom_changed_Listener= null;
		 
	}
    if (query_dragend_Listener) {
		   google.maps.event.removeListener(query_dragend_Listener);
		   query_dragend_Listener= null;
		  
	}
	 
	
	if(my_circle)
	{
	   my_circle.remove();
	}
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
	if(cnode=="")
	{
		clearPoint(queryPointArray);//清除点
		clearPoint(queryLineArray);//清除线
		clearPoint(queryAreaArray);//清除面
		clearCircle(queryCircleArray);//清除圆
		return;
	
	}
	else if(cnode!="")
	{
	var json = {};
	json["SN"]=cnode;

	
	var url="/map_query_data.action";

	doquery(url,json);
	query_dragend_Listener=google.maps.event.addListener(map,'dragend',function(event){
		 
		doquery(url,json);
	});
	query_zoom_changed_Listener=google.maps.event.addListener(map,'zoom_changed',function(event){
		 
		doquery(url,json);
	});
	
	}
	
	
	if(x==1)
	{
		var c=cnode.split(",");
	 
	 	jQuery("#sdfasdf").show();
 
		jQuery("#asdfasdf").hide();
		jQuery("#toquery").attr("onclick","toQuery('"+c[1]+"');");
		document.getElementById("queryString3Show").innerHTML="条件选择：";
		document.getElementById("queryString3").value="";
		document.getElementById("queryString").value="";
		
	}
	else
	{
		jQuery("#sdfasdf").hide();
		jQuery("#asdfasdf").show();
		jQuery("#toquery").attr("onclick","");
		document.getElementById("queryString3Show").innerHTML="条件选择：";
		document.getElementById("queryString3").value="";
		document.getElementById("queryString").value="";
	}
}
function doshow(listMap)
{
	clearPoint(queryPointArray);//清除点
	clearPoint(queryLineArray);//清除线
	clearPoint(queryAreaArray);//清除面
	clearCircle(queryCircleArray);//清除圆
	
	var k=0;

	var bounds = map.getBounds();
	for(var i=0;i<listMap.length;i++ ){
	var pathpoint=listMap[i];
	if(pathpoint.marker_type=="1")
	{
	

			var markerpoint = new google.maps.LatLng(pathpoint.lat,pathpoint.lng);
			if(bounds.contains(markerpoint))
			{
				var marker = new google.maps.Marker({
						position: markerpoint,
						map: map,
						draggable:false,//禁止拖拽
						icon:(pathpoint.mc_markers&&pathpoint.mc_markers!=''&&pathpoint.mc_markers!='null'&&pathpoint.mc_markers!='undefined')?(pathpoint.mc_markers):("/mapfiles/markers/0map-marker-md.png")//这里设置点的默认图标
				}); 
			

			  queryPointArray.push(marker);
			 	 
			  showinfomessage(marker,k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,"2");
			
			  k=k+1;
			}
			
		}
		if(pathpoint.marker_type=="2")
		{
			
				//线组装
				var line_lat_Array = [];
				var line_lng_Array = [];
				line_lat_Array=pathpoint.lat.split('#');
				line_lng_Array=pathpoint.lng.split('#');
				line_lat_Array.pop();
				line_lng_Array.pop();
				var line_Array = [];
				var show_flag=false;
				for(var j=0;j<line_lat_Array.length;j++){
					var tempLatLng=new google.maps.LatLng(line_lat_Array[j], line_lng_Array[j]);
					line_Array.push(tempLatLng);
					if(bounds.contains(tempLatLng))
					{
						show_flag=true;
					}
				}
				if(!show_flag)
				{
					continue;
				}
				var mc_markers=pathpoint.mc_markers
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
				var stIconurl = "";
				var tjIconurl = "";
				var edIconurl = "";
				if('T_MAP_CLASS_MC467'==pathpoint.table_name){
					stIconurl = "/mapfiles/markers2/ergan_st.png";
					tjIconurl = "/mapfiles/markers2/ergan_point.png";
					edIconurl = "/mapfiles/markers2/ergan_ed.png";
				}else if('T_MAP_CLASS_MC468'==pathpoint.table_name){
					stIconurl = "/mapfiles/markers2/shitang_st.png";
					tjIconurl = "/mapfiles/markers2/town_point.png";
					edIconurl = "/mapfiles/markers2/shitang_ed.png";
				}else if('T_MAP_CLASS_MC469'==pathpoint.table_name){
					stIconurl = "/mapfiles/markers2/shixian_st.png";
					tjIconurl = "/mapfiles/markers2/city_point.png";
					edIconurl = "/mapfiles/markers2/shixian_ed.png";
				}else{
					stIconurl = "/mapfiles/markers/spotmkrs_2b70b09.png";
					tjIconurl = "/mapfiles/markers/spotmkrs_2b70b09.png";
					edIconurl = "/mapfiles/markers/spotmkrs_2b70b09.png";
				}
				var stMarker = new google.maps.Marker({
					position: stPointLatLng,
					map: map,
					draggable:false,//禁止拖拽
					icon:stIconurl
				});
				
				if(line_lat_Array.length>2){
					for(var vv=1;vv<(line_lat_Array.length-1);vv++){
						var vvMarker = new google.maps.Marker({
							position: new google.maps.LatLng(line_lat_Array[vv],line_lng_Array[vv]),
							map: map,
							draggable:false,//禁止拖拽
							icon:tjIconurl
						});
						queryPointArray.push(vvMarker);
						k=k+1;
					}
				}
				
				queryPointArray.push(stMarker);
				showinfomessage(my_line,k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,"2");
				k=k+1;
				//终点组装
				var edPointLatLng = new google.maps.LatLng(line_lat_Array[line_lat_Array.length-1],line_lng_Array[line_lng_Array.length-1]);
				var edMarker = new google.maps.Marker({
					position: edPointLatLng,
					map: map,
					draggable:false,//禁止拖拽
					icon:edIconurl
				});
				queryPointArray.push(edMarker);
			    k=k+1;
			
		}
		if(pathpoint.marker_type=="3")
		{
				//面组装
				var area_lat_Array = [];
				var area_lng_Array = [];
				area_lat_Array=pathpoint.lat.split('#');
				area_lng_Array=pathpoint.lng.split('#');
				area_lat_Array.pop();//去除最后一个空字符
				area_lng_Array.pop();//去除最后一个空字符
				var area_Array = [];
				var show_flag=false;
				for(var j=0;j<area_lat_Array.length;j++){
					var tempLatLng=new google.maps.LatLng(area_lat_Array[j], area_lng_Array[j]); 
					area_Array.push(tempLatLng);
					if(bounds.contains(tempLatLng))
					{
						show_flag=true;
					}
				}
				if(!show_flag)
				{
					continue;
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
		if(pathpoint.marker_type=="4")//圆
		{
		
				var markerpoint = new google.maps.LatLng(pathpoint.lat,pathpoint.lng); 
			    geoQuery = new GeoQuery();  
			    geoQuery.initializeCircleRead(pathpoint.radius, markerpoint, map);  
				var temps=(pathpoint.mc_markers).split("||");
				  
			
			    geoQuery.renderRead(k,pathpoint.marker_sn,pathpoint.table_name,pathpoint.show_text,temps[0],temps[2],temps[1]);  
			    queryCircleArray.push(geoQuery);
				k=k+1;
			
		}
		if(pathpoint.marker_type=="5")//文本框
		{
		
	
				var markerpoint = new google.maps.LatLng(pathpoint.lat,pathpoint.lng);
				if(bounds.contains(markerpoint))
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
 
 
function map_query_1()
{
    if (query_zoom_changed_Listener) {
		   google.maps.event.removeListener(query_zoom_changed_Listener);
		   query_zoom_changed_Listener= null;
		 
	}
    if (query_dragend_Listener) {
		   google.maps.event.removeListener(query_dragend_Listener);
		   query_dragend_Listener= null;
	 
	}
	if(my_circle)
	{
	   my_circle.remove();
	}
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
	json["query_string"]=jQuery("#queryString").val();
	json["query_property"]=jQuery("#queryString3").val().replace(new RegExp("'","gm"),"@@@@@@");
	var url="/map_query_data.action";
	doquery(url,json);
	query_dragend_Listener=google.maps.event.addListener(map,'dragend',function(event){
		 
		doquery(url,json);
	});
	query_zoom_changed_Listener=google.maps.event.addListener(map,'zoom_changed',function(event){
		 
		doquery(url,json);
	});
	
}

function doquery(url,json)
{
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
				console.log(listMap);
				doshow(listMap); 
				}else{
					alert(msg.msg);
				}
			} 
			catch(exception)
			{
				
			}
			 
		} 
	});  
}
function showinfomessage(marker,j,marker_sn,table_name,show_text,flag)//显示infowindow,后台查询具体数据
{
	var temparray=[];
	temparray=queryPointArray;//地图上每个标记出的点都可以点出infowindow
	
	temparray[j].infowindow= new google.maps.InfoWindow();
	if(flag=="1")
	{
		temparray[j].infowindow.setContent(show_text);
		temparray[j].infowindow.open(map,temparray[j]);//显示点击的点所对应的infowindow
	}
	google.maps.event.addListener(marker, 'click', function(event) {
		
		var json = {};
		var url="/map_query_queryOneMarkerInfo.action";
		json["SN"]=marker_sn;
		json["html_table_name"]=table_name;

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
						var cols=msg.colList;
						var dataMap=msg.dataMap;
						var contentString = "<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"4\" cellspacing=\"1\"  style=\"background-color: #d3d3d3; font-size: 12px; margin-top: 10px;\">";
					    contentString+="<th colspan=\"6\"><span style=\"width:40%\"> <strong ><span><font style=\" font-size: 12px;font-weight: bold;\">详细信息</font></span></strong></span></th>";
						var copy_data="";
						var upd_data="";
					    var columns="";
					 
						var contentString1="";
						var ss=0;
						for(var kk=0;kk<cols.length;kk++)//拼装infowindow显示值
						{
							  if(cols[kk].column_name!="MC_MARKERS"&&cols[kk].column_name!="SN"&&cols[kk].column_name!="STATUS_FLAG"&&cols[kk].column_name!="LAT"&&cols[kk].column_name!="LNG"&&cols[kk].column_name!="POST_FLAG")
							  {
								  if(ss==0)
								  {
									  contentString1='<tr> '+eval("dataMap."+(cols[kk].column_name).toLowerCase())+'</tr>';
								  }
								  if(ss<5)
								  {
								  if(cols[kk].data_type=="4")
								  {
									  contentString+='<tr style=" background-color: #ffffff;"><td  align="right" style="word-wrap:break-word:" width="165"><font style=" font-size: 12px;font-weight: bold;">'+cols[kk].comments+'</font></td><td align="left" style="word-wrap:break-word:" width="250"><img width=\"200\" height=\"150\"   src=\"'+eval("dataMap."+(cols[kk].column_name).toLowerCase())+'\"></td></tr>';
										  
								  }
								  else
								  {
									  contentString+='<tr  style=" background-color: #ffffff;"><td align="right" style="word-wrap:break-word:" width="165"><font style=" font-size: 12px;font-weight: bold;">'+cols[kk].comments+'</font></td><td align="left" style="word-wrap:break-word:" width="250">'+eval("dataMap."+(cols[kk].column_name).toLowerCase())+'</td></tr>';
										
								  }
								  }
								  copy_data+= cols[kk].comments+"&&"+table_name+cols[kk].column_name+"&&"+cols[kk].data_type+"&&"+cols[kk].data_length+"&&"+cols[kk].nullable+"&&||";
								  upd_data +=cols[kk].comments+"&&"+table_name+cols[kk].column_name+"&&"+cols[kk].data_type+"&&"+cols[kk].data_length+"&&"+cols[kk].nullable+"&&"+eval("dataMap."+(cols[kk].column_name).toLowerCase())+"&&||";
								  columns+=	cols[kk].column_name+"|";
								  ss++;
							  }
						  }
						  contentString1='<tr> '+dataMap.mc_field1+'';
						  contentString+="<tr  style=\" background-color: #ffffff;\"><td  colspan=\"2\" align=\"right\"><span style='display:inline-block;float:right;' ><a onclick=\"copyInfo('"+table_name+"','"+columns+"','"+dataMap.sn+"','"+upd_data+"','show',"+msg.ifcreate+");\" style='width:30px;display:inline-block; text-align:right;cursor: hand;cursor: pointer;TEXT-DECORATION:underline;'>查看</a></span></td></tr></table>";
						
						  temparray[j].infowindow.close();
						  temparray[j].infowindow.setContent(contentString);
					 
						  temparray[j].infowindow.open(map,temparray[j]);//显示点击的点所对应的infowindow
						  temparray[j].infowindow.setZIndex(1000);
					}
				} 
				catch(exception)
				{
					
				}
				 
			} 
		}); 
		
		    
	});
	
	 	   
}

//function queryOneMarkerInfo(marker_sn,table_name)
//{
//	
//	var json = {};
//	var url="/map_query_queryOneMarkerInfo.action";
//	json["SN"]=marker_sn;
//	json["html_table_name"]=table_name;
//
//	$.ajax({
//		type : "POST",
//		cache : false,
//		async:true,
//		url : url,
//		dataType : "json",
//		data : json,
//		success : function(msg) {
//			try {
//				if(msg.if_success){
//					var cols=msg.colList;
//					var dataMap=msg.dataMap;
//					var contentString = "<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"4\" cellspacing=\"1\"  style=\"background-color: #d3d3d3; font-size: 12px; margin-top: 10px;\">";
//				    contentString+="<th colspan=\"6\"><span style=\"width:40%\"> <strong ><span><font style=\" font-size: 12px;font-weight: bold;\">详细信息</font></span></strong></span></th>";
//					var copy_data="";
//					var upd_data="";
//				    var columns="";
//				 
//					var contentString1="";
//					var ss=0;
//					for(var kk=0;kk<cols.length;kk++)//拼装infowindow显示值
//					{
//						  if(cols[kk].column_name!="MC_MARKERS"&&cols[kk].column_name!="SN"&&cols[kk].column_name!="STATUS_FLAG"&&cols[kk].column_name!="LAT"&&cols[kk].column_name!="LNG"&&cols[kk].column_name!="POST_FLAG")
//						  {
//							  if(ss==0)
//							  {
//								  contentString1='<tr> '+eval("dataMap."+(cols[kk].column_name).toLowerCase())+'</tr>';
//							  }
//							  if(ss<5)
//							  {
//							  if(cols[kk].data_type=="4")
//							  {
//								  contentString+='<tr style=" background-color: #ffffff;"><td  align="right" style="word-wrap:break-word:" width="165"><font style=" font-size: 12px;font-weight: bold;">'+cols[kk].comments+'</font></td><td align="left" style="word-wrap:break-word:" width="250"><img width=\"200\" height=\"150\"   src=\"'+eval("dataMap."+(cols[kk].column_name).toLowerCase())+'\"></td></tr>';
//									  
//							  }
//							  else
//							  {
//								  contentString+='<tr  style=" background-color: #ffffff;"><td align="right" style="word-wrap:break-word:" width="165"><font style=" font-size: 12px;font-weight: bold;">'+cols[kk].comments+'</font></td><td align="left" style="word-wrap:break-word:" width="250">'+eval("dataMap."+(cols[kk].column_name).toLowerCase())+'</td></tr>';
//									
//							  }
//							  }
//							  copy_data+= cols[kk].comments+"&&"+datakey+cols[kk].column_name+"&&"+cols[kk].data_type+"&&"+cols[kk].data_length+"&&"+cols[kk].nullable+"&&||";
//							  upd_data +=cols[kk].comments+"&&"+datakey+cols[kk].column_name+"&&"+cols[kk].data_type+"&&"+cols[kk].data_length+"&&"+cols[kk].nullable+"&&"+eval("dataMap."+(cols[kk].column_name).toLowerCase())+"&&||";
//							  columns+=	cols[kk].column_name+"|";
//							  ss++;
//						  }
//					  }
//					  contentString1='<tr> '+dataMap.mc_field1+'';
//					  contentString+="<tr  style=\" background-color: #ffffff;\"><td  colspan=\"2\" align=\"right\"><span style='display:inline-block;float:right;' ><a onclick=\"copyInfo('"+datakey+"','"+columns+"','"+dataMap.sn+"','"+upd_data+"','show','"+INDEXOF(marker_user_type,user_type)+"');\" style='width:30px;display:inline-block; text-align:right;cursor: hand;cursor: pointer;TEXT-DECORATION:underline;'>查看</a></span></td></tr></table>";
//					
//				}
//			} 
//			catch(exception)
//			{
//				
//			}
//			 
//		} 
//	}); 
//	
//}


function submitInfo(tablename,column,sn,type)
{
	var columns=column.split("|");
	columns.pop();
	var tempstring="";
	var string1="";
	var string2="";
	var filecolumn="";
	var obj=""
	for(var i=0;i<columns.length;i++)
	{
		if(type=="copy")
		{
			obj="#copyform";
			if(jQuery("#copy_"+tablename+columns[i]).attr("nullable")=="2")
			{
				if(jQuery("#copy_"+tablename+columns[i]).val().length==0)
				{
					$.messager.show({title: "信息",msg: jQuery("#copy_"+tablename+columns[i]).attr("data_comment")+"不能为空"}); 
					
					return
				}
				
			}
			
			if(jQuery("#copy_"+tablename+columns[i]).attr("data_type")!="4")
			{
				string1+=","+columns[i];
				string2+=",'"+jQuery("#copy_"+tablename+columns[i]).val()+"'";
			}
			else
			{
				filecolumn=columns[i];
			}
		}
		else if(type=="update")
		{
			obj="#modifyform";
			if(jQuery("#update_"+tablename+columns[i]).attr("nullable")=="2")
			{
				if(jQuery("#update_"+tablename+columns[i]).val().length==0)
				{
					$.messager.show({title: "信息",msg: jQuery("#update_"+tablename+columns[i]).attr("data_comment")+"不能为空"}); 
					return
				}
				
			}
			
			if(jQuery("#update_"+tablename+columns[i]).attr("data_type")!="4")
			{
				string2+=","+columns[i]+"='"+jQuery("#update_"+tablename+columns[i]).val()+"'"; 
			}
			else
			{
				filecolumn=columns[i];
			}
		}
	}

	var query_string =type+"|"+tablename+"|"+string1+"|"+string2+"|";
	var SN =sn;
	var urls="/map_query_CopyAndUpdate.action?query_string="+query_string+"&SN="+sn+"&query_operator="+filecolumn;
 		$(obj).form("submit",{
    	url: urls,
		onSubmit: function(){
			return $(this).form("validate");
		},
		success: function(result){
			var result=eval("("+result+")");
			if (result.if_success){
				if(type=="copy")
					{
						jQuery("#copy_dialog").dialog("close"); 
						jQuery("#show_dialog").dialog("close");
						$.messager.show({title: "信息",msg: result.msg}); 
				}
				else if(type=="update")
				{
					jQuery("#modify_dialog").dialog("close");
					jQuery("#show_dialog").dialog("close");
					$.messager.show({title: "信息",msg: result.msg}); 
				}  
				 
				 if(query_type=="1")
				 {
						map_query_1();
				 }
				 else
				 {
					 showDest(my_circle);
				 }
			} else {
				 
				$.messager.show({title: "信息",msg: result.msg}); 
				return;
			}
		},
		error: function(){
			$.messager.show({title: "信息",msg: 'error!!'}); 
		}
	});
	
}

function copyInfo(tablename,columns,sn,data,type,flag)//type:"copy" 复制窗口、"update"：修改窗口
{
	 
	var tr="";
	var datas=data.split("||");
	datas.pop();//去除空字符
	if(type=="copy")//打开复制信息窗口
	{
		$("#copy_table1").empty();
		$("#copy_table1").html("<tr></tr>");
		for(var i=0;i<datas.length;i++)
		{
			var tempdata=datas[i].split("&&");
			tempdata.pop();
			var tr="";
			if(tempdata[2]=="2")
			{
				tr= "<tr style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"：</td><td align='center' style='word-wrap:break-word:' width='360'><input type='text'  onkeyup='clearNoNum(this)' id='copy_"+tempdata[1]+"' data_type='"+tempdata[2]+"' data_comment='"+tempdata[0]+"' data_length='"+tempdata[3]+"' nullable='"+tempdata[4]+"' class='hidden_table_input' value=''/>" +"</td></tr>";
			}
			if(tempdata[2]=="1"||tempdata[2]=="3")
			{
				tr= "<tr style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"：</td><td align='center' style='word-wrap:break-word:' width='360'><input type='text' id='copy_"+tempdata[1]+"' data_type='"+tempdata[2]+"' data_comment='"+tempdata[0]+"' data_length='"+tempdata[3]+"' nullable='"+tempdata[4]+"' maxlength='"+tempdata[3]+"' class='hidden_table_input' value=''/>" +"</td></tr>";
			}
			if(tempdata[2]=="4")
			{
				tr= "<tr  style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>图片预览</td><td align='center' style='word-wrap:break-word:' width='360'><img id='preview' alt='' width='250' height='100'  src='' /> </td></tr>";
				tr+= "<tr  style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"：</td><td align='center' style='word-wrap:break-word:' width='360'>  <input id='copy_"+tempdata[1]+"' type='file' name='pic' data_type='"+tempdata[2]+"' data_comment='"+tempdata[0]+"' data_length='"+tempdata[3]+"' nullable='"+tempdata[4]+"' maxlength='"+tempdata[3]+"' onchange='change(this)' accept='image/*' />" +"</td></tr>";
				tr+="	<input id='filename' type='hidden' name='filename'/>";
			}
			$("#copy_table1 tr:last").after(tr);
		}
	 
		var copy_button_tr="<tr  style='background-color: #f0f3f5;' ><td colspan=\"2\"  align='center' ><input type='button'  value='保存' onclick=\"submitInfo('"+tablename+"','"+columns+"','"+sn+"','copy');\" /></td></tr>";
		 
		$("#copy_table1 tr:last").after(copy_button_tr);
		jQuery("#copy_dialog").dialog("open");
	}
	else if(type=="update")
	{
 
		$("#modify_table1").empty();
		$("#modify_table1").html("<tr></tr>");
		for(var i=0;i<datas.length;i++)
		{
			var tempdata=datas[i].split("&&");
			tempdata.pop();
			var tr="";
			if(tempdata[2]=="2")
			{
				tr="<tr style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"：</td><td align='center' style='word-wrap:break-word:' width='360'><input type='text'  onkeyup='clearNoNum(this)' id='update_"+tempdata[1]+"' data_type='"+tempdata[2]+"' data_length='"+tempdata[3]+"' data_comment='"+tempdata[0]+"' nullable='"+tempdata[4]+"' class='hidden_table_input' value='"+tempdata[5]+"'/>" +"</td></tr>";
			}
			if(tempdata[2]=="1"||tempdata[2]=="3")
			{
				tr="<tr style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"：</td><td align='center' style='word-wrap:break-word:' width='360'><input type='text'   id='update_"+tempdata[1]+"' data_type='"+tempdata[2]+"' data_length='"+tempdata[3]+"' data_comment='"+tempdata[0]+"' nullable='"+tempdata[4]+"' maxlength='"+tempdata[3]+"' class='hidden_table_input' value='"+tempdata[5]+"'/>" +"</td></tr>";
			}
			if(tempdata[2]=="4")
			{
				tr= "<tr  style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>图片预览</td><td align='center' style='word-wrap:break-word:' width='360'><img id='preview' alt=''   width='250' height='100'  src='"+tempdata[5]+"' /> </td></tr>";
				tr+= "<tr  style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"：</td><td align='center' style='word-wrap:break-word:' width='360'>  <input id='update_"+tempdata[1]+"' type='file' name='pic' data_type='"+tempdata[2]+"' data_comment='"+tempdata[0]+"' data_length='"+tempdata[3]+"' nullable='"+tempdata[4]+"' maxlength='"+tempdata[3]+"' onchange='change(this)' accept='image/*' />" +"</td></tr>";
				tr+="	<input id='filename' type='hidden' name='filename'/>";
			}
			$("#modify_table1 tr:last").after(tr);
		}
	
	  
	 
		 var upd_buttion_tr="<tr  style='background-color: #f0f3f5;' ><td colspan=\"2\"  align='center'><input type='button'  value='修改' onclick=\"submitInfo('"+tablename+"','"+columns+"','"+sn+"','update');\" /></td></tr>";
			
		$("#modify_table1 tr:last").after(upd_buttion_tr);
		jQuery("#modify_dialog").dialog("open");
	}
	else if(type=="show")
	{
 
		$("#show_table1").empty();
		$("#show_table1").html("<tr></tr>");
		for(var i=0;i<datas.length;i++)
		{
			var tempdata=datas[i].split("&&");
			tempdata.pop();
			var tr="";
		 
			tr="<tr  style='height:30px; margin-bottom:5px;  background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'>"+tempdata[0]+"</td><td align='center' style='word-wrap:break-word:' width='360'>"+tempdata[5] +"</td></tr>";
		 
			if(tempdata[2]=="4")
			{
				tr= "<tr  style='height:30px; margin-bottom:5px; background-color: #f0f3f5;'><td align='right' style='word-wrap:break-word:' width='165'> "+tempdata[0]+"</td><td align='center' style='word-wrap:break-word:' width='360'><img id='preview' alt=''   width='250' height='100'  src='"+tempdata[5]+"' /> </td></tr>";
		 	}
			$("#show_table1 tr:last").after(tr);
		}
	
	  
	 
		 var upd_buttion_tr="";
		 if(flag==true)
		 {
			 upd_buttion_tr="<tr style='background-color: #f0f3f5;' ><td colspan=\"2\" align='center'><a onclick=\"delete233('"+tablename+"','"+sn+"');\" style='width:30px;display:inline-block; text-align:right;cursor: hand;cursor: pointer;TEXT-DECORATION:underline;'>删除</a><a onclick=\"copyInfo('"+tablename+"','"+columns+"','"+sn+"','"+data+"','update');\" style='width:30px;display:inline-block; text-align:right;cursor: hand;cursor: pointer;TEXT-DECORATION:underline;'>修改</a><a onclick=\"copyInfo('"+tablename+"','"+columns+"','"+sn+"','"+data+"','copy');\" style='display:inline-block;width:30px; text-align:right;cursor: hand;cursor: pointer;TEXT-DECORATION:underline;'>复制</a></td></tr>";
		 }
		$("#show_table1 tr:last").after(upd_buttion_tr);
	//	$("#show_table2 tr:last").after(upd_buttion_tr);
		jQuery("#show_dialog").dialog("open");
	}
	
	 
}



function toQuery(sn){//弹出条件选择页面 
    var iWidth=600;                          //弹出窗口的宽度;
    var iHeight=350;                         //弹出窗口的高度;
    var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置   
    var iLeft = (window.screen.availWidth-10-iWidth)/2;       //获得窗口的水平位置    
	var attribution ="dialogLeft:"+iLeft+"px;dialogTop:"+iTop+"px;"+"toolbar:no;location:no;directories:no;status:no;menubar:no;Scroll:yes;resizable:yes;dialogheight:350px;dialogwidth:600px";
	var url ="/map_query_toQueryColumn.action?SN="+sn.split("#")[0];
	window.open (url,'newwindow','height=350,width=600,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
}

function delete233(table_name,sn)
{
	var url="/map_query_deleteData.action";
	var json = {};
	json["query_string"]=table_name;
	json["SN"]=sn;
	$.ajax({//调用后台
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
					if(msg.if_success)
					{
						
						 $.messager.show({title: "信息",msg: msg.msg}); 
						 jQuery("#show_dialog").dialog("close");
						 if(query_type=="1")
						 {
								map_query_1();
						 }
						 else
						 {
							 showDest(my_circle);
						 }
					}
				}
				catch (exception) {
				 
					$.messager.show({title: "信息",msg: exception}); 
				}
			},
			error : function(msg) {
			 
				$.messager.show({title: "信息",msg: 'error!!'}); 
			}
		});
}





