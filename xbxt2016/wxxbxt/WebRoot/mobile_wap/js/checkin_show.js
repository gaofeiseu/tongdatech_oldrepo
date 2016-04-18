function ljhx_reloadscreen(){
	if(phone_markerpath_array.length>0&&phone_markerpath_array!=null&&phone_markerpath_array!=undefined){
		for(var i in phone_markerpath_array){
			phone_markerpath_array[i].setMap(null);
		}
	}
}
var phone_markerpath_array=[];

function query_ljhx_data(){
	var st_time = '';
	var ed_time = '';
	var query_role_type = '';
	var query_loginname = '';
	var query_brchno = '';
	var query_location_type = '';
	var query_zoufang_type = '';
	var if_include_childbrch = '';//1表示包括	0表示不包括
	var if_success = false;
	var iconurl="/mapfiles/markers/purple_point.png";
	$("#time_show_str").html("");
	$("#time_show_str2").html("");
	console.log("root------------>"+root);
	var s_s=''; 
	$('input[name="ljhx_radiobutton"]:checked').each(function(){
		s_s+=$(this).val()+',';
	});
	if(s_s.length>0){
		s_s = s_s.substring(0,s_s.length-1);
	}
	if(s_s==''){
		alert('你没有选择任何内容！');
		return;
	}
	var arr_s_s = [];
	arr_s_s = s_s.split(',', 10);//包含的所有选择了个checkbox的value
	for(var i = 0;i<arr_s_s.length;i++){
		if(arr_s_s[i]=='1'){
			st_time = $('#ljhx_query_timeST').datebox('getValue');//   7/6/2014 11:08:25
			ed_time = $('#ljhx_query_timeED').datebox('getValue');
			
			if(st_time==''||st_time==undefined||st_time==null||
					ed_time==''||ed_time==undefined||ed_time==null){
				alert('按时间查询时，开始时间和结束时间均不能为空！');
				return;
			}else{
				if(Number(st_time)>Number(ed_time)){
					alert('开始时间不允许大于结束时间！');
					return;
				}else{
					if_success=true;
					
				}
			}
		}else if(arr_s_s[i]=='2'){
			query_role_type = $('#ljhx_selectbutton').combobox('getValue');
			console.log('query_role_type----------->'+query_role_type);
			if(query_role_type==''||query_role_type==undefined||query_role_type==null){
				alert('并未选择想要查询的类型！');
				return;
			}
			if_success=true;
		}else if(arr_s_s[i]=='3'){
			query_loginname = $('#ljhx_loginname_query').val();
			if(query_loginname==''||query_loginname==undefined||query_loginname==null){
				alert('按登录名查询时，查询的登录名不能为空！');
				return;
			}else{
				if_success=true;
			}
		}else if(arr_s_s[i]=='4'){
			//ljhx_brchno_query
			query_brchno = $('#ljhx_brchno_query').combotree("getValue");
			if(query_brchno==''||query_brchno==undefined||query_brchno==null){
				alert('按所属机构查询时，查询的机构不能为空！');
				return;
			}else{
				if($('#if_include_childbrch').attr("checked")=="checked"){
					if_include_childbrch = '1';
				}else{
					if_include_childbrch = '0';
				}
				if_success=true;
			}
		}
		else if(arr_s_s[i]=='5'){
			//定位种类查询
			query_location_type = $('#ljhx_location_type').combotree("getValue");
			if(query_location_type==''||query_location_type==undefined||query_location_type==null){
				alert('按定位种类查询时，定位种类不能为空！');
				return;
			}else{
				if_success=true;
			}
		}
		else if(arr_s_s[i]=='6'){
			//定位种类查询
			query_zoufang_type = $('#ljhx_zoufang_type').combotree("getValue");
			if(query_zoufang_type==''||query_zoufang_type==undefined||query_zoufang_type==null){
				alert('按走访形式查询时，走访形式不能为空！');
				return;
			}else{
				if_success=true;
			}
		}
		else{
			console.log('奇怪的事情发生~\(≧▽≦)/~啦啦啦，checkin_show.js');
		}
	}
	$("#detail_table").hide();
	$("#hidden_detail_info").hide();
	$("#show_msg_s").hide();
	if(if_success){
		var url="/mobileCheckinShow_query.action";
		var json = {};
		//准备传送到后台的子类型的相关数据
		json["ljhx_query_type"]=s_s;//路径回显的查询类型
		json["root_brch"]=root;
		for(var i = 0;i<arr_s_s.length;i++){
			if(arr_s_s[i]=='1'){
				json["ljhx_time_st"]=st_time;//时间查询的开始时间
				json["ljhx_time_ed"]=ed_time;//时间查询的结束时间
				$("#time_show_str").html("时间段：<br>&nbsp&nbsp"+st_time+"<br>到"+ed_time);
				$("#time_show_str2").html("时间段："+st_time+"到"+ed_time);
			}else if(arr_s_s[i]=='2'){
				json["ljhx_query_role_type"] = query_role_type;//部门类型查询
			}else if(arr_s_s[i]=='3'){
				json["ljhx_query_loginname"] = query_loginname;
			}else if(arr_s_s[i]=='4'){
				json["ljhx_query_brchno"] = query_brchno;
				json["if_include_childbrch"] = if_include_childbrch;
			}else if(arr_s_s[i]=='5'){
				json["ljhx_location_type"] = query_location_type;
			}else if(arr_s_s[i]=='6'){
				json["query_zoufang_type"] = query_zoufang_type;
			}
			else{
				return;
			}
		}
		
		$.ajax({
			type : "POST",
			cache : false,
			async:true,
			url : url,
			dataType : "json",
			data : json,
			success : function(msg) {
				ljhx_reloadscreen();
				try {
					if(msg.if_success){
						var pathpoint=[];
						pathpoint=msg.list_map;
						var lat_arr = [];
						var lon_arr = [];
						for(var o = 0; o < pathpoint.length; o++){
							if(pathpoint[o].check_in_lat!=undefined&&pathpoint[o].check_in_lng!=undefined
									&&pathpoint[o].check_in_lat!=""&&pathpoint[o].check_in_lng!=""
									&&pathpoint[o].check_in_lat!=null&&pathpoint[o].check_in_lng!=null){
								lat_arr.push(pathpoint[o].check_in_lat);
								lon_arr.push(pathpoint[o].check_in_lng);
							}else{
								continue;
							}
							var phonepoint = new google.maps.LatLng(pathpoint[o].check_in_lat,pathpoint[o].check_in_lng);
//							console.log('lat-->'+phonepoint.lat()+'		lng--->'+phonepoint.lng());
							var s_title = '签到人：'+pathpoint[o].login_name+'\n'
								+'昵称：'+pathpoint[o].nick_name+'\n'
								+'走访类型：'+pathpoint[o].visit_type_str+'\n'
								+'走访客户名：'+pathpoint[o].visit_custom_name+'\n'
								+'走访种类：'+pathpoint[o].visit_class_str+'\n'
								+'走访内容：'+pathpoint[o].visit_content+'\n'
								+'走访备注：'+pathpoint[o].visit_note+'\n'
								+'走访时间：'+trimTimeFunc(pathpoint[o].check_in_time)+'\n'
								+'签到方式：'+pathpoint[o].check_in_type_str;
//							console.log(s_title);
							if(map){
								var marker = new google.maps.Marker({
							        position: phonepoint,
							        map: map,
							        draggable:false,
							        icon:(pathpoint[o].user_icon==''||pathpoint[o].user_icon==null||pathpoint[o].user_icon==undefined)?(iconurl):(pathpoint[o].user_icon),
							        title:s_title
								});
								
								phone_markerpath_array.push(marker);
							}
							else{
								console.log("map不对！！！！！！！！！！！！");
							}
						}
//						console.log("my_lat:"+my_lat+"			my_lon:"+my_lon);
//						var ii = findRightPoint(my_lat,my_lon,lat_arr,lon_arr);
//						var myLatlng = new google.maps.LatLng(pathpoint[ii].lat,pathpoint[ii].lon);
						
						map.setCenter(new google.maps.LatLng(pathpoint[0].check_in_lat,pathpoint[0].check_in_lng));
						$("#echo_query_msg").text("查询成功，共有"+pathpoint.length+"个路径点。报表部分异步获取中，请稍后。");
						getTableWithAjax(pathpoint,msg.returnString);
						
					}else{
						alert(msg.msg);
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
}

function getTableWithAjax(pathpoint,sql){
	var url="/mobileCheckinShow_getTableWithAjax.action";
	var json = {};
	json["sql"]=sql;
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
					//first table
					
					$("#hidden_table1").empty();
					if(pathpoint.length>0){
						$("#show_msg_s").show();
						var row = "<tr><th>走访方式</th><th>签到次数</th></tr>";
						$("#hidden_table1").append(row);
						var list_detail_map = [];
						list_detail_map = msg.list_detail_map;
						var ii_num = 0;
						for(var ii=0;ii<list_detail_map.length;ii++){
							row = "<tr><td>"+list_detail_map[ii].visit_class_str+"</td><td>"+list_detail_map[ii].num+"</td></tr>";
							$("#hidden_table1").append(row);
							ii_num += Number(list_detail_map[ii].num);
						}
						row = "<tr><td>合计</td><td>"+ii_num+"</td></tr>";
						$("#hidden_table1").append(row);
						$("#detail_table").show();
					}
					
					//second table
					
					$("#hidden_table2").empty();
					if(pathpoint.length>0){
						var list_more_detail_title = [];
						list_more_detail_title = msg.list_more_detail_title;
						var row = "<tr>";
						for(var i2 = 0;i2<list_more_detail_title.length;i2++){
							row += "<th>"+list_more_detail_title[i2]+"</th>";
						}
						row += "</tr>";
						$("#hidden_table2").append(row);
						var list_more_detail_map = [];
						list_more_detail_map = msg.list_more_detail_map;
						var list_heji = [];
						list_heji.push(0);
						for(var i6=0;i6<list_more_detail_title.length-2;i6++){
							list_heji.push(0);
						}
						for(var i3 = 0;i3<list_more_detail_map.length;i3++){
							var more_detail_map = {};
							more_detail_map = list_more_detail_map[i3];
							row = "<tr><td>"+more_detail_map["name"]+"</td>";
							row += "<td>"+more_detail_map["nick_name"]+"</td>";
							row += "<td>"+more_detail_map["xiao_ji"]+"</td>";
							list_heji[0] = list_heji[0]+Number(more_detail_map["xiao_ji"]);
							for(var i4=0;i4<list_more_detail_title.length-3;i4++){
								var ss_tt = more_detail_map["tmp_"+i4.toString()];
								if(ss_tt){
									row += "<td>"+more_detail_map["tmp_"+i4.toString()]+"</td>";
									list_heji[i4+1] = list_heji[i4+1]+Number(more_detail_map["tmp_"+i4.toString()]);
								}else{
									row += "<td>0</td>";
								}
							}
							row += "</tr>";
							$("#hidden_table2").append(row);
						}
						row = "<tr><td>合计</td>";
						row += "<td></td>";
						for(var i7=0;i7<list_heji.length-1;i7++){
							row += "<td>"+list_heji[i7]+"</td>";
						}
						row += "</tr>";
						$("#hidden_table2").append(row);
						$("#hidden_detail_info").show();
					}
				}else{
					alert(msg.msg);
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


function findRightPoint(o_lat,o_lon,lat_arr,lon_arr){
	var radio_length_arr = [];
	for ( var i = 0; i < lat_arr.length; i++) {
		radio_length_arr.push((lat_arr[i]-o_lat)*(lat_arr[i]-o_lat)+(lon_arr[i]-o_lon)*(lon_arr[i]-o_lon));
	}
	var ii = findMinRadio(radio_length_arr);
	return ii;
}

function findMinRadio(radio_arr){
	Array.prototype.min = function(){
		return Math.min.apply({},this);
	};
	var min_n = radio_arr.min();
	var n = 0;
	for ( var i = 0; i < radio_arr.length; i++) {
		if(radio_arr[i]==min_n){
			n = i;
			console.log(radio_arr[i]+"=="+min_n);
		}else if(radio_arr[i]>min_n){
			console.log(radio_arr[i]+">"+min_n);
		}else{
			console.log(radio_arr[i]+"<"+min_n);
		}
	}
	return n;
}

function trimTimeFunc(mark_time){
	//20140728172412
	if(mark_time==''||mark_time==undefined||mark_time==null){
		return "无";
	}else{
		try {
			return mark_time.substring(0,4)+'年'+mark_time.substring(4,6)+'月'+mark_time.substring(6,8)+'日'+mark_time.substring(8,10)+'时'+mark_time.substring(10,12)+'分'+mark_time.substring(12,14)+'秒';
		} catch (e) {
			return "无";
		}
	}
}
